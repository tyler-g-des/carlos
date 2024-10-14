package com.bhd.ibp.pages.pagostransferencias;

import accion.AccionMetodos;
import basetest.BaseTest;
import com.bhd.ibp.pages.POSeleccionarFechaPage;
import data.Persistencia;
import microservicios.Utilidad;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.pagostransferencias.accesos.beneficiarios.POBeneficiariosNacionalesPage;
import servicios.micros.pagostransferencias.combox.MicroComboOrigenTercero;
import servicios.micros.pagostransferencias.combox.tecero.MicroComboDestinoTercero;

import static com.bhd.ibp.pages.XpathComunes.*;
import static com.bhd.ibp.pages.pagostransferencias.POPagosTransferenciasPage.*;
import static com.bhd.ibp.pages.pagostransferencias.POPagosTransferenciasPage.COMBO_DESTINO;
import static com.bhd.ibp.pages.pagostransferencias.POPagosTransferenciasPage.COMBO_ORIGEN;
import static com.bhd.ibp.pages.pagostransferencias.POPagosTransferenciasPage.COMBO_TIPO_TRANSACCION;
import static com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage.*;
//import static com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage.COMBO_TIPO_TRANSACCION;
import static com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage.TXT_CORREO;
import static data.ConstantesTasasImpuestos.calcularImpuesto;
import static pages.pyt.XpathTransacciones.CHECK_ADD_BENEFICIARIO_FAVORITO;
import static pages.pyt.XpathTransacciones.CHECK_ADD_TRANSACCION_FAVORITA;

public class POTransaccionesEntreProductosBHDOtrosBancosPage extends AccionMetodos {

    private POSeleccionarFechaPage pageFecha = null;
    private POBeneficiariosNacionalesPage beneficiario = null;
    private MicroComboOrigenTercero msOrigen = null;
    private Double monto = 0.0;
    private Double impuesto = 0.0;
    private String tipoTransaferencia = "";
    private boolean inscrito = false;
    private String montoLabel = "";
    private String totalLabel = "";


    private MicroComboDestinoTercero msDestino = null;

    public POTransaccionesEntreProductosBHDOtrosBancosPage(WebDriver webDriver) {
        super(webDriver);
        pageFecha = new POSeleccionarFechaPage(driver);
    }

    private By comboCanalEnvio = by("//label[contains(text(), 'Canal de envío')]/following-sibling::p-dropdown//span");
    private final String XPATH_LEYENDA = "//legend/span[contains(.,'%s')]";


    /**
     *
     * @return A tercero en BHD | ACH | Pago al instante BCRD
     */
    public String getTipoTransaferencia() {

        return tipoTransaferencia;
    }

    public MicroComboOrigenTercero getMsOrigen() {
        return msOrigen;
    }

    public MicroComboDestinoTercero getMsDestino() {
        return msDestino;
    }

    public boolean isInscrito() {
        return inscrito;
    }

    public String getMontoLabel() {
        return montoLabel;
    }

    public String getTotalLabel() {
        return totalLabel;
    }

    private void setCanalEnvio(String canalEnvio, boolean crearReporte){
        tipoTransaferencia = canalEnvio;
        String transactionType = getText( COMBO_TIPO_TRANSACCION,15, getClass() );
        pageFecha.modalEntiendo();
        if (! transactionType.equals("Transacciones entre productos BHD y a otros Bancos") ){
            selectItemCombo( COMBO_TIPO_TRANSACCION, "Transacciones entre productos BHD y a otros Bancos", "Tipo de transacción", 0, getClass(), crearReporte);
        }
        getText( COMBO_TIPO_TRANSACCION,1, getClass(), crearReporte);
        selectItemCombo( comboCanalEnvio, canalEnvio,"Canal de envio",0, getClass(), crearReporte);
        waitElementVisible( COMBO_ORIGEN,15, getClass());
    }
    private void setOrigenDestino(String productOrigen, String productDestino, boolean crearReporte){
        selectItemCombo( COMBO_ORIGEN, productOrigen,"Producto origen",15, getClass(), crearReporte);
        selectItemCombo( COMBO_DESTINO, productDestino,"Producto destino",15, getClass(), crearReporte);
    }
    private void setDatosTransaccion(String monto, String correo, String descripcion, boolean crearReporte ){
        writeOn( TXT_MONTO, monto, "Monto",0, getClass(), crearReporte );
        writeOn( TXT_CORREO, correo, "Correo beneficiario", 0, getClass(), crearReporte );
        writeOn( TXT_DESCRIPCION, descripcion, "Descripción", 0, getClass(), crearReporte );
    }
    private void setDataTransaccionBeneficiarioInscrito(String monto, String correo, String descripcion, boolean addTransaccionFavorita, boolean crearReporte ){
        setDatosTransaccion( monto, correo, descripcion, crearReporte );
        selectCheckBox( CHECK_ADD_TRANSACCION_FAVORITA,"Adicionar transaccion favorita",0, addTransaccionFavorita, getClass(), crearReporte );

    }
    private void setDataTransaccionBeneficiarioNuevo(String monto, String correo, String descripcion, boolean addBeneficiarioFavorito, boolean addTransaccionFavorita, boolean crearReporte){
        setDatosTransaccion( monto, correo, descripcion, crearReporte );
        selectCheckBox( CHECK_ADD_BENEFICIARIO_FAVORITO,"Adicionar beneficiario favorito",1, addBeneficiarioFavorito, getClass(), crearReporte );
        selectCheckBox( CHECK_ADD_TRANSACCION_FAVORITA,"Adicionar transaccion favorita",0, addBeneficiarioFavorito, getClass(), crearReporte );
        selectCheckBox( CHECK_ADD_BENEFICIARIO_FAVORITO,"Adicionar beneficiario favorito",1, false, getClass(), crearReporte );
        BaseTest.createStep("Pantalla", true, true);
    }



