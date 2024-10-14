package servicios.micros;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MicroContratos {

    private final String KEY_INDEX = "index";
    private final String KEY_VALUE = "value";
    private final String KEY_OUT = "out";
    private final String KEY_SQATYPE = "sqlType";
    private final String KEY_VARCHAR = "VARCHAR";
    private int statusCode = 0;
    private boolean contractIsAccepted = false;
    private String tituloContrato = "";


    /**
     * Constructor por defecto para permitir crear instancia de la clase solo a través del método: getInstance().
     */
    private MicroContratos(){}

    /**
     * Retorna una nueva instancia de la clase.
     * @return {@link MicroContratos}
     */
    public static MicroContratos getInstance(){
        return new MicroContratos();
    }

    /**
     * Retorna el statusCode de la ultima petición realizada.
     * @return {@link Integer}
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Retorna el titulo del contrato consultado actualmente.
     * @return {@link String}
     */
    public String getTituloContrato() {
        return tituloContrato;
    }

    /**
     * Retorna true si el contrato esta aceptado, de lo contrato retorna false.
     * @return {@link Boolean}
     */
    public boolean isContractAccepted() {
        return contractIsAccepted;
    }

    /**
     * Este método realiza una consulta al micro de contrato. El contrato se envía por parámetro y se puede enviar la consulta de los contratos APP, ACH y INT.
     * @param documentNumber Cedula del usuario a consultar su contrato.
     * @param contrato APP-ACH-INT
     * @return {@link MicroContratos}
     */
    private MicroContratos consultar(String documentNumber, String contrato){
        statusCode = 0;
        contractIsAccepted = false;
        tituloContrato = "Se ha producido un error consultando el contrato "+contrato+": ";

        Map<String, Object> body = new HashMap<>();
        body.put("connectionId", "");
        body.put("sentenceId", "PR_GET_CONTRACT_LIST");
        body.put("sqlSentence", "");

        Map<String, Object> objecto1 = new HashMap<>();
        objecto1.put(KEY_INDEX, "1");
        objecto1.put(KEY_VALUE, "BANCASA");
        objecto1.put(KEY_OUT, false);
        objecto1.put(KEY_SQATYPE, KEY_VARCHAR);

        Map<String, Object> objecto2 = new HashMap<>();
        objecto2.put(KEY_INDEX, "2");
        objecto2.put(KEY_VALUE, documentNumber);
        objecto2.put(KEY_OUT, false);
        objecto2.put(KEY_SQATYPE, KEY_VARCHAR);

        Map<String, Object> objecto3 = new HashMap<>();
        objecto3.put(KEY_INDEX, "3");
        objecto3.put(KEY_VALUE, contrato);
        objecto3.put(KEY_OUT, false);
        objecto3.put(KEY_SQATYPE, KEY_VARCHAR);

        Map<String, Object> objecto4 = new HashMap<>();
        objecto4.put(KEY_INDEX, "4");
        objecto4.put(KEY_VALUE, "");
        objecto4.put(KEY_OUT, true);
        objecto4.put(KEY_SQATYPE, "CURSOR");

        List<Map<String, Object>> parametersIn = Utilidad.crearListaMapa();

        parametersIn.add( objecto1 );
        parametersIn.add( objecto2 );
        parametersIn.add( objecto3 );
        parametersIn.add( objecto4 );

        body.put("parametersIn", parametersIn);

        try {
            Response response = BaseRequestHTTP.configNewRequest().given()
                    .contentType( ContentType.JSON )
                    .header("Accept", "*/*")
                    .body( body )
                    .post("https://ibp-sqa.app.noprod.cfbhd.com/portal/v1.1/dbcomponent/procedure/");

            statusCode = response.statusCode();

            if (statusCode == 200){
                JsonNode data = Utilidad.jsonNodeParse( response );
                contractIsAccepted = data.findValue("columns").get( 3 ).get( KEY_VALUE ).asInt() == 1;
                tituloContrato = data.findValue("columns").get( 0 ).get( KEY_VALUE ).asText();
            }else {
                tituloContrato = tituloContrato + statusCode;
            }

        }catch (Exception e){
            tituloContrato = tituloContrato + e;
            return this;
        }
        return this;
    }

    /**
     * Realiza consulta del contrató Términos y Condiciones de Uso Plataforma Online (APP).
     * @param documentNumber Cedula del usuario a consultar su contrato.
     * @return {@link MicroContratos}
     */
    public MicroContratos consultarcontratoAPP(String documentNumber){
         return consultar( documentNumber,"APP" );
    }

    /**
     * Realiza consulta del Contrato de transferencia ACH y Pagos al instante BCRD (ACH).
     * @param documentNumber Cedula del usuario a consultar su contrato.
     * @return {@link MicroContratos}
     */
    public MicroContratos consultarContratoACH(String documentNumber){
        return consultar( documentNumber,"ACH" );
    }

    /**
     * Realiza consulta del Contrato de transferencia internacional y regional SIPA (INT).
     * @param documentNumber Cedula del usuario a consultar su contrato.
     * @return {@link MicroContratos}
     */
    public MicroContratos consultarContratoInternacional(String documentNumber){
        return consultar( documentNumber,"INT" );
    }
}
