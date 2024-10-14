package pages.login.restaurarclave;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import static microservicios.Utilidad.by;
import static pages.XpathComunes.BTN_CONTINUAR;

public class PoRestaurarClaveAcceso extends BasePage {

    private Accion accion;
    private PoRestaurarClaveAcceso(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    public static PoRestaurarClaveAcceso getPagina(){
        return new PoRestaurarClaveAcceso(BaseTest.getDriver());
    }
    private By labelTituloRestaurarClaveDeAcceso = By.xpath("//h3[contains(.,'Restaurar clave de acceso')]");
    private By labelTituloValidarUsuario = By.xpath("//li[@class[contains(.,'p-highlight p-steps-current')]]/a/span[contains(.,'Validar usuario')]");
    private By labelTituloConfiguracionAcceso = By.xpath("//li[@class[contains(.,'p-highlight p-steps-current')]]/a/span[contains(.,'Configuración de acceso')]");
    private By labelTituloValidarOTP = By.xpath("//li[@class[contains(.,'p-highlight p-steps-current')]]/a/span[contains(.,'Validar OTP')]");
    private By txtNumeroDocumento = By.xpath("//p-inputmask[@placeholder='xxx-xxxxxxx-x']/input");
    private By btnValidar = By.xpath("//button[span[contains(.,'Validar')] and @ng-reflect-loading='false']");
    private By txtNuevaClave = By.xpath("//div[label[contains(.,'Nueva clave de acceso')]]/input");
    private By txtConfirmarNuevaClave = By.xpath("//div[label[contains(.,'Confirmar nueva clave')]]/input");
    private By txtOTP = By.xpath("//div[label[contains(.,'Código de validación')]]/input");
    private By contadorOTP = By.xpath("//strong[contains(.,'34:59')]"); // 04:59 5 minuo

    /** Captura la pantalla de validar usuario y permite llenar el campo numero documento.
     * @param numeroDocumento Numero de documento valido.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void pantallaValidarUsuario(String numeroDocumento,boolean crearReporte){
        String mensje = accion.getText( labelTituloRestaurarClaveDeAcceso,1, getClass(),false )+": "
                + accion.getText( labelTituloValidarUsuario,1, getClass(),false);
        accion.crearPaso( mensje,true, crearReporte, crearReporte );
        accion.writeOn( txtNumeroDocumento, numeroDocumento,"NUMERO DE DOCUMENTO",2, getClass(), crearReporte);
    }

    /** Captura la pantalla de Configuración de acceso y permite llenar los campos nueva contraseña y confirmar nueva contraseña.
     * @param nuevaClave Nueva clave por la cual se cambiara la clave actual.
     * @param confirmarNuevaClave Confirmar la nueva clave introducida.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void pantallaConfiguracionDeAcceso(String nuevaClave, String confirmarNuevaClave, boolean crearReporte){
         String mensje = accion.getText( labelTituloRestaurarClaveDeAcceso,1, getClass(),false )+": "
                 + accion.getText( labelTituloConfiguracionAcceso,3, getClass(),false);
                   accion.crearPaso( mensje,true, crearReporte, crearReporte );
         accion.writeMaskedOn( txtNuevaClave, nuevaClave,"CAMPO NUEVA CLAVE",2, getClass(), crearReporte);
         accion.writeMaskedOn( txtConfirmarNuevaClave, confirmarNuevaClave,"CAMPO CONFIRMAR CONTRASENA",1, getClass(), crearReporte);
    }

    private By labelCampoRequerido = By.xpath("//div/span[ contains(.,'Campo requerido')]");
    private By labelMinimoCaracteres = By.xpath("//div/span[ contains(.,'Mínimo 4 caracteres')]");
    private By labelContrasenaNoCoinciden = By.xpath("//div/span[ contains(.,'Las contraseñas no coinciden')]");
    private By labelCampoDebeSerNumerico = By.xpath("//div/span[ contains(.,'Este campo debe ser numérico')]");
    private By labelMsgErrorGenerico = By.xpath("//div/span[@class[contains(.,'error')]]");



    private void clickEnTituloPagina(){
        accion.clickOn(labelTituloRestaurarClaveDeAcceso, "RESTAURAR CLAVE DE ACCESO",1,getClass(),false,false);
    }

    protected String getMsgErrorGenericoNumeroDocumento(boolean crearReporte){
        clickEnTituloPagina();
        return accion.getText(labelMsgErrorGenerico,1, getClass(), crearReporte);
    }
    protected String getMsgErrorContrasenaNoCoinciden(boolean crearReporte){
        return accion.getText( labelContrasenaNoCoinciden,1, getClass(), crearReporte);
    }

    protected String getMsgErrorCampoRequerido(boolean crearReporte){
        clickEnTituloPagina();
        return accion.getText( labelCampoRequerido,1, getClass(), crearReporte);
    }

    protected String getMsgErrorMinimoCaracteres(boolean crearReporte){
        return accion.getText( labelMinimoCaracteres,1, getClass(), crearReporte);
    }

    protected String getMsgErrorCampoDebeSerNumerico(boolean crearReporte){
        return accion.getText(labelCampoDebeSerNumerico,1, getClass(), crearReporte);
    }

    /** Permite captura la pantalla validar OTP y llenar el campo código de validación.
     * @param codigoOTP Código OTP a introducir en el campo.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void pantallaValidarOTP(String codigoOTP,boolean crearReporte){
        String mensje = accion.getText( labelTituloRestaurarClaveDeAcceso,1, getClass(),false )+": "
                + accion.getText( labelTituloValidarOTP,1, getClass(),false);
        accion.crearPaso( mensje,true, crearReporte, crearReporte );
        accion.writeMaskedOn( txtOTP, codigoOTP,"CAMPO CODIGO DE VALIDACION",2, getClass(), crearReporte);
    }

    /** Retorna el conteo regresivo 04:59 al envíar el código OTP.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return 04:59
     */
    protected String obtenerTextoDeConteoRegresivoOTP(String conteo, boolean crearReporte){
        return accion.getText( by("//strong[contains(.,'"+conteo+"')]"),8,getClass(), crearReporte);
    }

    /**
     * Presiona el botón validar, lo que debe enviar a la pantalla configuración de acceso si el numero de documento es valido.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void botonValidarDocumento(boolean crearReporte){
        accion.clickOn( btnValidar,"BOTON VALIDAR",1, getClass(), crearReporte, crearReporte);
    }

    /** Presiona el botón validar, lo que debe enviar a la pantalla Validar OTP.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void botonContinuar(boolean crearReporte){
        accion.clickOn( BTN_CONTINUAR,"BOTON CONTINUAR",1, getClass(), crearReporte, crearReporte);
    }
}