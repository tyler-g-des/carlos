package pages.pagostransferencias.accesos.beneficiarios;

import accion.AccionMetodos;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.bhd.ibp.pages.XpathComunes.*;
import static pages.pagostransferencias.accesos.beneficiarios.POBeneficiariosNacionalesPage.*;

public class POBeneficiarioPinPesosPage extends AccionMetodos {

    POBeneficiariosNacionalesPage pageNacional = null;
    public POBeneficiarioPinPesosPage(WebDriver webDriver) {
        super(webDriver);
        pageNacional = new POBeneficiariosNacionalesPage(driver);
    }

    private final By comboTipoDocumento = by("//div[label[contains(.,'Tipo de documento')]]/p-dropdown//span");
    private final By txtNumeroDocumento = by("//div[label[contains(.,'Número del documento')]]//input");
    private final By txtTelefono = by("//div[label[contains(.,'Teléfono del beneficiario')]]//input");
    private final By labelErrorDocumento = by("//div[label[contains(.,'Número del documento')] ] /span[@class[contains(.,'error')] and contains(.,'no se encontró la información')]");

    private By getLabelError(String textoLabel){
        return by("//div[label[contains(.,'"+textoLabel+"')] ] /span[@class[contains(.,'error')] ]");
    }

 //   private final By labelNumeroDocumento = by("//div/label[contains(.,'Número del documento')]");
//    public void setTipoDocumento(String tipoDocumento, String numeroDocumento, String nombreBeneficiario,String alias, boolean crearReporte ){
//        selectItemCombo( comboTipoDocumento, tipoDocumento, "Tipo de documento", 30, getClass(), crearReporte );
//        writeOn( txtNumeroDocumento, numeroDocumento,"Número del documento",1, getClass(), crearReporte );
//        clickOn( labelNumeroDocumento );
//
//        waitElementVisible( TXT_NOMBRE_BENEFICIARIO,15, getClass() );
//
//        if (isElementPresent( labelErrorDocumento,0)){
//            writeOn( TXT_NOMBRE_BENEFICIARIO, nombreBeneficiario,"",1, getClass(), crearReporte);
//        }
//        writeOn( TXT_ALIAS_BENEFICIARIO, alias,"Alias",1, getClass(), crearReporte);
//        BaseTest.createStep("Pantalla", true, true);
//    }

    public void setFormulario(String alias, String telefono, String tipoDocumento, String numeroDocumento, String nombre, String pais, String genero, boolean crearReporte){
        writeOn(TXT_ALIAS_BENEFICIARIO, alias, "Alias",20, getClass(),crearReporte);
        writeOn(txtTelefono, telefono, "Teléfono beneficiario",0, getClass(),crearReporte);
        pageNacional.setTipoDocumentoReutilizar( tipoDocumento, numeroDocumento, nombre, crearReporte);
        selectItemCombo( COMBO_PAIS, pais, "País de nacimiento", 5, getClass(), crearReporte );
        selectItemCombo( COMBO_GENERO, genero, "Género", 5, getClass(), crearReporte );
    }

    private final By btnAgregarBeneficiarioPinPesos = by("//div[@aria-hidden='false'] //li[div//span[contains(.,'PIN Pesos')]]");
    private final By tituloNuevoBeneficiario = by("//h3[contains(.,'Nuevo beneficiario PIN Pesos')]");
    private final By tituloEditarBeneficiario = by("//h3[contains(.,'Editar beneficiario nacional')]");
    private final By btnInternacionales = by("//a[span[contains(.,'Internacionales')] ]");


    protected void probarFormularioNuevoBeneficiario(String alias, String telefono, String tipoDocumento, String numDocumento, String nombre, String pais, String genero, boolean crearReporte){
        ///clickOn( btnInternacionales, "Boton", "Internacionales", 10, getClass(), crearReporte, crearReporte );
        getText( TITULO_BENEFICIARIOS_INCRITOS, 10, getClass(), crearReporte );
        waitVisibleNoVisible( CARGA_TABLA, 0, 8 );
        clickOn( BTN_AGREGAR_BENEFICIARIO, "Boton", "Agregar beneficiario", 1, getClass(), crearReporte);
        clickOn( btnAgregarBeneficiarioPinPesos, "Boton", "PIN Pesos",1, getClass(), crearReporte, crearReporte );
        getText( tituloNuevoBeneficiario, 5, getClass(), crearReporte);
        setFormulario(alias, telefono, tipoDocumento, numDocumento, nombre, pais, genero, crearReporte);
    }

