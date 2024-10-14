package apis.consultas;

//import apis.ibp.dashboard.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// import static apis.ibp.dashboard.APISLogin.DEVICE_ID;
//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 13/05/2024 9:40 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISolicitudesReclamaciones {

    private Response response;
//    private APISLogin login;
//
//    private String urlSolicitudesGET = HOST + "/bhdleon/api/v1/personal/web/request-claim?";
//    private String urlConsultarComprobanteFiscalGET = HOST + "/bhdleon/api/v2/personal/web/ncf?";
//    private String urlComboSolicitudesReclamaciones = HOST + "/bhdleon/api/v1/personal/web/request-claim-combo?";
//
//    public APISolicitudesReclamaciones(APISLogin login){
//        this.login = login;
//    }
//
//    /**
//     *
//     * @param fechaInicio 3/13/2024
//     * @param fechaFin 5/13/2024
//     */
//    public void solicutudesReclamosRealizados200(String fechaInicio, String fechaFin){
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//     //       encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            encriptar.put("transactionId", UUID.randomUUID());
//            encriptar.put("startDate",fechaInicio);
//            encriptar.put("endDate",fechaFin);
//            encriptar.put("uuid",login.getUUID());
//
//            try {
//                response = RestAssured.given().urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlSolicitudesGET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("=",""));
//
//                System.out.println("CONSULTA DE SOLICITUDES: " + response.getStatusCode());
//
//            }catch (Exception e){
//
//            }
//        }
//    }
//
//
//    public void consultarComprobanteFiscal204(String fechaInicio, String fechaFin){
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//   //         encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            encriptar.put("transactionId", UUID.randomUUID());
//            encriptar.put("startDate",fechaInicio);
//            encriptar.put("endDate",fechaFin);
//            encriptar.put("uuid",login.getUUID());
//
//            try {
//                response = RestAssured.given().urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlConsultarComprobanteFiscalGET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("=",""));
//
//                System.out.println("CONSULTA COMPROBANTE FISCAL: " + response.getStatusCode());
//
//            }catch (Exception e){
//
//            }
//        }
//    }
//
//    public void comboSolicitud(){
//        solicitudesReclamaciones("request");
//    }
//
//    public void comboReclamacion(){
//        solicitudesReclamaciones("claim");
//    }
//
//    /**
//     *
//     * @param processType request,claim
//     */
//    public void solicitudesReclamaciones(String processType){
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//     //       encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            encriptar.put("transactionId", UUID.randomUUID());
//            encriptar.put("processType",processType);
//            encriptar.put("uuid",login.getUUID());
//
//            try {
//                response = RestAssured.given().urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlComboSolicitudesReclamaciones + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("=",""));
//
//                System.out.println("CONSULTA "+ processType +": " + response.getStatusCode());
//
//            }catch (Exception e){
//
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        APISolicitudesReclamaciones solicitudes = new APISolicitudesReclamaciones(login);
//        solicitudes.solicutudesReclamosRealizados200("4/13/2024","5/13/2024");
//        solicitudes.consultarComprobanteFiscal204("01/4/2024","30/4/2024");
//
//        solicitudes.comboSolicitud();
//        solicitudes.comboReclamacion();
//    }
}
