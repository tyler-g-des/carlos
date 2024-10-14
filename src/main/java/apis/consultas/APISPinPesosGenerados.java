package apis.consultas;

import apis.ibp.APISLogin;
//import apis.ibp.dashboard.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 28/05/2024 10:22 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISPinPesosGenerados {

    private APISLogin login;
    private Response response;

//    private String urlPinPesosGenerados  = HOST + "/bhdleon/api/v1/personal/web/pin-pesos";
//    private String urlReportesPinPesosGenerados = HOST + "/bhdleon/api/v1/personal/web/pinpesos-report";
//    private String urlCancelarPinPesosGenerados = HOST + "/bhdleon/api/v1/personal/web/pinpesos-cancellation";
//
//    public APISPinPesosGenerados(APISLogin login){
//        this.login = login;
//    }
//
//    public String getReporteAPI(){
//        return login.getReporteAPI();
//    }
//
//    public int getStatusCode(){
//        return response.getStatusCode();
//    }
//
//    public void consultarPinPesosGeneradosUltimoMes(){
//        consultarPinPesosGenerados(1,"","");
//    }
//
//    public void consultarPinPesosGeneradosDosUltimosMes(){
//        consultarPinPesosGenerados(2,"","");
//    }
//
//    public void consultarPinPesosGeneradosTresUltimoMes(){
//        consultarPinPesosGenerados(3,"","");
//    }
//
//    public void consultarPinPesosGeneradoPorRangoFecha(String fechaInicio,String fechaFinal){
//        consultarPinPesosGenerados(0, fechaInicio, fechaFinal);
//    }
//
//    private void consultarPinPesosGenerados(int noMeses, String fechaInicio, String fechaFinal){
//
//        Map<String, Object> encriptar = login.getMapaComunUUID();
//        encriptar.put("noMonths",noMeses);
//        encriptar.put("startDate",fechaInicio);
//        encriptar.put("endDate",fechaFinal);
//        try {
//            response = null;
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(urlPinPesosGenerados + "?" + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("=",""));
//
//            login.setReporte(urlPinPesosGenerados, response,"CONSULTAR PIN PESOS GENERADOS","GET");
//            System.out.println("RESPUESTA CONSULTAR PIN PESOS GENERADOS: " + response.getStatusCode());
//        }catch (Exception e){
//            System.out.println(e);
//            login.setReportError(urlPinPesosGenerados,"CONSULTAR PIN PESOS GENERADOS","GET", e);
//        }
//    }
//
//
//    public void cancelarPinPesosGenerados(String productOrigen, String tipoProductOrigen, String productDestino, String pin, String descripcion){
//        response = null;
//            try {
//
//                Map<String, Object> encriptar = login.getMapaComunUUID();
//                encriptar.put("debitProductNumber",productOrigen);
//                encriptar.put("debitProductType",tipoProductOrigen);
//                encriptar.put("creditProductNumber",productDestino);
//                encriptar.put("pin", pin);
//                encriptar.put("description", descripcion);
//
//                Map<String, Object> body = login.getMapaBodyComun();
//                body.put("data",ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)));
//
//                //String dtEncritadaMarketing = ServiciosDevops.getInstance().encriptarData(String.format( jsonMarketingDecision.replace("$","\""),documentNumber,documentNumber,DEVICE_ID,login.getUUID() ) );
//                response = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body(new Gson().toJson(body))
//                        //.body("{ \"data\": \""+dtEncritadaMarketing+"\" }")
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .post(urlCancelarPinPesosGenerados);
//                // header Clientid, Clientsecret
//
//                login.setReporte(urlPinPesosGenerados, response,"CANCELAR PIN PESOS GENERADOS","GET");
//                System.out.println("RESPUESTA CANCELAR PIN PESOS GENERADOS: " + response.getStatusCode());
//            }catch (Exception e){
//                System.out.println(e);
//                login.setReportError(urlPinPesosGenerados,"CANCELAR PIN PESOS GENERADOS","GET", e);
//            }
//    }
}
