package pages.login;

import accion.Accion;
import basetest.BaseTest;
import data.Persistencia;
import microservicios.MSClientInfoV2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.dashboard.DashboardLogic;
import pages.validacionacceso.POValidacionAcceso;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import static pages.login.LoginXpath.*;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 12/11/2023 7:20 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class POLogin extends BasePage {
    private static final String SQA_URL = "https://ibp-sqa.web.noprod.cfbhd.com/#/login";
    private Accion accion;
    private WebDriver driver = null;
    private POLogin(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        accion = Accion.getAcciones();
    }

    /** Retorna una nueva instancia de esta clase.
     * @return LoginPage
     */
    public static POLogin getInstance(){
        return new POLogin(BaseTest.getDriver());
    }

    public WebDriver getDriver() {
        return driver;
    }

    private By tituloAccediendoAlCanal = By.xpath("//h3[contains(.,'Accediendo al canal')]");

    /** Este método sirve para verificar que se navego a la pantalla de login IBP.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Internet Banking Personal
     */
    public String pantallaLogin(boolean crearReporte){
        String texto = "Internet Banking " + accion.getText( tituloLabel,5, getClass(), false );
        accion.crearPaso( texto,true, crearReporte, crearReporte );
        return texto;
    }

    /** Completa el formulario de inicio de sesión y toma una captura antes de presionar el botón entrar.
     * @param usuario Numero de documento del usuario. Obligatorio.
     * @param contrasena Contraseña del usuario.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void setFormLogin(String usuario, String contrasena, boolean crearReporte){
        accion.navegar(SQA_URL);
        pantallaLogin(crearReporte);
        accion.writeOn( txtUsuario, usuario,"CAMPO USUARIO",2,getClass(),crearReporte);
        accion.writeMaskedOn( txtContrasena, contrasena,"CAMPO CONTRASENA",1, getClass(), crearReporte);
        accion.clickOn( btnEntrar,"BOTON ENTRAR",1, getClass(), crearReporte, crearReporte);
    }

    public POValidacionAcceso login(String usuario, String contrasena, boolean crearReporte){
        consultarClintInfo(usuario, crearReporte);
        //pageLogin.consultarClintInfo(usuario, crearReporte);
        setFormLogin(usuario, contrasena, crearReporte);
        accion.isElementInvisibility(btnEntrarDeshabilitado,35, getClass());
        //accion.isElementInvisibility(txtUsuario, 1, getClass());
        return new POValidacionAcceso(driver);
    }

    private By btnEntrarDeshabilitado = By.xpath("//button[@class[contains(.,'loading')] and span[contains(.,'Entrar')]]");
    protected void iniciarSesion(String usuario, String contrasena, boolean crearReporte){
        setFormLogin( usuario, contrasena, crearReporte);
        accion.isElementInvisibility(txtUsuario, 40, getClass());
        //accion.isElementInvisibility(btnEntrar, 5, getClass());
        accion.isElementInvisibility(tituloAccediendoAlCanal, 30, getClass());
    }

    public DashboardLogic inicioSesionGenerico2(String usuario, String contrasena, boolean crearReporte){
        iniciarSesion(usuario, contrasena, crearReporte);
        new POValidacionAcceso(driver).validarAccesoOpcionDelMomento(crearReporte);
        return DashboardLogic.getLogica();
    }

    protected void validarAcceso(boolean crearReporte){
        POValidacionAcceso validacion = new POValidacionAcceso(driver);
        validacion.validarAcceso(crearReporte);
    }

    /**
     * <h1>Eliminar metodo</h1>
     * @param usuario
     * @param crearReporte
     */
    protected void consultarClintInfo(String usuario, boolean crearReporte){
        Persistencia.getInstance().newQueryClientInfoV2(usuario);
        //Persistencia.getInstance().newQueryClientInfo(usuario);
        String documento = Persistencia.getInstance().getDocumentNumber("Placida Solimans");
        System.out.println(documento);
        if (new MSClientInfoV2(usuario).newQuery().aceptarConsentimientoUsoInformacion()){

            //pageLogin.aceptarContratoCompartirInformacion(crearReporte);
            //aceptarContratoCompartirInformacion(crearReporte); Esta dando error
            if (new MSClientInfoV2(usuario).newQuery().aceptarConsentimientoUsoInformacion()){
                BaseTest.createStep("No se esta guardado la acceptacion del contrato de uso de informacion",false,false);
            }

        }
    }

    private void contenidoContratoConsentimiento(boolean crearReporte){
        By tituloContrato = By.xpath("//h3[contains(.,'Compartir información')]");
        By subtituloParteContenido = By.xpath("//span[@id='ibp-contract' and //u[contains(.,'COMPARTIR INFORMACIONES')] and p[contains(.,'El Suscrito,')] and p[contains(.,'a) Compartir sus datos')] and p[contains(.,'b) Solicitar y recibir')] ] ");
        accion.getText(tituloContrato,20,getClass(),crearReporte);
        accion.isElementPresent(subtituloParteContenido, "Contenido contrato",1,getClass());
    }

    public void aceptarContratoCompartirInformacion(boolean crearReporte){
        By btnSiAcepto = By.xpath("//p-button/button[span[contains(.,'Si acepto')]]");
        contenidoContratoConsentimiento(crearReporte);
        accion.clickOn(btnSiAcepto,"BOTON SI ACEPTO",1, getClass(),crearReporte, crearReporte);
    }

    /** Verifica que se muestre mensaje de Usuario o contraseña inválidos, toma captura y retorna dicho mensaje.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Usuario o contraseña inválidos
     */
    protected String verificarMsgCredencialesIncorrectas(boolean crearReporte){
        return accion.getText( labelCredencialesInvalidas, 15, getClass(), crearReporte);
    }

    /** Verifica que se muestre el label indicando que no se permiten caracteres especiales.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return No se permiten caracteres especiales
     */
    protected String verificarMsgNoCaracteresEspeciales(boolean crearReporte){
        return accion.getText(LABEL_NO_CARACTERES_ESPECIALES,1, getClass(), crearReporte);
    }

    /** Verifica que el campo usuario es obligatorio.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Campo requerido
     */
    protected String campoUsuarioRequerido(boolean crearReporte){
        return accion.getText( labelUsuarioRequerido,1, getClass(), crearReporte);
    }

    /** Verifica que el campo Contraseña es obligatorio.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Campo requerido
     */
    protected String campoContrasenaRequerido(boolean crearReporte){
        return accion.getText( labelContrasenaRequerida,1, getClass(), crearReporte);
    }

    protected void clickLinkOlvideMiContrasena(boolean crearReporte){
        driver.get(SQA_URL);
        pantallaLogin(crearReporte);
        accion.clickOn(linkOlvidasteTuClave,"¿OLVIDASTE TU CLAVE DE ACCESO?",5,getClass(),false,crearReporte);
    }

}
