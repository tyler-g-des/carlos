package com.bhd.ibp.servicios;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.UUID;

public class MicroComboOrigen {

    private String url = "https://ibp-sqa.app.noprod.cfbhd.com/bhd/v2/process/comboboxes/origin-products";
    private JsonNode productos = null;
    private String usuario = "";
    private int statusCode = 0;

    private String monedaSigla = "";
    private String monedaSimbolo = "";
    private String numProduct = "";
    private String tipProduct = "";
    private String tipProductSigla = "";
    private String montoLabel = "";
    private Double monto = 0.0;
    private String alias = "";

    public String getUsuario() {
        return usuario;
    }

    public String getAlias() {
        return alias;
    }

    public Double getMonto() {
        return monto;
    }

    public String getNumProduct() {
        if (tipProductSigla.equals("TAC")){
            return Utilidad.enmascararTC(numProduct);
        }
        return numProduct;
    }

    public String getNumProductLabel(){
        return numProduct;
    }

    // Crediclicl

    private  String montoAprobadoLabel = "";
    private Double montoAprobado = 0.0;
    private boolean disbursed = false;
    private String sistema = "";
    private String balancePrestamoLabel = "";
    private Double balancePrestamo = 0.0;
    private String minimoDesembolsoLabel = "";
    private Double minimoDesembolso = 0.0;
    private String maximoDesembolsoLabel = "";
    private Double maximoDesembo = 0.0;
    private String arragementId = "";


    public String getArragementId() {
        return arragementId;
    }

    public Double getMaximoDesembo() {
        return maximoDesembo;
    }

    public Double getMinimoDesembolso() {
        return minimoDesembolso;
    }

    public Double getBalancePrestamo() {
        return balancePrestamo;
    }

    public String getSistema() {
        return sistema;
    }

    public String getMontoAprobadoLabel() {
        return Utilidad.darFormatoMonedaComasPunto(montoAprobadoLabel);
    }

    public String getBalancePrestamoLabel() {
        return Utilidad.darFormatoMonedaComasPunto(balancePrestamoLabel);
    }

    public String getMaximoDesembolsoLabel() {
        return Utilidad.darFormatoMonedaComasPunto(maximoDesembolsoLabel);
    }

    public String getMinimoDesembolsoLabel() {
        return Utilidad.darFormatoMonedaComasPunto(minimoDesembolsoLabel);
    }

    public String getMonedaSimbolo() {
        return monedaSimbolo;
    }

    public String getTipProduct() {
        return tipProduct;
    }

    public String getTipProductSigla() {
        return tipProductSigla;
    }

    public Double getMontoAprobado() {
        return montoAprobado;
    }

