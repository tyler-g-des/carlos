package test2024.pagetest.pagostransferencias;

import basetest.BaseTest;
import org.testng.annotations.Test;
import pages.login.POLoginPage;

public class TestTransferenciasRegionalesSIPA extends BaseTest {

    @Test(description = "Transferencias")
    void transferenciaRegionalSIPA(){
        POLoginPage.getInstance( getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransferenciasRegionalesSIPA(false)
                .realizarTransferencia(
                        "05605070011",
                        "Carlos Loyola",
                        "123456789",
                        "COSTA RICA",
                        "Mi calle",
                        "carlos_loyola@bhd.com.do",
                        "BANCO DE COSTA RICA",
                        "225588997777",
                        "7",
                        "Mi descripcion",
                        true)
                .imprimirTitulo(true);
    }
}
