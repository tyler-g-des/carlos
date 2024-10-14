package transferencias;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;
import pages.pyt.voucher.PoVoucherPage;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 06/06/2024 4:22 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TransferenciaACHTest extends BaseTest {

    @Test(description = "Transferencia Cuenta Ahorros Pesos -> Cuenta ahorros")
    void transferenciaCuentaAhorroPesosACuentaAhorros(){
        String actual = LoginLogic.get().iniciarDesdeDispositivoSeguro("00100965995","1111", false)//01800705749 .transferenciaACHInscrito("22794710018","Nuevo beneficiario",true);
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .transferenciaACHInscrito("09896960030","10225448755203","25","carlos_loyola@bhd.com.do","AHC auto ibp",false,true);

        String esperado = "En proceso, Completado";
        String numConfirm =  PoVoucherPage.getNewPage().getNumeroConfirmacion(true);
        System.out.println(numConfirm);
        Assert.assertTrue( esperado.contains( actual));
    }

    @Test(description = "Transferencia Cuenta Corriente -> Cuenta Corriente")
    void transferenciaCuentaCorrienteACuentaCorriente(){
        String actual = LoginLogic.get().iniciarDesdeDispositivoSeguro("03102674383","1111", false)//01800705749 .transferenciaACHInscrito("22794710018","Nuevo beneficiario",true);
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .transferenciaACHInscrito("00480830024","55857551699",
                        "30","carlos_loyola@bhd.com.do",
                        "AHC auto ibp CC a CC",false,true);

        String esperado = "En proceso, Completado";
        String numConfirm =  PoVoucherPage.getNewPage().getNumeroConfirmacion(true);
        System.out.println(numConfirm);
        Assert.assertTrue( esperado.contains( actual));
    }

    @Test(description = "Transferencia Cuenta Corriente -> Tarjeta Credito")
    void transferenciaCuentaCorrienteATarjetaCredito(){
        String actual = LoginLogic.get().iniciarDesdeDispositivoSeguro("03102674383","1111", false)//01800705749 .transferenciaACHInscrito("22794710018","Nuevo beneficiario",true);
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .transferenciaACHInscrito("00480830024","3897145664587895",
                        "35","carlos_loyola@bhd.com.do","AHC auto ibp CC a CC",
                        false,true);

        String esperado = "En proceso, Completado";
        String numConfirm =  PoVoucherPage.getNewPage().getNumeroConfirmacion(true);
        System.out.println(numConfirm);
        Assert.assertTrue( esperado.contains( actual));
    }


    // A nuevo beneficiario

    @Test(description = "Transferencia Cuenta Corriente -> Cuenta Corriente Nuevo beneficiario")
    void transferenciaCuentaCorrienteACuentaCorrienteNuevoBeneficiario (){
        String actual =
                LoginLogic.get().iniciarDesdeDispositivoSeguro("03102674383","1111", false)//01800705749 .transferenciaACHInscrito("22794710018","Nuevo beneficiario",true);
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .transferenciaACHNuevoBeneficiario(
                        "00480830024", // 12,286,808.53
                        "CITIBANK, N.A.",
                        "Cuentas Corrientes",
                        "4967584912",
                        "Cédula",
                        "22301391524",
                        "Carlos Alberto Loyola Tejeda",
                        "Mi alias",
                        "NA",
                        "Masculino",
                        "carlos_loyola@bhd.com.do",
                        "15",
                        "Mi descripcion de prueba",
                        true,
                        true,
                        true);

        String esperado = "En proceso, Completado";
        String numConfirm =  PoVoucherPage.getNewPage().getNumeroConfirmacion(true);
        System.out.println(numConfirm);
        Assert.assertTrue( esperado.contains( actual));
    }

    @Test(description = "Transferencia Cuenta Ahorros Pesos -> Cuenta Corriente Nuevo beneficiario")
    void transferenciaCuentaAhorrosPesosACuentaCorrienteNuevoBeneficiario (){
        String actual =
                LoginLogic.get().iniciarDesdeDispositivoSeguro("03102674383","1111", false)//01800705749 .transferenciaACHInscrito("22794710018","Nuevo beneficiario",true);
                        .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                        .transferenciaACHNuevoBeneficiario(
                                "00480830067",
                                "CITIBANK, N.A.",
                                "Cuentas Corrientes",
                                "4967584912",
                                "Cédula",
                                "22301391524",
                                "Carlos Alberto Loyola Tejeda",
                                "Mi alias",
                                "NA",
                                "Masculino",
                                "carlos_loyola@bhd.com.do",
                                "25",
                                "de ctaAhorro a cta.Corriente",
                                true,
                                true,
                                true);

        String esperado = "En proceso, Completado";
        String numConfirm =  PoVoucherPage.getNewPage().getNumeroConfirmacion(true);
        System.out.println(numConfirm);
        Assert.assertTrue( esperado.contains( actual));
    }




}
