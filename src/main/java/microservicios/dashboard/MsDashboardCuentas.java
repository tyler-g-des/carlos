package microservicios.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.RequestMicroServiciosIBP;
import microservicios.Utilidad;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 16/04/2024 8:41 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsDashboardCuentas {

    private Response response;
    private JsonNode productos;
    private JsonNode producto;
    String documentNumber = "";
    private int statusCode =0;

    public MsDashboardCuentas(){
    }

    public MsDashboardCuentas newQuery(String documentNumber){
        new MsDashboardCuentas();
        response = RequestMicroServiciosIBP.newQuery().dashboardCuentas(documentNumber);
        productos = Utilidad.jsonNodeParse(response);
        System.out.println(productos.size());
        return this;
    }

    public int getToltaProductos(){
        return productos.size();
    }
    public MsDashboardCuentas iterarProductos(int indiceProducto){
        producto = productos.get(indiceProducto);
        return this;
    }

    public int getStatusCode(){
        if (statusCode == 0){
        }
        return response.getStatusCode();
    }

    public MsDashboardCuentas buscar(String accountNumber){
        if (!productos.isNull()){
            for (JsonNode benef : productos){
                if (benef.get("productNumber").asText().equalsIgnoreCase(accountNumber)){
                    producto = benef;
                    break;
                }else {
                    producto = null;
                }
            }
        }
        return this;
    }

    public String getNumProducto(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("productNumber").asText();
        }
        return texto;
    }

    public String getAlias(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("alias").asText();
        }
        return texto;
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
        String texto = "null";
        if (producto != null){
            texto = producto.get("name").asText();
        }
        return texto;
    }

    public String getMoneda(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("currency").asText();
        }
        return texto;
    }

    public String getEstadoProducto(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("status").asText();
        }
        return texto;
    }

    public String getTipoProducto(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("type").asText();
        }
        return texto;
    }

    public String getTipoProductoResumido(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("type").asText()
                    .replace("Cuentas Corrientes","Corriente")
                    .replace("Cuentas de Ahorro","Ahorros");
        }
        return texto;
    }

    public String getTipoProductoSigla(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("productType").asText();
        }
        return texto;
    }

    public String getBalance(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("balance").asText();
        }
        return texto;
    }

    public String getBalanceEnTransito(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("transitBalance").asText();
        }
        return texto;
    }

    public float getBalanceFlotante(){
        double texto = 0;
        if (producto != null){
            texto = producto.get("balance").asDouble();
        }
        return Float.parseFloat(String.valueOf(texto));
    }


}
