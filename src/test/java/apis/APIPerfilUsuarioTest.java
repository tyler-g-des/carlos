package apis;

import apis.ibp.APIPerfilUsuario;
import apis.ibp.APISLogin;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 12/06/2024 6:38 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APIPerfilUsuarioTest extends BaseTest {

    private APISLogin login = new APISLogin();
    private APIPerfilUsuario perfilUsuario;

    @Test(description = "Iniciar sesion")
    void iniciarSesion(){
        login.loginSecureDevice("00100965995","1111","Iniciar sesion");
        perfilUsuario = new APIPerfilUsuario(login);
    }

    @Test(description = "Consultar municio", dependsOnMethods = "iniciarSesion")
    void consultarMunicipio(){
        perfilUsuario.consultarMunicioCiudades("SAN CRISTOBAL","21","Consultar municipios de san cristobal");

        createStep( perfilUsuario.getMessageReport(),perfilUsuario.getStatusCode() == 200,false);
        Assert.assertEquals(perfilUsuario.getStatusCode(),200);
    }

    @Test(description = "Consultar municio", dependsOnMethods = "iniciarSesion")
    void consultarSectores(){
        perfilUsuario.consultarSectores("21","Sabana Grande de Palenque","03","Consultar sectores");

        createStep( perfilUsuario.getMessageReport(),perfilUsuario.getStatusCode() == 200,false);
        Assert.assertEquals(perfilUsuario.getStatusCode(),200);
    }

    @Test(description = "Consultar Localidad", dependsOnMethods = "iniciarSesion")
    void consultarLocalidad(){
        perfilUsuario.consultarLocalidad("21","03","Juan Baron","04","Consultar localidad");

        createStep( perfilUsuario.getMessageReport(),perfilUsuario.getStatusCode() == 200,false);
        Assert.assertEquals(perfilUsuario.getStatusCode(),200);
    }

    @Test(description = "Consultar Privincias", dependsOnMethods = "iniciarSesion")
    void consultarProvincias(){
        perfilUsuario.consultarListaProvincias("Consultar lista provincias");

        createStep( perfilUsuario.getMessageReport(),perfilUsuario.getStatusCode() == 200,false);
        Assert.assertEquals(perfilUsuario.getStatusCode(),200);
    }

    @Test(description = "Consultar Compañias telefonicas", dependsOnMethods = "iniciarSesion")
    void consultarCompiasTelefonicas(){
        perfilUsuario.consultarCompañiasTelefonicas("Consultar compañias telefonicas");

        createStep( perfilUsuario.getMessageReport(),perfilUsuario.getStatusCode() == 200,false);
        Assert.assertEquals(perfilUsuario.getStatusCode(),200);
    }


}
