package pages.configuracion;

import accion.AccionPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POInformacionAccesoSeguridadPage extends AccionPage {

    private POConfiguracionPage pageConfiguracion = null;
    public POInformacionAccesoSeguridadPage(WebDriver webDriver, POConfiguracionPage pageConfiguracion) {
        super(webDriver);
        this.pageConfiguracion = pageConfiguracion;
    }

    private final By titulo = by("//h3[contains(.,'Información de acceso y seguridad')]");


    /**
     * Estados: true cuando esta desplegado, false cuando no.
     * Nombre sesion: Cambiar clave de acceso | Preguntas de seguridad
     * Texto del label de Descricion de la sesion: Desde aquí podrás modificar tu clave de acceso | Serán requeridas para resetear tu clave de acceso y para acceder al canal desde cualquier dispositivo que no hayas establecido como seguro
     */
    private final String xpLinkCambiarClave = "//a[@aria-expanded[contains(.,'false')] and div/div[h4[contains(.,'Preguntas de seguridad')] and p[contains(.,'Serán requeridas para resetear tu clave de acceso')]]]  ";

    // Link buenos
    private final By linkCambiarClaveAcceso = by("//a[div/div[h4[contains(.,'Cambiar clave de acceso')] and p[contains(.,'Desde aquí podrás modificar tu clave de acceso.')]]]");
    private final By linkPreguntasSeguridad = by("//a[div/div[h4[contains(.,'Preguntas de seguridad')] and p[contains(.,'Serán requeridas para resetear tu clave de acceso y para acceder al canal desde cualquier dispositivo que no hayas establecido como seguro')]]]");
    private final By linkCambiarClaveNoDesplegado = by("//a[@aria-expanded[contains(.,'false')] and div/div[h4[contains(.,'Cambiar clave de acceso')] and p[contains(.,'Desde aquí podrás modificar tu clave de acceso.')]]]");
    private final By linkPreguntasSeguridadDeshbilitado = by("//a[@aria-expanded[contains(.,'false')] and div/div[h4[contains(.,'Preguntas de seguridad')] and p[contains(.,'Serán requeridas para resetear tu clave de acceso y para acceder al canal desde cualquier dispositivo que no hayas establecido como seguro')]]]");


    private final By txtClaveAccesoActual = by("//div[label[contains(.,'Clave de acceso actual')] ]/input");
    private final By txtNuevaClave = by("//div[label[contains(.,'Nueva clave de acceso')] ]/input");
    private final By txtConfirmarClave = by("//div[label[contains(.,'Confirmar nueva clave')] ]/input");

    private String getTitulo(boolean tomarCaptura){
        return getText( titulo, 3, getClass(), tomarCaptura );
    }
    public POInformacionAccesoSeguridadPage verificarFormularioCambioDeClaveAcceso(String claveActual, String nuevaClave, String confirmarClave, boolean crearReporte){
        if (getAttributeValue( linkCambiarClaveAcceso, "aria-expanded",1, getClass() ).equals("false")){
            clickOn( linkCambiarClaveAcceso, "link","Cambiar clave de acceso",1,false, getClass(), false, crearReporte);
        }
        writeOn( txtClaveAccesoActual, claveActual,"Clave de acceso actual",1, getClass(), crearReporte);
        writeOn( txtNuevaClave, nuevaClave,"Nueva clave de acceso",1, getClass(), crearReporte);
        writeOn( txtConfirmarClave, confirmarClave,"Confirmar nueva clave",1, getClass(), crearReporte);
        return this;
    }

    /**
     * Clave de acceso actual <br>
     * Nueva clave de acceso <br>
     * Confirmar nueva clave
     *
     * @param nombreCambo Clave de acceso actual | Nueva clave de acceso | Confirmar nueva
     * @param crearReporte
     * @return
     */
    public String getMsgAlertaCAmpo(String nombreCambo, boolean crearReporte){
        return getText(by("//div[label[contains(.,'%s')] ]/div/span[@class[contains(.,'error') ]]", nombreCambo),1,getClass(), crearReporte);
    }

    public void llenarFormularioClave(String claveActual, String nuevaClave, String confirmarClave, boolean crearReporte){
        getTitulo(crearReporte);
        verificarFormularioCambioDeClaveAcceso( claveActual, nuevaClave, confirmarClave, crearReporte );

        crearPaso("Pantalla",true, crearReporte, crearReporte);
    }
}
