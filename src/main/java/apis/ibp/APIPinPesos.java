package apis.ibp;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 13/06/2024 4:40 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APIPinPesos extends BaseRequest{

    private APISLogin login;

    public APIPinPesos(APISLogin login){
        super(login);
        this.login = login;
    }

    public void agregarBeneficiarioPinPesos(String alias, String telefono, String cedula, String nombre){

        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();

        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("beneficiaryType","Pin-pesos");
        Map<String, Object> beneficiary = new HashMap<>();

        beneficiary.put("creditProductAlias",alias);
        beneficiary.put("creditProductNumber",telefono);

        beneficiary.put("nationality","DO");
        beneficiary.put("gender","M");

        beneficiary.put("creditProductBankCode","BHD");
        beneficiary.put("creditDocumentType","1");
        beneficiary.put("creditDocumentNumber",cedula);
        beneficiary.put("creditProductHolderName",nombre);
        beneficiary.put("documentNumber",cedula);
        beneficiary.put("acceptDollar","N");
        beneficiary.put("documentType","1");
        beneficiary.put("bhdACHLBTR","");

        encriptar.put("beneficiary",beneficiary);

        httpPost(encriptar,"/bhdleon/api/v1/personal/web/beneficiaries-pinpesos","Agregar beneficiario pin pesos",nombreMetodo,getClass());
    }
}
