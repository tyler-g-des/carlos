package microservicios;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 09/04/2024 9:27 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsContratos {

    private Response response;
    private JsonNode data;
    private String documentNumber = "";
    private int statusCode = 0;

    public MsContratos(String documentNumber){
        this.documentNumber = documentNumber;
    }

    private MsContratos newQuery(String contrato){
        response = RequestMicroServiciosIBP.newQuery().getContrato(documentNumber, contrato);
        data = Utilidad.jsonNodeParse(response);
        return this;
    }

    public int getStatusCode(){
        if (statusCode == 0){
            //newQuery("APP");
        }
        return response.getStatusCode();
    }

    public boolean contractPlataformaOnlineIsAccepted(){
        newQuery("APP");
        return data.isEmpty();
    }

    public boolean contractACHPagoAlInstanteIsAccepted(){
        newQuery("ACH");
        System.out.println(data);
        return data.isEmpty();
    }

    public boolean contractInternacionalRegionalSIPAIsAccepted(){
        newQuery("INT");
        System.out.println(data);
        return data.isEmpty();
    }

    public boolean contractAutorizacionUsoInformacionIsAccepted(){
        newQuery("CCD");
        return data.isEmpty();
    }

    public boolean contractAFPIsAccepted(){
        newQuery("AFP");
        return data.isEmpty();
    }


}
