package com.bhd.ibp.pages.pagostransferencias;

import accion.AccionMetodos;
import basetest.BaseTest;
import com.bhd.ibp.pages.POSeleccionarFechaPage;
import com.bhd.ibp.servicios.MicroComboOrigen;
import data.Persistencia;
import microservicios.Utilidad;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import servicios.micros.MicroClientInfo;

import static com.bhd.ibp.pages.XpathComunes.*;
import static com.bhd.ibp.pages.pagostransferencias.POPagosTransferenciasPage.TITULO_CONFIRMAR_TRANSACCION;
import static com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage.*;

public class POTransferenciaEntreMisProductosPage extends AccionMetodos {

    private MicroComboOrigen msOrigen = null;


    public MicroComboOrigen getMsOrigen() {
        return msOrigen;
    }

    //private String usuario = "";

    private POSeleccionarFechaPage pageReutilizar = null;
    private MicroClientInfo info = null;

    public POTransferenciaEntreMisProductosPage(WebDriver webDriver) {
        super(webDriver);
        pageReutilizar = new POSeleccionarFechaPage(driver);
        //usuario = Persistencia.getInstance().getClientInfo(driver).getCedula();
        info = Persistencia.getInstance().getClientInfo(driver);
    }

    private void selectTipoTransaccion(boolean crearReporte){
        String tipo = getText(COMBO_TIPO_TRANSACCION,15, getClass());
        if (! tipo.equals("Transacciones entre mis productos")){
            selectItemCombo(COMBO_TIPO_TRANSACCION,"ransacciones entre mis productos","Tipo de transacción",1,getClass(),crearReporte);
        }
        getText(COMBO_TIPO_TRANSACCION,0,getClass(), crearReporte);
    }

    private void selectTipoTransaccionOrigen( String productOrigen, boolean crearReporte){

        String tipo = getText(COMBO_TIPO_TRANSACCION,15, getClass());
        if (! tipo.equals("Transacciones entre mis productos")){
            selectItemCombo(COMBO_TIPO_TRANSACCION,"ransacciones entre mis productos","Tipo de transacción",1,getClass(),crearReporte);
        }
        getText(COMBO_TIPO_TRANSACCION,0,getClass(), crearReporte);
        selectItemCombo(COMBO_ORIGEN,productOrigen,"Producto origen",40,getClass(),crearReporte);
    }



    private void setComboOrigenDestino(String productOrigen, String productDestino, boolean crearReporte){
        selectItemCombo( COMBO_ORIGEN, productOrigen, "Origen", 120, getClass(), crearReporte );
        selectItemCombo( COMBO_DESTINO, productDestino, "Destino", 120, getClass(), crearReporte );
    }

    public POTransferenciaEntreMisProductosPage setEntreCuentas(String cuentaOrigen, String cuentaDestino, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        pageReutilizar.modalEntiendo();
        msOrigen = new MicroComboOrigen().consultarEntreMisProductos(info.getCedula()).selectProducto(cuentaOrigen);
        System.out.println(msOrigen.getMonto());


        selectTipoTransaccion(crearReporte);
        setComboOrigenDestino( cuentaOrigen, cuentaDestino, crearReporte );
        getText(by( LABEL_LEYENDA,"Transferencia entre cuentas"),5, getClass(), crearReporte );
        writeOn( TXT_MONTO, monto,"Monto",1, getClass(), crearReporte );
        writeOn( TXT_DESCRIPCION, descripcion,"Descripción", 1, getClass(), crearReporte );
        pageReutilizar.seleccionarCheckboxTransaccion(null, addTransaccionFavorita, crearReporte );
        return this;
    }

    public POVoucherPage transferenciaentreCuentaHoy(String cuentaOrigen, String cuentaDestino, String monto, String descripcion, boolean addTransferenciaFavorita, boolean crearReporte){
        setEntreCuentas( cuentaOrigen, cuentaDestino, monto, descripcion, addTransferenciaFavorita, crearReporte );
        seleccionarFechaHoy();
        continuar( crearReporte, crearReporte );

        getText( TITULO_CONFIRMAR_TRANSACCION,1,getClass(), crearReporte );
        if ( info.isActivoTDCTransaccionar() ){
            writeMaskedOn( TXT_TDC,"1111","Tarjeta de claves",0, getClass(), crearReporte );
        }
        clickOn( BTN_REALIZAR_TRANSACCION,"Boton","Realizar transaccion",0,getClass(), crearReporte, crearReporte );
        return new POVoucherPage(driver, this);
    }


