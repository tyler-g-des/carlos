package apis.serviciosimpuestos;

import apis.ibp.APIPagoServiciosImpuesto;
import apis.ibp.APISLogin;
import basetest.BaseTest;
import data.GenerarData;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 12/06/2024 6:52 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APIServiciosIncritosTest extends BaseTest {

    APISLogin login = new APISLogin();
    APIPagoServiciosImpuesto apiPagoServicios;
    private String numReferencia;
    private String descipcion;
    private String reference = "888444";
    private String numServicio6Digitos = new GenerarData().getNumRandomString(6);
    private String numeroTelefono = new GenerarData().randomPhoneNumberRD();
    private String descripcionClaro = "Descrippcion claro";
    String descripcion = "";

    @Test(description = "Iniciar Sesion" , priority = 1)
    void iniciarSesion(){
        login.loginSecureDevice("22301391524","1111","Iniciar sesion");
        apiPagoServicios = new APIPagoServiciosImpuesto(login);

        createStep( login.getMessageReport(),login.getStatusCode() == 200,false);
        Assert.assertEquals(login.getStatusCode(),200);
        //numReferencia = UUID.randomUUID().toString().replace("-","");
        //System.out.println("Referencia: " + numReferencia);
        descipcion = "agregado desde API " + numServicio6Digitos;

        System.out.println("Prestamo: " + numServicio6Digitos);
        System.out.println("Descricion: " + descipcion);
    }

    @Test(description = "Agregar compra recarga Claro", dependsOnMethods = "iniciarSesion")
    void agregarCompraRecargaClaro(){
        descripcion = "compra Recarga claro " + numeroTelefono;
        System.out.println(numeroTelefono);

        apiPagoServicios.agregarServicio(
                "CLARO",
                "Compra de Recargas",
                numeroTelefono,
                descripcion,
                "Agregar servicio " + descripcion);

        createStep( apiPagoServicios.getMessageReport(),apiPagoServicios.getStatusCode() == 200,false);
        Assert.assertEquals(apiPagoServicios.getStatusCode(),200);
    }

    @Test(description = "Agregar compra recarga Claro", dependsOnMethods = "agregarCompraRecargaClaro")
    void EliminarCompraRecargaClaro(){

        apiPagoServicios.eliminarServicio(
                "CLARO",
                "Compra de Recargas",
                numeroTelefono,
                "Eliminar Servicio Recarga claro " + numeroTelefono);

        createStep( apiPagoServicios.getMessageReport(),apiPagoServicios.getStatusCode() == 200,false);
        Assert.assertEquals(apiPagoServicios.getStatusCode(),200);
    }

    //@Test(description = "Actualizar Servicio Claro correctamente API", dependsOnMethods = "agregarServicioCoopaspire")
    void actualizarServicioClaroPagoFacturas(){
        apiPagoServicios.actualizarServicio(
                descipcion,
                "COOPASPIRE",
                "Pago de Facturas",
                numReferencia,
                "COOPASPIRE agregado desde API " + numReferencia,
                "Actualizar Servicio " + descipcion);

        createStep( apiPagoServicios.getMessageReport(),apiPagoServicios.getStatusCode() == 200,false);
        Assert.assertEquals(apiPagoServicios.getStatusCode(),200);
        //dashboardNormaAlmonteExterna = new APIDashboard(loginMayo);
    }


    //@Test(description = "Agregar Servicio COOPASPIRE Prestamo correctamente API", dependsOnMethods = "iniciarSesion")
    void agregarServicioCoopaspirePrestamo(){
        descipcion = "Prestamo COOPASPIRE " + numServicio6Digitos;

        apiPagoServicios.agregarServicio(
                "COOPASPIRE",
                "Cuotas",
                numServicio6Digitos,
                descipcion,
                "Agregar " + descipcion);

        createStep( apiPagoServicios.getMessageReport(),apiPagoServicios.getStatusCode() == 200,false);
        Assert.assertEquals(apiPagoServicios.getStatusCode(),200);
    }

    //@Test(description = "Actualizar Servicio COOPASPIRE correctamente API", dependsOnMethods = "agregarServicioCoopaspire")
    void actualizarServicioCoopaspirePrestamo(){
        apiPagoServicios.actualizarServicio(
                descipcion,
                "COOPASPIRE",
                "Cuotas",
                numReferencia,
                "COOPASPIRE agregado desde API " + numReferencia,
                "Actualizar Servicio " + descipcion);

        createStep( apiPagoServicios.getMessageReport(),apiPagoServicios.getStatusCode() == 200,false);
        Assert.assertEquals(apiPagoServicios.getStatusCode(),200);
        //dashboardNormaAlmonteExterna = new APIDashboard(loginMayo);
    }



    //@Test(description = "Agregar Servicio COOPASPIRE correctamente API", dependsOnMethods = "iniciarSesion")
    void agregarServicioCoopaspire(){
        apiPagoServicios.agregarServicio(
                "COOPASPIRE",
                "Cuotas",
                numServicio6Digitos,
                descipcion,
                "Agregar Servicio COOPASPIRE correctamente API: " + numReferencia);

        createStep( apiPagoServicios.getMessageReport(),apiPagoServicios.getStatusCode() == 200,false);
        Assert.assertEquals(apiPagoServicios.getStatusCode(),200);
    }

    //@Test(description = "Actualizar Servicio COOPASPIRE correctamente API", dependsOnMethods = "agregarServicioCoopaspire")
    void actualizarServicioCoopaspire(){
        apiPagoServicios.actualizarServicio(
                descipcion,
                "COOPASPIRE",
                "Cuotas",
                numReferencia,
                "COOPASPIRE agregado desde API " + numReferencia,
                "Actualizar Servicio " + descipcion);

        createStep( apiPagoServicios.getMessageReport(),apiPagoServicios.getStatusCode() == 200,false);
        Assert.assertEquals(apiPagoServicios.getStatusCode(),200);
        //dashboardNormaAlmonteExterna = new APIDashboard(loginMayo);
    }



//        apiPagoServicios.agregarServicio(
//                "CLARO",
//                "Compra de Recargas",
//                "8093334444",
//                "Servicio claro Compra de recarga desde API",
//                "Servicio claro Compra de recarga desde API");


}
