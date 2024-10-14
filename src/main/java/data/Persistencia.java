package data;

import microservicios.MSClientInfoV2;
import microservicios.MSPreguntasDeSeguridad;
import org.openqa.selenium.WebDriver;
import servicios.apis.ibp.ApiLoginAutomatizacion;
import servicios.micros.MicroClientInfo;
import servicios.micros.MicroContratos;
import servicios.micros.dashboard.MicroDashboardCrediclickIBP;
import servicios.micros.dashboard.MicroDashboardNewFeature;
import servicios.micros.pagostransferencias.MicroCombOrigenPinPesos;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 03/04/2024 2:04 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class Persistencia {
    private static Persistencia instancia;
    private  ArrayList< MSClientInfoV2 > listaClientInfo;
    private MSClientInfoV2 clientInf;
    private static Properties sesiones;// = new Properties();
    private String nombreCompleto = "";

    private Persistencia(){}

    public static Persistencia getInstance(){
        if (instancia==null){
            instancia = new Persistencia();
            sesiones = new Properties();
        }
        return instancia;
    }

    public void newQueryClientInfoV2(String documentNumber){
        MSClientInfoV2 clienInfo = new MSClientInfoV2(documentNumber);

        System.out.println(clienInfo.getStatusCode());
        if (clienInfo.getStatusCode() == 200){
            sesiones.put(clienInfo.getCompleteNamePascalCase(), clienInfo);
            sesiones.put(clienInfo.getDocumentNumber(), new MSPreguntasDeSeguridad(documentNumber));

        }
    }

    public static boolean tienePreguntasConfiguradas(String nombreCompleto){

        if (sesiones.containsKey(nombreCompleto)){
            System.out.println("Esta llegando a tienePreguntasConfiguradas if");
            System.out.println("Si existe el nombre");
            MSPreguntasDeSeguridad seguridad = (MSPreguntasDeSeguridad) sesiones.get( getDocumentNumber(nombreCompleto));
            return  seguridad.tienePreguntasSeguridadConfiguradas();
        }else {
            System.out.println("Esta llegando a tienePreguntasConfiguradas else");
            return  false;
        }
    }



    public void newQueryClientInfo(String documentNumber){
        clientInf = new MSClientInfoV2(documentNumber).newQuery();
        nombreCompleto = clientInf.getCompleteNamePascalCase();

        sesiones.put("esEmpleado" + nombreCompleto, clientInf.esEmpleado());
        sesiones.put("usuario" + nombreCompleto, documentNumber);
        sesiones.put("correo"+documentNumber,clientInf.getEmail());
        sesiones.put("completeName"+documentNumber, clientInf.getCompleteName());
    }

    public void newQueryApiLogin(String usuario, String contrasena){
        sesiones.put("login-"+usuario, ApiLoginAutomatizacion.getInstance().login( usuario, contrasena ));
    }

    public void setClientInfo(String cedula){
        MicroClientInfo clientInfo = MicroClientInfo.getInstance().consultar(cedula);
        sesiones.put("clienInfo"+cedula, clientInfo);
    }

    public void setClientInfo(String cedula, WebDriver driver){
        MicroClientInfo clientInfo = MicroClientInfo.getInstance().consultar(cedula);
        sesiones.put("info"+driver, clientInfo);
    }

    public MicroClientInfo getClientInfo(WebDriver driver){
        return (MicroClientInfo) sesiones.get("info"+driver);
    }

    public void consultarComboOrigenPinPesos(String identificador, String cedula){
        MicroCombOrigenPinPesos pinpesos = new MicroCombOrigenPinPesos().consultarMicro(cedula);
        if (sesiones.containsKey(identificador)){
            sesiones.remove(identificador);
        }
        sesiones.put(identificador, pinpesos);
    }

    public MicroCombOrigenPinPesos getOrigenPinPeso(String identificador){
        return (MicroCombOrigenPinPesos) sesiones.get(identificador);
    }


    public void consultarDashboardPublicidad(String cedula){
        MicroDashboardNewFeature newFeature = new MicroDashboardNewFeature( cedula ).newQuery();
        if (sesiones.containsKey("publicidad"+cedula)){
            sesiones.remove("publicidad"+ cedula);
        }
        sesiones.put("publicidad"+cedula, newFeature );
    }



    public MicroDashboardNewFeature getPublicidad(String cedula){
        return (MicroDashboardNewFeature) sesiones.get("publicidad"+cedula);
    }

    public MicroClientInfo getClientInfo(String cedula){
        return (MicroClientInfo) sesiones.get("clienInfo"+cedula);
    }



    public void setLoginApi(String usuario, String contrasena){
        ApiLoginAutomatizacion login = ApiLoginAutomatizacion.getInstance().login(usuario, contrasena);
        sesiones.put("login-"+usuario, login);
    }

    public ApiLoginAutomatizacion getApiLogin(String usuario){
        return (ApiLoginAutomatizacion) sesiones.get("login-"+usuario);
    }



    public void setMicroContratos(String usuario){
        MicroContratos contratos = MicroContratos.getInstance();
        if (!sesiones.containsKey("contratos"+usuario)){
            sesiones.put("contratos"+usuario, contratos);
        }
    }

    public MicroContratos getMicroContratos(String usuario){
        return (MicroContratos) sesiones.get("contratos"+usuario);
    }

    public void setMicroDashboardCrediclick(String cedula){
        System.out.println("Cedula");
        MicroDashboardCrediclickIBP dashboardCredilcick = new MicroDashboardCrediclickIBP().consultar(cedula);
        if (sesiones.containsKey("crediclick"+ cedula)){
            sesiones.replace("crediclick"+ cedula, dashboardCredilcick);
        }else {
            sesiones.put("crediclick"+ cedula, dashboardCredilcick);
        }
    }

    public MicroDashboardCrediclickIBP getDashboardCrediclick(String usuario){
        return (MicroDashboardCrediclickIBP) sesiones.get("crediclick"+usuario);
    }

//    public ApiLoginAutomatizacion getLogin(String nombreCompleto){
//        MSClientInfoV2 info = (MSClientInfoV2) sesiones.get(nombreCompleto);
//        return (ApiLoginAutomatizacion) sesiones.get()
//    }

//    public boolean esEmpleado(String nombreCompleto){
//        return Boolean.parseBoolean(sesiones.get("esEmpleado" + nombreCompleto).toString());
//    }

    public boolean esEmpleado(String nombreCompleto){
        if (sesiones.containsKey(nombreCompleto)){
            MSClientInfoV2 info = (MSClientInfoV2) sesiones.get(nombreCompleto);
            return info.esEmpleado();
        }else {
            return  false;
        }
    }



    private void newQueryPreguntasSeguridad(String documentNumber){
        MSPreguntasDeSeguridad seguridad = new MSPreguntasDeSeguridad(documentNumber);
    }


//    public String getDocumentNumber(String nombreCompleto){
//        return sesiones.get("usuario"+ nombreCompleto).toString();
//    }

    public String getCorreo(String documentNumber){
        return sesiones.get("correo"+documentNumber).toString();
    }

//    public String getNombreCompleto(String documentNumber){
//        return sesiones.get("completeName"+documentNumber).toString();
//    }

    public String getNombreCompleto(String nombreCompleto){
        if (sesiones.containsKey(nombreCompleto)){
            MSClientInfoV2 info = (MSClientInfoV2) sesiones.get(nombreCompleto);
            return info.getCompleteNamePascalCase();
        }else {
            return  "NULO";
        }
    }

    public static String getDocumentNumber(String nombreCompleto){
        if (sesiones.containsKey(nombreCompleto)){
            MSClientInfoV2 info = (MSClientInfoV2) sesiones.get(nombreCompleto);
            return info.getDocumentNumber();
        }else {
        return  "NULO";
        }
    }






}
