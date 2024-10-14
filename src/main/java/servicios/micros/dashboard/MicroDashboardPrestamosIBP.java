package servicios.micros.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MicroDashboardPrestamosIBP {

    private JsonNode prestamos = null;
    private Response response = null;
    private String documentNumber = "";
    private int statusCode = 0;
    private String alias = "";
    private String monedaSigla = "";
    private String nombreProducto = "";
    private String numeroProducto = "";
    private String tipoProducto = "";
    private String tipoProductosigla = "";
    private String sistema = "";
    private String estadoProducto = "";

    private String balanceString = "";


    private String pendienteString = "";
    private String pendienteAlCorteString = "";


    private String balanceLabel = "";
    private String pendienteAlCorteLabel = "";
    private String pendienteLabel = "";

    private float balanceFloat = 0.0f;
    private float pendienteAlCorteFloat = 0.0f;
    private float pendienteFloat = 0.0f;


    private String montoEnAtrasoString = "";

    private String montoEnTransitoString = "";
    private String montoEnTransitoLabel = "";
    private boolean isFinancing = false;

    public String getBalanceLabel() {
        return balanceLabel;
    }

    public String getPendienteAlCorteLabel() {
        return pendienteAlCorteLabel;
    }

    private void setVariables(String alias, String monedaSigla, String nombreProducto, String numeroProducto, String tipoProducto, String tipoProductosigla, String sistema, String estadoProducto, String balanceString, String pendienteString, String montoEnTransitoString, boolean isFinancing) {
        this.alias = alias;
        this.monedaSigla = monedaSigla;
        this.nombreProducto = nombreProducto;
        this.numeroProducto = numeroProducto;
        this.tipoProducto = tipoProducto;
        this.tipoProductosigla = tipoProductosigla;
        this.sistema = sistema;
        this.estadoProducto = estadoProducto;
        this.balanceString = balanceString;
        this.pendienteString = pendienteString;
        this.montoEnTransitoString = montoEnTransitoString;
        this.isFinancing = isFinancing;
    }

    public MicroDashboardPrestamosIBP nuevaConsulta(String documentNumber, String customerCodeT24){
        this.documentNumber = documentNumber;
        try {
            Response response = BaseRequestHTTP.configNewRequest()
                    .param("transactionId","AUTOMATIZACION-"+ UUID.randomUUID())
                    .param("documentNumber",documentNumber)
                    .param("customerCodeT24",customerCodeT24).log().all()
                    .get( BaseRequestHTTP.HOST_MICRO_SQA_IBP + "/bhdleon/v2/system/dashboard/loans");
            response = response;
            statusCode = response.statusCode();
            if (statusCode == 200 || statusCode == 206){
                prestamos = Utilidad.jsonNodeParse(response);
                System.out.println(prestamos);
            }

        }catch (Exception e){

        }
        return this;
    }

    public MicroDashboardPrestamosIBP buscar(String numeroPrestamo){
        try {

            if (statusCode == 200 && prestamos.size() > 0){

                System.out.println("Si contiene datos" + prestamos.size());
                for (JsonNode prestamo : prestamos){
                    if (prestamo.get("productNumber").asText().equals(numeroPrestamo)){
                        System.out.println("este prestamo si existe: " + prestamo.get("productNumber").asText());

                        alias = prestamo.get("alias").asText();
                        monedaSigla = prestamo.get("currency").asText();
                        nombreProducto = prestamo.get("name").asText();
                        numeroProducto = prestamo.get("productNumber").asText();
                        tipoProducto = prestamo.get("type").asText();
                        tipoProductosigla = prestamo.get("productType").asText();
                        estadoProducto = prestamo.get("status").asText();

                        balanceString = prestamo.get("balance").asText();
                        System.out.println("Encontrado: " + balanceString);
                        balanceLabel = Utilidad.darFormatoMonedaComasPunto(balanceString);
                        balanceFloat = Float.parseFloat(balanceString);

                        pendienteString = prestamo.get("pendingLoanFee").asText();
                        pendienteLabel = Utilidad.darFormatoMonedaComasPunto(pendienteString);
                        pendienteFloat = Float.parseFloat(pendienteString);

                        pendienteAlCorteString = prestamo.get("loanFee").asText();
                        pendienteAlCorteLabel = Utilidad.darFormatoMonedaComasPunto(pendienteAlCorteString);
                        pendienteAlCorteFloat = Float.parseFloat(pendienteAlCorteString);

                        montoEnAtrasoString = prestamo.get("delayedAmount").asText();
                        isFinancing = prestamo.get("isFinancing").asBoolean();

                        break;
                    }else {
                        System.out.println("No existe");
                    }
                }
            }
        }catch (Exception e){
            System.out.println("Error consultando el micro servicio");
        }
        return this;
    }


}
