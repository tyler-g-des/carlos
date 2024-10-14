package test2024.pagetest.pagostransferencias.transferenciasentremisproductos;

import basetest.BaseTest;
import data.GenerarData;
import org.testng.annotations.Test;
import pages.login.POLoginPage;

public class TestAvanceEfectivoTCACuenta extends BaseTest {

    @Test(description = "Avance de Efectivo Tarjeta de credito - A Cuenta corriente")
    void avanceEfectivoTC_CuentaCorriente(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("40223165842","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .avanceEfectivoTarjetaCredito(
                        "4517001602323681",
                        "14165350036",
                        "5",
                        "Mi avance Efectivo TC",
                        false,
                        true)
                .imprimirTitulo(true);
    }

    @Test(description = "Avance Efectivo Tarjeta de credito - A Cuenta ahorro")
    void avanceEfectivoTC_CuentaAhorro(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("40223165842","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .avanceEfectivoTarjetaCredito(
                        "4517001284953441",
                        "14165350028",
                        new GenerarData().getNumRandomString(2),
                        "Mi avance Efectivo TC",
                        false,
                        true)
                .imprimirTitulo(true);
    }
}
