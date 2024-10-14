package pages.pyt.accesosrapidos.beneficiariosinscritos;

import org.openqa.selenium.By;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 17/02/2024 10:21 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class XpathBeneficiarios {
    private XpathBeneficiarios(){}

    /**
     * <h1> ELIMINAR o ver donde se puede usar.</h1>
     */
    public static final By txtBuscar = By.xpath("//p-table[@ng-reflect-loading='false']//input[@placeholder='Buscar']");
    /** Parametro para buscar el beneficiario que se va a eliminar. Usar String.format**/
    protected static final String XP_BNT_ELIMINAR = "//p-table[@ng-reflect-loading='false']//tbody/tr[td/span[contains(.,'%s')]]//button[@ng-reflect-content='Eliminar']";
    protected static final String XP_BTN_EDITAR = "//p-table[@ng-reflect-loading='false']//tbody/tr[td/span[contains(.,'%s')]]//button[@ng-reflect-content='Editar']";

    public static final By tituloModalConfirmarOperacionModal = By.xpath("//div[img[@src='assets/img/modal-logo.svg']]/div[contains(.,'Confirmar operación')]");
    /** <h3>Contiene el titulo confirmar Operación, el icono alerta de color naranja y el h3 con la pregunta: ¿Estás seguro de que quieres eliminar este beneficiario?</h3> */
    protected static By preguntaEliminarBeneficiarioCompleta = By.xpath("//div[@role='dialog' and //div[contains(.,'Confirmar operación')] and //em[@class[contains(.,'pi-sign-alert bhd-orange')]] ] //div/h3[contains(.,'¿Estás seguro de que quieres eliminar este beneficiario?')]");
    protected static By preguntaModalSeguroDeEliminarBeneficiario = By.xpath("//div[@role='dialog'] //div/h3[contains(.,'¿Estás seguro de que quieres eliminar este beneficiario?')]");

    protected static By tituloBeneficiarioInscritos = By.xpath("//h3[em[@class[contains(.,'user-and-check')]] and contains(.,'Beneficiarios inscritos')]");
    protected static By tituloDatosDelBeneficiario = By.xpath("//legend/span[contains(.,'Datos del beneficiario')]");
    protected static By btnAgregarBeneficiario = By.xpath("//p-table[@ng-reflect-loading='false']//button[span[contains(.,'Agregar beneficiario')]]");
    //protected static By btnAddPINPesos = By.xpath("//p-table[@ng-reflect-loading='false']//a[span[contains(.,'PIN Pesos')]]");

    protected static By labelPantallaActual = By.xpath("//a[@aria-selected='true']/span[contains(.,'es')]");

    protected static By txtAlias = By.xpath("//div[label[contains(.,'Alias del beneficiario')]]/input");
    public static final By COMBO_NOMBRE_BANCO = By.xpath("//div[label[contains(.,'Nombre del banco')]]/p-dropdown//span");
    protected static By comboTipoProducto = By.xpath("//div[label[contains(.,'Tipo de producto')]]/p-dropdown//span");
    protected static By comboTipoDocumento = By.xpath("//div[label[contains(.,'Tipo de documento')]]/p-dropdown//span");
    protected static By comboPais = By.xpath("//p-dropdown[@ng-reflect-name='country']//span");
    protected static By comboGenero = By.xpath("//div[label[contains(.,'Género')]]/p-dropdown//span");
    protected static By txtNumeroProducto = By.xpath("//div[label[contains(.,'Número del producto')]]/input");
    protected static By txtNumeroDocumento = By.xpath("//div[label[contains(.,'Número del documento')]]/input");
    protected static By labelNumeroProducto = By.xpath("//label/label[contains(.,'Número del producto')]");
    protected static By labelNumeroDocumento = By.xpath("//div/label[contains(.,'Número del documento')]");
    protected static By errorConsultadoJunta = By.xpath("//div[ label[contains(.,'Número del documento')]]/span/span");
    protected static By txtNombreBeneficiario = By.xpath("//div[label[contains(.,'Nombre del beneficiario')]]/input");
    //public static final By txtTelefonoClic = By.xpath("//div[label[contains(.,'Teléfono')]]/p-inputmask/input");
    // public static final By txtCorreo = By.xpath("//div[label[contains(.,'Correo electrónico')]]/input");


                                                                          //  @beneficiaryNames, @bankName, @Numerobeneficiario


    /** <a href='#'>@param:</a> nombreOAlias, <br> <a href='#'>@param:</a> nombreBanco, <br> <a href='#'>@param:</a> numeroBeneficiario */
    protected static final String XP_VERIFICAR_BENEFICIARIO_AGREGADO2 = "//p-table[@ng-reflect-loading='false']//tbody/tr[td[@id='let-beneficiaryNames']/span[contains(.,'%s')] and td[@id='let-bankName']/span[contains(.,'%s')]] //td[@id='let-accountType']/span[contains(.,'%s')]";
    protected static final String XP_VERIFICAR_BENEFICIARIO_AGREGADO = "//p-table[@ng-reflect-loading='false']//tbody/tr[td/span[contains(.,'1232331666456')]  and td[@id='let-beneficiaryNames']/span[contains(.,'alias de prueba')] and td[@id='let-bankName']/span[contains(.,'Banco de Reservas')]] //td[@id='let-accountType']/span[contains(.,'1232331666456')]";

    /** <a href='#'>@param id</a>: <br> nationalBeneficiaries, <br> internationalBeneficiaries, <br> pinPesosBeneficiaries*/
    protected static final String XPATH_BOTON_AGREGAR_BENEFICIARIO = "//p-table[@ng-reflect-loading='false' and @id='nationalBeneficiaries']//button[span[contains(.,'Agregar beneficiario')]]";

    protected static final By linkVentanaNacionales = By.xpath("//li[@role='presentation']/a[span[contains(.,'Nacionales')]]");
    protected static final By linkVentanaInternacionales = By.xpath("//li[@role='presentation']/a[span[contains(.,'Internacionales')]]");
    protected static final By linkVentanaPinPesos = By.xpath("//li[@role='presentation']/a[span[contains(.,'PIN Pesos')]]");

    /** Utilizar en: Histórico de transacciones,Beneficiarios inscritos, en pantalla: PIN Pesos generados.

     *
     */
    public static final String XPATH_PANTALLA_ACTUAL_CARGADA = "//p-tabview//p-tabpanel[div[@aria-hidden='false']] //p-table[@ng-reflect-loading='false']";
    protected static final By BTN_AGREGAR_BENEFICIARIO = By.xpath(XPATH_PANTALLA_ACTUAL_CARGADA + "//button[span[contains(.,'Agregar beneficiario')]]");
    //protected static final By BTN_ADD_NACIONAL = By.xpath(XPATH_PANTALLA_ACTUAL_CARGADA + "//span[contains(.,'Nacionales')]");
    //protected static final By BTN_ADD_INTERNACIONAL = By.xpath(XPATH_PANTALLA_ACTUAL_CARGADA + "//span[contains(.,'Internacionales')]");
    //protected static final By BNT_ADD_PIN_PESOS = By.xpath(XPATH_PANTALLA_ACTUAL_CARGADA + "//span[contains(.,'PIN Pesos')]");
    protected static final String BTN_EDITAR = XPATH_PANTALLA_ACTUAL_CARGADA + "//tbody/tr[td/span[contains(.,'%s')]]//button[@ng-reflect-content='Editar']";
    protected static final String BTN_ELIMINAR_ICON = XPATH_PANTALLA_ACTUAL_CARGADA + "//tbody/tr[td/span[contains(.,'%s')]]//button[@ng-reflect-content='Eliminar']";
    protected static final By TXT_BUSCAR = By.xpath(XPATH_PANTALLA_ACTUAL_CARGADA + "//input[@placeholder='Buscar']");
    protected static final String TOOL_TIP_PRODUCTO = "//div[@class='p-tooltip-text' and contains(.,'%s')]";
    public static final By msgNoSeEncontroRegistro = By.xpath(XPATH_PANTALLA_ACTUAL_CARGADA + "//td[contains(.,'No se encontraron registros para mostrar')]");
    public static By btnEliminar = By.xpath("//div[@role='dialog']//button[@ng-reflect-label='Eliminar']");


    protected static final By circuloCargando = By.xpath("//spinnericon");
    /**
     * paramentro: Titulo.
     */
    protected static final String XPATH_TITULO_BENEFICIARIO = "//div[@aria-hidden='false']//div[//span[contains(.,'Datos del beneficiario')]]//h3[contains(.,'%s')]"; //div[@aria-hidden='false']//div[//span[contains(.,'Datos del beneficiario')]]//h3[contains(.,'Nuevo beneficiario nacional')]
    protected static final String TEXTO_BUSCAR = "CAMPO BUSCAR";



















}
