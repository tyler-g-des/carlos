package frontend;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.POLoginPage;

public class TestLogin extends BaseTest {

    @Test(description = "Campo usuario requerido", priority = 1)
    void campoUsuarioRequerido(){
        POLoginPage login = POLoginPage.getInstance(getDriver())
                .loginValidacion("","1111",true);

        Assert.assertEquals(login.getMsgCampoRequerido("Usuario",false),"Campo requerido");
    }

    @Test(description = "Campo Contraseña requerido", priority = 2)
    void campoContrasenaRequerido(){
        POLoginPage login = POLoginPage.getInstance(getDriver())
                .loginValidacion("22301391524","",true);

        Assert.assertEquals(login.getMsgCampoRequerido("Contraseña",true),"Campo requerido");
    }

    @Test(description = "Campos usuario y contrasena requeridos", priority = 3)
    void camposUsuarioContrasenaRequeridos(){
        POLoginPage login = POLoginPage.getInstance(getDriver())
                .loginValidacion("","",true);

        Assert.assertEquals(login.getMsgCampoRequerido("Usuario",false),"Campo requerido");
        Assert.assertEquals(login.getMsgCampoRequerido("Contraseña",true),"Campo requerido");
    }

    @Test(description = "Inicio con contrasena Incorrecta", priority = 4)
    void inicioContrasenaIncorrecta(){
        POLoginPage login = POLoginPage.getInstance(getDriver())
                .loginValidacion("00100965995","1234",true);

        Assert.assertEquals(login.getMsgCredencialesInvalidas(true),"Usuario o contraseña inválidos");
    }

    @Test(description = "Inicio con Usuario Invalido", priority = 5)
    void inicioUsuarioInvalido(){
        POLoginPage login = POLoginPage.getInstance(getDriver())
                .loginValidacion("12364598748","1234",true);

        Assert.assertEquals(login.getMsgCredencialesInvalidas(true),"Usuario o contraseña inválidos");
    }

    @Test(description = "Validacion Usuario con caracteres especiales", priority = 6)
    void usuarioConCaracteresEspeciales(){
        POLoginPage login = POLoginPage.getInstance(getDriver())
                .loginValidacion("22301391524@","1111",true);

        Assert.assertEquals(login.getMsgCaracteresEspeciales(false),"No se permiten caracteres especiales  ");
    }
}
