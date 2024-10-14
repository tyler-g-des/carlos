package apis;

import apis.ibp.APISLogin;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Email: carlos_loyola@bhd.com.do
 * @ClassName: APILoginTest
 * @CreationData: 01/06/2024 2:21 p. m.
 * @ProjectName: AutomatizacionAPI_IBP
 */
public class APILoginTest extends BaseTest {
    private APISLogin login = new APISLogin();

    @Test(description = "Consultar servicio Feature Flags en login -> 200", priority = 1)
    void consultarFeatureFlagsEnLogin(){
        login.featureFlags("Consultar Feature Flags en login");
        createStep( login.getMessageReport(),login.getStatusCode() == 200,false);

        Assert.assertEquals(login.getStatusCode(),200);
    }

    @Test(description = "Forgot Password - Validar usuario existente para Cambio de contrasena -> 200", priority = 2)
    void forgotPasswordValidarUsuarioExistenteParaCambioContrasena(){
        login.forgotPasswordValidarUsuario("22301391524", "Validar usuario existente para Cambio de contrasena"); // "Consultar servicio Forgot Password - Validar usuario combio contrasena"
        createStep( login.getMessageReport(),login.getStatusCode() == 200,false);

        Assert.assertEquals(login.getStatusCode(),200);
    }

    @Test(description = "Forgot Password - Validar servicio usuario No existente para Cambio de contrasena -> 500", priority = 2)
    void forgotPasswordValidarServicioConUsuarioNoExistente(){
        login.forgotPasswordValidarUsuario("22301391560", "Validar servicio usuario No existente para Cambio de contrasena"); // "Consultar servicio Forgot Password - Validar usuario combio contrasena"
        createStep( login.getMessageReport(),login.getStatusCode() == 500,false);

        Assert.assertEquals(login.getStatusCode(),500);
    }

    @Test(description = "Solicitar Envio codigo OTP -> 200", priority = 3)
    void solicitarEnvioOTP(){
        login.solicitarEnvioOtpParaCambioContrasenaLogin("Solicitar cambio contrasena usuario valido"); // "Consultar servicio Forgot Password - Validar usuario combio contrasena"
        createStep( login.getMessageReport(),login.getStatusCode() == 200,false);

        Assert.assertEquals(login.getStatusCode(),200);
    }

    @Test(description = "Cambiar Contrasena con OTP Invalido -> 500", priority = 3)
    void changePasswordConOTPInvalido(){
        login.changePasswordOTP("1234568","2222","Cambio de contrasena con OTP Invalido"); // "Consultar servicio Forgot Password - Validar usuario combio contrasena"

        createStep( login.getMessageReport(),login.getStatusCode() == 500,false);

        Assert.assertEquals(login.getStatusCode(),500);
    }

    @Test(description = "Hacer login con credenciales correctas -> 200", priority = 0)
    void loginCredencialesCorrectas(){
        login.login("00100892793","1111","Inicio de sesion correctamente");

        createStep( login.getMessageReport(),login.getStatusCode() == 200,false);

        Assert.assertEquals(login.getStatusCode(),200);
    }

    @Test(description = "Hacer login con contrasena Incorrectas -> 401")
    void loginContrasenaIncorrecta(){
        login.login("00100892793","1113","Inicio sesion contrasena invalida");
        createStep( login.getMessageReport(),login.getStatusCode() == 401,false);

        Assert.assertEquals(login.getStatusCode(),401);
    }

    @Test(description = "Login con dispositivo Seguro -> 200")
    public void loginConDipositivoSeguro(){
        login.loginSecureDevice("22301391524","1111", "Dispositivo Seguro");
        createStep( login.getMessageReport(),login.getStatusCode() == 200,false);

        Assert.assertEquals(login.getStatusCode(),200);
    }

    @Test(description = "Inyeccion SQL -> 401", priority = 2)
    void loginInyeccionSQL(){
        login.login("'OR '1' = '1","'OR '1' = '1","Inyeccion SQL");

        createStep( login.getMessageReport() ,( login.getStatusCode() == 401),false);
        Assert.assertEquals( login.getStatusCode(),401);
    }

    @Test(description = "Iniciar sesion con usuario no creado en Active Directory -> 401", priority = 5)
    void iniciarConUsuarioNoCreadoEnActiveDirectory(){
        login.login("00118304880","1111","Usuario no existente en ActiveDirectory ");

        createStep( login.getMessageReport(), ( login.getStatusCode() == 401),false);
        Assert.assertEquals( login.getStatusCode(),401 );
    }

    @Test(description = "Iniciar sesion con usuario Bloqueado -> 401", priority = 6) // Buscar usuario bloqueado
    void iniciarConUsuarioBloqueado(){
        login.login("00112035563","1111","Inicio sesion con usuario bloqueado"); // Buscar usuario bloqueado

        createStep( login.getMessageReport(), ( login.getStatusCode() == 401),false);
        Assert.assertEquals( login.getStatusCode(),401 );
    }
}
