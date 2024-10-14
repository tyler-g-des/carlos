package com.bhd.ibp.pages;

import accion.AccionMetodos;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.bhd.ibp.pages.XpathComunes.*;
import static pages.pyt.XpathTransacciones.CHECK_ADD_BENEFICIARIO_FAVORITO;
import static pages.pyt.XpathTransacciones.CHECK_ADD_TRANSACCION_FAVORITA;

public class POSeleccionarFechaPage extends AccionMetodos {
    public POSeleccionarFechaPage(WebDriver webDriver) {
        super(webDriver);
    }



    private By radioButtonFechaHoy = by("//div[label[contains(.,'Hoy')]]/p-radiobutton[@name='date']/div/div/input[@aria-checked]  "); // false or true


    public void seleccionarFechaHoy(){

    }

    public void modalEntiendo(){
        if (isElementPresent( BTN_ENTIENDO)){
            clickOn( BTN_ENTIENDO,"Boton","Entiendo",2, getClass(),true,5,false, false  );
        }
    }

    private By radioButtonFechaFutura = by("//div[label[contains(.,'Fecha futura')]]/p-radiobutton[@name='date']/div");
    private By txtFechaFutura = by("//div[label[contains(.,'Fecha futura')]]//input[@placeholder='Fecha']");

    public void seleccionarFechaFutura(String fecha,boolean seleccionar, boolean crearReporte ){
        selectRadioButon( radioButtonFechaFutura,"Fecha futura",3, seleccionar, getClass(), crearReporte );
        clickOn( txtFechaFutura,"Input", "Fecha Futura", 1, getClass(), crearReporte );

        seleccionarFecha("23/Ago/2023",  crearReporte );

        esperar(5);
        BaseTest.createStep("Pantalla", true, true);
    }

    /**
     * Este metodo permite Adicionar este beneficiario como favorito y Adicionar la transacción a favoritos.
     *
     * @param addBeneficiarioFavorito true para dicionar el beneficiario, de lo contrario false. Si la transferencia no
     * permite adicionar beneficiario envial null.
     * @param addTransaccionFavorita permite marcar el checkBox adicionar la transferencia actual a favorita.
     * @param crearReporte
     */
    public void seleccionarCheckboxTransaccion(Object addBeneficiarioFavorito, boolean addTransaccionFavorita, boolean crearReporte){
        if ( addBeneficiarioFavorito != null){
            selectCheckBox( CHECK_ADD_BENEFICIARIO_FAVORITO,"Adicionar beneficiario favorito",1, Boolean.parseBoolean( addBeneficiarioFavorito.toString()) , getClass(), crearReporte );
        }
        selectCheckBox( CHECK_ADD_TRANSACCION_FAVORITA,"Adicionar transaccion favorita",0, addTransaccionFavorita, getClass(), crearReporte );
    }


    private By btnAnterior = by("//button[@aria-label[contains(.,'Previous')]]");
    private By btnSiguiente = by("//button[@aria-label[contains(.,'Next')]]");
    private By btnMesActual = by("//button[@aria-label='Choose Month']");
    private By btnAmoActual = by("//button[@aria-label='Choose Year']");


    private void seleccionarFecha(String fecha, boolean crearReporte){
        int anoEnviado = Integer.parseInt( fecha.split("/")[2] );

        seleccionarAno(anoEnviado);


    }

    private int getAnoActual(){
        return Integer.parseInt( getText( btnAmoActual,2, getClass()).replace(" ",""));
    }

    private By labelDecata = by("//span[@class[contains(.,'p-datepicker')]]");
    private int decadaFinal(){
        return Integer.parseInt( getText(labelDecata,1, getClass()).split(" - ")[1] ) ;
    }

    private int decadaInicio(){
        return Integer.parseInt( getText(labelDecata,1, getClass()).split(" - ")[0] ) ;
    }

