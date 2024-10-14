package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.By;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;


public class Utilidad {
    private static JsonNode node;
    private Utilidad(){}

    public static String extraerTextoDe(String esteTexto, String omitirEstoYTextoAnterior, String omitirEstoYTextoDespues ){
        String resultado = "";
        try {
            int inicio = esteTexto.indexOf(omitirEstoYTextoAnterior) + 2;


            int fin = esteTexto.indexOf(omitirEstoYTextoDespues);

            if (inicio != -1 && fin != -1) {
                resultado = esteTexto.substring(inicio, fin);
            } else {
                resultado = "El formato de la cadena no es correcto.";
            }

        }catch (Exception e){
            resultado = "Ha ocurrido un error extrayendo la cadena";
        }
        return resultado;
    }


    /** <h3>Este método permite convertir en un JsonNode un texto que contenga la estructura de un Json, para retornarlo como JsonNode.</h3>
     * @param jsonString Texto que contenga un Json.
     * @return JsonNode
     */
    public static JsonNode jsonNodeParse(String jsonString){
        try {
            node = new JsonMapper().readTree(jsonString);
        }catch (Exception e){
            return node;
        }
        return node;
    }

    /** <h3>Toma un response y lo convierte en JsonNode para retornarlo, este contendrá la data del response.</h3>
     * @param response Respuesta de una solicitud http
     * @return JsonNode
     */
    public static JsonNode jsonNodeParse(Response response){
        try {
            node = new JsonMapper().readTree(response.getBody().asString());
        }catch (Exception e){
            return node;
        }
        return node;
    }

    /** "$[?(<a href>@.nombre='Juan'</a>)]"
     * @param json
     * @param expresionJsonPath
     * @return
     */
    public static JsonNode filtrarJsonPath(String json, String expresionJsonPath){
        Object jsonObect = Configuration.defaultConfiguration().jsonProvider().parse(json);
        return jsonNodeParse(JsonPath.read(jsonObect,expresionJsonPath).toString());
    }

    /**<h3>Este metodo elimina la notacion cientifica.</h3>
     * doble = 1545498765656.59 = Impreso = 1.54549876565659E12 = Convertido 1,545,498,765,656.59
     * @param numero double 3114305.63 = 3,114,305.63
     * @return String
     */
    public static String formatearNumero(double numero){
        DecimalFormat formatoConComa = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
        return formatoConComa.format(numero);
    }

    /** <h3>import org.apache.commons.lang3.text.WordUtils</h3>
     * @param texto texto a formatear
     * @return retorna el texto formateado con la primera letra de cada palabra en mayuscula.
     */
    public static String formatearPrimerasLetrasPalabrasEnMayuscula(String texto) {
        String text = "";
        if (texto == null || texto.isEmpty()) {
            text = texto;
        }else {
            text = capitalizeFully(texto);
        }
        return text;
    }

    /** Este método da formato a un texto colocando la primera letra de cada palabra en mayúscula y el resto en minúscula.
     * Ejemplo: carlos Alberto Loyola = Carlos Alberto Loyola
     * Ejemplo: CARLOS LOYOLA TEJEDA = Carlos Alberto Loyola
     * @param texto texto el cual se desea formatear.
     * @return Texto formateado con la primera letra de cada palabra en mayuscula.
     */
    public static String formatearPrimeraLetraMayusc(String texto){
        String[] palabras = texto.toLowerCase().split(" ");
        StringBuilder resultado = new StringBuilder();
        for (String palabra : palabras) {
            resultado.append(Character.toUpperCase(palabra.charAt(0))).append(palabra.substring(1)).append(" ");
        }
        return resultado.toString().trim();
    }

    /** Retorna un By del Xpath pasado por parámetro.
     * @param xpath Xpath identificador del elemento.
     * @return By
     */
    public static By by(String xpath){
        return By.xpath(xpath);
    }

    /**
     * Este método recibe el numero de producto de una Tarjetas de crédito, Tarjetas de debito y retorna dicho numero de producto enmascarado.<br>
     * Ejemplo: 5544440000300665 = 55444400****0665
     * @param numeroTC Numero de TC o TD sin enmascarar.
     * @return Numero de producto enmascarado.
     */
    public static String enmascararTC(String numeroTC){
        StringBuilder tcEnmascarada = new StringBuilder();
        for (int i = 0; i < numeroTC.length(); i++) {
            if (i<8){
                tcEnmascarada.append(numeroTC.charAt(i));
            }
            if (i>=8 && i<12){
                tcEnmascarada.append("*");
            }
            if (i>=12){
                tcEnmascarada.append(numeroTC.charAt(i));
            }
        }
        return tcEnmascarada.toString();
    }

    public static String getTextoEntreParentesis(String texto){
        Pattern patron = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = patron.matcher(texto);
        if (matcher.find()){
            texto = matcher.group(1);
        }
        return texto;
    }



    public static String extractValue(String text, String start, String end) {
        int startIndex = text.indexOf(start);
        if (startIndex != -1) {
            startIndex += start.length();
            int endIndex = text.indexOf(end, startIndex);
            if (endIndex != -1) {
                return text.substring(startIndex, endIndex);
            }
        }
        return null; // Retorna null si no encuentra los delimitadores
    }


    /**
     * Crear una nueva lista de mapas para agregar otros mapas en el utilizando el método .add() y poder crear un Json ejemplo:
     *
     * Map<String, Object> json1 = new HashMap<>();
     *
     * json1.put("index", "2");
     * json1.put("value", "Maria");
     * json1.put("out", false);
     *
     * crearListaMapa.add(json1);
     *
     * @return {@link List<Map>}
     */
    public static List<Map<String, Object>> crearListaMapa(){
        List<Map<String, Object>> listaMapa = new ArrayList<>();
        return listaMapa;
    }

