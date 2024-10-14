package pages.login;

import accion.Accion;
import data.GenerarPreguntasSeguridad;
import data.Persistencia;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.Utilidad;

import static java.lang.String.format;

public class POFirstLoginPage extends Accion {

    private POValidacionAccesoPage pageValidacionAcceso;

    private final String xpathCombo = "//div[label[contains(.,'%s pregunta')]]  /p-dropdown//span";
    private final String xpathCampoPregunta = "//div[label[contains(.,'%s pregunta')]]  //input[@placeholder='Digita tu pregunta']";
    private final String xpathCampoRespuesta = "//div[label[contains(.,'%s pregunta')]]  //input[@placeholder='Digita tu respuesta']";


    private By txtCodigoTDC = By.xpath("//div[div[h4[contains(.,'Ingresa el código solicitado de tu tarjeta de claves:')]] ] //form //input[@placeholder='XXXX']");
    private By tituloTarjetaDeClaves = By.xpath("//ibp-common-modal//div[@role='dialog'] //div[contains(.,'Tarjeta de claves')]");
    private By btnRealizarTransaccion = By.xpath("//ibp-common-modal//button[span[contains(.,'Realizar transacción')]]");
    private By btnContinuarModal = By.xpath("//ibp-common-modal [//div[@role='dialog'] and //div[contains(.,'Tarjeta de claves')]] //button[span[contains(.,'Continuar')]]");



    private By tituloAccediendoAlCanal = By.xpath("//h3[contains(.,' Configuración de acceso')]");
    private String xpahtMsgHola = "//h4[contains(.,'Hola %S, por favor confirma tu información')]";
    private By txtCorreo = By.xpath("//div[label[contains(.,'Correo electrónico')] ] /input");
    private By btnEditarCorreo = By.xpath("//strong/div[contains(.,' Editar')]");
    private By btnGuardar = By.xpath("//strong/div[contains(.,' Guardar')]");
    private By btnContinuar = By.xpath("//button[@data-pc-name='button' and span[contains(.,'Continuar')]]");


    private By msgEstasPreguntasServiran = By.xpath("//h4[contains(.,'Estas servirán para autenticarte al momento de acceder a la plataforma desde un dispositivo')]");


    /**
     * Este método permite crear una nueva instancia de la clase e instanciar un objeto de la clase: {@link POValidacionAccesoPage}
     * @param webDriver
     * @param pageValidacionAcceso
     */
    public POFirstLoginPage(WebDriver webDriver, POValidacionAccesoPage pageValidacionAcceso) {
        super(webDriver);
        this.pageValidacionAcceso = pageValidacionAcceso;
    }

    /**
     * Este método configura el correo electrónico de un usuario de primer inicio de sesión y presiona continuar para pasa a la pantalla de configuración de preguntas de seguridad.
     * @param correo
     * @param crearReporte
     */
    public void configurarCorreoContinuar(String  correo, boolean crearReporte){
        setFormularioConfigurarCorreoFirstLogin(correo, (!correo.equalsIgnoreCase("NA") ), crearReporte);
        if (!correo.equalsIgnoreCase("NA") && !correo.equalsIgnoreCase("")){
            tdcContinuar(crearReporte);
        }
        clickOn(btnContinuar, "BOTON CONTINUAR FORMULARIO",10, getClass(), crearReporte, crearReporte);
    }

    public void clickBotonContinuar(boolean crearReporte){
        clickOn(btnContinuar, "BOTON CONTINUAR FORMULARIO",10, getClass(), crearReporte, crearReporte);
    }

    /**
     * Este método completa el formulario de configuración de correo para usuario de primer inicio de sesión.
     * @param correo
     * @param guardar
     * @param crearReporte
     */
    private void setFormularioConfigurarCorreoFirstLogin(String correo, boolean guardar, boolean crearReporte){
        String msg = getText( tituloAccediendoAlCanal,10, getClass(), false) + "<br><br>";
        msg = msg + getText( Utilidad.by( format(xpahtMsgHola, Persistencia.getInstance().getClientInfo( pageValidacionAcceso.getNumeroDocumento() ).getNombreCompleto())),1, getClass(), false);
        crearPaso( msg,true, crearReporte, crearReporte);
        if (correo.equalsIgnoreCase("NA")){            // No se edita

        }else {                                                    // Si se edita.
            clickOn( btnEditarCorreo,"BOTON EDITAR",1, getClass(),false, crearReporte);
            writeOn( txtCorreo, correo,"Correo Electronico",1, getClass(), crearReporte);
            if (guardar){
                clickOn( btnGuardar,"BOTON GUARDAR",1, getClass(), crearReporte, crearReporte);
            }
        }
        crearPaso("Pantalla",  true, true, true);
    }



//    private void configurarPrimeraPregunta( boolean completar, boolean preguntaPropia, boolean crearReporte){
//
//        if (preguntaPropia){
//
//            selectDropdownIBP( By.xpath( format( xpathCombo, "Primera")), pregunta,"COMBO PRIMERA PREGUNTA",3, getClass(), crearReporte);
//
//        }
//
//        String pregunta = "";
//        String preguntaPropia = "";
//
//        //GenerarData data = new GenerarData();
//
//
//        if (pregunta.contains("¿Pregunta propia?")){
//            writeOn(Utilidad.by( format( xpathCampoPregunta, "Primera" )), preguntaPropia,"CAMPO PRIMERA PREGUNTA",2, getClass(), crearReporte);
//
//            System.out.println(xpathCombo);
//            //xpathCombo = XPATH_TXT_pregunta1;
//
//        }
//    }


