package test2024.pagetest;

import basetest.BaseTest;
import data.GenerarData;
import data.Persistencia;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PODashboardPage;
import pages.login.POLoginPage;
import servicios.micros.MicroClientInfo;

public class TestDashborad extends BaseTest {


    @Test(description = "Consultar nombre de cliente", priority = 1)
    void consultarNombreCliente(){
        String result =  POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false)
                .validarAcceso(false)
                .consultarNombreCliente(true);

        System.out.println("Nombre del cliente: " + result);
        Assert.assertEquals( result,"Bienvenido(a) " + Persistencia.getInstance().getClientInfo("22301391524").getNombreCompletoTitleCase());
    }

    @Test(description = "Consultar Nombre y correo del usuario en el icono de perfil de usuario", priority = 2)
    void consultarNombreCorreoEnIconoPerfilDeusuario(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false)
                .validarAcceso(false)
                .consultarNombreCorreoEnPerfilDeUsuario(true);
    }


    @Test(description = "Consultar CarruserDivisas", priority = 3)
    void consultarCarruserDivisas(){
        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false)
                .validarAcceso(false)
                .consultarCarouselDeDivisas(true);
    }

    @Test(description = "Configurando recarga billet en segundo acceso", priority = 4)
    void editarCategoriaAccesosRapidos(){
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


    // RESUMEN DE PRODUCTOS 👇

    // CUENTAS -> EDITAR ALIAS 👇
    String digitos = new GenerarData().getNumRandomString(6);

    @Test(description = "Editar Alias -> Cuenta corriente")
    void editarAliasCuentaCorriente(){
        String nuevaAlias = "CTA.Corriente automatizacion " + digitos;

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00103705216","1111",false )
                .validarAcceso(false)
                .editarAliasCuenta("02236850016", nuevaAlias,true);
    }

    @Test(description = "Editar Alias -> Cuenta ahorro pesos")
    void editarAliasCuentaAhorroPesos(){
        String nuevaAlias = "CTA.A Pesos automatizacion " + digitos;

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false )
                .validarAcceso(false)
                .editarAliasCuenta("34066020016", nuevaAlias,true);
    }

    @Test(description = "Editar Alias -> Super Cuenta ahorro inversion Pesos")
    void editarAliasSuperCuentaAhorroInversionPesos(){
        String nuevaAlias = "Super CA inversion Pesos automatizacion " + digitos;

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100838234","1111",false )
                .validarAcceso(false)
                .editarAliasCuenta("01891170030", nuevaAlias,true);
    }

    @Test(description = "Editar Alias -> Cuenta ahorro Dolar")
    void editarAliasCuentaAhorroDolar(){
        String nuevaAlias = "CTA.A Dolar automatizacion " + digitos;

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100965995","1111",false )
                .validarAcceso(false)
                .editarAliasCuenta("05605070011", nuevaAlias,true);
    }

    @Test(description = "Editar Alias -> Super Cuenta Inversion Dolar")
    void editarAliasSuperCuentaAhorroDolar(){
        String nuevaAlias = "SUPER CA INVERSION Dolar automatizacion " + digitos;

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00118090919","2222",false )
                .validarAcceso(false)
                .editarAliasCuenta("09159390052", nuevaAlias,true);
    }

    @Test(description = "Editar Alias -> Cuenta ahorro Euro")
    void editarAliasCuentaAhorroEuro(){
        String nuevaAlias = "CTA.A Euro automatizacion " + digitos;

        POLoginPage.getInstance(getDriver())
                .iniciarSesion("00100838234","1111",false )
                .validarAcceso(false)
                .editarAliasCuenta("01891170064", nuevaAlias,true);
    }



    // CUENTAS INACTIVAS 👇

    @Test(description = "Consultar modal -> Tienes productos inactivos")
    void consultarModalTienesProductosInactivos(){
        String numDocumento = POLoginPage.getInstance(getDriver())
                .iniciarSesion("00101622678","3333",false )
                .validarAcceso(false)
                .verificarModalTienesProductosInactivos(true );

        Assert.assertTrue( MicroClientInfo.getInstance().consultar(numDocumento).isMuestraCuentasInactivas() );
    }

    @Test(description = "Consultar cuentas inactivos en peso y dolar modal")
    void consultarCuentasInactivasPesosDolar(){
        String numDocumento = POLoginPage.getInstance(getDriver())
                .iniciarSesion("00101622678","3333",false )
                .validarAcceso(false)
                .verificarModalProductosInactivos(true );

        Assert.assertTrue( MicroClientInfo.getInstance().consultar(numDocumento).isMuestraCuentasInactivas() );
    }



    // CREDICLICK 👈
    @Test(description = "Consultar Crediclick desembolsado", priority = 5)
    void consultarCrediclickDesembolsado(){
        int statusCode = POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301007070","1111",false)
                .validarAcceso(false)
                .consultarCrediclick(true);

        Assert.assertEquals( statusCode,200 );

    }

    @Test(description = "Consultar Crediclick No desembolsado", priority = 6)
    void consultarCrediclickNoDesembolsado(){
        int statusCode =  POLoginPage.getInstance(getDriver())
                .iniciarSesion("22301391524","2222",false)
                .validarAcceso(false)
                .consultarCrediclick(true);
        Assert.assertEquals(statusCode,200);
    }

    // RESUMEN DE PRODUCTOS 👆

}
