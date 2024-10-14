package apis.ibp;

import apis.devops.ServiciosDevops;
import basetest.BaseTest;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import microservicios.Utilidad;
import org.testng.Assert;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Email: carlos_loyola@bhd.com.do
 * @ClassName: APISLogin
 * @CreationData: 01/06/2024 12:37 p. m.
 * @ProjectName: AutomatizacionAPI_IBP
 */
public class APISLogin {

    private ServiciosDevops serviciosDevops = null;
    public static final String HOST = "https://api-sqa.bhdleon.com.do";
    private String clientId = "586a3572-34a1-414b-a12b-18f0339ab1cc";
    private String clientSecret = "46371f28-0c4b-4332-9567-d3ccabb247c8";
    private String deviceId = "025d6c67035ac349ce784363aa367960";
    private String x50Cert = "LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUMwakNDQWJxZ0F3SUJBZ0lFSmRBdHNEQU5CZ2txaGtpRzl3MEJBUVVGQURBck1Ta3dKd1lEVlFRREV5QmkKWkRnNFlURTNObVV6WXprd01qWm1ZekkwTXpWak1EQmpNMk01TUdVNE56QWVGdzB5TWpFd01EY3hOalE1TURKYQpGdzB5TkRFd01EWXhOalE1TURKYU1Dc3hLVEFuQmdOVkJBTVRJR0prT0RoaE1UYzJaVE5qT1RBeU5tWmpNalF6Ck5XTXdNR016WXprd1pUZzNNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQTFDd1UKaHFVNHpES1pPUnFudm5sQ0tMTkhyN2xlQXM5UkdwOU83dmxGd3F3OG03ZHFMTUx5dGx0ZUdlR2dzaXBteENCSwpZbC8xNEwrcjU3bTdXMVEvTVVESnViaDZjS0RpVG9iZWs3L0ZrOE9vQ21POFFvRjJyOW5qQkhVYXZyK1pwWmFiCnBuQzRMSXptMXdhMFV3SGUyeitGYmoyVVJjMlgrR3hKTk40c3FQZGtqWnUzazQ0UHVhU2l0L2tnZkN3anhxb0QKWjB4TkhqTkxKNUdBQnVyWGZ0bTd3UTdpWHJOMFh5K2VUQUNKVUJRcU5KaDhSSTlGWm5hTVNwcnFyWkFEZmpXMgpzZGJVVXZlQ0Y1K2VFRGtPTGZYcVJwNFY3eXprb2Q3VVZzRGdiQkNmQTlCWlRKaHVUc2xJckoxVVMyTTFXUy9WCjBGY0Q2amt6a291cTlZUkcyd0lEQVFBQk1BMEdDU3FHU0liM0RRRUJCUVVBQTRJQkFRQ1U3bEdyOXh3UHQ5QXYKeDM5WjlBdjFPOUxNMENiUnVmUkdYM0QzS0k3MGFsN3MrdVB6cVBpUU43bG5QaW9jUFdtUmF4ZEk3bmtwVlNOUwpRV1RSZ0Zody9HWXUrREhQeC96b1MvdmVZcUpYbk1iSjBQWTBzb1R5T2lzdkxsKzVMYWcwK1pTbEQxeHcyRFVJCldXaURmV2VNNEZnd1BxTmd4YmdkTXVVQnpPbDdXQWFwUE1Dby9lV2tjNHo2Z25SWFBEaGw4OXJHdTRtamZqcEUKRUpuL0RqVStJcDNTbGpNdWtLVHhvbTZGT3czanBLekplOG44YXVEVVF1S01DcGVITlM5SE1ic3haVitINVFsTQo4TmxieHpHcUQwUld3UVhtT29iZmo5c2xFUWFYSzNPam1uSFNNRVVEdGhSTUFPV0hzdElHM0NaZUZLY0hpUk03Ck50ci9HM3dDCi0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K";
    private String bfpHash = "eyJmaW5nZXJwcmludCI6ImI1NzQ2MDZhYjE2ZDllM2M0ZWUyZTZhMjg0MzYyZWNmIiwiZmluZ2VycHJpbnRJbmZvIjp7Imxhbmd1YWdlIjoiZXMtRVMiLCJjb2xvckRlcHRoIjoyNCwidGltZXpvbmVPZmZzZXQiOjI0MCwiaGFzU2Vzc2lvblN0b3JhZ2UiOnRydWUsImhhc0xvY2FsU3RvcmFnZSI6dHJ1ZSwiaGFzSW5kZXhlZERiIjp0cnVlLCJoYXNBZGRCZWhhdmlvciI6ZmFsc2UsImhhc09wZW5EYXRhYmFzZSI6ZmFsc2UsIm5hdmlnYXRvckNwdUNsYXNzIjoibmF2aWdhdG9yQ3B1Q2xhc3M6IHVua25vd24iLCJuYXZpZ2F0b3JQbGF0Zm9ybSI6Im5hdmlnYXRvclBsYXRmb3JtOiBXaW4zMiIsImRvTm90VHJhY2siOiJkb05vdFRyYWNrOiB1bmtub3duIiwicGx1Z2lucyI6IlBERiBWaWV3ZXI6OlBvcnRhYmxlIERvY3VtZW50IEZvcm1hdDo6YXBwbGljYXRpb24vcGRmfnBkZix0ZXh0L3BkZn5wZGY7Q2hyb21lIFBERiBWaWV3ZXI6OlBvcnRhYmxlIERvY3VtZW50IEZvcm1hdDo6YXBwbGljYXRpb24vcGRmfnBkZix0ZXh0L3BkZn5wZGY7Q2hyb21pdW0gUERGIFZpZXdlcjo6UG9ydGFibGUgRG9jdW1lbnQgRm9ybWF0OjphcHBsaWNhdGlvbi9wZGZ+cGRmLHRleHQvcGRmfnBkZjtNaWNyb3NvZnQgRWRnZSBQREYgVmlld2VyOjpQb3J0YWJsZSBEb2N1bWVudCBGb3JtYXQ6OmFwcGxpY2F0aW9uL3BkZn5wZGYsdGV4dC9wZGZ+cGRmO1dlYktpdCBidWlsdC1pbiBQREY6OlBvcnRhYmxlIERvY3VtZW50IEZvcm1hdDo6YXBwbGljYXRpb24vcGRmfnBkZix0ZXh0L3BkZn5wZGYiLCJoYXNBZEJsb2NrIjpmYWxzZSwiaGFzTGllZExhbmd1YWdlcyI6ZmFsc2UsImhhc0xpZWRSZXNvbHV0aW9uIjpmYWxzZSwiaGFzTGllZE9zIjpmYWxzZSwiZm9udHMiOiJBZ2VuY3kgRkI7QWxnZXJpYW47QXJpYWw7QXJpYWwgQmxhY2s7QXJpYWwgTmFycm93O0Jhc2tlcnZpbGxlIE9sZCBGYWNlO0JhdWhhdXMgOTM7QmVsbCBNVDtCZXJsaW4gU2FucyBGQjtCZXJuYXJkIE1UIENvbmRlbnNlZDtCbGFja2FkZGVyIElUQztCb2RvbmkgNzI7Qm9kb25pIDcyIE9sZHN0eWxlO0JvZG9uaSA3MiBTbWFsbGNhcHM7Qm9kb25pIE1UO0JvZG9uaSBNVCBCbGFjaztCb2RvbmkgTVQgQ29uZGVuc2VkO0Jvb2sgQW50aXF1YTtCb29rbWFuIE9sZCBTdHlsZTtCb29rc2hlbGYgU3ltYm9sIDc7QnJhZGxleSBIYW5kIElUQztCcm9hZHdheTtCcnVzaCBTY3JpcHQgTVQ7Q2FsaWJyaTtDYWxpZm9ybmlhbiBGQjtDYWxpc3RvIE1UO0NhbWJyaWE7Q2FtYnJpYSBNYXRoO0NhbmRhcmE7Q2FzdGVsbGFyO0NlbnRhdXI7Q2VudHVyeTtDZW50dXJ5IEdvdGhpYztDZW50dXJ5IFNjaG9vbGJvb2s7Q2hpbGxlcjtDb2xvbm5hIE1UO0NvbWljIFNhbnMgTVM7Q29uc29sYXM7Q29uc3RhbnRpYTtDb29wZXIgQmxhY2s7Q29wcGVycGxhdGUgR290aGljO0NvcHBlcnBsYXRlIEdvdGhpYyBMaWdodDtDb3JiZWw7Q291cmllcjtDb3VyaWVyIE5ldztDdXJseiBNVDtFYnJpbWE7RWR3YXJkaWFuIFNjcmlwdCBJVEM7RWxlcGhhbnQ7RW5nbGlzaCAxMTEgVml2YWNlIEJUO0VuZ3JhdmVycyBNVDtGZWxpeCBUaXRsaW5nO0Zvb3RsaWdodCBNVCBMaWdodDtGb3J0ZTtGcmFua2xpbiBHb3RoaWM7RnJhbmtsaW4gR290aGljIEJvb2s7RnJhbmtsaW4gR290aGljIEhlYXZ5O0ZyYW5rbGluIEdvdGhpYyBNZWRpdW07RnJlZXN0eWxlIFNjcmlwdDtGcmVuY2ggU2NyaXB0IE1UO0dhYnJpb2xhO0dhcmFtb25kO0dlb3JnaWE7R2VvU2xhYiA3MDMgTHQgQlQ7R2VvU2xhYiA3MDMgWEJkIEJUO0dpZ2k7R2lsbCBTYW5zIE1UO0dpbGwgU2FucyBNVCBDb25kZW5zZWQ7R291ZHkgT2xkIFN0eWxlO0dvdWR5IFN0b3V0O0hhZXR0ZW5zY2h3ZWlsZXI7SGFycmluZ3RvbjtIZWx2ZXRpY2E7SGlnaCBUb3dlciBUZXh0O0h1bWFuc3QgNTIxIENuIEJUO0ltcGFjdDtJbXByaW50IE1UIFNoYWRvdztJbmZvcm1hbCBSb21hbjtKb2tlcm1hbjtKdWljZSBJVEM7S3Jpc3RlbiBJVEM7S3Vuc3RsZXIgU2NyaXB0O0x1Y2lkYSBCcmlnaHQ7THVjaWRhIENhbGxpZ3JhcGh5O0x1Y2lkYSBDb25zb2xlO0x1Y2lkYSBGYXg7THVjaWRhIEhhbmR3cml0aW5nO0x1Y2lkYSBTYW5zO0x1Y2lkYSBTYW5zIFR5cGV3cml0ZXI7THVjaWRhIFNhbnMgVW5pY29kZTtNYWduZXRvO01haWFuZHJhIEdEO01hbGd1biBHb3RoaWM7TWFybGV0dDtNYXR1cmEgTVQgU2NyaXB0IENhcGl0YWxzO01pY3Jvc29mdCBIaW1hbGF5YTtNaWNyb3NvZnQgSmhlbmdIZWk7TWljcm9zb2Z0IE5ldyBUYWkgTHVlO01pY3Jvc29mdCBQaGFnc1BhO01pY3Jvc29mdCBTYW5zIFNlcmlmO01pY3Jvc29mdCBUYWkgTGU7TWljcm9zb2Z0IFlhSGVpO01pY3Jvc29mdCBZaSBCYWl0aTtNaW5nTGlVX0hLU0NTLUV4dEI7TWluZ0xpVS1FeHRCO01pc3RyYWw7TW9kZXJuIE5vLiAyMDtNb25nb2xpYW4gQmFpdGk7TW9ub3R5cGUgQ29yc2l2YTtNUyBHb3RoaWM7TVMgT3V0bG9vaztNUyBQR290aGljO01TIFJlZmVyZW5jZSBTYW5zIFNlcmlmO01TIFJlZmVyZW5jZSBTcGVjaWFsdHk7TVMgU2FucyBTZXJpZjtNUyBTZXJpZjtNUyBVSSBHb3RoaWM7TVYgQm9saTtOaWFnYXJhIEVuZ3JhdmVkO05pYWdhcmEgU29saWQ7TlNpbVN1bjtPbGQgRW5nbGlzaCBUZXh0IE1UO09ueXg7UGFsYWNlIFNjcmlwdCBNVDtQYWxhdGlubyBMaW5vdHlwZTtQYXB5cnVzO1BhcmNobWVudDtQZXJwZXR1YTtQZXJwZXR1YSBUaXRsaW5nIE1UO1BsYXliaWxsO1BNaW5nTGlVLUV4dEI7UG9vciBSaWNoYXJkO1ByaXN0aW5hO1JhdmllO1JvY2t3ZWxsO1JvY2t3ZWxsIENvbmRlbnNlZDtTZWdvZSBQcmludDtTZWdvZSBTY3JpcHQ7U2Vnb2UgVUk7U2Vnb2UgVUkgTGlnaHQ7U2Vnb2UgVUkgU2VtaWJvbGQ7U2Vnb2UgVUkgU3ltYm9sO1Nob3djYXJkIEdvdGhpYztTaW1TdW47U2ltU3VuLUV4dEI7U25hcCBJVEM7U3RlbmNpbDtTeWxmYWVuO1RhaG9tYTtUZW1wdXMgU2FucyBJVEM7VGltZXM7VGltZXMgTmV3IFJvbWFuO1RyZWJ1Y2hldCBNUztUdyBDZW4gTVQ7VHcgQ2VuIE1UIENvbmRlbnNlZDtVbml2ZXJzIENFIDU1IE1lZGl1bTtWZXJkYW5hO1ZpbmVyIEhhbmQgSVRDO1ZpdmFsZGk7VmxhZGltaXIgU2NyaXB0O1dpZGUgTGF0aW47V2luZ2RpbmdzO1dpbmdkaW5ncyAyO1dpbmdkaW5ncyAzIiwiY3JjQ2FudmFzIjoiZTJjODVjMjQiLCJjcmNXZWJHbCI6IjVkNzhmMmM0Iiwic2NyZWVuUmVzb2x1dGlvbkluZm8iOnsic2NyZWVuSGVpZ2h0IjoxMDI0LCJzY3JlZW5XaWR0aCI6MTI4MCwiYXZhaWxhYmxlSGVpZ2h0Ijo5OTQsImF2YWlsYWJsZVdpZHRoIjoxMjgwfX0sInN5c3RlbUluZm8iOnsidWEiOiJNb3ppbGxhLzUuMCAoV2luZG93cyBOVCAxMC4wOyBXaW42NDsgeDY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvMTIzLjAuMC4wIFNhZmFyaS81MzcuMzYiLCJicm93c2VyIjp7Im5hbWUiOiJDaHJvbWUiLCJ2ZXJzaW9uIjoiMTIzLjAuMC4wIiwibWFqb3IiOiIxMjMifSwiZW5naW5lIjp7Im5hbWUiOiJXZWJLaXQiLCJ2ZXJzaW9uIjoiNTM3LjM2In0sIm9zIjp7Im5hbWUiOiJXaW5kb3dzIiwidmVyc2lvbiI6IjEwIn0sImRldmljZSI6e30sImNwdSI6eyJhcmNoaXRlY3R1cmUiOiJhbWQ2NCJ9fX0=";
    private String urlLogin = "/bhdleon/api/v1/personal/web/login";
    private String urlFeatureFlags = "/bhdleon/api/v2/utility/setting/feature-flags/sdk-qM5vusoFDEuzh36";
    private String urlForgotPassword = "/bhdleon/api/v1/personal/web/forgot-password";
    private String urlChangePassword = "/bhdleon/api/v1/personal/web/change-password";
    private String urlOTP = "/bhdleon/api/v1/personal/web/otp";
    private String urlSecureDevice = "/bhdleon/api/v1/personal/web/secure-device";

