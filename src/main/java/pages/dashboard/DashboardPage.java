package pages.dashboard;

import accion.Accion;
import basetest.BaseTest;
import data.Persistencia;
import microservicios.Utilidad;
import microservicios.dashboard.MsCuentas;
import microservicios.dashboard.MsDashboardNewFeature;
import microservicios.dashboard.MsDashboardTarjetaCredito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.PoAccionesRepetitivas;
import pages.configuracion.ConfiguracionLogic;
import pages.pyt.PagosTransferenciasLogic;
import pages.pyt.transferencias.entrebhdotrosbancos.LogicaTransaccionesBHDOtrosBancos;
import pages.pyt.transferencias.entremisproductos.LogicaTransaccionesMisProductos;
import pages.pyt.transferencias.internacional.LogicaTransferenciaInternacional;
import pages.pyt.transferencias.pinpesos.PinPesosLogic;
import pages.pyt.transferencias.regionalesipa.LogicaTransferenciaRegionalSIPA;

import static java.lang.String.format;
import static microservicios.Utilidad.*;
import static pages.XpathComunes.*;
import static pages.dashboard.DashboardXpath.*;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 14/11/2023 2:47 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class DashboardPage extends BasePage {
    private final Accion accion;
    private static final By txtEditarAlias = By.xpath("//input[@id='alias']");
    private final By btnGuardarDeshabilitado = By.xpath("//button[@disabled and span[contains(.,'Guardar')]]");

    private DashboardPage(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    private String numeroDocumento;
     /** <h2>Retorna una nueva instancia de la clase DashboardPage</h2>
     * @return DashboardPage
     */
    public static DashboardPage getPage(){
        return new DashboardPage(BaseTest.getDriver());
    }

    /**
     * Param; Sesion
     * Param: Numero producto
     */
    private String xpathLinkVerDetalleProducto = "//p-accordiontab[@ng-reflect-header[contains(.,'%s')]]  //div[div/p[contains(.,'%s')]]  //span[contains(.,'Ver detalle de producto')]";

    /**
     * Param: numeroCuenta
     */
    private String xpathBotonCompartirCuenta = "//p-accordiontab[@ng-reflect-header[contains(.,'CUENTAS')]]  //div[div/p[contains(.,'%s')]] //button[@ng-reflect-content='Compartir']";
    private String xpathLabelAliasCuenta = "//p-accordiontab[@ng-reflect-header[contains(.,'CUENTAS')]]  //div[div/p[contains(.,'%s')]]  /div/div/strong";


    public String getTutuloGenerico(){
        return accion.getText(By.xpath("//div/h3"),3,getClass(), false);

    }

    /** <h2>Toma captura y retorna el h3 que contiene el titulo del Dashboard Resumen de producto.</h2>
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return 360 - Resumen de Productos
     */
    protected String pantallaDashboard(boolean crearReporte){
        String titulo = accion.getText(TITULO_RESUMEN360, 45, getClass(), crearReporte );
        numeroDocumento = Persistencia.getInstance().getDocumentNumber(getNombreCliente());
        return titulo;
    }

    public String getNombreCliente(){
        return accion.getText(labelNombreCliente,5, getClass(),false);
    }

    /** <h2>Captura de pantalla para las acciones a realizar en la misma pantalla.</h2>
     * @param usuario Usuario actual.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return 360 - Resumen de Productos
     */
    protected String pantallaDashboard(String usuario,boolean crearReporte){

        String mensaje = accion.getText(TITULO_RESUMEN360, 45, getClass(), crearReporte );
        numeroDocumento = Persistencia.getInstance().getDocumentNumber(getNombreCliente());
        MsDashboardNewFeature consultarPublicidad = new MsDashboardNewFeature(numeroDocumento).newQuery();
        if (consultarPublicidad.contienePublicidad()){
            accion.clickOn(btnCerrarBotonBloqueante,"MODAL PRESENTACION",5,getClass(), crearReporte, crearReporte);
        }return mensaje;
    }

    /** <h2>Realiza click en el icono de configuración la cual navegará a la página de configuración de producto.</h2>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected ConfiguracionLogic clickBotonIconoConfiguracion(boolean crearReporte){
        accion.clickOns( iconoConfiguracion,"ICONO CONFIGURACION",5,getClass(),false,crearReporte);
        return ConfiguracionLogic.getLogica();
    }

    /** <h2>Este realiza click sobre el icono de perfil del usuario para desplegar el mismo.</h2>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    private void clickBotonIconoPerfilUsuario(boolean crearReporte){
        accion.clickOns( iconoPerFilUsuario,"ICONO PERFIL DE USUARIO",10, getClass(),false, crearReporte);
    }

    /** <h2>Verifica que este cargando el nombre y correo del usuario, retorna true, de lo contrario retorna false.</h2>
     * @param nombre Nombre del usuario.
     * @param correo Correo del usuario
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return boolean = true
     */
    protected boolean verificarSeMuestreNombreCorreo(String nombre, String correo, boolean crearReporte){
        clickBotonIconoPerfilUsuario( crearReporte );
        String mensaje = accion.getText(By.xpath( format(XP_LABEL_NOMBRE_CORREO, nombre)),3, getClass(),false)
                +"<br>" + accion.getText(By.xpath( format(XP_LABEL_NOMBRE_CORREO, correo)),1, getClass(), false);
        accion.crearPaso( mensaje, true, crearReporte, crearReporte);
        return true;
    }

    /** <h2>Realiza click en el icono perfil de usuario y luego presiona el botón Perfil de usuario, acción la cual envagara a dicha pagina.</h2>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void clickBotonPerfilDeUsuario(boolean crearReporte){
        clickBotonIconoPerfilUsuario(crearReporte);
        accion.clickOn(btnPerfilDeUsuario,"BOTON PERFIL DE USUARIO",5, getClass(), crearReporte, crearReporte);
    }

    /** <h2>Realiza click sobre pagos y transferencias.</h2>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected PagosTransferenciasLogic clicMenuPagosTransferencias(boolean crearReporte){
        clickMenuBotonHamburguesa(crearReporte);
        accion.clickOns(LINK_PAGOS_TRANSFERENCIAS,"PAGOS Y TRANSFERENCIAS",1, getClass(), false, crearReporte);
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        return PagosTransferenciasLogic.getLogica();
    }

    /** Realiza click sobre el botón en forma de hamburguesa, que se visualiza en el menú cuando el navegador
     * esta en un tamaño mas pequeño. Esto desplegara el menú principal del canal, permitiendo visualizar las
     * opciones del menú: <br><br>Mis productos, <br>Pagos y transferencias, <br>Solicitudes y reclamaciones, <br>Ofertas.
     * Este método esta condicionado, por lo que si el xpath del botón cambia, no realizara el click sobre el mismo cuando esta presente, lo cual causaría que no se desplegué el menú.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return boolean.
     */
    private boolean clickMenuBotonHamburguesa(boolean crearReporte){
        if (accion.isElementVisibleNoException(BTN_MENU_HAMBURGUESA, 0 )){
            accion.clickOn(BTN_MENU_HAMBURGUESA,"BOTON HAMBURGUESA",1, getClass(), false, crearReporte);
            return true;
        }return false;
    }

    /** Cuando el navegador esta maximizado en tamaño normal, realizara un mouseOver sobre pagos y
     * transferencias. <br><br>Si en pagos y transferencia se visualiza una flecha apuntando hacia abajo, entonces
     * realizara click en pagos y transferencia para desplegarlo. <br><br>Si se muestra el botón en forma de
     * hamburguesa hará click sobre el mismo y luego realizara click en pagos y transferencias para desplegarlo.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    private void mouseOverPagosTransferencias(boolean crearReporte){
        clickMenuBotonHamburguesa(crearReporte);
        accion.mouseOvers(LINK_PAGOS_TRANSFERENCIAS,2, getClass());
    }

    // MEtodo nuevo que estoy creando para desplegar Pagos Transferencias.
    private void desplegarPagoTransferencias(boolean crearReporte){
        if (clickMenuBotonHamburguesa(crearReporte)){
            accion.clickOns(LINK_PAGOS_TRANSFERENCIAS,"PAGOS Y TRANSFERENCIAS",3,getClass(),false, crearReporte);
        }else {
            if (accion.isElementVisibleNoException(by(format(XPATH_PAGOS_TRANSFERENCIAS,"1") ),0)){
                accion.mouseOvers(LINK_PAGOS_TRANSFERENCIAS,2, getClass());
            }else {
                accion.clickOn( by(format(XPATH_PAGOS_TRANSFERENCIAS,10)),"PAGOS Y TRANSFERENCIAS",3,getClass(),false,crearReporte);
            }
        }
    }

    public void clickMisProductos( boolean crearReporte){
        By linkMisProductos = By.xpath("//a/span[contains(.,'Mis Productos')]");
        clickMenuBotonHamburguesa(crearReporte);
        accion.clickOns( linkMisProductos,"LINK MENU MIS PRODUCTOS",1,getClass(),crearReporte,crearReporte);
    }

    /** <h2>Realiza click en el menú en la opción de Transacciones entre mis productos, desde el menu.</h2>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected LogicaTransaccionesMisProductos clicMenuTransaccionesEntreMisProductos(boolean crearReporte){
        //mouseOverPagosTransferencias(crearReporte);
        desplegarPagoTransferencias(crearReporte);
        accion.clickOns(linkTransaccionesEntreMisProductos, "TRANSACCIONES ENTRE MIS PRODUCTOS", 2, getClass(), crearReporte, crearReporte);
        return LogicaTransaccionesMisProductos.getNewLogica();
    }

    /** <h2>Realiza click en la opción de Transacciones entre productos BHD y a otros bancos desde el menú.</h2>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected LogicaTransaccionesBHDOtrosBancos clicMenuTransaccionesBHDOtrosBancos(boolean crearReporte){
        //mouseOverPagosTransferencias(crearReporte);
        desplegarPagoTransferencias(crearReporte);
        accion.clickOns(linkTransaccionesEntreBHDOtrosBancos, "TRANSACCIONES ENTRE PRODUCTOS BHD Y OTROS BANCOS",2, getClass(), crearReporte,crearReporte);
        return LogicaTransaccionesBHDOtrosBancos.getNewLogica();
    }

    protected LogicaTransferenciaInternacional clicMenuInternacionales(boolean crearReporte){
        //mouseOverPagosTransferencias(crearReporte);
        desplegarPagoTransferencias(crearReporte);
        accion.clickOns( linkTransferenciaInternacional, "TRANSFERENCIA INTERNACIONAL",3, getClass(), crearReporte,crearReporte);
        return LogicaTransferenciaInternacional.getLogica();
    }

    protected LogicaTransferenciaRegionalSIPA clicMenuTransferenciaRegionalesSIPA(boolean crearReporte){
        //mouseOverPagosTransferencias(crearReporte);
        desplegarPagoTransferencias(crearReporte);
        accion.clickOns( linkTransferenciasRegionalesSIPA, "TRANSFERENCIAS REGIONALES SIPA",3, getClass(), crearReporte,crearReporte);
        return LogicaTransferenciaRegionalSIPA.getNewLogica();
    }

    /** <h2>Despliega en el menú la sesión pagos y transferencias y realiza click sobre la opción Pin Pesos.</h2>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected PinPesosLogic clicMenuPINPesos(boolean crearReporte){
        mouseOverPagosTransferencias(crearReporte);
        accion.clickOns( linkTransferenciaPINPesos, "PIN PESOS TRANSFERENCIAS",3, getClass(), crearReporte,crearReporte);
        return PinPesosLogic.getNewLogica();
    }

    /** <h2>Dirige al modal para editar el alias de un certificado digital.</h2>
     * @param numeroProducto Numero del certificado digital.
     * @param nuevaAlias Nuevo alias para el certificado.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return CERTIFICADOS
     */
    protected String pantallaEditarAliasCertificado(String numeroProducto, String nuevaAlias,boolean crearReporte){
        String sesion = "CERTIFICADOS";
        desplegarSesionAll(sesion, crearReporte);
        pantallaEditarAlias(sesion,numeroProducto, nuevaAlias, crearReporte);
        return sesion;
    }

    /** <h2>Dirige al modal para editar el alias de una cuenta.</h2>
     * @param numeroProducto Numero de la cuenta.
     * @param nuevaAlias Nueva alias para la cuenta.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return CUENTAS
     */
    protected String pantallaEditarAliasCuenta(String numeroProducto, String nuevaAlias,boolean crearReporte){
        String sesion = "CUENTAS";
        desplegarSesionAll(sesion, crearReporte);
        pantallaEditarAlias(sesion,numeroProducto, nuevaAlias, crearReporte);
        return sesion;
    }

    /** <h2>Dirige al modal para editar el alias de un prestamo.</h2>
     * @param numeroProducto Numero del prestamo.
     * @param nuevaAlias Nueva alias para la prestamo.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return PRÉSTAMOS
     */
    protected String pantallaEditarAliasPrestamos(String numeroProducto, String nuevaAlias,boolean crearReporte){
        String sesion = "PRÉSTAMOS";
        desplegarSesionAll( sesion, crearReporte);
        pantallaEditarAlias( sesion,numeroProducto, nuevaAlias, crearReporte);
        return sesion;
    }

    /** <h2>Dirige al modal para editar el alias de una tarjeta de credito.</h2>
     * @param numeroProducto Numero de la tarjeta de credito.
     * @param nuevaAlias Nueva alias para la tarjeta de credito.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return TARJETAS DE CRÉDITO
     */
    protected String pantallaEditarAliasTarjetaCredito(String numeroProducto, String nuevaAlias,boolean crearReporte){
        String sesion = "TARJETAS DE CRÉDITO";
        desplegarSesionAll(sesion, crearReporte);
        pantallaEditarAlias(sesion,numeroProducto, nuevaAlias, crearReporte);
        return sesion;
    }

    /** <h2>Permite desplegar las diferentes sesión del resumen de productos 960.</h2>
     * @param nombreSesion Nombre a desplegar.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void desplegarSesionAll(String nombreSesion, boolean crearReporte){
        if (accion.getAttribute( By.xpath( format (XP_LINK_SESION, nombreSesion )),"aria-expanded",10, getClass() ).equals("false")){
            accion.clickOn( By.xpath( format( XP_LINK_SESION, nombreSesion )),"SESION " + nombreSesion,1, getClass(),false, crearReporte);
        }
        accion.isElementsVisible( By.xpath( format(XP_SESION_LOAD, nombreSesion )),30, getClass() );
        if (accion.isElementVisibleNoException( By.xpath( format(XP_LINK_VER_MAS, nombreSesion )), 0 )){
            accion.clickOn( By.xpath( format(XP_LINK_VER_MAS,nombreSesion )),"VER MAS",0, getClass(), crearReporte, crearReporte);
        }
    }

    protected void consultarProducto(String sesion,String alias,String numProducto, String tipo, String estado, String moneda, String balance, String balanceTransito, boolean crearReporte){

    }

    /** Presiona el botón editar del producto enviado por parámetro en la sesión indica por el parámetro nombreSesion, llevando al modal para editar el alias.
     * @param nombreSesion Nombre de la sesión donde se encuentra el producto.
     * @param numeroProducto Numero de producto el cual se seleccionara para editar el alias.
     * @param nuevaAlias Permite llenar el campo de la nueva alias.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    private void pantallaEditarAlias(String nombreSesion, String numeroProducto, String nuevaAlias, boolean crearReporte){
        accion.clickOn( By.xpath( format( XP_BTN_EDITAR, nombreSesion, numeroProducto )),"BOTON EDITAR",1, getClass(), crearReporte, crearReporte);
        accion.getText( tituloModalEditarAlias,2, getClass(), crearReporte);
        accion.writeOn( txtEditarAlias, nuevaAlias,"CAMPO ALIAS",1, getClass(), crearReporte);
    }

    /** <h2>Permite ingresar un nuevo Alias, guarda la misma y retorna el alias Actual en Dashboard luego de su modificación.</h2>
     * @param nombreSesion Nombre de la sesión al cual pertenece el producto que se esta actualizando el alias.
     * @param numeroProducto Numero del producto que se actualizara el alias.
     * @param nuevaAlias Nueva alias que se asignara al producto.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Alias actual del producto en el Dashboard luego de ser editada.
     */
    protected String guardarAlias(String nombreSesion, String numeroProducto,String nuevaAlias, boolean crearReporte){
        accion.clickOn(BTN_GUARDAR, "BOTON GUARDAR",0,getClass(),crearReporte,crearReporte);
        accion.isElementInvisibility(btnGuardarDeshabilitado, 5, getClass());
        String aliasActual = accion.getText(By.xpath(format(XP_LABEL_ALIAS_ACTUAL, nombreSesion, numeroProducto) ),2, getClass(), false);
        accion.isElementsVisible(By.xpath( format(XP_BTN_EDITAR, nombreSesion, numeroProducto) ), 1, getClass());

        if (!aliasActual.equals(nuevaAlias)){
            String mensajError = format("Error al intentar modificar el alias  <br>Alias actual: %s  <br>Resultado esperado: %s",aliasActual,nuevaAlias);
            accion.crearPaso(mensajError,false,true, true);
        }else {
            accion.crearPaso("NUEVO ALIAS: " + nuevaAlias,true, crearReporte, crearReporte);
        }
        return aliasActual;
    }

    private String sesionCuentas = "CUENTAS";

    // ______________________  Metodos para consulta cuentas  ___________________

    /**
     * Este metodo nos permite consultar la informacion de una cuenta en el dashboard.
     * @param numeroCuenta
     * @param alias
     * @param tipoProducto
     * @param estadoProducto
     * @param moneda
     * @param balance
     * @param balanceEnTransito
     * @param crearReporte
     */
    private void consultarCuentaa(String numeroCuenta, String alias, String tipoProducto, String estadoProducto, String moneda, String balance, String balanceEnTransito, boolean crearReporte){
        String formatoProducto = "Consulta de la cuenta: %s  <br><br> Nombre producto: %s <br> Numero producto: %s<br> Tipo de producto: %s <br> Estado de producto: %s <br> Balance Disponible: %s <br> Balance en transito: %s <br>";

        alias = accion.getText(by(format(XPA_LABEL_ALIAS, sesionCuentas, numeroCuenta, alias)),1,getClass(),false);
        numeroCuenta = accion.getText(by(format(XPA_LABEL_NUM_PRODUCTO, sesionCuentas, numeroCuenta,numeroCuenta )),1,getClass(),false);
        tipoProducto = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionCuentas, numeroCuenta, tipoProducto )),1,getClass(),false);
        estadoProducto = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionCuentas, numeroCuenta, estadoProducto)),1,getClass(),false);
        balance = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionCuentas, numeroCuenta, moneda+"$ " + balance )),1,getClass(),false);
        balanceEnTransito = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionCuentas, numeroCuenta, moneda+"$ " + balanceEnTransito)),1,getClass(),false);

        accion.mouseOver(by(format(xpathLinkVerDetalleProducto,sesionCuentas,numeroCuenta)), 0,getClass());
        accion.crearPaso(String.format(formatoProducto,numeroCuenta, alias, numeroCuenta, tipoProducto, estadoProducto, balance, balanceEnTransito),true, crearReporte, crearReporte);
    }

    public void consultarCuenta(String numeroCuenta, boolean crearReporte){
        pantallaDashboard(crearReporte);
        MsCuentas cuenta = new MsCuentas(numeroDocumento).buscar(numeroCuenta);
        desplegarSesionAll(sesionCuentas, crearReporte);

        consultarCuentaa(cuenta.getNumProducto(), cuenta.getAlias(),cuenta.getTipoProductoResumido(),cuenta.getEstadoProducto(),cuenta.getMoneda(),cuenta.getBalance(),cuenta.getBalanceEnTransito(),crearReporte);

//        String formatoProducto = "Consulta de cuenta: %s  <br><br> Nombre producto: %s <br> Numero producto: %s<br> Tipo de producto: %s <br> Estado de producto: %s <br> Balance Disponible: %s <br> Balance en transito: %s <br>";
//
//        String alias = accion.getText(by(format(XPA_LABEL_ALIAS, "CUENTAS",cuenta.getNumProducto(),cuenta.getAlias())),1,getClass(),false);
//        String numProduct = accion.getText(by(format(XPA_LABEL_NUM_PRODUCTO, "CUENTAS",numeroCuenta,numeroCuenta )),1,getClass(),false);
//        String tipo = accion.getText(by(format(XPA_LABEL_TIPO_ESTADO_BALANCE_BLTRANSITO, "CUENTAS",numeroCuenta, cuenta.getTipoProductoResumido() )),1,getClass(),false);
//        String estado = accion.getText(by(format(XPA_LABEL_TIPO_ESTADO_BALANCE_BLTRANSITO, "CUENTAS",numeroCuenta, cuenta.getEstadoProducto())),1,getClass(),false);
//        String balance = accion.getText(by(format(XPA_LABEL_TIPO_ESTADO_BALANCE_BLTRANSITO, "CUENTAS",numeroCuenta, cuenta.getMoneda()+"$ " + cuenta.getBalance() )),1,getClass(),false);
//        String balanceTransito = accion.getText(by(format(XPA_LABEL_TIPO_ESTADO_BALANCE_BLTRANSITO, "CUENTAS",numeroCuenta, cuenta.getMoneda()+"$ " +cuenta.getBalanceEnTransito())),1,getClass(),false);
//
//        accion.mouseOvers(by(format(XPATH_DETALLE_PRODUCTO, "CUENTAS", numeroCuenta)),1,getClass());
//
//        accion.isElementInvisibleNoException(by(format(XPATH_DETALLE_PRODUCTO, "CUENTAS", numeroCuenta)),0);
//        accion.crearPaso(String.format(formatoProducto,numProduct, alias, numProduct, tipo, estado, balance, balanceTransito),true, crearReporte, crearReporte);
    }

    public void consultarCuentas(boolean crearReporte){
        pantallaDashboard(crearReporte);
        MsCuentas cuentas = new MsCuentas(numeroDocumento);
        desplegarSesionAll(sesionCuentas, crearReporte);

        for (int i = 0; i < cuentas.getToltaProductos(); i++) {
            MsCuentas cuenta = cuentas.iterarProductos(i);
            consultarCuentaa(cuenta.getNumProducto(), cuenta.getAlias(),cuenta.getTipoProductoResumido(),cuenta.getEstadoProducto(),cuenta.getMoneda(),cuenta.getBalance(),cuenta.getBalanceEnTransito(),crearReporte);
        }
    }

    public void verDetalleDeCuenta(String numeroCuenta, boolean crearReporte){
        consultarCuenta(numeroCuenta, crearReporte);
        accion.clickOn(By.xpath(format(xpathLinkVerDetalleProducto,sesionCuentas,numeroCuenta)),"LINK ver detalle de producto",1, getClass(),false, crearReporte);
    }

    public void compartirCuenta(String numeroCuenta, boolean crearReporte){
        pantallaDashboard("",crearReporte);
        desplegarSesionAll(sesionCuentas, crearReporte);
        MsCuentas cuentas = new MsCuentas(numeroDocumento);
        System.out.println(Persistencia.getInstance().getCorreo(numeroDocumento));
        if (!cuentas.buscar(numeroCuenta).getNombreProducto().contains("SUPERCUENTA")){
            botonCompartirCuenta(numeroCuenta, cuentas, crearReporte);
        }
        //accion.clickOn(by( format(xpathBotonCompartirCuenta, numeroCuenta) ),"BOTON COMPARTIR",1,getClass(),crearReporte,crearReporte);
    }

    public void compartirCuentas(boolean crearReporte){
        pantallaDashboard("", crearReporte);
        desplegarSesionAll(sesionCuentas, crearReporte);
        //System.out.println(numeroDocumento);
        MsCuentas cuentas = new MsCuentas(numeroDocumento);
        //System.out.println(cuentas.getToltaProductos());

//        for (int i = 0; i < cuentas.getToltaProductos(); i++) {
//            System.out.println(cuentas.getNumProducto());
//        }

        for (int i = 0; i < cuentas.getToltaProductos(); i++) {
            MsCuentas cuenta = cuentas.iterarProductos(i);

            if (!cuenta.getNombreProducto().contains("SUPER")){
                System.out.println(cuenta.getNumProducto());
                botonCompartirCuenta( cuenta.getNumProducto(),cuenta, crearReporte);
            }
            //System.out.println(cuenta.getAlias());
            //System.out.println(cuenta.getNumProducto());
            //System.out.println(cuentas.iterarProductos(i).getNumProducto());
            //System.out.println(cuentas.getNombreProducto());

//            if (!cuentas.getNombreProducto().contains("SUPERCUENTA")){
//                botonCompartirCuenta(cuentas.getNumProducto(), cuentas, crearReporte);
//            }
        }
    }

    /**
     * Param: Secion: Transferencias BHD, Transferencias interbancarias, Transferencias internacionales
     * Param: Label
     * Param: value Label
     */
    private String xpathLabelsShareAccountContainSpanStrong = "//div[div/span[contains(.,'%s')]]  //div[span[contains(.,'%s')] and strong[contains(.,'%s')]]";
    private String tabla = "<table>\n" +
            "        <tr>\n" + "<th>Transferencias BHD</th> \n"   + "<th>Transferencias BHD</th>\n" + "<th>Transferencias internacionales</th>\n" + "    </tr>" +

            "        <tr>\n" + "<td>Juan Pérez</td>\n"     + "<td>18</td>\n"   + "<td>90</td>\n"   + " </tr>" +

            "        <tr>\n" + "<td>María López</td>\n"    + " <td>19</td>\n"  + "<td>85</td>\n"   + " </tr>\n" +
            "        <tr>\n" + "<td>Carlos Sánchez</td>\n" + " <td>17</td>\n"  + " <td>88</td>\n"  + " </tr>\n" +
            "        <tr>\n" + "<td>Ana Gómez</td>\n"      + "<td>18</td>\n"   + "<td>92</td>\n"   + " </tr>\n" +
            "        <tr>\n" + "<td>Ana Gómez</td>\n"      + "<td>18</td>\n"   + "<td>92</td>\n"   + " </tr>\n" +
            "\n" +
            "        <tr>\n" + "<td></td>\n"               + "<td>18</td>\n"   + "<td>92</td>\n"   + " </tr>\n" +
            "        <tr>\n" + "<td></td>\n"               + "<td>18</td>\n"   + "<td>92</td>\n"   + " </tr>\n" +
            "        <tr>\n" + "<td></td>\n"               + "<td></td>\n"     + "<td>92</td>\n"   + " </tr>\n" +
            "    </table>";

