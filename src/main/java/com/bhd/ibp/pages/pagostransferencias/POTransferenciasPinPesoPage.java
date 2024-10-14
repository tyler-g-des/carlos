package com.bhd.ibp.pages.pagostransferencias;

import accion.AccionMetodos;
import basetest.BaseTest;
import com.bhd.ibp.pages.POSeleccionarFechaPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PODashboardPage;
import pages.pagostransferencias.accesos.beneficiarios.POBeneficiarioPinPesosPage;
import servicios.micros.accesos.beneficiarios.BeneficiarioPinPeso;
import servicios.micros.accesos.beneficiarios.MicroBeneficiariosNacionales;
import servicios.micros.pagostransferencias.MicroCombOrigenPinPesos;

import java.util.ArrayList;

import static com.bhd.ibp.pages.XpathComunes.*;
import static com.bhd.ibp.pages.pagostransferencias.POPagosTransferenciasPage.*;
import static com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage.TXT_DESCRIPCION;
import static com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage.TXT_MONTO;


public class POTransferenciasPinPesoPage extends AccionMetodos {

    private POBeneficiarioPinPesosPage pageBenficiarioPinPesos = null;
    private POSeleccionarFechaPage pageFecha = null;
    private MicroCombOrigenPinPesos producto = null;

    BeneficiarioPinPeso benefPinPeso = null; // new MicroBeneficiariosNacionales().consultarMicro("22301391524").selectBeneficiariosPinPesos().buscarBeneficiario(beneficiario);
    private MicroBeneficiariosNacionales microBeneficiario = new MicroBeneficiariosNacionales();
    private BeneficiarioPinPeso beneficiario = null;

    private String monto = "";
    private String descripcion = "";
    private String origen = "";
    private String destino = "";
    private String alias = "";
    private String telefono = "";
    private boolean beneficiarioInscrito = false;
    private String identificador = "";
    private String usuario = "";


    public MicroCombOrigenPinPesos getProducto() {
        return producto;
    }

    public boolean isBeneficiarioInscrito() {
        return beneficiarioInscrito;
    }

    public POTransferenciasPinPesoPage(WebDriver webDriver) {
        super(webDriver);
        //msComboOrigen.consultarMicro("22301391524");
        pageBenficiarioPinPesos = new POBeneficiarioPinPesosPage( driver );
        pageFecha = new POSeleccionarFechaPage( driver );
    }

    public POTransferenciasPinPesoPage(WebDriver webDriver, PODashboardPage pageDashboard) {
        super(webDriver);

        //msComboOrigen.consultarMicro("22301391524");
        pageBenficiarioPinPesos = new POBeneficiarioPinPesosPage( driver );
        pageFecha = new POSeleccionarFechaPage( driver );
        this.identificador = pageDashboard.getIdentificador();
        usuario = pageDashboard.getNumeroDocumento();

//       Persistencia.getInstance().consultarComboOrigenPinPesos(identificador, usuario);
//        System.out.println(Persistencia.getInstance().getOrigenPinPeso(identificador).buscarCuenta("34066020016").getAlias());
//       msComboOrigen = Persistencia.getInstance().getOrigenPinPeso(usuario);
        producto = new MicroCombOrigenPinPesos().consultarMicro(usuario);
    }

    public String getUsuario() {
        return usuario;
    }

    private final By labelLeyenda = by("//legend/span[contains(.,'Transferencia PIN Pesos')]");
    private void setCombos(String productOrigen, String productDestino, boolean crearReporte){
        String tipo = getText( COMBO_TIPO_TRANSACCION,15, getClass(),false);
         titul0 = tipo;
         pageFecha.modalEntiendo();

        if (! tipo.equalsIgnoreCase("PIN Pesos") ){
            selectItemCombo( COMBO_TIPO_TRANSACCION, "PIN Pesos","Tipo de transacción",1, getClass(), false );
        }
        BaseTest.createStep("Transacciones -> PIN Pesos", true, crearReporte );

        selectItemCombo( COMBO_ORIGEN, productOrigen, "Tipo de transacción", 20, getClass(), crearReporte );
        selectItemCombo( COMBO_DESTINO, productDestino, "Tipo de transacción", 20, getClass(), crearReporte );
        waitElementVisible( labelLeyenda, 10, getClass() );
    }




    public void probarFormularioBeneficiarioInscrito(String productOrigen, String telefBeneficiario, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        beneficiarioInscrito = true;
        beneficiario = new MicroBeneficiariosNacionales().consultarMicro(usuario).selectBeneficiariosPinPesos().buscarBeneficiario(telefBeneficiario);

        setCombos( productOrigen, telefBeneficiario, crearReporte);
        writeOn( TXT_MONTO, monto,"Monto",1, getClass(), crearReporte ); this.monto = monto;
        writeOn( TXT_DESCRIPCION, descripcion,"Descripción",1, getClass(), crearReporte );
        pageFecha.seleccionarCheckboxTransaccion(null, addTransaccionFavorita, crearReporte);

    }

