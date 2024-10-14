package data;

import microservicios.Utilidad;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 11/04/2024 10:48 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class ConstantesTasasImpuestos {



    /**
     * cashAdvance
     */
    public static final float AVANCE_EFECTIVO = 0.0625f;
    /**
     * cashAdvanceFee, <br>
     * deferred
     */
    public static final float DIFERIDO_AND_AVANCE_EFECTIVO = 0.04F;
    /**
     * international
     */
    public static final int INTERNACIONAL = 60;
    /**
     * lbtr
     */
    public static final int LBTR = 100;
    /**
     * lbtrEu,<br>
     * lbtrUs
     */
    public static final int LBTR_EURO_DOLAR = 5;
    /**
     * pinPesos
     */
    public static final int PIN_PESOS = 30;

    public static final float IMPUESTO_PINPESOS = 0.15F;
    /**
     * tax
     */
    public static final Double IMPUESTO = 0.0015;

    public static String calcularImpuesto(float monto){
       return Utilidad.convertirAMoneda( String.valueOf( (monto * IMPUESTO)) );
    }

    public static String calcularImpuesto(Double monto){
        return Utilidad.darFormatoMonedaComasPunto( String.valueOf((monto * IMPUESTO)) );
    }

    /**
     * Calcula el impuesto y lo retorna en formato String. monto * 0.15 / 100
     * @param monto
     * @return
     */
    public static String calcularImpuesto(String monto){
        float mont = Float.parseFloat(monto);
        return Utilidad.darFormatoMonedaFinal( String.valueOf(  (mont *0.15)    ));

        //return Utilidad.convertirAMoneda( String.valueOf(  ((mont*0.15)/100  )) );
    }

    public static void main(String[] args) {
        System.out.println(  calcularImpuesto("800")  );
    }






}
