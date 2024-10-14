package test2024.pagetest.pagostransferencias.bhdotrosbancos;

import basetest.BaseTest;
import data.GenerarData;
import org.testng.annotations.Test;
import pages.login.POLoginPage;

public class TestTransferenciasATerceroEnBHD extends BaseTest {

    @Test(description = "Transferencia CTA Ahoro Pesos -> Cuenta Corriente")
    void transferenciaCuentAhorroPesosACuentaCorriente(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false)
                .transferenciaATerceroEnBHDInscrito(
                        "00060330051",
                        "07269510018",
                        new GenerarData().getNumRandomString(2),
                        "NA",
                        "Mi descripcion de prueba",
                        true,
                        true)
                .verificarTransferenciaTercero(true);

    }

    @Test(description = "Transferencia CTA Ahoro Pesos -> Cuenta ahorro pesos")
    void transferenciaCuentAhorroPesosACuentaAhorroPesos(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false)
                .transferenciaATerceroEnBHDInscrito(
                        "00060330051",
                        "15273480015",
                        new GenerarData().getNumRandomString(2),
                        "NA",
                        "Mi descripcion de prueba",
                        true,
                        true)
                .verificarTransferenciaTercero(true);

    }

    @Test(description = "Transferencia CTA Ahoro Dolar -> Cuenta ahorro Dolar")
    void transferenciaCuentAhorroDolarACuentaAhorroDolar(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false)
                .transferenciaATerceroEnBHDInscrito(
                        "05605070011",
                        "06984730045",
                        new GenerarData().getNumRandomString(2),
                        "NA",
                        "Mi descripcion de prueba",
                        true,
                        true)
                .verificarTransferenciaTercero(true);

    }

    @Test(description = "Transferencia CTA Ahoro Euro -> Cuenta ahorro Euro")
    void transferenciaCuentAhorroEuroACuentaAhorroEuro(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00101391548","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false)
                .transferenciaATerceroEnBHDInscrito(
                        "02016990127",
                        "12525570013",
                        new GenerarData().getNumRandomString(2),
                        "NA",
                        "CA EUR a EUR "+ GenerarData.createDescripcion(),
                        true,
                        true)
                .verificarTransferenciaTercero(true);


    }

    @Test(description = "Transferencia CTA Ahoro Pesos -> Tarjeta de credito")
    void transferenciaCuentAhorroPesosATarjetaCredito(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false)
                .transferenciaATerceroEnBHDInscrito(
                        "00060330051",
                        "4076733115760867",
                        new GenerarData().getNumRandomString(2),
                        "NA",
                        "Mi descripcion de prueba",
                        true,
                        true)
                .verificarTransferenciaTercero(true);

    }

    @Test(description = "Transferencia CTA Ahoro Pesos -> Prestamo AS400")
    void transferenciaCuentAhorroPesosAPrestamoAS400(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false)
                .transferenciaATerceroEnBHDInscrito(
                        "00060330051",
                        "4104553",
                        new GenerarData().getNumRandomString(2),
                        "NA",
                        "Mi descripcion de prueba",
                        true,
                        true)
                .verificarTransferenciaTercero(true);

    }

    @Test(description = "Transferencia CTA Ahoro Pesos -> Crediclick T24")
    void transferenciaCuentAhorroPesosACrediclickT24(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false)
                .transferenciaATerceroEnBHDInscrito(
                        "00060330051",
                        "11267971",
                        new GenerarData().getNumRandomString(2),
                        "NA",
                        "Mi descripcion de prueba",
                        true,
                        true)
                .verificarTransferenciaTercero(true);

    }

    @Test(description = "Transferencia CTA Ahoro Dolar -> Prestmo Dolar T24")
    void transferenciaCuentAhorroDolarAPrestmoDolarT24(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false)
                .transferenciaATerceroEnBHDInscrito(
                        "05605070011",
                        "9209867",
                        new GenerarData().getNumRandomString(2),
                        "NA",
                        "Mi descripcion de prueba",
                        true,
                        true)
                .verificarTransferenciaTercero(true);

    }




}
