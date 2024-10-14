package microservicios.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.Utilidad;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/06/2024 2:51 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsDashboardTarjetaCredito {

    private MicroServiciosDashboard microServiciosDashboard;
    private Response response;
    private String documentNumber;
    private JsonNode tarjetasCredito;
    private JsonNode tarjetaCredito;
    private String mensaje;

    public MsDashboardTarjetaCredito(){

    }

    public MsDashboardTarjetaCredito(String documentNumber){
        this.documentNumber = documentNumber;
        response = new MicroServiciosDashboard().getTarjetasCredito(documentNumber);
        tarjetasCredito = Utilidad.jsonNodeParse(response);
    }

    public MsDashboardTarjetaCredito newQuery(String documentNumber){
        this.documentNumber = documentNumber;
        response = new MicroServiciosDashboard().getTarjetasCredito(documentNumber);
        tarjetasCredito = Utilidad.jsonNodeParse(response);
        return this;
    }

    public MsDashboardTarjetaCredito newQuery(){
        response = new MicroServiciosDashboard().getCuentas(documentNumber);
        tarjetasCredito = Utilidad.jsonNodeParse(response);
        return this;
    }

    public MsDashboardTarjetaCredito buscar(String accountNumber){
        mensaje = "";
        if (tarjetasCredito.isEmpty()){
            mensaje = "El usuario no contiene tarjetas de credito";
        }else {
            for (JsonNode benef : tarjetasCredito){
                if (benef.get("productNumber").asText().equalsIgnoreCase(accountNumber)){
                    tarjetaCredito = benef;
                    break;
                }else {
                    tarjetaCredito = null;
                    mensaje = "Esta tarjeta de credito no existe";
                }
            }
        }
        return this;
    }

    public int getToltaProductos(){
        if (tarjetasCredito != null){
            return tarjetasCredito.size();
        }
        return 0;
    }
    public MsDashboardTarjetaCredito iterarProductos(int indiceProducto){
        if (tarjetasCredito != null){
            tarjetaCredito = tarjetasCredito.get(indiceProducto);
        }
        return this;
    }



    public String getNumeroProducto(){

        if (tarjetaCredito == null){
            return mensaje;
        }else {
            mensaje = tarjetaCredito.get("productNumber").asText();
        }
        return mensaje;
    }

    public String getTCEnmascarada(){
        return Utilidad.enmascararTC( getNumeroProducto() );
    }

    public String getAlias(){
        if (tarjetaCredito == null){
            return mensaje;
        }else {
            mensaje = tarjetaCredito.get("alias").asText();
        }
        return mensaje;
    }

    public String getTituloProducto(){
        String texto = "null";
        if (getAlias().equals("")){
            texto = getNombreProducto();
        }else {
            texto = getAlias();
        }
        return texto;
    }

    public String getNombreProducto(){
        if (tarjetaCredito == null){
            return mensaje;
        }else {
            mensaje = tarjetaCredito.get("productName").asText();
        }
        return mensaje;
    }

    public String getMonedaPesos(){
        if (tarjetaCredito == null){
            return mensaje;
        }else {
            mensaje = tarjetaCredito.get("currency").asText();
        }
        return mensaje;
    }

    public String getMonedaDolar(){
        if (tarjetaCredito == null){
            return mensaje;
        }else {
            mensaje = tarjetaCredito.get("currencyUS").asText();
        }
        return mensaje;
    }

    public boolean isInternacional(){
        boolean isInternacional = false;
        if (tarjetaCredito == null){
            return isInternacional;
        }else {
            isInternacional = tarjetaCredito.get("isInternational").asBoolean();
        }
        return isInternacional;
    }

    public String getEstadoProducto(){
        if (tarjetaCredito == null){
            return mensaje;
        }else {
            mensaje = Utilidad.formatearPrimeraLetraMayusc( tarjetaCredito.get("productStatus").asText() ) ;
        }
        return mensaje;
    }

//    public String getTipoProducto(){
//        if (tarjetaCredito == null){
//            return mensaje;
//        }else {
//            mensaje = tarjetaCredito.get("type").asText();
//        }
//        return mensaje;
//    }

    public String getTipoProducto(){
        String texto = "null";
        if (tarjetaCredito != null){
            texto = tarjetaCredito.get("productBrand").asText();
        }
        return texto;
    }

    public String getTipoProductoSigla(){
        if (tarjetaCredito == null){
            return mensaje;
        }else {
            mensaje = tarjetaCredito.get("productType").asText();
        }
        return mensaje;
    }

    public String getBalancePesos(){
        if (tarjetaCredito == null){
            return mensaje;
        }else {
            mensaje = Utilidad.darFormatoMonedaFinal( tarjetaCredito.get("pesosBalance").asText() );
            if (mensaje.equals("-1.0")){
                mensaje = "No disponible";
            }
        }
        return mensaje;
    }

    public String getBalancePesosConMoneda(){
        String balance = getBalancePesos();
        if (!balance.equals("No disponible")){
            balance = getMonedaPesos()+"$ " + balance;
        }

        return balance;
    }

    public String getBalanceDolarMoneda(){
        String balance = getBalanceDolar();
        if (!balance.equals("No disponible")){
            balance = getMonedaDolar()+"$ " + balance;
        }

        return balance;
    }

    public String getBalanceDolar(){
        if (tarjetaCredito == null){
            return mensaje;
        }else {
            mensaje = Utilidad.darFormatoMonedaFinal( tarjetaCredito.get("dollarBalance").asText() ) ;
            if (mensaje.equals("-1.0")){
                mensaje = "No disponible";
            }
        }
        return mensaje;
    }


    public boolean getBalanceFlotante(){
        boolean isInternacional = false;
        if (tarjetaCredito == null){
            return isInternacional;
        }else {
            return tarjetaCredito.get("isInternational").asBoolean();
        }
    }

}
