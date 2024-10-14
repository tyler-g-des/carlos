package microservicios.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.Utilidad;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 07/06/2024 3:33 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsProveedoresServicios {

    private String documentNumber;
    private Response response;
    private JsonNode provedoresServicios;
    private String providerId;
    private String referenceLabel;
    private String serviceId;
    private String serviceProvider;
    private String servicio;
    private String acceptPaymentCC;


    public MsProveedoresServicios(){

    }

    public MsProveedoresServicios(String documentNumber){
        this.documentNumber = documentNumber;
        response = new MicroServiciosDashboard().getProveedoresServicios(documentNumber);
        provedoresServicios = Utilidad.jsonNodeParse(response);
    }

    public MsProveedoresServicios newQuery(String documentNumber){
        response = new MicroServiciosDashboard().getProveedoresServicios(documentNumber);
        provedoresServicios = Utilidad.jsonNodeParse(response);
        return this;
    }

public MsProveedoresServicios newQuery(){
    response = new MicroServiciosDashboard().getProveedoresServicios(documentNumber);
    provedoresServicios = Utilidad.jsonNodeParse(response);
    return this;
}

    public String getProviderId() {
        return providerId;
    }

    /**
     * Retorna el label de referencia. Este aplica para los parametros: domiciliationCollectionPeriod, domiciliationReference, infoReference
     * @return referencia del servicio actual.
     */
    public String getReferenceLabel() {
        return referenceLabel;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public String getAffectedService() {
        return servicio;
    }

    public String getAcceptPaymentCC() {
        return acceptPaymentCC;
    }

    public MsProveedoresServicios buscar(String proveedorDeServicios, String servicio){
    //System.out.println(provedoresServicios.get(0));
    for (JsonNode provedor  : provedoresServicios){
        if (provedor.get("providerName").asText().equalsIgnoreCase(proveedorDeServicios)){
            System.out.println("Se encontro claro: \n" + provedor);

            providerId = provedor.get("identification").asText();
            serviceProvider = provedor.get("providerName").asText();
            acceptPaymentCC = provedor.get("acceptTCPayments").asText();

            for (JsonNode servicios : provedor.get("services")){
                //System.out.println();
                //System.out.println(servicios);
                //if (servicio.get())
                if (servicios.get("description").asText().equalsIgnoreCase(servicio)){
                    referenceLabel = servicios.get("reference").asText();
                    serviceId = servicios.get("serviceId").asText();
                    this.servicio = servicios.get("description").asText();
                    System.out.println();
                    System.out.println(servicios);
                    break;
                }
            }
            break;
        }
    }
    return this;
}

    public static void main(String[] args) {
        MsProveedoresServicios proveedoresServicios = new MsProveedoresServicios("00111435384");
        proveedoresServicios.buscar("COOPASPIRE","cuotas"); // CLARO, Compra de Recargas

        System.out.println(proveedoresServicios.getProviderId());
        System.out.println(proveedoresServicios.getReferenceLabel());
        System.out.println(proveedoresServicios.getServiceId());
        System.out.println(proveedoresServicios.getServiceProvider());
        System.out.println(proveedoresServicios.getAffectedService());
        System.out.println(proveedoresServicios.getAcceptPaymentCC());
    }


}
