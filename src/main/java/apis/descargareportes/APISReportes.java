package apis.descargareportes;

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
 * @Date 17/05/2024 2:00 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISReportes {

    private APISLogin login;
    private Response response;

//    private String urlEstadoCuenta = HOST + "/bhdleon/api/v1/personal/web/account-statements-report";
//    private String urlAccountsReport = HOST + "/bhdleon/api/v1/personal/web/accounts-report";
//
//    public APISReportes(APISLogin login){
//        this.login = login;
//    }
//
//    public void dashboardAccountStatementsReport(String numeroCuenta, String tipoCuenta, String fechaInicio, String fechaFinal){
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar =login.getMapaComunUUID();
//            encriptar.put("accountNumber",numeroCuenta);
//            encriptar.put("accountType",tipoCuenta);
//            encriptar.put("startDate",fechaInicio);
//            encriptar.put("endDate",fechaFinal);
//
//            response = RestAssured.given().
//                    urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(urlEstadoCuenta +"?"+ ServiciosDevops.getInstance().encriptarData( new Gson().toJson(encriptar)).replace("=",""));
//
//            System.out.println("Reporte estado cuenta: " + response.getStatusCode());
//        }
//    }
//
//    public static void main(String[] args) {
//        APISLogin loginAPI = APISLogin.getInstance().iniciarSesionDispositivoSeguro("03102674383","1111");
//
//        APISReportes reportes = new APISReportes(loginAPI);
//        reportes.dashboardAccountStatementsReport("00480830059","CUA","01/01/2024","31/03/2024");
//    }
}
