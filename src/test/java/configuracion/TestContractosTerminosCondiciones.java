package configuracion;

import basetest.BaseTest;
import microservicios.MsContratos;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginLogic;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 09/04/2024 3:54 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class TestContractosTerminosCondiciones extends BaseTest {

    @Test(description = "Aceptar Contrato ACH y Pagos al instante BCRD")
    void activarContratoACHPagoAlInstante(){
        boolean result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111",false)
                .configuracion(true)
                .activarContratoACHPagoInstanteBCRD(true);

        //Assert.assertTrue(result);
        Assert.assertTrue(new MsContratos("22301391524").contractACHPagoAlInstanteIsAccepted());
    }

    @Test(description = "Aceptar Contrato Internacional y Regional SIPA")
    void aceptarContratoInternacionalregionalSIPA(){
        boolean result = LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("40200573463","1111",false)
                .configuracion(true)
                .activarContratoInternacionalSIPA(true);

        //Assert.assertTrue(result);
        Assert.assertTrue(new MsContratos("40200573463").contractInternacionalRegionalSIPAIsAccepted());
    }

    @Test(description = "No Aceptar Actualizar Contrato Autorizacion uso informacion")
    void noAceptarActializarContratoAutorizacionUsoInformacion(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111",false)
                .configuracion(true)
                .actualizarContratoUsoDeInformacionOpcionNoAceptar(true);
    }

    @Test(description = "Aceptar Actualizar Contrato Autorizacion uso informacion")
    void aceptarActualizarContratoAutorizacionUsoInformacion(){
        LoginLogic.get()
                .iniciarDesdeDispositivoSeguro("22301391524","1111",false)
                .configuracion(true)
                .actualizarContratoUsoDeInformacionOpcionAceptar(true);
    }
}
