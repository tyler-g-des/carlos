package apis.ibp.configuracion;

//import apis.ibp.dashboard.APISLogin;
import apis.ibp.APISLogin;
import apis.ibp.BaseRequest;

import java.util.Map;

//import static apis.ibp.dashboard.APISLogin.DEVICE_ID;
//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 16/05/2024 8:03 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISInformacionAccesoSeguridad extends BaseRequest {
    public APISInformacionAccesoSeguridad(APISLogin login) {
        super(login);
    }

//    private APISLogin login;
//    private Response response;
//    private String urlSecurityQuestions = HOST +"/bhdleon/api/v1/personal/web/security-questions";
//    private final String URL_API_FORGOT_PASSWORD_POST = HOST + "/bhdleon/api/v1/personal/web/forgot-password";
//    private String urlChangePassword = HOST +"/bhdleon/api/v1/personal/web/change-password";
//
//    public APISInformacionAccesoSeguridad(APISLogin login){
//        this.login = login;
//    }
//    public void forgotPassword200(String documentNumber){
//        //serviciosDevops.generarXkeyValue(x509cert);
//        //iniciarSesion(username,contrasenaActual);
//        //iniciarSesion2(username,contrasenaActual);
//        if (login.isResponseSuccessful()) {
//            response = null;
//            try {
//                Map<String, Object> dataBody = new HashMap<>();
//                dataBody.put("channel", "IBP");
//                dataBody.put("platform", "IBP");
//       //         dataBody.put("deviceId", DEVICE_ID);   colocar de nuevo
//                dataBody.put("transactionId", UUID.randomUUID());
//                dataBody.put("username", documentNumber);
//                dataBody.put("uuid",login.getUUID());
//
//                Map<String, Object> data = new HashMap<>();
//                data.put("clientId",login.getClientId());
//                data.put("clientSecret",login.getClientSecret());
//                data.put("data", ServiciosDevops.getInstance().encriptarData(new Gson().toJson(dataBody)));
//
//                //dataBody.put("data", getDataComunEncriptada());
//                response = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .header("x-keyvalue",  login.getXKeyValue())
//                        .header("jwt", login.getJwtLogin())
//                        .header("clientId",login.getClientId())
//                        .header("clientSecret",login.getClientSecret())
//                        //.body(String.format(jsonAPILogin,clientId,clientSecret,dataEncriptadaLogin))
//
//                        .body(new Gson().toJson(data))
//                        //.header("x-keyvalue",getXKeyValue())
//                        //.header("GeneratedUUID", getUUID())
//                        //.header("jwt",getJwt())
//                        .log().all()
//                        .post(URL_API_FORGOT_PASSWORD_POST);
//
//                System.out.println("NUEVA RESPUESTA FORGOT_PASSWORD: " + response.getStatusCode());
//                //codigoDeEstado = responses.getStatusCode();
//            } catch (Exception e) {
//               // codigoDeEstado = responses.getStatusCode();
//               // System.out.println("ALGO SALIO MAL EN FORGOT_PASSWORD: " + codigoDeEstado);
//            }
//        }
//        //}else {
//        //    codigoDeEstado = getStatusCode();
//        // }
//        //dtComunEncriptada = ServiciosDevops.getInstance().encriptarData(String.format(JSON_ENCRIPTAR_COMUN_DASHBOARD,DEVICE_ID,login.getUUID())).replace("=","");
//    }
//
////    public void getSecurityQuestions(){
////        if (isResponseSuccessful()){
////            responses = null;
////            responses = RestAssured
////                    .given()
////                    .urlEncodingEnabled(true)
////                    .header("x-keyvalue",getXKeyValue())
////                    .header("GeneratedUUID", getUUID())
////                    .header("jwt",login. jwtLogin)
////                    .log().all()
////                    .get(urlSecurityQuestions + login.getDataComunEncriptada());
////            System.out.println("RESPUESTA GET SECURITY_QUESTIONS: " + responses.getStatusCode());
////            codigoDeEstado = responses.getStatusCode();
////        }else {
////            codigoDeEstado = getStatusCode();
////        }
////    }
//
//    public static void main(String[] args) {
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("22301391524","1111");
//        APISInformacionAccesoSeguridad seguridad = new APISInformacionAccesoSeguridad(login);
//        seguridad.forgotPassword200("22301391524");
//    }


    public void cambiarContrasena(String contrasenaAnterior, String nuevaContrasena, String confirmarContrasena, String keyCard, String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();

        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("oldPassword",contrasenaAnterior);
        encriptar.put("newPassword",nuevaContrasena);
        encriptar.put("confirmPassword",confirmarContrasena);
        encriptar.put("keyCard",keyCard);

        httpPost(encriptar,"/bhdleon/api/v1/personal/web/change-password", accion, nombreMetodo, getClass());
    }

    public void consultarKeyCard(String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        httpGETComun("/bhdleon/api/v1/personal/web/key-card", accion, nombreMetodo, getClass());
    }

    public void consultarKeyCard(String keyCard, String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("keyCard", keyCard);

        httpPost(encriptar,"/bhdleon/api/v1/personal/web/key-card", accion, nombreMetodo, getClass());

     //   getDataEncriptada();
    }

    public void consultarPreguntaSeguridad(String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        httpGETComun("/bhdleon/api/v1/personal/web/security-questions", accion, nombreMetodo, getClass());
    }

    private void habilitarDeshabilitarClaveTransaccionar(boolean deshabilitar,String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();

        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("allowTransactionWithoutKeyCard",deshabilitar);

        httpPost(encriptar,"/bhdleon/api/v1/personal/web/transaction-without-key-card", accion, nombreMetodo, getClass());
    }

    public static void main(String[] args) {
        APISLogin login = new APISLogin();
        login.loginSecureDevice("00100965995","1111","");
        APISInformacionAccesoSeguridad seguridad = new APISInformacionAccesoSeguridad(login);
        //seguridad.cambiarContrasena("2222","1111","1111","1111","Cambiar contrasena");
        seguridad.consultarKeyCard("1111","Consultar Key Key");
        ///seguridad.consultarPreguntaSeguridad("Preguntas");
        seguridad.habilitarDeshabilitarClaveTransaccionar(false,"Deshabilitar clave transaccionar");
        System.out.println(seguridad.getStatusCode());
    }
}
