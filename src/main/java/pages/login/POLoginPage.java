package pages.login;

import accion.Accion;
import data.Persistencia;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.Utilidad;

import java.util.UUID;

import static pages.login.LoginXpath.*;

public class POLoginPage extends Accion {

    private String documentNumber = "";
    private String identificador = "";
    private POLoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    public static POLoginPage getInstance(WebDriver driver){
        return new POLoginPage(driver);
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getIdentificador() {
        return identificador;
    }

    private String xpBtnEntrar = "//p-button[@ng-reflect-loading='%s'] / button[span[contains(.,'Entrar')] ]"; // false o true

    public String getPantallaLogin(boolean crearReporte){
        String texto = "Internet Banking " + getText( tituloLabel,25, getClass(), false );
        crearPaso( texto,true, crearReporte, crearReporte );
        return texto;
    }

    private void navegarIBP(boolean crearReporte){
        navegar(SQA_URL);
        getPantallaLogin(crearReporte);
    }

    public POForgotPassword clickEnOlvidasteTuClaveDeAcceso(boolean crearReporte){
        navegarIBP(crearReporte);
        clickOn(By.xpath("//span[contains(.,'¿Olvidaste tu clave de acceso?')]"),"Link ¿Olvidaste tu clave de acceso?",1,getClass(),false,crearReporte);
        System.out.println("Ya se ");
        return new POForgotPassword(getDriver());
    }
    private void setFormulario(String usuario, String contrasena, boolean crearReporte){
        identificador = usuario + UUID.randomUUID();
        documentNumber = usuario;
        navegarIBP(crearReporte);
        writeOn( txtUsuario, usuario,"CAMPO USUARIO",2,getClass(),crearReporte);
        writeMaskedOn( txtContrasena, contrasena,"CAMPO CONTRASENA",1, getClass(), crearReporte);
        clickOn( btnEntrar,"BOTON ENTRAR",1, getClass(), crearReporte, crearReporte);
    }

    public POValidacionAccesoPage iniciarSesion(String usuario, String contrasena, boolean crearReporte){
        //ApiLoginAutomatizacion login = ApiLoginAutomatizacion.getInstance().login(usuario,contrasena);
        //System.out.println("Es primer inicio: " + login.isFirstLogin());
        //Persistencia.getInstance().newQueryClientInfoV2(usuario);

        Persistencia.getInstance().setLoginApi(usuario, contrasena);

        setFormulario(usuario, contrasena, crearReporte);
        // isElementInvisibility( Utilidad.by(String.format(xpBtnEntrar, "true")),40, getClass() ); // Me esta producciendo un error

        Persistencia.getInstance().setClientInfo(usuario);
        Persistencia.getInstance().setClientInfo(usuario, getDriver());

        //System.out.println("Desaparecion el invisible");
        //System.out.println("Tipo de validacion: " + ApiLoginAutomatizacion.getInstance().login(usuario,contrasena).getTipoValidacion());
        //System.out.println(Persistencia.getInstance().getClientInfo(usuario).getApellido());
        System.out.println("Driver en login:" + getDriver());
        return new POValidacionAccesoPage(getDriver(), this);
    }

    public POLoginPage loginValidacion(String usuario, String contrasena, boolean crearReporte){
        setFormulario(usuario, contrasena, crearReporte);
        return this;
    }
    public String getMsgCampoRequerido(String labelCampo, boolean capturarPantalla){
        return getText(By.xpath("//div[label[contains(.,'"+labelCampo+"')]]/div/span [contains(.,'Campo requerido')]"),1, getClass(), capturarPantalla);
    }

    public String getMsgCredencialesInvalidas(boolean crearReporte){
        return getText(By.xpath("//span[contains(.,'Usuario o contraseña inválidos') ]"), 30,getClass(),crearReporte);
    }

    public String getMsgCaracteresEspeciales(boolean crearReporte){
       return getText(By.xpath("//div[label[contains(.,'Usuario')]]/div/span [contains(.,'No se permiten caracteres especiales')]"),1, getClass(), crearReporte);
    }
}
