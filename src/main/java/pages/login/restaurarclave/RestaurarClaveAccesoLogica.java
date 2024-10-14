package pages.login.restaurarclave;

public class RestaurarClaveAccesoLogica {
    private PoRestaurarClaveAcceso page;
    private RestaurarClaveAccesoLogica(){
        page = PoRestaurarClaveAcceso.getPagina();
    }

    /** Retorna una una instancia de la clase RestaurarClaveAccesoLogica
     * @return RestaurarClaveAccesoLogica
     */
    public static RestaurarClaveAccesoLogica getLogica(){
        return new RestaurarClaveAccesoLogica();
    }

    /** Retorna el conteo regresivo 04:59 al envíar el código OTP.
     * @param numeroDocumento Numero de cedula valida.
     * @param nuevaClave Nueva clave.
     * @param confirmarClave Repetir la nueva clave.
     * @param codigoOTP solo se envia para llenar el campo.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return 04:59
     */
    public String validarPantallaEnvioOTP(String numeroDocumento, String nuevaClave, String confirmarClave, String codigoOTP, String tiempo, boolean crearReporte ){
        page.pantallaValidarUsuario( numeroDocumento, crearReporte );
        page.botonValidarDocumento( crearReporte );
        page.pantallaConfiguracionDeAcceso( nuevaClave, confirmarClave, crearReporte);
        page.botonContinuar(crearReporte);
        page.pantallaValidarOTP( codigoOTP, crearReporte);
        return page.obtenerTextoDeConteoRegresivoOTP(tiempo, crearReporte);
    }

    public String getMensajeErrorValidacionNumeroDocumento(String numeroDocumento, boolean crearReporte){
        page.pantallaValidarUsuario( numeroDocumento, crearReporte );
        return page.getMsgErrorGenericoNumeroDocumento(crearReporte);
    }
    public String verificarContrasenaDiferentes(String numeroDocumento,String nuevaClave, String confirmarClave, boolean crearReporte){
        page.pantallaValidarUsuario( numeroDocumento, crearReporte );
        page.botonValidarDocumento( crearReporte );
        page.pantallaConfiguracionDeAcceso( nuevaClave, confirmarClave, crearReporte);
        return page.getMsgErrorContrasenaNoCoinciden( crearReporte );
    }

    public String verificarCampoNuevaContrasenaOConfirmarContrasenaDebenSerNumerico(String numeroDocumento,String nuevaClave, String confirmarClave, boolean crearReporte){
        page.pantallaValidarUsuario( numeroDocumento, crearReporte );
        page.botonValidarDocumento( crearReporte );
        page.pantallaConfiguracionDeAcceso( nuevaClave, confirmarClave, crearReporte);
        return page.getMsgErrorCampoDebeSerNumerico( crearReporte );
    }

    public String verificarMensajeErrorMinimoDeCaracteres(String numeroDocumento,String nuevaClave, String confirmarClave, boolean crearReporte){
        page.pantallaValidarUsuario( numeroDocumento, crearReporte );
        page.botonValidarDocumento( crearReporte );
        page.pantallaConfiguracionDeAcceso( nuevaClave, confirmarClave, crearReporte);
        return page.getMsgErrorMinimoCaracteres( crearReporte );
    }

    public String verificarMensajeErrorCampoRequerido(String numeroDocumento,String nuevaClave, String confirmarClave, boolean crearReporte){
        page.pantallaValidarUsuario( numeroDocumento, crearReporte );
        page.botonValidarDocumento( crearReporte );
        page.pantallaConfiguracionDeAcceso( nuevaClave, confirmarClave, crearReporte);
        return page.getMsgErrorCampoRequerido( crearReporte );
    }







}