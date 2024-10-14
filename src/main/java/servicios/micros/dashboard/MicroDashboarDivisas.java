package servicios.micros.dashboard;

import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.UUID;

public class MicroDashboarDivisas {

    private int statusCode = 0;
    private String compraDolarString = "";
    private String ventaDolarString = "";
    private String compraEuroString = "";
    private String ventaEuroString = "";

    private MicroDashboarDivisas(){
    }

    public static MicroDashboarDivisas getInstance(){
        return new MicroDashboarDivisas();
    }

    private void setError(int statusCode, String error){
        String msg = "Se ha produccido un error consultando la divisas. Error: "+ error + " "+ statusCode;
        compraDolarString = msg;
        ventaDolarString = msg;
        compraEuroString = msg;
        ventaEuroString = msg;
    }

    public MicroDashboarDivisas consultar(String documentNumber){
        try {
            Response response = BaseRequestHTTP.configNewRequest()
                    .param("transactionId", UUID.randomUUID())
                    .param("documentNumber", documentNumber)
                    .get("https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/services/exchange-rates");
            statusCode = response.getStatusCode();
            if ( statusCode == 200 ){
                compraDolarString = response.getBody().jsonPath().getString("usdBuyingRate");
                ventaDolarString = response.getBody().jsonPath().getString("usdSellingRate");
                compraEuroString = response.getBody().jsonPath().getString("eurNormalBuyingRate");
                ventaEuroString = response.getBody().jsonPath().getString("eurNormalSellingRate");
            }else {
                setError(statusCode, "");
            }
        }catch (Exception e){
            setError(statusCode, e.toString());
        }
        return this;
    }

    public String getCompraDolarLabel(){
        return Utilidad.darFormatoMonedaComasPunto(compraDolarString);
    }

    public float getCompraDolar(){
        return Float.parseFloat(compraDolarString);
    }

    public String getCompraEuroLabel(){
        return Utilidad.darFormatoMonedaComasPunto(compraEuroString);
    }

    public float getCompraEuro(){
        return Float.parseFloat(compraEuroString);
    }

    public String getVentaDolarLabel(){
        return Utilidad.darFormatoMonedaComasPunto(ventaDolarString);
    }

    public float getVentaDolar(){
        return Float.parseFloat(ventaDolarString);
    }

    public String getVentaEuroLabel(){
        return Utilidad.darFormatoMonedaComasPunto(ventaEuroString);
    }

    public float getVentaEuro(){
        return Float.parseFloat(ventaEuroString);
    }

}
