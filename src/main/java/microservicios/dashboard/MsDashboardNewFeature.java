package microservicios.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.RequestMicroServiciosIBP;
import microservicios.Utilidad;

public class MsDashboardNewFeature {

    private Response response;
    private JsonNode data;
    private boolean estado = false;
    String documentNumber = "";

    public MsDashboardNewFeature(String documentNumber){
        this.documentNumber = documentNumber;
    }

    /** <h3>Retorna el código de respuesta de la solicitud http.</h3>
     * @return Status Code: 200,400,401.. etc..
     */
    public int getStatusCode(){
        return response.getStatusCode();
    }

    /** <h3>Realiza una nueva consulta y retorna una instancia de la clase MsDashboardNewFeature.</h3>
     * @return MsDashboardNewFeature
     */
    public MsDashboardNewFeature newQuery(){
        response = RequestMicroServiciosIBP.newQuery().dashboardNewFeature(documentNumber);
        data = Utilidad.jsonNodeParse(response);
        return this;
    }

    public boolean isSuccess(){
        if (data.isEmpty()){
            estado = false;
        }else {
            estado = data.get("success").asBoolean();
        }
        return estado;
    }

    /** <h3>Retorna true si contiene publicidad, de lo contrario responde false.</h3>
     * @return boolean
     */
    public boolean contienePublicidad(){
        if (data.isEmpty()){
            estado = false;
        }else {
            estado = data.get("features").size() != 0;
        }
        return estado;
    }
}