    public POTransferenciaEntreMisProductosPage seleccionarFechaHoy(){
        pageReutilizar.seleccionarFechaHoy();
        return this;
    }

    public void seleccionarFechaFutura(String fecha, boolean crearReporte){
        pageReutilizar.seleccionarFechaFutura(fecha,true,crearReporte);
    }

    public void seleccionarFechaRecurrente(String fecha,String frecuencia, boolean repetirHastaCancelacion,int oNTransaccionesRealizadas, boolean notificarme, int nDiasAntesRealizarTransaccion,boolean crearReporte){
        pageReutilizar.seleccionarFechaRecurrente( fecha, frecuencia, repetirHastaCancelacion, oNTransaccionesRealizadas, notificarme, nDiasAntesRealizarTransaccion,crearReporte );
    }

    public POTransferenciaEntreMisProductosPage continuar(boolean tomarCaptura,boolean crearReporte){
        clickOn( BTN_CONTINUAR, "boton","Continuar",0, getClass(), tomarCaptura, crearReporte );
        return this;
    }

    public void modalConfirmacionEntreCuentas(){
        getText(MSG_INGRESA_CODIGO_SOLICITADO_TDC, 1,getClass(),true);
    }
    public POTransferenciaEntreMisProductosPage realizarTransaccion(boolean tomarCaptura, boolean crearReporte){
        if (Persistencia.getInstance().getClientInfo(driver).isActivoTDCTransaccionar()){
            writeMaskedOn(TXT_TDC,"1111","Realizar transaccion",0, getClass(), crearReporte);
        }
        clickOn( BTN_REALIZAR_TRANSACCION, "boton","Continuar",0, getClass(), tomarCaptura, crearReporte );
        return this;
    }

    private By txtMonto = by("//p-inputnumber//input");
    private By radioAbonoDisminucionMontoCuota = by("//p-radiobutton[@value='decreaseAmountToInstallments']  /div");
    private By radioAbonoDisminucionCantidadCuota = by("//p-radiobutton[@value='decreaseAmountQuotas']  /div");

    public void setFormularioPagoPrestamoPropio(String cuentaOrigen, String numPrestamo, boolean cuotaPendiente, boolean disminucionMontoCuota, boolean disminucionCantidadCuotas, String monto, String descripcion, boolean crearReporte){
        pageReutilizar.modalEntiendo();
        selectTipoTransaccion( crearReporte );
        setComboOrigenDestino( cuentaOrigen, numPrestamo, crearReporte );
        getText(by( LABEL_LEYENDA, "Pago préstamos propios" ),  15, getClass(), crearReporte );
        if (cuotaPendiente){
            selectRadioButon( radioAbonoDisminucionCantidadCuota,"Cuota Pendiente",1, cuotaPendiente, getClass(), crearReporte);
        }
        selectRadioButon( radioAbonoDisminucionMontoCuota,"disminucion monto cuotas",1, disminucionMontoCuota, getClass(), crearReporte);
        selectRadioButon( radioAbonoDisminucionCantidadCuota,"disminucion cantidad cuotas",1, disminucionCantidadCuotas, getClass(), crearReporte);
        writeOn( txtMonto, monto,"Monto",0, getClass(), crearReporte );
        writeOn( TXT_DESCRIPCION, descripcion,"Descripcion",0, getClass(), crearReporte );
    }

    public POVoucherPage pagoPrestamoPropio(String numCuentaOrigen, String numPrestamo, boolean disminucionMontoCuota, boolean disminucionCantidadCuota, String monto, String descripcion,boolean crearReporte){
        setFormularioPagoPrestamoPropio( numCuentaOrigen, numPrestamo,false, disminucionMontoCuota, disminucionCantidadCuota, monto, descripcion, crearReporte);
        continuar( crearReporte, crearReporte );
        realizarTransaccion( crearReporte, crearReporte );
        return new POVoucherPage(driver);
    }

    private void setOrigenDestino(String productOrigen, String productDestino, boolean crearReporte ){
        pageReutilizar.modalEntiendo();
        selectTipoTransaccion( crearReporte );
        setComboOrigenDestino( productOrigen, productDestino, crearReporte );
    }


