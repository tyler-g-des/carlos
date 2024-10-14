package pages.detalleproducto;

import accion.Accion;
import data.Persistencia;
import microservicios.dashboard.MsCuentas;
import microservicios.dashboard.MsDashboardTarjetaCredito;
import microservicios.detalleproducto.MsDetalleDeCuenta;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.dashboard.DashboardPage;

import static java.lang.String.format;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 19/06/2024 2:25 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PoDetalleProducto extends BasePage {

    private Accion accion;
    public PoDetalleProducto(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
        numeroDocumento = Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente());
    }


    private By tituloDetalleProductos = By.xpath("//h3[contains(.,'Detalle de mis productos')]");
    private By comboProductos = By.xpath("//div[label[contains(.,'Productos')]  ] // p-dropdown//span");
    private By SpanContains = By.xpath("//strong[contains(.,'ILONKA A.RODRIGUEZ ORTEGA')]");

    private String labelStrongContains = "//strong[contains(.,'%s')]";
    private String labelSpanContains = "//span[contains(.,'%s')]";

    private String producto;
    private String numeroDocumento;



    public void comboProductos(String numeroProducto, boolean crearReporte){
        producto = numeroProducto;
        accion.selectDropdownIBP(comboProductos, numeroProducto,"COMBO PRODUCTOS",20, getClass(), crearReporte);
    }

    // Desde dashboard: 00480830059
    String cuentaDeDashboard = "asda";


    public void consultarDetalleCuenta( String numeroCuenta, boolean crearReporte){
        String imprimir = "";

        MsDetalleDeCuenta dp = new MsDetalleDeCuenta(numeroDocumento, numeroCuenta);
        MsCuentas cuentas = new MsCuentas(numeroDocumento);

        accion.isElementVisible(By.xpath(format(labelSpanContains, "Número de producto:")),15,getClass());

        imprimir = "ALIAS: " + accion.getText(By.xpath(format(labelStrongContains, cuentas.buscar(numeroCuenta).getAlias())),0,getClass(),false)+ "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Número de producto:")),0,getClass(),false).replace(":", ": ");
        imprimir = imprimir + accion.getText(By.xpath(format(labelStrongContains, cuentas.getNumProducto())),0,getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Cuenta estándar:")),0,getClass(),false).replace(":", ": ");

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, dp.getRegionalNumber())),0,getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Tipo de cuenta:")),0, getClass(),false).replace(":", ": ");
        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, cuentas.getTiposProducto() )),0, getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Estado:")),0,getClass(),false).replace(":", ": ");
        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, cuentas.getEstadoProducto().toUpperCase() )),0, getClass(),false) + "<br><br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Balance disponible:")),0,getClass(),false).replace(":", ": ");
        imprimir = imprimir + accion.getText(By.xpath(format(labelStrongContains, cuentas.getMoneda() + "$ " + cuentas.getBalance() )),0, getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Monto en tránsito:")),0,getClass(),false).replace(":", ": ");
        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, cuentas.getMoneda() + "$ " + cuentas.getBalanceEnTransito())),0,getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Balance actual:")),0,getClass(),false).replace(":", ": ");
        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, cuentas.getMoneda() + "$ " + cuentas.getBalance())),0,getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Balance promedio del mes:")),0,getClass(),false).replace(":", ": ");
        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, cuentas.getMoneda() + "$ " + dp.getBalancePromedioMes())),0,getClass(),false) + "<br>";
        accion.crearPaso(imprimir, true, crearReporte, crearReporte);
    }

    public void consultarDetalleTarjetaCredito(String numeroTC, boolean crearReporte){
        String imprimir = "";
        String msgPesos = "Alias: %s.  Número de producto: %s<br><br>" +
                "%s <br>" +
                "%s %s<br>" +
                "%s %s<br>" +
                "%s %s<br>" +
                "%s %s<br>" +
                "%s %s<br>" +
                "%s %s<br>" +
                "%s %s<br>";

        String msgDolar = "";

        MsDashboardTarjetaCredito dashboardTC = new MsDashboardTarjetaCredito(numeroDocumento);

        String alias = "ALIAS: " + accion.getText(By.xpath(format(labelStrongContains, dashboardTC.buscar(numeroTC).getAlias())),0,getClass(),false)+ "<br>";
        accion.getText(By.xpath(format(labelSpanContains, dashboardTC.getTCEnmascarada())),0,getClass(),false);

        String estadoProducto = accion.getText(By.xpath(format(labelSpanContains, dashboardTC.getEstadoProducto())),0,getClass(),false); // Defecto no se esta visualizando.

        String labelBalanceDisponibleCompras = accion.getText(By.xpath(format(labelSpanContains, "Número de producto:")),0,getClass(),false).replace(":", ": ");

        String labelSaldoALaFecha = accion.getText(By.xpath(format(labelSpanContains, "Número de producto:")),0,getClass(),false).replace(":", ": ");

        //imprimir = imprimir + accion.getText(By.xpath(format(labelStrongContains, cuentas.getNumProducto())),0,getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Cuenta estándar:")),0,getClass(),false).replace(":", ": ");

      //  imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, dp.getRegionalNumber())),0,getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Tipo de cuenta:")),0, getClass(),false).replace(":", ": ");
    ///    imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, cuentas.getTipoProducto() )),0, getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Estado:")),0,getClass(),false).replace(":", ": ");
   //     imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, cuentas.getEstadoProducto().toUpperCase() )),0, getClass(),false) + "<br><br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Balance disponible:")),0,getClass(),false).replace(":", ": ");
  //      imprimir = imprimir + accion.getText(By.xpath(format(labelStrongContains, cuentas.getMoneda() + "$ " + cuentas.getBalance() )),0, getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Monto en tránsito:")),0,getClass(),false).replace(":", ": ");
   //     imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, cuentas.getMoneda() + "$ " + cuentas.getBalanceEnTransito())),0,getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Balance actual:")),0,getClass(),false).replace(":", ": ");
    //    imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, cuentas.getMoneda() + "$ " + cuentas.getBalance())),0,getClass(),false) + "<br>";

        imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, "Balance promedio del mes:")),0,getClass(),false).replace(":", ": ");
   //     imprimir = imprimir + accion.getText(By.xpath(format(labelSpanContains, cuentas.getMoneda() + "$ " + dp.getBalancePromedioMes())),0,getClass(),false) + "<br>";
        accion.crearPaso(imprimir, true, crearReporte, crearReporte);
    }






}
