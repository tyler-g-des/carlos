package servicios.micros.pagostransferencias.combox.tecero;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import servicios.micros.pagostransferencias.combox.MicroComboOrigenTercero;
import util.Utilidad;

import java.util.UUID;

public class MicroComboDestinoTercero {

    private final String url = "https://ibp-sqa.app.noprod.cfbhd.com/bhd/v1.1/process/transactions/other-banks/destination";

    private JsonNode beneficiarios = null;
    private boolean existe = false;
    private int statusCode = 0;
    private String nombre = "name";
    private String correo = "email";
    private String nombreBanco = "bankName";
    private String monedaSigla = "currency";
    private String alias = "favoriteAccountAlias";
    private String tipoSigla = "accountType";
    private String numProduct = "accountNumber";

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }
    public String getMonedaSigla() {
        return monedaSigla;
    }

    public String getAlias() {
        return alias;
    }
    public String getTipoSigla() {
        return tipoSigla;
    }
    public String getNumProduct() {
        if (tipoSigla.equals("TAC")){
            return Utilidad.enmascararTC(numProduct);
        }
        return numProduct;
    }



    public MicroComboDestinoTercero consultarMicro(MicroComboOrigenTercero microOrigen){
        try {

            Response response = BaseRequestHTTP.configNewRequest()
                    .param("transactionId", UUID.randomUUID())
                    .param("isRecurrence","N")
                    .param("documentNumber",microOrigen.getUsuario())
                    .param("transChannel",microOrigen.getCanal())
                    .param("debitProductNumber",microOrigen.getNumProducto())
                    .param("debitProductType",microOrigen.getTipoSigla())
                    .param("debitCurrency",microOrigen.getMoneda())
                    .log().all()
                    .get(url);
            statusCode = response.getStatusCode();
            System.out.println("Beneficiario estado: " + statusCode);
            System.out.println(response.getBody().asString());
            beneficiarios = Utilidad.jsonNodeParse(response);

        }catch (Exception e){
            setError("Error consultando los beneficiarios. StatusCode: " + statusCode);
        }
        return this;
    }

    public MicroComboDestinoTercero buscar(String numeroProducto){
        try {
            if (statusCode == 200){
                if (beneficiarios.size() > 0){
                    for (JsonNode beneficiario : beneficiarios){
                        if (beneficiario.get("accountNumber").asText().equals(numeroProducto)){

                            nombre = beneficiario.get("name").asText();
                            correo = beneficiario.get("email").asText();
                            nombreBanco = beneficiario.get("bankName").asText();
                            monedaSigla = beneficiario.get("currency").asText();
                            alias = beneficiario.get("favoriteAccountAlias").asText();
                            tipoSigla = beneficiario.get("accountType").asText();
                            numProduct = beneficiario.get("accountNumber").asText();
                            existe = true;
                            break;
                        }else {
                            setError("El beneficiario: "+numeroProducto+" no existe o no esta cargando en el combo.");
                        }
                    }
                }else {
                    setError("El usuario no contiene beneficiarios o no estan cargando.");
                }
            }else if (statusCode == 204){
                setError("El usuario no contiene beneficiarios.");
            }else if (statusCode != 0){
                setError("Error consultando los beneficiarios. StatusCode: " + statusCode);
            }
        }catch (Exception e){

        }
        return this;
    }

    private void setError(String msgError){
        nombre = msgError;
        correo = msgError;
        nombreBanco = msgError;
        monedaSigla = msgError;
        alias = msgError;
        tipoSigla = msgError;
        numProduct = msgError;
        existe = false;
    }

}
