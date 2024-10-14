package pages.pyt.transferencias.internacional;

import pages.PoAccionesRepetitivas;
import pages.pyt.PoModalConfirmacion;
import pages.pyt.voucher.PoVoucherPage;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 07/03/2024 10:25 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class LogicaTransferenciaInternacional {

    private POtransferenciaInternacional page;

    private LogicaTransferenciaInternacional(){
        page = POtransferenciaInternacional.getNewPage();
        //PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(false);
    }

    public static LogicaTransferenciaInternacional getLogica(){
        return new LogicaTransferenciaInternacional();
    }

    public String transferenciaBeneficiarioInscrito(String productOrigen, String productDestino, String proposito, String moneda, String monto, String correo, String descripcion,boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones( crearReporte );
        page.setFormTransferenciaBeneficiarioInscrito( productOrigen, productDestino, proposito, moneda, monto, correo, descripcion, crearReporte);

        PoModalConfirmacion.getNewPagina().confirmarTransaccion("1111", crearReporte);

        PoVoucherPage.getNewPage().imprimirMensajePresentacion(crearReporte);
        return PoVoucherPage.getNewPage().getStatus(crearReporte);
    }

    public String transferenciaNuevoBeeneficiario(String productOrigen, String nombre, String numIdentificacion, String pais, String calle, String correo, String numProducto,String tipoCodigo, String codigo, boolean usarIntermediario, String numIbanIntermediario, String tipoCodIntermediario, String codIntermediario, String destino, String clasificacion, String proposito, String moneda, String monto, String descripcion, boolean addBeneficiarioFavorito, boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones( crearReporte );
        page.setFormNuevoBeneficiario( productOrigen, nombre, numIdentificacion, pais, calle, correo, numProducto, tipoCodigo, codigo, usarIntermediario, numIbanIntermediario, tipoCodIntermediario, codIntermediario, destino, clasificacion, proposito, crearReporte);
        page.setCampos(moneda,monto,descripcion,addBeneficiarioFavorito,crearReporte);

        PoModalConfirmacion.getNewPagina().confirmarTransaccion("1111", crearReporte);

        PoVoucherPage.getNewPage().imprimirMensajePresentacion(crearReporte);
        return PoVoucherPage.getNewPage().getStatus(crearReporte);
    }


}
