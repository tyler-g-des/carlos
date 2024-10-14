package transferencias;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/06/2024 4:27 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TransferenciasPinPesosTest extends BaseTest {

    //@Test(description = "Transferencia a Beneficiario Inscrito")
    void transferenciaBeneficiarioInscrito(){

        String actual =  LoginLogic.get().iniciarSesion("03102892530","1111", false)
                .validarAcceso(false)
                .pinPesos(false)
                .transferencia(
                        "04005280015",
                        "8092031322",
                        "200",
                        "Descripcion",
                        true,
                        true);
        String esperado = "En proceso, Completado";

        Assert.assertTrue( esperado.contains( actual));
    }

    @Test(description = "Transferencia a nuevo beneficiario")
    void transferenciaNuevoBeneficiario(){

        String actual =  LoginLogic.get().inicioSesionGenerico("03102892530","1111", false)

                .pinPesos(false)
                .transfenciaNuevoBeneficiario(
                        "04005280015",
                        "Carlos",
                        "8293371829",
                        "Cédula",
                        "22301391524",
                        "Carlos A, Loyola Tejeda",
                        "TURQUIA",
                        "Masculino",
                        "600",
                        "Descripcion",
                        true,
                        true,
                        true);

        //String esperado = "En proceso, Completado";
        //Assert.assertTrue( esperado.contains( actual));
    }
}
