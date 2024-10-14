package servicios.micros.accesos.beneficiarios;

import com.fasterxml.jackson.databind.JsonNode;

public class BeneficiariosTerceroOtrosBancos {

    private int statusCode = 0;
    private MicroBeneficiariosNacionales micro = null;
    private JsonNode beneficiarios = null;
    private boolean beneficiarioExiste = false;
    private boolean existError = false;
    private String mensaje = "";


    private String nombreBanco = "bankName";
    private String tipoProductSigla = "accountType";
    private String numProducto = "productNumber";
    private String tipoDocumento = "bhdLBTRDocumentType";
    private String numDocumento = "documentId";
    private String nombreBeneficiario = "name";
    private String aliasBeneficiario = "favoriteAccountAlias";
    private String paisSigla = "nationality";
    private String genero = "gender";
    private String correo = "email";
    private String modenaSigla = "currency";


    public BeneficiariosTerceroOtrosBancos(MicroBeneficiariosNacionales micro){
        this.micro = micro;
        statusCode = micro.statusCode;
        if (statusCode == 200){
            beneficiarios = micro.beneficiarios.get("otherBanks");
        }
    }

    public String getTipoProductoCombo(){
        return tipoProductSigla.replace("Prestamos","Préstamos")
                .replace("Tarjetas de Credito","Tarjetas de Crédito")
                .replace("CUA","Cuentas de Ahorro")
                .replace("PRE","Préstamos");
    }
    public String getTipoProductoLabel(){
        return tipoProductSigla.replace("Cuentas de Ahorro","Ahorros")
                .replace("CUA","Ahorros")
                .replace("Cuentas Corrientes","Corriente")
                .replace("PRE","Préstamos")
                .replace("Tarjetas de Credito","Tarjeta de crédito");
    }

    // label: Ahorros            - Corriente          - Préstamos - Tarjeta de crédito
    // combo: Cuentas de Ahorro  - Cuentas Corrientes - Préstamos - Tarjetas de Crédito




    // Canal: Cuentas de Ahorro
    // micro:
    public String getTipoProducto() {// No remplazar: cc-
        return tipoProductSigla.replace("Prestamos","Préstamos");
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public String getNumProducto() {
        return numProducto;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public String getAliasBeneficiario() {
        return aliasBeneficiario;
    }

    public String getCorreo() {
        return correo;
    }

    public BeneficiariosTerceroOtrosBancos buscar(String banco, String numProducto){
        beneficiarioExiste = false;
        if (statusCode == 200){

            if (beneficiarios.size()> 0){
                for (JsonNode beneficiario : beneficiarios){
                    if (beneficiario.get("bankName").asText().contains(banco) && beneficiario.get("productNumber").asText().equals(numProducto)){

                        nombreBanco = beneficiario.get("bankName").asText();
                        tipoProductSigla = beneficiario.get("accountType").asText();
                        this.numProducto = beneficiario.get("productNumber").asText();
                        tipoDocumento = beneficiario.get("bhdLBTRDocumentType").asText();
                        numDocumento = beneficiario.get("documentId").asText();
                        nombreBeneficiario = beneficiario.get("name").asText();
                        aliasBeneficiario = beneficiario.get("favoriteAccountAlias").asText();
                        paisSigla = "nationality";
                        genero = beneficiario.get("gender").asText();
                        correo = beneficiario.get("email").asText();
                        modenaSigla = beneficiario.get("currency").asText();
                        beneficiarioExiste = true;
                        break;
                    }
                }

                if (!beneficiarioExiste){
                    mensaje = "El beneficiario de otro banco " + numProducto + " no existe en el usuario: " + micro.cedula;
                    existError = true;
                }
            }else {
                mensaje = "No se encontraron beneficiarios de otros bancos en el usuario: " + micro.cedula;
                existError = true;
            }

        }else {
            mensaje = "Usuario : "+ micro.cedula + ", no posee beneficiarios o ha ocurrido un error. StatusCode: " + statusCode;
            existError = true;
        }
        if (existError){
            setError(mensaje, false);
        }
        return this;
    }

    public void setError( String msgError, boolean beneficiarioExiste) {
        this.beneficiarioExiste = beneficiarioExiste;
        this.nombreBanco = msgError;
        this.tipoProductSigla = msgError;
        this.numProducto = msgError;
        this.tipoDocumento = msgError;
        this.numDocumento = msgError;
        this.nombreBeneficiario = msgError;
        this.aliasBeneficiario = msgError;
        this.paisSigla = msgError;
        this.genero = msgError;
        this.correo = msgError;
        this.modenaSigla = msgError;
    }
}
