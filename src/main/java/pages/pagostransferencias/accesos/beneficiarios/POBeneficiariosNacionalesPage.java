package pages.pagostransferencias.accesos.beneficiarios;

import accion.AccionMetodos;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.bhd.ibp.pages.XpathComunes.*;
import static pages.pagostransferencias.accesos.beneficiarios.POBeneficiarioPinPesosPage.MSG_CONFIRMAR_ELIMINAR_BENEFICIARIO;

public class POBeneficiariosNacionalesPage extends AccionMetodos {
    public POBeneficiariosNacionalesPage(WebDriver webDriver) {
        super(webDriver);
    }


    private final By comboNombreBanco = by("//div[label[contains(.,'Nombre del banco')]]/p-dropdown//span");
    private final By comboTipoProducto = by("//div[label[contains(.,'Tipo de producto')]]/p-dropdown//span");
    private final By txtNumeroProducto = by("//div[label[contains(.,'Número del producto')]]//input");
    private final By comboTipoDocumento = by("//div[label[contains(.,'Tipo de documento')]]/p-dropdown//span");
    private final By txtNumeroDocumento = by("//div[label[contains(.,'Número del documento')]]//input");
    private final By labelErrorDocumento = by("//div[label[contains(.,'Número del documento')] ] /span[@class[contains(.,'error')] and contains(.,'no se encontró la información')]");
    private final By labelNumeroDocumento = by("//div/label[contains(.,'Número del documento')]");
    protected static final By COMBO_PAIS = By.xpath("//div[label[contains(.,'País de nacimiento')]]/p-dropdown//span");
    protected static final By COMBO_GENERO = By.xpath("//div[label[contains(.,'Género')]]/p-dropdown//span");
    protected static final By TITULO_BENEFICIARIOS_INCRITOS = By.xpath("//h3[contains(.,'Beneficiarios inscritos')]");
    private final By tituloNuevoBeneficiario = by("//h3[contains(.,'Nuevo beneficiario nacional')]");
    private final By tituloEditarBeneficiario = by("//h3[contains(.,'Editar beneficiario nacional')]");
    protected static final By BTN_AGREGAR_BENEFICIARIO = By.xpath("//div[@aria-hidden='false'] //span[contains(.,'Agregar beneficiario')]");
    private final By btnAgregarBeneficiarioNacional = by("//div[@aria-hidden='false'] //li[div//span[contains(.,'Nacionales')]]");
    protected static final By CARGA_TABLA = By.xpath("//p-tabview/div//div[@role='tabpanel' and @aria-hidden='false']  //spinnericon"); //div[@aria-hidden='false'] //spinnericon

    protected static final By LINK_LISTA_BENEFICIARIO_ACTUAL = by("//li/a[@aria-selected='true' ]/span ");
    private final By labelNumeroProducto = by("//div/label[contains(.,'Número del producto')]");
    private final By txtNombreBeneficiario = by("//div[label[contains(.,'Nombre del beneficiario')]]//input");
    protected static final By txtBuscar = By.xpath("//div[@aria-hidden='false'] //input[@placeholder='Buscar']");


