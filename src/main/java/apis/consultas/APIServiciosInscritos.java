package apis.consultas;

import apis.ibp.APISLogin;
//import apis.ibp.dashboard.APISLogin;
import apis.serviciosdevops.ServiciosDevops;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import microservicios.Utilidad;
import microservicios.beneficiarios.MsServiciosInscritos;

//import static apis.ibp.dashboard.APISLogin.DEVICE_ID;
//import static apis.ibp.dashboard.APISLogin.HOST;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 06/05/2024 1:01 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APIServiciosInscritos {

    private APISLogin login;
    Response response;

    private String urlServiciosInscritosGet =  "/bhdleon/api/v1/personal/web/beneficiaries-services?";
    private String urlProveedoresDeServicioGET = "/bhdleon/api/v1/personal/web/payments-providers?";
    public APIServiciosInscritos(APISLogin login){
        this.login = login;
    }

    public int getStatusCode(){
        return response.getStatusCode();
    }

    public void getListaServiciosInscritos(){
        response = null;
        //if (login.isResponseSuccessful()){
            response = RestAssured
                    .given()
                    .urlEncodingEnabled(true)
                    .header("x-keyvalue",login.getXKeyValue())
                    .header("GeneratedUUID", login.getUUID())
                    .header("jwt",login.getJwtSecureDevice())
                    .log().all()
                    .get(urlServiciosInscritosGet + login.getDataComunEncriptada());

            System.out.println("LISTA SERVICIOS INSCRITOS: " + response.getStatusCode());

//        }else {
//            System.out.println("Algo salio mal con API LISTA SERVICIOS INSCRITOS ");
//        }
    }

    /**
     * Falta Desencriptar lada.
     */
    public void getProveedoresDeServicios(){
        response = null;
        //if (login.isResponseSuccessful()){
            response = RestAssured
                    .given()
                    .urlEncodingEnabled(true)
                    .header("x-keyvalue",login.getXKeyValue())
                    .header("GeneratedUUID", login.getUUID())
                    .header("jwt",login.getJwtSecureDevice())
                    .log().all()
                    .get(urlProveedoresDeServicioGET + login.getDataComunEncriptada());

            System.out.println("LISTA PROVEEDIRES DE SERVICIOS: " + response.getStatusCode());
            JsonNode data = Utilidad.jsonNodeParse(response);

            //JsonNode dataDesecriptada = Utilidad.jsonNodeParse(  ServiciosDevops.getInstance().desencriptarData(login.getUUID(), data.get("data").asText())  );
            String resultd = ServiciosDevops.getInstance().desencriptarData(login.getUUID(), data.get("data").asText());

            //JsonNode node = Utilidad.jsonNodeParse(  )
            System.out.println("RESULTADO DESENCRIPTADO 1: " + resultd);
            JsonNode sacar = Utilidad.jsonNodeParse(resultd);
            System.out.println("Sacado: " +  sacar);

            JsonNode dataDesencriptada = Utilidad.jsonNodeParse(resultd);

            //System.out.println("RESULTADO DESENCRIPTADO: " + resultd);
            JsonNode dat = Utilidad.jsonNodeParse(resultd);

            System.out.println("RESULTADO DESENCRIPTADO: " + dataDesencriptada);
            //System.out.println(dat.get("data"));


//        }else {
//            System.out.println("Algo salio mal con API LISTA PROVEEDIRES DE SERVICIOS");
//        }
    }


//    public void eliminarServicio(String id){
//
//        if (login.isResponseSuccessful()){
//
//            Map<String, Object> encriptar = new HashMap<>();
//            encriptar.put("channel","IBP");
//            encriptar.put("deviceId",DEVICE_ID);
//            encriptar.put("platform","IBP");
//            encriptar.put("transactionId",UUID.randomUUID());
//            encriptar.put("id",id);
//            encriptar.put("uuid",login.getUUID());
//
//            Map<String, Object> dataBody = new HashMap<>();
//            dataBody.put("data",ServiciosDevops.getInstance().encriptarData(new Gson().toJson(encriptar)));
//            dataBody.put("clientId",login.getClientId());
//            dataBody.put("clientSecret",login.getClientSecret());
//
//            response  = RestAssured
//                    .given()
//                    .contentType(ContentType.JSON)
//                    .body(new Gson().toJson(dataBody))
//                    .header("x-keyvalue",login.getXKeyValue())
//                    .header("GeneratedUUID", login.getUUID())
//                    .header("jwt",login.getJwtSecureDevice())
//                    .header("clientId",login.getClientId())
//                    .header("clientSecret",login.getClientSecret())
//                    .log().all()
//                    .delete(urlServiciosInscritosGet.replace("?",""));
//
//            System.out.println("ELIMINAR SERVICIO: " + response.getStatusCode());
//        }
//    }

