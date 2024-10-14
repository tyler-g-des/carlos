package apis.serviciosdevops;

import basetest.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import microservicios.Utilidad;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 25/04/2024 2:13 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class ServiciosDevops {

    private RequestSpecification requestSpecification;
    private Response respuesta;

    /**
     * En este método se inicializa la configuración del los Request. Configurando en ella el certificado SSL para que no se presenten errores por temas de certificados, que suelen ocurrir en algunas peticiones.
     */
    private ServiciosDevops(){
        requestSpecification = given().config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
    }

     /**
     * Retorna una nueva instancia de la clase ServiciosDevops
     * @return ServiciosDevops
     */
    public static ServiciosDevops getInstance(){
        return new ServiciosDevops();
    }

    /**
     * Este método sirve para encriptar el Json recibido como String, utilizando el servicio devops apigtwGenerarEncriptacionData.
     * @param jsonDataAEncriptar json String.
     * @return data
     */
    public String encriptarData(String jsonDataAEncriptar){
        Response response;
        String data = "";
        try {
            response  = requestSpecification.body(jsonDataAEncriptar).log().all().post("https://devops.cfbhd.com/controlador/apigtwGenerarEncriptacionData");

            if (response.getStatusCode() == 200){
                String resultado = response.getBody().asString();
                if (!resultado.contains("Error")){
                    data = Utilidad.jsonNodeParse(response).get("data").asText();
                }else {
                    BaseTest.createStep(resultado,false,false);
                    // No se envio el certificado o el mismo es invalido.
                }
            }
        }catch (Exception e){
            BaseTest.createStep("Ha ocurrido un error en el método encriptarData de la clase: ServiciosDevops. <br> " + e.getCause(),false,false);
        }
        return data;
    }

    /**
     * Este método sirve para desencriptar la data, utilizando el servicio devops apigtwGenerarDesencriptacionData
     * @param uuid Utilizado para encriptar la data y retornado por el certificado.
     * @param dataEncriptada Data encriptada la cual se va a desencriptar. Enviar el value de data.
     * @return Data desencriptada.
     */
    public String desencriptarData(String uuid, String dataEncriptada){
        Map<String, Object> body = new HashMap<>();
        body.put("uuid", uuid);
        body.put("data",dataEncriptada);
        Response response;
        String resultado = "";
        try {
            response = requestSpecification.body(new Gson().toJson(body).replace("\\u003d","=")).log().all().post("https://devops.cfbhd.com/controlador/apigtwGenerarDesencriptacionData");
            resultado = response.getBody().asString().replace("\\n","").replace("\\r","").replace("\r\n","").replace("\\","");
        }catch (Exception e){
            BaseTest.createStep("Ha ocurrido un error en el método desencriptarData de la clase: ServiciosDevops. <br> " + e.getCause(),false,false);
            return "";
        }
        return resultado;
    }

    /**
     * Este método sirve para generar el uuid y el x-keyValue a partir del certificado enviado por parámetro.
     * @param x509Cert Certificado valido, del cual se extraerá el uuid y el x-keyValue.
     * @return ServiciosDevops
     */
    public ServiciosDevops generarXkeyValue(String x509Cert){
        Map<String, Object> body = new HashMap<>();
        body.put("x509Cert", x509Cert);
        try {
            respuesta = requestSpecification.body(new Gson().toJson(body)).log().all().post("https://devops.cfbhd.com/controlador/apigtwGenerarXKeyValue");
        }catch (Exception e){
            BaseTest.createStep("Error desencriptando el certificado: <br>" + e,false,false);
        }
        return this;
    }


    /**
     * Retorna el x-keyvalue Obtenido del certificado.
     * @return x-keyvalue
     */
    public String getXKeyValue(){
        return Utilidad.jsonNodeParse(respuesta).get("x-keyvalue").asText();
    }

    /**
     * Retorna el uuid Obtenido del certificado.
     * @return uuid
     */
    public String getUUID(){
        return Utilidad.jsonNodeParse(respuesta).get("uuid").asText();
    }

    /**
     * Este método retorna el certificado x509Cert.
     * @return x509Cert
     */
    public String getx509Cert(){
        return Utilidad.jsonNodeParse(respuesta).get("x509Cert").asText();
    }

}
