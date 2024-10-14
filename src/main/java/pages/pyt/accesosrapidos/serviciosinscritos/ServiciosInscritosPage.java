package pages.pyt.accesosrapidos.serviciosinscritos;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.PoAccionesRepetitivas;
//import pages.accesosrapidos.beneficiariosInscritos.BeneficiariosInscritosXpath;
//import pages.accionesrepetitivas.AccionRepetitivaLogic;

import static java.lang.String.format;
import static microservicios.Utilidad.by;
import static pages.XpathComunes.BTN_CONTINUAR;
//import static pages.accesosrapidos.serviciosinscritos.ServiciosInscritosXpath.btnContinuar;
//import static pages.accesosrapidos.serviciosinscritos.ServiciosInscritosXpath.tituloPagina;
//import static pages.pagostransferencias.PagosTransferenciasXpath.TXT_DESCRIPCION;
//import static pages.pagostransferencias.PagosTransferenciasXpath.txtBuscar;
import static pages.pyt.XpathTransacciones.TXT_DESCRIPCION;
import static pages.pyt.accesosrapidos.beneficiariosinscritos.XpathBeneficiarios.*;
import static pages.pyt.accesosrapidos.serviciosinscritos.ServiciosInscritosXpath.*;

/**
 * Carlos Loyola Tejeda
 *
 * @author: Carlos A. Loyola Tejeda
 * @Date 22/11/2023 11:44 a. m.
 * 2023
 * @Email carlos_loyola@bhd.com.do
 * BHDL_AutomatizacionMigracionIBP
 */
public class ServiciosInscritosPage extends BasePage {

