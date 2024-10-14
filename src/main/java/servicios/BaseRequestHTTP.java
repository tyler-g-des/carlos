package servicios;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class BaseRequestHTTP {

    public static final String HOST_MICRO_SQA_IBP = "https://ibp-sqa.app.noprod.cfbhd.com";
    public static final String HOST_MICRO_SQA_MBP = "https://mbp-sqa.app.noprod.cfbhd.com";
    public static final String HOST_API_SQA_IBP = "https://api-sqa.bhdleon.com.do";
    public static final String HOST_API_SERVICIO_DEVOPS = "https://devops.cfbhd.com/controlador/";
    public static RequestSpecification configNewRequest(){
        return RestAssured.given().config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
    }

    public static RequestSpecification setHttPOST(Map<String, Object> body, Map<String, Object> headers){
        return configNewRequest().given()
                .contentType(ContentType.JSON)
                .headers( headers )
                .body(new Gson().toJson(body))
                .log()
                .all();
    }

    public static RequestSpecification setHttPOST( Map<String, Object> body){
        return configNewRequest().given()
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(body))
                .log()
                .all();
    }


}
