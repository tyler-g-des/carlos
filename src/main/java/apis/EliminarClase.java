package apis;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Email: carlos_loyola@bhd.com.do
 * @ClassName: EliminarClase
 * @CreationData: 04/06/2024 5:57 a. m.
 * @ProjectName: BHDL_AutomatizacionMigracionIBP
 */
public class EliminarClase {

    public static void main(String[] args) {

        Map<String, Object> encriptar = new HashMap<>();
        encriptar.put("deviceId", "68de0c74-c877-409c-a46c-f99e7afc24e0");
        encriptar.put("secureDevice", false);

        Map<String, Object> userSecretQuestions = new HashMap<>();

        Map<String, Object> pregunta1 = new HashMap<>();
        pregunta1.put("question", "¿Cuál es la marca de su primer carro?");
        pregunta1.put("position", 4);
        pregunta1.put("answer", "carro");
        userSecretQuestions.put("1", pregunta1);

        Map<String, Object> pregunta2 = new HashMap<>();
        pregunta2.put("question", "¿Cuál es el nombre de su abuela materna?");
        pregunta2.put("position", 3);
        pregunta2.put("answer", "abuela");
        userSecretQuestions.put("2", pregunta2);
        encriptar.put("userSecretQuestions",userSecretQuestions);


        System.out.println(new Gson().toJson(encriptar).replace("\"1\":","").replace("\"2\":","").replace("{{","[{").replace("}}","}]"));

    }
}
