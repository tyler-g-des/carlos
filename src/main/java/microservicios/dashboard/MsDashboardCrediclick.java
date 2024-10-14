package microservicios.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.RequestMicroServiciosIBP;
import microservicios.Utilidad;

import java.util.Optional;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 19/04/2024 9:39 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsDashboardCrediclick {

    private Response response;
    private JsonNode productos;
    private JsonNode producto;
    String documentNumber = "";

    public MsDashboardCrediclick(String documentNumber){
        this.documentNumber = documentNumber;
    }

    public MsDashboardCrediclick newQuery(){
        response = RequestMicroServiciosIBP.newQuery().dashboardCrediclick(documentNumber);
        productos = Utilidad.jsonNodeParse(response);
        System.out.println(productos);
        //System.out.println(productos.size());
        return this;
    }

    public int getToltaProductos(){
        return productos.size();
    }
    public MsDashboardCrediclick iterarProductos(int indiceProducto){
        producto = productos.get(indiceProducto);
        return this;
    }

    public int getStatusCode(){
        return response.getStatusCode();
    }

    public MsDashboardCrediclick buscar(String accountNumber) {
        Optional.ofNullable(productos).ifPresentOrElse(data -> Optional.ofNullable(data.get(0)).ifPresentOrElse(masElementos -> { // Si contiene varios crediclick
            for (JsonNode product : data) {
                if (product.get("productNumber").asText().equalsIgnoreCase(accountNumber)) {
                    producto = product;
                    break; // Salir del bucle una vez que encuentres el producto
                }
            }

        }, () -> { // Si solo contiene un crediclick

            if (!data.isEmpty() && data.get("productNumber").asText().equalsIgnoreCase(accountNumber)) {
                producto = data;
            }
        }), () -> producto = null); // Si no contiene data, asigna producto = null
        return this;
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

    public String getAlias(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("alias").asText();
        }
        return texto;
    }

    public String getMontoAprobado(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("approvedAmount").asText();
        }
        return texto;
    }

    public String getMontoDisponible(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("availableAmount").asText();
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

    public String getMoneda(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("currency").asText();
        }
        return texto;
    }

    public String getCuotaMensual(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("monthlyFee").asText();
        }
        return texto;
    }

    public float getCuotaMensualFlotante(){
        return Float.parseFloat( getCuotaMensual() );
    }

    public Double getCuotaMensualDouble(){
        return Double.parseDouble( getCuotaMensual() );
    }

    public String getNombreProducto(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("name").asText();
        }
        return texto;
    }

    public String getCuotaPendiente(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("pendingLoanFee").asText();
        }
        return texto;
    }

    public String getNumProducto(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("productNumber").asText();
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

    public String getEstadoProducto(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("status").asText();
        }
        return texto;
    }

    /**
     *  T24, o SIEBEL
     * @return
     */
    public String getSistemaOrigen(){
        String texto = "null";
        if (producto != null){
            texto = producto.get("system").asText();
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

    public static void main(String[] args) {
        MsDashboardCrediclick crediclick = new MsDashboardCrediclick("03102674383").newQuery().buscar("8199299");
        System.out.println(crediclick.getAlias());
        System.out.println(crediclick.getCuotaMensual());
        System.out.println(crediclick.getTituloProducto());
    }


}
