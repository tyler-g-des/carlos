package pyt.beneficiariosinscritos;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 26/02/2024 9:39 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TestBeneficiariosInternacionales extends BaseTest {

    private String usuario = "03102674383";
    @Test(priority = 1,description = "Agregar beneficiario moneda en Dolares - Codigo SWIFT")
    void agregarBeneficiarioMonedaDolaresCodigoSWIFT(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)//00109852111
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioInternacional(
                        usuario,
                        "Carlos Loyola",
                        "22301391524",
                        "BERMUDA",
                        "LOS GIRASOLES",
                        "carlos_loyola@bhd.com.do",
                        "12345678990",
                        "Dólares",
                        "SWIFT",
                        "AAALSARIXXX",
                        false,
                        "NA",
                        "NA",
                        "NA",
                        "REGALIA",
                        "TARJETAS INTERN. DE CREDI",
                        "VIVIENDA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 2,description = "Actualizar beneficiario moneda en Dolares - Codigo SWIFT", dependsOnMethods = "agregarBeneficiarioMonedaDolaresCodigoSWIFT")
    void actualizarBeneficiarioMonedaDolaresCodigoSWIFT(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioInternacional(
                        usuario,
                        "12345678990",
                        "123456789",
                        "AUSTRALIA",
                        "Mi calle",
                        "josemanuel_perez@bhd.com.do",
                        "NA",
                        "REGALIA",
                        "NAVIERAS(ESTIBADORES,REMO",
                        "MEDICINA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 3,description = "Eliminar beneficiario moneda en Dolares - Codigo SWIFT", dependsOnMethods = "agregarBeneficiarioMonedaDolaresCodigoSWIFT")
    void eliminarBeneficiarioMonedaDolaresCodigoSWIFT(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .eliminarBeneficiarioInternacional("12345678990",true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    }



    @Test(priority = 4,description = "Agregar beneficiario moneda en Dolares - Codigo ABA")
    void agregarBeneficiarioMonedaDolaresCodigoABA(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)//00109852111
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioInternacional(
                        usuario,
                        "Carlos Loyola",
                        "22301391524",
                        "BERMUDA",
                        "LOS GIRASOLES",
                        "carlos_loyola@bhd.com.do",
                        "12345678994",
                        "Dólares",
                        "ABA",
                        "071125024",
                        false,
                        "NA",
                        "NA",
                        "NA",
                        "REGALIA",
                        "TARJETAS INTERN. DE CREDI",
                        "VIVIENDA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 5,description = "Actualizar beneficiario moneda en Dolares - Codigo ABA", dependsOnMethods = "agregarBeneficiarioMonedaDolaresCodigoABA")
    void actualizarBeneficiarioMonedaDolaresCodigoABA(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioInternacional(
                        usuario,
                        "12345678994",
                        "123456789",
                        "AUSTRALIA",
                        "Mi calle",
                        "josemanuel_perez@bhd.com.do",
                        "NA",
                        "REGALIA",
                        "NAVIERAS(ESTIBADORES,REMO",
                        "MEDICINA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 6,description = "Eliminar beneficiario moneda en Dolares - Codigo ABA", dependsOnMethods = "agregarBeneficiarioMonedaDolaresCodigoABA")
    void eliminarBeneficiarioMonedaDolaresCodigoABA(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .eliminarBeneficiarioInternacional("12345678994",true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    }



    @Test(priority = 7,description = "Agregar beneficiario moneda en Dolares con banco intermediario - Codigo SWIFT - Banco Intermediario ABA")
    void agregarBeneficiarioMonedaDolaresCodigoSWIFTBancoIntermediarioABA(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)//00109852111
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioInternacional(
                        usuario,
                        "Carlos Loyola",
                        "22301391524",
                        "BERMUDA",
                        "LOS GIRASOLES",
                        "carlos_loyola@bhd.com.do",
                        "12345678999",
                        "Dólares",
                        "SWIFT",
                        "AAALSARIXXX",
                        true,
                        "071125024",
                        "ABA",
                        "071125024",
                        "REGALIA",
                        "TARJETAS INTERN. DE CREDI",
                        "VIVIENDA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 8,description = "Actualizar beneficiario moneda en Dolares con banco intermediario - Codigo SWIFT - Banco Intermediario ABA", dependsOnMethods = "agregarBeneficiarioMonedaDolaresCodigoSWIFTBancoIntermediarioABA")
    void actualizarBeneficiarioMonedaDolaresCodigoSWIFTBancoIntermediarioABA(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioInternacional(
                        usuario,
                        "12345678999",
                        "123456789",
                        "AUSTRALIA",
                        "Mi calle",
                        "josemanuel_perez@bhd.com.do",
                        "NA",
                        "REGALIA",
                        "NAVIERAS(ESTIBADORES,REMO",
                        "MEDICINA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 9, description = "Eliminar beneficiario moneda en Dolares con banco intermediario - Codigo SWIFT - Banco Intermediario ABA", dependsOnMethods = "agregarBeneficiarioMonedaDolaresCodigoSWIFTBancoIntermediarioABA")
    void eliminarBeneficiarioMonedaDolaresCodigoSWIFTBancoIntermediarioABA(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .eliminarBeneficiarioInternacional("12345678999",true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    }



    @Test(priority = 10,description = "Agregar beneficiario moneda en Euros - Codigo SWIFT")
    void agregarBeneficiarioMonedaEurosCodigoSWIFT(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)//00109852111
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioInternacional(
                        usuario,
                        "Carlos Loyola",
                        "22301391524",
                        "BERMUDA",
                        "LOS GIRASOLES",
                        "carlos_loyola@bhd.com.do",
                        "12345698987",
                        "Euros",
                        "SWIFT",
                        "AAALSARIXXX",
                        false,
                        "NA",
                        "NA",
                        "NA",
                        "REGALIA",
                        "TARJETAS INTERN. DE CREDI",
                        "VIVIENDA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 11,description = "Actualizar beneficiario moneda en Euros - Codigo SWIFT", dependsOnMethods = "agregarBeneficiarioMonedaEurosCodigoSWIFT")
    void actualizarBeneficiarioMonedaEurosCodigoSWIFT(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioInternacional(
                        usuario,
                        "12345698987",
                        "123456789",
                        "AUSTRALIA",
                        "Mi calle",
                        "josemanuel_perez@bhd.com.do",
                        "NA",
                        "REGALIA",
                        "NAVIERAS(ESTIBADORES,REMO",
                        "MEDICINA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 12,description = "Eliminar beneficiario moneda en Euros - Codigo SWIFT", dependsOnMethods = "agregarBeneficiarioMonedaEurosCodigoSWIFT")
    void eliminarBeneficiarioMonedaEurosCodigoSWIFT(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .eliminarBeneficiarioInternacional("12345698987",true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    }



    @Test(priority = 4,description = "Agregar beneficiario moneda en Euros - Codigo ABA")
    void agregarBeneficiarioMonedaEurosCodigoABA(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)//00109852111
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioInternacional(
                        usuario,
                        "Carlos Loyola",
                        "22301391524",
                        "BERMUDA",
                        "LOS GIRASOLES",
                        "carlos_loyola@bhd.com.do",
                        "12387967994",
                        "Euros",
                        "ABA",
                        "071125024",
                        false,
                        "NA",
                        "NA",
                        "NA",
                        "REGALIA",
                        "TARJETAS INTERN. DE CREDI",
                        "VIVIENDA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 5,description = "Actualizar beneficiario moneda en Euros - Codigo ABA", dependsOnMethods = "agregarBeneficiarioMonedaEurosCodigoABA")
    void actualizarBeneficiarioMonedaEurosCodigoABA(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioInternacional(
                        usuario,
                        "12387967994",
                        "123456789",
                        "AUSTRALIA",
                        "Mi calle",
                        "josemanuel_perez@bhd.com.do",
                        "NA",
                        "REGALIA",
                        "NAVIERAS(ESTIBADORES,REMO",
                        "MEDICINA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 6,description = "Eliminar beneficiario moneda en Euros - Codigo ABA", dependsOnMethods = "agregarBeneficiarioMonedaEurosCodigoABA")
    void eliminarBeneficiarioMonedaEurosCodigoABA(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .eliminarBeneficiarioInternacional("12387967994",true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    }









    //@Test(description = "Agregar beneficiario")
    void agregarBeneficiarioInternacional(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)//00109852111
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioInternacional(
                        "22301391524",
                        "Carlos Loyola",
                        "22301391524",
                        "BERMUDA",
                        "LOS GIRASOLES",
                        "carlos_loyola@bhd.com.do",
                        "1234567890",
                        "Euros",
                        "SWIFT",
                        "AAALSARIXXX",
                        true,
                        "1243569788",
                        "ABA",
                        "071125024",
                        "REGALIA",
                        "TARJETAS INTERN. DE CREDI",
                        "VIVIENDA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    //@Test(description = "Actualizar beneficiario", dependsOnMethods = "agregarBeneficiarioInternacional")
    void actualizarBeneficiarioInternacional(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioInternacional(
                        "22301391524",
                        "1234567890",
                        "123456789",
                        "AUSTRALIA",
                        "Mi calle",
                        "josemanuel_perez@bhd.com.do",
                        "Euros",
                        "REGALIA",
                        "NAVIERAS(ESTIBADORES,REMO",
                        "MEDICINA",
                        true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    //@Test(description = "Eliminar beneficiario", dependsOnMethods = "agregarBeneficiarioInternacional")
    void eliminarBeneficiario(){
        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .eliminarBeneficiarioInternacional("1234567890",true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    }


    //@Test(description = "Actualizar beneficiario")
    void actualizarBeneficiarioInternacional2(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioInternacional("22301391524","123456789","123456789","AUSTRALIA","Mi calle","carlos_loyola@bhd.com.do","Euros","REGALIA","NAVIERAS(ESTIBADORES,REMO","VIVIENDA",true);

        Assert.assertEquals(result,"El beneficiario ha sido guardado correctamente");
    }

    //@Test(description = "Agregar beneficiario Existente")
    void agregarBeneficiarioInternacionalExistente(){

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111", false)
                .irPaginaPagosTransferencias(true)
                .beneficiariosInscritos(true)
                .agregarBeneficiarioInternacionalExistente(
                        "22301391524",
                        "BANCO DE AHORRO Y CREDITO CONFISA, S.A.",
                        "22301391524",
                        "BERMUDA",
                        "LOS GIRASOLES",
                        "carlos_loyola@bhd.com.do",
                        "123456789",
                        "Euros",
                        "SWIFT",
                        "AAALSARIXXX",
                        true,
                        "1243569788",
                        "SWIFT",
                        "AAALSARIXXX",
                        "REGALIA",
                        "TARJETAS INTERN. DE CREDI",
                        "VIVIENDA",
                        true);
        System.out.println(result);
    }


}
