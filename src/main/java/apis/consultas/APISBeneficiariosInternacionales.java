package apis.consultas;

//import apis.ibp.dashboard.APISLogin;
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
 * @Date 13/05/2024 4:53 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISBeneficiariosInternacionales {

    private Response response;
//    private APISLogin login;
//
//    private String urlBeneficiarios = HOST +"/bhdleon/api/v1/personal/web/international-beneficiaries";
//    private String urlPaisesInternacional = HOST + "/bhdleon/api/v1/personal/web/international-countries";
//    private String urlInformacionDeBanco = HOST + "/bhdleon/api/v1/personal/web/international-bank-information";
//    private String urlVerificarCodigo = HOST + "/bhdleon/api/v1/personal/web/international-check-code";
//
//
//    public APISBeneficiariosInternacionales(APISLogin login){
//        this.login = login;
//    }
//
//    public void getListaBeneficiarios(){
//        if (login.isResponseSuccessful()){
//            try {
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlBeneficiarios+"?" + login.getDataComunEncriptada());
//
//                System.out.println("BENEFICIARIOS INTERNCIONALES: " + response.getStatusCode());
//            }catch (Exception e){
//            }
//        }
//    }
//
//    public void getInformacionBanco(){
//        if (login.isResponseSuccessful()){
//            try {
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlInformacionDeBanco+"?" + login.getDataComunEncriptada());
//
//                System.out.println("INFORMACION DE BANCO: " + response.getStatusCode());
//            }catch (Exception e){
//            }
//        }
//    }
//
//
//
//    public void getPaisesInternacionales(){
//        if (login.isResponseSuccessful()){
//            try {
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlPaisesInternacional+"?" + login.getDataComunEncriptada());
//
//                System.out.println("PAISES INTERNACIONALES: " + response.getStatusCode());
//            }catch (Exception e){
//            }
//        }
//    }
//
//    public void consultarCodigoSWIFT(String codigo){
//        consultarCodigosBanco(codigo,"2");
//    }
//
//    public void consultarCodigoABA(String codigo){
//        consultarCodigosBanco(codigo,"1");
//    }
//
//    /**
//     *
//     * @param codigo codigo a validar.
//     * @param tipo 1,2. 1=ABA, 2=SWIFT
//     */
//    public void consultarCodigosBanco(String codigo, String tipo){
//        if (login.isResponseSuccessful()){
//            try {
//
//                Map<String, Object> encriptar = new HashMap<>();
//                encriptar.put("channel","IBP");
//                encriptar.put("platform","IBP");
////                encriptar.put("deviceId",DEVICE_ID);      colocar de nuevo
//                encriptar.put("transactionId", UUID.randomUUID());
//                encriptar.put("code",codigo);
//                encriptar.put("type",tipo);
//                encriptar.put("uuid",login.getUUID());
//
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
////                        .header("clientId",login.getClientId())
////                        .header("clientSecret",login.getClientSecret())
//                        .log().all()
//                        .get(urlVerificarCodigo+"?" + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("?",""));
//
//                System.out.println("BERIFICAR CODIGO: " + response.getStatusCode());
//            }catch (Exception e){
//
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        APISBeneficiariosInternacionales beneficiario = new APISBeneficiariosInternacionales(login);
//        //beneficiario.getListaBeneficiarios();
//        //beneficiario.verificarCodigo("AAALSARIXXX","2");
//        //beneficiario.consultarCodigoSWIFT("MATPUS31XXX");
//        //beneficiario.consultarCodigoABA("266086554");
//        beneficiario.getInformacionBanco();
//        beneficiario.getPaisesInternacionales();
//    }

}
