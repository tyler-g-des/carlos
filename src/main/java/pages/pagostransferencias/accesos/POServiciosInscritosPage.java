package pages.pagostransferencias.accesos;

import accion.AccionMetodos;
import accion.AccionPage;
import basetest.BaseTest;
import data.Persistencia;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import servicios.micros.accesos.MicroServiciosInscritos;

import static com.bhd.ibp.pages.XpathComunes.*;
import static com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage.TXT_DESCRIPCION;

public class POServiciosInscritosPage extends AccionPage {

    private String numeroDocumento = "";
    public POServiciosInscritosPage(WebDriver webDriver, String numeroDocumento) {
        super(webDriver);
        this.numeroDocumento = numeroDocumento;
    }

    private By tituloDePagina = by("//h3[contains(.,'Servicios inscritos')]");
    private final By btnAgregarServicio = by("//div/button[span[contains(.,'Agregar servicio')]]");
    private final By tituloInscrbirSericioNuevo = by("//h3[contains(.,' Inscripción de nuevo servicio')]");
    private final By comboProveedorServicio = by("//div[label[contains(.,'Proveedor de servicios')] ]  /p-dropdown//span");
    private final By comboServicio = by("//div[label[contains(.,'Servicio')] ]  /p-dropdown//span");

    public static final By LABEL_REFERENCIA = by("//div[input[@id='reference']] /label");
    public static final By TXT_REFERENCIA = by("//input[@id='reference']");


    private void  pantallaListaServicios(boolean crearReporte){
        getText( tituloDePagina,20, getClass(), crearReporte );
    }

    private By cargaListaServicios = by("//div[@class='p-datatable-loading-overlay p-component-overlay ng-star-inserted']");


    /**
     * Este método permite llenar el formulario para un servicio.
     * @param proveedor Obligatorio -
     * @param servicio Obligatorio -
     * @param referencia Obligatorio -
     * @param descripcion Obligatorio -
     * @param crearReporte
     */
    public void setFormularioServicioReutilizar(String proveedor, String servicio, String referencia, String descripcion, boolean crearReporte){
        new AccionMetodos(driver).selectItemCombo( comboProveedorServicio, proveedor,"Proveedor de servicios",20, getClass(), crearReporte );
        new AccionMetodos(driver).selectItemCombo( comboServicio, servicio,"Servicio",1, getClass(), crearReporte);
        String labelReferencia = "";
        if (! referencia.equalsIgnoreCase("NA")){
            labelReferencia = getText(LABEL_REFERENCIA, 1, getClass());
        }

        new AccionMetodos(driver).writeOn(TXT_REFERENCIA, referencia, labelReferencia,1, getClass(), crearReporte );
        new AccionMetodos(driver).writeOn( TXT_DESCRIPCION, descripcion, "Descripción",1, getClass(), crearReporte);
    }

    /**
     * Este método permite llenar el formulario de un nuevo servicio.
     * @param proveedor
     * @param servicio
     * @param referencia
     * @param descripcion
     * @param crearReporte
     * @return
     */
    public POServiciosInscritosPage probarFormularioNuevoServicio(String proveedor, String servicio, String referencia, String descripcion, boolean crearReporte){
        pantallaListaServicios(crearReporte);
        new AccionMetodos(driver).waitElementInvisible( cargaListaServicios, 1, 5, getClass() );
        new AccionMetodos(driver).clickOn( btnAgregarServicio,"boton","Agregar servicio",2, getClass(),true,5, false, crearReporte );
        new AccionMetodos(driver).getText( tituloInscrbirSericioNuevo, 20, getClass(), crearReporte );
        setFormularioServicioReutilizar( proveedor, servicio, referencia, descripcion, crearReporte);
        return this;
    }

    /**
     * <h1>Editar boton editar con la consulta del micro de servicios.</h1>
     * Permite editar la información de un servicio inscrito y a su vez realizar las pruebas del formulario.
     * @param descricionServicioEditar
     * @param proveedor
     * @param servicio
     * @param referencia
     * @param descripcion
     * @param crearReporte
     */
    public POServiciosInscritosPage probarFormularioEditarServicio(String descricionServicioEditar, String proveedor, String servicio, String referencia, String descripcion, boolean crearReporte){

        By btnEditar = by("//tbody/tr[td[@id='let-description' and contains(.,'"+descricionServicioEditar+"')] ]  //div[button[@icon[contains(.,'edit')]]]");

        consultarServicioInscrito( proveedor, servicio, referencia, descricionServicioEditar );

        new AccionMetodos(driver).clickOn( btnEditar,  "boton", "Editar", 1, getClass(), crearReporte, crearReporte );
        getText( tituloEditarServicio, 25, getClass(), crearReporte );
        setFormularioServicioReutilizar( proveedor, servicio, referencia, descripcion, crearReporte );
        return this;
    }


