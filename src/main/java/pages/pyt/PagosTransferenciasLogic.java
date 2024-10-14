package pages.pyt;

import pages.PoAccionesRepetitivas;
import pages.pyt.accesosrapidos.beneficiariosinscritos.BeneficiariosInscritosLogic;
import pages.pyt.accesosrapidos.serviciosinscritos.ServiciosIncritosLogic;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 22/02/2024 10:38 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PagosTransferenciasLogic {

    private PoPagosTransferencias pagina;
    private PagosTransferenciasLogic(){
        pagina = PoPagosTransferencias.getNewPagina();
    }
    public static PagosTransferenciasLogic getLogica(){
        return new PagosTransferenciasLogic();
    }

    public BeneficiariosInscritosLogic beneficiariosInscritos(boolean crearReporte){
        return pagina.clickBeneficiariosInscritos(crearReporte);
    }

    public ServiciosIncritosLogic serviciosInscritos(boolean crearReporte){
        return pagina.clickServiciosInscritos(crearReporte);
    }
}
