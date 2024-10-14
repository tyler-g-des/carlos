package microservicios;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;

import java.util.Properties;

import static microservicios.Utilidad.formatearPrimerasLetrasPalabrasEnMayuscula;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 03/04/2024 11:38 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MSClientInfoV2 {

    private Response response;
    private JsonNode data;
    private String documentNumber = "";
    private int statusCode = 0;

    public MSClientInfoV2(String documentNumber){
        this.documentNumber = documentNumber;
    }

    public MSClientInfoV2 newQuery(){
        response = RequestMicroServiciosIBP.newQuery().getClientInfoV2(documentNumber);
        data = Utilidad.jsonNodeParse(response);
        try {
            statusCode = response.getStatusCode();
        }catch (Exception e){
            statusCode = 0;
        }
        return this;
    }

    public MSClientInfoV2(){

    }

    public int getStatusCode(){
        if (statusCode != 200){
            newQuery();
        }
        return response.getStatusCode();
    }

//    public boolean esEmpleado(){
//        boolean isEmpleado = false;
//        if (data != null){
//            isEmpleado = data.get("isEmployee").asBoolean();
//        }
//        return isEmpleado;
//    }

    public boolean esEmpleado(){
        if (data != null && data.size() > 0){
            return data.get("isEmployee").asBoolean();
        }
        return false;
    }

    public boolean aceptarConsentimientoUsoInformacion(){
        boolean aceptarConsentimiento = false;

        if (data != null && data.size() > 0){
            aceptarConsentimiento = data.get("showConsentForInformationSharing").asBoolean();
        }
        return aceptarConsentimiento;
    }

    public String getCompleteNamePascalCase(){
        String texto = "null";
        if (data != null){
            texto = formatearPrimerasLetrasPalabrasEnMayuscula(getCompleteName());
        }
        return texto;
    }

    public String getCompleteName(){
        String texto = "null";
        if (data != null){
            texto = data.get("completeName").asText();
        }
        return texto;
    }

    public String getDocumentNumber(){
        if (data != null && data.size() > 0){
            return data.get("cedula").asText();
        }
        return "";
    }



    public String getEmail(){
        String texto = "null";
        if (data != null){
            texto = data.get("email").asText();
        }
        return texto;
    }


    public boolean isEnableKeyCardMyProducts(){
        boolean status = false;
        if (data != null){
            String resultado = data.get("allowTransactionWithoutKeyCard").asText();
            if (resultado.equals("0")){
                status = true;
            }
        }
        return status;
    }


    public String getContactId(){
        String texto = "null";
        if (data != null){
            texto = data.get("contactId").asText();
        }
        return texto;
    }

    public String getCustomerCodeT24(){
        String texto = "null";
        if (data != null){
            texto = data.get("customerCodeT24").asText();
        }
        return texto;
    }

    public String getCustomerCode(){
        String texto = "null";
        if (data != null){
            texto = data.get("customerCode").asText();
        }
        return texto;
    }

    public static void main(String[] args) {

        MSClientInfoV2 servicio = new MSClientInfoV2("22301391524").newQuery();
        Properties sesiones = new Properties();
        sesiones.put("apellido","Loyola");
        sesiones.put("apellido","Tejeda");
        sesiones.put("carro", servicio.esEmpleado());
        System.out.println(sesiones.containsKey("apellidos"));
        System.out.println(sesiones.get("apellido"));
        System.out.println(sesiones.get("carro"));
        System.out.println(sesiones);
    }

}
