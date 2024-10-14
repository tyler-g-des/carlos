package pyt.beneficiariosinscritos;

import basetest.BaseTest;
import data.GenerarData;
import microservicios.Utilidad;
import microservicios.beneficiarios.nacionales.MsBeneficiariosNacionales;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Sesiones;
import pages.login.LoginLogic;

//import static pages.Sesiones.sesiones;


/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 21/02/2024 2:42 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TestBeneficiariosNacionales extends BaseTest {

    String usuario = "03102674383";
    private final String usuarioCarlosLeon = "03102674383";
    private String prestamoPesosT24 = "5513634";


    @Test(  priority = 1, description = "Agregar beneficiario BHD -> Prestamo Pesos") // Done
    void agregarBeneficiarioBHDPrestamoPesos(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro( usuarioCarlosLeon,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalBHDPanama( usuarioCarlosLeon,"BANCO MULTIPLE BHD, S.A.","3144169","NA","carlos_loyola@bhd.com.do",true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 2, description = "Actualizar beneficiario BHD -> Prestamo Pesos", dependsOnMethods = "agregarBeneficiarioBHDPrestamoPesos") // Done
    void actualizarBeneficiarioBHDPrestamoPesos(){
    //    String usuario = "03102674383";
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro( usuarioCarlosLeon,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false) // 5588464 Prestamo T24
                        .actualizarBeneficiarioNacionalBHDPanama( usuarioCarlosLeon,"3144169","La nueva Alias 4","carlos_loyola@bhd.com.do",true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 3, description = "Eliminar Beneficiario BHD -> Prestamo Pesos", dependsOnMethods = "agregarBeneficiarioBHDPrestamoPesos")
    void eliminarBeneficiarioBHDPrestamoPesos(){
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro( usuarioCarlosLeon,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional( usuarioCarlosLeon,"3144169",true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }



    @Test(priority = 4, description = "Agregar beneficiario BHD -> Prestamo Pesos T24") // Done
    void agregarBeneficiarioBHDPrestamoPesosT24(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuarioCarlosLeon,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalBHDPanama(usuarioCarlosLeon,"BANCO MULTIPLE BHD, S.A.",prestamoPesosT24,"NA","carlos_loyola@bhd.com.do",true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 5, description = "Actualizar beneficiario - Prestamo T24", dependsOnMethods = "agregarBeneficiarioBHDPrestamoPesosT24") // Done
    void actualizarBeneficiarioPrestamoT24(){

        String actualResult =
                LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuarioCarlosLeon,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false) // 5588464 Prestamo T24
                .actualizarBeneficiarioNacionalBHDPanama(usuarioCarlosLeon,prestamoPesosT24,"La nueva Alias 4","carlos_loyola@bhd.com.do",true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 6, description = "Eliminar Beneficiario Prestmo T24", dependsOnMethods = "agregarBeneficiarioBHDPrestamoPesosT24")
    void eliminarBeneficiarioPrestamoT24(){
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuarioCarlosLeon,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional(usuarioCarlosLeon,prestamoPesosT24,true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }



    @Test(priority = 7, description = "Agregar beneficiario BHD - CTA. Corriente")  // Done
    void agregarBeneficiarioCuentaCorrienteBHD(){
        String usuario = "03102674383";
        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalBHDPanama(usuario,"BANCO MULTIPLE BHD, S.A.","01523330027","NA","carlos_loyola@bhd.com.do",true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 8, description = "Actualizar beneficiario BHD - CTA. Corriente", dependsOnMethods = "agregarBeneficiarioCuentaCorrienteBHD")  // Done
    void actualizarBeneficiarioCuentaCorrienteBHD(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioNacionalBHDPanama(usuario,"01523330027","Carlitos","carlos_loyola@bhd.com.do",true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 9,description = "Eliminar Beneficiario BHD - CTA. Corriente", dependsOnMethods = "agregarBeneficiarioCuentaCorrienteBHD")
    void eliminarBeneficiarioCuentaCorrienteBHD(){
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional(usuario,"01523330027",true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }



    @Test(priority = 10,description = "Agregar beneficiario BHD - CTA. Ahorros Pesos") // Done
    void agregarBeneficiarioBHDCuentaAhorrosPesos(){
        String usuario = "03102674383";
        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalBHDPanama(
                        usuario,
                        "BANCO MULTIPLE BHD, S.A.",
                        "01289910014",
                        "NA",
                        "carlos_loyola@bhd.com.do",
                        true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 11, description = "Actualizar beneficiario BHD - CTA. Ahorros Pesos", dependsOnMethods = "agregarBeneficiarioBHDCuentaAhorrosPesos")  // Done
    void actualizarBeneficiarioBHDCuentaAhorrosPesos(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("03102674383","1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioNacionalBHDPanama(
                        usuario,
                        "01289910014",
                        "Carlitos 2",
                        "carlos_loyola@bhd.com.do",
                        true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 12,description = "Eliminar Beneficiario BHD - CTA. Ahorros Pesos", dependsOnMethods = "agregarBeneficiarioBHDCuentaAhorrosPesos")
    void eliminarBeneficiarioBHDCuentaAhorrosPesos(){
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional(
                                usuario,
                                "01289910014",
                                true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }



    @Test(priority = 13,description = "Agregar beneficiario BHD - CTA. Ahorros en Dolar") // Done
    void agregarBeneficiarioBHDCuentaAhorrosDolar(){

        String usuario = "03102674383";
        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalBHDPanama(
                        usuario,
                        "BANCO MULTIPLE BHD, S.A.",
                        "05307380031",
                        "NA",
                        "carlos_loyola@bhd.com.do",
                        true);

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 14, description = "Actualizar beneficiario BHD - CTA. Ahorros en Dolar", dependsOnMethods = "agregarBeneficiarioBHDCuentaAhorrosDolar")  // Done
    void actualizarBeneficiarioBHDCuentaAhorrosDolar(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioNacionalBHDPanama(
                        usuario,
                        "05307380031",
                        "Carlitos 2",
                        "carlos_loyola@bhd.com.do",
                        true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 15,description = "Eliminar Beneficiario BHD - CTA. Ahorros en Dolar", dependsOnMethods = "agregarBeneficiarioBHDCuentaAhorrosDolar") // DONE
    void eliminarBeneficiarioBHDCuentaAhorrosDolar(){
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional(
                                usuario,
                                "05307380031",
                                true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }



    @Test(priority = 16,description = "Agregar beneficiario BHD - CTA. Ahorros en Euro") // Done
    void agregarBeneficiarioBHDCuentaAhorrosEuro(){
        String usuario = "03102674383";
        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalBHDPanama(
                        usuario,
                        "BANCO MULTIPLE BHD, S.A.",
                        "07741080022",
                        "NA",
                        "carlos_loyola@bhd.com.do",
                        true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 17, description = "Actualizar beneficiario BHD - CTA. Ahorros en Euro", dependsOnMethods = "agregarBeneficiarioBHDCuentaAhorrosEuro")  // Done
    void actualizarBeneficiarioBHDCuentaAhorrosEuro(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioNacionalBHDPanama(
                        usuario,
                        "07741080022",
                        "Carlitos 2",
                        "carlos_loyola@bhd.com.do",
                        true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 18,description = "Eliminar Beneficiario BHD - CTA. Ahorros en Euro", dependsOnMethods = "agregarBeneficiarioBHDCuentaAhorrosEuro") // DONE
    void eliminarBeneficiarioBHDCuentaAhorrosEuro(){
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional(
                                usuario,
                                "07741080022",
                                true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }



    @Test(priority = 19,description = "Agregar beneficiario BHD - Tarjeta de credito")  // Verificar
    void agregarBeneficiarioBHDTarjetaCredito(){
        String usuario = "03102674383";
        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalBHDPanama(
                        usuario,
                        "BANCO MULTIPLE BHD, S.A.",
                        "4560443105064006",
                        "NA",
                        "carlos_loyola@bhd.com.do",
                        true);

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 20, description = "Actualizar beneficiario BHD - Tarjeta de credito", dependsOnMethods = "agregarBeneficiarioBHDTarjetaCredito")  // Done
    void actualizarBeneficiarioBHDTarjetaCredito(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioNacionalBHDPanama(
                        usuario,
                        "4560443105064006",
                        "Carlitos 2",
                        "carlos_loyola@bhd.com.do",
                        true);

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 21,description = "Eliminar Beneficiario BHD - Tarjeta de credito", dependsOnMethods = "agregarBeneficiarioBHDTarjetaCredito") // DONE
    void eliminarBeneficiarioBHDTarjetaCredito(){
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional(
                                usuario,
                                "4560443105064006",
                                true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }




    @Test(priority = 22,description = "Agregar beneficiario TC - Panama") // DONE
    void agregarBeneficiarioTarjetaCreditoPanama(){
        String usuario = "03102674383";
        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalBHDPanama(
                        usuario,
                        "BHDIB Panamá",
                        "4824740000111911",
                        "NA",
                        "carlos_loyola@bhd.com.do",
                        true);

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 23, description = "Actualizar beneficiario TC - Panama", dependsOnMethods = "agregarBeneficiarioTarjetaCreditoPanama")  // Done
    void actualizarBeneficiarioTarjetaCreditoPanama(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioNacionalBHDPanama(
                        usuario,
                        "4824740000111911",
                        "Carlitos 2",
                        "carlos_loyola@bhd.com.do",
                        true);

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
        //BeneficiariosInscritosLogic.getLogica().addBeneficiarioNacionalPinPesos("BANCO MULTIPLE BHD, S.A.","5513634","Alias de beneficiario","carlos_loyola@bhd.com.do",true);
    }

    @Test(priority = 24,description = "Eliminar Beneficiario TC - Panama", dependsOnMethods = "agregarBeneficiarioTarjetaCreditoPanama") // DONE
    void eliminarBeneficiarioTarjetaCreditoPanama(){
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional(
                                usuario,
                                "4824740000111911",
                                true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }


    String numeroProducto = new GenerarData().getNumRandomString(11);


    private String tcBancoJMMB = "382474000" + new GenerarData().getNumRandomString(7);
    private String prestamoDeOtroBanco = new GenerarData().getNumRandomString(8);;


    // Beneficiario de otros bancos
    @Test(description = "Agregar Beneficiario con Tarjeta credito - BANCO MULTIPLE JMMB BANK, S.A.")   //TC inician con 3 o 4. y contiene 16 digitos.
    void agregarBeneficiarioBancoJMMBTarjetaCredito(){

        String actualResult =  LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuarioCarlosLeon,"1111",false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalDeOtrosBancos(
                        usuarioCarlosLeon,
                        "BANCO MULTIPLE JMMB BANK, S.A.",
                        "Tarjetas de Crédito",
                        tcBancoJMMB,
                        "Cédula",
                        "22301391524",
                        "Carlos Tejeda",
                        "NA",
                        "TRINIDAD TOBAGO",
                        "Masculino",
                        "carlos_loyola@bhd.com.do",true);

        // Producto sustituido: 3824740000111918

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
        MsBeneficiariosNacionales rs = new MsBeneficiariosNacionales(usuarioCarlosLeon).newQuery().selecOtrosBancos().buscar(tcBancoJMMB);

        System.out.println(rs.getPais());
        System.out.println(rs.getNombreBanco());
    }

    @Test(description = "Actualizar beneficiario tarjeta de credito - BANCO MULTIPLE JMMB BANK, S.A.", dependsOnMethods = "agregarBeneficiarioBancoJMMBTarjetaCredito") // Done
    void actualizarBeneficiarioBancoJMMBTarjetaCredito(){

        String actualResult = LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuarioCarlosLeon,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false) // 5588464 Prestamo T24
                        .actualizarBeneficiarioNacionalDeOtrosBancos(
                                usuarioCarlosLeon,
                                tcBancoJMMB,
                                "Alias de prueba",
                                "ESPANA",
                                "NA",
                                "carlos_loyola@bhd.com.do",
                                true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
        MsBeneficiariosNacionales rs = new MsBeneficiariosNacionales(usuarioCarlosLeon).newQuery().selecOtrosBancos().buscar(tcBancoJMMB);

   //     System.out.println(rs.getPais());
//        Assert.assertEquals(rs.getPais(),"ES");
    }

    @Test(description = "Eliminar beneficiario tarjeta de credito - BANCO MULTIPLE JMMB BANK, S.A.", dependsOnMethods = "agregarBeneficiarioBancoJMMBTarjetaCredito")
    public void eliminarBeneficiarioBancoJMMBTarjetaCredito(){
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuarioCarlosLeon,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional(usuarioCarlosLeon,tcBancoJMMB,true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }

    @Test(priority = 1,description = "Agregar Beneficiario de BANCO MULTIPLE ACTIVO DOMINICANA - Cuenta Corriente")   //TC inician con 3 o 4. y contiene 16 digitos.
    void agregarBeneficiarioCuentaCorrienteDelBancoActivo(){
        String usuario = "03102674383";
        String actualResult =  LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111",false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalDeOtrosBancos(
                        usuario,
                        "BANCO MULTIPLE ACTIVO DOMINICANA, S.A.",
                        "Cuentas Corrientes",
                        "1495708045",
                        "Cédula",
                        "22301391524",
                        "Carlos Tejeda de banco activo",
                        "NA",
                        "TRINIDAD TOBAGO",
                        "Masculino",
                        "carlos_loyola@bhd.com.do",true); // Tarjeta Panama

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(priority = 2,description = "Actualizar beneficiario BANCO MULTIPLE ACTIVO DOMINICANA - Cuenta Corriente", dependsOnMethods = "agregarBeneficiarioCuentaCorrienteDelBancoActivo") // Done
    void actualizarBeneficiarioCuentaCorrienteDelBancoActivo(){

        String actualResult = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false) // 5588464 Prestamo T24
                .actualizarBeneficiarioNacionalDeOtrosBancos(
                        usuario,
                        "1495708045",
                        "Alias de prueba",
                        "ESPANA",
                        "NA",
                        "carlos_loyola@bhd.com.do",
                        true); // 05236100032

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
        MsBeneficiariosNacionales rs = new MsBeneficiariosNacionales(usuario).newQuery().selecOtrosBancos().buscar(numeroProducto);

        //     System.out.println(rs.getPais());
//        Assert.assertEquals(rs.getPais(),"ES");
    }


    @Test(priority = 3,description = "Eliminar beneficiario BANCO MULTIPLE ACTIVO DOMINICANA - Cuenta corriente", dependsOnMethods = "agregarBeneficiarioCuentaCorrienteDelBancoActivo")
    public void eliminarBeneficiarioCuentaCorrienteDelBancoActivo(){
        String actualResult = LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional(usuario,"1495708045",true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }

    @Test(description = "Eliminar beneficiario de otro banco")
    public void eliminarBeneficiarioDeOtroBanco(){
        String usuario = "03102674383";
        String actualResult =
                LoginLogic.get()
                        .iniciarDesdeDispositivoSeguro(usuario,"1111", false)
                        .irPaginaPagosTransferencias(false)
                        .beneficiariosInscritos(false)
                        .eliminarBeneficiarioNacional(usuario,numeroProducto,true);

        Assert.assertEquals( actualResult,"No se encontraron registros para mostrar");
    }


    // Agregar ✔ - Actualizar y Eliminar prestamo de otros bancos.
    @Test(description = "A Beneficiario con Préstamo de otro banco")   //TC inician con 3 o 4. y contiene 16 digitos.
    void beneficiarioPréstamoDeOtroBanco(){

        String actualResult =  LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuarioCarlosLeon,"1111",false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .agregarBeneficiarioNacionalDeOtrosBancos(
                        usuarioCarlosLeon,
                        "BANCO MULTIPLE JMMB BANK, S.A.",
                        "Préstamos",
                        prestamoDeOtroBanco,
                        "Cédula",
                        "22301391524",
                        "Carlos Tejeda",
                        "NA",
                        "TRINIDAD TOBAGO",
                        "Masculino",
                        "carlos_loyola@bhd.com.do",true); // Tarjeta Panama

        Assert.assertEquals(actualResult,"El beneficiario ha sido guardado correctamente");
    }

    @Test(description = "Actualizar beneficiario prestamo de otro banco - THE BANK OF NOVA SCOTIA")
    void actualizarBeneficiarioPrestamoDeotrosBancosBancoScotia(){
        String actualResult =  LoginLogic.get()
                .iniciarDesdeDispositivoSeguro(usuarioCarlosLeon,"1111",false)
                .irPaginaPagosTransferencias(false)
                .beneficiariosInscritos(false)
                .actualizarBeneficiarioNacionalDeOtrosBancos(
                        usuarioCarlosLeon,
                        prestamoDeOtroBanco,
                        "",
                        "",
                        "",
                        "",
                        true);
    }





}