    /**
     * Este método realiza click sobre el ícono eliminar del servicio levantando el modal de confirmación para la eliminación del servicio.
     * @param descripcion
     * @param crearReporte
     * @return
     */
    private POServiciosInscritosPage probarModalEliminarServicio(String descripcion, boolean crearReporte){
        MicroServiciosInscritos servic = new MicroServiciosInscritos().consultar(numeroDocumento).buscarServicio(descripcion);

        System.out.println("ID del servicio: " + servic.getId());

        By btnEliminar = by("//tbody/tr[ td[@id='let-serviceProvider' and contains(.,'"+servic.getProveedor()+"')]  and td[@id='let-affectedService'  and contains(.,'"+servic.getServicio()+"')]   and td[@id='let-referenceNumber' and contains(.,'"+servic.getReferencia()+"')]  and td[@id='let-description' and contains(.,'"+servic.getDescripcion()+"')] ]  //button[span[@class[contains(.,'delete')]]]");

        consultarServicioInscrito(descripcion);
        new AccionMetodos(driver).clickOn( btnEliminar, "Boton","Eliminar",2, getClass(), crearReporte, crearReporte);
        new AccionMetodos(driver).getText( MSG_SEGURO_DESEA_ELIMIAR_SERVICIO, 3, getClass(), crearReporte );
        new AccionMetodos(driver).waitElementVisible( MESSAGE_CONFIRMAR_OPERACION_MODAL, 0, getClass() );
        new AccionMetodos(driver).waitElementVisible( ICON_WARNNIG_MODAL, 0, getClass() );
        return this;
    }




    // ___________________________________________________________________________________________________


    private POServiciosInscritosPage probarModalTDC(boolean crearReporte){
        new AccionMetodos(driver).clickOn( BTN_GUARDAR,"boton","Guardar",1, getClass(), crearReporte, crearReporte);
        new AccionMetodos(driver).getText(MESSAGE_TDC_BENEFICIARIOS_SERVICIOS, 2, getClass(), crearReporte);
        new AccionMetodos(driver).writeMaskedOn(TXT_TDC, "1111","Tarjeta de claves",1, getClass(), crearReporte);
        //BaseTest.createStep("pantalla", true,true);
        return this;
    }

    private By iconoGuardadoCorrectamente = by("//div[@role='dialog' and //img[@src='assets/img/modal-logo.svg'] ] //em[@class[contains(.,'check-circle')]]");

    /**
     * <h1>Eliminar</h1>
     */
    private By messageRegistradoCorrectamente = by("//div[@role='dialog' and //img[@src='assets/img/modal-logo.svg'] ] // h3[contains(.,'El servicio ha sido registrado correctamente')]");

    /**
     *
     * @param mensajeModal Cuando se agregar un servicio nuevo: El servicio ha sido registrado correctamente <br>Cuando se actualiza un servicio: El servicio ha sido actualizado correctamente
     * @param crearReporte
     */
    private void completarTDC(String mensajeModal ,boolean crearReporte){
        By msgModal = by("//div[@role='dialog' and //img[@src='assets/img/modal-logo.svg'] ] // h3[contains(.,'"+mensajeModal+"')]");
        probarModalTDC(crearReporte);
        new AccionMetodos(driver).clickOn( BTN_CONTINUAR_MODAL,"boton","Continuar",1, getClass(),true,10, crearReporte, crearReporte);
        new AccionMetodos(driver).getText( msgModal, 10, getClass(), crearReporte );
        new AccionMetodos(driver).waitElementVisible( iconoGuardadoCorrectamente, 0, getClass() );
        new AccionMetodos(driver).clickOn( BTN_CONTINUAR_MODAL,"Boton","Continuar", 1, getClass(), crearReporte );
        //consultarServicioInscrito("");
    }

    public void consultarServicioInscrito(String descripcion){
        new AccionMetodos(driver).waitElementInvisible(cargaListaServicios, 1,10, getClass() );
        writeOn(by("//input[@placeholder='Buscar']"), descripcion,"Buscar",0, getClass(), true);
        By servicioInscrito = by("//tbody/tr[ td[@id='let-description' and contains(.,'"+descripcion+"')] ] ");
        new AccionMetodos(driver).getText( servicioInscrito,3, getClass(),false);
    }

