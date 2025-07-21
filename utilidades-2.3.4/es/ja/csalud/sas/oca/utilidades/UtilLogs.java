package es.ja.csalud.sas.oca.utilidades;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class UtilLogs {


	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(UtilLogs.class.getName());

	/** PATH LOGS **/
	private String pathLogs = "logs\\";
	private String fileNameLog = "ficheroLog";
	private String maskDate = "yyyyMMddHHmmss";

	/**
	 * @param pathLogParam
	 * @param ficheroLogParam
	 * @param maskDateParam
	 */
	public UtilLogs(String pathLogParam, String ficheroLogParam, String maskDateParam ) {
		super();
		this.pathLogs = pathLogParam;
		this.fileNameLog = ficheroLogParam;
		this.maskDate = maskDateParam;
	}

	/**
	 * @param pathLogParam
	 * @param ficheroLogParam
	 */
	public UtilLogs(String pathLogParam, String ficheroLogParam) {
		super();
		this.pathLogs = pathLogParam;
		this.fileNameLog = ficheroLogParam;
	}

	/**
	 * @param pathLogParam
	 */
	public UtilLogs(String pathLogParam) {
		super();
		this.pathLogs = pathLogParam;
	}

	public UtilLogs() {

		super();
	}


	/**
	 * @return the pathLogs
	 */
	public String getPathLogs() {
		return pathLogs;
	}


	/**
	 * @param pathLogs the pathLogs to set
	 */
	public void setPathLogs(String pathLogs) {
		this.pathLogs = pathLogs;
	}


	/**
	 * @return the fileNameLog
	 */
	public String getFileNameLog() {
		return fileNameLog;
	}


	/**
	 * @param fileNameLog the fileNameLog to set
	 */
	public void setFileNameLog(String fileNameLog) {
		this.fileNameLog = fileNameLog;
	}


	/**
	 * @return the dateFormat
	 */
	public String getDateFormat() {
		return maskDate;
	}


	/**
	 * @param dateFormat the dateFormat to set
	 */
	public void setDateFormat(String dateFormat) {
		this.maskDate = dateFormat;
	}
}
