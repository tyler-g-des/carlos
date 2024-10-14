package http;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/06/2024 6:41 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class BaseRequestHTTP {

    private int statusCode = 0;
    private String messageError = "";

    public Response httGET(Map<String,Object> parametros, Map <String, Object> headers, String urlPath) {
        Response response = null;
        try {
             response = RestAssured.given().config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                    .given()
                    .urlEncodingEnabled(true)
                    .headers(headers)
                    .params(parametros)
                    .get(urlPath);
            System.out.println(response.getStatusCode());
            statusCode = response.getStatusCode();

            if (statusCode == 200 || statusCode == 203 || statusCode == 206){
                System.out.println("Se ejecuto exitosamente la solicitud get. " + statusCode);
                //return response;
            }else {
                if (getStatusCode() >= 400){
                    messageError = "Error consultando el micro servicio. " + urlPath + " \nStatus Code: "+ statusCode;
                }
            }
        }catch (Exception e){
            statusCode = 0;
            messageError = "Error consultando el micro servicio: " + urlPath + ": " + e;
            System.out.println(messageError);
        }
        return response;

    }



    public String getMessageError() {
        return messageError;
    }

    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Retorna true si la respuesta es exitosa y la misma puede contener data.
     * Status Code: 200 y 206.
     * @return boolean
     */
    public boolean isSuccessful(){
        return getStatusCode() == 200 || getStatusCode() == 206;
    }

    public boolean isNoContent(){
        return getStatusCode() == 204;
    }

    public boolean isStatusCodeError(){
        return getStatusCode() == 0 || getStatusCode() >= 400;
    }

    public static void main(String[] args) {
//        Map<String, Object > parametros = new HashMap<>();
//        parametros.put("documentNumber","00111435384");
//        parametros.put("transactionId", UUID.randomUUID());
//
//        Map <String, Object> headers = new HashMap<>();
//        headers.put("Accept","*/*");
//        headers.put("Connection","keep-alive");

       // httpost(parametros, headers, "https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/dashboard/accounts");
    }

}
