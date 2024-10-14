package apis.ibp;

import microservicios.dashboard.MsProveedoresServicios;
import microservicios.servicios.MsServiciosInscritos;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 07/06/2024 10:25 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APIPagoServiciosImpuesto{

    private BaseRequest baseRequest;
    private APISLogin login;


    public APIPagoServiciosImpuesto(APISLogin login) {
        baseRequest = new BaseRequest(login);
        this.login = login;
    }

    public String getMessageReport(){
        return baseRequest.getMessageReport();
    }

    public int getStatusCode(){
        return baseRequest.getStatusCode();
    }

    public void setMessageReport(String msgReport){
        baseRequest.setMessageReport(msgReport);
    }

    /**
     *
     * @param serviceProvider das
     * @param servicioAffected das
     * @param numeroDeReferencia da
     * @param description da
     * @param providerId d
     * @param referenciaLabel dd
     * @param acceptPaymentCC dddd
     * @param serviceId ddsf
     * @param monedaSigla RD, US
     */
    public void agregarActualizarServicio(String id, String serviceProvider, String servicioAffected, String numeroDeReferencia, String description, String providerId, String referenciaLabel, String acceptPaymentCC, String serviceId, String monedaSigla, String accion){

        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();

        Map<String, Object> encriptar = baseRequest.getMapaComunUUID();
        Map<String, Object> service = new HashMap<>();

        service.put("acceptPaymentCC", acceptPaymentCC);
        service.put("affectedService", servicioAffected);
        service.put("billDescription", "");
        service.put("description", description);
        service.put("domiciliationCollectionPeriod", referenciaLabel);
        service.put("domiciliationReference", referenciaLabel);
        service.put("infoReference", referenciaLabel);
        service.put("providerId", providerId);
        service.put("referenceNumber", numeroDeReferencia);
        service.put("serviceId", serviceId);
        service.put("serviceProvider", serviceProvider);
        service.put("newCurrency", monedaSigla);
        service.put("newcurrency", monedaSigla);
        service.put("currency", monedaSigla);
        if (!id.equals("") && !id.equalsIgnoreCase("NA")){
            service.put("Id", id);
        }
        encriptar.put("service",service);
        baseRequest.httpPost(encriptar,"/bhdleon/api/v1/personal/web/beneficiaries-services",accion,nombreMetodo,getClass());
    }

    public void agregarServicio(String proveedorDeservicio, String servicio, String numeroDeReferencia, String descripcion, String accion){

        MsProveedoresServicios provedor = new MsProveedoresServicios(login.getDocumentNumber()).buscar( proveedorDeservicio, servicio);

        agregarActualizarServicio("",
                provedor.getServiceProvider(),
                provedor.getAffectedService(),
                numeroDeReferencia,
                descripcion,
                provedor.getProviderId(),
                provedor.getReferenceLabel(),
                provedor.getAcceptPaymentCC(),
                provedor.getServiceId(),
                "RD",
                accion
        );
    }

    public void actualizarServicio(String descripctionDeServicioAModificar,String proveedorDeservicio, String servicio, String numeroDeReferencia, String descripcion, String accion){
        MsProveedoresServicios provedor = new MsProveedoresServicios(login.getDocumentNumber()).buscar( proveedorDeservicio, servicio);
        MsServiciosInscritos serviciosInscritos = new MsServiciosInscritos(login.getDocumentNumber());
        serviciosInscritos.buscarServicioPorDescripcion(descripctionDeServicioAModificar);

        agregarActualizarServicio(
                serviciosInscritos.getId(),
                provedor.getServiceProvider(),
                provedor.getAffectedService(),
                numeroDeReferencia,
                descripcion,
                provedor.getProviderId(),
                provedor.getReferenceLabel(),
                provedor.getAcceptPaymentCC(),
                provedor.getServiceId(),
                "RD",
                accion
        );
    }

    public void eliminarServicio(String proveedorServico,String servicio, String referencia, String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        MsServiciosInscritos serviciosInscritos = new MsServiciosInscritos(login.getDocumentNumber());
        serviciosInscritos.buscar(proveedorServico,servicio,referencia);

        Map<String, Object> encriptar = baseRequest.getMapaComunUUID();
        encriptar.put("id",serviciosInscritos.getId());
        baseRequest.httpDelete(encriptar,"/bhdleon/api/v1/personal/web/beneficiaries-services",accion,nombreMetodo,getClass());
    }

//    public static void main(String[] args) {
//        APISLogin login = new APISLogin();
//        login.loginSecureDevice("22301391524","1111","Iniciar sesion");
//        APIPagoServiciosImpuesto apiPagoServicios = new APIPagoServiciosImpuesto(login);
//
////        apiPagoServicios.agregarServicio(
////                "CLARO",
////                "Compra de Recargas",
////                "8093334444",
////                "Servicio claro Compra de recarga desde API",
////                "Servicio claro Compra de recarga desde API");
//
//        apiPagoServicios.agregarServicio(
//                "COOPASPIRE",
//                "Cuotas",
//                "123458",
//                "COOPASPIRE agregado desde API",
//                "COOPASPIRE agregado desde API");
//
//
//
////        MsProveedoresServicios provedor = new MsProveedoresServicios(login.getDocumentNumber()).buscar("COOPASPIRE","Cuotas");
////
////
////        apiPagoServicios.agregarServicioBase(
////                "COOPASPIRE",
////                "Cuotas",
////                "123456",
////                "Servicio agregado desde API Testing",
////                provedor.getProviderId(),
////                provedor.getReferenceLabel(),
////                provedor.getAcceptPaymentCC(),
////                provedor.getServiceId(),
////                "RD",
////                "Agregar servicio correctamente");
//    }
}
