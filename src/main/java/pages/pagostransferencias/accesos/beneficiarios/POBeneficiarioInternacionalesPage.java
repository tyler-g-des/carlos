package pages.pagostransferencias.accesos.beneficiarios;

import accion.AccionMetodos;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.bhd.ibp.pages.XpathComunes.TXT_CORREO;
import static com.bhd.ibp.pages.XpathComunes.TXT_NOMBRE_BENEFICIARIO;
import static com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage.COMBO_MONEDA;
import static pages.pagostransferencias.accesos.beneficiarios.POBeneficiarioPinPesosPage.MSG_CONFIRMAR_ELIMINAR_BENEFICIARIO;
import static pages.pagostransferencias.accesos.beneficiarios.POBeneficiariosNacionalesPage.*;

public class POBeneficiarioInternacionalesPage extends AccionMetodos {
    public POBeneficiarioInternacionalesPage(WebDriver webDriver) {
        super(webDriver);
    }


    private By txtNumIdetificacion = by("//div[label[contains(.,'Número de identificación')]]//input");
    private By comboDireccionBeneficiario = by("//div[label[contains(.,'Dirección del beneficiario')] ]  /p-dropdown//span");
    private By txtCalleCiudadEstado = by("//input[@placeholder='CALLE, CIUDAD, ESTADO']");
    private By txtNumProductIBAN = by("//div[label[contains(.,'Número de producto / IBAN')]]//input");
    private By comboTipoCodigoBanco = by("//div[div[h6[text()=' DATOS DEL BANCO '] ]]  //p-dropdown//span");
    private By comboTipoCodigoBancoIntermediario = by("//div[div[h6[text()=' DATOS DEL BANCO INTERMEDIARIO '] ]] //p-dropdown[@name='bankCodeTypeInter']//span");
    private By txtCodigo = by("//div[div[h6[text()=' DATOS DEL BANCO '] ]]  //input[@formcontrolname='bankCodeNumber']");
    private By txtCodBancoIntermediario = by("//div[div[h6[text()=' DATOS DEL BANCO INTERMEDIARIO '] ]] //input[@formcontrolname='bankCodeNumberInter']");
    private By tituloNuevoBeneficiario = by("//h3[contains(.,'Nuevo beneficiario internacional')]");
    private final By btnAgregarBeneficiarioInternacional = by("//div[@aria-hidden='false'] //li[div//span[contains(.,'Internacionales')]]");
    private By btnValidarcodigo = by("//div[div[h6[text()=' DATOS DEL BANCO '] ]] //button[span[contains(.,'Validar código')]]");
    private By btnValidarCodigoIntermediario = by("//div[div[h6[text()=' DATOS DEL BANCO INTERMEDIARIO '] ]] //button[span[contains(.,'Validar código')]]  ");
    private By checkBoxUsarBancoIntermediario = by("//p-checkbox[@name='useInterBank']//div");
    private By comboDestino = by("//div[label[contains(.,'Destino')] ]  /p-dropdown//span");
    private By comboClasificacion = by("//div[label[contains(.,'Clasificación')] ]  /p-dropdown//span");
    private By comboProposito = by("//div[label[contains(.,'Propósito')] ]  /p-dropdown//span");
    private By txtNumProductIBANIntermediario = by("//div[div[h6[text()=' DATOS DEL BANCO INTERMEDIARIO '] ]]  //input[@formcontrolname='productNumberInter']");
    private By linkSesionInternacionales = by("//li/a[span[contains(.,'Internacionales')]]");
    private final String XPATH_BTN_EDITAR_ELIMINAR = "//div[@aria-hidden='false']  //tr[td[@id='let-accountType']/span[contains(.,'%s')]] //button[@icon[contains(.,'%s')] ]";



