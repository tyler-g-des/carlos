package test2024.pagetest.pagostransferencias.transferenciasentremisproductos;

import basetest.BaseTest;
import data.GenerarData;
import org.testng.annotations.Test;
import pages.login.POLoginPage;

public class TestTransferenciasEntreCuentas extends BaseTest {

    @Test(description = "Transferencia CC Pesos -> CA", priority = 2)
    public void transferenciaCuentaCorriente_CuentaAhorroPesos(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00103705216","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .transferenciaentreCuentaHoy(
                        "02236850016",
                        "02236850059",
                        new GenerarData().getNumRandomString(2),
                        GenerarData.createDescripcion(),
                        false,
                        true)
                .verificarTransferenciaEntreCuentas(true);
    }

    @Test(description = "Transferencia CC -> CA Dolar", priority = 3)
    public void transferenciaCuentaCorriente_cuentaAhorroDolar(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00108504028","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .transferenciaentreCuentaHoy(
                        "02829150029",
                        "22641460012",
                        new GenerarData().getNumRandomString(2),
                        GenerarData.createDescripcion(),
                        false,
                        true)
                .verificarTransferenciaEntreCuentas(true);
    }

    @Test(description = "Transferencia CA Pesos -> CC", priority = 5)
    public void transferenciaCuentaAhorroPesos_CuentaCorriente(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .transferenciaentreCuentaHoy(
                        "00060330051",
                        "00060330069",//00060330069-Da mensaje expirado
                        new GenerarData().getNumRandomString(2),
                        GenerarData.createDescripcion(),
                        false,
                        true)
                .verificarTransferenciaEntreCuentas(true);
    }


    @Test(description = "Transferencia CA Pesos -> CA Pesos", priority = 6)
    public void transferenciaCuentaAhorroPesos_cuentaAhorroPesos(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00107768012","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .transferenciaentreCuentaHoy(
                        "01508640044",
                        "01508640061",
                        new GenerarData().getNumRandomString(2),
                        GenerarData.createDescripcion(),
                        false,
                        true)
                .verificarTransferenciaEntreCuentas(true);
    }

    @Test(description = "Transferencia CA Pesos -> CA Dolar", priority = 7)
    public void transferenciaCuentaAhorroPesos_cuentaAhorroDolar(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("03700163748","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .transferenciaentreCuentaHoy(
                        "04499540014",
                        "04499540031",
                        new GenerarData().getNumRandomString(1),
                        GenerarData.createDescripcion(),
                        false,
                        true)
                .verificarTransferenciaEntreCuentas(true);
    }


    @Test(description = "Transferencia CA Dolar -> CA Pesos", priority = 10)
    public void transferenciaCuentaAhorroDolar_cuentaAhorroPesos(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("03100283732","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .transferenciaentreCuentaHoy(
                        "03965370047",
                        "03965370098",
                        new GenerarData().getNumRandomString(2),
                        GenerarData.createDescripcion(),
                        false,
                        true)
                .verificarTransferenciaEntreCuentas(true);
    }

    @Test(description = "Transferencia CA Dolar -> CA Dolar", priority = 11)
    public void transferenciaCuentaAhorroDolar_cuentaAhorroDolar(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00108504028","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .transferenciaentreCuentaHoy(
                        "02829150045",
                        "22641460012",
                        new GenerarData().getNumRandomString(2),
                        GenerarData.createDescripcion(),
                        false,
                        true)
                .verificarTransferenciaEntreCuentas(true);
    }



    @Test(description = "Transferencia Super CA Pesos -> CC", priority = 15)
    public void transferenciaSuperCuentaAhorroPesos_cuentaCorriente(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00111671855","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .transferenciaentreCuentaHoy(
                        "04080430022",
                        "04080430014",
                        new GenerarData().getNumRandomString(2),
                        GenerarData.createDescripcion(),
                        false,
                        true)
                .verificarTransferenciaEntreCuentas(true);
    }




}
