package com.bhd.ibp.pages.pagostransferencias;

import accion.AccionMetodos;
import com.bhd.ibp.pages.POSeleccionarFechaPage;
import data.Persistencia;
import org.openqa.selenium.WebDriver;
import servicios.micros.MicroContratos;

import static com.bhd.ibp.pages.pagostransferencias.POTransferenciasRegionalesSIPAPage.COMBO_TIPO_TRANSACCION;

public class POTransferenciaInternacionalPage extends AccionMetodos {

    private POSeleccionarFechaPage reutilizar = null;
    public POTransferenciaInternacionalPage(WebDriver webDriver) {
        super(webDriver);
        reutilizar = new POSeleccionarFechaPage(driver);
    }


    private void selectTipoTransaccion(boolean crearReporte){
        String tipo = getText(COMBO_TIPO_TRANSACCION,15, getClass());
        if (! tipo.equals("Transferencia Internacional")){
            selectItemCombo(COMBO_TIPO_TRANSACCION,"Transferencia Internacional","Tipo de transacción",1,getClass(),crearReporte);
        }
        getText(COMBO_TIPO_TRANSACCION,0,getClass(), crearReporte);

        MicroContratos contratos =  MicroContratos.getInstance().consultarContratoInternacional(Persistencia.getInstance().getClientInfo(driver).getCedula());

        waitElementVisible( COMBO_TIPO_TRANSACCION,20, getClass() );
        if ( contratos.isContractAccepted()){
            reutilizar.modalEntiendo();
        }else {
            reutilizar.modalContratoInternacionalRegionalSIPA("1111",crearReporte);
        }
        getText( COMBO_TIPO_TRANSACCION, 0, getClass(), crearReporte);
    }

    private void setFormulario(){
        
    }
}
