package accion;

import org.openqa.selenium.By;

public class XpathComunes {

    private XpathComunes(){}

    public static final By btnCancelar = By.xpath("//button[@data-pc-name='button' and span[contains(.,'Cancelar')]]");
    public static final By btnContinuar = By.xpath("//button[@data-pc-name='button' and span[contains(.,'Continuar')]]");

    public static final By tituloModalConfirmarOperacion = By.xpath("//div[@role='dialog'] //div[img] /div[contains(.,'Confirmar operación')]");
    public static final By preguntaSeguroCancelarOperacion = By.xpath("//div[@role='dialog'] //div[em[@class[contains(.,'bhd-orange') ] ] ] //h3[contains(.,'¿Seguro que deseas cancelar la operación?')]");

    public static final By btnNoModal = By.xpath("//div[@role='dialog'] //button[span[contains(.,'No')]]");
    public static final By btnSiModal = By.xpath("//div[@role='dialog'] //button[span[contains(.,'Sí')]]");




}