    private int statusCode = 0;
    private String documentNumber = "";
    private String dataComunEncriptada = "";
    private String jwtLogin = "";
    private String jwtSecureDevice = "";
    private String keyClientSecret = "clientSecret";
    private String keyClientId = "clientId";
    private String keyXkeyvalue = "x-keyvalue";

    /**
     * pathServicio, TipoSolicitud
     */
    private String messageRespuestaExitosa = "Servicio: %s <br>Tipo de solicitud: %s <br>Acción: %s <br>Response Status Code: %s";
    private String message = "";
    /**
     * Accion,Exception, Servicio, TipoSolicitud, Accion
     */
    private String messagePlantillaError = "Error al intentar (%S):  <br>\n%s" +
            "<br><br>\n" +
            "Servicio: %s <br>\n" +
            "Tipo de solicitud: %S <br>\n" +
            "Acción: %S. <br>\n" +
            "Status code: No se puede obtener status Code de este error.";



    /**
     * Retorna el mensaje configurado en el solicitud actual, la cual servirá para crear el reporte final.
     * @return Mensaje de reporte.
     */
    public String getMessageReport() {
        return message;
    }

    /**
     * Permite configurar o setear el mensaje para la creación de reporte.
     * @param message
     */
    public void setMessageReport(String message) {
        this.message = message;
    }

