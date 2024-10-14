package servicios.micros.accesos.beneficiarios;

import com.fasterxml.jackson.databind.JsonNode;

public class BeneficiarioPinPeso {
    private JsonNode beneficiarios = null;
    private MicroBeneficiariosNacionales micro = null;




    private int status = 0;
    private String alias = "favoriteAccountAlias";
    private String nombreBeneficiario = "name";
    private String numDocumentoBeneficiario = "bhdLBTRDocumentNumber";
    private String telefonoBeneficiario = "productNumber";
    private String nombreBancoLabel = "bankName";



    private boolean existError = false;
    private String cedula = "";
    private boolean beneficiarioExiste = false;
    private String mensaje = "";



    private void setCampos(String msgError, boolean beneficiarioExiste) {
        this.alias = msgError;
        this.nombreBeneficiario = msgError;
        this.numDocumentoBeneficiario = msgError;
        this.telefonoBeneficiario = msgError;
        this.nombreBancoLabel = msgError;
        this.beneficiarioExiste = beneficiarioExiste;
    }

    public String getNombreActualBeneficiario() {
        if ( alias.equals("") ){
            return nombreBeneficiario;
        }else {
            return alias;
        }
    }

    public int getStatus() {
        return status;
    }

    public String getAlias() {
        return alias;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public String getNumDocumentoBeneficiario() {
        return numDocumentoBeneficiario;
    }

    public String getTelefonoBeneficiario() {
        return telefonoBeneficiario;
    }

    public String getNombreBancoLabel() {
        return nombreBancoLabel;
    }

    public boolean isBeneficiarioExiste() {
        return beneficiarioExiste;
    }

    public BeneficiarioPinPeso(MicroBeneficiariosNacionales micro){
        this.micro = micro;
        cedula = micro.cedula;
        if (micro.statusCode == 200){

            status = micro.statusCode;
            beneficiarios = micro.beneficiarios.get("pinPesos");

        }
    }

    public BeneficiariosTerceroBHD selectBeneficiariosBHD(){
        return new BeneficiariosTerceroBHD(micro);
    }

    public BeneficiariosTerceroOtrosBancos selectBeneficiariosOtrosBancos(){
        return new BeneficiariosTerceroOtrosBancos(micro);
    }

    public BeneficiarioPinPeso buscarBeneficiario(String telefono){
        beneficiarioExiste = false;
        if (status == 200 ){

            if (beneficiarios.size() > 0){

                for (JsonNode benefic : beneficiarios){
                    if (benefic.get("productNumber").asText().equals(telefono)){

                        alias = benefic.get("favoriteAccountAlias").asText();
                        nombreBeneficiario = benefic.get("name").asText();
                        numDocumentoBeneficiario = benefic.get("bhdLBTRDocumentNumber").asText();
                        telefonoBeneficiario = benefic.get("productNumber").asText();
                        nombreBancoLabel = benefic.get("bankName").asText();
                        beneficiarioExiste = true;
                        break;
                    }
                }

                if (!beneficiarioExiste){
                    mensaje = "El beneficiario PIN Pesos " + telefono + " no existe en el usuario: " + cedula;
                    existError = true;
                }

            }else {
                mensaje = "No se encontraron beneficiarios PIN Pesos en el usuario: " + cedula;
                existError = true;
            }
        }else {
            mensaje = "Usuario : "+ cedula + ", no posee beneficiarios o ha ocurrido un error. StatusCode: " + status;
            existError = true;
        }

        if (existError){
            setCampos(mensaje, false);
        }
        return this;
    }


}
