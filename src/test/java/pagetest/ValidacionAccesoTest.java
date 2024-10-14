package pagetest;

import basetest.BaseTest;
import org.testng.annotations.Test;
import pages.POVoucherPage;
import pages.dashboard.DashboardLogic;
import pages.login.LoginLogic;
import pages.pyt.transferencias.entremisproductos.PoEntreCuentas;
import pages.validacionacceso.POValidacionAcceso;

public class ValidacionAccesoTest extends BaseTest {

    //@Test(description = "Valicar preguntas seguridad correctamente")
    void validarPreguntasSeguridadCorrectamente(){
        LoginLogic.get().iniciarSesion("40221583491","1111",false);
        new POValidacionAcceso(getDriver()).validarAcceso(true);
        String result = DashboardLogic.getLogica().getTitulo360();

        System.out.println(result);
    }

    //00111435384-Jose Quintana - Validar preguntas de seguridad
    //00100014182-Placida Soliman - Configurar preguntas de seguridad
    //@Test(description = "Validar preguntas de seguridad")
    void validarPreguntasSeguridad(){
        LoginLogic.get().iniciarSesion("00100965995","1111", false); //00100014182
        String result = new POValidacionAcceso( getDriver() ).validarAcceso(true)
                .irTransaccionesEntreMisProductos(true)
                .pruebaNuevoFormulario(
                        "00060330051",
                        "05526370041",
                        "10",
                        "descripec",
                        false,
                        true);

        System.out.println(result);
        //System.out.println(Persistencia.getInstance().getDocumentNumber("Placida Soliman"));
    }

    //@Test(description = "Validar preguntas de seguridad")
    void validarPreguntasSeguridad2(){
        LoginLogic.get().inicioSesionGenerico("00100965995","1111", false); //00100014182
        String result = new POValidacionAcceso( getDriver() ).validarAcceso(true)
                .irTransaccionesEntreMisProductos(true)
                .pruebaNuevoFormulario(
                        "00060330051",
                        "05526370041",
                        "10",
                        "descripec",
                        false,
                        true);

        System.out.println(result);
        //System.out.println(Persistencia.getInstance().getDocumentNumber("Placida Soliman"));
    }

    @Test(description = "Final - Sin Canje Dolar")
    void finalSinCanjeDolarSacadoDeCuenta(){
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


    //@Test(description = "Usuario Reseteado")
    void usuarioReseteado(){
        LoginLogic.get().iniciarSesion("00100014182","1111", false); //00100014182
        new POValidacionAcceso( getDriver() ).validarAcceso(true);
        //System.out.println(Persistencia.getInstance().getDocumentNumber("Placida Soliman"));
    }
}
