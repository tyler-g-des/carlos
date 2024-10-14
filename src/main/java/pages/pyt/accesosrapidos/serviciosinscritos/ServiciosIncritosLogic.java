package pages.pyt.accesosrapidos.serviciosinscritos;

//import pages.accionesrepetitivas.AccionRepetitivaLogic;

/**
 * Carlos Loyola
 *
 * @author: Carlos A. Loyola Tejeda
 * @Date 23/11/2023 11:28 a. m.
 * 2023
 * @Email carlos_loyola@bhd.com.do
 * BHDL_AutomatizacionMigracionIBP
 */
public class ServiciosIncritosLogic {

    private ServiciosInscritosPage page;
    //private AccionRepetitivaLogic pageRepetitivo;
    private ServiciosIncritosLogic(){
        page = ServiciosInscritosPage.getPage();
        //pageRepetitivo = AccionRepetitivaLogic.getInstance();
    }

    /** Retorna una nueva instancia de la clase Lógica ServiciosIncritosLogic.
     * @return ServiciosIncritosLogic
     */
    public static ServiciosIncritosLogic getLogica(){
        return new ServiciosIncritosLogic();
    }

    /** Permite agregar un Servicio nuevo.
     * @param proveedorServicio
     * @param servicio
     * @param referencia
     * @param descripcion
     * @param codigoClave
     * @param crearReporte
     * @return
     */
    public String agregarServicio(String proveedorServicio, String servicio, String referencia, String descripcion, String codigoClave, boolean crearReporte){
        page.pantallaNuevoServicio(proveedorServicio, servicio, referencia, descripcion, crearReporte);
        page.guardarServicio(codigoClave, crearReporte);
        String msg = page.modalServicioGuardadoCorrectamente(crearReporte);
        page.verificarUsuarioAgregado(proveedorServicio, servicio, referencia, descripcion, crearReporte);

        //String msg = page.modalServicioGuardadoCorrectamente(crearReporte);
        //page.verificarUsuarioAgregado(proveedorServicio, servicio, referencia, descripcion, crearReporte);
        return msg;
    }

    /** Permite editar un Servicio existente.
     * @param servicioAEditar
     * @param proveedorServicio
     * @param servicio
     * @param referencia
     * @param descripcion
     * @param codigoTDC
     * @param crearReporte
     * @return El servicio ha sido actualizado correctamente
     */
    public String editarServicio(String servicioAEditar,String proveedorServicio, String servicio, String referencia, String descripcion, String codigoTDC,boolean crearReporte){
        page.pantallaEditarServicio( servicioAEditar, proveedorServicio, servicio, referencia, descripcion, crearReporte );
        String msg =  page.actualizarServicioCorrectamente(codigoTDC, crearReporte);
        //page.guardarServicio( codigoTDC, crearReporte );
        return msg;//page.modalActualizadoCorrectamente( crearReporte );
    }

    /** Permite eliminar un Servicio existente.
     * @param servicio Servicio a eliminar
     * @param crearReporte
     * @return No se encontraron registros para mostrar
     */
    public String eliminarServicio(String servicio, boolean crearReporte){
        page.modalEliminarServicio(servicio, crearReporte);
        return page.confirmarEliminarServicioModal(servicio, crearReporte);
    }
}
