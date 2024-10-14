package apis.pagostransferencias;

//import apis.ibp.dashboard.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import microservicios.Utilidad;

import java.util.*;

//import static apis.ibp.dashboard.APISLogin.DEVICE_ID;
//import static apis.ibp.dashboard.APISLogin.HOST;


/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 02/05/2024 8:57 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISPagosTransferencias {


//    private String urlTransactionsTypesGet = HOST + "/bhdleon/api/v1/personal/web/transactions-types?";
//    private String urlOriginProductsGet = HOST + "/bhdleon/api/v1/personal/web/origin-products?";
//    private String urlDestinationProductsGet = HOST + "/bhdleon/api/v1/personal/web/destination-products?";
//    private String urlDynamicParametersGet = HOST + "/bhdleon/api/v1/services/dynamic-parameters?";
//    private String urlPaymentsProvidersGet = HOST + "/bhdleon/api/v1/personal/web/payments-providers?";
//    private String urlFilterByPaymentGet = HOST + "/bhdleon/api/v1/personal/web/filter-by-payment?";
//    private String urlInvoiceByServiceGet = HOST + "/bhdleon/api/v1/personal/web/invoice-by-service?";
//    private String urlDisclaimersGet = HOST + "/bhdleon/api/v1/personal/web/disclaimers?";
//    private String urlTransactionsTimeGet = HOST + "/bhdleon/api/v1/services/transactions/time?";
//    private String urlTransactionsQueryGET = HOST + "/bhdleon/api/v1/personal/transactions-query?";
//    private String urlTransferenciaEntreCuentasPOST = HOST + "/bhdleon/api/v2/account/transactions";
//
//    private Response response;
//    private APISLogin login;
//    public APISPagosTransferencias(APISLogin login){
//        this.login = login;
//    }
//
//    public void transactionsTypes(){
//        Map<String, Object> bodyAEncript = new HashMap<>();
//        bodyAEncript.put("channel","IBP");
//        bodyAEncript.put("platform","IBP");
//     //   bodyAEncript.put("deviceId",DEVICE_ID);   colocar de nuevo
//        bodyAEncript.put("isRecurrence","N");
//        bodyAEncript.put("systemId","BANCASA");
//        bodyAEncript.put("transactionId", UUID.randomUUID());
//        bodyAEncript.put("uuid",login.getUUID());
//
//        if (login.isResponseSuccessful()){
//            response = null;
//            String dataEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(bodyAEncript));
//            System.out.println(dataEncriptada);
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId", login.getClientId())
//                    .header("clientSecret", login.getClientSecret())
//                    .log().all()
//                    .get(urlTransactionsTypesGet + dataEncriptada);
//
//            System.out.println("Transactions Types: " + response.getStatusCode());
//        }
//
//    }
//
//    private String encriptarBodyComboOrigenDestino(String transactionType, String transChannel, String debitCurrency, String debitProductNumber, String debitProductType){
//        Map<String, Object> bodyAEncript = new HashMap<>();
//        bodyAEncript.put("channel","IBP");
//        bodyAEncript.put("debitCurrency", debitCurrency);
//        bodyAEncript.put("debitProductNumber", debitProductNumber);
//        bodyAEncript.put("debitProductType", debitProductType);
//    //    bodyAEncript.put("deviceId",DEVICE_ID);   colocar de nuevo
//        bodyAEncript.put("isRecurrence","N");
//        bodyAEncript.put("platform","IBP");
//        bodyAEncript.put("transChannel",transChannel);
//        bodyAEncript.put("transactionId", UUID.randomUUID());
//        bodyAEncript.put("transactionType",transactionType);
//        bodyAEncript.put("uuid",login.getUUID());
//
//        return ServiciosDevops.getInstance().encriptarData(new Gson().toJson(bodyAEncript)).replace("=","");
//    }
//    /**
//     *
//     * @param transactionType TP, OB, TI, RS, PI
//     * @param transChannel TBHD, ACH, LBTR
//     * @param debitCurrency undefined
//     * @param debitProductNumber undefined
//     * @param debitProductType undefined
//     */
//    public void originProducts(String transactionType, String transChannel, String debitCurrency, String debitProductNumber, String debitProductType){
//        if (login.isResponseSuccessful()){
//            response = null;
//            String dataEncriptada = encriptarBodyComboOrigenDestino(transactionType, transChannel, debitCurrency,debitProductNumber,debitProductType); //ServiciosDevops.getInstance().encriptarData(new Gson().toJson(bodyAEncript)).replace("=","");
//            System.out.println(dataEncriptada);
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId", login.getClientId())
//                    .header("clientSecret", login.getClientSecret())
//                    .log().all()
//                    .get(urlOriginProductsGet + dataEncriptada);
//
//            System.out.println("Origin Products: " + response.getStatusCode());
//        }
//    }
//
//    public void destinationProducts(String transactionType, String transChannel, String debitCurrency, String debitProductNumber, String debitProductType){
//        if (login.isResponseSuccessful()){
//            response = null;
//            String dataEncriptada = encriptarBodyComboOrigenDestino(transactionType, transChannel, debitCurrency,debitProductNumber,debitProductType); //ServiciosDevops.getInstance().encriptarData(new Gson().toJson(bodyAEncript)).replace("=","");
//
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId", login.getClientId())
//                    .header("clientSecret", login.getClientSecret())
//                    .log().all()
//                    .get(urlDestinationProductsGet + dataEncriptada);
//
//            System.out.println("Destination Products: " + response.getStatusCode());
//        }
//    }
//
//    public void dynamicParameters(){
//        if (login.isResponseSuccessful()){
//            response = null;
//
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId", login.getClientId())
//                    .header("clientSecret", login.getClientSecret())
//                    .log().all()
//                    .get(urlDynamicParametersGet + login.getDataComunEncriptada());
//
//            System.out.println("Dynamic Parameters: " + response.getStatusCode());
//        }
//    }
//
//    public void paymentsProviders(){
//        if (login.isResponseSuccessful()){
//            response = null;
//
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId", login.getClientId())
//                    .header("clientSecret", login.getClientSecret())
//                    .log().all()
//                    .get(urlPaymentsProvidersGet + login.getDataComunEncriptada());
//
//            System.out.println("PaymentsProviders: " + response.getStatusCode());
//        }
//    }
//
//    public void filterByPayment(boolean acceptedCreditCard, String currency){
//        if (login.isResponseSuccessful()){
//            response = null;
//            Map<String, Object> bodyAEncriptar = new HashMap<>();
//            bodyAEncriptar.put("acceptedCreditCard",acceptedCreditCard);
//            bodyAEncriptar.put("channel","IBP");
//            bodyAEncriptar.put("currency",currency);
//      //      bodyAEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            bodyAEncriptar.put("platform","IBP");
//            bodyAEncriptar.put("transactionId",UUID.randomUUID());
//            bodyAEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId", login.getClientId())
//                    .header("clientSecret", login.getClientSecret())
//                    .log().all()
//                    .get(urlFilterByPaymentGet + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(bodyAEncriptar)).replace("=",""));
//
//            System.out.println("Filter By Payment: " + response.getStatusCode());
//        }
//    }
//
//    public void invoiceByService(String referenceNumber, String serviceId){
//        if (login.isResponseSuccessful()){
//            response = null;
//            Map<String, Object> bodyAEncriptar = new HashMap<>();
//            bodyAEncriptar.put("channel","IBP");
//       //     bodyAEncriptar.put("deviceId",DEVICE_ID);    colocar de nuevo
//            bodyAEncriptar.put("platform","IBP");
//            bodyAEncriptar.put("referenceNumber",referenceNumber);
//            bodyAEncriptar.put("serviceId",serviceId);
//            bodyAEncriptar.put("transactionId",UUID.randomUUID());
//            bodyAEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId", login.getClientId())
//                    .header("clientSecret", login.getClientSecret())
//                    .log().all()
//                    .get(urlInvoiceByServiceGet + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(bodyAEncriptar)).replace("=",""));
//
//            System.out.println("Invoice By Service: " + response.getStatusCode());
//        }
//    }
//
//    public void disclaimers(){
//        if (login.isResponseSuccessful()){
//            response = null;
//            Map<String, Object> bodyAEncriptar = new HashMap<>();
//            bodyAEncriptar.put("channel","IBP");
//            bodyAEncriptar.put("code","");
//    //        bodyAEncriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            bodyAEncriptar.put("platform","IBP");
//            bodyAEncriptar.put("transactionId",UUID.randomUUID());
//            bodyAEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId", login.getClientId())
//                    .header("clientSecret", login.getClientSecret())
//                    .log().all()
//                    .get(urlDisclaimersGet + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(bodyAEncriptar)).replace("=",""));
//
//            System.out.println("Disclaimers: " + response.getStatusCode());
//        }
//    }
//
//    public void transactionsTime(){
//        if (login.isResponseSuccessful()){
//            response = null;
//
//            response = RestAssured.given().urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId", login.getClientId())
//                    .header("clientSecret", login.getClientSecret())
//                    .log().all()
//                    .get(urlTransactionsTimeGet + login.getDataComunEncriptada());
//
//            System.out.println("Transactions Time: " + response.getStatusCode());
//        }
//    }
//
//
//    public void transferenciaEntreCuentas(String origenProductNumber, String origenProductAlias, String origenProductCurrency, String origenProductType,String destinoProductNumber, String destinoProductAlias, String destinoProductCurrency, String destinoProductType, String monto,String descripcion, String confirmationNumber){
//        if (login.isResponseSuccessful()){
//            response = null;
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//            encriptar.put("transactionId",UUID.randomUUID());
// //           encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//
//            encriptar.put("amount",monto);
//            encriptar.put("confirmationNumber",confirmationNumber);
//            encriptar.put("creditProductAlias",destinoProductAlias);
//            encriptar.put("creditProductCurrency",destinoProductCurrency);
//            encriptar.put("creditProductNumber",destinoProductNumber);
//            encriptar.put("creditProductType",destinoProductType);
//            encriptar.put("debitProductAlias",origenProductAlias);
//            encriptar.put("debitProductCurrency",origenProductCurrency);
//            encriptar.put("debitProductNumber",origenProductNumber);
//            encriptar.put("debitProductType",origenProductType);
//
//            encriptar.put("description",descripcion);
//            encriptar.put("foreignAmount",null);
//            encriptar.put("providerDeviceId","");
//            encriptar.put("pushProvider","firebase");
//            encriptar.put("transactionType","TP");
//            encriptar.put("waitTime",60);
//            encriptar.put("uuid",login.getUUID());
//
//            String encriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar));
//
//            Map<String, Object> body = new HashMap<>();
//            body.put("data",encriptada);
//            body.put("clientId",login.getClientId());
//            body.put("clientSecret",login.getClientSecret());
//
//            response = RestAssured.given()
//                    .contentType(ContentType.JSON)
//                    .body(new Gson().toJson(body))
//                    .header("clientId",login.getClientId())
//                    .header("clientSecret",login.getClientSecret())
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all().post(urlTransferenciaEntreCuentasPOST);
//
//            System.out.println("Transferncia entre cuentas: " +  response.getStatusCode());
//
//        }
//    }
//
//    public void getTransactionsQuery(String confirmationNumber){
//        if (login.isResponseSuccessful()){
//            Map<String, Object> encriptar = new HashMap<>();
//
////            Map<String, Object> filters = new HashMap<>();
////            filters.put("type","confirmationNumber");
////            filters.put("value",confirmationNumber);
//
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//     //       encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            encriptar.put("transactionId",UUID.randomUUID());
//            encriptar.put("rows","1");
//            encriptar.put("page","1");
//
//            Map<String, Object> type = new HashMap<>();
//            type.put("type","confirmationNumber");
//            type.put("value",confirmationNumber);
//
//            List<Map<String, Object>> filters = new ArrayList<>();
//            filters.add(type);
//
//            encriptar.put("filters",filters);
//            encriptar.put("uuid",login.getUUID());
//
//            try {
//                String encriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("?","");
//
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(urlTransactionsQueryGET + encriptada);
//                System.out.println("TRANSATION QUERY: " + response.getStatusCode());
//                System.out.println(response.getBody().asString());
//                JsonNode data = Utilidad.jsonNodeParse(response.getBody().asString());
//
//                JsonNode dt = Utilidad.jsonNodeParse(response);
//                System.out.println("JSON; " + dt.get("data"));
//
//                System.out.println(ServiciosDevops.getInstance().desencriptarData(login.getUUID(),dt.get("data").asText()));
//            }catch (Exception e){
//
//            }
//        }
//    }
//    public static void main(String[] args) {
////        LoginAPI login = LoginAPI.getInstance().iniciarSesion("00111435384","1111");
////        APISPagosTransferencias pyt = new APISPagosTransferencias(login);
////        //pyt.transactionsTypes();
////        //pyt.originProducts("OB","TBHD","","","");
////        //pyt.destinationProducts("TP","","RD","01289910022","CUA");
////        pyt.dynamicParameters();
////        pyt.paymentsProviders();
////        pyt.filterByPayment(true,"RD");
////        pyt.disclaimers();
////        pyt.invoiceByService("8298164488","4071");
////        pyt.transactionsTime();
////        login.logOut();
//
//        // Transferencia entre cuenta
//
//        APISLogin loginTransferencia = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        APISPagosTransferencias pytEntreMisProductos = new APISPagosTransferencias(loginTransferencia);
//        pytEntreMisProductos.transferenciaEntreCuentas(
//                "01289910057",
//                "NUEVA CUENTA MOVIL EN RD$",
//                "RD$",
//                "CUA",
//                "01289910014",
//                "CUENTA AHORROS STAFF RD$",
//                "RD$",
//                "CUA",
//                "9",
//                "Transferencia desde API",
//                "AUTOMATIZACION IBP-" + UUID.randomUUID());
//       // pytEntreMisProductos.getTransactionsQuery("AUTOMATIZACION IBP-04954729-3f10-4033-8229-cc3fbb718663");
//
//
////
////    LoginAPI login = LoginAPI.getInstance().iniciarSesion("03102674383","1111");
////    APISPagosTransferencias entreCuenta = new APISPagosTransferencias(login);
////    numeroConfirmacion = "IBP-AUTOMATIZACION-" + UUID.randomUUID();
////
////    entreCuenta.transferenciaEntreCuentas(
////            "00480830024",
////            "CUENTA CORRIENTE STAFF RD$",
////            "RD",
////            "CUC",
////            "00480830067",
////            "CTA.AHORROS PERSONAL RD$",
////            "RD",
////            "CUA",
////            "25",
////            "Transferencia desde API 13 Marzo 2024",
////            numeroConfirmacion);
////
////    entreCuenta.getTransactionsQuery(numeroConfirmacion);
//
//    }

    static String numeroConfirmacion = "";
}
