package es.ja.csalud.sas.oca.utilidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Clase Logs
 */
public class Logs {

    // Mensajes relacionados con la conexión y sesión
    public static final String URL_NO_VALIDA = "URL del hub de Selenium no es válida: ";
    public static final String NAVEGADOR_NO_ALCANZADO = "No se pudo alcanzar el navegador a través de Selenium Grid: ";
    public static final String SESION_NO_CREADA = "No se pudo crear la sesión de WebDriver: ";
    public static final String ERROR_WEBDRIVER_GENERAL = "Error general de WebDriver al conectar con Selenium Grid: ";
    public static final String SESION_NO_EXISTE = "La sesión de WebDriver ya no existe ";
    public static final String SESION_ID_INVALIDA = "El ID de sesión es inválido";
    public static final String ERROR_INESPERADO = "Error inesperado al intentar conectar con Selenium Grid: ";

    // Mensajes relacionados con la interacción con el DOM
    public static final String ELEMENTO_NO_ENCONTRADO = "No se pudo encontrar el elemento en la página: ";
    public static final String ELEMENTO_NO_INTERACTUABLE = "El elemento no es interactuable ";
    public static final String ELEMENTO_DESACTUALIZADO = "El elemento ya no es parte del DOM ";
    public static final String ELEMENTO_BLOQUEADO = "El clic en el elemento fue bloqueado por otro: ";
    public static final String SELECTOR_INVALIDO = "El selector proporcionado es inválido";

    // Mensajes relacionados con errores de tiempo y argumentos
    public static final String TIMEOUT_EXCEDIDO = "Se excedió el tiempo máximo para la operación en el navegador ";
    public static final String ARGUMENTO_INVALIDO = "Se ha proporcionado un argumento inválido";

    // Mensajes de depuración de conexión
    public static final String MENSAJE_INICIO_SELENIUM_EDGE = "[DEBUG] - seleniumEdge() - inicio";
    public static final String MENSAJE_REMOTEWEBDRIVER_SELENIUM_EDGE = "[DEBUG] - seleniumEdge() - RemoteWebDriver creado. driver=";
    public static final String MENSAJE_INICIO_SELENIUM_CHROME = "[DEBUG] - Iniciando conexión con Selenium Grid usando Chrome";
    public static final String MENSAJE_INICIO_SELENIUM_FIREFOX = "[DEBUG] - Iniciando conexión con Selenium Grid usando Firefox";
    public static final String MENSAJE_EXITO_CONEXION_SELENIUM_CHROME = "[DEBUG] - Conexión con Selenium Grid usando Chrome ha sido exitosa";
    public static final String MENSAJE_EXITO_CONEXION_SELENIUM_FIREFOX = "[DEBUG] - Conexión con Selenium Grid usando Firefox ha sido exitosa";

    // Mensajes relacionados con sistemas operativos
    public static final String SO_WINDOWS = "Windows";
    public static final String SO_LINUX = "Linux";

    // Otros mensajes de depuración
    public static final String MICROSOFT_EDGE = "MicrosoftEdge";
    public static final String DEBUG_START_INIT = "[DEBUG] - UtilSelenium() - inicio (llamada a super ejecutada)";
    public static final String DEBUG_ID_TEST = "[DEBUG] - UtilSelenium() - id test =";
    public static final String DEBUG_BROWSER = "[DEBUG] - UtilSelenium() - browser=";
    public static final String DEBUG_VERSION = "[DEBUG] - UtilSelenium() - version=";




    public Logs() {
    }

    public Logs(String tipoLog) {
        String Log = tipoLog;
    }


    /**
     *
     * @param tipoError
     */
    public void error (String tipoError) {
        date();
        System.out.println("[ERROR]: "+tipoError);
    }

    /**
     *
     * @param tipoError
     * @param valor
     */
    public void error (String tipoError, int valor) {
        date();
        System.out.println("[ERROR]: "+tipoError+" "+valor) ;
    }

    /**
     *
     * @param tipoError
     */
    public void error (String tipoError, Exception e) {
        date();
        System.out.println("[ERROR]: "+tipoError+" "+e);
    }


    /**
     *
     * @param infoLog
     */
    public void info(String infoLog){
        date();
        System.out.println("[INFO]: "+ infoLog);
    }


    /**
     *
     * @param infoLog
     * @param valor
     *
     */
    public void info(String infoLog, int valor){
        date();
        System.out.println("[INFO]: "+ infoLog+" "+valor);
    }


    /**
     *   Metodo para generar fecha
     */
    public void date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.print("[DEBUG] - "+dtf.format(LocalDateTime.now())+" - ");
    }

    /**
     *
     * @param tipoError
     * @param pathToScan
     */
    public void error(String tipoError, String pathToScan) {
        System.out.println("[ERROR]: "+tipoError+" : "+pathToScan);

    }

    /**
     *
     * @param string
     * @param pathToScan
     * @param inicio
     * @param ultimo
     */
    public void error(String string, String pathToScan, String inicio, String ultimo) {
        System.out.println("[ERROR]: "+string+" : "+pathToScan+" "+inicio+" "+ultimo);

    }

}
