package pages.configuracion;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.PoAccionesRepetitivas;

import static java.lang.String.format;
import static pages.XpathComunes.BTN_CONTINUAR;
import static pages.XpathComunes.BTN_GUARDAR;
import static pages.configuracion.XpathConfiguracion.textoPantallaActual;
import static pages.configuracion.XpathConfiguracion.tituloConfiguracion;

public class InformacionAccesoSeguridadPage extends BasePage {

    private final Accion accion;
    private InformacionAccesoSeguridadPage(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    public static InformacionAccesoSeguridadPage getPage(){
        return new InformacionAccesoSeguridadPage(BaseTest.getDriver());
    }

    private By expandibleCambiarContrasena = By.xpath("//a[div/div/h4[contains(.,'Cambiar clave de acceso')]]"); // aria-expanded="false"
    private By txtClaveActual = By.xpath("//div[label[contains(.,'Clave de acceso actual')]] /input");
    private By txtNuevaClave = By.xpath("//div[label[contains(.,'Nueva clave de acceso')]] /input");
    private By txtNuevaClaveConfirmar = By.xpath("//div[label[contains(.,'Confirmar nueva clave')]] /input");
    private By barraProgresoSeguridadContrasena = By.xpath("//div[span[contains(.,'Seguridad de la contraseña')]] /div[@class[contains(.,'bhd-change-password-progress')]]");
    private By tituloInformacionAccesoSeguridad = By.xpath("//h3[contains(.,'Información de acceso y seguridad')]");
    private By linkInformacionAccesoSeguridad = By.xpath("//a/span[contains(.,'Información de acceso y seguridad')]");

    private By tituloModalClaveRestablecida = By.xpath("//div[@role='dialog']/div[contains(.,'Clave de acceso restablecida')] ");
    private By iconoMensajeClaveRestablecidaExito = By.xpath("//div[em[@class[contains(.,'check-circle bhd-green')]]]/h3[contains(.,'Tu clave de acceso ha sido modificada con éxito')]");
    private By btnAceptar = By.xpath("//button[@ng-reflect-label='Aceptar']");
    private By btnNo = By.xpath("//button[@ng-reflect-label='No']");

    private By tituloAlgoSalioMal = By.xpath("//div[@role='dialog']/div[contains(.,'Algo salió mal')]");
    private By mensajeClaveActualIncorrecta = By.xpath("//div[em[@class[contains(.,'bhd-orange')]]]/h3[contains(.,'La clave de acceso actual es incorrecta')]");
    private By mensajeContrasenaNoCoinciden = By.xpath("//span[contains(.,'Las contraseñas no coinciden')]");
    private By msgCampoDebeSerNumerico = By.xpath("//span[contains(.,'Este campo debe ser numérico')]");
    private By linkExpandirContraerPreguntasSeguridad = By.xpath("//a[div/div/h4[contains(.,'Preguntas de seguridad')]]");

    private By btnEncenderClaveTransaccionar = By.xpath("//p-inputswitch[@name='allowTransactionWithoutKeyCard' and @ng-reflect-model='true']");
    private By btnApagarClaveTransaccionar = By.xpath("//p-inputswitch[@name='allowTransactionWithoutKeyCard' and @ng-reflect-model='false']");

    /** Navega a la pagina información de acceso y seguridad.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Información de acceso y seguridad
     */
    protected String navegarAInformacionDeAccesoSeguridad(boolean crearReporte){
        accion.isElementPresent(tituloConfiguracion, "CONFIGURACION",3, getClass());
        if (!accion.getText( textoPantallaActual,3, getClass(),false ).equals("Información de acceso y seguridad")){
            accion.getText( textoPantallaActual,2, getClass(), crearReporte);
            accion.clickOn(linkInformacionAccesoSeguridad,"INFORMACIÓN DE ACCESO Y SEGURIDAD",2, getClass(),false,crearReporte);
        }
        return accion.getText(tituloInformacionAccesoSeguridad,3, getClass(), crearReporte);
    }

    /**
     * Despliega la sesión cambiar clave de acceso y permite llenar los diferentes campos.
     * @param claveActual Permite llenar el campo clave de acceso actual.
     * @param claveNueva Permite llenar el campo nueva clave de acceso.
     * @param confirmarClave Permite llenar el campo confirmar nueva clave de acceso.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void pantallaCambiarClaveAcceso(String claveActual, String claveNueva, String confirmarClave, boolean crearReporte){
        navegarAInformacionDeAccesoSeguridad(crearReporte);
        if (accion.getAttribute( expandibleCambiarContrasena,"aria-expanded",3,getClass()).equals("false")){
            accion.clickOn(expandibleCambiarContrasena, "SESIÓN CAMBIAR CLAVE DE ACCESO",2,getClass(),false,crearReporte);
        }
        accion.isElementVisible(barraProgresoSeguridadContrasena, 3, getClass());
        accion.getText(expandibleCambiarContrasena, 1, getClass(), crearReporte);

        accion.writeMaskedOn(txtClaveActual,claveActual,"CAMPO CLAVE ACTUAL",1,getClass(),crearReporte);
        accion.writeMaskedOn(txtNuevaClave,claveNueva,"CAMPO CLAVE NUEVA",1,getClass(),crearReporte);
        accion.writeMaskedOn(txtNuevaClaveConfirmar,confirmarClave,"CAMPO CONFIRMAR CLAVE",1,getClass(),crearReporte);
    }

    /**
     * Presiona el botón para guardar la contraseña, y luego completa el modal de TDC.
     * @param codigoTCD Permite ingresar el código digital.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void guardarContrasena(String codigoTCD,boolean crearReporte){
        accion.clickOn( BTN_GUARDAR, "BOTÓN GUARDAR",1, getClass(), crearReporte, crearReporte);
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().continuarTDC(codigoTCD, crearReporte);
    }

    /** Verifica que se muestre el mensaje cuando la nueva contrasena ingresada no coinciden.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Las contraseñas no coinciden
     */
    protected String msgAlertaContrasenasNoCoinciden(boolean crearReporte){
        return accion.getText( mensajeContrasenaNoCoinciden,1, getClass(), crearReporte);
    }

    /**
     * Verifica que se muestre el mensaje cuando la contraseña no es numérica.
     * @return Este campo debe ser numérico
     */
    protected String msgNuevaContrasenaCampoNumerico(){
        return accion.getText( msgCampoDebeSerNumerico,1, getClass(), true);
    }

    /** Verifica que se muestre el mensaje cuando la contrasena ingresada es diferente a la contrasena actual.
     * @return La clave de acceso actual es incorrecta
     */
    protected String modalClaveActualIncorrecta(){
        String claveIncorrecta = accion.getText(mensajeClaveActualIncorrecta, 1, getClass(), false);
        accion.crearPaso(accion.getText(tituloAlgoSalioMal,2,getClass(),false) + "<br>" + claveIncorrecta, true, true, true);
        return claveIncorrecta;
    }

    /**
     * Verifica que se muestre el modal cuando se cambia la contraseña y permite con un booleano cerrar o no la sesión luego de cambiar la contraseña.
     * @param cerrarSesion true para cerrar sesión y false para mantener la sesión.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Clave de acceso restablecida
     */
    protected String modalClaveRestablecida(boolean cerrarSesion,boolean crearReporte){
        String claveModificada = accion.getText( tituloModalClaveRestablecida,20, getClass(), false);
        accion.crearPaso(claveModificada +  ".<br>"+ accion.getText(iconoMensajeClaveRestablecidaExito,1, getClass(),false), true, crearReporte, crearReporte);
        if (cerrarSesion){
            accion.clickOn( btnAceptar, "BOTON ACEPTAR",1, getClass(),false, crearReporte);
        }else {
            accion.clickOn( btnNo, "BOTON NO",1, getClass(),false, crearReporte);
        }
        return claveModificada;
    }


    private By labelMsgPreguntasServiranAutenticarse = By.xpath("//h4[contains(.,'Estas servirán para autenticarte al momento de acceder a la plataforma desde un dispositivo')]");

    protected void expandirPantallaPreguntaSeguridad(boolean crearReporte){
        navegarAInformacionDeAccesoSeguridad(crearReporte);
        if (accion.getAttribute( linkExpandirContraerPreguntasSeguridad,"aria-expanded",3,getClass()).equals("false")){
            accion.clickOn(linkExpandirContraerPreguntasSeguridad, "SESIÓN PREGUNTAS DE SEGURIDAD",2,getClass(),false, crearReporte);
        }
        accion.isElementVisible(comboPreguntaUno, 3, getClass());
        accion.getText(labelMsgPreguntasServiranAutenticarse, 1, getClass(), crearReporte);
        //accion.isElementVisible(labelMsgPreguntasServiranAutenticarse, 3,getClass());
    }

    private String xpathComboPregunta = "//div[label[contains(.,'%s')]] /p-dropdown//span";
    private String xpathTxtTuPregunta = "//div[label[contains(.,'%s')]] //input[@placeholder='Digita tu pregunta']";
    private String xpathTxtTuRespuesta = "//div[label[contains(.,'%s')]] //input[@placeholder='Digita tu respuesta']";

    private By comboPreguntaUno = By.xpath(format(xpathComboPregunta,"Primera pregunta"));
    private By comboPreguntaDos = By.xpath(format(xpathComboPregunta,"Segunda pregunta"));
    private By comboPreguntaTres = By.xpath(format(xpathComboPregunta,"Tercera pregunta"));
    private By comboPreguntaCuatro = By.xpath(format(xpathComboPregunta,"Cuarta pregunta"));
    private By comboPreguntaCinco = By.xpath(format(xpathComboPregunta,"Quinta pregunta"));

    private By txtTuPreguntaUno = By.xpath(format(xpathTxtTuPregunta,"Primera pregunta"));
    private By txtTuPreguntaDos = By.xpath(format(xpathTxtTuPregunta,"Segunda pregunta"));
    private By txtTuPreguntaTres = By.xpath(format(xpathTxtTuPregunta,"Tercera pregunta"));
    private By txtTuPreguntaCuatro = By.xpath(format(xpathTxtTuPregunta,"Cuarta pregunta"));
    private By txtTuPreguntaCinco = By.xpath(format(xpathTxtTuPregunta,"Quinta pregunta"));

    private By txtTuRespuestaUno = By.xpath(format(xpathTxtTuRespuesta,"Primera pregunta"));
    private By txtTuRespuestaDos = By.xpath(format(xpathTxtTuRespuesta,"Segunda pregunta"));
    private By txtTuRespuestaTres = By.xpath(format(xpathTxtTuRespuesta,"Tercera pregunta"));
    private By txtTuRespuestaCuatro = By.xpath(format(xpathTxtTuRespuesta,"Cuarta pregunta"));
    private By txtTuRespuestaCinco = By.xpath(format(xpathTxtTuRespuesta,"Quinta pregunta"));

    public void setPreguntaSeguridadUno(String pregunta, String preguntaPropia, String respuesta, boolean crearReporte){
        accion.selectDropdownIBP(comboPreguntaUno, pregunta,"PRIMERA PREGUNTA",3, getClass(), crearReporte);
        if (pregunta.contains("Pregunta propia")){
            accion.writeOn(txtTuPreguntaUno, preguntaPropia,"CAMPO PRIMERA PREGUNTA PROPIA",3, getClass(), crearReporte);
        }
        accion.writeOn(txtTuRespuestaUno, respuesta,"CAMPO PRIMERA RESPUESTA",3, getClass(), crearReporte);
    }

    public void setPreguntaSeguridadDos(String pregunta, String preguntaPropia, String respuesta, boolean crearReporte){
        accion.selectDropdownIBP(comboPreguntaDos, pregunta,"SEGUNDA PREGUNTA",3, getClass(), crearReporte);
        if (pregunta.contains("Pregunta propia")){
            accion.writeOn(txtTuPreguntaDos, preguntaPropia,"CAMPO SEGUNDA PREGUNTA PROPIA",3, getClass(), crearReporte);
        }
        accion.writeOn(txtTuRespuestaDos, respuesta,"CAMPO SEGUNDA RESPUESTA",3, getClass(), crearReporte);
    }

    public void setPreguntaSeguridadTres(String pregunta, String preguntaPropia, String respuesta, boolean crearReporte){
        accion.selectDropdownIBP(comboPreguntaTres, pregunta,"TERCERA PREGUNTA",3, getClass(), crearReporte);
        if (pregunta.contains("Pregunta propia")){
            accion.writeOn(txtTuPreguntaTres, preguntaPropia,"CAMPO TERCERA PREGUNTA PROPIA",3, getClass(), crearReporte);
        }
        accion.writeOn(txtTuRespuestaTres, respuesta,"CAMPO TERCERA RESPUESTA",3, getClass(), crearReporte);
    }

    public void setPreguntaSeguridadCuatro(String pregunta, String preguntaPropia, String respuesta, boolean crearReporte){
        accion.selectDropdownIBP(comboPreguntaCuatro, pregunta,"CUARTA PREGUNTA",3, getClass(), crearReporte);
        if (pregunta.contains("Pregunta propia")){
            accion.writeOn(txtTuPreguntaCuatro, preguntaPropia,"CAMPO CUARTA PREGUNTA PROPIA",3, getClass(), crearReporte);
        }
        accion.writeOn(txtTuRespuestaCuatro, respuesta,"CAMPO CUARTA RESPUESTA",3, getClass(), crearReporte);
    }

    public void setPreguntaSeguridadCinco(String pregunta, String preguntaPropia, String respuesta, boolean crearReporte){
        accion.selectDropdownIBP(comboPreguntaCinco, pregunta,"QUINTA PREGUNTA",3, getClass(), crearReporte);
        if (pregunta.contains("Pregunta propia")){
            accion.writeOn(txtTuPreguntaCinco, preguntaPropia,"CAMPO QUINTA PREGUNTA PROPIA",3, getClass(), crearReporte);
        }
        accion.writeOn(txtTuRespuestaCinco, respuesta,"CAMPO QUINTA RESPUESTA",3, getClass(), crearReporte);
    }



    public void setPreguntaPropiaUno( String preguntaPropia, String respuesta, boolean crearReporte){
        setPreguntaSeguridadUno("Pregunta propia",preguntaPropia, respuesta, crearReporte);
    }

    public void setPreguntaPropiaDos(String preguntaPropia, String respuesta, boolean crearReporte){
        setPreguntaSeguridadDos("Pregunta propia",preguntaPropia, respuesta, crearReporte);
    }
    public void setPreguntaPropiaTres(String preguntaPropia, String respuesta, boolean crearReporte){
        setPreguntaSeguridadTres("Pregunta propia",preguntaPropia, respuesta, crearReporte);
    }
    public void setPreguntaPropiaCuatro(String preguntaPropia, String respuesta, boolean crearReporte){
        setPreguntaSeguridadCuatro("Pregunta propia",preguntaPropia, respuesta, crearReporte);
    }

    public void setPreguntaPropiaCinco(String preguntaPropia, String respuesta, boolean crearReporte){
        setPreguntaSeguridadCinco("Pregunta propia",preguntaPropia, respuesta, crearReporte);
    }

    public void guardarPreguntaSeguridad(String codigoTCD, boolean crearReporte){
        accion.clickOn(BTN_CONTINUAR,"BONTON GUARDAR",1,getClass(),crearReporte,crearReporte);
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().continuarTDC(codigoTCD, crearReporte);
        // Esperar modal. Se debe levantar un modal, expresando el guardado correcto de las preguntas de seguridad.
    }





    protected void deshabilitarClaveTransaccionar(String codigoTCD,boolean crearReporte){
        navegarAInformacionDeAccesoSeguridad(crearReporte);
        accion.clickOn( btnEncenderClaveTransaccionar , "BOTON DESHABILITAR CLAVE TRANSACCIONAR",3,getClass(),false,crearReporte);
        //accionesRepetitivasPage.confirmarTDC( codigoTCD, crearReporte);
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarConfirmarTDC(codigoTCD, crearReporte);
        //accion.crearPaso("CLAVE TRANSACCION APAGADA", accion.isElementVisible(btnApagarClaveTransaccionar,2, getClass()), crearReporte, crearReporte);
    }

}
