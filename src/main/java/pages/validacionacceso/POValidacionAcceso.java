package pages.validacionacceso;

import accion.Accion;
import data.GenerarData;
import data.Persistencia;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.dashboard.DashboardLogic;
import pages.dashboard.DashboardPage;

import static pages.XpathComunes.BTN_CONTINUAR;

public class POValidacionAcceso extends BasePage {


    private Accion accion;
    public POValidacionAcceso(WebDriver webDriver) {
        super(webDriver);
        accion = new Accion(webDriver);
    }

    private By labelPregunta1 = By.xpath("//div[@formarrayname]/div[@ng-reflect-name='0']/label");
    private By labelPregunta2 = By.xpath("//div[@formarrayname]/div[@ng-reflect-name='1']/label");
    private By txtRespuesta1 = By.xpath("//div[@formarrayname]/div[@ng-reflect-name='0']/input[@placeholder='Ingresa tu respuesta']");
    private By txtRespuesta2 = By.xpath("//div[@formarrayname]/div[@ng-reflect-name='1']/input[@placeholder='Ingresa tu respuesta']");
    private By chechBoxGuardarDispositivoSeguro = By.xpath("//form/div/div/div/p-checkbox[@name='secureDevice']");
    private By tituloValidacionAcceso = By.xpath("//h3[contains(.,'Validación de acceso')]");
    private By tituloPreguntasDeSeguridad = By.xpath("//h3[contains(.,'Preguntas de seguridad')]");
    private By labelConfigurarPreguntasSubMensaje = By.xpath("//h4[contains(.,'Estas servirán para autenticarte al momento de acceder a la plataforma')]");

    private By tituloPrincipalEnDudasPreguntas = By.xpath("//div[@class='bhd-container']/div/div[@class[contains(.,'p-lg-8')] ]//p-card/div/div/h3");
    private By tituloRando = By.xpath("//h3");
    private By tituloDudasPreguntas = By.xpath("//h3[contains(.,'Dudas y preguntas')]");


    // String pregunta1 = accion.getText( labelPregunta1,1, getClass(),false);
    // String pregunta2 = accion.getText( labelPregunta2,1, getClass(),false);


    private void formularioValidarPreguntasSeguridad(String respuesta1, String respuesta2, boolean guardarDispositivoSeguro, boolean crearReporte){
        accion.getText(tituloValidacionAcceso, 20, getClass(), crearReporte);
        accion.writeOn( txtRespuesta1,  respuesta1 ,"CAMPO PRIMERA RESPUESTA",1, getClass(), crearReporte);
        accion.writeOn( txtRespuesta2,  respuesta2 ,"CAMPO SEGUNDA RESPUESTA",1, getClass(), crearReporte);
        accion.selectCheckBox(chechBoxGuardarDispositivoSeguro,"",guardarDispositivoSeguro,1 ,getClass(), crearReporte);
    }

    private String getPregunta1(){
        return accion.getText( labelPregunta1,1, getClass(),false);
    }

    private String getPregunta2(){
        return accion.getText( labelPregunta2,1, getClass(),false);
    }

    private String getPantalla(){
        return accion.getText(tituloPrincipalEnDudasPreguntas,1,getClass(),false);
    }

    private void capturarPantallaFormularioConfigurarPreguntaSeguridad(boolean crearReporte){
        String titulo = accion.getText(tituloPreguntasDeSeguridad,1, getClass(), false);
        String mesg = accion.getText(labelConfigurarPreguntasSubMensaje,1, getClass(), false);
        accion.crearPaso(titulo + "<br><br>" + mesg,true,crearReporte, crearReporte);
    }

