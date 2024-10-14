package apis.consultas;

//import apis.ibp.dashboard.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//import static apis.ibp.dashboard.APISLogin.*;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 29/04/2024 1:39 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class ApiDetalleProducto {

//    private APISLogin login;
//    private Response response;
//    private int codigoDeEstado;
//    private final String URL_API_PRODUCT_DETAILS_GET = HOST + "/bhdleon/api/v1/personal/web/product-details?";
//    private final String URL_API_PASSIVE_ACCOUNTS_DETAILS_GET = HOST + "/bhd/api/v2/personal/web/passive-saving-accounts-details?";
//    private final String URL_API_PASSIVE_CERTIFICATES_DETAILS_GET = HOST + "/bhdleon/api/v1/personal/web/passive-certificates-details?";
//    private final String URL_API_ASSET_CREDIT_DETAILS_GET = HOST +"/bhd/api/v2/personal/web/asset-credit-cards-details?";
//    private final String URL_API_ASSET_LOANS_DETAILS_GET = HOST +"/bhd/api/v2/personal/web/asset-loans-details?";
//    private String urlApiPassiveStockExchangeDetailsGET = HOST +"/bhd/api/v2/personal/web/passive-stock-exchange-details?"; // Detalla inversion puesto de bolsa.
//
//    /**
//     * SE ENVIA TAMBIEN EN SOLICITUDES Y RECLAMACIONES
//     */
//    private final String URL_API_REQUEST_COMPLAINTS_PRODUCTS_GET = HOST +"/bhdleon/api/v1/personal/web/request-complaints-products?";
//    private final String URL_API_RELATED_DATA_GET = HOST +"/bhdleon/api/v1/personal/web/related-data?";
//    private final String URL_API_TRANSIT_AMOUNT_GET = HOST +"/bhd/api/v1/personal/web/accounts/detail/transit-amount?";
//    private final String URL_API_PASSIVE_ACCOUNTS_TRANSACTION_HISTORY_GET = HOST + "/bhd/api/v2/personal/web/passive-accounts-transaction-history?";
//
//    /**
//     * SE ENVIA TAMBIEN EN SOLICITUDES Y RECLAMACIONES
//     */
//    private final String URL_API_REQUEST_CLAIM_COMBO_GET = HOST + "/bhdleon/api/v1/personal/web/request-claim-combo?";
//
//
//    public ApiDetalleProducto(APISLogin loginAPI){
//        login = loginAPI;
//    }
//
//
//    public void productDetails(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_PRODUCT_DETAILS_GET + login.getDataComunEncriptada());
//
//            System.out.println("RESPUESTA PRODUCT_DETAILS: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void passiveSavingAccountsDetails200(String numeroDeCuenta){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonEncriptar = new HashMap<>();
//            jsonEncriptar.put("channel","IBP");
//    //        jsonEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            jsonEncriptar.put("platform","IBP");
//            jsonEncriptar.put("productNumber",numeroDeCuenta);
//            jsonEncriptar.put("transactionId", UUID.randomUUID());
//            jsonEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_PASSIVE_ACCOUNTS_DETAILS_GET + encriptarDetalleProducto(numeroDeCuenta));
//                    //.get(URL_API_PASSIVE_ACCOUNTS_DETAILS_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonEncriptar)).replace("=",""));
//
//            System.out.println("RESPUESTA PASSIVE ACCOUNTS DETAILS: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void certificatePassiveSavingAccountsDetails(String numeroDeCuenta){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonEncriptar = new HashMap<>();
//            jsonEncriptar.put("channel","IBP");
//    //       jsonEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            jsonEncriptar.put("platform","IBP");
//            jsonEncriptar.put("productNumber",numeroDeCuenta);
//            jsonEncriptar.put("transactionId", UUID.randomUUID());
//            jsonEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_PASSIVE_CERTIFICATES_DETAILS_GET + encriptarDetalleProducto(numeroDeCuenta));
//
//            System.out.println("RESPUESTA PASSIVE_CERTIFICATES_DETAILS: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void assetCreditCardDetails(String numeroDeCuenta){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonEncriptar = new HashMap<>();
//            jsonEncriptar.put("channel","IBP");
//     //       jsonEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            jsonEncriptar.put("platform","IBP");
//            jsonEncriptar.put("productNumber",numeroDeCuenta);
//            jsonEncriptar.put("transactionId", UUID.randomUUID());
//            jsonEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_ASSET_CREDIT_DETAILS_GET + encriptarDetalleProducto(numeroDeCuenta));
//            //.get(URL_API_PASSIVE_ACCOUNTS_DETAILS_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonEncriptar)).replace("=",""));
//
//            System.out.println("RESPUESTA ASSET_CREDIT_DETAILS: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//
//
//    public void assetLoansDetails(String numeroDeCuenta){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonEncriptar = new HashMap<>();
//            jsonEncriptar.put("channel","IBP");
//     //       jsonEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            jsonEncriptar.put("platform","IBP");
//            jsonEncriptar.put("productNumber",numeroDeCuenta);
//            jsonEncriptar.put("transactionId", UUID.randomUUID());
//            jsonEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_ASSET_LOANS_DETAILS_GET + encriptarDetalleProducto(numeroDeCuenta));
//
//            System.out.println("RESPUESTA ASSET_LOANS_DETAILS: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//
//    /**
//     * custodyAccount en formato 62-(3392)-1467
//     * @param custodyAccount
//     * @param isPassport
//     */
//    public void getPassiveStockExchangeDetails(String custodyAccount, boolean isPassport){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonEncriptar = new HashMap<>();
//            jsonEncriptar.put("channel","IBP");
//            jsonEncriptar.put("custodyAccount",custodyAccount);
//            //jsonEncriptar.put("dealNumber",null);
//            jsonEncriptar.put("isPassport",isPassport);
//            //jsonEncriptar.put("passport",null);
//        //    jsonEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            jsonEncriptar.put("platform","IBP");
//            jsonEncriptar.put("transactionId", UUID.randomUUID());
//            jsonEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(urlApiPassiveStockExchangeDetailsGET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonEncriptar)).replace("=",""));
//
//            System.out.println("Detalle Puesto bolsa PassiveStockExchangeDetails: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//
//
//    public void requestComplaintsProducts(String tipoProductoSigla){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonEncriptar = new HashMap<>();
//            jsonEncriptar.put("channel","IBP");
//      //      jsonEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            jsonEncriptar.put("platform","IBP");
//            jsonEncriptar.put("productType",tipoProductoSigla);
//            jsonEncriptar.put("requestClaimType", "R");
//            jsonEncriptar.put("transactionId", UUID.randomUUID());
//            jsonEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_REQUEST_COMPLAINTS_PRODUCTS_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonEncriptar)).replace("=","") ); //encriptarDetalleProducto(tipoProductoSigla)
//
//            System.out.println("RESPUESTA REQUEST_COMPLAINTS_PRODUCTS: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void accountTransitAmount503(String accountNumber){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonEncriptar = new HashMap<>();
//            jsonEncriptar.put("channel","IBP");
//  //          jsonEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            jsonEncriptar.put("platform","IBP");
//            jsonEncriptar.put("accountNumber",accountNumber);
//            jsonEncriptar.put("transactionId", UUID.randomUUID());
//            jsonEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_TRANSIT_AMOUNT_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonEncriptar)).replace("=","") );
//
//            System.out.println("RESPUESTA TRANSIT_AMOUNT: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void accountsRelatedData(String productNumber, String productType){
//
//        if (login.isResponseSuccessful()){
//            response = null;
//            Map<String, Object> jsonEncriptar = new HashMap<>();
//            jsonEncriptar.put("channel","IBP");
//   //         jsonEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            jsonEncriptar.put("platform","IBP");
//            jsonEncriptar.put("transactionId", UUID.randomUUID());
//            jsonEncriptar.put("productNumber",productNumber);
//            jsonEncriptar.put("productType",productType);
//            jsonEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_RELATED_DATA_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonEncriptar)).replace("=","") ); //encriptarDetalleProducto(tipoProductoSigla)
//
//            System.out.println("RESPUESTA RELATED_DATA: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    private String encriptarMovimientosCuentasMovimientoTC(String productNumber, String productType, int page, int pageSize, int lastRecord, int numMeses){
//        Map<String, Object> jsonEncriptar = new HashMap<>();
//        jsonEncriptar.put("channel","IBP");
//    //    jsonEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//        jsonEncriptar.put("platform","IBP");
//        jsonEncriptar.put("transactionId", UUID.randomUUID());
//        jsonEncriptar.put("page",page);
//        jsonEncriptar.put("pageSize",pageSize);
//        jsonEncriptar.put("noMonths",numMeses);
//        jsonEncriptar.put("lastRecord",lastRecord);
//        jsonEncriptar.put("refresh","Y");
//        jsonEncriptar.put("claveInicio",null);
//        jsonEncriptar.put("claveFin",null);
//        jsonEncriptar.put("pagina",null);
//        jsonEncriptar.put("pantallaPag",null);
//        jsonEncriptar.put("productNumber",productNumber);
//        jsonEncriptar.put("productType",productType);
//        jsonEncriptar.put("uuid",login.getUUID());
//
//        return ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonEncriptar)).replace("=","");
//    }
//
//    public void accountPassiveTransactionHistory500(String productNumber, String productType, int page, int pageSize, int lasrRecord, int numMeses){
//        response = null;
//        if (login.isResponseSuccessful()){
//
////            Map<String, Object> jsonEncriptar = new HashMap<>();
////            jsonEncriptar.put("channel","IBP");
////            jsonEncriptar.put("deviceId",DEVICE_ID);
////            jsonEncriptar.put("platform","IBP");
////            jsonEncriptar.put("accountNumber",accountNumber);
////            jsonEncriptar.put("transactionId", UUID.randomUUID());
////            jsonEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    //.header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId", login.getClientId())
//                    .header("clientSecret", login.getClientSecret())
//                    .log().all()
//                    .get(URL_API_PASSIVE_ACCOUNTS_TRANSACTION_HISTORY_GET + encriptarMovimientosCuentasMovimientoTC(productNumber,productType,page,pageSize,lasrRecord,numMeses) );
//
//            System.out.println("RESPUESTA TRANSIT_AMOUNT: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//
//
//    public void tcRequestClaimCombo(){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonEncriptar = new HashMap<>();
//            jsonEncriptar.put("channel","IBP");
//            jsonEncriptar.put("platform","IBP");
//     //       jsonEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            jsonEncriptar.put("processType","request");
//            jsonEncriptar.put("transactionId", UUID.randomUUID());
//            jsonEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_REQUEST_CLAIM_COMBO_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonEncriptar)).replace("=","") );
//
//            System.out.println("RESPUESTA CLAIM_COMBO: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//
//
//    private String encriptarDetalleProducto(String productNumber){
//        Map<String, Object> jsonBodyEncriptar = new HashMap<>();
//        jsonBodyEncriptar.put("productNumber",productNumber);
//        jsonBodyEncriptar.put("transactionId",UUID.randomUUID());
//    //    jsonBodyEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//        jsonBodyEncriptar.put("channel","IBP");
//        jsonBodyEncriptar.put("platform","IBP");
//        jsonBodyEncriptar.put("uuid",login.getUUID());
//        return ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonBodyEncriptar)).replace("=","");
//    }
//
//
//
//    public static void main(String[] args) {
//        APISLogin login = getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        ApiDetalleProducto dt = new ApiDetalleProducto(login);
////
////        //dt.productDetails();
//        dt.passiveSavingAccountsDetails200("01289910057");
////        //dt.certificatePassiveSavingAccountsDetails("2018664");
////        //dt.assetCreditCardDetails("4076733111412174");
////        //dt.assetLoansDetails("01289910057");
////
////        //dt.requestComplaintsProducts("PRE");
////        //dt.accountTransitAmount503("01289910022");
////        dt.accountPassiveTransactionHistory500("01289910057","CUA",1,38,0,1);
////        dt.accountsRelatedData("01289910057","CUA");
////        dt.tcRequestClaimCombo();
////        login.logOut();
////        //dt.assetCreditCardDetails("4076733111412174");
//
//        APISLogin login2 = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00100965995","1111");
//        ApiDetalleProducto dp = new ApiDetalleProducto(login2);
//        dp.getPassiveStockExchangeDetails("62-(3392)-1467",false);
//
//    }


}
