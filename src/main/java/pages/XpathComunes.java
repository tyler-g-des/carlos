package pages;

import org.openqa.selenium.By;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 13/11/2023 12:49 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class XpathComunes {
	
	private XpathComunes(){}

    public static final By BTN_CONTINUAR = By.xpath("//button[span[contains(.,'Continuar')]]");
    public static final By BTN_CONTINUAR_MODAL = By.xpath("//div[@role='dialog']//button[span[contains(.,'Continuar')]]");
    public static final By BTN_GUARDAR = By.xpath("//button[span[contains(.,'Guardar')]]");
    public static final By TXT_CORREO = By.xpath("//div[label[contains(.,'Correo electrónico')]]/input");
    public static final By COMBO_MONEDA = By.xpath("//div[label[contains(.,'Moneda')]]/p-dropdown//span");
    public static final By TXT_TELEFONO = By.xpath("//div[label[contains(.,'Teléfono')]]/p-inputmask/input");
    public static final By BTN_REGRESAR = By.xpath("//button[span[contains(.,'Regresar')]]");
    public static String tituloAnterior = "";
    public static By BTN_ACEPTAR_MODAL = By.xpath("//div[@role='dialog']//button[span[contains(.,'Aceptar')]]");
    public static final By TXT_TDC = By.xpath("//input[@placeholder='XXXX']");


    //div[@role='dialog']//td[@id='let-debitProduct']/span[contains(.,'NUEVA CUENTA MOVIL EN RD$') and contains(.,'01289910057')]

    /**
     * Param: Alias o nombre producto. Ejem: NUEVA CUENTA MOVIL EN RD$
     * Param: numProduct: numero de producto. Ejem: 01289910057
     */
    public static final String XP_PRODUCTO_ORIGEN_MODAL = "//div[@role='dialog']//td[@id='let-debitProduct']/span[contains(.,'%s') and contains(.,'%s')]";
    public static By btnContinuarModalDisable = By.xpath("//button[@ng-reflect-label='Continuar' and @ng-reflect-loading='true']");

    /**
     * Param: Alias o nombre beneficiario. Ejem: NUEVA CUENTA MOVIL EN RD$
     * Param: numProduct: numero de producto. Ejem: 01289910057
     */
    public static final String XP_PRODUCTO_DESTINO_MODAL = "//div[@role='dialog']//td[@id[contains(.,'creditBeneficiary')]]/span[contains(.,'%s') and contains(.,'%s')]";

    /**
     * Param: Comision. Ejem: RD$ 0.00
     */
    //public static final String XP_COMISION_MODAL = "//div[@role='dialog']//td[@id='let-commission']/span[contains(.,'%s')]";
    /**
     * Param: IMPUESTO. Ejem: RD$ 10.50
     */
    //public static final String XP_IMPUESTO_MODAL = "//div[@role='dialog']//td[@id='let-tax']/span[contains(.,'%s')]";

    public static By btnCancelarModal = By.xpath("//div[@role='dialog']//button[@ng-reflect-label='Cancelar']");


    /**
     * Param: MONTO. Ejem: RD$ 10.50
     */
    public static final String XPATH_MONTO_MODAL = "//div[@role='dialog']//td[@id='let-amount']/span[contains(.,'%s')]";
    public static final String XPATH_MONEDA_MONTO_MODAL = "//div[@role='dialog']//td[@id='let-amount']/span[contains(.,' %s %s')]";
    public static By txtCodigoTDC = By.xpath("//ibp-token-tdc//div[div/img[@src='assets/img/tarjeta-clave-logo.png'] ] //input[@placeholder='XXXX']");
    /**
     * Param: Moneda: US, RD
     * Param: Comision: Ej: 30.00
     *
     * 'RD'$ '30.00'
     */
    public static final String XPATH_COMISION_MODAL = "//td[@id='let-commission']/span[contains(.,'%s %s')]";
    /**
     * Param: moneda: RD,US
     * Param: impuesto: Ejemplo 3.55
     */
    public static final String XPATH_IMPUESTO_MODAL = "//td[@id[contains(.,'tax')]]/span[contains(.,'%s %s')]";
    /**
     * Param: moneda: RD,US
     * Param: impuesto: Ejemplo 3.55
     */
    public static final String XPATH_TOTAL_TRANSFERENCIA_MODAL = "//div[@role]//td[@id[contains(.,'total-transaction')]]/span[contains(.,'%s$ %s')]";
    /**
     * Sigla:   RD$,   US$,     EU€
     * Monedas: Pesos, Dólares, Euros
     */
    public static final String XPATH_COMBO_MONEDA = "//div[label[contains(.,'Moneda')]]/p-dropdown//span[contains(.,'%s')]";

    // informacion del beneficiario transferencias

    /**
     * Param: Nombre del banco
     * Param: Tipo de producto.
     */
    public static final String XPATH_BANCO_TIPO_PRODUCTO = "//ibp-beneficiary-info//div[div//span[contains(.,'Banco y tipo de producto del beneficiario:')] and //strong[contains(.,'%s - %s')]]";

    /**
     * Param: Numero de producto
     */
    public static final String XPATH_NUMERO_PRODUCTO = "//ibp-beneficiary-info//div[div//span[contains(.,'Número del producto') ] and //strong[contains(.,'%s')]]";
    ////ibp-beneficiary-info/div[div[span[contains(.,'Banco y tipo de producto del beneficiario:')]] and div/strong[contains(.,'BANCO MULTIPLE CARIBE INTERNAC - Tarjeta de crédito')]]
    //ibp-beneficiary-info//div[div//span[contains(.,'Banco y tipo de producto del beneficiario:')] and //strong[contains(.,'Banco Santa Cruz - Corriente')]]

    public static By tituloModalConfirmaTuTransaccion = By.xpath("//div[img[@src='assets/img/modal-logo.svg']]/div[contains(.,'Confirma tu transacción')]");





}
