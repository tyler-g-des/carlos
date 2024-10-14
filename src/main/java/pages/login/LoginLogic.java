package pages.login;

import basetest.BaseTest;
import data.Persistencia;
import microservicios.MSClientInfoV2;
import pages.dashboard.DashboardLogic;
import pages.login.restaurarclave.RestaurarClaveAccesoLogica;
import pages.validacionacceso.POValidacionAcceso;


/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 12/11/2023 7:20 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class LoginLogic {

    private POLogin pageLogin;
    private LoginLogic(){
        pageLogin = POLogin.getInstance();
    }

    /** Retorna una nueva instancia de la clase LoginLogic.
     * @return LoginLogic
     */
    public static LoginLogic get(){
        return new LoginLogic();
    }


    /** Verifica inicio de sesión con usuario correcto y contraseña incorrecta y contraseña y usuario no existente, a lo que se mostrara el mensaje: Usuario o contraseña inválidos.
     * @param usuario Usuario valido y usuario no existente.
     * @param contrasena Contraseña invalida y contraseña no existente
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Usuario o contraseña inválidos
     */
    public String verificarCredencialesIncorrectas(String usuario, String contrasena, boolean crearReporte){
        pageLogin.setFormLogin(usuario, contrasena, crearReporte);
        return pageLogin.verificarMsgCredencialesIncorrectas(crearReporte);
    }

    /** Verifica que se muestre el label indicando que no se permiten caracteres especiales.
     * @param usuario
     * @param contrasena
     * @param crearReporte
     * @return No se permiten caracteres especiales
     */
    public String verificarUsuarioConCaracteresEspeciales(String usuario, String contrasena, boolean crearReporte){
        pageLogin.setFormLogin(usuario, contrasena, crearReporte);
        return pageLogin.verificarMsgNoCaracteresEspeciales(crearReporte);
    }

    /** Inicia sesión desde un dispositivo que fue guardado como seguro, por lo cual debe redireccionar a la pagina de Dashboard 360.
     * @param usuario Usuario que haya guardado el dispositivo como seguro.
     * @param contrasena Contraseña valida del usuario.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public DashboardLogic iniciarDesdeDispositivoSeguro(String usuario, String contrasena, boolean crearReporte){
        pageLogin.setFormLogin(usuario, contrasena, crearReporte);
        //MSClientInfoV2 infoV2 = new MSClientInfoV2(usuario).newQuery();
        //Sesiones.instance().setUsuario();
        //System.out.println("Es empleado:" + infoV2.esEmpleado());
        //System.out.println(infoV2.getNombreCompleto());

        pageLogin.consultarClintInfo(usuario, crearReporte);
        //consultarClintInfo(usuario, crearReporte);

//        Persistencia.getInstance().newQueryClientInfo(usuario);
//        if (new MSClientInfoV2(usuario).newQuery().aceptarConsentimientoUsoInformacion()){
//            // 03102999269
//            pageLogin.aceptarContratoCompartirInformacion(crearReporte);
//
//            if (new MSClientInfoV2(usuario).newQuery().aceptarConsentimientoUsoInformacion()){
//                BaseTest.createStep("No se esta guardado la acceptacion del contrato de uso de informacion",false,false);
//            }
//        }



        //System.out.println("Es: " + new MSClientInfoV2(usuario).newQuery().esEmpleado());
        return DashboardLogic.getLogica();
    }

    public POValidacionAcceso iniciarSesion(String usuario, String contrasena, boolean crearReporte){
        Persistencia.getInstance().newQueryClientInfoV2(usuario);
        pageLogin.iniciarSesion(usuario, contrasena, crearReporte);
        return new POValidacionAcceso(pageLogin.getDriver());
    }

    public DashboardLogic inicioSesionGenerico(String usuario, String contrasena, boolean crearReporte){
        iniciarSesion(usuario, contrasena, crearReporte);
        pageLogin.validarAcceso( crearReporte);
        return DashboardLogic.getLogica();
    }

//    public DashboardLogic inicioSesionGenerico2(String usuario, String contrasena, boolean crearReporte){
//        iniciarSesion(usuario, contrasena, crearReporte);
//
//    }



    protected void consultarClintInfo(String usuario, boolean crearReporte){
        Persistencia.getInstance().newQueryClientInfo(usuario);
        if (new MSClientInfoV2(usuario).newQuery().aceptarConsentimientoUsoInformacion()){

            pageLogin.aceptarContratoCompartirInformacion(crearReporte);
            if (new MSClientInfoV2(usuario).newQuery().aceptarConsentimientoUsoInformacion()){
                BaseTest.createStep("No se esta guardado la acceptacion del contrato de uso de informacion",false,false);
            }

        }
    }

    /** Verifica que los campos usuario y contraseña son requeridos.
     * @param usuario Dejar campo vacio.
     * @param contrasena Dejar campo vacio.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Campo requerido
     */
    public String verificarUsuarioContrasenaRequeridos(String usuario, String contrasena, boolean crearReporte){
        pageLogin.setFormLogin(usuario, contrasena, crearReporte);
        pageLogin.campoUsuarioRequerido(false);
        return pageLogin.campoContrasenaRequerido(crearReporte);
    }

    /** Verifica que el campo usuario es obligatorio.
     * @param usuario Dejar campo vacio.
     * @param contrasena Contraseña del usuario - Campo obligatorio.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Campo requerido
     */
    public String verificarUsuarioRequerido(String usuario, String contrasena, boolean crearReporte){
        pageLogin.setFormLogin(usuario, contrasena, crearReporte);
        return pageLogin.campoUsuarioRequerido(crearReporte);
    }

    /** Verifica que el campo Contraseña es obligatorio.
     * @param usuario Numero de documento o pasaporte - Campo obligatorio
     * @param contrasena Dejar campo vacio.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Campo requerido
     */
    public String verificarContrasenaRequerida(String usuario, String contrasena, boolean crearReporte){
        pageLogin.setFormLogin(usuario, contrasena, crearReporte);
        return pageLogin.campoContrasenaRequerido(crearReporte);
    }

    /** Navega a la pagina restaurar clave de acceso y retorna una instancia de la pagina.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return RestaurarClaveAccesoLogica
     */
    public RestaurarClaveAccesoLogica navegarAPaginaOlvideMiContrasena(boolean crearReporte){
        pageLogin.clickLinkOlvideMiContrasena(crearReporte);
        return RestaurarClaveAccesoLogica.getLogica();
    }

    /** <h3> Este método sirve para verificar que se navego a la pantalla de login IBP.</h3>
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Internet Banking Personal
     */
    public String verificarPantallaLogin(boolean crearReporte){
        return pageLogin.pantallaLogin(crearReporte);
    }
}
