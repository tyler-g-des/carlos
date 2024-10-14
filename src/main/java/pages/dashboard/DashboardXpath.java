package pages.dashboard;

import org.openqa.selenium.By;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 14/11/2023 2:49 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class DashboardXpath {
    private DashboardXpath(){}

    /**<h1> %s nombre de la sesion. </h1> */

    protected static final By btnCerrarBotonBloqueante = By.xpath("//button[em[@class[contains(.,'action-close')]]]");
    protected static final By BTN_MENU_HAMBURGUESA = By.xpath("//a[@class[contains(.,'p-menubar-button')]]");
    protected static final By TITULO_RESUMEN360 = By.xpath("//h3[contains(.,'360 - Resumen de Productos')]");
    protected static final By LINK_MIS_PRODUCTOS = By.xpath("//a/span[contains(.,'Mis Productos')]");
    protected static final By LINK_PAGOS_TRANSFERENCIAS = By.xpath("//a/span[contains(.,'Pagos y Transferencias')]");
    protected static final By LINK_SOLICITUDES_RECLAMACIONES = By.xpath("//a/span[contains(.,'Solicitudes y Reclamaciones')]");
    protected static final By LINK_OFERTAS = By.xpath("//a/span[contains(.,'Ofertas')]");
    protected static final String XPATH_PAGOS_TRANSFERENCIAS = "(//ul/li//a[span[contains(.,'Pagos y Transferencias')]])[%s]";

    protected static By linkTransaccionesEntreMisProductos = By.xpath("//a/span[contains(.,'Transacciones entre mis productos')]");
    protected static By linkTransaccionesEntreBHDOtrosBancos = By.xpath("//a/span[contains(.,'Transacciones entre productos BHD y a otros bancos')]");
    protected static By linkPagoServiciosImpuestos = By.xpath("//a/span[contains(.,'Pago de servicios e impuestos')]");
    protected static By linkPagosMultiples = By.xpath("//a/span[contains(.,'Pagos múltiples')]");
    protected static By linkTransferenciaInternacional = By.xpath("//a/span[contains(.,'Transferencia internacional')] ");
    protected static By linkTransferenciasRegionalesSIPA = By.xpath("//a/span[contains(.,'Transferencias Regionales SIPA')]");
    protected static By linkTransferenciaPINPesos = By.xpath("//a/span[contains(.,'PIN Pesos')]");

    protected static By iconoPerFilUsuario = By.xpath("//ibp-header-buttons//div[@class[contains(.,'bhd-icons-header')]]//p-avatar/div");
    protected static By iconoConfiguracion = By.xpath("//ibp-header-buttons//div[@routerlink='configurations']//p-avatar/div");
    protected static By iconoCerrarSesion = By.xpath("//ibp-header-buttons//div[@label='Confirm']//p-avatar/div");

    /** parametros (String nombre o correo del usuario). */
    protected static final String XP_LABEL_NOMBRE_CORREO = "//div[@class[contains(.,'p-overlaypanel-content')]]//p[contains(.,'%s')]"; // //div[@class[contains(.,'p-overlaypanel-content')]]//p[contains(.,'mayonex_mb@bhdleon.com.do')]
    protected static By btnPerfilDeUsuario = By.xpath("//button[span[contains(.,'Perfil de usuario')]]");

    protected static By btnHamburgesaMenu = By.xpath("//a[@class[contains(.,'p-menubar-button')]]");

    private static final String XPATH_SESION = "//p-accordiontab[@ng-reflect-header[contains(.,'%s')]]";

    /** parametros (String session, String numeroProducto).*/
    private static final String XPATH_PRODUCTO = XPATH_SESION + "//div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]]";

    /**
     * Param: nombre Sesion
     * Param numero Producto
     */
    protected static final String XPATH_DETALLE_PRODUCTO = XPATH_PRODUCTO + "//span[contains(.,'Ver detalle de producto')]"; // Arreglar xpath detalle de producto

    /**
     * Param; Sesion
     * Param: Numero producto
     */
    private String xpathLinkVerDetalleProducto = "//p-accordiontab[@ng-reflect-header[contains(.,'CUENTAS')]]  //div[div/p[contains(.,'00480830059')]]  //span[contains(.,'Ver detalle de producto')]";

    /** parametro (String nombreSession).*/
    protected static final String XP_LINK_SESION = XPATH_SESION+"//a";
    protected static final String XP_LINK_VER_MAS = XPATH_SESION + "//SPAN[contains(.,'Ver más')]";

    /** parametros (String session, String numeroProducto). */
    protected static final String XP_BTN_EDITAR =  XPATH_PRODUCTO + "//button[@ng-reflect-icon='pi pi-file-edit']";

    /** parametros (String session, String numeroProducto). */
    protected static final String XP_LABEL_ALIAS_ACTUAL = XPATH_PRODUCTO + "//strong[@title]";

    /** parametro (String session).*/
    protected static final String XP_SESION_LOAD = XPATH_SESION + "//div[@class[contains(.,'inserted')]]//strong";
    protected static final By tituloModalEditarAlias = By.xpath("//div[img[@src='assets/img/modal-logo.svg'] ]/div[contains(.,'Ingrese el nuevo alias')]");

    protected static By labelNombreCliente = By.xpath("//div/p[contains(.,'Bienvenido(a)')]/span");

    protected static final String XPA_LABEL_ALIAS = "//p-accordiontab[@ng-reflect-header[contains(.,'%s')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //strong[contains(.,'%s')]";

    /**
     * Param: Sesion
     * Param: num Producto
     * Param: num Producto
     */
    protected static final String XPA_LABEL_NUM_PRODUCTO = "//p-accordiontab[@ng-reflect-header[contains(.,'%s')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] /div/p[contains(.,'%s')]";


    /**
     * Param: Sesion, NumProducto,
     * label: Tipo de producto ,Estado Producto,Balance y Balance en transito.
     */
    protected static final String XPATH_SPAN_COMUNES_CONTAINS = "//p-accordiontab[@ng-reflect-header[contains(.,'%s')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //span[contains(.,'%s')]";

    /**
     * Param: Sesion: PRÉSTAMOS, TARJETAS DE CRÉDITO
     * Param: Numero de producto.
     */
    protected static final String XPATH_BTN_PAGAR = "//p-accordiontab[@ng-reflect-header[contains(.,'%s')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //button[@ng-reflect-label='Pagar']";
    /**
     * Param: Sesiones: TARJETAS DE CRÉDITO, TARJETA PREPAGO
     * Param: numeroProducto
     */
    protected static final String XPATH_ACTIVAR_TARJETA = "//p-accordiontab[@ng-reflect-header[contains(.,'%s')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //span[contains(.,'Actívalo aquí')]";
    protected static By tituloModalPagarProductOrigen = By.xpath("//div[img[@src='assets/img/modal-logo.svg'] ]/div[contains(.,'Producto origen')]");
    protected static By comboProductOrigen = By.xpath("//p-dropdown[@ng-reflect-placeholder='Seleccione el producto origen.']//span");



}
