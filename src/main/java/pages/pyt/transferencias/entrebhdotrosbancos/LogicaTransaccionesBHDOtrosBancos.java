package pages.pyt.transferencias.entrebhdotrosbancos;

import data.Persistencia;
import microservicios.MsContratos;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;
import pages.PoAccionesRepetitivas;
import pages.dashboard.DashboardPage;
import pages.pyt.PoModalConfirmacion;
import pages.pyt.accesosrapidos.beneficiariosinscritos.PoBeneficiariosNacionales;
import pages.pyt.voucher.PoVoucherPage;
import pages.pyt.voucher.VoucherLogica;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 11/03/2024 11:29 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class LogicaTransaccionesBHDOtrosBancos {

    private final POTransaccionesBHDOtrosBancos page;
    private PoBeneficiariosNacionales beneficiario;
    private LogicaTransaccionesBHDOtrosBancos(){
        page = POTransaccionesBHDOtrosBancos.getNewPage();
        beneficiario = PoBeneficiariosNacionales.getNewPagina();
    }

    public static LogicaTransaccionesBHDOtrosBancos getNewLogica(){
        return new LogicaTransaccionesBHDOtrosBancos();
    }

    public void transferenciaATerceroBHD(String productOrigen, String productDestino,boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        page.setComboATerceroBHD(productOrigen,productDestino,crearReporte);
    }

    public void transferenciaTerceroBHDNuevoBeneficiario(String productOrigen, String nombreBanco, String numProducto, String alias, String correo, String monto, String descriocion, boolean addBeneficiarioFavorito, boolean addTransaccionFavorita, boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        //page.setCombox("A tercero en BHD",productOrigen,"Nuevo beneficiario","Transferencia a terceros en BHD",crearReporte);//.setComboATerceroBHD(productOrigen,"",crearReporte);
        //PoBeneficiariosNacionales.getNewPagina().setBeneficiarioNacional(nombreBanco,numProducto,alias,correo,crearReporte);
        //page.setFormularioTerceroBHDNuevoBeneficiario( productOrigen, "NA","NA","NA","NA","NA","NA",false,false, crearReporte);
        //PoBeneficiariosNacionales.getNewPagina().setBeneficiarioNacional(nombreBanco,numProducto,alias, correo,crearReporte);
        page.setFormularioTerceroBHDNuevoBeneficiario( productOrigen, nombreBanco,numProducto,alias,correo,monto,descriocion,addBeneficiarioFavorito,addTransaccionFavorita, crearReporte);
    }

    public String transferenciaACHInscrito(String productOrigen, String productDestino, String monto, String correo, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        MsBeneficiariosNacionales beneficiariosNacionales = new MsBeneficiariosNacionales(Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente()));
        beneficiariosNacionales.newQuery().selecOtrosBancos().buscar(productDestino);
        page.setComboATerceroACHOtrosBancos(productOrigen,beneficiariosNacionales.getNumProductoEnmascarado(),crearReporte);
        page.setCampos(monto, correo, descripcion, addTransaccionFavorita, crearReporte);

        PoModalConfirmacion.getNewPagina().confirmarTransaccion("1111", crearReporte);
        PoVoucherPage.getNewPage().imprimirMensajePresentacion(crearReporte);
        return PoVoucherPage.getNewPage().getStatus(crearReporte);
    }

    public String transferenciaACHNuevoBeneficiario(String productOrigen, String nombreBanco, String tipoProducto, String numProducto, String tipoDocumento, String numDocumento, String nombre, String alias, String pais, String genero, String correo, String monto, String descripcion, boolean addBeneficiarioFavorito, boolean addTransaccionFavorita, boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        page.setComboATerceroACHOtrosBancos(productOrigen,"Nuevo beneficiario",crearReporte);
        beneficiario.setBeneficiarioNacional(nombreBanco, tipoProducto, numProducto,tipoDocumento, numDocumento, nombre, alias, pais, genero, correo, crearReporte);
        page.setCampos(monto,descripcion, addBeneficiarioFavorito, addTransaccionFavorita,crearReporte);

        PoModalConfirmacion.getNewPagina().confirmarTransaccion("1111", crearReporte);
        PoVoucherPage.getNewPage().imprimirMensajePresentacion(crearReporte);
        return PoVoucherPage.getNewPage().getStatus(crearReporte);
    }

    public String transferenciaPagoAlIsntanteInscrito(String productOrigen, String productoDestino, String monto, String correo, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){

        String numeroDocumento = Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente());
        MsContratos contratos = new MsContratos(numeroDocumento);
        MsBeneficiariosNacionales beneficiario = new MsBeneficiariosNacionales(numeroDocumento).newQuery().selecOtrosBancos().buscar(productoDestino);

        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        page.canalEnvioPagoAlInstante( crearReporte );
        page.aceptarContacto( contratos, crearReporte );

        if (beneficiario.esUnaTC()) {
            page.setComboxPagoInstante(productOrigen,beneficiario.getNumProductoEnmascarado(),crearReporte);
        }else {
            page.setComboxPagoInstante(productOrigen,beneficiario.getNumProducto(),crearReporte);
        }

        page.verificarInformacionBeneficiarioOtrosBancos(beneficiario);
        String moneda = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().validarMoneda();

        page.setCampos( monto, correo, descripcion, addTransaccionFavorita, crearReporte);
        page.continuarTransaccion(crearReporte);
        page.continuarModalConfirmacionPagoInstante(productOrigen,moneda,beneficiario,monto,"1111",crearReporte);
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().realizarTransaccion("1111",crearReporte);

        PoModalConfirmacion.getNewPagina().confirmarTransaccion("1111", crearReporte);
        PoVoucherPage.getNewPage().imprimirMensajePresentacion(crearReporte);
        return PoVoucherPage.getNewPage().getStatus(crearReporte);
    }

    public void transferenciaATerceroPagoAlInstanteOtrosBancos(String productOrigen, String productoDestino,boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        page.setComboATerceroPagoAlInstante( productOrigen, productoDestino, crearReporte);
    }

    public VoucherLogica someterPagoAlIsntanteInscrito(String productOrigen, String productoDestino, String monto, String correo, String descripcion, boolean addTransaccionFavorita, boolean crearReporte){

        String numeroDocumento = Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente());
        MsContratos contratos = new MsContratos(numeroDocumento);
        MsBeneficiariosNacionales beneficiario = new MsBeneficiariosNacionales(numeroDocumento).newQuery().selecOtrosBancos().buscar(productoDestino);

        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);
        page.canalEnvioPagoAlInstante( crearReporte );
        page.aceptarContacto( contratos, crearReporte );

        if (beneficiario.esUnaTC()) {
            page.setComboxPagoInstante(productOrigen,beneficiario.getNumProductoEnmascarado(),crearReporte);
        }else {
            page.setComboxPagoInstante(productOrigen,beneficiario.getNumProducto(),crearReporte);
        }

        page.verificarInformacionBeneficiarioOtrosBancos(beneficiario);
        String moneda = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().validarMoneda();

        page.setCampos( monto, correo, descripcion, addTransaccionFavorita, crearReporte);
        page.continuarTransaccion(crearReporte);
        page.continuarModalConfirmacionPagoInstante(productOrigen,moneda,beneficiario,monto,"1111",crearReporte);
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().realizarTransaccion("1111",crearReporte);
        return VoucherLogica.getNewLogica();
    }

    public VoucherLogica someterPagoAlInstanteNuevoBeneficiario(String productOrigen, String nombreBanco,String tipoProducto, String numProducto, String tipoDocumento, String numDocumento, String nombre, String alias, String pais, String genero, String correo,String monto, String descripcion, boolean addBeneficiarioFavorito, boolean addTransaccionFavorita,boolean crearReporte){
        MsContratos contratos = new MsContratos(Persistencia.getInstance().getDocumentNumber(DashboardPage.getPage().getNombreCliente()));
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);

        page.canalEnvioPagoAlInstante(crearReporte);
        page.aceptarContacto(contratos,crearReporte);

        page.setComboxPagoInstante(productOrigen,"Nuevo beneficiario",crearReporte);
        PoBeneficiariosNacionales.getNewPagina().setBeneficiarioNacional(
                nombreBanco,
                tipoProducto,
                numProducto,
                tipoDocumento,
                numDocumento,
                nombre,
                alias,
                pais,
                genero,
                correo,
                true);

        String moneda = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().validarMoneda();

        page.setCampos(monto,descripcion,addBeneficiarioFavorito, addTransaccionFavorita, crearReporte);
        page.continuarTransaccion(crearReporte);
        page.validarDataModalPagoInstenteNuevoBeneficiario(productOrigen,moneda,tipoProducto,alias,nombre,numProducto);
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().realizarTransaccion("1111",crearReporte);
        return VoucherLogica.getNewLogica();
    }

    ///////////////////// VERDADERO CODIGO

    // 03700257789
    public void pantallaContratoACH(String productOrigen, String productoDestino, boolean crearReporte){
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().modalNotificaciones(crearReporte);

        page.setTipoTransaccionCanalEnvio("ACH", crearReporte);
        System.out.println("Tiene preguntas configuradas: " + Persistencia.tienePreguntasConfiguradas(DashboardPage.getPage().getNombreCliente()) );

        // Si tiene contrato debe de aceptarse.

        page.setComboxProductoLeyenda(productOrigen,productoDestino,"Transferencia a terceros de otros bancos (ACH)", crearReporte);
        page.setCampos("120","carlos_loyola@bhd.com.do","Mi descricion", true, crearReporte);
    }
}