    /**
     * Este constructor por defecto se encarga de inicializar el objeto del servicio devops, el cual se encargara
     * de crear las Llaves uuid y x-keyValue que son necesarios para poder realizar las solicitudes en el canal.
     */
    public APISLogin(){
        serviciosDevops = new ServiciosDevops();
        serviciosDevops.generarXKeyValue(x50Cert);
        getDataComunEncriptada();
    }

    /**
     * Retorna el host del canal.
     * @return https://api-sqa.bhdleon.com.do
     */
    public String getHost(){
        return HOST;
    }

    /**
     * Retorna el clientId.
     * @return clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Retorna el key clientId.
     * @return clientId
     */
    public String getKeyClientId() {
        return keyClientId;
    }

    /**
     * Retorna el valor del clientSecret.
     * @return clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    public String desencriptar(String dataEncriptada){
        return serviciosDevops.desencriptarData(getUUID(), dataEncriptada);
    }

    /**
     * Retorna el key clientSecret.
     * @return clientSecret
     */
    public String getKeyClientSecret() {
        return keyClientSecret;
    }

    public String getDeviceId(){
        return deviceId;
    }


    /**
     * Retorna el uuid generado con el certificado x50cert. El uuid es necesario para encriptar la
     * data que se envia. El mismo debe enviarse en el Json de la data que se va a encriptar.
     * @return uuid
     */
    public String getUUID(){
        return serviciosDevops.getUuid();
    }

