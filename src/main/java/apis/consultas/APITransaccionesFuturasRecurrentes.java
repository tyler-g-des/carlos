package apis.consultas;

//import apis.ibp.dashboard.APISLogin;
import apis.ibp.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//import static apis.ibp.dashboard.APISLogin.DEVICE_ID;
//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 10/05/2024 11:05 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APITransaccionesFuturasRecurrentes {

    private APISLogin login;
    Response response;

//    private String urlTransaccionesFuturasRecurrentesGET = HOST + "/bhdleon/api/v1/personal/web/future-transactions?";
//
//    public APITransaccionesFuturasRecurrentes(APISLogin login){
//        this.login = login;
//    }
//
//    public void getTransaccionesFuturasRecurrentes(){
//        if (login.isResponseSuccessful()){
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(urlTransaccionesFuturasRecurrentesGET + login.getDataComunEncriptada());
//
//            System.out.println("LISTA TRANSACCIONES FUTURAS RECURRENTES: " + response.getStatusCode());
//
//        }
//    }
//
//    public void eliminarTransacionFutura(String recurrenceId){
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//     //       encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            encriptar.put("platform","IBP");
//            encriptar.put("transactionId", UUID.randomUUID());
//            encriptar.put("recurrenceId",recurrenceId);
//            encriptar.put("uuid",login.getUUID());
//
//            Map<String, Object> dataBody = new HashMap<>();
//            dataBody.put("data", ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)));
//            dataBody.put("clientId",login.getClientId());
//            dataBody.put("clientSecret",login.getClientSecret());
//
//            response  = RestAssured
//                    .given()
//                    .contentType(ContentType.JSON)
//                    .body(new Gson().toJson(dataBody))
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId",login.getClientId())
//                    .header("clientSecret",login.getClientSecret())
//                    .log().all()
//                    .delete(urlTransaccionesFuturasRecurrentesGET.replace("?",""));
//
//            System.out.println("Status al eliminar Servicio: " + response.getStatusCode());
//        }
//    }
//
//
//    public static void main(String[] args) {
//
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        APITransaccionesFuturasRecurrentes transaccionesFuturas = new APITransaccionesFuturasRecurrentes(login);
//        transaccionesFuturas.getTransaccionesFuturasRecurrentes();
//        transaccionesFuturas.eliminarTransacionFutura("48772");
//    }
}
