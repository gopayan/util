package es.ja.csalud.sas.oca.utilidades;
import es.ja.csalud.sas.oca.exception.UtilException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;



/**
 * Clase UtilSelenium
 *
 */

public class UtilSelenium extends BasePrueba {

    // VALORES PREDETERMINADOS PARA LANZAR TEST EN SELENIUM GRID REMOTO
    private static final UtilPropiedades pUtil = UtilPropiedades.getInstance("jira.properties");
    private static final String DEFAULT_URLHUB = "http://sg-hub:4444/";
    private static final String BROWSER = pUtil.getValorKey("browser");
    private static final String DRIVERCHROME = "driverchrome";
    private static final String DRIVERFIREFOX = "driverfirefox";
    private static final String DRIVERIE = "driverie";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String IEXPLORER = "internet explorer";
    private static final String EDGE = "edge";
    private static final String GUIONES = "------------";
    private static final String ERROR = " - ERROR";
    private static final String ELEMENTOPADRE = "-- DEVOLVER ELEMENTO PADRE - ";
    public static final String URLHUB = pUtil.getValorKey("urlhub", DEFAULT_URLHUB); // Obtener URLHUB desde el archivo properties o usar valor predeterminado

    private RemoteWebDriver driver;
    private static UtilSelenium instancia = null;
    private DesiredCapabilities capability;
    private int numPaso = 0;


    // Comprobar SO (Sistema Operativo)
    String sSistemaOperativo = System.getProperty("os.name");

    // Para el error conocido de sfl4j en el sistema operativo Linux se utilizará la clase "Logs" hasta solventar el problema.
    Logs logs = new Logs();


    private Boolean isTraceActivated = true;
    private void trace( String msj) {

        if ( this.isTraceActivated ) {
            System.out.println(msj);
        }
    }



    /**
     * Crear una instancia de selenium para un navegador determinado por la variable browser.
     * @param nombreLogger
     * @param browser navegador
     * @param version
     * @param customOptions
     */
    public UtilSelenium(String nombreLogger, String browser, String version, Map<String, Object> customOptions) throws Exception {
        super(nombreLogger);

        // Debug
        System.out.println(Logs.DEBUG_START_INIT);
        System.out.println(Logs.DEBUG_ID_TEST + nombreLogger);
        System.out.println(Logs.DEBUG_BROWSER + browser);
        System.out.println(Logs.DEBUG_VERSION + version);


        switch (browser) {

            case CHROME:
                seleniumChrome(customOptions);
                break;

            case FIREFOX:
                seleniumFirefox(customOptions);
                break;

            case EDGE:
                seleniumEdge(customOptions);
                break;
                
            case IEXPLORER:
                seleniumIE(customOptions);
                break;

            default:
                seleniumChrome(customOptions);
                break;
        }
    }




    /**
     * Levantar selenium para Interner Explorer
     * @param version version del selenium
     */
    public void seleniumIE(Map<String, Object> customOptions) throws Exception  {

    	// Configuramos las capacidades deseadas para Internet Explorer
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        capabilities.setBrowserName("internet explorer");
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setCapability("ignoreZoomSetting", true); // Añadir si hay problemas con el zoom
        capabilities.setCapability("nativeEvents", false); // Evitar ciertos problemas con eventos de IE
        capabilities.setCapability("requireWindowFocus", true); // Mejora la interacción con elementos
        
        
        // Establecemos la ruta del driver de Internet Explorer
     //   System.setProperty("webdriver.ie.driver", "ruta/al/IEDriverServer.exe");
        driver = new RemoteWebDriver(new URL(URLHUB), capabilities);
        trace(Logs.MENSAJE_REMOTEWEBDRIVER_SELENIUM_EDGE + driver);
        

    }
 
    
    /**
     *
     * @param customOptions
     * @throws Exception
     */
    public void seleniumEdge(Map<String, Object> customOptions) {

        try {
            trace(Logs.MENSAJE_INICIO_SELENIUM_EDGE);

            EdgeOptions options = new EdgeOptions();
            customOptions.forEach(options::setCapability);

            driver = new RemoteWebDriver(new URL(URLHUB), options);
            trace(Logs.MENSAJE_REMOTEWEBDRIVER_SELENIUM_EDGE + driver);

        } catch (Exception e) {
            UtilException.manejarMapeoDeExcepcionesConexionSelenium(e, EDGE);
        }
    }


