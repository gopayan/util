package es.ja.csalud.sas.oca.utilidades;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by rgarcial on 21/04/2017.
 */
public abstract class BasePrueba {
    private static final String PATH_RESOURCES = "src/test/resources/";
    private static final String SEPARATOR_CSV = ";";
    private static final String NOMBRESO = "os.name";
    private static final String RUTAHOME = "user.home";

    private String rutaLog = rutaDirectorioLog();
    private String rutaImagen = rutaDirectorioImagen();
    private String rutaDescarga = rutaDirectorioDescargas();
    public Logs log = new Logs();

    protected BasePrueba() {
    }

    public BasePrueba(String nombrelogger) {
        super();
    
    }

    public String getRutaDescarga() {
        return rutaDescarga;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public String getRutaLog() {
        return rutaLog;
    }

    private String rutaDirectorioLog() {
        String ruta;
        if (Boolean.TRUE.equals(estoyEnWindows())) {
            ruta = "C:" + File.separator + "OCA" + File.separator + "Logs" + File.separator;
        } else {
            ruta = System.getProperty(RUTAHOME) + File.separator + "OCA" + File.separator + "Logs" + File.separator;
        }
        return ruta;
    }

    private String rutaDirectorioImagen() {
        String ruta;
        if (Boolean.TRUE.equals(estoyEnWindows())) {
            ruta = "C:" + File.separator + "OCA" + File.separator + "Imagenes" + File.separator;
        } else {
            ruta = System.getProperty(RUTAHOME) + File.separator + "OCA" + File.separator + "Imagenes" + File.separator;
        }
        return ruta;
    }

    private String rutaDirectorioDescargas() {
        String ruta;
        if (Boolean.TRUE.equals(estoyEnWindows())) {
            ruta = "C:" + File.separator + "OCA" + File.separator + "tmp";
        } else {
            ruta = System.getProperty(RUTAHOME) + File.separator + "OCA" + File.separator + "tmp";
        }
        return ruta;
    }


    public String getResourcesByBrowser(String browser) {
        String result;
        switch (browser) {
            case "firefox":
                result = PATH_RESOURCES + "firefox";
                break;
            case "internet explorer":
                result = PATH_RESOURCES + "ie";
                break;
            case "chrome":
                result = PATH_RESOURCES + "chrome";
                break;
            default:
                result = "ie";
        }
        return result;
    }

    /**
     * Método que descifra la clave en Base64 previamente codificada.
     *
     * @return string
     */
    public String obtenerCadenaDescodificada(String entrada) {
        byte[] valorDescodificado = Base64.decodeBase64(entrada);

        return new String(valorDescodificado);
    }

    public List<String> listarPropiedadesQueEmpiecenPor(String fichero, String cadenaComienzo) {
        List<String> propiedades = new ArrayList<>();
        UtilPropiedades pDatosPrueba = UtilPropiedades.getInstance();
        try {
            Properties props = pDatosPrueba.leerFichero(fichero);
            for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements(); ) {
                String name = (String) e.nextElement();
                String value = props.getProperty(name);
                if (name.startsWith(cadenaComienzo)) {
                    propiedades.add(value);
                }
            }
        } catch (Exception e) {
            log.error("-- LISTAR PROPIEDADES QUE EMPIECEN POR - " + e.getMessage(), e);
        }
        return propiedades;
    }

    /**
     * Existe fichero boolean.
     *
     * @param nombreFichero the nombre fichero
     * @return the boolean
     */
    public boolean existeFichero(String nombreFichero) {
        return new File(nombreFichero).exists();
    }

    /**
     * Método que busca un fichero según el directorio que le pasemos y el inicio y final del nombre del fichero
     *
     * @param inicio inicio del nombre del fichero
     * @param ultimo final del nombre del fichero
     * @return devuelve verdadero si lo encuentra y falso si no
     */
    public boolean existeFichero(String pathToScan, String inicio, String ultimo) {
        String fileThatYouWantToFilter;
        File folderToScan = new File(pathToScan);
        File[] listOfFiles = folderToScan.listFiles();
        boolean correcto = Boolean.FALSE;
        if (listOfFiles != null){
            for (int i = 0; i < listOfFiles.length && !correcto; i++) {
                if (listOfFiles[i].isFile()) {
                    fileThatYouWantToFilter = listOfFiles[i].getName();
                    if (inicio != null && fileThatYouWantToFilter.startsWith(inicio)
                            && ultimo != null && fileThatYouWantToFilter.endsWith(ultimo)) {
                        correcto = Boolean.TRUE;
                    }
                }
            }
        }
        return correcto;
    }

    /**
     * Método para buscar ficheros descargados en la ruta predefinida en rutaDescarga
     *
     * @param nombreFichero nombre del fichero a buscar
     * @return <ul>
     * <li>true: si existe el fichero</li>
     * <li>false: no existe el fichero</li>
     * </ul>
     */
    public boolean existeFicheroDescargado(String nombreFichero) {
        return new File(getRutaDescarga().concat(File.separator + nombreFichero)).exists();
    }

