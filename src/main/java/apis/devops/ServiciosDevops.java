package apis.devops;

import basetest.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import http.BaseRequestHTTP;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import microservicios.Utilidad;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Email: carlos_loyola@bhd.com.do
 * @ClassName: ServiciosDevops
 * @CreationData: 01/06/2024 11:46 a. m.
 * @ProjectName: AutomatizacionAPI_IBP
 */
public class ServiciosDevops {
    private Response response;
    private String urlServicioEncriptacion = "https://devops.cfbhd.com/controlador/apigtwGenerarEncriptacionData";
    private String urlServicioGenerarXKeyValue = "https://devops.cfbhd.com/controlador/apigtwGenerarXKeyValue";
    private String uuid;
    private String xkeyValue;
    private String x50CertRetornado;
    private int statusCode;
    private JsonNode dataEncriptadaJson;


    public String encriptarData(String gson){
        response = null;
        String resultado = "";
        String dataEncriptada = "";
        statusCode = 0;
        try {
            response = RestAssured.given().config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                    .body(gson).log().all().post(urlServicioEncriptacion);
                    //.body(new Gson().toJson(gson)).post(urlServicioEncriptacion);
            dataEncriptadaJson = Utilidad.jsonNodeParse(response);
            System.out.println(response.getBody().asString());
            if ( response.statusCode() == 200 ){
                System.out.println("DATA encriptada");
                resultado = response.getBody().asString();
                if (resultado.contains("Error")){
                    System.out.println("No contiene certificado.");
                }else {
                    dataEncriptada = Utilidad.jsonNodeParse(response).get("data").asText(); // obteniendo data encriptada.
                }
            }
            statusCode = response.getStatusCode();
        }catch (Exception e){
            System.out.println("Se producto un error al intentar incriptar la data. En metodo: encriptarData, clase: ServiciosDevops. " + e);
        }
        return dataEncriptada;
    }

    public String desencriptarData(String uuid, String dataEncriptada){
        Map<String, Object> body = new HashMap<>();
        body.put("uuid", uuid);
        body.put("data",dataEncriptada);
        //Response response;
        String resultado = "";
        try {
            Response response = RestAssured.given().config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                    .body(new Gson().toJson(body).replace("\\u003d","=") ).log().all().post("https://devops.cfbhd.com/controlador/apigtwGenerarDesencriptacionData");
                    //requestSpecification.body(new Gson().toJson(body).replace("\\u003d","=")).log().all().post("https://devops.cfbhd.com/controlador/apigtwGenerarDesencriptacionData");
            resultado = response.getBody().asString().replace("\\n","").replace("\\r","").replace("\r\n","").replace("\\","");
        }catch (Exception e){
            BaseTest.createStep("Ha ocurrido un error en el método desencriptarData de la clase: ServiciosDevops. <br> " + e.getCause(),false,false);
            return "";
        }
        return resultado;
    }

    public JsonNode getDataEncriptada(){
        return dataEncriptadaJson;
    }

    public void generarXKeyValue(String x509Cert){
        Map<String, Object> body = new HashMap<>();
        body.put("x509Cert", x509Cert);
        response = null;
        statusCode = 0;

        try {
            response = RestAssured.given().config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                    .body(new Gson().toJson( body )).log().all().post(urlServicioGenerarXKeyValue);

            if ( response.statusCode() == 200 ){
                String resultado = response.getBody().asString();
                if (resultado.contains("uuid") && resultado.contains("x-keyvalue")){

                    uuid = Utilidad.jsonNodeParse(response).get("uuid").asText();
                    xkeyValue = Utilidad.jsonNodeParse(response).get("x-keyvalue").asText();
                    x50CertRetornado = Utilidad.jsonNodeParse(response).get("x509Cert").asText();
                }

            }else {
                System.out.println("Error en la solicitud: " + response.statusCode());
            }
            statusCode = response.getStatusCode();
        }catch (Exception e){
            System.out.println("Se ha produccido un error generando el xKeyValue. En metodod: generarXKeyValue clase: ServiciosDevops. " + e);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public String getXkeyValue() {
        return xkeyValue;
    }

    public String getX50Cert() {
        return x50CertRetornado;
    }

    public int getStatusCode(){
        return statusCode;
    }

}
