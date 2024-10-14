package accion;

import basetest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pages.BasePage;
import util.Utilidad;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.format;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

public class AccionPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AccionPage.class);
    private static final String MESSAGE_ERROR = "Error ";
    private static final String MESSAGE_CLICK_ACTION = "Se hizo click en %s: %s";


    public AccionPage(WebDriver webDriver) {
        super(webDriver);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.MILLISECONDS);
    }

//    public WebDriver getDriver() {
//        return driver;
//    }

    protected WebDriver getDriver(){
        return driver;
    }
    protected void navegarPagina(String url){
        driver.get(url);
    }

    protected By by(String format, Object... args){
        System.out.println(args.length);
        return By.xpath( String.format(format, args) );
    }



//    protected By xpath(String xpath){
//        return By.xpath(xpath);
//    }

    protected static By by(String xpath){
        return By.xpath(xpath);
    }

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
        Assert.fail("Este test ha fallado"); // Error
    }

    private String obtenerStringElementoNoEncontado(Exception exception){
        String mensajeDeError = exception.toString().substring(109);
        String[] lista = mensajeDeError.split("tried");
        return lista[0].substring(0, lista[0].length()-1);
    }


    // Acciones internas - Reutilizar
    private void focusElement(WebElement element){
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });", element);
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
    }






    // ACCIONES PAGE ⏬
    private WebElement waitElementVisible(By element, long waitSecond){
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            return espera.until(ExpectedConditions.elementToBeClickable(element));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
            return null;
        }
    }

    private List<WebElement> getListElement(By element, long waitSecond){
        List<WebElement> listaElementos = null;
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            listaElementos = espera.until(ExpectedConditions.elementToBeClickable(element)).findElements(element);
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
            return listaElementos;
        }
        return listaElementos;
    }

    private boolean waitElementNoClicable(By element, long waitSecond){
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            return espera.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
            return false;
        }
    }

    private void waitWebElementNoVisible(WebElement element, long waitSecond){
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            espera.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
    }

    /**
     * Verificar metodo porque esta dando un error en la interacion con el elemento cuando no existe: Origen Method: .newInstance(PODashboardPage.java:480)
     * @param element
     * @param waitSecond
     * @param thisClass
     */
    protected void waitElementVisible(By element, long waitSecond, Class<?> thisClass){
        try {
             Optional.ofNullable( waitElementVisible( element, waitSecond) ).ifPresent(this::focusElement);
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    protected boolean isElementVisible(By element, long waitSecond){
        AtomicBoolean visible = new AtomicBoolean(false);
        try {
            Optional.ofNullable( waitElementVisible(element,waitSecond) ).ifPresent( webElement -> {
                focusElement(webElement);
                visible.set(true);
            });
        }catch (Exception ignored){
        }
        return visible.get();
    }

    private void waitElementInvisibility(WebElement webElement, long waitSecond){
        try {

            new WebDriverWait(driver, waitSecond).until(ExpectedConditions.invisibilityOf(webElement));
            //new WebDriverWait(driver, waitSecond).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(webElement)));
            //new WebDriverWait(driver,waitSecond).until(ExpectedConditions.invisibilityOf(webElement) );
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
    }

    protected void esperarSegundos(long waitSeconds){
        try {
            new WebDriverWait(driver, waitSeconds).until(ExpectedConditions.visibilityOfElementLocated(by("/espera")));
        }catch (Exception ignored){
        }
    }

    public void waitElementNotVisibleOrNotClickCable(By element, long waitSecond, Class<?> thisClass){
        try {
            waitElementNoClicable(element, waitSecond);
        }catch (Exception e){
            imprimirFallo( thisClass, e );
        }
    }


    /** <h1>Listo.</h1>
     * Este método permite retornar el texto del elemento y tomar captura de pantalla al encontrar el mismo..
     * @param element
     * @param waitSecond
     * @param thisClass
     * @param takeScreenShotReport
     * @return
     */
    protected String getText(By element, long waitSecond, Class <?> thisClass, boolean takeScreenShotReport){
        AtomicReference<String> text = new AtomicReference<>("");
        try {
            Optional.ofNullable( waitElementVisible(element, waitSecond) ).ifPresent(webElement -> {
                focusElement( webElement );
                text.set( webElement.getText() );
                crearPaso( text.get(),true, takeScreenShotReport, takeScreenShotReport );
            });
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return text.get();
    }

    /**
     * <h1>Listo.</h1>
     * Este método permite retornar el texto del elemento.
     * @param element
     * @param waitSecond
     * @param thisClass
     * @return
     */
    protected String getText(By element, long waitSecond, Class <?> thisClass){
        AtomicReference<String> text = new AtomicReference<>("");
        try {
            Optional.ofNullable( waitElementVisible(element, waitSecond) ).ifPresent(webElement -> {
                focusElement( webElement );
                text.set( webElement.getText() );
            });
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return text.get();
    }


    /** <h1>Listo.</h1>
     * Permite escribir sobre un elemento o input. Si se envía el texto NA se omite la interacción con el elemento.
     * @param element
     * @param text
     * @param nameElement
     * @param waitSecond
     * @param thisClass
     * @param createReport
     */
    protected void writeOn( By element, String text, String nameElement, long waitSecond, Class<?> thisClass, boolean createReport){ // Arregar metodo if el texto es vacio.
        try {
            if (!text.equalsIgnoreCase("NA")){
                Optional.ofNullable( waitElementVisible( element, waitSecond ) ).ifPresent( webElement -> {
                    if (!webElement.isDisplayed()){
                        focusElement(webElement);
                    }
                    webElement.clear();
                    webElement.sendKeys(text);
                    if (text.equals("")){
                        crearPaso("Se envio un texto vacio " + text + " en el " + nameElement,true, createReport,false);
                    }else {
                        crearPaso("Se escribio el texto " + text + " en el campo " + nameElement,true, createReport,false);
                    }
                });
            }
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }


    /** <h1>Listo.</h1>
     * Este método permite realizar una captura de pantalla antes de hacer click sobre un elemento, opcionalmente puede
     * esperar a que el elemento deje de ser visible luego de haber realizado el click sobre el mismo.
     * @param element
     * @param typeElement
     * @param nameElement
     * @param waitSecond
     * @param waitInvisibility
     * @param thisClass
     * @param takeCapture
     * @param createReport
     */
    protected void clickOn(By element, String typeElement, String nameElement, long waitSecond, boolean waitInvisibility, Class <?> thisClass, boolean takeCapture, boolean createReport){
        try {

            Optional.ofNullable( waitElementVisible( element, waitSecond ) ).ifPresent( webElement ->{

                //System.out.println("Elemento presente y visible: " + nameElement);
                //if (!webElement.isDisplayed()){
                    focusElement( webElement );
                //System.out.println("Se hizo focus: " + nameElement);
                //}

                new Actions(driver).moveToElement(webElement).build().perform();

                //System.out.println("Movido sobre elemento: " + nameElement);
//                crearPaso(format( messageError, estiloLinkBold(nameElement)),true, createReport,takeCapture); // Agregar metodos faltantes
                crearPaso(format(MESSAGE_CLICK_ACTION, typeElement, nameElement),true, createReport,takeCapture);
                webElement.click();
                System.out.println("Se hizo el click : " + nameElement);
                if ( waitInvisibility ){
                    //waitWebElementNoVisible(webElement, waitSecond);
                    waitElementInvisibility( webElement, waitSecond );
                }
            });

        }catch (Exception e){
            System.out.println("Fallo en el click "  + nameElement);
            imprimirFallo(thisClass, e);
        }
    }

    /**
     * <h1>Listo</h1>
     * Retorna el primer {@link WebElement} visible de una lista de elementos presentes en la pagina.<br>
     * Si no hay elemento presente en la pagina retorna un {@link NoSuchElementException}
     * @param elements By que contiene una lista de elementos.
     * @param waitSecond Tiempo maximo que se esperara a que el elemento este presente.
     * @return {@link WebElement}
     */
    private WebElement waitListElementPresentAndReturnTheVisibleElement( By elements, long waitSecond ){
        WebElement result = null;
        try {

            List< WebElement > elementos = new WebDriverWait( driver, waitSecond ).until( ExpectedConditions.presenceOfElementLocated( elements )).findElements( elements );

            for ( WebElement element: elementos ){
                if ( element.isDisplayed() ){
                    result = element;
                    break;
                }
            }
        }catch (NoSuchElementException ignored){

        }
        return result;
    }

    /**
     * <h1>Listo. Pendiente colocar parametros. </h1>
     * Realiza click sobre el primer elemento visible de una lista de elemento en la pagina.
     * @param elements
     * @param typeElement
     * @param nameElement
     * @param waitSecond
     * @param thisClass
     * @param takeCapture
     * @param createReport
     */
    protected void clickOnVisible(By elements, String typeElement, String nameElement, long waitSecond, Class <?> thisClass, boolean takeCapture, boolean createReport){
        try {

            Optional.ofNullable( waitListElementPresentAndReturnTheVisibleElement( elements, waitSecond )).ifPresent( elemento -> {
                focusElement(elemento);
                elemento.click();
            });
            System.out.println("Se hizo click en el elemento doble");
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    protected By txtBuscarEnCombo = by("//p-dropdown//input[@autocomplete='off']");

    /**
     * <h1>Eliminar</h1>
     * @param opcion
     * @return
     */
    public By opcion(String opcion){
        return by("//p-dropdown //ul /p-dropdownitem//li/span[@class='ng-star-inserted' and contains(.,'%s')]",opcion);
    }


    public String selectItemCombo(By combo, String text, String nameElement, long waitSecond, Class <?> thisClass, boolean productoPresente, boolean crearReporte){
        AtomicReference<String> textReturnCombo = new AtomicReference<>("");

        String opciones = "//p-dropdown //ul /p-dropdownitem//li/span[@class='ng-star-inserted' and contains(.,'%s')]";
        By opcion = by(opciones,text);

        String xpathTotalElementos = "//p-dropdown //ul /p-dropdownitem";
        //By opcion = by(xpathTotalElementos + "//span[contains(.,'"+text+"')]");
        //String xpathOpcion = "//p-dropdownitem//li[@role='option']/span[contains(.,'%s')]";

        try {
            if (!text.equalsIgnoreCase("NA")){
                Optional.ofNullable( waitElementVisible(combo, waitSecond) ).ifPresent( webElement -> {   // Se despliega el combo
                    focusElement(webElement);
                    webElement.click(); // Se desplego el combo


                    if ( isElementVisible( txtBuscarEnCombo,0)){
                        Optional.ofNullable( waitElementVisible( txtBuscarEnCombo,0) ).ifPresent( WebElement :: sendKeys);
                    }


                    Optional.ofNullable( getListElement( by(opciones,text), 1 ) ).ifPresent(listaElementos -> {
                        System.out.println("Total de producto: " + listaElementos.size());
                        if (productoPresente){
                            if (listaElementos.size() == 1){
                                String visibleEnCombo = listaElementos.get(0).getText();

                                if (visibleEnCombo.equalsIgnoreCase("Nuevo beneficiario")){
                                    waitElementVisible(by("No estan cargando los beneficiario"),0);
                                }else if (visibleEnCombo.equalsIgnoreCase("No se encontraron registros")){
                                    waitElementVisible(by("No estan cargando los productos"),0);
                                }
                            }else {
                             Optional.ofNullable( waitElementVisible( opcion,0) ).ifPresent( producto -> {
                                 focusElement(producto);
                                 producto.click();
                                 waitElementInvisibility(producto, waitSecond);
                             });
                            }
                        }else {
                            textReturnCombo.set(listaElementos.get(0).getText());
                        }
                    });
                });
            }

        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return textReturnCombo.get();
    }

    protected List<WebElement> getListaElementos(By elements, long waitSecond, Class <?> thisClass){
        List<WebElement> listaElementos = null;
        try {
            listaElementos = getListElement(elements, waitSecond);
        }catch (Exception e){
            imprimirFallo( thisClass, e );
        }
        return listaElementos;
    }




//    protected void selectItemCombo2(By combo, By text, String nameElement, long waitSecond, Class <?> thisClass, boolean productoPresente, boolean crearReporte){
//        try {
//            waitElementVisible(combo, waitSecond).click();
//
//        }catch (Exception e){
//
//        }
//    }


    private final String XP_OPCION_COMBO = "//p-dropdownitem//li[@role='option']/span[contains(.,'%s')]";
    private final By TXT_BUSCAR = By.xpath("//input[@autocomplete='off' and @class[contains(.,'p-dropdown-filter')]]") ;

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
                waitElementVisible(combo, waitSecond);
                clickOn(combo);
                if (isElementVisible(TXT_BUSCAR,0)){
                    writeTextOn(TXT_BUSCAR, text);
                }

                crearPaso("Se desplego el combo " + nombreElement,true, crearReporte,false);
                waitElementVisible(option, 1);
                clickOn(option);
                crearPaso("Se selecciono "+text+" en combo "+nombreElement,true, crearReporte,false);
            }
        }catch (Exception e){
            imprimirFallo(clas, e);
        }
    }


    /**
     * <h1>Listo. <br>Permite seleccionar una una opcion dentro del combo.</h1>
     * @param combo
     * @param text
     * @param nameElement
     * @param waitSecond
     * @param thisClass
     * @param productoPresente
     * @param crearReporte
     * @return
     */
    public String selectItemComboFinales(By combo, String text, String nameElement, long waitSecond, Class <?> thisClass, boolean productoPresente, boolean crearReporte){
        AtomicReference<String> textReturnCombo = new AtomicReference<>("");
        String textoCombo = "";

        String opciones = "//p-dropdown //ul /p-dropdownitem//li/span[@class='ng-star-inserted' and contains(.,'%s')]";
        By opcion = by(opciones,text);

        try {
            if (!text.equalsIgnoreCase("NA")){

                    WebElement comboWebElement = waitElementVisible(combo, waitSecond);
                    focusElement(comboWebElement);
                    comboWebElement.click();

                    if ( isElementVisible( txtBuscarEnCombo,0)){
                        WebElement element = waitElementVisible(txtBuscarEnCombo, 3);
                        element.sendKeys(text);
                        //Optional.ofNullable( waitElementVisible( txtBuscarEnCombo,3) ).ifPresent( WebElement :: sendKeys);
                    }
                System.out.println("Se hizo click en el combo: " + nameElement);

                    List<WebElement> listaElementos = getListElement( by(opciones,""), 1 );

                        if ( productoPresente ){
                            if (listaElementos.size() == 1){
                                String visibleEnCombo = listaElementos.get(0).getText();

                                if (visibleEnCombo.equalsIgnoreCase("Nuevo beneficiario")){
                                    waitElementVisible(by("No estan cargando los beneficiario"),0);
                                }else if (visibleEnCombo.equalsIgnoreCase("No se encontraron registros")){
                                    waitElementVisible(by("No estan cargando los productos"),0);
                                }else {
                                    if (!visibleEnCombo.contains(text)){
                                        crearPaso("El " + nameElement + ": " + text + " no esta cargado.",false, crearReporte, crearReporte);
                                        waitElementVisible(opcion, 1,getClass());
                                    }else {
                                        focusElement(listaElementos.get(0));
                                        listaElementos.get(0).click();
                                    }
                                   // crearPaso("","","");
                                }

                            }else {

                                WebElement producto =    waitElementVisible( opcion,1);
                                focusElement(producto);
                                producto.click();
                                waitElementInvisibility(producto, waitSecond);
                                crearPaso("Se selecciono " + text + ", en el comobo: " + nameElement + "...",true, crearReporte, false);

                            }
                        }else {
                            textoCombo = listaElementos.get(0).getText();
                            //textReturnCombo.set(listaElementos.get(0).getText());
                        }
            }

        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return textoCombo;
    }

    private WebElement getElement(By elemento, long waitSecond){
        try {
            WebDriverWait espera = new WebDriverWait(driver, waitSecond);
            return  espera.until(ExpectedConditions.presenceOfElementLocated(elemento));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
            return null;
        }
    }

    public boolean isElementVisibleNoException(By elemento, long tiempoEsperaSegundo){
        try {
            WebDriverWait espera = new WebDriverWait(BaseTest.getDriver(),tiempoEsperaSegundo);
            espera.until(ExpectedConditions.visibilityOfElementLocated(elemento));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public void selectDropdownIBP2(By combo,String text,String nombreElement, long waitSecond, Class<?> clas,boolean crearReporte){
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


    /**
     * <h1>Limpiar metodo.</h1>
     * Permite realizar click sobre un combo ({@link WebElement}) para desplegarlo y seleccionar una opción en el mismo.
     * @param combo
     * @param text
     * @param nameElement
     * @param waitSecond
     * @param thisClass
     * @param productoPresente
     * @param crearReporte
     * @return
     */
    public String selectItemComboFinales(WebElement combo, String text, String nameElement, long waitSecond, Class <?> thisClass, boolean productoPresente, boolean crearReporte){

        String textoCombo = "";
        String opciones = "//p-dropdown //ul /p-dropdownitem//li/span[@class='ng-star-inserted' and contains(.,'%s')]";
        By opcion = by(opciones,text);

        try {
            if (!text.equalsIgnoreCase("NA")){

                focusElement(combo);
                combo.click();

                if ( isElementVisible( txtBuscarEnCombo,0)){
                    Optional.ofNullable( waitElementVisible( txtBuscarEnCombo,0) ).ifPresent( WebElement :: sendKeys);
                }

                List<WebElement> listaElementos = getListElement( by(opciones,""), 1 );

                if ( productoPresente ){
                    if (listaElementos.size() == 1){
                        String visibleEnCombo = listaElementos.get(0).getText();

                        if (visibleEnCombo.equalsIgnoreCase("Nuevo beneficiario")){
                            waitElementVisible(by("No estan cargando los beneficiario"),0);
                        }else if (visibleEnCombo.equalsIgnoreCase("No se encontraron registros")){
                            waitElementVisible(by("No estan cargando los productos"),0);
                        }else {
                            if (!visibleEnCombo.contains(text)){
                                crearPaso("El " + nameElement + ": " + text + " no esta cargado.",false, crearReporte, crearReporte);
                                waitElementVisible(opcion, 0,getClass());
                            }else {
                                listaElementos.get(0).click();
                            }
                            // crearPaso("","","");
                        }

                    }else {

                        WebElement producto =    waitElementVisible( opcion,1);
                        focusElement(producto);
                        producto.click();
                        waitElementInvisibility(producto, waitSecond);
                        crearPaso("Se selecciono " + text + ", en el comobo: " + nameElement + "...",true, crearReporte, false);

                    }
                }else {
                    textoCombo = listaElementos.get(0).getText();
                    //textReturnCombo.set(listaElementos.get(0).getText());
                }
            }

        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return textoCombo;
    }



    public String selectItemComboFinal(By combo, By text, String nameElement, long waitSecond, Class <?> thisClass, boolean productoPresente, boolean crearReporte){
        AtomicReference<String> textReturnCombo = new AtomicReference<>("");

        String opciones = "//p-dropdown //ul /p-dropdownitem//li/span[@class='ng-star-inserted' and contains(.,'%s')]";
        //By opcion = by(opciones,text);


        //String texto = Utilidad.getTextoEntreParentesis()

        String xpathTotalElementos = "//p-dropdown //ul /p-dropdownitem";
        //By opcion = by(xpathTotalElementos + "//span[contains(.,'"+text+"')]");
        //String xpathOpcion = "//p-dropdownitem//li[@role='option']/span[contains(.,'%s')]";

        String texto = Utilidad.extraerTextoDe(text.toString(), ",'","')");

        try {
            if (!texto.equalsIgnoreCase("NA")){
                Optional.ofNullable( waitElementVisible(combo, waitSecond) ).ifPresent( webElement -> {   // Se despliega el combo
                    focusElement(webElement);
                    webElement.click(); // Se desplego el combo


                    if ( isElementVisible( txtBuscarEnCombo,0)){
                        Optional.ofNullable( waitElementVisible( txtBuscarEnCombo,0) ).ifPresent( WebElement :: sendKeys);
                    }

                    Optional.ofNullable( getListElement( by(opciones,""), 0 ) ).ifPresent(listaElementos -> {
                        System.out.println("Total de producto: " + listaElementos.size());
                        if (productoPresente){
                            if (listaElementos.size() == 1){
                                String visibleEnCombo = listaElementos.get(0).getText();

                                if (visibleEnCombo.equalsIgnoreCase("Nuevo beneficiario")){
                                    waitElementVisible(by("No estan cargando los beneficiario"),0);
                                }else if (visibleEnCombo.equalsIgnoreCase("No se encontraron registros")){
                                    waitElementVisible(by("No estan cargando los productos"),0);
                                }
                            }else {
                                WebElement element = waitElementVisible( text,0);
//                                Optional.ofNullable( waitElementVisible( text,1) ).ifPresent( producto -> {
//                                    //focusElement(producto);
//                                    focusElement(producto);
//                                    producto.click();
//                                    //waitElementInvisibility(producto, waitSecond);
//                                });
                            }
                        }else {
                            textReturnCombo.set(listaElementos.get(0).getText());
                        }
                    });
                });
            }

        }catch (Exception e){
            System.out.println(e);
            imprimirFallo(thisClass, e);
        }
        return textReturnCombo.get();
    }
    public String selectItemCombo2(By combo, By text, String nameElement, long waitSecond, Class <?> thisClass, boolean productoPresente, boolean crearReporte){
        AtomicReference<String> textReturnCombo = new AtomicReference<>("");

        String opciones = "//p-dropdown //ul /p-dropdownitem//li/span[@class='ng-star-inserted' and contains(.,'%s')]";
        //By opcion = by(opciones,text);


        //String texto = Utilidad.getTextoEntreParentesis()

        String xpathTotalElementos = "//p-dropdown //ul /p-dropdownitem";
        //By opcion = by(xpathTotalElementos + "//span[contains(.,'"+text+"')]");
        //String xpathOpcion = "//p-dropdownitem//li[@role='option']/span[contains(.,'%s')]";

        String texto = Utilidad.extraerTextoDe(text.toString(), ",'","')");

        try {
            if (!texto.equalsIgnoreCase("NA")){
                Optional.ofNullable( waitElementVisible(combo, waitSecond) ).ifPresent( webElement -> {   // Se despliega el combo
                    focusElement(webElement);
                    webElement.click(); // Se desplego el combo


                    if ( isElementVisible( txtBuscarEnCombo,0)){
                        Optional.ofNullable( waitElementVisible( txtBuscarEnCombo,0) ).ifPresent( WebElement :: sendKeys);
                    }

                    Optional.ofNullable( getListElement( by(opciones,""), 0 ) ).ifPresent(listaElementos -> {
                        System.out.println("Total de producto: " + listaElementos.size());
                        if (productoPresente){
                            if (listaElementos.size() == 1){
                                String visibleEnCombo = listaElementos.get(0).getText();

                                if (visibleEnCombo.equalsIgnoreCase("Nuevo beneficiario")){
                                    waitElementVisible(by("No estan cargando los beneficiario"),0);
                                }else if (visibleEnCombo.equalsIgnoreCase("No se encontraron registros")){
                                    waitElementVisible(by("No estan cargando los productos"),0);
                                }
                            }else {
                                WebElement element = waitElementVisible( text,0);
//                                Optional.ofNullable( waitElementVisible( text,1) ).ifPresent( producto -> {
//                                    //focusElement(producto);
//                                    focusElement(producto);
//                                    producto.click();
//                                    //waitElementInvisibility(producto, waitSecond);
//                                });
                            }
                        }else {
                            textReturnCombo.set(listaElementos.get(0).getText());
                        }
                    });
                });
            }

        }catch (Exception e){
            System.out.println(e);
            imprimirFallo(thisClass, e);
        }
        return textReturnCombo.get();
    }




    protected void clickOn(By element, long waitSecond, Class <?> thisClass){
        try {

            Optional.ofNullable( waitElementVisible( element, waitSecond ) ).ifPresent(webElement->{
                focusElement( webElement );

                new Actions(driver).moveToElement(webElement).build().perform();
//                crearPaso(format( messageError, estiloLinkBold(nameElement)),true, createReport,takeCapture); // Agregar metodos faltantes
                webElement.click();
            });

        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    /**
     * Este método permite realizar click sobre un elemento de una lista de elementos, el cual contenga el texto
     * enviado por parámetro {@link String}( textContainsElement ).
     *
     * @param listElementos
     * @param textContainsElement
     * @param typeElement
     * @param waitSecond
     * @param thisClass
     * @param crearReporte
     * @return
     */
    protected boolean clicnOnElementContainsText(By listElementos, String textContainsElement, String typeElement, long waitSecond, Class <?> thisClass, boolean crearReporte){
        AtomicBoolean presente = new AtomicBoolean(false);
        try {
            Optional.ofNullable( getListElement( listElementos, waitSecond) ).ifPresent( listWebElements -> {
                for (WebElement webElement : listWebElements) {
                    System.out.println(webElement.getText());
                    if (webElement.getText().contains(textContainsElement)) {
                        presente.set(true);
                        //focusElement(webElement);
                        focusElement(webElement);

                        new  Actions(driver).moveToElement(webElement).build().perform();

                        crearPaso(format(MESSAGE_CLICK_ACTION, typeElement, textContainsElement),true, crearReporte, crearReporte);
                        webElement.click();
                        break;
                    } else {
                        presente.set(false);
                    }
                }
            });
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return presente.get();
    }

    /**<h1>Listo. Pendeinte documentar.</h1>
     * Este método retorna del elemento el texto que tiene el atributo enviado por parámetro.
     * @param element
     * @param attribute
     * @param waitSecond
     * @param thisClass
     * @return
     */
    protected String getAttributeValue(By element, String attribute, long waitSecond, Class <?> thisClass ){
        AtomicReference<String> atributo = new AtomicReference<>("");
        try {
            Optional.ofNullable( waitElementVisible( element, waitSecond )).ifPresent( webElement -> {
                //if ( !webElement.isDisplayed() ){
                    focusElement( webElement );
                //}
                atributo.set( webElement.getAttribute( attribute ) );
            });
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return atributo.get();
    }


    // ACCIONES PAGE ⏫

    // REPORTE

    protected void crearPaso(String description, boolean decision, boolean crearReporte, boolean takeScreenShot){
        if (crearReporte){
            // BaseTest.createStep(estiloFuenteTamanoH5(description) , decision, takeScreenShot); Falta crear este metodo en esta clase.
            BaseTest.createStep( description, decision, takeScreenShot);
        }
    }
}