    public void setFormularioTerceroBHDBeneficiarioInscrito(String productOrigen, String productDestino, String monto, String correo, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){

        setCanalEnvio("A tercero en BHD", crearReporte );
        setOrigenDestino( productOrigen, productDestino, crearReporte );
        //waitElementVisible( by(XPATH_LEYENDA, "Transferencia a terceros en BHD"), 5,  getClass());

        getText(by(XPATH_LEYENDA, "Transferencia a terceros en BHD"),5,getClass(),crearReporte);

        setDataTransaccionBeneficiarioInscrito( monto, correo, descripcion, addTransaccionFavorita, crearReporte);
    }

    public POVoucherPage transferenciaATerceroEnBHDInscrito(String productOrigen, String productDestino, String monto, String correo, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        msOrigen = new MicroComboOrigenTercero().consultarBHD(Persistencia.getInstance().getClientInfo(driver).getCedula()).buscar(productOrigen);
        msDestino = new MicroComboDestinoTercero().consultarMicro(msOrigen).buscar(productDestino);
        this.monto = Double.parseDouble(monto);

        System.out.println("Impuesto: " + calcularImpuesto(Double.parseDouble(monto)));

        setFormularioTerceroBHDBeneficiarioInscrito( productOrigen, msDestino.getNumProduct(), monto, correo, descripcion, addTransaccionFavorita, crearReporte );
        inscrito = true;
        return modalConfirmacionBHDACH(crearReporte);
    }

    /**
     * @idLabel
     * @monto
     */
    private String xpLabelMontos = "//div[@role='dialog']//td[@id[contains(.,'%s')] ]//span[contains(.,'%s')]";
    private POVoucherPage modalConfirmacionBHDACH(boolean crearReporte){
        impuesto = Double.parseDouble( calcularImpuesto( monto )) ;
        tipoTransaferencia = msOrigen.getCanal();
        System.out.println("CANAL" + tipoTransaferencia);
        clickOn( BTN_CONTINUAR,"boton","Continuar",0, getClass(), crearReporte, crearReporte );
        getText( TITULO_CONFIRMAR_TRANSACCION,1, getClass(), crearReporte );
        waitElementVisible(by(XPA_PRODUCTO_DEBITO_MODAL, msOrigen.getAlias(), msOrigen.getNumProducto()),1, getClass());
        waitElementVisible(by(XPA_PRODUCTO_CREDITO_MODAL, msDestino.getAlias(), msDestino.getNumProduct()),1, getClass());
        waitElementVisible( by(XPA_IMPUESTO_MODAL, calcularImpuesto( monto )),1, getClass() );
        writeMaskedOn( TXT_TDC,"1111","Tarjeta de clave",0,getClass(),crearReporte);
        montoLabel = getText(by(xpLabelMontos,"amount", Utilidad.darFormatoMonedaComasPunto( String.valueOf(monto))),1,getClass());
        totalLabel = getText(by(xpLabelMontos,"total", Utilidad.darFormatoMonedaComasPunto( String.valueOf((monto+impuesto)))), 0, getClass());

        clickOn( BTN_REALIZAR_TRANSACCION,"boton","Realizar transaccion",0, getClass(), crearReporte, crearReporte );
        return new POVoucherPage(driver, this);
    }

    public void setFormularioTerceroBHDBeneficiarioNuevo(String productOrigen, String nombreBanco, String numProducto, String alias, String correo, String monto, String descripcion, boolean addBeneficiarioFavorito, boolean addTransaccionFavorita, boolean crearReporte){
        setCanalEnvio("A tercero en BHD", crearReporte );
        setOrigenDestino( productOrigen, "Nuevo beneficiario", crearReporte );
        waitElementVisible( by(XPATH_LEYENDA, "Transferencia a terceros en BHD"), 5,  getClass());
        beneficiario = new POBeneficiariosNacionalesPage(driver);
        beneficiario.setBeneficiarioNacional( nombreBanco, numProducto, alias, correo, crearReporte);
        setDataTransaccionBeneficiarioNuevo(monto, correo, descripcion, addBeneficiarioFavorito, addTransaccionFavorita, crearReporte);

        pageFecha.seleccionarFechaFutura("",true, true);
    }



}