// Descomentar
//    public void agregarActualizarServicio(String id, String serviceProvider, String servicio, String referencia, String descripcion, String providerId, String referenceNumber, String serviceId ){
//
//        if (login.isResponseSuccessful()){
//            response = null;
//            try {
//                Map<String, Object> service = new HashMap<>();
//                if (!id.equals("") && !id.equalsIgnoreCase("NA")){
//                    service.put("Id","1-5DPQI6N"); // id
//                }
//                service.put("acceptPaymentCC","S");
//                service.put("affectedService","Pago de Facturas");  // servicio  - Compra de Recargas
//                service.put("currency","RD");
//                service.put("description","Pago de factura descripcion"); // descripcion
//                service.put("domiciliationCollectionPeriod","No. de Telefono");  // No. de Telefono
//                service.put("domiciliationReference","No. de Telefono");         // No. de Telefono
//                service.put("infoReference","No. de Telefono");                  // No. de Telefono
//                service.put("newCurrency","RD");
//                service.put("newcurrency","RD");
//                service.put("providerId","101001577");        // providerId
//                service.put("referenceNumber","8094291548");  // referenceNumber - referencia
//                service.put("serviceId","4071");              // serviceId
//                service.put("serviceProvider","CLARO");       // serviceProvider
//
//                Map<String, Object> bodyAEncriptar = new HashMap<>();
//                bodyAEncriptar.put("channel","IBP");
//                bodyAEncriptar.put("platform","IBP");
//                bodyAEncriptar.put("transactionId", UUID.randomUUID());
//                bodyAEncriptar.put("deviceId",DEVICE_ID);
//                bodyAEncriptar.put("service",service);
//
//                bodyAEncriptar.put("uuid",login.getUUID());
//
//                String dataEncriptada = ServiciosDevops.getInstance().encriptarData(new Gson().toJson(bodyAEncriptar));
//
//                Map<String, Object> body = new HashMap<>();
//                body.put("data",dataEncriptada);
//                body.put("clientId",login.getClientId());
//                body.put("clientSecret", login.getClientSecret());
//
//                response = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body(new Gson().toJson( body ))
//                        .header("clientId",login.getClientId())
//                        .header("clientSecret",login.getClientSecret())
//                        .header("x-keyvalue",login.getXKeyValue())
//                        .header("GeneratedUUID", login.getUUID())
//                        .header("jwt",login.getJwtSecureDevice())
//                        .log().all()
//                        .post(urlServiciosInscritosGet.replace("?",""));
//
//                System.out.println("AGREGAR ACTUALIZAR SERVICIO: " + getStatusCode());
//                //System.out.println(String.format("AGREGAR SERVICIO STATUS: %s: ", contratoType)  +response.getStatusCode());
//                //codigoDeEstado = response.getStatusCode();
//            }catch (Exception e){
//                //codigoDeEstado = response.getStatusCode();
//                //System.out.println(String.format( "ALGO SALIO MAL EN ACCEPT CONTRACT %s: ", contratoType)   + codigoDeEstado);
//            }
//
//        }else {
//            //codigoDeEstado = login.getStatusCode();
//        }
//    }

    public static void main(String[] args) {
        MsServiciosInscritos msServicio = new MsServiciosInscritos("22301391524").newQuery().buscar("8094291548");
        System.out.println( "IDÑ " + msServicio.getId());
        APISLogin login = new  APISLogin();
        login.loginSecureDevice("22301391524","1111","Inicio de sesion");

        APIServiciosInscritos serviciosInscritos = new APIServiciosInscritos(login);


        serviciosInscritos.getListaServiciosInscritos();
        serviciosInscritos.getProveedoresDeServicios();
        //serviciosInscritos.agregarActualizarServicio("","","","","","","","");

       // serviciosInscritos.eliminarServicio(msServicio.getId());
    }
}
