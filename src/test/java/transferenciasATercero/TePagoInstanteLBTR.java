package transferenciasATercero;

import basetest.BaseTest;
import microservicios.Utilidad;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;
import microservicios.dashboard.MsDashboardCuentas;
import org.testng.annotations.Test;
import pages.login.LoginLogic;
import pages.pyt.voucher.PoVoucherPage;
import pages.pyt.voucher.VoucherLogica;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 16/04/2024 10:15 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TePagoInstanteLBTR extends BaseTest {
    MsDashboardCuentas consultarCuenta = new MsDashboardCuentas();


    @Test(description = "PAGO AL INSTANTE --> CA US$ --> TARJETA US$")
    void deCuentAhorroDolar_ATarjetaCreditoInscrito(){
        String ctaOrigenDolar = "05605070011";
        String cedula = "00100965995";
        String monto = "1000";

        float montoActual = consultarCuenta.newQuery(cedula).buscar(ctaOrigenDolar).getBalanceFlotante();
        System.out.println("Balance INICIAR: " + Utilidad.convertStringToMoneyFormat(montoActual) );


        MsBeneficiariosNacionales beneficiarios;
        VoucherLogica voucher =
                LoginLogic.get().iniciarDesdeDispositivoSeguro(cedula,"1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlIsntanteInscrito(
                        ctaOrigenDolar,
                        "2323232323232323",
                        monto,
                        "carlos_loyola@bhd.com.do",
                        "Descripcion",
                        true,
                        true);

        beneficiarios = new MsBeneficiariosNacionales(cedula).newQuery().selecOtrosBancos().buscar("2323232323232323");

        consultarCuenta.newQuery(cedula).buscar(ctaOrigenDolar);

        voucher.validarDataPagoInstante(
                monto,
                "US",
                "US$ 5.00",
                "US",
                consultarCuenta.getNumProducto(),
                consultarCuenta.getNombreProducto(),
                consultarCuenta.getAlias(),
                beneficiarios.getNumProducto(),
                beneficiarios.getNombre(),
                beneficiarios.getAlias(),
                "Descripcion",
                true);

//        PoVoucherPage.getNewPage()
//                .imprimirMensajePresentacion(true)
//                .imprimirEstadoTransaccion(true)
//                .validarProductosConBeneficiario(
//                        consultarCuenta.getNumProducto(),
//                        consultarCuenta.getNombreProducto(),
//                        consultarCuenta.getAlias(),
//                        beneficiarios.getNumProducto(),
//                        beneficiarios.getNombre(),
//                        beneficiarios.getAlias())
//                .validarComision("US$ 5.00")
//                .validarImpuesto("US$ 0.30")
//                .validarMontoDescripcion("US$ 200.00","Descripcion");

        System.out.println(PoVoucherPage.getNewPage().getStatus(false));

        montoActual = consultarCuenta.newQuery(cedula).buscar(ctaOrigenDolar).getBalanceFlotante();
        System.out.println("Balance INICIAR: " + montoActual);



    }

    //@Test(description = "Contrantos sin acitar transacciones")
    void capturaContratoPagoInstante(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("00101391548","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlIsntanteInscrito(
                        "02016990062",
                        "4678765342987213",
                        "200",
                        "carlos_loyola@bhd.com.do",
                        "Descripcion",
                        true,
                        true)
                .validarTransferenciaPagoInstente(true);
    }




    //  Nuevos beneficiarios

    //@Test(description = "De Cuenta Ahorros Pesos a Cuenta Ahorros Pesos - Empleado")
    void deCTAhorroPesos_CTAhorrosPesosNuevoBeneficiarioClienteExterno(){
        MsDashboardCuentas cuentas;

        LoginLogic.get().iniciarDesdeDispositivoSeguro("00101391548","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlInstanteNuevoBeneficiario(
                        "02016990046",
                        "ASOCIACION DUARTE DE AHORROS Y PRESTAMOS",
                        "Cuentas Corrientes",
                        "45432156465654",
                        "Pasaporte",
                        "22301391524",
                        "Carlos Alberto Loyola Tejeda",
                        "NA",
                        "BAHREN",
                        "Femenino",
                        "carlos_loyola@bhd.com.do",
                        "100",
                        "DESCRIPCION",
                        false,
                        false,
                        true);


        cuentas = consultarCuenta.newQuery("00101391548").buscar("02016990046");

        PoVoucherPage.getNewPage().imprimirMensajePresentacion(true)
                .imprimirEstadoTransaccion(true)
                .validarMontoDescripcion("RD$ 100.00","DESCRIPCION")
                .validarComision("RD$ 100.00")
                .validarProductosConBeneficiario(
                        cuentas.getNumProducto(),
                        cuentas.getNombreProducto(),
                        cuentas.getAlias(),
                        "45432156465654",
                        "Carlos Alberto Loyola Tejeda",
                        "NA");
    }
}
