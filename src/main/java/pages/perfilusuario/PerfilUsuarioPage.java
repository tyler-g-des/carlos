package pages.perfilusuario;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import static pages.XpathComunes.*;

/**
 * Carlos Loyola
 *
 * @author: Carlos A. Loyola Tejeda
 * @Date 05/12/2023 11:51 p. m.
 * 2023
 * @Email carlos_loyola@bhd.com.do
 * BHDL_AutomatizacionMigracionIBP
 */
public class PerfilUsuarioPage extends BasePage {

    private final Accion accion;
    private PerfilUsuarioPage(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    protected static PerfilUsuarioPage getPage(){
        return new PerfilUsuarioPage(BaseTest.getDriver());
    }

    private By tituloDatosContacto = By.xpath("//h3[contains(.,'Datos de contacto')]");
    private By tituloOtrosDatos = By.xpath("//h3[contains(.,'Otros datos')]");
    private By comboCompaniaTelefonica = By.xpath("//div[label[contains(.,'Compañía telefónica')]]/p-dropdown//span");
    private By txtCalle = By.xpath("//div[label[contains(.,'Calle')]]/input");
    private By txtNumeroCalle = By.xpath("//div[label[contains(.,'Número de calle')]]/input");
    private By comboProvincia = By.xpath("//div[label[contains(.,'Provincia')]]/p-dropdown//span");
    private By comboMunicipio = By.xpath("//div[label[contains(.,'Municipio/Ciudad')]]/p-dropdown//span");
    private By comboSector = By.xpath("//div[label[contains(.,'Sector')]]/p-dropdown//span");
    private By comboLocalidad = By.xpath("//div[label[contains(.,'Localidad')]]/p-dropdown//span");
    private By txtEdificio = By.xpath("//div[label[contains(.,'Edificio')]]/input");
    private By txtNumeroApartamento = By.xpath("//div[label[contains(.,'No. de apartamento')]]/input");
    private By txtReferencia = By.xpath("//div[label[contains(.,'Referencia de ubicación')]]/input");
    private By tituloDatosActualizados = By.xpath("//div[div/img[@src='assets/img/CircleCheck.png']]/div/h2[contains(.,'Tus datos han sido actualizados satisfactoriamente.')]");



    protected void capturarTituloDatosContacto(boolean crearReporte){
        accion.getText(tituloDatosContacto, 10, getClass(), crearReporte);
    }

    protected void capturarTituloOtrosDatos(boolean tomarCaptura){
        accion.getText(tituloOtrosDatos, 1, getClass(), tomarCaptura);
        //accion.crearPaso(accion.getText(tituloOtrosDatos,1, getClass()) + " de contacto.",true, crearReporte, tomarCaptura);
    }

    protected void setDatosContacto(String correo, String companiaTelefono, String telefono, boolean crearReporte){
        accion.writeOn(TXT_CORREO, correo,"CORREO ELECTRONICO",1, getClass(), crearReporte);
        accion.selectDropdownIBP( comboCompaniaTelefonica, companiaTelefono,"COMPAÑIA TELEFONICA",15, getClass(), crearReporte);
        accion.writeOn(TXT_TELEFONO, telefono,"TELEFONO",1, getClass(), crearReporte);
        accion.crearPaso("CAptura,", true,true,true);
        //div[label[contains(.,'Teléfono')]]/p-inputmask/input
    }

    protected void setOtrosDatos(String calle, String numCalle, String provincia, String municipio, String sector, boolean crearReporte){
        accion.writeOn( txtCalle, calle,"CALLE",1, getClass(), crearReporte);
        accion.writeOn( txtNumeroCalle, numCalle,"NUMERO CALLE",1, getClass(), crearReporte);
        accion.selectDropdown( comboProvincia, provincia,"PROVINCIA",5, getClass(), crearReporte);
        accion.selectDropdown( comboMunicipio, municipio,"MUNICIPIO",5, getClass(), crearReporte);
        accion.selectDropdown( comboSector, sector,"SECTOR",10, getClass(), crearReporte);
    }

    protected void setOtrosDatos(String localidad, String edificio, String numApartamento, String referencia, boolean crearReporte){
        accion.selectDropdownIBP( comboLocalidad, localidad,"LOCALIDAD",5, getClass(), crearReporte);
        accion.writeOn( txtEdificio, edificio,"EDIFICIO",1, getClass(), crearReporte);
        accion.writeOn( txtNumeroApartamento, numApartamento,"NO. APARTAMENTO",1, getClass(), crearReporte);
        accion.writeOn( txtReferencia, referencia,"FERERENCIA",1, getClass(), crearReporte);
    }


    protected void guardarDatos(boolean crearReporte){
        accion.clickOn(BTN_GUARDAR,"BOTON GUARDAR",1,getClass(), crearReporte,crearReporte);
        accion.getText(tituloDatosActualizados,2, getClass(), crearReporte);
        accion.clickOn(BTN_REGRESAR,"BOTON REGRESAR",1,getClass(),false,crearReporte);
    }
}
