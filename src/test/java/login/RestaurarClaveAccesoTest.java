package login;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;

public class RestaurarClaveAccesoTest extends BaseTest {

    //@Test(description = "VERIFICAR PANTALLA DE ENVIO OTP")
    void verificarPantallaEnvioOTP(){
        String result = LoginLogic.get().navegarAPaginaOlvideMiContrasena(false)
                .validarPantallaEnvioOTP(
                        "00201352754", "2222", "2222",
                        "234233", "04:59", true);

        Assert.assertEquals(result,"04:59");
    }


    //@Test(description = "Verificar mensaje de error -> Las contraseñas no coinciden")
    void verificarMensajeErrorContrasenaNoCoinciden(){
        String result = LoginLogic.get().navegarAPaginaOlvideMiContrasena(false)
                .verificarContrasenaDiferentes("00201352754","2222","3333",true);

        Assert.assertEquals(result,"Las contraseñas no coinciden");
    }

    //@Test(description = "Verificar mensaje de error -> Campo Nueva clave debe ser numérico")
    void verificarMensajeErrorCampoNuevaClaveDebeSerNumerico(){
        String result = LoginLogic.get().navegarAPaginaOlvideMiContrasena(false)
                .verificarCampoNuevaContrasenaOConfirmarContrasenaDebenSerNumerico("00201352754","asd4","asd4",true);

        Assert.assertEquals(result,"Este campo debe ser numérico");
    }

    //@Test(description = "Verificar mensaje de error -> Campo confirmar nueva clave debe ser numérico")
    void verificarMensajeErrorCampoConfirmarClaveDebeSerNumerico(){
        String result = LoginLogic.get().navegarAPaginaOlvideMiContrasena(false)
                .verificarCampoNuevaContrasenaOConfirmarContrasenaDebenSerNumerico("00201352754","asd4","asd4",true);

        Assert.assertEquals(result,"Este campo debe ser numérico");
    }


    //@Test(description = "Verificar mensaje de error -> Minimo de caracteres campo nueva contraseña")
    void verificarMensajeErrorMinimoDeCaracteresCampoNuevaContrasena(){
        String result = LoginLogic.get().navegarAPaginaOlvideMiContrasena(false)
                .verificarMensajeErrorMinimoDeCaracteres("00201352754","111","1234",true);

        Assert.assertEquals(result,"Mínimo 4 caracteres");
    }

    //@Test(description = "Verificar mensaje de error -> Minimo de caracteres campo confirmar contraseña")
    void verificarMensajeErrorMinimoDeCaracteresCampoConfirmarContrasena(){
        String result = LoginLogic.get().navegarAPaginaOlvideMiContrasena(false)
                .verificarMensajeErrorMinimoDeCaracteres("00201352754","1111","123",true);

        Assert.assertEquals(result,"Mínimo 4 caracteres");
    }

    //@Test(description = "Verificar mensaje de error -> Campo nueva contraseña requerido")
    void verificarMensajeErrorCampoNuevaContrasenaRequerido(){
        String result = LoginLogic.get().navegarAPaginaOlvideMiContrasena(false)
                .verificarMensajeErrorCampoRequerido("00201352754","","1234",true);

        Assert.assertEquals(result,"Campo requerido");
    }

    //@Test(description = "Verificar mensaje de error -> Campo confirmar contraseña requerido")
    void verificarMensajeErrorCampoCoonfirmarContrasenaRequerido2(){
        String result = LoginLogic.get().navegarAPaginaOlvideMiContrasena(false)
                .verificarMensajeErrorCampoRequerido("00201352754","1111","",true);

        Assert.assertEquals(result,"Campo requerido");
    }


    @Test(description = "Verificar mensaje de error (Mínimo 11 caracteres) -> Campo Número de documento")
    void verificarMensajeErrorCaracteresMinimosEnNumeroDeDocumento(){
        String result = LoginLogic.get().navegarAPaginaOlvideMiContrasena(false)
                .getMensajeErrorValidacionNumeroDocumento("0020135275",true);

        Assert.assertEquals(result,"Mínimo 11 caracteres");
    }

    @Test(description = "Verificar mensaje de error (Mínimo 11 caracteres) -> Campo Número de documento")
    void verificarMensajeErrorCampoNumeroDeDocumentoRequerido(){
        String result = LoginLogic.get().navegarAPaginaOlvideMiContrasena(false)
                .getMensajeErrorValidacionNumeroDocumento("",true);

        System.out.println(result);
        //Assert.assertEquals(result,"Campo requerido");
    }

}
