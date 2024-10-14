package apis.ibp;

import apis.serviciosdevops.ServiciosDevops;
import basetest.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import microservicios.Utilidad;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.lang.String.format;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/06/2024 6:33 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class BaseRequest {

    private APISLogin login;
    private String messageReport;
    private int statusCode = 0;
    private JsonNode dataEncriptada;
    public BaseRequest(APISLogin login){
        this.login = login;
    }

    public String getMessageReport() {
        return messageReport;
    }

    public void setMessageReport(String messageReport) {
        this.messageReport = messageReport;
    }

    public int getStatusCode(){
        return statusCode;
        //return login.getStatusCode();
    }

//    public JsonNode getDataEncriptada(){
//        //JsonNode resultado = Utilidad.jsonNodeParse(  login.desencriptar(dataEncriptada.get("data").asText()) ) ;
//        System.out.println("Resultado: " + resultado);
//        return resultado;
//    }

    public void setStatusCode(int statusCode){
        this.statusCode = statusCode;
        login.setStatusCode(statusCode);
    }

    public Map<String, Object> getMapaComunUUID(){
        return login.getMapaComunUUID();
    }

    public String getDataComunEncriptada(){
        return login.getDataComunEncriptada();
    }

    public void httpPost(Map<String, Object> encriptar,String pathServicio, String accion, String getMethodName, Class <?> getClass){
        httpPostDeletePut( encriptar, pathServicio, accion, getMethodName, getClass,"POST");
    }

    public void httoPost(String dataAEncriptar, String pathServicio, String accion, String getMethodName, Class <?> getClass){

        Map<String, Object> body = getMapaBodyComunRequest();
        body.put("data", login.getServiciosDevops().encriptarData(new Gson().toJson( dataAEncriptar ).replace("\"1\":","").replace("\"2\":","").replace("{{","[{").replace("}}","}]")));

        try {
            Response response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .header("x-keyvalue", login.getServiciosDevops().getXkeyValue())
                    .header("clientId", login.getClientId())
                    .header("clientSecret", login.getClientSecret())
                    .header("jwt", login.getJwtSecureDevice())
                    .body(new Gson().toJson( body ))
                    .log().all().post( login.getHost()+ pathServicio );
            System.out.println("Resultado data: " + response.getBody().asString());

            System.out.println("Configurando preguntas de seguridad: " + response.statusCode());
            setStatusCode(response.getStatusCode());
        }catch (Exception e){
            System.out.println("Error en nueva peticion post linea 39 clase BaseRequest.java");
        }
    }

    public void httpDelete(Map<String, Object> encriptar,String pathServicio, String accion, String getMethodName, Class <?> getClass){
        httpPostDeletePut( encriptar, pathServicio, accion, getMethodName, getClass,"Delete");
    }

    public void httpPut(Map<String, Object> encriptar,String pathServicio, String accion, String getMethodName, Class <?> getClass){
        httpPostDeletePut( encriptar, pathServicio, accion, getMethodName, getClass,"put");
    }

    public Map<String, Object>  getMapaBodyComunRequest(){
        Map<String, Object> bodyComun = new HashMap<>();
        bodyComun.put("clientId", login.getClientId());
        bodyComun.put("clientSecret", login.getClientSecret());
        return bodyComun;
    }
    /**
     * Este método permite hacer una solicitud Post a los servicios de IBP de este tipo.
     * @param encriptar
     * @param pathServicio
     * @param accion
     * @param getMethodName
     * @param getClass
     */
    private void httpPostDeletePut(Map<String, Object> encriptar, String pathServicio, String accion, String getMethodName, Class <?> getClass, String tipoSolicitud){
        Map<String, Object> body = getMapaBodyComunRequest();
        body.put("data", login.getServiciosDevops().encriptarData(new Gson().toJson( encriptar ).replace("\"1\":","").replace("\"2\":","").replace("{{","[{").replace("}}","}]")));
        //BaseTest.createStep("Usuario iniciando sesión: " + usuario,true,false);

        try {
            //Response response = null;
            if (tipoSolicitud.equalsIgnoreCase("POST")){
                Response response = RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .header("x-keyvalue", login.getServiciosDevops().getXkeyValue())
                        .header("clientId", login.getClientId())
                        .header("clientSecret", login.getClientSecret())
                        .header("jwt", login.getJwtSecureDevice())
                        .body(new Gson().toJson( body ))
                        .log().all().post( login.getHost()+ pathServicio );
                System.out.println("Resultado data: " + response.getBody().asString());

                setStatusCode(response.getStatusCode());
                //dataEncriptada = Utilidad.jsonNodeParse(response);
  //              System.out.println("Respuesta de key card: " +  response.getBody().asString());
                //String resultado = ServiciosDevops.getInstance().desencriptarData(login.getUUID(), Utilidad.jsonNodeParse(response).get("data").asText());
                //System.out.println("Resultado desencritacion: " + resultado);

            } else if (tipoSolicitud.equalsIgnoreCase("delete")) {

                Response response = RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .header("x-keyvalue", login.getServiciosDevops().getXkeyValue())
                        .header("clientId", login.getClientId())
                        .header("clientSecret", login.getClientSecret())
                        .header("jwt", login.getJwtSecureDevice())
                        .body(new Gson().toJson( body ))
                        .log().all().delete( login.getHost()+ pathServicio );
                dataEncriptada = Utilidad.jsonNodeParse(response);
                setStatusCode(response.getStatusCode());
            }else if (tipoSolicitud.equalsIgnoreCase("put")){
               Response  response = RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .header("x-keyvalue", login.getServiciosDevops().getXkeyValue())
                        .header("clientId", login.getClientId())
                        .header("clientSecret", login.getClientSecret())
                        .header("jwt", login.getJwtSecureDevice())
                        .body(new Gson().toJson( body ))
                        .log().all().put( login.getHost()+ pathServicio );
                dataEncriptada = Utilidad.jsonNodeParse(response);

                setStatusCode(response.getStatusCode());
            }


            //setStatusCode(response.getStatusCode());
            String msg = format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, tipoSolicitud.toUpperCase(), accion, getStatusCode());
            System.out.println(msg.replace("<br>","\n"));
            setMessageReport(msg);
            //setStatusCode(response.getStatusCode());
//            if (response.getStatusCode() == 200) {
//
//                //setReporteAPI(response,urlLoginApiPost,"SESION INICIADA");
//                //jsoNodeResponseLogin = Utilidad.jsonNodeParse(response);  | ELIMINAR
//                //jwtLogin = jsoNodeResponseLogin.get("x-jwt").asText();    | ELIMINAR
//                //jwtLogin = Utilidad.jsonNodeParse(response).get("x-jwt").asText();
//            }else if (response.getStatusCode() == 401){
//                //setReporteAPI(response,urlLoginApiPost,"INICIO DE SESION: Status = NO AUTORIZADO");
//                setMessageReport(msg);
//            }
        }catch (Exception e){
            setStatusCode(0);
            //setReportError(urlLoginApiPost,"INICIANDO SESION","POST",e);
            String error = format("Se ha producido un error en Clase %s en el Método %s.  <br>\n" +
                    "Error: %s             <br><br>\n" +
                    "\n" +
                    "Servicio: %s          <br>\n" +
                    "Tipo de solicitud: %S <br>\n" +
                    "Acción: %S.           <br>\n" +
                    "Status code: No se puede obtener status Code de este error.",getClass.getSimpleName(),getMethodName,e,pathServicio,"GET", accion);
            System.out.println(error);
            BaseTest.createStep(error, false, false);
        }
    }

    /**
     * Permite realizar petición GET a servicios de IBP.
     * @param dataAEncriptar Mapa con la data que se va a encriptar. Requiere del metodo getMapaComunUUID, para la creacion del mapa.
     * @param pathServicio URL del servicio afectado.
     * @param accion que se esta realizando sobre el servicio.
     * @param getMethodName Nombre del método en donde se esta ejecutando esta solicitud. Obtener de: Thread.currentThread().getStackTrace()[1].getMethodName();
     * @param getClass Clase desde donde se realiza solicitud. Utilizar el método getClass();
     */
    public void httpGET(Map<String, Object> dataAEncriptar, String pathServicio, String accion, String getMethodName, Class <?> getClass){
        setStatusCode(0);
        try {
            Response response = RestAssured
                    .given()
                    .urlEncodingEnabled(true)
                    .header("x-keyvalue",login.getXKeyValue())
                    .header("clientId",login.getClientId())
                    .header("clientSecret",login.getClientSecret())
                    //.header("GeneratedUUID", login.getUUID())
                    .header("jwt",login.getJwtSecureDevice())
                    .log().all()
                    .get(login.getHost() + pathServicio  +"?"+ login.getServiciosDevops().encriptarData(new Gson().toJson(dataAEncriptar)).replace("=",""));


            String resultado = ServiciosDevops.getInstance().desencriptarData(login.getUUID(), Utilidad.jsonNodeParse(response).get("data").asText());
            System.out.println("Resultado desencritacion: " + resultado);

            String msg = "";
            //statusCode = response.getStatusCode();
            setStatusCode(response.getStatusCode());
            //if (getStatusCode() == 200){
            msg = format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, "GET", accion, getStatusCode());
            setMessageReport(msg);

            System.out.println("\n"+ msg.replace("<br>","\n"));

        }catch (Exception e){

            String error = format("Se ha producido un error en Clase %s en la Método %s.  <br>\n" +
                    "Error: %s             <br><br>\n" +
                    "\n" +
                    "Servicio: %s          <br>\n" +
                    "Tipo de solicitud: %S <br>\n" +
                    "Acción: %S.           <br>\n" +
                    "Status code: No se puede obtener status Code de este error.",getClass.getSimpleName(),getMethodName,e,pathServicio,"GET", accion);

            setMessageReport(error);
            BaseTest.createStep(error,false,false);
            setStatusCode(0);;

            System.out.println(error.replace("<br>","\n"));
            //format("Se ha producido un error en Clase %S en la Método %s: <br><br> Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %S. <br><br>Se ha produccido un error:<br> %s.",clase.getSimpleName(), methodClass, pathServicio, "GET", accion, e);
            //System.out.println(error);
        }
    }

