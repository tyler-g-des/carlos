package microservicios.beneficiarios.internacionales;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.RequestMicroServiciosIBP;
import microservicios.Utilidad;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/03/2024 8:20 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsConsultarCodigoInternacional {

    private String documentNumber = "";
    private Response response;
    private JsonNode data;

    public MsConsultarCodigoInternacional(String documentNumber){
        this.documentNumber = documentNumber;
    }

    public MsConsultarCodigoInternacional newQuery(String tipoCodigo, String codigo){
        response = RequestMicroServiciosIBP.newQuery().consultarCodigoInternacional(documentNumber, tipoCodigo, codigo);
        data = Utilidad.jsonNodeParse(response);
        return this;
    }

    public String getNombreBanco(){
        String texto = "null";
        if (data != null){
            texto = data.get("bankName").asText();
        }
        return texto;
    }

    public String getCiudadBanco(){
        String texto = "null";
        if (data != null){
            texto = data.get("bankCity").asText();
        }
        return texto;
    }

    public String getPaisBanco(){
        String texto = "null";
        if (data != null){
            texto = data.get("bankCountry").asText();
        }
        return texto;
    }

    public String getCodigo(){
        String texto = "null";
        if (data != null){
            texto = data.get("bankId").asText();
        }
        return texto;
    }


    public void toStrings() {
        System.out.println("MsConsultarCodigoInternacional{" +
                "documentNumber='" + documentNumber + '\'' +
                ", response=" + response +
                ", data=" + data +
                '}');
    }
}
