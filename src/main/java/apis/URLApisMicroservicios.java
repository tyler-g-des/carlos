package apis;

import java.util.UUID;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 25/04/2024 10:15 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class URLApisMicroservicios {

    private URLApisMicroservicios(){

    }

    private static String hostSQA = "https://api-sqa.bhdleon.com.do";
    private static String host = hostSQA;
    //public static final String URL_API_LOGIN_GET =     host+ "https://api-sqa.bhdleon.com.do/bhdleon/api/v1/personal/web/login";
    //public static final String URL_API_SECURE_DEVICE_ = host+ "/bhdleon/api/v1/personal/web/secure-device";
    public static final String URL_API_CLIENT_INFO_GET = host+ "/bhd/api/v2.1/personal/web/client-info";
    public static final String URL_API_IMAGE_GET = host+ "/bhdleon/api/v1/personal/web/image";

    String resulto = "{\"channel\":\"IBP\",\"platform\":\"IBP\", \"deviceId\":\"%s\", \"transactionId\":\""+ UUID.randomUUID() +"\"," +"\"uuid\":\"%s\"    ,\" +\"customerCodeT24\":\"%s\"}";
    /**
     * PARAMS: deviceId, uuidLogin
     */
    public static final String JSON_ENCRIPTAR_COMUN_DASHBOARD = "{\"channel\":\"IBP\",\"platform\":\"IBP\"," +
                                                                 "\"deviceId\":\"%s\"," +
                                                                 "\"transactionId\":\""+ UUID.randomUUID() +"\",\n" +
                                                                 "\"uuid\":\"%s\"}";


}
