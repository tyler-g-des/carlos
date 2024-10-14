import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 21/03/2024 4:16 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TestPerfilDeUsuario extends BaseTest {

    @Test(description = "Actualizar Otros datos")
    void actualizarDatosContacto(){
        String titulo = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .navegarAPerfilDeUsuarioPage(false)
                .actualizarOtrosDatos(
                        "La esquina 12",
                        "12",
                        "Monte Plata",
                        "Chirino (DM)",
                        "Yaita",
                        "Las Caobas",
                        "Edificio amarillo",
                        "33",
                        "Cerca de mi casa",
                        true);

        Assert.assertEquals(titulo,"360 - Resumen de Productos");
    }
}
