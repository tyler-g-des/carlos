package microservicios.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.Utilidad;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 06/06/2024 5:58 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsPrestamos {


    private Response response;
    private String documentNumber;
    private JsonNode cuentas;
    private JsonNode cuenta;
    private String mensaje;

    public MsPrestamos(String documentNumber){
        this.documentNumber = documentNumber;
        response = new MicroServiciosDashboard().getPrestamos(documentNumber);
        cuentas = Utilidad.jsonNodeParse(response);
    }
    public MsPrestamos(){
    }

    public MsPrestamos newQuery(String documentNumber){
        this.documentNumber = documentNumber;
        response = new MicroServiciosDashboard().getPrestamos(documentNumber);
        cuentas = Utilidad.jsonNodeParse(response);
        return this;
    }

    public MsPrestamos newQuery(){
        response = new MicroServiciosDashboard().getPrestamos(documentNumber);
        cuentas = Utilidad.jsonNodeParse(response);
        return this;
    }

    public MsPrestamos buscar(String accountNumber){
        mensaje = "";
        if (cuentas.isEmpty()){
            mensaje = "El usuario no contiene prestamos";
        }else {
            for (JsonNode benef : cuentas){
                if (benef.get("productNumber").asText().equalsIgnoreCase(accountNumber)){
                    cuenta = benef;
                    break;
                }else {
                    cuenta = null;
                    mensaje = "Este prestamo no existe";
                }
            }
        }
        return this;
    }

    public String getAlias(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("alias").asText();
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

    public String getNombreProducto(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("name").asText();
        }
        return mensaje;
    }

    public String getNumeroProducto(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("productNumber").asText();
        }
        return mensaje;
    }

    public String getTipoProducto(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("type").asText();
        }
        return mensaje;
    }

    public String getTipoProductoSigla(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("productType").asText();
        }
        return mensaje;
    }

    public String getEstadoProducto(){
        if (cuenta == null){
            return mensaje;
        }else {
            mensaje = cuenta.get("status").asText();
        }
        return mensaje;
    }




    public String getTituloProducto(){
        if (cuenta == null){
            return mensaje;
        }else {
            if (getAlias().equals("")){
                return getNombreProducto();
            }else {
                return getAlias();
            }
        }
    }

    public boolean isFinancing(){
        if (cuenta == null){
            return false;
        }else {
            return cuenta.get("isFinancing").asBoolean();
        }
    }






    public static void main(String[] args) {
        MsPrestamos prestamos = new MsPrestamos("00111435384").buscar("3927639");


        System.out.println(prestamos.getTituloProducto());
//        System.out.println(prestamos.isFinancing());
//
//
//        System.out.println(prestamos.getAlias());
//        System.out.println(prestamos.isFinancing());
//
//        Object objecto = prestamos.isFinancing();
//
//        System.out.println(objecto.toString());

    }
}
