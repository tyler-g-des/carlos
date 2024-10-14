package apis.ibp;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Email: carlos_loyola@bhd.com.do
 * @ClassName: APIValidacionAcceso
 * @CreationData: 06/06/2024 7:57 a. m.
 * @ProjectName: BHDL_AutomatizacionMigracionIBP
 */
public class APIValidacionAcceso extends BaseRequest{
    public APIValidacionAcceso(APISLogin login) {
        super(login);
    }

    private String urlVerifyAnswers = "/bhdleon/api/v1/personal/web/verify-answers";

    public void verifyAnswersStatusCode400BadRequest(String documentNumber, String contactId, String documentType, String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();

        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("secureDevice", "false");

        Map<String, Object> userSecretQuestions = new HashMap<>();

        Map<String, Object> pregunta1 = new HashMap<>();
        pregunta1.put("question", "¿Cuál es su color favorito?");
        pregunta1.put("position", "1");
        pregunta1.put("answer", "color");
        userSecretQuestions.put("1", pregunta1);

        Map<String, Object> pregunta2 = new HashMap<>();
        pregunta2.put("question", "¿Cuál es el nombre de su abuela materna?");
        pregunta2.put("position", "2");
        pregunta2.put("answer", "abuela");
        userSecretQuestions.put("2", pregunta2);
        encriptar.put("userSecretQuestions",userSecretQuestions);

        httpPost(encriptar,urlVerifyAnswers,accion,nombreMetodo,getClass());

        //httpGET(encriptar,urlProductsRefreshment,accion,nombreMetodo,getClass());
    }

    public static void main(String[] args) {
        APISLogin login = new APISLogin();
        login.loginSecureDevice("00111435384","1111","");

        APIValidacionAcceso validacionAcceso = new APIValidacionAcceso(login);
        validacionAcceso.verifyAnswersStatusCode400BadRequest("","","","");
    }
}
