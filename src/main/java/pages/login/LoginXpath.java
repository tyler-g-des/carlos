package pages.login;

import org.openqa.selenium.By;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 12/11/2023 7:20 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class LoginXpath {

    private LoginXpath(){

    }

    public static final String SQA_URL = "https://ibp-sqa.web.noprod.cfbhd.com/#/login";
    public static final String SQA_DINAMICO11 = "https://ibp-156796-sqa.web.noprod.cfbhd.com/#/login";
    protected static By tituloLabel = By.xpath("//div[img[@src='assets/img/logo-bhd.svg']] /h2[contains(.,'Personal')]");
    protected static By txtUsuario = By.xpath("//div[label[contains(.,'Usuario')]]//input");
    protected static By txtContrasena = By.xpath("//div[label[contains(.,'Contraseña')]]//input");
    protected static By btnEntrar = By.xpath("//button[span[contains(.,'Entrar')]]");

    protected static By linkOlvidasteTuClave = By.xpath("//div[span[contains(.,'¿Olvidaste tu clave de acceso?')]]");
    protected static By linkIrAInternetBankingEmpresarial = By.xpath("");

    protected static By labelCredencialesInvalidas = By.xpath("//p-messages//span[contains(.,'Usuario o contraseña inválidos')]");
    protected static By labelUsuarioRequerido = By.xpath("//div[label[contains(.,'Usuario')]]//span[contains(.,'Campo requerido')]");
    protected static By labelContrasenaRequerida = By.xpath("//div[label[contains(.,'Contraseña')]]//span[contains(.,'Campo requerido')]");
    public static final By LABEL_NO_CARACTERES_ESPECIALES = By.xpath("//span[contains(.,'No se permiten caracteres especiales')]");
}
