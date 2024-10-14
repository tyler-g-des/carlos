package test2024.pagetest.login;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.POForgotPassword;
import pages.login.POLoginPage;

public class TestForgotPasswordLogin extends BaseTest {


    // Casos de pruebas Validar usuario

    //@Test(description = "Validar OTP", priority = 1)
    void validarOTP(){

    }

    //@Test(description = "validacion -> Número de documento no existente", priority = 2)
    void validarNumeroDocumentoNoExistente(){

    }




    @Test(description = "Campo Número de documento -> Requerido", priority = 3)
    void campoNumeroDocumentoRequerido(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaValidarUsuario("",true);

        Assert.assertEquals( page.getMsgValidarCampo("Número de documento",true),"Campo requerido");
    }

    @Test(description = "Numero de documento con caracteres menor al permitido", priority = 4)
    void campoNumeroDocumentoCantidadCaracteresMenorAlPermitido(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaValidarUsuario("2230139152",true);

        Assert.assertEquals( page.getMsgValidarCampo("Número de documento",true),"Mínimo 11 caracteres");
    }

    @Test(description = "Campo numero de documento con Texto", priority = 5)
    void numeroDocumentoConTexto(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaValidarUsuario("wjsiwadosq",true);

        Assert.assertEquals( page.getMsgValidarCampo("Número de documento",true),"Campo requerido");
    }

    @Test(description = "Cancelar Validar usuario - Opción No", priority = 6)
    void cancelarValidacionUsiarioOpcionNo(){
        String titulo = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .cancelarValidarUsuario("22301391524",true,false,true);
        //.cancelarValidacionUsuarioOpcionNo("22301391524",true);

        System.out.println(titulo);

        Assert.assertEquals( titulo,"Restaurar clave de acceso");
    }

    @Test(description = "Cancelar Validar usuario - Opción sí", priority = 7)
    void cancelarValidacionUsiarioOpcionSi(){
        String titulo = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .cancelarValidarUsuario("22301391524",false,true,true);

        System.out.println(titulo);

        Assert.assertEquals( titulo,"Internet Banking Personal");
    }



    // Casos de pruebas pestaña -> Configuración de acceso

    @Test(description = "Campo Nueva clave -> Correctamente & <br> Campo Confirmar Clave -> Campo requerido", priority = 8)
    void nuevaClaveCorrecta_ConfirmarClaveRequerido(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","2222","",true);

        page.botonContinuarDebeEstarDeshabilidado();
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Campo requerido");

    }

    @Test(description = "Campo Nueva clave -> Correctamente & <br>Campo Confirmar Clave -> Diferente", priority = 9)
    void nuevaClaveCorrecta_CampoConfirmarClaveDiferente(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","1234","2222",true);

        page.botonContinuarDebeEstarDeshabilidado();
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Las contraseñas no coinciden");
    }

    @Test(description = "Campo Nueva clave -> Correctamente & <br>Campo Confirmar Clave -> Caracteres menor al permitido", priority = 10)
    void nuevaClaveCorrecta_confirmarClaveCaracteresMenorAlPermitido(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","2222","222",true);

        page.botonContinuarDebeEstarDeshabilidado();
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Mínimo 4 caracteres");
    }

    @Test(description = "Campo Nueva clave -> Correctamente & <br>Campo Confirmar Clave -> No numerica", priority = 11)
    void nuevaClaveCorrecta_CampoConfirmarClaveNoNumerica(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","2222","222s",true);

        page.botonContinuarDebeEstarDeshabilidado();
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Este campo debe ser numérico");
    }