    public void consultarServicioInscrito(String proveedor, String servicio, String referencia,String descripcion){
        new AccionMetodos(driver).waitElementInvisible(cargaListaServicios, 1,10, getClass() );
        writeOn(by("//input[@placeholder='Buscar']"), descripcion,"Buscar",0, getClass(), true);
        By servicioInscrito = by("//tbody/tr[ td[@id='let-serviceProvider' and contains(.,'"+proveedor+"')]  and td[@id='let-affectedService'  and contains(.,'"+servicio+"')]   and td[@id='let-referenceNumber' and contains(.,'"+referencia+"')]  and td[@id='let-description' and contains(.,'"+descripcion+"')] ]");
        new AccionMetodos(driver).getText( servicioInscrito,3, getClass(),false);
    }

    public void agregarServicio(String proveedor, String servicio, String referencia, String descripcion, boolean crearReporte){
        probarFormularioNuevoServicio( proveedor, servicio, referencia, descripcion, crearReporte )
                .completarTDC("El servicio ha sido registrado correctamente" ,crearReporte );
        pantallaListaServicios(crearReporte);
        consultarServicioInscrito(proveedor, servicio, referencia, descripcion);
        //consultarServicioInscrito( descripcion );
        BaseTest.createStep("Proveedor: " + proveedor + "<br>Servicio: " + servicio + "<br>Referencia: " + referencia + "<br>Descripcion: " + descripcion,true, crearReporte );
    }

    private final static By tituloEditarServicio = by("//h3[contains(.,'Edición de servicio inscrito')]");


    private void verificarDatosAntesDeEditar(String proveedor, String servicio, String referencia, String descripcion, boolean crearReporte ){
        By comboProveedores = by("//div[label[contains(.,'Proveedor de servicios')] ]  /p-dropdown//span[contains(.,'"+proveedor+"')]");
        By comboServicio = by("//div[label[contains(.,'Servicio')] ]  /p-dropdown//span[contains(.,'"+servicio+"')]");
        new AccionMetodos(driver).waitElementVisible(comboProveedores, 10, getClass() );
        new AccionMetodos(driver).waitElementVisible(comboServicio, 1, getClass() );
        System.out.println(getText(TXT_REFERENCIA,3, getClass() ));
    }

    public void editarServicio(String descripcionServicioEditar,String proveedor, String servicio, String referencia, String descripcion, boolean crearReporte){

        MicroServiciosInscritos msServicio = new MicroServiciosInscritos().consultar( numeroDocumento ).buscarServicio(descripcionServicioEditar);
        getText(tituloDePagina, 15, getClass(), crearReporte);

        probarFormularioEditarServicio( descripcionServicioEditar, proveedor, servicio, referencia, descripcion, crearReporte );
        completarTDC("El servicio ha sido actualizado correctamente", crearReporte);

        pantallaListaServicios(crearReporte);
        if (proveedor.equalsIgnoreCase("NA")){
            proveedor = msServicio.getProveedor();
        }
        if (servicio.equalsIgnoreCase("NA")){
            servicio = msServicio.getServicio();
        }

        if (referencia.equalsIgnoreCase("NA")){
            referencia = msServicio.getReferencia();
        }

        if (descripcion.equalsIgnoreCase("NA")){
            descripcion = msServicio.getDescripcion();
        }

        System.out.println("REferencia:: " + msServicio.getReferencia());


        //msServicio = new MicroServiciosInscritos().consultar(numeroDocumento).buscarId(msServicio.getId());


        //consultarServicioInscrito( descripcion );
        //consultarServicioInscrito( msServicio.getProveedor(), msServicio.getServicio(), referencia, descripcion);
        consultarServicioInscrito( proveedor, servicio, referencia, descripcion);
        BaseTest.createStep("Proveedor: " + proveedor + "<br>Servicio: " + servicio + "<br>Referencia: " + referencia + "<br>Descripcion: " + descripcion,true, crearReporte );
    }

    private final By MSG_SEGURO_DESEA_ELIMIAR_SERVICIO = by("//div[@role='dialog'] //h3[contains(.,'¿Estás seguro de que deseas eliminar este servicio?')]");

    public String eliminarServicio(String descripcionServicioAEliminar, boolean crearReporte){
        getText(tituloDePagina, 15, getClass(), crearReporte);
        probarModalEliminarServicio( descripcionServicioAEliminar, crearReporte );
        return botonConfirmarEliminacionDeServicio( crearReporte );
    }
    private String botonConfirmarEliminacionDeServicio(boolean crearReporte){
        By msgNoSeEncontraronRegistros = by("//td[contains(.,'No se encontraron registros para mostrar')]");
        new AccionMetodos(driver).clickOn( BTN_ELIMINAR_MODAL,"boton","Eliminar",0, getClass(), true,10,false, crearReporte );
        return new AccionMetodos(driver).getText( msgNoSeEncontraronRegistros, 2, getClass(), crearReporte );
    }
    // h3[contains(.,'El servicio ha sido registrado correctamente ')]
}