    /**
     * Retorna el x-keyvalue generado con el certificado x50cert. El x-keyvalue es necesario para enviarlo en los header de la solicitud.
     * @return x-keyvalue
     */
    public String getXKeyValue(){
        return serviciosDevops.getXkeyValue();
    }

    /**
     * Retorna el keyXkeyvalue que contiene el valor: x-keyvalue
     * @return x-keyvalue
     */
    public String getKeyXkeyvalue() {
        return keyXkeyvalue;
    }


    /**
     * Llena el Status code con el Status Code enviado por parámetro.
     * @param statusCode statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Retorna el status Code del servicio actual.
     * @return statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Devuelve el numero de documento del usuario actual.
     * @return documentNumber
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Retorna el jwt obtenido del servicio de login.
     * @return jwt
     */
    public String getJwtLogin() {
        return jwtLogin;
    }

    /**
     * Retorna el jwt obtenido del servicio de secureDevice.
     * @return jwt
     */
    public String getJwtSecureDevice() {
        return jwtSecureDevice;
    }

    /**
     * Retorna la instancia creada del servicio devops que contiene el uuid, y el x-keyvalue, que son necesarios para las solicitudes del canal.
     * @return ServiciosDevops
     */
    public ServiciosDevops getServiciosDevops() {
        return serviciosDevops;
    }

