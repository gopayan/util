## README

- [Version librería de utilidades:] La última versión de la libreria es la : **2.3.2**
- [Para utilizar esta librería]: 2 OPCIONES
    - A) Descargar el archivo desde el repositorio de Nexus y agregar al proyecto.
    - B) Utilizar en el archivo pom.xml esta dependencia.

                  <dependency>
                  <groupId>es.ja.csalud.sas.oca</groupId>
                  <artifactId>utilidades</artifactId>
                  <version>2.3.2</version>
                  </dependency>

## IMPORTANTE LEER ANTES USAR LA LIBRERIA:
- Este proyecto contiene tests para la propia comprobación del proyecto de utilidades. Tambien puede probarse directamente como librería en un proyecto OCA u otro proyecto con la arquitectura compatible con dicha librería.
  -- _**Importante, estos test estan obsoletos y requieren de una actualización.**_ Se requiere esta mejora para futuras versiones.(PruebasSeleniumTest.java y PruebasUtilidadesTest.java)
- El proyecto además de contener clases para estos test también contienen archivos de propiedades para estos test. Es importante no confundir estos archivos con los del proyecto que utilizará dicha librería.
    - Por ejemplo desde la versión 2.2.1 se ha incorporado un funcionamiento para utilizar el valor urlHub por el archivo Jira.properties o por defecto. Este archivo es el que tiene que estar en el proyecto, no en la librería. Ya que este valor deberá ser parametrizado.
- El .jar de la libreria de utilidades no es necesario que contenga el directorio con sus tests ya que éste se utilizará en un proyecto distinto y no necesita de sus propios test para su funcionamiento.


## Descripcion de las funcionalidades de la librería con respecto a su uso con Selenium Grid.

- Esta librería es funcional para proyectos automatizados de la OCA o que la arquitectura del proyecto automatizado acepte esta librería de utilidades.
- Cambiar la version de las librerías de Selenium (3.141.59) en el archivo pom.xml de esta librería puede provocar que el funcionamiento no sea el esperado o haga que su funcionalidad sea nula.
- A partir de la version 2.2.1 se adapta para que pueda ser utilizado de forma parametrizable los argumentos del navegador y del urlHub desde el archivo jira.properties (ya anteriormente tambien estaba parametrizado el navegador).



## Ejemplo para la instancia de UtilSelenium para la utilización de la librería desde la versión 2.2.1

-- La diferencia con respecto a versiones anteriores con respecto a la instancia es que se ha añadido un nuevo parámetro, que en el ejemplo podemos indicar como "customOptions".

            Map<String, Object> customOptions = new HashMap<>();
            customOptions.put("headless", true); // ejecutar en modo headless
            utilSelenium = UtilSelenium.getInstancia("test", "navegador", "version", customOptions);


## Ejemplo para incluir en el fichero Jira.properties datos con respecto al navegador y el urlhub.

        browser=edge
        urlhub=http://localhost:4444/wd/hub

## [Casuísticas confirmadas de funcionamiento correcto de la librería de utilidades].(**1.3141.49**)


    -- Lanzamiento integro del proyecto en una infraestructura con entorno Linux.
         - Navegadores Chrome, Edge y Firefox.
    
    -- Para proyectos alojados en un entorno local con sistema operativo Windows.

          - Lanzamiento de pruebas automatizadas(Java) con Selenium Grid instalado en la misma máquina local Windows donde se encuentran alojados los códigos fuentes de los proyectos. 
                - Navegadores Chrome, Edge y Firefox.
                - Selenium Grid (version Selenium-server-4.1.2)
        
          - Lanzamiento de pruebas automatizadas (Java) con Selenium Grid instalado en una máquina remota (Linux) distinta a donde se encuentra alojado los códigos fuentes de los proyectos.
                - Navegadores Chrome, Edge y Firefox.
                - Selenium Grid (version Selenium-server-4.x.x)


## Histórico de las librería con Selenium Grid
- Desde la version 1.3141.49 la librería de utilidades solo puede ser utilizada con Selenium Grid.
- Está probado su correcto funcionamiento siempre con un entorno local con un sistema operativo Windows.
- Está probado su correcto funcionamiento en una infraestructura con Jenkins y Selenium Grid, bajo un entorno linux.
- Se ha probado que la ejecución del navegador sea en local con windows y en remoto para linux. Pero el proyecto siempre alojado en local bajo un entorno windows.
- A partir de la versión 1.3141.44 la librería de utilidades incluye una mejora para utilizarla en el navegador Edge en remoto con Selenium Grid.
- A partir de la version "1.3141.40" de la librería de utilidades incluye una mejora para utilizarla con Selenium grid en remoto para navegadores firefox y Chrome.


