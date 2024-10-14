package pages.pyt.transferencias.regionalesipa;

import pages.PoAccionesRepetitivas;
import pages.pyt.PoModalConfirmacion;
import pages.pyt.voucher.PoVoucherPage;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 08/03/2024 9:56 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class LogicaTransferenciaRegionalSIPA {

    private POTransferenciasRegionalesSIPA pageSIPA;
    private LogicaTransferenciaRegionalSIPA(){
        pageSIPA = POTransferenciasRegionalesSIPA.getNewPage();
    }

    public static LogicaTransferenciaRegionalSIPA getNewLogica(){
        return new LogicaTransferenciaRegionalSIPA();
    }

    public String transferencia(String productOrigen, String nombre, String numIdentificacion, String pais, String calle, String correo,String nombreBanco, String numProducto, String monto, String descripcion,boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        pageSIPA.setFormulario(productOrigen,nombre,numIdentificacion,pais,calle,correo,nombreBanco,numProducto,monto,descripcion,crearReporte);

        PoModalConfirmacion.getNewPagina().confirmarTransaccion("1111", crearReporte);

        PoVoucherPage.getNewPage().imprimirMensajePresentacion(crearReporte);

        return PoVoucherPage.getNewPage().getStatus(crearReporte);
    }
}
