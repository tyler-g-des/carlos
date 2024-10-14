package microservicios.dashboard;

import http.BaseRequestHTTP;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/06/2024 10:17 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MicroServiciosDashboard {

    Map <String, Object> headers = new HashMap<>();
    private Map<String, Object > parametros;// = new HashMap<>();

    public MicroServiciosDashboard(){
        headers.put("Accept","*/*");
        headers.put("Connection","keep-alive");
    }

    private void setParametros(String documentNumber){
        parametros = new HashMap<>();
        parametros.put("documentNumber",documentNumber);
        parametros.put("transactionId", UUID.randomUUID());
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public Response getCuentas(String documentNumber){
        BaseRequestHTTP requestHTTP = new BaseRequestHTTP();

        Map<String, Object > parametros = new HashMap<>();
        parametros.put("documentNumber",documentNumber);
        parametros.put("transactionId", UUID.randomUUID());

//        Map <String, Object> headers = new HashMap<>();
//        headers.put("Accept","*/*");
//        headers.put("Connection","keep-alive");

        try {
           return  requestHTTP.httGET( parametros, headers,"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/dashboard/accounts");
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Response getTarjetasCredito(String documentNumber){
        BaseRequestHTTP requestHTTP = new BaseRequestHTTP();

        Map<String, Object > parametros = new HashMap<>();
        parametros.put("documentNumber",documentNumber);
        parametros.put("transactionId", UUID.randomUUID());

//        Map <String, Object> headers = new HashMap<>();
//        headers.put("Accept","*/*");
//        headers.put("Connection","keep-alive");

        try {
            return  requestHTTP.httGET( parametros, headers,"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/dashboard/credit-cards");
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }


    public Response getCertificados(String documentNumber){
        BaseRequestHTTP requestHTTP = new BaseRequestHTTP();

        Map<String, Object > parametros = new HashMap<>();
        parametros.put("documentNumber",documentNumber);
        parametros.put("transactionId", UUID.randomUUID());

//        Map <String, Object> headers = new HashMap<>();
//        headers.put("Accept","*/*");
//        headers.put("Connection","keep-alive");

        try {
            return  requestHTTP.httGET( parametros, headers,"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/dashboard/certificates");
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Response getPrestamos(String documentNumber){
        BaseRequestHTTP requestHTTP = new BaseRequestHTTP();

        setParametros(documentNumber);

//        Map<String, Object > parametros = new HashMap<>();
//        parametros.put("documentNumber",documentNumber);
//        parametros.put("transactionId", UUID.randomUUID());
//        Map <String, Object> headers = new HashMap<>();
//        headers.put("Accept","*/*");
//        headers.put("Connection","keep-alive");

        try {
            return  requestHTTP.httGET( getParametros(), headers,"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/dashboard/loans");
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Response getTarjetasPrepago(String documentNumber){
        BaseRequestHTTP requestHTTP = new BaseRequestHTTP();

        setParametros(documentNumber);
        //Map<String, Object > parametros = new HashMap<>();
        //parametros.put("documentNumber",documentNumber);
        //parametros.put("transactionId", UUID.randomUUID());

//        Map <String, Object> headers = new HashMap<>();
//        headers.put("Accept","*/*");
//        headers.put("Connection","keep-alive");

        try {
            return  requestHTTP.httGET( getParametros(), headers,"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/dashboard/prepaid-cards");
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }


    public Response getProveedoresServicios(String documentNumber){
        BaseRequestHTTP requestHTTP = new BaseRequestHTTP();
        setParametros(documentNumber);
        return  requestHTTP.httGET( getParametros(), headers,"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/services/payment/providers");
    }

    public Response getNombreCuentaBHD(String documentNumber, String productNumber){
        return getNombreCuentaBHDPanama(documentNumber, productNumber,"BHD");
    }

    public Response getNombreCuentaBHDIBPanama(String documentNumber, String productNumber){
        return getNombreCuentaBHDPanama(documentNumber, productNumber,"BHDIB");
    }

    public Response getNombreCuentaBHDPanama(String documentNumber, String productNumber, String bankCode){
        BaseRequestHTTP requestHTTP = new BaseRequestHTTP();
        setParametros(documentNumber);
        parametros.put("productNumber",productNumber);
        parametros.put("bankCode",bankCode);
        return  requestHTTP.httGET( getParametros(), headers,"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/services/account-name");
    }

    public static void main(String[] args) {
//        MicroServiciosDashboard ms = new MicroServiciosDashboard();
//        Response respuesta = ms.getNombreCuentaBHDPanama("00111435384","15273480015","BHD");
//        System.out.println(respuesta.getBody().asString());

        MsAccountName nombreCuenta = new MsAccountName("00111435384").queryBHD("06984730045"); // Pesos 15273480015 | Dolar 06984730045
        System.out.println();
        System.out.println(nombreCuenta.getNombre());
        System.out.println(nombreCuenta.getMonedaSigla());
        System.out.println(nombreCuenta.getTipoProductoSigla());
        System.out.println(nombreCuenta.getMonedaConvertida());
    }
}
