package pages.login;

import accion.Accion;
import data.GenerarData;
import data.GenerarPreguntasSeguridad;
import data.Persistencia;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PODashboardPage;
import servicios.micros.MicroClientInfo;
import servicios.micros.MicroContratos;
import servicios.micros.MicroPreguntaSeguridad;

import java.util.Map;

import static pages.login.XpathValidacionAcceso.*;

public class POValidacionAccesoPage extends Accion {

    private POLoginPage loginPage = null;
    private String numeroDocumento;
    private String identificador = "";
    private By labelNombreCliente = By.xpath("//div/p[contains(.,'Bienvenido(a)')]/span");
    private By tituloDudasPreguntas = By.xpath("//h3[contains(.,'Dudas y preguntas')]");
    private By tituloPrincipal = By.xpath("(//div/div/h3)[1]"); // Validación de acceso-
    private By tituloValidacionAcceso = By.xpath("//h3[contains(.,'Validación de acceso')]");
    By tituloAccediendoAlCanal = By.xpath("//h3[contains(.,'Accediendo al canal')]");

    private POFirstLoginPage pageFirstLogin;



    public POValidacionAccesoPage(WebDriver webDriver, POLoginPage loginPage) {
        super(webDriver);
        this.loginPage = loginPage;
        pageFirstLogin = new POFirstLoginPage(getDriver(), this);
        numeroDocumento = loginPage.getDocumentNumber();
        identificador = loginPage.getIdentificador();
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNumeroDocumento(){
        return numeroDocumento;
    }

    public String getNombreCliente(){
        return getText(labelNombreCliente,5,getClass(),false);
    }

    private void setValidacionAcceso(boolean respuestaCorrecta, boolean respuestaCorrecta2, boolean guardarDispositivoSeguro, boolean crearReporte){
        getText(tituloValidacionAcceso, 20, getClass(), crearReporte);
        writeOn( txtRespuesta1, new GenerarData().getRespuestaSeguridad(
                getText(labelPregunta1,20, getClass(),false), respuestaCorrecta) ,"CAMPO PRIMERA RESPUESTA",1, getClass(), crearReporte);
        writeOn( txtRespuesta2, new GenerarData().getRespuestaSeguridad( getText(labelPregunta2,1, getClass(),false), respuestaCorrecta2) ,"CAMPO SEGUNDA RESPUESTA",1, getClass(), crearReporte);
        selectCheckBox(chechBoxGuardarDispositivoSeguro,"", guardarDispositivoSeguro,1 ,getClass(), crearReporte);
    }

    private String getPantallaActual(){
        return getText(tituloPrincipal,25,getClass(), false);
    }

    private By tituloConfigPreguntasSeguridad = By.xpath("//h3[contains(.,'Preguntas de seguridad')]");
    private By tituloValidacionDeAcceso = By.xpath("//h3[contains(.,'Validación de acceso')]");

    public PODashboardPage validarAcceso(boolean crearReporte){

        String titulo = "";

        if (Persistencia.getInstance().getApiLogin(loginPage.getDocumentNumber()).isFirstLogin()){ // El usuario es primer inicio de sesion
            System.out.println("El usuario si es FirstLogin");

            pageFirstLogin.configurarCorreoContinuar("carlos_loyola@bhd.com.do", crearReporte);
            //pageFirstLogin.setFormularioConfiguracionPreguntasSeguridad( false,false,false,false,false, crearReporte);


        }else { // Es usuario recurrente.

            MicroContratos contratoAPP = MicroContratos.getInstance().consultarcontratoAPP(getNumeroDocumento());
            System.out.println("Contrato APP Aceptado: " + contratoAPP.isContractAccepted());

            if (MicroPreguntaSeguridad.getInstance().consultar(getNumeroDocumento()).isPreguntasConfiguradas()){ // Recurrente - Tiene preguntas configuradas

                titulo =  getText( tituloValidacionDeAcceso, 50, getClass(), false);
                setValidacionAcceso(true, true, false, crearReporte);
                pageFirstLogin.clickBotonContinuar(crearReporte);

                if (!contratoAPP.isContractAccepted()){
                    System.out.println("Se debe de aceptar el contrato");
                    getText(By.xpath("//h3[contains(.,'Validando contrato de uso de la plataforma')]"), 10, getClass(), crearReporte);
                    isElementInvisibility(By.xpath("//h3[contains(.,'Validando contrato de uso de la plataforma')]"),15, getClass());
                    getText(By.xpath("//h3[contains(.,'Términos y Condiciones de Uso Plataforma Online')]"), 10, getClass(), crearReporte);
                }


            }else { // Recurrente Reseteado: Muestra pantalla: Configurar Preguntas de seguridad
              titulo =  getText( tituloConfigPreguntasSeguridad, 30, getClass(), crearReporte);
              //POFirstLoginPage firstLoginPage = new POFirstLoginPage(getDriver(),this);
              pageFirstLogin.setFormularioConfiguracionPreguntasSeguridad( false,false,false,false,false, crearReporte);
            }

            System.out.println(titulo);
        }

        System.out.println("Driver en Validacion de acceso: " + getDriver());
        //crearPaso("CAptura",true,true,crearReporte);
        return new PODashboardPage(getDriver(), this);
    }

    private String getPantallaActual(boolean crearReporte){
        getText(tituloPrincipal,25,getClass(), false);
        if (isElementVisibleNoException(tituloDudasPreguntas,2)){
            return getText(tituloPrincipal,5,getClass(), crearReporte);
        }else {
            return "";
        }
    }


    private void setFormularioConfigPreguntasSeguridad(){
        GenerarPreguntasSeguridad preSegurd = new GenerarPreguntasSeguridad();

    }
}
