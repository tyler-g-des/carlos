package pages;

import microservicios.MsContratos;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 18/04/2024 2:54 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class PruebasEliminar {

    public static void main(String[] args) {
        String usuarioConContrato = "00111671855";
        String usuarioSinContrato = "00100965995";
        MsContratos contratos = new MsContratos(usuarioSinContrato);

        System.out.println(contratos.contractInternacionalRegionalSIPAIsAccepted());
    }
}
