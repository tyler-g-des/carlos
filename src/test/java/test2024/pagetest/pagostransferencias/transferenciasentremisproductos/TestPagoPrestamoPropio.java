package test2024.pagetest.pagostransferencias.transferenciasentremisproductos;

import basetest.BaseTest;
import com.bhd.ibp.pages.pagostransferencias.POVoucherPage;
import data.GenerarData;
import org.testng.annotations.Test;
import pages.login.POLoginPage;

public class TestPagoPrestamoPropio extends BaseTest {

    @Test(description = "Pago Prestamo propio -> Disminucion monto de la cuota")
    void pagoPrestamoPropioDisminucionMontoCuota(){
        //POVoucherPage page =
                POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301007070","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .pagoPrestamoPropio(
                        "09769940011",
                        "4259077",
                        true,
                        false,
                        new GenerarData().getNumRandomString(2),
                        "Automatizacion IBP",
                        true)
                .imprimirTitulo(true);

    }

    @Test(description = "Pago Prestamo propio -> Disminucion cantidad de cuotas")
    void pagoPrestamoPropioDisminucionCantidadCuotas(){
        //POVoucherPage page =
                POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301007070","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .pagoPrestamoPropio(
                        "09769940011",
                        "4259077",
                        true,
                        false,
                        new GenerarData().getNumRandomString(2),
                        "Automatizacion IBP",
                        true)
                .imprimirTitulo(true);

    }
}
