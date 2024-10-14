package data;

import java.util.*;

public class GenerarPreguntasSeguridad {

    // 40006420738- Reseteado con configuradion de preguntas
    private static Map<Object, String> preguntasDefauld = null;
    private static Map<Object, String> preguntasPropias = null;
    private final String preguntaPropia = "¿Pregunta propia?";





    public GenerarPreguntasSeguridad(){
        iniPreguntasPorDefecto();
        iniPreguntasPropias();
    }

    /**
     * Inicializa mapa con preguntas y respuestas propias.
     */
    private void iniPreguntasPropias(){
        preguntasPropias = new HashMap<>();
        preguntasPropias.put("Como se llama mi hermana", "Pamela");
        preguntasPropias.put("Cual es el primer apellido de mi hermana", "Loyola");
        preguntasPropias.put("Como se llama mi mama", "Marilin");
    }

    /**
     * Inicializa mapa con las preguntas y respuestas por defecto del canal IBP.
     */
    private void iniPreguntasPorDefecto(){
        preguntasDefauld = new HashMap<>();
        preguntasDefauld.put("¿Cuál es el personaje de su libro favorito?", "libro");
        preguntasDefauld.put("¿Cuál es su color favorito?", "color");
        preguntasDefauld.put("¿Cuál es el nombre de su abuela materna?", "abuela");
        preguntasDefauld.put("¿Cuál es la marca de su primer carro?", "marca");
        preguntasDefauld.put("¿Cuál es el nombre del colegio donde cursó la primaria?", "colegio");
        preguntasDefauld.put("¿Cuál es el nombre de su primera mascota?", "mascota");
        preguntasDefauld.put("¿Cuál es su comida favorita?", "comida");
        preguntasDefauld.put("¿Cuál es su canción favorita?", "cancion");
        preguntasDefauld.put("¿Cuál es el nombre de su artista favorito?", "artista");
        preguntasDefauld.put("¿Cuál es el nombre de la calle donde vivió en su infancia?", "calle");
        preguntasDefauld.put("¿Cuál es el nombre del hospital o clínica donde usted nació?", "hospital");
        preguntasDefauld.put("¿Cuál es el nombre de la empresa donde labora?", "empresa");
        preguntasDefauld.put("¿Cuál es su hobby favorito?", "hobby");
        preguntasDefauld.put("¿Cuál es el nombre de su deportista favorito?", "deportista");
        preguntasDefauld.put("¿Cuál es el nombre del lugar que sueña visitar?", "lugar");
        preguntasDefauld.put("¿Cuál fue su materia favorita en el bachillerato?", "materia");
        preguntasDefauld.put("¿Cuál es la fecha del aniversario de su boda?", "aniversario");
        preguntasDefauld.put("¿Cuál es la fecha de nacimiento de su primer hijo?", "hijo");
        preguntasDefauld.put("¿Cuál es el nombre del centro de belleza que visita?", "centro");
        preguntasDefauld.put("¿Cuál es el nombre de su restaurant favorito?", "restaurant");
        preguntasDefauld.put("¿Cuál es el nombre de la universidad donde estudia/estudió?", "universidad");
        preguntasDefauld.put("¿Cuál es el nombre de su tienda favorita?", "tienda");
        preguntasDefauld.put("¿Cuál es el nombre de su pelicula favorita?", "pelicula");
        preguntasDefauld.put("cual es mi nombre", "Carlos Loyola");
    }

    private String combo = "";
    private String pregunta = "";
    private String respuesta = "";

    /**
     * Configura una pregunta y respuesta de seguridad. El mismo retorna la pregunta de seguridad. Y el metodo
     * getRespuesta(); retorna la respuesta de dicha pregunta.
     * @param propia
     * @return {@link String}
     */
    public String getPregunta( boolean propia) {

        if (propia){
            setPreguntaRespuesta(preguntasPropias, propia);
            preguntasPropias.remove(pregunta);
        }else {
            setPreguntaRespuesta(preguntasDefauld, propia);
            preguntasDefauld.remove(pregunta);
        }
        return pregunta;
    }

    public GenerarPreguntasSeguridad configurarPregunta( boolean propia ){
        getPregunta(propia);
        return this;
    }

    /**
     * Retorna la respuesta de la pregunta generada por el metodo: getPregunta(boleano es propia);
     * @return {@link String}
     */
    public String getRespuesta() {
        return respuesta;
    }

    public String getCombo() {
        return combo;
    }

    public String getPregunta() {
        return pregunta;
    }

    /**
     * Retorna la respuesta de la pregunta enviada como parametro.
     * @param pregunta Pregunta de la cual se retornara la respuesta.
     * @return {@link String}
     */
    public String getRespuesta(String pregunta){
        if (preguntasPropias.containsKey(pregunta)){
            return preguntasPropias.get(pregunta);
        }else {
            return preguntasDefauld.getOrDefault(pregunta, "Esta pregunta no existe en el json");
        }
    }

    /**
     * Este metodo configura una pregunta y respuesta de seguridad.
     * @param mapa {@link Map} que contenga las preguntas y respuestas de seguridad.
     */
    private void setPreguntaRespuesta(Map<Object, String> mapa, boolean propia){


        //String pregunta = "";
        //String respuesta = "";


        if (mapa.size()> 0){

            List<String> values = new ArrayList<>( mapa.values() );
            int ramdon = new Random().nextInt( values.size() );

            this.pregunta = new ArrayList<>(mapa.keySet()).get(ramdon).toString();
            this.respuesta = values.get(ramdon);

            //this.pregunta = pregunta;
            //this.respuesta = respuesta;

            if (propia){
                combo = "¿Pregunta propia?";

            }else {
                combo = pregunta;
            }

        }else {
            this.pregunta = "No existen mas preguntas";
            this.respuesta = "No existe esta respuesta porque no existen mas preguntas";
        }
    }

}