## Clases
- UtilSelenium: Clase que contiene los distintos métodos para iniciar RemotoWebdriver en tanto en local como remoto con Selenium Grid para los navegadores Chrome, Firefox y Edge.
- BasePrueba: Clase que tiene principalmente la inicialización del objeto Logger. También cuenta con distintos métodos para la programación de java.
- UtilLogs: Clase para los logs de la libreria de utilidades. (Bloqueado su funcionamiento hasta solventar el problema para el entorno de linux de la libreria SFL4J)
- UtilPropiedades: Clase con distintas propiedades que ayuda al desarrollo de funciones.
- Logs: Clase para logs. (Esta clase se utilizará en esta libreria hasta solventar el error de la libreria SF4LJ en linux).


## Versiones de la librería con Selenium Grid

[Importante]
- Se debe utilizar siempre la version del driver correspondiente con la versión del navegador donde se ejecutará la prueba.
- Cuando se actualiza un navegador en un entorno remoto o local, también se debe actualizar su driver correspondiente para el correcto funcionamiento de esta librería.
- Los proyectos que utilicen la librería de utilidades es deben tener actualizadas la librería a su última versión.
- Para utilizar el navegador Edge se debe utilizar la librería a partir de la version 1.3141.44 (aconsejable siempre actualizar a la última versión de la librería).


