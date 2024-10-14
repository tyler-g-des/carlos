package microservicios.beneficiarios.internacionales;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.RequestMicroServiciosIBP;
import microservicios.Utilidad;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 01/03/2024 4:04 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsBeneficiariosInternacionales {

    private Response response;
    private JsonNode beneficiarios;
    private JsonNode beneficiario;
    private String documentNumber = "";
    private int statusCode = 0;

    public MsBeneficiariosInternacionales(String documentNumber){
        this.documentNumber = documentNumber;
    }

    public MsBeneficiariosInternacionales newQuery(){
        response = RequestMicroServiciosIBP.newQuery().getBeneficiariosInternacionales(documentNumber);
        beneficiarios = Utilidad.jsonNodeParse(response);
        return this;
    }

    public MsBeneficiariosInternacionales buscar(String accountNumber){
        if (!beneficiarios.isEmpty()){
            for (JsonNode benef : beneficiarios){
                if (benef.get("productNumber").asText().equalsIgnoreCase(accountNumber)){
                    beneficiario = benef;
                    break;
                }else {
                    beneficiario = null;
                }
            }
        }
        return this;
    }

    public int getStatusCode(){
        if (statusCode == 0){
            newQuery();
        }
        return response.getStatusCode();
    }

    public String getTipoCodigo(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("bankCodeType").asText();
            if (texto.equals("2")){
                texto = "SWIFT";
            }else if (texto.equals("1")){
                texto = "ABA";
            }
        }
        return texto;
    }

    public String getTipoCodigoIntermediario(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("bankCodeTypeInter").asText();
            if (texto.equals("2")){
                texto = "SWIFT";
            }else if (texto.equals("1")){
                texto = "ABA";
            }
        }
        return texto;
    }

    public String getNombreBeneficiario(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("beneficiaryNames").asText();
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

    public String getnombreBanco(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("bankName").asText();
        }
        return texto;
    }

    public String getnombreBancoIntermediario(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("bankNameInter").asText();
        }
        return texto;
    }

    public String getNumSwift(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("numSwift").asText();
        }
        return texto;
    }

    public String getNumSwiftIntermediario(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("numSwiftInter").asText();
        }
        return texto;
    }

    public String getEmailBeneficiario(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("beneficiaryEmail").asText();
        }
        return texto;
    }

    public String getNumProducto(){
        String texto = "null";
        if (beneficiario != null){
            texto = beneficiario.get("productNumber").asText();
        }
        return texto;
    }

    public String getCalle(){
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
}
