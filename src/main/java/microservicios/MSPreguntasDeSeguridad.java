package microservicios;

import com.fasterxml.jackson.databind.JsonNode;
import http.BaseRequestHTTP;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MSPreguntasDeSeguridad {

    private String url = "https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/personal/login";
    private String documentNumber;
    private JsonNode data;
    int statusCode = 0;
    private Response response;
    public MSPreguntasDeSeguridad(String documentNumber){
        this.documentNumber = documentNumber;
        nuevaConsulta();
    }

    public MSPreguntasDeSeguridad(){

    }

    public MSPreguntasDeSeguridad newQuery(){
        nuevaConsulta();
        return this;
    }

    public MSPreguntasDeSeguridad newQuery(String documentNumber){
        this.documentNumber = documentNumber;
        nuevaConsulta();
        return this;
    }

    private void nuevaConsulta(){
        try {
            Map <String, Object> parametros = new HashMap<>();
            parametros.put("documentNumber",documentNumber);
            parametros.put("transactionId", UUID.randomUUID());
            parametros.put("login",true);

            Map <String, Object> headers = new HashMap<>();
            headers.put("Accept","*/*");
            headers.put("Connection","keep-alive");

            response = new BaseRequestHTTP().httGET( parametros, headers, url);
            statusCode = response.getStatusCode();
            data = Utilidad.jsonNodeParse(response);
        }catch (Exception e){
            System.out.println("Error en Micro Servicio Preguntas de seguridad: " + e);
        }
    }

    public int getTipoPregunta(){
        int tipoPregunta = 0;
        if (data == null){
            tipoPregunta = 0;
        }else {
            tipoPregunta = data.get("questionsType").asInt();
        }
        return tipoPregunta;
    }

    public boolean tienePreguntasSeguridadConfiguradas(){
        boolean preguntasConfiguradas = false;
        if (getTipoPregunta() == 1 ){
            preguntasConfiguradas = true;
        }else if (getTipoPregunta() == 3){
            return false;
        }
        return preguntasConfiguradas;
    }


}
