package transferencias;

import basetest.BaseTest;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;
import microservicios.dashboard.MsDashboardCuentas;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;
import pages.pyt.voucher.PoVoucherPage;
import pages.pyt.voucher.VoucherLogica;

import java.util.Random;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 11/06/2024 2:21 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TransferenciaPagoInstenteBCRD extends BaseTest {

    MsDashboardCuentas consultarCuenta = new MsDashboardCuentas();
    @Test(description = "Transferencia Cuenta Corriente -> Tarjeta Credito")
    void transferenciaCuentaCorrienteATarjetaCredito(){
        //String actual =
       // float montoActual = consultarCuenta.newQuery("03102674383").buscar(ctaOrigenDolar).getBalanceFlotante();


        MsBeneficiariosNacionales beneficiarios;
                //VoucherLogica voucher =
       String actual =                 LoginLogic.get().iniciarDesdeDispositivoSeguro("03102674383","1111", false)//01800705749 .transferenciaACHInscrito("22794710018","Nuevo beneficiario",true);
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .transferenciaPagoAlIsntanteInscrito(
                        "00480830024",
                        "3897145664587895",
                        "35",
                        "carlos_loyola@bhd.com.do",
                        "AHC auto ibp CC a CC",
                        false,
                        true);
    //    beneficiarios = new MsBeneficiariosNacionales("03102674383").newQuery().selecOtrosBancos().buscar("2323232323232323");

       // consultarCuenta.newQuery(cedula).buscar(ctaOrigenDolar);
//        voucher.validarDataPagoInstante(
//                "35",
//                "RD",
//                "RD$ 100.00",
//                "RD",
//                consultarCuenta.getNumProducto(),
//                consultarCuenta.getNombreProducto(),
//                consultarCuenta.getAlias(),
//                beneficiarios.getNumProducto(),
//                beneficiarios.getNombre(),
//                beneficiarios.getAlias(),
//                "Descripcion",
//                true);

        String esperado = "En proceso, Completado";
        String numConfirm =  PoVoucherPage.getNewPage().getNumeroConfirmacion(true);
        System.out.println(numConfirm);
        Assert.assertTrue( esperado.contains( actual));
    }
}
