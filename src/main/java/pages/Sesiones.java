package pages;

import basetest.BaseTest;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

import static microservicios.Utilidad.getTextoEntreParentesis;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 22/02/2024 3:34 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class Sesiones extends BasePage{

    private static Sesiones instancia = null;

    /**
     * tituloPyt
     */
    private static Properties sesiones;

    private Sesiones(WebDriver webDriver) {
        super(webDriver);
    }

    public static Sesiones instance(){
        if (instancia == null){
            instancia = new Sesiones(BaseTest.getDriver());
            sesiones = new Properties();
        }
        return instancia;
    }

    public String getTituloActual(){
        return sesiones.get("tituloActual" + getTextoEntreParentesis(driver.toString())).toString();
    }

    public void setTituloActual(String nombre){
        sesiones.put("tituloActual" + getTextoEntreParentesis(driver.toString()), nombre);
    }

    public void setUsuario(String usuario){
        sesiones.put("usuario" + getTextoEntreParentesis(driver.toString()), usuario);
    }

    public String getUsuario(){
        System.out.println(sesiones);
        return sesiones.get("usuario" + getTextoEntreParentesis(driver.toString())).toString();
    }








}
