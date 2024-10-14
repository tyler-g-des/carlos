package dashboard;

import basetest.BaseTest;
import data.GenerarData;
import microservicios.MSClientInfoV2;
import microservicios.dashboard.MsCuentas;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.dashboard.DashboardLogic;
import pages.dashboard.DashboardPage;
import pages.login.LoginLogic;
import pages.validacionacceso.POValidacionAcceso;

public class DashboardTest extends BaseTest {

    // CUENTAS EDITAR ALIAS


    //@Test(description = "EDITAR ALIAS CUENTA CORRIENTE -> NOMINA EMPRESARIAL", priority = 1) // defect
    void editarAliasCuentaCorrienteNominaEmpresarial(){
        String nuevaAlias = "Cuenta Corriente nomina empresarial IBP " + new GenerarData().getNumRandomString(3);

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00101535631","1111",false)
                .editarAliasCuenta("00101535631","01613750017", nuevaAlias,true);


        Assert.assertEquals( result, nuevaAlias);
        Assert.assertEquals( new MsCuentas("00101535631").buscar("01613750017").getAlias(), nuevaAlias );
    }

    @Test(description = "EDITAR ALIAS CUENTA CORRIENTE -> SUPERCUENTA C/CKS PERS-EMP RD$", priority = 2) // defect
    void editarAliasSuperCuentaCorrientePersEmp(){
        String nuevaAlias = "SUPERCUENTA Corriente,CKS PERS,EMP IBP " + new GenerarData().getNumRandomString(3);

        //String result =
                LoginLogic.get()
                .iniciarSesion("05500267934","1111",false);
                new POValidacionAcceso(getDriver()).validarAcceso(false);
                //.editarAliasCuenta("05500267934","06476950048", nuevaAlias,true);
        String result = DashboardLogic.getLogica().editarAliasCuenta("05500267934","06476950048", nuevaAlias,true);


        Assert.assertEquals( result, nuevaAlias);
        Assert.assertEquals( new MsCuentas("05500267934").buscar("06476950048").getAlias(), nuevaAlias );
    }

    @Test(description = "EDITAR ALIAS CUENTA CORRIENTE -> SUPERCUENTA C/CKS PERS-EMP RD$", priority = 2) // defect
    void editarAliasSuperCuentaCorrientePersEmpEliminar(){
        String nuevaAlias = "SUPERCUENTA Corriente,CKS PERS,EMP IBP " + new GenerarData().getNumRandomString(3);



        //String result =
        LoginLogic.get()
                .iniciarSesion("40221583491","1111",true);
        new POValidacionAcceso(getDriver()).validarAcceso(true);
        //.editarAliasCuenta("05500267934","06476950048", nuevaAlias,true);
        //String result = DashboardLogic.getLogica().editarAliasCuenta("05500267934","06476950048", nuevaAlias,true);


        ///Assert.assertEquals( result, nuevaAlias);
        //Assert.assertEquals( new MsCuentas("05500267934").buscar("06476950048").getAlias(), nuevaAlias );
    }




    //@Test(description = "EDITAR ALIAS -> CUENTA AHORRO $RD SIN LIBRETA", priority = 3) // DONE
    void editarAliasCuentAhorroSinLibretaPesos(){

        String nuevaAlias = "FD CTA.AHORRO SIN LIBRETA PESOS IBP " + new GenerarData().getNumRandomString(3);

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .editarAliasCuenta("03102892530","04005280015", nuevaAlias,true);

        Assert.assertEquals( result, nuevaAlias );
        Assert.assertEquals( new MsCuentas("03102892530").buscar("04005280015").getAlias(), nuevaAlias );
    }

    //@Test(description = "EDITAR ALIAS -> CTA.AHORROS-NOMINA EMPRESARIAL PESOS", priority = 4) // DONE
    void editarAliasCuentaAhorroNominaEmpresarialPesos(){

        String nuevaAlias = "CTA.AHORROS NOMINA EMPRESARIAL IBP " + new GenerarData().getNumRandomString(3);

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00112839220","1111",false)
                .editarAliasCuenta("00112839220","05700550020", nuevaAlias,true);

        Assert.assertEquals( result, nuevaAlias );
        Assert.assertEquals( new MsCuentas("00112839220").buscar("05700550020").getAlias(), nuevaAlias );
    }

    //@Test(description = "EDITAR ALIAS -> CUENTA DE AHORRO AGIL RD$", priority = 5) // DONE
    void editarAliasCuentaAhorroAgilPesos(){

        String nuevaAlias = "CTA.AHORROS AGIL PESOS IBP " + new GenerarData().getNumRandomString(3);

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00101691491","1111",false)
                .editarAliasCuenta("00101691491","03318770017",nuevaAlias,true);

        Assert.assertEquals( result, nuevaAlias );
        Assert.assertEquals( new MsCuentas("00101691491").buscar("03318770017").getAlias(), nuevaAlias );
    }

    //@Test(description = "EDITAR ALIAS -> NUEVA CUENTA AHORRO MOVIL EN RD$", priority = 6) // DONE
    void editarAliasCuentaMovilAhorroEnPesos(){

        String nuevaAlias = "NUEVA CUENTA AHORRO MOVIL PESOS IBP " + new GenerarData().getNumRandomString(3);

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102892530","1111",false)//  40221056902-10155730056
                .editarAliasCuenta("03102892530","04005280066", nuevaAlias,true);

        Assert.assertEquals( result, nuevaAlias );
        Assert.assertEquals( new MsCuentas("03102892530").buscar("04005280066").getAlias(), nuevaAlias );
    }

