package test2024.pagetest;

import basetest.BaseTest;
import com.bhd.ibp.pages.pagostransferencias.POTransaccionesEntreProductosBHDOtrosBancosPage;
import com.bhd.ibp.pages.pagostransferencias.POTransferenciasPinPesoPage;
import com.bhd.ibp.pages.pagostransferencias.POVoucherPage;
import data.GenerarData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.POLoginPage;
import pages.pagostransferencias.POPagoServicioImpuestosPage;
import pages.pagostransferencias.accesos.beneficiarios.POBeneficiarioPinPesosPage;
import pages.pagostransferencias.accesos.beneficiarios.POBeneficiariosInscritosPage;
import pages.pagostransferencias.accesos.beneficiarios.POBeneficiariosNacionalesPage;

public class PruebasTest extends BaseTest {


    //@Test(description = "Prueba Formulario entre cuenta")
    public void pruebaFormularioTransferenciaentreCuenta(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .transferenciaentreCuentaHoy("00060330051",
                        "00060330069",//00060330069-Da mensaje expirado
                        "33",
                        GenerarData.createDescripcion(),
                        false,
                        true)
                .verificarTransferenciaEntreCuentas(true);
//                .seleccionarFechaHoy()
//                .continuar(true,true)
//                .modalConfirmacionEntreCuentas();
                //.realizarTransaccion(true,true);
    }

    //@Test(description = "Prueba SIPA")
    void pruebaSIPA(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransferenciasRegionalesSIPA(false)
                .realizarTransferencia(
                        "05605070011",
                        "Carlos Loyola",
                        "123456789",
                        "COSTA RICA",
                        "Mi calle",
                        "carlos_loyola@bhd.com.do",
                        "BANCO DE COSTA RICA",
                        "225588997777",
                        "7",
                        "Mi descripcion",
                        true)
                .imprimirTitulo(true);
    }

    //@Test(description = "Formulario Avance Efectivo TC - A Cuenta corriente")
    void formularioAvanceEfectivoTC_CuentaCorriente(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("40223165842","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .avanceEfectivoTarjetaCredito(
                        "4517001602323681",
                        "14165350036",
                        "5",
                        "Mi avance Efectivo TC",
                        true,
                        true);
                //.imprimirTitulo(true);
    }

    //@Test(description = "Formulario Avance Efectivo TC - A Cuenta ahorro")
    void formularioAvanceEfectivoTC2(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("40223165842","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .avanceEfectivoTarjetaCredito(
                        "4517001284953441",
                        "14165350028",
                        "5",
                        "Mi avance Efectivo TC",
                        true,
                        true);
        //.imprimirTitulo(true);
    } // 4641330016057323


    //@Test(description = "Formulario Difiere Tu corte")
    void formularioDifiereTuCorte(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("40223165842","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .setDifiereTuCorte(
                        "4641330016057323",
                        true);
    }

