package servicios.micros.accesos;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.UUID;

public class MicroServiciosInscritos {

    private int statusCode = 0;
    private String id = "";
    private String proveedor = "";
    private String servicio = "";
    private String referencia = "";
    private String descripcion = "";
    private String labelNombreReferencia = "";
    private String numDocumento = "";

    public int getStatusCode() {
        return statusCode;
    }

    public String getId() {
        return id;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getServicio() {
        return servicio;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLabelNombreReferencia() {
        return labelNombreReferencia;
    }

    private JsonNode listaServicios = null;


    public MicroServiciosInscritos consultar(String documentNumber){
        try {
            numDocumento = documentNumber;
            Response response = BaseRequestHTTP.configNewRequest()
                    .param("transactionId", UUID.randomUUID())
                    .param("documentNumber",documentNumber)
                    .get("https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/customer/services-beneficiaries/beneficiaries");

            statusCode = response.getStatusCode();
            if (statusCode == 200){
                listaServicios = Utilidad.jsonNodeParse(response);
            }
        }catch (Exception e){

        }
        return this;
    }

    public MicroServiciosInscritos buscarId(String id){
        try {
            if (statusCode == 200 && listaServicios.size()> 0){
                for (JsonNode service : listaServicios){
                    if (service.get("id").asText().equals(id)){
                        id = service.get("id").asText();
                        proveedor = service.get("serviceProvider").asText();
                        servicio = service.get("affectedService").asText();
                        referencia = service.get("referenceNumber").asText();
                        this.descripcion = service.get("description").asText();
                        labelNombreReferencia = service.get("domiciliationReference").asText();
                    }
                }
            }
        }catch (Exception e){

        }
        return this;
    }

    public MicroServiciosInscritos buscarServicio(String descripcion){
        try {
            if (statusCode == 200 && listaServicios.size() > 0){
                for (JsonNode thisServicio : listaServicios ){
                    if (thisServicio.get("description").asText().equals(descripcion)){
                        id = thisServicio.get("id").asText();
                        proveedor = thisServicio.get("serviceProvider").asText();
                        servicio = thisServicio.get("affectedService").asText();
                        referencia = thisServicio.get("referenceNumber").asText();
                        this.descripcion = thisServicio.get("description").asText();
                        labelNombreReferencia = thisServicio.get("domiciliationReference").asText();
                        break;
                    } else {
                        String noExiste = "El servicio con descripcion %s no existe.";
                        id = String.format(noExiste, descripcion);
                        proveedor = String.format(noExiste, descripcion);
                        servicio = String.format(noExiste, descripcion);
                        referencia = String.format(noExiste, descripcion);
                        this.descripcion = String.format(noExiste, descripcion);
                        labelNombreReferencia = String.format(noExiste, descripcion);
                    }
                }
            }else if (statusCode == 204) {
                String sinServicios = "El usuario %s no contiene servicios inscritos";
                id = String.format(sinServicios, numDocumento);
                proveedor = String.format(sinServicios, numDocumento);
                servicio = String.format(sinServicios, numDocumento);
                referencia = String.format(sinServicios, numDocumento);
                this.descripcion = String.format(sinServicios, numDocumento);
                labelNombreReferencia = String.format(sinServicios, numDocumento);
            }
        }catch (Exception e){
            String error = "Se ha produccido un error consultando los servicios inscritos. " + e;
            id = error;
            proveedor = error;
            servicio = error;
            referencia = error;
            this.descripcion = error;
            labelNombreReferencia = error;
        }
        return this;
    }
}
