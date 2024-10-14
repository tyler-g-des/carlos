package pages.pyt.transferencias.entrebhdotrosbancos;

import accion.Accion;
import basetest.BaseTest;
import data.Persistencia;
import microservicios.MsContratos;
import microservicios.Utilidad;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.PoAccionesRepetitivas;
import pages.dashboard.DashboardPage;
import pages.pyt.PoPagosTransferencias;
import pages.pyt.accesosrapidos.beneficiariosinscritos.PoBeneficiariosNacionales;

import static data.ConstantesTasasImpuestos.IMPUESTO;
import static java.lang.String.format;
import static microservicios.Utilidad.by;
import static microservicios.Utilidad.convertStringToMoneyFormat;
import static pages.XpathComunes.*;
import static pages.XpathComunes.TXT_CORREO;
import static pages.pyt.XpathTransacciones.*;
import static pages.pyt.transferencias.entrebhdotrosbancos.XpathBHDOtrosBancos.COMBO_CANAL_ENVIO;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 11/03/2024 11:10 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class POTransaccionesBHDOtrosBancos extends BasePage {

    private final Accion accion;
    private final PoPagosTransferencias pagePagosTransferencias;
    private final PoBeneficiariosNacionales pageBeneficiarioNacionales;

    private POTransaccionesBHDOtrosBancos(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
        pagePagosTransferencias = PoPagosTransferencias.getNewPagina();
        pageBeneficiarioNacionales = PoBeneficiariosNacionales.getNewPagina();
    }

    public static POTransaccionesBHDOtrosBancos getNewPage(){
        return new POTransaccionesBHDOtrosBancos(BaseTest.getDriver());
    }

    /**
     * @param canalEnvio A tercero en BHD, ACH,Pago al instante BCRD
     * @param crearReporte
     */
    private void setCanalDeEnvio(String canalEnvio,boolean crearReporte){
        pagePagosTransferencias.setComboTipoTransaccion("Transacciones entre productos BHD y a otros Bancos", crearReporte);
        accion.selectDropdown(COMBO_CANAL_ENVIO, canalEnvio,"CANAL ENVIO",2, getClass(), crearReporte);
    }

    public void canalEnvioTerceroBHD(boolean crearReporte){
        setCanalDeEnvio("A tercero en BHD",crearReporte);
    }

    public void canalEnvioACH(boolean crearReporte){
        setCanalDeEnvio("ACH",crearReporte);
    }
    public void canalEnvioPagoAlInstante(boolean crearReporte){
        setCanalDeEnvio("Pago al instante BCRD",crearReporte);
    }

    public void captura(){
        accion.isElementVisibleNoException(By.xpath("Espera"), 4);
        accion.crearPaso("CAptura", true, true, true);
    }

    /**
     *
     * @param productOrigen
     * @param productDestino
     * @param leyenda Transferencia a terceros en BHD, <br>Transferencia a terceros de otros bancos (ACH), <br>Transferencia a terceros de otros bancos (Pago al instante BCRD)
     * @param crearReporte
     */
    protected void setProductoOrigenDestino(String productOrigen, String productDestino, String leyenda, boolean crearReporte){
        pagePagosTransferencias.setCombox("NA", productOrigen, productDestino, leyenda, crearReporte);
    }

    public void setComboxPagoInstante(String productOrigen, String productDestino,boolean crearReporte){
        setProductoOrigenDestino(productOrigen,productDestino,"Transferencia a terceros de otros bancos (Pago al instante BCRD)",crearReporte);
    }





    /**
     *
     * @param canalEnvio A tercero en BHD, ACH, Pago al instante BCRD
     * @param productOrigen
     * @param productDestino Nuevo beneficiario, o #de producto.
     * @param leyenda A tercero en BHD, <br>Transferencia a terceros de otros bancos (ACH), <br>Transferencia a terceros de otros bancos (Pago al instante BCRD)
     * @param crearReporte
     */
    protected void setCombox(String canalEnvio, String productOrigen, String productDestino, String leyenda, boolean crearReporte){
        pagePagosTransferencias.setComboTipoTransaccion("Transacciones entre productos BHD y a otros Bancos", crearReporte);
        accion.selectDropdown(COMBO_CANAL_ENVIO, canalEnvio,"CANAL ENVIO",2, getClass(), crearReporte);
        pagePagosTransferencias.setCombox("NA",productOrigen,productDestino, leyenda, crearReporte);
        //accion.selectDropdownIBP(by("//div[label[contains(.,'Nombre del banco')]]/p-dropdown//span"), "BANCO MULTIPLE BHD, S.A.","NOMBRE BANCO",10, getClass(), crearReporte);
        //accion.seleccionarCombo(by("//div[label[contains(.,'Nombre del banco')]]/p-dropdown//span"), "BANCO MULTIPLE BHD, S.A.","NOMBRE BANCO",10, getClass(), crearReporte);
        //accion.isElementVisibleNoException(By.xpath("//Esperamoss"),10);
        //accion.crearPaso("Pantalla", true, true, true );
        //Accion.get().
              // accion. seleccionarCombo( COMBO_NOMBRE_BANCO,"BANCO MULTIPLE BHD, S.A.","NOMBRE BANCO",30, crearReporte);
    }

    protected void setComboATerceroBHD(String productOrigen, String productDestino,boolean crearReporte){
        setCombox("A tercero en BHD", productOrigen, productDestino,"Transferencia a terceros en BHD", crearReporte);
    }

    protected void setFormularioTerceroBHDNuevoBeneficiario(String productOrigen, String nombreBanco, String  numProducto, String alias, String correo,String monto, String descripcion, boolean addBeneficiarioFavorito, boolean addTransaccionFavorita,boolean crearReporte){
        setComboATerceroBHD( productOrigen, "Nuevo beneficiario", crearReporte);
        pageBeneficiarioNacionales.setBeneficiarioNacional( nombreBanco, numProducto, alias, correo, crearReporte );
        pagePagosTransferencias.setCamposNewBeneficiario("NA", monto,"NA",descripcion,addBeneficiarioFavorito,addTransaccionFavorita,crearReporte);
    }

    protected void setFormularioTerceroBHDInscrito(String productOrigen, String productDestino, String monto, String descripcion, String correo, boolean addTransaccionFavorita, boolean crearReporte){
        setComboATerceroBHD(productOrigen,productDestino,crearReporte);
        pagePagosTransferencias.setCamposBeneficiarioExistente("NA",monto,descripcion,addTransaccionFavorita,crearReporte);
    }

    protected void setFormularioTerceroBHDInscrito(String productOrigen, String productDestino,String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){

    }

    protected void setComboATerceroACHOtrosBancos(String productOrigen, String productDestino,boolean crearReporte){
        setCombox("ACH", productOrigen, productDestino,"Transferencia a terceros de otros bancos (ACH)", crearReporte);
    }

    /**
     * <h1> ELIMINAR </h1>
     * @param productOrigen
     * @param productDestino
     * @param crearReporte
     */
    protected void setComboATerceroPagoAlInstante(String productOrigen, String productDestino,boolean crearReporte){
        setCombox("Pago al instante BCRD", productOrigen, productDestino,"Transferencia a terceros de otros bancos (Pago al instante BCRD)", crearReporte);
    }

    protected void verificarInformacionBeneficiarioOtrosBancos(MsBeneficiariosNacionales beneficiario){
        accion.isElementVisible( by( format(XPATH_BANCO_TIPO_PRODUCTO, beneficiario.getNombreBanco(), beneficiario.getTipoCuenta() )),1,getClass());
        accion.isElementVisible( by(format(XPATH_NUMERO_PRODUCTO,beneficiario.getNumProducto())),1,getClass());
    }

    public void setCampos(String monto, String correo, String descripcion,boolean addTransaccionFavorita,boolean crearReporte){
        accion.writeOn(TXT_MONTO, monto,"CAMPO MONTO",1, getClass(), crearReporte);
        accion.writeOn(TXT_CORREO, correo,"CAMPO CORREO",1, getClass(), crearReporte);
        accion.writeOn(TXT_DESCRIPCION,descripcion,"CAMPO DESCRIPCION",1,getClass(), crearReporte);
        accion.selectCheckBox(CHECK_ADD_TRANSACCION_FAVORITA,"CHECK ADICIONAR TRANSACCION FAVORITA",addTransaccionFavorita,1,getClass(),crearReporte);
    }

    public void setCampos(String monto, String descripcion, boolean addBeneficiarioFavorito, boolean addTransaccionFavorita, boolean crearReporte){
        accion.writeOn(TXT_MONTO, monto,"CAMPO MONTO",1, getClass(), crearReporte);
        accion.writeOn(TXT_DESCRIPCION,descripcion,"CAMPO DESCRIPCION",1,getClass(), crearReporte);
        accion.selectCheckBox(CHECK_ADD_BENEFICIARIO_FAVORITO,"CHECK ADICIONAR BENEFICIARIO FAVORITO",addTransaccionFavorita,1,getClass(),crearReporte);
        accion.selectCheckBox(CHECK_ADD_TRANSACCION_FAVORITA,"CHECK ADICIONAR TRANSACCION FAVORITA",addTransaccionFavorita,1,getClass(),crearReporte);
    }

    public void continuarTransaccion(boolean crearReporte){
        accion.clickOn(BTN_CONTINUAR,"BOTON CONTINUAR",1,getClass(),crearReporte,crearReporte);
        accion.getText(tituloModalConfirmaTuTransaccion,2,getClass(), crearReporte);
    }

    public void validarDataModalPagoInstenteNuevoBeneficiario(String productOrigen,String moneda,String tipoProducto,String alias, String nombre,String numProducto){
        accion.isElementVisible(by(format(XP_PRODUCTO_ORIGEN_MODAL,"",productOrigen)),3,getClass());
        if (tipoProducto.equalsIgnoreCase("Tarjetas de Crédito")){
            numProducto = Utilidad.enmascararTC(numProducto);
        }
        if (alias.equals("") || alias.equalsIgnoreCase("NA")){
            accion.isElementVisible(by(format(XP_PRODUCTO_DESTINO_MODAL,nombre,numProducto)),1,getClass());
        }else {
            accion.isElementVisible(by(format(XP_PRODUCTO_DESTINO_MODAL,alias,numProducto)),1,getClass());
        }

        boolean esEmpleado = Persistencia.getInstance().esEmpleado(DashboardPage.getPage().getNombreCliente());
        verificarComisionLBRT(esEmpleado, moneda);
//        if (Persistencia.getInstance().esEmpleado(DashboardPage.getPage().getNombreCliente())){
//            accion.isElementVisible(by(format(XPATH_COMISION_MODAL,moneda,"0.00")),1,getClass());
//        }else {
//            if (moneda.contains("RD")){
//                accion.isElementVisible(by(format(XPATH_COMISION_MODAL,moneda,"100")),1,getClass());
//            }else {
//                accion.isElementVisible(by(format(XPATH_COMISION_MODAL,moneda,"5")),1,getClass());
//            }
//        }
    }

    private void verificarComisionLBRT(boolean esEmpleado, String moneda){
        if (esEmpleado){
            accion.isElementVisible(by(format(XPATH_COMISION_MODAL,moneda,"0.00")),1,getClass());
        }else {
            if (moneda.contains("RD")){
                accion.isElementVisible(by(format(XPATH_COMISION_MODAL,moneda,"100")),1,getClass());
            }else {
                accion.isElementVisible(by(format(XPATH_COMISION_MODAL,moneda,"5")),1,getClass());
            }
        }

    }

    public void continuarModalConfirmacionPagoInstante(String productOrigen, String moneda, MsBeneficiariosNacionales beneficiario, String monto,  String codigoTDC, boolean crearReporte){
        //accion.clickOn(BTN_CONTINUAR,"BOTON CONTINUAR",1,getClass(),crearReporte,crearReporte);
        //accion.getText(tituloModalConfirmaTuTransaccion,2,getClass(), crearReporte);

        accion.isElementVisible(by(format(XP_PRODUCTO_ORIGEN_MODAL,productOrigen,"")),1,getClass());
        if (beneficiario.esUnaTC()){
            accion.isElementVisible(by(format(XP_PRODUCTO_DESTINO_MODAL,beneficiario.getAlias(),beneficiario.getNumProductoEnmascarado())),1,getClass());
        }else {
            accion.isElementVisible(by(format(XP_PRODUCTO_DESTINO_MODAL,beneficiario.getAlias(),beneficiario.getNumProducto())),1,getClass());
        }
        accion.getText(by(format(XPATH_MONEDA_MONTO_MODAL, moneda, convertStringToMoneyFormat(monto) )),1,getClass(),false);
        //accion.writeMaskedOn(txtCodigoTDC,codigoTDC,"CAMPO CODIGO TDC",1,getClass(),crearReporte);
        //accion.crearPaso("Pantalla",true, true, true);

        boolean esEmpleado = Persistencia.getInstance().esEmpleado(DashboardPage.getPage().getNombreCliente());
        verificarComisionLBRT(esEmpleado, moneda);

//        if (Persistencia.getInstance().esEmpleado(DashboardPage.getPage().getNombreCliente())){
//            accion.isElementVisible(by(format(XPATH_COMISION_MODAL,moneda,"0.00")),1,getClass());
//        }else {
//            if (moneda.contains("RD")){
//                accion.isElementVisible(by(format(XPATH_COMISION_MODAL,moneda,"100")),1,getClass());
//            }else {
//                accion.isElementVisible(by(format(XPATH_COMISION_MODAL,moneda,"5")),1,getClass());
//            }
//        }

        String impuesto = String.valueOf ((Float.parseFloat(monto)*IMPUESTO));
        accion.isElementVisible(by(format(XPATH_IMPUESTO_MODAL,moneda, impuesto)),1, getClass());
    }

    public void aceptarContacto(MsContratos contratos, boolean crearReporte){
        if (!contratos.contractACHPagoAlInstanteIsAccepted()){
            PoAccionesRepetitivas.getNewAccionesRepetitivasPage().aceptarContratoACHPagoInstante("1111",crearReporte);
        }
    }

    /////////////////////////////// VERDERO CODIGO

    protected void setTipoTransaccionCanalEnvio(String canalEnvio,boolean crearReporte){
        String tipoTransaccion = accion.getText(COMBO_TIPO_TRANSACCION,25, getClass(), crearReporte);
        if (!tipoTransaccion.contains("Transacciones entre productos BHD y a otros Bancos")){
            accion.selectDropdown(COMBO_TIPO_TRANSACCION,"Transacciones entre productos BHD y a otros Bancos","COMBO TIPO DE TRANSACCION",5, getClass(), crearReporte);
        }
        accion.selectDropdown(COMBO_CANAL_ENVIO, canalEnvio,"COMBO CANAL ENVIO",5, getClass(), crearReporte);
    }

    protected void setComboxProductoLeyenda(String productOrigen, String productoDestino, String leyenda, boolean crearReporte){
        accion.selectDropdownIBP(COMBO_ORIGEN, productOrigen,"PRODUCTO ORIGEN",20, getClass(), crearReporte);
        accion.selectDropdownIBP(COMBO_DESTINO, productoDestino,"PRODUCTO DESTINO",20, getClass(), crearReporte);
        accion.getText(by(String.format(XPATH_LEYENDA, leyenda)),8,getClass(),false);
    }

    protected void leyendaACH(){

    }

}
