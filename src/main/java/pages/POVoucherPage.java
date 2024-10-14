package pages;

import accion.Accion;
import microservicios.dashboard.MsCuentas;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.dashboard.DashboardPage;
import pages.pyt.transferencias.entremisproductos.PoEntreCuentas;

import static java.lang.String.format;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 17/06/2024 2:07 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class POVoucherPage extends BasePage{

    private Accion accion;
    //private MsCuentas msCuentas;
    private String titutloAcontinuacion;
    private PoEntreCuentas transferenciaEntreCuentas;

    public POVoucherPage(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    public void imprimirMensajeAcontinuacion(boolean crearReporte){
        By tituloAcontinuacion = By.xpath("//h3/strong[contains(.,'¡"+ DashboardPage.getPage().getNombreCliente().toUpperCase() +", a continuación presentamos el resultado')]");
        titutloAcontinuacion = accion.getText(tituloAcontinuacion,300, getClass(),crearReporte);
    }

    private By labelStatusTransaccionUnica = By.xpath("//div[@class='voucher-large-summary-transaction']/div/div[div[@class[contains(.,'status')]]]/h5");
    private By labelProductoOrigenUnico = By.xpath("//div[@class='voucher-large-summary-transaction']/div//ibp-voucher-left/div[div[strong[contains(.,'Producto origen:')]  and h5[contains(.,'CUENTA AHORROS US AUT, 135 - 18750450017')] ]]");

    /**
     * Param: Producto origen, Producto destino. <br>
     * Param: nombreProducto o alias - Ejemplo: CTA.AHORROS PERSONAL RD$ <br>
     * Param: numero de producto. Ejemplo: 00480830105
     *  //div[@class='voucher-large-summary-transaction']/div//ibp-voucher-left/div[div[strong[contains(.,'Producto origen:')]  and h5[contains(.,'CUENTA AHORROS US AUT, 135 - 18750450017')]]]
     */
    private String labelProductos = "//div[@class='voucher-large-summary-transaction']/div//ibp-voucher-left/div[div[strong[contains(.,'%s:')]  and h5[contains(.,'%s - %s')]]]";

    /**
     * Tipo de transacciones: Transaccion entre mis productos,
     */
    private String labelTipoTransaccion = "//div[@class='voucher-large-summary-transaction']/div//ibp-voucher-left/div/div[strong[contains(.,'%s')]]  /h5[contains(.,'%s')]";

    /**
     * Labels: Monto:, Total transacción:
     * Valores: RD$ 10.00, USD$ 10.00
     */
    private String labelsDerecho = "//div[@class='voucher-large-summary-transaction']/div//ibp-voucher-right/div[div[strong[contains(.,'%s')]  and h5[contains(.,'%s')] ]]";


    private String contenedorLabelsIzquierdos = "";

    public void verificarTransferenciaEntreCuentas(PoEntreCuentas poEntreCuentas){
        //this.transferenciaEntreCuentas = transferenciaEntreCuentas;
        if (titutloAcontinuacion.contains("tus")){
            
        }else {
            if (poEntreCuentas.isEsCanjeDivisas()){

            }else {
                if (accion.isElementVisibleNoException(labelStatusTransaccionUnica,1)){
                    accion.crearPaso(accion.getText(labelStatusTransaccionUnica,1,getClass(),false),true,true,false);
                }
                accion.getText(By.xpath(format(labelProductos, "Producto origen", poEntreCuentas.getCuentas().buscar(poEntreCuentas.getOrigen()).getTituloProducto(), poEntreCuentas.getOrigen())),1,getClass(),false);
                accion.getText(By.xpath(format(labelProductos,"Producto destino",poEntreCuentas.getCuentas().buscar(poEntreCuentas.getDestino()).getTituloProducto() ,poEntreCuentas.getDestino())),1,getClass(),false);

                if (!poEntreCuentas.getDescripcion().equals("")){
                    accion.getText(By.xpath(format(labelTipoTransaccion,"Descripción:",poEntreCuentas.getDescripcion())),1,getClass(),false);
                }

                accion.getText(By.xpath(format(labelTipoTransaccion,"Tipo de transacción:","Transaccion entre mis productos")),1,getClass(),false);

                accion.getText(By.xpath(format(labelsDerecho,"Monto:",poEntreCuentas.getMontoModal() )),1,getClass(),false);
                accion.getText(By.xpath(format(labelsDerecho,"Total transacción:",poEntreCuentas.getMontoModal() )),1,getClass(),false);
            }
            //verificarTransferenciaCuentasUnica(poEntreCuentas.getOrigen(),poEntreCuentas.getDestino(),"","");

        }
    }
    public void verificarTransferenciaCuentasUnica(String productOrigen, String productoDestino, String monto, String montoEqivalente){
//        accion.getText(By.xpath(format(labelProductos, "Producto origen", msCuentas.buscar(productOrigen).getTituloProducto(), productOrigen)),1,getClass(),false);
//        accion.getText(By.xpath(format(labelProductos,"Producto destino",msCuentas.buscar(productoDestino).getTituloProducto() ,productoDestino)),1,getClass(),false);
//        accion.getText(By.xpath(format(labelTipoTransaccion,"Transaccion entre mis productos")),1,getClass(),false);

        // Lado derecho
        accion.getText(By.xpath(format(labelsDerecho,"Monto:",monto)),1,getClass(),false);
        accion.getText(By.xpath(format(labelsDerecho,"Monto equivalente en pesos",monto)),1,getClass(),false);
        accion.getText(By.xpath(format(labelsDerecho,"Total transacción:",monto)),1,getClass(),false);
        accion.getText(By.xpath(format(labelsDerecho,"Tasa de venta dólares:",monto)),1,getClass(),false);
    }


    public void verificarTransferenciaEntreCuentas(String cuentaOrigen, String cuentaDestino){

        By labelOrigen = By.xpath("//div[@class='voucher-small-summary-transaction']/div[div/strong[contains(.,'Producto origen:')]   and div[h5[contains(.,'CUENTA AHORROS US AUT, 135 18750450017')]]]");
        By labelOrigenDestino = By.xpath("//div[@class='voucher-small-summary-transaction']/div[div/strong[contains(.,'Producto origen:')]   and div[h5[contains(.,'CUENTA CORRIENTE STAFF RD$ 00480830024')]]  and //div[strong[contains(.,'Producto destino:')]]    and div[h5[contains(.,'CTA.AHORROS PERSONAL RD$ 00480830105')]]  ]    ");
        By labelOrigenDestinoMonto = By.xpath("//div[@class='voucher-small-summary-transaction']/div[div/strong[contains(.,'Producto origen:')] and div[h5[contains(.,'CUENTA AHORROS US AUT, 135 18750450017')]] and //div[strong[contains(.,'Producto destino:')]] and div[h5[contains(.,'CUENTA CORRIENTE STAFF RD$ 00480830024')]] and div[strong[contains(.,'Monto:')]] and div[h5[contains(.,'US$ 550.50')]]]");
         //div[@class='voucher-small-summary-transaction']/div[div/strong[contains(.,'Producto origen:')]   and div[h5[contains(.,'CUENTA AHORROS US AUT, 135 18750450017')]]  and //div[strong[contains(.,'Producto destino:')]]]
    }
}