//    public static String createTable(String body){
//    return "<table>"+body+"</table>";
//    }
//
//    public static String createTR(String body){
//        return "<tr>"+body+"</tr>";
//    }
//
//    public static String createTH(String body){
//        return "<th>"+body+"</th>";
//    }
//
//    public static String createTD(String body){
//        return "<td>"+body+"</td>";
//    }


    private void botonCompartirCuenta(String numeroCuenta, MsCuentas cuenta, boolean crearReporte){
        String sesionTransferBHD = "Transferencias BHD";
        String sesionTransferInterbancarias = "Transferencias interbancarias";
        String sesionTransferInternacionales = "Transferencias internacionales";

        accion.clickOn(by( format(xpathBotonCompartirCuenta, numeroCuenta) ),"BOTON COMPARTIR CUENTA: " + cuenta.getNumProducto(),1,getClass(),crearReporte,crearReporte);

        String nombre = Persistencia.getInstance().getNombreCompleto(numeroDocumento);
        String correo = Persistencia.getInstance().getCorreo(numeroDocumento);

        String bhdNombre = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferBHD,"Nombre:",nombre ) ),1,getClass(),false).replace(":",":<br>");
        String bhdTipoProducto = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferBHD,"Tipo de producto:",cuenta.getTipoProducto() ) ),1,getClass(),false).replace(":",":<br>");
        String bhdNumCuenta = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferBHD,"Número de cuenta:",cuenta.getNumProducto() ) ),1,getClass(),false).replace(":",":<br>");
        String bhdCorreo = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferBHD,"Correo electrónico:",correo ) ),1,getClass(),false).replace(":",":<br>");
        String bhdBanco = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferBHD,"Banco:","Banco BHD" ) ),1,getClass(),false).replace(":",":<br>");

        String interbancariaNombre = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInterbancarias,"Nombre:",nombre ) ),1,getClass(),false).replace(":",":<br>");
        String interbancariaIdentidad = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInterbancarias,"Documento de identidad",numeroDocumento ) ),1,getClass(),false);
        String interbancarioTipoProducto = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInterbancarias,"Tipo de producto:",cuenta.getTipoProducto() ) ),1,getClass(),false).replace(":",":<br>");
        String interbancarioNumCuenta = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInterbancarias,"Número de cuenta:",cuenta.getNumProducto() ) ),1,getClass(),false).replace(":",":<br>");
        String interbancarioMoneda = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInterbancarias,"Moneda:",cuenta.getMoneda() ) ),1,getClass(),false).replace(":",":<br>");
        String interbancarioCorreo = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInterbancarias,"Correo electrónico:", correo ) ),1,getClass(),false).replace(":",":<br>");
        String interbancarioBanco = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInterbancarias,"Banco:", "Banco BHD" ) ),1,getClass(),false).replace(":",":<br>");

        String internacionalesNombre = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInternacionales,"Nombre:",nombre ) ),1,getClass(),false).replace(":",":<br>");
        String internacionalesIdentidad = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInternacionales,"Documento de identidad",numeroDocumento ) ),1,getClass(),false);
        String internacionalesTipoProducto = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInternacionales,"Tipo de producto:",cuenta.getTipoProducto() ) ),1,getClass(),false).replace(":",":<br>");
        String internacionalesNumCuenta = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInternacionales,"Número de cuenta:",cuenta.getNumProducto() ) ),1,getClass(),false).replace(":",":<br>");
        String internacionalesMoneda = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInternacionales,"Moneda:",cuenta.getMoneda() ) ),1,getClass(),false).replace(":",":<br>");
        String internacionalesCorreo = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInternacionales,"Correo electrónico:", correo ) ),1,getClass(),false).replace(":",":<br>");
        String internacionalesCodigoSwift = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInternacionales,"SWIFT:", "BCBHDOSDXXX" ) ),1,getClass(),false).replace(":",":<br>");
        String internacionalesBanco = accion.getText(by( format( xpathLabelsShareAccountContainSpanStrong,sesionTransferInternacionales,"Banco:", "Banco BHD" ) ),1,getClass(),false).replace(":",":<br>");


        String tabla2 = createTable(
                createTR(createTH("Transferencias BHD") + createTH("Transferencias <br>interbancarias") + createTH("Transferencias <br>internacionales"))+
                        createTR(createTD( bhdNombre )            + createTD( interbancariaNombre )                + createTD(internacionalesNombre) ) +
                        createTR(createTD( bhdTipoProducto )      + createTD(interbancariaIdentidad.split(numeroDocumento)[0] + "<br>"+numeroDocumento ) + createTD(internacionalesIdentidad.split(numeroDocumento)[0] +"<br>"+ numeroDocumento))+
                        createTR(createTD( bhdNumCuenta )         + createTD( interbancarioTipoProducto )          + createTD(internacionalesTipoProducto) ) +
                        createTR(createTD( bhdCorreo )            + createTD( interbancarioNumCuenta )             + createTD(internacionalesNumCuenta) ) +
                        createTR(createTD( bhdBanco )             + createTD( interbancarioMoneda )                + createTD(internacionalesMoneda) ) +
                        createTR(createTD("" )               + createTD( interbancarioCorreo )                + createTD(internacionalesCorreo) ) +
                        createTR(createTD("" )               + createTD( interbancarioBanco )                 + createTD(internacionalesCodigoSwift) ) +
                        createTR(createTD("" )               + createTD("" )                             + createTD(internacionalesBanco) )
        );

        String tabla = "<table>\n" +
                "<tr>\n" + "<th>Transferencias BHD</th> \n"   + "<th>Transferencias BHD</th>\n" + "<th>Transferencias<br> internacionales</th>\n" + "    </tr>" +

                " <tr>\n" + "<td>"+bhdNombre+"</td>\n"       + "<td>"+interbancariaNombre+"</td>\n"                      + "<td>"+internacionalesNombre+"</td>\n"   + " </tr>" +
                " <tr>\n" + "<td>"+bhdTipoProducto+"</td>\n" + "<td>"+interbancariaIdentidad.split(numeroDocumento)[0]   + "<br>"+numeroDocumento+"</td>\n"  + "<td>"+internacionalesIdentidad.split(numeroDocumento)[0]+"<br>"+numeroDocumento+"</td>\n"+ " </tr>\n" +
                " <tr>\n" + "<td>"+bhdNumCuenta+"</td>\n"    + "<td>"+interbancarioTipoProducto+"</td>\n"                + " <td>"+internacionalesTipoProducto+"</td>\n"  + " </tr>\n" +
                " <tr>\n" + "<td>"+bhdCorreo+"</td>\n"       + "<td>"+interbancarioNumCuenta+"</td>\n"                   + "<td>"+internacionalesNumCuenta+"</td>\n"   + " </tr>\n" +
                " <tr>\n" + "<td>"+bhdBanco+"</td>\n"        + "<td>"+interbancarioMoneda+"</td>\n"                      + "<td>"+internacionalesMoneda+"</td>\n"   + " </tr>\n" +
                "\n" +
                " <tr>\n" + "<td></td>\n"                    + "<td>"+interbancarioCorreo+"</td>\n"                      + "<td>"+internacionalesCorreo+"</td>\n"   + " </tr>\n" +
                " <tr>\n" + "<td></td>\n"                    + "<td>"+interbancarioBanco+"</td>\n"                       + "<td>"+internacionalesCodigoSwift+"</td>\n"   + " </tr>\n" +
                " <tr>\n" + "<td></td>\n"                    + "<td></td>\n"                                             + "<td>"+internacionalesBanco+"</td>\n"   + " </tr>\n" +
                " </table>";