| **VERSION LIBERÍA** | **GECKODRIVER**	 | **CHROMEDRIVER** |   **EDGE**	   | **FECHA LIBRERIA** |                                                                                         **NOTAS**                                                                                          |
|:-------------------:|:----------------:|:----------------:|:-------------:|:------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|      2.3.2			       |        -         |        -         |     -				     |     09/09/2024     |                                 Se ha actualizado con nuevas mejoras para el control de excepciones para RemoteWebdriver, Compilado para la version Java 8                                  |
|      2.2.3			       |        -         |        -         |     -				     |     16/07/2024     |                                                                              Compilado para la version Java 8                                                                              |
|      2.2.2			       |        -         |        -         |     -				     |     01/07/2024     |                                                       Actualzación del readme con ejemplo para la utilizacion desde la version 2.2.1                                                       |
|      2.2.1			       |        -         |        -         |     -				     |     19/06/2024     |                   Se realizan mejoras para incluir metodos configurables con respecto a los argumentos de los navegadores y para hacer parametrizable el valor de urlhub                   |
|    1.3141.49 			    |        -         |        -         |     -				     |     26/12/2022     |                                                             Se actualiza la libreria para ser utilizada solo con Selenium Grid                                                             |
|    1.3141.48 			    |        -         |        -         |     -				     |     22/12/2022     |                                                                          Se actualiza la URL de selenium grid hub                                                                          |
|    1.3141.47 			    |   **v.102.0**    |     **95.0**     | **95.0** 				 |     12/07/2022     | Se actualiza archivo readme.md. La librerias con version 1.3141.47 , 1.3141.46 y 1.3141.45 funcionalmente son iguales, pero el archivo readme.md ha sido actualizado en la ultima version. |
|    1.3141.46 			    |   **v.102.0**    |     **95.0**     | **95.0** 				 |     11/07/2022     |                                                                                    Se actualiza readme                                                                                     |
|    1.3141.45 			    |   **v.102.0**    |     **95.0**     | **95.0** 				 |     11/07/2022     |                                                                          Se actualiza la url del entorno remoto.                                                                           |
|    1.3141.44  		    |     **/**  	     |      **/**       | **/** 	   		  |                    |                                                                                                                                                                                            |
|    1.3141.43  		    |     **/**  	     |      **/**       |  **/** 	  		  |                    |                                                                                                                                                                                            |
|   1.3141.42  			    |     **/**  	     |      **/**       |   **/** 		    |                    |                                                                                                                                                                                            |
|   1.3141.41  			    |     **/**  	     |      **/**       |    **/** 	    |                    |                                                                                                                                                                                            |
|   1.3141.40  			    |     **/**  	     |      **/**       | **/** 	    	  |                    |                                                                                                                                                                                            |



## Anteriores Librerias para utilizarlas en local.
Driver/Navegadores disponibles dentro de la librería (en negrita el driver por defecto según versión)

| **VERSION LIBERÍA** | **GECKODRIVER**	 |   **OS**  	|  **CHROMEDRIVER**  |   **OS**  	   | **IEDRIVERSERVER**	|   **OS**  	|
|:-------------------:|:----------------:|:-----:     	|:------------------:|:-------------:|:-----:        		|:-----:     	|
|   1.3141.39  				   | **v.0.30.0**  	  |Win64, Linux64 | **100.0.4896.20**  |Win32, Linux64 | **Win32_4.0.0** 	    |Win32 			|
|   1.3141.38  				   | **v.0.30.0**  	  |Win64, Linux64 |  **99.0.4844.51**  |Win32, Linux64 | **Win32_4.0.0** 	    |Win32 			|
|   1.3141.37  				   | **v.0.30.0**  	  |Win64, Linux64 | **98.0.4758.102**  |Win32, Linux64 | **Win32_4.0.0** 	    |Win32 			|
|   1.3141.36  				   | **v.0.30.0**  	  |Win64, Linux64 | **99.0.4844.35** 	 |Win32, Linux64 | **Win32_4.0.0** 	    |Win32 			|
|   1.3141.35  				   | **v.0.30.0**  	  |Win64, Linux64 | **96.0.4664.45** 	 |Win32, Linux64 | **Win32_3.150.2** 	|Win32 			| 
|       							       |   v.0.29.1  		   |Win64, Linux64 |   89.0.4389.23		   |Win32, Linux64 | x64_3.150.2       	|Win64	 		| 
|       							       |   v.0.29.0  		   |          		|   90.0.4430.24		   |      		       | 				    	|          		| 
|       							       |      			 		      |          		|   91.0.4472.19		   |      		       |				    	|          		|
|       							       |      			 		      | 			 	|        				        |     				      | 						|				| 
|   1.3141.34  				   | **v.0.29.1**  	  |Win64, Linux64 | **90.0.4430.24** 	 |Win32, Linux64 | **Win32_3.150.1** 	|Win32 			| 
|       							       |   v.0.29.0  		   |Win64, Linux64 |   89.0.4389.23		   |Win32, Linux64 | x64_3.150.1       	|Win64	 		| 
|       							       |   v.0.28.0  		   |          		|   88.0.4324.27		   |      		       | 				    	|          		| 
|       							       |      			 		      |          		|   91.0.4472.19		   |      		       |				    	|          		|
|       							       |      			 		      | 			 	|       					        |     				      | 						|				| 
|   1.3141.32					    | **v.0.29.0**  	  |Win64, Linux64 | **88.0.4324.96** 	 |Win32, Linux64 | **Win32_3.150.1** 	|Win32 			| 
|       							       |   v.0.28.0  		   |Win64, Linux64 |   89.0.4389.23		   |Win32, Linux64 | x64_3.150.1       	|Win64	 		| 
|       							       |      					       |          		|   88.0.4324.27		   |      		       | 				    	|          		| 
|       							       |      			 		      |          		|   87.0.4280.88		   |      		       |				    	|          		|
|       							       |      			 		      | 			 	|       					        |     				      | 						|				| 
|   1.3141.29					    | **v.0.28.0**  	  |Win64, Linux64 | **87.0.4280.88** 	 |Win32, Linux64 | **Win32_3.150.1** 	|Win32			| 
|       							       |   v.0.27.0  		   |Win64, Linux64 |  88.0.4324.27 		   |Win32, Linux64 | x64_3.150.1       	|Win64			| 
|       							       |  v.0.26.0		  	   |Win64, Linux64 |   86.0.4240.22		   |Win32, Linux64 |				    	|	       		|
|       							       |      			 		      | 			 	|   85.0.4183.87		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|       					        |     				      | 						|				| 
|   1.3141.28					    | **v.0.27.0**	  	 |Win64, Linux64 | **85.0.4183.87**	  |Win32, Linux64 | **Win32_3.150.1** 	|Win32			| 
|       							       |  v.0.26.0		  	   |Win64, Linux64 |   84.0.4147.30		   |Win32, Linux64 | x64_3.150.1       	|Win64			| 
|       							       |      			 		      | 			 	|   83.0.4103.39		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|  81.0.4044.138		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|  80.0.3987.16 		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|  79.0.3945.36 		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|  78.0.3904.105		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|       					        |     				      | 						|				|
|   1.3141.27					    |   v0.26.0	 		    |Win64, Linux64 |  84.0.4147.30 		   |Win32, Linux64 | **Win32_3.150.1** 	|Win32			| 
|       							       |      			 		      | 			 	|  83.0.4103.39 		   |Win32, Linux64 | x64_3.150.1       	|Win64			| 
|       							       |      			 		      | 			 	|  81.0.4044.138		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|  80.0.3987.16 		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|  79.0.3945.36 		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|  78.0.3904.105		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|       					        |     				      | 						|				|
|   1.3141.26					    |   v0.26.0	 		    |Win64, Linux64 |  84.0.4147.30 		   |Win32, Linux64 | **Win32_3.150.1** 	|Win32			| 
|       							       |      			 		      | 			 	|  83.0.4103.39 		   |Win32, Linux64 | x64_3.150.1       	|Win64			| 
|       							       |      			 		      | 			 	|  81.0.4044.138		   |Win32, Linux64 | 3.14.0.0       		|Win32			| 
|       							       |      			 		      | 			 	|  80.0.3987.16 		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|  79.0.3945.36 		   |Win32, Linux64 | 						|				|
|       							       |      			 		      | 			 	|  78.0.3904.105		   |Win32, Linux64 | 						|				|


## Ruta descarga drivers
|  **Navegador** 			        | **Descarga WebDriver**       |  
|:-----:        					|:-----:        		        |
|geckodriver 						| https://github.com/mozilla/geckodriver/releases  		        |
|chromedriver 						| https://sites.google.com/a/chromium.org/chromedriver/downloads				        |
|IEDriverServer 					| https://www.selenium.dev/downloads/		        |