    /**
     * Retorna un mapa con data que es común en algunas peticiones del canal. channel, platform, deviceId, transactionId, uuid.
     * @return Map<String, Object>
     */
    public Map<String, Object> getMapaComunUUID(){
        Map<String, Object> jsonComunEnRequest = new HashMap<>();
        jsonComunEnRequest.put("channel", "IBP");
        jsonComunEnRequest.put("platform", "IBP");
        jsonComunEnRequest.put("deviceId", deviceId);
        jsonComunEnRequest.put("transactionId", UUID.randomUUID());
        jsonComunEnRequest.put("uuid", serviciosDevops.getUuid());
        return jsonComunEnRequest;
    }

    /**
     * Retorna la data encriptada que es común en algunas peticiones del canal. channel, platform, deviceId, transactionId.
     * @return data encriptada.
     */
    public String getDataComunEncriptada(){
        if (dataComunEncriptada.equals("")){
            dataComunEncriptada = serviciosDevops.encriptarData(new Gson().toJson(getMapaComunUUID()));
        }
        return dataComunEncriptada;
    }

    /**
     * Este método permite crear un body que contiene el clientId y clientSecret que son necesarios para las
     * solicitudes del tipo: POST, DELETE, PUT. solo hay que add el key data y la data encriptada en el value.
     * @return Map<String, Object>
     */
    private Map<String, Object> getMapaBodyComunRequest(){
        Map<String, Object> bodyComun = new HashMap<>();
        bodyComun.put(getKeyClientId(), clientId);
        bodyComun.put(getKeyClientSecret(), clientSecret);
        return bodyComun;
    }

