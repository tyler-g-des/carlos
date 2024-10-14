package apis;

//import apis.ibp.dashboard.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import microservicios.Utilidad;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// import static apis.ibp.dashboard.APISLogin.DEVICE_ID;   colocar de nuevo
//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 02/05/2024 2:57 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APIBeneficiariosInscritos {

//    private APISLogin login;
//    private Response response = null;
//
//    private String urlBeneficiariesNacionalesPinPesosGet = HOST + "/bhdleon/api/v1/personal/web/beneficiaries?";
//    private String urlBankListNacionalesGet = HOST +"/bhdleon/api/v1/personal/web/banks-list?";
//    private String urlInterncionalCountriesGet = HOST +"/bhdleon/api/v1/personal/web/international-countries?";
//    private String urlAccountNameGet = HOST + "/bhdleon/api/v1/personal/web/account-name?";
//    private String urlNeneficiariesInternationalPost = HOST + "/bhdleon/api/v1/personal/web/international-beneficiaries";
//    private String urlBeneficiarioPinPesosPost = HOST + "/bhdleon/api/v1/personal/web/beneficiaries-pinpesos";
//    private String urlBeneficiariesNacionalesPinPesosDelete = HOST + "/bhdleon/api/v1/personal/web/beneficiaries";
//
//    public APIBeneficiariosInscritos(APISLogin login){
//        this.login = login;
//    }
//
//    public void consultarBeneficiariosNacionales(){
//        if (login.isResponseSuccessful()){
//            response = null;
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID",login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all().get(urlBeneficiariesNacionalesPinPesosGet + login.getDataComunEncriptada());
//            System.out.println();
//            System.out.println("Beneficiarios Nacionales: "+ response.getStatusCode());
//            System.out.println();
//        }
//    }
//
//    public void banckListNacionales(){
//        if (login.isResponseSuccessful()){
//            response = null;
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID",login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all().get(urlBankListNacionalesGet + login.getDataComunEncriptada());
//            System.out.println();
//            System.out.println("LISTA DE BANCOS NACIONALES: "+ response.getStatusCode());
//            System.out.println();
//        }
//    }
//
//
//
//    public void getInternacionalCountries(){
//        if (login.isResponseSuccessful()){
//            response = null;
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID",login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all().get(urlInterncionalCountriesGet + login.getDataComunEncriptada());
//            System.out.println();
//            System.out.println("Interncional Countries: "+ response.getStatusCode());
//            System.out.println();
//
//            JsonNode jsonNode = Utilidad.jsonNodeParse(response.getBody().asString());
//            System.out.println("JSON: " + jsonNode);
//            String resultado =  ServiciosDevops.getInstance().desencriptarData( login.getUUID(), jsonNode.get("data").asText());
//            System.out.println();
//            System.out.println(new Gson().toJson(resultado));
//        }
//    }
//
//    /**
//     *
//     * @param productNumber numeros de productos BHD o BHDIB
//     * @param bankCode BHD, BHDIB
//     */
//    public void accountNameNacionalesBHDBDIPanama(String productNumber, String bankCode){
//        if (login.isResponseSuccessful()){
//            response = null;
//
//            Map<String, Object> dataBody = new HashMap<>();
//            dataBody.put("bankCode",bankCode);
//            dataBody.put("channel","IBP");
//     //       dataBody.put("deviceId",DEVICE_ID);   colocar de nuevo
//            dataBody.put("platform","IBP");
//            dataBody.put("productNumber", productNumber);
//            dataBody.put("transactionId",UUID.randomUUID());
//            dataBody.put("uuid",login.getUUID());
//
//
//
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID",login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all().get(urlAccountNameGet + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(dataBody)).replace("=",""));
//            System.out.println();
//            System.out.println("Account Name: "+ response.getStatusCode());
//            System.out.println();
//        }
//    }
//
//    public void consultarBeneficiariosInternacionales(){
//        if (login.isResponseSuccessful()){
//            response = null;
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID",login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all().get(urlNeneficiariesInternationalPost + login.getDataComunEncriptada());
//            System.out.println();
//            System.out.println("Beneficiarios Internacionales: "+ response.getStatusCode());
//            System.out.println();
//        }
//    }
//
//    public void agregarBeneficiarioPinPesos(String alias, String telefono, String tipoDocumento, String numDocumento, String nombre, String pais, String genero){
//        if (login.isResponseSuccessful()){
//            response = null;
//
//            Map<String, Object> beneficiary = new HashMap<>();
//            beneficiary.put("nationality",pais);
//            beneficiary.put("gender",genero);
//            beneficiary.put("creditProductNumber",telefono);
//            beneficiary.put("creditProductAlias",alias);
//            beneficiary.put("creditProductBankCode","BHD");
//            beneficiary.put("creditProductHolderName",nombre);
//            beneficiary.put("documentNumber",numDocumento);
//            beneficiary.put("documentType",tipoDocumento);
//            beneficiary.put("acceptDollar","N");
//            beneficiary.put("bhdACHLBTR","");
//
//            beneficiary.put("accountNumber",null);
//            beneficiary.put("accountType",null);
//            beneficiary.put("address",null);
//            beneficiary.put("alias",null);
//            beneficiary.put("amountRD",null);
//            beneficiary.put("atmLocation",null);
//            beneficiary.put("bank",null);
//            beneficiary.put("bankCodeNumber",null);
//            beneficiary.put("bankName",null);
//            beneficiary.put("banksFormat",null);
//            beneficiary.put("beneficiaryId",null);
//            beneficiary.put("bhdLBTRDocumentNumber",null);
//            beneficiary.put("bhdLBTRDocumentType",null);
//            beneficiary.put("bhdLastTransactionAmount",null);
//            beneficiary.put("bhdLastTransactionCurrency",null);
//            beneficiary.put("bhdLastTransactionDate",null);
//            beneficiary.put("classification",null);
//            beneficiary.put("clientBeneficiaryName",null);
//            beneficiary.put("country",null);
//            beneficiary.put("currency",null);
//            beneficiary.put("date",null);
//            beneficiary.put("debitProductNumber",null);
//            beneficiary.put("debitProductType",null);
//            beneficiary.put("description",null);
//            beneficiary.put("documentId",null);
//            beneficiary.put("email",null);
//            beneficiary.put("favoriteAccountAlias",null);
//            beneficiary.put("favoriteAccountName",null);
//            beneficiary.put("id",null);
//            beneficiary.put("name",null);
//            beneficiary.put("pin",null);
//            beneficiary.put("pinPesosLabel",null);
//            beneficiary.put("productNumber",null);
//            beneficiary.put("senderEmail",null);
//            beneficiary.put("status",null);
//            beneficiary.put("type",null);
//
//
//            Map<String, Object> dataEncriptar = new HashMap<>();
//            dataEncriptar.put("beneficiaryType","Pin-pesos");
//            dataEncriptar.put("transactionId",UUID.randomUUID());
//  //          dataEncriptar.put("deviceId",DEVICE_ID);    colocar de nuevo
//            dataEncriptar.put("channel","IBP");
//            dataEncriptar.put("platform","IBP");
//            dataEncriptar.put("beneficiary",beneficiary);
//            dataEncriptar.put("uuid",login.getUUID());
//
//            Map<String, Object> dataBody = new HashMap<>();
//            dataBody.put("clientId",login.getClientId());
//            dataBody.put("clientSecret",login.getClientSecret());
//            dataBody.put("data", ServiciosDevops.getInstance().encriptarData(new Gson().toJson(dataEncriptar)));
//
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .contentType(ContentType.JSON)
//                    .body( new Gson().toJson( dataBody))
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID",login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId",login.getClientId())
//                    .header("clientSecret",login.getClientSecret())
//                    .log().all().post(urlBeneficiarioPinPesosPost);
//            System.out.println();
//            System.out.println("AGREGAR BEBEFICIARIO PIN PESOS: "+ response.getStatusCode());
//            System.out.println();
//        }
//    }
//
//    private String encriptarDataEliminarBeneficiario(String id){
//        Map<String, Object> dataBody = new HashMap<>();
//        dataBody.put("id",id);
//        dataBody.put("transactionId", UUID.randomUUID());
//    //    dataBody.put("deviceId",DEVICE_ID);      colocar de nuevo
//        dataBody.put("channel","IBP");
//        dataBody.put("platform","IBP");
//        dataBody.put("uuid",login.getUUID());
//
//        return ServiciosDevops.getInstance().encriptarData(new Gson().toJson(dataBody));
//    }
//
//    public void agregarbeneficiarioDeOtroBanco(){
//        try {
//
//            Map<String, Object> primerJson = login.getMapaComunUUID();
//            primerJson.put("beneficiaryType","National");
//
//            Map<String, Object> segundoJson = new HashMap<>();
//            segundoJson.put("currency","RD");
//            segundoJson.put("type","ACH");
//            segundoJson.put("name","carlos Alberto Loyola Loyola");
//            segundoJson.put("nationality","DO");
//            segundoJson.put("gender","M");
//            segundoJson.put("creditProductNumber","12345678977");
//            segundoJson.put("bhdAchLbtr","BHD");
//            segundoJson.put("creditProductAlias","Campos requeridos vacios");
//            segundoJson.put("creditProductBankCode","ABO");
//            segundoJson.put("creditProductHolderName","carlos Alberto Loyola Loyola");
//            segundoJson.put("creditProductType","CUA");
//            segundoJson.put("bhdDocumentNumberLbtr","132366444");
//            segundoJson.put("bhdDocumentTypeLbtr","2");
//            primerJson.put("beneficiary",segundoJson);
//
//            Map<String, Object> dataBody = new HashMap<>();
//            dataBody.put("clientId",login.getClientId());
//            dataBody.put("clientSecret",login.getClientSecret());
//            dataBody.put("data", ServiciosDevops.getInstance().encriptarData(new Gson().toJson(primerJson)));
//            //dataBody.put("data", ServiciosDevops.getInstance().encriptarData(new Gson().toJson(dataEncriptar)));
//
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .contentType(ContentType.JSON)
//                    .body( new Gson().toJson( dataBody))
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID",login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId",login.getClientId())
//                    .header("clientSecret",login.getClientSecret())
//                    .log().all().post(HOST+"/bhdleon/api/v1/personal/web/beneficiaries");
//            System.out.println();
//            System.out.println("AGREGAR BENEFICIARIO NACIONAL OTRO BANCO: "+ response.getStatusCode());
//            System.out.println();
//
//        }catch (Exception e){
//            System.out.println("Error metodo: agregarbeneficiarioDeOtroBanco. " + e);
//        }
//    }
//
//    public void eliminarBeneficiarioNacional(String id){
//
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> dataBody = new HashMap<>();
//            dataBody.put("data",encriptarDataEliminarBeneficiario(id));
//            dataBody.put("clientId",login.getClientId());
//            dataBody.put("clientSecret",login.getClientSecret());
//
//           response  = RestAssured
//                    .given()
//                    .contentType(ContentType.JSON)
//                    .body(new Gson().toJson(dataBody))
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                   .header("clientId",login.getClientId())
//                   .header("clientSecret",login.getClientSecret())
//                    .log().all()
//                    .delete(urlBeneficiariesNacionalesPinPesosDelete);
//
//            System.out.println("ELIMINAR BENEFICIARIO NACIONAL: " + response.getStatusCode());
//        }
//    }
//
//    public void eliminarBeneficiarioInternacional(String id){
//
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> dataBody = new HashMap<>();
//            dataBody.put("data",encriptarDataEliminarBeneficiario(id));
//            dataBody.put("clientId",login.getClientId());
//            dataBody.put("clientSecret",login.getClientSecret());
//
//            response  = RestAssured
//                    .given()
//                    .contentType(ContentType.JSON)
//                    .body( new Gson().toJson( dataBody ))
//                    .header("x-keyvalue", login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID() )
//                    .header("jwt", login.getJwtSecureDevice() )
//                    .header("clientId", login.getClientId() )
//                    .header("clientSecret", login.getClientSecret() )
//                    .log().all()
//                    .delete(urlNeneficiariesInternationalPost);
//
//            System.out.println("ELIMINAR BENEFICIARIO INTERNACIONAL: " + response.getStatusCode());
//        }
//    }
//
//    public void eliminarBeneficiarioPinPesos(String id){
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> dataBody = new HashMap<>();
//            dataBody.put("data",encriptarDataEliminarBeneficiario(id));
//            dataBody.put("clientId",login.getClientId());
//            dataBody.put("clientSecret",login.getClientSecret());
//
//            response  = RestAssured
//                    .given()
//                    .contentType(ContentType.JSON)
//                    .body( new Gson().toJson( dataBody ))
//                    .header("x-keyvalue", login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID() )
//                    .header("jwt", login.getJwtSecureDevice() )
//                    .header("clientId", login.getClientId() )
//                    .header("clientSecret", login.getClientSecret() )
//                    .log().all()
//                    .delete(urlBeneficiarioPinPesosPost);
//
//            System.out.println("ELIMINAR BENEFICIARIO PIN PESOS: " + response.getStatusCode());
//        }
//    }
//
//
//    public static void main(String[] args) {
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00100965995","1111");
//        APIBeneficiariosInscritos beneficiario = new APIBeneficiariosInscritos(login);
//
//        beneficiario.agregarbeneficiarioDeOtroBanco();
//
//
//        //LoginAPI login = LoginAPI.getInstance().iniciarSesion("00111435384","1111");
//
//      //  MsBeneficiariosNacionales msBeneficiario = new MsBeneficiariosNacionales("22301391524").newQuery().selecOtrosBancos();
//        //System.out.println(msBeneficiario.buscar("149570800109").getId());
//        //String idBeneficiarioNacional = msBeneficiario.buscar("149570800109").getId();
//
////        MsBeneficiariosNacionales beneficiarioPinPesos = new MsBeneficiariosNacionales("22301391524").newQuery().selecPinPesos().buscar("8298888891");
////        System.out.println(beneficiarioPinPesos.getId());
//
//
////        LoginAPI login = LoginAPI.getInstance().iniciarSesion("22301391524","1111");
////        APIBeneficiariosInscritos servicioBeneficiario = new APIBeneficiariosInscritos(login);
////        //servicioBeneficiario.banckListNacionales();
////        //servicioBeneficiario.accountNameNacionalesBHDBDIPanama("09931270033","BHD"); 8298888888
////       // servicioBeneficiario.getInternacionalCountries();
////       // servicioBeneficiario.agregarBeneficiarioPinPesos("nuevo Alias","8298888891","3","22301391524","Nombre Elias","CH","M");
////
////
////        //servicioBeneficiario.consultarBeneficiariosNacionales();
////        //servicioBeneficiario.consultarBeneficiariosInternacionales();
////        //servicioBeneficiario.eliminarBeneficiarioNacional(idBeneficiarioNacional);
////
//////        MsBeneficiariosInternacionales beneficiariosInternacionales = new MsBeneficiariosInternacionales("22301391524").newQuery().buscar("46565664646464");
//////
//////        String idBeneficiarioInternacional = beneficiariosInternacionales.getId();
//////
//////        System.out.println("ID BENEFICIARIO" + idBeneficiarioInternacional);
//////
//////
//////        servicioBeneficiario.eliminarBeneficiarioInternacional(idBeneficiarioInternacional);
////
////
////        // ELIMINAR BENEFICIARIO PIN PESOS / BHD / OTROS BANCOS / INTERNACIONAL
////        MsBeneficiariosNacionales beneficiarioPinPesos = new MsBeneficiariosNacionales("22301391524").newQuery().selecPinPesos().buscar("8298888891");
////        Assert.assertEquals(beneficiarioPinPesos.getId(),"1-5DP5AYJ");
////        servicioBeneficiario.eliminarBeneficiarioPinPesos(beneficiarioPinPesos.getId());
//
//
//    }


}
