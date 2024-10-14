package pages.pyt.accesosrapidos.beneficiariosinscritos;

import accion.Accion;
import basetest.BaseTest;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import static java.lang.String.format;
import static microservicios.Utilidad.by;
import static pages.XpathComunes.TXT_TELEFONO;
import static pages.pyt.accesosrapidos.beneficiariosinscritos.XpathBeneficiarios.*;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 17/02/2024 2:19 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PoBeneficiariosPinPesos extends BasePage {
    private final Accion accion;

    private static final By tituloNuevoBeneficiario = By.xpath("//h3[contains(.,'Nuevo beneficiario PIN Pesos')]");
    private static final By menuPinPesos = By.xpath("//li[@role='presentation']/a[span[contains(.,'PIN Pesos')]]");
    private final By tituloEditarBeneficiarioPinPesos = By.xpath("//h3[contains(.,'Editar beneficiario PIN Pesos')]");
    private final By btnADDPINPesos = By.xpath(XPATH_PANTALLA_ACTUAL_CARGADA + "//span[contains(.,'PIN Pesos')]");

    private PoBeneficiariosPinPesos(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    /** <h3>Retorna una nueva instancia de la pagina PoBeneficiariosPinPesos</h3>
     * @return PoBeneficiariosPinPesos
     */
    public static PoBeneficiariosPinPesos getNewPagina(){
        return new PoBeneficiariosPinPesos(BaseTest.getDriver());
    }

    /** <h3> Toma captura de la pantalla actual entre las diferentes pantalla de los beneficiarios. </h3>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    private void imprimirPantallaActual(boolean crearReporte){
        String actual = accion.getText(tituloBeneficiarioInscritos, 3, getClass(), false) + " " +
                        accion.getText(labelPantallaActual, 2, getClass(), false);
        accion.crearPaso(actual, true, crearReporte, crearReporte);
    }

    /** <h3> Realiza click en la pestaña PIN Pesos en caso de estar en una pestaña de beneficiario diferente, llevando a la pestaña de la lista de beneficiarios PIN Pesos. </h3>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void irAPantallaListaBeneficiarios(boolean crearReporte){
        if (!accion.getText(labelPantallaActual, 10, getClass(), false).equals("PIN Pesos")){
            imprimirPantallaActual(crearReporte);
            accion.clickOn(menuPinPesos, "BOTON LISTA PIN PESOS",1, getClass(),false, crearReporte);
        }
        imprimirPantallaActual(crearReporte);
    }

    /** <h3>Escribe en el campo de búsqueda el beneficiario que se quiere editar y luego presiona el botón editar de ese beneficiario.</h3>
     * @param buscarBeneficiario Identificador del beneficiario que quiere editar.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    private void clickBotonEditar(String buscarBeneficiario,boolean crearReporte){
        accion.writeOns(txtBuscar, buscarBeneficiario, TEXTO_BUSCAR,15,getClass(), crearReporte);
        accion.clickOn(By.xpath(format(XP_BTN_EDITAR,buscarBeneficiario)),"BOTON EDITAR",3,getClass(),crearReporte,crearReporte);
    }

    private void clickBotonEliminar(String beneficiarioAEliminar, boolean crearReporte){
        accion.writeOn(TXT_BUSCAR, beneficiarioAEliminar, TEXTO_BUSCAR,15,getClass(), crearReporte);
        accion.clickOn(By.xpath(format(XP_BNT_ELIMINAR,beneficiarioAEliminar)),"BOTON ELIMINAR",3,getClass(),crearReporte,crearReporte);
    }

    protected void modalEliminarBeneficiario(String buscar, boolean crearReporte){
        //By btnEliminar = By.xpath(format(XP_BNT_ELIMINAR,buscar));
        //By btnEliminar = By.xpath("//p-table[@ng-reflect-loading='false']//tbody/tr[td/span[contains(.,'"+buscar+"')]]//button[@ng-reflect-text='Eliminar']");
        accion.writeOn( TXT_BUSCAR, buscar,TEXTO_BUSCAR,25, getClass(), crearReporte );
        accion.clickOn(By.xpath(format(XP_BNT_ELIMINAR,buscar)),"BOTON ELIMINAR",5, getClass(), crearReporte, crearReporte);

        String imprimir = accion.getText(tituloModalConfirmarOperacionModal,3, getClass(),false) + "<br><br>"
                        + accion.getText(preguntaModalSeguroDeEliminarBeneficiario,1,getClass(),false);
        accion.crearPaso(imprimir,true,crearReporte,crearReporte);
    }

    /** <h3> Direcciona a la pantalla para agregar un nuevo beneficiario pin Pesos donde permitirá llenar los campos de dicho formulario. </h3>
     * @param alias Opcional - Permite asignar un alias al beneficiario.
     * @param telefono Obligatorio - Permite asignar un teléfono al beneficiario.
     * @param tipoDocumento Obligatorio - Permite seleccionar el tipo de documento con el que se creara el beneficiario: Cédula, Pasaporte
     * @param numeroDocumento Obligatorio - Permite llenar el numero de documento del beneficiario.
     * @param nombre Permite llenar el nombre del beneficiario - Obligatorio cuando es pasaporte o si es cedula y falla la consulta de la junta.
     * @param pais Permite llenar el pías del beneficiario - Obligatorio cuando es pasaporte o si es cedula y falla la consulta de la junta.
     * @param genero Permite llenar el genero del beneficiario - Obligatorio cuando es pasaporte o si es cedula y falla la consulta de la junta.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void irAPantallaNuevoBeneficiario(String alias, String telefono, String tipoDocumento, String numeroDocumento, String nombre, String pais, String genero, boolean crearReporte){
        accion.clickOn( BTN_AGREGAR_BENEFICIARIO,"BOTON AGREGAR BENEFICIARIO",20, getClass(),false, crearReporte);
        accion.clickOn( btnADDPINPesos,"BOTON PIN PESOS",3, getClass(), crearReporte, crearReporte);
        setPantallaNuevoBeneficiario(alias, telefono, tipoDocumento, numeroDocumento, nombre, pais, genero, crearReporte);
    }

    /** <h3> Este método realiza una consulta mediante el microservicio de beneficiarios nacionales PIN Pesos, para luego validar que se muestre la data correspondiente al beneficiario agregado en la lista de beneficiarios. </h3>
     * @param usuarioActual Usuario actual desde el cual se agregara o se actualizara el beneficiario.
     * @param telefono Número de teléfono del beneficiario que se va a crear o actualizar.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void consultarMsVerificarVisualizacionBeneficiario(String usuarioActual, String telefono, boolean crearReporte){
        accion.writeOn( TXT_BUSCAR, telefono,TEXTO_BUSCAR,25, getClass(), crearReporte );
        MsBeneficiariosNacionales msPinPesos = new MsBeneficiariosNacionales(usuarioActual).newQuery().selecPinPesos().buscar( telefono );

        if (msPinPesos.getAlias().equals("")){
            accion.focusOnPress(by(format(XP_VERIFICAR_BENEFICIARIO_AGREGADO2, msPinPesos.getNombre(), msPinPesos.getNombreBanco(),telefono)),1, getClass());
        }else {
            accion.focusOnPress(by(format(XP_VERIFICAR_BENEFICIARIO_AGREGADO2, msPinPesos.getAlias(), msPinPesos.getNombreBanco(),telefono)),1, getClass());
        }
        accion.isElementVisible(by(format(TOOL_TIP_PRODUCTO,telefono)),3,getClass());
        accion.crearPaso("Se visualiza el beneficiario: " + telefono,true, crearReporte,crearReporte);
    }

    /** <h3>En beneficiarios inscrito, presiona en el menú la opción Pin Pesos, dirigiendo a la pantalla de la lista de beneficiarios del mismo, escribe en el campo de búsqueda y luego presiona el botón editar del beneficiario enviado por parámetro.</h3>
     * @param beneficiarioAEditar Identificador del beneficiario que quiere editar.
     * @param alias Permite asignar un nuevo alias al beneficiario.
     * @param nombre Permite asignar un nuevo nombre al beneficiario.
     * @param pais Permite asignar un pais al beneficiario.
     * @param genero Permite asignar un genero al beneficiario.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void irAPantallaEditarBeneficiario(String beneficiarioAEditar,String alias, String nombre, String pais, String genero,boolean crearReporte){
        irAPantallaListaBeneficiarios(crearReporte);
        //clickBotonEditar( beneficiarioAEditar, crearReporte );

        accion.writeOn(TXT_BUSCAR, beneficiarioAEditar, TEXTO_BUSCAR,15,getClass(), crearReporte);
        accion.clickOn(By.xpath(format(BTN_EDITAR,beneficiarioAEditar)),"BOTON EDITAR",3,getClass(),crearReporte,crearReporte);
        setPantallaActualizarBeneficiario( alias, nombre, pais, genero, crearReporte );
    }

    protected void irAModalEliminar(String beneficiarioAEliminar, boolean crearReporte){
        clickBotonEliminar(beneficiarioAEliminar, crearReporte);
        accion.isElementPresent(tituloModalConfirmarOperacionModal,10, getClass());
    }

    /** <h3> Este método permite actualizar la data de un beneficiario pin pesos ya existente. Reutilizar en acceso pin pesos generados.</h3>
     * @param alias Permite asignar un nuevo alias al beneficiario.
     * @param nombre Permite asignar un nuevo nombre al beneficiario.
     * @param pais Permite asignar un pais al beneficiario.
     * @param genero Permite asignar un genero al beneficiario.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public void setPantallaActualizarBeneficiario(String alias, String nombre, String pais, String genero, boolean crearReporte){
        accion.isElementsVisible(tituloDatosDelBeneficiario,10, getClass());
        accion.getText(tituloEditarBeneficiarioPinPesos, 7, getClass(), false);
        accion.isElementsVisible(tituloDatosDelBeneficiario,10, getClass());
        setFomulario(alias, "NA", "NA", "NA", nombre, pais, genero, crearReporte);
    }

    /** <h3>Permite llenar el formulario para un nuevo beneficiario Pin pesos.</h3>
     * @param alias Permite asignar un alias al beneficiario.
     * @param telefono Permite asignar un teléfono al beneficiario.
     * @param tipoDocumento Permite seleccionar el tipo de documento con el cual se creara el beneficiario.
     * @param numeroDocumento Permite asignar el numero de documento al beneficiario.
     * @param nombre Permite asignar el nombre del beneficiario.
     * @param pais Permite asignar el pais al beneficiario.
     * @param genero Permite asignar el genero del beneficiario.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public void setPantallaNuevoBeneficiario(String alias, String telefono, String tipoDocumento, String numeroDocumento, String nombre, String pais, String genero, boolean crearReporte){
        String imprimir = accion.getText(tituloNuevoBeneficiario,5, getClass(), false) +"<br>" +
                accion.getText(tituloDatosDelBeneficiario, 1, getClass(), false);
        accion.crearPaso( imprimir,true, crearReporte, crearReporte);
        setFomulario(alias, telefono, tipoDocumento, numeroDocumento, nombre, pais, genero, crearReporte);
    }

    /** <h3>Este método permite llenar el formulario para un beneficiario pin pesos. <a href='#'>Reutilizar en formulario transferencia pin pesos.</a> </h3>
     * @param alias Permite asignar un alias al beneficiario.
     * @param telefono Permite asignar un teléfono al beneficiario.
     * @param tipoDocumento Permite seleccionar el tipo de documento con el cual se creara el beneficiario.
     * @param numeroDocumento Permite asignar el numero de documento del beneficiario.
     * @param nombre Permite asignar el nombre del beneficiario.
     * @param pais Permite asignar el pais al beneficiario.
     * @param genero Permite asignar el genero del beneficiario.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public void setFomulario(String alias, String telefono, String tipoDocumento, String numeroDocumento, String nombre, String pais, String genero, boolean crearReporte){
        if (accion.isElementVisibleNoException(comboGenero, 0)){
            accion.isElementVisibleNoException(comboPais, 10);
        }
        accion.writeOn( txtAlias, alias,"ALIAS",1,getClass(), crearReporte);
        By mascaraTelefono = By.xpath("//div[label[contains(.,'Teléfono del beneficiario')]]/p-inputmask");
        if (!telefono.equalsIgnoreCase("NA")){
            accion.clickOn( mascaraTelefono,"CAMPO TELEFONO",1, getClass(),false,false);
        }
        accion.writeOn(TXT_TELEFONO, telefono,"ALIAS",1,getClass(), crearReporte);

        accion.selectDropdownIBP( comboTipoDocumento, tipoDocumento,"TIPO DOCUMENTO",10, getClass(), crearReporte);

        accion.writeOn( txtNumeroDocumento, numeroDocumento,"NUMERO DOCUMENTO",1,getClass(), crearReporte);
        if (!numeroDocumento.equalsIgnoreCase("NA")){
            accion.clickOn( labelNumeroDocumento,"LABEL NUMERO DOCUMENTO",1, getClass(),false,false);
        }
        //String tipDocument = action.getText(comboTipoDocumento,1,getClass());
        if ( accion.isElementVisibleNoException(comboGenero, 1) || tipoDocumento.equals("Pasaporte") || (tipoDocumento.equals("Cédula") || tipoDocumento.equals("RNC")  && accion.isElementVisibleNoException(errorConsultadoJunta,0))){ // Estos campos se completaran si es pasaporte o falla la JCE.

            accion.isElementPresent(comboPais,"COMBO PAIS NACIMIENTO",15, getClass());
            accion.writeOn( txtNombreBeneficiario, nombre,"NOMBRE BENEFICIARIO",2, getClass(), crearReporte);
            accion.selectDropdownIBP( comboPais, pais,"PAIS",1, getClass(), crearReporte);
            accion.selectDropdownIBP( comboGenero, genero,"GENERO",1, getClass(), crearReporte);
        }
    }

    private boolean isBeneficiaryPinPesos(String productNumber){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            result.append(productNumber.charAt(i));// = result+ numCuenta.charAt(i);
        }
        if (result.toString().contains("809") || result.toString().contains("829") || result.toString().contains("849")){
            //System.out.println("Entro" + result);
            //accion.waitMilliSeconds(10);
            System.out.println("Es pin Pesos");
            return true;
        }
        return false;
    }

    /** <h3> </h3>
     * @param usuario
     * @param telefDeBeneficiario
     * @return
     */
    private String consultarBeneficiarioMs(String usuario, String telefDeBeneficiario){
        MsBeneficiariosNacionales ms = new MsBeneficiariosNacionales(usuario).newQuery().selecPinPesos().buscar(telefDeBeneficiario);
            telefDeBeneficiario = ms.getNumProducto();
        return telefDeBeneficiario;
    }


    /** <h3> Dirige a la pantalla de la lista de beneficiarios pin pesos, busca el beneficiario que se va a eliminar y hace click en el icono de eliminar, lo cual debe de abrir el modal de confirmación para la eliminación del beneficiario. </h3>
     * @param usuarioActual Obligatorio - Usuario actual desde el cual se esta iniciando sesión.
     * @param telefBeneficiarioAEliminar Obligatorio - Teléfono del beneficiario que se va a eliminar.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void modalEliminarBeneficiario(String usuarioActual,String telefBeneficiarioAEliminar, boolean crearReporte){
        irAPantallaListaBeneficiarios(crearReporte);
        if (isBeneficiaryPinPesos(telefBeneficiarioAEliminar)){
            telefBeneficiarioAEliminar = consultarBeneficiarioMs(usuarioActual, telefBeneficiarioAEliminar);
        }
        accion.writeOns( txtBuscar, telefBeneficiarioAEliminar,TEXTO_BUSCAR,25, getClass(), crearReporte );
        accion.clickOn(by(format(XP_BNT_ELIMINAR,telefBeneficiarioAEliminar)),"BOTON ELIMINAR",1, getClass(), crearReporte, crearReporte);
    }

    /** <h3> Realiza una búsqueda en la lista de beneficiarios pin pesos, en donde se espera no encontrar resultados. </h3>
     * @param telefDeBeneficiario Obligatorio - Numero de teléfono del beneficiario que se consultara.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return No se encontraron registros para mostrar
     */
    protected String verificarBeneficiarioNoExistente(String telefDeBeneficiario, boolean crearReporte){
        accion.writeOns( TXT_BUSCAR, telefDeBeneficiario,TEXTO_BUSCAR,25, getClass(), crearReporte );
        return accion.getText(msgNoSeEncontroRegistro,3, getClass(), crearReporte);
    }
    
}
