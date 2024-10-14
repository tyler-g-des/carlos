package pages.pyt.voucher;

import accion.Accion;
import basetest.BaseTest;
import data.Persistencia;
import microservicios.Utilidad;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.dashboard.DashboardPage;

import static java.lang.String.format;
import static microservicios.Utilidad.by;
import static pages.pyt.voucher.XpathVoucher.*;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 15/04/2024 4:24 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PoVoucherPage extends BasePage {

    private Accion accion;

    private PoVoucherPage(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    public static PoVoucherPage getNewPage(){
        return new PoVoucherPage(BaseTest.getDriver());
    }

    public PoVoucherPage imprimirMensajePresentacion(boolean crearReporte){
        accion.getText(by(format(XPATH_MSG_RESULTADO_TRANSACCION, DashboardPage.getPage().getNombreCliente())),300,getClass(),crearReporte);
    return this;
    }

    public PoVoucherPage validarProductosConBeneficiario(String numeroProductOrigen, String nombreProductOrigen, String aliasProductOrigen, String numeroProductDestino, String nombreBeneficiario,String aliasBeneficiario){
        if (aliasProductOrigen.equals(nombreProductOrigen)){
            accion.isElementVisible(by(format(XPATH_PRODUCTO_ORIGEN_COMPLETO,nombreProductOrigen, numeroProductOrigen)),1,getClass());
        }else {
            accion.isElementVisible(by(format(XPATH_PRODUCTO_ORIGEN_COMPLETO,aliasProductOrigen, numeroProductOrigen)),1,getClass());
        }

        if (aliasBeneficiario.equals("") || aliasBeneficiario.equalsIgnoreCase("NA")){
            accion.isElementVisible(by(format(XPATH_PRODUCTO_DESTINO_ALIAS,nombreBeneficiario, numeroProductDestino)),1,getClass());
        }else {
            accion.isElementVisible(by(format(XPATH_PRODUCTO_DESTINO_ALIAS,aliasBeneficiario, numeroProductDestino)),1,getClass());
        }
        return this;
    }

    /**
     *
     * @param tipoDeTransaccion Transaccion entre productos BHD y a otros Bancos ( LBTR ),
     */
    public void validarTipoDeTransaccion(String tipoDeTransaccion){
        accion.getText(by(format(XPATH_TIPO_TRANSACCION,tipoDeTransaccion)),1,getClass(),false);
    }

    public void validarProductos(String numeroProductOrigen, String nombreProductOrigen, String aliasProductOrigen, String numeroProductDestino, String nombreProductoDestino,String aliasProductoDestino){
        if (aliasProductOrigen.equals(nombreProductOrigen)){
            accion.isElementVisible(by(format(XPATH_PRODUCTO_ORIGEN_COMPLETO,nombreProductOrigen, numeroProductOrigen)),1,getClass());
        }else {
            accion.isElementVisible(by(format(XPATH_PRODUCTO_ORIGEN_COMPLETO,aliasProductOrigen, numeroProductOrigen)),1,getClass());
        }

    }



    public void validarDataTransferencia(String descripcion, String tipoTransaccion, String moneda,String monto, String comision, String impuesto){
        if (!descripcion.equalsIgnoreCase("NA")){
            accion.isElementVisible(by(format(XPATH_DESCRIPCION,descripcion)),1,getClass());
        }
        accion.isElementVisible(by(format(XPATH_TIPO_TRANSACCION,tipoTransaccion)),1,getClass());
        accion.isElementVisible(by(format(XPATH_MONTO,moneda,monto)),1,getClass());
        if (!comision.equalsIgnoreCase("NA")){
            accion.isElementVisible(by(format(XPATH_MONEDA_COMISION,comision)),1,getClass());
        }
        if (!impuesto.equalsIgnoreCase("NA")){
            accion.isElementVisible(by(format(XPATH_MONEDA_IMPUESTO,impuesto)),1,getClass());
        }
    }



    public void validarPagoInstante(String productOrigen, String productDestinNombre, String productDestino, boolean esEmpleado,String descripcion, String montoMoneda, String monto,String comisionMoneda,String comision, String impuestoMoneda, String impuesto){
        accion.isElementVisible(by(format(XPATH_PRODUCTO_ORIGEN,productOrigen)),1,getClass());
        accion.isElementVisible(by(format(XPATH_PRODUCTO_DESTINO_ALIAS,productDestinNombre,productDestino)),1,getClass());
        if (!descripcion.equals("") && !descripcion.equalsIgnoreCase("NA")){
            accion.isElementVisible(by(format(XPATH_DESCRIPCION,descripcion)),1,getClass());
        }
        if (esEmpleado){
            accion.isElementVisible(by(format(XPATH_COMISION,comisionMoneda,"0.00")),1,getClass());
        }else {
            accion.isElementVisible(by(format(XPATH_COMISION,comisionMoneda,comision)),1,getClass());
        }
        accion.isElementVisible(by(format(XPATH_MONTO,montoMoneda,monto)),1,getClass());

        accion.isElementVisible(by(format(XPATH_IMPUESTO,impuestoMoneda,impuesto)),1,getClass());

        //referencia
    }

    private String getEstadoTransaccion(){
        return accion.getText(labelStatus,15,getClass(),false);
    }

    public PoVoucherPage imprimirEstadoTransaccion(boolean crearReporte){
        if (getEstadoTransaccion().equalsIgnoreCase("En proceso") || getEstadoTransaccion().equalsIgnoreCase("Completada")){
            accion.crearPaso("Estado de transaccion: " + getEstadoTransaccion() + "<br>Numero confirmacion: " + getNumeroConfirmacion(false),true, crearReporte,false);
        }else if (getEstadoTransaccion().equalsIgnoreCase("Rechazada")){
            accion.crearPaso("Estado de transaccion: <br>" + getEstadoTransaccion() +" "+ accion.getText(mensajErrorRechazo,1, getClass(), false),true,crearReporte,false);
        }
        return this;
    }

    private float total =0;

    /**
     * Permite verificar que se muestre el monto.
     * @param monto
     */
    public void validarMonto(String monto){
        String mont = accion.getText(by(format(XPATH_MONTO_COMPLETO, monto)),1,getClass(), false)
                .replace("US$ ","")
                .replace("EU$ ","")
                .replace("RD$ ","");
        System.out.println("MONTO: " + mont);
        total = total + Float.parseFloat(mont) ;
    }

    /**
     * La comisión se cobra a los usuarios que no son empleados internos.
     * @param comision
     */
    public PoVoucherPage validarComision(String comision){
        String comisio;
        if (!Persistencia.getInstance().esEmpleado(DashboardPage.getPage().getNombreCliente())){
            comisio = accion.getText(by(format(XPATH_MONEDA_COMISION,comision)),1,getClass(), false)
                    .replace("US$ ","")
                    .replace("EU$ ","")
                    .replace("RD$ ","");
            System.out.println("COMISION: " + comisio);

            total =  total + Float.parseFloat(comisio);
        }
        return this;
    }

    public void calcularTotal(){
        String result = accion.getText(by(format(XPATH_TOTAL, Utilidad.convertStringToMoneyFormat(String.valueOf(total)))),1,getClass(),false);
        System.out.println("Resultado de total");
        //XPATH_TOTAL
    }

    public PoVoucherPage validarImpuesto(String impuesto){
        String impuest;
        if (!impuesto.equalsIgnoreCase("NA")){
            impuest = accion.getText(by(format(XPATH_MONEDA_IMPUESTO,impuesto)),1,getClass(),false)
                    .replace("US$ ","")
                    .replace("EU$ ","")
                    .replace("RD$ ","");
            total = total + Float.parseFloat(impuest);

            System.out.println("IMPUESTO: " + impuest);
            System.out.println("TOTAL: " + total);
        }
        return this;
    }

    public PoVoucherPage validarMonto(String monto, String moneda){
        accion.getText(by(format(XPATH_MONTO,moneda, Utilidad.darFormatoMonedaFinal( monto ))),1,getClass(),false);
        total = total + Float.parseFloat(monto);
        return this;
    }

    public PoVoucherPage validarDescripcion(String descripcion){
        if (  !descripcion.equals("") && !descripcion.equalsIgnoreCase("NA")){
            accion.getText(by(format(XPATH_DESCRIPCION, descripcion)),1,getClass(),false);
        }
        return this;
    }


    public PoVoucherPage validarMontoDescripcion(String monto, String descripcion){
        //Utilidad.redondearFlotante(monto)
        String mont = accion.getText(by(format(XPATH_MONTO_COMPLETO, Utilidad.darFormatoMonedaFinal( monto ))),1,getClass(), false)
                .replace("US$ ","")
                .replace("EU$ ","")
                .replace("RD$ ","");

        System.out.println("MONTO: " + mont);
        total = total +  Float.parseFloat(monto);
        if (  !descripcion.equals("") && !descripcion.equalsIgnoreCase("NA")){
            accion.getText(by(format(XPATH_DESCRIPCION, descripcion)),1,getClass(),false);
        }
        System.out.println("El total es: " + Utilidad.darFormatoMonedaFinal(String.valueOf(total)) );
        return this;
    }


    public String getStatus(boolean crearReporte){
        String codigoTransaccion = accion.getText(labelStatus,5,getClass(),false);
        accion.crearPaso(codigoTransaccion,true, crearReporte,false);
        return codigoTransaccion;
    }

    public String getNumeroConfirmacion(boolean crearReporte){
        String numeroConfirmacion = accion.getText(labelNumConfirmacion,2,getClass(),false);
        accion.crearPaso(numeroConfirmacion,true,crearReporte,false);
        return numeroConfirmacion.replace("Número de confirmación:","");
    }


}
