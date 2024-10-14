package accesos.serviciosinscritos;

import basetest.BaseTest;
import data.GenerarData;
import io.restassured.http.ContentType;
import microservicios.Utilidad;
import microservicios.beneficiarios.MsServiciosInscritos;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.login.LoginLogic;
import pages.login.POLogin;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 25/03/2024 9:34 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TestServiciosInscritos extends BaseTest {

    private String usuario = "03102674383";
//    @DataProvider
//    public Object[][] dataAgregar() {
//        // Aquí proporcionas los datos para tus pruebas
//        // Puedes obtener los datos de una hoja de cálculo, una base de datos, un archivo de texto, etc.
//        return new Object[][] {
//                { "CLARO", "Compra de Recargas","8092318604","Mi recarga" },
//                { "CLARO", "Pago de Facturas","8092318655","Mi factura" },
//                { "CLARO", "Pago de ordenes","8092318644","Mis ordenes" },
////                { "CLARO", "Pago Distribuidores Mayoristas","8092318645","Mi pago mayorista" },
////                { "CLARO", "Servicio corto","8092318789","Mi corto plazo" },
////                { "CLARO", "servicio largo","8092318964","Mi largo plazo" },
//
//        };
//    }

//    //@Test(priority = 1, description = "Agregar Servicio", dataProvider = "dataAgregar")
//    void agregarServicio(String proveedor, String servicio, String referencia, String descripcion){
//        String result =  LoginLogic.get().iniciarDesdeDispositivoSeguro(usuario,"1111",false)
//                .irPaginaPagosTransferencias(false)
//                .serviciosInscritos(false)
//                .agregarServicio(
//                        proveedor,
//                        servicio,
//                        referencia,
//                        descripcion,
//                        "1111",
//                        true);
//
//        Assert.assertEquals(result,"El servicio ha sido registrado correctamente");
//
//        MsServiciosInscritos servicios = new MsServiciosInscritos("22301391524").newQuery().buscar(proveedor,referencia);
//
//        Assert.assertEquals(servicios.getProveedorServicio(), proveedor);
//        Assert.assertEquals(servicios.getServicio(), servicio);
//        Assert.assertEquals(servicios.getReferencia(), referencia);
//        Assert.assertEquals(servicios.getDescripcion(), descripcion);
//    }


    //@Test(description = "Prueba Login")
    void pruebaLogin(){
        //String result = // 02300767262-
        // 00100965995
        // 22301391524

        System.out.println(ContentType.JSON);

                POLogin.getInstance()
                .login("22301391524","1111",true)
                        .validarPreguntasRespuestasIncorrectas(new GenerarData(),"Respuesta incorrecta",false,true);
                        //.pantallaActual(true);
//                .getTitulo360();
//        System.out.println(result);
        //POLogin.getInstance().inicioSesionGenerico2("00100965995", "1111",false);
    }

    @Test(description = "Prueba Login") // Contrato no aceptado: 03700257789-00100965995
    void pruebaContratoACH(){
        POLogin.getInstance()
                .login("00100965995","1111",true)
                .validarAcceso(false)
                .irPaginaTransaccionesEntreBHDOtrosBancos(false)
                .pantallaContratoACH("00060330051","1111111111111",true);
    }

    @Ignore
    //@Test(priority = 1, description = "Agregar Servicio CLARO")
    void agregarServicioClaro(){


        String result =  POLogin.getInstance().inicioSesionGenerico2(usuario, "1111",false)
                //LoginLogic.get().iniciarDesdeDispositivoSeguro(usuario,"1111",false)
                .irPaginaPagosTransferencias(false)
                .serviciosInscritos(false)
                .agregarServicio(
                        "CLARO",
                        "Compra de Recargas",
                        "8092318604",
                        "requerido",
                        "1111",
                        true);


        Assert.assertEquals(result,"El servicio ha sido registrado correctamente");

        MsServiciosInscritos servicios = new MsServiciosInscritos(usuario).newQuery().buscar("CLARO","8092318604");

        Assert.assertEquals(servicios.getProveedorServicio(), "CLARO");
        Assert.assertEquals(servicios.getServicio(), "Compra de Recargas");
        Assert.assertEquals(servicios.getReferencia(), "8092318604");
        Assert.assertEquals(servicios.getDescripcion(), "requerido");
    }

    @Ignore
    //@Test(priority = 2, description = "Actualizar Servicio CLARO")
    void actualizarServicioClaro(){
        String result = LoginLogic.get().iniciarDesdeDispositivoSeguro(usuario,"1111",false)
                .irPaginaPagosTransferencias(false)
                .serviciosInscritos(false)
                .editarServicio(
                        "8092318604",
                        "CLARO",
                        "Pago de ordenes",
                        "809220111112121212",
                        "descripcion",
                        "1111",
                        true);
        System.out.println(result);
    }

    @Ignore
    //@Test(priority = 3, description = "Eliminar Servicio CLARO")
    void eliminarServicioClaro(){
        String result = LoginLogic.get().iniciarDesdeDispositivoSeguro(usuario,"1111",false)
                .irPaginaPagosTransferencias(false)
                .serviciosInscritos(false)
                .eliminarServicio("809220111112121212",
                        true);

        Assert.assertEquals(result,"No se encontraron registros para mostrar");

        MsServiciosInscritos servicios = new MsServiciosInscritos("22301391524").newQuery().buscar("CLARO","8092318604");
        Assert.assertEquals(servicios.getServicio(),"null");
    }


}
