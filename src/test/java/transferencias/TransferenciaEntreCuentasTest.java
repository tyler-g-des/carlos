package transferencias;

import basetest.BaseTest;
import data.GenerarData;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.POVoucherPage;
import pages.login.LoginLogic;
import pages.pyt.transferencias.entremisproductos.PoEntreCuentas;
import pages.pyt.voucher.PoVoucherPage;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 13/06/2024 1:08 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TransferenciaEntreCuentasTest extends BaseTest {

    // 00101514420-CA: DOP1 - USD2 - EUR1 - ( [USD->RD], [RD-USD], [DOP->EUR], [EUR-> DOP] )

    @Test(description = "Tranferencia entre cuentas corrientes")
    void transferenciaEntreCuentasCorrientes(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00117810267","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "07005310011",
                        "15229330014",
                        "15",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Test(description = "Tranferencia de Cuenta Corrente -> Cuenta ahorro Pesos $RD")
    void transferenciaDeCuentaCorrienteCuentaAhorroPesos(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00108800582","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "18404910010",
                        "14048220024",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Test(description = "Tranferencia de Cuenta Corrente -> Cuenta ahorro Dolar $US")
    void transferenciaDeCuentaCorrienteCuentaAhorroDolar(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00103705216","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "00381060047",
                        "14730050017",
                        "25",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Ignore // Contrato Compartir Informacion
    @Test(description = "Tranferencia de Cuenta Corrente -> Cuenta ahorro Euro $EU")
    void transferenciaDeCuentaCorrienteCuentaAhorroEuro(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00101005171","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "01356050050",
                        "20194260016",
                        "25",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Test(description = "Tranferencia de Cuenta ahorro Pesos $RD -> Cuenta Corrente")
    void transferenciaCuentaAhorroPesosCuentaCorriente(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00116617382","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "08106410047",
                        "00003330010",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Ignore // Contrato Compartir Informacion
    @Test(description = "Tranferencia de Cuenta ahorro Pesos $RD -> Cuenta ahorro Pesos $RD") // ❌ Usuario Expirado Resetear
    void transferenciaCuentaAhorroPesosCuentaAhorroPesos(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00100599885","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "02007620031",
                        "10710820015",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Ignore // Contrato Compartir Informacion
    @Test(description = "Tranferencia de Cuenta ahorro Pesos $RD -> Cuenta ahorro Dolar $US")
    void transferenciaCuentaAhorroPesosCuentaAhorroDolar(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00112627328","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "01053670071",
                        "01053670063",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Ignore // Contrato Compartir Informacion y no se visualiza cuenta destino
    @Test(description = "Tranferencia de Cuenta ahorro Pesos $RD -> Cuenta ahorro Euro $EU")
    void transferenciaCuentaAhorroPesosCuentaAhorroEuro(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00100846039","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "04987450041",
                        "04987450067",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Test(description = "Tranferencia de Cuenta ahorro Dolar $US -> Cuenta Corriente")
    void transferenciaCuentaAhorroDolarCuentaAhorroCorriente(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00100838234","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "01891170013",
                        "01891170021",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Ignore // Contrato Compartir Informacion
    @Test(description = "Tranferencia de Cuenta ahorro Dolar $US -> Cuenta Ahorro Pesos $RD")
    void transferenciaCuentaAhorroDolarCuentaAhorroPesos(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00100621382","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "09175820024",
                        "09175820016",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Ignore // Contrato Compartir Informacion
    @Test(description = "Tranferencia de Cuenta ahorro Dolar $US -> Cuenta Ahorro Dolar $US")
    void transferenciaCuentaAhorroDolarCuentaAhorroDolar(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("03700212685","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "01618910021",
                        "01071050021",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Ignore // Contrato Compartir Informacion
    @Test(description = "Tranferencia de Cuenta Ahorro Euro $EU -> Cuenta Corriente") // Password Expirado ❌
    void transferenciaCuentaAhorroEuroCuentaCorriente(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00102484946","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "12525570013",
                        "12525570030",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Ignore // Contrato Compartir Informacion
    @Test(description = "Tranferencia de Cuenta Ahorro Euro $EU -> Cuenta Ahorro Pesos $RD")
    void transferenciaCuentaAhorroEuroCuentaAhorroPesos(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00112062245","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "12830930011",
                        "04504650010",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }

    @Ignore // Falta data valida
    @Test(description = "Tranferencia de Cuenta Ahorro Euro $EU -> Cuenta Ahorro Euro $EU")
    void transferenciaCuentaAhorroEuroCuentaAhorroEuro(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00112062245","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "12830930011",
                        "04504650010",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }


















    @Test(description = "Tranferencia de Cuenta ahorro $RD -> Cuenta Corrente")
    void prueba(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("00100621382","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "08106410047",
                        "00003330010",
                        "30",
                        "automatizacion IBP " + new GenerarData().getNumRandomString(3),
                        true,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }




    //@Test(description = "Final - Sin Canje Dolar")
    void ejemplo(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("03102674383","1111",true)
                .irTransaccionesEntreMisProductos(true)
                .transferenciaCuentasFechaHoy(
                        "00480830059",
                        "18750450017",
                        "5",
                        "descripcion",
                        false,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }





    //@Test(description = "Formulario transferencia entre cuentas de cuenta dolar a cuenta Dolar")
    void transferenciaSinCanjeCuentaAhorroDolarACuentaAhorroDolar(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00101514420","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .pruebaNuevoFormulario(
                        "00056210059",
                        "07741080014",
                        "100",
                        "DEscripcopnm",
                        true,
                        true);
    }

    //@Test(description = "Prueba Nuevo formulario entre cuentas")
    void pruebaNuevoFormularioCanjePesosADolar(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00101514420","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .pruebaNuevoFormulario(
                        "00056210016",
                        "07741080014",
                        "100",
                        "DEscripcopnm",
                        true,
                        true);
    }

    //@Test(description = "Prueba Nuevo formulario entre cuentas")
    void pruebaNuevoFormularioCanjeDolarAPesos(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00101514420","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .pruebaNuevoFormulario(
                        "07741080014",
                        "00056210016",
                        "100",
                        "DEscripcopnm",
                        true,
                        true);
    }

    //@Test(description = "Prueba Nuevo formulario entre cuentas")
    void pruebaNuevoFormularioCuentaCorrienteCuentaAhorrosRD(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00101514420","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .pruebaNuevoFormulario(
                        "07720870015",
                        "02851130040",
                        "100",
                        "DEscripcopnm",
                        true,
                        true);
    }

    //@Test(description = "Transferencia Cuenta corriente -> Cuenta ahorro Pesos")
    void transferenciaCuentaCorrienteACuentaAhorroPesos(){
        String actual = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102674383","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .pruebaNuevoFormulario(
                        "00480830024",  // CA-RD: 00480830105, CC-RD: 00480830024
                        "00480830105",
                        "150",
                        "DEscripcopnm",
                        true,
                        true);

        String esperado = "En proceso, Completado";
        Assert.assertTrue( esperado.contains( actual));
        String numConfirm =  PoVoucherPage.getNewPage().getNumeroConfirmacion(true);
        System.out.println(numConfirm);
    }

    //@Test(description = "Final - Sin Canje Pesos")
    void finalSinCanjePesos(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102674383","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "00480830024",
                        "00480830105",
                        "15",
                        "descripcion",
                        false,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }


    //@Test(description = "Final - Sin Canje Dolar") // Bueno
    void finalSinCanjeDolar(){
        PoEntreCuentas cuentas = LoginLogic.get()
                .inicioSesionGenerico("03102674383","1111",false)
                .irTransaccionesEntreMisProductos(false)
                .transferenciaCuentasFechaHoy(
                        "00480830059",
                        "18750450017",
                        "5",
                        "descripcion",
                        false,
                        true);

        POVoucherPage vouver = new POVoucherPage(getDriver());
        vouver.imprimirMensajeAcontinuacion(true);
        vouver.verificarTransferenciaEntreCuentas(cuentas);
    }


}