    /**
     * <h1>Eliminar</h1>
     * @param pregunta1
     * @param respueta1
     * @param pregunta2
     * @param respueta2
     * @param pregunta3
     * @param respueta3
     * @param pregunta4
     * @param respueta4
     * @param pregunta5
     * @param respueta5
     * @param crearReporte
     */
    private void setPreguntasSeguridad(String pregunta1, String respueta1, String pregunta2, String respueta2, String pregunta3, String respueta3, String pregunta4, String respueta4, String pregunta5, String respueta5, boolean crearReporte){
        capturarPantallaFormularioConfigurarPreguntaSeguridad(crearReporte);
        configurarPregunta1(pregunta1,"NA",respueta1, crearReporte);
        configurarPregunta2(pregunta2,"NA",respueta2,crearReporte);
        setConfigurarPregunta3(pregunta3,"NA",respueta3, crearReporte);
        setConfigurarPregunta4(pregunta4,"NA",respueta4, crearReporte);
        setConfigurarPregunta5(pregunta5,"NA",respueta5, crearReporte);
        accion.crearPaso("",crearReporte,crearReporte,crearReporte);
    }


    public void validarAccesoOpcionDelMomento(boolean crearReporte){
        DashboardPage.getPage().getNombreCliente();
        if (accion.isElementVisibleNoException(tituloDudasPreguntas,1)){
            String textoTituloPrincipal = accion.getText(tituloPrincipalEnDudasPreguntas,3,getClass(),crearReporte);
            if (textoTituloPrincipal.contains("Compartir información")){
                // Aceptar contrato compartir informacion
            } else if (textoTituloPrincipal.contains("Validación de acceso")) {
                validarPreguntasSeguridad(false, crearReporte);
            } else if (textoTituloPrincipal.contains("Preguntas de seguridad")) {
                setFormularioConfigurarPreguntasSeguridad(
                        "","", "",
                        "", "","",
                        "","", "",
                        "", "","",
                        "","", "",crearReporte);
            }
        }
    }

    private By msgAccediendoCanal = By.xpath("//h3[contains(.,'Accediendo al canal')]");

    public void pantallaActual(boolean crearReporte){
        accion.isElementInvisibility(msgAccediendoCanal, 30, getClass());
        System.out.println("Dudas y preguntas visible: " + accion.isElementVisible(tituloDudasPreguntas, 2));
        if (accion.isElementVisible(tituloDudasPreguntas,2)){
            String tituloVentana = accion.getText(tituloPrincipalEnDudasPreguntas, 1,getClass(),false);
            System.out.println("Titulo: " + tituloVentana);
            if (tituloVentana.contains("Validación de acceso")){
                //
                // accion.getText(tituloValidacionAcceso, 1, getClass(), crearReporte);
                //accion.crearPaso("Se esta validando el acceso", true,crearReporte, crearReporte);
                validarPreguntasSeguridad(false, crearReporte);
            }
        }

        System.out.println(accion.getText(tituloPrincipalEnDudasPreguntas, 1,getClass(),false));
    }

