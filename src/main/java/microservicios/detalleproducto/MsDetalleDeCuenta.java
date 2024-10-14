package microservicios.detalleproducto;

import com.fasterxml.jackson.databind.JsonNode;
import http.BaseRequestHTTP;
import io.restassured.response.Response;
import microservicios.Utilidad;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 20/06/2024 6:05 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsDetalleDeCuenta{

    private String documentNumber;
    private Response response;
    private JsonNode detalleCuenta;
    private String productNumber;
    private String mensaje;
    private int statusCode = 0;

    public MsDetalleDeCuenta(String documentNumber, String productNumber){
        this.documentNumber = documentNumber;
        this.productNumber = productNumber;
        nuevaConsulta(documentNumber, productNumber);
    }

    public MsDetalleDeCuenta(){

    }

    public MsDetalleDeCuenta newQuery(){
        nuevaConsulta(documentNumber, productNumber);
        return this;
    }

    public MsDetalleDeCuenta newQuery(String documentNumber, String productNumber){
        nuevaConsulta(documentNumber, productNumber);
        this.documentNumber = documentNumber;
        this.productNumber = productNumber;
        return this;
    }

    private void nuevaConsulta(String documentNumber, String productNumber){
        try {
            statusCode = 0;
            mensaje = "Error buscando el producto: " + productNumber;
            Map<String, Object > parametros = new HashMap<>();
            parametros.put("documentNumber",documentNumber);
            parametros.put("transactionId", UUID.randomUUID());
            parametros.put("productNumber", productNumber);

            Map <String, Object> headers = new HashMap<>();
            headers.put("Accept","*/*");
            headers.put("Connection","keep-alive");

            BaseRequestHTTP baseRequestHTTP = new BaseRequestHTTP();
            response = baseRequestHTTP.httGET(parametros,headers,"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v2/system/product-details/saving-accounts");
            statusCode = baseRequestHTTP.getStatusCode();
            detalleCuenta = Utilidad.jsonNodeParse(response);
        }catch (Exception e){
            System.out.println("Error en Micro Servicio Detalle de cuentas: " + e);
        }
    }

    public String getEstadoProducto(){
        if (detalleCuenta == null){
            return mensaje;
        }else {
            mensaje = Utilidad.formatearPrimeraLetraMayusc(detalleCuenta.get("status").asText());
        }
        return mensaje;
    }

    public String getRegionalNumber(){
        System.out.println("Cantidad Detalle ?? " + detalleCuenta + "??");
        System.out.println();
        if (detalleCuenta == null || statusCode >= 500){
            return mensaje;
        }else {
            mensaje = (detalleCuenta.get("regionalNumber").asText());
        }
        return mensaje;
    }

    public String getBalancePromedioMes(){
        System.out.println("Cantidad Detalle ?? " + detalleCuenta + "??");
        System.out.println();
        if (detalleCuenta == null ){
            return mensaje;
        }else {
            mensaje = Utilidad.darFormatoMonedaFinal( detalleCuenta.get("averageMonthlyBalance").asText() );
        }
        return mensaje;
    }



}
