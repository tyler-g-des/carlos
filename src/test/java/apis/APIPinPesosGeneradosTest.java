package apis;

import apis.consultas.APISPinPesosGenerados;
//import apis.ibp.dashboard.APISLogin;
import apis.ibp.APISLogin;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 28/05/2024 10:50 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APIPinPesosGeneradosTest extends BaseTest {

    private APISLogin loginUsuarioPinPesosGenerados;
    private APISPinPesosGenerados pinPesosGenerados;

//    @Test(description = "Iniciar sesion usuario con Pin Pesos Generados")
//    void iniciarSesionConPinPesosGenerados(){
//        loginUsuarioPinPesosGenerados = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        pinPesosGenerados = new APISPinPesosGenerados(loginUsuarioPinPesosGenerados);
//
//        createStep("Inicio de sesion status: " + loginUsuarioPinPesosGenerados.getStatusCode());
//        Assert.assertEquals(loginUsuarioPinPesosGenerados.getStatusCode(), 200);
//    }
//
//    //@Test(description = "Pin Pesos generados en el ultimo mes", dependsOnMethods = "iniciarSesionConPinPesosGenerados")
//    void consultarPinPesosGeneradosUltimoMes(){
////        loginUsuarioPinPesosGenerados = LoginAPI.getInstance().iniciarSesion("00111435384","1111");
////        pinPesosGenerados = new APISPinPesosGenerados(loginUsuarioPinPesosGenerados);
////
////        createStep("Inicio de sesion status: " + loginUsuarioPinPesosGenerados.getStatusCode());
////        Assert.assertEquals(loginUsuarioPinPesosGenerados.getStatusCode(), 200);
//
//        pinPesosGenerados.consultarPinPesosGeneradosUltimoMes();
//        createStep( pinPesosGenerados.getReporteAPI(), ( pinPesosGenerados.getStatusCode() == 200),false);
//        Assert.assertEquals( pinPesosGenerados.getStatusCode(),200);
//    }
//
//    //@Test(description = "Pin Pesos generados en Dos ultimos meses", dependsOnMethods = "iniciarSesionConPinPesosGenerados")
//    void consultarPinPesosGeneradosDosUltimosMes(){
//
//        pinPesosGenerados.consultarPinPesosGeneradosDosUltimosMes();
//        createStep( pinPesosGenerados.getReporteAPI(), ( pinPesosGenerados.getStatusCode() == 200),false);
//        Assert.assertEquals( pinPesosGenerados.getStatusCode(),200);
//    }
//
//    //@Test(description = "Pin Pesos generados en Tres ultimos meses", dependsOnMethods = "iniciarSesionConPinPesosGenerados")
//    void consultarPinPesosGeneradosTresUltimosMes(){
//
//        pinPesosGenerados.consultarPinPesosGeneradosTresUltimoMes();
//        createStep( pinPesosGenerados.getReporteAPI(), ( pinPesosGenerados.getStatusCode() == 200),false);
//        Assert.assertEquals( pinPesosGenerados.getStatusCode(),200);
//    }
//
//
//    @Test(description = "Consultar PIN Pesos generados por rango de fecha", dependsOnMethods = "iniciarSesionConPinPesosGenerados")
//    void consultarPinPesosGeneradosPorRangoFecha(){
//
//        pinPesosGenerados.consultarPinPesosGeneradoPorRangoFecha("01/01/2024","27/05/2024");
//        createStep( pinPesosGenerados.getReporteAPI(), ( pinPesosGenerados.getStatusCode() == 200),false);
//        Assert.assertEquals( pinPesosGenerados.getStatusCode(),200);
//    }
}