    public void setFormulario(String nombreBeneficiario,String numIdentificacion, String direccion, String calle, String correo, String numProductIBAN,String moneda, String tipoCodigo, String codigo, boolean validarCodigo, boolean validarCodigIntermediario,String tipoCodigoIntermediario, String numProductIbanIntermediario,String codigoIntermediario, String destino, String clasificacion, String proposito, boolean crearReporte ){
        writeOn( TXT_NOMBRE_BENEFICIARIO, nombreBeneficiario,"Nombre beneficiario",5, getClass(), crearReporte );
        writeOn( txtNumIdetificacion, numIdentificacion,"Numero identificacion",5, getClass(), crearReporte );
        selectItemCombo( comboDireccionBeneficiario, direccion,"Dirección del beneficiario",3,getClass(), crearReporte );
        writeOn( txtCalleCiudadEstado, calle,"CALLE, CIUDAD, ESTADO",5, getClass(), crearReporte );
        writeOn( TXT_CORREO, correo,"Correo del beneficiario",5, getClass(), crearReporte );
        writeOn( txtNumProductIBAN, numProductIBAN,"Número de producto IBAN",5, getClass(), crearReporte );
        selectItemCombo( COMBO_MONEDA, moneda,"Moneda",3, getClass(), crearReporte );
        selectItemCombo( comboTipoCodigoBanco, tipoCodigo,"Tipo codigo",3, getClass(), crearReporte );
        writeOn( txtCodigo, codigo,"Codigo Banco",5, getClass(), crearReporte );
        if (validarCodigo){
            clickOn( btnValidarcodigo,"Boton","Validar codigo",1,getClass(),crearReporte,crearReporte);
        }
        if (validarCodigIntermediario){
            selectCheckBox(checkBoxUsarBancoIntermediario,"Usar Banco Intermediario",1,true, getClass(), crearReporte );

            writeOn( txtNumProductIBANIntermediario, numProductIbanIntermediario,"Número de producto IBAN Intermediario",5, getClass(), crearReporte );

            selectItemCombo( comboTipoCodigoBancoIntermediario, tipoCodigoIntermediario,"Tipo codigo",3, getClass(), crearReporte );
            writeOn( txtCodBancoIntermediario, codigoIntermediario,"Codigo Banco",5, getClass(), crearReporte );
            clickOn( btnValidarCodigoIntermediario,"Boton","Validar codigo",1, getClass(),crearReporte,crearReporte);
        }

        selectItemCombo( comboDestino, destino,"Destino",3, getClass(), crearReporte );
        selectItemCombo( comboClasificacion, clasificacion,"Clasificación",3, getClass(), crearReporte );
        selectItemCombo( comboProposito, proposito,"Propósito",3, getClass(), crearReporte );
        capturarPanalla();
    }
    protected void setFormularioNuevoBeneficiario(String nombreBeneficiario,String numIdentificacion, String direccion, String calle, String correo, String numProductIBAN,String moneda, String tipoCodigo, String codigo, boolean validarCodigo, boolean validarCodigIntermediario, String numProductIbanIntermediario,String tipoCodigoIntermediario, String codigoIntermediario, String destino, String clasificacion, String proposito, boolean crearReporte){

        getText( TITULO_BENEFICIARIOS_INCRITOS, 10, getClass(), crearReporte );
        waitVisibleNoVisible( CARGA_TABLA, 0, 8 );

//        getText( TITULO_BENEFICIARIOS_INCRITOS,10, getClass(),crearReporte);
//        if (isElementPresent(CARGA_TABLA)){
//            waitElementInvisible(CARGA_TABLA,2,15, getClass() );
//        }
        clickOn( BTN_AGREGAR_BENEFICIARIO,"boton","Agregar beneficiario",10, getClass(), crearReporte );
        clickOn(btnAgregarBeneficiarioInternacional,"boton","Nacionales",10, getClass(),  crearReporte, crearReporte );
        waitElementVisible( TXT_NOMBRE_BENEFICIARIO, 20, getClass() );
        getText( tituloNuevoBeneficiario, 0, getClass(), crearReporte );
        setFormulario(
                nombreBeneficiario,
                numIdentificacion,
                direccion,
                calle,
                correo,
                numProductIBAN,
                moneda,
                tipoCodigo,
                codigo,
                validarCodigo,
                validarCodigIntermediario,
                tipoCodigoIntermediario,
                numProductIbanIntermediario,
                codigoIntermediario,
                destino,
                clasificacion,
                proposito,
                crearReporte);
    }

