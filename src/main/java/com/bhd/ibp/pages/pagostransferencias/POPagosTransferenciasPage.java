package com.bhd.ibp.pages.pagostransferencias;

import accion.AccionMetodos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POPagosTransferenciasPage extends AccionMetodos {

    public POPagosTransferenciasPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static final By COMBO_TIPO_TRANSACCION = by("//div[label[contains(.,'Tipo de transacción')] ]  /p-dropdown//span");
    public static final By COMBO_ORIGEN = by("//div[label[contains(.,'Producto origen')] ]  /p-dropdown//span");
    public static final By COMBO_DESTINO = by("//div[label[contains(.,'Producto destino')] ]  /p-dropdown//span");

    public static final By TITULO_CONFIRMAR_TRANSACCION = By.xpath("//div[img[@src='assets/img/modal-logo.svg'] ] /div[contains(.,'Confirma tu transacción')]");
    /**
     * @idLabel
     * @nombreProducto
     * @numProducto
     */
    public static final String XPA_PRODUCTO_MODAL_CONFIRMACION = "//div[@role='dialog']//td[@id[contains(.,'%s')] ] /span[contains(.,'%s') and contains(.,'%s')]";


    /**
     * @nombreProducto
     * @numProducto
     */
    public static final String XPA_PRODUCTO_DEBITO_MODAL = "//div[@role='dialog']//td[@id[contains(.,'debitProduct')] ] /span[contains(.,'%s') and contains(.,'%s')]";


    /**
     * @nombreProducto
     * @NumeroProducto
     */
    public static final String XPA_PRODUCTO_CREDITO_MODAL = "//div[@role='dialog']//td[@id[contains(.,'creditBeneficiary')] ] /span[contains(.,'%s') and contains(.,'%s')]";

    /**
     * @impuesto
     */
    public static final String XPA_IMPUESTO_MODAL = "//div[@role='dialog']//td[@id[contains(.,'tax')] ]//span[contains(.,'%s')]";

}
