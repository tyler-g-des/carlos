package microservicios.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import http.BaseRequestHTTP;
import io.restassured.response.Response;
import microservicios.Utilidad;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 13/06/2024 1:52 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsCambioDivisas extends BaseRequestHTTP {

    private Response response;
    private String documentNumber;
    private JsonNode data;

    private Response nuevaConsulta(){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("transactionId", UUID.randomUUID());
        parametros.put("documentNumber","00111435384");

        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept","*/*");
        headers.put("Connection","keep-alive");

        response = httGET(parametros,headers,"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/services/exchange-rates");
        return response;
    }

//    public MsCambioDivisas(String documentNumber){
//        this.documentNumber = documentNumber;
//        response = nuevaConsulta();
//        //response = new MicroServiciosDashboard().getCuentas(documentNumber);
//        cuentas = Utilidad.jsonNodeParse(response);
//    }
    public MsCambioDivisas(){
        response = nuevaConsulta();
        data = Utilidad.jsonNodeParse(response);
    }

//    public MsCambioDivisas newQuery(String documentNumber){
//        this.documentNumber = documentNumber;
//        response = nuevaConsulta();
//        //response = new MicroServiciosDashboard().getCuentas(documentNumber);
//        cuentas = Utilidad.jsonNodeParse(response);
//        return this;
//    }

    public MsCambioDivisas newQuery(){
        response = nuevaConsulta();
        //response = new MicroServiciosDashboard().getCuentas(documentNumber);
        data = Utilidad.jsonNodeParse(response);
        return this;
    }

    public String getCompraEnDolar(){
        String texto = "null";
        if (data != null){
            texto = data.get("usdBuyingRate").asText();
        }
        return texto;
    }

    public String getVentaEnDolar(){
        String texto = "null";
        if (data != null){
            texto = data.get("usdSellingRate").asText();
        }
        return texto;
    }

    public String getCompraEnEuro(){
        String texto = "null";
        if (data != null){
            texto = data.get("eurNormalBuyingRate").asText();
        }
        return texto;
    }

    public String getVentaEnEuro(){
        String texto = "null";
        if (data != null){
            texto = data.get("eurNormalSellingRate").asText();
        }
        return texto;
    }
}
