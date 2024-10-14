package transferencias;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;

import java.util.Random;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/06/2024 5:14 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TransferenciaInternacionalTest extends BaseTest {


    @Test(description = "TRANSFERENCIA A BENEFICIARIO INSCRITO -> MONEDA EUROS")
    void transferenciaBeneificiarioInscritoMonedaEuros(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .transferenciaInternacional(false)
                .transferenciaBeneficiarioInscrito(
                        "04005280015",
                        "123456789123456789123456789",
                        "OTROS",
                        "Euros",
                        "3",
                        "carlos_loyola@bhd.com.do",
                        "Descripicon automatizacion " + new Random().nextInt(50),
                        true);
    }


    @Test(description = "TRANSFERENCIA NUEVO BENEFICIARIO -> MONEDA EUROS")
    void transferenciaNuevoBeneficiario(){
        String actual = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .transferenciaInternacional(false)
                .transferenciaNuevoBeeneficiario(
                        "04005280015",
                        "Carlos Alberto Loyola",
                        "54325466563",
                        "ANTARTICA",
                        "LOS GIRASOLES",
                        "carlos_loyola@bhd.com.do",
                        "4646446546",
                        "SWIFT",
                        "AAALSARIXXX",
                        true,
                        "1243569788",
                        "ABA",
                        "071125024",
                        "REGALIA",
                        "TARJETAS INTERN. DE CREDI",
                        "VIVIENDA",
                        "Euros",
                        "10",
                        "Descripicon automatizacion " + new Random().nextInt(50),
                        false,
                        true);

        String esperado = "En proceso, Completado";

        Assert.assertTrue( esperado.contains( actual));
    }
}
