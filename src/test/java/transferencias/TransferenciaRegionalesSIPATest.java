package transferencias;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;
import pages.pyt.voucher.PoVoucherPage;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 06/06/2024 2:39 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TransferenciaRegionalesSIPATest extends BaseTest {

    @Test(description = "REGIONAL SIPA -> ")
    void pruebaFormularioTransferenciaSIPA(){
        String actual = LoginLogic.get().iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .transferenciasRegionalesSIPA(true)
                .transferencia(
                        "04005280040",
                        "Carlos Alberto Loyola Tejeda",
                        "22301391524",
                        "GUATEMALA",
                        "La calle de mi casa",
                        "carlos_loyola@bhd.com.do",
                        "AGROMERCANTIL DE GUATEMALA, S.A.",
                        "546323564623",
                        "100",
                        "Descripcion",
                        true);

        String esperado = "En proceso, Completado";

        Assert.assertTrue( esperado.contains( actual));

        String numConfirm =  PoVoucherPage.getNewPage().getNumeroConfirmacion(true);

        System.out.println(numConfirm);
    }

    //@Test(description = "PRUEBA FORMULARIO TRANSFERENCIA SIPA")
    void pruebaFormularioTransferenciaSIPA2(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .transferenciasRegionalesSIPA(false)
                .transferencia(
                        "04005280040",
                        "Carlos Alberto Loyola Tejeda",
                        "22301391524",
                        "GUATEMALA",
                        "La calle de mi casa",
                        "carlos_loyola@bhd.com.do",
                        "AGROMERCANTIL DE GUATEMALA, S.A.",
                        "546323564623",
                        "100",
                        "Descripcion",
                        true);
    }
}
