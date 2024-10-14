package pyt.beneficiariosinscritos;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 26/02/2024 9:40 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TestBeneficiariosPinPesos extends BaseTest {

    String usuario = "03102674383";

    @Test(priority = 1,description = "Agregar beneficiario -> Cedula sin Alias")
    void agregarBeneficiarioCedulaSinAlias(){
        String resultadoActual = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioPinPesos(
                        usuario,
                        "NA",
                        "8295556644",
                        "Cédula",
                        "22301391524",
                        "Carlos Loyola Cedula",
                        "DINAMARCA",
                        "Masculino",
                        true);

        Assert.assertEquals(resultadoActual,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 2, description = "Actualizar beneficiario cedula -> Agregar alias", dependsOnMethods = "agregarBeneficiarioCedulaSinAlias")
    void actualizarBeneficiarioCedulaAgregarAlias(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",true)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioPinPesos(
                        usuario,
                        "8295556644",
                        "Alias Julito Perez",
                        "Carlos A, Loyola Tejeda",
                        "COSTA RICA",
                        "NA",
                        true);
    }

    @Test(priority = 3,description = "Eliminar beneficiario existente con cedula", dependsOnMethods = "agregarBeneficiarioCedulaSinAlias")
    void eliminarBeneficiarioConCedula(){
        String message = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",true)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .eliminarBeneficiarioPinPesos(
                        usuario,
                        "8295556644",
                        true);

        Assert.assertEquals(message,"No se encontraron registros para mostrar");
    }



    @Test(priority = 4,description = "Agregar beneficiario Cedula -> Con Alias")
    void agregarBeneficiarioCedulaConAlias(){
        String resultadoActual = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioPinPesos(
                        usuario,
                        "Alias usuario con cedula",
                        "8295556633",
                        "Cédula",
                        "22301391524",
                        "Carlos Loyola Cedula",
                        "DINAMARCA",
                        "Masculino",
                        true);

        Assert.assertEquals(resultadoActual,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 5, description = "Actualizar Beneficiario con cedula -> Eliminar Alias", dependsOnMethods = "agregarBeneficiarioCedulaConAlias")
    void actualizarBeneficiarioEliminarAlias(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",true)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioPinPesos(
                        usuario,
                        "8295556633",
                        "",
                        "Carlos A, Loyola Tejeda",
                        "COSTA RICA",
                        "NA",
                        true);
    }

    @Test(priority = 6,description = "Eliminar Beneficiario con cedula -> Sin Alias", dependsOnMethods = "agregarBeneficiarioCedulaConAlias")
    void eliminarBeneficiarioSinAlias(){
        String message = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",true)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .eliminarBeneficiarioPinPesos(
                        usuario,
                        "8295556633",
                        true);

        Assert.assertEquals(message,"No se encontraron registros para mostrar");
    }




    @Test(priority = 7,description = "Agregar Beneficiario Pasaporte -> Sin Alias")
    void AgregarBeneficiarioPasaporteSinAlias(){
        String resultadoActual = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioPinPesos(
                        usuario,
                        "NA",
                        "8092312323",
                        "Pasaporte",
                        "531081252",
                        "Carlos Loyola Pasaporte",
                        "DINAMARCA",
                        "Masculino",
                        true);

        Assert.assertEquals( resultadoActual,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 8,description = "Actualizar Beneficiario Pasaporte -> Agregar Alias", dependsOnMethods = "AgregarBeneficiarioPasaporteSinAlias")
    void actualizarBeneficiarioPasaporteAgregarAlias(){
        String message = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",true)
                .irPaginaPagosTransferencias(false).
        beneficiariosInscritos(false)
                .actualizarBeneficiarioPinPesos(
                        usuario,
                        "8092312323",
                        "Alias asignada con pasaporte",
                        "Carlos Tejeda pasaporte con alias",
                        "COSTA RICA",
                        "Masculino",
                        true);
        System.out.println(message);
        Assert.assertEquals(message,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 9,description = "Eliminar Beneficiario - Pasaporte", dependsOnMethods = "AgregarBeneficiarioPasaporteSinAlias")
    void eliminarBeneficiarioPasaporte(){
        String message = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",true)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .eliminarBeneficiarioPinPesos(
                        usuario,
                        "8092312323",
                        true);

        Assert.assertEquals(message,"No se encontraron registros para mostrar");
    }



    @Test(priority = 10,description = "Agregar Beneficiario Pasaporte -> Con alias")
    void AgregarBeneficiarioPasaporteConAlias(){
        String resultadoActual = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioPinPesos(
                        usuario,
                        "Alias user pasaporte",
                        "8092319825",
                        "Pasaporte",
                        "531081252",
                        "Carlos Loyola Pasaporte con alias",
                        "DINAMARCA",
                        "Masculino",
                        true);

        Assert.assertEquals( resultadoActual,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 11,description = "Actualizar Beneficiario Pasaporte -> Eliminar Alias", dependsOnMethods = "AgregarBeneficiarioPasaporteConAlias")
    void actualizarBeneficiarioPasaporteEliminarAlias(){
        String message = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",true)
                .irPaginaPagosTransferencias(false).
                beneficiariosInscritos(false)
                .actualizarBeneficiarioPinPesos(
                        usuario,
                        "8092319825",
                        "",
                        "Carlos Tejeda pasaporte con alias",
                        "COSTA RICA",
                        "Masculino",
                        true);
        System.out.println(message);
        Assert.assertEquals(message,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 12,description = "Eliminar Beneficiario Pasaporte -> Sin alias", dependsOnMethods = "AgregarBeneficiarioPasaporteConAlias")
    void eliminarBeneficiarioPasaporteSinAlias(){
        String message = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",true)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .eliminarBeneficiarioPinPesos(
                        usuario,
                        "8092319825",
                        true);

        Assert.assertEquals(message,"No se encontraron registros para mostrar");
    }
}
