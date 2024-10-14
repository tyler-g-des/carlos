package pages.pyt.transferencias.pinpesos;

import data.Persistencia;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;
import pages.PoAccionesRepetitivas;
import pages.dashboard.DashboardPage;
import pages.pyt.PoModalConfirmacion;
import pages.pyt.voucher.PoVoucherPage;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/03/2024 7:47 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PinPesosLogic {

    private final PoPinPesos page;

    private PinPesosLogic(){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(true);
        page = PoPinPesos.getPage();
    }

    public static PinPesosLogic getNewLogica(){
        return new PinPesosLogic();
    }

    public String transferencia(String productOrigen, String productDestino, String monto, String descripcion, boolean addTransaccionFavorita,boolean crearReporte){
        page.setFormTransferenciaBeneficiarioInscrito(productOrigen, productDestino, monto, descripcion, addTransaccionFavorita, crearReporte);
        MsBeneficiariosNacionales benef = new MsBeneficiariosNacionales(Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente())).newQuery().selecPinPesos().buscar(productDestino);
        System.out.println("NOMBRE: "+ benef.getNombre());
        System.out.println("ALIAS: "+ benef.getAlias());
        PoModalConfirmacion.getNewPagina().confirmarTransaccion("1111", crearReporte);

        PoVoucherPage.getNewPage().imprimirMensajePresentacion(crearReporte);
        return PoVoucherPage.getNewPage().getStatus(crearReporte);
    }

    public String transfenciaNuevoBeneficiario(String productOrigen, String alias, String  telefono, String tipoDocumento, String numDocumento, String nombre, String pais, String genero, String monto, String descripcion, boolean addBeneficiarioFavorito, boolean addTransaccionFavorita, boolean crearReporte){

        //System.out.println(DashboardPage.getPage().getNombreCliente());

        page.setFormTransferenciaNuevoBeneficiario(productOrigen,alias,telefono,tipoDocumento,numDocumento,nombre,pais,genero,monto,descripcion,addBeneficiarioFavorito,addTransaccionFavorita,crearReporte);
        page.modalConfirmacionNuevoBeneficiario(productOrigen,alias,nombre,telefono,monto,crearReporte); // Cuidado

        //MsBeneficiariosNacionales benef = new MsBeneficiariosNacionales(Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente())).newQuery().selecPinPesos().buscar(productDestino);
       // System.out.println("NOMBRE: "+ benef.getNombre());
        //System.out.println("ALIAS: "+ benef.getAlias());

//        Descoemtnar
//        PoModalConfirmacion.getNewPagina().confirmarTransaccion("1111", crearReporte);
//        PoVoucherPage.getNewPage().imprimirMensajePresentacion(crearReporte);


        return"" ;// Descomentar PoVoucherPage.getNewPage().getStatus(crearReporte);
        //page.modalConfirmacion(monto,crearReporte);
        //System.out.println(Persistencia.getInstance().esEmpleado(DashboardPage.getPage().getNombreCliente()));
        // page.setCombox( productOrigen,"Nuevo beneficiario", crearReporte);
        //page.setBeneficiario( alias, telefono, tipoDocumento, numDocumento, nombre, pais, genero, crearReporte);
    }
}