    public void setTipoDocumentoReutilizar(String tipoDocumento, String numeroDocumento, String nombreBeneficiario, boolean crearReporte){
        selectItemCombo( comboTipoDocumento, tipoDocumento, "Tipo de documento", 30, getClass(), crearReporte );
        writeOn( txtNumeroDocumento, numeroDocumento,"Número del documento",1, getClass(), crearReporte );
        clickOn( labelNumeroDocumento );

        waitElementVisible( TXT_NOMBRE_BENEFICIARIO,15, getClass() );

        if (isElementPresent( labelErrorDocumento,0) || tipoDocumento.equals("Pasaporte")){
            writeOn( TXT_NOMBRE_BENEFICIARIO, nombreBeneficiario,"Nombre beneficiario",1, getClass(), crearReporte);
        }
    }
    public void setBeneficiarioNacional(String nombreBanco, String numeroProducto, String alias, String correo, boolean crearReporte ){
        selectItemCombo( comboNombreBanco, nombreBanco,"Nombre del banco",10, getClass(), crearReporte );
        writeOn( txtNumeroProducto, numeroProducto,"Número del producto",1, getClass(), crearReporte );
        clickOn( labelNumeroProducto );

        waitElementVisible( txtNombreBeneficiario,  20, getClass());
        writeOn( TXT_ALIAS_BENEFICIARIO, alias,"Alias del beneficiario",1, getClass(), crearReporte );
        writeOn( TXT_CORREO, correo,"Correo del beneficiario",1, getClass(), crearReporte );
    }
    private void setBeneficiarioNacionalOtrosBancos(String nombreBanco, String tipoProducto, String numeroProducto, String tipoDocumento, String numeroDocumento, String nombreBeneficiario, String alias, String pais, String genero, String correo, boolean crearReporte){
        selectItemCombo( comboNombreBanco, nombreBanco,"Nombre del banco",10, getClass(), crearReporte );
        selectItemCombo( comboTipoProducto, tipoProducto,"Tipo producto",1, getClass(), crearReporte );
        writeOn( txtNumeroProducto, numeroProducto,"Número del producto",1, getClass(), crearReporte );
        setTipoDocumentoReutilizar( tipoDocumento, numeroDocumento, nombreBeneficiario, crearReporte );
        writeOn( TXT_ALIAS_BENEFICIARIO, alias,"Alias del beneficiario",1, getClass(), crearReporte );

        selectItemCombo(COMBO_PAIS, pais,"Tipo de producto",1, getClass(), crearReporte );
        selectItemCombo(COMBO_GENERO, genero,"Tipo de producto",1, getClass(), crearReporte );
        writeOn( TXT_CORREO, correo,"Correo del beneficiario",1, getClass(), crearReporte );
    }
    private void botonAgregarBeneficiarioNacional(boolean crearReporte){
        if (isElementPresent(CARGA_TABLA)){
            waitElementInvisible(CARGA_TABLA,2,15, getClass() );
        }

        clickOn( BTN_AGREGAR_BENEFICIARIO,"boton","Agregar beneficiario",10, getClass(), crearReporte );
        clickOn( btnAgregarBeneficiarioNacional,"boton","Nacionales",10, getClass(),  crearReporte, crearReporte );
        waitElementVisible( comboNombreBanco, 20, getClass() );
        getText( tituloNuevoBeneficiario, 0, getClass(), crearReporte );
    }
    private void buscarBeneficiario(String buscar, boolean crearReporte){
        if (isElementPresent(CARGA_TABLA)){
            waitElementInvisible(CARGA_TABLA,2,15, getClass() );
        }
        getText(TITULO_BENEFICIARIOS_INCRITOS, 15, getClass(), crearReporte );
        writeOn( txtBuscar, buscar,"Alias del beneficiario",1, getClass(), crearReporte );
        clickOn(txtBuscar);
        clickOn(txtBuscar);
    }

    protected void buscarMarcarProducto(String buscar, boolean crearReporte){
        buscarBeneficiario(buscar, crearReporte);
        By producto = by("//tr[td[@id='let-accountType']/span[contains(.,'"+buscar+"')] ] /td[@id='let-accountType']");
        clickOn( producto, "Registro","Producto",2, getClass(),false,false);
        BaseTest.createStep("Beneficiario " + buscar, true, true);
    }


    public POBeneficiariosNacionalesPage probarFormularioNuevoBeneficiario(String nombreBanco, String numeroProducto, String alias, String correo, boolean crearReporte){
        botonAgregarBeneficiarioNacional(crearReporte);
        setBeneficiarioNacional( nombreBanco, numeroProducto, alias, correo, crearReporte );
        return this;
    }

    public POBeneficiariosNacionalesPage probarGuardarDesplegarModalTDC(String codigoTDC,boolean crearReporte){
        clickOn(BTN_GUARDAR,"Boton","GUARDAR",0, getClass(), crearReporte, crearReporte);
        waitElementVisible(TITULO_TARJETA_CLAVES,2, getClass());
        getText(MSG_INGRESA_CODIGO_SOLICITADO_TDC,0, getClass(), crearReporte);
        writeMaskedOn(TXT_TDC, codigoTDC,"Tarjeta de claves",2, getClass(), crearReporte );
        //TXT_TDC
        return this;
    }

    private POBeneficiariosNacionalesPage continuarTDC(boolean crearReporte){
        clickOn(BTN_CONTINUAR_MODAL,"Boton","Continuar",0,getClass(), crearReporte, crearReporte);
        return this;
    }

