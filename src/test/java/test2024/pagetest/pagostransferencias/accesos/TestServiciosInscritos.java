package test2024.pagetest.pagostransferencias.accesos;

import basetest.BaseTest;
import data.GenerarData;
import data.Persistencia;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.POLoginPage;
import servicios.micros.dashboard.MicroDashboardNewFeature;

public class TestServiciosInscritos extends BaseTest {

    private String telefono = "";
    private String descripcion = "";

    @Test(description = "Agregar Servicio nuevo", priority = 1)
    void agregarServicioNuevo(){
        telefono = new GenerarData().randomPhoneNumberRD();
        descripcion = "Automatizacion IBP " + new GenerarData().getNumRandomString(6);
        System.out.println(descripcion);

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoServiciosInscritos(false)
                .agregarServicio(
                        "CLARO",
                        "Compra de Recargas",
                        telefono,
                        descripcion,
                        true);
    }

    @Test(description = "Actualizar servicio", dependsOnMethods = "agregarServicioNuevo")//, dependsOnMethods = "agregarServicioNuevo")
    void editarServicioInscrito(){

//        MicroDashboardNewFeature newFeature = new MicroDashboardNewFeature("22301391524");
//        Persistencia.getInstance().consultarDashboardPublicidad("22301391524");

        //System.out.println("Contiene publicidad: " + Persistencia.getInstance().getPublicidad("22301391524").contienePublicidad());

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoServiciosInscritos(false)
                .editarServicio(
                        descripcion,
                        "CLARO",
                        "Compra de Recargas",
                        telefono,
                        "NA",
                        true);
    }

    @Test(description = "Eliminar Servicio inscrito", dependsOnMethods = "agregarServicioNuevo")
    void eliminarServicioInscrito(){
        String result = POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoServiciosInscritos(false)
                .eliminarServicio(
                        descripcion,
                        true);
        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    }

// 886691
}
