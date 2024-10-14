package pages.configuracion;

import accion.AccionPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POConfiguracionPage extends AccionPage {
    public POConfiguracionPage(WebDriver webDriver) {
        super(webDriver);
    }

    private final By linkConfigurarResumenProductos = by("//a[contains(.,'Configurar resumen de productos')]");
    private final By linkInformacionAccesoSeguridad = by("//a[contains(.,'Información de acceso y seguridad')]");
    private final By linkContratosTerminosCondiciones = by("//a[contains(.,'Contratos, términos y condiciones')]");




    // PESTAÑA INFORMACION DE ACCESO Y SEGURIDAD
    public POInformacionAccesoSeguridadPage informacionDeAccesoSeguridad(boolean crearReporte){
        getText(by("//h3[contains(.,'Configuración')]"),20, getClass() );
        if (! getAttributeValue( linkInformacionAccesoSeguridad, "class", 3, getClass() ).contains("link-active")){
            clickOn(linkInformacionAccesoSeguridad, "Link","Información de acceso y seguridad",1,false, getClass(), crearReporte, crearReporte);
        }
        return new POInformacionAccesoSeguridadPage(driver, this);
    }


    protected void menuces(){

    }


}