    public POBeneficiariosNacionalesPage modarGuardadoCorrectamente(boolean crearReporte){
        clickOn( BTN_CONTINUAR_MODAL,"Boton","Continuar",1, getClass(), crearReporte, crearReporte );
        By msgBeneficiarioGuardadoCorrectamente = by("//div[@role='dialog'] //h3[contains(.,'El beneficiario ha sido guardado correctamente')]");
        By iconoGuardadoCorrectamente = by("//div[@role='dialog'] //em[@class[contains(.,'check-circle bhd-green')]]");
        getText( msgBeneficiarioGuardadoCorrectamente,10, getClass(), crearReporte );
        waitElementVisible( iconoGuardadoCorrectamente, 0, getClass() );

        clickOn( BTN_CONTINUAR_MODAL,"Boton","Continuar",0, getClass(), crearReporte );
        return this;
    }
    public POBeneficiariosNacionalesPage probarForFormularioNuevoBeneficiarioOtrosBancos(String nombreBanco, String tipoProducto, String numProducto, String tipoDocumento, String numDocumento, String nombreBeneficiario, String alias, String pais, String genero, String correo, boolean crearReporte ){
        botonAgregarBeneficiarioNacional(crearReporte);
        setBeneficiarioNacionalOtrosBancos( nombreBanco, tipoProducto, numProducto, tipoDocumento, numDocumento, nombreBeneficiario, alias, pais, genero, correo, crearReporte );
        return this;
    }


    private void buscarParaEditarBeneficiario(String numCuentaAEditar, boolean crearReporte){
        buscarBeneficiario( numCuentaAEditar, crearReporte );
        By btnEditar = by("//tr[td[@id='let-accountType']/span[contains(.,'"+numCuentaAEditar+"')] ]// button[@icon='pi pi-file-edit']");
        clickOn( btnEditar, "Boton","Editar",2, getClass(), crearReporte, crearReporte );
        waitElementVisible( comboNombreBanco, 30, getClass());
        getText( tituloEditarBeneficiario, 1, getClass(), crearReporte );
    }
    public POBeneficiariosNacionalesPage probarFormularioEditarBeneficiario(String numCuentaAEditar, String alias, String correo, boolean crearReporte){
        buscarBeneficiario( numCuentaAEditar, crearReporte );
        By btnEditar = by("//tr[td[@id='let-accountType']/span[contains(.,'"+numCuentaAEditar+"')] ]// button[@icon='pi pi-file-edit']");
        clickOn( btnEditar, "Boton","Editar",2, getClass(), crearReporte, crearReporte );
        waitElementVisible( comboNombreBanco, 30, getClass());
        getText( tituloEditarBeneficiario, 1, getClass(), crearReporte );
        setBeneficiarioNacional("NA","NA", alias, correo, crearReporte );
        BaseTest.createStep("pantalla", true, true );
        return this;
    }

    public POBeneficiariosNacionalesPage probarFormularioEditarBeneficiario(String buscarProducto,String producto, String alias, String correo, boolean crearReporte){
        buscarBeneficiario( buscarProducto, crearReporte );
        By btnEditar = by("//tr[td[@id='let-accountType']/span[contains(.,'"+producto+"')] ]// button[@icon='pi pi-file-edit']");
        clickOn( btnEditar, "Boton","Editar",2, getClass(), crearReporte, crearReporte );
        waitElementVisible( comboNombreBanco, 30, getClass());
        getText( tituloEditarBeneficiario, 1, getClass(), crearReporte );
        setBeneficiarioNacional("NA","NA", alias, correo, crearReporte );
        BaseTest.createStep("pantalla", true, true );
        return this;
    }

    public POBeneficiariosNacionalesPage probarFormularioEditarBeneficiarioOtrosBancos(String numeroCuentaAEditar, String alias, String pais, String genero, String correo, boolean crearReporte){
        buscarParaEditarBeneficiario( numeroCuentaAEditar, crearReporte );
        setBeneficiarioNacionalOtrosBancos("NA","NA","NA","NA","NA", "NA", alias, pais, genero, correo, crearReporte);
        return this;
    }

    protected POBeneficiariosNacionalesPage modalEliminarBeneficiario(String numCuentaAEliminar, boolean crearReporte){
        buscarBeneficiario( numCuentaAEliminar, crearReporte );
        By btnEliminar = by("//tr[td[@id='let-accountType']/span[contains(.,'"+numCuentaAEliminar+"')] ]// button[@icon[contains(.,'action-delete')]]");
        clickOn( btnEliminar, "Icono","Eliminar",2, getClass(), crearReporte, crearReporte );
        waitElementVisible( MESSAGE_CONFIRMAR_OPERACION_MODAL, 1, getClass() );
        waitElementVisible( ICON_WARNNIG_MODAL, 0, getClass() );
        getText(MSG_CONFIRMAR_ELIMINAR_BENEFICIARIO,0, getClass(), crearReporte);
        return this;
    }




}