    /**
     *
     * @param customOptions
     * @throws UtilException
     */
    public void seleniumChrome(Map<String, Object> customOptions)  {
        try {
            trace(Logs.MENSAJE_INICIO_SELENIUM_CHROME);

            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName(CHROME);

            if (sSistemaOperativo.contains(Logs.SO_WINDOWS)) {
                cap.setPlatform(Platform.WINDOWS);
            } else {
                cap.setPlatform(Platform.LINUX);
            }

            ChromeOptions options = new ChromeOptions();
            customOptions.forEach(options::setCapability);
            driver = new RemoteWebDriver(new URL(URLHUB), options);
            trace(Logs.MENSAJE_EXITO_CONEXION_SELENIUM_CHROME);

        } catch (Exception e) {
            UtilException.manejarMapeoDeExcepcionesConexionSelenium(e, CHROME);
        }
    }


    /**
     * Levantar Firefox con driver remoto de Selenium
     * @param customOptions Opciones personalizadas para el controlador
     */
    public void seleniumFirefox(Map<String, Object> customOptions) {
        try {
            trace(Logs.MENSAJE_INICIO_SELENIUM_FIREFOX);
            System.setProperty("webdriver.gecko.driver", rutaBinarioDriver(FIREFOX));
            FirefoxOptions options = new FirefoxOptions();
            customOptions.forEach(options::setCapability);
            driver = new RemoteWebDriver(new URL(URLHUB), options);
            trace(Logs.MENSAJE_EXITO_CONEXION_SELENIUM_FIREFOX);

        } catch (Exception e) {
            UtilException.manejarMapeoDeExcepcionesConexionSelenium(e, FIREFOX);
        }
    }









    /**
     * Opciones de configuración
     *
     * @return options, variable de tipo ChromeOptions con la configuración del navegador chrome
     */

    public ChromeOptions opcionesIE() {
        borrarFichero(getRutaDescarga());
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", Boolean.FALSE);
        prefs.put("download.directory_upgrade", Boolean.TRUE);
        prefs.put("plugins.always_open_pdf_externally", Boolean.TRUE);
        prefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
        prefs.put("download.default_directory", getRutaDescarga());
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--test-type");
        options.addArguments("--start-maximized");

        return options;
    }


    /**
     * Opciones de configuración del propio navegador chrome
     *
     * @return options, variable de tipo ChromeOptions con la configuración del navegador chrome
     */
    public ChromeOptions opcionesChrome() {
        borrarFichero(getRutaDescarga());
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", Boolean.FALSE);
        prefs.put("download.directory_upgrade", Boolean.TRUE);
        prefs.put("plugins.always_open_pdf_externally", Boolean.TRUE);
        prefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
        prefs.put("download.default_directory", getRutaDescarga());
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--test-type");
        options.addArguments("--start-maximized");

        return options;
    }


    /**
     * Metodo obsoleto para esta librería. La librería se ha actualizado solo para lanzar los test con Selenium GRID.
     *
     * info:
     *
     *  - Este metodo de SeleniumIE solo se lanzaba en local sin Selenium Grid. Para que funcione con selenium Grid es necesario actualizar este metodo.
     *  - IE no puede levantarse en el Sistema operativo del servidor remoto (LINUX). Se mantiene por si fuese necesario lanzar en local con Selenium Grid pero es necesario su actualización.
     */






    /**
     * Modificar las opciones y ruta de descargas de IE
     */
    public void modificarRutaDescargasIE() throws Exception {
        String path = getRutaDescarga();
        borrarFichero(getRutaDescarga());
        String cmd1 = "REG ADD \"HKEY_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\Main\" /F /V \"Default Download Directory\" /T REG_SZ /D " + path;
        try {
            Runtime.getRuntime().exec(cmd1);
        } catch (Exception e) {
            logs.error("Coulnd't change the registry for default directory for IE");
        }
    }






