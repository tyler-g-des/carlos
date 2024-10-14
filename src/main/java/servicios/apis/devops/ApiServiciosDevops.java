package servicios.apis.devops;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.HashMap;
import java.util.Map;

public class ApiServiciosDevops {

    private String uuid = "";
    private String xKeyValue = "";
    private String x50Cert = "";
    private int statusCode = 0;

    public String getUuid() {
        return uuid;
    }

    public String getxKeyValue() {
        return xKeyValue;
    }

    public String getX50Cert() {
        return x50Cert;
    }

    public int getStatusCode() {
        return statusCode;
    }

    private ApiServiciosDevops(){}

    public static ApiServiciosDevops getInstance(){
        return new ApiServiciosDevops();
    }

    public ApiServiciosDevops generateNewUUID(String x509Cert){

        Map<String, Object> body = new HashMap<>();
        body.put("x509Cert", x509Cert);

        try {
            Response response = BaseRequestHTTP.setHttPOST(body).post(BaseRequestHTTP.HOST_API_SERVICIO_DEVOPS + "apigtwGenerarXKeyValue");

            if (response.statusCode() == 200){

                JsonNode data = Utilidad.jsonNodeParse(response);
                uuid = data.get("uuid").asText();
                xKeyValue = data.get("x-keyvalue").asText();
                this.x50Cert = data.get("x509Cert").asText();

            }else {

                uuid = response.getBody().asString();
                xKeyValue = response.getBody().asString();
                this.x50Cert = response.getBody().asString();
            }

        }catch (Exception e){
            System.out.println("Error en Class: ApiGenerarXkeyValueUUID,  Method: consultar. " + e);
        }
        return this;
    }

    public String encriptarData(Map<String, Object> body){
        JsonNode data = null;
        try {
            Response response = BaseRequestHTTP.setHttPOST(body).post(BaseRequestHTTP.HOST_API_SERVICIO_DEVOPS + "apigtwGenerarEncriptacionData");

            System.out.println(response.getBody().asString());
            if (response.statusCode() == 200 && response.getBody().asString().contains("data")){
                 data =  Utilidad.jsonNodeParse(response);
            }else {
                System.out.println("Se ha producido un error encriptando la data: " + response.getBody().asString());
            }
        }catch (Exception e){
            System.out.println("Se ha produccido un error encriptando la data: " + e);
        }
        return data.get("data").asText();
    }

    public JsonNode desencriptarData(String uuid, String data){
        JsonNode dataa = null;
        Map<String, Object> body = new HashMap<>();
        body.put("uuid",uuid);
        body.put("data",data);
        try {
            Response response = BaseRequestHTTP.configNewRequest().given().body(body)//.log().all()
                    .post(BaseRequestHTTP.HOST_API_SERVICIO_DEVOPS + "apigtwGenerarDesencriptacionData");
            System.out.println("Status Desencriptado: "  + response.statusCode());

            System.out.println("__________________________________________________________________________________");
            System.out.println(response.getBody().asString());
            //ystem.out.println(response.getBody().jsonPath().get("data").toString());
            JsonNode daoFinal = Utilidad.jsonNodeParse(response.getBody().jsonPath().get("data").toString());
            System.out.println("__________________________________________________________________________________");
            System.out.println("Final" +daoFinal.get("secureDevice"));
            System.out.println("__________________________________________________________________________________");
            //System.out.println(new Gson().toJson(response.getBody().asString()));
            //String dataLimpia = response.getBody().asString().replace("\\r","").replace("\\n","").replace("\\","");
            //JsonNode respuesta = Utilidad.jsonNodeParse(dataLimpia);
            //System.out.println("Respuesta> " + respuesta);

            //System.out.println(response.getBody().asString().replace("\\r","").replace("\\n","").replace("\\",""));
            //System.out.println("Data limpia> " + dataLimpia);
            //JsonNode dataLimpiaa = Utilidad.jsonNodeParse(dataLimpia);
            //System.out.println("Data Limpia 2" + dataLimpiaa);
            if (response.statusCode() == 200){
                dataa = Utilidad.jsonNodeParse(response.getBody().jsonPath().get("data").toString());
            }
        }catch (Exception e){

        }
        return dataa;
    }
}
