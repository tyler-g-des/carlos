package com.bhd.ibp.pages;

import org.openqa.selenium.By;

public class XpathComunes {

    private XpathComunes(){}

    public static final By BTN_GUARDAR = By.xpath("//button[span[contains(.,'Guardar')]]");
    public static final By BTN_CONTINUAR_MODAL = By.xpath("//div[@role='dialog'] //button[span[contains(.,'Continuar')]]");
    public static final By BTN_CONTINUAR = By.xpath("//div[p-button/button[span[contains(.,'Continuar')]]]");
    public static final By BTN_REALIZAR_TRANSACCION = By.xpath("//div[@role='dialog'] //button[span[contains(.,'Realizar transacción')]]");
    public static final By BTN_CANCELAR = By.xpath("//button[span[contains(.,'Cancelar')]]");
    public static final By BTN_CANCELAR_MODAL = By.xpath("//div[@role='dialog'] //button[span[contains(.,'Cancelar')]]");
    public static final By BTN_SALIR_MODAL = By.xpath("//div[@role='dialog'] //button[span[contains(.,'Salir')]]");
    public static final By BTN_REGRESAR_MODAL = By.xpath("//div[@role='dialog'] //button[span[contains(.,'Regresar')]]");
    public static final By BTN_ELIMINAR_MODAL = By.xpath("//div[@role='dialog'] //button[span[contains(.,'Eliminar')]]");

    public static final By TITULO_TARJETA_CLAVES = By.xpath("//div[@role='dialog'] //div[img[@src='assets/img/modal-logo.svg']] /div[contains(.,'Tarjeta de claves')]");
    public static final By MSG_INGRESA_CODIGO_SOLICITADO_TDC = By.xpath("//div[@role='dialog'] //h4[contains(.,'Ingresa el código solicitado de tu tarjeta de claves')]");
    public static final By TXT_TDC = By.xpath("//input[@placeholder='XXXX']");
    public static final By MESSAGE_TDC_BENEFICIARIOS_SERVICIOS = By.xpath("//div[@role='dialog' and //div[img and div[contains(.,'Tarjeta de claves')] ] ] //h4[contains(.,'Ingresa el código solicitado de tu tarjeta de claves')]");
    public static final By MESSAGE_CONFIRMAR_OPERACION_MODAL = By.xpath("//div[@role='dialog'] //div[contains(.,'Confirmar operación') and img[@src='assets/img/modal-logo.svg']]");
    public static final By ICON_WARNNIG_MODAL = By.xpath("//div[@role='dialog'] //em[@class[contains(.,'sign-alert')]]");
    public static final By BTN_ENTIENDO = By.xpath("//button[span[contains(.,'Entendido')]]");
    public static final By TXT_ALIAS_BENEFICIARIO = By.xpath("//div[label[contains(.,'Alias del beneficiario')]]//input");
    public static final By TXT_CORREO = By.xpath("//div[label[contains(.,'Correo')]]//input");

    public static final By TXT_NOMBRE_BENEFICIARIO = By.xpath("//div[label[contains(.,'Nombre del beneficiario')]]//input");

}
