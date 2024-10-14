package servicios.micros.accesos.beneficiarios;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import servicios.BaseRequestHTTP;
import util.Utilidad;

import java.util.UUID;

public class MicroBeneficiariosNacionales {

    protected int statusCode = 0;
    protected String cedula = "";
    protected JsonNode beneficiarios = null;

    public MicroBeneficiariosNacionales consultarMicro(String usuario){
        cedula = usuario;
        try {
            Response response = BaseRequestHTTP.configNewRequest()
                    .param("transactionId", UUID.randomUUID())
                    .param("documentNumber", usuario)
                    .get("https://ibp-sqa.app.noprod.cfbhd.com/bhd/v1.1/system/customer/beneficiaries/beneficiaries");

            statusCode = response.getStatusCode();
            if (statusCode == 200){
                beneficiarios = Utilidad.jsonNodeParse(response);
            }

            //System.out.println(beneficiarios);

        }catch (Exception e){

        }
        return this;
    }

    public BeneficiariosTerceroBHD selectBeneficiariosBHD(){
        return new BeneficiariosTerceroBHD(this);
    }

    public BeneficiariosTerceroOtrosBancos selectBeneficiariosOtrosBancos(){
        return new BeneficiariosTerceroOtrosBancos(this);
    }

    public BeneficiarioPinPeso selectBeneficiariosPinPesos(){
        return new BeneficiarioPinPeso(this);
    }

    public static void main(String[] args) {
        MicroBeneficiariosNacionales ms = new MicroBeneficiariosNacionales();
        BeneficiarioPinPeso pin = ms.consultarMicro("00111435384")
                .selectBeneficiariosPinPesos()
                .buscarBeneficiario("8091234567");

        System.out.println( pin.getAlias() );
        System.out.println( pin.getNombreActualBeneficiario() );
        System.out.println( pin.getNombreBancoLabel() );
        System.out.println( pin.getTelefonoBeneficiario() );
        System.out.println( pin.getNumDocumentoBeneficiario() );
        System.out.println(pin.getNombreBeneficiario());
        System.out.println("Beneficiario Existe: " + pin.isBeneficiarioExiste());

        BeneficiariosTerceroBHD bhd = pin.selectBeneficiariosBHD().buscarBeneficiario("210224300212");

        System.out.println("\n____________________________________________\nBeneficiario BHD");
        System.out.println();
        System.out.println(bhd.toString());

        System.out.println();
        System.out.println("\n____________________________________________\nBeneficiario Tercero de otro banco");
        BeneficiariosTerceroOtrosBancos tercero =  bhd.selectBeneficiariosOtrosBancos().buscar("BANCO MULTIPLE ADEMI, S.A.","12312312123");

        System.out.println("Nombre del banco: " + tercero.getNombreBanco()) ;
        System.out.println("Número del producto: " + tercero.getNumProducto()) ;
        System.out.println("Número del documento: " + tercero.getNumDocumento()) ;
        System.out.println("Alias: " + tercero.getAliasBeneficiario());
        System.out.println("nombre: " + tercero.getNombreBeneficiario()) ;
        System.out.println("correo: " + tercero.getCorreo()) ;
        System.out.println("Combo: " + tercero.getTipoProductoCombo()) ;
        System.out.println("Label: " + tercero.getTipoProductoLabel()) ;
    }
}
