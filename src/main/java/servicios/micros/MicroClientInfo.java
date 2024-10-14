package servicios.micros;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.UUID;

public class MicroClientInfo {

    boolean esEmpleado = false;
    boolean datosDeContactoBloqueados = false;
    private String edad = "";
    private String contactId = "";
    private String nombreCompleto = "";
    private String nombre = "";
    private String segundoNombre = "";
    private String apellido = "";
    private String segundoApellido = "";
    private String correo = "";
    private String customerCode = "";
    private String customerCodeT24 = "";
    private String cedula = "";
    private boolean seMuestraConsentimientoCompartirInformacion = false;
    private boolean activoTDCTransaccionar = false;

    private boolean muestraCuentasInactivas = false;

    private static final String MENSAJE_USUARIO_SIN_INFO = "Este usuario [%s] no contiene informacion de cliente: Status= %s";
    private void setVariables(boolean esEmpleado, boolean datosDeContactoBloqueados, String edad, String contactId, String nombreCompleto, String nombre, String segundoNombre, String apellido, String segundoApellido, String correo, String customerCode, String customerCodeT24, String cedula, boolean seMuestraConsentimientoCompartirInformacion, boolean muestraCuentasInactivas, boolean estaActivoTDCTransaccionar) {
        this.esEmpleado = esEmpleado;
        this.datosDeContactoBloqueados = datosDeContactoBloqueados;
        this.edad = edad;
        this.contactId = contactId;
        this.nombreCompleto = nombreCompleto;
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.apellido = apellido;
        this.segundoApellido = segundoApellido;
        this.correo = correo;
        this.customerCode = customerCode;
        this.customerCodeT24 = customerCodeT24;
        this.cedula = cedula;
        this.seMuestraConsentimientoCompartirInformacion = seMuestraConsentimientoCompartirInformacion;
        this.muestraCuentasInactivas = muestraCuentasInactivas;
        this.activoTDCTransaccionar = !estaActivoTDCTransaccionar;
    }

    private void setVariablesError(String msgError){
        setVariables(false,false, msgError, msgError, msgError, msgError, msgError, msgError, msgError, msgError, msgError, msgError, msgError,false, false,false);
    }

    public boolean isEsEmpleado() {
        return esEmpleado;
    }

    public boolean isDatosDeContactoBloqueados() {
        return datosDeContactoBloqueados;
    }

    public String getEdad() {
        return edad;
    }

    public String getContactId() {
        return contactId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getNombreCompletoTitleCase(){
        return Utilidad.formatearPrimerasLetrasPalabrasEnMayuscula(nombreCompleto);
    }

    public String getNombre() {
        return nombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getCustomerCodeT24() {
        return customerCodeT24;
    }

    public String getCedula() {
        return cedula;
    }

    public boolean isSeMuestraConsentimientoCompartirInformacion() {
        return seMuestraConsentimientoCompartirInformacion;
    }

    public boolean isMuestraCuentasInactivas() {
        return muestraCuentasInactivas;
    }

    public boolean isActivoTDCTransaccionar() {
        return activoTDCTransaccionar;
    }

    public static MicroClientInfo getInstance(){
        return new MicroClientInfo();
    }

    private MicroClientInfo(){

    }
    //private String host = "https://ibp-sqa.app.noprod.cfbhd.com";
    private String servicio = "/bhd/v2/system/login/client/info";
    private Response response = null;

    private String msErrorServicioFinal = "Error en [Class: MicroClientInfo], [method: consultar], [Usuario: %s], [Error: %s], Servicio: %s.";
    private String errorEnServicio = "Se ha produccido un error en el Micro Servicio: %s. Status Code: %s";
    public MicroClientInfo consultar(String documentNumber){
        this.cedula = documentNumber;
        try {
            response = BaseRequestHTTP.configNewRequest().given()
                    .param("personalChannelID",documentNumber)
                    .param("transactionId", UUID.randomUUID())
                    .param("channel","IBP")
                    .log().all()
                    .get(BaseRequestHTTP.HOST_MICRO_SQA_IBP + servicio);
            System.out.println("ClienfIndo: " + response.getStatusCode());

            if (response.statusCode()==200){
                JsonNode data = Utilidad.jsonNodeParse(response);

                setVariables(
                        data.get("isEmployee").asBoolean(), data.get("contactDataLocked").asBoolean(),
                        data.get("age").asText(), data.get("contactId").asText(),
                        data.get("completeName").asText(), data.get("name").asText(),
                        data.get("secondName").asText(), data.get("lastName").asText(),
                        data.get("secondLastName").asText(), data.get("email").asText(),
                        data.get("customerCode").asText(), data.get("customerCodeT24").asText(),
                        data.get("cedula").asText(), data.get("showConsentForInformationSharing").asBoolean(),
                        data.get("inactiveAccountNotificationExpire").asBoolean(),
                        data.get("allowTransactionWithoutKeyCard").asBoolean()
                        );
            } else if (response.statusCode() == 204) {
                String errorSinInformacion = String.format(MENSAJE_USUARIO_SIN_INFO, cedula, response.getStatusCode());

                setVariables(
                        false, false,
                        errorSinInformacion, errorSinInformacion,
                        errorSinInformacion, errorSinInformacion,
                        errorSinInformacion, errorSinInformacion,
                        errorSinInformacion, errorSinInformacion,
                        errorSinInformacion, errorSinInformacion,
                        errorSinInformacion, false,
                        false, false
                );
            }else {
                String error = String.format( msErrorServicioFinal, cedula, "StatusCode:"+ response.getStatusCode(), servicio);

                setVariablesError(error);
//                setVariables(
//                        false, false,
//                        error, error, error, error, error, error, error, error, error, error, error,
//                        false, false
//                );
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return this;
    }
}