    /**
     * Esta API permite autenticarse ante el sistema IBP.
     * @param usuario Numero de documento del usuario.
     * @param contrasena Actual del usuario.
     * @param accion que se esta realizando sobre el servicio.
     */
    public void login(String usuario, String contrasena, String accion){
        statusCode = 0;
        documentNumber = usuario;
        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("userId",usuario);
        encriptar.put("password",contrasena);

        Map<String, Object> body = getMapaBodyComunRequest();
        body.put("data", serviciosDevops.encriptarData(new Gson().toJson(encriptar)));

        try {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .header(getKeyXkeyvalue(),serviciosDevops.getXkeyValue())
                    .header(getKeyClientId(),clientId)
                    .header(getKeyClientSecret(),clientSecret)
                    .body(new Gson().toJson(body)).log().all()
                    .post(HOST + urlLogin);

            statusCode = response.getStatusCode();
            String msg = format(messageRespuestaExitosa,urlLogin, "POST", accion, getStatusCode());
            setMessageReport(msg);

            if (statusCode == 200){
                jwtLogin = Utilidad.jsonNodeParse(response).get("x-jwt").asText();

            }else if (statusCode == 401){
                msg = format(messageRespuestaExitosa,urlLogin, "POST", "Inicio de sesión no autorizado.", getStatusCode());
                setMessageReport(msg);
            }

        }catch (Exception e){
            setStatusCode(0);
            String error =  format(messagePlantillaError,"iniciar sesión",e,urlLogin,"POST","iniciar sesión");
            BaseTest.createStep( error,false,false);
            //Assert.assertEquals( getStatusCode(),200 );
        }
    }

    /**
     * Esta API Permite validar que la seguridad del dispositivo. Esta validación es requerida para el consumo de las demás APIS del canal.
     * @param firstLogin Permite seleccionar si el usuario es de primer inicio de sesión o no.
     * @param accion que se esta realizando sobre el servicio.
     */
    public void secureDevice(boolean firstLogin, String accion){
        statusCode = 0;
        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("ip", "127.0.0.0");
        encriptar.put("server", "127.0.0.0");
        encriptar.put("bfpHash", bfpHash);
        encriptar.put("validationType", "SQT");
        encriptar.put("firstLogin", firstLogin);

        Map<String, Object> body = getMapaBodyComunRequest();
        body.put("data", serviciosDevops.encriptarData( new Gson().toJson( encriptar )));

        try {
            Response response = given().config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                    .contentType(ContentType.JSON)
                    .header(getKeyXkeyvalue(), serviciosDevops.getXkeyValue())
                    .header("jwt", jwtLogin)
                    .body(new Gson().toJson( body ))
                    .log().all().post( HOST + urlSecureDevice);

            statusCode = response.getStatusCode();

            String msg = format(messageRespuestaExitosa, urlSecureDevice, "POST", accion, getStatusCode());
            setMessageReport(msg);

            if (statusCode==200){
                jwtSecureDevice = Utilidad.jsonNodeParse(response).get("x-jwt").asText();
            }

        }catch (Exception e){
            setStatusCode(0);
            String error =  format(messagePlantillaError,"Validar dispositivo seguro",e, urlSecureDevice, "POST","Validar dispositivo seguro");
            BaseTest.createStep( error,false,false);
            //Assert.assertEquals(getStatusCode(),200);
        }
    }

    /**
     * Este método permite logarse y validar que el dispositivo sea seguro.
     * @param user numero de documento para ingresar.
     * @param password del usuario para ingresar.
     * @param accion que se esta realizando sobre el servicio.
     */
    public void loginSecureDevice(String user, String password, String accion){
        login(user, password,"Iniciar sesion correctamente");
        if (statusCode == 200){
            secureDevice(false, accion);
        }
    }

    /**
     * Permite consultar el servicio de featureFlags en el login.
     * @param accion que se esta realizando sobre el servicio.
     */
    public void featureFlags(String accion){
        try {
            Response response = RestAssured. given(). urlEncodingEnabled(true)
                    .header(getKeyXkeyvalue(), serviciosDevops.getXkeyValue())
                    .header(getKeyClientSecret(), getClientSecret())
                    .header(getKeyClientId(), getClientId())
                    .log(). all(). get(HOST + urlFeatureFlags+ "?" + getDataComunEncriptada().replace("=",""));
            setStatusCode(response.getStatusCode());

            String msg = format(messageRespuestaExitosa,urlFeatureFlags, "POST", accion, getStatusCode());
            setMessageReport(msg);
        }catch (Exception e){
            setStatusCode(0);
            setMessageReport(format(messagePlantillaError,accion,e,urlFeatureFlags,"POST",accion));
            BaseTest.createStep(getMessageReport(), false, false);
        }
    }