//BCBHDOSDXXX
//BCBHDOSDXXX
//BCBHDOSDXXX
        //accion.crearPaso(tabla, true,crearReporte,crearReporte);
        accion.crearPaso(tabla2, true,crearReporte,crearReporte);
        System.out.println(tabla);

        accion.clickOn(by( format(xpathLabelAliasCuenta, numeroCuenta) ),"BOTON COMPARTIR",1,getClass(),false,false);
    }


    //________________________________________________________________________
    private String sesionTC = "TARJETAS DE CRÉDITO";

    // Metodos para consultar Tarjeta de credito
    private void consultarTarjetaDeCredito(String alias, String numeroDeTC, String tipoDeTC, String estadoProducto, String balanceDisponiblePesos, boolean isInternacional, String balanceDisponibleDolar, boolean crearReporte){
        String formatoProducto = "Consulta de la Tarjeta de credito: %s  <br><br> " +
                                 "Alias del producto: %s <br> " +
                                 "Numero producto: %s <br> " +
                                 "Tipo de \t Tarjeta: %s <br> " +
                                 "Es      \t internacional: %s <br> " +
                                 "Estado de producto: %s <br> " +
                                 "%s %s <br> " +
                                 "%s %s";

        alias = accion.getText(by(format(XPA_LABEL_ALIAS, sesionTC, numeroDeTC, alias)),1,getClass(),false);
        numeroDeTC = accion.getText(by(format(XPA_LABEL_NUM_PRODUCTO, sesionTC, numeroDeTC,numeroDeTC )),1,getClass(),false);

        tipoDeTC = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionTC, numeroDeTC, tipoDeTC )),1,getClass(),false);
        estadoProducto = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionTC, numeroDeTC, estadoProducto)),1,getClass(),false);

        String labelDisponiblePesos  = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionTC, numeroDeTC, "Disponible en pesos:")),1,getClass(),false);
        balanceDisponiblePesos = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionTC, numeroDeTC, balanceDisponiblePesos )),1,getClass(),false);
        String labelDisponibleDolar = "";
        if (isInternacional){
            labelDisponibleDolar = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionTC, numeroDeTC, "Disponible en dólares:")),1,getClass(),false);
            balanceDisponibleDolar = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionTC, numeroDeTC, balanceDisponibleDolar )),1,getClass(),false);
        }else {
            balanceDisponibleDolar = "";
        }

        //balanceEnTransito = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, sesionCuentas, numeroDeTC, moneda+"$ " + balanceEnTransito)),1,getClass(),false);

        accion.mouseOver(by(format(xpathLinkVerDetalleProducto,sesionTC,numeroDeTC)), 0,getClass());
        accion.crearPaso(String.format(formatoProducto,numeroDeTC, alias, numeroDeTC, tipoDeTC, isInternacional, estadoProducto, labelDisponiblePesos,balanceDisponiblePesos,labelDisponibleDolar, balanceDisponibleDolar).replace("No disponible", "<span  style='color: red;'>No disponible.</span>").replace("No Disponible","<span style='color: red;'>No disponible.</span>"),true, crearReporte, crearReporte);
    }

    public void consultarTarjetaDeCredito(String numeroDeTarjetaCredito, boolean crearReporte){
        pantallaDashboard("",crearReporte);
        MsDashboardTarjetaCredito tc = new MsDashboardTarjetaCredito(numeroDocumento).buscar(numeroDeTarjetaCredito);
        desplegarSesionAll(sesionTC, crearReporte);
        consultarTarjetaDeCredito(tc.getAlias(), Utilidad.enmascararTC(tc.getNumeroProducto()) ,tc.getTipoProducto(), tc.getEstadoProducto(), tc.getBalancePesosConMoneda(), tc.isInternacional(), tc.getBalanceDolarMoneda(), crearReporte);
    }

    public void consultarTarjetasDeCretido(boolean crearReporte){
        pantallaDashboard("",crearReporte);
        MsDashboardTarjetaCredito tarjetas = new MsDashboardTarjetaCredito(numeroDocumento);
        desplegarSesionAll(sesionTC, crearReporte);

        for (int i = 0; i < tarjetas.getToltaProductos(); i++) {
            MsDashboardTarjetaCredito tc = tarjetas.iterarProductos(i);
            consultarTarjetaDeCredito(tc.getAlias(), Utilidad.enmascararTC(tc.getNumeroProducto()) ,tc.getTipoProducto(), tc.getEstadoProducto(), tc.getBalancePesosConMoneda(), tc.isInternacional(), tc.getBalanceDolarMoneda(), crearReporte);
        }
    }

    public void verDetalleDeTarjetaCredito(String numeroDeTarjetaCredito, boolean crearReporte){
        consultarTarjetaDeCredito(numeroDeTarjetaCredito, crearReporte);
        accion.clickOn(By.xpath(format(xpathLinkVerDetalleProducto,sesionTC,numeroDeTarjetaCredito)),"LINK ver detalle de producto",1, getClass(),false, crearReporte);
    }

    // ________________________________________________________________________________________

    protected void consultarCuenta(String numProduct, String alias,String tipo, String estado, String moneda, String balance, String balanceTransito,boolean crearReporte){
        String formatoProducto = "Consulta de cuenta: %s  <br><br> Nombre producto: %s <br> Numero producto: %s<br> Tipo de producto: %s <br> Estado de producto: %s <br> Balance Disponible: %s <br> Balance en transito: %s <br>";

        alias = accion.getText(by(format(XPA_LABEL_ALIAS, "CUENTAS",numProduct,alias)),1,getClass(),false);
        numProduct = accion.getText(by(format(XPA_LABEL_NUM_PRODUCTO, "CUENTAS",numProduct,numProduct)),1,getClass(),false);
        tipo = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, "CUENTAS",numProduct,tipo)),1,getClass(),false);
        estado = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, "CUENTAS",numProduct,estado)),1,getClass(),false);
        balance = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, "CUENTAS",numProduct,moneda+"$ " + balance)),1,getClass(),false);
        balanceTransito = accion.getText(by(format(XPATH_SPAN_COMUNES_CONTAINS, "CUENTAS",numProduct,moneda+"$ " +balanceTransito)),1,getClass(),false);
        accion.crearPaso(String.format(formatoProducto,numProduct, alias, numProduct, tipo, estado, balance, balanceTransito),true, crearReporte, crearReporte);
    }
    protected void detalleProducto( String sesion,String numeroProducto, boolean crearReporte){
        //pantallaDashboard("00111435384",crearReporte);
        //pantallaDashboard(Persistencia.getInstance().getDocumentNumber(getNombreCliente()), crearReporte);
        desplegarSesionAll( sesion, crearReporte );
        accion.clickOn(by(format(XPATH_DETALLE_PRODUCTO, sesion, numeroProducto)),"VER DETALLE PRODUCTO",1,getClass(),crearReporte,crearReporte);
        accion.isElementVisibleNoException(By.xpath("Espera"),10);
    }


    /**
     * @param sesion TARJETAS DE CRÉDITO, PRÉSTAMOS
     * @param productOrigen
     * @param productDestino
     * @param crearReporte
     */
    protected void modalPagar(String sesion,String productOrigen, String productDestino ,boolean crearReporte){
        pantallaDashboard(crearReporte);
        desplegarSesionAll( sesion, crearReporte);
        accion.clickOn(by(format(XPATH_BTN_PAGAR, sesion, productDestino)),"BOTON PAGAR",2, getClass(), crearReporte, crearReporte);
        accion.getText(tituloModalPagarProductOrigen, 2, getClass(), crearReporte);
        accion.selectDropdownIBP(comboProductOrigen, productOrigen,"ORIGEN",10, getClass(), crearReporte);
    }

    /**
     *
     * @param sesion TARJETAS DE CRÉDITO, TARJETA PREPAGO
     * @param numProducto
     * @param crearReporte
     */
    private void botonActivarTarjeta(String sesion, String numProducto, boolean crearReporte){
        pantallaDashboard(crearReporte);
        desplegarSesionAll( sesion, crearReporte);
        accion.clickOn(by(format(XPATH_ACTIVAR_TARJETA, sesion, numProducto)),"LINK ACTIVALO AQUI",10,getClass(),crearReporte,crearReporte);
    }

    protected static By tituloActivarTarjeta = By.xpath("//div[@role='dialog' and //img[@src='assets/img/renew-card.svg'] ] //div[img[@src='assets/img/modal-logo.svg']]/div[contains(.,'Activar tarjeta')]");
    private static By msgEsNecesariaLaTarjeta = By.xpath("//div[@role='dialog']//div[@class[contains(.,'ng-star-inserted')]]/div[contains(.,'Para la activación es necesario que tengas a mano tu nueva tarjeta de crédito correspondiente a la renovación')]");
    protected static By msgSiTienesOpcionActivar = By.xpath("//div[@role='dialog']//div[@class[contains(.,'ng-star-inserted')]]/div[contains(.,'Si tienes disponible la opción de \"Activar\"')]");
    protected static By btnSiLoTengo = By.xpath("//div[@role='dialog']//p-button[@ng-reflect-label='Sí, lo tengo']/button");
    /**
     * Param: numProducto
     */
    protected static final String XPATH_COMBO_PRODUCTO = "//p-dropdown[@name='productNumber']//span[contains(.,'%s')]";
    protected static By comboSolicitudesParaEsteProducto = By.xpath("//p-dropdown[@ng-reflect-placeholder='Solicitudes para este producto']//span[contains(.,'Activar tarjeta')]");
    protected static By txtMesAno = By.xpath("//div[label[contains(.,'Mes/año')] ]//input");

    protected void modalActivarTarjetaCredito(String numProducto, boolean siLoTengo,boolean crearReporte){
        botonActivarTarjeta("TARJETAS DE CRÉDITO", numProducto, crearReporte);
        accion.crearPaso(accion.getText(tituloActivarTarjeta,5,getClass(),false) +"<br><br>"+
                        accion.getText(msgEsNecesariaLaTarjeta,1,getClass(),false),
                crearReporte,crearReporte,crearReporte);
        accion.isElementVisible(msgSiTienesOpcionActivar,1,getClass());
            if (siLoTengo){
                accion.clickOn(btnSiLoTengo,"BOTON SI, LO TENGO",1,getClass(),false,crearReporte);
        }
    }

    protected void modalActivacionTCContinuar(String numProducto, String statusTC,String mesAno, boolean crearReporte){
        accion.isElementVisible(by(format(XPATH_COMBO_PRODUCTO,numProducto)),1,getClass());
        accion.isElementVisible(comboSolicitudesParaEsteProducto,1,getClass());
        accion.clickOn(txtMesAno, "CAMPO MES/AÑO",1,getClass(),false,crearReporte);

    }

    protected void botonCancelar(boolean crearReporte){
        accion.clickOn(btnCancelarModal, "BOTON CANCELAR",1, getClass(), crearReporte, crearReporte);
        accion.isElementInvisibility(tituloModalPagarProductOrigen, 3,getClass());
    }

    protected void consultarDivisas(boolean crearReporte){
        pantallaDashboard(crearReporte);
    }

    protected void consultarDetallePuestoBolsa(boolean crearReporte){
        pantallaDashboard(crearReporte);
        desplegarSesionAll("INVERSIONES PUESTO DE BOLSA",crearReporte);
        accion.isElementVisibleNoException(By.xpath("//espera"),20);
        accion.crearPaso("Patalla", true, true, true);
    }

    // Acceso Rapido
    private By listaAccesos = By.xpath("//div[div/h3[contains(.,'Accesos rápidos')] ] /div[em]");
    private void accederAcceso(String searchAcceso){
        String acces = "";
        for (WebElement acceso: accion.getAllElements(listaAccesos,15,getClass())){
            if (acceso.getText().contains(searchAcceso)){
                acces = searchAcceso;
                break;
            }
        }
        if (acces.equals("")){ // ConfigurarAccesp
        }else {// El acceso esta configurado. Hacer click

        }
    }

}
