package pages.pyt;

import org.openqa.selenium.By;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/03/2024 2:43 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class XpathTransacciones {

    private XpathTransacciones(){

    }

    public static final By COMBO_TIPO_TRANSACCION = By.xpath("//div[label[contains(.,'Tipo de transacción')]] /p-dropdown//span");
    public static final By COMBO_ORIGEN = By.xpath("//div[label[contains(.,'Producto origen')]] /p-dropdown//span");
    public static final By COMBO_DESTINO = By.xpath("//div[label[contains(.,'Producto destino')]] /p-dropdown//span");
    public static final By TXT_MONTO = By.xpath("//div[label[contains(.,'Monto')]]//input");
    public static final By COMBO_MONEDA = By.xpath("//div[label[contains(.,'Moneda')]] /p-dropdown//span");
    public static final By TXT_CORREO = By.xpath("//div[label[contains(.,'Correo')]] //input");
    public static final By TXT_DESCRIPCION = By.xpath("//div[label[contains(.,'Descripción')]] //input");
    public static final String XPATH_LEYENDA = "//legend/span[contains(.,'%s')]";
    public static final By CHECK_ADD_TRANSACCION_FAVORITA = By.xpath("//p-checkbox[@name='transactionFavorite']//div");
    public static final By CHECK_ADD_BENEFICIARIO_FAVORITO = By.xpath("//p-checkbox[@name='beneficiaryFavorite']//div");
    /**
     * param: Texto que contiene el parrafo del disclaimer.
     */
    public static final String XPATH_DISCLAIMER = "//div[@role='alert' and @class[contains(.,'message-info')]]//p[contains(.,'%s')]";


}
