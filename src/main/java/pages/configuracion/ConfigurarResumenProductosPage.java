package pages.configuracion;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import static pages.configuracion.XpathConfiguracion.textoPantallaActual;

public class ConfigurarResumenProductosPage extends BasePage {
    private ConfigurarResumenProductosPage(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    public static ConfigurarResumenProductosPage getPage(){
        return new ConfigurarResumenProductosPage(BaseTest.getDriver());
    }

    private Accion accion;
    private By tituloConfiguracion = By.xpath("//h3[contains(.,'Configuración')]");
    private By tituloConfigurarResumenProductos = By.xpath("//h3[contains(.,'Configurar resumen de productos')]");
    private By tituloInformacionAccesoSeguridad = By.xpath("//h3[contains(.,'Información de acceso y seguridad')]");
    private By tituloContratosTerminosCondiciones = By.xpath("//h3[contains(.,'Contratos, términos y condiciones')]");

    private By linkConfigurarResumenProductos = By.xpath("//a/span[contains(.,'Configurar resumen de productos')]");
    private By linkInformacionAccesoSeguridad = By.xpath("//a/span[contains(.,'Información de acceso y seguridad')]");
    private By linkContratosTerminosCondiciones = By.xpath("//a/span[contains(.,'Contratos, términos y condiciones')]");

    //private By textoPantallaActual = By.xpath("//a[@class[contains(.,'p-menuitem-link-active')]]/span[2]");



    protected void pantallaConfigurarResumenProductos(boolean crearReporte){
        if (!accion.getText( textoPantallaActual,3, getClass(),false ).equals("Configurar resumen de productos")){
            accion.getText( textoPantallaActual,2, getClass(), crearReporte);
            accion.clickOn(linkConfigurarResumenProductos,"LINK CONFIGURAR RESUMEN DE PRODUCTOS",2, getClass(),false,crearReporte);
        }
        accion.getText(tituloConfigurarResumenProductos,3, getClass(), crearReporte);
    }

    protected void pantallaInformacionAccesoSeguridad(boolean crearReporte){
        accion.isElementPresent(tituloConfiguracion, "CONFIGURACION",3, getClass());
        if (!accion.getText( textoPantallaActual,3, getClass(),false ).equals("Información de acceso y seguridad")){
            accion.getText( textoPantallaActual,2, getClass(), crearReporte);
            accion.clickOn(linkInformacionAccesoSeguridad,"INFORMACIÓN DE ACCESO Y SEGURIDAD",2, getClass(),false,crearReporte);
        }
        accion.getText(tituloInformacionAccesoSeguridad,3, getClass(), crearReporte);
    }

    protected void pantallaContratosTerminosCondiciones(boolean crearReporte){
        accion.isElementPresent(tituloConfiguracion, "CONFIGURACION",3, getClass());
        if (!accion.getText( textoPantallaActual,3, getClass(),false ).equals("Contratos, términos y condiciones")){
            accion.getText( textoPantallaActual,2, getClass(), crearReporte);
            accion.clickOn(linkContratosTerminosCondiciones,"CONTRATOS, TÉRMINOS Y CONDICIONES",2, getClass(),false,crearReporte);
        }
        accion.getText(tituloContratosTerminosCondiciones,3, getClass(), crearReporte);
    }


}