    @Test(description = "Campos Nueva clave & Confirmar Clave -> Campos Requeridos", priority = 12)
    void nuevaClaveRequerida_ConfirmarClaveRequeridos(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","","",true);

        page.botonContinuarDebeEstarDeshabilidado();
        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Campo requerido");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Campo requerido");
    }

    @Test(description = "Campo Nueva Clave -> Requerido & Confirmar clave -> No coincide", priority = 13)
    void nuevaClaveRequerido_ConfirmarClaveNoCoincide(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","","1234",true);

        page.botonContinuarDebeEstarDeshabilidado();

        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Campo requerido");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Las contraseñas no coinciden");
    }

    @Test(description = "Campo Nueva Clave -> Requerido & Confirmar Clave -> Caracteres menor al minimo permitido", priority = 14)
    void nuevaClaveRequerido_confirmarClaveConCaracteresMenorAlPermitido(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","","124",true);

        page.botonContinuarDebeEstarDeshabilidado();
        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Campo requerido");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Mínimo 4 caracteres");
    }

    @Test(description = "Campo Nueva Clave -> Confirmar nueva clave -> Debe ser numerico", priority = 15)
    void nuevaClaveRequerido_confirmarClaveConCaracteresNoNumericos(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","","123S",true);

        page.botonContinuarDebeEstarDeshabilidado();

        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Campo requerido");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Este campo debe ser numérico");
    }

    @Test(description = "Campo Confirmar nueva clave -> Campos requerido", priority = 16)
    void nuevaClaveCaracteresMenorAlPermitido_ConfirmarClaveRequerido(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","222","",true);

        page.botonContinuarDebeEstarDeshabilidado();

        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Mínimo 4 caracteres");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Campo requerido");
    }

    @Test(description = "Campos Nueva clave de acceso y Confirmar nueva clave -> Caracteres menor al minimo", priority = 17)
    void nuevaClaveCaracteresMenorAlPermitido_confirmarClaveNoCoincide(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","124","1234",true);

        page.botonContinuarDebeEstarDeshabilidado();
        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Mínimo 4 caracteres");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Las contraseñas no coinciden");
    }

    @Test(description = "Campos Nueva clave de acceso y Confirmar nueva clave -> Deben ser numericos", priority = 18)
    void nuevaClaveCaracteresMenorAlPermitido_ConfirmarClaveDebeSerNumerico(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","123","123s",true);

        page.botonContinuarDebeEstarDeshabilidado();
        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Mínimo 4 caracteres");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Este campo debe ser numérico");
    }

    @Test(description = "Campos Nueva clave de acceso y Confirmar nueva clave -> Claves diferentes", priority = 19)
    void nuevaClaveCaracteresMenorAlPermitido_confirmarClaveCaracteresMenorAlPermitido(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","123","432",true);

        page.botonContinuarDebeEstarDeshabilidado();

        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",true),"Mínimo 4 caracteres");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Mínimo 4 caracteres");
    }

    @Test(description = "Campo Nueva clave de acceso -> No numerica y <br>Campo Confirmar nueva clave -> Contraseña no coinciden", priority = 20)
    void nuevaClaveDebeSerNumerica_confirmarClaveRequerido(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","123s","",true);

        page.botonContinuarDebeEstarDeshabilidado();

        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Este campo debe ser numérico");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Campo requerido");
    }

    @Test(description = "Campo Nueva clave de acceso -> Caracteres menor al minimo y <br>Campo Confirmar nueva clave -> Contraseña no coinciden", priority = 21)
    void nuevaClaveDebeSerNumerica_confirmarClaveNoCoincide(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","123s","1111",true);

        page.botonContinuarDebeEstarDeshabilidado();

        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Este campo debe ser numérico");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Las contraseñas no coinciden");
    }

    @Test(description = "Campo Nueva clave de acceso -> Caracteres menor al minimo y <br>Campo Confirmar nueva clave -> Contraseña no coinciden", priority = 22)
    void nuevaClaveDebeSerNumerica_confirmarClaveMenorAlPermitido(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","123s","111",true);

        page.botonContinuarDebeEstarDeshabilidado();

        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Este campo debe ser numérico");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Mínimo 4 caracteres");
    }

    @Test(description = "Campo Nueva clave de acceso -> Caracteres menor al minimo y <br>Campo Confirmar nueva clave -> Contraseña no coinciden", priority = 23)
    void nuevaClaveDebeSerNumerica_confirmarClaveDebeSerNumerica(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","123s","123s",true);

        page.botonContinuarDebeEstarDeshabilidado();

        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Este campo debe ser numérico");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Este campo debe ser numérico");
    }


    // Pantalla OTP

    @Test(description = "Timer de envio codigo OTP", priority = 24)
    void consultarContadorEnvioOTP(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaValidarOTP("00100965995","2222","2222","1111",true);

        Assert.assertEquals( page.getTiempoExpiracionOTP("04:59",true),"04:59");
    }

}
