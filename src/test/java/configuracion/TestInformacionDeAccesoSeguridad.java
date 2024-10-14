package configuracion;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/04/2024 8:38 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TestInformacionDeAccesoSeguridad extends BaseTest {

    @Test(description = "Verificar cambio de contraseña con contraseña actual incorrecta", priority = 0)
    void verificarCambioDeContrasenaConContrasenaActualIncorrecta(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .configuracion(false)
                .claveActualIncorrecta(
                        "8888",
                        "2222",
                        "2222",
                        "1111",
                        true);

        Assert.assertEquals(actualResult,"La clave de acceso actual es incorrecta");
    }

    @Test(description = "Verificar campo nueva contrasena no numerica", priority = 1)
    void verificarCampoNuevaContrasenaNoNumerica(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .configuracion(false)
                .verificarContrasenaDebeSerNumerica(
                        "1111",
                        "asdd",
                        "2222",
                        true);

        Assert.assertEquals(actualResult,"Este campo debe ser numérico");
    }

    @Test(description = "Verificar Campo confirmar contrasena no numerica", priority = 2)
    void verificarCampoConfirmarContrasenaNoNumerica(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .configuracion(false)
                .verificarContrasenaDebeSerNumerica(
                        "1111",
                        "2222",
                        "sfgh",
                        true);

        Assert.assertEquals(actualResult,"Este campo debe ser numérico");
    }



    @Test(description = "Verificar mensaje contraseña no coincide", priority = 3)
    void verificarContrasenaNoCoinciden(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .configuracion(false)
                .verificarContrasenaQueNoCoinciden(
                        "1111",
                        "3333",
                        "2222",
                        true);

        Assert.assertEquals(actualResult,"Las contraseñas no coinciden");
    }


    @Test(description = "Cambiar contranseña", priority = 4)
    void cambiarContrasenaCerrarSesion(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .configuracion(false)
                .cambiarContrasenaCerrarSesion(
                        "1111",
                        "2222",
                        "2222",
                        "1111",
                        true)
                .verificarPantallaLogin(true);

        Assert.assertEquals(actualResult,"Internet Banking Personal");
    }

    @Test(description = "Cambiar contranseña y no cerrar sesion", dependsOnMethods = "cambiarContrasenaCerrarSesion")
    void cambiarContrasenaNoCerrarSesion(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .configuracion(false)
                .cambiarContrasenaNoCerrarSesion(
                        "2222",
                        "1111",
                        "1111",
                        "1111",
                        true);

        Assert.assertEquals(actualResult,"Información de acceso y seguridad");
    }

}
