import apis.ibp.APIDashboard;
import apis.ibp.APISLogin;
//import apis.ibp.dashboard.APISLogin;
import basetest.BaseTest;
import org.testng.annotations.Test;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 30/04/2024 4:29 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PruebaTestAPI extends BaseTest {

//    @Test(description = "PRUEBA API LOGIN")
//    void prueba1(){
//        APISLogin login = APISLogin.getInstance().iniciarSesionDispositivoSeguro("00111435384","1111");
//        createStep(String.valueOf( login.getStatusCode() ) ,login.isResponseSuccessful(),false);
//    }

    @Test(description = "Configurar Preguntas de seguridad")
    void configurarPreguntasSeguridad(){
        APISLogin login = new APISLogin();  // 00100000066-22300221078-00114335433-00100715325
        login.loginSecureDevice("00100965995","1111","Iniciando"); // 00107624660-03100958614
        APIDashboard dashboard = new APIDashboard(login);
        dashboard.configurarPreguntasSeguridadPorDefauld();
        //dashboard.image("Prueba Imagen");
        dashboard.getServicesRates("");
        dashboard.getPassiveAccounts("Existoso");
    }
}
