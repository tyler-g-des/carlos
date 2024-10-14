package pages.pyt.accesosrapidos.beneficiariosinscritos;

import accion.Accion;
import basetest.BaseTest;
import microservicios.beneficiarios.internacionales.MsBeneficiariosInternacionales;
import microservicios.beneficiarios.internacionales.MsConsultarCodigoInternacional;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import static java.lang.String.format;
import static microservicios.Utilidad.by;
import static pages.XpathComunes.TXT_CORREO;
import static pages.XpathComunes.COMBO_MONEDA;
import static pages.pyt.accesosrapidos.beneficiariosinscritos.XpathBeneficiarios.*;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 17/02/2024 2:19 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */public class PoBeneficiariosInternacionales extends BasePage {

    private final Accion accion;
    private static final By BTN_ADD_INTERNACIONAL = By.xpath(XPATH_PANTALLA_ACTUAL_CARGADA + "//span[contains(.,'Internacionales')]");
    private final By listaInternacionales = By.xpath("//li[@role='presentation']/a[span[contains(.,'Internacionales')]]");
    private final By txtNombreBeneficiario = By.xpath("//div[label[contains(.,'Nombre del beneficiario')]]/input");
    private final By txtNumeroIdentificacion = By.xpath("//div[label[contains(.,'Número de identificación')]]/input");
    private final By comboPais = By.xpath("//p-dropdown[@ng-reflect-name='country']//span");
    private final By txtCalle = By.xpath("//input[@formcontrolname='address']");
    public static final By TXT_NUM_PRODUCTO = By.xpath("//div[label[contains(.,'Número de producto')]]/input");

    private final By comboTipoCodigo = By.xpath("//div[div[h6[contains(.,'DATOS DEL BANCO')]]] //p-dropdown[@formcontrolname='bankCodeType']//span");
    private final By txtCodigo = By.xpath("//div[div[h6[contains(.,'DATOS DEL BANCO')]]] //input[@formcontrolname='bankCodeNumber']");
    private final By checkUsarBancoIntermediario = By.xpath("//p-checkbox[@name='useInterBank']/label[contains(.,'Usar Banco Intermediario (Opcional)')]");

    private final By txtNumIbanIntermediario = By.xpath("//div[div[h6[contains(.,'DATOS DEL BANCO INTERMEDIARIO')]]] //div[label[contains(.,'Número de producto / IBAN')]]/input ");
    private final By comboTipoCodigoIntermediario = By.xpath("//div[div[h6[contains(.,'DATOS DEL BANCO INTERMEDIARIO')]]] //p-dropdown//span");
    private final By txtCodIntermediario = By.xpath("//div[div[h6[contains(.,'DATOS DEL BANCO INTERMEDIARIO')]]] //input[@formcontrolname='bankCodeNumberInter']");

    private final By comboDestino = By.xpath("//div[label[contains(.,'Destino')]]/p-dropdown//span");
    private final By comboClasificacion = By.xpath("//div[label[contains(.,'Clasificación')]]/p-dropdown//span");
    private final By comboProposito = By.xpath("//div[label[contains(.,'Propósito')]]/p-dropdown//span");

    private final By btnValidarCodigoBanco = By.xpath("//div[div[h6[contains(.,'DATOS DEL BANCO')]]]//button[span[contains(.,'Validar código')]]");
    private final By btnValidarCodigoBancoIntermediario = By.xpath("//div[div[h6[contains(.,'BANCO INTERMEDIARIO')]]]//button[span[contains(.,'Validar código')]]");
    private final By tituloNuevoBeneficiarioInternacional = By.xpath("//h3[contains(.,'Nuevo beneficiario internacional')]");
    private final By tituloEditarBeneficiarioInternacional = By.xpath("//h3[contains(.,'Editar beneficiario internacional')]");

    /** Xpath para verificar que se muestre muestre la data de la consulta del codigo de banco.
     * param: nombreBanco.
     * param: ciudad.
     */
    private static final String XPATH_DATA_BANCO = "//div[ div[h6[text()=' DATOS DEL BANCO ']] ]//div[p[strong[text()='Nombre del banco'] and strong[text()='Ciudad']  and strong[text()='País'] and contains(.,'%s') and contains(.,'%s')]]";

    /** Xpath para verificar que se muestre muestre la data de la consulta del codigo de banco Intermediario.
     * param: nombreBanco.
     * param: ciudad.
     */
    private static final String XP_DATA_BANCO_INTERMEDIARIO = "//div[ div[h6[text()=' DATOS DEL BANCO INTERMEDIARIO ']] ]//div[p[strong[text()='Nombre del banco'] and strong[text()='Ciudad']  and strong[text()='País'] and contains(.,'%s') and contains(.,'%s')] ]";


    private PoBeneficiariosInternacionales(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    /** <h3> Retorna una nueva instancia de la pagina PoBeneficiariosInternacionales </h3>
     * @return PoBeneficiariosInternacionales
     */
    public static PoBeneficiariosInternacionales getNewPagina(){
        return new PoBeneficiariosInternacionales(BaseTest.getDriver());
    }

    /** <h3> Toma captura de la pantalla actual entre las diferentes pantalla de los beneficiarios. </h3>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    private void imprimirPantallaActual(boolean crearReporte){
        String actual = accion.getText(tituloBeneficiarioInscritos, 3, getClass(), false) + " " +
                accion.getText(labelPantallaActual, 2, getClass(), false);
        accion.crearPaso(actual, true, crearReporte, crearReporte);
    }

    /** <h3> Realiza click en la pestaña Internacionales en caso de estar en una pestaña de beneficiario diferente, llevando a la pestaña de la lista de beneficiarios Internacionales. </h3>
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @apiNote Llamar estos metodos solo en la logica para no crear conflitos de instancias.
     */
    protected void irAPantallaListaBeneficiarios(boolean crearReporte){
        if (!accion.getText(labelPantallaActual,5,getClass(),false).equalsIgnoreCase("Internacionales")){
            imprimirPantallaActual(crearReporte);
            accion.clickOn(listaInternacionales,"MENU Internacionales",3,getClass(),false,crearReporte);
        }
        imprimirPantallaActual(crearReporte);
    }

    /** <h3> Permite llenar los primeros 7 campos del formulario de beneficiario internacional.. Enviar NA a los campos con el cual no se quiere interactuar </h3>
     * @param nombre Obligatorio. Permite llenar el nombre del beneficiario.
     * @param numIdentificacion Obligatorio. Permite llenar el numIdentificacion del beneficiario.
     * @param pais Obligatorio. Permite llenar el pais del beneficiario.
     * @param calle Obligatorio. Permite llenar la calle del beneficiario.
     * @param correo Opcional. Permite llenar el correo del beneficiario.
     * @param numProductoIBAN Obligatorio. Permite llenar el numProductoIBAN del beneficiario.
     * @param moneda Obligatorio. Permite seleccionar la moneda.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public void setFormulario(String nombre, String numIdentificacion, String pais, String calle, String correo, String numProductoIBAN, String moneda, boolean crearReporte){
        accion.writeOn( txtNombreBeneficiario, nombre,"NOMBRE BENEFICIARIO",5,getClass(), crearReporte);
        accion.writeOn( txtNumeroIdentificacion, numIdentificacion,"NUMERO IDENTIFICACION",1,getClass(), crearReporte);
        accion.selectDropdownIBP( comboPais, pais,"PAIS",1, getClass(), crearReporte);
        accion.writeOn(txtCalle, calle,"CALLE",1,getClass(), crearReporte);
        accion.writeOn(TXT_CORREO, correo,"CORREO",1,getClass(), crearReporte);
        accion.writeOn(TXT_NUM_PRODUCTO, numProductoIBAN,"CORREO",1,getClass(), crearReporte);
        accion.selectDropdownIBP(COMBO_MONEDA, moneda,"COMBO MONEDA",1,getClass(),crearReporte);
    }

    /** <h3> Permite llenar los últimos 9 campos del formulario de beneficiario internacional. </h3>
     * @param tipoCodigo Permite seleccionar el tipo de código: <a href='#'>SWIFT</a>  o <a href='#'>ABA</a>.
     * @param codigo Permite ingresar el código de tipo de código ingresado anteriormente.
     * @param usarIntermediario <a href='#'>Opcional.</a> Permite agregar un banco intermediario si es necesario.
     * @param numIbanIntermediario Permite seleccionar el tipo de código: <a href='#'>SWIFT</a>  o <a href='#'>ABA</a>, del banco Intermediario, en caso de que se haya seleccionado agregar banco intermediario en la opción anterior.
     * @param tipoCodigoInterm Permite ingresar el código para el banco intermediario.
     * @param codIntermediario  Permite ingresar el código para el banco intermediario, en caso de que se haya seleccionado usar banco intermediario.
     * @param destino <a href='#'>Obligatorio</a>. Permite seleccionar el destino
     * @param clasificacion <a href='#'>Obligatorio</a>. Permite seleccionar el clasificacion
     * @param proposito <a href='#'>Obligatorio</a>. Permite seleccionar el proposito
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public void setFormulario(String tipoCodigo, String codigo, boolean usarIntermediario, String numIbanIntermediario, String tipoCodigoInterm, String codIntermediario, String destino, String clasificacion, String proposito, boolean crearReporte){
        accion.selectDropdownIBP( comboTipoCodigo, tipoCodigo,"TIPO CODIGO",1,getClass(), crearReporte);
        accion.writeOn( txtCodigo, codigo,"CAMPO CODIGO",1, getClass(), crearReporte);
        if (!codigo.equalsIgnoreCase("NA")){
            accion.clickOn(btnValidarCodigoBanco,"VALIDAR CODIGO",1,getClass(),false,crearReporte);
        }
        if (usarIntermediario && accion.getAttribute(checkUsarBancoIntermediario,"class",1,getClass()).equals("p-checkbox-label ng-star-inserted")){
            accion.selectCheckBox(checkUsarBancoIntermediario,"USAR BANCO INTERMEDIARIO",usarIntermediario,1,getClass(),crearReporte);
            accion.writeOn( txtNumIbanIntermediario, numIbanIntermediario,"NUMERO IBAN INTERMEDIARIO",1,getClass(), crearReporte);
            accion.selectDropdown(comboTipoCodigoIntermediario, tipoCodigoInterm,"TIPO CODIGO INTERMEDIARIO",1,getClass(),crearReporte);
            accion.writeOn( txtCodIntermediario, codIntermediario,"CODIGO BANCO INTERMEDIARIO",1,getClass(), crearReporte);
            accion.clickOn(btnValidarCodigoBancoIntermediario,"VALIDAR CODIGO INTERMEDIARIO",1,getClass(),false,crearReporte);
        }
        accion.selectDropdown(comboDestino, destino,"COMBO DESTINO",1,getClass(),crearReporte);
        accion.selectDropdown(comboClasificacion, clasificacion,"COMBO CLASIFICACION",1,getClass(),crearReporte);
        accion.selectDropdown(comboProposito, proposito,"COMBO PROPOSITO",1,getClass(),crearReporte);
        accion.crearPaso("Pantalla", true, true, true);
    }

    /** <h3> Este método hace click sobre el botón Agregar beneficiario, luego toma captura antes de presionar el botón Internacional, toma captura cuando se muestra el formulario de nuevo beneficiario internacional, permitiendo llenar los datos para un nuevo beneficiario Internacional. </h3>
     * @param nombre Obligatorio. Permite llenar el nombre del beneficiario.
     * @param numIdentificacion Obligatorio. Permite llenar el numIdentificacion del beneficiario.
     * @param pais Obligatorio. Permite llenar el pais del beneficiario.
     * @param calle Obligatorio. Permite llenar la calle del beneficiario.
     * @param correo Opcional. Permite llenar el correo del beneficiario.
     * @param numIban Obligatorio. Permite llenar el numProductoIBAN del beneficiario.
     * @param moneda Obligatorio. Permite seleccionar la moneda: Dólares, Euros.
     * @param tipoCodigo Permite seleccionar el tipo de código: <a href='#'>SWIFT</a>  o <a href='#'>ABA</a>.
     * @param codigo Permite ingresar el código de tipo de código ingresado anteriormente.
     * @param usarBancoIntermediario <a href='#'>Opcional.</a> Permite agregar un banco intermediario si es necesario.
     * @param numIbanIntermediario Permite ingresar el Numero Iban del banco intermediario.
     * @param tipoCodIntermediario Permite seleccionar el tipo de código: <a href='#'>SWIFT</a>  o <a href='#'>ABA</a>, del banco Intermediario, en caso de que se haya seleccionado agregar banco intermediario en la opción anterior.
     * @param codIntermediario Permite ingresar el código para el banco intermediario, en caso de que se haya seleccionado usar banco intermediario.
     * @param destino <a href='#'>Obligatorio</a>. Permite seleccionar el destino
     * @param clasificacion <a href='#'>Obligatorio</a>. Permite seleccionar el clasificacion
     * @param proposito <a href='#'>Obligatorio</a>. Permite seleccionar el proposito
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void pantallaNuevoBeneficiario(String nombre, String numIdentificacion, String pais, String calle, String correo, String numIban, String moneda, String tipoCodigo, String codigo,boolean usarBancoIntermediario, String numIbanIntermediario, String tipoCodIntermediario, String codIntermediario, String destino, String clasificacion, String proposito,boolean crearReporte){
        accion.clickOn(BTN_AGREGAR_BENEFICIARIO,"BOTON AGREGAR BENEFICIARIO",20,getClass(),false,crearReporte);
        accion.clickOn(BTN_ADD_INTERNACIONAL,"BOTON INTERNACIONALES",1,getClass(),crearReporte,crearReporte);

        String imprimir = accion.getText(tituloNuevoBeneficiarioInternacional,5, getClass(), false) +"<br>" +
                accion.getText(tituloDatosDelBeneficiario, 1, getClass(), false);
        accion.crearPaso( imprimir,true, crearReporte, crearReporte);
        setFormulario( nombre, numIdentificacion, pais, calle, correo, numIban, moneda, crearReporte );
        setFormulario( tipoCodigo, codigo, usarBancoIntermediario, numIbanIntermediario, tipoCodIntermediario, codIntermediario, destino, clasificacion, proposito, crearReporte);
    }

    /** <h3> Navega a la pantalla de beneficiarios Inscritos Internacionales, tomando captura de pantalla de esta, busca el beneficiario, y presiona el botón editar de ese beneficiario, lo que llevara a la pantalla que permite editar el beneficiario internacional. </h3>
     * @param numIbanBeneficiarioAEditar Número de producto Iban del beneficiario que se va actualzar.
     * @param numIdentificacion Obligatorio. Permite llenar el numIdentificacion del beneficiario.
     * @param pais Obligatorio. Permite llenar el pais del beneficiario.
     * @param calle Obligatorio. Permite llenar la calle del beneficiario.
     * @param correo Opcional. Permite llenar el correo del beneficiario.
     * @param moneda Obligatorio. Permite seleccionar la moneda.
     * @param destino Obligatorio. Permite seleccionar el destino
     * @param clasificacion Obligatorio. Permite seleccionar el clasificacion
     * @param proposito Obligatorio. Permite seleccionar el proposito
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void pantallaEditarBeneficiario(String numIbanBeneficiarioAEditar, String numIdentificacion, String pais, String calle, String correo, String moneda, String destino, String clasificacion, String proposito, boolean crearReporte){
        irAPantallaListaBeneficiarios(true);
        accion.writeOn( TXT_BUSCAR, numIbanBeneficiarioAEditar, "CAMPO BUSCAR",15,getClass(), crearReporte);
        accion.clickOn(By.xpath(format(BTN_EDITAR,numIbanBeneficiarioAEditar)),"BOTON EDITAR",3,getClass(),crearReporte,crearReporte);
        accion.isElementsVisible(tituloEditarBeneficiarioInternacional, 20,getClass());
        String imprimir = accion.getText(tituloEditarBeneficiarioInternacional,1, getClass(), false) +"<br>" +
                accion.getText(tituloDatosDelBeneficiario, 2, getClass(), false);
        accion.crearPaso( imprimir,true, crearReporte, crearReporte);

        setFormulario( "NA", numIdentificacion, pais, calle, correo, "NA", moneda, crearReporte );
        setFormulario( "NA", "NA", false, "NA", "NA", "NA", destino, clasificacion, proposito, crearReporte);
    }

    /** <h3> En beneficiarios inscritos navega a la pantalla de beneficiarios internacionales, busca el beneficiario que se va a eliminar y luego presiona el icono de eliminar, acción que llevara al modal para eliminar un beneficiario. </h3>
     * @param beneficiarioAEliminar
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public void pantallaEliminarBeneficiario(String beneficiarioAEliminar, boolean crearReporte){
        irAPantallaListaBeneficiarios( crearReporte );
        accion.writeOn(TXT_BUSCAR, beneficiarioAEliminar, "CAMPO BUSCAR",15,getClass(), crearReporte);
        accion.clickOn(By.xpath(format(BTN_ELIMINAR_ICON,beneficiarioAEliminar)),"BOTON ELIMINAR",3,getClass(),crearReporte,crearReporte);
    }

    /** <h3> Este método realiza una consulta mediante el microservicio de beneficiarios internacionales, para luego validar que se muestre la data correspondiente al beneficiario agregado en la lista de beneficiarios. </h3>
     * @param usuarioActual usuario actual desde el que se va a consultar el beneficiario.
     * @param beneficiario Número de producto Iban del beneficiario que se va a consultar.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    protected void verificarVisualizacionBeneficiario(String usuarioActual, String beneficiario, boolean crearReporte){
        accion.visiblesAndThenInvisible( circuloCargando, 4);
        accion.writeOn( TXT_BUSCAR, beneficiario, TEXTO_BUSCAR,15, getClass(), crearReporte );
        MsBeneficiariosInternacionales msInternacionales = new MsBeneficiariosInternacionales( usuarioActual ).newQuery().buscar( beneficiario );
        accion.focusOnPress(by(format(XP_VERIFICAR_BENEFICIARIO_AGREGADO2, msInternacionales.getNombreBeneficiario(), msInternacionales.getnombreBanco(),beneficiario)),1);
        accion.crearPaso("Se guardo correctamente el beneficiario: " + beneficiario, true,crearReporte, crearReporte);
    }

    /** <h3> Este método verifica que se muestre la información de banco del código ingresado. </h3>
     * @param banco Objeto que contiene la data de la consulta del código del banco.
     * @param intermediario Objeto que contiene la data de la consulta del código del banco intermediario.
     * @param usuarBancoIntermediario Permite validar la información del banco intermediario en caso que este sea usado.
     */
    protected void verificarVisualizacionCodigoBanco(MsConsultarCodigoInternacional banco, MsConsultarCodigoInternacional intermediario, boolean usuarBancoIntermediario){
        accion.getText(by(String.format(XPATH_DATA_BANCO, banco.getNombreBanco(), banco.getCiudadBanco())),10, getClass(), false);
        if (usuarBancoIntermediario){
            accion.getText(by(String.format(XP_DATA_BANCO_INTERMEDIARIO, intermediario.getNombreBanco(), intermediario.getCiudadBanco())),7, getClass(), false);
        }
    }

}
