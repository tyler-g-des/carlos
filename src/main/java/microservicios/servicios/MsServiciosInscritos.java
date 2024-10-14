package microservicios.servicios;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.Utilidad;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 07/06/2024 12:38 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsServiciosInscritos {
    private String documentNumber;
    private Response response;
    private JsonNode servicios;
    private JsonNode servicio;


    public MsServiciosInscritos(){

    }

    public MsServiciosInscritos(String documentNumber){
        this.documentNumber = documentNumber;
        response = new MicroServiciosIncritos(documentNumber).getServiciosIncritos();
        servicios = Utilidad.jsonNodeParse(response);
    }

    public MsServiciosInscritos newQuery(String documentNumber){
        this.documentNumber = documentNumber;
        response = new MicroServiciosIncritos(documentNumber).getServiciosIncritos();
        servicios = Utilidad.jsonNodeParse(response);
        return this;
    }

    public MsServiciosInscritos newQuery(){
        response = new MicroServiciosIncritos(documentNumber).getServiciosIncritos();
        servicios = Utilidad.jsonNodeParse(response);
        return this;
    }


    public MsServiciosInscritos buscar(String proveedorDeServicio, String servicio, String numeroReferencia){
        for (JsonNode servicioActual : servicios){
            if (servicioActual.get("serviceProvider").asText().equalsIgnoreCase(proveedorDeServicio) &&
            servicioActual.get("affectedService").asText().equalsIgnoreCase(servicio) &&
            servicioActual.get("referenceNumber").asText().equalsIgnoreCase(numeroReferencia)){
                System.out.println(servicioActual);
                this.servicio = servicioActual;
            }
        }
        return this;
    }

    public MsServiciosInscritos buscarServicioPorDescripcion(String descripcion){
        for (JsonNode servicioActual : servicios){
            if (servicioActual.get("description").asText().equalsIgnoreCase(descripcion)){
                System.out.println(servicioActual);
                this.servicio = servicioActual;
            }
        }
        return this;
    }



    public String getId(){
        if (servicio == null){
            return null;
        }else {
            return servicio.get("id").asText();
        }
    }

    public static void main(String[] args) {
        MsServiciosInscritos servInscr = new MsServiciosInscritos("22301391524")
                .buscar("CLARO","Compra de Recargas","809220111112121212");
        System.out.println(servInscr.getId());

    }
}
