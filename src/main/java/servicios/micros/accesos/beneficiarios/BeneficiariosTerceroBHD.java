package servicios.micros.accesos.beneficiarios;

import com.fasterxml.jackson.databind.JsonNode;
import util.Utilidad;

public class BeneficiariosTerceroBHD {

    private JsonNode beneficiariosBHD = null;
    private boolean beneficiarioExiste = false;
    private String mensaje = "";
    private boolean existError = false;
    private String cedula = "";
    private MicroBeneficiariosNacionales micro = null;




    private  int statusCode = 0;
    private String nombreBanco = "";
    private String numeroProducto = "";
    private String nombreBeneficiario = "";
    private String alias = "";
    private String correo = "";
    private String tipoProducto = "";

    public BeneficiariosTerceroBHD(MicroBeneficiariosNacionales micro){
        this.micro = micro;
        statusCode = micro.statusCode;
        cedula = micro.cedula;

        if (micro.statusCode == 200 ){
            beneficiariosBHD = micro.beneficiarios.get("bhd");
        }
    }
    public BeneficiariosTerceroOtrosBancos selectBeneficiariosOtrosBancos(){
        return new BeneficiariosTerceroOtrosBancos(micro);
    }
    public BeneficiariosTerceroBHD(){

    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public String getNumeProduct() {
        String numProducto = numeroProducto;
        if (tipoProducto.contains("Tarjeta")){
            numProducto = Utilidad.enmascararTC(numeroProducto);
        }
        return numProducto;
    }

    public String getNumProductSinEnmascarar() {
        return numeroProducto;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public String getAlias() {
        return alias;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    @Override
    public String toString() {
        return "BeneficiarioBHD\n" +
                "Nombre Banco: " + nombreBanco +
                "\nNumero Producto: " + numeroProducto +
                "\nNombre Beneficiario: " + nombreBeneficiario +
                "\nAlias: " + alias  +
                "\nCorreo: " + correo;
    }

    public BeneficiariosTerceroBHD buscarBeneficiario(String numeroProducto){
        beneficiarioExiste = false;
        if (statusCode == 200){

            if (beneficiariosBHD.size() > 0){

                for (JsonNode beneficiario: beneficiariosBHD){
                    if (beneficiario.get("productNumber").asText().equals(numeroProducto)){

                        nombreBanco = beneficiario.get("bankName").asText();
                        this.numeroProducto = beneficiario.get("productNumber").asText();
                        nombreBeneficiario = beneficiario.get("name").asText();
                        alias = beneficiario.get("favoriteAccountAlias").asText();
                        correo = beneficiario.get("email").asText();
                        tipoProducto = beneficiario.get("accountType").asText();

                        beneficiarioExiste = true;
                        break;
                    }
                }
                if (!beneficiarioExiste){
                    mensaje = "El beneficiario BHD: " + numeroProducto + ", no existe en el usuario: " + cedula;
                    existError = true;
                }
            }else {
                mensaje = "No se encontraron beneficiarios BHD en el usuario: " + cedula;
                existError = true;
            }
        }else {
            mensaje = "El usuario : "+ cedula + ", no posee beneficiarios BHD o ha ocurrido un error. StatusCode: " + statusCode;
            existError = true;
        }

        if (existError){
            setError(mensaje);
        }
        return this;
    }

    private void setError(String error) {
        this.nombreBanco = error;
        this.numeroProducto = error;
        this.nombreBeneficiario = error;
        this.alias = error;
        this.correo = error;
        this.tipoProducto = error;
    }
}
