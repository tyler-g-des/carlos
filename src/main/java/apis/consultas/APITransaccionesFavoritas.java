package apis.consultas;

//import apis.ibp.dashboard.APISLogin;
import apis.ibp.APISLogin;
import io.restassured.RestAssured;
import io.restassured.response.Response;

//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 13/05/2024 12:32 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APITransaccionesFavoritas {

    private Response response;
    private APISLogin login;

//    private String urlTransaccionesFavoritas = HOST +"/bhdleon/api/v1/personal/web/favorite-transactions?";
//
//    public APITransaccionesFavoritas(APISLogin login){
//        this.login = login;
//    }
//
//    public void getListaTransaccionsFavoritas(){
//        if (login.isResponseSuccessful()){
//
//            try {
//                response = RestAssured.given().urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlTransaccionesFavoritas + login.getDataComunEncriptada());
//
//                System.out.println("CONSULTA LISTA DE TRANSACCIONES FAVORITAS: " + response.getStatusCode());
//
//            }catch (Exception e){
//
//            }
//        }
//    }
//
//
//    public static void main(String[] args) {
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        APITransaccionesFavoritas favoritas = new APITransaccionesFavoritas(login);
//        favoritas.getListaTransaccionsFavoritas();
//        //login.logOut();
//
//        APISLogin login2 = APISLogin.getInstance().iniciarSesionDispositivoSeguro("22301391524","1111");
//        APITransaccionesFavoritas favoritas2 = new APITransaccionesFavoritas(login2);
//        favoritas2.getListaTransaccionsFavoritas();
//    }
}