    public POVoucherPage realizarTransferenciaBeneficiarioInscrito(String productOrigen, String telefBeneficiario, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        probarFormularioBeneficiarioInscrito(productOrigen, telefBeneficiario, monto, descripcion, addTransaccionFavorita, crearReporte);
        realizarTransaccion(crearReporte);
        return new POVoucherPage(driver, this);
    }
    private void realizarTransaccion(boolean crearReporte){
        clickOn( BTN_CONTINUAR,"Boton","Continuar",0, getClass(), crearReporte ,crearReporte);
        getText( TITULO_CONFIRMAR_TRANSACCION,0, getClass(), crearReporte);
        writeMaskedOn( TXT_TDC, "1111","Tarjeta de clave",0, getClass(), crearReporte);
        clickOn( BTN_REALIZAR_TRANSACCION,"Boton","Realizar transaccion",0, getClass(),true,30, crearReporte ,crearReporte);
    }


    //private MicroCombOrigenPinPesos msComboOrigen = new MicroCombOrigenPinPesos();
//    public void probarModalConfirmacionInscrito(String cuentaOrigen, String beneficiario, String monto, String descripcion, boolean addTransaccionFavorita,boolean crearReporte){
//        probarFormularioBeneficiarioInscrito( cuentaOrigen, beneficiario, monto, descripcion, addTransaccionFavorita, crearReporte );
//        clickOn( BTN_CONTINUAR,"Boton","Continuar",0, getClass(), crearReporte, crearReporte );
//        getText( TITULO_CONFIRMAR_TRANSACCION, 2, getClass(), crearReporte );
//
//    }

    /**
     * Id: debito o credito
     * producto: alias o nombre:
     * numeroProducto
     */
    private final String XPATH_LABEL_MODAL = "//div[@role='dialog']//td[@id[contains(.,'%s')] ] /span[contains(.,'%s') and contains(.,'%s')]";
    public void modarConfirmacionBeneficiarioInscrito(String cuentaOrigen, String telefonoBeneficiario, String monto, String descripcion, boolean addTransaccionFavorita, String codigoTDC, boolean crearReporte){
        probarFormularioBeneficiarioInscrito( cuentaOrigen, telefonoBeneficiario, monto, descripcion, addTransaccionFavorita, crearReporte );
        clickOn( BTN_CONTINUAR,"Boton","Continuar",0, getClass(), crearReporte, crearReporte );
        getText( TITULO_CONFIRMAR_TRANSACCION, 2, getClass(), crearReporte );
        waitElementVisible(by(XPATH_LABEL_MODAL, "debitProduct", producto.getAlias(), producto.getNumeroCuenta()),20, getClass());
        waitElementVisible(by(XPATH_LABEL_MODAL, "creditBeneficiaryPinPesos", beneficiario.getNombreActualBeneficiario(), beneficiario.getTelefonoBeneficiario()),20, getClass());
        writeMaskedOn(TXT_TDC, codigoTDC,"Tarjeta de claves",0,getClass(),crearReporte);
    }

    public POVoucherPage realizarTransaccion(String cuentaOrigen, String telefonoBeneficiario, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        modarConfirmacionBeneficiarioInscrito(cuentaOrigen, telefonoBeneficiario, monto, descripcion, addTransaccionFavorita, "1111",crearReporte);
        clickOn( BTN_REALIZAR_TRANSACCION, "boton","Realizar transaccion",2,getClass(),true,15, crearReporte, crearReporte);
        return new POVoucherPage(driver,this);
    }



    String titul0 = "";
    public POTransferenciasPinPesoPage probarFormularioBeneficiarioNuevo(String cuentaOrigen, String alias, String telefono, String tipoDocumento, String numDocumento, String nombre, String pais, String genero, String monto, String descripcion, boolean addBeneficiarioFavorito, boolean addTransaccionFavorita, boolean crearReporte){
        beneficiarioInscrito = false;

        setCombos( cuentaOrigen, "Nuevo beneficiario", crearReporte );

        pageBenficiarioPinPesos.setFormulario( alias, telefono, tipoDocumento, numDocumento, nombre, pais, genero, crearReporte );
        writeOn( TXT_MONTO, monto,"Monto",1, getClass(), crearReporte );
        writeOn( TXT_DESCRIPCION, descripcion,"Descripción",1, getClass(), crearReporte );
        pageFecha.seleccionarCheckboxTransaccion( addBeneficiarioFavorito, addTransaccionFavorita, crearReporte);



        lista.add(this);
        Object objecto = lista.get(0);
        if (objecto instanceof POTransferenciasPinPesoPage){

            POTransferenciasPinPesoPage page = (POTransferenciasPinPesoPage) objecto;
            System.out.println("Mi titulo: " + page.titul0);
        }
        System.out.println();
        return this;
    }


    public String getMsgAlert(String labelAEvaluar, boolean tomarCaptura){
        return getText(by("//div[label[contains(.,'"+labelAEvaluar+"')]]/span"),1,getClass(), tomarCaptura);
    }



    ArrayList lista = new ArrayList<>();


    public void probarModalConfirmacionInscritoNuevo(){


    }
}