    private void seleccionarAno(int ano){
        int anoActual = Integer.parseInt( getText( btnAmoActual,2, getClass()).replace(" ",""));
        By anoASeleccionar = by("//div[@class[contains(.,'yearpicker')] ] /span[contains(.,'"+ano+"')]");

        if (anoActual != ano){
            clickOn(btnAmoActual,"Boton","Ano acual",2, getClass(),true);
            if ( ano > decadaFinal() ){
                while (ano > decadaFinal()){
                    clickOn( btnSiguiente,"Boton","Siguiente",2, getClass(),true);
                }
            }else {

                while (ano < decadaInicio()){
                    clickOn( btnAnterior,"Icono","Anterior",2, getClass(),true);
                }
            }
            String estado = getAttribute(anoASeleccionar,1,"class", getClass());
            if (estado.contains("disabled ")){
                BaseTest.createStep("El año " + ano + " esta deshabilitado.", true, true);
            }else {
                clickOn( anoASeleccionar,"año", String.valueOf(ano), 2,  getClass(), true);
            }
        }
    }


    private By radioButtonTransaccionRecurrente = by("//div[label[contains(.,'Programar transacción recurrente')]]/p-radiobutton[@name='date']/div/div/input[@aria-checked]");
    private By txtFechaPrimeraTransaccion = by("//div[span[contains(.,'Fecha de la primera transacción:')]]//input");
    private By comboFrecuencia = by("//div[span[contains(.,'Frecuencia')] ]  /p-dropdown//span");
    private By radioButtonRepetir = by("//div[span[contains(.,'Repetir')]]/p-radiobutton[@name='repeatType']/div/div/input[@aria-checked]"); //fasle true
    private By radioButtonHastaCancelacion = by("//div[span[contains(.,'Hasta su cancelación')]]//p-radiobutton[@name='repeatType']/div/div/input[@aria-checked]"); //fasle true
    private By checkNotificarme = by("//p-checkbox[@name='notify']/div/div[@data-p-highlight]");
    private By txtNotificarmeNDiasAntes = by("//div[p-checkbox[@name='notify']] /p-inputnumber[@name='notifyDaysBefore']//input");


    /**
     *
     * @param fecha
     * @param frecuencia Diario-Semanal-Quincenal-Mensual-Anual
     * @param repetirHastaCancelacion
     * @param oNTransacionesRealizadas
     * @param notificarme
     * @param nDiasAntesRealizarTransaccion
     * @param crearReporte
     */
    public void seleccionarFechaRecurrente(String fecha, String frecuencia, boolean repetirHastaCancelacion, int oNTransacionesRealizadas, boolean notificarme, int nDiasAntesRealizarTransaccion, boolean crearReporte){

        if (! "atributo".equals("true")){

        }

    }

    private By tituloContratoInternacionalSIPA = by("//div[@role='dialog'] //div[contains(.,'Contrato de transferencia internacional y regional SIPA')]");
    private By subTituloContrato = by("//div[@role='dialog'] //h3[contains(.,'Para activar este servicio deberás leer y aceptar los términos y condiciones')]");
    private By contenidoContratoInternacionalSIPA = by("//div[@role='dialog'] //p[contains(.,'El Banco Múltiple BHD')]");
    public void modalContratoInternacionalRegionalSIPA(String codigoTDC, boolean crearReporte ){
        getText( tituloContratoInternacionalSIPA, 7, getClass(),  crearReporte );
        waitElementVisible( subTituloContrato,1, getClass() );
        waitElementVisible( contenidoContratoInternacionalSIPA, 1, getClass() );
        writeMaskedOn( TXT_TDC, codigoTDC, "Tarjeta de claves", 0, getClass(), crearReporte );
    }

    public void aceptarContratoInternacionalRegionalSIPA(boolean crearReporte){
        modalContratoInternacionalRegionalSIPA("1111", crearReporte);
        clickOn(BTN_CONTINUAR_MODAL,"boton","Continuar",1, getClass(),true,15, crearReporte, crearReporte );
    }



}
