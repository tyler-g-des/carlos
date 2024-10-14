package servicios.apis.ibp;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
//import servicios.devops.ApiServiciosDevops;
import servicios.apis.devops.ApiServiciosDevops;
import util.Utilidad;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ApiLoginAutomatizacion {

    private static ApiServiciosDevops devops = null;
    //private static ApiLoginAutomatizacion login = null;
    private String deviceId = "025d6c67035ac349ce784363aa367960";
    private String clientId = "586a3572-34a1-414b-a12b-18f0339ab1cc";
    private String clientSecret = "46371f28-0c4b-4332-9567-d3ccabb247c8";
    private static String x50Cert = "LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUMwakNDQWJxZ0F3SUJBZ0lFSmRBdHNEQU5CZ2txaGtpRzl3MEJBUVVGQURBck1Ta3dKd1lEVlFRREV5QmkKWkRnNFlURTNObVV6WXprd01qWm1ZekkwTXpWak1EQmpNMk01TUdVNE56QWVGdzB5TWpFd01EY3hOalE1TURKYQpGdzB5TkRFd01EWXhOalE1TURKYU1Dc3hLVEFuQmdOVkJBTVRJR0prT0RoaE1UYzJaVE5qT1RBeU5tWmpNalF6Ck5XTXdNR016WXprd1pUZzNNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQTFDd1UKaHFVNHpES1pPUnFudm5sQ0tMTkhyN2xlQXM5UkdwOU83dmxGd3F3OG03ZHFMTUx5dGx0ZUdlR2dzaXBteENCSwpZbC8xNEwrcjU3bTdXMVEvTVVESnViaDZjS0RpVG9iZWs3L0ZrOE9vQ21POFFvRjJyOW5qQkhVYXZyK1pwWmFiCnBuQzRMSXptMXdhMFV3SGUyeitGYmoyVVJjMlgrR3hKTk40c3FQZGtqWnUzazQ0UHVhU2l0L2tnZkN3anhxb0QKWjB4TkhqTkxKNUdBQnVyWGZ0bTd3UTdpWHJOMFh5K2VUQUNKVUJRcU5KaDhSSTlGWm5hTVNwcnFyWkFEZmpXMgpzZGJVVXZlQ0Y1K2VFRGtPTGZYcVJwNFY3eXprb2Q3VVZzRGdiQkNmQTlCWlRKaHVUc2xJckoxVVMyTTFXUy9WCjBGY0Q2amt6a291cTlZUkcyd0lEQVFBQk1BMEdDU3FHU0liM0RRRUJCUVVBQTRJQkFRQ1U3bEdyOXh3UHQ5QXYKeDM5WjlBdjFPOUxNMENiUnVmUkdYM0QzS0k3MGFsN3MrdVB6cVBpUU43bG5QaW9jUFdtUmF4ZEk3bmtwVlNOUwpRV1RSZ0Zody9HWXUrREhQeC96b1MvdmVZcUpYbk1iSjBQWTBzb1R5T2lzdkxsKzVMYWcwK1pTbEQxeHcyRFVJCldXaURmV2VNNEZnd1BxTmd4YmdkTXVVQnpPbDdXQWFwUE1Dby9lV2tjNHo2Z25SWFBEaGw4OXJHdTRtamZqcEUKRUpuL0RqVStJcDNTbGpNdWtLVHhvbTZGT3czanBLekplOG44YXVEVVF1S01DcGVITlM5SE1ic3haVitINVFsTQo4TmxieHpHcUQwUld3UVhtT29iZmo5c2xFUWFYSzNPam1uSFNNRVVEdGhSTUFPV0hzdElHM0NaZUZLY0hpUk03Ck50ci9HM3dDCi0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K";
    private int statusCode = 0;


    public static ApiLoginAutomatizacion getInstance(){
        return new ApiLoginAutomatizacion();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public boolean isContractAccepted() {
        return contractAccepted;
    }

    public String getTipoValidacion() {
        return tipoValidacion;
    }

    public ApiLoginAutomatizacion login(String usuario, String contrasena){
        try {
            if (devops == null){
                devops = ApiServiciosDevops.getInstance();
                devops.generateNewUUID(x50Cert);
            }
            Map<String, Object> encriptar = new HashMap<>();
            encriptar.put("channel","IBP");
            encriptar.put("platform","IBP");
            encriptar.put("deviceId",deviceId);
            encriptar.put("transactionId", UUID.randomUUID());
            encriptar.put("uuid", devops.getUuid());
            encriptar.put("userId", usuario);
            encriptar.put("password", contrasena);

            Map<String, Object> body = new HashMap<>();
            body.put("data", devops.encriptarData(encriptar));
            body.put("clientId",clientId);
            body.put("clientSecret",clientSecret);

            Map<String, Object> header = new HashMap<>();
            header.put("clientId",clientId);
            header.put("clientSecret",clientSecret);
            header.put("x-keyvalue",devops.getxKeyValue());

            Response response = BaseRequestHTTP.setHttPOST(body,header).post(BaseRequestHTTP.HOST_API_SQA_IBP + "/bhdleon/api/v1/personal/web/login");

            statusCode = response.statusCode();
            JsonNode data = Utilidad.jsonNodeParse( response );

            if (statusCode == 200){
                JsonNode desencriptado = devops.desencriptarData(devops.getUuid(), data.get("data").asText());
                firstLogin = desencriptado.get("firstLogin").asBoolean();
                changePassword = desencriptado.get("changePassword").asBoolean();
                contractAccepted = desencriptado.get("contractAccepted").asBoolean();
                tipoValidacion = desencriptado.get("validationType").asText();
            }
        }catch (Exception e){

        }
        return this;
    }

    private boolean firstLogin = false;
    private boolean changePassword = false;
    private boolean contractAccepted = false;
    private String tipoValidacion = "";

    public static void main(String[] args) {

        ApiLoginAutomatizacion.getInstance().login("00101412476","1111");
        ApiLoginAutomatizacion.getInstance().login("00100965995","1111");

    }


}
