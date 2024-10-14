package test2024.pagetest.pagostransferencias.beneficiariosinscritos;

import basetest.BaseTest;
import data.GenerarData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.POLoginPage;
import pages.pagostransferencias.accesos.beneficiarios.POBeneficiariosInscritosPage;

public class TestBeneficiariosInternacionales extends BaseTest {

    private String beneficiarioCodigoSwiftMonedaDolar = "";
    private String beneficiarioCodigoABA_MonedaDolar = "";
    private String beneficiarioCodigoSwiftMonedaEuro = "";
    private String beneficiarioMondaDolarConBancoIntermediario = "";
    private String beneficiarioMondaEuroConBancoIntermediario = "";




    @Test(description = "Agregar beneficiario codigo SWIFT -> Moneda dolares" )
    void agregarBeneficiarioCodigoSwiftMonedaDolares(){
        beneficiarioCodigoSwiftMonedaDolar = new GenerarData().getNumRandomString(14);

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true)
                .agregarBeneficiarioInternacional(
                        GenerarData.getNombreCompletoMasculino(),
                        new GenerarData().getNumRandomString(6),
                        "BAHREN",
                        "Mi calle",
                        "carlos_loyola@bhd.com.do",
                        beneficiarioCodigoSwiftMonedaDolar,
                        "Dólares",
                        "SWIFT",
                        "AAALSARIXXX",
                        "PAGOS BIENES IMPORTACIONE",
                        "FIRMAS DE ABOGADOS",
                        "COLEGIOS",
                        true);
    }

    @Test(description = "Editar beneficiario codigo SWIFT de -> Moneda dolares a Euros", dependsOnMethods = "agregarBeneficiarioCodigoSwiftMonedaDolares")
    void editarBeneficiarioDeDolaresAEurosCodigoSwift(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true)
                .formularioEditarBeneficiarioInternacional(
                        beneficiarioCodigoSwiftMonedaDolar,
                        new GenerarData().getNumRandomString(5),
                        "INGLATERRA",
                        "La misma calle de ayer " + new GenerarData().getNumRandomString(3),
                        "ca.loyola.tejeda@gmail.com",
                        "Euros",
                        "TRANSFERENCIA DE TECNOLOG",
                        "OTROS",
                        "MEDICINA",
                        true);
    }

    @Test(description = "Eliminar beneficiario codigo SWIFT editado de -> moneda dolar a euros", dependsOnMethods = "agregarBeneficiarioCodigoSwiftMonedaDolares")
    void eliminarBeneficiarioEditadoDeDolarAEuros(){

        String resultado = POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true)
                .eliminarBeneficiarioInternacional(
                        beneficiarioCodigoSwiftMonedaDolar,
                        true);

        Assert.assertEquals( resultado,"No se encontraron registros para mostrar");

    }




    @Test(description = "Agregar beneficiario codigo ABA -> Moneda dolares" )
    void agregarBeneficiarioCodigoABA_MonedaDolares(){
        beneficiarioCodigoABA_MonedaDolar = new GenerarData().getNumRandomString(14);

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true)
                .agregarBeneficiarioInternacional(
                        GenerarData.getNombreCompletoMasculino(),
                        new GenerarData().getNumRandomString(6),
                        "BAHREN",
                        "Mi calle",
                        "carlos_loyola@bhd.com.do",
                        beneficiarioCodigoABA_MonedaDolar,
                        "Dólares",
                        "ABA",
                        "011001276",
                        "PAGOS BIENES IMPORTACIONE",
                        "FIRMAS DE ABOGADOS",
                        "COLEGIOS",
                        true);
    }

    @Test(description = "Editar beneficiario codigo ABA de -> Moneda dolares a Euros", dependsOnMethods = "agregarBeneficiarioCodigoABA_MonedaDolares")
    void editarBeneficiarioCodigoABA_DeDolaresAEuros(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true)
                .formularioEditarBeneficiarioInternacional(
                        beneficiarioCodigoABA_MonedaDolar,
                        new GenerarData().getNumRandomString(5),
                        "INGLATERRA",
                        "La misma calle de ayer " + new GenerarData().getNumRandomString(3),
                        "ca.loyola.tejeda@gmail.com",
                        "Euros",
                        "TRANSFERENCIA DE TECNOLOG",
                        "OTROS",
                        "MEDICINA",
                        true);
    }

    @Test(description = "Eliminar beneficiario codigo ABA editado de -> moneda dolar a euros", dependsOnMethods = "agregarBeneficiarioCodigoABA_MonedaDolares")
    void eliminarBeneficiarioCodigoABA_EditadoDeDolarAEuros(){

        String resultado = POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true)
                .eliminarBeneficiarioInternacional(
                        beneficiarioCodigoABA_MonedaDolar,
                        true);

        Assert.assertEquals( resultado,"No se encontraron registros para mostrar");

    }





    @Test(description = "Agregar beneficiario -> Moneda Euros")
    void agregarBeneficiarioMonedaEuros(){

        beneficiarioCodigoSwiftMonedaEuro = new GenerarData().getNumRandomString(14);

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true)
                .agregarBeneficiarioInternacional(
                        GenerarData.getNombreCompletoMasculino(),
                        new GenerarData().getNumRandomString(8),
                        "BAHREN",
                        "Mi calle",
                        "carlos_loyola@bhd.com.do",
                        beneficiarioCodigoSwiftMonedaEuro,
                        "Euros",
                        "SWIFT",
                        "AAALSARIXXX",
                        "PAGOS BIENES IMPORTACIONE",
                        "FIRMAS DE ABOGADOS",
                        "COLEGIOS",
                        true);
    }

    @Test(description = "Editar beneficiario de -> Moneda dolares a Euros", dependsOnMethods = "agregarBeneficiarioMonedaEuros")
    void editarBeneficiarioDeEurosADolares(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiariosInscritosPage(getDriver())
                .formularioEditarBeneficiarioInternacional(
                        beneficiarioCodigoSwiftMonedaEuro,
                        new GenerarData().getNumRandomString(9),
                        "INGLATERRA",
                        "La misma calle de ayer",
                        "ca.loyola.tejeda@gmail.com",
                        "Dólares",
                        "TRANSFERENCIA DE TECNOLOG",
                        "OTROS",
                        "MEDICINA",
                        true);
    }



    @Test(description = "Eliminar beneficiario", dependsOnMethods = "agregarBeneficiarioMonedaEuros")
    void eliminarBeneficiario(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        String resultado = new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioInternacional(
                        beneficiarioCodigoSwiftMonedaEuro,
                        true);

        System.out.println(resultado);

    }




    //@Test(description = "Agregar beneficiario -> Dolares con banco intermediario")
    void agregarBeneficiarioDolaresConBancoIntermediario(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioIntermediarioConBancoIntermediario(
                        "Carlos Loyola",
                        "12345678",
                        "BAHREN",
                        "Mi calle",
                        "carlos_loyola@bhd.com.do",
                        "123456789",
                        "Euros",
                        "SWIFT",
                        "AAALSARIXXX",
                        "798656465689865656686865",
                        "SWIFT",
                        "AAALSARIXXX",
                        "PAGOS BIENES IMPORTACIONE",
                        "FIRMAS DE ABOGADOS",
                        "COLEGIOS",

                        true);

    }

    //@Test(description = "Agregar beneficiario -> Euros con banco intermediario")
    void agregarBeneficiarioEurosConBancoIntermediario(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioIntermediarioConBancoIntermediario(
                        "Carlos Loyola",
                        "12345678",
                        "BAHREN",
                        "Mi calle",
                        "carlos_loyola@bhd.com.do",
                        "123456789",
                        "Euros",
                        "SWIFT",
                        "AAALSARIXXX",
                        "798656465689865656686865",
                        "SWIFT",
                        "AAALSARIXXX",
                        "PAGOS BIENES IMPORTACIONE",
                        "FIRMAS DE ABOGADOS",
                        "COLEGIOS",

                        true);

    }

    //@Test(description = "Editar beneficiario con banco Intermediario")
    void editarBeneficiarioConBancoIntermediario(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiariosInscritosPage(getDriver())
                .formularioEditarBeneficiarioInternacional(
                        "1234567899",
                        "7777777777",
                        "INGLATERRA",
                        "La misma calle de ayer",
                        "ca.loyola.tejeda@gmail.com",
                        "Dólares",
                        "TRANSFERENCIA DE TECNOLOG",
                        "OTROS",
                        "MEDICINA",
                        true);

    }

    //@Test(description = "Eliminar beneficiario -> con banco Intermediario", priority = 2)
    void eliminarBeneficiarioConBancoIntermediario(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        String resultado = new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioInternacional(
                        "1234567899",
                        true);

        System.out.println(resultado);

    }














    //@Test(description = "Editar beneficiario")
    void formularioEditarBeneficiarioInternacional(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiariosInscritosPage(getDriver())
                .formularioEditarBeneficiarioInternacional(
                        "1234567899",
                        "7777777777",
                        "INGLATERRA",
                        "La misma calle de ayer",
                        "ca.loyola.tejeda@gmail.com",
                        "Dólares",
                        "TRANSFERENCIA DE TECNOLOG",
                        "OTROS",
                        "MEDICINA",
                        true);

    }

    //@Test(description = "Eliminar beneficiario", priority = 2)
    void formularioEliminarBeneficiarioInternacional(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        String resultado = new POBeneficiariosInscritosPage(getDriver())
                .eliminarBeneficiarioInternacional(
                        "1234567899",
                        true);

        System.out.println(resultado);

    }

    //@Test(description = "Formulario Beneficiario Internacional")
    void formularioBeneficiarioInternacionalBancoIntermediario(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiariosInscritosPage(getDriver())
                .agregarBeneficiarioIntermediarioConBancoIntermediario(
                        "Carlos Loyola",
                        "12345678",
                        "BAHREN",
                        "Mi calle",
                        "carlos_loyola@bhd.com.do",
                        "123456789",
                        "Euros",
                        "SWIFT",
                        "AAALSARIXXX",
                        "798656465689865656686865",
                        "SWIFT",
                        "AAALSARIXXX",
                        "PAGOS BIENES IMPORTACIONE",
                        "FIRMAS DE ABOGADOS",
                        "COLEGIOS",

                        true);

    }
}
