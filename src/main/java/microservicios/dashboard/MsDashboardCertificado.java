package microservicios.dashboard;

import com.fasterxml.jackson.databind.JsonNode;
import http.BaseRequestHTTP;
import io.restassured.response.Response;
import microservicios.Utilidad;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 07/06/2024 4:28 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class MsDashboardCertificado extends BaseRequestHTTP {

    private String documentNumber;
    private Response response;
    private JsonNode certificados;
    private JsonNode certificado;
    private String mensaje;

    public MsDashboardCertificado(){

    }

    public MsDashboardCertificado(String documentNumber){
        this.documentNumber = documentNumber;
        realizarConsulta();
    }

    public MsDashboardCertificado newQuery(String documentNumber){
        this.documentNumber = documentNumber;
        realizarConsulta();
        return this;
    }

    public MsDashboardCertificado newQuery(){
        realizarConsulta();
        return this;
    }


    private void realizarConsulta(){
        response = httGET(inicializarParametos(),getHeaders(),"https://ibp-sqa.app.noprod.cfbhd.com/bhdleon/v1/system/dashboard/certificates");
        certificados = Utilidad.jsonNodeParse(response);
    }

    private Map<String, Object> getHeaders(){
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept","*/*");
        headers.put("Connection","keep-alive");
        return headers;
    }

    private Map<String, Object> inicializarParametos(){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("documentNumber",documentNumber);
        parametros.put("transactionId", UUID.randomUUID());
        return parametros;
    }

    public void buscar(String numeroCertificado){
        if (isSuccessful()){
            System.out.println("Respuesta exitosa");
            System.out.println(certificados);
            System.out.println(certificados.asText());

            if (!certificados.asText().equals("")){
                for (JsonNode certificadoActual : certificados){
                    System.out.println("Entro a for.");
                    if (certificadoActual.get("productNumber").asText().equalsIgnoreCase(numeroCertificado)){
                        certificado = certificadoActual;
                        System.out.println(certificado);
                        break;
                    }else {
                        mensaje = "El certificado: "+ numeroCertificado + ", no existe.";
                    }
                }
            }else {
                mensaje = "La respuesta de certificado es null o vacia.";
            }

        } else if (isNoContent()){
            mensaje = "El usuario no contiene certificados.";
                    System.out.println("El usuario no contiene certificados.");
        }
        else {
            System.out.println(getMessageError());
        }
//        if (getStatusCode() == 206 || getStatusCode() == 200){
//
//
//        }else if (getStatusCode() == 204){
//
//        }

//        System.out.println(certificados);
//        System.out.println();
//        if (certificados.isEmpty() || certificados.asText().equals(""))
//        if (certificados == null || certificados.asText().equals("")){
//
//            if (getStatusCode()> 300){
//                System.out.println("El micro servicio de certificado ha fallado: " + getStatusCode());
//            } else {
//                System.out.println("No contiene certificados");
//            }
//
//        }else {
//            System.out.println("certificado: " + certificados);
//            for (JsonNode certificadoActual : certificados){
//                if (certificadoActual.get("productNumber").asText().equalsIgnoreCase(numeroCertificado)){
//                    certificado = certificadoActual;
//                    System.out.println(certificado);
//                    break;
//                }else {
//                    System.out.println("El certificado no existe");
//                }
//            }
//        }

    }


    public String getAlias(){
        if (certificado == null){
            return mensaje;
        }else {
            mensaje = certificado.get("alias").asText();
        }
        return mensaje;
    }

    public String getNumProducto(){
        if (certificado == null){
            return mensaje;
        }else {
            mensaje = certificado.get("productNumber").asText();
        }
        return mensaje;
    }

    public String getNombreProducto(){
        if (certificado == null){
            return mensaje;
        }else {
            mensaje = certificado.get("name").asText();
        }
        return mensaje;
    }

    public String getNombreTipoProducto(){
        if (certificado == null){
            return mensaje;
        }else {
            mensaje = certificado.get("type").asText();
        }
        return mensaje;
    }

    public String getMoneda(){
        if (certificado == null){
            return mensaje;
        }else {
            mensaje = certificado.get("currency").asText();
        }
        return mensaje;
    }

    public String getStatusProduct(){
        if (certificado == null){
            return mensaje;
        }else {
            mensaje = certificado.get("status").asText();
        }
        return mensaje;
    }

    public String getTipoSigla(){
        if (certificado == null){
            return mensaje;
        }else {
            mensaje = certificado.get("productType").asText();
        }
        return mensaje;
    }










    public static void main(String[] args) {
        MsDashboardCertificado certificados = new MsDashboardCertificado("01800101790");  // 01800101790  22301391524
        certificados.buscar("3000258");
    }
}
