import apis.ibp.APIDashboard;
import apis.ibp.APISLogin;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PruebaTestCuentas extends BaseTest {


    @Test(description = "Consulta cuenta")
    void cuenta(){
        APISLogin login = new APISLogin();
        login.loginSecureDevice("40220182089","1111", "Prueba"); // 00108644469-CON-436003

        APIDashboard dashboard = new APIDashboard(login);
        //dashboard.getPassiveAccounts("CON-391950","Prueba");
        dashboard.getPassiveAccounts("Consulta de cuentas.");

        Assert.assertEquals(dashboard.getStatusCode(),200);
    }
}
