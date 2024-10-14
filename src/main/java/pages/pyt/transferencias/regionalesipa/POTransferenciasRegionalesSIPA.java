package pages.pyt.transferencias.regionalesipa;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.pyt.PoPagosTransferencias;
import pages.pyt.accesosrapidos.beneficiariosinscritos.PoBeneficiariosInternacionales;

import static pages.pyt.accesosrapidos.beneficiariosinscritos.PoBeneficiariosInternacionales.TXT_NUM_PRODUCTO;
import static pages.pyt.accesosrapidos.beneficiariosinscritos.XpathBeneficiarios.COMBO_NOMBRE_BANCO;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 08/03/2024 9:44 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class POTransferenciasRegionalesSIPA extends BasePage {

    private final PoPagosTransferencias pagePagoTransferencias;
    private final PoBeneficiariosInternacionales beneficioInternacionalPage;
    private final Accion accion;

    private POTransferenciasRegionalesSIPA(WebDriver webDriver) {
        super(webDriver);
        pagePagoTransferencias = PoPagosTransferencias.getNewPagina();
        beneficioInternacionalPage = PoBeneficiariosInternacionales.getNewPagina();
        accion = Accion.getAcciones();
    }

    public static POTransferenciasRegionalesSIPA getNewPage(){
        return new POTransferenciasRegionalesSIPA(BaseTest.getDriver());
    }

    protected void setFormulario(String productOrigen, String nombre, String numIdentificacion, String pais, String calle, String correo,String nombreBanco, String numProducto, String monto, String descripcion,boolean crearReporte){
        pagePagoTransferencias.setCombox("Transferencias Regionales - SIPA", productOrigen,"NA","Transferencia regional - SIPA", crearReporte);
        beneficioInternacionalPage.setFormulario( nombre, numIdentificacion, pais, calle, correo, "NA","NA", crearReporte);
        accion.selectDropdown( COMBO_NOMBRE_BANCO, nombreBanco,"NOMBRE BANCO",5, getClass(), crearReporte);
        accion.writeOn(TXT_NUM_PRODUCTO, numProducto,"NUMERO PRODUCTO",1,getClass(), crearReporte);
        pagePagoTransferencias.setCamposBeneficiarioExistente("NA", monto, descripcion,false, crearReporte);
    }


}
