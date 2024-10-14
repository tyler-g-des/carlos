package pages.configuracion;

import org.openqa.selenium.By;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 04/04/2024 2:28 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class XpathConfiguracion {

    private XpathConfiguracion(){

    }

    protected static By textoPantallaActual = By.xpath("//a[@class[contains(.,'p-menuitem-link-active')]]/span[2]");
    protected static  By tituloConfiguracion = By.xpath("//h3[contains(.,'Configuración')]");
}