    public static String redondearFlotante(double numero){
        return String.valueOf(  Math.round(numero * 100.0) / 100.0  ) ;
    }

    public static String redondearFlotante(String monto){
        return String.valueOf(  Math.round(Double.parseDouble(monto) * 100.0) / 100.0  ) ;
    }

    public static String darFormatoMonedaComasPunto(String monto){
        String formateado = "";

        double money = Double.parseDouble(monto);
        DecimalFormat formato = new DecimalFormat("#,###.00");
        formateado = formato.format(money);

        if (formateado.length() == 3 && formateado.charAt(0) == '.'){
            formateado = 0 + formateado;
        }
        return formateado;
    }

    /**
     * Retorna el monto introducido en formato moneda.
     * ejemplo. 20 = 20.0
     * 1000 = 1,000.0
     * @param monto
     * @return
     */
    public static String darFormatoMonedaFinal(String monto){
        String resultado = "";
        try {
            monto = redondearFlotante(monto);
            if (monto.length()<=5 && monto.contains(".")){
                resultado = monto;
            }
            else {
                String conversion = darFormatoMonedaComasPunto(monto);
                if (conversion.contains(".")){
                    resultado =   conversion.replace(",","@").replace(".",",").replace("@",".");
                }
            }
        }catch (Exception e){
            System.out.println("El texto introducido no se puede convertir a numérico.");
        }
        return resultado;
    }

    public static String convertirAMoneda(String monto){
        String resultado = "";
        DecimalFormat formato = new DecimalFormat("#,###.00");

        if (!monto.contains(".")){
            monto = monto+".00";
            resultado = formato.format(Double.parseDouble(monto)).replace(",","@").replace(".",",").replace("@",".");
        }else {
            double money = Double.parseDouble(monto);
            if (monto.length()==3){
                if (monto.contains(".")){
                    resultado = monto;
                }
            }else {
                String validarMonto = String.valueOf(money);

                if (validarMonto.contains("0.") && validarMonto.length()<5){
                    resultado = validarMonto;
                }else {
                    resultado = formato.format(money).replace(",","@").replace(".",",").replace("@",".");
                }
            }
        }

        return resultado;
    }
    public static String convertStringToMoneyFormat(String stringMoney){
        double money = Double.parseDouble(stringMoney);
        DecimalFormat formato = new DecimalFormat("#,###.00");
        return formato.format(money).replace(",","@").replace(".",",").replace("@",".");
    }

    public static String convertStringToMoneyFormat(float moneys){
        double money = Double.parseDouble(String.valueOf(moneys));
        DecimalFormat formato = new DecimalFormat("#,###.00");
        return formato.format(money).replace(",","@").replace(".",",").replace("@",".");
    }

    /**
     * Permite abrir una etiqueta table para crear una tabla. Recibe como parámetro un String con las columnas y filas con la data.
     * @param body Columnas y filas a agregar en la tabla.
     * @return Tabla HTML como String.
     */
    public static String createTable(String body){
        return "<table>"+body+"</table>";
    }

    /**
     * Permite crear una nueva fila HTML, para agregar nuevas columnas o data en cada columna de la fila actual.<br><br>
     *
     * Para agregar varias columnas llamar al mismo método createTH por cada columna a crear de forma concatenada.<br><br>
     *
     * Lo mismo aplica para cada dato de cada columna de una misma fila. llamando al método createTD.
     *
     * @param ColumnsOrRows Columnas a agregar o datos de la fila actual. Cada TH representa una nueva columna y cada TD representa un nuevo nada en la fila actual de la columna actual.
     * @return Retorna un String con la fila de columnas o fila de datos.
     */
    public static String createTR(String ColumnsOrRows){
        return "<tr>"+ColumnsOrRows+"</tr>";
    }

    /**
     * Este método permite crear una o varias columnas dentro de la tabla. Si se quieren agregar varias
     * columnas llamar este método de forma concatenada pasando como parámetro el nombre de cada columna.<br><br>
     *
     * Este método debe llamarse dentro del método <b> createTR </b>, ya sea para crear una o varias columnas. <br><br>
     *
     * Ejemplo: insertando una primera fila con varias columnas: <br> createTR( createTH( "Columna 1" ) + createTH( "Columna 2" ) + createTH( "Columna 3" )) <br><br>
     * @see #createTR(String)
     * @param columnName Nombre que se le asignara a cada columna o cabecera.
     * @return Una o varias columnas en formato HTML.
     */
    public static String createTH(String columnName){
        return "<th>"+columnName+"</th>";
    }

    /**
     * Este método permite crear un nuevo dato en cada columna de la fila actual. Si se quiere agregar
     * datos en varias columnas de la fila actual, se debe llamar este método de forma concatenada
     * pasando como parámetro el dato, donde por cada vez que se invoque el método ira al numero de
     * la columna de la fila actual. <br><br>
     *
     * Este método debe llamarse dentro del método createTR, ya sea para un dato o los datos de varias columnas. <br><br>
     *
     * Ejemplo: insertando una fila de datos: createTR( createTD( data1 ) + createTD( data2 ) + createTD( data3 ))
     *
     * @param dtFila Data que será ingresada.
     * @return Retorna un data en la primera o siguiente columna.
     */
    public static String createTD(String dtFila){
        return "<td>"+dtFila+"</td>";
    }

}
