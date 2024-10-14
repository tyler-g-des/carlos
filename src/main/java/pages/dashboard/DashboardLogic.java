package pages.dashboard;

import data.Persistencia;
import microservicios.dashboard.MsCuentas;
import microservicios.dashboard.MsDashboardCuentas;
import pages.configuracion.ConfiguracionLogic;
import pages.perfilusuario.PerfilUsuarioLogic;
import pages.pyt.PagosTransferenciasLogic;
import pages.pyt.transferencias.entrebhdotrosbancos.LogicaTransaccionesBHDOtrosBancos;
import pages.pyt.transferencias.entremisproductos.LogicaTransaccionesMisProductos;
import pages.pyt.transferencias.internacional.LogicaTransferenciaInternacional;
import pages.pyt.transferencias.pinpesos.PinPesosLogic;
import pages.pyt.transferencias.regionalesipa.LogicaTransferenciaRegionalSIPA;

import static microservicios.Utilidad.darFormatoMonedaFinal;
import static microservicios.Utilidad.formatearPrimeraLetraMayusc;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 14/11/2023 2:48 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class DashboardLogic {
    private final DashboardPage page;

    /** <h3> Constructor por defecto para instancia la pagina de Dashboard. </h3> */
    private DashboardLogic(){
        page = DashboardPage.getPage();
    }

    /** Retorna una nueva instancia de la clase DashboardLogic
     * @return DashboardLogic
     */
    public static DashboardLogic getLogica(){
        return new DashboardLogic();
    }

    /** <h3> Retorna el titulo del h3 de la pantalla de 360 - Resumen de Productos. </h3>
     * @return 360 - Resumen de Productos
     */
    public String getTitulo360(){
        return page.pantallaDashboard(true);
    }

    /** <h3>Verifica que este cargando el nombre y correo del usuario, retorna true, de lo contrario retorna false.</h3>
     * @param nombre Nombre del usuario.
     * @param correo Correo del usuario.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return boolean.
     */
    public boolean verifiCarNombreCorreo( String nombre, String correo,boolean crearReporte){
        page.pantallaDashboard(crearReporte);
        return page.verificarSeMuestreNombreCorreo( nombre, correo, crearReporte );
    }

    /** <h3> Realizar clic sobre pagos y transferencia, navegando a la pagina. </h3>
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Permite crear o no reporte de este proceso.
     */
    public PagosTransferenciasLogic irPaginaPagosTransferencias(boolean crearReporte){
        page.pantallaDashboard(crearReporte);
        return page.clicMenuPagosTransferencias(crearReporte);
    }

    public LogicaTransaccionesMisProductos irTransaccionesEntreMisProductos(boolean crearReporte){
        page.pantallaDashboard(crearReporte);
        return page.clicMenuTransaccionesEntreMisProductos(crearReporte);
    }

    /** <h3>Navega a la pagina Transacciones entre productos BHD y a otros bancos desde el menú de pagos y transferencias y retornando una instancia de la clase </h3>
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public LogicaTransaccionesBHDOtrosBancos irPaginaTransaccionesEntreBHDOtrosBancos(boolean crearReporte){
        page.pantallaDashboard(crearReporte);
        return page.clicMenuTransaccionesBHDOtrosBancos(crearReporte);
    }

    public LogicaTransferenciaInternacional transferenciaInternacional(boolean crearReporte){
    page.pantallaDashboard(crearReporte);
    return page.clicMenuInternacionales(crearReporte);
    }

    public LogicaTransferenciaRegionalSIPA transferenciasRegionalesSIPA(boolean crearReporte){
        page.pantallaDashboard(crearReporte);
        return page.clicMenuTransferenciaRegionalesSIPA(crearReporte);
    }

    public PinPesosLogic pinPesos(boolean crearReporte){
        page.pantallaDashboard(crearReporte);
        return page.clicMenuPINPesos(crearReporte);
    }

    /** <h3>Permite editar el alias de las cuentas de Ahorro y corriente.</h3>
     * @param usuario Numero de documento del usuario.
     * @param numeroProducto Numero de la cuenta a editar.
     * @param nuevaAlias Nueva alias que se va asignar a la cuenta.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Alias actual en Dashboard luego de editar.
     */
    public String editarAliasCuenta(String usuario,String numeroProducto, String nuevaAlias, boolean crearReporte){
        page.pantallaDashboard(usuario,crearReporte);
        return page.guardarAlias(page.pantallaEditarAliasCuenta(numeroProducto,nuevaAlias, crearReporte),numeroProducto, nuevaAlias,crearReporte) ;
    }

    /** <h3>Permite editar el alias de las tarjetas de crédito.</h3>
     * @param usuario Numero de documento del usuario.
     * @param numeroProducto Numero de la tarjeta a editar.
     * @param nuevaAlias Nueva alias que se va asignar a la tarjeta de crédito.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Alias actual en Dashboard luego de editar.
     */
    public String editarAliasTarjetaCredito(String usuario,String numeroProducto, String nuevaAlias, boolean crearReporte){
        page.pantallaDashboard(usuario,crearReporte);
        return page.guardarAlias(page.pantallaEditarAliasTarjetaCredito( numeroProducto, nuevaAlias, crearReporte), numeroProducto, nuevaAlias, crearReporte);
    }

    /** <h3>Permite editar el alias del certificado.</h3>
     * @param usuario Numero de documento del usuario.
     * @param numeroProducto Numero del certificado
     * @param nuevaAlias Nueva alias que se va asignar al certificado.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Alias actual en Dashboard luego de editar.
     */
    public String editarAliasCertificado(String usuario,String numeroProducto, String nuevaAlias, boolean crearReporte){
        page.pantallaDashboard(usuario,crearReporte);
        return page.guardarAlias( page.pantallaEditarAliasCertificado( numeroProducto,nuevaAlias,crearReporte), numeroProducto,nuevaAlias,crearReporte );
    }

    /** <h3>Permite editar el alias del préstamo.</h3>
     * @param usuario Numero de documento del usuario.
     * @param numeroProducto Numero del prestamo
     * @param nuevaAlias Nueva alias que se va asignar al prestamo.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Alias actual en Dashboard luego de editar.
     */
    public String editarAliasPrestamo(String usuario,String numeroProducto, String nuevaAlias, boolean crearReporte){
        page.pantallaDashboard( usuario,crearReporte );
        return page.guardarAlias( page.pantallaEditarAliasPrestamos( numeroProducto, nuevaAlias, crearReporte), numeroProducto, nuevaAlias, crearReporte);
    }


    public PerfilUsuarioLogic navegarAPerfilDeUsuarioPage(boolean crearReporte){
        page.pantallaDashboard(crearReporte);
        page.clickBotonPerfilDeUsuario(crearReporte);
        return PerfilUsuarioLogic.getLogica();
    }

    public ConfiguracionLogic configuracion(boolean crearReporte){
        page.pantallaDashboard(crearReporte);
        return page.clickBotonIconoConfiguracion(crearReporte);
    }


    public void consultarCuenta(MsDashboardCuentas cuenta, boolean crearReporte){
        String nombreProducto ="";
        page.pantallaDashboard(crearReporte);
        page.desplegarSesionAll("CUENTAS", crearReporte);
        if (!cuenta.getAlias().equals("")){
            nombreProducto = cuenta.getAlias();
        }else {
            nombreProducto = cuenta.getNombreProducto();
        }
        page.consultarCuenta(
                cuenta.getNumProducto(),
                nombreProducto,
                cuenta.getTipoProductoResumido(),
                formatearPrimeraLetraMayusc(cuenta.getEstadoProducto()),
                cuenta.getMoneda(),
                  darFormatoMonedaFinal(cuenta.getBalance()),
                 darFormatoMonedaFinal(cuenta.getBalanceEnTransito()),
                crearReporte);
    }

    public DashboardPage consultarCuenta(String numeroCuenta, boolean crearReporte){
        MsCuentas cuentas = new MsCuentas(Persistencia.getInstance().getDocumentNumber(page.getNombreCliente())).buscar(numeroCuenta);
        page.pantallaDashboard(crearReporte);
        page.desplegarSesionAll("CUENTAS", crearReporte);
        page.consultarCuenta(
                cuentas.getNumProducto(),
                cuentas.getTituloProducto(),
                cuentas.getTiposProducto(),
                cuentas.getEstadoProducto(),
                cuentas.getMoneda(),
                cuentas.getBalance(),
                cuentas.getBalanceEnTransito(),
                crearReporte);
        return page;
    }
    public DashboardPage consultarDetalleCuenta(MsDashboardCuentas cuenta, boolean crearReporte){
        String nombreProducto ="";
        page.pantallaDashboard(crearReporte);
        page.desplegarSesionAll("CUENTAS", crearReporte);
        if (!cuenta.getAlias().equals("")){
            nombreProducto = cuenta.getAlias();
        }else {
            nombreProducto = cuenta.getNombreProducto();
        }
        page.consultarCuenta(
                cuenta.getNumProducto(),
                nombreProducto,
                cuenta.getTipoProductoResumido(),
                formatearPrimeraLetraMayusc(cuenta.getEstadoProducto()),
                cuenta.getMoneda(),
                darFormatoMonedaFinal(cuenta.getBalance()),
                darFormatoMonedaFinal(cuenta.getBalanceEnTransito()),
                crearReporte);
        page.detalleProducto("CUENTAS",cuenta.getNumProducto(),crearReporte);
        return page;
    }

    public void consultarCuentas(MsDashboardCuentas cuentas, boolean crearReporte){
        page.pantallaDashboard(crearReporte);
        page.desplegarSesionAll("CUENTAS", crearReporte);
        MsDashboardCuentas producto;

        for (int i = 0; i < cuentas.getToltaProductos(); i++) {
            String nombreProducto ="";

            producto = cuentas.iterarProductos(i);
            System.out.println(cuentas.iterarProductos(i).getNumProducto());
            if (!producto.getAlias().equals("")){
                nombreProducto = producto.getAlias();
            }else {
                nombreProducto = producto.getNombreProducto();
            }

            page.consultarCuenta(
                    producto.getNumProducto(),
                    nombreProducto,
                    producto.getTipoProductoResumido(),
                    formatearPrimeraLetraMayusc(producto.getEstadoProducto()),
                    producto.getMoneda(),
                    darFormatoMonedaFinal(producto.getBalance()),
                    darFormatoMonedaFinal(producto.getBalanceEnTransito()),
                    crearReporte);
        }
    }

    public void delleDeCuenta(String producto, boolean crearReporte){
        page.detalleProducto("CUENTAS",producto,crearReporte);
    }

    public void delleDeCertificado(String producto, boolean crearReporte){
        page.detalleProducto("CERTIFICADOS",producto,crearReporte);
    }

    public void modalPagarTC(String productOrigen, String productDestino, boolean crearReporte){
        page.modalPagar("TARJETAS DE CRÉDITO",productOrigen,productDestino,crearReporte);
    }

    public void modalPagarPrestamo(String productOrigen, String productDestino, boolean crearReporte){
        page.modalPagar("PRÉSTAMOS",productOrigen,productDestino,crearReporte);
        page.botonCancelar(crearReporte);
    }

    public void modalActivarTarjetaCredito(String numTarjeta,boolean crearReporte){
        //page.botonActivarTarjeta("TARJETAS DE CRÉDITO", numTarjeta, crearReporte);
        page.modalActivarTarjetaCredito(numTarjeta,true,crearReporte);
    }

    public void consultarDetallePuestoBolsa(boolean crearReporte){
        page.consultarDetallePuestoBolsa(crearReporte);
    }



}
