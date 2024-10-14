package apis.consultas;

//import static apis.ibp.dashboard.APISLogin.DEVICE_ID;


import apis.ibp.APISLogin;
import apis.ibp.BaseRequest;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 26/04/2024 6:17 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APIDashboard extends BaseRequest {
    public APIDashboard(APISLogin login) {
        super(login);
    }

//    private APISLogin login;
//
//    private final String URL_API_CLIENT_INFO_GET = "https://api-sqa.bhdleon.com.do/bhd/api/v2.1/personal/web/client-info?";
//    private final String URLAPI_IMAGE_GET = HOST +"/bhdleon/api/v1/personal/web/image?";
//    private final String URL_API_DASHBOARD_ACCOUNT_GET = HOST + "/bhdleon/api/v1/personal/web/passive-accounts";
//    private final String URL_API_DASHBOARD_ASSET_CREDIT_CARD_GET = HOST +"/bhdleon/api/v1/personal/web/asset-credit-cards?";
//    private final String URL_API_DASHBOARD_PREPAID_CARDS = HOST +"/bhdleon/api/v1/personal/web/passive-prepaid-cards?";
//    private final String URL_API_DASHBOARD_PASSIVE_FINANCING_GET = HOST +"/bhdleon/api/v1/personal/web/passive-financing?";
//    private final String URL_API_DASHBOARD_PASSIVE_CERTIFICATES_GET = HOST +"/bhdleon/api/v1/personal/web/passive-certificates?";
//    private final String URL_API_DASHBOARD_ACCOUNTS_STATEMENTS_GET = HOST + "/bhdleon/api/v1/personal/web/accounts-statements?";
//    private final String URL_API_DASHBOARD_GRAPHICS_GET = HOST + "/bhdleon/api/v1/personal/web/dashboard-graphics?";
//    private final String URL_API_DASHBOARD_PASSIVE_STOCK_EXCHANGE_LIST_GET = HOST + "/bhd/api/v2/personal/web/passive-stock-exchange-list?"; // INVERSIONES PUESTO DE BOLSA
//    private final String URL_API_DASHBOARD_PRODUCT_DESTINATION_PAYMENT_GET = HOST + "/bhdleon/api/v1/personal/web/product-destination-payment?";
//    private final String URL_API_DASHBOARD_KEY_CARD_GET = HOST + "/bhdleon/api/v1/personal/web/key-card?";
//    private final String URL_API_DASHBOARD_IMAGE_GET = HOST + "/bhdleon/api/v1/personal/web/image?";
//    private final String URL_API_DASHBOARD_ASSET_LOANS_GET = HOST + "/bhdleon/api/v2/personal/web/asset-loans?";
//    private final String URL_API_DASHBOARD_PASSIVE_INVESTMENTS_GET = HOST +"/bhdleon/api/v1/personal/web/passive-investments?";
//    private final String URL_API_DASHBOARD_AFP_RETIREMENT_PAY_GET = HOST +"/bhdleon/api/v1/personal/web/afp-retirement-pay?";
//    private final String URL_API_DASHBOARD_SERVICES_RATES_GET = HOST + "/bhdleon/api/v1/personal/web/services-rates?";
//    private final String URL_API_DASHBOARD_SHORTCUTS_GET = HOST + "/bhdleon/api/v1/personal/web/dashboard-shortcuts?";
//    private String productsRefreshment = HOST +"/bhd/api/v1/personal/web/products-refreshment";
//    private final String URL_API_DASHBOARD_CALENDAR_GET = HOST + "/bhdleon/api/v1/personal/web/dashboard-calendar?";
//    private final String URL_API_DASHBOARD_NEW_FEATURE_GET = HOST + "/bhdleon/api/v1/personal/web/new-feature?";
//    private final String URL_API_DASHBOARD_CONTRACT_GET = HOST + "/bhdleon/api/v1/personal/web/contract?";
//    private final String URL_API_DASHBOARD_BUSINESS_EXECUTIVE_GET = HOST + "/bhdleon/api/v1/qmatic/business-executive?";
//    private final String URL_API_DASHBOARD_TIKETS_PROMOTIONS_GET = HOST + "/bhd/api/v1/personal/web/tickets-promotions?";
//    private final String URL_API_DASHBOARD_LOGOUT_POST = HOST + "/bhdleon/api/v1/personal/web/logout";
//    private final String URL_API_DASHBOARD_MARKETING_DECISION_POST = HOST + "/bhdleon/api/v1/personal/marketing/decision";
//    private final String urlActivateCredicardPOST = HOST + "/bhdleon/api/v1/personal/web/activate-creditcard";
//
//    private final String URL_API_DASHBOARD_PRODUCT_ALIAS_PUT = HOST + "/bhdleon/api/v1/personal/web/product-alias";
//    private final String URL_API_DASHBOARD_FEES_TAXES_GET = HOST + "/bhdleon/api/v1/personal/web/fees-taxes?"; // invok al consultar origin-multipayment - Pagos multiples
//    private final String URL_API_DASHBOARD_ORIGEN_MULTIPAYMENT_GET = HOST + "/bhdleon/api/v1/personal/web/origin-multipayment?";
//    private final String URL_API_DASHBOARD_DESTINATION_MULTIPAYMENT_GET = HOST + "/bhdleon/api/v1/personal/web/destination-multipayment?";
//
//    /**
//     * Param: type = AFP, uuidLogin;
//     */
//    private final String jsonEncriptarContratoDashboard = "{$type$: $%s$, $transactionId$: $"+ UUID.randomUUID() +"$," +
//            "$deviceId$: $"+DEVICE_ID+"$, $channel$: $IBP$, $platform$: $IBP$, $uuid$:$%s$}";
//
//
//    /**
//     *
//     * Param: CustomerID-cedula = 00111435384 <br>
//     * Param: SACustomerID-cedula = 00111435384 <br>
//     * Param: deviceId<br>
//     *
//     *
//     */
//    private static final String jsonMarketingDecision = "{ $params$: { $CustomerID$: $%s$, $SACustomerID$: $%s$, $ContainerName$: $TopOffers$, $Channel$: $Web$, $ChannelGroup$: $IBP$, \n" +
//            "$Direction$: $Inbound$, $ClientRequestType$: $$,$Placements$: $Carousel,Carousel,Carousel$, $CurrentPage$: $Account$, $PreviousPage$: $Login$,\n" +
//            "$Contexts$: [ { $Type$: $Intent$, $Value$: $Refi mortgage$, $Key$: $IntentName$ } ]\n" +
//            "},$transactionId$: $"+UUID.randomUUID()+"$, $deviceId$: $%s$, $channel$: $IBP$, $platform$: $IBP$, $uuid$:$%s$ }";
//
//
//    private Response response;
//    //private String dtComunEncriptada = "";
//    private int codigoDeEstado = 0;
//    private MSClientInfoV2 clientInfo;
//    private String documentNumber = "";
//
//    Map<String, Object> jsonEncriptarBussinessExectiveTiketsPromotions = new HashMap<>();
//    private String dataEncriptadaBussinExceTiketsPromotions = "";
//
//    public boolean isSuccessfulConnection(){
//        boolean resultado = false;
//        int codigoEstado = response.getStatusCode();
//        if (codigoEstado == 200 || codigoEstado == 204){
//            resultado = true;
//        }
//        return resultado;
//    }
//
//    public int getStatusCode(){
//        return response.getStatusCode();
//    }
//
//    public String getReporteAPI(){
//        return login.getReporteAPI();
//    }
//
//    public APIDashboard(APISLogin login){
//        this.login = login;
//    }
//
//
//    /**
//     * Verificar metodos
//     * @param usuario
//     * @param contrasena
//     * @return
//     */
////    public APIDashboard iniciarSesion(String usuario, String contrasena){
////        documentNumber = usuario;
////        login.iniciarSesion(usuario,contrasena);
////        clientInfo = new MSClientInfoV2(usuario).newQuery();
////        System.out.println("T24 CODE: "+clientInfo.getCustomerCodeT24());
////
////        jsonEncriptarBussinessExectiveTiketsPromotions.put("contactId",clientInfo.getContactId());
////        jsonEncriptarBussinessExectiveTiketsPromotions.put("transactionId",UUID.randomUUID());
////        jsonEncriptarBussinessExectiveTiketsPromotions.put("deviceId",DEVICE_ID);
////        jsonEncriptarBussinessExectiveTiketsPromotions.put("channel","IBP");
////        jsonEncriptarBussinessExectiveTiketsPromotions.put("platform","IBP");
////        jsonEncriptarBussinessExectiveTiketsPromotions.put("uuid",login.getUUID());
////
////        dtComunEncriptada = ServiciosDevops.getInstance().encriptarData(String.format(JSON_ENCRIPTAR_COMUN_DASHBOARD,DEVICE_ID,login.getUUID())).replace("=","");
////        return this;
////    }
//
//    public boolean loginIsSuccessful(){
//        return login.isResponseSuccessful();
//    }
//
//    public void clientInfo(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            try {
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(URL_API_CLIENT_INFO_GET+ login.getDataComunEncriptada());
//
//                System.out.println("NUEVA RESPUESTA CLIENT INFO: " +response.getStatusCode());
//                codigoDeEstado = response.getStatusCode();
//            }catch (Exception e){
//                codigoDeEstado = response.getStatusCode();
//                System.out.println("ALGO SALIO MAL EN CLIENT INFO: " + codigoDeEstado);
//            }
//
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//        //dtComunEncriptada = ServiciosDevops.getInstance().encriptarData(String.format(JSON_ENCRIPTAR_COMUN_DASHBOARD,DEVICE_ID,login.getUUID())).replace("=","");
//    }
//
//    public void apiImage(){
//        response = null;
//            try {
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(URLAPI_IMAGE_GET + login.getDataComunEncriptada());
//
//                login.setReporte(URLAPI_IMAGE_GET,response,"CONSULTAR IMAGEN","GET");
//                System.out.println("RESPUESTA IMAGE: " + response.getStatusCode());
//            }catch (Exception e){
//                login.setReportError(URL_API_DASHBOARD_ACCOUNT_GET,"CONSULTAR IMAGEN","GET", e);
//                System.out.println("ALGO SALIO MAL EN LA IMAGEN: " + codigoDeEstado);
//            }
//    }
//
//    public void apiDashboardAccounts(){
//        try {
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(URL_API_DASHBOARD_ACCOUNT_GET +"?"+ login.getDataComunEncriptada());
//
//                login.setReporte(URL_API_DASHBOARD_ACCOUNT_GET,response,"CONSULTAR CUENTAS","GET");
//                System.out.println("RESPUESTA CUENTAS: " + response.getStatusCode());
//                codigoDeEstado = login.getStatusCode();
//
//        }catch (Exception e){
//            login.setReportErrorEliminarMetodoSustituirPorSetReportError(URL_API_DASHBOARD_ACCOUNT_GET,response,"CONSULTAR CUENTAS","GET");
//        }
//    }
//
//    public void apiDashboardAssetCreditCard(){
//        try {   response = null;
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(URL_API_DASHBOARD_ASSET_CREDIT_CARD_GET + login.getDataComunEncriptada());
//
//                login.setReporte(URL_API_DASHBOARD_ASSET_CREDIT_CARD_GET,response,"CONSULTAR TARJETAS DE CREDITO","GET");
//                System.out.println("RESPUESTA TARJETA CREDITO: " + response.getStatusCode());
//                codigoDeEstado = response.getStatusCode();
//
//        }catch (Exception e){
//            login.setReportError(URL_API_DASHBOARD_ASSET_CREDIT_CARD_GET,"CONSULTAR TARJETAS DE CREDITO","GET",e);
//        }
//    }
//
//    public void apiDashboardTarjetasPrepago(){
//        try {
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(URL_API_DASHBOARD_PREPAID_CARDS + login.getDataComunEncriptada());
//
//                login.setReporte(URL_API_DASHBOARD_PREPAID_CARDS,response,"CONSULTAR TARJETAS PREPAGO","GET");
//                System.out.println("RESPUESTA TARJETA PREPAGO: " + response.getStatusCode());
//                codigoDeEstado = response.getStatusCode();
//        }catch (Exception e){
//            login.setReportErrorEliminarMetodoSustituirPorSetReportError(URL_API_DASHBOARD_PREPAID_CARDS,response,"CONSULTAR TARJETAS PREPAGO","GET");
//        }
//    }
//
//    public void apiDashboardPassiveCertificates(){
//        try {
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(URL_API_DASHBOARD_PASSIVE_CERTIFICATES_GET + login.getDataComunEncriptada());
//
//                login.setReporte(URL_API_DASHBOARD_PASSIVE_CERTIFICATES_GET,response,"CONSULTAR CERTIFICADOS","GET");
//                System.out.println("RESPUESTA PASSIVE CERTIFICATES: " + response.getStatusCode());
//                codigoDeEstado = response.getStatusCode();
//        }catch (Exception e){
//            login.setReportErrorEliminarMetodoSustituirPorSetReportError(URL_API_DASHBOARD_PASSIVE_CERTIFICATES_GET,response,"CONSULTAR CERTIFICADOS","GET");
//        }
//
//    }
//
//    public void apiDashboardPassiveFinancing(){
//        try {
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_PASSIVE_FINANCING_GET + login.getDataComunEncriptada());
//
//            login.setReporte(URL_API_DASHBOARD_PASSIVE_CERTIFICATES_GET,response,"CONSULTAR FINANCIAMIENTO PASIVO","GET");
//            System.out.println("RESPUESTA PASSIVE FINANCING: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }catch (Exception e){
//            login.setReportErrorEliminarMetodoSustituirPorSetReportError(URL_API_DASHBOARD_PASSIVE_CERTIFICATES_GET,response,"CONSULTAR FINANCIAMIENTO PASIVO","GET");
//        }
//    }
//
//    public void apiDashboardLoans(String customerCodeT24){
//
//        Map<String, Object> encriptar = login.getMapaComunUUID();
//        encriptar.put("customerCodeT24",customerCodeT24);
//        encriptar.put("refresh",0);
//
//        String dataEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("=","");
//        try {
//                response = RestAssured
//                        .given()
//                        .urlEncodingEnabled(true)
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .get(URL_API_DASHBOARD_ASSET_LOANS_GET + dataEncriptada);
//
//                login.setReporte(URL_API_DASHBOARD_ASSET_LOANS_GET, response,"CONSULTAR PRESTAMO DASHBOARD","GET");
//                System.out.println("Status code ASSET LOANS: " + response.getStatusCode());
//                codigoDeEstado = response.getStatusCode();
//
//        }catch (Exception e){
//            login.setReportErrorEliminarMetodoSustituirPorSetReportError(URL_API_DASHBOARD_ASSET_LOANS_GET, response,"CONSULTAR","GET");
//        }
//    }
//
//    public void apiDashboardPassiveInvestments(){
//        response = null;
//        try {
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_PASSIVE_INVESTMENTS_GET + login.getDataComunEncriptada());
//
//            login.setReporte(URL_API_DASHBOARD_PASSIVE_INVESTMENTS_GET, response,"CONSULTAR INVERSIONES PASIVAS","GET");
//            System.out.println("RESPUESTA DASHBOARD PASSIVE INVESTMENST: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }catch (Exception e){
//            login.setReportErrorEliminarMetodoSustituirPorSetReportError(URL_API_DASHBOARD_PASSIVE_INVESTMENTS_GET, response,"CONSULTAR","GET");
//        }
//    }
//
//    public void inversionesPuestoBolsa(boolean refresh, boolean isPassport){
//        response = null;
//        try {
//            Map<String, Object> jsonBodyEncriptar = new HashMap<>();
//            jsonBodyEncriptar.put("refresh",refresh);
//            jsonBodyEncriptar.put("isPassport",isPassport);
//            jsonBodyEncriptar.put("transactionId",UUID.randomUUID());
//            jsonBodyEncriptar.put("deviceId",DEVICE_ID);
//            jsonBodyEncriptar.put("platform","IBP");
//            jsonBodyEncriptar.put("channel","IBP");
//            jsonBodyEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_PASSIVE_STOCK_EXCHANGE_LIST_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonBodyEncriptar)).replace("=",""));
//
//            login.setReporte(URL_API_DASHBOARD_PASSIVE_STOCK_EXCHANGE_LIST_GET, response,"CONSULTAR INVERSIONES PUESTO DE BOLSA","GET");
//            System.out.println("RESPUESTA PASSIVE_STOCK_EXCHANGE_LIST: " + response.getStatusCode());
//        }catch (Exception e){
//            login.setReportError(URL_API_DASHBOARD_PASSIVE_STOCK_EXCHANGE_LIST_GET,"CONSULTAR INVERSIONES PUESTO DE BOLSA","GET", e);
//        }
//
//    }
//
//    public void apiDashboardServicesRates(){
//        response = null;
//        try {
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_SERVICES_RATES_GET + login.getDataComunEncriptada());
//
//            login.setReporte(URL_API_DASHBOARD_SERVICES_RATES_GET, response,"CONSULTAR DIVISAS","GET");
//            System.out.println("RESPUESTA DASHBOARD SERVICES RATES: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }catch (Exception e){
//            login.setReportErrorEliminarMetodoSustituirPorSetReportError(URL_API_DASHBOARD_SERVICES_RATES_GET, response,"CONSULTAR DIVISAS","GET");
//        }
//    }
//
//    public void apiDashboardAfpRetiremenPay(){
//        response = null;
//        try {
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_AFP_RETIREMENT_PAY_GET + login.getDataComunEncriptada());
//
//            login.setReporte(URL_API_DASHBOARD_AFP_RETIREMENT_PAY_GET, response,"CONSULTAR AFP","GET");
//            System.out.println("RESPUESTA DASHBOARD AFP RETIREMENT PAY: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }catch (Exception e){
//            login.setReportErrorEliminarMetodoSustituirPorSetReportError(URL_API_DASHBOARD_AFP_RETIREMENT_PAY_GET, response,"CONSULTAR AFP","GET");
//        }
//    }
//
//    public void apiDashboardShortCuts(){
//        response = null;
//        try {
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_SHORTCUTS_GET + login.getDataComunEncriptada());
//
//            login.setReporte(URL_API_DASHBOARD_SHORTCUTS_GET, response,"CONSULTAR ACCESOS RAPIDOS","GET");
//            System.out.println("RESPUESTA DASHBOARD SHORTCUTS: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }catch (Exception e){
//            login.setReportErrorEliminarMetodoSustituirPorSetReportError(URL_API_DASHBOARD_SHORTCUTS_GET, response,"CONSULTAR ACCESOS RAPIDOS","GET");
//        }
//    }
//
//
//
//
//
//
//    // 00101514420
//    // descargar reporte de estado de cuenta = /bhdleon/api/v1/personal/web/account-statements-report
//    public void dashboardAccountsStatements(String productNumber, String productType,String altaCenterOpcional, String contractNumberOpcional){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//
//            Map<String, Object> jsonBodyEncriptar = new HashMap<>();
//            jsonBodyEncriptar.put("productNumber",productNumber);
//            jsonBodyEncriptar.put("productType",productType);
//            jsonBodyEncriptar.put("altaCenter",altaCenterOpcional);
//            jsonBodyEncriptar.put("contractNumber",contractNumberOpcional);
//            jsonBodyEncriptar.put("transactionId",UUID.randomUUID());
//            jsonBodyEncriptar.put("deviceId",DEVICE_ID);
//            jsonBodyEncriptar.put("platform","IBP");
//            jsonBodyEncriptar.put("channel","IBP");
//            jsonBodyEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_ACCOUNTS_STATEMENTS_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonBodyEncriptar)).replace("=",""));
//
//            System.out.println("RESPUESTA ACCOUNTS_STATEMENTS: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    /**
//     *
//     * @param productNumber
//     * @param fechaInicio formato dia/mes/año  25/11/2023
//     * @param fechaFinal formato dia/mes/año  25/11/2023
//     */
//    public void dashboardGraphics(String productNumber, String fechaInicio,String fechaFinal){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonBodyEncriptar = new HashMap<>();
//            jsonBodyEncriptar.put("productNumber",productNumber);
//            jsonBodyEncriptar.put("startDate",fechaInicio);
//            jsonBodyEncriptar.put("endDate",fechaFinal);
//            jsonBodyEncriptar.put("transactionId",UUID.randomUUID());
//            jsonBodyEncriptar.put("deviceId",DEVICE_ID);
//            jsonBodyEncriptar.put("platform","IBP");
//            jsonBodyEncriptar.put("channel","IBP");
//            jsonBodyEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_GRAPHICS_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonBodyEncriptar)).replace("=",""));
//
//            System.out.println("RESPUESTA DASHBOARD_GRAPHICS: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//
//
//
//    /**
//     * Al presionar el boton pagar - Prestamo
//     * @param moneda RD, US
//     * @param tipoProductoSigla PRE, TAC
//     */
//    public void productDestinationPayment(String moneda, String tipoProductoSigla){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonBodyEncriptar = new HashMap<>();
//            jsonBodyEncriptar.put("currency",moneda);
//            jsonBodyEncriptar.put("type",tipoProductoSigla);
//            jsonBodyEncriptar.put("transactionId",UUID.randomUUID());
//            jsonBodyEncriptar.put("deviceId",DEVICE_ID);
//            jsonBodyEncriptar.put("platform","IBP");
//            jsonBodyEncriptar.put("channel","IBP");
//            jsonBodyEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_PRODUCT_DESTINATION_PAYMENT_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonBodyEncriptar)).replace("=",""));
//
//            System.out.println("RESPUESTA PRODUCT_DESTINATION_PAYMENT: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void keyCard(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_KEY_CARD_GET + login.getDataComunEncriptada());
//
//            System.out.println("RESPUESTA DASHBOARD_KEY_CARD: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void dashboardImage(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_IMAGE_GET + login.getDataComunEncriptada());
//
//            System.out.println("RESPUESTA DASHBOARD_IMAGE: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//
//    public void apiDashboardProductsRefreshment(String documentNumber, String documentType, String contactId){
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = login.getMapaComunUUID();
//            encriptar.put("documentNumber",documentNumber);
//            encriptar.put("documentType",documentType);
//            encriptar.put("contactId",contactId);
//
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(productsRefreshment +"?"+ ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)).replace("=",""));
//
//            System.out.println("products-refreshment: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void apiDashboardCalendar(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_CALENDAR_GET + login.getDataComunEncriptada());
//
//            System.out.println("RESPUESTA DASHBOARD CALENDAR: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void apiDashboardNewFeature(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_NEW_FEATURE_GET + login.getDataComunEncriptada());
//
//            System.out.println("RESPUESTA DASHBOARD NEW FEATURE: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void feetTaxes(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_FEES_TAXES_GET + login.getDataComunEncriptada());
//
//            System.out.println("RESPUESTA DASHBOARD FEES_TAXES: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    /**
//     * Monedas: RD, US
//     * @param moneda
//     */
//    public void origenMultipayment(String moneda){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonBodyAEncriptar = new HashMap<>();
//            jsonBodyAEncriptar.put("channel","IBP");
//            jsonBodyAEncriptar.put("currency",moneda);
//            jsonBodyAEncriptar.put("deviceId",DEVICE_ID);
//            jsonBodyAEncriptar.put("platform","IBP");
//            jsonBodyAEncriptar.put("transactionId",UUID.randomUUID());
//            jsonBodyAEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured.given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_ORIGEN_MULTIPAYMENT_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonBodyAEncriptar)).replace("=",""));
//
//            System.out.println("RESPUESTA DASHBOARD ORIGEN_MULTIPAYMENT: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    /**
//     *
//     * @param moneda RD, US
//     * @param productNumber
//     * @param productType CUA, CUC, TAC
//     */
//    public void destinationMultipayment(String moneda, String productNumber, String productType){
//        response = null;
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> jsonBodyAEncriptar = new HashMap<>();
//            jsonBodyAEncriptar.put("channel","IBP");
//            jsonBodyAEncriptar.put("currency",moneda);
//            jsonBodyAEncriptar.put("productType",productType);
//            jsonBodyAEncriptar.put("productNumber",productNumber);
//            jsonBodyAEncriptar.put("deviceId",DEVICE_ID);
//            jsonBodyAEncriptar.put("platform","IBP");
//            jsonBodyAEncriptar.put("transactionId",UUID.randomUUID());
//            jsonBodyAEncriptar.put("uuid",login.getUUID());
//
//            response = RestAssured.given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_DESTINATION_MULTIPAYMENT_GET + ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonBodyAEncriptar)).replace("=",""));
//
//            System.out.println("RESPUESTA DASHBOARD DESTINATION_MULTIPAYMENT: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void apiDashboardAFP(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            String dataEncriptada = ServiciosDevops.getInstance().encriptarData(String.format(jsonEncriptarContratoDashboard.replace("$","\""),"AFP",login.getUUID())).replace("=","");
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_CONTRACT_GET + dataEncriptada);
//
//            System.out.println("RESPUESTA DASHBOARD CONTRATO AFP: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void apiDashboardContratoAPP(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            String dataEncriptada = ServiciosDevops.getInstance().encriptarData(String.format(jsonEncriptarContratoDashboard.replace("$","\""),"APP",login.getUUID())).replace("=","");
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_CONTRACT_GET + dataEncriptada);
//
//            System.out.println("RESPUESTA DASHBOARD CONTRATO APP: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void apiContratoAPP(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            String dataEncriptada = ServiciosDevops.getInstance().encriptarData(String.format(jsonEncriptarContratoDashboard.replace("$","\""),"APP",login.getUUID())).replace("=","");
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_CONTRACT_GET + dataEncriptada);
//
//            System.out.println("RESPUESTA DASHBOARD CONTRATO APP: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void aceptarContratoAFP(String contratoType,String keyCard, String versionContrato){
//
//        if (login.isResponseSuccessful()){
//            response = null;
//            try {
//                Map<String, Object> bodyAEncriptar = new HashMap<>();
//                bodyAEncriptar.put("channel","IBP");
//                bodyAEncriptar.put("platform","IBP");
//                bodyAEncriptar.put("transactionId",UUID.randomUUID());
//                bodyAEncriptar.put("deviceId",DEVICE_ID);
//                bodyAEncriptar.put("type",contratoType);
//                bodyAEncriptar.put("keyCard",keyCard);
//                bodyAEncriptar.put("version",versionContrato);
//                bodyAEncriptar.put("uuid",login.getUUID());
//
//                String dataEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(bodyAEncriptar));
//                //String dtEncritadaMarketing = ServiciosDevops.getInstance().encriptarData(String.format( jsonMarketingDecision.replace("$","\""),documentNumber,documentNumber,DEVICE_ID,login.getUUID() ) );
//
//                Map<String, Object> body = new HashMap<>();
//                body.put("data",dataEncriptada);
//                body.put("clientId",login.getClientId());
//                body.put("clientSecret", login.getClientSecret());
//
//                response = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body(new Gson().toJson( body ))
//                        .header("clientId",login.getClientId())
//                        .header("clientSecret",login.getClientSecret())
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .post(URL_API_DASHBOARD_CONTRACT_GET.replace("?",""));
//
//                System.out.println(String.format("NUEVA RESPUESTA ACCEPT CONTRACT %s: ", contratoType)  +response.getStatusCode());
//                codigoDeEstado = response.getStatusCode();
//            }catch (Exception e){
//                codigoDeEstado = response.getStatusCode();
//                System.out.println(String.format( "ALGO SALIO MAL EN ACCEPT CONTRACT %s: ", contratoType)   + codigoDeEstado);
//            }
//
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//
//    /**
//     *
//     *                 "NORMA ALMONTE",
//     *                 "EL SEIBO",
//     *                 "TAC",
//     *                 "4641330377379134",
//     *                 "PENDIENTE ACTIVACION",
//     *                 "RD$",
//     *                 "000002093782",
//     *                 "1/8/2024",
//     *                 "1111",
//     *                 "12"
//     *
//     * @param customerName
//     * @param productName
//     * @param typeProduct
//     * @param productNumber
//     * @param productStatus
//     * @param currency
//     * @param contractNumber
//     * @param expirationDate
//     * @param keyCard
//     * @param positionKey
//     */
//    public void activateCredicardReturn500(String customerName, String productName, String typeProduct, String productNumber, String productStatus, String currency, String contractNumber, String expirationDate, String keyCard, String positionKey){
//        if (login.isResponseSuccessful()){
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//            encriptar.put("transactionId",UUID.randomUUID());
//            encriptar.put("deviceId",DEVICE_ID);
//
//            encriptar.put("contractNumber",contractNumber);
//            encriptar.put("currency",currency);
//            encriptar.put("customerName",customerName);
//            encriptar.put("expirationDate",expirationDate);
//            encriptar.put("keyCard",keyCard);
//            encriptar.put("positionKey",positionKey);
//            encriptar.put("productName",productName);
//            encriptar.put("productNumber",productNumber);
//            encriptar.put("productStatus",productStatus);
//            encriptar.put("typeProduct",typeProduct);
//            encriptar.put("uuid",login.getUUID());
//            try {
//                response = null;
//                String dataEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar));
//
//                Map<String, Object> body = new HashMap<>();
//                body.put("data",dataEncriptada);
//                body.put("clientSecret",login.getClientSecret());
//                body.put("clientId",login.getClientId());
//                response = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body(new Gson().toJson(body))
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice()).log().all()
//                        .post(urlActivateCredicardPOST);
//
//                System.out.println("ACTIVAR TC: " + response.getStatusCode());
//            }catch (Exception e){
//
//            }
//        }
//    }
//
//    public void marketingDecision(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            try {
//                String dtEncritadaMarketing = ServiciosDevops.getInstance().encriptarData(String.format( jsonMarketingDecision.replace("$","\""),documentNumber,documentNumber,DEVICE_ID,login.getUUID() ) );
//                response = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body("{ \"data\": \""+dtEncritadaMarketing+"\" }")
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .post(URL_API_DASHBOARD_MARKETING_DECISION_POST);
//
//                System.out.println("NUEVA RESPUESTA MARKETINGDECISION: " +response.getStatusCode());
//                codigoDeEstado = response.getStatusCode();
//            }catch (Exception e){
//                codigoDeEstado = response.getStatusCode();
//                System.out.println("ALGO SALIO MAL EN MARKETINGDECISION: " + codigoDeEstado);
//            }
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    /**
//     * Permite editar el alias del producto.
//     * @param alias
//     * @param numProducto
//     * @param tipoProductoSigla CUA, CUA, PRE, CEF, TAC
//     * @param nombreProducto
//     */
//    public void productAlias(String alias, String numProducto,String tipoProductoSigla, String nombreProducto){
//        response = null;
//        if (login.isResponseSuccessful()){
//            try {
//
//                Map<String, Object> jsonBodyEncriptar = new HashMap<>();
//                jsonBodyEncriptar.put("alias",alias);
//                jsonBodyEncriptar.put("channel","IBP");
//                jsonBodyEncriptar.put("deviceId",DEVICE_ID);
//                jsonBodyEncriptar.put("display",true);
//                jsonBodyEncriptar.put("platform","IBP");
//                jsonBodyEncriptar.put("productName",nombreProducto);
//                jsonBodyEncriptar.put("productNumber",numProducto);
//                jsonBodyEncriptar.put("productType",tipoProductoSigla);
//                jsonBodyEncriptar.put("transactionId",UUID.randomUUID());
//                jsonBodyEncriptar.put("uuid",login.getUUID());
//
//                String enciptacion = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonBodyEncriptar));
//
//
//                //String dtEncritadaMarketing = ServiciosDevops.getInstance().encriptarData(String.format( jsonMarketingDecision.replace("$","\""),documentNumber,documentNumber,DEVICE_ID,login.getUUID() ) );
//
//                response = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body("{ \"data\": \""+enciptacion+"\" }")
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .put(URL_API_DASHBOARD_PRODUCT_ALIAS_PUT);
//
//                System.out.println("NUEVA RESPUESTA PRODUCT ALIAS: " +response.getStatusCode());
//                codigoDeEstado = response.getStatusCode();
//            }catch (Exception e){
//                codigoDeEstado = response.getStatusCode();
//                System.out.println("ALGO SALIO MAL EN PRODUCT ALIAS: " + codigoDeEstado);
//            }
//
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void businessExecutive(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            if (dataEncriptadaBussinExceTiketsPromotions.equals("")){
//                dataEncriptadaBussinExceTiketsPromotions = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonEncriptarBussinessExectiveTiketsPromotions));
//            }
//            //String dataEncriptada = ServiciosDevops.getInstance().encriptarData(String.format(jsonEncriptarContratoDashboard.replace("$","\""),"APP",login.getUUID())).replace("=","");
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_BUSINESS_EXECUTIVE_GET + dataEncriptadaBussinExceTiketsPromotions);
//
//            System.out.println("RESPUESTA DASHBOARD BUSINESS EXECUTIVE_GET: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void tiketsPromotions(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            if (dataEncriptadaBussinExceTiketsPromotions.equals("")){
//                dataEncriptadaBussinExceTiketsPromotions = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(jsonEncriptarBussinessExectiveTiketsPromotions));
//            }
//            response = RestAssured
//                    .given()
//                    .urlEncodingEnabled(true)
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all()
//                    .get(URL_API_DASHBOARD_TIKETS_PROMOTIONS_GET + dataEncriptadaBussinExceTiketsPromotions);
//
//            System.out.println("RESPUESTA DASHBOARD TIKETS_PROMOTIONS_GET: " + response.getStatusCode());
//            codigoDeEstado = response.getStatusCode();
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//    }
//
//    public void logOut(){
//        response = null;
//        if (login.isResponseSuccessful()){
//            try {
//
//                response = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body("{ \"data\": \""+login.getDataComunEncriptada()+"\" }")
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .post(URL_API_DASHBOARD_LOGOUT_POST);
//
//                System.out.println("NUEVA RESPUESTA CERRAR SESION: " +response.getStatusCode());
//                codigoDeEstado = response.getStatusCode();
//            }catch (Exception e){
//                codigoDeEstado = response.getStatusCode();
//                System.out.println("ALGO SALIO MAL EN CERRAR SESION: " + codigoDeEstado);
//            }
//
//        }else {
//            codigoDeEstado = login.getStatusCode();
//        }
//        //dtComunEncriptada = ServiciosDevops.getInstance().encriptarData(String.format(JSON_ENCRIPTAR_COMUN_DASHBOARD,DEVICE_ID,login.getUUID())).replace("=","");
//    }
//
//
//    public static void main(String[] args) {
//
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        APIDashboard dashboard = new APIDashboard(login);
//        dashboard.apiDashboardProductsRefreshment("00111435384","2","CON-376961");
//
////        System.out.println(jsonMarketingDecision);
////        APIDashboard consultarAPIS = APIDashboard.getInstance();
////        //consultarAPIS.iniciarSesion("22301391524","1111");
////        //consultarAPIS.iniciarSesion("00116188293","1111");//.clientInfo(); 00116188293 03102674383
////        //consultarAPIS.iniciarSesion("00111435384","1111");
////        //consultarAPIS.aceptarContratoAFP("AFP","1111","59");
////
////        consultarAPIS.iniciarSesion("04700012380","1111");
////        consultarAPIS.aceptarContratoAFP("ACH","1111","41");
////        consultarAPIS.aceptarContratoAFP("INT","1111","70");
//        //consultarAPIS.apiImage(); // DONE
//        //consultarAPIS.apiDashboardAccounts(); // DONE
//        //consultarAPIS.apiDashboardAssetCreditCard(); // DONE
//        //System.out.println(consultarAPIS.isSuccessfulConnection()); // DONE
//        //System.out.println(consultarAPIS.getStatusCode()); // DONE
//        //consultarAPIS.apiDashboardTarjetasPrepago();
//        //consultarAPIS.apiDashboardPassiveFinancing();
////        consultarAPIS.apiDashboardPassiveCertificates();
////        System.out.println(consultarAPIS.isSuccessfulConnection());
////        //consultarAPIS.apiDashboardConfiguracion();
////        //consultarAPIS.apiDashboardPassiveInvestments();
////        //consultarAPIS.apiDashboardAfpRetiremenPay();
////        consultarAPIS.apiDashboardServicesRates();
////        consultarAPIS.apiDashboardShortCuts();
////        consultarAPIS.apiDashboardCalendar();
////        consultarAPIS.apiDashboardNewFeature();
//        //consultarAPIS.apiDashboardAFP();
//        //consultarAPIS.apiDashboardContratoAPP();
//        //consultarAPIS.marketingDecision();
//        //consultarAPIS.businessExecutive();
//        //consultarAPIS.apiDashboardLoans();
//        //consultarAPIS.tiketsPromotions();
//        //consultarAPIS.productAlias("Alias editada desde Automatizacion API","01289910057","CUA","");
//        //consultarAPIS.feetTaxes();
//        //consultarAPIS.origenMultipayment("RD");
//        //consultarAPIS.destinationMultipayment("RD","01289910057","CUC");
//        //consultarAPIS.accountsStatements("01289910049","CUA","","");
//        //consultarAPIS.graphics("01289910057","26/04/2023","26/04/2024");
////        consultarAPIS.passiveStockExchangeList(false,false);
////        //consultarAPIS.productDestinationPayment("RD","US");
////        //consultarAPIS.keyCard();
////        consultarAPIS.image();
//
//        //LoginAPI loginActivacionTC = LoginAPI.getInstance().iniciarSesion("00100965995","1111");
////        APIDashboard dashboard = new APIDashboard();
////        dashboard.iniciarSesion("00100965995","1111");
////        dashboard.apiDashboardAccounts();
//
////        dashboard.activateCredicardReturn500("NORMA ALMONTE",
////                "EL SEIBO",
////                "TAC",
////                "4641330377379134",
////                "PENDIENTE ACTIVACION",
////                "RD$",
////                "000002093782",
////                "1/8/2024",
////                "1111",
////                "12");
//        //consultarAPIS.logOut();
//
//    }



}
