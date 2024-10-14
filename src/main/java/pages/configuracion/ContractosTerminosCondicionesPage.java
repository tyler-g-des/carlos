package pages.configuracion;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.PoAccionesRepetitivas;

import static microservicios.Utilidad.by;
import static pages.XpathComunes.btnContinuarModalDisable;
import static pages.configuracion.XpathConfiguracion.textoPantallaActual;
import static pages.configuracion.XpathConfiguracion.tituloConfiguracion;

public class ContractosTerminosCondicionesPage extends BasePage {

    private Accion accion;
    private By linkContratosTerminosCondiciones = By.xpath("//a/span[contains(.,'Contratos, términos y condiciones')]");
    private By tituloContratosTerminosCondiciones = By.xpath("//h3[contains(.,'Contratos, términos y condiciones')]");
    private String xpathBtnActivar = "//div[div/h4[contains(.,'%s')]] //button[span[contains(.,'Activar')]]";
    private By btnActualizarContratoUsoInformacion = By.xpath("//div[div/h4[contains(.,'Contrato de Autorización de uso de información')]] //button[span[contains(.,'Actualizar')]]");
    private By btnSiAcepto = By.xpath("//button[@ng-reflect-label='Si acepto']");
    private By btnNoAcepto = By.xpath("//button[@ng-reflect-label='No acepto']");

    private ContractosTerminosCondicionesPage(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    public static ContractosTerminosCondicionesPage getNewPage(){
        return new ContractosTerminosCondicionesPage(BaseTest.getDriver());
    }

    private void pantallaContratosTerminosCondiciones(boolean crearReporte){
        accion.isElementPresent(tituloConfiguracion, "CONFIGURACION",3, getClass());
        if (!accion.getText( textoPantallaActual,3, getClass(),false ).equals("Contratos, términos y condiciones")){
            accion.getText( textoPantallaActual,2, getClass(), crearReporte);
            accion.clickOn(linkContratosTerminosCondiciones,"CONTRATOS, TÉRMINOS Y CONDICIONES",2, getClass(),false,crearReporte);
        }
        accion.getText(tituloContratosTerminosCondiciones,3, getClass(), crearReporte);
    }

    protected void clickBotonActivarContratoACHPagoAlInstante(boolean crearReporte){
        pantallaContratosTerminosCondiciones(crearReporte);
        accion.clickOn(by(String.format(xpathBtnActivar,"Contrato de transferencia ACH y Pagos al instante BCRD")),"BOTON ACTIVAR",10, getClass(), crearReporte, crearReporte);
    }

    protected boolean activarContratoACHPagoAlInstante(boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().continuarTDC("1111", crearReporte);
        accion.isElementInvisibility(btnContinuarModalDisable, 18, getClass());
        boolean contratoNoActivado = accion.isElementVisibleNoException(by(String.format(xpathBtnActivar,"Contrato de transferencia ACH y Pagos al instante BCRD")),1);
        if (contratoNoActivado){
            accion.crearPaso("No se pudo activar el contrato", !contratoNoActivado, crearReporte, crearReporte);
        }else {
            accion.crearPaso("Contrato Activado", contratoNoActivado, crearReporte, crearReporte);
        }
        return contratoNoActivado;
    }

    protected void clickBotonActivarContratoInternacionalSIPA(boolean crearReporte){
        pantallaContratosTerminosCondiciones(crearReporte);
        accion.clickOn(by(String.format(xpathBtnActivar,"Contrato de transferencia internacional y regional SIPA")),"BOTON ACTIVAR",10, getClass(), crearReporte, crearReporte);
    }

    protected boolean activarContratoInternacionalSIPA(boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().continuarTDC("1111", crearReporte);
        accion.isElementInvisibility(btnContinuarModalDisable, 18, getClass());
        boolean contratoNoActivado = accion.isElementVisibleNoException(by(String.format(xpathBtnActivar,"Contrato de transferencia internacional y regional SIPA")),1);
        if (contratoNoActivado){
            accion.crearPaso("No se pudo activar el contrato",!contratoNoActivado,crearReporte,crearReporte);
        }else {
            accion.crearPaso("Contrato Activado",contratoNoActivado,crearReporte,crearReporte);
        }
        return contratoNoActivado;
    }

    protected void clickBotonActualizarContratoAutorizacionUsoDeInformacion(boolean crearReporte){
        pantallaContratosTerminosCondiciones(crearReporte);
        accion.clickOn(btnActualizarContratoUsoInformacion,"BOTON ACTIALIZAR",10, getClass(), crearReporte, crearReporte);
    }

    protected void modalContratoUsoInformacion(boolean aceptar, boolean noAceptar, boolean crearReporte){

        if (aceptar){
            accion.clickOn(btnSiAcepto, "BOTON SI ACEPTO",1,getClass(),crearReporte, crearReporte);
        }else {
            if (noAceptar){
                accion.clickOn(btnNoAcepto, "BOTON NO ACEPTO",1,getClass(),crearReporte, crearReporte);
            }
        }
        accion.isElementInvisibility(btnNoAcepto, 15,getClass());
    }
}