    public boolean isDisbursed() {
        return disbursed;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMontoLabel() {
        return montoLabel;
    }

    public String getMonedaSigla() {
        return monedaSigla;
    }


    private void setErrorCredicli(String msgError, Double monto, boolean exito) {
        this.montoAprobadoLabel = msgError;
        this.montoAprobado = monto;
        this.disbursed = exito;
        this.sistema = msgError;
        this.balancePrestamoLabel = msgError;
        this.balancePrestamo = monto;
        this.minimoDesembolsoLabel = msgError;
        this.minimoDesembolso = monto;
        this.maximoDesembolsoLabel = msgError;
        this.maximoDesembo = monto;
        this.arragementId = msgError;
    }

    private void setError(String msgError, Double monto) {
        this.monedaSigla = msgError;
        this.monedaSimbolo = msgError;
        this.numProduct = msgError;
        this.tipProduct = msgError;
        this.tipProductSigla = msgError;
        this.montoLabel = msgError;
        this.monto = monto;
        this.alias = msgError;
    }

    /**
     *
     * @param tipoTransaccion OB-TP-INT-
     * @param canal
     * @param usuario
     */
    private void consultar(String tipoTransaccion, String canal, String usuario){
        this.usuario = usuario;
        try {
            Response response = BaseRequestHTTP.configNewRequest()
                    .param("transactionId", UUID.randomUUID())
                    .param("transactionType",tipoTransaccion)
                    .param("isRecurrence","N")
                    .param("transChannel",canal)
                    .param("documentNumber",usuario)
                    .log().all()
                    .get(url);
            statusCode = response.getStatusCode();
            productos = Utilidad.jsonNodeParse(response);
            System.out.println(statusCode);

        }catch (Exception e){
            setError("Error consultando combo origen del usuario: "+usuario+" StatusCode: " + e,0.0);
        }
    }

    public MicroComboOrigen consultarEntreMisProductos(String usuario){
        consultar("TP","", usuario);
        return this;
    }

    public MicroComboOrigen consultarTerceroEnBHD(String usuario){
        consultar("OB","TBHD", usuario);
        return this;
    }

    public MicroComboOrigen consultarTerceroACH(String usuario){
        consultar("OB","ACH", usuario);
        return this;
    }
    public MicroComboOrigen consultarTerceroLBTR(String usuario){
        consultar("OB","LBTR", usuario);
        return this;
    }

    public MicroComboOrigen consultarInternacional(String usuario){
        consultar("TI","", usuario);
        return this;
    }

    public MicroComboOrigen consultarRegionalSIPA(String usuario){
        consultar("RS","", usuario);
        return this;
    }

    public MicroComboOrigen consultarPinPesos(String usuario){
        consultar("PI","", usuario);
        return this;
    }

    public MicroComboOrigen selectProducto(String numProduct){

        try {
            if (statusCode == 200){
                if ( productos.size() > 0 ){
                    for (JsonNode producto : productos){
                        System.out.println("_________________________________");
                        System.out.println(producto);
                        System.out.println("_________________________________");
                        if (producto.get("account").asText().contains(numProduct)){
                            monedaSigla = producto.get("currency").asText();
                            this.numProduct = producto.get("account").asText();
                            tipProduct = producto.get("type").asText();
                            montoLabel = Utilidad.darFormatoMonedaComasPunto( producto.get("amount").asText() ) ;
                            monto = producto.get("amount").asDouble();
                            alias = producto.get("alias").asText();

                            tipProductSigla = producto.get("productType").asText();


                            if (tipProductSigla.equals("LIC")){

                                minimoDesembolsoLabel = producto.get("minDisbursement").asText();
                                minimoDesembolso = Double.parseDouble(minimoDesembolsoLabel);
                                maximoDesembolsoLabel = producto.get("maxDisbursement").asText();
                                maximoDesembo = Double.parseDouble(maximoDesembolsoLabel);

                                disbursed = producto.get("isDisbursed").asBoolean();
                                sistema = producto.get("system").asText();
                                montoAprobadoLabel = producto.get("approvedAmount").asText();
                                montoAprobado = Double.parseDouble(montoAprobadoLabel);

                                if (sistema.equals("T24")){
                                    arragementId = producto.get("arrangementId").asText();
                                }

                                if (disbursed){
                                    balancePrestamoLabel = producto.get("loanBalance").asText();
                                    balancePrestamo = Double.parseDouble(balancePrestamoLabel);
                                }else {

                                }
                            }else {
                                setErrorCredicli("El producto no es crediclick", 0.0,false);
//                                minimoDesembolsoLabel = "El producto no es crediclick";
//                                minimoDesembolso = 0.0;
//                                maximoDesembolsoLabel = "El producto no es crediclick";
//                                maximoDesembo = 0.0;
//                                disbursed = false;
//                                sistema = "El producto no es crediclick";
//                                montoAprobadoLabel = "El producto no es crediclick";
//                                montoAprobado = 0.0;

                                monedaSimbolo = producto.get("currencySymbol").asText();
                            }

                            break;
                        }else {
                            setError("Usuario: " + usuario + ", no tiene el producto: " + numProduct + " o no esta cargando.",0.0);
                            setErrorCredicli("Usuario: " + usuario + ", no tiene el producto: " + numProduct + " o no esta cargando.", 0.0,false);
                        }
                    }
                }else {
                    setError("Combo origen no esta cargando los productos del usuario: " + usuario,0.0);
                    setErrorCredicli("Combo origen no esta cargando los productos del usuario: " + usuario, 0.0,false);

                }
            }else if (statusCode == 204){

                setError("El usuario: "+usuario+", no tiene productos, StatusCode: " + statusCode,0.0);
                setErrorCredicli("El usuario: "+usuario+", no tiene productos, StatusCode: " + statusCode, 0.0,false);

            }else {
                setError("Error consultando combo origen del usuario: "+usuario+", StatusCode: " + statusCode,0.0);
                setErrorCredicli("Error consultando combo origen del usuario: "+usuario+", StatusCode: " + statusCode, 0.0,false);

            }
        }catch (Exception e){
            setError("Error consultando combo origen del usuario: "+usuario+", Error: " + e,0.0);
            setErrorCredicli("Error consultando combo origen del usuario: "+usuario+", Error: " + e, 0.0,false);
        }
        return this;
    }
}
