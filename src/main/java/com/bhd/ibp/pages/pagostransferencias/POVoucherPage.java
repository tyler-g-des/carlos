package com.bhd.ibp.pages.pagostransferencias;

import accion.AccionMetodos;
import basetest.BaseTest;
import data.Persistencia;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POVoucherPage extends AccionMetodos {


    private Double montoAnteriorOrigen = 0.0;
    private String producto = "";
    public POVoucherPage(WebDriver webDriver) {
        super(webDriver);
    }

    private String labelNombre = "//strong[contains(.,'¡%S, a continuación presentamos el resultado de tu transacción!')]";
    private By estadoTransaccion = by("//div[div[@class[contains(.,'status ')]]]/h5");
    private By labelNumeroConfirmacion = by("//strong[contains(.,'Número de confirmación:')]");
    private final String XP_LBL_PRODUCTO = "//div[strong[contains(.,'Producto %s:')] and h5[contains(.,'%s - %s')]]";

    private void verificarNombre(){

    }
    private String getEstadoTransaccion (){
        return getText( estadoTransaccion,5, getClass() );
    }

    public boolean transaccionExistosa(){
        boolean estado = false;
        String estadoTransacc = getText( estadoTransaccion,1, getClass() );
        if (estadoTransacc.contains("proceso") || estadoTransacc.contains("Completada")){
            estado = true;
        }
        return estado;
    }


    public String getNumeroConfirmacion(){
        String confirmacion = getText(labelNumeroConfirmacion, 1, getClass());
        return confirmacion.split(":")[1].replace(" ","");
    }


    private POTransferenciasPinPesoPage pageTransfPinPesos = null;
    private POTransaccionesEntreProductosBHDOtrosBancosPage pageTercero = null;
    private POTransferenciaEntreMisProductosPage pageMisProductos = null;

    public POVoucherPage(WebDriver webDriver, POTransferenciasPinPesoPage pinPesos) {
        super(webDriver);
        pageTransfPinPesos = pinPesos;
    }

    public POVoucherPage(WebDriver webDriver, POTransaccionesEntreProductosBHDOtrosBancosPage pageTercero) {
        super(webDriver);
        this.pageTercero = pageTercero;
    }

    public void imprimirTitulo(boolean crearReporte){
        String nombre = Persistencia.getInstance().getClientInfo(driver).getNombreCompleto();// Persistencia.getInstance().getClientInfo( pageTransfPinPesos.getUsuario() ).getNombreCompleto();
        getText( by( labelNombre, nombre ),360, getClass(), crearReporte );
    }

    private final String labelTipoTransaccion = "//div[strong[contains(.,' Tipo de transacción')]  and h5[contains(.,'Transaccion entre productos BHD y a otros Bancos ( TBHD )')]]";

    /**
     * @nombreLabel
     * @MonedaSigla_SingMoneda_Monto
     */
    private final String labelMontos = "//div[strong[contains(.,'%s')] and h5[contains(.,'%s')]]";
    public void verificarTransferenciaTercero(boolean crearReporte){
        imprimirTitulo(crearReporte);
        montoAnteriorOrigen = pageTercero.getMsOrigen().getMonto();

        String canal = pageTercero.getTipoTransaferencia();

        //String origen = getText(by(XP_LBL_PRODUCTO,"origen", pageTercero.getMsOrigen().getAlias(),pageTercero.getMsOrigen().getNumProducto()), 0, getClass());
        String destino = "";
        if (pageTercero.isInscrito()){
            destino = getText(by(XP_LBL_PRODUCTO,"destino", pageTercero.getMsDestino().getAlias(), pageTercero.getMsDestino().getNumProduct()), 0, getClass());
        }
        String tipoTransaccion = getText(by(labelTipoTransaccion,"Transaccion entre productos BHD y a otros Bancos ( "+canal+" )"),0, getClass());
        String monto = getText(by(labelMontos,"Monto", pageTercero.getMontoLabel() ), 0, getClass());
        String total = getText(by(labelMontos,"Total", pageTercero.getTotalLabel() ), 0, getClass());
        //String total = getText(by(labelMontos,"Total", pageTercero.getMontoLabel() )); // Se esta mostrando cobro de impuestos
        String numConfirmacion = "";
        if (transaccionExistosa()){
            numConfirmacion = "Numero confirmacion: " + getNumeroConfirmacion();
        }
        BaseTest.createStep(numConfirmacion+ "<br>"
                + //origen +
                "<br>" + destino + "<br>" + monto + "<br>" + total + "<br>" + tipoTransaccion,true, crearReporte);
    }

    public void verificarTransaferenciaPinPesos(boolean crearReporte){
        imprimirTitulo(crearReporte);
        BaseTest.createStep("Numero de confirmacion: "+ getNumeroConfirmacion(), true, false);
        System.out.println("Numero de confirmacion: " + getNumeroConfirmacion());
    }


    public void verificarPinPesos( boolean crearReporte){
        String nombre = Persistencia.getInstance().getClientInfo( pageTransfPinPesos.getUsuario() ).getNombreCompleto();
        getText( by( labelNombre, nombre ),360, getClass(), crearReporte );

        if ( pageTransfPinPesos.isBeneficiarioInscrito() ){
            String origen = getText(by(XP_LBL_PRODUCTO,"origen", pageTransfPinPesos.getProducto().getAlias(),pageTransfPinPesos.getProducto().getNumeroCuenta()));
            BaseTest.createStep(origen,true, true);
        }
    }


    public POVoucherPage(WebDriver webDriver, POTransferenciaEntreMisProductosPage pageMisProductos){
        super(webDriver);
        this.pageMisProductos = pageMisProductos;
    }

    public void verificarTransferenciaEntreCuentas(boolean crearReporte){
        imprimirTitulo( crearReporte );
        montoAnteriorOrigen = pageMisProductos.getMsOrigen().getMonto();
        producto = pageMisProductos.getMsOrigen().getNumProduct();
        System.out.println("NumeroProducto: " + producto);
        System.out.println("BalanceAnterior: " +  montoAnteriorOrigen); // 168,123.73
        System.out.println("BalanceAnterior: " + pageMisProductos.getMsOrigen().getMontoLabel());

    }
}
