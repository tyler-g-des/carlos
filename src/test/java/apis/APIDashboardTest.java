package apis;

import apis.ibp.APIDashboard;
import apis.ibp.APISLogin;
import basetest.BaseTest;
import microservicios.MSClientInfoV2;
import microservicios.dashboard.MsCuentas;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static java.lang.String.format;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 27/05/2024 8:40 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APIDashboardTest extends BaseTest {

    private APISLogin loginMayo;
    private APIDashboard dashboardMayo;

    private APISLogin loginNormaAlmonteExterna;
    private APIDashboard dashboardNormaAlmonteExterna;

    @Test(description = "Inicio de sesion", priority = 1)
    void iniciarSesionMayo(){
        loginMayo = new APISLogin();
        loginMayo.loginSecureDevice("00111435384","1111", "Inicio de sesion");

        createStep( loginMayo.getMessageReport(),loginMayo.getStatusCode() == 200,false);
        Assert.assertEquals(loginMayo.getStatusCode(),200);
        dashboardMayo = new APIDashboard(loginMayo);
    }

    @Test(description = "Inicio de sesion Norma Almorte Externa -> Expected: 200", priority = 2)
    void iniciarSesionNormaAlmonteExterna(){
        //loginNormaAlmonteExterna = new APISLogin();
        loginMayo.loginSecureDevice("00100965995","1111", "Inicio de sesion");
        //loginNormaAlmonteExterna.loginSecureDevice("00100965995","1111", "Inicio de sesion");

        createStep( loginMayo.getMessageReport(),loginMayo.getStatusCode() == 200,false);
        Assert.assertEquals(loginMayo.getStatusCode(),200);
        dashboardNormaAlmonteExterna = new APIDashboard(loginMayo);
    }


    @Test(description = "Editar alia Cuenta corriente -> Expected: 200", dependsOnMethods = "iniciarSesionMayo")
    void marketingDecision(){
        APIDashboard dashboard = new APIDashboard(loginMayo);

        dashboard.marketingDecision("05447780030","Consultar Marqueting Decision");
        //String mensaje = "<br><br> Alias actual: %s <br> alias esperada: %s";

        createStep( dashboard.getMessageReport(),dashboard.getStatusCode() == 200,false);
        Assert.assertEquals(dashboard.getStatusCode(),200);
    }


    // EDICION ALIAS DE CUENTAS
    @Test(description = "Editar alia Cuenta corriente -> Expected: 200", dependsOnMethods = "iniciarSesionMayo")
    void editarAliasCuentaCorriente(){
        APIDashboard dashboard = new APIDashboard(loginMayo);

        dashboard.editarAliasCuenta("05447780030","Editar alia Cuenta corriente 1","Editar alias Cuenta corriente");
        String mensaje = "<br><br> Alias actual: %s <br> alias esperada: %s";

        createStep( dashboard.getMessageReport() + format(mensaje, new MsCuentas("00111435384").buscar("05447780030").getAlias(), "Editar alia Cuenta corriente 1"),dashboard.getStatusCode() == 200,false);
        Assert.assertEquals(dashboard.getStatusCode(),200);
        MsCuentas cuentas = new MsCuentas("00111435384").buscar("05447780030");
        System.out.println("Alias final: " + cuentas.getAlias() );
    }

    @Test(description = "Editar alias Cuenta Ahorros pesos -> Expected: 200", dependsOnMethods = "iniciarSesionMayo")
    void editarAliasCuentaAhorroPesos(){
        APIDashboard dashboard = new APIDashboard(loginMayo);
        String productNumber = "29310770016";
        String nuevaAlias = "cuenta de ahorros pesos " + new Random().nextInt(50);

        dashboard.editarAliasCuenta( productNumber,nuevaAlias,"Editar alias Cuenta Ahorros pesos");
        String mensaje = "Resultado:<br> Alias actual: %s <br> alias esperada: %s";

        createStep( dashboard.getMessageReport(),dashboard.getStatusCode() == 200,false);
        Assert.assertEquals(dashboard.getStatusCode(),200);

        String aliasActual = new MsCuentas(loginMayo.getDocumentNumber()).buscar( productNumber ).getAlias();

        createStep(format(mensaje, aliasActual, nuevaAlias) ,nuevaAlias.equals(aliasActual),false);
        Assert.assertEquals( aliasActual, nuevaAlias );
    }

    //@Test(description = "Editar alias Cuenta Ahorros Dolar -> Expected: 200", dependsOnMethods = "iniciarSesionMayo")
    void editarAliasCuentaAhorroDolar(){
        APIDashboard dashboard = new APIDashboard(loginMayo);
        String productNumber = "01289910031";
        String nuevaAlias = "cuenta de ahorros Dolar " + new Random().nextInt(50);

        dashboard.editarAliasCuenta( productNumber,nuevaAlias,"Editar alias Cuenta Ahorros Dolar");
        String mensaje = "Resultado:<br> Alias actual: %s <br> alias esperada: %s";

        createStep( dashboard.getMessageReport(),dashboard.getStatusCode() == 200,false);
        Assert.assertEquals(dashboard.getStatusCode(),200);

        String aliasActual = new MsCuentas(loginMayo.getDocumentNumber()).buscar( productNumber ).getAlias();

        createStep(format(mensaje, aliasActual, nuevaAlias) ,nuevaAlias.equals(aliasActual),false);
        Assert.assertEquals( aliasActual, nuevaAlias );
    }

    //@Test(description = "Editar alias Cuenta Ahorros Euro -> Expected: 200", dependsOnMethods = "iniciarSesionMayo")
    void editarAliasCuentaAhorroEuro(){
        APIDashboard dashboard = new APIDashboard(loginMayo);
        String productNumber = "01289910057";
        String nuevaAlias = "cuenta de ahorros Euro " + new Random().nextInt(50);

        dashboard.editarAliasCuenta( productNumber,nuevaAlias,"Editar alias Cuenta Ahorros Euro");
        String mensaje = "Resultado:<br> Alias actual: %s <br> alias esperada: %s";

        createStep( dashboard.getMessageReport(),dashboard.getStatusCode() == 200,false);
        Assert.assertEquals(dashboard.getStatusCode(),200);

        String aliasActual = new MsCuentas(loginMayo.getDocumentNumber()).buscar( productNumber ).getAlias();

        createStep(format(mensaje, aliasActual, nuevaAlias) ,nuevaAlias.equals(aliasActual),false);
        Assert.assertEquals( aliasActual, nuevaAlias );
    }

    //@Test(description = "Editar alia Super Cuenta inversion Ahorros pesos -> Expected: 200", dependsOnMethods = "iniciarSesionMayo")
    void editarAliasSuperCuentaInversionAhorroPesos(){
        APIDashboard dashboard = new APIDashboard(loginMayo);
        String productNumber = "01289910022";
        String nuevaAlias = "super cuenta inversion ahorro en pesos " + new Random().nextInt(50);

        dashboard.editarAliasCuenta( productNumber,nuevaAlias,"Editar alia Super Cuenta Ahorros inversion pesos");
        String mensaje = "Resultado:<br> Alias actual: %s <br> alias esperada: %s";

        createStep( dashboard.getMessageReport(),dashboard.getStatusCode() == 200,false);
        Assert.assertEquals(dashboard.getStatusCode(),200);

        String aliasActual = new MsCuentas(loginMayo.getDocumentNumber()).buscar( productNumber ).getAlias();

        createStep(format(mensaje, aliasActual, nuevaAlias) ,nuevaAlias.equals(aliasActual),false);
        Assert.assertEquals(aliasActual,nuevaAlias);
    }

    //@Test(description = "Editar alia Super Cuenta inversion Ahorros Dolar -> Expected: 200", dependsOnMethods = "iniciarSesionMayo")
    void editarAliasSuperCuentaInversionAhorroDolar(){
        APIDashboard dashboard = new APIDashboard(loginMayo);
        String productNumber = "01289910049";
        String nuevaAlias = "super cuenta inversion ahorro en Dolar " + new Random().nextInt(50);

        dashboard.editarAliasCuenta( productNumber,nuevaAlias,"Editar alia Super Cuenta Ahorros inversion Dolar");
        String mensaje = "Resultado:<br> Alias actual: %s <br> alias esperada: %s";

        createStep( dashboard.getMessageReport(),dashboard.getStatusCode() == 200,false);
        Assert.assertEquals(dashboard.getStatusCode(),200);

        String aliasActual = new MsCuentas(loginMayo.getDocumentNumber()).buscar( productNumber ).getAlias();

        createStep(format(mensaje, aliasActual, nuevaAlias) ,nuevaAlias.equals(aliasActual),false);
        Assert.assertEquals(aliasActual,nuevaAlias);
    }





    // CONSULTA DE PRODUCTOS -> USUARIOS CON PRODUCTOS

    @Test(description = "Consultar cuentas de usuario interno -> Expected: 200", dependsOnMethods = "iniciarSesionMayo")
    void consultarCuentasDeUsuarioInterno(){
        dashboardMayo.getPassiveAccounts("Consultar Cuentas de usuario interno");


        createStep( dashboardMayo.getMessageReport(),dashboardMayo.getStatusCode() == 200,false);
        Assert.assertEquals(dashboardMayo.getStatusCode(),200);
    }

    @Test(description = "Consultar Tarjetas credito de usuario interno -> Expected: 200,206", dependsOnMethods = "iniciarSesionMayo")
    void consultarTarjetasCreditoDeUsuarioInterno(){
        dashboardMayo.getAssetCreditCards("Consultar Tarjetas credito de usuario interno");


        createStep( dashboardMayo.getMessageReport(),dashboardMayo.getStatusCode() == 200 || dashboardMayo.getStatusCode() == 206,false);
        //Assert.assertEquals(dashboardMayo.getStatusCode(),200,206);
        Assert.assertTrue(dashboardMayo.getStatusCode()== 200 || dashboardMayo.getStatusCode() == 206);
    }

    @Test(description = "Consultar Certificados de usuario interno -> Expected: 200,206", dependsOnMethods = "iniciarSesionMayo")
    void consultarCertificadosDeUsuarioInterno(){
        dashboardMayo.getPassiveCertificates(true,"Consultar Certificados de usuario interno");


        createStep( dashboardMayo.getMessageReport(),dashboardMayo.getStatusCode() == 200 || dashboardMayo.getStatusCode() == 206,false);
        //Assert.assertEquals(dashboardMayo.getStatusCode(),200,206);
        Assert.assertTrue(dashboardMayo.getStatusCode()== 200 || dashboardMayo.getStatusCode() == 206);
    }

    @Test(description = "Consultar Cuenta Pension de usuario interno -> Expected: 200,206", dependsOnMethods = "iniciarSesionMayo")
    void consultarCuentaPensionUsuarioInterno(){
        dashboardMayo.getAFPRetirementPay("Consultar Cuenta Pension de usuario interno");

        createStep( dashboardMayo.getMessageReport(),dashboardMayo.getStatusCode() == 200 || dashboardMayo.getStatusCode() == 206,false);
        Assert.assertEquals(dashboardMayo.getStatusCode(),200);
        //Assert.assertTrue(dashboardMayo.getStatusCode()== 200 || dashboardMayo.getStatusCode() == 206);
    }

    @Test(description = "Consultar Prestamos de usuario interno -> Expected: 200,206", dependsOnMethods = "iniciarSesionMayo")
    void consultarPrestamosUsuarioInterno(){
        MSClientInfoV2 clientInfo = new MSClientInfoV2(loginMayo.getDocumentNumber()).newQuery();
        dashboardMayo.getAssetLoans(true, clientInfo.getCustomerCodeT24(),"Consultar Prestamos de usuario interno");

        createStep( dashboardMayo.getMessageReport(),dashboardMayo.getStatusCode() == 200 || dashboardMayo.getStatusCode() == 206,false);
        //Assert.assertEquals(dashboardMayo.getStatusCode(),200,206);
        Assert.assertTrue(dashboardMayo.getStatusCode()== 200 || dashboardMayo.getStatusCode() == 206);
    }

    @Test(description = "Consultar Crediclick de usuario Externo -> Expected: 200,206", dependsOnMethods = "iniciarSesionNormaAlmonteExterna")
    void consultarCrediclickUsuarioExterno(){
        dashboardNormaAlmonteExterna.getPassiveFinancing("Consultar Crediclick de usuario Externo");

        createStep( dashboardNormaAlmonteExterna.getMessageReport(),dashboardNormaAlmonteExterna.getStatusCode() == 206,false);
        //Assert.assertEquals(dashboardMayo.getStatusCode(),200,206);
        Assert.assertEquals(dashboardNormaAlmonteExterna.getStatusCode() , 200);
    }

    @Test(description = "Consultar Inversion Puesto Bolsa de usuario Externo -> Expected: 200,206", dependsOnMethods = "iniciarSesionNormaAlmonteExterna")
    void consultarInversionPuestoBolsaUsuarioExterno(){
        dashboardNormaAlmonteExterna.getPassiveStockExchangeList(false,false,"Consultar Inversion Puesto Bolsa de usuario Externo");

        createStep( dashboardNormaAlmonteExterna.getMessageReport(),dashboardNormaAlmonteExterna.getStatusCode() == 200,false);
        //Assert.assertEquals(dashboardMayo.getStatusCode(),200,206);
        Assert.assertEquals(dashboardNormaAlmonteExterna.getStatusCode() , 200);
    }

    @Test(description = "Consultar accesos rapidos -> Expected: 200", dependsOnMethods = "iniciarSesionMayo")
    void consultarAccesoRapido(){
        dashboardMayo.getDashboardShortcuts("Consultar accesos rapidos");
        createStep( dashboardMayo.getMessageReport(),dashboardMayo.getStatusCode() == 200,false);
        Assert.assertEquals(dashboardMayo.getStatusCode() , 200);
    }


//    @Test(description = "Consultar accesos rapidos -> Expected: 200", dependsOnMethods = "iniciarSesionMayo")
//    void editarAliasCuentaCorriente(){
//        dashboardMayo.editarAliasCuenta("","","Consultar accesos rapidos");
//        createStep( dashboardMayo.getMessageReport(),dashboardMayo.getStatusCode() == 200,false);
//        Assert.assertEquals(dashboardMayo.getStatusCode() , 200);
//    }





//    private APISLogin loginUsuarioSinProductos;
//    private APIDashboard dashboardSinProductos;
//
//    private APISLogin usuarioConCuentaTcCertificadoPrestamo;
//    private APIDashboard dashboardCuentaTcCertificadoPrestamo;
//    private MSClientInfoV2 clientInfo;
//
//    //@Test(description = "inicio sesion usuario con: <br> Cuenta, <br>Tarjeta Credito, <br>Certificado y prestamo", priority = 1)
//    void loginUsuarioConCuentaTcCertificadoPrestamo(){
//        usuarioConCuentaTcCertificadoPrestamo = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        dashboardCuentaTcCertificadoPrestamo = new APIDashboard(usuarioConCuentaTcCertificadoPrestamo);
//
//        createStep("Inicio de sesion status: " + usuarioConCuentaTcCertificadoPrestamo.getStatusCode());
//        Assert.assertEquals(usuarioConCuentaTcCertificadoPrestamo.getStatusCode(), 200);
//    }
//
//    //@Test(description = "Consultar usuario con Cuentas", dependsOnMethods = "loginUsuarioConCuentaTcCertificadoPrestamo")
//    void consultarUsuarioConCuentas(){
//        dashboardCuentaTcCertificadoPrestamo.apiDashboardAccounts();
//
//        createStep( dashboardCuentaTcCertificadoPrestamo.getReporteAPI(), ( dashboardCuentaTcCertificadoPrestamo.getStatusCode() == 200),false);
//        Assert.assertEquals( dashboardCuentaTcCertificadoPrestamo.getStatusCode(),200);
//    }
//
//    //@Test(description = "Consultar usuario Con tarjetas de credito",dependsOnMethods = "loginUsuarioConCuentaTcCertificadoPrestamo")
//    void consultarUsuarioConTarjetaCredito(){
//        dashboardCuentaTcCertificadoPrestamo.apiDashboardAssetCreditCard();
//
//        createStep( dashboardCuentaTcCertificadoPrestamo.getReporteAPI(), ( dashboardCuentaTcCertificadoPrestamo.getStatusCode() == 200 || dashboardCuentaTcCertificadoPrestamo.getStatusCode()==206),false);
//        Assert.assertTrue( dashboardCuentaTcCertificadoPrestamo.getStatusCode() == 200 || dashboardCuentaTcCertificadoPrestamo.getStatusCode() ==206);
//    }
//
//    //@Test(description = "Consultar usuario Con prestamos", dependsOnMethods = "loginUsuarioConCuentaTcCertificadoPrestamo")
//    void consultarUsuarioConPrestamos(){
//
//        clientInfo = new MSClientInfoV2("00111435384").newQuery(); // defecto al pasar el codigo de T24 de otro usuario
//        dashboardCuentaTcCertificadoPrestamo.apiDashboardLoans(clientInfo.getCustomerCodeT24());
//
//        createStep( dashboardCuentaTcCertificadoPrestamo.getReporteAPI(), ( dashboardCuentaTcCertificadoPrestamo.getStatusCode() == 206),false);
//        Assert.assertEquals( dashboardCuentaTcCertificadoPrestamo.getStatusCode(),206);
//    }
//
//    //@Test(description = "Consultar usuario con certificados",dependsOnMethods = "loginUsuarioConCuentaTcCertificadoPrestamo")
//    void consultarUsuarioConCertificado(){
//        dashboardCuentaTcCertificadoPrestamo.apiDashboardPassiveCertificates();
//
//        createStep( dashboardCuentaTcCertificadoPrestamo.getReporteAPI(), ( dashboardCuentaTcCertificadoPrestamo.getStatusCode() == 200 || dashboardCuentaTcCertificadoPrestamo.getStatusCode()==206),false);
//        Assert.assertTrue( dashboardCuentaTcCertificadoPrestamo.getStatusCode() == 200 || dashboardCuentaTcCertificadoPrestamo.getStatusCode() ==206);
//    }
//
//
//    /**
//     * <h1>Inicio sesion usuario sin productos</h1>
//     */
//    @Test(description = "inicio sesion usuario sin productos", priority = 0)
//    void inicioSesionUsuarioSinProductos(){
//        loginUsuarioSinProductos = APISLogin.getInstance().iniciarSesionDispositivoSeguro("22301391524","1111");
//        dashboardSinProductos = new APIDashboard(loginUsuarioSinProductos);
//
//        createStep("Inicio de sesion status: " + loginUsuarioSinProductos.getStatusCode());
//        Assert.assertEquals(loginUsuarioSinProductos.getStatusCode(), 200);
//    }
//
//
//
//    @Test(description = "Consultar API Imagen", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarAPIImagen(){
//        dashboardSinProductos.apiImage();
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 200),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),200);
//    }
//
//    //@Test(description = "Consultar usuario sin Cuentas", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarUsuarioSinCuentas(){
//        dashboardSinProductos.apiDashboardAccounts();
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 204),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),204);
//    }
//
//    //@Test(description = "Consultar usuario sin tarjetas de credito",dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarUsuarioSinTarjetaCredito(){
//        dashboardSinProductos.apiDashboardAssetCreditCard();
//
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 204),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),204);
//    }
//
//    //@Test(description = "Consultar usuario sin tarjetas prepago",dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarUsuarioSinTarjetaPrepago(){
//        dashboardSinProductos.apiDashboardTarjetasPrepago();
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 204),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),204);
//    }
//
//    //@Test(description = "Consultar usuario sin certificados",dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarUsuarioSinCertificados(){
//        dashboardSinProductos.apiDashboardPassiveCertificates();
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 204),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),204);
//    }
//
//    //@Test(description = "Consultar usuario sin Financiamiento Pasivo",dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consularUsuarioSinFinanciamientoPasivo(){
//        dashboardSinProductos.apiDashboardPassiveFinancing();
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 204),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),204);
//    }
//
//    //@Test(description = "Consultar usuario sin prestamos", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarUsuarioSinPrestamos(){
//
//        clientInfo = new MSClientInfoV2("22301391524").newQuery();
//        dashboardSinProductos.apiDashboardLoans(clientInfo.getCustomerCodeT24());
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 206),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),206);
//    }
//
//    @Test(description = "Consultar usuario sin Inversiones pasivas", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarUsuarioSinInversionesPasivas(){
//        dashboardSinProductos.apiDashboardPassiveInvestments();
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 200),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),200);
//    }
//
//
//
//    @Test(description = "Consultar usuario sin Crediclick", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarUsuarioSinCrediclick(){
//        dashboardSinProductos.apiDashboardPassiveFinancing();
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 204),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),204);
//    }
//
//    @Test(description = "Consultar Divisas usuario sin productos", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarDivisasUsuarioSinProductos(){
//        dashboardSinProductos.apiDashboardServicesRates();
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 200),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),200);
//    }
//
//    @Test(description = "Consultar AFP usuario externo", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarAFPUsuarioExterno(){
//        dashboardSinProductos.apiDashboardAfpRetiremenPay();
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 204),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),204);
//    }
//
//    @Test(description = "Consultar Acceso Rapidos usuario cedula", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarAccesosRapidosUsuarioSinProductos(){
//        dashboardSinProductos.apiDashboardShortCuts();
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 200),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),200);
//    }
//
//    @Test(description = "Consultar Usuario sin Inversiones puesto de Bolsa", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarUsuarioSinInversionesPuestoBolsa(){
//        dashboardSinProductos.inversionesPuestoBolsa(false,false);
//
//        createStep( dashboardSinProductos.getReporteAPI(), ( dashboardSinProductos.getStatusCode() == 204),false);
//        Assert.assertEquals( dashboardSinProductos.getStatusCode(),204);
//    }
//
//    private APIDashboard dashboardCrediclickInversionPuestoBolsa;
//
//    @Test(description = "Inicio de sesion usuario con Crediclick, Inversion Puesto Bolsa")
//    void iniciarSesionUsuarioConCrediclickInversionPuestoBolsa(){
//        APISLogin loginUsuarioCrediclickInversionPustoBolsa = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00100965995","1111");
//        dashboardCrediclickInversionPuestoBolsa = new APIDashboard(loginUsuarioCrediclickInversionPustoBolsa);
//
//        createStep("Inicio de sesion status: " + loginUsuarioCrediclickInversionPustoBolsa.getStatusCode());
//        Assert.assertEquals(loginUsuarioCrediclickInversionPustoBolsa.getStatusCode(), 200);
//    }
//
//    @Test(description = "Consultar Usuario Con Inversiones puesto de Bolsa", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarUsuarioConCrediclick(){
//        dashboardCrediclickInversionPuestoBolsa.apiDashboardPassiveFinancing();
//
//        createStep( dashboardCrediclickInversionPuestoBolsa.getReporteAPI(), ( dashboardCrediclickInversionPuestoBolsa.getStatusCode() == 206),false);
//        Assert.assertEquals( dashboardCrediclickInversionPuestoBolsa.getStatusCode(),206);
//    }
//
//    @Test(description = "Consultar Usuario Con Inversiones puesto de Bolsa", dependsOnMethods = "inicioSesionUsuarioSinProductos")
//    void consultarUsuarioConInversionesPuestoBolsa(){
//        dashboardCrediclickInversionPuestoBolsa.inversionesPuestoBolsa(false,false);
//
//        createStep( dashboardCrediclickInversionPuestoBolsa.getReporteAPI(), ( dashboardCrediclickInversionPuestoBolsa.getStatusCode() == 200),false);
//        Assert.assertEquals( dashboardCrediclickInversionPuestoBolsa.getStatusCode(),200);
//    }




}
