package apis.consultas;

import apis.ibp.APISLogin;
//import apis.ibp.dashboard.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 17/05/2024 12:40 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISTransferenciasRegionalesSIPA {

    private APISLogin login;
    private Response response;

//    private String urlCountriesSIPA = HOST +"/bhdleon/api/v1/personal/web/countries-sipa";
//    private String urlBancosSIPA = HOST +"/bhdleon/api/v1/personal/web/banks-sipa";
//
//    public APISTransferenciasRegionalesSIPA(APISLogin login){
//        this.login = login;
//    }
//
//    public void queryCountriesSIPA200(){
//        if (login.isResponseSuccessful()){
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(urlCountriesSIPA+"?" + login.getDataComunEncriptada());
//
//            System.out.println("countries-sipa: " + response.getStatusCode());
//        }
//    }
//
//    public void queryBancosSIPA200(String siglaBanco){
//        if (login.isResponseSuccessful()){
//            Map<String, Object> encriptar = login.getMapaComunUUID();
//            encriptar.put("countryCode",siglaBanco);
//
//            String dataEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar));
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(urlBancosSIPA+"?" + dataEncriptada.replace("=",""));
//
//            System.out.println("banks-sipa: " + response.getStatusCode());
//        }
//    }
//
//    public static void main(String[] args) {
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("03102674383","1111");
//
//        APISTransferenciasRegionalesSIPA sipa = new APISTransferenciasRegionalesSIPA(login);
//        //sipa.queryCountriesSIPA200();
//        sipa.queryBancosSIPA200("ELS");
//    }
}