    protected void consultarBeneficiario(String numTelefono, boolean crearReporte){
        getText(TITULO_BENEFICIARIOS_INCRITOS,5, getClass(), false);
        waitElementNoPresent(CARGA_TABLA,1,8);
        writeOn( txtBuscar, numTelefono,"Buscar",10, getClass(), crearReporte );
        clickOn(txtBuscar);
        clickOn(txtBuscar);
        BaseTest.createStep("Beneficiario: " + numTelefono, true, crearReporte);
    }

    private final By linkSesionPinPesos = by("//li/a[span[contains(.,'PIN Pesos')]]");

    /**
     * numero cuenta
     * icono de accion
     */
    private final String xpBotonesEditarEliminar = "//div[@aria-hidden='false']  //tr[td[@id='let-accountType']/span[contains(.,'%s')] ] //button[@icon[contains(.,'%s')] ]"; //div[@aria-hidden='false']  //tr[td[@id='let-accountType']/span[contains(.,' 8097865456')] ]   //button[@icon='pi pi-file-edit']
    private void clickSesionBeneficiariosPINPesos(boolean crearReporte){
        String listaActual = "";
        getText( TITULO_BENEFICIARIOS_INCRITOS, 10, getClass(), false );
        if (getAttribute(linkSesionPinPesos, 0, "aria-selected", getClass()).equals("false") ){

            listaActual = getText( LINK_LISTA_BENEFICIARIO_ACTUAL, 0, getClass());

            BaseTest.createStep("Beneficiarios inscritos " + listaActual, true, crearReporte);
            clickOn(linkSesionPinPesos,"Boton","PIN PESOS",0, getClass(),false, crearReporte );
        }
        BaseTest.createStep("Beneficiarios inscritos PIN Pesos", true, crearReporte);
    }

    public POBeneficiarioPinPesosPage probarFormularioEditarBeneficiario(String numTelefDeBeneficiarioAEditar, String alias, boolean crearReporte ){
        clickSesionBeneficiariosPINPesos( crearReporte );
        writeOn(txtBuscar, numTelefDeBeneficiarioAEditar,"Buscar",0, getClass(), crearReporte);
        clickOn(by( xpBotonesEditarEliminar, numTelefDeBeneficiarioAEditar, "edit"),"Icono","Editar",3,getClass(),crearReporte,crearReporte);
        setFormulario( alias,"NA","NA","NA","NA","NA","NA", crearReporte );
    return this;
    }

    protected static final By MSG_CONFIRMAR_ELIMINAR_BENEFICIARIO = by("//div[@role='dialog'] //div[div[em[@class[contains(.,'orange')] ] ]] //h3[contains(.,'¿Estás seguro de que quieres eliminar este beneficiario?')]");
    public void probarModalEliminarBeneficiario(String numTelefDeBeneficiarioAEliminar , boolean crearReporte){
        clickSesionBeneficiariosPINPesos( crearReporte );
        writeOn( txtBuscar, numTelefDeBeneficiarioAEliminar,"Buscar",0, getClass(), crearReporte);
        clickOn( txtBuscar);
        clickOn(by( xpBotonesEditarEliminar, numTelefDeBeneficiarioAEliminar, "action-delete"),"Icono","Eliminar",3,getClass(),crearReporte,crearReporte);
        waitElementVisible( MESSAGE_CONFIRMAR_OPERACION_MODAL, 2, getClass() );
        getText( MSG_CONFIRMAR_ELIMINAR_BENEFICIARIO, 0, getClass(), crearReporte);
    }

    private final By msgNoSeEncotroRegistros = by("//div[@aria-hidden='false'] //td[contains(.,'No se encontraron registros para mostrar')]");

    /**
     * <h1>Listo.</h1>
     * @param crearReporte
     * @return
     */
    protected String confirmarEliminacionBeneficiario(boolean crearReporte){
        clickOn( BTN_ELIMINAR_MODAL, "Boton", "Eliminar", 0, getClass(),true, 15, false , crearReporte );
        return getText( msgNoSeEncotroRegistros,2, getClass(), crearReporte);
    }
}
