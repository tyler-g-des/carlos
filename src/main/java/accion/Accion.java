package accion;

import basetest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;
import pages.BasePage;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.format;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 * @author Carlos Loyola
 */
public class Accion extends BasePage {

    private static final String MESSAGE_CLICK = "Se hizo click en: %s";
    private static Logger logger = LoggerFactory.getLogger(Accion.class);
    private static final String MESSAGE_ERROR = "Error ";
    private final String XP_OPCION_COMBO = "//p-dropdownitem//li[@role='option']/span[contains(.,'%s')]";
    private final By TXT_BUSCAR = By.xpath("//input[@autocomplete='off' and @class[contains(.,'p-dropdown-filter')]]") ;

    private WebDriver driver = null;
    public Accion(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static Accion getAcciones(){
        return new Accion(BaseTest.getDriver());
    }

    public By by(String format, Object... args){
        System.out.println(args.length);
        return By.xpath( String.format(format, args) );
    }

    public By by(String xpath){
        return By.xpath(xpath);
    }

    /** Permite colocar el foco sobre el elemento, haciendo que este visible en la pantalla.
     * @param element Elemento el cual se quiere enfocar si esta presente pero no esta visible en la pantalla.
     * @throws NoSuchElementException
     */
    public void focusElement(WebElement element){
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });", element);
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
    }



    /** Método que abre el navegador y navega a la pagina indicada en el parametro URL.
     * @param url URL de la pagina que se abrirá en el navegador.
     */
    public void navegar(String url){
        try {
            driver.get(url);
        }catch (Exception e){
            logger.error(MESSAGE_ERROR, e);
        }
    }

    public void esperar(long waitSecons){
        try {
            new WebDriverWait(driver,waitSecons).until(ExpectedConditions.presenceOfElementLocated(By.xpath("Espera")));
        }catch (Exception e){

        }
    }
    public void refrescarPagina(){
        driver.navigate().refresh();
    }

    /** Escribe en un elemento con identificador único en la pagina.
     * @param element Elemento en el cual se va a escribir.
     * @param text Texto el cual se escribirá en el campo. Enviar NA si no se quiere interactuar con el elemento
     * @param nameObject Nombre del campo en el cual se escribirá.
     * @param wait Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @param createReport Permite crear o no reporte de este proceso.
     */
    public void writeOn(By element, String text, String nameObject, long wait, Class<?> thisClass, boolean createReport){
        try {
            if (!text.equalsIgnoreCase("NA")){

                WebElement elemento = getElement( element, wait );
                Optional.ofNullable(elemento).ifPresent(objecto -> {

                    if (!objecto.isDisplayed()){
                        focusElement(objecto);
                    }
                    new WebDriverWait(driver, wait).until(ExpectedConditions.elementToBeClickable(objecto)); // agregado final
                    objecto.clear();
                    new WebDriverWait(driver, wait).until(ExpectedConditions.elementToBeClickable(objecto)).click();
                    objecto.sendKeys(text);
                    if (text.equals("")){
                        crearPaso("Se escribio el texto " + text + " en el " + nameObject,true, createReport,false);
                    }
                    crearPaso("Se escribio el texto " + text + " en el " + nameObject,true, createReport,false);
                });
            }
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    /** Se encarga de enfocar enfocar el elemento.
     * @param elements Elemento en el cual se va a enfocar.
     * @param waitSeg Tiempo máximo que se esperara por el elemento.
     * @param thisClass enviar el metodo getClass();
     */
    public void mouseOvers(By elements, long waitSeg, Class <?> thisClass ){
        try {
            Optional.ofNullable(getElementOnList(elements, waitSeg)).ifPresent(objecto -> {
                focusElement(objecto);
                new Actions(driver).moveToElement(objecto).build().perform();
            });
        }catch (Exception e){
            imprimirFallo(thisClass, e);
            logger.error(MESSAGE_ERROR, e);
        }
    }

    /** Escribe en el elemento que este visible de una lista de elementos que tienen el mismo identificador. Enviar NA en el parámetro texto si no se quiere interactuar con el elemento.
     * @param elements Elemento que puede tener varios elementos en la pagina, donde el método solo va interactuar con el elemento visible.
     * @param text Texto el cual se escribirá en el campo. Enviar NA si no se quiere interactuar con el elemento
     * @param nameObject Nombre del campo en el cual se escribirá.
     * @param wait Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @param createReport Permite crear o no reporte de este proceso.
     */
    public void writeOns(By elements, String text, String nameObject, long wait, Class<?> thisClass, boolean createReport){
        try {
            if (!text.equalsIgnoreCase("NA")){
                Optional.ofNullable( getElementOnList( elements, wait ) ).ifPresent( objeto -> {
                    focusElement(objeto);
                    objeto.clear();
                    new WebDriverWait(driver, wait).until(ExpectedConditions.elementToBeClickable(objeto)).click();
                    objeto.sendKeys(text);
                    crearPaso("Se escribio el texto " + text + " en el " + nameObject,true, createReport,false);
                });
            }
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    /** Permite escribir el texto enmascarado en un elemento.
     * @param element Elemento en el cual se va a escribir.
     * @param text Texto el cual se escribirá en el campo. Enviar NA si no se quiere interactuar con el elemento.
     * @param nameObject Nombre del campo en el cual se escribirá.
     * @param wait Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @param createReport Permite crear o no reporte de este proceso.
     */
    public void writeMaskedOn(By element, String text, String nameObject, long wait, Class<?> thisClass, boolean createReport){
        try {
            if (!text.equalsIgnoreCase("NA")){
                Optional.ofNullable( getElement( element, wait ) ).ifPresent( objeto -> {
                    if (!objeto.isDisplayed()){
                        focusElement(objeto);
                    }
                    objeto.clear();
                    new WebDriverWait(driver, wait).until(ExpectedConditions.elementToBeClickable(objeto)).click();
                    objeto.sendKeys(text);
                    crearPaso("Se escribio ********* en el " + nameObject,true, createReport,false);
                });
            }
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    /** <h3>Retorna el texto del elemento. También permite tomar captura de manera opcional.</h3>
     * @param element Elemento del cual se va a obtener el texto.
     * @param wait Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @return Texto que contiene el elemento.
     */
    public String getText(By element, long wait, Class<?> thisClass, boolean takeScreenShotReporte){
        AtomicReference<String> texto = new AtomicReference<>("");
        try {

            Optional.ofNullable(getElement(element,wait)).ifPresent(objecto -> {

                focusElement(objecto);
                texto.set(objecto.getText());
                crearPaso(texto.get(),true, takeScreenShotReporte, takeScreenShotReporte);
            });

        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return texto.get();
    }

    /** <h3>Verificar que el elemento este presente en la pagina.</h3>
     * @param element Elemento que se verificara su presencia.
     * @param waitSecond Tiempo máximo que se esperara por el elemento.
     * @param thisClass  Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     */
    public void isElementPresent(By element, long waitSecond, Class<?> thisClass){
        try {
            getAllElements(element, waitSecond,thisClass);
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }
    public void isElementPresent(By element, String nombreElement, long waitSecond, Class<?> thisClass){
        try {
            getAllElements(element, waitSecond,thisClass);
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    /** <h3>De una lista de elementos, espera a que un elemento este visible. Si no esta visible genera un excepción.</h3>
     * @param elements Elemento el cual se esperara que este visible.
     * @param waitSecond Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     */
    public boolean isElementsVisible(By elements, long waitSecond, Class<?> thisClass){
        try {
            getAllElements(elements, waitSecond,thisClass);
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            WebElement element1 = espera.until(ExpectedConditions.visibilityOfElementLocated(elements));
            focusElement(element1);
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return true;
    }


    /** <h3> Espera a que un elemento elemento este visible y luego invisible o que no este presente. </h3>
     * @param elementos Elemento que contiene varios elementos con el mismo identificador en la pagina.
     * @param waitSeg Tiempo máximo que se esperara por el elemento.
     */
    public boolean visiblesAndThenInvisible(By elementos, int waitSeg){
        try {
            Optional.ofNullable(getElementOnList(elementos, waitSeg)).ifPresent(objecto -> {
                if (!objecto.isDisplayed()){
                    focusElement(objecto);
                }
                new WebDriverWait(driver,waitSeg).until(ExpectedConditions.invisibilityOf(objecto));
            });
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /** <h3> Enfoca el elemento en la pagina y deja el mouse presionado sobre el mismo. </h3>
     * @param element
     * @param waitSecond Tiempo máximo que se esperara por el elemento.
     */
    public void focusOnPress(By element, long waitSecond){
        try {
            WebElement eelemento = getElement(element,waitSecond);
            Optional.ofNullable(eelemento).ifPresent(elemento->{
                focusElement(elemento);
                new  Actions(driver).moveToElement(eelemento).perform();
            });
        }catch (Exception e){
            logger.error("No se pudo enfocar el elemento. con el metodo focusOnPress de la class Accion", e);
        }
    }

    public void focusOnPress(By element, long waitSecond, Class<?> thisClass){
        try {
            WebElement eelemento = getElement(element,waitSecond);
            Optional.ofNullable(eelemento).ifPresent(elemento->{
                focusElement(elemento);
                new  Actions(driver).moveToElement(eelemento).perform();
            });
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }


    /** <h3> Verifica que un elemento este visible en la pagina. Si esta visible retorna true, de lo contrario retorna false. </h3>
     * @param element Elemento que se espera a que sea visible.
     * @param waitSecond Tiempo máximo que se esperara por elemento en segundos.
     * @return boolean
     */
    public boolean isElementVisible(By element, long waitSecond){
        try {
            WebElement element1 = getElement(element, waitSecond);
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            espera.until(ExpectedConditions.visibilityOf(element1));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**<h1>Da numero erroneo donde fallo el codigo</h1>
     * <h3> Espera a que el elemento este visible en la pantalla, si el elemento no se muestra, dispara un error. </h3>
     * @param element Elemento que se espera a que se muestre.
     * @param waitSecond Tiempo máximo que se esperara por elemento en segundos.
     * @param getClass Solo para por parámetro el método: getClass()
     * @throws NoSuchElementException
     */
    public void isElementVisible(By element, long waitSecond, Class<?> getClass){
        try {
            getElementVisible(element,waitSecond);
        }catch (Exception e){
            imprimirFallo(getClass, e);
        }
    }

    public void waitElementVisible(By element, long wait, Class<?> thisClass){
        try {
          Optional.ofNullable( waitVisible( element, wait) ).ifPresent(this::focusElement);
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }


    /** Espera a que el elemento visible en la pagina se haga Invisible. Si no esta visible genera un excepción.
     * @param element Elemento el cual se esperara que se haga invisible.
     * @param waitSecond Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     */
    public void isElementInvisibility(By element, long waitSecond, Class<?> thisClass){
        try {
            getElementInvisibility(element, waitSecond);
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    private void elementoInvisible(By element, long wait){
        try {
            WebElement element1 = getElement(element, wait);
            WebDriverWait espera = new WebDriverWait(driver, wait);
            espera.until(ExpectedConditions.invisibilityOf(element1));
        }catch (NoSuchElementException e){

        }
    }

    public void isElementInvisibility3(By element, long wait, Class<?> thisClass){
        try {
            elementoInvisible(element,wait);
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    public boolean isElementInvisibility2(By element, long wait, Class<?> thisClass){
        //AtomicReference<String> texto = new AtomicReference<>("");
        try {

            Optional.ofNullable(getElement(element,wait)).ifPresent(objecto -> {

                focusElement(objecto);
                //texto.set(objecto.getText());
                //crearPaso(texto.get(),true, takeScreenShotReporte, takeScreenShotReporte);
                WebDriverWait espera = new WebDriverWait(driver, wait);
                espera.until(ExpectedConditions.invisibilityOf(objecto));
            });
            return true;
        }catch (Exception e){
            imprimirFallo(thisClass, e);
            return false;
        }

        //
        //
        // return texto.get();
    }
//    public boolean isElementInvisibility(By element, long waitSecond, Class<?> thisClass){
//        try {
//
//        }catch (Exception e){
//
//        }
//    }



    /** Permite obtener el atributo de un elemento presente en la pagina.
     * @param element Elemento del cual se obtendrá el atributo.
     * @param atribute Atributo del cual se quiere obtener el valor.
     * @param wait Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @return valor del atributo.
     */
    public String getAttribute(By element, String atribute,long wait, Class<?> thisClass ){
        String atributoValor = "";
        try {
            WebElement element1 = getElement(element,wait);
            if (element1!=null){
                if (!element1.isDisplayed()){
                    focusElement(element1);
                }
                atributoValor = element1.getAttribute(atribute);
            }

        }catch (Exception e){
            imprimirFallo(thisClass, e);
            logger.error(MESSAGE_ERROR, e);
        }
        return atributoValor;
    }

    /** Permite obtener el atributo de un elemento presente en la pagina.
     * @param element Elemento del cual se obtendrá el atributo.
     * @param atribute Atributo del cual se quiere obtener el valor.
     * @param wait Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @return valor del atributo.
     */
    public String getAttribute(WebElement element, String atribute, Class<?> thisClass ){
        AtomicReference<String> atributo = new AtomicReference<>("");
        try {
            focusElement(element);
            atributo.set(element.getAttribute(atribute));
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return atributo.get();
    }

    private void esperarClicable(WebElement element, long wait){
        try {
            new WebDriverWait(driver, wait).until(ExpectedConditions.elementToBeClickable(element));
        }catch (NoSuchElementException e){

        }
    }

    private WebElement getElementClicable(By elemento, long waitSecond){

        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            return espera.until(ExpectedConditions.elementToBeClickable(espera.until(ExpectedConditions.presenceOfElementLocated(elemento))))  ;
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
            return null;
        }
    }

    /** <h3>VERIFICAR. </h3>Realiza clic sobre el elemento.
     * @param element Elemento en el cual se realizara click.
     * @param nameObject Nombre del elemento.
     * @param waitSeg Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @param takeCapture Permite tomar captura antes de hacer click en el elemento.
     * @param createReport Permite crear o no reporte de este proceso.
     */
    public void clickOn(By element, String nameObject, long waitSeg, Class<?> thisClass, boolean takeCapture, boolean createReport){
        try {
            WebElement eelemento = getElementVisibleClicable(element,waitSeg);

            Optional.ofNullable(eelemento).ifPresent(elemento->{
                focusElement(elemento);

                new  Actions(driver).moveToElement(eelemento).build().perform();
                //getElementClicable(element,waitSeg);

                crearPaso(format(MESSAGE_CLICK, estiloLinkBold(nameObject)),true, createReport,takeCapture);
                elemento.click();
            });
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    public String clickOnVisClickcable(By element, long wait, Class<?> thisClass, boolean takeScreenShotReporte){
        AtomicReference<String> texto = new AtomicReference<>("");
        try {

            Optional.ofNullable(getElement(element,wait)).ifPresent(objecto -> {

                focusElement(objecto);
                texto.set(objecto.getText());
                crearPaso(texto.get(),true, takeScreenShotReporte, takeScreenShotReporte);
            });

        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return texto.get();
    }

    public void mouseOver(By element, long waitSeg, Class<?> thisClass){
        try {
            WebElement eelemento = getElement(element,waitSeg);
            Optional.ofNullable(eelemento).ifPresent(elemento->{
                if (!elemento.isDisplayed()){
                    focusElement(elemento);
                }
                new  Actions(driver).moveToElement(eelemento).build().perform();
                //crearPaso(format(MESSAGE_CLICK, estiloLinkBold(nameObject)),true, createReport,takeCapture);
                //elemento.click();
            });
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    /** De una lista de elemento realiza click sobre indicado por el indice.
     * @param elements Lista de elemento que contiene el elemento visible en el cual se hará según el índice.
     * @param index Índice del elemento en el cual se hará click.
     * @param nameObject Nombre del elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @param takeCapture Permite tomar captura antes de hacer click en el elemento.
     * @param createReport Permite crear o no reporte de este proceso.
     */
    public void clickOn(List<WebElement>  elements, int index, String nameObject, Class<?> thisClass, boolean takeCapture, boolean createReport){
        try {
            WebElement elemento = elements.get(index);
            focusElement(elemento);
            new Actions(driver).moveToElement(elemento).perform();
            crearPaso(format(MESSAGE_CLICK, estiloLinkBold(nameObject)),true, createReport,takeCapture);
            elemento.click();
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    /** De una lista de elemento realiza click sobre el primer elemento visible.
     * @param element Elemento en el cual se hara click.
     * @param nameObject Nombre del elemento.
     * @param waitSeg Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @param takeCapture Permite tomar captura antes de hacer click en el elemento.
     * @param createReport Permite crear o no reporte de este proceso.
     */
    public void clickOns(By element, String nameObject, long waitSeg, Class<?> thisClass, boolean takeCapture, boolean createReport){
        try {
            Optional.ofNullable(getElementOnList(element, waitSeg)).ifPresent(objecto -> {
                if (!objecto.isDisplayed()){
                    focusElement(objecto);
                }
                new  Actions(driver).moveToElement(objecto).build().perform();
                crearPaso( format(MESSAGE_CLICK,  estiloLinkBold(nameObject)),true, createReport, takeCapture);
                objecto.click();
            });
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    /** Permite seleccionar un CheckBox o RadioButton.
     * @param radioCheckBox Radio Button o checkBox que se quiere seleccionar.
     * @param nombreElemento Nombre del elemento.
     * @param selecionar true para seleccionar , false para no interactuar con el elemento.
     * @param waitSecond Tiempo máximo que se esperara por el elemento.
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public void selectCheckBox(By radioCheckBox, String nombreElemento, boolean selecionar, long waitSecond, Class<?> clas, boolean crearReporte){
        try {
            if (selecionar){
                Optional.ofNullable(getElement( radioCheckBox, waitSecond)).ifPresent(button->{
                    if (!button.isDisplayed()){
                        focusElement(button);
                    }
                    button.click();
                });
                crearPaso("Se selecciono : " + nombreElemento,true, crearReporte,false);
            }
        }catch (Exception e){
            imprimirFallo(clas, e);
            logger.error(MESSAGE_ERROR, e);
        }
    }


    /** Permite seleccionar una opción en un combo.
     * @param combo Combo desde el cual se seleccionara la opción.
     * @param text Opción existente el combo la cual será seleccionada.
     * @param nombreElement Nombre del elemento.
     * @param waitSecond Tiempo máximo que se esperara por el elemento.
     * @param clas Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @param crearReporte Permite crear o no reporte de este proceso.
     */
    public void selectDropdownIBP(By combo,String text,String nombreElement, long waitSecond, Class<?> clas,boolean crearReporte){
        By option = By.xpath( format(XP_OPCION_COMBO, text) );

        try {
            if (!text.equalsIgnoreCase("NA")){
                getElement(combo, waitSecond);
                clickOn(combo);
                if (isElementVisibleNoException(TXT_BUSCAR,0)){
                    writeTextOn(TXT_BUSCAR, text);
                }

                crearPaso("Se desplego el combo " + nombreElement,true, crearReporte,false);
                getElement(option, 1);
                clickOn(option);
                crearPaso("Se selecciono "+text+" en combo "+nombreElement,true, crearReporte,false);
            }
        }catch (Exception e){
            imprimirFallo(clas, e);
        }
    }

//    public void selectDropdown2(By combo,String text,String nombreElement, long waitSecond, Class<?> clas,boolean crearReporte){
//        By opcionASelecionar = By.xpath("//p-dropdownitem//li/span[contains(text(),'"+text+"')]");
//
//        if (!text.equalsIgnoreCase("NA")){
//            try {
//                WebDriverWait espera = new WebDriverWait(driver, waitSecond);
//                espera.until(ExpectedConditions.visibilityOfElementLocated(combo));
//                clickOn(combo);
//
//                espera = new WebDriverWait(driver, 1);
//                espera.until(ExpectedConditions.visibilityOfElementLocated( opcionASelecionar ));
//                espera.until(ExpectedConditions.elementToBeClickable(opcionASelecionar));
//
//                clickOn( opcionASelecionar );
//
//                if (crearReporte) {
//                    //crearPaso(estiloFuenteTamanoH5("Se selecciono " + estiloLinkBold(valorOpcion) + " en combo: " + nombreElemento /*estiloColorFuenteNegro()*/), true);
//                }
//            } catch (NoSuchElementException e) {
//            }
//        }


//        By txtBuscar = By.xpath("//input[@autocomplete='off']");
//        By opcion = By.xpath("//p-dropdownitem//li/span[contains(text(),'"+text+"')]");
//
//        try {
//            if (!text.equalsIgnoreCase("NA")){
//                getElement(combo, waitSecond);
//                clickOn(combo);
//                if (isElementVisibleNoException(txtBuscar, 0)){
//                    writeTextOn(txtBuscar, text);
//                }
//
//                getElement(opcion, 1);
//
//                //new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOfElementLocated( opcion ));
//                clickOn( opcion );
//
//                crearPaso("Se desplego el combo " + nombreElement,true, crearReporte,false);
////                Optional.ofNullable( getElement(opcion, 1) ).ifPresent(objecto-> {
////                    new WebDriverWait(driver,2).until(ExpectedConditions.visibilityOf(objecto));
////                    objecto.click();
////                });
//                //clickOn(opcion);
//                crearPaso("Se selecciono "+text+" en combo "+nombreElement,true, crearReporte,false);
//            }
//        }catch (Exception e){
//            imprimirFallo(clas, e);
//        }
//    }

    public void selectDropdown(By combo,String text,String nombreElement, long waitSecond, Class<?> clas,boolean crearReporte){
        //By txtBuscar = By.xpath("//input[@autocomplete='off']");
        By opcion = By.xpath("//li[@role='option']/span[text()='"+text+"']");
        By option = By.xpath( format(XP_OPCION_COMBO, text) );

        try {
            if (!text.equalsIgnoreCase("NA")){
                getElement(combo, waitSecond);
                clickOn(combo);
                if (isElementVisibleNoException(TXT_BUSCAR, 0)){
                    writeTextOn(TXT_BUSCAR, text);
                }

                crearPaso("Se desplego el combo " + nombreElement,true, crearReporte,false);
                getElement(option, 1);
                clickOn(option);
                crearPaso("Se selecciono "+text+" en combo "+nombreElement,true, crearReporte,false);
            }
        }catch (Exception e){
            imprimirFallo(clas, e);
        }
    }

    /** Verifica si un elemento esta visible en la pagina, si esta visible retorna true, de lo contrario retorna false.
     * @param elemento Elemento el cual se esperara que este visible.
     * @param tiempoEsperaSegundo Tiempo máximo que se esperara por el elemento.
     * @return boolean
     */
    public boolean isElementVisibleNoException(By elemento, long tiempoEsperaSegundo){
        try {
            WebDriverWait espera = new WebDriverWait(BaseTest.getDriver(),tiempoEsperaSegundo);
            espera.until(ExpectedConditions.visibilityOfElementLocated(elemento));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean waitMilliSeconds(int waitMilliseconds){
        try {
            driver.manage().timeouts().implicitlyWait(waitMilliseconds, TimeUnit.MILLISECONDS);
            driver.findElement(By.xpath("//Espera")).click();
            //new WebDriverWait(driver, waitMilliseconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath("Espera")));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /** Verifica que un elemento que estaba visible en la pagina, se vuelva  invisible, para retornar true. Si el elemento nunca estuvo visible o si nunca se vuelve invisible retorna false.
     * @param element Elemento visible el cual se esperara que se vuelva invisible.
     * @param waitSeg Tiempo máximo que se esperara por el elemento.
     * @return boolean
     */
    public boolean isElementInvisibleNoException(By element, long waitSeg){
        try {
            WebDriverWait espera = new WebDriverWait(BaseTest.getDriver(),waitSeg);
            espera.until(ExpectedConditions.invisibilityOfElementLocated(element));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /** De una lista de elemento, retorna un webElement del primer elemento visible de la lista.
     * @param elemento Elementos que contienen el mismo identificador en la pagina.
     * @param waitSeg Tiempo máximo que se esperara por el elemento.
     * @return WebElement
     * @throws NoSuchElementException
     */
    private WebElement getElementOnList(By elemento, long waitSeg){
        WebElement element = null;
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSeg);
            List<WebElement> listWebElements = espera.until(ExpectedConditions.presenceOfElementLocated(elemento)).findElements(elemento);

            if (listWebElements.size()>1){
                for (int i = 0; i < listWebElements.size(); i++) {

                    if (listWebElements.get(i).isDisplayed()){
                        element = listWebElements.get(i); break;
                    }
                }
            }
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
            return element;
        }
        return element;
    }

    /** Retorna un webElement, si el elemento no esta presente en la pagina dispara una excepción.
     * @param elemento Elemento que se verificara que este presente en la pagina.
     * @param waitSecond Tiempo máximo que se esperara por el elemento.
     * @return WebElement
     * @throws NoSuchElementException
     */
    private WebElement getElement(By elemento, long waitSecond){
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            return  espera.until(ExpectedConditions.presenceOfElementLocated(elemento));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
            return null;
        }
    }

    private WebElement waitVisible(By element, long waitSecond){
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            return espera.until(ExpectedConditions.elementToBeClickable(element));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
            return null;
        }
    }

    /**
     * Este metodo espera a que un elemento presente en la pagina sea cliclable.
     * @param elemento Elemento por el cual se esperara.
     * @param waitSecond Tiempo de espera al elemento en segundos.
     * @return
     */
    private WebElement getElementVisibleClicable(By elemento, long waitSecond){
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            WebElement element1 = espera.until(ExpectedConditions.presenceOfElementLocated(elemento));
            element1 = espera.until(ExpectedConditions.elementToBeClickable(element1));

            return  element1;
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
            return null;
        }
    }

    private WebElement getElementVisible(By elemento, long waitSecond){
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);

            getElement(elemento, waitSecond);
             return espera.until(ExpectedConditions.visibilityOfElementLocated(elemento));

        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
            return null;
        }
    }

    private void getElementInvisibility(By elemento, long waitSecond){
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            WebElement elementExpeted = espera.until(ExpectedConditions.presenceOfElementLocated(elemento));
            espera.until(ExpectedConditions.visibilityOfElementLocated(elemento));
            espera.until(ExpectedConditions.invisibilityOfElementLocated(elemento));
            //espera.until(ExpectedConditions.visibilityOf(elementExpeted));
            //espera.until(ExpectedConditions.invisibilityOf(elementExpeted));
        }catch (NoSuchElementException ignored){

        }
    }

    /** Espera que mas de un elemento con el mismo identificador este presente en la pagina.
     * @param elements Elementos con el mismo identificador.
     * @param waitSeg Tiempo máximo que se esperara por el elemento.
     * @param thisClass Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @return lista de WebElement
     */
    public List<WebElement> getAllElements(By elements, long waitSeg, Class<?> thisClass){
        List<WebElement> lista = null;
        try {
            lista = new WebDriverWait(driver, waitSeg).until(ExpectedConditions.presenceOfElementLocated(elements)).findElements(elements);
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return lista;
    }


    /** Cuando se produce un error se encarga de detener el caso de prueba y toma una captura de pantalla actual.
     * @param clase Clase en la cual se esta interactuando con el elemento. enviar el método getClass();
     * @param exception Excepción generada.
     */
    private void imprimirFallo(Class<?> clase, Exception exception){
        StackTraceElement lineaError = new StackTraceElement("",exception.getStackTrace()[4].getMethodName(),clase.getSimpleName()+".java",exception.getStackTrace()[4].getLineNumber());

        java.util.logging.Logger loggers;
        loggers = getLogger("Log");

        String errorReporte = "Elemento no encontrado: " + obtenerStringElementoNoEncontado(exception) + "<br>Origen: " + lineaError.toString();
        String errorConsola = "Elemento no encontrado: " + obtenerStringElementoNoEncontado(exception) + "\n"+ "Origen Method: " + lineaError.toString();

        if (exception.toString().contains("System")){
            crearPaso("El elemento esta deshabilitado o otro elemento intercepto la acción.",false,true,true);
        }else {
            crearPaso( errorReporte,false,true,true);
        }
        loggers.log(SEVERE, errorConsola);
        new SkipException("Esqupiendo caso");
        Assert.fail("Este test ha fallado"); // Error
    }

    /** Formatea el texto cuando no se encuentra un elemento, para hacerlo mas entendible y lo retorna como cadena de texto.
     * @param exception Excepción generada en la interacción con un elemento de la pagina.
     * @return Texto excepción generada.
     */
    private String obtenerStringElementoNoEncontado(Exception exception){
        String mensajeDeError = exception.toString().substring(109);
        String[] lista = mensajeDeError.split("tried");
        return lista[0].substring(0, lista[0].length()-1);
    }

    /** Este método nos permite plasmar en el reporte una acción realizada, si fue exitosa, fallida o si se quiere o no tomar captura de dicho paso.
     * @param description Esta en el reporte será la descripción del paso realizado.
     * @param decision Define si el paso fue éxito o fallido. True = exitoso, False = fallido.
     * @param crearReporte Define si se creara reporte o no del paso realizado. True = creara el reporte, False = No creara reporte.
     * @param takeScreenShot Este parámetro sirve para tomar o no una captura del paso realizado.
     */
    public void crearPaso(String description, boolean decision, boolean crearReporte, boolean takeScreenShot){
        if (crearReporte){
            BaseTest.createStep(estiloFuenteTamanoH5(description) , decision, takeScreenShot);
        }
    }

    /** Da al texto la propiedad de un titulo h5.
     * <h4> Tamaño de fuente H4 </h4>
     * @param text Texto el cual se quiere colocar como titulo h5.
     * @return texto formateado con estilo titulo H5.
     */
    public String estiloFuenteTamanoH5(String text){
        return "<h5>" + text + "</h5>";
    }

    /** Da estilo link al texto y lo coloca en negrita.
     * @param texto Texto al cual se le aplicara el estilo.
     * @return texto con el estilo.
     */
    protected String estiloLinkBold(String texto){
        return "<b><a color = '#121815' href='#'>"+ texto +"</a></b>";
    }
}
