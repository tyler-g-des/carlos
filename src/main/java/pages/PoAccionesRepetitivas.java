package pages;

import accion.Accion;
import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;
import static microservicios.Utilidad.by;
import static pages.XpathComunes.*;
import static pages.XpathComunes.txtCodigoTDC;
import static pages.pyt.XpathTransacciones.*;
import static pages.pyt.accesosrapidos.beneficiariosinscritos.XpathBeneficiarios.msgNoSeEncontroRegistro;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 18/02/2024 2:59 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PoAccionesRepetitivas extends BasePage{

    protected static By tituloTarjetaDeClaves = By.xpath("//div[img[@src='assets/img/modal-logo.svg']] /div[@class[contains(.,'title')]  and contains(.,'Tarjeta de claves')]");
    protected static By txtCodigoTDC = By.xpath("//ibp-token-tdc//div[div/img[@src='assets/img/tarjeta-clave-logo.png'] ] //input[@placeholder='XXXX']");
    protected static By parrafoTDC = By.xpath("//ibp-token-tdc//h4[contains(.,'Ingresa el código solicitado de tu tarjeta de claves')]");
    private static final By BTN_CANCELAR_MODAL = By.xpath("//div[@role='dialog']//button[@ng-reflect-label='Cancelar']");

    private final By msgPermitirNotificaciones = By.xpath("//div[img[@src[contains(.,'img/notification')]] and h2[contains(.,'Permitir notificaciones')]]/p[contains(.,'envío de notificaciones')]");
    private final By btnEntiendo = By.xpath("//button[span[contains(.,'Entendido')]]");
    private final By tituloPagosTransferencias = By.xpath("//form[@novalidate and //p-dropdown]//h3[contains(.,'Pagos')]");
    private final By comboTipoTransaccion = By.xpath("//form[@novalidate and //h3[contains(.,'Pagos')]] //p-dropdown//span");

    private static final String TEXT_BTN_CONTINUAR = "BOTON CONTINUAR";
    private static final String MSG_AL_GUARDAR_BENEFICIARIO = "//p-dialog[//img[@src='assets/img/modal-logo.svg']]//div[em[@class[contains(.,'%s')]] ]/h3[contains(.,'%s')]";
    private final By msgGuardadoCorrectamente = By.xpath(format(MSG_AL_GUARDAR_BENEFICIARIO, "pi-check-circle bhd-green-2","El beneficiario ha sido guardado correctamente"));
    private final By msgGuardadoAnteriormente = By.xpath(format(MSG_AL_GUARDAR_BENEFICIARIO,"pi-sign-alert bhd-orange","Este beneficiario ha sido inscrito anteriormente"));
    private final By btnSalir = By.xpath("//button[span[contains(.,'Salir')]]");
    private final By btnContinuarDeshabilitado = By.xpath("//button[@ng-reflect-label='Continuar' and @ng-reflect-loading='true']");

    public static final By preguntaEliminarBeneficiario = By.xpath("//div[@role='dialog' and div[//img and div[contains(.,'Confirmar operación')]] ] /div[div//em[@class[contains(.,'alert bhd-orange')]]]  //h3[contains(.,'¿Estás seguro de que quieres eliminar este beneficiario?')]");
    private final By btnEliminar = By.xpath("//div[@role='dialog']//button[@ng-reflect-label='Eliminar']");

    private final Accion accion;


    private PoAccionesRepetitivas(WebDriver webDriver) {
        super(webDriver);
        accion = Accion.getAcciones();
    }

    /** <h3> Retorna una nueva instancia de la clase  PoAccionesRepetitivas </h3>
     * @return PoAccionesRepetitivas
     */
    public static PoAccionesRepetitivas getNewAccionesRepetitivasPage(){
        return new PoAccionesRepetitivas(BaseTest.getDriver());
    }

    /** <h3>Toma captura de pantalla del modal de TCD cuando este se despliega y luego llena el campo del código de tarjeta de claves.</h3>
     * @param codigoDigital Permite llenar el campo Código de tarjeta de clave. Enviar <a href='#'>NA</a> si no se quiere interactuar con el campo, esto en caso de que este deshabilitado el código de tarjeta de clave para transacciones entre mis productos.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    private void setModalTDC(String codigoDigital, boolean crearReporte){
        String text = accion.getText( tituloTarjetaDeClaves,2, getClass(), false) +"<br>"+
                accion.getText( parrafoTDC,2, getClass(), false);
        accion.crearPaso( text,true, crearReporte, crearReporte);
        accion.writeMaskedOn( txtCodigoTDC, codigoDigital,"CAMPO CLAVE TDC",1, getClass(), crearReporte);
    }

    /** <h3> Toma captura de pantalla antes de presionar el botón aguardar, completa el modal TDC para luego presionar el botón continuar. </h3>
     * @param codigoDigital
     * @param crearReporte
     */
    public void guardarConfirmarTDC(String codigoDigital, boolean crearReporte){
        accion.clickOn( BTN_GUARDAR, "BOTON GUARDAR",1, getClass(), crearReporte, crearReporte);
        setModalTDC( codigoDigital, crearReporte);
        accion.clickOn( BTN_CONTINUAR, TEXT_BTN_CONTINUAR,1, getClass(), crearReporte, crearReporte);
        accion.isElementInvisibleNoException( btnContinuarDeshabilitado, 20);
    }

    public void continuarTDC(String codigoDigital, boolean crearReporte){
        setModalTDC( codigoDigital, crearReporte);
        accion.clickOn( BTN_CONTINUAR_MODAL, TEXT_BTN_CONTINUAR,1, getClass(), crearReporte, crearReporte);
        //accion.isElementInvisibleNoException( btnContinuarDeshabilitado, 10);
    }

    /** <h3> Presiona el botón guardar, Completa el modal de TCD, y toma captura de pantalla antes de presionar el botón continuar y espera que se muestre el mensaje: El beneficiario ha sido guardado correctamente. </h3>
     * @param codTDC Permite llenar el código TDC.
     * @param crearReporte
     * @return El beneficiario ha sido guardado correctamente.
     */
    public String guardarBeneficiarioCorrectamente(String codTDC, boolean crearReporte){
        guardarConfirmarTDC( codTDC, crearReporte );
        String msg = accion.getText( msgGuardadoCorrectamente ,25, getClass(), crearReporte);
        accion.clickOn(BTN_CONTINUAR, TEXT_BTN_CONTINUAR,2 , getClass(),false, crearReporte);
        return msg;
    }

    /** <h3> Presiona el botón guardar, Completa el modal de TCD, y toma captura de pantalla antes de presionar el botón continuar y espera que se muestre el mensaje: Este beneficiario ha sido inscrito anteriormente </h3>
     * @param codTDC Permite llenar el código TDC.
     * @param crearReporte
     * @return Este beneficiario ha sido inscrito anteriormente
     */
    public String guardarBeneficiarioExistente(String codTDC, boolean crearReporte){
        guardarConfirmarTDC( codTDC, crearReporte );
        String msg = accion.getText(msgGuardadoAnteriormente,10,getClass(),crearReporte);
        accion.clickOn(btnSalir, "BOTON SALIR",1,getClass(),false,crearReporte);
        accion.isElementInvisibleNoException( btnContinuarDeshabilitado, 2);
        return msg;
    }

    public void modalEliminarBeneficiario(boolean crearReporte){
        accion.getText(preguntaEliminarBeneficiario, 3, getClass(), crearReporte);
    }

    /** <h3> Toma captura del modal para eliminar un beneficiario, presiona el botón eliminar y espera que se muestre el mensaje: No se encontraron registros para mostrar. </h3>
     * @param crearReporte
     * @return No se encontraron registros para mostrar
     */
    public String confirmarEliminarBeneficiario(boolean crearReporte){
        accion.getText(preguntaEliminarBeneficiario, 3, getClass(), crearReporte);
        accion.clickOn(btnEliminar,"BOTON ELIMINAR",2, getClass(),false, crearReporte);
        accion.isElementInvisibility(preguntaEliminarBeneficiario, 20, getClass());
        return accion.getText(msgNoSeEncontroRegistro,3, getClass(), crearReporte);
    }

    public void confirmarEliminarBeneficiarioVoid(boolean crearReporte){
        accion.getText(preguntaEliminarBeneficiario, 3, getClass(), crearReporte);
        accion.clickOn(btnEliminar,"BOTON ELIMINAR",2, getClass(),false, crearReporte);
        accion.isElementInvisibility(preguntaEliminarBeneficiario, 20, getClass());
    }

    /** <h3>Permite toma captura del modal, completa el campo código de tarjeta de clave y luego presiona el botón cancelar.</h3>
     * @param codigoDigital Permite llenar el campo Código de tarjeta de clave. Enviar NA si no se quiere interactuar con el campo.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public void cancelarTDC(String codigoDigital, boolean crearReporte){
        setModalTDC(codigoDigital, crearReporte);
        accion.clickOn(BTN_CANCELAR_MODAL, "BOTON CANCELAR",1, getClass(), crearReporte, crearReporte);
    }

    /** <h3> Este método acepta el modal de notificaciones. </h3>
     * @param crearReporte
     */
    public void modalNotificaciones(boolean crearReporte){
        String titulo = accion.getText(tituloPagosTransferencias, 20, getClass(),false);

        accion.isElementVisible(COMBO_TIPO_TRANSACCION,30,getClass());
        if (accion.isElementVisibleNoException(tituloPagosTransferencias,0)) {

            accion.getText(msgPermitirNotificaciones, 1, getClass(), false);
            accion.clickOn(btnEntiendo, "BOTON ENTIENDO", 1, getClass(), false, false);
            accion.isElementInvisibleNoException(btnEntiendo, 5);
        }

        if (titulo.equals("Pagos y Transferencias")){
            if (accion.getText(comboTipoTransaccion,0, getClass(),false).equals("Seleccione el tipo de transacción...")){
                accion.crearPaso(titulo, true, crearReporte, crearReporte);
            }else {
                accion.crearPaso(accion.getText(comboTipoTransaccion, 0, getClass(), false), true, crearReporte, crearReporte);
            }
        }else {
            accion.crearPaso(titulo, true, crearReporte, crearReporte);
        }
    }

    public String validarMoneda(){
        String combOrigen = accion.getText(COMBO_ORIGEN,1,getClass(),false);
        String comboDestino = accion.getText(COMBO_DESTINO,1,getClass(),false);
        String moneda = "null";

        if (combOrigen.contains("RD$") && comboDestino.contains("RD$")){
             moneda =  accion.getText( by( format(XPATH_COMBO_MONEDA,"Pesos") ),2,getClass(), false).replace("Pesos","RD$");
        } else if (combOrigen.contains("US$") || comboDestino.contains("US$")){
            moneda = accion.getText( by( format(XPATH_COMBO_MONEDA,"Dólares") ),2,getClass(), false).replace("Dólares","US$");
        }else if (combOrigen.contains("EU€") || comboDestino.contains("EU€")){
           moneda = accion.getText( by( format(XPATH_COMBO_MONEDA,"Euros") ),2,getClass(), false).replace("Euros","EU€");
        }else if (combOrigen.contains("RD$")){
            moneda = accion.getText( by( format(XPATH_COMBO_MONEDA,"Pesos") ),2,getClass(), false).replace("Pesos","RD$");
        }
        return moneda;
    }

    private By tituloContratoACHPagoInstante = By.xpath("//div[@role='dialog']//div[contains(.,'Contrato de transferencia ACH y Pagos al instante BCRD')]");
    private By contenidoContrato = By.xpath("//div[@role='dialog']//div[@class[contains(.,'dialog-content')] and div[contains(.,'Para activar este servicio')] and //p[contains(.,'Términos y Condiciones')]] //img[@src='https://static.bhd.com.do/image_160_0380742b54.png']");

    private void contractosACHPagosInstenteBCRD(String codigoTDC,boolean crearReporte){
        accion.getText(tituloContratoACHPagoInstante,4,getClass(),crearReporte);
        accion.focusOnPress(contenidoContrato, 3, getClass());
        accion.writeMaskedOn(txtCodigoTDC,codigoTDC,"CAMPO CODIGO TDC",3,getClass(),crearReporte);
    }

    public void aceptarContratoACHPagoInstante(String codigoTDC,boolean crearReporte){
        contractosACHPagosInstenteBCRD( codigoTDC, crearReporte);
        accion.clickOn(BTN_ACEPTAR_MODAL,"BOTON ACEPTAR",1,getClass(), crearReporte, crearReporte);
        accion.isElementInvisibility(BTN_ACEPTAR_MODAL, 10, getClass());
    }

    private final By btnRealizarTransaccion = By.xpath("//div[@role='dialog']//button[span[contains(.,'Realizar transacción')]]");

    public void realizarTransaccion(String codigoTDC,boolean crearReporte){
        accion.writeMaskedOn(txtCodigoTDC,codigoTDC,"CAMPO CODIGO TDC",1,getClass(),crearReporte);
        accion.clickOn(btnRealizarTransaccion, "BOTON REALIZAR TRANSACCION",1,getClass(),crearReporte, crearReporte);
        accion.isElementInvisibleNoException(btnRealizarTransaccion, 20);
    }

}