    /**
     * Este método valida que un numero de documento sea valido para el flujo de olvidar contraseña - forgotPassword. Se necesita la ejecución del método de login antes de ejecutar este método.
     * @param username numero de documento de usuario valido.
     * @param accion que se esta realizando sobre el servicio.
     */
    public void forgotPasswordValidarUsuario(String username, String accion){
        try {
            Map<String, Object> encriptar = getMapaComunUUID();
            encriptar.put("username", username);

            Map<String, Object> body = getMapaBodyComunRequest();
            body.put("data", serviciosDevops.encriptarData(new Gson().toJson( encriptar )));

            Response response = RestAssured. given(). contentType( ContentType.JSON )
                    .header(getKeyXkeyvalue(), serviciosDevops.getXkeyValue())
                    .header("jwt", jwtLogin)
                    .header(getKeyClientSecret(), getClientSecret())
                    .header(getKeyClientId(), getClientId())
                    .body(new Gson().toJson( body ))
                    .log(). all(). post(HOST + urlForgotPassword);

            setStatusCode(response.getStatusCode());

            String msg = format(messageRespuestaExitosa,urlForgotPassword, "POST", accion, getStatusCode());
            setMessageReport(msg);

        } catch (Exception e) {
            setStatusCode(0);
            setMessageReport(format(messagePlantillaError,accion,e,urlForgotPassword,"POST",accion));
            BaseTest.createStep(getMessageReport(), false, false);

        }
    }

    /**
     * Este método envía una solicitud para el envió de código OTP para cambio de contraseña en el login.
     * @param accion que se esta realizando sobre el servicio.
     */
    public void solicitarEnvioOtpParaCambioContrasenaLogin(String accion){
        try {
            Map<String, Object> body = getMapaBodyComunRequest();
            body.put("data",getDataComunEncriptada());

            Map<String, Object> dataBody = new HashMap<>();
            dataBody.put(getKeyClientId(), clientId);
            dataBody.put(getKeyClientSecret(), clientSecret);
            dataBody.put("data", getDataComunEncriptada());
            Response response = RestAssured .given() .contentType( ContentType.JSON )
                    .body( new Gson().toJson( dataBody ))
                    .header(getKeyXkeyvalue(),serviciosDevops.getXkeyValue())
                    .header("GeneratedUUID", getUUID())
                    .header("jwt", jwtLogin )
                    .log(). all(). post( HOST +  urlOTP );

            setStatusCode(response.getStatusCode());

            String msg = format(messageRespuestaExitosa,urlOTP, "POST", accion, getStatusCode());
            setMessageReport(msg);

        }catch (Exception e){
            setStatusCode(0);
            setMessageReport(format(messagePlantillaError,accion,e,urlOTP,"POST",accion));
            BaseTest.createStep(getMessageReport(), false, false);
        }
    }

    /**
     * Este método permite cambiar la contraseña utilizando OTP.
     * @param tolen OTP enviado.
     * @param newPassword Nueva contraseña que se asignara al usuario.
     * @param accion que se esta realizando sobre el servicio.
     */
    public void changePasswordOTP(String tolen, String newPassword, String accion){
        try {

            Map<String, Object> encriptar = getMapaComunUUID();
            encriptar.put("token", tolen);
            encriptar.put("newPassword", newPassword);

            Map<String, Object> body = getMapaBodyComunRequest();
            body.put("data",serviciosDevops.encriptarData(new Gson().toJson(encriptar)));

            Response response = RestAssured .given() .contentType( ContentType.JSON )
                    .body( new Gson().toJson( body ))
                    .header(getKeyXkeyvalue(),serviciosDevops.getXkeyValue())
                    .header("GeneratedUUID", getUUID())
                    .header("jwt", jwtLogin )
                    .header(getKeyClientSecret(),clientSecret)
                    .header(getKeyClientId(), clientId)
                    .log(). all(). post( HOST +  urlChangePassword );

            setStatusCode(response.getStatusCode());

            String msg = format(messageRespuestaExitosa,urlChangePassword, "POST", accion, getStatusCode());
            setMessageReport(msg);
        }catch (Exception e){
            setMessageReport(format(messagePlantillaError,accion,e,urlChangePassword,"POST",accion));
            BaseTest.createStep(getMessageReport(), false, false);
            setStatusCode(0);
        }
    }

}
