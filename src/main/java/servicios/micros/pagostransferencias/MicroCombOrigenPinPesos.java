package servicios.micros.pagostransferencias;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.UUID;

public class MicroCombOrigenPinPesos {

    private String url = "https://ibp-sqa.app.noprod.cfbhd.com/bhd/v2/process/comboboxes/origin-products";
    private JsonNode productos = null;
    private String cedula = "";



    private int statusCode = 0;
    private String monedaSigla = "RD";
    private String numeroCuenta = "";
    private String tipoCuenta = "";
    private String tipoCuentaSigla = "";
    private String montoString = "";
    private String alias = "";
    private boolean beneficiarioExiste = false;

    public String getAlias() {
        return alias;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public String getTipoCuentaSigla() {
        return tipoCuentaSigla;
    }

    public String getMonedaSigla() {
        return monedaSigla;
    }



    public Double getMonto(){
        Double mont = 0.0;
        if (beneficiarioExiste){
            mont = Double.parseDouble(montoString);
        }
        return mont;
    }

    public MicroCombOrigenPinPesos consultarMicro(String usuario){
        cedula = usuario;
        try {
            Response response = BaseRequestHTTP.configNewRequest()
                    .param("transactionId", UUID.randomUUID())
                    .param("transactionType","PI")
                    .param("isRecurrence","N")
                    .param("documentNumber",usuario)
                    .param("transChannel","")
                    .get( url );
            statusCode = response.getStatusCode();

            productos = Utilidad.jsonNodeParse(response);
            System.out.println(response.getBody().asString());
            if (statusCode != 200 && statusCode != 204){
                setError("Error consultando el combo origen PIN Pesos. Status: " + statusCode, false);
            }

        }catch (Exception e){
            setError("Error consultando el combo origen PIN Pesos: " + e, false);
        }
        return this;
    }

    public MicroCombOrigenPinPesos buscarCuenta(String numCuenta){
        if ( statusCode == 200 ){
            if ( productos.size() > 0 ){
                for ( JsonNode product: productos ){
                    if ( product.get("account").asText().equals( numCuenta )){
                        this.numeroCuenta = product.get("account").asText();
                        tipoCuenta = product.get("type").asText();
                        tipoCuentaSigla = product.get("productType").asText();
                        montoString = product.get("amount").asText();
                        alias = product.get("alias").asText();
                        beneficiarioExiste = true;
                        break;
                    }else {
                        setError("El usuario: " + cedula + ", no contiene la cuenta: " + numCuenta, false);
                    }
                }
            }
        } else if ( statusCode == 204 ) {
            setError("El usuario: " + cedula + ", no tiene cuentas", false);
        }


        return this;
    }

    public void setError(String msgError, boolean beneficiarioExiste) {
        this.numeroCuenta = msgError;
        this.tipoCuenta = msgError;
        this.alias = msgError;
        this.tipoCuentaSigla = msgError;
        this.beneficiarioExiste = beneficiarioExiste;
    }
}
