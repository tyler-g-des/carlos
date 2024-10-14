package pages.pyt.transferencias.entremisproductos;

import pages.PoAccionesRepetitivas;
import pages.pyt.PoModalConfirmacion;
import pages.pyt.voucher.PoVoucherPage;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 23/04/2024 1:38 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class LogicaTransaccionesMisProductos {

    private final PoEntreCuentas page;
    private LogicaTransaccionesMisProductos(){
        page = PoEntreCuentas.getNewPage();
    }

    public static LogicaTransaccionesMisProductos getNewLogica(){
        return new LogicaTransaccionesMisProductos();
    }


    public void transferenciaEntreCuentas(String productOrigen, String productDestino,String monto, String descripcion, boolean addTransaccionFavorita,boolean crearReporte){

        //MsCuentas cuentas = new MsCuentas(Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente())).newQuery();

        //System.out.println(cuentas.buscar(productOrigen).getMoneda());
        //System.out.println(cuentas.buscar(productDestino).getMoneda());
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        page.setCampos(productOrigen, productDestino, monto, descripcion, addTransaccionFavorita, crearReporte);
    }

    public String pruebaNuevoFormulario(String cuentaOrigen, String cuentaDestino, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        page.setFormulario( cuentaOrigen, cuentaDestino, monto, descripcion, addTransaccionFavorita, crearReporte);

        page.modalComfirmacionTransaccion("NA",crearReporte);


        PoModalConfirmacion.getNewPagina().confirmarTransaccion("111", crearReporte);

        // Agreado ahora
        page.setFormulario( cuentaOrigen, cuentaDestino, monto, descripcion, addTransaccionFavorita, crearReporte);
        page.modalComfirmacionTransaccion("1111",crearReporte);




        PoVoucherPage.getNewPage().imprimirMensajePresentacion(crearReporte);
        return PoVoucherPage.getNewPage().getStatus(crearReporte);
    }

    public PoEntreCuentas transferenciaCuentasFechaHoy(String cuentaOrigen, String cuentaDestino, String monto, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        page.setFormulario( cuentaOrigen, cuentaDestino, monto, descripcion, addTransaccionFavorita, crearReporte);
        page.modalComfirmacionTransaccion("1111",crearReporte);
        return this.page;
    }

}