    /**
     * Permite completar la primera pregunta en el formulario de configuración de preguntas de seguridad.
     * @param preguntaPropia
     * @param pregt
     * @param crearReporte
     */
    private void setPregunta1(boolean preguntaPropia, GenerarPreguntasSeguridad pregt, boolean crearReporte){
        getText(msgEstasPreguntasServiran,10, getClass(),crearReporte);
        selectDropdownIBP( By.xpath( format( xpathCombo, "Primera")), pregt.getCombo(),"PRIMERA PREGUNTA",3, getClass(), crearReporte);

        if (preguntaPropia){
            writeOn(Utilidad.by( format( xpathCampoPregunta, "Primera" )), pregt.getPregunta(),"CAMPO PRIMERA PREGUNTA",2, getClass(), crearReporte);
        }

        writeOn(Utilidad.by( format( xpathCampoRespuesta, "Primera" )), pregt.getRespuesta(),"CAMPO PRIMERA RESPUESTA",2, getClass(), crearReporte);
    }

    /**
     * Permite completar la Segunda pregunta en el formulario de configuración de preguntas de seguridad.
     * @param preguntaPropia
     * @param preguntasRespuestas
     * @param crearReporte
     */
    private void setPregunta2(boolean preguntaPropia, GenerarPreguntasSeguridad preguntasRespuestas, boolean crearReporte){

        selectDropdownIBP( By.xpath( format( xpathCombo, "Segunda")), preguntasRespuestas.getCombo(),"SEGUNDA PREGUNTA",3, getClass(), crearReporte);

        if (preguntaPropia){
            writeOn(Utilidad.by( format( xpathCampoPregunta, "Segunda" )), preguntasRespuestas.getPregunta(),"CAMPO SEGUNDA PREGUNTA",2, getClass(), crearReporte);
        }

        writeOn(Utilidad.by( format( xpathCampoRespuesta, "Segunda" )), preguntasRespuestas.getRespuesta(),"CAMPO SEGUNDA RESPUESTA",2, getClass(), crearReporte);
    }

    /**
     * Permite completar la Tercera pregunta en el formulario de configuración de preguntas de seguridad.
     * @param preguntaPropia
     * @param preguntasRespuestas
     * @param crearReporte
     */
    private void setPregunta3(boolean preguntaPropia, GenerarPreguntasSeguridad preguntasRespuestas, boolean crearReporte){

        selectDropdownIBP( By.xpath( format( xpathCombo, "Tercera")), preguntasRespuestas.getCombo(),"TERCERA PREGUNTA",3, getClass(), crearReporte);

        if (preguntaPropia){
            writeOn(Utilidad.by( format( xpathCampoPregunta, "Tercera" )), preguntasRespuestas.getPregunta(),"CAMPO TERCERA PREGUNTA",2, getClass(), crearReporte);
        }

        writeOn(Utilidad.by( format( xpathCampoRespuesta, "Tercera" )), preguntasRespuestas.getRespuesta(),"CAMPO TERCERA RESPUESTA",2, getClass(), crearReporte);
    }

    /**
     * Permite completar la Cuarta pregunta en el formulario de configuración de preguntas de seguridad.
     * @param preguntaPropia
     * @param preguntasRespuestas
     * @param crearReporte
     */
    private void setPregunta4(boolean preguntaPropia, GenerarPreguntasSeguridad preguntasRespuestas, boolean crearReporte){

        selectDropdownIBP( By.xpath( format( xpathCombo, "Cuarta")), preguntasRespuestas.getCombo(),"CUARTA PREGUNTA",3, getClass(), crearReporte);

        if (preguntaPropia){
            writeOn(Utilidad.by( format( xpathCampoPregunta, "Cuarta" )), preguntasRespuestas.getPregunta(),"CAMPO CUARTA PREGUNTA",2, getClass(), crearReporte);
        }

        writeOn(Utilidad.by( format( xpathCampoRespuesta, "Cuarta" )), preguntasRespuestas.getRespuesta(),"CAMPO CUARTA RESPUESTA",2, getClass(), crearReporte);
    }

