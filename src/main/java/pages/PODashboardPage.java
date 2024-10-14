package pages;

import accion.AccionMetodos;
import accion.AccionPage;
import basetest.BaseTest;
import com.bhd.ibp.pages.pagostransferencias.POTransaccionesEntreProductosBHDOtrosBancosPage;
import com.bhd.ibp.pages.pagostransferencias.POTransferenciaEntreMisProductosPage;
import com.bhd.ibp.pages.pagostransferencias.POTransferenciasPinPesoPage;
import com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage;
import pages.pagostransferencias.accesos.POServiciosInscritosPage;
import data.Persistencia;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.configuracion.POConfiguracionPage;
import pages.login.POValidacionAccesoPage;
import pages.pagostransferencias.accesos.beneficiarios.POBeneficiariosInscritosPage;
import servicios.micros.MicroClientInfo;
import servicios.micros.dashboard.MicroDashboarDivisas;
import servicios.micros.dashboard.MicroDashboardCrediclickIBP;
import servicios.micros.dashboard.MicroDashboardPrestamosIBP;

import static com.bhd.ibp.pages.XpathComunes.BTN_GUARDAR;

public class PODashboardPage extends AccionPage {


    private String numeroDocumento;
    private String identificador = "";
    POValidacionAccesoPage pageValidacionAcceso;
    public PODashboardPage(WebDriver webDriver, POValidacionAccesoPage pageValidacionAcceso) {
        super(webDriver);
        this.pageValidacionAcceso = pageValidacionAcceso;
        numeroDocumento = pageValidacionAcceso.getNumeroDocumento();
        identificador = pageValidacionAcceso.getIdentificador();
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    // 360 - Resumen de Productos

    private final By titulo360 = By.xpath("//h3[contains(.,'360 - Resumen de Productos')]");
    private final String xpathLinkSesion = "//p-accordiontab[@ng-reflect-header[contains(.,'CUENTAS')]] //a"; // Nombre de Sesion
    private final String xpathLinkVerMas = "//p-accordiontab[@ng-reflect-header[contains(.,'CUENTAS')]] /div //SPAN[contains(.,'Ver más')]";

    /**
     * // nombreSesion | numeroProducto| P
     */
    private String xpathParrafosProductos = "//p-accordiontab[.//span[contains(.,'%S')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] /div/p[contains(.,'%s')]";
    private String xpathStrongProductos = "//p-accordiontab[.//span[contains(.,'%s')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //strong[contains(.,'%s')]";
    private String xpathSpanProductos = "//p-accordiontab[.//span[contains(.,'%S')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //span[contains(.,'%s')]";

    /**
     * nombreSesion-NumeroProducto-labelText-moneda+monto
     */
    private final String xp_SpanLabelMonto = "//p-accordiontab[.//span[contains(.,'%s')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]]  //div[.//span[contains(text(), '%s')]]//span[contains(text(), '%s')]";

    /**
     * nombreSesion-numProducto-labelTextoSpan-moneda-balanceSpan
     */
    private final String xpathSpanBalances = "//p-accordiontab[@ng-reflect-header[contains(.,'%s')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //div[.//span[contains(text(), '%s')]]//span[contains(text(), '%s$ %s')]";
    private String xpathbotonReflectlabel = "//p-accordiontab[@ng-reflect-header[contains(.,'%S')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //span[contains(.,'%s')]";

    /**
     * Monto divisa-nombreLabelDivisa. Ejemplo: Param1 = "60.55", Param2 = "Compra Euro". <br><br>
     *  monto - Compra Dólar, <br>monto - Venta Dólar, <br>monto - Compra Euro, <br>monto - Venta Euro
     */
    private final String xpCarruCerDivisas = "//div[div[h3[contains(.,'Divisas')]]] //p-carousel //div[@class[contains(.,'p-carousel-item ng-star-inserted p-carousel-item-active')]] /div/div[h4[contains(.,'%s')] and h5[contains(.,'%s')]]";

    /**
     * monto+Compra Dólar y monto + Venta Dólar.
     * monto+Compra Euro y monto + Venta Euro.
     */
    private final String xpCarruselDivisas2 = "//div[div[h3[contains(.,'Divisas')] ]] //p-carousel //div[@class[contains(.,'p-carousel-item ng-star-inserted p-carousel-item-active ')]]/div[  div[h4[contains(.,'%s')] and h5[contains(.,'%s')]]  and div[h4[contains(.,'%s')] and h5[contains(.,'%s')]]]";
    private By btnSiguienteCarruserl = by("//div[div[h3[contains(.,'Divisas')]]] //p-carousel //button[@class[contains(.,'p-carousel-next')]]");


    private final By btnCloseModalBloqueante = By.xpath("//div[@role='dialog']//button[em[@class[contains(.,'action-close')] ]]");
    public String getTituloResumen360(boolean verificarModalEnDashboard, boolean crearReporte){
        Persistencia.getInstance().consultarDashboardPublicidad(numeroDocumento);
        String tituloResument360 = getText(titulo360,15, getClass(), crearReporte);

        if (verificarModalEnDashboard){
            if (Persistencia.getInstance().getPublicidad(numeroDocumento).contienePublicidad()){
                new AccionMetodos(driver).clickOn(btnCloseModalBloqueante, "Boton modal","Aviso",10,getClass(),true,  false);
            }
        }
        return tituloResument360;
    }

    private By btnConfiguracion = by("//ibp-header-buttons//div[@routerlink='configurations']//p-avatar/div");


    /**
     * Verifica que se este mostrando el nombre del cliente en el Dashboard.
     * @param crearReporte
     * @return
     */
    public String consultarNombreCliente(boolean crearReporte){
        getTituloResumen360(true, false);
        By labelNombreCliente = by("//ibp-header //p[contains(.,'Bienvenido(a)') and //span[contains(.,'"+Persistencia.getInstance().getClientInfo(numeroDocumento).getNombreCompletoTitleCase()+"')] ] ");
        return getText(labelNombreCliente,2, getClass(), crearReporte);
    }

    /**
     * Verifica que se muestre el nombre y el correo del cliente en el icono perfil de usuario.
     * @param crearReporte
     */
    public void consultarNombreCorreoEnPerfilDeUsuario(boolean crearReporte){
        desplegarPerfilDeUsuario( crearReporte );
        MicroClientInfo info = Persistencia.getInstance().getClientInfo(numeroDocumento);
        By labelsNombreUsuarioCorreoUsuario = by("//div[@role='dialog'] //div[p[contains(.,'"+info.getNombreCompletoTitleCase()+"')]  and p[contains(.,'"+info.getCorreo()+"')]]");
        waitElementVisible( labelsNombreUsuarioCorreoUsuario, 1, getClass());
        crearPaso("Nombre: " +info.getNombreCompletoTitleCase() + "<br>Correo: " + info.getCorreo(),true, crearReporte, crearReporte);
    }

    public POConfiguracionPage configuracion(boolean crearReporte){
        getTituloResumen360(false, false);
        clickOn(btnConfiguracion, "Boton","Connfiguracion",2,false, getClass(), crearReporte, crearReporte);
        return new POConfiguracionPage(driver);
    }



    public void consultarCarouselDeDivisas(boolean crearReporte){
        MicroDashboarDivisas dv = MicroDashboarDivisas.getInstance().consultar(numeroDocumento);
        getTituloResumen360(true, crearReporte);

        clickOn(btnSiguienteCarruserl, "boton","Siguiente Carrrusel",20,false, getClass(), crearReporte, crearReporte);
        esperarSegundos(1);

        waitElementVisible(by(xpCarruselDivisas2, dv.getCompraEuroLabel(), "Compra Euro", dv.getVentaEuroLabel(), "Venta Euro"),0, getClass());
        crearPaso("Compra Euro: " + dv.getCompraEuroLabel() + "<br>Venta Euro: " + dv.getVentaEuroLabel(), true, crearReporte, crearReporte);

        clickOn(btnSiguienteCarruserl, "boton","Siguent Carrrusel",0,false, getClass(),false, true);
        esperarSegundos(1);

        waitElementVisible(by(xpCarruselDivisas2, dv.getCompraDolarLabel(), "Compra Dólar", dv.getVentaDolarLabel(), "Venta Dólar"),1, getClass());
        crearPaso("Compra Dólar: " + dv.getCompraDolarLabel() + "<br>Venta Dólar: " + dv.getVentaDolarLabel(), true, crearReporte, crearReporte);
    }

    private By listaAccesoRapido = By.xpath("//ibp-quick-access/div/div/div[//h3[contains(.,'Accesos rápidos')]]/div[@tabindex='0']");
    private By btnEditarCategorio = By.xpath("//ibp-quick-access/div/div/div[//h3[contains(.,'Accesos rápidos')]] //a[contains(.,'Editar categorías')]");
    private By btnGuardarCategoria = by("//ibp-quick-access/div/div/div[//h3[contains(.,'Editar accesos rápidos')]] //a[contains(.,'Guardar')]");
    private By tituloEditarAccesoRapidos = by("//ibp-quick-access/div/div/div[//h3[contains(.,'Editar accesos rápidos')]] //h3[contains(.,'Editar accesos rápidos')]");
    private By tituloAccesoRapido = By.xpath("//ibp-quick-access/div/div/div[div[@tabindex=\"0\" and @class[contains(.,'inserted')] ]] /div/h3[contains(.,'Accesos rápidos')]");

    private final By comboAcceso1 = By.xpath("//ibp-quick-access/div/div/div[//h3[contains(.,'Editar accesos rápidos')]]  /form/div/div[2]");


    private final By btnPerfilUsuario = by("//ibp-header-buttons[@class[contains(.,'inserted')] ]//div[@class[contains(.,'bhd-icons-header')]]//p-avatar/div");
    private final By btnPerfilUsuario2 = by("//ibp-header-buttons//div[@class[contains(.,'bhd-icons-header')]]//p-avatar/div");
    private final By linkSolicitudesReclamaciones = by("//a[span[contains(.,'Solicitudes y Reclamaciones')]]");
    private final By linkOfertas = by("//a[span[contains(.,'Ofertas')]]");
    private final By linKMisProductos = by("//a[span[contains(.,'Mis Productos')]]");
    private final By linkPagosTransferencias = by("//a[span[contains(.,'Pagos y Transferencias')]]");

    private final By btnAmburguesa = by("//a[@class[contains(.,'p-menubar-button')]]");

    private void desplegarBotonAmburguesa(boolean crearReporte){
        if (isElementVisible( btnAmburguesa, 1)){
            clickOnVisible( btnAmburguesa, "boton","Amburguesa",0, getClass(),false, crearReporte);
        }
    }

    public void navSolicitudesReclamaciones(boolean crearReporte ){
        desplegarBotonAmburguesa(crearReporte);
        clickOnVisible( linkSolicitudesReclamaciones, "link","Solicitudes y Reclamaciones",0, getClass(),false, crearReporte);
        esperarSegundos(10);
    }

    public void navOfertas(boolean crearReporte ){
        getTituloResumen360(false, false);
        desplegarBotonAmburguesa(crearReporte);
        clickOnVisible( linkOfertas, "link","Ofertas",0, getClass(),false, crearReporte);
        esperarSegundos(10);
    }

    public void navMisProductos(boolean crearReporte ){
        getTituloResumen360(false, false);
        desplegarBotonAmburguesa(crearReporte);
        clickOnVisible(linKMisProductos, "link","Mis Productos",0, getClass(),false, crearReporte);
        esperarSegundos(10);
    }

    public void navPagosTransferencias(boolean crearReporte ){
        getTituloResumen360(false, false);
        desplegarBotonAmburguesa(crearReporte);
        clickOnVisible(linkPagosTransferencias, "link","Mis Productos",0, getClass(),false, crearReporte);
        esperarSegundos(10);
    }



    public void desplegarPerfilDeUsuario(boolean crearReporte){
        getTituloResumen360(false, crearReporte);
        clickOnVisible( btnPerfilUsuario2,"","",10, getClass(), crearReporte, crearReporte);
    }

    public POTransferenciasRegionalesSIPAPage leerAccesosRapidos( boolean crearReporte ){

        getTituloResumen360(true, crearReporte );

        By linkAccesoRapido = by("//ibp-quick-access/div/div/div[//h3[contains(.,'Accesos rápidos')]]/div[@tabindex='0' and contains(.,'Transacciones entre mis productos')]");
        if (! clicnOnElementContainsText( listaAccesoRapido, "Transacciones entre mis productos", "Link",15, getClass(), crearReporte ) ){
            clickOn( btnEditarCategorio,"Link","Editar categorías",10,true, getClass(),false, crearReporte );
            esperarSegundos(1);
            getText( tituloEditarAccesoRapidos,3, getClass(), crearReporte);
            selectItemComboFinales( comboAcceso1, "Transacciones entre mis productos","Elemento",1, getClass(),true, crearReporte );

            clickOn( btnGuardarCategoria,"Link","Guardar",2,true, getClass(), crearReporte, crearReporte );
            clickOn( linkAccesoRapido,"Link","Transacciones entre mis productos",50,true, getClass(), crearReporte, crearReporte );
        }
        return new POTransferenciasRegionalesSIPAPage( driver, this );
    }

    private void seleccionarComboCategoria(int indiceCombo, String nombreAccesoRapido, boolean crearReporte){
        By combo = by("//ibp-quick-access/div/div/div[//h3[contains(.,'Editar accesos rápidos')]]  /form/div/div["+(1+indiceCombo)+"]//span");
        By listaDeCombo = by("//ibp-quick-access/div/div/div[//h3[contains(.,'Editar accesos rápidos')]]  /form/div/div[p-dropdown]//span");
        //String listaCombo = "//ibp-quick-access/div/div/div[//h3[contains(.,'Editar accesos rápidos')]]  /form/div/div[p-dropdown]//span%s";

        String opcionActual = getText( combo,2, getClass());
        if ( ! opcionActual.contains( nombreAccesoRapido )){
            for (WebElement comboActual : getListaElementos( listaDeCombo, 1, getClass() ) ){
                if ( comboActual.getText().contains( nombreAccesoRapido )){
                    selectItemComboFinales( comboActual,"s","Combo Categoria: " + indiceCombo,2, getClass(),true, false);
                }
            }
            selectItemComboFinales( combo, nombreAccesoRapido,"Combo Categoria: " + indiceCombo,2, getClass(),true, crearReporte);
        }
    }

    public PODashboardPage editarAccesosRapidos(String acceso1, String acceso2, String acceso3, String acceso4, String acceso5, boolean crearReporte){
        getTituloResumen360(true, false );


        waitElementVisible( listaAccesoRapido, 25, getClass() );
        getText( tituloAccesoRapido,20 ,getClass(), crearReporte );

        clickOn( btnEditarCategorio,"Link","Editar categorías",10,true, getClass(),false, crearReporte );
        esperarSegundos(1);
        getText( tituloEditarAccesoRapidos,3, getClass(), crearReporte);

        seleccionarComboCategoria( 1, acceso1, crearReporte );
        seleccionarComboCategoria( 2, acceso2, crearReporte );
        seleccionarComboCategoria( 3, acceso3, crearReporte );
        seleccionarComboCategoria( 4, acceso4, crearReporte );
        seleccionarComboCategoria( 5, acceso5, crearReporte );

        clickOn( btnGuardarCategoria,"Link","Guardar",2,true, getClass(), crearReporte, crearReporte );
        getText( tituloAccesoRapido,25 ,getClass(), crearReporte );
        return this;
    }

    public String getNombreAccesoRapido(int indice){
        return getText(by("//ibp-quick-access/div/div/div[//h3[contains(.,'Accesos rápidos')]]/div[@tabindex='0']["+indice+"]"),1, getClass());
    }

    private void navegarAccesoRapido(String nombreAccessoRapido, boolean crearReporte){
        getTituloResumen360(true, false );

        getText( tituloAccesoRapido,25 ,getClass(), crearReporte );
        waitElementVisible( listaAccesoRapido, 1, getClass() );


        By linkAccesoRapido = by("//ibp-quick-access/div/div/div[//h3[contains(.,'Accesos rápidos')]]/div[@tabindex='0' and contains(.,'"+nombreAccessoRapido+"')]");

        if (! clicnOnElementContainsText( listaAccesoRapido, nombreAccessoRapido, "Link",15, getClass(), crearReporte ) ){
            clickOn( btnEditarCategorio,"Link","Editar categorías",10,true, getClass(),false, crearReporte );
            esperarSegundos(1);
            getText( tituloEditarAccesoRapidos,3, getClass(), crearReporte);
            selectItemComboFinales( comboAcceso1, nombreAccessoRapido, "Categoria",1, getClass(),true, crearReporte );

            clickOn( btnGuardarCategoria,"Link","Guardar",2,true, getClass(), crearReporte, crearReporte );
            clickOn( linkAccesoRapido,"Link","Transacciones entre mis productos",50,true, getClass(), crearReporte, crearReporte );
        }

        //esperarSegundos(10);
    }



    public void accesoRapidoRecargaBillet(boolean crearReporte){
        navegarAccesoRapido( "Recarga Billet", crearReporte);
    }

    public POTransferenciaEntreMisProductosPage accesoRapidoTransaccionesEntreMisProductos(boolean crearReporte){
        navegarAccesoRapido( "Transacciones entre mis productos", crearReporte);
        return new POTransferenciaEntreMisProductosPage(driver);
    }

    public void accesoRapidoHistóricTransacciones(boolean crearReporte){
        navegarAccesoRapido( "Histórico de transacciones", crearReporte);
    }

    public void accesoRapidoPagosMultiples(boolean crearReporte){
        navegarAccesoRapido( "Pagos múltiples", crearReporte);
    }

    public POBeneficiariosInscritosPage accesoRapidoBeneficiariosInscritos(boolean crearReporte){
        navegarAccesoRapido( "Beneficiarios inscritos", crearReporte);
        return new POBeneficiariosInscritosPage(driver);
    }

    public POServiciosInscritosPage accesoRapidoServiciosInscritos(boolean crearReporte){
        navegarAccesoRapido( "Inscripción de nuevo servicio", crearReporte);
        return new POServiciosInscritosPage(driver, numeroDocumento);
    }

    public void accesoRapidoPagoServiciosImpuestos(boolean crearReporte){
        navegarAccesoRapido( "Pago de servicios e impuestos", crearReporte);
    }

    public POTransferenciasPinPesoPage accesoRapidoTransaccionesPINPesos(boolean crearReporte){
        System.out.println("Driver en dashboard: " + driver);
        navegarAccesoRapido( "Transacciones PIN Pesos", crearReporte);
        return new POTransferenciasPinPesoPage(driver,this);
    }

    public POTransaccionesEntreProductosBHDOtrosBancosPage accesoRapidoTransaccionesProductosBHDyAOtrosBancos(boolean crearReporte){
        navegarAccesoRapido( "Transacciones entre productos BHD y a otros bancos", crearReporte);
        return new POTransaccionesEntreProductosBHDOtrosBancosPage(driver);
    }

    public void accesoRapidoTransaccionesFuturasRecurrentes(boolean crearReporte){
        navegarAccesoRapido( "Transacciones futuras y recurrentes", crearReporte);
    }

    public void accesoRapidoTransferenciaInternacional(boolean crearReporte){
        navegarAccesoRapido( "Transferencia internacional", crearReporte);
    }

    public POTransferenciasRegionalesSIPAPage accesoRapidoTransferenciasRegionalesSIPA(boolean crearReporte ){
        navegarAccesoRapido( "Transferencias Regionales SIPA", crearReporte );
        return new POTransferenciasRegionalesSIPAPage( driver,  this);
    }









    private String desplegarSesion(String nombreSesion, boolean crearReporte){
        By linkSesion = by("//p-accordiontab[.//span[contains(.,'"+nombreSesion+"')]]//a");
        By cargaSesion = By.xpath("//p-accordiontab[.//span[contains(.,'"+nombreSesion+"')]]  //div[@role='region'] //*[contains(@class, 'inserted')] //strong");
        By linkVerMas = by("//p-accordiontab[.//span[contains(.,'"+nombreSesion+"')]] /div //SPAN[contains(.,'Ver más')]");

        if (getAttributeValue(linkSesion,"aria-expanded",20, getClass()).equalsIgnoreCase("false")){
            clickOn( linkSesion,"Link","Sesion " + nombreSesion,1,false, getClass(), crearReporte, crearReporte);
        }
        waitElementVisible(cargaSesion, 20, getClass());
        System.out.println("Sesion cargada");

        if (isElementVisible(linkVerMas,0)){
            System.out.println("Es visible ");
            clickOn(linkVerMas,"Link","Ver más",0,false, getClass(),false, crearReporte);
        }
        return nombreSesion;
    }

    private void consultarCuenta(boolean crearReporte){
        String body = "Nombre producto: %s <br>Número de producto: %s <br>Estado de producto: %s<br>Balance disponible: %s<br>Balance en tránsito: %s";
    }

    public void consultarCuentas(boolean crearReporte){
        desplegarSesion("CUENTAS", crearReporte);
    }

    private void consultarCuenta(String sesion, String microCuenta, boolean crearReporte){

//        waitElementVisible( by(xpathStrongProductos, sesion, numProd, nombreProduct), 20, getClass());
//        waitElementVisible( by(xpathParrafosProductos, sesion, numProd, numProd), 20, getClass() );
//        waitElementVisible( by(xpathSpanProductos, sesion, numProd, estado ),0, getClass());

    }

    private void desplegarSesionCrediclick(boolean crearReporte){
        desplegarSesion("CREDICLICK", crearReporte);
    }

    public int consultarCrediclick(boolean crearReporte){

        String bodyNoDesembolsado = "Nombre producto: %s <br>Número de producto: %s <br>Estado de producto: %s<br>Monto aprobado: %s";
        String bodyDesembolsado = "Nombre producto: %s <br>Número de producto: %s <br>Estado de producto: %s <br>Balance disponible: %s <br>Balance: %s <br>Cuota mensual: %s ";

        Persistencia.getInstance().setMicroDashboardCrediclick(numeroDocumento);
        MicroDashboardCrediclickIBP credi = Persistencia.getInstance().getDashboardCrediclick(numeroDocumento);
                //MicroDashboardCrediclickIBP.getInstance().consultar( pageValidacionAcceso.getNumeroDocumento() );


        if (credi.getStatusCode() == 200){

            desplegarSesionCrediclick( crearReporte );

            String nombreProduct = credi.getNombreActualDelProducto();
            String numProd = credi.getNumeroProducto();
            String estado = credi.getEstadoProducto();
            String disponible = credi.getBalanceDisponibleLabel();
            String balance = credi.getBalanceLabel();
            String cuotaMensual = credi.getCuotaMensualLabel();

            waitElementVisible( by(xpathStrongProductos, "CREDICLICK", numProd, nombreProduct), 20, getClass());
            waitElementVisible( by(xpathParrafosProductos, "CREDICLICK", numProd, numProd), 20, getClass() );
            waitElementVisible( by(xpathSpanProductos, "CREDICLICK", numProd, estado ),0, getClass());

            desplegarSesion("CREDICLICK", crearReporte);

            if (credi.isDesembolsado()){

                waitElementVisible( by( xp_SpanLabelMonto, "CREDICLICK", numProd, "Balance disponible", "RD$ " + disponible ) ,1, getClass());
                waitElementVisible( by( xp_SpanLabelMonto, "CREDICLICK", numProd, "Balance:", "RD$ " + balance ) ,1, getClass());
                waitElementVisible( by(xp_SpanLabelMonto, "CREDICLICK", numProd, "Cuota mensual", "RD$ " + cuotaMensual ),0, getClass());

                crearPaso(String.format( bodyDesembolsado, nombreProduct, numProd, estado, disponible, balance, cuotaMensual),
                        true, crearReporte, crearReporte);
            }else {
                waitElementVisible(by(xp_SpanLabelMonto, "CREDICLICK","11268188","Monto aprobado", credi.getMontoAprobadoLabel() ),0, getClass());
                crearPaso(String.format(bodyNoDesembolsado, nombreProduct, numProd, estado, credi.getMontoAprobadoLabel() ),true, crearReporte, crearReporte);
            }
        }else {
            BaseTest.createStep("Dashboard de crediclick no esta cargando o el usuario no contiene el producto: " + credi.getStatusCode(), false, crearReporte);
        }
        return credi.getStatusCode();
    }

    public void consultarDestalleCrediclick(boolean crearReporte){
        consultarCrediclick(crearReporte);
        MicroDashboardCrediclickIBP credc = Persistencia.getInstance().getDashboardCrediclick(numeroDocumento);

        if (credc.getStatusCode() == 200){
            clickOn( by(xpathParrafosProductos, "CREDICLICK", credc.getNumeroProducto(), credc.getNumeroProducto()), "Boton", "Link " + credc.getNumeroProducto(),1,false, getClass(), crearReporte, crearReporte );
            waitElementVisible(by("//h3[contains(.,'Detalle de mis productos')]"),10,getClass());

            crearPaso("Pantalla", true, true, true);
        }
    }

    /**
     * Nombre de sesion
     * Numero de producto
     */
    private final String btnEditar = "//p-accordiontab[.//span[contains(.,'%S')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //button[@icon[contains(.,'edit')]]";
    public static final By TITULO_ALIAS = By.xpath("//div[@role='dialog'] //div[img[@src='assets/img/modal-logo.svg'] ]/div[contains(.,'Ingrese el nuevo alias')]");

    public static final By txtAlias = By.xpath("//input[@id='alias']");
    private PODashboardPage desplegarModalEditarAlias(String sesion, String numeroProducto, String nuevaAlias, boolean crearReporte){
        getTituloResumen360(true, crearReporte);
        desplegarSesion( sesion, crearReporte );
        new AccionMetodos(driver).clickOn(by(btnEditar, sesion, numeroProducto),"Icono","Editar",1, getClass(), crearReporte, crearReporte);
        new AccionMetodos(driver).getText( TITULO_ALIAS,2, getClass(), crearReporte );
        new AccionMetodos(driver).writeOn( txtAlias, nuevaAlias,"Alias",0, getClass(), crearReporte );
        return this;
    }

    /**
     * Sesion
     * NumProducto
     * Alias
     */
    private final String xpLabelAlias = "//p-accordiontab[.//span[contains(.,'%S')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //strong[contains(.,'%s')]";
    private void guardarAlias(String nombreSesion, String numProducto, String alias, boolean crearReporte){
        new AccionMetodos( driver ).clickOn( BTN_GUARDAR, "Boton", "Guardar", 0, getClass(),true, 25, crearReporte, crearReporte );
        //labelProductosFinal
        new AccionMetodos(driver).clickOn(by( xpLabelAlias, nombreSesion, numProducto, alias),"Label","Alias",1, getClass(),false,false);
    }

    public void editarAliasCuenta(String numProducto, String nuevaAlias,boolean crearReporte){
        desplegarModalEditarAlias("CUENTAS", numProducto, nuevaAlias, crearReporte );
        guardarAlias("CUENTAS", numProducto, nuevaAlias, crearReporte);
    }



    // RESUMEN DE PRODUCTOS 👇

    // CUENTAS -> CONSULTA PRODUCTOS


    // CUENTAS INACTIVAS

    private final By btnVerProductos = by("//div[@role='dialog'] //button[span[contains(.,'Ver productos')]]");
    private final By tituloProductosInactivosLogoBanco = by("//div[@role='dialog'] //div[  div[contains(.,'Productos inactivos')] and img[@src='assets/img/modal-logo.svg']]");
    private String modalCuentasInactivas(boolean clickEnBotonVerProductos, boolean crearReporte){
        getTituloResumen360(true, crearReporte);
        By imgLogobanco = by("//div[@role='dialog']//img[@src='assets/img/modal-logo.svg']");
        By imgAlerta = by("//div[@role='dialog']//img[@src='assets/img/alert-triangle.svg']");
        By mensajeModal = by("//div[@role='dialog'] //div[h2[contains(.,'Tienes productos inactivos')] and p[contains(.,'Las cuentas inactivas no pueden recibir depósitos ni realizar transacciones.')]]");
        By tituloProductosInactivos = by("//div[@role='dialog'] //div[  div[contains(.,'Productos inactivos')] and img[@src='assets/img/modal-logo.svg']]");

        waitElementVisible( imgLogobanco, 5, getClass() );
        waitElementVisible( imgAlerta, 0, getClass());
        crearPaso( getText( mensajeModal, 0, getClass(), false ).replace("inactivos","inactivos<br>"),true, crearReporte, crearReporte );
        waitElementVisible( btnVerProductos, 0, getClass() );

        if (clickEnBotonVerProductos){
            clickOn( btnVerProductos, "boton", "Ver productos",0,false, getClass(), false, crearReporte);
            getText( tituloProductosInactivos, 1, getClass(), crearReporte);
        }
        return numeroDocumento;
    }

    public String verificarModalTienesProductosInactivos(boolean crearReporte ){
        return modalCuentasInactivas(false, crearReporte );
    }

    /**
     * <h1>Falta iterar productos</h1>
     * @param crearReporte
     * @return
     */
    public String verificarModalProductosInactivos(boolean crearReporte){
        return modalCuentasInactivas(true, crearReporte);
    }


    private By labelMontoAcumuladoAFP = by("//p-accordiontab[.//span[contains(.,'CUENTA DE PE')]] //ibp-balance[//span[contains(.,'Acumulado:')] and //span[contains(.,'RD$ 3,410,364.71')]]");
    private By labelFechaFondo = by("//p-accordiontab[.//span[contains(.,'CUENTA DE PE')]] //div[ibp-balance]/div [//span[contains(.,'Fecha fondo:')] and //span[contains(.,'11/09/2024')] ]");

    // RESUMEN DE PRODUCTOS 👆




    // CENTRO FINANCIERO BHD

    private final By imgSegurosMafre = by("//div[@class[contains(.,'card')]] /div[h3[contains(.,'Centro Financiero BHD')]] /div [div/strong[contains(.,'Seguros MAPFRE')]] //img[@src='assets/img/financial-mapfre.svg']");

    private final String xpthBalance = "";
    private String labelBalanceCrediclick = "";

    /**
     * NombreSesion <br>
     * Numero de producto <br>
     * Label actual <br>
     * Monto del label <br>
     */
    private final String labelProductosFinal = "//p-accordiontab[.//span[contains(.,'%S')]] //div[@class[contains(.,'p-grid')] and div/p[contains(text(),'%s')]] //div[div/span[contains(.,'%s')] and div/span[contains(.,'%s')] ]";

    public void consultarPrestamo(String numeroProducto, boolean crearReporte){
        MicroDashboardPrestamosIBP prestamo = new MicroDashboardPrestamosIBP().nuevaConsulta( numeroDocumento, Persistencia.getInstance().getClientInfo(numeroDocumento).getCustomerCodeT24()).buscar(numeroProducto);
        getTituloResumen360(true, false);
        desplegarSesion("PRÉSTAMOS", crearReporte );
        System.out.println("NumeroProducto: " + numeroProducto + " Balance: " + prestamo.getBalanceLabel());

        getText(by( labelProductosFinal, "PRÉSTAMOS", numeroProducto, "Balance:", prestamo.getBalanceLabel() ), 10, getClass(), crearReporte);
        //waitElementVisible( by( labelProductosFinal, "PRÉSTAMOS", numeroProducto, "Balance:", prestamo.getBalanceLabel() ),30, getClass() );
        //waitElementVisible( by( labelProductosFinal, "PRÉSTAMOS", numeroProducto, "Pendiente al corte", prestamo.getPendienteAlCorteLabel() ),0, getClass() );

    }







}
