package test2024.pagetest.pagostransferencias.beneficiariosinscritos;

import basetest.BaseTest;
import data.GenerarData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.POLoginPage;
import pages.pagostransferencias.accesos.beneficiarios.POBeneficiarioPinPesosPage;
import pages.pagostransferencias.accesos.beneficiarios.POBeneficiariosInscritosPage;

public class TestBeneficiariosPinPesos extends BaseTest {


    /**
     * <h1>Terminar validacion del beneficiario luego de crearlo.</h1>
     */
    //Test(description = "Agregar beneficiario")

    private String telefono = "";

    @Test(description = "Agregar beneficiario -> Con cedula", priority = 1)
    void AgregarBeneficiarioConCedula(){
        telefono = new GenerarData().randomPhoneNumberRD();


        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioPinPesos(
                        "con cedula",
                        telefono,
                        "Cédula",
                        "22301391524",
                        "Carlos Alberto Loyola",
                        "REPUBLICA CHECA",
                        "Masculino",
                        true);
    }

    @Test(description = "Actualizar beneficiario -> Con cedula", dependsOnMethods = "AgregarBeneficiarioConCedula")
    void actualizarBeneficiarioConCedula(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .actualizarBeneficiario(
                        telefono,
                        "con cedula " + new GenerarData().getNumRandomString(2),
                        true);
    }

    @Test(description = "Eliminar beneficiario -> Con cedula", dependsOnMethods = "AgregarBeneficiarioConCedula")
    void eliminarBeneficiarioConCedula(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        String result = new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioPinPesos( telefono,true );

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    }





    String pasaporte = "";
    String telefonoConPasaporte = "";

    @Test(description = "Agregar beneficiario -> Con Pasaporte", priority = 2)
    void AgregarBeneficiarioConPasaporte(){
        telefonoConPasaporte = new GenerarData().randomPhoneNumberRD();
        pasaporte = new GenerarData().getNumRandomString(11);


        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioPinPesos(
                        "con cedula",
                        telefonoConPasaporte,
                        "Pasaporte",
                        pasaporte,
                        "Carlos Alberto Loyola",
                        "REPUBLICA CHECA",
                        "Masculino",
                        true);
    }

    @Test(description = "Actualizar beneficiario -> Con Pasaporte", dependsOnMethods = "AgregarBeneficiarioConPasaporte")
    void actualizarBeneficiarioConPasaporte(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .actualizarBeneficiario(
                        telefonoConPasaporte,
                        "con pasaporte " + new GenerarData().getNumRandomString(2),
                        true);
    }

    @Test(description = "Eliminar beneficiario -> Con Pasaporte", dependsOnMethods = "AgregarBeneficiarioConPasaporte")
    void eliminarBeneficiarioConPasaporte(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        String result = new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioPinPesos( telefonoConPasaporte,true );

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    }


}
