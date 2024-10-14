package com.bhd.ibp.pages.pagostransferencias;

import accion.AccionMetodos;
import accion.AccionPage;
import basetest.BaseTest;
import com.bhd.ibp.pages.POSeleccionarFechaPage;
import data.Persistencia;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PODashboardPage;
import servicios.micros.MicroContratos;

import static com.bhd.ibp.pages.XpathComunes.*;

public class POTransferenciasRegionalesSIPAPage extends AccionMetodos {

    private PODashboardPage pageDashboard = null;
    private POSeleccionarFechaPage fecha = null;

    public POTransferenciasRegionalesSIPAPage(WebDriver webDriver, PODashboardPage pageDashboard) {
        super(webDriver);
        this.pageDashboard = pageDashboard;
        fecha = new POSeleccionarFechaPage(driver);
    }

    public static final By COMBO_TIPO_TRANSACCION = By.xpath("//div[label[contains(.,'Tipo de transacción')] ]  /p-dropdown//span");
    public static final By COMBO_ORIGEN = By.xpath("//div[label[contains(.,'Producto origen')] ]  /p-dropdown//span");
    public static final By COMBO_DESTINO = by("//div[label[contains(.,'Producto destino')] ]  /p-dropdown//span ");
    public static final By COMBO_MONEDA = By.xpath("//div[label[contains(.,'Moneda')] ]  /p-dropdown//span");
    public static final By TXT_CORREO = by("//div[label[contains(.,'Correo ')] ]  //input");
    public static final By TXT_MONTO = by("//div[label[contains(.,'Monto')] ]  //input");
    public static final By TXT_DESCRIPCION = by("//div[label[contains(.,'Descripción')] ]  //input");

    /**
     * @textLeyenda
     */
    public static final String LABEL_LEYENDA = "//legend/span[contains(.,'')]";

    public void verificacion(boolean crearReporte){
//
//        waitElementVisible(by("//h3[contains(.,'Transferencias')]"), 20,getClass());
//        waitElementVisible( COMBO_TIPO_TRANSACCION,30, getClass() );
//
//        System.out.println("Antes de ser visible");
//
//        if (isElementVisible(by("//button[span[contains(.,'Entendido')] ]"),1)){
//            System.out.println("Si esta visible entiendo");
//            clickOn(by("//button[span[contains(.,'Entendido')] ]"),"Boton","Entendido",5,true,getClass(),crearReporte,crearReporte);
//        }
//
//        System.out.println("Despues de ser visible");
//
//        System.out.println(121212);
//        selectItemComboFinales(COMBO_ORIGEN,"09769940054","Producto origen",45, getClass(),true, crearReporte);
//
//        crearPaso("Captura final", true, true, true);
    }

    private void seleccionarTransferencia(boolean crearReporte){
        MicroContratos contratos =  MicroContratos.getInstance().consultarContratoInternacional(Persistencia.getInstance().getClientInfo(driver).getCedula());

        waitElementVisible( COMBO_TIPO_TRANSACCION,20, getClass() );
        if ( contratos.isContractAccepted()){
            fecha.modalEntiendo();
        }else {
            fecha.modalContratoInternacionalRegionalSIPA("1111",crearReporte);
        }
        getText( COMBO_TIPO_TRANSACCION, 0, getClass(), crearReporte);
        //BaseTest.createStep("Pantalla",true, true);
    }

    private By txtNumIdentificacionBeneficiario = by("//div[label[contains(.,'Número de identificación del beneficiario')]]//input");
    private By comboDirecionBeneficiario = by("//div[label[contains(.,'Dirección del beneficiario')] ]  /p-dropdown//span");
    private By txtCalleCiudad = by("//input[@placeholder='CALLE, CIUDAD, ESTADO']");
    private By txtNumProducto = by("//div[label[contains(.,'Número de producto')] ]  //input");
    private By comboNombreBanco = by("//div[label[contains(.,'Nombre del banco')] ]  /p-dropdown//span");


    public void setFormulario(String productOrigen, String nombreBeneficiario, String numIdentificacion, String direccion, String calle, String correo, String nombreBanco, String numProducto, String monto, String descripcion, boolean crearReporte){
        seleccionarTransferencia(crearReporte);
        selectItemCombo( COMBO_ORIGEN, productOrigen,"Producto Origen",30, getClass(), crearReporte );
        writeOn( TXT_NOMBRE_BENEFICIARIO, nombreBeneficiario,"Nombre del beneficiario",0, getClass(), crearReporte );
        writeOn( txtNumIdentificacionBeneficiario, numIdentificacion,"Numero identifiacion",0, getClass(), crearReporte );
        selectItemCombo( comboDirecionBeneficiario, direccion,"Producto Origen",30, getClass(), crearReporte );
        writeOn(txtCalleCiudad, calle,"Calle",1, getClass(), crearReporte );
        writeOn(TXT_CORREO, correo,"Correo beneficiario",1, getClass(), crearReporte );
        selectItemCombo(comboNombreBanco , nombreBanco,"Nombre del banco",40, getClass(), crearReporte );

        writeOn(txtNumProducto, numProducto,"Numero de producto",1, getClass(), crearReporte );
        writeOn(TXT_MONTO, monto,"Monto",1, getClass(), crearReporte );
        writeOn(TXT_DESCRIPCION, descripcion,"Descripción",1, getClass(), crearReporte );
    }

    private void continuar(boolean crearReporte){
        clickOn( BTN_CONTINUAR,"Boton","Continuar",0, getClass(), crearReporte, crearReporte );
    }

    private POVoucherPage realizarTransaccion(String codigoTDC, boolean crearReporte){
        writeMaskedOn( TXT_TDC, codigoTDC,"Codigo tarjeta de claves",0, getClass(), crearReporte);
        clickOn( BTN_REALIZAR_TRANSACCION,"Boton","Realizar transaccion",0, getClass(), crearReporte, crearReporte);
        return new POVoucherPage(driver);
    }

    public POVoucherPage realizarTransferencia(String productOrigen, String nombreBeneficiario, String numIdentificacion, String direccion, String calle, String correo, String nombreBanco, String numProducto, String monto, String descripcion, boolean crearReporte){
        setFormulario(productOrigen, nombreBeneficiario, numIdentificacion, direccion, calle, correo, nombreBanco, numProducto, monto, descripcion, crearReporte);
        continuar(crearReporte);
        return realizarTransaccion("1111", crearReporte);
    }





}
