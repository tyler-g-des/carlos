package apis.ibp;

import java.util.Map;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 12/06/2024 3:45 p. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class APIPerfilUsuario extends BaseRequest{

    private APISLogin login;
    public APIPerfilUsuario(APISLogin login) {
        super(login);
        this.login = login;
    }

    public void consultarCompañiasTelefonicas(String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("queryString","typeoflist=PhoneCompany");

        httpGET(encriptar,"/bhdleon/api/v1/personal/web/sor-value-list/getlistvalues",accion,nombreMetodo, getClass());
    }

    public void consultarListaProvincias(String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("category","Provincia");
        encriptar.put("queryString","countrycode=DO&designation=Provincia");

        httpGET(encriptar,"/bhdleon/api/v1/personal/web/pega-self-service-api/country-details",accion,nombreMetodo, getClass());
    }

    public void consultarMunicioCiudades(String provincia, String codigoProvincia, String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("category","Ciudad");
        encriptar.put("provincecode",codigoProvincia);
        encriptar.put("queryString","countrycode=DO&designation=Ciudad&provincecode=" + codigoProvincia);

        httpGET(encriptar,"/bhdleon/api/v1/personal/web/pega-self-service-api/country-details",accion + " " + provincia,nombreMetodo, getClass());
    }
    
    public void consultarSectores(String codigoProvincia, String sector,String codigoSector, String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        Map<String, Object> encriptar = getMapaComunUUID();

        encriptar.put("category","Sector");
        encriptar.put("queryString","countrycode=DO&designation=Sector&provincecode="+codigoProvincia+"&citycode=" + codigoSector);
        encriptar.put("provincecode",codigoProvincia);
        encriptar.put("cityCode",codigoSector);
        httpGET(encriptar,"/bhdleon/api/v1/personal/web/pega-self-service-api/country-details",accion + " " + sector,nombreMetodo, getClass());
    }

    public void consultarLocalidad(String codigoProvincia,String codigoCiudad, String sector,String codigoSector, String accion){
        String nombreMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
        Map<String, Object> encriptar = getMapaComunUUID();
        encriptar.put("category","Paraje");
        encriptar.put("queryString","countrycode=DO&designation=Paraje&provincecode="+codigoProvincia+"&citycode="+codigoCiudad+"&sectorcode=" + codigoSector);
        encriptar.put("provincecode",codigoProvincia);
        encriptar.put("cityCode",codigoCiudad);
        encriptar.put("sectorCode",codigoSector);


        httpGET(encriptar,"/bhdleon/api/v1/personal/web/pega-self-service-api/country-details",accion + " " + sector,nombreMetodo, getClass());
    }

    public static void main(String[] args) {
        APISLogin login = new APISLogin();
        login.loginSecureDevice("00100965995","1111","Iniciar sesion");

        APIPerfilUsuario perfilUsuario = new APIPerfilUsuario(login);
        //perfilUsuario.consultarMunicioCiudades("SAN CRISTOBAL","21","Consultar municipios de san cristobal");
        //perfilUsuario.consultarSectores("21","Sabana Grande de Palenque","03","Consultar sectores");
        perfilUsuario.consultarLocalidad("21","03","Juan Baron","04","Consultar localidad");
        //perfilUsuario.consultarCompañiasTelefonicas("Consultar compañias telefonicas");
        //perfilUsuario.consultarListaProvincias("Consultar lista provincias");
    }
}
