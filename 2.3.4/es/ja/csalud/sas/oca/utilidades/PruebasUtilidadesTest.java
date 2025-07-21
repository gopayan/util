package es.ja.csalud.sas.oca.utilidades;

import java.io.File;
import java.util.Properties;

import org.junit.Test;

import ch.qos.logback.classic.Logger;
//import ch.qos.logback.core.FileAppender;

import static junit.framework.TestCase.assertNull;


/**
 * Unit test for simple App.
 */
public class PruebasUtilidadesTest 
{
    private Properties propiedadesLeeme;

	/**
     * Rigourous Test :-)
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
     */
    @Test
    public void testApp() throws NoSuchFieldException, SecurityException
    {

    	//crear logger metodo 1
    	UtilLogs log0App = new UtilLogs();
    	log0App.setDateFormat("yyyyMMdd");
    	log0App.setFileNameLog("fichero1");
    	log0App.setPathLogs("//tmp//logs//1");
    	System.out.println(log0App.getFileNameLog());
    	System.out.println(log0App.getPathLogs());
    	System.out.println(log0App.getDateFormat());
    	
    	System.out.println("log1App antes.................. ");
    //	Logger log1App = log0App.createLoggerFor();
    	System.out.println("log1App después................ ");
    	
    	//crear logger metodo 2
    	System.out.println("log2App antes.................. ");
  //  	Logger log2App =  new UtilLogs("//tmp//logs//2", "fichero2", "yyyyMMdd_hh").createLoggerFor();
    	System.out.println("log2App después................ ");

   // 	FileAppender<Object> appenderFichero = new FileAppender<Object>();
   // 	appenderFichero = (FileAppender) log2App.getAppender("ficheroLOG");
   // 	appenderFichero.setAppend(false);
    	
    	
	//    File file1 = new File(appenderFichero.getFile());
    	
//		System.out.println("ruta fichero a ver que pasa " + file1.getAbsolutePath());
    	
//    	System.out.println(appenderFichero.getField());
    	
    	///System.out.println(log2App.getAppender("ficheroLOG").getClass().getField("fileName"));
    	
    	
    	//crear logger metodo 3
    	System.out.println("log3App antes.................. ");
//    	Logger log3App =  new UtilLogs("//tmp//logs//3", "fichero3").createLoggerFor();
    	System.out.println("log3App después................ ");

    	//crear logger metodo 4
    	System.out.println("log4App antes.................. ");
//    	Logger log4App =  new UtilLogs("//tmp//logs//4").createLoggerFor();
    	System.out.println("log4App después................ ");

		/*
    	log4App.info("INICIO PRUEBA log4-----------------------------------------------------");
    	log4App.debug("debug log4");
    	log4App.error("error log4");
    	log4App.info("FIN PRUEBA log4-----------------------------------------------------");

    	log3App.info("INICIO PRUEBA log3-----------------------------------------------------");
    	log3App.debug("debug log3");
    	log3App.error("error log3");
    	log3App.info("FIN PRUEBA log3-----------------------------------------------------");

    	log2App.info("INICIO PRUEBA log2-----------------------------------------------------");

    	log2App.debug("debug log2");
    	log2App.error("error log2");
    	log2App.info("FIN PRUEBA log2-----------------------------------------------------");

    	log1App.info("INICIO PRUEBA log1-----------------------------------------------------");

    	log1App.debug("debug log1");
    	log1App.error("error log1");
    	log1App.info("FIN PRUEBA log1-----------------------------------------------------");
		assertNull(null);
*/
//
//    	//crear lector de propiedades
//    	System.out.println("PropertyUtils antes.................. "); 
//    	System.out.println(UtilPropiedades.getInstance().getValorKey("propiedadUno"));
//    	System.out.println(UtilPropiedades.getInstance().getValorKey("propiedadDos"));
//    	System.out.println(UtilPropiedades.getInstance().getValorKey("propiedadTres"));    	
//    	System.out.println("PropertyUtils después................  "); 
//    	
//    	System.out.println(" ");    	
//    	//crear lector de propiedades
//    	UtilPropiedades pUtil = new UtilPropiedades();
//    	
//    	System.out.println("    " + pUtil.getFilePropertiesConfig());
//    	System.out.println("    " + pUtil.getEnconding_UTF8());
//
//    	System.out.println("    " + pUtil.getValorKey("propiedadUno"));
//    	System.out.println("    " + pUtil.getValorKey("propiedadDos"));
//    	System.out.println("    " + pUtil.getValorKey("propiedadTres"));
//    	
//    	//crear lector de propiedades
//
//    	System.out.println(" ");    	
//    	
//    	System.out.println("pUtil2 antes.................. ");    	
//    	UtilPropiedades pUtil2 = new UtilPropiedades("fichero2.propiedades", "UTF-8");
//   	
//    	System.out.println("pUtil2 después................ ");
//
//    	System.out.println("    " + pUtil2.getValorKey("propiedad2Uno"));
//    	System.out.println("    " + pUtil2.getValorKey("propiedad2Dos"));
//    	System.out.println("    " + pUtil2.getValorKey("propiedad2Tres"));
//
//    	System.out.println(" ");    	
//    	
//    	System.out.println("pUtil1 antes.................. ");    	
//    	UtilPropiedades pUtil1 = new UtilPropiedades("fichero2.propiedades");
//   	
//    	System.out.println("pUtil1 después................ ");
//
//    	System.out.println("    " + pUtil1.getValorKey("propiedad2Uno"));
//    	System.out.println("    " + pUtil1.getValorKey("propiedad2Dos"));
//    	System.out.println("    " + pUtil1.getValorKey("propiedad2Tres"));
//
//
//    	System.out.println(" ");
//    	//crear lector de propiedades
//    	System.out.println("pUtil3 fichero.propiedades antes.................. ");    
//    	UtilPropiedades pUtil3 = new UtilPropiedades();
//    	
//    	System.out.println("    " + pUtil3.getFilePropertiesConfig());
//    	System.out.println("    " + pUtil3.getEnconding_UTF8());
//
//    	
//    	System.out.println("    " + pUtil3.getValorKey("propiedadUno"));
//    	System.out.println("    " + pUtil3.getValorKey("propiedadDos"));
//    	System.out.println("    " + pUtil3.getValorKey("propiedadTres"));
//    	
//    	System.out.println("pUtil3 fichero.propiedades despues................ ");
//
//    	System.out.println("pUtil3 fichero2.propiedades antes.................. "); 
//    	pUtil3.setFilePropertiesConfig("fichero2.propiedades");
//    	pUtil3.setEnconding_UTF8("UTF-8");
//    	
//    	pUtil3.leerFichero();
//
//    	System.out.println("    " + pUtil3.getFilePropertiesConfig());
//    	System.out.println("    " + pUtil3.getEnconding_UTF8());
//
//    	System.out.println("    " + pUtil3.getValorKey("propiedadUno"));
//    	System.out.println("    " + pUtil3.getValorKey("propiedadDos"));
//    	System.out.println("    " + pUtil3.getValorKey("propiedadTres"));
//    	
//    	System.out.println("    " + pUtil3.getValorKey("propiedad2Uno"));
//    	System.out.println("    " + pUtil3.getValorKey("propiedad2Dos"));
//    	System.out.println("    " + pUtil3.getValorKey("propiedad2Tres"));
//    	
//    	System.out.println("pUtil3 fichero2.propiedades despues................ ");
//    	System.out.println("pUtil3 fichero3.propiedades antes.................. "); 
//    	pUtil3.setFilePropertiesConfig("fichero3.propiedades");
//    	pUtil3.setEnconding_UTF8("UTF-8");
//    	
//    	pUtil3.leerFichero();
//
//    	System.out.println("    " + pUtil3.getFilePropertiesConfig());
//    	System.out.println("    " + pUtil3.getEnconding_UTF8());
//
//    	System.out.println("    " + pUtil3.getValorKey("propiedadUno"));
//    	System.out.println("    " + pUtil3.getValorKey("propiedadDos"));
//    	System.out.println("    " + pUtil3.getValorKey("propiedadTres"));
//    	
//    	System.out.println("    " + pUtil3.getValorKey("propiedad2Uno"));
//    	System.out.println("    " + pUtil3.getValorKey("propiedad2Dos"));
//    	System.out.println("    " + pUtil3.getValorKey("propiedad2Tres"));
//    	System.out.println("pUtil3 fichero3.propiedades despues................ ");
//    	
//
//    	System.out.println("pUtil4 fichero4.propiedades antes.................. "); 
//        propiedadesLeeme = new Properties();
//        
//        propiedadesLeeme = pUtil3.leerFichero("fichero4.propiedades");
//
//        for (Map.Entry<Object, Object> e : propiedadesLeeme.entrySet()) {
//        	  String key = (String) e.getKey();
//        	  String value = (String) e.getValue();
//        	  System.out.println("    " + key);
//        	  System.out.println("    " + value);
//        	}
//    	System.out.println("pUtil4 fichero4.propiedades despues................ ");
        
    }
}
