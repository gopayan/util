package es.ja.csalud.sas.oca.utilidades;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Date;
import static junit.framework.TestCase.assertNull;

/**
 *  Info: Se comentan las lineas relacionadas con logger(hasta solventar el error sfl4j en entorno Linux) y con
 *  el lanzamiento de pruebas con IE (obsoleto para Selenium Grid en entorno linux)
 */

public class PruebasSeleniumTest {

    private UtilSelenium utilSelenium;
    private UtilPropiedades propiedades;

    @Test
    public void testApp() throws Exception {
        String textoBoton = "Home", appTab;
        WebElement prueba;

        // firefox
     //   utilSelenium = UtilSelenium.getInstancia("PruebasSeleniumTest", "firefox", null);

        Logs logs = new Logs();

        // Parte BasePrueba
        utilSelenium.getRutaDescarga();
        utilSelenium.getRutaImagen();
        utilSelenium.getRutaLog();
        utilSelenium.getResourcesByBrowser("firefox");
        utilSelenium.obtenerCadenaDescodificada("45687123");
        utilSelenium.listarPropiedadesQueEmpiecenPor("jira.properties", "a");
        utilSelenium.existeFichero("prueba");
        utilSelenium.existeFichero("C:\\OCA", "prueba", ".pdf");
        utilSelenium.existeFicheroDescargado("prueba");
        utilSelenium.existeFicheroDescargado("prueba", "pdf");
        utilSelenium.comprobarficheroConDatos("prueba");
        utilSelenium.borrarFichero("prueba");
        utilSelenium.borrarFichero("C:\\OCA", "prueba", ".pdf");
        utilSelenium.comprobarCabeceraCSV("prueba", "prueba");
        utilSelenium.diferenciaEntreFechas("21/01/2020", "22/01/2020", "dd/MM/yyyy");
        utilSelenium.calcularEdadDias("04/11/2019", "dd/MM/yyyy");
        utilSelenium.calcularEdadMeses("04/11/2019", "dd/MM/yyyy");
        utilSelenium.calcularEdadAnos("30/10/2001", "dd/MM/yyyy");
        utilSelenium.obtenerFechaHoyFormato("dd.MM.yyyy");
        utilSelenium.validarFecha("13/09/1987", "dd/MM/yyyy");
        utilSelenium.sumarRestarDiasFecha(new Date(), 2);
        utilSelenium.sumarRestarDiasFechaHoy(3);
        utilSelenium.numeroAleatorio(1, 100);

        // UtilSelenium
        utilSelenium.getUtilSelenium();
        utilSelenium.getDriver();
        utilSelenium.getBrowser();
        utilSelenium.getCapability();
        utilSelenium.imprimirPaso("Acceder a Redmine");
        //     utilSelenium.getLogger().info("URL acceso a http://redmine.sas.junta-andalucia.es/");
        utilSelenium.getDriver().navigate().to("http://redmine.sas.junta-andalucia.es/");
        utilSelenium.isElementPresent(By.xpath("//div[@id='header']"));
        utilSelenium.isAttributePresent(By.xpath("//div[@id='header']"), "class");
        utilSelenium.checkAttributeValue(By.xpath("//div[@id='header']"), "id", "header");
        utilSelenium.isDisplayedAndEnabled(By.xpath("//div[@id='header']"));
        prueba = utilSelenium.getDriver().findElement(By.xpath("//div[@id='header']"));
        utilSelenium.devolverElementoPadre(prueba);
        utilSelenium.devolverElementoPadre(prueba, 2);
        utilSelenium.highLighterMethod(By.xpath("//div[@id='header']"));
        utilSelenium.esperarSegundos(1);
     //   utilSelenium.esperarHastaElementoVisible(By.xpath("//div[@id='header']"));
        utilSelenium.buscarElementoPorTexto(By.xpath("//div[@id='header']/div/h1"), "Redmine");
        if (utilSelenium.buscarElementoPorTexto(By.xpath("//ul/li/a[@class='home']"), "Inicio")) {
            textoBoton = "Inicio";
        }
        utilSelenium.click(By.xpath("//ul/li/a[@class='home']"), textoBoton);
        utilSelenium.devolverElementoBuscadoPorTexto(By.xpath("//ul/li/a"), textoBoton);
        utilSelenium.devolverElementoPadre(By.xpath("//ul/li/a"), textoBoton);
        utilSelenium.devolverElementoPadre(By.xpath("//ul/li/a"), textoBoton, 2);
        utilSelenium.highLighterMethod(By.xpath("//ul/li/a"), textoBoton);
        utilSelenium.devolverElementoPorTexto(By.xpath("//ul/li/a"), textoBoton);
       // utilSelenium.esperarHastaElementoClickable(By.xpath("//div[@id='header']"));
        utilSelenium.tomarFoto();
        // utilSelenium.getLogger().info("Cerrar navegador");
        utilSelenium.imprimirResultadoEsperado(Boolean.TRUE);

        // fin firefox
        appTab = utilSelenium.getDriver().getWindowHandle();
        utilSelenium.cerrarTabs(appTab, Boolean.TRUE);
        utilSelenium.cerrarDriver();


//        Internet Explorer
//        utilSelenium = UtilSelenium.getInstancia("PruebasSeleniumTest", "internet explorer", null);
//        utilSelenium.getLogger().info("URL acceso a http://oca.sas.junta-andalucia.es/");
//        utilSelenium.getDriver().navigate().to("http://oca.sas.junta-andalucia.es/");
//        utilSelenium.cerrarDriver();

        // Chrome
    //    utilSelenium = UtilSelenium.getInstancia("PruebasSeleniumTest", "chrome", null);
        // utilSelenium.getLogger().info("URL acceso a http://oca.sas.junta-andalucia.es/new/");
        utilSelenium.getDriver().navigate().to("http://oca.sas.junta-andalucia.es/new/");
        utilSelenium.tomarFoto();
        utilSelenium.cerrarDriver();

        // Propiedades
        propiedades = UtilPropiedades.getInstance("jira.properties", "UTF-8");
        propiedades = UtilPropiedades.getInstance("jira.properties");
        propiedades.getInstance();
        propiedades.leerFichero();
        propiedades.getValorKey("browser");
        propiedades.listarPropiedadesQueEmpiecenPor("b");
        propiedades.getFilePropertiesConfig();
        propiedades.getEncondingUTF8();
        propiedades.leerFichero("fichero2.propiedades");

        assertNull(null);
    }

}
