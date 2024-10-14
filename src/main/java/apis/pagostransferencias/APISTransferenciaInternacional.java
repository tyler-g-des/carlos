package apis.pagostransferencias;

//import apis.ibp.dashboard.APISLogin;
import io.restassured.RestAssured;
import io.restassured.response.Response;

//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 15/05/2024 7:57 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISTransferenciaInternacional {

//    private Response response;
//    private APISLogin login;
//
//    private String urlInternationalRates = HOST + "/bhdleon/api/v1/personal/web/international-rates?"; // En transferencia internacional
//
//    public APISTransferenciaInternacional(APISLogin login){
//        this.login = login;
//    }
//
//    public void tarifasInternacionales200(){
//        if (login.isResponseSuccessful()){
//            try {
//
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlInternationalRates + login.getDataComunEncriptada());
//
//                System.out.println("TARIFAS INTERNACIONALES: " + response.getStatusCode());
//            }catch (Exception e){
//
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        APISTransferenciaInternacional pytInternacional = new APISTransferenciaInternacional(login);
//        pytInternacional.tarifasInternacionales200();
//    }
}
