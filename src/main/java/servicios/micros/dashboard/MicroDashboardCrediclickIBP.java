package servicios.micros.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.UUID;

public class MicroDashboardCrediclickIBP {

    private String documentNumber = "";
    private int statusCode = 0;
    private String alias = "";
    private String numeroProducto = "";
    private String nombreProducto = "";
    private String tipoProductoSigla = "";
    private String tipoProducto = "";
    private String moneda = "RD";
    private String montoAprobadoString = "";
    private String montoAprobadoLabel = "";
    private String arrangementId = "";

    private String montoDisponibleString = "";
    private String montoDisponibleLabel = "";
    private String estadoProducto = "";

    private String balanceString = "";
    private String balanceLabel = "";
    private String cuotaMensualString = "";

    private String cuotaMensualLabel = "";

    private String cuotaPendienteString = "";

    private String cuotaPendienteLabel = "";
    private boolean isDesembolsado = false;
    private boolean isTieneMontoDisponible = false;

    public static MicroDashboardCrediclickIBP getInstance(){
        return new MicroDashboardCrediclickIBP();
    }

    public MicroDashboardCrediclickIBP(){

    }

    public int getStatusCode() {
        return statusCode;
    }
    public String getNombreActualDelProducto(){
        if (alias.equals("")){
            return nombreProducto;
        }else {
            return alias;
        }
    }

    public String getAlias() {
        return alias;
    }

    public String getNumeroProducto() {
        return numeroProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getTipoProductoSigla() {
        return tipoProductoSigla;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getMontoAprobadoLabel() {
        return montoAprobadoLabel;
    }

    public String getArrangementId() {
        return arrangementId;
    }

    public String getBalanceDisponibleLabel() {
        return montoDisponibleLabel;
    }

    /**
     * Retorna el estado del producto
     * @return {@link String} ACTIVO o APROBADO PENDIENTE DE DESEMBOLSO
     */
    public String getEstadoProducto() {
        return estadoProducto;
    }

    public String getBalanceLabel() {
        return balanceLabel;
    }

    public String getCuotaMensualLabel() {
        return cuotaMensualLabel;
    }

    public String getCuotaPendienteLabel() {
        return cuotaPendienteLabel;
    }

    public boolean isDesembolsado() {
        return isDesembolsado;
    }

    public boolean isTieneMontoDisponible() {
        return isTieneMontoDisponible;
    }

    private void setCampos(int statusCode, JsonNode jsonNode) {

        if (statusCode == 200){
            this.statusCode = statusCode;
            this.alias = jsonNode.get("alias").asText();
            this.numeroProducto = jsonNode.get("productNumber").asText();
            this.nombreProducto = jsonNode.get("name").asText();
            this.tipoProductoSigla = jsonNode.get("productType").asText();
            this.tipoProducto = jsonNode.get("type").asText();
            this.montoAprobadoString = jsonNode.get("approvedAmount").asText();

            montoAprobadoLabel = Utilidad.darFormatoMonedaComasPunto(montoAprobadoString);

            this.arrangementId = jsonNode.get("arrangementId").asText();

            this.montoDisponibleString = jsonNode.findValue("availableAmount").asText();
            montoDisponibleLabel = Utilidad.darFormatoMonedaComasPunto(montoDisponibleString);

            this.estadoProducto = jsonNode.findValue("status").asText();
            this.balanceString = jsonNode.findValue("balance").asText();

            balanceLabel = Utilidad.darFormatoMonedaComasPunto(balanceString);

            this.cuotaMensualString = jsonNode.findValue("monthlyFee").asText();

            cuotaMensualLabel = Utilidad.darFormatoMonedaComasPunto(cuotaMensualString);

            this.cuotaPendienteString = jsonNode.findValue("pendingLoanFee").asText();
            cuotaPendienteLabel = Utilidad.darFormatoMonedaComasPunto(cuotaPendienteString);

            this.isDesembolsado = jsonNode.findValue("isDisbursed").asBoolean();
            this.isTieneMontoDisponible = jsonNode.findValue("hasAvailableAmount").asBoolean();

        } else if (statusCode == 204) {
            setError( statusCode,"Este usuario ["+documentNumber+"] no contiene Crediclick.",false,"0");
        } else {
            setError( statusCode,"Se ha producido un error consultando el Dashboard Credicklick. Usuario: " + documentNumber,false,"0");
        }

    }

    private void setError(int statusCode, String mensajeError, boolean booleanos, String montos) {
            this.statusCode = statusCode;
            this.alias = mensajeError;
            this.numeroProducto = mensajeError;
            this.nombreProducto = mensajeError;
            this.tipoProductoSigla = mensajeError;
            this.tipoProducto = mensajeError;
            this.montoAprobadoString = montos;
            this.arrangementId = mensajeError;
            this.montoDisponibleString = montos;
            this.estadoProducto = mensajeError;
            this.balanceString = montos;
            this.cuotaMensualString = montos;
            this.cuotaPendienteString = montos;
            this.isDesembolsado = booleanos;
            this.isTieneMontoDisponible = booleanos;
    }


    public MicroDashboardCrediclickIBP consultar(String documentNumber){
        this.documentNumber =  documentNumber;
        statusCode = 0;
        try {
            Response response = BaseRequestHTTP.configNewRequest()
                    .param("transactionId", UUID.randomUUID())
                    .param("documentNumber", documentNumber).log().all()
                    .get("https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v2/system/dashboard/financing");

            statusCode = response.getStatusCode();
            System.out.println(response.getBody().asString());
            setCampos(statusCode, Utilidad.jsonNodeParse(response));
        }catch (Exception e){
            setError(0,"Error realizando la consulta de Crediclick del usuario: " + documentNumber,false,"0");
        }
        return this;
    }
}
