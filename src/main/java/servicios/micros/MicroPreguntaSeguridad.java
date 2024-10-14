package servicios.micros;

import data.Persistencia;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;

import java.util.UUID;

public class MicroPreguntaSeguridad {
    // Primer inicio y no tiene preguntas configuradas-40220975094
    // Primer inicio de sesion con preguntas configuradas-00101922854

    private String path = "/bhdleon/v1/system/personal/login";
    private int statusCode = 0;
    private boolean preguntasConfiguradas = false;

    private MicroPreguntaSeguridad(){

    }

    public static MicroPreguntaSeguridad getInstance(){
        return new MicroPreguntaSeguridad();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isPreguntasConfiguradas() {
        return preguntasConfiguradas;
    }

    public MicroPreguntaSeguridad consultar(String documentNumber){
        preguntasConfiguradas = false;
        statusCode = 0;
        Response response = null;
        try {
            response = BaseRequestHTTP.configNewRequest()
                    .param("transactionId", UUID.randomUUID())
                    .param("documentNumber", documentNumber)
                    .param("login",true)
                    .log().all()
                    .get(BaseRequestHTTP.HOST_MICRO_SQA_IBP + path);
            statusCode = response.getStatusCode();
            if (statusCode == 200){
                System.out.println(response.getBody().asString());
                preguntasConfiguradas = response.getBody().jsonPath().getInt("questionsType") == 1;
            }

        }catch (Exception ignored){
            return this;
        }
        return this;
    }

    public static void main(String[] args) {
        // Pasport: 40006420738 - Reseteado muestra configuracion de preguntas de seguridad.
        // Reseado y no mostro las preguntas de seguridad: 00101674588
        Persistencia.getInstance().setLoginApi("40006420738","1111");

        System.out.println("___________________________________");



        System.out.println("Preguntas configuradas: " + MicroPreguntaSeguridad.getInstance().consultar("40006420738").isPreguntasConfiguradas() );
        System.out.println("Es primer incio: " + Persistencia.getInstance().getApiLogin("40006420738").isFirstLogin());
        System.out.println("Plataforma Onlie: " + MicroContratos.getInstance().consultarcontratoAPP("40006420738").isContractAccepted());
        System.out.println("Se muestra consentimiento: " + MicroClientInfo.getInstance().consultar("40006420738").isSeMuestraConsentimientoCompartirInformacion());
//
//        Persistencia.getInstance().setMicroContratos("00100965995");
//        System.out.println(Persistencia.getInstance().getMicroContratos("00100965995").consultarContratoInternacional("00100965995").isContractAccepted());
    }

}