    /**
     * Permite completar la Quinta pregunta en el formulario de configuración de preguntas de seguridad.
     * @param preguntaPropia
     * @param preguntasRespuestas
     * @param crearReporte
     */
    private void setPregunta5(boolean preguntaPropia, GenerarPreguntasSeguridad preguntasRespuestas, boolean crearReporte){

        selectDropdownIBP( By.xpath( format( xpathCombo, "Quinta")), preguntasRespuestas.getCombo(),"QUITA PREGUNTA",3, getClass(), crearReporte);

        if (preguntaPropia){
            writeOn(Utilidad.by( format( xpathCampoPregunta, "Quinta" )), preguntasRespuestas.getPregunta(),"CAMPO QUINTA PREGUNTA",2, getClass(), crearReporte);
        }

        writeOn(Utilidad.by( format( xpathCampoRespuesta, "Quinta" )), preguntasRespuestas.getRespuesta(),"CAMPO QUINTA RESPUESTA",2, getClass(), crearReporte);
    }

    /**
     * Este método llena el campo de código de tarjeta de claves.
     * @param codigoTDC
     * @param crearReporte
     */
    private void setModalTDC(String codigoTDC, boolean crearReporte){
        writeMaskedOn(txtCodigoTDC, codigoTDC,"CAMPO CODIGO TARJETA CLAVES",10, getClass(), crearReporte);
    }

    /**
     * Completa el modal de TDC que no es de transacciones y presiona el boton continuar.
     * @param crearReporte
     */
    public void tdcContinuar(boolean crearReporte){
        getText( tituloTarjetaDeClaves,5, getClass(), crearReporte );
        setModalTDC("1111", crearReporte);
        clickOn( btnContinuarModal,"BOTON CONTINUAR TDC",3, getClass(), crearReporte, crearReporte ); //dfd
        isElementInvisibility( btnContinuarModal, 15, getClass() );
    }


    /**
     * Completa el modal de TDC en las transacciones diferente a Transacciones entre mis productos y presiona el boton realizar transaccion.
     * @param usuarioActual
     * @param crearReporte
     */
    public void tdcRealizarTransaccion(String usuarioActual, boolean crearReporte){
        setModalTDC("1111", crearReporte);
        clickOn(btnRealizarTransaccion,"BOTON CONTINUAR",5, getClass(), crearReporte, crearReporte); //dfd
    }

    /**
     * Completa el modal de TDC en las Transacciones entre mis productos y presiona el boton realizar transaccion. El mismo valida si esta activo o no la TCD para transaccionar
     * @param usuarioActual
     * @param crearReporte
     */
    public void tdcRealizarTransaccionEntreMisProductos(String usuarioActual, boolean crearReporte){
        if (Persistencia.getInstance().getClientInfo( usuarioActual).isActivoTDCTransaccionar()){
            setModalTDC("1111", crearReporte);
        }
        clickOn(btnRealizarTransaccion,"BOTON CONTINUAR",5, getClass(), crearReporte, crearReporte); //dfd
    }

    /**
     * Permite completar el formulario de configuracion de preguntas de seguridad.
     * @param pregunta1EsPropia
     * @param pregunta2EsPropia
     * @param pregunta3EsPropia
     * @param pregunta4EsPropia
     * @param pregunta5EsPropia
     * @param crearReporte
     */
    public void setFormularioConfiguracionPreguntasSeguridad(boolean pregunta1EsPropia,boolean pregunta2EsPropia,boolean pregunta3EsPropia,boolean pregunta4EsPropia,boolean pregunta5EsPropia, boolean crearReporte){
        GenerarPreguntasSeguridad preSegurd = new GenerarPreguntasSeguridad();

        setPregunta1( pregunta1EsPropia, preSegurd.configurarPregunta(pregunta1EsPropia), crearReporte);
        setPregunta2( pregunta2EsPropia, preSegurd.configurarPregunta( pregunta2EsPropia ), crearReporte);
        setPregunta3( pregunta3EsPropia, preSegurd.configurarPregunta( pregunta3EsPropia ), crearReporte);
        setPregunta4( pregunta4EsPropia, preSegurd.configurarPregunta(pregunta4EsPropia), crearReporte);
        setPregunta5( pregunta5EsPropia, preSegurd.configurarPregunta( pregunta5EsPropia ), crearReporte);

        crearPaso("Captura de pantalla", true, true, crearReporte);
    }


}
