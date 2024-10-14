package pages.pyt.transferencias.entremisproductos;

import accion.Accion;
import microservicios.Utilidad;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import static microservicios.Utilidad.by;
import static pages.pyt.XpathTransacciones.*;

public class PoTarjetaPrepago extends BasePage {

    // Usuario: 00100010891
    private Accion accion;
    public PoTarjetaPrepago(WebDriver webDriver) {
        super(webDriver);
        accion = new Accion(driver);
    }

    private void setCombo(String productOrigen, String productDestino, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        String textoDeLeyenda = "";
        if (productOrigen.length() == 16){
            textoDeLeyenda = "Pago tarjeta prepago a cuentas";
            productOrigen = Utilidad.enmascararTC( productOrigen );
        }else {
            productDestino = Utilidad.enmascararTC( productDestino );
        }
        accion.selectDropdownIBP(COMBO_ORIGEN, productOrigen,"PRODUCTO ORIGEN",20, getClass(), crearReporte); // COMBO_ORIGEN
        accion.selectDropdownIBP(COMBO_DESTINO, productDestino,"PRODUCTO DESTINO",20, getClass(), crearReporte); // COMBO_ORIGEN
        accion.getText(by("//legend/span[contains(.,'"+textoDeLeyenda+"')]"),10, getClass(),false);

        accion.writeOn(TXT_MONTO, monto,"MONTO",1, getClass(), crearReporte);
        accion.writeOn(TXT_DESCRIPCION, descripcion,"DESCRIPCION",1, getClass(), crearReporte);
        accion.selectCheckBox(CHECK_ADD_TRANSACCION_FAVORITA,"ADICIONAR TRANSACCION FAVORITA",addTransaccionFavorita,1,getClass(),crearReporte);
    }


}
