package es.ja.csalud.sas.oca.exception;

import es.ja.csalud.sas.oca.utilidades.Logs;
import es.ja.csalud.sas.oca.utilidades.UtilSelenium;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.UnreachableBrowserException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static es.ja.csalud.sas.oca.utilidades.UtilSelenium.URLHUB;

public class UtilException extends Exception {

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }


    // Mapa para mapeo de excepciones a sus mensajes correspondientes
    private static final Map<Class<? extends Exception>, String> EXCEPTION_MESSAGES = new HashMap<>();

    static {
        // Excepciones relacionadas con la conexión y sesión
        EXCEPTION_MESSAGES.put(SessionNotCreatedException.class, Logs.SESION_NO_CREADA);
        EXCEPTION_MESSAGES.put(UnreachableBrowserException.class, Logs.NAVEGADOR_NO_ALCANZADO);
        EXCEPTION_MESSAGES.put(MalformedURLException.class, Logs.URL_NO_VALIDA);
        EXCEPTION_MESSAGES.put(WebDriverException.class, Logs.ERROR_WEBDRIVER_GENERAL);
        EXCEPTION_MESSAGES.put(NoSuchSessionException.class, Logs.SESION_NO_EXISTE);

        // Excepciones relacionadas con la interacción con el DOM
        EXCEPTION_MESSAGES.put(NoSuchElementException.class, Logs.ELEMENTO_NO_ENCONTRADO);
        EXCEPTION_MESSAGES.put(ElementNotInteractableException.class, Logs.ELEMENTO_NO_INTERACTUABLE);
        EXCEPTION_MESSAGES.put(StaleElementReferenceException.class, Logs.ELEMENTO_DESACTUALIZADO);
        EXCEPTION_MESSAGES.put(ElementClickInterceptedException.class, Logs.ELEMENTO_BLOQUEADO);
        EXCEPTION_MESSAGES.put(InvalidSelectorException.class, Logs.SELECTOR_INVALIDO);

        // Excepciones relacionadas con errores de tiempo y argumentos
        EXCEPTION_MESSAGES.put(TimeoutException.class, Logs.TIMEOUT_EXCEDIDO);
        EXCEPTION_MESSAGES.put(InvalidArgumentException.class, Logs.ARGUMENTO_INVALIDO);
    }

    /**
     * Maneja las excepciones de Selenium utilizando el mapa de excepciones.
     *
     * @param e       Excepción capturada
     * @param browser Nombre del navegador para personalizar los mensajes de error
     */
    public static void manejarMapeoDeExcepcionesConexionSelenium(Exception e, String browser) {
        try {
            // Busca el mensaje correspondiente en el mapa de excepciones
            String mensaje = EXCEPTION_MESSAGES.getOrDefault(e.getClass(), Logs.ERROR_INESPERADO);

            // Lanzamos la excepción personalizada con el mensaje correcto
            throw new UtilException(mensaje + e.getMessage(), e);

        } catch (UtilException ue) {
            // Manejo del caso donde no se puede gestionar la excepción
            Logger logger = Logger.getLogger(UtilSelenium.class.getName());
            logger.severe("Error al manejar la excepción de Selenium: " + ue.getMessage());
            ue.printStackTrace();  // Opcionalmente imprimir el stack trace
        }
    }


    /**
         * Maneja excepciones específicas en las pruebas y realizar limpieza.
         *
         * @param e Excepción capturada
         * @param message Mensaje de error adicional
         * @param utilSelenium Instancia de UtilSelenium, si es necesario para la limpieza
         */
        public static void handleTestException(Exception e, String message, UtilSelenium utilSelenium) {
            if (e instanceof ClassNotFoundException) {
                System.err.println("Clase no encontrada: " + e.getMessage());
            } else if (e instanceof NoSuchMethodException) {
                System.err.println("Método no encontrado: " + e.getMessage());
            } else if (e instanceof InstantiationException) {
                System.err.println("Error al instanciar la clase: " + e.getMessage());
            } else if (e instanceof IllegalAccessException) {
                System.err.println("Acceso ilegal al método: " + e.getMessage());
            } else if (e instanceof InvocationTargetException) {
                System.err.println("Error al invocar el método: " + ((InvocationTargetException) e).getTargetException().getMessage());
            } else if (e instanceof UtilException) {
                System.err.println("Error al obtener instancia de UtilSelenium: " + e.getMessage());
            } else {
                System.err.println("Error inesperado: " + e.getMessage());
            }
            e.printStackTrace();

            // En caso de que no se utilice la limpieza en el método handleTestException
            if (utilSelenium != null) {
                // Ejemplo de limpieza: cerrar el driver si es necesario
                utilSelenium.getDriver().close();
            }
        }
    }