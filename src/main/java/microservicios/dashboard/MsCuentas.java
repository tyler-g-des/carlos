package microservicios.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.Utilidad;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 05/06/2024 9:54 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsCuentas {

    private MicroServiciosDashboard microServiciosDashboard;
    private Response response;
    private String documentNumber;
    private JsonNode cuentas;
    private JsonNode cuenta;
    private String mensaje;

    public MsCuentas(String documentNumber){
        this.documentNumber = documentNumber;
        response = new MicroServiciosDashboard().getCuentas(documentNumber);
        cuentas = Utilidad.jsonNodeParse(response);
    }
    public MsCuentas(){
    }

    public MsCuentas newQuery(String documentNumber){
        this.documentNumber = documentNumber;
        response = new MicroServiciosDashboard().getCuentas(documentNumber);
        cuentas = Utilidad.jsonNodeParse(response);
        return this;
    }

    public MsCuentas newQuery(){
        response = new MicroServiciosDashboard().getCuentas(documentNumber);
        cuentas = Utilidad.jsonNodeParse(response);
        return this;
    }


    public int getToltaProductos(){
        if (cuentas != null){
            return cuentas.size();
        }
        return 0;
    }
    public MsCuentas iterarProductos(int indiceProducto){
        if (cuentas != null){
            cuenta = cuentas.get(indiceProducto);
        }
        return this;
    }


    public MsCuentas buscar(String accountNumber){
        mensaje = "";
        cuenta = null;
        System.out.println(cuentas);
        if (cuentas.isEmpty()){
            mensaje = "El usuario no contiene cuentas";
        }else {
            if (cuentas.size()>0){
                for (JsonNode benef : cuentas){
                    if (benef.get("productNumber").asText().equalsIgnoreCase(accountNumber)){
                        cuenta = benef;
                        break;
                    }else {
                        cuenta = null;
                        mensaje = "Esta cuenta no existe";
                    }
                }
            }
        }
        return this;
    }


    public String getNumProducto(){
        String texto = "null";
        if (cuenta != null){
            texto = cuenta.get("productNumber").asText();
        }
        return texto;
    }

    public String getAlias(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("alias").asText();
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
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("name").asText();
        }
        return mensaje;
    }

    public String getMoneda(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("currency").asText();
        }
        return mensaje;
    }

    public String getEstadoProducto(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = Utilidad.formatearPrimeraLetraMayusc(cuenta.get("status").asText());
        }
        return mensaje;
    }

    public String getTiposProducto(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("type").asText();
        }
        return mensaje;
    }

    public String getTipoProducto(){
        return getTiposProducto().replace("Cuentas de Ahorro","Cuenta de ahorros").replace("Cuentas Corrientes","Cuenta corriente");
    }

    public String getTipoProductoResumido(){
        String texto = "null";
        if (cuenta != null){
            texto = cuenta.get("type").asText()
                    .replace("Cuentas Corrientes","Corriente")
                    .replace("Cuentas de Ahorro","Ahorros");
        }
        return texto;
    }

    public String getTipoProductoSigla(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("productType").asText();
        }
        return mensaje;
    }

    public String getBalance(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = Utilidad.darFormatoMonedaFinal(cuenta.get("balance").asText() );
        }
        return mensaje;
    }

    public String getBalanceEnTransito(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = Utilidad.darFormatoMonedaFinal(cuenta.get("transitBalance").asText());
        }
        return mensaje;
    }

    public float getBalanceFlotante(){

        if (cuenta == null){
            return 0;
        }else {
            return Float.parseFloat(String.valueOf(cuenta.get("balance").asDouble()));
                    //return cuenta.get("balance").asDouble();
        }


//        double texto = 0;
//        if (cuenta != null){
//            texto = cuenta.get("balance").asDouble();
//        }
//        return Float.parseFloat(String.valueOf(texto));
    }



//    public static void main(String[] args) {
//        MsCuentas cuentas = new MsCuentas();
//        cuentas.newQuery("00111435384").buscar("29310770016");
//        System.out.println(cuentas.getNombreProducto());
//        System.out.println(cuentas.getNombreProducto());
//        System.out.println(cuentas.getAlias());
//        System.out.println(cuentas.getBalanceFlotante());
//
//    }
}
