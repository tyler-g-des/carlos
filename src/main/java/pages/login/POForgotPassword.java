package pages.login;

import accion.AccionPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static accion.XpathComunes.*;

public class POForgotPassword extends AccionPage {

    public POForgotPassword(WebDriver webDriver) {
        super(webDriver);
    }

    private final By tituloRestaurarClaveAcceso = by("//h3[contains(.,'Restaurar clave de acceso')]");
    private static final String XP_SUB_TITULO = "//li[@class[contains(.,'p-steps-current')] ]//span[contains(.,'%s')]";
    private final By txtNumeroDocumento = By.xpath("//div[label[contains(.,'Número de documento')] ] //p-inputmask[@placeholder='xxx-xxxxxxx-x']/input");
    private final By txtNumeroDocumentoMascara = By.xpath("//div[label[contains(.,'Número de documento')] ] //p-inputmask[@placeholder='xxx-xxxxxxx-x' and @class[contains(.,'focus') ] ]/input");
    private final By btnCancelar = by("//button[span[contains(.,'Cancelar')]]");
    private final By btnValidar = By.xpath("//button[span[contains(.,'Validar')]]");


    private final By txtNuevaClave = by("//div[label[contains(.,'Nueva clave de acceso')] ]//input");
    private final By txtConfirmarClave = by("//div[label[contains(.,'Confirmar nueva clave')] ]//input");
    private final By btnContinuar = By.xpath("//button[contains(.,'Continuar')]");
    private final By txtCodigoValidacion = By.xpath("//div[label[contains(.,'Código de validación')] ]//input");
    private final By labelTiempoOTP = By.xpath("//span[contains(.,'Reenviar código -')] /strong [contains(.,'59')]");



    private final static String XPATH_TXT = "//div[label[contains(.,'%s')] ] //input"; //Nueva clave de acceso | Confirmar nueva clave





    private void formularioValidarUsuario(String numeroDocumento,boolean crearReporte){
        getText( tituloRestaurarClaveAcceso,3, getClass(), crearReporte );
        waitElementVisible( by(XP_SUB_TITULO, "Validar usuario" ),0, getClass() );

        clickOn( txtNumeroDocumento, 0, getClass() );
        clickOn( txtNumeroDocumentoMascara, 0, getClass() );
        writeOn( txtNumeroDocumentoMascara, numeroDocumento,"Número de documento",0, getClass(), crearReporte);

       // System.out.println(getText(by("//div[label[contains(.,'Número de documento')] ] //input"),0, getClass(),false));
    }

    public POForgotPassword verificarPestanaValidarUsuario(String numeroDocumento, boolean crearReporte){
        formularioValidarUsuario( numeroDocumento , crearReporte );
        return this;
    }

    public POForgotPassword verificarPestanaConfiguracionDeAcceso(String numeroDocumento,String nuevaContrasena, String confirmarContrasena, boolean crearReporte){
        formularioValidarUsuario( numeroDocumento, crearReporte );
        clickOn( btnValidar,"Boton","Validar",1,false, getClass(), crearReporte, crearReporte);


        getText( by(XP_SUB_TITULO, "Configuración de acceso" ),5, getClass(),crearReporte);
        waitElementVisible( tituloRestaurarClaveAcceso,0, getClass() );

        writeOn( txtNuevaClave, nuevaContrasena,"Nueva clave de acceso",0, getClass(), crearReporte );
        writeOn( txtConfirmarClave, confirmarContrasena,"Confirmar nueva clave",0, getClass(), crearReporte );
        clickOn(by("//label[contains(.,'Nueva clave de acceso')]"),0, getClass());
        return this;
    }

    public POForgotPassword verificarPestanaValidarOTP(String numeroDocumento, String nuevaContrasena, String confirmarContrasena, String codigoOTP, boolean crearReporte){
        verificarPestanaConfiguracionDeAcceso( numeroDocumento, nuevaContrasena, confirmarContrasena, crearReporte);
        clickOn(btnContinuar,"Boton","Continuar",1,false, getClass(), crearReporte, crearReporte );

        getText( by(XP_SUB_TITULO, "Validar OTP" ),15, getClass(), crearReporte );
        waitElementVisible( tituloRestaurarClaveAcceso,0, getClass() );
        writeOn( txtCodigoValidacion, codigoOTP,"Código de validación",0, getClass(), crearReporte );
        return this;
    }


    /**
     *
     * @param timer 04:52
     */
    public String getTiempoExpiracionOTP(String timer, boolean crearReporte){
        By labelTiempoExpiracion = by("//div[label[contains(.,'Código de validación')] ] /span[contains(.,'Reenviar código')] /strong[contains(.,'"+timer+"')]");
        return getText( labelTiempoExpiracion, 5, getClass(), crearReporte );
    }




    public String cancelarValidarUsuario(String numeroDocumento, boolean noCancelar, boolean siCancelar, boolean crearReporte){
        formularioValidarUsuario( numeroDocumento, crearReporte );
        clickOn(btnCancelar,"Boton","Cancelar",0,false, getClass(), crearReporte, crearReporte);
        return modalConfirmarOperacion( noCancelar, siCancelar, crearReporte);
    }
    private String modalConfirmarOperacion(boolean noCancelar, boolean siCancelar, boolean crearReporte){
        String titulo = "";
        waitElementVisible(tituloModalConfirmarOperacion, 2, getClass());
        getText(preguntaSeguroCancelarOperacion,0, getClass(), crearReporte);
        if (noCancelar){
            clickOn(btnNoModal, "Boton","No",2,true, getClass(),false, crearReporte);
            titulo = getText(by("//h3[contains(.,'Restaurar clave de acceso')]"), 1, getClass(), crearReporte);

        }else {
            if (siCancelar){
                clickOn(btnSiModal, "Boton","Sí",0, false, getClass(),false, crearReporte);
                titulo = POLoginPage.getInstance( getDriver()).getPantallaLogin( crearReporte );
            }
        }
        return titulo;
    }

    public void formularioConfiguracionAcceso(String nuevaClave, String confirmarClave, boolean crearReporte){
        writeOn( txtNuevaClave, nuevaClave,"Número de documento",0, getClass(), crearReporte);
        writeOn( txtConfirmarClave, confirmarClave,"Número de documento",0, getClass(), crearReporte);
    }

    /**
     *
     * @param textOfTheElementName <br>Número de documento <br>Nueva clave de acceso <br>Confirmar nueva clave<br>
     * @param tomarCaptura
     * @return {@link String} <br>Campo requerido <br>Mínimo 11 caracteres <br>Mínimo 4 caracteres
     */
    public String getMsgValidarCampo(String textOfTheElementName, boolean tomarCaptura){
        return getText(by("//div[label[contains(.,'"+textOfTheElementName+"')] ]//span"),1, getClass(), tomarCaptura);
    }

    private By btnContinuarDeshabilitado = By.xpath("//button[span[contains(.,'Continuar')] and @disabled]");
    public void botonContinuarDebeEstarDeshabilidado(){
        waitElementNotVisibleOrNotClickCable(btnContinuarDeshabilitado, 0, getClass());
    }









}
