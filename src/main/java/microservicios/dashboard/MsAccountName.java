package microservicios.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import microservicios.Utilidad;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 06/06/2024 10:07 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsAccountName {

    private Response response;
    private String documentNumber;
    private JsonNode data;
    private String mensaje;

    public MsAccountName(String documentNumber){
        this.documentNumber = documentNumber;
    }
    public MsAccountName(){
    }


    public MsAccountName queryBHD(String productNumber){
        data = null;
        response = new MicroServiciosDashboard().getNombreCuentaBHD(documentNumber,productNumber);
        data = Utilidad.jsonNodeParse(response);
        if (data.isEmpty()){
            mensaje = "El Servicio ha fallado";
        }else {
            mensaje = "Este numero de producto es invalido";
        }
        System.out.println(data.size());
        return this;
    }

    public void queryPamama(String productNumber){
        data = null;
        response = new MicroServiciosDashboard().getNombreCuentaBHDIBPanama(documentNumber,productNumber);
        data = Utilidad.jsonNodeParse(response);
        System.out.println(data.size());
    }

    public String getNombre(){
        if (data == null){
            return mensaje;
        }else {
            if (data.size() == 1 ){
                return mensaje;
            }
            return data.get("financialName").asText();
        }
    }

    public String getTipoProductoSigla(){
        if (data == null){
            return mensaje;
        }else {
            if (data.size() == 1 ){
                return mensaje;
            }
            return data.get("financtialType").asText();
        }
    }

    /**
     *
     * @return DOP, USD
     */
    public String getMonedaSigla(){
        if (data == null){
            return mensaje;
        }else {
            if (data.size() == 1 ){
                return mensaje;
            }
            return data.get("financialCurrency").asText();
        }
    }

    /**
     * Retorna la moneda USD = US. Y la moneda DOP = RD.
     * @return moneda.
     */
    public String getMonedaConvertida(){
        return getMonedaSigla().replace("DOP","RD").replace("USD","US");
    }

}
