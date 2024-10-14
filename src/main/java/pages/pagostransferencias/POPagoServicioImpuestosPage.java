package pages.pagostransferencias;

import accion.AccionMetodos;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.pagostransferencias.accesos.POServiciosInscritosPage;

import static com.bhd.ibp.pages.XpathComunes.BTN_ENTIENDO;
import static com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage.*;
import static pages.pagostransferencias.accesos.POServiciosInscritosPage.LABEL_REFERENCIA;
import static pages.pagostransferencias.accesos.POServiciosInscritosPage.TXT_REFERENCIA;

public class POPagoServicioImpuestosPage extends AccionMetodos {
    public POPagoServicioImpuestosPage(WebDriver webDriver) {
        super(webDriver);
    }

    private final By combOpcionDeServicio = by("//p-dropdown[@name='serviceOption'] //span");
    private final By comboServicio = by("//p-dropdown[@name='service'] //span");
    private final By comboPagarConEsteProducto = by("//div[label[contains(.,'Pagar con este producto')] ] /p-dropdown //span");
    private final By leyendaPagoServicio = by("//legend/span[contains(.,'Pago de servicios')]");
    private final By txtMonto = by("//div[label[contains(.,'Monto de la recarga')] ] //input");
    private static final By BTN_CONSULTAR = by("//button[contains(.,'Consultar')]");
    private static final String XPATH_LABEL_LEGEND = "//legend/span[contains(.,'Pago de impuestos (%S)')]";
    private final By txtNumeroAutorizacion = by("//div[label[contains(.,'Número de autorización:')]]//input");
    private static final By comboTipoImpuesto = by("//div[label[contains(.,'Tipo de impuesto o importe')]] /p-dropdown //span");



    // Reutilizar para prueba de formularios.

    private void setTipoTransaccion(String opcionDeServicio, boolean crearReporte){
        String tipo = getText( COMBO_TIPO_TRANSACCION, 20, getClass() );
        if (isElementPresent(BTN_ENTIENDO,1)){
            clickOn(BTN_ENTIENDO, "boton","Entiendo",1, getClass(),crearReporte);
        }
        if (! tipo.equalsIgnoreCase("Pago de Servicio e Impuestos")){
            selectItemCombo( COMBO_TIPO_TRANSACCION, "Pago de Servicio e Impuestos","Tipo de transacción",1, getClass(), crearReporte );
        }
        selectItemCombo( combOpcionDeServicio, opcionDeServicio,"Tipo de servicio",10, getClass(), crearReporte );
    }
    private POPagoServicioImpuestosPage setMonto(String monto, String correo, String descripcion, boolean adicionarTransaccionFavorita, boolean crearReporte ){
        writeOn( txtMonto, monto,"Monto",1, getClass(), crearReporte );
        writeOn( TXT_CORREO, correo,"Correo",1, getClass(), crearReporte );
        writeOn( TXT_DESCRIPCION, descripcion,"Descripción",1, getClass(), crearReporte );
        BaseTest.createStep("Pantalla setMonto clase POPagoServicioImpuestosPage.java", true, true);
        return this;
    }


    public POPagoServicioImpuestosPage probarServicioNuevoHastaConsultaReferencia(String proveedor, String servicio, String productoOrigen, String referencia, boolean consultarReferencia, boolean crearReporte){
        POServiciosInscritosPage servicioNuevo = new POServiciosInscritosPage( driver, "");

        setTipoTransaccion("Servicio nuevo", crearReporte );
        servicioNuevo.setFormularioServicioReutilizar( proveedor, servicio,"NA","NA", crearReporte );

        selectItemCombo( comboPagarConEsteProducto, productoOrigen,"Producto Origen",10, getClass(), crearReporte );
        waitElementVisible( leyendaPagoServicio,10, getClass() );
        writeOn(TXT_REFERENCIA, referencia, getText( LABEL_REFERENCIA, 0, getClass() ),1, getClass(), crearReporte );

        if (consultarReferencia){
            clickOn( BTN_CONSULTAR,"Boton","Consultar",1, getClass(), crearReporte );
        }
        return this;
    }
    public POPagoServicioImpuestosPage probarServicioInscritoHastaConsultaReferencia(String servicio, String productOrigen, boolean crearReporte){
        setTipoTransaccion( "Servicio inscrito", crearReporte );
        selectItemCombo( comboServicio, servicio,"Servicio",10, getClass(), crearReporte );
        selectItemCombo( comboPagarConEsteProducto, productOrigen, "Producto Origen", 10, getClass(), crearReporte );
        waitElementVisible( leyendaPagoServicio, 10, getClass() );
        return this;
    }
    public POPagoServicioImpuestosPage probarConsultaImpuestoNuevo(String tipoImpuesto, String referencia, boolean consultarReferencia, boolean crearReporte ){
        setTipoTransaccion("Impuesto nuevo", crearReporte );

        selectItemCombo( comboTipoImpuesto, tipoImpuesto,"Tipo de impuesto o importe",10, getClass(), crearReporte );
        waitElementVisible( by( XPATH_LABEL_LEGEND, tipoImpuesto),10, getClass());

        writeOn( txtNumeroAutorizacion, referencia, "Número de autorización" ,1, getClass(), crearReporte );

        if (consultarReferencia){
            clickOn( BTN_CONSULTAR,"Boton","Consultar",1, getClass(), crearReporte );
        }
        BaseTest.createStep("Pantalla",true, true);
        return this;
    }

    public POPagoServicioImpuestosPage setFormularioServicioNuevo(String proveedor, String servicio, String productOrigen, String referencia, String monto, String correo, String descripcion, boolean addFavorita, boolean crearReporte){
        probarServicioNuevoHastaConsultaReferencia(proveedor,servicio,productOrigen,referencia,true, crearReporte );
        waitElementVisible( TXT_MONTO, 10, getClass());
        setMonto( monto, correo, descripcion, addFavorita, crearReporte );
        return this;
    }




    public void formularioPagoServicioInscrito( String servicio, String productoOrigen, String monto, String correo, String descripcion, boolean addAFavorito, boolean crearReporte){
        setTipoTransaccion("Servicio inscrito", crearReporte );
        selectItemCombo( comboServicio, servicio,"Servicio",10, getClass(), crearReporte );
        selectItemCombo( comboPagarConEsteProducto, productoOrigen,"Producto Origen",10, getClass(), crearReporte );
        waitElementVisible( leyendaPagoServicio,10, getClass() );
        setMonto( monto, correo, descripcion, addAFavorito, crearReporte );

        BaseTest.createStep("Pantalla", true, true );
    }















}
