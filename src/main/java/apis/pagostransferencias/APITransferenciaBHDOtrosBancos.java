package apis.pagostransferencias;

//import apis.ibp.dashboard.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//import static apis.ibp.dashboard.APISLogin.DEVICE_ID;
//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 16/05/2024 11:54 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APITransferenciaBHDOtrosBancos {

//    private Response response;
//    private APISLogin login;
//
//    public APITransferenciaBHDOtrosBancos(APISLogin login){
//        this.login = login;
//    }
//
//    private String urlTerceroBHD = HOST + "/bhdleon/api/v2/thirdpartybhdleon/acounts/transfer";
//
//    private void transferenciaTerceroBHDNuevoBeneficiario(String origenNumProduct, String origenAlias, String origenMoneda, String origenTipoSig, String destinoNumProduct, String destinoAlias, String destinoMoneda, String destinoTipoSig, String destinoName, String nombreBanco,  String descripcion, String email, int montoRD,int montoUSD, int montoEUR, int timpoEspera, String numConfirmacion){
//        if (login.isResponseSuccessful()){
//            Map<String, Object> encriptar = new HashMap<>();
//
//            encriptar.put("debitProductNumber", origenNumProduct);
//            encriptar.put("debitProductAlias", origenAlias);
//            encriptar.put("debitProductCurrency", origenMoneda);
//            encriptar.put("debitProductType", origenTipoSig);
//
//            encriptar.put("creditProductNumber",destinoNumProduct);
//            encriptar.put("creditProductAlias",destinoAlias);
//            encriptar.put("creditProductCurrency",destinoMoneda);
//            encriptar.put("creditProductType",destinoTipoSig);
//            encriptar.put("creditProductHolderName",destinoName);
//
//            encriptar.put("description",descripcion);
//            encriptar.put("email",email);
//            encriptar.put("beneficiaryId","undefined");
//
//            encriptar.put("bankName",nombreBanco);
//            encriptar.put("documentNumber",login.getDocumentNumber());
//            encriptar.put("amountRD",String.valueOf(montoRD) );
//            encriptar.put("amountUS",String.valueOf(montoUSD) );
//            encriptar.put("amountEU",String.valueOf(montoEUR) );
//            encriptar.put("confirmationNumber",numConfirmacion);
//            encriptar.put("waitTime",String.valueOf(timpoEspera));
//
//            encriptar.put("pushProvider","firebase");
//            encriptar.put("transactionType","OB");
//            encriptar.put("providerDeviceId","");
//
//            encriptar.put("transactionId",UUID.randomUUID());
//     //       encriptar.put("deviceId",DEVICE_ID); colocar de nuevo
//            encriptar.put("channel","IBP");
//            encriptar.put("platform","IBP");
//            encriptar.put("uuid",login.getUUID());
//
//            String dtEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar));
//            Map<String, Object> body = new HashMap<>();
//            body.put("data",dtEncriptada);
//            body.put("clientId",login.getClientId());
//            body.put("clientSecret", login.getClientSecret());
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
//                        .post(urlTerceroBHD);
//
//            System.out.println("Transferencia tercero BHD: " + response.getStatusCode());
//
//        }
//    }
//
//    private Map<String, Object>  mapaComun(String idBeneficiario,String documentNumber, String origenNum, String origenAlias, String origenMoneda,  String origenTipo, String destinoNumProduct, String destinoAlias, String destinoMoneda, String destinoTipo, String destinoName,  String montoRD,String montoUSD, String montoEUR, String correo, String descripcion, int espera, String confirmationNumber){
//        Map<String, Object> encriptar = new HashMap<>();
//        encriptar.put("creditProductAlias", destinoAlias);
//        encriptar.put("creditProductCurrency", destinoMoneda);
//        encriptar.put("creditProductHolderName", destinoName);
//        encriptar.put("creditProductNumber", destinoNumProduct);
//        encriptar.put("creditProductType", destinoTipo);
//        encriptar.put("debitProductAlias", origenAlias);
//        encriptar.put("debitProductCurrency",origenMoneda);
//        encriptar.put("debitProductNumber",origenNum);
//        encriptar.put("debitProductType",origenTipo);
//        encriptar.put("description",descripcion);
//        encriptar.put("email",correo);
//        encriptar.put("transactionType","OB");
//        if (!idBeneficiario.equals("") && !idBeneficiario.equalsIgnoreCase("NA")){
//            encriptar.put("beneficiaryId",idBeneficiario);
//        }
//        encriptar.put("bankName","");
//        encriptar.put("documentNumber",documentNumber);
//        encriptar.put("amountRD",montoRD);
//        encriptar.put("amountEU",montoEUR);
//        encriptar.put("amountUS",montoUSD);
//
//        encriptar.put("waitTime", String.valueOf(espera));
//        encriptar.put("providerDeviceId","");
//        encriptar.put("pushProvider","firebase");
//        encriptar.put("confirmationNumber",confirmationNumber);
//
//        encriptar.put("channel","IBP");
//        encriptar.put("platform","IBP");
//    //    encriptar.put("deviceId",DEVICE_ID); colocar de nuevo
//        encriptar.put("transactionId",UUID.randomUUID());
//        encriptar.put("uuid",login.getUUID());
//
//        return encriptar;
//    }
//
//    public void transferenciaLBTRBeneficiarioInscrito(String documentNumer,String origenNumber, String origenAlias, String origenMoneda, String origenTipoSigla, String productDestino, String montoRD, String montoUSD, String montoEUR, String correo, String descripcion, String numeroConfirmacion){
//        if (login.isResponseSuccessful()){
//
//            MsBeneficiariosNacionales benefic = new MsBeneficiariosNacionales(documentNumer).newQuery().selecOtrosBancos().buscar(productDestino);
//            Map<String, Object> encriptar = mapaComun(
//                    benefic.getId(),documentNumer,origenNumber,origenAlias,origenMoneda,origenTipoSigla,benefic.getNumProducto(), benefic.getAlias(), benefic.getMoneda(),benefic.getTipoCuenta(),benefic.getNombre(),montoRD,montoUSD,montoEUR,correo,descripcion,45,numeroConfirmacion);
//            encriptar.put("documentNumber",documentNumer);
//            encriptar.put("","");
//            encriptar.put("","");
//            encriptar.put("","");
//            encriptar.put("","");
//
//
//
//        }
//    }
//
////    public void transferenciaTerceroBHD(){
////        if (login.isResponseSuccessful()){
////
////            try {
////                Map<String, Object> service = new HashMap<>();
//////                if (!id.equals("") && !id.equalsIgnoreCase("NA")){
//////                    service.put("Id","1-5DPQI6N"); // id
//////                }
////                service.put("acceptPaymentCC","S");
////                service.put("affectedService","Pago de Facturas");  // servicio  - Compra de Recargas
////                service.put("currency","RD");
////                service.put("description","Pago de factura descripcion"); // descripcion
////                service.put("domiciliationCollectionPeriod","No. de Telefono");  // No. de Telefono
////                service.put("domiciliationReference","No. de Telefono");         // No. de Telefono
////                service.put("infoReference","No. de Telefono");                  // No. de Telefono
////                service.put("newCurrency","RD");
////                service.put("newcurrency","RD");
////                service.put("providerId","101001577");        // providerId
////                service.put("referenceNumber","8094291548");  // referenceNumber - referencia
////                service.put("serviceId","4071");              // serviceId
////                service.put("serviceProvider","CLARO");       // serviceProvider
////
////                Map<String, Object> encriptar = new HashMap<>();
////                encriptar.put("channel","IBP");
////                encriptar.put("platform","IBP");
////                encriptar.put("transactionId", UUID.randomUUID());
////                encriptar.put("deviceId", DEVICE_ID);
////                encriptar.put("service", service);
////
////                encriptar.put("uuid",login.getUUID());
////
////                String dataEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar));
////
////                Map<String, Object> body = new HashMap<>();
////                body.put("data",dataEncriptada);
////                body.put("clientId",login.getClientId());
////                body.put("clientSecret", login.getClientSecret());
////
////                response = RestAssured
////                        .given()
////                        .contentType(ContentType.JSON)
////                        .body(new Gson().toJson( body ))
////                        .header("clientId",login.getClientId())
////                        .header("clientSecret",login.getClientSecret())
////                        .header("x-keyvalue",login.getXKeyValue())
////                        .header("GeneratedUUID", login.getUUID())
////                        .header("jwt",login.getJwt())
////                        .log().all()
////                        .post(urlServiciosInscritosGet.replace("?",""));
////
////                System.out.println("AGREGAR ACTUALIZAR SERVICIO: " + getStatusCode());
////                //System.out.println(String.format("AGREGAR SERVICIO STATUS: %s: ", contratoType)  +response.getStatusCode());
////                //codigoDeEstado = response.getStatusCode();
////            }catch (Exception e){
////                //codigoDeEstado = response.getStatusCode();
////                //System.out.println(String.format( "ALGO SALIO MAL EN ACCEPT CONTRACT %s: ", contratoType)   + codigoDeEstado);
////            }
////
////        }
////    }
//
//
//    public static void main(String[] args) {
//
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("03100283732","1111");
//        //APITransferenciaBHDOtrosBancos terceroBHD = new APITransferenciaBHDOtrosBancos(login);
//
//        String numConfirmacion = "W" + UUID.randomUUID();
//
//        System.out.println("NumeroConfirmacion: " + numConfirmacion);
////        terceroBHD.transferenciaTerceroBHDNuevoBeneficiario(
////                "03965370021",
////                "SUPERCUENTA C/CKS PERS-EMP RD$",
////                "RD",
////                "CUA",
////                "01289910014",
////                "alias de prueba noche",
////                "RD",
////                "CUA",
////                "QUINTANA ABREU, JOSE MAYOBANEX",
////                "BHD",
////                "transferencia desde API",
////                "carlos_loyola@bhd.com.do",
////                5,
////                0,
////                0,
////                15,
////                numConfirmacion);
//
//        APISPagosTransferencias pyt = new APISPagosTransferencias(login);
//        pyt.transferenciaEntreCuentas(
//                "03965370021",
//                "SUPERCUENTA C/CKS PERS-EMP RD$",
//                "RD",
//                "CUA",
//                "03965370047",
//                "CTA.AHORROS PERSONALES US$",
//                "USD",
//                "CUA",
//                "3",
//                "descrip",
//                numConfirmacion);
//
//        pyt.getTransactionsQuery(numConfirmacion);
//    }
}
