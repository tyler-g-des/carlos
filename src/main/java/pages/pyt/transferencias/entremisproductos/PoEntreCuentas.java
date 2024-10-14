package pages.pyt.transferencias.entremisproductos;

import accion.Accion;
import basetest.BaseTest;
import data.Persistencia;
import microservicios.MSClientInfoV2;
import microservicios.Utilidad;
import microservicios.dashboard.MsCambioDivisas;
import microservicios.dashboard.MsCuentas;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.PoAccionesRepetitivas;
import pages.dashboard.DashboardPage;

import static java.lang.String.format;
import static pages.XpathComunes.BTN_CONTINUAR;
import static pages.XpathComunes.TXT_TDC;
import static pages.pyt.XpathTransacciones.*;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 23/04/2024 12:33 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PoEntreCuentas extends BasePage {

    private MsCambioDivisas divisas = new MsCambioDivisas();
    private Accion accion = Accion.getAcciones();
    private By leyenda = By.xpath("//legend/span[contains(.,'Transferencia entre cuentas')]");
    private String origen;
    private String destino;
    private MsCuentas productos;
    private MSClientInfoV2 microClintInfo;
    private By leyendaCanjeDivisas = By.xpath("//legend/span[contains(.,'Transferencia entre cuentas (Canje de divisas)')]");
    private By leyendaEntreCuentas = By.xpath("//legend/span[contains(.,'Transferencia entre cuentas')]");
    //private By valorActualMonto =
    private boolean esCanjeDivisas = false;
    private String monto;
    private String montoModal;
    private String moneda;
    private String tasaVentaCompra;
    private String montoEquivalenteString;
    private String numeroDocumento;
    private String descripcion;

    public MsCuentas getCuentas() {
        return productos;
    }

    public boolean isEsCanjeDivisas() {
        return esCanjeDivisas;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getMontoModal() {
        return montoModal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * param: Dólares, Euros
     */
    private String xpathMoneda = "//div[label[contains(.,'Moneda')]] /p-dropdown  //span[contains(.,'%s')]";


    private PoEntreCuentas(WebDriver webDriver) {
        super(webDriver);
    }

    public static PoEntreCuentas getNewPage(){
        return new PoEntreCuentas(BaseTest.getDriver());
    }


    private void setComboTipoTransaccion(boolean crearReporte){
        numeroDocumento = Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente());
        String tipoTransaccion = accion.getText(COMBO_TIPO_TRANSACCION,25, getClass(),false);
        if (!tipoTransaccion.equalsIgnoreCase("Transacciones entre mis productos")){
            accion.selectDropdownIBP(COMBO_TIPO_TRANSACCION,"Transacciones entre mis productos","TIPO DE TRANSACCION",25,getClass(), crearReporte);
        }
    }

    /**
     *
     * //div[span[contains(.,'Tasa de venta dólares:')]  and span[contains(.,'RD$ 55.05')]]
     *
     * Param: dólares, euros
     * Param: tasa: 55.05
     */
    //private String labelTasaVenta = "//div[span[contains(.,'Tasa de venta %s:')]  and span[contains(.,'RD$ %s')]]";

    /**
     * Tasa de venta euros
     * Tasa de compra euros
     *
     * Tasa de venta dólares
     * Tasa de compra dólares
     * dólares
     */
    private String labelTasaCompra = "//div/span[contains(.,'Tasa de compra %s:')]";

    private String labelTasaVenta = "//div/span[contains(.,'Tasa de venta %s:')]";

    /**
     * montoMonedaDivisa
     */
    private String labelMonedaCambio = "//div/span[contains(.,'RD$ %s')]";

    /**
     * Param: dólares, euros
     */
    //private String labelTasaCompra = "//div[span[contains(.,'Tasa de compra %s')]  and span[contains(.,'RD$ %s')]] ";


    /**
     * //div[span[contains(.,'Tasa de venta euros:')]] /span[contains(.,'RD$ 60.35')]
     * //div[span[contains(.,'Monto equivalente:')]] /span[contains(.,'RD$ 6,360.00')]
     * @param monedaTasa euros, dólares
     * @param montoTasa Tasa de la compra o venta de la moneda. Ejemplo: 60.35
     * @param monto monto ingresado.
     */
    private String verificarDivisas(String monedaTasa, String montoTasa, String monto, boolean crearReporte){
        String retorno = "";
        By tasaVenta = By.xpath("//div[span[contains(.,'"+ monedaTasa +":')] and span[contains(.,'RD$ "+Utilidad.darFormatoMonedaFinal( montoTasa )+"')] ]");

        By montoEquivalent = By.xpath("//div[span[contains(.,'Monto equivalente:')] and span[contains(.,'RD$ "+Utilidad.darFormatoMonedaFinal( String.valueOf( Float.parseFloat(montoTasa) * Float.parseFloat(monto) ) )+"')] ]");///span[contains(.,'RD$ " + Utilidad.darFormatoMonedaFinal( String.valueOf( Float.parseFloat(montoTasa) * Float.parseFloat(monto) ) ) + "')]");
        String tasa = accion.getText(tasaVenta,1,getClass(),false);
        String equivalent =  accion.getText(montoEquivalent,1,getClass(),false);
        montoEquivalenteString = equivalent.replaceAll("\\r?\\n","");
        tasaVentaCompra = tasa.replaceAll("\\r?\\n","");
        System.out.println(tasa.replaceAll("\\r?\\n"," "));
        retorno = "TRANSFERENCIA ENTRE CUENTAS (CANJE DE DIVISAS) <br><br>Monto: "  + monto +" "+ accion.getText(COMBO_MONEDA,1,getClass(),false)+"<br>";
        retorno = retorno + tasa +"<br>"+ equivalent;

        System.out.println(retorno.replace("<br>","\n"));
        accion.crearPaso( retorno,true, crearReporte,false);
        return retorno;
    }

    /**
     * Este método valida que el combo moneda tenga la moneda correcta, la leyenda correcta y si es o no
     * una transferencia con canje de divisas. Si es con canje retorna true, de lo contrario retorna false.
     */
    private boolean validarMonedaCanje(){
        String monedaOrigen = productos.buscar(origen).getMoneda();
        String monedaDestino = productos.buscar(destino).getMoneda();

        if (monedaOrigen.equals(monedaDestino)){
            accion.getText(leyendaEntreCuentas,20,getClass(),false);
            esCanjeDivisas = false;
        }else {
            accion.getText(leyendaCanjeDivisas,20,getClass(),false);
            esCanjeDivisas = true;
        }

        if (monedaOrigen.equals("US") || monedaDestino.equals("US")){
            moneda = "US";
            accion.isElementVisible(By.xpath(format(xpathMoneda,"Dólares")),2, getClass());
        }else if (monedaOrigen.equals("EU") || monedaDestino.equals("EU")){
            moneda = "EU";
            accion.isElementVisible(By.xpath(format(xpathMoneda,"Euros")),2, getClass());
        }else {
            moneda = "RD";
            accion.isElementVisible(By.xpath(format(xpathMoneda,"Pesos")),2, getClass());
        }
        return esCanjeDivisas;
    }

    /**
     * Este método permite llenar el formulario para una transferencia entre cuentas, sea con canje o sin canje de divisas.
     * @param cuentaOrigen Obligatorio. Número de la cuenta origen desde donde se hará la transferencia.
     * @param cuentaDestino Obligatorio. Número de cuenta destino a la cual se acreditara el monto transferido desde el origen.
     * @param monto Obligatorio. Monto que se enviara en la transferencia.
     * @param descripcion Opcional. Permite agregar la descripción de la transferencia.
     * @param addTransaccionFavorita Opcional. Al enviar true permite agregar la transferencia como favorita.
     * @param crearReporte Permite crear o no reporte del proceso.
     */
    public void setFormulario(String cuentaOrigen, String cuentaDestino, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        setComboTipoTransaccion(crearReporte);
        //System.out.println("La clave transaccional esta habilitada: " +  microClintInfo.isEnableKeyCardMyProducts());
        accion.selectDropdownIBP(COMBO_ORIGEN,cuentaOrigen,"ORIGEN",20,getClass(),crearReporte);
        accion.selectDropdownIBP(COMBO_DESTINO,cuentaDestino,"DESTINO",20,getClass(),crearReporte);

        origen = accion.getText(COMBO_ORIGEN,1,getClass(),false).split(" \\| ")[1];
        destino = accion.getText(COMBO_DESTINO,1,getClass(),false).split(" \\| ")[1];
        productos = new MsCuentas(numeroDocumento);
        //productos = new MsCuentas(Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente()));
        System.out.println("Nombre Actual Producto Origen: " +productos.buscar(origen).getTituloProducto() );
        boolean siEsCanje = validarMonedaCanje();

        accion.writeOn(TXT_MONTO, monto,"CAMPO MONTO",5, getClass(), crearReporte);

        this.monto = accion.getAttribute(TXT_MONTO,"aria-valuenow",1,getClass());
        if (siEsCanje){ // Si es canje de divisas
            if (productos.buscar(origen).getMoneda().equals("RD")){

                if (productos.buscar(destino).getMoneda().equals("US")){
                    verificarDivisas("Tasa de compra dólares", divisas.getVentaEnDolar(), this.monto, crearReporte );
                }else {
                    verificarDivisas("Tasa de compra euros",divisas.getVentaEnEuro(), this.monto, crearReporte);
                }
            }else {
                if (productos.buscar(origen).getMoneda().equals("US")){
                    verificarDivisas("Tasa de venta dólares", divisas.getCompraEnDolar(), this.monto, crearReporte);
                }else {
                    verificarDivisas("Tasa de venta euros",divisas.getCompraEnEuro(), this.monto, crearReporte);
                }
            }
        }

        accion.writeOn(TXT_DESCRIPCION,descripcion,"CAMPO DESCRIPCION",1, getClass(), crearReporte);
        this.descripcion = accion.getText(TXT_DESCRIPCION,1,getClass(),false);
        accion.selectCheckBox(CHECK_ADD_TRANSACCION_FAVORITA,"ADICIONAR TRANSACCION FAVORITA",addTransaccionFavorita,1,getClass(),crearReporte);
        if (esCanjeDivisas){
            accion.getText(disclaimerConCanje,2,getClass(),false); // Activar luego.
        }else {
            accion.getText(disclaimerSinCanje,2,getClass(),false); // Activar luego.
        }
    }

    private By btnRegresar = By.xpath("//button[span[contains(.,'Regresar')]]");
    private By btnRealizarTransaccion = By.xpath("//button[span[contains(.,'Realizar transacción')]]");

    private By tituloConfirmarTransaccion = By.xpath("//div[@class[contains(.,'modal')]]/div/div/div[contains(.,'Confirma tu transacción')]");
    public void modalComfirmacionTransaccion(String codigoTDC,boolean crearReporte){
        microClintInfo = new MSClientInfoV2(numeroDocumento).newQuery();
        if (esCanjeDivisas){
            //accion.getText(disclaimerConCanje,2,getClass(),false); // Activar luego.
        }else {
            //accion.getText(disclaimerSinCanje,2,getClass(),false); // Activar luego.
        }
        accion.clickOn(BTN_CONTINUAR,"BOTON CONTINUAR",1, getClass(), crearReporte, crearReporte); // Activar luego.
        By labelOrigen = By.xpath("//div[@role='dialog']//td[@id='let-debitProduct'] /span[contains(.,'"+productos.buscar(origen).getTituloProducto()+"') and contains(.,'"+origen+"')]");
        By labelDestino = By.xpath("//div[@role='dialog']//td[@id='let-creditProduct'] /span[contains(.,'"+productos.buscar(destino).getTituloProducto()+"') and contains(.,'"+destino+"')]");
        accion.getText(labelOrigen,1,getClass(),false);
        accion.getText(labelDestino,1,getClass(),false);
        //accion.getText(tituloConfirmarTransaccion, 1, getClass(), crearReporte);
        if (esCanjeDivisas){
            // Tasa -> Total conversion -> Monto en dolar o Euro.
            By labelMonto = By.xpath("//div[@role='dialog']//td[@id='let-amount' and small[contains(.,'"+tasaVentaCompra.split(":")[0]+": "+tasaVentaCompra.split(":")[1]+"')] and small[contains(.,'"+montoEquivalenteString.replace("Monto equivalente:","")+"')] ] /small [contains(.,'"+moneda + "$ " + this.monto+"')]");
            accion.getText(labelMonto,1,getClass(),false);
            By total = null;
            if (productos.buscar(origen).getMoneda().equals("RD")){
                System.out.println("El origen es pesos");
                total = By.xpath("//div[@role='dialog']//td[@id='let-total-transaction'] /span[contains(.,'"+montoEquivalenteString.replace("Monto equivalente:","")+"')]");
            }else {
                System.out.println("El origen no es pesos");
                total = By.xpath("//div[@role='dialog']//td[@id='let-total-transaction'] /span[contains(.,'"+moneda + "$ " + this.monto+"')]");
            }
            String result = accion.getText(total,1,getClass(),false);
            System.out.println("TOTAL: " + result);


            System.out.println("\n");
            System.out.println("Si es canje");
            System.out.println(moneda + "$ " + this.monto);
            System.out.println(tasaVentaCompra);
        }else {
            System.out.println("No es canje");
            By labelMonto = By.xpath("//div[@role='dialog']//td[@id='let-amount'] /span[contains(.,'"+productos.buscar(origen).getMoneda()+ "$ "+this.monto+"')]");
            montoModal = accion.getText(labelMonto,1,getClass(),false);

        }
        if (microClintInfo.isEnableKeyCardMyProducts()){
            // Llenar TDC.
            System.out.println("Si Esta TDC Activo");
            accion.writeMaskedOn(TXT_TDC,codigoTDC,"CAMPO TDC",2,getClass(),crearReporte);
        }else {
            System.out.println("No esta activo TDC");
        }
        accion.clickOn(btnRealizarTransaccion, "BOTON REALIZAR TRANSACCION",1,getClass(),crearReporte,crearReporte);
        accion.isElementInvisibility(btnRegresar, 10,getClass());
    }

    private By disclaimerConCanje = By.xpath("//p-messages[@severity='info']//p[contains(.,'Por este medio el Cliente autoriza al Banco BHD a debitar de su cuenta el valor de las ventas de divisas a la tasa del día.')]");
    private By disclaimerSinCanje = By.xpath("//p-messages[@severity='info']//p[contains(.,'Esta transacción está sometida a todos los términos y condiciones del contrato de Banca en Línea.')]");

//    private void setProductosSeleccionados(){
//        origen = accion.getText(COMBO_ORIGEN,1,getClass(),false).split(" \\| ")[1];
//        destino = accion.getText(COMBO_DESTINO,1,getClass(),false).split(" \\| ")[1];
//        productos = new MsCuentas(Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente()));
//
//        if (productos.buscar(origen).getMoneda().equals(productos.buscar(destino).getMoneda())){
//            System.out.println(productos.buscar(origen).getMoneda());
//            System.out.println(productos.buscar(destino).getMoneda());
//            System.out.println("LA transferencia es sin Canje");
//        }else {
//            System.out.println("LA transferencia es con Canje");
//            System.out.println(productos.buscar(origen).getMoneda());
//            System.out.println(productos.buscar(destino).getMoneda());
//        }
//        System.out.println("ORIGEN: " + productos.buscar(origen).getNumProducto());
//        System.out.println("DESTINO: " + productos.buscar(destino).getNumProducto());
//    }

    public void setCampos(String comboOrigen, String comboDestino,String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){

        setComboTipoTransaccion(crearReporte);
        accion.selectDropdownIBP(COMBO_ORIGEN,comboOrigen,"ORIGEN",20,getClass(),crearReporte);
        accion.selectDropdownIBP(COMBO_DESTINO,comboDestino,"DESTINO",20,getClass(),crearReporte);
        //setProductosSeleccionados();
        String leyend = accion.getText(leyenda,20,getClass(),false);
        System.out.println(leyend);

        accion.writeOn(TXT_MONTO,monto,"CAMPO MONTO",5, getClass(), crearReporte);
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().validarMoneda();
        String moneda = accion.getText(COMBO_MONEDA,2,getClass(),false);

                String origen = accion.getText(COMBO_ORIGEN,1,getClass(),false);
        String destino = accion.getText(COMBO_DESTINO,1,getClass(),false);
        System.out.println(origen);
        System.out.println(destino);

        if (leyend.equalsIgnoreCase("Transferencia entre cuentas (Canje de divisas)")){

            //float tasa;
            //float total;

            if (origen.contains("RD$") && destino.contains("US") || destino.contains("EU")){
                By tasaCompra = By.xpath(format(labelTasaCompra,moneda.replace("Euros","euros")
                        .replace("Dólares","dólares"))); // ,"63.60"

                //System.out.println("Entro en compra");
                String tasCompra =  accion.getText(tasaCompra,2,getClass(),true);
                //System.out.println(tasCompra);
                //String compra = "";

                if (tasCompra.contains("euros")){
                    //compra = "63.60";
                   //MsCambioDivisas divisas = new MsCambioDivisas();
                    verificarDivisas("Tasa de compra euros",divisas.getVentaEnEuro(), monto, crearReporte);
                    //compra = divisas.getVentaEnEuro();

                    //System.out.println(Utilidad.redondearFlotante(divisas.getVentaEnEuro()) );

                }else {
                    //compra = "55.05";
                    verificarDivisas("Tasa de compra dólares", divisas.getVentaEnDolar(), monto, crearReporte);
                    //System.out.println(Utilidad.redondearFlotante(divisas.getVentaEnDolar()) );
                    //compra = divisas.getVentaEnDolar();
                }

                //By precioCompra = By.xpath(String.format(labelMonedaCambio,compra));
                //accion.getText(precioCompra,1,getClass(),true);
                //tasa = Float.parseFloat( compra  );
                //total = tasa * Float.parseFloat(monto);

                //System.out.println("Monto equivalente: " + Utilidad.darFormatoMonedaFinal(String.valueOf( total ) ) );
                //By equivalente  = By.xpath("//div[span[contains(.,'Monto equivalente:')]]/span[contains(.,'"+Utilidad.darFormatoMonedaFinal(String.valueOf(total))+"')]");
                //accion.getText(equivalente, 1, getClass(), false);

            }else if (origen.contains("US") || origen.contains("EU") && destino.contains("RD")){
                if (origen.contains("EU")){
                    //System.out.println(divisas.getCompraEnEuro());
                    verificarDivisas("Tasa de venta euros",divisas.getCompraEnEuro(), monto, crearReporte);
                }else {
                    //System.out.println(divisas.getCompraEnDolar());
                    verificarDivisas("Tasa de venta dólares",divisas.getCompraEnDolar(), monto, crearReporte);
                }
//                By tasaVenta = By.xpath(String.format(labelTasaVenta,moneda.replace("Euros","euros")
//                                                                           .replace("Dólares","dólares"))); // ,"60.35"
//                String resulta =   accion.getText(tasaVenta,2,getClass(),true);
//                System.out.println(resulta);
            }

        }
        //accion.writeOn(TXT_MONTO,monto,"CAMPO MONTO",1, getClass(), crearReporte);
        accion.writeOn(TXT_DESCRIPCION,descripcion,"CAMPO DESCRIPCION",1, getClass(), crearReporte);
        accion.selectCheckBox(CHECK_ADD_TRANSACCION_FAVORITA,"ADICIONAR TRANSACCION FAVORITA",addTransaccionFavorita,1,getClass(),crearReporte);

    }

    private By tituloConfirmarTuTransaccion = By.xpath("//div[@class[contains(.,'common-modal')]]//div[div[contains(.,'Confirma tu transacción')]]");
    public void modalConfirmacion(MsCuentas cuentaOrigen, MsCuentas cuentaDestino, boolean crearReporte){
        By origen = By.xpath("//div[@role='dialog']//td[@id='let-debitProduct'] /span[contains(.,'"+cuentaOrigen.getTituloProducto()+"') and contains(.,'"+cuentaOrigen.getNumProducto()+"')]");
        By destino = By.xpath("//div[@role='dialog']//td[@id='let-creditProduct'] /span[contains(.,'"+cuentaDestino.getTituloProducto()+"') and contains(.,'"+cuentaDestino.getNumProducto()+"')]");
        accion.getText(tituloConfirmarTuTransaccion,2,getClass(),crearReporte);
        accion.isElementVisible(origen,1,getClass());
        accion.isElementVisible(destino,1,getClass());

        if (cuentaOrigen.getMoneda().equals(cuentaDestino.getMoneda())){
            // se coloca el monto en la moneda / comision, impuesto
        }else {
            if (cuentaOrigen.getMoneda().equals("RD")){
                System.out.println("El origen es pesos");
            }else {
                System.out.println("El Destino es pesos");
            }
        }


    }
}