    // Tarjeta de credito ⏬⏬⏬
    public static String XP_LEYENDA = "//legend/span[contains(.,'%s')]";
    private void setPagoTarjetaCredito( String cuentaOrigen, String tcDestino, boolean saldoActual, boolean pendienteCorte, boolean pagoMinimo, String otroValorPagar, boolean crearReporte ){
        pageReutilizar = new POSeleccionarFechaPage(driver);
        setOrigenDestino( cuentaOrigen, tcDestino, crearReporte );
        getText( by(XP_LEYENDA,"Pago de tarjeta de crédito"),10, getClass(), crearReporte );
        //fecha.seleccionarFechaFutura();

       // selectRadioButon("","","","", getClass(), crearReporte);
    }

    private By comboOpcionTC = by("//p-dropdown[@inputid='creditCardOption']//span");
    public void setAvanceEfectivoTC( String productOrigen, String cuentaDestino, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte ){
        pageReutilizar.modalEntiendo();
        selectTipoTransaccionOrigen( Utilidad.enmascararTC(productOrigen), crearReporte );
        selectItemCombo( comboOpcionTC,"Avance de efectivo Tarjeta de Crédito","Opcion de Tarjeta",5, getClass(), crearReporte);
        selectItemCombo( COMBO_DESTINO, cuentaDestino,"Producto destino",60, getClass(), crearReporte);

        getText(by( XP_LEYENDA,"Avance de efectivo - TC"),10, getClass(), crearReporte);

        writeOn( TXT_MONTO, monto,"Monto",0, getClass(), crearReporte );
        writeOn( TXT_DESCRIPCION, descripcion,"Descripción",0, getClass(), crearReporte );
        pageReutilizar.seleccionarCheckboxTransaccion(null, addTransaccionFavorita, crearReporte );
        BaseTest.createStep("Pantalla", true, true);
    }

    public POVoucherPage avanceEfectivoTarjetaCredito(String tcOrigen, String cuentaDestino, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        setAvanceEfectivoTC( tcOrigen, cuentaDestino, monto, descripcion, addTransaccionFavorita, crearReporte );
        continuar( crearReporte, crearReporte);
        realizarTransaccion( crearReporte, crearReporte );
        return new POVoucherPage( driver );
    }


    // Opciones de tarjeta de credito
    private void setOrigenOpcionTCDestino( String productOrigen, String opcionTC, String destino, boolean crearReporte){
        pageReutilizar.modalEntiendo();
        String tipo = getText(COMBO_TIPO_TRANSACCION,15, getClass());
        if (! tipo.equals("Transacciones entre mis productos")){
            selectItemCombo(COMBO_TIPO_TRANSACCION,"ransacciones entre mis productos","Tipo de transacción",1,getClass(),crearReporte);
        }
        getText(COMBO_TIPO_TRANSACCION,0,getClass(), crearReporte);
        selectItemCombo(COMBO_ORIGEN,productOrigen,"Producto origen",60,getClass(),crearReporte);

        selectItemCombo( comboOpcionTC,opcionTC,"Opcion de Tarjeta",5, getClass(), crearReporte);
        selectItemCombo( COMBO_DESTINO, destino,"Producto destino",60, getClass(), crearReporte);
    }

    public void setDifiereTuCorte( String tcOrigen, boolean crearReporte ){
        setOrigenOpcionTCDestino( Utilidad.enmascararTC( tcOrigen) , "Difiere tu corte", Utilidad.enmascararTC( tcOrigen), crearReporte );
        getText( by( XP_LEYENDA,"Difiere tu corte"),5, getClass(), crearReporte);
    }

    private By comboPlazo = by("//div[label[contains(.,'Plazo')] ]  /p-dropdown//span");
    public void setAvanceEfectivoCuotasBHD(String tcOrigen, String cuentaDestino, String monto,String plazo, String descripcion, boolean crearReporte){
        setOrigenOpcionTCDestino( Utilidad.enmascararTC( tcOrigen ) ,"Avance de efectivo Cuotas BHD", cuentaDestino, crearReporte );
        getText( by( XP_LEYENDA, "Avance de efectivo - Cuotas BHD"),15, getClass(), crearReporte );
        writeOn( TXT_MONTO, monto,"Monto",0, getClass(), crearReporte );
        writeOn( TXT_DESCRIPCION, descripcion,"Descripción",0, getClass(), crearReporte );
        selectItemCombo( comboPlazo, plazo,"Plazo",5, getClass(), crearReporte );
    }

}
