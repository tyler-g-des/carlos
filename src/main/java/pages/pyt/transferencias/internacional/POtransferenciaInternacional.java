package pages.pyt.transferencias.internacional;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.pyt.PoPagosTransferencias;
import pages.pyt.accesosrapidos.beneficiariosinscritos.PoBeneficiariosInternacionales;

import static pages.XpathComunes.COMBO_MONEDA;
import static pages.pyt.XpathTransacciones.*;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 07/03/2024 9:28 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class POtransferenciaInternacional extends BasePage {

    private final Accion accion;
    private final PoPagosTransferencias pagePagosTransferencias;
    private final PoBeneficiariosInternacionales pageBeneficiario;
    private static final By COMBO_PROPOSITO = By.xpath("//p-dropdown[@inputid='purpose']//span");

    private POtransferenciaInternacional(WebDriver webDriver) {
        super(webDriver);
        pagePagosTransferencias = PoPagosTransferencias.getNewPagina();
        accion = Accion.getAcciones();
        pageBeneficiario = PoBeneficiariosInternacionales.getNewPagina();
    }

    public static POtransferenciaInternacional getNewPage(){
        return new POtransferenciaInternacional(BaseTest.getDriver());
    }

    protected void setFormTransferenciaBeneficiarioInscrito(String productOrigen, String productDestino,String proposito, String moneda, String monto, String correo, String descripcion, boolean crearReporte){
        pagePagosTransferencias.setCombox("Transferencia Internacional", productOrigen, productDestino,"Transferencia internacional", crearReporte);
        accion.selectDropdown(COMBO_PROPOSITO, proposito,"PROPOSITO",10, getClass(), crearReporte);
        pagePagosTransferencias.setCamposBeneficiarioExistente( moneda, monto, correo, descripcion,false, crearReporte);
        accion.crearPaso("Pantalla setFormTransferenciaBeneficiarioInscrito", true, true, true);
    }

    protected void setFormNuevoBeneficiario(String productOrigen, String nombre, String numIdentificacion, String pais, String calle, String correo, String numProducto, String tipoCodigo, String codigo, boolean usarIntermediario, String numIbanIntermediario, String tipoCodIntermediario, String codIntermediario, String destino, String clasificacion, String proposito, boolean crearReporte){
        pagePagosTransferencias.setCombox("Transferencia Internacional", productOrigen, "Nuevo beneficiario","Transferencia internacional", crearReporte);
        pageBeneficiario.setFormulario( nombre, numIdentificacion, pais, calle, correo, numProducto, "NA", crearReporte);
        pageBeneficiario.setFormulario( tipoCodigo, codigo, usarIntermediario, numIbanIntermediario, tipoCodIntermediario, codIntermediario, destino, clasificacion, proposito, crearReporte);
        //accion.crearPaso("Pantalla",true, true, true);
    }

    protected void setCampos(String moneda,String monto, String descripcion,boolean addBeneficiarioFavorito, boolean crearReporte){
        accion.selectDropdown(COMBO_MONEDA, moneda,"CAMPO MONEDA",15, getClass(), crearReporte);
        accion.writeOn(TXT_MONTO, monto,"CAMPO MONTO",1,getClass(),crearReporte);
        accion.writeOn(TXT_DESCRIPCION, descripcion,"CAMPO DESCRIPCION",1,getClass(),crearReporte);
        accion.selectCheckBox(CHECK_ADD_BENEFICIARIO_FAVORITO, "CHECK ADICIONAR BENEFICIARIO FAVORITO",addBeneficiarioFavorito,1,getClass(),crearReporte);
    }
}
