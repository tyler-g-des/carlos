package microservicios.servicios;

import http.BaseRequestHTTP;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 07/06/2024 1:02 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MicroServiciosIncritos extends BaseRequestHTTP{

    private Map<String, Object> headers;
    private Map<String, Object > parametros;
    private String documentNumber;

    public MicroServiciosIncritos(String documentNumber){
        this.documentNumber = documentNumber;
    }

    private Map<String, Object> getHeaders(){
        headers = new HashMap<>();
        headers.put("Accept","*/*");
        headers.put("Connection","keep-alive");
        return headers;
    }

    private void inicializarParametros(){
        parametros = new HashMap<>();
        parametros.put("documentNumber", documentNumber);
        parametros.put("transactionId", UUID.randomUUID());
    }

    public Response getServiciosIncritos(){
        //BaseRequestHTTP request = new BaseRequestHTTP();
        inicializarParametros();
        return httGET(parametros, getHeaders(),"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/customer/services-beneficiaries/beneficiaries");
    }

    public Response getProveedoresServicios(){
        //BaseRequestHTTP requestHTTP = new BaseRequestHTTP();
        inicializarParametros();
        return  httGET( parametros, headers,"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/services/payment/providers");
    }

    public static void main(String[] args) {
        MicroServiciosIncritos servInscr = new MicroServiciosIncritos("22301391524");
        servInscr.getServiciosIncritos();
    }
}
