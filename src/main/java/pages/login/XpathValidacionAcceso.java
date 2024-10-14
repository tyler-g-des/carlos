package pages.login;

import org.openqa.selenium.By;

public class XpathValidacionAcceso {

    protected static By labelPregunta1 = By.xpath("//div[@formarrayname]/div[1]/label");
    protected static By labelPregunta2 = By.xpath("//div[@formarrayname]/div[2]/label");

    protected static By txtRespuesta1 = By.xpath("//div[@formarrayname]/div[1]/input[@placeholder='Ingresa tu respuesta']");
    protected static By txtRespuesta2 = By.xpath("//div[@formarrayname]/div[2]/input[@placeholder='Ingresa tu respuesta']");
    protected static By chechBoxGuardarDispositivoSeguro = By.xpath("//form/div/div/div/p-checkbox[@name='secureDevice']");
}
