package test2024.pagetest.pagostransferencias.beneficiariosinscritos;

import basetest.BaseTest;
import data.GenerarData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.POLoginPage;
import pages.pagostransferencias.accesos.beneficiarios.POBeneficiariosInscritosPage;

public class TestBeneficiariosNacionalesBHD extends BaseTest {

    // TC Activa = 4076733115760867
    // CA Pesos -05526370041
    // CA dolar-05605070011
    // CC -02236850016
    // CA Euro -01891170064


    @Test(description = "Agregar beneficiario Prestamo Crediclick T24", priority = 1)
    void agregarBeneficiarioPrestamoCrediclickT24(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioBHD(
                        "11272576",
                        "Crediclick t24 automatizado",
                        "carlos_loyola@bhd.com.do",
                        true);
    } // Listo

    @Test(description = "Editar beneficiario Prestamo Crediclick T24", dependsOnMethods = "agregarBeneficiarioPrestamoCrediclickT24")
    void editarBeneficiarioPrestamoCrediclickT24(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .editarBeneficiarioBHD(
                        "11272576",
                        "Crediclick t24 automatizado " + new GenerarData().getNumRandom(2),
                        "carlos_loyola@bhd.com.do",
                        true);
    } // Listo

    @Test(description = "Eliminar beneficiario Prestamo Crediclick T24", dependsOnMethods = "agregarBeneficiarioPrestamoCrediclickT24")
    void eliminarBeneficiarioPrestamoCrediclickT24(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        String result =  new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioNacional(
                        "11272576",
                        true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    } // Listo




    @Test(description = "Agregar beneficiario BHD -> Cuenta Corriente", priority = 2)
    void agregarBeneficiarioCuentaCorrienteBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioBHD(
                        "02236850016",
                        "CC Pesos automatizado",
                        "carlos_loyola@bhd.com.do",
                        true);
    } // Listo

    @Test(description = "Editar beneficiario BHD -> Cuenta Corriente", dependsOnMethods = "agregarBeneficiarioCuentaCorrienteBHD")
    void editarBeneficiarioCuentaCorrienteBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .editarBeneficiarioBHD(
                        "02236850016",
                        "CC Pesos automatizado " + new GenerarData().getNumRandom(2),
                        "carlos_loyola@bhd.com.do",
                        true);
    } // Listo

    @Test(description = "Eliminar beneficiario BHD -> Cuenta Corriente", dependsOnMethods = "agregarBeneficiarioCuentaCorrienteBHD")
    void eliminarBeneficiarioCuentaCorrienteBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        String result =  new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioNacional(
                        "02236850016",
                        true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    } // Listo




    @Test(description = "Agregar beneficiario BHD -> Cuenta ahorro Pesos", priority = 3)
    void agregarBeneficiarioCuentaAhorroPesosBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioBHD(
                        "05526370041",
                        "CA Pesos automatizado",
                        "carlos_loyola@bhd.com.do",
                        true);
    } // Listo

    @Test(description = "Editar beneficiario BHD -> Cuenta ahorro Pesos", dependsOnMethods = "agregarBeneficiarioCuentaAhorroPesosBHD")
    void editarBeneficiarioCuentaAhorroPesosBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .editarBeneficiarioBHD(
                        "05526370041",
                        "CA Pesos automatizado " + new GenerarData().getNumRandom(2),
                        "carlos_loyola@bhd.com.do",
                        true);
    } // Listo

    @Test(description = "Eliminar beneficiario BHD -> Cuenta ahorro Pesos", dependsOnMethods = "agregarBeneficiarioCuentaAhorroPesosBHD")
    void eliminarBeneficiarioCuentaAhorroPesosBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        String result =  new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioNacional(
                        "05526370041",
                        true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    } // Listo




    @Test(description = "Agregar beneficiario BHD -> Cuenta ahorro Dolar", priority = 4)
    void agregarBeneficiarioCuentaAhorroDolarBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioBHD(
                        "05605070011",
                        "CA Dolar automatizado",
                        "carlos_loyola@bhd.com.do",
                        true);
    } // Listo

    @Test(description = "Editar beneficiario BHD -> Cuenta ahorro Dolar", dependsOnMethods = "agregarBeneficiarioCuentaAhorroDolarBHD")
    void editarBeneficiarioCuentaAhorroDolarBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .editarBeneficiarioBHD(
                        "05605070011",
                        "CA Dolar automatizado " + new GenerarData().getNumRandom(2),
                        "carlos_loyola@bhd.com.do",
                        true);
    } // Listo

    @Test(description = "Eliminar beneficiario BHD -> Cuenta ahorro Dolar", dependsOnMethods = "agregarBeneficiarioCuentaAhorroDolarBHD")
    void eliminarBeneficiarioCuentaAhorroDolarBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        String result =  new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioNacional(
                        "05605070011",
                        true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    } // Listo




    @Test(description = "Agregar beneficiario BHD -> Cuenta ahorro Euro", priority = 5)
    void agregarBeneficiarioCuentaAhorroEuroBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioBHD(
                        "01891170064",
                        "CA Euro automatizado",
                        "carlos_loyola@bhd.com.do",
                        true);
    } // Listo

    @Test(description = "Editar beneficiario BHD -> Cuenta ahorro Euro", dependsOnMethods = "agregarBeneficiarioCuentaAhorroEuroBHD")
    void editarBeneficiarioCuentaAhorroEuroBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .editarBeneficiarioBHD(
                        "01891170064",
                        "CA Euro automatizado " + new GenerarData().getNumRandom(2),
                        "carlos_loyola@bhd.com.do",
                        true);
    } // Listo

    @Test(description = "Eliminar beneficiario BHD -> Cuenta ahorro Euro", dependsOnMethods = "agregarBeneficiarioCuentaAhorroEuroBHD")
    void eliminarBeneficiarioCuentaAhorroEuroBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        String result =  new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioNacional(
                        "01891170064",
                        true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    } // Listo









    // Otros bancos

    String cuentaAhorroOtroBanco = String.valueOf( new GenerarData().getNumRandom(12) ) ;

    @Test(description = "Agregar Beneficairio CA de otro banco con cedula") // Listo
    void agregarBeneficiarioCADeOtroBancoConCedula(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioDeOtrosBancos(
                "ASOCIACION POPULAR DE AHORROS Y PRESTAMOS",
                "Cuentas de Ahorro",
                        cuentaAhorroOtroBanco,
                "Cédula",
                "22301391524",
                "Carlos Alberto Loyola tejeda",
                "Automatizacion IBP " + new GenerarData().getNumRandom(3),
                "NA",
                "Masculino",
                "carlos_loyola@bhd.com.do",
                true);
    }

    @Test(description = "Editar Beneficairio CA de otro banco con cedula") // Listo
    void editarBeneficiarioCADeOtroBancoConCedula(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosInscritosPage(getDriver())
                .editarBeneficiarioDeOtrosBancos(
                        cuentaAhorroOtroBanco,
                        "Automatizacion IBP " + new GenerarData().getNumRandom(3),
                        "SWAZILAND",
                        "NA",
                        "NA",
                        true);
    }

    @Test(description = "Eliminar Beneficairio CA de otro banco con cedula") // 123456789452 // Listo
    void eliminarBeneficiarioCADeOtroBancoConCedula(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        String result = new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioNacional(
                        cuentaAhorroOtroBanco,
                        true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar" );
    }



    // Cuentas en dolar buena: 21022430021-01291570018-01820880044-13896360026-02033270022
    // Prestamos T24 -11272576



}
