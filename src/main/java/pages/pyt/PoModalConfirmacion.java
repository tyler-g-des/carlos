package pages.pyt;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/06/2024 4:05 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PoModalConfirmacion extends BasePage {

    private Accion accion;
    private PoModalConfirmacion(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    private By btnRegresar = By.xpath("//button[span[contains(.,'Regresar')]]");
    private By btnRealizarTransaccion = By.xpath("//button[span[contains(.,'Realizar transacción')]]");
    private By txtTDC = By.xpath("//input[@placeholder='XXXX']");
    private By btnContinuar = By.xpath("//button[span[contains(.,'Continuar')]]");

    private void modalConfirmacion(boolean crearReporte){
        accion.clickOn(btnContinuar, "BOTON CONTINUAR",1, getClass(), crearReporte, crearReporte);
        By tituloConfirmarTransaccion = By.xpath("//div[@class[contains(.,'common-modal')]]//div[contains(.,'Confirma tu transacción')]");
        accion.isElementVisible( btnRegresar,5, getClass());
        accion.getText(tituloConfirmarTransaccion,1,getClass(),crearReporte);
    }

    public void confirmarTransaccion(String codigoTDC,boolean crearReporte){
        modalConfirmacion(crearReporte);
        accion.writeMaskedOn(txtTDC, codigoTDC,"CAMPO CODIGO TARJETA DIGITAL",1,getClass(),crearReporte);
        accion.clickOn(btnRealizarTransaccion, "BOTON REALIZAR TRANSACCION",1,getClass(),crearReporte,crearReporte);
        accion.isElementInvisibility(btnRegresar, 10,getClass());
    }

    public static PoModalConfirmacion getNewPagina(){
        return new PoModalConfirmacion(BaseTest.getDriver());
    }
}
