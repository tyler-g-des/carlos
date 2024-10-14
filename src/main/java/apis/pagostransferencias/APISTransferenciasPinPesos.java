package apis.pagostransferencias;

//import apis.ibp.dashboard.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import microservicios.MSClientInfoV2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//import static apis.ibp.dashboard.APISLogin.DEVICE_ID;
//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 17/05/2024 8:54 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APISTransferenciasPinPesos {

//    private Response response;
//    private APISLogin login;
//
//    public APISTransferenciasPinPesos(APISLogin login){
//        this.login = login;
//    }
//
//    private String urlTransferencia = HOST + "/bhdleon/api/v2/thirdparty/transfer/pinpesos";
//
//    public void transferenciaPinPesos(String origenNumProduct, String origenAlias, String origenMoneda, String origenTipoSig, String destinoNumProduct, String destinoMoneda, String destinoTipoSig, String monto, String descripcion, String numConformacion){
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("debitProductNumber",origenNumProduct);
//            encriptar.put("debitProductAlias",origenAlias);
//            encriptar.put("debitProductCurrency",origenMoneda);
//            encriptar.put("debitProductType",origenTipoSig);
//            encriptar.put("creditDocumentNumber","");
//            encriptar.put("creditProductHolderName","");
//            encriptar.put("creditProductCurrency",destinoMoneda);
//            encriptar.put("creditProductNumber",destinoNumProduct);
//            encriptar.put("creditDocumentType","");
//            encriptar.put("creditProductType","PI");
//            encriptar.put("description",descripcion);
//
//            encriptar.put("fee","30");
//            encriptar.put("gender","M");
//            encriptar.put("nationality","DD");
//            encriptar.put("providerDeviceId","");
//            encriptar.put("pushProvider","firebase");
//            encriptar.put("tax","0.15");
//            encriptar.put("total","130.15");
//            encriptar.put("waitTime","15");
//            encriptar.put("confirmationNumber",numConformacion);
//            encriptar.put("amount",monto);
//            encriptar.put("transactionType","OB");
//
//      //      encriptar.put("deviceId",DEVICE_ID);   colocar de nuevo
//            encriptar.put("transactionId", UUID.randomUUID());
//            encriptar.put("channel","IBP");
//            //encriptar.put("platform","IBP");
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
//                    .urlEncodingEnabled(false)
//                    .body(new Gson().toJson(body))
//                    .header("clientId",login.getClientId())
//                    .header("clientSecret",login.getClientSecret())
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .log().all().post(urlTransferencia);
//
//            System.out.println("TRANSFERNCIA PIN PESOS: " +  response.getStatusCode());
//
//        }
//    }
//
//    public static void main(String[] args) {
//
//        MSClientInfoV2 clintInfo = new MSClientInfoV2("03200247413").newQuery();
//
//        APISLogin consentimiento = APISLogin.getInstance().iniciarSesionDispositivoSeguro("03200247413","1111");
//
//        //consentimiento.updateConsent(clintInfo.getContactId(),true);
//
//
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("03102674383","1111");
//        APISTransferenciasPinPesos pinP = new APISTransferenciasPinPesos(login);
//        String numConfirmacion = "W" + UUID.randomUUID();
//        System.out.println(numConfirmacion);
//
//        pinP.transferenciaPinPesos(
//                "00480830024",
//                "CUENTA CORRIENTE STAFF RD$",
//                "RD",
//                "CUC",
//                "8093333333",
//                "RD",
//                "PI",
//                "200",
//                "Transf desde API",
//                "");
//        APISPagosTransferencias pyt = new APISPagosTransferencias(login);
//        pyt.getTransactionsQuery(numConfirmacion);
//    }



}