    private void navegarAListaBeneficiarios(boolean crearReporte){
        String listaActual = "";
        getText( TITULO_BENEFICIARIOS_INCRITOS, 10, getClass(), false );
        if (getAttribute(linkSesionInternacionales, 0, "aria-selected", getClass()).equals("false") ){

            listaActual = getText( LINK_LISTA_BENEFICIARIO_ACTUAL, 0, getClass());

            BaseTest.createStep("Beneficiarios inscritos " + listaActual, true, crearReporte);
            clickOn(linkSesionInternacionales,"Sesion","Internacionales",0, getClass(),false, crearReporte );
        }
        BaseTest.createStep("Beneficiarios inscritos Internacionales", true, crearReporte);
    }
    protected void formularioEditarBeneficiario(String numProductIBANBeneficiarioAEditar, String numIdentificacion, String direccion, String calle, String correo, String moneda, String destino, String clasificacion, String proposito,boolean crearReporte){

//        String listaActual = "";
//        getText( TITULO_BENEFICIARIOS_INCRITOS, 10, getClass(), false );
//        if (getAttribute(linkSesionInternacionales, 0, "aria-selected", getClass()).equals("false") ){
//
//            listaActual = getText( LINK_LISTA_BENEFICIARIO_ACTUAL, 0, getClass());
//
//            BaseTest.createStep("Beneficiarios inscritos " + listaActual, true, crearReporte);
//            clickOn(linkSesionInternacionales,"Sesion","Internacionales",0, getClass(),false, crearReporte );
//        }
//        BaseTest.createStep("Beneficiarios inscritos Internacionales", true, crearReporte);
        navegarAListaBeneficiarios(crearReporte);

        writeOn(txtBuscar, numProductIBANBeneficiarioAEditar,"Buscar",0, getClass(), crearReporte);
        clickOn(by( XPATH_BTN_EDITAR_ELIMINAR, numProductIBANBeneficiarioAEditar, "edit"),"Icono","Editar",3,getClass(),crearReporte,crearReporte);
        //setFormulario( alias,"NA","NA","NA","NA","NA","NA", crearReporte );
        setFormulario(
                "NA", numIdentificacion, direccion, calle, correo, "NA", moneda,
                "NA", "NA", false, false, "NA",
                "NA", "NA", destino, clasificacion, proposito, crearReporte
                );
    }

    protected void modalEliminarBeneficiario(String numProductIBANBeneficiarioAEliminar,boolean crearReporte){
        navegarAListaBeneficiarios(crearReporte);
        writeOn(txtBuscar, numProductIBANBeneficiarioAEliminar,"Buscar",0, getClass(), crearReporte);
        esperar(1);
        clickOn(by( XPATH_BTN_EDITAR_ELIMINAR, numProductIBANBeneficiarioAEliminar, "delete"),"Icono","Eliminar",3,getClass(),crearReporte,crearReporte);
        getText( MSG_CONFIRMAR_ELIMINAR_BENEFICIARIO,5, getClass(), crearReporte );
    }

    protected void consultarBeneficiario(String numProductIBANBeneficiarioABuscar,boolean crearReporte){
        By beneficiario = by("//div[@aria-hidden='false']  //tr[td[@id='let-accountType']/span[contains(.,'"+numProductIBANBeneficiarioABuscar+"')]]");
        navegarAListaBeneficiarios(crearReporte);
        if (isElementPresent(CARGA_TABLA,1)){
            waitElementInvisible(CARGA_TABLA,0,15, getClass() );
        }
        writeOn( txtBuscar, numProductIBANBeneficiarioABuscar,"Buscar",0, getClass(), crearReporte);
        esperar(0 );
        marcarObjectoPresenteVisble( beneficiario,2, getClass(),"Se visualiza el beneficiario: " + numProductIBANBeneficiarioABuscar, crearReporte);
    }




}
