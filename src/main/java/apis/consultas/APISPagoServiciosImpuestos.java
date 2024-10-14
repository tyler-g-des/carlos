package apis.consultas;

//import apis.ibp.dashboard.APISLogin;
import apis.ibp.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//import static apis.ibp.dashboard.APISLogin.DEVICE_ID;
//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 13/05/2024 9:08 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISPagoServiciosImpuestos {

    private Response response;
    private APISLogin login;

//    private String urlConsultarServicio = HOST +"/bhdleon/api/v1/personal/web/invoice-by-service?";
//    private String urlTiempoDeTransacciones = HOST + "/bhdleon/api/v1/services/transactions/time?";
//
//    public APISPagoServiciosImpuestos(APISLogin login){
//        this.login = login;
//    }
//
//    public void consultarServicio200(String referenceNumber, String serviceId){
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//     //       encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            encriptar.put("transactionId", UUID.randomUUID());
//            encriptar.put("referenceNumber",referenceNumber);
//            encriptar.put("serviceId",serviceId);
//            encriptar.put("uuid",login.getUUID());
//
//            try {
//                response = RestAssured.given().urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlConsultarServicio + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("=",""));
//
//                System.out.println("CONSULTA DE SERVICIO: " + response.getStatusCode());
//
//            }catch (Exception e){
//
//            }
//        }
//    }
//
//    public void tiempoDeTransacciones200(){
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//    //        encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            encriptar.put("transactionId", UUID.randomUUID());
//            //encriptar.put("referenceNumber",referenceNumber);
//            //encriptar.put("serviceId",serviceId);
//            encriptar.put("uuid",login.getUUID());
//
//            try {
//                response = RestAssured.given().urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlTiempoDeTransacciones + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("=",""));
//
//                System.out.println("TIEMPO DE TRANSACCIONES: " + response.getStatusCode());
//
//            }catch (Exception e){
//
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("03700163748","1111");
//        APISPagoServiciosImpuestos servicio = new APISPagoServiciosImpuestos(login);
//        servicio.consultarServicio200("8298164488","4071");
//        servicio.tiempoDeTransacciones200();
//    }
}