//    /**
//     * <h1>ELIMINAR</h1>
//     * @param dataJson
//     * @param pathServicio
//     * @param msgStatus200_203
//     * @param msgStatusDiferente
//     * @param accion
//     * @param methodClass
//     * @param clase
//     */
//    public void httpGET(Map<String, Object> dataJson, String pathServicio, String msgStatus200_203, String msgStatusDiferente, String accion, String methodClass, Class <?>clase){
//        //statusCode = 0;
//        setStatusCode(0);
//
//        try {
//            Response response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("clientId",login.getClientId())
//                    .header("clientSecret",login.getClientSecret())
//                    //.header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(login.getHost() + pathServicio  +"?"+ login.getServiciosDevops().encriptarData(new Gson().toJson(dataJson)).replace("=",""));
//
//            String msg = "";
//            //statusCode = response.getStatusCode();
//            setStatusCode(response.getStatusCode());
//            if (getStatusCode() == 200){
//                msg = format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, "GET", msgStatus200_203, getStatusCode());
//                //System.out.println(msgStatus200_203);
//            }else {
//                //System.out.println(msgStatusDiferente);
//                msg = format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, "GET", msgStatusDiferente, getStatusCode());
//            }
//
//            BaseTest.createStep(msg,true,false);
//            System.out.println(msg.replace("<br>","\n"));
//            //System.out.println(format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, "GET", accion, statusCode));
//        }catch (Exception e){
//
//            String error = format("Se ha producido un error en Clase %S en la Método %s: <br><br> Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %S. <br><br>Se ha produccido un error:<br> %s.",clase.getSimpleName(), methodClass, pathServicio, "GET", msgStatus200_203, e);
//            BaseTest.createStep(error,false,false);
//            setStatusCode(0);;
//
//            System.out.println(error.replace("<br>","\n"));
//            //format("Se ha producido un error en Clase %S en la Método %s: <br><br> Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %S. <br><br>Se ha produccido un error:<br> %s.",clase.getSimpleName(), methodClass, pathServicio, "GET", accion, e);
//            //System.out.println(error);
//        }
//    }


    public void httpGETComun(String pathServicio, String accion, String getMethodName, Class <?> getClass){
        setStatusCode(0);
        try {
            String transaccionID = UUID.randomUUID().toString();
            Response response = RestAssured
                    .given()
                    .urlEncodingEnabled(true)
                    .header("x-keyvalue",login.getXKeyValue())
                    .header("clientId",login.getClientId())
                    .header("clientSecret",login.getClientSecret())
                    //.header("GeneratedUUID", login.getUUID()) // Quitar en dado caso
                    .header("jwt",login.getJwtSecureDevice())
                    .log().all()
                    .get(login.getHost() + pathServicio  +"?"+ login.getDataComunEncriptada().replace("=",""));
            //.get(HOST + pathServicio  +"?"+ login.getServiciosDevops().encriptarData(new Gson().toJson(dataJson)).replace("=",""));

            System.out.println( "Respuesta key card: " + response.statusCode());
            //String resultado = ServiciosDevops.getInstance().desencriptarData(login.getUUID(), Utilidad.jsonNodeParse(response).get("data").asText());
            //System.out.println("Resultado desencritacion: " + resultado);
            String msg = "";
            //statusCode = response.getStatusCode();
            setStatusCode(response.getStatusCode());
            //if (getStatusCode() == 200){
            msg = format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, "GET", accion, getStatusCode());
            setMessageReport(msg);
            //System.out.println(msgStatus200_203);
            //}else {
            //System.out.println(msgStatusDiferente);
            //   msg = format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, "GET", accion, getStatusCode());
            //}

            //BaseTest.createStep(msg,true,false);
            System.out.println(msg.replace("<br>","\n"));
            //System.out.println(format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, "GET", accion, statusCode));
        }catch (Exception e){
            System.out.println(e);

            String error = //format("Se ha producido un error en Clase %S en la Método %s: <br><br> Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %S. <br><br>Se ha produccido un error:<br> %s.",getClass.getSimpleName(), getMethodName, pathServicio, "GET", msgStatus200_203, e);
                    //error =
                    format("Se ha producido un error en Clase %s en la Método %s.  <br>\n" +
                            "Error: %s             <br><br>\n" +
                            "\n" +
                            "Servicio: %s          <br>\n" +
                            "Tipo de solicitud: %S <br>\n" +
                            "Acción: %S.           <br>\n" +
                            "Status code: No se puede obtener status Code de este error.",getClass.getSimpleName(),getMethodName,e,pathServicio,"GET", accion);

            setMessageReport(error);
            BaseTest.createStep(error,false,false);
            setStatusCode(0);;

            System.out.println(error.replace("<br>","\n"));
            //format("Se ha producido un error en Clase %S en la Método %s: <br><br> Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %S. <br><br>Se ha produccido un error:<br> %s.",clase.getSimpleName(), methodClass, pathServicio, "GET", accion, e);
            //System.out.println(error);
        }
    }



    // ELiminar este codigo comentado jsonComunEnRequest
