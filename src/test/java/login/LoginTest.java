package login;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 12/11/2023 10:47 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class LoginTest extends BaseTest {

    @Test(description = "Verificar Campo usuario requerido")
    void usuarioRequerido(){
        String result = LoginLogic.get().verificarUsuarioRequerido("","1111",true);
        Assert.assertEquals( result,"Campo requerido");
    }

    @Test(description = "Verificar Campo Contrasena requerido")
    void contrasenaRequerida(){
        String result = LoginLogic.get().verificarContrasenaRequerida("123456789452","",true);
        Assert.assertEquals( result,"Campo requerido");
    }

    @Test(description = "Verificar campos usuario y contraseña requeridos.")
    void usuarioContrasenaRequeridos(){
        String result = LoginLogic.get().verificarUsuarioContrasenaRequeridos("","",true);
        Assert.assertEquals( result,"Campo requerido");
    }

    @Test(description = "Verificar usuario no existente.")
    void verificarUsuarioInvalido(){
        String result = LoginLogic.get().verificarCredencialesIncorrectas("12345678914","1111",true);
        Assert.assertEquals( result,"Usuario o contraseña inválidos");
    }

    @Test(description = "Verificar campo usuario con caracteres especiales.")
    void campoUsuarioCaracteresEspeciales(){
        String result = LoginLogic.get().verificarUsuarioConCaracteresEspeciales("2230139152%","1111",true);
        Assert.assertEquals( result,"No se permiten caracteres especiales  ");
    }

    @Test(description = "Inicio exitoso.")
    void verificarInicioExitoso(){
        String result = LoginLogic.get().iniciarDesdeDispositivoSeguro("22301391524","1111",true)
                .getTitulo360();
        Assert.assertEquals( result,"360 - Resumen de Productos");
    }
}
