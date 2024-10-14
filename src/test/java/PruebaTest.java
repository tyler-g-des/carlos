import basetest.BaseTest;
import data.Persistencia;
import microservicios.MsContratos;
import microservicios.dashboard.MsDashboardCuentas;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PODashboardPage;
import pages.login.LoginLogic;
import pages.login.POForgotPassword;
import pages.login.POLoginPage;
import pages.pyt.voucher.PoVoucherPage;

import java.util.Random;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 18/02/2024 12:53 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PruebaTest extends BaseTest {

    //@Test(description = "A tercero BHD - Nuevo Beneficiario")
    void formularioTerceroBHDNuevoBebeficiario(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("40221056902","1111",false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .transferenciaTerceroBHDNuevoBeneficiario(
                        "10155730048",
                        "BANCO MULTIPLE BHD, S.A.",
                        "01289910014",
                        "Alias",
                        "carlos_loyola@bhd.com.do",
                        "120",
                        "Descripcion",
                        true,
                        true,
                        true);
    }

    //@Test(description = "Prueba formulario Pago al Instante")
    void pruebaFormulariosPagoInstante(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("01800705749","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .transferenciaATerceroPagoAlInstanteOtrosBancos("22794710018","Nuevo beneficiario",true);
    }



    //@Test(description = "PRUEBA FORMULARIO TRANSFERENCIA SIPA")
    void pruebaFormularioTransferenciaSIPA(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .transferenciasRegionalesSIPA(true)
                .transferencia(
                        "04005280040",
                        "Carlos Alberto Loyola Tejeda",
                        "22301391524",
                        "GUATEMALA",
                        "La calle de mi casa",
                        "carlos_loyola@bhd.com.do",
                        "AGROMERCANTIL DE GUATEMALA, S.A.",
                        "546323564623",
                        "100",
                        "Descripcion",
                        true);
    }

    //@Test(description = "PRUEBA FORMULARIO TRANSFERENCIA SIPA")
    void pruebaFormularioTransferenciaSIPA2(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .transferenciasRegionalesSIPA(false)
                .transferencia(
                        "04005280040",
                        "Carlos Alberto Loyola Tejeda",
                        "22301391524",
                        "GUATEMALA",
                        "La calle de mi casa",
                        "carlos_loyola@bhd.com.do",
                        "AGROMERCANTIL DE GUATEMALA, S.A.",
                        "546323564623",
                        "100",
                        "Descripcion",
                        true);
    }


   //@Test(description = "TRANSFERENCIA INTERNACIONAL")
    void pruebaFormularioTransferenciaInternacional(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .transferenciaInternacional(false)
                .transferenciaNuevoBeeneficiario(
                        "04005280015",
                        "Carlos Alberto Loyola",
                        "54325466563",
                        "ANTARTICA",
                        "LOS GIRASOLES",
                        "carlos_loyola@bhd.com.do",
                        "4646446546",
                        "SWIFT",
                        "AAALSARIXXX",
                        true,
                        "1243569788",
                        "ABA",
                        "071125024",
                        "REGALIA",
                        "TARJETAS INTERN. DE CREDI",
                        "VIVIENDA",
                        "Euros",
                        "10",
                        "Descripicon automatizacion " + new Random().nextInt(50),
                        false,
                        true);
    }

    //@Test(description = "TRANSFERENCIA INTERNACIONAL")
    void pruebaFormularioTransferenciaInternacionalInscrito(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .transferenciaInternacional(false)
                .transferenciaBeneficiarioInscrito(
                        "04005280015",
                        "123456789123456789123456789",
                        "OTROS",
                        "Euros",
                        "200",
                        "carlos_loyola@bhd.com.do",
                        "Descripcion",
                        true);
    }

    //@Test(description = "Prueba formulario Transferencia Pin Pesos")
    void pruebaTransferenciaPinPesos(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("03102892530","1111", false)
                .pinPesos(false)
                .transferencia(
                        "04005280015",
                        "8092031322",
                        "200",
                        "Descripcion",
                        true,
                        true);
    }

    //@Test(description = "Prueba formulario Transferencia Pin Pesos")
    void consultarDetalleImpuestoBolsa(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("03102674383","1111", false)
                .consultarDetallePuestoBolsa(false);
    }

    MsDashboardCuentas consultarCuenta = new MsDashboardCuentas();



    //@Test(description = "PAGO AL INSTANTE --> CA EU$ --> CA EU$")
    void aNuevoBeneficiarioDeCTAhorrosEuro_CTAhorroEuro_UsuarioExterno(){
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
        //.validarTransferenciaPagoInstente(true);


        cuentas = consultarCuenta.newQuery("00101391548").buscar("02016990046");


        PoVoucherPage.getNewPage().imprimirMensajePresentacion(true)
                .imprimirEstadoTransaccion(true)
                .validarProductosConBeneficiario(
                        cuentas.getNumProducto(),
                        cuentas.getNombreProducto(),
                        cuentas.getAlias(),
                        "45432156465654",
                        "Carlos Alberto Loyola Tejeda",
                        "NA")
                .validarTipoDeTransaccion("Transaccion entre productos BHD y a otros Bancos ( LBTR )");
    }

    //@Test(description = "PAGO AL INSTANTE --> CA US$ --> TARJETA US$")
    void aNuevoBeneficiarioDeCTAhorrosDolar_TarjetaCredito_UsuarioExterno(){
        MsDashboardCuentas cuentas;

        LoginLogic.get().iniciarDesdeDispositivoSeguro("00100965995","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlInstanteNuevoBeneficiario(
                        "05605070011",
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
        //.validarTransferenciaPagoInstente(true);


        cuentas = consultarCuenta.newQuery("00100965995").buscar("05605070011");


        PoVoucherPage.getNewPage().imprimirMensajePresentacion(true)
                .imprimirEstadoTransaccion(true)
                .validarProductosConBeneficiario(
                        cuentas.getNumProducto(),
                        cuentas.getNombreProducto(),
                        cuentas.getAlias(),
                        "45432156465654",
                        "Carlos Alberto Loyola Tejeda",
                        "NA")
                .validarTipoDeTransaccion("Transaccion entre productos BHD y a otros Bancos ( LBTR )");
    }

    //@Test(description = "Contrantos sin acitar transacciones")
    void capturaContratoPagoInstanteNuevoBeneficiario(){
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
                //.validarTransferenciaPagoInstente(true);


        cuentas = consultarCuenta.newQuery("00101391548").buscar("02016990046");


        PoVoucherPage.getNewPage().imprimirMensajePresentacion(true)
                .imprimirEstadoTransaccion(true)
                .validarProductosConBeneficiario(
                        cuentas.getNumProducto(),
                        cuentas.getNombreProducto(),
                        cuentas.getAlias(),
                        "45432156465654",
                        "Carlos Alberto Loyola Tejeda",
                        "NA")
                .validarTipoDeTransaccion("Transaccion entre productos BHD y a otros Bancos ( LBTR )");
    }

    //@Test(description = "Contrantos sin acitar transacciones")
    void capturaContratoPagoInstanteNuevoBeneficiarios(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("00101391548","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlInstanteNuevoBeneficiario(
                        "02016990046",
                        "ASOCIACION DUARTE DE AHORROS Y PRESTAMOS",
                        "Tarjetas de Crédito",
                        "4545454545545454",
                        "Pasaporte",
                        "22301391524",
                        "Carlos Alberto Loyola Tejeda",
                        "NA",
                        "BAHREN",
                        "Femenino",
                        "carlos_loyola@bhd.com.do",
                        "5000",
                        "DESCRIPCION",
                        true,
                        true,
                        true);
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

    //@Test(description = "Contrantos sin acitar transacciones Pesos")
    void capturaContratoPagoInstantePesos(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("00101391548","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlIsntanteInscrito(
                        "23406930016",
                        "4670983246549812",
                        "5000",
                        "carlos_loyola@bhd.com.do",
                        "Descripcion",
                        true,
                        true);
    }

    //@Test(description = "Contrantos sin acitar transacciones Pesos Usuario interno")
    void capturaContratoPagoInstantePesosUsuarioInterno(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("00111435384","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlIsntanteInscrito(
                        "01289910057",
                        "406584664134",
                        "5000",
                        "carlos_loyola@bhd.com.do",
                        "Descripcion",
                        true,
                        true);
    }

    //@Test(description = "Pago Instante CA USD a TC BANCO CARIBE")
    void verificarMonedaPagoInstanteInscrito(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("00101391548","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlIsntanteInscrito(
                        "02016990062",
                        "4678765342987213",
                        "100",
                        "carlos_loyola@bhd.com.do",
                        "Descripcion",
                        true,
                        true);
    }

    //@Test(description = "Pago Instante CA USD a Prestamo banco Popular")
    void pagoInstenteCA_USDaPrestamoPopular(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("00101391548","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlIsntanteInscrito(
                        "02016990062",
                        "332434565432",
                        "100",
                        "carlos_loyola@bhd.com.do",
                        "Descripcion",
                        true,
                        true);
    }

    //@Test(description = "Pago Instante CA USD a Cuenta Ahorros Banco ADEMI")
    void pagoInstenteCA_USDaCuentaAhorroAdemi(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("00101391548","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlIsntanteInscrito(
                        "02016990062",
                        "1234567891",
                        "100",
                        "carlos_loyola@bhd.com.do",
                        "Descripcion",
                        true,
                        true);
    }

    //@Test(description = "Pago Instante CA EUR a Cuenta Ahorros Banco ADEMI")
    void pagoInstenteCA_EURaCuentaAhorroAdemi(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("00101391548","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlIsntanteInscrito(
                        "02016990127",
                        "1234567891",
                        "100",
                        "carlos_loyola@bhd.com.do",
                        "Descripcion",
                        true,
                        true);
    }

    //@Test(description = "Pago Instante CA EUR a Prestamo popular")
    void pagoInstenteCA_EURaCuentaPrestamoPopular(){
        LoginLogic.get().iniciarDesdeDispositivoSeguro("00101391548","1111", false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .someterPagoAlIsntanteInscrito(
                        "02016990127",
                        "332434565432",
                        "100",
                        "carlos_loyola@bhd.com.do",
                        "Descripcion",
                        true,
                        true);
    }

    //@Test(description = "Prueba formulario Transferencia Pin Pesos2")
    void pruebaTransferenciaPinPesos2(){

        LoginLogic.get().iniciarDesdeDispositivoSeguro("03102892530","1111", false)
                .pinPesos(false)
                .transfenciaNuevoBeneficiario(
                        "04005280015",
                        "Carlos",
                        "8293371829",
                        "Cédula",
                        "22301391524",
                        "Carlos A, Loyola Tejeda",
                        "TURQUIA",
                        "Masculino",
                        "2000",
                        "Descripcion",
                        true,
                        true,
                        true);
    }


    //@Test(description = "Cambiar pregunta seguridad")
    void cambiarPreguntaSeguridad(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111",false)
                .configuracion(false)
                .cambiarPreguntaSeguridad(
                        "Cuál era tu apodo cuando niño/niña","Soledad 1",
                        "Cuál era tu actividad preferida en la escuela","Solo 2",
                        "Cuál es el nombre de la Iglesia que frecuenta","Sila 3",
                        "Cuál es el apellido del profesor de tu colegio que menos te agradaba","Silla 4",
                        "Quién es tu personaje histórico favorito"," Madera 5",
                        true
                );
    }

    //@Test(description = "Cambiar pregunta seguridad Propias")
    void cambiarPreguntaSeguridadPropias(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111",false)
                .configuracion(false)
                .cambiarPreguntaSeguridadPreguntasPropias(
                        "Donde naciste","Soledad 1",
                        "Donde creciste","Solo 2",
                        "Donde pensaste","Sila 3",
                        "Donde llegaste","Silla 4",
                        "Donde te imaginaste"," Madera 5",
                        true
                );
    }

    //@Test(description = "Detalle de producto")
    void detalleCuenta(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111435384","1111",false)
                .delleDeCuenta("01289910049",true);
    }

    //@Test(description = "Detalle de Certificado")
    void detalleCertificado(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111435384","1111",false)
                .delleDeCertificado("2018676",true);
    }

    //@Test(description = "Contrato ACH")
    void contratoACH(){
        boolean result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111",false)
                .configuracion(true)
                .activarContratoACHPagoInstanteBCRD(true);

        //Assert.assertTrue(result);
        Assert.assertTrue(new MsContratos("22301391524").contractACHPagoAlInstanteIsAccepted());
    }

    //@Test(description = "Contrato Internaciona Regional SIPA")
    void contratoInternacionalSIPA(){
        boolean result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("40200573463","1111",false)
                .configuracion(true)
                .activarContratoInternacionalSIPA(true);

        //Assert.assertTrue(result);
        Assert.assertTrue(new MsContratos("40200573463").contractInternacionalRegionalSIPAIsAccepted());
    }

    //@Test(description = "No Aceptar Actualizar Contrato Autorizacion uso informacion")
    void noAceptarActializarContratoAutorizacionUsoInformacion(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111",false)
                .configuracion(true)
                .actualizarContratoUsoDeInformacionOpcionNoAceptar(true);
    }

    //@Test(description = "Aceptar Actualizar Contrato Autorizacion uso informacion")
    void aceptarActualizarContratoAutorizacionUsoInformacion(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111",false)
                .configuracion(true)
                .actualizarContratoUsoDeInformacionOpcionAceptar(true);
    }

    //@Test(description = "Modal pagar TC")
    void modalPagarTC(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111435384","1111",false)
                .modalPagarTC("01289910057","45170060****7142",true);
    }

    //@Test(description = "Modal pagar prestamo")
    void modalPagarPrestamo(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111435384","1111",false)
                .modalPagarPrestamo("01289910057","3038239",true);
    }

    //@Test(description = "Modal activar Tarjeta Credito")
    void modalActivarTarjetaCredito(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00100965995","1111",false)
                .modalActivarTarjetaCredito("46413303****9134",true);
    }

    //@Test(description = "Cnsultar Cuenta")
    void consultarCuenta(){

        MsDashboardCuentas cuentas = new MsDashboardCuentas().newQuery("00111671855").buscar("04571650016");
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111671855","1111",false)
                .consultarCuenta(cuentas,true);
    }

    //@Test(description = "Cnsultar Cuentas")
    void consultarCuentas(){
        MsDashboardCuentas cuentas = new MsDashboardCuentas().newQuery("00111671855");
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111671855","1111",false)
                .consultarCuentas(cuentas,true);
    }

    //@Test(description = "Cnsultar Detalle Cuenta")
    void consultarDetalleCuenta(){

        MsDashboardCuentas cuenta = new MsDashboardCuentas().newQuery("00111671855").buscar("04571650016");
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111671855","1111",false)
                .consultarDetalleCuenta( cuenta,true);
    }

    //@Test(description = "Transferencia entre cuentas Compra")
    void transferenciaEntreCuentasCompra(){

        //MsDashboardCuentas cuenta = new MsDashboardCuentas().newQuery("00111671855").buscar("04571650016");
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00101514420","1111",false)
                .irTransaccionesEntreMisProductos( true)
                .transferenciaEntreCuentas("00056210016","07741080022","100","DESCRIPCION",true,true);
    }

    //@Test(description = "Transferencia entre cuentas Venta")
    void transferenciaEntreCuentasVenta(){

        //MsDashboardCuentas cuenta = new MsDashboardCuentas().newQuery("00111671855").buscar("04571650016");
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00101514420","1111",false)
                .irTransaccionesEntreMisProductos( true)
                .transferenciaEntreCuentas("07741080022","00056210016","100","DESCRIPCION",true,true);
    }

    //@Test(description = "Prueba Login con validacion de acceso")
    void loginUsuarioValidacionAcceso(){
        // Reseteado         -00101674588- Reseteado con validacion de acceso
        // FirstLogin        -00101922854
        // DispositivoSeguro -00100964733
        // 40006420738- Reseteado con configuracion de preguntas seguridad.


//        LoginLogic.get().iniciarSesion("22301391524", "2222", true)
//                .validarAcceso(true);
        //POLoginPage.getInstance(getDriver())
          //      .iniciarSesion("22301391524","1111",true);

        POLoginPage.getInstance(getDriver())
                //.iniciarSesion("00101922854","1111",true)
                //.iniciarSesion("00111435384","1111",true)
                .iniciarSesion("00100965995","1111",true)// Dispositivo seguro-00100964733
                .validarAcceso(true);

        // Usuario Primer inicio-00101412476
        // Usuario    Recurrente-00101922854
    }

    //@Test(description = "Prueba Login usuario reseteado con preguntas de seguridad")
    void loginUsuarioReseteadoConPreguntasSeguridad(){
        // Reseteado         -00101674588- Reseteado con validacion de acceso
        // FirstLogin        -00101922854
        // DispositivoSeguro -00100964733
        // 40006420738- Reseteado con configuracion de preguntas seguridad.


//        LoginLogic.get().iniciarSesion("22301391524", "2222", true)
//                .validarAcceso(true);
        //POLoginPage.getInstance(getDriver())
        //      .iniciarSesion("22301391524","1111",true);

        POLoginPage.getInstance(getDriver())
                //.iniciarSesion("00101922854","1111",true)
                //.iniciarSesion("00111435384","1111",true)
                .iniciarSesion("40006420738","1111",true)// Dispositivo seguro-00100964733
                .validarAcceso(true);

        // Usuario Primer inicio-00101412476
        // Usuario    Recurrente-00101922854
    }


    //@Test(description = "Prueba Login usuario reseteado con preguntas de seguridad")
    void firstLogin(){
        // Reseteado         -00101674588- Reseteado con validacion de acceso
        // FirstLogin        -00101922854
        // DispositivoSeguro -00100964733
        // 40006420738- Reseteado con configuracion de preguntas seguridad.


//        LoginLogic.get().iniciarSesion("22301391524", "2222", true)
//                .validarAcceso(true);
        //POLoginPage.getInstance(getDriver())
        //      .iniciarSesion("22301391524","1111",true);

        POLoginPage.getInstance(getDriver())
                //.iniciarSesion("00101922854","1111",true)
                //.iniciarSesion("00111435384","1111",true)
                .iniciarSesion("03102999269","1111",true)// Dispositivo seguro-00100964733
                .validarAcceso(true);

        // Usuario Primer inicio-00101412476
        // Usuario    Recurrente-00101922854
    }

    //@Test(description = "Prueba Login usuario reseteado con preguntas de seguridad")
    void usuarioRecurrenteContratoAPPNoAceptado(){
        // Reseteado         -00101674588- Reseteado con validacion de acceso
        // FirstLogin        -00101922854
        // DispositivoSeguro -00100964733
        // 40006420738- Reseteado con configuracion de preguntas seguridad.


//        LoginLogic.get().iniciarSesion("22301391524", "2222", true)
//                .validarAcceso(true);
        //POLoginPage.getInstance(getDriver())
        //      .iniciarSesion("22301391524","1111",true);

        POLoginPage.getInstance(getDriver())
                //.iniciarSesion("00101922854","1111",true)
                //.iniciarSesion("00111435384","1111",true)
                .iniciarSesion("00114669054","1111",true)// Dispositivo seguro-00100964733
                .validarAcceso(true);

        // Usuario Primer inicio-00101412476
        // Usuario    Recurrente-00101922854
    }



    //@Test(description = "Desplegar Cuentas", priority = 1)
    void desplegarSesionCuentas(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false)
                .validarAcceso(false)
                .consultarCuentas(true);
    }

    //@Test(description = "Consultar Crediclick desembolsado", priority = 2)
    void consultarCrediclickDesembolsado(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301007070","1111",false)
                .validarAcceso(false)
                .consultarCrediclick(true);
    }

    //@Test(description = "Consultar Crediclick No desembolsado", priority = 2)
    void consultarCrediclickNoDesembolsado(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false)
                .validarAcceso(false)
                .consultarCrediclick(true);
    }

    //@Test(description = "Consultar Crediclick No desembolsado", priority = 2)
    void consultarDetalleCrediclick(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false)
                .validarAcceso(false)
                .consultarDestalleCrediclick(true);
    }

    //@Test(description = "Consultar nombre de cliente", priority = 2)
    void consultarNombreCliente(){
        String result =  POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false)
                .validarAcceso(false)
                .consultarNombreCliente(true);

        System.out.println("Nombre del cliente: " + result);
        Assert.assertEquals( result,"Bienvenido(a) " + Persistencia.getInstance().getClientInfo("22301391524").getNombreCompletoTitleCase());
    }

    //@Test(description = "Consultar nombre de cliente", priority = 2)
    void consultarCarruserDivisas(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false)
                .validarAcceso(false)
                .consultarCarouselDeDivisas(true);

        //System.out.println("Nombre del cliente: " + result);
        //Assert.assertEquals( result,"Bienvenido(a) " + Persistencia.getInstance().getClientInfo("22301391524").getNombreCompletoTitleCase());
    }

    //@Test(description = "Consultar nombre de cliente", priority = 2)
    void leerListaAccesoRapidos(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301007070","1111",false)
                .validarAcceso(false)
                .leerAccesosRapidos(true)
                .verificacion(true);

        //System.out.println("Nombre del cliente: " + result);
        //Assert.assertEquals( result,"Bienvenido(a) " + Persistencia.getInstance().getClientInfo("22301391524").getNombreCompletoTitleCase());
    }

    @Test(description = "Case de prueba 1")
    void testCase1(){
        System.out.println("Mensaje");
    }

    //@Test(description = "Accediendo a acceso rapido recarga billet")
    void accerderARecargaBiller(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301007070","1111",false)
                .validarAcceso(true)
                .accesoRapidoRecargaBillet(true);
    }

    @Test(description = "Configurando recarga billet en segundo acceso")
    void configurarRecargaBilletEnSegundoAccesoRapido(){
        PODashboardPage dashb = POLoginPage.getInstance( getDriver() )
                .iniciarSesion("00100965995","1111",false) // 22301007070
                .validarAcceso(false)
                .editarAccesosRapidos(
                        "Transferencias Regionales SIPA",
                        "Transacciones entre mis productos",
                        "Transacciones PIN Pesos",
                        "Transferencia internacional",
                        "Recarga Billet",
                        true);

        System.out.println( dashb.getNombreAccesoRapido(1) );
        Assert.assertEquals( dashb.getNombreAccesoRapido(1),"Transferencias Regionales SIPA");
    }

    @Test(description = "Desplegar perfil de usuario")
    void desplegarPerfilUsuario(){
       POLoginPage.getInstance( getDriver() )
                .iniciarSesion("00100965995","1111",false) // 22301007070
                .validarAcceso(false)
               .consultarCrediclick(true);
                //.desplegarPerfilDeUsuario(true);

    }









    //@Test(description = "Campo Confirmar contrasena requerido", priority = 4)
    void campoConfirmarContrasenaRequerido(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","1234","",true);

        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Campo requerido");
    }

    //@Test(description = "Campo Confirmar contrasena requerido", priority = 5)
    void camposRequeridosNuevaClaveConfirmarNuevaClave(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaConfiguracionDeAcceso("22301391524","","",true);

        Assert.assertEquals( page.getMsgValidarCampo("Nueva clave de acceso",false),"Campo requerido");
        Assert.assertEquals( page.getMsgValidarCampo("Confirmar nueva clave",true),"Campo requerido");
    }




    //@Test(description = "Vericar la pestaña - Validar OTP", priority = 6)
    void verificarPestanaValidarOTP(){
        POForgotPassword page = POLoginPage.getInstance(getDriver())
                .clickEnOlvidasteTuClaveDeAcceso(true)
                .verificarPestanaValidarOTP("22301391524","2222","2222","1111",true);
    }








}
