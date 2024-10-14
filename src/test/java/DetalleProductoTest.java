import basetest.BaseTest;
import microservicios.dashboard.MsCuentas;
import org.testng.annotations.Test;
import pages.dashboard.DashboardPage;
import pages.detalleproducto.PoDetalleProducto;
import pages.login.LoginLogic;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 19/06/2024 3:58 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class DetalleProductoTest extends BaseTest {

    //@Test(description = "Consultar Cuenta")
    void consultarDetalleCuenta(){
//       MsCuentas cuentas = new MsCuentas("03102674383").buscar("00480830059");
//        System.out.println(cuentas.getEstadoProducto());
//        System.out.println(cuentas.getBalance());

        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102674383","1111",false);
        DashboardPage.getPage().consultarCuenta("21911170015",true);
                //.consultarCuenta("00480830059",false);
    }


    //@Test(description = "Consultar Cuentas")
    void consultarCuentas(){
//       MsCuentas cuentas = new MsCuentas("03102674383").buscar("00480830059");
//        System.out.println(cuentas.getEstadoProducto());
//        System.out.println(cuentas.getBalance());

        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102674383","1111",false);
        DashboardPage.getPage().consultarCuentas(true);
        //.consultarCuenta("00480830059",false);
    }

    //@Test(description = "Ver detalla de Cuenta")
    void verDetalleDeCuenta(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102674383","1111",false);
        //DashboardPage dashboardPage =
                DashboardPage.getPage().verDetalleDeCuenta("00480830059",true);
        //dashboardPage.verDetalleDeCuenta("00480830059",true);

        PoDetalleProducto dp = new PoDetalleProducto(getDriver());
        dp.consultarDetalleCuenta("00480830059",true);

        DashboardPage.getPage().clickMisProductos(true);

        DashboardPage.getPage().consultarCuenta("00480830059",true);

    }


    //@Test(description = "Consultar Tarjeta de credito")
    void consultarTarjetaDeCredito(){

        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00116903048","1111",false);
        DashboardPage.getPage().consultarTarjetaDeCredito("4517003392554947",true);
    }

    @Test(description = "Consultar Tarjetas de credito")
    void consultarTarjetasDeCredito(){

        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00116903048","1111",false);
        DashboardPage.getPage().consultarTarjetasDeCretido(true);
    }

    //@Test(description = "Compartir Cuenta")
    void compartirCuenta(){

        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111435384","1111",false);
        DashboardPage.getPage()
                .compartirCuenta("05447780030",true);
    }

    //@Test(description = "Compartir Cuentas")
    void compartirCuentas(){

        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111435384","1111",false);
        DashboardPage.getPage()
                .compartirCuentas(true);
    }
}
