package apis;

import io.restassured.response.Response;

//import static apis.ibp.dashboard.APISLogin.HOST;
import static java.lang.String.format;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 31/05/2024 3:22 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class OrdenarSuLugar {

////    private final String URL_API_DASHBOARD_LOGOUT_POST = HOST + "/bhdleon/api/v1/personal/web/logout";
////    private final String URL_API_FIRST_DEVICE_VALIDATION_POST = HOST + "/bhdleon/api/v1/personal/web/first-device-validation";
////    private final String URL_LOGIN_VERIFY_ANSWERS_POST = HOST + "/bhdleon/api/v1/personal/web/verify-answers";
////    private final String URL_SECURITY_QUESTIONS_GET = HOST+ "/bhdleon/api/v1/personal/web/security-questions?";
////
////    private final String urlUpdateConsent = HOST + "/bhd/api/v1/personal/web/client/login/update-consent";
////    private final String urlChangePassword = HOST + "/bhdleon/api/v1/personal/web/change-password";
////    private final String urlFirstLoginPost = HOST +"/bhdleon/api/v1/personal/web/first-login";
//
//    /**
//     * <h1>ELIMINAR</h1>
//     */
//    private String mensaje;
//    private Response responses; // ELIMINAR
//    /**
//     * <h1> Sustituir por mensaje en metodo. ELiminar este</h1>
//     */
//    String msgPlantilla = "Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s";
//
//    /**
//     * <h1>ELIMINAR METODO</h1>
//     * @param urlServicio
//     * @param response
//     * @param accion
//     * @param tipoPeticion
//     */
//    public void setReportErrorEliminarMetodoSustituirPorSetReportError(String urlServicio, Response response, String accion, String tipoPeticion){
//        mensaje = format(msgPlantilla.replace("Response Status Code","SE HA PRODUCIDO UN ERROR: "),urlServicio,tipoPeticion,accion,responses.getStatusCode());
//    }


//
//    /**
//     * 201 status respuesta correctamente.
//     * @param contactId
//     * @param firmarContratoCompartirInformacion
//     */
//    public void updateConsent(String contactId,boolean firmarContratoCompartirInformacion){
//        if (isResponseSuccessful()){
//            responses = null;
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("transactionId", UUID.randomUUID());
//            encriptar.put("deviceId",DEVICE_ID);
//            encriptar.put("platform","IBP");
//            encriptar.put("contactId",contactId);
//            encriptar.put("signedConsentForInformationSharing",firmarContratoCompartirInformacion);
//            encriptar.put("uuid",getUUID());
//
//            try {
//                Map<String, Object> dataBody = new HashMap<>();
//                dataBody.put("clientId",clientId);
//                dataBody.put("clientSecret",clientSecret);
//                dataBody.put("data", ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)));
//                responses = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body(new Gson().toJson(dataBody))
//                        .header("x-keyvalue",getXKeyValue())
//                        .header("clientId",clientId)
//                        .header("clientSecret",clientSecret)
//                        .header("GeneratedUUID", getUUID())
//                        .header("jwt", getJwtSecureDevice())
//                        //.header("jwt",jwtLogin)
//                        .log().all()
//                        .post(urlUpdateConsent);
//
//                System.out.println("update-consent: " +responses.getStatusCode());
//                codigoDeEstado = responses.getStatusCode();
//            }catch (Exception e){
//                codigoDeEstado = responses.getStatusCode();
//                System.out.println("ALGO SALIO MAL EN update-consent: " + codigoDeEstado);
//            }
//        }
//    }
//
//    /**
//     * <h1>Mover a Informacion Acceso y seguridad</h1>
//     * @param oldPassword
//     * @param newPassword
//     * @param keyCard
//     */
//    public void changePassword(String oldPassword, String newPassword,String keyCard){
//        if (isResponseSuccessful()){
//            responses = null;
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("transactionId",UUID.randomUUID());
//            encriptar.put("deviceId",DEVICE_ID);
//            encriptar.put("platform","IBP");
//            encriptar.put("oldPassword",oldPassword);
//            encriptar.put("newPassword",newPassword);
//            encriptar.put("keyCard",keyCard);
//            encriptar.put("uuid",getUUID());
//
//            try {
//                Map<String, Object> dataBody = new HashMap<>();
//                dataBody.put("clientId",clientId);
//                dataBody.put("clientSecret",clientSecret);
//                dataBody.put("data", ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)));
//                responses = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body(new Gson().toJson(dataBody))
//                        .header("x-keyvalue",getXKeyValue())
//                        .header("clientId",clientId)
//                        .header("clientSecret",clientSecret)
//                        .header("GeneratedUUID", getUUID())
//                        .header("jwt", getJwtSecureDevice())
//                        //.header("jwt",jwtLogin)
//                        .log().all()
//                        .post(urlChangePassword);
//
//                System.out.println("Change Password: " +responses.getStatusCode());
//                codigoDeEstado = responses.getStatusCode();
//            }catch (Exception e){
//                codigoDeEstado = responses.getStatusCode();
//                System.out.println("ALGO SALIO MAL EN update-consent: " + codigoDeEstado);
//            }
//        }
//    }
//
//    public void logOut(){
//
//        if (isResponseSuccessful()){
//            responses = null;
//            try {
//                Map<String, Object> dataBody = new HashMap<>();
//                dataBody.put("data", getDataComunEncriptada());
//                responses = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body(new Gson().toJson(dataBody))
//                        .header("x-keyvalue",getXKeyValue())
//                        .header("GeneratedUUID", getUUID())
//                        .header("jwt", getJwtSecureDevice())
//                        .log().all()
//                        .post(URL_API_DASHBOARD_LOGOUT_POST);
//
//                System.out.println("NUEVA RESPUESTA CERRAR SESION: " +responses.getStatusCode());
//                codigoDeEstado = responses.getStatusCode();
//            }catch (Exception e){
//                codigoDeEstado = responses.getStatusCode();
//                System.out.println("ALGO SALIO MAL EN CERRAR SESION: " + codigoDeEstado);
//            }
//
//        }else {
//            codigoDeEstado = getStatusCode();
//        }
//        //dtComunEncriptada = ServiciosDevops.getInstance().encriptarData(String.format(JSON_ENCRIPTAR_COMUN_DASHBOARD,DEVICE_ID,login.getUUID())).replace("=","");
//    }
//
//
//    public void getSecurityQuestions(){
//
//        if (isResponseSuccessful()){
//            responses = null;
////            Map<String, Object> jsonEncriptar = new HashMap<>();
////            jsonEncriptar.put("channel","IBP");
////            jsonEncriptar.put("platform","IBP");
////            jsonEncriptar.put("deviceId",DEVICE_ID);
////            jsonEncriptar.put("processType","request");
////            jsonEncriptar.put("transactionId", UUID.randomUUID());
////            jsonEncriptar.put("uuid",getUUID());
//
//            responses = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",getXKeyValue())
//                    .header("GeneratedUUID", getUUID())
//                    .header("jwt", jwtLogin)
//                    .log().all()
//                    .get(URL_SECURITY_QUESTIONS_GET + getDataComunEncriptada());
//            System.out.println("RESPUESTA GET SECURITY_QUESTIONS: " + responses.getStatusCode());
//            codigoDeEstado = responses.getStatusCode();
//        }else {
//            codigoDeEstado = getStatusCode();
//        }
//    }
//
//    public void setSecurityQuestions(String username, String contrasenaActual, String nuevaContrasena, String keyCard){
//        if (isResponseSuccessful()) {
//            responses = null;
//            try {
//
//                List<Map<String, Object>> userSecretQuestions = new ArrayList<>();
//                Map<String, Object> pregunta1 = new HashMap<>();
//                pregunta1.put("position","0");
//                pregunta1.put("question","¿Cuál es el personaje de su libro favorito?");
//                pregunta1.put("answer","libro2");
//
//                Map<String, Object> pregunta2 = new HashMap<>();
//                pregunta2.put("position","1");
//                pregunta2.put("question","¿Cuál es su color favorito?");
//                pregunta2.put("answer","color2");
//
//                Map<String, Object> pregunta3 = new HashMap<>();
//                pregunta3.put("position","2");
//                pregunta3.put("question","¿Cuál es el nombre de su abuela materna?");
//                pregunta3.put("answer","abuela2");
//
//                Map<String, Object> pregunta4 = new HashMap<>();
//                pregunta4.put("position","3");
//                pregunta4.put("question","¿Cuál es la marca de su primer carro?");
//                pregunta4.put("answer","carro2");
//
//                Map<String, Object> pregunta5 = new HashMap<>();
//                pregunta5.put("position","4");
//                pregunta5.put("question","¿Cuál es el nombre del colegio donde cursó la primaria?");
//                pregunta5.put("answer","colegio2");
//
//                userSecretQuestions.add(pregunta1);
//                userSecretQuestions.add(pregunta2);
//                userSecretQuestions.add(pregunta3);
//                userSecretQuestions.add(pregunta4);
//                userSecretQuestions.add(pregunta5);
//
//
//                Map<String, Object> dataBody = new HashMap<>();
//                dataBody.put("channel", "IBP");
//                dataBody.put("platform", "IBP");
//                dataBody.put("deviceId", DEVICE_ID);
//                dataBody.put("transactionId", UUID.randomUUID());
//                dataBody.put("userSecretQuestions", userSecretQuestions);
//                dataBody.put("uuid",getUUID());
//
//                String dataEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(dataBody));
//
//                Map<String, Object> data = new HashMap<>();
//                data.put("clientId",clientId);
//                data.put("clientSecret",clientSecret);
//                data.put("data", dataEncriptada.replace("=",""));
//
//                Response response = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .header("x-keyvalue", serviciosDevops.getXKeyValue())
//                        .header("jwt", jwtLogin)
//                        .header("clientId",clientId)
//                        .header("clientSecret",clientSecret)
//
//                        .body(new Gson().toJson(data))
//                        //.header("x-keyvalue",getXKeyValue())
//                        //.header("GeneratedUUID", getUUID())
//                        //.header("jwt",getJwt())
//                        .log().all()
//                        .post(URL_SECURITY_QUESTIONS_GET.replace("?",""));
//
//                System.out.println(response.getStatusCode());
//
//                System.out.println("SECURITY_QUESTIONS POST: " + responses.getStatusCode());
//                codigoDeEstado = responses.getStatusCode();
//            } catch (Exception e) {
//                codigoDeEstado = responses.getStatusCode();
//                System.out.println("SECURITY_QUESTIONS POST: " + codigoDeEstado);
//            }
//        }
//
//    }
//
//    public void password(String username,String contrasenaActual, String nuevaContrasena, String keyCard){
//        //serviciosDevops.generarXkeyValue(x509cert);
//        //iniciarSesion(username,contrasenaActual);
//        iniciarSesion2(username,contrasenaActual);
//        if (isResponseSuccessful()) {
//            responses = null;
//            try {
//                Map<String, Object> dataBody = new HashMap<>();
//                dataBody.put("channel", "IBP");
//                dataBody.put("platform", "IBP");
//                dataBody.put("deviceId", DEVICE_ID);
//                dataBody.put("transactionId", UUID.randomUUID());
//                dataBody.put("username", username);
//                dataBody.put("oldPassword", contrasenaActual);
//                dataBody.put("newPassword", nuevaContrasena);
//                dataBody.put("keyCard", keyCard);
//                dataBody.put("uuid",getUUID());
//
//                Map<String, Object> data = new HashMap<>();
//                data.put("clientId",clientId);
//                data.put("clientSecret",clientSecret);
//                data.put("data", ServiciosDevops.getInstance().encriptarData(new Gson().toJson(dataBody)));
//
//                //dataBody.put("data", getDataComunEncriptada());
//                responses = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .header("x-keyvalue", serviciosDevops.getXKeyValue())
//                        .header("jwt", jwtLogin)
//                        .header("clientId",clientId)
//                        .header("clientSecret",clientSecret)
//                        //.body(String.format(jsonAPILogin,clientId,clientSecret,dataEncriptadaLogin))
//
//                        .body(new Gson().toJson(data))
//                        //.header("x-keyvalue",getXKeyValue())
//                        //.header("GeneratedUUID", getUUID())
//                        //.header("jwt",getJwt())
//                        .log().all()
//                        .post(URL_API_FORGOT_PASSWORD_POST);
//
//                System.out.println("NUEVA RESPUESTA FORGOT_PASSWORD: " + responses.getStatusCode());
//                codigoDeEstado = responses.getStatusCode();
//            } catch (Exception e) {
//                codigoDeEstado = responses.getStatusCode();
//                System.out.println("ALGO SALIO MAL EN FORGOT_PASSWORD: " + codigoDeEstado);
//            }
//        }
//        //}else {
//        //    codigoDeEstado = getStatusCode();
//        // }
//        //dtComunEncriptada = ServiciosDevops.getInstance().encriptarData(String.format(JSON_ENCRIPTAR_COMUN_DASHBOARD,DEVICE_ID,login.getUUID())).replace("=","");
//    }
//
//    public void verifyAnswers_Arreglar(boolean guardarDispositivoComoSeguro, String respuesta1, int posision1, String respuesta2, int posision2){
//        if (isResponseSuccessful()){
//            System.out.println("SI INICIO");
//            responses = null;
//
//            Map<String, Object> jsonBody = new HashMap<>();
//
//            jsonBody.put("secureDevice",guardarDispositivoComoSeguro);
//            jsonBody.put("transactionId",UUID.randomUUID());
//            jsonBody.put("deviceId",DEVICE_ID);
//            jsonBody.put("channel","IBP");
//            jsonBody.put("platform","IBP");
//
//            Map<String, Object> jsonBodyRespuesta1 = new HashMap<>();
//            jsonBodyRespuesta1.put("answer",respuesta1);
//            jsonBodyRespuesta1.put("question","¿Cuál es el personaje de su libro favorito?");
//            jsonBodyRespuesta1.put("position",String.valueOf(posision1) );
//
//            Map<String, Object> jsonBodyRespuesta2 = new HashMap<>();
//            jsonBodyRespuesta2.put("answer",respuesta2);
//            jsonBodyRespuesta2.put("question","¿Cuál es la marca de su primer carro?");
//            jsonBodyRespuesta2.put("position",String.valueOf(posision2) );
//
//            List<Map<String, Object>> userSecretQuestions = new ArrayList<>();
//            userSecretQuestions.add(jsonBodyRespuesta1);
//            userSecretQuestions.add(jsonBodyRespuesta2);
//
//            jsonBody.put("userSecretQuestions", userSecretQuestions);
//            jsonBody.put("uuid",getUUID());
//
//            String dataEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonBody));
//
//            try {
//                Map<String, Object> dataBody = new HashMap<>();
//                dataBody.put("clientId",clientId);
//                dataBody.put("clientSecret",clientSecret);
//                dataBody.put("data", dataEncriptada);
//                responses = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body(new Gson().toJson(dataBody))
//                        .header("x-keyvalue",getXKeyValue())
//                        .header("clientId",clientId)
//                        .header("clientSecret",clientSecret)
//                        //.header("GeneratedUUID", getUUID())
//                        .header("jwt", getJwtSecureDevice())
//                        //.header("jwt",jwtLogin)
//                        .log().all()
//                        .post(URL_LOGIN_VERIFY_ANSWERS_POST);
//
//                System.out.println("NUEVA VERIFY_ANSWERS: " +responses.getStatusCode());
//                codigoDeEstado = responses.getStatusCode();
//            }catch (Exception e){
//                codigoDeEstado = responses.getStatusCode();
//                System.out.println("ALGO SALIO MAL EN VERIFY_ANSWERS: " + codigoDeEstado);
//            }
//        }else {
//            codigoDeEstado = getStatusCode();
//        }
//    }
//
//    public void firstLogin(){ // POST and DELETE
//        if (isResponseSuccessful()){
//            responses = null;
//
////            Map<String, Object> encriptar = new HashMap<>();
////            encriptar.put("channel","IBP");
////            encriptar.put("transactionId",UUID.randomUUID());
////            encriptar.put("deviceId",DEVICE_ID);
////            encriptar.put("platform","IBP");
////            encriptar.put("oldPassword",oldPassword);
////            encriptar.put("newPassword",newPassword);
////            encriptar.put("keyCard",keyCard);
////            encriptar.put("uuid",getUUID());
//
//            try {
//                Map<String, Object> dataBody = new HashMap<>();
//                dataBody.put("clientId",clientId);
//                dataBody.put("clientSecret",clientSecret);
//                dataBody.put("data", getDataComunEncriptada());
//                responses = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body(new Gson().toJson(dataBody))
//                        .header("x-keyvalue",getXKeyValue())
//                        .header("clientId",clientId)
//                        .header("clientSecret",clientSecret)
//                        .header("GeneratedUUID", getUUID())
//                        .header("jwt", getJwtSecureDevice())
//                        //.header("jwt",jwtLogin)
//                        .log().all()
//                        .post(urlFirstLoginPost);
//
//                System.out.println("first-login: " +responses.getStatusCode());
//                codigoDeEstado = responses.getStatusCode();
//            }catch (Exception e){
//                codigoDeEstado = responses.getStatusCode();
//                System.out.println("ALGO SALIO MAL EN update-consent: " + codigoDeEstado);
//            }
//        }
//    }
//
//    public void firstDeviceValidation(){
//        if (isResponseSuccessful()){
//            responses = null;
//            Map<String, Object> dataBody = new HashMap<>();
//            dataBody.put("channel","IBP");
//            dataBody.put("platform","IBP");
//            dataBody.put("deviceId",DEVICE_ID);
//            dataBody.put("transactionId",UUID.randomUUID());
//            dataBody.put("bfpHash",bfpHash);
//            dataBody.put("uuid", getUUID());
//
//            String dataEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(dataBody));
//            Map<String, Object> data = new HashMap<>();
//
//            data.put("clientId",clientId);
//            data.put("clientSecret",clientSecret);
//            data.put("data", dataEncriptada);
//
//            responses = RestAssured
//                    .given()
//                    .urlEncodingEnabled(false)
//                    .contentType(ContentType.JSON)
//                    .header("x-keyvalue", serviciosDevops.getXKeyValue())
//                    .header("jwt", jwtLogin)
//                    .header("clientId",clientId)
//                    .header("clientSecret",clientSecret)
//                    //.header("GeneratedUUID", getUUID())
//                    .body( new Gson().toJson(data) )
//                    //.header("x-keyvalue",getXKeyValue())
//
//                    //.header("jwt",getJwt())
//                    .log().all()
//                    .post(URL_API_FIRST_DEVICE_VALIDATION_POST);
//
//            System.out.println("FIRST_DEVICE_VALIDATION: "+responses.getStatusCode());
//        }
//    }




}
