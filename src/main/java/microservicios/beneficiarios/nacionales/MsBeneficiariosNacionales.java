package microservicios.beneficiarios.nacionales;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.RequestMicroServiciosIBP;
import microservicios.Utilidad;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 19/02/2024 4:17 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsBeneficiariosNacionales {
    private Response response;
    private JsonNode allBeneficiarios;
    private JsonNode beneficiarios;
    private JsonNode beneficiario;
    String documentNumber = "";
    private int statusCode =0;

    public MsBeneficiariosNacionales(String documentNumber){
        this.documentNumber = documentNumber;
    }

    public MsBeneficiariosNacionales newQuery(){
        response = RequestMicroServiciosIBP.newQuery().beneficiariosNacionales(documentNumber);
        allBeneficiarios = Utilidad.jsonNodeParse(response);
        return this;
    }

    public int getStatusCode(){
        if (statusCode == 0){
            newQuery();
        }
        return response.getStatusCode();
    }



    public String getErrorMessage(){
        return response.getStatusCode() + ": " + response.getBody().asString();
    }

    public MsBeneficiariosNacionales selecBHD(){
        beneficiarios = null;
        if (!allBeneficiarios.isEmpty()){
            beneficiarios = allBeneficiarios.get("bhd");
        }
        return this;
    }

    public MsBeneficiariosNacionales buscar(String accountNumber){
        if (!beneficiarios.isEmpty()){

            for (JsonNode benef : beneficiarios){
                if (benef.get("accountNumber").asText().equalsIgnoreCase(accountNumber)){
                    beneficiario = benef;
                    break;
                }else {
                    beneficiario = null;
                }
            }
        }
        return this;
    }


    public String getNumProducto(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("accountNumber").asText();
        }
        return texto;
    }

    public String getId(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("id").asText();
        }
        return texto;
    }

    public String getNumProductoEnmascarado(){
        String texto = "null";
        if (beneficiario != null){
            if (getTipoCuentaSigla().contains("TAC")){
                texto = Utilidad.enmascararTC(beneficiario.get("accountNumber").asText());
            }else {
                texto = beneficiario.get("accountNumber").asText();
            }
        }
        return texto;
    }

    /** Retorna el tipo de producto. Si el producto buscado no existe retorna el texto: null
     * @return
     */
    public String getTipoCuenta(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("accountType").asText();
            texto = texto.replace("CUA","Ahorros")
                    .replace("PRE","Préstamos")
                    .replace("CUC","Corriente")
                    .replace("TAC","Tarjeta de crédito");
        }
        return texto;
    }

    public String getTipoCuentaSigla(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("accountType").asText();
        }
        return texto;
    }

    public boolean esUnaTC(){
        boolean isTC = false;
        if (getTipoCuentaSigla().equalsIgnoreCase("TAC")){
            isTC = true;
        }
        return isTC;
    }



    public String getCorreo(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("email").asText();
        }
        return texto;
    }

    public String getNombre(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("name").asText();
        }
        return texto;
    }

    public String getNombreBanco(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("bankName").asText();
        }
        return texto;
    }

    public String getAlias(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("favoriteAccountAlias").asText();
        }
        return texto;
    }

    public String getPais(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("address").asText();
        }
        return texto;
    }

    public String getMoneda(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("currency").asText();
        }
        return texto;
    }



    @Override
    public String toString() {
        return "MsBeneficiariosNacionales{" +
                "beneficiario=" + beneficiario +
                '}';
    }

    public MsBeneficiariosNacionales selecOtrosBancos(){
        beneficiarios = null;
        if (!allBeneficiarios.isEmpty()){
            beneficiarios = allBeneficiarios.get("otherBanks");
        }
        return this;
    }

    public MsBeneficiariosNacionales selecPinPesos(){
        beneficiarios = null;
        if (!allBeneficiarios.isEmpty()){
            beneficiarios = allBeneficiarios.get("pinPesos");
        }
        return this;
    }

}
