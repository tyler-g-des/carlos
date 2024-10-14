package pages.perfilusuario;

import pages.dashboard.DashboardLogic;

/**
 * Carlos Loyola
 *
 * @author: Carlos A. Loyola Tejeda
 * @Date 05/12/2023 11:59 p. m.
 * @Email carlos_loyola@bhd.com.do
 * BHDL_AutomatizacionMigracionIBP
 */
public class PerfilUsuarioLogic {

    private PerfilUsuarioPage page;
    private PerfilUsuarioLogic(){
        page = PerfilUsuarioPage.getPage();
    }
    public static PerfilUsuarioLogic getLogica(){
        return new PerfilUsuarioLogic();
    }

    public void actualizarDatosDeContacto(String correo, String companiaTelefono, String telefono, boolean crearReporte){
        page.capturarTituloDatosContacto(crearReporte);
        page.setDatosContacto( correo, companiaTelefono, telefono, crearReporte );
    }

    public String actualizarOtrosDatos(String calle, String numCalle, String provincia, String municipio,String sector, String localidad, String edificio, String numApartamento, String referencia,boolean crearReporte){
        page.capturarTituloOtrosDatos(crearReporte);
        page.setOtrosDatos(calle,numCalle,provincia,municipio,sector, crearReporte);
        page.setOtrosDatos(localidad,edificio,numApartamento,referencia,crearReporte);
        page.guardarDatos(crearReporte);
        return DashboardLogic.getLogica().getTitulo360();
    }

    public void actualizarDatos(String correo, String companiaTelefono, String telefono, String calle, String numCalle, String provincia, String municipio,String sector, String localidad, String edificio, String numApartamento, String referencia,boolean crearReporte){
        actualizarDatosDeContacto(correo, companiaTelefono, telefono, crearReporte);
        page.capturarTituloOtrosDatos( crearReporte);
        page.setOtrosDatos(calle,numCalle,provincia,municipio,sector, crearReporte);
        page.setOtrosDatos( localidad, edificio, numApartamento, referencia, crearReporte);

    }
}
