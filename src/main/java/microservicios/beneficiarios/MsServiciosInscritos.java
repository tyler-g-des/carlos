package microservicios.beneficiarios;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.RequestMicroServiciosIBP;
import microservicios.Utilidad;
import microservicios.beneficiarios.internacionales.MsBeneficiariosInternacionales;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 27/03/2024 11:40 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsServiciosInscritos {

    private String documentNumber = "";
    private Response response;
    private JsonNode servicios;
    private JsonNode servicio;

    public MsServiciosInscritos(String documentNumber){
        this.documentNumber = documentNumber;
    }

    public MsServiciosInscritos newQuery(){
        response = RequestMicroServiciosIBP.newQuery().consultarServiciosInscritos(documentNumber);
        servicios = Utilidad.jsonNodeParse(response);
        return this;
    }


    public MsServiciosInscritos buscar(String referencia){
        if (!servicios.isEmpty()){
            for (JsonNode resultado : servicios){
                if (resultado.get("referenceNumber").asText().equalsIgnoreCase(referencia)){
                    servicio = resultado;
                    break;
                }else {
                    servicio = null;
                }
            }
        }
        return this;
    }

    public MsServiciosInscritos buscar(String proveedor,String referencia){
        if (!servicios.isEmpty()){
            for (JsonNode resultado : servicios){
                if (resultado.get("serviceProvider").asText().equalsIgnoreCase(proveedor) && resultado.get("referenceNumber").asText().equalsIgnoreCase(referencia)){
                    servicio = resultado;
                    break;
                }else {
                    servicio = null;
                }
            }
        }
        return this;
    }

    public String getProveedorServicio(){
        String texto = "null";
        if (servicio != null){
            texto = servicio.get("serviceProvider").asText();
        }
        return texto;
    }

    public String getServicio(){
        String texto = "null";
        if (servicio != null){
            texto = servicio.get("affectedService").asText();
        }
        return texto;
    }

    public String getId(){
        String texto = "null";
        if (servicio != null){
            texto = servicio.get("id").asText();
        }
        return texto;
    }

    public String getReferencia(){
        String texto = "null";
        if (servicio != null){
            texto = servicio.get("referenceNumber").asText();
        }
        return texto;
    }

    public String getDescripcion(){
        String texto = "null";
        if (servicio != null){
            texto = servicio.get("description").asText();
        }
        return texto;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "Proveedor de servicio='" + servicio.get("serviceProvider").asText() + '\'' +
                ", Servicio =" + servicio.get("affectedService").asText() +
                ", Referencia =" + servicio.get("referenceNumber").asText() +
                ", Descripcion =" + servicio.get("description").asText() +
                '}';
    }
}
