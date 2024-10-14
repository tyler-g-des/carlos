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

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

public class AccionMetodos extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AccionMetodos.class);
    private static final String MESSAGE_ERROR = "Error ";
    private final By txtBuscar = By.xpath("//div[@class[contains(.,'dropdown-panel')] and //ul[@role='listbox']]//input");


    public AccionMetodos(WebDriver webDriver) {
        super(webDriver);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.MILLISECONDS);
    }

    public static By by(String xpath){
        return By.xpath(xpath);
    }

    public By by(String format, Object... args){
        System.out.println(args.length);
        return By.xpath( String.format(format, args) );
    }

    public void capturarPanalla(){
        BaseTest.createStep("Capturar pantalla",true,true);
    }


    // 👇 METODOS PRIVADOS 👇

    /**
     * Este metodo se encarga de enfocar el elemento al centro de la pantalla.
     * @param element
     */
    private void focusElement(WebElement element){
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });", element);
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
    }

    /**
     * <h1>Listo. No tocar</h1>
     * @param webElement
     * @param color red <br>blue <br>green <br>black <br>yellow <br>orange <br>purple <br>pink <br>gray. <br><br>
     *              Colores Hexadecimal = #00FF00 <br>
     *              Colores RGB = rgb(0, 0, 255) <br>
     *              Colores RGBA = rgba(255, 165, 0, 0.7) <br>
     *              Colores HSL = hsl(240, 100%, 50%) <br>
     */
    private void mouseHover(WebElement webElement, String color){
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid " + color + "'", webElement);
            new Actions( driver ).moveToElement( webElement ).build().perform();
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
    }

    private void mouseHover(WebElement webElement){
        try {
            new Actions( driver ).moveToElement( webElement ).build().perform();
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
    }


//    private void setBorderColor(WebElement webElement, String color){
//        try {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid " + color + "'", webElement);
//        }catch (NoSuchElementException e){
//            logger.error(MESSAGE_ERROR, e);
//        }
//    }

//    private void setPurpleBorderColor(WebElement webElement){
//        setBorderColor(webElement, "blue"); // purple
//    }

    /**
     * Este metodo se encarga de esperar a que el elemento este presente en la pagina. Si el elemento no llegar a estar
     * peresente, entonces disparara un {@link NoSuchElementException}.
     *
     * @param element
     * @param waitSeg
     * @return {@link WebElement}
     */
    private WebElement waitPresent(By element, long waitSeg){
        WebElement element1 = null;
        try {
             element1 = new WebDriverWait(driver,waitSeg).until(ExpectedConditions.presenceOfElementLocated(element));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
        return element1;
    }

    private void waitNotPresent(By element, long waitSeg){
        try {
            new WebDriverWait(driver,waitSeg).until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(element)));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
    }

    /**
     *
     * @param webElement
     * @param waitSeg
     * @return
     */
    private WebElement waitVisible(WebElement webElement, long waitSeg){
        WebElement element1 = null;
        try {
            element1 = new WebDriverWait(driver,waitSeg).until(ExpectedConditions.visibilityOf(webElement));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
        return element1;
    }

    /**
     *
     * @param webElement
     * @param waitSeg
     * @return
     */
    private WebElement waitClickable(WebElement webElement, long waitSeg){
        WebElement element1 = null;
        try {
            element1 = new WebDriverWait(driver,waitSeg).until(ExpectedConditions.elementToBeClickable(webElement));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
        return element1;
    }

    /**
     *
     * @param webElement
     * @param waitSeg
     */
    private void waitInvisible(WebElement webElement, long waitSeg){
        try {
            new WebDriverWait(driver, waitSeg).until(ExpectedConditions.invisibilityOf(webElement));
        }catch (NoSuchElementException e){
            logger.error(MESSAGE_ERROR, e);
        }
    }

    // 👆 METODOS PRIVADOS ⏪ 👆






    // 👇 ACCCIONES PAGES ⏪ 👇

    protected void marcarObjectoPresenteVisble(By element, long waitSeg, Class <?> thisClass, String mensaje,boolean crearReporte){
        try {
            WebElement webElement = waitVisible(waitPresent(element, waitSeg), waitSeg);
            focusElement(webElement);
            mouseHover( webElement,"blue" );
            BaseTest.createStep( mensaje, true, crearReporte);
        }catch (Exception e){
            logger.error(MESSAGE_ERROR, e);
            imprimirFallo(thisClass, e);
        }
    }

    public void selectItemCombo(By combo, String text, String nameElement, long waitSeg, Class <?> thisClass, boolean crearReporte){
        try {
            if (!text.equalsIgnoreCase("NA")){

                By opcion = By.xpath("//ul[@role='listbox']/p-dropdownitem/li/span[@class='ng-star-inserted' and contains(.,'"+text+"')]");
                //By opcion = By.xpath("//p-dropdown //ul /p-dropdownitem//li/span[@class='ng-star-inserted' and contains(.,'"+text+"')]");

                WebElement elemento = waitPresent(combo, waitSeg);
                focusElement( elemento );
                elemento = waitClickable( elemento, waitSeg);
                Optional.ofNullable(elemento).ifPresent(WebElement::click);

                if (isElementPresent( txtBuscar,0)){
                    WebElement buscar =  waitPresent( txtBuscar, 1);
                    buscar = waitClickable( buscar, 1);
                    Optional.ofNullable( buscar ).ifPresent(buscqueda -> buscqueda.sendKeys(text));
                }

                WebElement opc = waitPresent(opcion, 1);
                focusElement(opc);
                Optional.ofNullable(opc).ifPresent(WebElement::click);
                if (crearReporte){
                    BaseTest.createStep("Se selecciono " + text + " en el combo: " + nameElement,true,false);
                }

            }
        }catch (Exception e){
            logger.error(MESSAGE_ERROR, e);
            imprimirFallo(thisClass, e);
        }
    }


    /**
     * <h1>Listo. No tocar</h1>
     * @param by
     * @param type
     * @param name
     * @param waitSeg
     * @param thisClass
     * @param takeScreenShot
     * @param createReport
     */
    public void clickOn(By by, String type, String name, long waitSeg, Class <?> thisClass, boolean takeScreenShot, boolean createReport){
        try {
            WebElement elemento = waitPresent( by, waitSeg );
            elemento = waitClickable(elemento, waitSeg);
            focusElement(elemento);
            mouseHover(elemento,"blue");
            if (createReport){
                BaseTest.createStep("Se hizo click en el " + type + " " + name,true, takeScreenShot );
            }
            Optional.ofNullable(elemento).ifPresent( WebElement::click);
        }catch (Exception e){
            imprimirFallo(thisClass, e);
            logger.error(MESSAGE_ERROR, e);
            logger.error(thisClass.getSimpleName());
        }
    }

    public void clickOn(By by, String type, String name, long waitSeg, Class <?> thisClass, boolean createReport){
        try {
            WebElement elemento = waitPresent( by, waitSeg );
            focusElement(elemento);
            elemento = waitClickable(elemento, waitSeg);
            if (createReport){
                BaseTest.createStep("Se hizo click en el " + type + " " + name,true,false);
            }

            Optional.ofNullable(elemento).ifPresent( WebElement::click);
        }catch (Exception e){
            imprimirFallo(thisClass, e);
            logger.error(MESSAGE_ERROR, e);
            logger.error(thisClass.getSimpleName());
        }
    }

    /**
     * <h1>Listo. No tocar</h1>
     * @param by
     * @param type
     * @param name
     * @param waitSeg
     * @param thisClass
     * @param waitInvisible
     * @param waitInvisibleSeg
     * @param takeScreenShot
     * @param createReport
     */
    public void clickOn(By by, String type, String name, long waitSeg, Class <?> thisClass, boolean waitInvisible, long waitInvisibleSeg, boolean takeScreenShot, boolean createReport){
        try {
            WebElement elemento = waitPresent( by, waitSeg );
            elemento = waitClickable( elemento, waitSeg );
            focusElement( elemento );



            if (createReport){
                mouseHover(elemento, "rgba(255, 0, 0, 0.5)");
                //setPurpleBorderColor(elemento);
                //new Actions( driver ).moveToElement( elemento ).build().perform();

                BaseTest.createStep("Se hizo click en el " + type + " " + name,true, takeScreenShot );
            }

            if ( elemento != null){
                elemento.click();
            }

            if (waitInvisible){
                waitInvisible( elemento, waitInvisibleSeg );
            }
        }catch (Exception e){
            imprimirFallo( thisClass, e );
            logger.error( MESSAGE_ERROR, e );
            logger.error(thisClass.getSimpleName());
        }
    }

    /**
     * <h1>Listo. No tocar</h1>
     * @param by
     * @param text
     * @param nameElement
     * @param waitSeg
     * @param thisClass
     * @param crearReporte
     */
    public void writeOn(By by, String text, String nameElement, long waitSeg, Class <?> thisClass, boolean crearReporte){
        try {
            if (!text.equalsIgnoreCase("NA")){

                WebElement input = waitPresent( by, waitSeg );
                focusElement( input );
                input = waitClickable( input, waitSeg );
                Optional.ofNullable( input ).ifPresent( campo -> {
                    campo.clear();
                    campo.sendKeys(text);
                } );
                if (crearReporte){
                    BaseTest.createStep("Se escribio el texto: " + text + " en el campo: " + nameElement,true,false);
                }
            }
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    /**
     * <h1>Listo. No Tomar</h1>
     * @param by
     * @param text
     * @param nameElement
     * @param waitSeg
     * @param thisClass
     * @param crearReporte
     */
    public void writeMaskedOn(By by, String text, String nameElement, long waitSeg, Class <?> thisClass, boolean crearReporte){
        try {
            if (!text.equalsIgnoreCase("NA")){

                WebElement input = waitPresent( by, waitSeg );
                focusElement( input );
                input = waitClickable( input, waitSeg );
                Optional.ofNullable( input ).ifPresent( campo -> {
                    campo.clear();
                    campo.sendKeys(text);
                });

                String enmascarado = "";
                for (int i = 0; i < text.length(); i++) {
                    enmascarado = enmascarado + "x";
                }

                if (crearReporte){
                    BaseTest.createStep("Se escribio el texto: " + enmascarado + " en el campo: " + nameElement,true,false);
                }
            }
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    public String getText(By by, long waitSeg, Class <?> thisClass){

        AtomicReference<String> text = new AtomicReference<>("");

        try {
            WebElement element = waitPresent( by, waitSeg );
            focusElement( element );
            element = waitVisible( element, waitSeg );

            Optional.ofNullable( element ).ifPresent( webElement -> text.set( webElement.getText() ));

        }catch (Exception e){
            imprimirFallo( thisClass, e );
        }
        return text.get();
    }

    public String getText(By by, long waitSeg, Class <?> thisClass, boolean takeScreenShot){
        AtomicReference<String> text = new AtomicReference<>("");

        try {
            WebElement element = waitPresent( by, waitSeg );
            //focusElement( element );
            element = waitVisible( element, waitSeg);
            focusElement( element );

            Optional.ofNullable( element ).ifPresent( webElement -> {
                text.set( webElement.getText() );
                mouseHover(webElement);
                if ( takeScreenShot ){
                    BaseTest.createStep( text.get(),true, takeScreenShot );
                }
            });
        }catch (Exception e){
            imprimirFallo( thisClass, e );
        }
        return text.get();
    }

    public String getAttribute(By by, long waitSeg, String attribute, Class <?> thisClass){
        AtomicReference<String> atributo = new AtomicReference<>("");
        try {
            Optional.ofNullable( waitPresent(by, waitSeg) ).ifPresent( elemento -> atributo.set(elemento.getAttribute(attribute)));
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
        return atributo.get();
    }

    public void waitElementVisible(By by, long waitSeg, Class <?> thisClass){
        try {
            WebElement element = waitPresent( by, waitSeg );
            focusElement( element );
            waitVisible( element, waitSeg );
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    // 👆 ACCIONES PAGES⏪ 👆







    // DESICIONES ⏪

    public boolean isElementPresent(By by, long waitSeg){
        AtomicBoolean presente = new AtomicBoolean(false);
        try {
           Optional.ofNullable(waitPresent(by, waitSeg)).ifPresent(si-> presente.set(si.isDisplayed()));
        }catch (Exception e){
            logger.error(MESSAGE_ERROR, e);
        }
        return presente.get();
    }




    // ESPERAS ⏪

    public void waitElementNoPresent(By by, long waitPresent, long waitNoPresente){
        try {
            waitPresent(by, waitPresent);
            waitNotPresent(by, waitNoPresente);
        }catch (Exception e){

        }
    }

    public void waitVisibleNoVisible(By by, long waitVisible, long waitInvisible){
        try {
             WebElement element = waitVisible(waitPresent(by, waitVisible), waitVisible);
             waitInvisible(element, waitInvisible);
        }catch (Exception e){

        }
    }
    public void waitElementInvisible(By by, long waitPresentSeg, long waitInvisibleSeg, Class <?> thisClass){
        try {
            WebElement elementVisible = waitPresent( by, waitPresentSeg);
            waitInvisible(elementVisible, waitInvisibleSeg);

        }catch (Exception e){
            logger.error(MESSAGE_ERROR,  e); // Meotod imprimir
            logger.error(thisClass.getSimpleName());  // Meotod imprimir
        }
    }

    /**
     * Class que contains(.,'checked')
     * @param by
     * @param name
     * @param waitElement
     * @param select
     * @param thisClass
     * @param crearReporte
     */
    public void selectRadioButon(By by, String name, long waitElement, boolean select, Class <?> thisClass, boolean crearReporte){
        try {
            WebElement element = waitPresent( by, waitElement );
            element = waitClickable( element, waitElement );
            focusElement( element );
            Optional.ofNullable( element ).ifPresent(webElement -> {
                boolean isSelected = webElement.getAttribute("class").contains("checked");

                if (select){
                    if (! isSelected){
                        webElement.click();
                        BaseTest.createStep("Se selecciono el radio button " + name, true, false);

                    }
                }else if (! select){
                    if (isSelected){
                        webElement.click();
                          BaseTest.createStep("Se deseleccino el radio button " + name, true, false);
                    }
                }

//                if (! isSelected.equals(select)){
//                    webElement.click();
//                }
//                if (crearReporte){
//                    if (! isSelected.equals(select)){
//                        if (select){
//                            BaseTest.createStep("Se selecciono el radio button " + name, true, false);
//                        }else {
//                            BaseTest.createStep("Se deseleccino el radio button " + name, true, false);
//                        }
//                    }
//                }
            });

        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    public void selectCheckBox(By by, String name, long waitElement, boolean select, Class <?> thisClass, boolean crearReporte){
        try {
            WebElement element = waitPresent( by, waitElement );
            element = waitClickable( element, waitElement );
            focusElement( element );
            Optional.ofNullable( element ).ifPresent(webElement -> {
                String estado = webElement.getAttribute("class");

                if (select){
                    if (! estado.contains("checked")){
                        webElement.click();
                        if (crearReporte){
                            BaseTest.createStep("Se selecciono el radio button " + name, true, false);
                        }

                    }
                }else {
                    if (estado.contains("checked")){
                        webElement.click();
                        if (crearReporte){
                            BaseTest.createStep("Se deseleccino el radio button " + name, true, false);
                        }
                    }
                }
            });

        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }

    public void selectCheckBox(By by, String name, long waitElement, Object seleccionar, Class <?> thisClass, boolean crearReporte){
        try {
            if (seleccionar != null){

                WebElement element = waitPresent( by, waitElement );
                element = waitClickable( element, waitElement );
                focusElement( element );
                Optional.ofNullable( element ).ifPresent(webElement -> {

                    String estado = webElement.getAttribute("class");

                    boolean select = (boolean) seleccionar;

                    if (select){
                        if (! estado.contains("checked")){
                            webElement.click();
                            if (crearReporte){
                                BaseTest.createStep("Se selecciono el radio button " + name, true, false);
                            }

                        }
                    }else {
                        if (estado.contains("checked")){
                            webElement.click();
                            if (crearReporte){
                                BaseTest.createStep("Se deseleccino el radio button " + name, true, false);
                            }
                        }
                    }
                });
            }
        }catch (Exception e){
            imprimirFallo(thisClass, e);
        }
    }











    // IMPRIMIR ERROR EN REPORTE ✔✔
    private void imprimirFallo(Class<?> clase, Exception exception){
        StackTraceElement lineaError = new StackTraceElement("",exception.getStackTrace()[4].getMethodName(),clase.getSimpleName()+".java",exception.getStackTrace()[4].getLineNumber());

        java.util.logging.Logger loggers;
        loggers = getLogger("Log");

        String errorReporte = "Elemento no encontrado: " + obtenerStringElementoNoEncontado(exception) + "<br>Origen: " + lineaError.toString();
        String errorConsola = "Elemento no encontrado: " + obtenerStringElementoNoEncontado(exception) + "\n"+ "Origen Method: " + lineaError.toString();

        if (exception.toString().contains("System")){
            BaseTest.createStep("El elemento esta deshabilitado o otro elemento intercepto la acción.",false,true);
        }else {
            BaseTest.createStep( errorReporte,false,true);
        }
        loggers.log(SEVERE, errorConsola);
        Assert.fail("Este test ha fallado"); // Error
    }
    private String obtenerStringElementoNoEncontado(Exception exception){
        String mensajeDeError = exception.toString().substring(109);
        String[] lista = mensajeDeError.split("tried");
        return lista[0].substring(0, lista[0].length()-1);
    }

    public void esperar(long waitSecons){
        try {
            new WebDriverWait(driver,waitSecons).until(ExpectedConditions.presenceOfElementLocated(By.xpath("Espera")));
        }catch (Exception e){

        }
    }


}
