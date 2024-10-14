package servicios.micros.pagostransferencias.combox;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.UUID;

public class MicroComboOrigenTercero {

    private final String URL_COMBO = "https://ibp-sqa.app.noprod.cfbhd.com/bhd/v2/process/comboboxes/origin-products";

    private JsonNode productos = null;
    private boolean existe = false;
    private int statusCode = 0;

    private String alias = "";
    private String numProducto = "";
    private String moneda = "";
    private String simboloMoneda = "";
    private String tipo = "";
    private String tipoSigla = "";
    private String montoString = "";


    private String canal = "";
    private String usuario = "";

    public String getUsuario() {
        return usuario;
    }

    public String getCanal() {
        return canal;
    }

    public MicroComboOrigenTercero consultarBHD(String usuario){
        canal = "TBHD";
        consultar( canal, usuario );
        return this;
    }

    public MicroComboOrigenTercero consultarACH(String usuario){
        canal = "ACH";
        consultar( canal, usuario );
        return this;
    }

    public MicroComboOrigenTercero consultarLBTR(String usuario){
        canal = "LBTR";
        consultar( canal, usuario );
        return this;
    }

    private void consultar(String canal, String usuario){
        this.usuario = usuario;
        try {
            Response response = BaseRequestHTTP.configNewRequest()
                    .param("transactionId", UUID.randomUUID())
                    .param("transactionType","OB")
                    .param("isRecurrence","N")
                    .param("transChannel",canal)
                    .param("documentNumber",usuario)
                    .log().all()
                    .get(URL_COMBO);
            statusCode = response.getStatusCode();
            productos = Utilidad.jsonNodeParse(response);
            System.out.println(productos.size());
            for (int i = 0; i < productos.size(); i++) {
                System.out.println(productos.get(i));;
            }

        }catch (Exception e){

        }
    }

    public MicroComboOrigenTercero buscar(String numProduct){
        try {
            if (statusCode == 200){
                if (productos.size() > 0){
                    for (JsonNode producto: productos){
                        if (producto.get("account").asText().equals(numProduct)){
                            moneda = producto.get("currency").asText();
                            this.numProducto = producto.get("account").asText();
                            tipo = producto.get("type").asText();
                            montoString = producto.get("amount").asText();
                            simboloMoneda = producto.get("currencySymbol").asText();
                            alias = producto.get("alias").asText();
                            tipoSigla = producto.get("productType").asText();
                            existe = true;
                            break;
                        }else {
                            setError("El producto: "+ producto+ " no existe: ");
                        }
                    }
                }else {
                    setError("No estan cargando los productos del cliente.");
                }
            }else if (statusCode == 204){
                setError("El cliente no posee productos");
            }else {
                setError("Error consultando los productos del cliente. Status: " + statusCode);
            }
        }catch (Exception e){
            setError("Error consultando los productos del cliente: " + e);
        }
        return this;
    }

    private void setError(String error){
        moneda = error;
        this.numProducto = error;
        tipo = error;
        montoString = error;
        simboloMoneda = error;
        alias = error;
        tipoSigla = error;
        existe = false;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getAlias() {
        return alias;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getNumProducto() {
        return numProducto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTipoSigla() {
        return tipoSigla;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public Double getMonto(){
        Double mont = 0.0;
        if (existe){
            mont = Double.parseDouble(montoString);
        }
        return mont;
    }
}
