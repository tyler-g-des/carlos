package pages.pyt.transferencias.pinpesos;

import accion.Accion;
import basetest.BaseTest;
import data.Persistencia;
import microservicios.Utilidad;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.dashboard.DashboardPage;
import pages.pyt.PoPagosTransferencias;
import pages.pyt.accesosrapidos.beneficiariosinscritos.PoBeneficiariosPinPesos;

import static data.ConstantesTasasImpuestos.IMPUESTO_PINPESOS;
import static java.lang.String.format;
import static microservicios.Utilidad.by;
import static pages.XpathComunes.*;
import static pages.pyt.XpathTransacciones.XPATH_DISCLAIMER;
import static pages.pyt.accesosrapidos.serviciosinscritos.ServiciosInscritosXpath.btnContinuar;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/03/2024 7:48 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PoPinPesos extends BasePage {

    private final PoPagosTransferencias pagePagoTransferencias;
    private final PoBeneficiariosPinPesos pageBeneficiario;
    private final Accion accion;

    private PoPinPesos(WebDriver webDriver) {
        super(webDriver);
        pagePagoTransferencias = PoPagosTransferencias.getNewPagina();
        pageBeneficiario = PoBeneficiariosPinPesos.getNewPagina();
        accion = Accion.getAcciones();
    }

    public static PoPinPesos getPage(){
        return new PoPinPesos(BaseTest.getDriver());
    }

    /**
     *
     * @param productOrigen Permite seleccionar el producto Origen.
     * @param productDestino producto destino existente o Nuevo beneficiario.
     * @param crearReporte
     */
//    public void setCombox(String productOrigen, String productDestino, boolean crearReporte){
//        pagePagoTransferencias.setCombox("PIN Pesos", productOrigen, productDestino,"Transferencia PIN Pesos", crearReporte);
//    }

//    public void setBeneficiario(String alias, String telefono, String tipoDocumento, String numDocumento, String nombre, String pais, String genero, boolean crearReporte){
//        pageBeneficiario.setFomulario( alias, telefono, tipoDocumento, numDocumento, nombre, pais, genero, crearReporte );
//    }

//    public void setCampos(String monto, String descripcion, boolean adicionarTransaccionFavorita, boolean crearReporte){
//        accion.writeOn(TXT_MONTO, monto,"CAMPO MONTO",2, getClass(), crearReporte);
//        accion.writeOn(TXT_DESCRIPCION, descripcion,"CAMPO DESCRIPCION",2, getClass(), crearReporte);
//        accion.selectCheckBox( checkAddTransaccionFavorita,"ADICIONAR TRANSACCION A FAVORITA", adicionarTransaccionFavorita,1, getClass(), crearReporte);
//    }

    public void setFormTransferenciaBeneficiarioInscrito(String productOrigen, String productDestino, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        pagePagoTransferencias.setCombox("PIN Pesos", productOrigen, productDestino,"Transferencia PIN Pesos", crearReporte);
        pagePagoTransferencias.setCamposBeneficiarioExistente( "NA", monto, descripcion, addTransaccionFavorita, crearReporte);
//        accion.crearPaso( accion.getText(by(String.format(XPATH_DISCLAIMER,"Esta transacción está sometida a todos los términos y condiciones del contrato de Banca en Línea.")),5,getClass(),false)
//                , true, true, true);
        accion.isElementVisible(by(format(XPATH_DISCLAIMER,"Esta transacción está sometida a todos los términos y condiciones del contrato de Banca en Línea.")),5,getClass());
    }

    public void setFormTransferenciaNuevoBeneficiario(String productOrigen, String alias, String telefono, String tipoDocumento, String numDocumento, String nombre, String pais, String genero, String monto, String descripcion, boolean addBeneficiarioFavorito, boolean addTransaccionFavorita, boolean crearReporte){
        pagePagoTransferencias.setCombox("PIN Pesos", productOrigen, "Nuevo beneficiario","Transferencia PIN Pesos", crearReporte);
        pageBeneficiario.setFomulario( alias, telefono, tipoDocumento, numDocumento, nombre, pais, genero, crearReporte);
        pagePagoTransferencias.setCamposNewBeneficiario("NA",monto,"NA",descripcion, addBeneficiarioFavorito, addTransaccionFavorita, crearReporte );
        accion.isElementVisible(by(format(XPATH_DISCLAIMER,"Esta transacción está sometida a todos los términos y condiciones del contrato de Banca en Línea.")),5,getClass());
    }

    protected By comision = By.xpath("//td[@id='let-commission']/span[contains(.,'RD$ 30.00')]");

    String resultado = "";

    protected void modalConfirmacionNuevoBeneficiario(String productOrigen, String alias,String nombre, String telefono ,String monto, boolean crearReporte){
        accion.clickOn(btnContinuar,"BOTON CONTINUAR",2, getClass(), crearReporte, crearReporte);
        float total = 0;

        accion.isElementVisible(by( format(XP_PRODUCTO_ORIGEN_MODAL, "", productOrigen) ) , 1, getClass());

        if (!alias.equalsIgnoreCase("NA") && !alias.equals("")){
            accion.crearPaso(accion.getText(by(format(XP_PRODUCTO_DESTINO_MODAL, alias, telefono)), 1, getClass(), false),true,true,false);
        }else {
            accion.crearPaso(accion.getText(by(format(XP_PRODUCTO_DESTINO_MODAL, nombre, telefono)), 1, getClass(), false),true,true,false);
        }

        accion.getText(by(format(XPATH_MONTO_MODAL, util.Utilidad.darFormatoMonedaComasPunto(monto) )),1,getClass(),false).replace("RD$ ","") ;
        total = total + Float.parseFloat(monto);

        if (!Persistencia.getInstance().esEmpleado(DashboardPage.getPage().getNombreCliente())){
            accion.getText(by(format(XPATH_COMISION_MODAL,"RD$","30.00")),5,getClass(),false);
            total = total + Float.parseFloat(accion.getText(comision,5,getClass(),false).replace("RD$ ",""));// accion.getText(By.xpath("//td[@id='let-commission']/span[contains(.,'RD$ 30.00')]"),5, getClass(), crearReporte);
        }
        total = total + IMPUESTO_PINPESOS * Integer.parseInt(monto)/100;
        accion.isElementVisible(by(format(XPATH_IMPUESTO_MODAL,"RD$",IMPUESTO_PINPESOS * Integer.parseInt(monto)/100)),1,getClass());

        accion.isElementVisible(by(format(XPATH_TOTAL_TRANSFERENCIA_MODAL,"RD", Utilidad.darFormatoMonedaComasPunto( String.valueOf(total) ))),1,getClass());
    }


    // //td[@id='let-amount']/span[contains(.,'RD$ 700.00')]   label editar
    protected void modalConfirmacionBeneficiarioInscrito(String productOrigen,String productDestino,String alias, String nombre, String monto, boolean crearReporte){
        accion.clickOn(btnContinuar,"BOTON CONTINUAR",2, getClass(), crearReporte, crearReporte);

        accion.isElementVisible(by( format(XP_PRODUCTO_ORIGEN_MODAL, productOrigen, monto) ) , 1, getClass());

        if (productDestino.contains("Nuevo beneficiario")){
            if (!alias.equalsIgnoreCase("NA") && !alias.equals("")){
                accion.isElementVisible(by(format(XP_PRODUCTO_DESTINO_MODAL, alias, productDestino)),1, getClass());
            }else {
                accion.isElementVisible(by(format(XP_PRODUCTO_DESTINO_MODAL, nombre, productDestino)),1, getClass());
            }
        }else {
            MsBeneficiariosNacionales beneficiario = new MsBeneficiariosNacionales(Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente())).newQuery().selecPinPesos();
            System.out.println(beneficiario.getNombre());
        }
        //
        if (!Persistencia.getInstance().esEmpleado(DashboardPage.getPage().getNombreCliente())){
            //accion.isElementVisible(By.xpath("//td[@id='let-commission']/span[contains(.,'RD$ 30.00')]"),3,getClass());
            accion.getText(By.xpath("//td[@id='let-commission']/span[contains(.,'RD$ 30.00')]"),5, getClass(), crearReporte);
        }
        System.out.println(Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente()));
        //System.out.println(Persistencia.getInstance().esEmpleado(DashboardPage.getPage().getNombreCliente()));
    }
}