    public DashboardLogic validarAcceso(boolean crearReporte){

        String nombreCliente = DashboardPage.getPage().getNombreCliente();
        System.out.println(nombreCliente);
        boolean dudas = accion.isElementVisibleNoException(tituloDudasPreguntas,1);
        System.out.println(dudas);


        if (Persistencia.tienePreguntasConfiguradas(nombreCliente)){
            System.out.println("Se debe visualizar el titulo de dudas");
            if (dudas){
                System.out.println("Si esta presente dudas y preguntas");
                System.out.println(accion.getText(tituloPrincipalEnDudasPreguntas,2,getClass(),false));
            }
        }else {
            System.out.println("No tiene preguntas configuradas");
            System.out.println(accion.getText(tituloPrincipalEnDudasPreguntas,2,getClass(),false));

            //System.out.println("No se debe visualizar el titulo de dudas");
        }



        System.out.println("Preguntas configuradas: "+ Persistencia.tienePreguntasConfiguradas(nombreCliente));

        if (accion.isElementVisibleNoException(tituloDudasPreguntas,1)){
            System.out.println("Si esta presente dudas y preguntas");
            System.out.println(accion.getText(tituloPrincipalEnDudasPreguntas,2,getClass(),false));
        }
        String textTituloRandom = accion.getText(tituloRando,2,getClass(),false);


        System.out.println("RANDON:"+ textTituloRandom);

        if (textTituloRandom.equals("360 - Resumen de Productos")){
            System.out.println("El dispositivo es seguro");
        }else {
            String tituloPantallaActual = accion.getText(tituloPrincipalEnDudasPreguntas,1,getClass(),false);

            if (Persistencia.tienePreguntasConfiguradas(nombreCliente)){ // Validar preguntas de seguridad.
                System.out.println("El cliente si tiene las preguntas configuradas");
                validarPreguntasSeguridad( false, crearReporte);

                if (tituloPantallaActual.equals("Validación de acceso")){ // Validar preguntas de seguridad.
                    System.out.println("Se deben validar las preguntas de seguridad.");
                }

            }else { // Configurar preguntas de usuario resetado y primer inicio de sesion

                if (tituloPantallaActual.equals("Preguntas de seguridad")){ // Configurar preguntas de usuario resetado.
                    System.out.println("El usuario esta reseteado y se debe configurar las preguntas de seguridad");

                    setFormularioConfigurarPreguntasSeguridad(
                            "¿Cuál es el nombre del colegio donde cursó la primaria?","NA","colegio",
                            "¿Cuál es el personaje de su libro favorito?","NA","libro",
                            "¿Cuál es su color favorito?","NA","color",
                            "¿Cuál es la marca de su primer carro?","NA","carro",
                            "¿Cuál es el nombre de su abuela materna?","NA","abuela",crearReporte
                    );

                }else { // Pantalla de primer inicio de sesion.
                    System.out.println("Este es un usuario que esta realizando su primer inicio de sesion");
                }
            }
        }

        return DashboardLogic.getLogica();
    }

    private By comboPregunta1 = By.xpath("//div[label[contains(.,'Primera pregunta')]]  /p-dropdown//span");
    private By txtConfRespuesta1 = By.xpath("//div[label[contains(.,'Primera pregunta')]]  //input[@placeholder='Digita tu respuesta']");
    private By txtConfPreguntaPropia1 = By.xpath("//div[label[contains(.,'Primera pregunta')] ]  //input[@placeholder='Digita tu pregunta']");

    private By comboPregunta2 = By.xpath("//div[label[contains(.,'Segunda pregunta')]]  /p-dropdown//span");
    private By txtConfRespuesta2 = By.xpath("//div[label[contains(.,'Segunda pregunta')]]  //input[@placeholder='Digita tu respuesta']");
    private By txtConfPreguntaPropia2 = By.xpath("//div[label[contains(.,'Segunda pregunta')] ]  //input[@placeholder='Digita tu pregunta']");

    private By comboPregunta3 = By.xpath("//div[label[contains(.,'Tercera pregunta')]]  /p-dropdown//span");
    private By txtConfRespuesta3 = By.xpath("//div[label[contains(.,'Tercera pregunta')]]  //input[@placeholder='Digita tu respuesta']");
    private By txtConfPreguntaPropia3 = By.xpath("//div[label[contains(.,'Tercera pregunta')] ]  //input[@placeholder='Digita tu pregunta']");


    private By comboPregunta4 = By.xpath("//div[label[contains(.,'Cuarta pregunta')]]  /p-dropdown//span");
    private By txtConfRespuesta4 = By.xpath("//div[label[contains(.,'Cuarta pregunta')]]  //input[@placeholder='Digita tu respuesta']");
    private By txtConfPreguntaPropia4 = By.xpath("//div[label[contains(.,'Cuarta pregunta')] ]  //input[@placeholder='Digita tu pregunta']");


