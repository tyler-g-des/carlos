package pages.pyt.voucher;

import data.ConstantesTasasImpuestos;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 15/04/2024 6:05 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class VoucherLogica {

    private PoVoucherPage page;
    private VoucherLogica(){
       page = PoVoucherPage.getNewPage();
    }
    public static VoucherLogica getNewLogica(){
        return new VoucherLogica();
    }

    /**
     *
     * @param monto
     * @param comision US$ 5.00
     * @param numProductOrigen
     * @param nombreProductOrigen
     * @param aliasProductOrigen
     * @param numProductbeneficiario
     * @param nombreBeneficiario
     * @param aliasbeneficiario
     * @param crearReporte
     */
    public void validarDataPagoInstante(String monto,String monedaMonto,String comision,String monedaImpuesto,String numProductOrigen, String nombreProductOrigen, String aliasProductOrigen, String numProductbeneficiario, String nombreBeneficiario, String aliasbeneficiario,String descripcion,boolean crearReporte){
//        String comision;
//        if (monto.contains("RD")){
//            comision = "RD$ 100.00";
//        }
        page.imprimirMensajePresentacion(crearReporte)
                .imprimirEstadoTransaccion(crearReporte)
                .validarProductosConBeneficiario(
                        numProductOrigen,
                        nombreProductOrigen,
                        aliasProductOrigen,
                        numProductbeneficiario,
                        nombreBeneficiario,
                        aliasbeneficiario)
                .validarComision(comision)
                .validarImpuesto(monedaImpuesto+"$ " + ConstantesTasasImpuestos.calcularImpuesto(monto))
                .validarMontoDescripcion(monedaMonto+"$ "+monto,descripcion);
    }

    public void validarVoucherPagoInstante(boolean crearReporte){
        page.imprimirMensajePresentacion(crearReporte)
                .validarProductosConBeneficiario("","","","","","")
                .validarDescripcion("")
                .validarMonto("","")
                .validarComision("")
                .validarImpuesto("")
                .validarTipoDeTransaccion("Transaccion entre productos BHD y a otros Bancos ( LBTR )");
    }


    public VoucherLogica validarTransferenciaPagoInstente(boolean crearReporte){
        page.imprimirMensajePresentacion(crearReporte);
        //page.validarPagoInstante("","","",false,"","","","","","","");
        String estado = getStatusTransaccion(true);
        if (estado.equalsIgnoreCase("Completada") || estado.equalsIgnoreCase("En proceso")){
            page.getNumeroConfirmacion(crearReporte);
        }

        return VoucherLogica.getNewLogica();
    }

    public String getStatusTransaccion(boolean crearReporte){
        return page.getStatus(crearReporte);
    }
}
