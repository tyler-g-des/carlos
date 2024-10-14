package pages.configuracion;

//import pages.accionesrepetitivas.AccionRepetitivaPage;
import pages.login.LoginLogic;

public class ConfiguracionLogic {


    private ConfigurarResumenProductosPage configResumenProductoPage;
    private ContractosTerminosCondicionesPage contratoTerminosCondicionesPage;
    private InformacionAccesoSeguridadPage informacionAccesoSeguridadPage;
    //AccionRepetitivaPage accionesRepetitivasPage;

    private ConfiguracionLogic(){
        configResumenProductoPage = ConfigurarResumenProductosPage.getPage();
        informacionAccesoSeguridadPage = InformacionAccesoSeguridadPage.getPage();
        contratoTerminosCondicionesPage = ContractosTerminosCondicionesPage.getNewPage();
        //contratoTerminosCondicionesPage = ContractosTerminosCondicionesPage.
        //accionesRepetitivasPage = AccionRepetitivaPage.getInstance();
        //terminosCondicionesPage = ConfigurarResumenProductosPage.getPage();
    }

    public static ConfiguracionLogic getLogica(){
        return new ConfiguracionLogic();
    }


    // Sesion Informacion de acceso y seguridad.

    private void cambiarContrasena(String claveActual, String claveNueva, String confirmarClave, String codigoTDC, boolean cerrarSesion, boolean crearReporte){
        informacionAccesoSeguridadPage.pantallaCambiarClaveAcceso( claveActual, claveNueva, confirmarClave, crearReporte);
        informacionAccesoSeguridadPage.guardarContrasena(codigoTDC,crearReporte);
        informacionAccesoSeguridadPage.modalClaveRestablecida( cerrarSesion, crearReporte);
    }

    public String cambiarContrasenaNoCerrarSesion(String claveActual, String claveNueva, String confirmarClave, String codigoTDC, boolean crearReporte){
        cambiarContrasena(claveActual, claveNueva, confirmarClave, codigoTDC, false, crearReporte);
        return informacionAccesoSeguridadPage.navegarAInformacionDeAccesoSeguridad(crearReporte);
    }

    public LoginLogic cambiarContrasenaCerrarSesion(String claveActual, String claveNueva, String confirmarClave, String codigoTDC, boolean crearReporte){
        cambiarContrasena(claveActual, claveNueva, confirmarClave, codigoTDC, true, crearReporte);
        return LoginLogic.get();
    }

    public String verificarContrasenaQueNoCoinciden(String claveActual, String claveNueva, String confirmarClave, boolean crearReporte){
        informacionAccesoSeguridadPage.pantallaCambiarClaveAcceso( claveActual, claveNueva, confirmarClave, crearReporte);
        return informacionAccesoSeguridadPage.msgAlertaContrasenasNoCoinciden(crearReporte);
    }

    public String verificarContrasenaDebeSerNumerica(String claveActual, String claveNueva, String confirmarClave, boolean crearReporte){
        informacionAccesoSeguridadPage.pantallaCambiarClaveAcceso( claveActual, claveNueva, confirmarClave, crearReporte);
        return informacionAccesoSeguridadPage.msgNuevaContrasenaCampoNumerico();
    }

    /** Verifica que se muestre el mensaje cuando la contrasena ingresada es diferente a la contrasena actual
     * @param claveActual
     * @param claveNueva
     * @param confirmarClave
     * @param codigoTDC
     * @param crearReporte
     * @return La clave de acceso actual es incorrecta
     */
    public String claveActualIncorrecta(String claveActual, String claveNueva, String confirmarClave, String codigoTDC, boolean crearReporte){
        informacionAccesoSeguridadPage.pantallaCambiarClaveAcceso( claveActual, claveNueva, confirmarClave, crearReporte);
        informacionAccesoSeguridadPage.guardarContrasena(codigoTDC,crearReporte);
        return informacionAccesoSeguridadPage.modalClaveActualIncorrecta();
    }


    public void cambiarPreguntaSeguridad(String pregt1, String respt1, String pregt2, String respt2, String pregt3, String respt3, String pregt4, String respt4, String pregt5, String respt5,boolean crearReporte){
        informacionAccesoSeguridadPage.expandirPantallaPreguntaSeguridad(crearReporte);
        informacionAccesoSeguridadPage.setPreguntaSeguridadUno(pregt1,"NA",respt1, crearReporte);
        informacionAccesoSeguridadPage.setPreguntaSeguridadDos(pregt2,"NA",respt2, crearReporte);
        informacionAccesoSeguridadPage.setPreguntaSeguridadTres(pregt3,"NA",respt3, crearReporte);
        informacionAccesoSeguridadPage.setPreguntaSeguridadCuatro(pregt4,"NA",respt4, crearReporte);
        informacionAccesoSeguridadPage.setPreguntaSeguridadCinco(pregt5,"NA",respt5, crearReporte);
        informacionAccesoSeguridadPage.guardarPreguntaSeguridad("1111",crearReporte);
    }

    public void cambiarPreguntaSeguridadPreguntasPropias(String preguntaPropia1, String respuesta1, String  preguntaPropia2, String respuesta2, String  preguntaPropia3, String respt3, String  preguntaPropia4, String respt4, String  preguntaPropia5, String respt5, boolean crearReporte){
        informacionAccesoSeguridadPage.expandirPantallaPreguntaSeguridad( crearReporte );
        informacionAccesoSeguridadPage.setPreguntaPropiaUno( preguntaPropia1, respuesta1, crearReporte );
        informacionAccesoSeguridadPage.setPreguntaPropiaDos(  preguntaPropia2, respuesta2, crearReporte );
        informacionAccesoSeguridadPage.setPreguntaPropiaTres(  preguntaPropia3, respt3, crearReporte );
        informacionAccesoSeguridadPage.setPreguntaPropiaCuatro(  preguntaPropia4, respt4, crearReporte );
        informacionAccesoSeguridadPage.setPreguntaPropiaCinco(  preguntaPropia5, respt5, crearReporte );
        informacionAccesoSeguridadPage.guardarPreguntaSeguridad("1111", crearReporte );
    }


    public void deshablilitarClaveTransaccionar(String codigoTDC, boolean crearReporte){
        informacionAccesoSeguridadPage.deshabilitarClaveTransaccionar( codigoTDC, crearReporte);
    }


    // Contratos Terminos Condiciones

    public boolean activarContratoACHPagoInstanteBCRD(boolean crearReporte){
        contratoTerminosCondicionesPage.clickBotonActivarContratoACHPagoAlInstante(crearReporte);
        return !contratoTerminosCondicionesPage.activarContratoACHPagoAlInstante(crearReporte);
    }

    public boolean activarContratoInternacionalSIPA(boolean crearReporte){
        contratoTerminosCondicionesPage.clickBotonActivarContratoInternacionalSIPA(crearReporte);
        return !contratoTerminosCondicionesPage.activarContratoInternacionalSIPA(crearReporte);
    }

    public void actualizarContratoUsoDeInformacionOpcionNoAceptar(boolean crearReporte){
        contratoTerminosCondicionesPage.clickBotonActualizarContratoAutorizacionUsoDeInformacion(crearReporte);
        contratoTerminosCondicionesPage.modalContratoUsoInformacion(false,true,crearReporte);
    }

    public void actualizarContratoUsoDeInformacionOpcionAceptar(boolean crearReporte){
        contratoTerminosCondicionesPage.clickBotonActualizarContratoAutorizacionUsoDeInformacion(crearReporte);
        contratoTerminosCondicionesPage.modalContratoUsoInformacion(true,false,crearReporte);
    }
}