    private By comboPregunta5 = By.xpath("//div[label[contains(.,'Quinta pregunta')]]  /p-dropdown//span");
    private By txtConfRespuesta5 = By.xpath("//div[label[contains(.,'Quinta pregunta')]]  //input[@placeholder='Digita tu respuesta']");
    private By txtConfPreguntaPropia5 = By.xpath("//div[label[contains(.,'Quinta pregunta')] ]  //input[@placeholder='Digita tu pregunta']");



    private void setFormularioConfigurarPreguntasSeguridad(String pregunta1, String preguntaPropia1, String respuesta1, String pregunta2, String preguntaPropia2, String respuesta2, String pregunta3, String preguntaPropia3, String respuesta3, String pregunta4, String preguntaPropia4, String respuesta4,  String pregunta5, String preguntaPropia5, String respuesta5, boolean crearReporte){
        capturarPantallaFormularioConfigurarPreguntaSeguridad( crearReporte );

        accion.selectDropdownIBP( comboPregunta1, pregunta1,"COMBO PRIMERA PREGUNTA",1, getClass(), crearReporte);
        if (pregunta1.equals("¿Pregunta propia?")){
            accion.writeOn( txtConfPreguntaPropia1, preguntaPropia1,"COMBO PRIMERA PREGUNTA",1, getClass(), crearReporte);
        }
        accion.writeOn( txtConfRespuesta1, respuesta1,"CAMPO PRIMERA RESPUESTA",1, getClass(), crearReporte);


        accion.selectDropdownIBP( comboPregunta2, pregunta2,"COMBO SEGUNDA PREGUNTA",1, getClass(), crearReporte);
        if (pregunta1.equals("¿Pregunta propia?")){
            accion.writeOn( txtConfPreguntaPropia2, preguntaPropia2,"COMBO SEGUNDA PREGUNTA",1, getClass(), crearReporte);
        }
        accion.writeOn( txtConfRespuesta2, respuesta2,"CAMPO SEGUNDA RESPUESTA",1, getClass(), crearReporte);


        accion.selectDropdownIBP( comboPregunta3, pregunta3,"COMBO TERCERA PREGUNTA",1, getClass(), crearReporte);
        if (pregunta1.equals("¿Pregunta propia?")){
            accion.writeOn( txtConfPreguntaPropia3, preguntaPropia3,"COMBO TERCERA PREGUNTA",1, getClass(), crearReporte);
        }
        accion.writeOn( txtConfRespuesta3, respuesta3,"CAMPO TERCERA RESPUESTA",1, getClass(), crearReporte);


        accion.selectDropdownIBP( comboPregunta4, pregunta4,"COMBO CUARTA PREGUNTA",1, getClass(), crearReporte);
        if (pregunta1.equals("¿Pregunta propia?")){
            accion.writeOn( txtConfPreguntaPropia4, preguntaPropia4,"COMBO CUARTA PREGUNTA",1, getClass(), crearReporte);
        }
        accion.writeOn( txtConfRespuesta4, respuesta4,"CAMPO CUARTA RESPUESTA",1, getClass(), crearReporte);


        accion.selectDropdownIBP( comboPregunta5, pregunta5,"COMBO QUINTA PREGUNTA",1, getClass(), crearReporte);
        if (pregunta1.equals("¿Pregunta propia?")){
            accion.writeOn( txtConfPreguntaPropia5, preguntaPropia5,"COMBO QUINTA PREGUNTA",1, getClass(), crearReporte);
        }
        accion.writeOn( txtConfRespuesta5, respuesta5,"CAMPO QUINTA RESPUESTA",1, getClass(), crearReporte);

        accion.crearPaso("",true,crearReporte,crearReporte);
    }

    private void configurarPregunta1(String pregunta, String preguntaPropia, String respuesta, boolean crearReporte){
        accion.selectDropdownIBP( comboPregunta1, pregunta,"PRIMERA PREGUNTA",1, getClass(), crearReporte);

        if (pregunta.equals("¿Pregunta propia?")){
            accion.writeOn( txtConfPreguntaPropia1, preguntaPropia,"PREGUNTA PROPIA 1",1, getClass(), crearReporte);
        }
        accion.writeOn( txtConfRespuesta1, respuesta,"RESPUESTA 1",1, getClass(), crearReporte);
    }

