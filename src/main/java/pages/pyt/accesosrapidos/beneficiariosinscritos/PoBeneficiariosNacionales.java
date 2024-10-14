package pages.pyt.accesosrapidos.beneficiariosinscritos;

import accion.Accion;
import basetest.BaseTest;
import microservicios.Utilidad;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.PoAccionesRepetitivas;

import static java.lang.String.format;
import static microservicios.Utilidad.by;
import static pages.XpathComunes.TXT_CORREO;
import static pages.pyt.accesosrapidos.beneficiariosinscritos.XpathBeneficiarios.*;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 17/02/2024 2:19 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PoBeneficiariosNacionales extends BasePage {

    private final Accion accion;
    private MsBeneficiariosNacionales microBeneficiariosNacionales;
    private static final String TEXTO_ALIAS = "CAMPO ALIAS";
    private final By btnADDNacional = By.xpath(XPATH_PANTALLA_ACTUAL_CARGADA + "//span[contains(.,'Nacionales')]");
    private final By tituloNuevoBeneficiarioNacional = By.xpath("//h3[contains(.,'Nuevo beneficiario nacional')]");
    private final By circuloCargando = By.xpath("//spinnericon");
    private final By tituloEditarBeneficiario = By.xpath(String.format(XPATH_TITULO_BENEFICIARIO,"Editar beneficiario nacional"));

    /** <h3> Constructor por defecto. </h3>
     * @param webDriver
     */
    private PoBeneficiariosNacionales(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    /** <h3> Retorna una nueva instancia de la clase PoBeneficiariosNacionales </h3>
     * @return PoBeneficiariosNacionales
     */
    public static PoBeneficiariosNacionales getNewPagina(){
        return new PoBeneficiariosNacionales(BaseTest.getDriver());
    }

    /** <h3> Toma captura de la pantalla actual entre las diferentes pantalla de los beneficiarios. </h3>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    private void imprimirPantallaActual(boolean crearReporte){
        String actual = accion.getText(tituloBeneficiarioInscritos, 3, getClass(), false) + " " +
                accion.getText(labelPantallaActual, 2, getClass(), false);
        accion.crearPaso(actual, true, crearReporte, crearReporte);
    }

    /** <h3> Realiza click en la pestaña Nacionales en caso de estar en una pestaña de beneficiario diferente, llevando a la pestaña de la lista de beneficiarios Nacionales. </h3>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void irAPantallaListaBeneficiarios(boolean crearReporte){
        if (!accion.getText(labelPantallaActual,5,getClass(),false).equalsIgnoreCase("Nacionales")){
            imprimirPantallaActual(crearReporte);
            accion.clickOn(linkVentanaNacionales,"MENU NACIONALES",3,getClass(),false,crearReporte);
        }
        imprimirPantallaActual(crearReporte);
    }

    /** Permite llenar el formulario de un beneficiario Nacional del <a href='#'>BANCO MULTIPLE BHD, S.A.</a>  y <a href='#'>BHDIB Panamá</a>.
     * @param nombreBanco <a href='#'>Obligatorio</a> - Permite seleccionar el banco destino - <a href='#'>BANCO MULTIPLE BHD, S.A.</a> o <a href='#'>BHDIB Panamá</a>.
     * @param numeroProducto <a href='#'>Obligatorio</a> - Permite llenar el numero de producto hacia el banco destino seleccionado. Debe ser un numero de producto valido.
     * @param alias <a href='#'>Opcional</a> - Permite asignar un alias al beneficiario.
     * @param correo <a href='#'>Opcional</a> - Permite llenar el correo del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     */
    public void setBeneficiarioNacional(String nombreBanco, String numeroProducto, String alias, String correo, boolean crearReporte){
        accion.selectDropdownIBP(COMBO_NOMBRE_BANCO, nombreBanco,"NOMBRE BANCO",10, getClass(), crearReporte);
        accion.writeOn( txtNumeroProducto, numeroProducto,"NUMERO PRODUCTO",1,getClass(), crearReporte);

        if (!numeroProducto.equalsIgnoreCase("NA")){
            accion.clickOn(labelNumeroProducto, "LABEL NUMERO PRODUCTO",1, getClass(),false,false);
            accion.isElementPresent(txtNombreBeneficiario, "NOMBRE BENEFICIARIO",15, getClass());
        }
        accion.writeOn( txtAlias, alias, TEXTO_ALIAS,1,getClass(), crearReporte);
        accion.writeOn( TXT_CORREO, correo,"CORREO",1, getClass(), crearReporte);
        accion.crearPaso("Captura Pantalla",true, crearReporte, crearReporte);
    }

    /** <h3>  Permite llenar el formulario de un beneficiario nacional de otro banco. Esto excluya el banco BHDIB Panamá. </h3>
     * @param nombreBanco <a href='#'>Obligatorio</a> - Permite seleccionar el nombre del banco destino.
     * @param tipoProducto <a href='#'>Obligatorio</a> - Permite seleccionar el tipo de producto.
     * @param numeroProducto <a href='#'>Obligatorio</a> - Permite llenar el campo numero de producto.
     * @param tipoDocumento <a href='#'>Obligatorio</a> - Permite seleccionar el tipo de documento.
     * @param numeroDocumento <a href='#'>Obligatorio</a> - Permite llenar el numero de documento.
     * @param nombre Obligatorio, con tipo de documento pasaporte o si falla la Junta.
     * @param alias <a href='#'>Opcional</a> - Permite asignar un alias al beneficiario.
     * @param pais Obligatorio, con tipo documento pasaporte o si falla la junta. Permite llenar el pais del beneficiario.
     * @param genero Obligatorio, con tipo documento pasaporte o si falla la junta. Permite llenar el genero del beneficiario.
     * @param correo <a href='#'>Opcional</a> - Permite llenar el correo del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     */
    public void setBeneficiarioNacional(String nombreBanco, String tipoProducto, String numeroProducto,
                                        String tipoDocumento, String numeroDocumento, String nombre, String alias,
                                        String pais, String genero, String correo, boolean crearReporte){

        accion.selectDropdownIBP(COMBO_NOMBRE_BANCO, nombreBanco,"NOMBRE BANCO",10, getClass(), crearReporte);
        accion.selectDropdownIBP( comboTipoProducto, tipoProducto,"TIPO PRODUCTO",10, getClass(), crearReporte);
        accion.writeOn( txtNumeroProducto, numeroProducto,"NUMERO PRODUCTO",1,getClass(), crearReporte);
        accion.selectDropdownIBP( comboTipoDocumento, tipoDocumento,"TIPO DOCUMENTO",10, getClass(), crearReporte);
        accion.writeOn( txtNumeroDocumento, numeroDocumento,"NUMERO DOCUMENTO",1,getClass(), crearReporte);
        accion.clickOn( labelNumeroDocumento,"LABEL NUMERO DOCUMENTO",1, getClass(),false,false);
        accion.isElementPresent( txtNombreBeneficiario,"CAMPO NOMBRE BENEFICIARIO",15, getClass());

        if (tipoDocumento.equals("Pasaporte") || ((tipoDocumento.equals("Cédula") && accion.isElementVisibleNoException(errorConsultadoJunta,0)) || (tipoDocumento.equals("RNC") && accion.isElementVisibleNoException(errorConsultadoJunta,0)) || accion.isElementVisibleNoException(comboGenero,0) )){
            accion.writeOn( txtNombreBeneficiario, nombre,"NOMBRE BENEFICIARIO",1,getClass(), crearReporte);
            accion.writeOn( txtAlias, alias, TEXTO_ALIAS,1,getClass(), crearReporte);
            accion.selectDropdownIBP( comboPais, pais,"PAIS",10, getClass(), crearReporte);
            accion.selectDropdownIBP( comboGenero, genero,"GENERO",10, getClass(), crearReporte);
        }else {
            accion.writeOn( txtAlias, alias, TEXTO_ALIAS,1,getClass(), crearReporte);
        }
        accion.writeOn( TXT_CORREO, correo,"CORREO",1,getClass(), crearReporte);
    }

    /** <h3> Presiona el icono para eliminar el beneficiario enviado por parámetro, esto dirigiendo al modal para confirmar si se quiere eliminar el beneficiario. </h3>
     * @param usuario Usuario desde el cual se eliminara el beneficiario.
     * @param buscarNumCuenta Número de producto del beneficiario a eliminar.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     */
    protected void modalEliminarBeneficiario(String usuario,String buscarNumCuenta, boolean crearReporte){
        String beneficiario = consultarBeneficiariosBHDOtrosBancos(usuario, buscarNumCuenta);
        irAPantallaListaBeneficiarios(crearReporte);

        accion.writeOn( TXT_BUSCAR, buscarNumCuenta, TEXTO_BUSCAR,25, getClass(), crearReporte );
        accion.clickOn(by(format(XP_BNT_ELIMINAR,beneficiario)),"BOTON ELIMINAR",1, getClass(), crearReporte, crearReporte);
    }

    /** <h3> Presiona el botón agregar beneficiario, luego entre las opciones desplegada selecciona Nacionales, dirigiendo a la pantalla para crear un nuevo beneficiario nacional de los bancos: <a href='#'>BANCO MULTIPLE BHD, S.A</a> o <a href='#'>BHDIB Panamá</a> . </h3>
     * @param nombreBanco <a href='#'>Obligatorio</a> - Permite seleccionar el nombre del banco destino.
     * @param numProducto <a href='#'>Obligatorio</a> - Permite llenar el campo numero de producto.
     * @param alias <a href='#'>Opcional</a> - Permite asignar un alias al beneficiario.
     * @param correo <a href='#'>Opcional</a> - Permite llenar el correo del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     */
    protected void pantallaNuevoBeneficiarioBHDPanama(String nombreBanco, String numProducto, String alias, String correo, boolean crearReporte){
        accion.clickOn(BTN_AGREGAR_BENEFICIARIO, "BOTON AGREGAR BENEFICIARIO",20, getClass(),false, crearReporte);
        accion.clickOn(btnADDNacional, "BOTON NACIONALES",2, getClass(), crearReporte, crearReporte);

        String imprimir = accion.getText(tituloNuevoBeneficiarioNacional,10, getClass(), false) +"<br>" +
                accion.getText(tituloDatosDelBeneficiario, 1, getClass(), false);
        accion.crearPaso( imprimir,true, crearReporte, crearReporte);
        setBeneficiarioNacional(nombreBanco, numProducto, alias, correo, crearReporte);
    }

    /** <h3> Presiona el botón agregar beneficiario, luego entre las opciones desplegada, selecciona Nacionales, dirigiendo a la pantalla para crear un nuevo beneficiario nacional de otros bancos. </h3>
     * @param nombreBanco <a href='#'>Obligatorio</a> - Permite seleccionar el nombre del banco destino.
     * @param tipoProducto <a href='#'>Obligatorio</a> - Permite seleccionar el tipo de producto.
     * @param numProducto <a href='#'>Obligatorio</a> - Permite llenar el campo numero de producto.
     * @param tipoDocumento <a href='#'>Obligatorio</a> - Permite seleccionar el tipo de documento.
     * @param numDocumento <a href='#'>Obligatorio</a> - Permite llenar el numero de documento.
     * @param nombre Obligatorio, con tipo de documento pasaporte o si falla la Junta.
     * @param alias <a href='#'>Opcional</a> - Permite asignar un alias al beneficiario.
     * @param pais Obligatorio, con tipo documento pasaporte o si falla la junta. Permite llenar el pais del beneficiario.
     * @param genero Obligatorio, con tipo documento pasaporte o si falla la junta. Permite llenar el genero del beneficiario.
     * @param correo <a href='#'>Opcional</a> - Permite llenar el correo del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     */
    protected void pantallaNuevoBeneficiarioDeOtrosBancos(String nombreBanco, String tipoProducto, String numProducto, String tipoDocumento, String numDocumento, String nombre, String alias, String pais, String genero, String correo, boolean crearReporte){
        accion.clickOn(BTN_AGREGAR_BENEFICIARIO, "BOTON AGREGAR BENEFICIARIO",20, getClass(),false, crearReporte);
        accion.clickOn(btnADDNacional, "BOTON NACIONALES",2, getClass(), crearReporte, crearReporte);

        String imprimir = accion.getText(tituloNuevoBeneficiarioNacional,20, getClass(), false) +"<br>" +
                accion.getText(tituloDatosDelBeneficiario, 1, getClass(), false);
        accion.crearPaso( imprimir,true, crearReporte, crearReporte);
        setBeneficiarioNacional(nombreBanco, tipoProducto, numProducto, tipoDocumento, numDocumento, nombre, alias, pais, genero, correo, crearReporte);
    }

    /** <h3> Este método Realiza click en beneficiarios Nacionales, toma captura de la pantalla, busca en el campo y realiza click en el icono para editar beneficiario nacional buscado, llevando a la pantalla para editar un beneficiario nacional del <a href='#'>BANCO MULTIPLE BHD, S.A.</a> o <a href='#'>BHDIB Panamá</a>. </h3>
     * @param numProductoBeneficiarioAEditar Numero de producto del beneficiario que se va editar.
     * @param alias Permite editar el alias del beneficiario.
     * @param correo Permite editar el correo del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     */
    protected void pantallaActualizarBeneficiario(String usuarioActual,String numProductoBeneficiarioAEditar, String alias, String correo,boolean crearReporte){
        irAPantallaListaBeneficiarios(crearReporte);
        accion.writeOn( TXT_BUSCAR, numProductoBeneficiarioAEditar, TEXTO_BUSCAR,10, getClass(), crearReporte );
        numProductoBeneficiarioAEditar = consultarBeneficiariosBHDOtrosBancos(usuarioActual, numProductoBeneficiarioAEditar);
        accion.clickOn( by(format(BTN_EDITAR,numProductoBeneficiarioAEditar)),"BOTON EDITAR",3,getClass(),crearReporte,crearReporte);
        accion.getText(tituloEditarBeneficiario, 10,getClass(),crearReporte);
        setBeneficiarioNacional("NA","NA", alias, correo, crearReporte );
    }

    /** <h3> Este método Realiza click en beneficiarios Nacionales, toma captura de la pantalla, busca en el campo y realiza click en el icono para editar beneficiario nacional, llevando a la pantalla para editar un beneficiario nacional de otros bancos. </h3>
     * @param numProductoBeneficiarioAEditar Numero de producto del beneficiario que se va editar.
     * @param nombre Permite editar el nombre del beneficiario.
     * @param alias Permite cambiar el alias del beneficiario.
     * @param pais Permite cambiar el país del beneficiario.
     * @param genero Permite cambiar el genero del beneficiario.
     * @param correo Permite editar el correo del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     */
    protected void pantallaActualizarBeneficiarioDeOtrosBancos(String usuarioActual,String numProductoBeneficiarioAEditar, String alias,String pais,String genero, String correo,boolean crearReporte){
        irAPantallaListaBeneficiarios(crearReporte);
        accion.writeOn( TXT_BUSCAR, numProductoBeneficiarioAEditar, TEXTO_BUSCAR,10, getClass(), crearReporte );

        numProductoBeneficiarioAEditar = consultarBeneficiariosBHDOtrosBancos(usuarioActual, numProductoBeneficiarioAEditar);

        accion.clickOn( by(format(BTN_EDITAR,numProductoBeneficiarioAEditar)),"BOTON EDITAR",3,getClass(),crearReporte,crearReporte);
        accion.isElementVisible(tituloEditarBeneficiario, 20, getClass());
        accion.getText(tituloEditarBeneficiario, 1,getClass(),crearReporte);
        setBeneficiarioNacional("NA", "NA", "NA", "NA", "NA", "NA", alias, pais, genero, correo, crearReporte);
    }

    /** <h3> Realiza una consulta a los beneficiarios nacionales del cliente mediante el micro, con el fin de validar de que se haya agregado o actualizado correctamente el beneficiario en cuestion. Este a su vez enmascara el numero de producto si es una Tarjeta de crédito (TC). </h3>
     * @param documentNumber Numero de documento del usuario que esta registrando el beneficiario.
     * @param beneficiario Numero de producto del beneficiario que se esta creando o actualizando.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     * @return numero de producto
     */
    protected String verificarBeneficiarioBHDPanama(String documentNumber, String beneficiario, boolean crearReporte){
        accion.visiblesAndThenInvisible( circuloCargando, 4);
        accion.writeOn( TXT_BUSCAR, beneficiario, TEXTO_BUSCAR,10, getClass(), crearReporte );
        microBeneficiariosNacionales = new MsBeneficiariosNacionales( documentNumber ).newQuery().selecBHD().buscar( beneficiario );

        System.out.println(microBeneficiariosNacionales.getTipoCuentaSigla());
        if (!microBeneficiariosNacionales.getTipoCuentaSigla().equals("null")){
            if (microBeneficiariosNacionales.getTipoCuentaSigla().equals("TAC")){
                System.out.println("Si es una TC");
                beneficiario = Utilidad.enmascararTC( beneficiario );
            }
        }else {
            if (microBeneficiariosNacionales.selecOtrosBancos().buscar(beneficiario).getTipoCuentaSigla().equals("TAC")){
                beneficiario = Utilidad.enmascararTC(beneficiario);
            }
        }

        if (microBeneficiariosNacionales.getAlias().equals("")){
            accion.focusOnPress(by(format(XP_VERIFICAR_BENEFICIARIO_AGREGADO2, microBeneficiariosNacionales.getNombre(), microBeneficiariosNacionales.getNombreBanco(),beneficiario)),1);
        }else {
            accion.focusOnPress(by(format(XP_VERIFICAR_BENEFICIARIO_AGREGADO2, microBeneficiariosNacionales.getAlias(), microBeneficiariosNacionales.getNombreBanco(),beneficiario)),1);
        }
        accion.isElementVisible(by(format(TOOL_TIP_PRODUCTO,beneficiario)),4,getClass());
        accion.crearPaso("Se visualiza el beneficiario: " + beneficiario,true, crearReporte,crearReporte);
        return beneficiario;
    }

    /** <h3> Consulta tanto los beneficiarios nacionales BHD, como de otros bancos, enmascarando el numero de producto en caso de ser una Tarjeta de crédito. Esta consulta a usar para la eliminación del beneficiario. </h3>
     * @param usuario Numero de documento del usuario que esta eliminando el beneficiario.
     * @param buscarNumCuenta Cuenta del beneficiario a eliminar.
     * @return numero de producto del beneficiario a eliminar.
     */
    private String consultarBeneficiariosBHDOtrosBancos(String usuario, String buscarNumCuenta){
        MsBeneficiariosNacionales ms = new MsBeneficiariosNacionales(usuario).newQuery().selecBHD().buscar(buscarNumCuenta);

        if (!ms.getTipoCuentaSigla().equals("null")){
            if (ms.getTipoCuentaSigla().equals("TAC")){
                return Utilidad.enmascararTC(buscarNumCuenta);
            }
        }else {
            if (ms.selecOtrosBancos().buscar(buscarNumCuenta).getTipoCuentaSigla().equals("TAC")){
                return Utilidad.enmascararTC(buscarNumCuenta);
            }
        }
        return buscarNumCuenta;
    }

    /** <h3> Realiza consulta a los beneficiarios de otros bancos, través de microservicio, para verificar que se haya registrado el beneficiario correctamente y para validar que el mismo se muestre en la lista de beneficiarios. </h3>
     * @param documentNumber Numero de documento del usuario que esta registrando el beneficiario.
     * @param cuentaBeneficiario numero de producto del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     */
    protected void verificarBeneficiarioDeOtrosBancos(String documentNumber, String cuentaBeneficiario, boolean crearReporte){
        accion.visiblesAndThenInvisible(circuloCargando, 4);
        accion.writeOn( TXT_BUSCAR, cuentaBeneficiario, TEXTO_BUSCAR,10, getClass(), crearReporte );
        microBeneficiariosNacionales = new MsBeneficiariosNacionales(documentNumber).newQuery().selecOtrosBancos().buscar(cuentaBeneficiario);

        if (microBeneficiariosNacionales.getTipoCuentaSigla().equals("TAC")){
            cuentaBeneficiario = Utilidad.enmascararTC(cuentaBeneficiario);
        }

        if (microBeneficiariosNacionales.getAlias().equals("")){
            accion.focusOnPress(by(format(XP_VERIFICAR_BENEFICIARIO_AGREGADO2, microBeneficiariosNacionales.getNombre(), microBeneficiariosNacionales.getNombreBanco(),cuentaBeneficiario)),1, getClass());
        }else {
            accion.focusOnPress(by(format(XP_VERIFICAR_BENEFICIARIO_AGREGADO2, microBeneficiariosNacionales.getAlias(), microBeneficiariosNacionales.getNombreBanco(),cuentaBeneficiario)),1, getClass());
        }
        accion.isElementVisible(by(format(TOOL_TIP_PRODUCTO,cuentaBeneficiario)),1, getClass());
        accion.crearPaso("Se visualiza el beneficiario: " + cuentaBeneficiario,true, crearReporte,crearReporte);
    }

}
