package apis.ibp.configuracion;

//import apis.ibp.dashboard.APISLogin;
import apis.ibp.APISLogin;
import apis.ibp.BaseRequest;
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
 * @Date 15/05/2024 2:22 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISConfigurarResumenProductos extends BaseRequest {

    private APISLogin login;
    public APISConfigurarResumenProductos(APISLogin login) {
        super(login);
        this.login = login;
    }

//    private Response response;
//    private APISLogin login;
//
//    /**
//     * Metodos: GET, DELETE
//     */
//    private String urlConfiguracion = HOST +"/bhdleon/api/v1/personal/web/configuration";
//
//
//    public APISConfigurarResumenProductos(APISLogin login){
//        this.login = login;
//    }
//
//    public void consultarResumenProductosConfiguracion200(boolean isPassport){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//            encriptar.put("transactionId", UUID.randomUUID());
//    //        encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            encriptar.put("cfg","0");
//            encriptar.put("isPassport",isPassport);
//            encriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(urlConfiguracion+"?" + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("?",""));
//
//            System.out.println("RESPUESTA DASHBOARD CONFIGURACION: " + response.getStatusCode());
//         //   codigoDeEstado = response.getStatusCode();
//        }else {
//        //    codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void restablecerOrden(boolean isPassport){
//
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//     //       encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            encriptar.put("transactionId",UUID.randomUUID());
//            encriptar.put("uuid",login.getUUID());
//
//            encriptar.put("isPassport",isPassport);
//
//
//            Map<String, Object> dataBody = new HashMap<>();
//            dataBody.put("data",ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)));
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
//                    .delete(urlConfiguracion);
//
//            System.out.println("RESTABLECER ORDEN: " + response.getStatusCode());
//        }
//    }
//
//    public static void main(String[] args) {
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        APISConfigurarResumenProductos configuracion = new APISConfigurarResumenProductos(login);
//        configuracion.consultarResumenProductosConfiguracion200(false);
//        configuracion.restablecerOrden(false);
//    }


    public void consultarConfiguracion(boolean isPassport, String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("cfg", "1");
        encriptar.put("isPassport",isPassport);

        httpGET(encriptar,"/bhdleon/api/v1/personal/web/configuration",accion,nombreMetodo,getClass());
    }

    public void restaurarOrdenProductos(boolean isPassport, String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("isPassport",isPassport);

        httpDelete(encriptar,"/bhdleon/api/v1/personal/web/configuration",accion,nombreMetodo,getClass());
    }
}
