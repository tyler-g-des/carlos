package pages.pagostransferencias.accesos.beneficiarios;

import accion.AccionMetodos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POBeneficiariosInscritosPage extends AccionMetodos {

    private POBeneficiarioPinPesosPage pagePinPeso = null;
    private POBeneficiariosNacionalesPage pageNacionales = null;
    private POBeneficiarioInternacionalesPage pageBeneficInternacionales = null;
    public POBeneficiariosInscritosPage(WebDriver webDriver) {
        super(webDriver);
        pagePinPeso = new POBeneficiarioPinPesosPage(driver);
        pageNacionales = new POBeneficiariosNacionalesPage(driver);
        pageBeneficInternacionales = new POBeneficiarioInternacionalesPage(driver);
    }

    private static final By btnAgregarBeneficiario = by("//div[@aria-hidden='false'] // span[contains(.,'Agregar beneficiario')]");


    /**
     *
     * @param nombreBanco BANCO MULTIPLE BHD, S.A. |O| BHDIB Panamá
     * @param numeroProducto
     * @param alias
     * @param correo
     * @param crearReporte
     */
    public void agregarBeneficiarioBHD( String numeroProducto,String alias, String correo, boolean crearReporte){
        pageNacionales.probarFormularioNuevoBeneficiario("BANCO MULTIPLE BHD, S.A.",
                        numeroProducto,
                        alias,
                        correo,
                        crearReporte)
                .probarGuardarDesplegarModalTDC("1111", crearReporte)
                .modarGuardadoCorrectamente(true).
                buscarMarcarProducto( numeroProducto, crearReporte );
    }

    public void agregarBeneficiarioPanama(String numeroTarjetaCredito, String alias, String correo, boolean crearReporte){
        pageNacionales.probarFormularioNuevoBeneficiario("BHDIB Panamá", numeroTarjetaCredito, alias, correo, crearReporte )
                .probarGuardarDesplegarModalTDC("1111", crearReporte)
                .modarGuardadoCorrectamente(true).
                buscarMarcarProducto( numeroTarjetaCredito, crearReporte );
    }

    public void editarBeneficiarioBHD(String numProductoBeneficiarioEditar, String alias, String correo, boolean crearReporte ){


        pageNacionales.probarFormularioEditarBeneficiario( numProductoBeneficiarioEditar, numProductoBeneficiarioEditar, alias, correo, crearReporte )
                .probarGuardarDesplegarModalTDC( "1111", crearReporte )
                .modarGuardadoCorrectamente(crearReporte)
                .buscarMarcarProducto( numProductoBeneficiarioEditar, crearReporte);
    }



    // DE OTROS BANCOS 👇
    public void agregarBeneficiarioDeOtrosBancos(String nombreBanco, String tipoProducto, String numProducto, String tipoDocumento, String numDocumento, String nombreBeneficiario, String alias, String pais, String genero, String correo, boolean crearReporte){
        pageNacionales.probarForFormularioNuevoBeneficiarioOtrosBancos(
                nombreBanco,
                tipoProducto,
                numProducto,
                tipoDocumento,
                numDocumento,
                nombreBeneficiario,
                alias,
                pais,
                genero,
                correo,
                crearReporte ).probarGuardarDesplegarModalTDC("1111", crearReporte )
                .modarGuardadoCorrectamente( crearReporte )
                .buscarMarcarProducto( numProducto, crearReporte );
    }

    public void editarBeneficiarioDeOtrosBancos(String numeroProductoAEditar, String alias, String pais, String genero, String correo, boolean crearReporte){
        pageNacionales.probarFormularioEditarBeneficiarioOtrosBancos( numeroProductoAEditar, alias, pais, genero, correo, crearReporte )
                .probarGuardarDesplegarModalTDC("1111", crearReporte )
                .modarGuardadoCorrectamente( crearReporte )
                .buscarMarcarProducto( numeroProductoAEditar, crearReporte );
    }

    public String eliminarBeneficiarioNacional(String numeroCuentaBeneficiarioAEliminar, boolean crearReporte){
        pageNacionales.modalEliminarBeneficiario( numeroCuentaBeneficiarioAEliminar, crearReporte );
        return pagePinPeso.confirmarEliminacionBeneficiario(true);
    }



    // Sesion Pin Pesos 👇
    public String eliminarBeneficiarioPinPesos(String numeroBeneficiarioAEliminar, boolean crearReporte ){
        pagePinPeso.probarModalEliminarBeneficiario( numeroBeneficiarioAEliminar, crearReporte );
        return pagePinPeso.confirmarEliminacionBeneficiario(crearReporte);
    }

    public void agregarBeneficiarioPinPesos(String alias, String telefono, String tipoDocumento, String numDocumento, String nombre, String pais, String genero, boolean crearReporte){
        pagePinPeso.probarFormularioNuevoBeneficiario( alias, telefono, tipoDocumento, numDocumento, nombre, pais, genero, crearReporte );
        pageNacionales.probarGuardarDesplegarModalTDC("1111", crearReporte)
                .modarGuardadoCorrectamente(crearReporte);
        pagePinPeso.consultarBeneficiario(telefono, crearReporte);
    }

    public void actualizarBeneficiario(String numTelefonoBeneficiarioAEditar, String alias, boolean crearReporte){
        pagePinPeso.probarFormularioEditarBeneficiario( numTelefonoBeneficiarioAEditar, alias, crearReporte);
        pageNacionales.probarGuardarDesplegarModalTDC("1111", crearReporte )
                .modarGuardadoCorrectamente(true);
        pagePinPeso.consultarBeneficiario(numTelefonoBeneficiarioAEditar, crearReporte);
    }

    public void agregarBeneficiarioInternacional(String nombreBeneficiario, String numIdentificacion, String direccion, String calle, String correo, String numProductIBAN, String moneda, String tipoCodigo, String codigo, String destino, String clasificacion, String proposito, boolean crearReporte){
        //.setFormularioNuevoBeneficiario( nombreBeneficiario, numIdentificacion,direccion, calle, correo, numProductIBAN, moneda, tipoCodigo, codigo, crearReporte );
        pageBeneficInternacionales.setFormularioNuevoBeneficiario(
                nombreBeneficiario,
                numIdentificacion,
                direccion,
                calle,
                correo,
                numProductIBAN,
                moneda,
                tipoCodigo,
                codigo,
                true,
                false,
                "NA",
                "NA",
                "NA",
                destino,
                clasificacion,
                proposito,
                crearReporte
                );

        pageNacionales.probarGuardarDesplegarModalTDC("1111", crearReporte).modarGuardadoCorrectamente(crearReporte);
        pageBeneficInternacionales.consultarBeneficiario( numProductIBAN, crearReporte );
    }

    public void agregarBeneficiarioIntermediarioConBancoIntermediario( String nombreBeneficiario, String numIdentificacion, String direccion, String calle,
                                                                       String correo, String numProductIBAN, String moneda, String tipoCodigo, String codigo,
                                                                       String numProductIbanIntermediario, String tipoCodigoIntermediario, String codigoIntermediario,
                                                                       String destino, String clasificacion, String proposito, boolean crearReporte){

        pageBeneficInternacionales.setFormularioNuevoBeneficiario( nombreBeneficiario, numIdentificacion, direccion, calle,
                correo, numProductIBAN, moneda, tipoCodigo, codigo, true, true, numProductIbanIntermediario,
                tipoCodigoIntermediario, codigoIntermediario, destino, clasificacion, proposito, crearReporte
        );

        pageNacionales.probarGuardarDesplegarModalTDC("1111", crearReporte)
                .modarGuardadoCorrectamente(crearReporte);
        pageBeneficInternacionales.consultarBeneficiario( numProductIBAN, crearReporte );
    }

    public void formularioEditarBeneficiarioInternacional(String numProductIbanBeneficiarioAEditar, String numIdentificacion, String direccion
            ,String calle, String correo, String moneda, String destino, String clasificacion, String proposito, boolean crearReporte){
        pageBeneficInternacionales.formularioEditarBeneficiario(
                numProductIbanBeneficiarioAEditar,
                numIdentificacion,
                direccion,
                calle,
                correo,
                moneda,
                destino,
                clasificacion,
                proposito,
                crearReporte

                );
        pageNacionales.probarGuardarDesplegarModalTDC("1111", crearReporte)
                .modarGuardadoCorrectamente(crearReporte);
        pageBeneficInternacionales.consultarBeneficiario( numProductIbanBeneficiarioAEditar, crearReporte );

    }

    public String eliminarBeneficiarioInternacional(String numProductIbanBeneficiarioAEditar, boolean crearReporte){
        pageBeneficInternacionales.modalEliminarBeneficiario(numProductIbanBeneficiarioAEditar, crearReporte);
        return pagePinPeso.confirmarEliminacionBeneficiario(crearReporte);
    }
}
