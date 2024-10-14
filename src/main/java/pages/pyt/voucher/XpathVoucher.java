package pages.pyt.voucher;

import org.openqa.selenium.By;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 15/04/2024 4:28 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class XpathVoucher {

    private XpathVoucher(){

    }

    protected static final String XPATH_PRODUCTO_ORIGEN = "//div[strong[contains(.,'Producto origen:')] and h5[contains(.,'02016990062')]]";
    protected static final String XPATH_PRODUCTO_ORIGEN_COMPLETO = "//div[strong[contains(.,'Producto origen:')] and h5[contains(.,'%s - %s')]]";

    //protected static final String XPATH_PRODUCTO_DESTINO = "//div[strong[contains(.,'Producto destino')] and h5[contains(.,'4678765342987213')]]";
    //protected static String xpathProductoDestino = "//div[strong[contains(.,'Producto destino')] and h5[contains(.,'%s - %s')]]";

    /**
     * Param: Alias o nombre
     * Param: numero Producto
     */
    protected static final String XPATH_PRODUCTO_DESTINO_ALIAS = "//div[strong[contains(.,'Producto destino')] and h5[contains(.,'%s - %s')]]";

    protected static final String XPATH_DESCRIPCION = "//div[strong[contains(.,'Descripción')] and h5[contains(.,'%s')]]";
    protected static By referencia = By.xpath("//div[strong[contains(.,'Referencia')] ]/h5");

    /**
     * Param: Tipo de Transacciones: <br>Transaccion entre productos BHD y a otros Bancos ( LBTR ) <br>
     */
    protected static final String XPATH_TIPO_TRANSACCION = "//div[strong[contains(.,'Tipo de transacción')] and h5[contains(.,'%s')]]";

    /**
     * Param: Moneda: US$, RD$, EU$
     * Param: Monto
     */
    protected static final String XPATH_MONTO = "//div[strong[contains(.,'Monto')] ] //h5[contains(.,'%s$ %s')]";
    protected static final String XPATH_MONTO_COMPLETO = "//div[strong[contains(.,'Monto')]]/h5[contains(.,'%s')]";

    /**
     * Param: Moneda: US$, RD$, EU$
     * Param: Monto
     */
    protected static final String XPATH_COMISION = "//div[strong[contains(.,'Comisión')] and h5[contains(.,'%s$ %s')]]";
    protected static final String XPATH_MONEDA_COMISION = "//div[strong[contains(.,'Comisión')]]/h5[contains(.,'%s')]";
    protected static final String XPATH_TOTAL = "//div[strong[contains(.,'Total transacción')]]/h5[contains(.,'%s')]";

    /**
     * Param: Moneda: US$, RD$, EU$
     * Param: Monto
     */
    protected static final String XPATH_IMPUESTO = "//div[strong[contains(.,'Impuesto')] and h5[contains(.,'%s$ %s')]]";
    protected static final String XPATH_MONEDA_IMPUESTO = "//div[strong[contains(.,'Impuesto')]]/h5[contains(.,'%s')]";
    protected static By labelNumConfirmacion = By.xpath("//div/strong[contains(.,'Número de confirmación:')]");

    /**
     * Param: Nombre cliente
     */
    protected static final String XPATH_MSG_RESULTADO_TRANSACCION = "//strong[contains(.,'¡%S, a continuación presentamos el resultado de tu transacción!:')]";
    protected static By labelStatus = By.xpath("//div[div[@class[contains(.,'status')] ]]/h5");
    protected static By mensajErrorRechazo = By.xpath("//div[div[@class[contains(.,'status')] ]]/h5[2]");



}
