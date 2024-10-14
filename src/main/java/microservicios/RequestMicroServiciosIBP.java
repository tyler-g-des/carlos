package microservicios;

import basetest.BaseTest;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import microservicios.beneficiarios.MsServiciosInscritos;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;

import java.util.UUID;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;



public class RequestMicroServiciosIBP {

    private final RequestSpecification conf;
    private RequestMicroServiciosIBP(){
        RestAssured.baseURI = "https://ibp-sqa.app.noprod.cfbhd.com";
        conf = given().config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
    }

    /** <h3>Retorna una nueva instancia de la clase RequestsMSIBP. </h3>
     * @return RequestsMSIBP
     */
    public static RequestMicroServiciosIBP newQuery(){
        return new RequestMicroServiciosIBP();
    }

    /** <h3>Realiza una nueva consulta del servicio newFeature y retorna el response con la data.  Permite verificar publicidad en el dashboard.</h3>
     * @param documentNumber Numero de cedula del usuario.
     * @return Response
     */
    public Response dashboardNewFeature(String documentNumber){
        Response response = conf.param("idNumber", documentNumber)
                .param("transactionId","IBP-Automatizacion-001234")
                .get("/bhdleon/v1/system/services/newfeature");
        Logger.getLogger(response.getBody().asString());
        return response;
    }

    public Response beneficiariosNacionales(String documentNumber){
        Response response = conf.
                param("transactionId","AutomatizaciónIBP-"+ UUID.randomUUID())
                .param("documentNumber",documentNumber)
                .get("/bhdleon/v1/system/customer/beneficiaries/beneficiaries");

        Logger.getLogger(response.getBody().asString());
        return response;
    }

    public Response getBeneficiariosInternacionales(String documentNumber){
        Response response = conf.
                param("transactionId","AutomatizaciónIBP-"+ UUID.randomUUID())
                .param("documentNumber",documentNumber)
                .get("/bhdleon/v1/system/customer/international/beneficiaries");

        Logger.getLogger(response.getBody().asString());
        return response;
    }

    public Response getClientInfoV2(String documentNumber){
        Response response =  null;
        try {
            response  = conf.
                    param("transactionId","AutomatizaciónIBP-"+ UUID.randomUUID())
                    .param("personalChannelID",documentNumber)
                    .param("channel","IBP")
                    .get("/bhd/v2/system/login/client/info");
        }catch (Exception e){
            BaseTest.createStep("Fallo en el servicio Client Info: " + e,false,false);
            Logger.getLogger("Fallo en servicio clientInfo: " + e);
        }

        return response;
    }

    public Response getContrato(String documentNumber, String contrato){
        Response response = conf.
                param("transactionId","AutomatizaciónIBP-"+ UUID.randomUUID())
                .param("documentNumber",documentNumber)
                .param("type",contrato)
                .get("/bhdleon/v1/system/services/contract");

        Logger.getLogger(response.getBody().asString());
        return response;
    }

    public Response consultarCodigoInternacional(String documentNumber, String tipoCodigo, String codigo){
        Response response = conf
                .param("documentNumber",documentNumber)
                .param("transactionId","AutomatizaciónIBP-"+ UUID.randomUUID())
                .param("type",tipoCodigo.replace("SWIFT","2").replace("ABA","1")) // SWIFT: type = 2; ABA: type = 1;
                .param("code",codigo) // AETCUS55XXX
                .get("/bhdleon/v1/system/international/checkcode");

        Logger.getLogger(response.getBody().asString());
        return response;
    }

    public Response consultarServiciosInscritos(String documentNumber){
        Response response = conf
                .param("documentNumber",documentNumber)
                .param("transactionId",//"AutomatizaciónIBP-"+
                        UUID.randomUUID())
                .get("/bhdleon/v1/system/customer/services-beneficiaries/beneficiaries");

        Logger.getLogger(response.getBody().asString());
        return response;
    }

    public Response dashboardCuentas(String documentNumber){
        Response response = conf.
                param("transactionId","AutomatizaciónIBP-"+ UUID.randomUUID())
                .param("documentNumber",documentNumber)
                .get("/bhdleon/v1/system/dashboard/accounts");

        Logger.getLogger(response.getBody().asString());
        return response;
    }

    public Response dashboardCrediclick(String documentNumber){
        Response response = conf.
                param("transactionId","AutomatizaciónIBP-"+ UUID.randomUUID())
                .param("documentNumber",documentNumber)
                .get("/bhdleon/v2/system/dashboard/financing");

        Logger.getLogger(response.getBody().asString());
        return response;
    }

    public static void main(String[] args) {
        MsServiciosInscritos servicios = new MsServiciosInscritos("22301391524").newQuery().buscar("ARGOS DOMINICANA","8092318604");

        System.out.println(servicios.getProveedorServicio());
        System.out.println(servicios.getServicio());
        System.out.println(servicios.getReferencia());
        System.out.println(servicios.getDescripcion());

    }
}
