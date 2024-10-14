package test2024.pagetest.pagostransferencias.pinpesos;

import basetest.BaseTest;
import data.GenerarData;
import org.testng.annotations.Test;
import pages.login.POLoginPage;

public class TestTransferenciasPinPesos extends BaseTest {

    @Test(description = "De cuenta ahorro -> Beneficiario inscrito")
    void transferenciaDeCuentaAhorro_BeneficiarioInscrito(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesPINPesos(true)
                .realizarTransferenciaBeneficiarioInscrito(
                        "00060330051",
                        "8091234567",
                        "100",
                        "Pin pesos" + GenerarData.createDescripcion(),
                        false,
                        true)
                .imprimirTitulo(true);
    }

    @Test(description = "De cuenta corriente -> Beneficiario inscrito")
    void transferenciaDeCuentaCorriente_BeneficiarioInscrito(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("03102674383","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesPINPesos(true)
                .realizarTransferenciaBeneficiarioInscrito(
                        "00480830024",
                        "8295623475",
                        "100",
                        "Pin pesos" + GenerarData.createDescripcion(),
                        false,
                        true)
                .imprimirTitulo(true);
    }


}