    //@Test(description = "EDITAR ALIAS -> SUPERCUENTA DE INVERSION AHORRO EN RD$", priority = 7) // DONE
    void editarAliasSuperCuentaInversionAhorroEnPesos(){

        String nuevaAlias = "SUPERCUENTA INVERSION PESOS IBP " + new GenerarData().getNumRandomString(3);

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00101535631","1111",false)//  40221056902-10155730056
                .editarAliasCuenta("00101535631","01613750076", nuevaAlias,true);

        Assert.assertEquals( result, nuevaAlias );
        Assert.assertEquals( new MsCuentas("00101535631").buscar("01613750076").getAlias(), nuevaAlias );
    }




    //@Test(description = "EDITAR ALIAS SUPERCUENTA DE INVERSION EN USD$") // DONE
    void editarAliasSuperCuentaInversionEnDolar(){

        String nuevaAlias = "SUPERCUENTA INVERSION EN US IBP " + new GenerarData().getNumRandomString(3);

        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111435384","1111",false)
                .editarAliasCuenta("00111435384","01289910049",nuevaAlias,true);

        Assert.assertEquals( result, nuevaAlias);
    }

    //@Test(description = "EDITAR ALIAS CUENTA AHORRO EN USD$") // DONE
    void editarAliasCuentAhorroEnDolar(){
        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00116188293","1111",false)
                .editarAliasCuenta("00116188293","06137280025","CUENTA AHORROS EN US IBP",true);

        Assert.assertEquals(result,"CUENTA AHORROS EN US IBP");
    }

    //@Test(description = "EDITAR ALIAS CTA.AHORROS PERSONAL CREDITO AMIGO RD$") // DONE
    void editarAliasCuentaAhorroPersonalCreditoAmigo(){
        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00101391548","1111",false)
                .editarAliasCuenta("00101391548","02016990071","AHORRO PERSONAL CREDITO AMIGO Automatz IBP",true);

        Assert.assertEquals(result,"AHORRO PERSONAL CREDITO AMIGO Automatz IBP");
    }





    // TARJETAS DE CREDITO
    //@Test(description = "EDITAR ALIAS TARJETA DE CREDITO")
    void editarAliasTarjetaCredito(){
        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .editarAliasTarjetaCredito("03102892530","54887700****5818","STAND.INTL. EN 50 IBP",true);

        Assert.assertEquals(result,"STAND.INTL. EN 50 IBP");
    }



    // PRESTAMOS
    //@Test(description = "EDITAR ALIAS PRESTAMO EN PESOS")
    void editarAliasPretamoEnPesos(){
        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("05500267934","1111",false)
                .editarAliasPrestamo("05500267934","3956310","LIBERACION MEDIANTE REPO 2020 CUOTA RD 50 IBP",true);

        Assert.assertEquals(result,"LIBERACION MEDIANTE REPO 2020 CUOTA RD 50 IBP");
    }

    //@Test(description = "EDITAR ALIAS PRESTAMO EN PESOS T24")
    void editarAliasPretamoEnPesosT24(){
        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00109187385","1111",false)
                .editarAliasPrestamo("00109187385","5630247","PERSONAL.CUOTAS EN USD T24 50 IBP",true);

        Assert.assertEquals(result,"PERSONAL.CUOTAS EN USD T24 50 IBP");
    }

    //@Test(description = "EDITAR ALIAS PRESTAMO EN DOLAR")
    void editarAliasPretamoEnDolar(){
        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00116188293","1111",false)
                .editarAliasPrestamo("00116188293","4007389","ADQUISICION VIVIENDA EN US 50 IBP",true);

        Assert.assertEquals(result,"ADQUISICION VIVIENDA EN US 50 IBP");
    }



    // CERTIFICADOS FINANCIEROS
    //@Test(description = "EDITAR ALIAS CERTIFICADO EN RD$")
    void editarAliasCertificadoEnPesos(){
        String result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102892530","1111",false)
                .editarAliasCertificado("03102892530","1440123","Certificado FinancieroRD editada Automatz IBP",true);

        Assert.assertEquals(result,"Certificado FinancieroRD editada Automatz IBP");
    }

    //@Test(description = "VERIFICAR SE MUESTRE NOMBRE Y CORREO")
    void verificarNombreCorreo(){
        MSClientInfoV2 clientInfoV2 = new MSClientInfoV2("00107768012").newQuery();
        boolean result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00107768012","1111",false)
                .verifiCarNombreCorreo(
                        clientInfoV2.getCompleteNamePascalCase(), //Ricardo Nuñez
                        clientInfoV2.getEmail().toLowerCase(), // yuneibi_batista@bhd.com.do
                        true);
        Assert.assertTrue(result);
    }

    // Boton Consultar cuenta

    //@Test(description = "Boton Compartir Cuenta")
    void botonCompartirCuenta(){

        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111435384","1111",false);
        DashboardPage.getPage()
                .compartirCuenta("05447780030",true);
    }

    //@Test(description = "Botones Compartir Cuentas")
    void botonesCompartirCuentas(){

        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("00111435384","1111",false);
        DashboardPage.getPage()
                .compartirCuentas(true);
    }
}
