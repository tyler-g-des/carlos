package test2024.pagetest.configuracion;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.configuracion.POInformacionAccesoSeguridadPage;
import pages.login.POLoginPage;

public class TestInformacionDeAccesoSeguridad extends BaseTest {

    @Test(description = "Campo Clave de acceso actual con caracteres menor al minimo permitido", priority = 1)
    void campoClaveActualConCaracteresMenorAlMinimoPermitido(){
        POInformacionAccesoSeguridadPage page = POLoginPage.getInstance(getDriver())
                .iniciarSesion("05400855671","1111",false)
                .validarAcceso(false)
                .configuracion(false)
                .informacionDeAccesoSeguridad(true)
                .verificarFormularioCambioDeClaveAcceso("111","2222","2222", true);;

        Assert.assertEquals( page.getMsgAlertaCAmpo("Clave de acceso actual",true),"Mínimo 4 caracteres");
    }

    @Test(description = "Campo Clave de acceso actual con caracteres menor al minimo permitido", priority = 2)
    void campoClaveActualConCaracteresNoNumericos(){
        POInformacionAccesoSeguridadPage page = POLoginPage.getInstance(getDriver())
                .iniciarSesion("05400855671","1111",false)
                .validarAcceso(false)
                .configuracion(false)
                .informacionDeAccesoSeguridad(true)
                .verificarFormularioCambioDeClaveAcceso("123s","2222","2222", true);;

        Assert.assertEquals( page.getMsgAlertaCAmpo("Clave de acceso actual",true),"Este campo debe ser numérico");
    }

    @Test(description = "Campos Clave actual y Nueva clave -> caracteres menor al minimo | campo Confirmar clave contrasena diferente", priority = 3)
    void camposClaveActualNuevaConCaracteresMenorAlMinimo_CampoConfirmarClaveContrasenaDiferente(){
        POInformacionAccesoSeguridadPage page = POLoginPage.getInstance(getDriver())
                .iniciarSesion("05400855671","1111",false)
                .validarAcceso(false)
                .configuracion(false)
                .informacionDeAccesoSeguridad(true)
                .verificarFormularioCambioDeClaveAcceso("111","222","2222", true);;

        Assert.assertEquals( page.getMsgAlertaCAmpo("Clave de acceso actual",true),"Mínimo 4 caracteres");
        Assert.assertEquals( page.getMsgAlertaCAmpo("Nueva clave de acceso",true),"Mínimo 4 caracteres");


        Assert.assertEquals( page.getMsgAlertaCAmpo("Confirmar nueva clave",true),"Las contraseñas no coinciden");
    }

    @Test(description = "cambiar contrasena con campo confirmar clave diferente", priority = 6)
    void campoConfirmarNuevaClaveDiferente(){
        POInformacionAccesoSeguridadPage page = POLoginPage.getInstance(getDriver())
                .iniciarSesion("05400855671","1111",false)
                .validarAcceso(false)
                .configuracion(false)
                .informacionDeAccesoSeguridad(true)
                .verificarFormularioCambioDeClaveAcceso("1111","2222","3333", true);;

        System.out.println(page.getMsgAlertaCAmpo("Confirmar nueva clave", false));
        Assert.assertEquals( page.getMsgAlertaCAmpo("Confirmar nueva clave",true),"Las contraseñas no coinciden");
    }




}