    /**
     * Método para buscar ficheros descargados en la ruta predefinida en rutaDescarga
     *
     * @param inicio
     * @param ultimo <ul>
     *               * <li>true: si existe el fichero</li>
     *               * <li>false: no existe el fichero</li>
     *               * </ul>
     */
    public boolean existeFicheroDescargado(String inicio, String ultimo) {
        String pathToScan = getRutaDescarga();
        String fileThatYouWantToFilter;
        File folderToScan = new File(pathToScan);
        File[] listOfFiles = folderToScan.listFiles();
        boolean correcto = Boolean.FALSE;
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length && !correcto; i++) {
                if (listOfFiles[i].isFile()) {
                    fileThatYouWantToFilter = listOfFiles[i].getName();
                    if (inicio != null && fileThatYouWantToFilter.startsWith(inicio)
                            && ultimo != null && fileThatYouWantToFilter.endsWith(ultimo)) {
                        correcto = Boolean.TRUE;
                    }
                }
            }
        }
        return correcto;
    }

    /**
     * Comprobarfichero con datos boolean.
     *
     * @param nombreFichero the nombre fichero
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean comprobarficheroConDatos(String nombreFichero) {
        boolean bResultado = false;
        String line = null;

        if (existeFichero(nombreFichero)) {
            try (FileReader fr = new FileReader(getRutaLog().concat(nombreFichero));
                 BufferedReader br = new BufferedReader(fr);) {
                if ((line = br.readLine()) != null) {
                    bResultado = true;
                    log.info(line);
                } else {
                    log.error("ERROR: EL FICHERO ESTÁ VACÍO");
                }
            } catch (IOException e) {
                log.error("ERROR", e);
            }
        }
        return bResultado;
    }


    /**
     * ComprobarficheroDescargado con datos boolean.
     *
     * @param nombreFichero the nombre fichero
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean comprobarficheroDescargadoConDatos(String nombreFichero) {
        boolean bResultado = false;
        String line = null;

        if (existeFicheroDescargado(nombreFichero)) {
            try (FileReader fr = new FileReader(getRutaLog().concat(nombreFichero));
                 BufferedReader br = new BufferedReader(fr);) {
                if ((line = br.readLine()) != null) {
                    bResultado = true;
                    log.info(line);
                } else {
                    log.error("ERROR: EL FICHERO ESTÁ VACÍO");
                }
            } catch (IOException e) {
                log.error("ERROR",e);
            }
        }
        return bResultado;
    }

    /**
     * Borrar fichero.
     *
     * @param nombreDirectorio Nombre del fichero o directorio a borrar
     * @throws IOException the io exception
     */
    public void borrarFichero(String nombreDirectorio) {
        try {
            if (existeFichero(nombreDirectorio)) {
                FileUtils.forceDelete(new File(nombreDirectorio));
            }
        } catch (IOException e) {
            log.error("HA OCURRIDO UN ERROR AL BORRAR EL DIRECTORIO / FICHERO -> " + nombreDirectorio);
        }
    }

    /**
     * Método para borrar un fichero sabiendo el comienzo y el final del nombre de dicho fichero
     *
     * @param pathToScan
     * @param inicio
     * @param ultimo
     */
    public void borrarFichero(String pathToScan, String inicio, String ultimo) {
        String fileThatYouWantToFilter;
        File folderToScan = new File(pathToScan);
        File[] listOfFiles = folderToScan.listFiles();
        try {
            if (listOfFiles != null) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        fileThatYouWantToFilter = listOfFiles[i].getName();
                        if (fileThatYouWantToFilter.startsWith(inicio)
                                && fileThatYouWantToFilter.endsWith(ultimo)) {
                            FileUtils.forceDelete(new File(fileThatYouWantToFilter));
                        }
                    }
                }
            }  else {
                log.error("NO EXISTE LA RUTA {}", pathToScan );

            }
        } catch (IOException e) {
            log.error("HA OCURRIDO UN ERROR AL BORRAR EL FICHERO QUE SE UBICA EN -> {} Y QUE EMPIEZA POR -> {} Y FINALIZA POR -> {}", pathToScan, inicio, ultimo);
        }
    }

    /**
     * Comprobar cabecera csv boolean.
     *
     * @param nombreFichero the nombre fichero
     * @param propCabecera  the prop cabecera
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean comprobarCabeceraCSV(String nombreFichero, String propCabecera) {
        boolean bResultado = false;
        String primeraFila = null;
        String[] cabeceraEsperada = propCabecera.split(SEPARATOR_CSV);
        String[] cabeceraFichero = null;

        if (existeFichero(nombreFichero)) {
            try (BufferedReader br = new BufferedReader(new FileReader(getRutaLog().concat(nombreFichero)));) {
                primeraFila = br.readLine();
                if (primeraFila == null) {
                    log.error("ERROR: EL FICHERO ESTÁ VACÍO");
                } else {
                    cabeceraFichero = primeraFila.split(SEPARATOR_CSV);
                    if (Arrays.equals(cabeceraFichero, cabeceraEsperada)) {
                        bResultado = true;
                    }
                }
            } catch (IOException e) {
                log.error("ERROR",e);
            }
        }
        return bResultado;
    }

    /**
     * Comprobar cabecera ficheroDescargado csv boolean.
     *
     * @param nombreFichero the nombre fichero
     * @param propCabecera  the prop cabecera
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean comprobarCabeceraFicheroDescargadoCSV(String nombreFichero, String propCabecera) {
        boolean bResultado = false;
        String primeraFila = null;
        String[] cabeceraEsperada = propCabecera.split(SEPARATOR_CSV);
        String[] cabeceraFichero = null;

        if (existeFicheroDescargado(nombreFichero)) {
            try (BufferedReader br = new BufferedReader(new FileReader(getRutaLog().concat(nombreFichero)));) {
                primeraFila = br.readLine();
                if (primeraFila == null) {
                    log.error("ERROR: EL FICHERO ESTÁ VACÍO");
                } else {
                    cabeceraFichero = primeraFila.split(SEPARATOR_CSV);
                    if (Arrays.equals(cabeceraFichero, cabeceraEsperada)) {
                        bResultado = true;
                    }
                }
            } catch (IOException e) {
                log.error("ERROR", e);
            }
        }
        return bResultado;
    }

    /**
     * Método que devuelve la diferencia entre dos fechas en días
     *
     * @param fechaBaja
     * @param fechaAlta
     * @param formato
     * @return
     */
    public String diferenciaEntreFechas(String fechaBaja, String fechaAlta, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        Date firstDate;
        Date secondDate;
        long resta = 0;
        int diferencia = 0;
        try {
            firstDate = sdf.parse(fechaBaja);
            secondDate = sdf.parse(fechaAlta);
            resta = secondDate.getTime() - firstDate.getTime();
            diferencia = (int) (resta / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            log.error("Error al calcular la diferencia de fechas en días");
        }
        return String.valueOf(diferencia);
    }

    public String calcularEdadDias(String fecha, String formato) {
        LocalDate today = LocalDate.now();
        LocalDate birthday;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formato);
        long days;
        birthday = LocalDate.parse(fecha, dtf);
        days = ChronoUnit.DAYS.between(birthday, today);
        return String.valueOf(days);
    }

    public String calcularEdadMeses(String fecha, String formato) {
        LocalDate today = LocalDate.now();
        LocalDate birthday;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formato);
        long months;
        birthday = LocalDate.parse(fecha, dtf);
        months = ChronoUnit.MONTHS.between(birthday, today);
        return String.valueOf(months);
    }

    public String calcularEdadAnos(String fecha, String formato) {
        LocalDate today = LocalDate.now();
        LocalDate birthday;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formato);
        long years;
        birthday = LocalDate.parse(fecha, dtf);
        years = ChronoUnit.YEARS.between(birthday, today);
        return String.valueOf(years);
    }

    /**
     * Obtener la fecha actual segun el formato que le pasemos
     *
     * @param formato
     * @return
     */
    public String obtenerFechaHoyFormato(String formato) {
        Date date;
        SimpleDateFormat format;
        String fecha;

        date = new Date();
        format = new SimpleDateFormat(formato);
        fecha = format.format(date);

        return fecha;
    }

    /**
     * Método para validar si una fecha tiene el formato correcto
     *
     * @param fecha   fecha a comprobar
     * @param formato formato que tiene que tener la fecha a comprobar
     * @return verdadero si la fecha tiene el formato y falso si no lo es
     */
    public boolean validarFecha(String fecha, String formato) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            date = sdf.parse(fecha);
            if (!fecha.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return date != null;
    }

    /**
     * Sumar restar dias fecha date.
     *
     * @param fecha the fecha
     * @param dias  the dias
     * @return the date
     */
    public Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    /**
     * Método que sumar días a la fecha de hoy.
     */
    public String sumarRestarDiasFechaHoy(int dias) {
        LocalDateTime fechaHora = LocalDateTime.now().plusDays(dias);
        return obtenerFecha(fechaHora);
    }

    /**
     * Obtener Fecha string.
     *
     * @param lFecha the l fecha
     * @return the string Ex: 12/
     */
    public String obtenerFecha(LocalDateTime lFecha) {
        String retorno = "";
        try {
            retorno = retorno
                    .concat(StringUtils.leftPad(String.valueOf(lFecha.getDayOfMonth()), 2, '0')).concat("/")
                    .concat(StringUtils.leftPad(String.valueOf(lFecha.getMonthValue()), 2, '0')).concat("/")
                    .concat(String.valueOf(lFecha.getYear())).concat(" ")
                    .concat(StringUtils.leftPad(String.valueOf(lFecha.getHour()), 2, '0')).concat(":")
                    .concat(StringUtils.leftPad(String.valueOf(lFecha.getMinute()), 2, '0'));
        } catch (Exception excep) {
            retorno = ""; // unparseable date
        }
        return retorno;
    }

    /**
     * Dar un número aleatorio
     *
     * @param min
     * @param max
     * @return
     */
    public int numeroAleatorio(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public Boolean estoyEnWindows() {
        String so = System.getProperty(NOMBRESO);
        return so.contains("Windows");
    }
}