    //@Test(description = "Formulario Difiere Tu corte")
    void formularioAvanceEfectivoCuotasBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("40223165842","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .setAvanceEfectivoCuotasBHD(
                        "4641330357746500",
                        "00060330069",
                        new GenerarData().getNumRandomString(2),
                        "Cuotas BHD"+ GenerarData.createDescripcion(),
                        "21",
                        true);
    }

    //@Test(description = "Transferencia Pin pesos")
    void transferenciaPinPesos(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesPINPesos(true)
                .realizarTransferenciaBeneficiarioInscrito(
                        "00060330051",
                        "8091234567",
                        "100",
                        "Pin pesos" + GenerarData.createDescripcion(),
                        false,
                        true)
                .imprimirTitulo(true);
    }


    @Test(description = "Formulario Beneficiario Internacional", priority = 1)
    void formularioBeneficiarioInternacional(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

                new POBeneficiariosInscritosPage(getDriver())
                        .agregarBeneficiarioInternacional(
                                "Carlos Loyola 2",
                                "12345678",
                                "BAHREN",
                                "Mi calle",
                                "carlos_loyola@bhd.com.do",
                                "1234567899",
                                "Euros",
                                "SWIFT",
                                "AAALSARIXXX",
                                "PAGOS BIENES IMPORTACIONE",
                                "FIRMAS DE ABOGADOS",
                                "COLEGIOS",
                                true);

    }

    //@Test(description = "Formulario Beneficiario Internacional")
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

    @Test(description = "modal Eliminar Beneficiario Internacional", priority = 2)
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

    //@Test(description = "Prueba Formulario entre cuenta")
    void pruebaFormularioPrestamo(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301007070","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .setFormularioPagoPrestamoPropio(
                        "09769940011",
                        "4259077",
                        false,
                        false,
                        true,
                        "50",
                        "Automatizacion IBP",
                        true);
    }

    //@Test(description = "Pago Prestamo propio")
    void pagoPrestamoPropioDisminucion(){
        POVoucherPage page = POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301007070","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesEntreMisProductos(false)
                .pagoPrestamoPropio(
                        "09769940011",
                        "4259077",
                        true,
                        false,
                        "3",
                        "Automatizacion IBP",
                        true);

        page.getNumeroConfirmacion();
    }

    //@Test(description = "Transferencia pin pesos abeneficiario inscrito")
    void formularioTransferenciaPinPesosInscrito(){

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesPINPesos(false)
                .realizarTransferenciaBeneficiarioInscrito(
                        "00060330051",
                        "8092203030",
                        "100",
                        "Mi prueba formulario",
                        false,
                        true)
                .verificarTransaferenciaPinPesos(true);

//        POTransferenciasPinPesoPage pagePinPeso = new POTransferenciasPinPesoPage(getDriver());
//        pagePinPeso.probarFormularioBeneficiarioInscrito(
//                "34066020016",
//                "8097865456",
//                "200",
//                "Mi prueba formulario",
//                true,
//                true
//        );

    }



    ///@Test(description = "Formulario transferencia a nuevo beneficiario")
    void formularioTransferenciaPinPesosNuevoBeneficiario(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesPINPesos(false);

         POTransferenciasPinPesoPage pagePinPeso = new POTransferenciasPinPesoPage(getDriver());
        String result = pagePinPeso.probarFormularioBeneficiarioNuevo(
                "34066020016",
                "Juancito",
                new GenerarData().randomPhoneNumberRD(),
                "Cédula",
                "22301391524",
                "Carlos Alberto Loyola",
                "NA",
                "Masculino",
                "50",
                "Mi prueba formulario",
                true,
                true,
                true
        ).getMsgAlert("Monto", true);

        System.out.println("Resultado:" + result);

    }


    //________________________________________________



    //@Test(description = "Formulario transferencia a nuevo beneficiario")
    void pruebaFormularioTerceroBHDInscrito1(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false);

        POTransaccionesEntreProductosBHDOtrosBancosPage transferenciaBHD = new POTransaccionesEntreProductosBHDOtrosBancosPage(getDriver());

        transferenciaBHD
                .transferenciaATerceroEnBHDInscrito(
                "00060330051",
                "11267971",
                new GenerarData().getNumRandomString(2),
                "carlos_loyola@bhd.com.do",
                "Mi descripcion de prueba",
                true,
                true)
                .verificarTransferenciaTercero(true);

    }




    //@Test(description = "Transferencia CTA Ahoro Pesos Tercero BHD")
    void pruebaFormularioTerceroBHDInscrito(){


        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false)
                .transferenciaATerceroEnBHDInscrito(
                        "00060330051",
                        "15273480015",
                        new GenerarData().getNumRandomString(2),
                        "NA",
                        "Mi descripcion de prueba",
                        true,
                        true)
                .verificarTransferenciaTercero(true);

    }

    //@Test(description = "Formulario transferencia a nuevo beneficiario")
    void pruebaFormularioTerceroBHDInscrito2(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false);

        POTransaccionesEntreProductosBHDOtrosBancosPage transferenciaBHD = new POTransaccionesEntreProductosBHDOtrosBancosPage(getDriver());

        transferenciaBHD
                .transferenciaATerceroEnBHDInscrito(
                        "00060330051",
                        "11267971",
                        new GenerarData().getNumRandomString(3),
                        "carlos_loyola@bhd.com.do",
                        "Mi descripcion de prueba",
                        true,
                        true
                );

    }

    //@Test(description = "Formulario transferencia a nuevo beneficiario")
    void pruebaFormularioTerceroBHDNuevoBeneficiario(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .accesoRapidoTransaccionesProductosBHDyAOtrosBancos(false);

        POTransaccionesEntreProductosBHDOtrosBancosPage transferenciaBHD = new POTransaccionesEntreProductosBHDOtrosBancosPage(getDriver());

        transferenciaBHD
                .setFormularioTerceroBHDBeneficiarioNuevo(
                        "00060330051",
                        "BANCO MULTIPLE BHD, S.A.",
                        "11267971",
                        "Mi alias",
                        "carlos_loyola@bhd.com.do",
                        new GenerarData().getNumRandomString(3),

                        "Mi descripcion de prueba",
                        true,
                        true,
                        true
                );

    }


    //________________________________________________








    //@Test(description = "Consultar Crediclick No desembolsado", priority = 6)
    void consultarPrestamo(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("05400855671","1111",false )
                .validarAcceso(false)
                .consultarPrestamo("3887081",true);
    }

    //@Test(description = "Agregar Servicio nuevo", priority = 1)
    void formularioPagoServicios(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false)
                .accesoRapidoServiciosInscritos(true)
                .agregarServicio(
                        "CLARO",
                        "Compra de Recargas",
                        "8298164488",
                        "Pruebas automatizadas",
                        true);
    }


    //@Test(description = "Formulario pago de servicios", priority = 6)
    void verifacarFormularioEditarServicio(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false)
                .accesoRapidoServiciosInscritos(true)
                .editarServicio(
                        "Pruebas automatizadas",
                        "CLARO",
                        "Compra de Recargas",
                        "8298164488",
                        "Pruebas automatizadas",
                        true);
    }

    //@Test(description = "Eliminar Servicio inscrito", priority = 2)
    void eliminarServicioInscrito(){
        String result = POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false)
                .accesoRapidoServiciosInscritos(true)
                .eliminarServicio(
                        "Pruebas automatizadas",
                        true);

        Assert.assertEquals( result,"No se encontraron registros para mostrar");
    }

    //@Test(description = "Formulario paga servicio inscrito")
    void consultarServicioInscrito(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false).accesoRapidoPagoServiciosImpuestos(true);
        POPagoServicioImpuestosPage pago = new POPagoServicioImpuestosPage(getDriver());
        pago.formularioPagoServicioInscrito(
                "Pruebas automatizadas",
                "34066020016",
                "NA",
                "NA",
                "NA",
                true,
                true);
    }

    //@Test(description = "Formulario paga servicio Nuevo")
    void consultarFormularioServicioNuevo(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false).accesoRapidoPagoServiciosImpuestos(true);
        POPagoServicioImpuestosPage pago = new POPagoServicioImpuestosPage(getDriver());
        pago.setFormularioServicioNuevo(
                "CLARO",
                "Compra de Recargas",
                "34066020016",
                "8298164488",
                "300",
                "carlos_loyola@bhd.com.do",
                "Mi descripcion",
                true,
                true);
    }

    //@Test(description = "Probar Consulta pago impuesto")
    void consultaPagoImpuesto(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false).accesoRapidoPagoServiciosImpuestos(true);
        POPagoServicioImpuestosPage pago = new POPagoServicioImpuestosPage(getDriver());
        pago.probarConsultaImpuestoNuevo(
                "DGII",
                "15454215465",
                true,
                true);
    }


    // BENEFICIARIOS 👇

    //@Test(description = "Consultar Formulario Nuevo Beneficiario Pin Pesos") // Fallo no estar cargando los beneficiarios
    void consultarFormularioNuevoBeneficiarioPinPesos(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiarioPinPesosPage(getDriver())
                .probarFormularioEditarBeneficiario("","",true);
//                .probarFormularioNuevoBeneficiario(
//                        "Carlitos",
//                        "8091234567",
//                        "Cédula",
//                        "22301391524",
//                        "Carlos Loyola",
//                        "BERMU",
//                        "Masculino",
//                        true);


    }

    //@Test(description = "Consultar Formulario Nuevo Beneficiario Pin Pesos") // Fallo no estar cargando los beneficiarios
    void modalEliminarBeneficiarioPinPesos(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiarioPinPesosPage(getDriver())
                .probarModalEliminarBeneficiario("8298164488",true);
//                .probarFormularioNuevoBeneficiario(
//                        "Carlitos",
//                        "8091234567",
//                        "Cédula",
//                        "22301391524",
//                        "Carlos Loyola",
//                        "BERMU",
//                        "Masculino",
//                        true);
    }

   //@Test(description = "Consultar Formulario Nuevo Beneficiario Nacional")
    void consultarFormularioNuevoBeneficiarioNacional(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiarioPinPesosPage(getDriver())
                .setFormulario(
                        "",
                        "",
                        "Cédula",
                        "22301391524",
                        "Carlos Loyola",
                        "NA",
                        "",
                        true);
    }


    //@Test(description = "Consultar Formulario Nuevo Beneficiario Nacional")
    void consultaNuevoBeneificairoNaciona(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiariosNacionalesPage(getDriver())
                .probarFormularioNuevoBeneficiario(
                        "BANCO MULTIPLE BHD, S.A.",
                        "00060330051",
                        "Mi alias",
                        "carlos_loyola@bhd.com.do",
                        true);
    }

    //@Test(description = "Consultar Formulario Nuevo Beneficiario Nacional")
    void consultaNuevoBeneificairoNacionalOtrosBancos(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiariosNacionalesPage(getDriver())
                .probarForFormularioNuevoBeneficiarioOtrosBancos(
                        "ASOCIACION BONAO DE AHORROS Y PRESTAMOS",
                        "Cuentas de Ahorro",
                        "Cédula",
                        "00060330051",
                        "00111435384",
                        "Carlos Alberto Loyola",
                        "Mi alias",
                        "NA",
                        "Masculino",
                        "carlos_loyola@bhd.com.do",
                        true);
    }

    //@Test(description = "Consultar Formulario Nuevo Beneficiario Nacional")
    void editarBeneficiarioBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiariosNacionalesPage(getDriver())
                .probarFormularioEditarBeneficiario(
                        "01851580057",
                        "Mi alias",
                        "carlos_loyola@bhd.com.do",
                        true);
    }

    //@Test(description = "Prueba formulario editar Beneficiario de otro banco")
    void porobarformularioEditarbBeneficiarioDeOtroBanco(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",true )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(true);

        new POBeneficiariosNacionalesPage(getDriver())
                .probarFormularioEditarBeneficiarioOtrosBancos(
                        "123456789",
                        "Alias de prueba",
                        "BURUNDI",
                        "Femenino",
                        "carlos_loyola2@bhd.com.do",
                        true
                        );
    }

    //@Test(description = "Agregar beneficiario BHD prestamo T24")
    void agregarNuevoBeneficiarioBHD(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .accesoRapidoBeneficiariosInscritos(false);

        new POBeneficiariosNacionalesPage(getDriver())
                .probarFormularioNuevoBeneficiario(
                        "BANCO MULTIPLE BHD, S.A.",
                        "11272576",
                        "Mi alias",
                        "carlos_loyola@bhd.com.do",
                        true)
                .probarGuardarDesplegarModalTDC("1111", true)
                .modarGuardadoCorrectamente(true);
                //.buscarMarcarProducto("11272576",true);
                //.buscarBeneficiario("11272576", true);

        // Pamela Reservas 9601092858 - 40209547567
    }







}
