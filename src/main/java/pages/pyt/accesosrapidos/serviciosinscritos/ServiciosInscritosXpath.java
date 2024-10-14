package pages.pyt.accesosrapidos.serviciosinscritos;

import org.openqa.selenium.By;

/**
 * Carlos Loyola Tejeda
 *
 * @author: Carlos A. Loyola Tejeda
 * @Date 22/11/2023 11:24 a. m.
 * 2023
 * @Email carlos_loyola@bhd.com.do
 * BHDL_AutomatizacionMigracionIBP
 */
public class ServiciosInscritosXpath {

    public ServiciosInscritosXpath(){

    }

    protected static By tituloPagina = By.xpath("//h3[em[@class[contains(.,'pi-book-service')] ] and contains(.,'Servicios inscritos')]");
    protected static By tituloEditarServicio = By.xpath("//h3[contains(.,'Edición de servicio inscrit')]");
    protected static By leyenda = By.xpath("//legend/span[contains(.,'Datos del servicio')]");
    //protected static By btnAgregarServicio = By.xpath("//p-table[@ng-reflect-loading='false']//button[span[contains(.,'Agregar servicio')]]");
    // txtBuscar de beneficiarios inscritos
    // editar, eliminar
    protected static By tituloNuevoServicio = By.xpath("//h3[contains(.,'Inscripción de nuevo servicio')]");

    protected static By leyendaFomulario = By.xpath("//legend/span[contains(.,'Datos del servicio')]");
    protected static By comboProveedorServicios = By.xpath("//div[label[contains(.,'Proveedor de servicios')] ] /p-dropdown//span");
    protected static By comboServicio = By.xpath("//div[label[contains(.,'Servicio')] ] /p-dropdown//span");

    protected static By labelReferencia = By.xpath("//div[ input[@formcontrolname='reference'] ]/label");
    protected static By txtReferencia = By.xpath("//input[@formcontrolname='reference']");

    public static By iconoGuardadoCorrectamente = By.xpath("//div/em[@class[contains(.,'pi-check-circle')]]");
    protected static By labelServicioRegistradoCorrectamente = By.xpath("//h3[contains(.,'El servicio ha sido registrado correctamente')]");
    protected static By labelServicioActualizadoCorrectamente = By.xpath("//h3[contains(.,'El servicio ha sido actualizado correctamente')]");
    public static By btnContinuar = By.xpath("//button[span[contains(.,'Continuar')]]");

    protected static By preguntaEliminarServicio = By.xpath("//h3[contains(.,'¿Estás seguro de que deseas eliminar este servicio?')]");

    public static By mensajeNoResultadoBusqueda = By.xpath("//td[contains(.,'No se encontraron registros para mostrar')]");
    //Elemento descripcion creado en otra pagina static;

    private static final String XPATH_PANTALLA_CARGADA = "//p-table[@ng-reflect-loading='false']";
    protected static final String XP_BTN_EDITAR = XPATH_PANTALLA_CARGADA + "//tbody/tr[td[contains(.,'%s')] ] //button[@ng-reflect-content='Editar']";
    protected static final String XP_BTN_ELIMINAR = XPATH_PANTALLA_CARGADA +"//tbody/tr[td[contains(.,'%s')] ] //button[@ng-reflect-icon='pi pi-action-delete']";
    protected static By btnAgregarServicio = By.xpath(XPATH_PANTALLA_CARGADA + "//button[span[contains(.,'Agregar servicio')]]");

    /**
     * param proveedorDeservicio <br>
     * param Servicio <br>
     * param Referencia <br>
     * param descripcion <br>
     */
    protected static final String XP_SERVICIO_AGREGADO = XPATH_PANTALLA_CARGADA +"//tr[td[ @id='let-serviceProvider' and contains(.,'%s')]  and td[@id='let-affectedService' and contains(.,'%s')] and td[@id='let-referenceNumber'] and contains(.,'%s') and td[@id='let-description' and contains(.,'%s')] ]";
    protected static By messageServicioGuardado = By.xpath("//div[em[@class[contains(.,'pi-check-circle')] ] ] /h3[contains(.,'El servicio ha sido registrado correctamente')]");




}
