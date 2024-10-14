package pages.pyt;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.pyt.accesosrapidos.beneficiariosinscritos.BeneficiariosInscritosLogic;
import pages.pyt.accesosrapidos.serviciosinscritos.ServiciosIncritosLogic;

import static microservicios.Utilidad.by;
import static pages.pyt.XpathTransacciones.*;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 22/02/2024 10:38 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PoPagosTransferencias extends BasePage {

    private final Accion accion;
    private static final By linkBeneficiariosInscritos = By.xpath("//a/span[contains(.,'Beneficiarios inscritos')]");
    private final By linkServiciosInscritos = By.xpath("//a/span[contains(.,'Servicios inscritos')]");

    private PoPagosTransferencias(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    public static PoPagosTransferencias getNewPagina(){
        return new PoPagosTransferencias(BaseTest.getDriver());
    }

    public BeneficiariosInscritosLogic clickBeneficiariosInscritos(boolean crearReporte){
        accion.clickOn(linkBeneficiariosInscritos,"LINK BENEFICIARIOS INSCRITOS",3,getClass(), crearReporte, crearReporte);
        return BeneficiariosInscritosLogic.getLogica();
    }

    public ServiciosIncritosLogic clickServiciosInscritos(boolean crearReporte){
        accion.clickOn(linkServiciosInscritos,"LINK SERVICIOS INSCRITOS",3,getClass(), crearReporte, crearReporte);
        return ServiciosIncritosLogic.getLogica();
    }

    public void setComboTipoTransaccion(String tipoTransaccion, boolean crearReporte){
        if (accion.getText(COMBO_TIPO_TRANSACCION,1,getClass(),false).equals("Seleccione el tipo de transacción...")){
            accion.selectDropdown(COMBO_TIPO_TRANSACCION, tipoTransaccion,"TIPO TRANSACCION",15, getClass(), crearReporte);
        }
    }
    public void setCombox(String tipoTransaccion, String productOrigen, String productDestino, String leyenda, boolean crearReporte){
        if (accion.getText(COMBO_TIPO_TRANSACCION,1,getClass(),false).equals("Seleccione el tipo de transacción...")){
            accion.selectDropdownIBP(COMBO_TIPO_TRANSACCION, tipoTransaccion,"TIPO TRANSACCION",15, getClass(), crearReporte);
        }
        accion.selectDropdownIBP(COMBO_ORIGEN, productOrigen,"PRODUCTO ORIGEN",20, getClass(), crearReporte);
        accion.selectDropdownIBP(COMBO_DESTINO, productDestino,"PRODUCTO DESTINO",20, getClass(), crearReporte);
        accion.getText(by(String.format(XPATH_LEYENDA, leyenda)),8,getClass(),false);
    }

    public void setCamposBeneficiarioExistente(String moneda, String monto, String descripcion, boolean adicionarTransaccionFavorita, boolean crearReporte){
        accion.selectDropdown(COMBO_MONEDA, moneda,"MONEDA",6, getClass(), crearReporte);
        accion.writeOn(TXT_MONTO, monto,"CAMPO MONTO",2, getClass(), crearReporte);
        accion.writeOn(TXT_DESCRIPCION, descripcion,"CAMPO DESCRIPCION",2, getClass(), crearReporte);
        accion.selectCheckBox(CHECK_ADD_TRANSACCION_FAVORITA,"ADICIONAR TRANSACCION A FAVORITA", adicionarTransaccionFavorita,1, getClass(), crearReporte);
        accion.crearPaso("Pantalla en setCamposBeneficiarioExistente", true,true,true);
    }

    public void setCamposBeneficiarioExistente(String moneda, String monto, String correo, String descripcion, boolean adicionarTransaccionFavorita, boolean crearReporte){
        accion.selectDropdown(COMBO_MONEDA, moneda,"MONEDA",6, getClass(), crearReporte);
        accion.writeOn(TXT_MONTO, monto,"CAMPO MONTO",2, getClass(), crearReporte);
        accion.writeOn(TXT_DESCRIPCION, descripcion,"CAMPO DESCRIPCION",2, getClass(), crearReporte);
        accion.writeOn(TXT_CORREO, correo,"CAMPO CORREO",2, getClass(), crearReporte);
        accion.selectCheckBox(CHECK_ADD_TRANSACCION_FAVORITA,"ADICIONAR TRANSACCION A FAVORITA", adicionarTransaccionFavorita,1, getClass(), crearReporte);
    }

    public void setCamposNewBeneficiario(String moneda, String monto, String correo, String descripcion, boolean addBeneficiarioFavorito,boolean adicionarTransaccionFavorita, boolean crearReporte){
        accion.selectDropdown(COMBO_MONEDA, moneda,"MONEDA",6, getClass(), crearReporte);
        accion.writeOn(TXT_MONTO, monto,"CAMPO MONTO",2, getClass(), crearReporte);
        accion.writeOn(TXT_DESCRIPCION, descripcion,"CAMPO DESCRIPCION",2, getClass(), crearReporte);
        accion.writeOn(TXT_CORREO, correo,"CAMPO CORREO",2, getClass(), crearReporte);
        accion.selectCheckBox(CHECK_ADD_BENEFICIARIO_FAVORITO,"ADICIONAR BENEFICIARIO FAVORITO", addBeneficiarioFavorito,1, getClass(), crearReporte);
        accion.selectCheckBox(CHECK_ADD_TRANSACCION_FAVORITA,"ADICIONAR TRANSACCION A FAVORITA", adicionarTransaccionFavorita,1, getClass(), crearReporte);
        accion.crearPaso("Patalla", true, true, true);
    }
}