    private void configurarPregunta2(String pregunta, String preguntaPropia, String respuesta, boolean crearReporte){
        accion.selectDropdownIBP( comboPregunta2, pregunta,"SEGUNDA PREGUNTA",1, getClass(), crearReporte);

        if (pregunta.equals("¿Pregunta propia?")){
            accion.writeOn( txtConfPreguntaPropia2, preguntaPropia,"SEGUNDA PROPIA 1",1, getClass(), crearReporte);
        }
        accion.writeOn( txtConfRespuesta2, respuesta,"RESPUESTA 1",1, getClass(), crearReporte);
    }

    private void setConfigurarPregunta3(String pregunta, String preguntaPropia, String respuesta, boolean crearReporte){
        accion.selectDropdownIBP( comboPregunta3, pregunta,"TERCERA PREGUNTA",1, getClass(), crearReporte);

        if (pregunta.equals("¿Pregunta propia?")){
            accion.writeOn( txtConfPreguntaPropia3, preguntaPropia,"TERCERA PROPIA 1",1, getClass(), crearReporte);
        }
        accion.writeOn( txtConfRespuesta3, respuesta,"RESPUESTA 3",1, getClass(), crearReporte);
    }

    private void setConfigurarPregunta4(String pregunta, String preguntaPropia, String respuesta, boolean crearReporte){
        accion.selectDropdownIBP( comboPregunta4, pregunta,"CUARTA PREGUNTA",1, getClass(), crearReporte);

        if (pregunta.equals("¿Pregunta propia?")){
            accion.writeOn( txtConfPreguntaPropia4, preguntaPropia,"CUARTA PROPIA 1",1, getClass(), crearReporte);
        }
        accion.writeOn( txtConfRespuesta4, respuesta,"RESPUESTA 4",1, getClass(), crearReporte);
    }

    private void setConfigurarPregunta5(String pregunta, String preguntaPropia, String respuesta, boolean crearReporte){
        accion.selectDropdownIBP( comboPregunta5, pregunta,"SEGUNDA PREGUNTA",1, getClass(), crearReporte);

        if (pregunta.equals("¿Pregunta propia?")){
            accion.writeOn( txtConfPreguntaPropia5, preguntaPropia,"SEGUNDA PROPIA 1",1, getClass(), crearReporte);
        }
        accion.writeOn( txtConfRespuesta5, respuesta,"RESPUESTA 1",1, getClass(), crearReporte);
    }

    private By msgTienesRespuestasIncorrectas = By.xpath("//span[contains(.,'Tienes respuestas incorrectas, por favor revisa y vuelve a intentar')]");
    public String validarPreguntasRespuestasIncorrectas(GenerarData respuesta1Correcta, String respuesta2, boolean guardarDispositivoSeguro, boolean crearReporte){
        formularioValidarPreguntasSeguridad( respuesta1Correcta.getRespuestaSeguridad(getPregunta1()), respuesta2, guardarDispositivoSeguro, crearReporte);
        accion.clickOn( BTN_CONTINUAR, "BOTON CONTINIAR",1, getClass(), crearReporte, crearReporte );
        return accion.getText(msgTienesRespuestasIncorrectas,30,getClass(),crearReporte);
    }

    public void validarPreguntasSeguridad(boolean guardarDispositivoSeguro, boolean crearReporte){
        GenerarData data = new GenerarData();
        formularioValidarPreguntasSeguridad( data.getRespuestaSeguridad( getPregunta1() ), data.getRespuestaSeguridad( getPregunta2() ), guardarDispositivoSeguro, crearReporte);
        accion.clickOn( BTN_CONTINUAR, "BOTON CONTINIAR",1, getClass(), crearReporte, crearReporte );
    }
}