//    public  Map<String, Object> getMapaComunUUID(){
//        Map<String, Object> jsonComunEnRequest = new HashMap<>();
//        jsonComunEnRequest.put("channel", "IBP");
//        jsonComunEnRequest.put("platform", "IBP");
//        jsonComunEnRequest.put("deviceId", login.getDeviceId());
//        jsonComunEnRequest.put("transactionId", UUID.randomUUID());
//        jsonComunEnRequest.put("uuid", login.getUUID());
//        return jsonComunEnRequest;
//    }



    /**
     * <h1> Creo que ELIMINAR</h1>
     * @param pathServicio
     * @param msgStatus200_203
     * @param msgStatusDiferente
     * @param accion
     * @param methodClass
     * @param clase
     */
    public void httpGETComun(String pathServicio, String msgStatus200_203, String msgStatusDiferente, String accion, String methodClass, Class <?>clase){
        setStatusCode(0);// statusCode = 0;
        try {
            Response response = RestAssured
                    .given()
                    .urlEncodingEnabled(true)
                    .header("x-keyvalue",login.getXKeyValue())
                    .header("clientId",login.getClientId())
                    .header("clientSecret",login.getClientSecret())
                    //.header("GeneratedUUID", login.getUUID())
                    .header("jwt",login.getJwtSecureDevice())
                    .log().all()
                    .get(login.getHost() + pathServicio  +"?"+ login.getDataComunEncriptada().replace("=",""));

            String msg = "";
            setStatusCode(response.getStatusCode()); //= ;
            if (getStatusCode() == 200){
                msg = format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, "GET", msgStatus200_203, getStatusCode());
                //System.out.println(msgStatus200_203);
            }else {
                //System.out.println(msgStatusDiferente);
                msg = format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, "GET", msgStatusDiferente, getStatusCode());
            }

            BaseTest.createStep(msg,true,false);
            System.out.println(msg.replace("<br>","\n"));
            //System.out.println(format("Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s",pathServicio, "GET", accion, statusCode));
        }catch (Exception e){

            String error = format("Se ha producido un error en Clase %S en la Método %s: <br><br> Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %S. <br><br>Se ha produccido un error:<br> %s.",clase.getSimpleName(), methodClass, pathServicio, "GET", msgStatus200_203, e);
            BaseTest.createStep(error,false,false);
            setStatusCode(0);// = 0;

            System.out.println(error.replace("<br>","\n"));
            //format("Se ha producido un error en Clase %S en la Método %s: <br><br> Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %S. <br><br>Se ha produccido un error:<br> %s.",clase.getSimpleName(), methodClass, pathServicio, "GET", accion, e);
            //System.out.println(error);
        }
    }

}