    /**
     * Método para configurar las opciones del propio navegador firefox
     *
     * @return myProfile, variable de tipo FirefoxProfile que contiene la configuración del navegador firefox
     */
    public FirefoxProfile opcionesFirefox() {
        borrarFichero(getRutaDescarga());
        FirefoxProfile myProfile = new FirefoxProfile();
        myProfile.setPreference("browser.download.folderList", 2);
        myProfile.setPreference("browser.download.manager.showWhenStarting", false);
        myProfile.setPreference("browser.download.dir", getRutaDescarga());
        myProfile.setPreference("browser.helperApps.neverAsk.openFile",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        myProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,application/pdf,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        myProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        myProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        myProfile.setPreference("browser.download.manager.focusWhenStarting", false);
        myProfile.setPreference("browser.download.manager.useWindow", false);
        myProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
        myProfile.setPreference("browser.download.manager.closeWhenDone", false);

        // Deshabilitar configuración Acrobat
        myProfile.setPreference("pdfjs.disabled", true);

        // Desactivar Acrobat plugin que previsualiza PDFs en Firefox
        myProfile.setPreference("plugin.scan.Acrobat", "99.0");
        myProfile.setPreference("plugin.scan.plid.all", false);

        return myProfile;
    }

    /**
     * Crea la instancia de UtilSelenium si no existe y la devuelve
     * @param nombreLogger nombre del fichero log
     * @param browser navegador
     * @param version version del selenium webDriver
     * @return devuelve la instancia de UtilSelenium

    public static UtilSelenium getInstancia(String nombreLogger, String browser, String version) {

        // Debug
        System.out.println("[DEBUG] - UtilSelenium.getIntancia() - inicio");

        try {
            if (instancia == null) {
                instancia = new UtilSelenium(nombreLogger, browser, version);
                // Debug
                System.out.println("[DEBUG] - UtilSelenium.getIntancia() - instancia creada");
            }

            // Debug
            System.out.println("[DEBUG] - UtilSelenium.getIntancia() - fin normal");

            return instancia;

        } catch (final Exception e) {
            // Debug
            System.out.println("[DEBUG] - UtilSelenium.getIntancia() - fin con excepcion");
            return null;
        }
    }
*/



    /**
     *
     * @param nombreLogger
     * @param browser
     * @param version
     * @param customOptions
     * @return
     */
    public static UtilSelenium getInstancia(String nombreLogger, String browser, String version, Map<String, Object> customOptions) {
        try {
            if (instancia == null) {
                instancia = new UtilSelenium(nombreLogger, browser, version, customOptions);
            }
            return instancia;
        } catch (final Exception e) {
            return null;
        }
    }

    /**
     * devuelve la instancia de utilSelenium
     * @return instancia de UtilSelenium
     */
    public static UtilSelenium getUtilSelenium() {
        return instancia;
    }




    /**
     * cerrar driver y poner a null la variable instancia
     */
    public void cerrarDriver() {
        try {
            if (getDriver() != null && StringUtils.isNotBlank(getDriver().getWindowHandle())) {
                getDriver().quit();
                finalizaInstancia();
            }
        } catch (ElementNotInteractableException e) {
            logs.error("-- CERRAR DRIVER - " + e.getMessage() + ERROR, e);
        }
    }

    /**
     * Finaliza la instancia de UtilSelenium
     */
    public static void finalizaInstancia() {
        instancia = null;
    }

    /**
     * Matar los procesos del selenium WebDriver
     *
     * @param nombre nombre de los procesos a eliminar
     */
    public void matarProcesos(String nombre) throws Exception {
        try {
            if (Boolean.TRUE.equals(estoyEnWindows())) {
                Runtime.getRuntime().exec("taskkill /T /F /IM " + nombre);
            } else {
                Runtime.getRuntime().exec("killall " + nombre);
            }
        } catch (IOException e) {
            logs.error("Imposible de finalizar procesos");
        }
    }

    /**
     * Gets driver.
     *
     * @return the driver
     */
    public RemoteWebDriver getDriver() {
        return driver;
    }

    /**
     * Sets driver.
     *
     * @param driver the driver
     */
    public void setDriver(RemoteWebDriver driver) {
        this.driver = driver;
    }

    /**
     * Gets browser.
     *
     * @return the browser
     */
    public String getBrowser() {
        return BROWSER;
    }

    /**
     * Gets capability.
     *
     * @return the capability
     */
    public DesiredCapabilities getCapability() {
        return capability;
    }

    /**
     * Sets capability.
     *
     * @param capability the capability
     */
    public void setCapability(DesiredCapabilities capability) {

        this.capability = capability;
    }





    /**
     * Método que comprueba si un elemento es visible o no.
     *
     * @param by the by
     * @return boolean
     */
    public boolean isElementPresent(By by) {
        try {
            getDriver().findElement(by);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    /**
     * Método que comprueba si un atributo existe, aunque en caso de que sea un atributo tipico del elemento delvolverá un valor vacio
     *
     * @param by
     * @param attribute
     * @return null si no existe y vacío si es típico el elemento aunque no exista
     */
    public boolean isAttributePresent(By by, String attribute) {
        try {
            if (getDriver().findElement(by).getAttribute(attribute) != null) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    /**
     * Método que comprueba si el valor de un atributo contiene el valor pasado
     *
     * @param by
     * @param attribute
     * @param value
     * @return devuelve false si da una excepcion o el atributo no contiene el valor pasado y true en caso contrario
     */
    public boolean checkAttributeValue(By by, String attribute, String value) {
        try {
            if (getDriver().findElement(by).getAttribute(attribute).contains(value)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    /**
     * Método que comprueba si un elemento está visible y activo.
     *
     * @param by
     * @return
     */
    public boolean isDisplayedAndEnabled(By by) {
        boolean enc = Boolean.FALSE;
        try {
            if (getDriver().findElement(by).isDisplayed() && getDriver().findElement(by).isEnabled()) {
                enc = Boolean.TRUE;
            }
            return enc;
        } catch (ElementNotInteractableException e) {
            return enc;
        }
    }

    /**
     * Metodo que busca un elemento a través del atributo que se pase por parámetro
     * y el texto que contenga entre la etiqueta de apertura y cierre
     *
     * @param by
     * @param texto
     * @return encontrado
     */
    public boolean buscarElementoPorTexto(By by, String texto) {
        Boolean encontrado = Boolean.FALSE;
        List<WebElement> lista;
        try {
            lista = getDriver().findElements(by);
            for (WebElement we : lista) {
                if (we.getText().contains(texto)) {
                    encontrado = Boolean.TRUE;
                    break;
                }
            }
        } catch (ElementNotInteractableException e) {
            logs.error("-- BUSCAR ELEMENTO POR TEXTO - " + e.getMessage() + ERROR, e);
        }
        return encontrado;
    }

    /**
     * Metodo que hace click en el elemento que busca a través del atributo que se pase por parámetro
     * y el texto que contenga entre la etiqueta de apertura y cierre
     *
     * @param by
     * @param texto
     */
    public void click(By by, String texto) {
        Boolean encontrado = Boolean.FALSE;
        List<WebElement> lista;
        try {
            lista = getDriver().findElements(by);
            for (WebElement we : lista) {
                if (we.getText().contains(texto)) {
                    we.click();
                    encontrado = Boolean.TRUE;
                    break;
                }
            }
        } catch (ElementNotInteractableException e) {
            logs.error("-- CLICK - " + e.getMessage() + ERROR, e);
        } finally {
            Assert.isTrue(encontrado, "No se ha podido hacer click en el elemento " + by);
        }
    }

    /**
     * Método que busca un elemento por el nombre que contiene entre el componente buscado y devuelve dicho elemento
     *
     * @param by
     * @param texto
     * @return
     */
    public WebElement devolverElementoBuscadoPorTexto(By by, String texto) {
        List<WebElement> lista;
        WebElement valor = null;
        try {
            lista = getDriver().findElements(by);
            for (WebElement we : lista) {
                if (we.getText().contains(texto)) {
                    valor = we;
                    break;
                }
            }
        } catch (ElementNotInteractableException e) {
            logs.error("-- DEVOLVER ELEMENTO BUSCADO POR TEXTO - " + e.getMessage() + ERROR, e);
        }
        return valor;
    }

    /**
     * Método que devuelve el elemento padre del hijo cuando tenemos el WebElement hijo
     *
     * @param hijo
     * @return
     */
    public WebElement devolverElementoPadre(WebElement hijo) {
        WebElement padre = null;
        try {
            padre = hijo.findElement(By.xpath("./.."));
        } catch (ElementNotInteractableException e) {
            logs.error(ELEMENTOPADRE + e.getMessage() + ERROR, e);
        }
        return padre;
    }

    /**
     * Método que devuelve el elemento subpadre (i) del hijo cuando tenemos el WebElement
     *
     * @param hijo
     * @param i
     * @return
     */
    public WebElement devolverElementoPadre(WebElement hijo, int i) {
        WebElement padre = hijo;
        try {
            for (int z = 1; z <= i; z++) {
                padre = padre.findElement(By.xpath("./.."));
            }
        } catch (ElementNotInteractableException e) {
            logs.error(ELEMENTOPADRE + e.getMessage() + ERROR, e);
        }
        return padre;
    }

    /**
     * Método que devuelve el elemento padre del hijo que le pasamos con el texto que contiene
     *
     * @param by
     * @param texto
     * @return
     */
    public WebElement devolverElementoPadre(By by, String texto) {
        List<WebElement> lista;
        WebElement padre = null;
        try {
            lista = getDriver().findElements(by);
            for (WebElement we : lista) {
                if (we.getText().contains(texto)) {
                    padre = we.findElement(By.xpath("./.."));
                    break;
                }
            }
        } catch (ElementNotInteractableException e) {
            logs.error(ELEMENTOPADRE + e.getMessage() + ERROR, e);
        }
        return padre;
    }

    /**
     * Método que devuelve el elemento subpadre(i) del hijo que le pasemos con el texto que contiene
     *
     * @param by
     * @param texto
     * @param i
     * @return
     */
    public WebElement devolverElementoPadre(By by, String texto, int i) {
        List<WebElement> lista;
        WebElement padre = null;
        try {
            lista = getDriver().findElements(by);
            for (WebElement we : lista) {
                if (we.getText().contains(texto)) {
                    padre = we;
                    for (int z = 1; z <= i; z++) {
                        padre = padre.findElement(By.xpath("./.."));
                    }
                    break;
                }
            }
        } catch (ElementNotInteractableException e) {
            logs.error(ELEMENTOPADRE + e.getMessage() + ERROR, e);
        }
        return padre;
    }

    /**
     * Método que marca en amarillo el elemento deseado
     *
     * @param by
     */
    public void highLighterMethod(By by) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", getDriver().findElement(by));
        esperarSegundos(2);
    }

    /**
     * Método que marca en amarillo el elemento deseado
     *
     * @param by
     * @param texto
     * @throws Exception
     */
    public void highLighterMethod(By by, String texto) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", devolverElementoPorTexto(by, texto));
            esperarSegundos(2);
        } catch (ElementNotInteractableException e) {
            logs.error("-- HIGHLIGHTER METHOD - " + e.getMessage(), e);
        }
    }

    /**
     * Método que devuelve el elemento buscado
     *
     * @param by
     * @param texto
     * @return
     * @throws Exception
     */
    public WebElement devolverElementoPorTexto(By by, String texto) {
        WebElement w = null;
        List<WebElement> lista;
        try {
            lista = getDriver().findElements(by);
            for (WebElement we : lista) {
                if (we.getText().contains(texto)) {
                    w = we;
                    break;
                }
            }
        } catch (ElementNotInteractableException e) {
            logs.error("-- DEVOLVER ELEMENTO POR TEXTO - " + e.getMessage() + ERROR, e);
            Assert.isTrue(Boolean.FALSE, "-- DEVOLVER ELEMENTO POR TEXTO - " + e.getMessage() + ERROR);
        }
        return w;
    }

    /**
     * Metodo para esperar x segundos.
     *
     * @param segundos the segundos
     */
    public void esperarSegundos(long segundos) {

        try {
            synchronized (getDriver()) {
                while (true) {
                    getDriver().wait(segundos * 1000);
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logs.error("- ESPERAR SEGUNDOS - " + e.getMessage(), e);
        }
    }


    /**
     * Metodo que cierra la pestaña abierta dejando solo pa primera.
     *
     * @param tituloVentanaPrincipal
     * @param cerrarTodas
     */
    public void cerrarTabs(String tituloVentanaPrincipal, boolean cerrarTodas) {
        logs.info(" CERRAR PESTAÑAS NO USADAS ");
        int numerosTabs;
        ArrayList<String> tabs;
        try {
            tabs = new ArrayList<>(getDriver().getWindowHandles());

            numerosTabs = tabs.size();

            if (numerosTabs > 1) {
                if (cerrarTodas) {
                    numerosTabs = 2;
                }

                for (int i = 1; i < numerosTabs; i++) {
                    getDriver().switchTo().window(tabs.toArray()[i].toString());
                    getDriver().close();
                    logs.info("Cerrando pestaña {}", i);
                }

                if (tituloVentanaPrincipal.isEmpty()) {
                    getDriver().switchTo().window(tabs.get(0));
                } else {
                    getDriver().switchTo().window(tituloVentanaPrincipal);
                }
            }
        } catch (ElementNotInteractableException e) {
            logs.error("-- CERRAR PESTAÑAS - " + e.getMessage() + ERROR, e);
        }
    }

    /**
     * Imprimir el paso que se va a realizar
     */
    public void imprimirPaso(String paso) {
        numPaso++;
        logs.info(GUIONES + " Realizar el paso {} " + GUIONES, numPaso);
        logs.info(paso);
    }

    public void imprimirResultadoEsperado(boolean correcto) {
        if (correcto) {
            logs.info(GUIONES + " Resultado esperado del paso {} correcto " + GUIONES, numPaso);
        } else {
            logs.error(GUIONES + " Error en el resultado esperado del paso {} " + GUIONES, numPaso);
        }
        Assert.isTrue(correcto, GUIONES + " Error en el resultado esperado del paso " + numPaso + " " + GUIONES);
    }


    /**
     * Tomar captura de pantalla
     *
     * @return
     */
    public String tomarFoto() {

        //obtener nombre de la clase que invoca
        String className = new Exception().getStackTrace()[1].getClassName();
        String nombreImagen = "";


        Date fecha = new Date();
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy_HHmmss");
        try {
            File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

            if (!("".equals(className))){
                nombreImagen = getRutaImagen().concat(className).concat("-").concat(format.format(fecha)).concat(".png");
            } else {
                nombreImagen = getRutaImagen().concat(getClass().getSimpleName().replace("_", "-").toUpperCase()).concat("_").concat(format.format(fecha)).concat(".png");
            }

            FileUtils.copyFile(scrFile, new File(nombreImagen));
        } catch (IOException e) {
            logs.error("ERROR AL TOMAR CAPTURA DE PANTALLA -> " + e.getMessage());
        }
        return nombreImagen;
    }

    /**
     * Devuelve la ruta del binario del driver.
     *
     * @param browser
     * @return
     */
    public String rutaBinarioDriver(String browser) {
        String binarioBrowser = "";

        switch (browser) {
            case CHROME:
                binarioBrowser = DRIVERCHROME;
                break;
            case IEXPLORER:
                binarioBrowser = DRIVERIE;
                break;
            case FIREFOX:
                binarioBrowser = DRIVERFIREFOX;
                break;
            default:
                binarioBrowser = DRIVERCHROME;
                break;

        }
        return getRutaLog() + binarioBrowser;
    }



}