    private final Accion accion;
    private ServiciosInscritosPage(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    /**
     * Retorna una instancia de la clase Page ServiciosInscritosPage
     * @return ServiciosInscritosPage
     */
    public static ServiciosInscritosPage getPage(){
        return new ServiciosInscritosPage(BaseTest.getDriver());
    }

    /** Permite llenar el formulario cuando se quiere editar o crear un servicio nuevo.
     * @param proveedorServicio que se inscribirá - Obligatorio.
     * @param servicio a seleccionar, este depende del proveedor seleccionado - Obligatorio.
     * @param referencia del servicio a inscribir - Obligatorio.
     * @param descripcion del servicio a inscribir - Obligatorio.
     * @param crearReporte Crear o no reporte del paso a paso.
     */
    public void setFormulario(String proveedorServicio, String servicio, String referencia, String descripcion, boolean crearReporte){
        accion.selectDropdownIBP( comboProveedorServicios, proveedorServicio,"PROVEEDOR DE SERVICIO",10, getClass(), crearReporte);
        accion.selectDropdownIBP( comboServicio, servicio,"SERVICIO",3, getClass(), crearReporte);
        setReferencia( referencia, crearReporte );
        accion.writeOn(TXT_DESCRIPCION, descripcion, "DESCRIPCION",3, getClass(), crearReporte);
    }

    /** Llena el campo referencia tomando el titulo de la misma para el reporte, ya que esta puede cambiar dependiendo del servicio seleccionado.
     * @param referencia  del servicio a inscribir - Obligatorio.
     * @param crearReporte  Crear o no reporte del paso a paso.
     */
    public void setReferencia(String referencia, boolean crearReporte){
        String nombreReferencia = "";
        if (!referencia.equalsIgnoreCase("NA")){
            nombreReferencia = accion.getText(ServiciosInscritosXpath.labelReferencia,3, getClass(), false);
        }
        accion.writeOn( ServiciosInscritosXpath.txtReferencia, referencia, nombreReferencia,3,getClass(), crearReporte);
    }

    /**
     * Presiona el botón Agregar Servicio, dirigiendo al formulario para crear un Servicio Nuevo.
     * @param proveedorServicio a seleccionar.
     * @param servicio a seleccionar dependiendo del proveedor seleccionado.
     * @param referencia del servicio a inscribir - Obligatorio.
     * @param descripcion del servicio a inscribir - Obligatorio.
     * @param crearReporte  Crear o no reporte del paso a paso.
     */
    protected void pantallaNuevoServicio(String proveedorServicio, String servicio, String referencia, String descripcion,boolean crearReporte){

        accion.getText(tituloPagina, 5, getClass(), crearReporte);
        //accion.getTextItemVisibleList( tituloPagina,5,getClass(), crearReporte);
        accion.clickOn(ServiciosInscritosXpath.btnAgregarServicio,"AGREGAR SERVICIO",20,getClass(),false,crearReporte);
        accion.isElementVisible(comboProveedorServicios,15,getClass()); accion.isElementVisible(leyendaFomulario,5,getClass());
        accion.getText(tituloNuevoServicio, 1,getClass(),crearReporte);
//        String imprimir =  accion.getText( ServiciosInscritosXpath.tituloNuevoServicio,1,getClass(), false) +"<br>"+
//                accion.getText( leyendaFomulario,1,getClass(), false);
//        accion.crearPaso(imprimir,true, crearReporte, crearReporte);
        setFormulario( proveedorServicio, servicio, referencia, descripcion, crearReporte);
    }

    /**
     * Realiza la búsqueda del servicio ingresado, luego presiona el botón editar, lo que dirigirá al formulario para editar el servicio. Debe de ser un servicio existente.
     * @param servicioAEditar Identificador del servicio que se va editar - Obligatorio.
     * @param proveedorServicio Proveedor del servicio que se va a inscribir - Obligatorio..
     * @param servicio Para seleccionar un Servicio del proveedor de servicio seleccionado - Obligatorio..
     * @param referencia del servicio a inscribir - Obligatorio.
     * @param descripcion del servicio a inscribir - Obligatorio.
     * @param crearReporte Crear o no reporte del paso a paso.
     */
    protected void pantallaEditarServicio(String servicioAEditar,String proveedorServicio, String servicio, String referencia, String descripcion,boolean crearReporte){
        By btnEditar = By.xpath("//p-table[@ng-reflect-loading='false']//tbody/tr[td[contains(.,'"+servicioAEditar+"')] ] //button[@ng-reflect-text='Editar']");

        accion.getText( tituloPagina,5, getClass(), crearReporte );
        accion.writeOn( txtBuscar, servicioAEditar,"CAMPO BUSCAR",20, getClass(), crearReporte );
        accion.clickOn(by(format(XP_BTN_EDITAR,servicioAEditar)),"BOTON EDITAR",2,getClass(),crearReporte,crearReporte);

        //accion.clickOn( btnEditar,"BOTON EDITAR",3, getClass(), crearReporte, crearReporte);
        accion.isElementPresent( comboProveedorServicios,"COMBO PROVEEDOR SERVICIOS",20, getClass());
        accion.isElementVisible(leyenda, 15, getClass());
        accion.getText(tituloEditarServicio, 1, getClass(), crearReporte);

//        String tituloImprimir = accion.getText(ServiciosInscritosXpath.tituloEditarServicio,3, getClass()) + "<br>" +
//                                accion.getText(ServiciosInscritosXpath.leyenda,1,getClass());
        //accion.crearPaso( tituloImprimir,true, crearReporte, crearReporte );
        setFormulario( proveedorServicio, servicio, referencia, descripcion, crearReporte );
    }

    /**
     * Presiona el botón guardar en el formulario y luego completa y confirma el modal de código clave TDC.
     * @param codigoClave Código de tarjeta de clave, TDC.
     * @param crearReporte Crear o no reporte del paso a paso.
     */
    protected void guardarServicio(String codigoClave, boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarConfirmarTDC(codigoClave,crearReporte);
    }

//    protected void modalServicioGuardadoCorrectamente(boolean crearReporte){
//        accion.getText(messageServicioGuardado, 5,getClass(),crearReporte);
//    }

    protected void verificarUsuarioAgregado(String proveedor, String servicio, String referencia, String descripcion,boolean crearReporte){
        accion.getText(tituloPagina, 5, getClass(), crearReporte);
        accion.writeOn(txtBuscar, referencia,"CAMPO BUSCAR",20, getClass(), crearReporte);
        accion.mouseOvers(by(format(XP_SERVICIO_AGREGADO, proveedor, servicio, referencia, descripcion)),3,getClass());
        accion.getText(by(format(XP_SERVICIO_AGREGADO, proveedor, servicio, referencia, descripcion)),3, getClass(), crearReporte);
    }

    protected String actualizarServicioCorrectamente(String codigoTDC,boolean crearReporte){
        guardarServicio( codigoTDC, crearReporte );
        String msg = accion.getText( messageServicioActualizado, 3, getClass(), crearReporte);
        accion.clickOn(BTN_CONTINUAR,"BOTON CONTINUAR",1, getClass(),false,crearReporte);
        return msg;
    }

    protected static By messageServicioActualizado = By.xpath("//div[em]/h3[contains(.,'El servicio ha sido actualizado correctamente')]");

    /** ❤ Realiza la búsqueda del servicio ingresado, presionando luego el botón de eliminar, lo que abrirá el modal para confirmar si se elimina o no el servicio. ❤
     * @param servicio que se va a eliminar.
     * @param crearReporte Crear o no reporte del paso a paso.
     */
    protected void modalEliminarServicio(String servicio, boolean crearReporte){
        By btnEliminar = By.xpath("//p-table[@ng-reflect-loading='false']//tbody/tr[td[contains(.,'"+servicio+"')] ] //button[@ng-reflect-icon='pi pi-action-delete']");
        accion.getText( tituloPagina,5,getClass(), crearReporte);
        accion.writeOn( txtBuscar, servicio,"CAMPO BUSCAR",20, getClass(), crearReporte);
        accion.clickOn(btnEliminar,"BOTON ELIMINAR",5, getClass(), crearReporte, crearReporte);
        accion.getText(preguntaEliminarServicio, 6,getClass(),crearReporte);
       // String imprimir = "";//accion.getText(BeneficiariosInscritosXpath.tituloModalConfirmarOperacionModal,3,getClass()) + "<br>" +
                          //accion.getText(ServiciosInscritosXpath.preguntaEliminarServicio,1,getClass());
        //accion.crearPaso(imprimir,true, crearReporte, crearReporte);
    }

    /** ❤ Presiona el botón eliminar en el modal para eliminar un servicio, confirmando la acción de eliminar el mismo. ❤
     * @param servicio que se va a eliminar.
     * @param crearReporte Crear o no reporte del paso a paso.
     * @return No se encontraron registros para mostrar
     */
    protected String confirmarEliminarServicioModal(String servicio, boolean crearReporte){
        accion.clickOn(btnEliminar, "BOTON ELIMINAR",5,getClass(),false,crearReporte);
        accion.getText( tituloPagina,15,getClass(), crearReporte);
        accion.writeOn( txtBuscar, servicio,"CAMPO BUSCAR",20, getClass(), crearReporte);

//        accion.mouseCaptureElementBeforeClick(BeneficiariosInscritosXpath.btnEliminarModalBeneficiarioServicio,"BOTON ELIMINAR",2, getClass(), false, crearReporte);
//        accion.expectElementNotPresent(BeneficiariosInscritosXpath.btnEliminarModalBeneficiarioServicio, 20, getClass());
//        accion.writeOn( txtBuscar, servicio,"CAMPO BUSCAR",1, getClass(), crearReporte);
        return accion.getText(mensajeNoResultadoBusqueda,5, getClass(), crearReporte); //accion.getTextItemVisibleList(ServiciosInscritosXpath.mensajeNoResultadoBusqueda, 3, getClass(), crearReporte);
    }

    /**
     * ❤ Modal que verifica el mensaje que indica que el servicio fue guardado correctamente. ❤
     * @param crearReporte
     * @return El servicio ha sido registrado correctamente
     */
    protected String modalServicioGuardadoCorrectamente(boolean crearReporte){
        //accion.isElementVisible(ServiciosInscritosXpath.iconoGuardadoCorrectamente,20, getClass());
        String msg = accion.getText(messageServicioGuardado, 5,getClass(),crearReporte);
        //String servicioGuardadoExitosamente = accion.getTextItemVisibleList(ServiciosInscritosXpath.labelServicioRegistradoCorrectamente,1, getClass(), crearReporte);
        accion.clickOn(ServiciosInscritosXpath.btnContinuar,"BOTON CONTINUAR",1,getClass(),false,crearReporte);
        return msg;//servicioGuardadoExitosamente;
    }

    protected String modalGuardadoAnteriormente(boolean crearReporte){

        //String msgServicioGuardadoAnteriormente = accion3.getText()
        accion.clickOn(btnContinuar, "BOTON CONTINUAR", 1, getClass(), false, crearReporte);
    return "msgServicioGuardadoAnteriormente";
    }

    /**
     * ❤ <h1> ELIMINAR </h1> Modal que verifica el mensaje cuando el servicio ha sido actualizado correctamente. ❤
     * @param crearReporte
     * @return El servicio ha sido actualizado correctamente
     */
    protected String modalActualizadoCorrectamente(boolean crearReporte){
        accion.isElementVisible(ServiciosInscritosXpath.iconoGuardadoCorrectamente,20, getClass());
        String servicioGuardadoExitosamente = accion.getText(labelServicioActualizadoCorrectamente,1, getClass(), crearReporte);
        accion.clickOn(ServiciosInscritosXpath.btnContinuar,"BOTON CONTINUAR",1,getClass(),false,crearReporte);
        return servicioGuardadoExitosamente;
    }

}
