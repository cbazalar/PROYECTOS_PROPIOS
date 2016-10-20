package biz.belcorp.ssicc.service.sisicc.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.ReporteExecutionService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteResult;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailResult;
import biz.belcorp.ssicc.service.sisicc.util.exception.StoringFileOverFtpException;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.service.util.ZipUtil;

/**
 * Clase abstracta para las Reportes SSIcc desde el service. Define un flujo basico de
 * ejecucion de un reporte de Jasper Reports.
 * 
 * */ 
@Service("scsicc.baseReporteAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseReporteAbstractService extends BaseService {
	
	protected static final String JASPER_DIRECTORIO = "/biz/belcorp/ssicc/reportes/jasper/";
	
	protected static final String JASPER_EXTENSION = ".jasper";

	protected static final String PDF_EXTENSION = ".pdf";

	protected static final String XLS_EXTENSION = ".xls";

	protected static final String CSV_EXTENSION = ".csv";
	
	protected static final String TXT_EXTENSION = ".txt";

	protected static final String CONTENT_TYPE_PDF = "application/pdf";

	protected static final String CONTENT_TYPE_HTML = "text/html";

	protected static final String CONTENT_TYPE_XLS = "application/vnd.ms-excel";

	protected static final Integer MAXIMUM_ROWS_PER_SHEET_XLS = new Integer(50000);

	protected static final String DEFAULT_ERROR_FORWARD = "errorReporte";

	protected static final String NOMBRE_REPORTE_VIRTUALIZADO = "reporteGenerico";
	
	protected static final String DEFAULT_REPORTE_ORACLE_FORWARD = "dwReporteOracle";

	private boolean virtualizador;

	private String  exportacion;
	
	private String  nombreReporte;
	
	private boolean mostrarLogPantalla = true;
	
	private boolean visualizarReporte = true;
	
	private String  nombreArchivoReporte;
	
	private String  prefijoArchivo;
	
	private int     nroReporteProcesando = 1;
	
	private boolean enviarporCorreo = false;
	
	private boolean isDeleteTemporal=true;
	
	private boolean isErrorEnvioEmail=false;
	
	private String messageErrorEmail;//este mensaje se muestra por pantalla
	
	private boolean generateTabsXLS = false;
	
	private static final char SEPARATOR = ';';
	
	@Resource(name="scsicc.reporteService")
	protected ReporteService reporteService;
	
	@Resource(name="scsicc.reporteExecutionService")
	protected ReporteExecutionService reporteExecutionService; 
	
	@Resource(name="idiomaService")
	protected IdiomaService service;
	
	@Resource(name="sisicc.interfazSiCCService")
	private InterfazSiCCService interfazSiCCService;
	
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	
	protected BaseMailService mailService;
	
	private boolean enviarporFTP = false;
	private boolean indicadorMultiReporte = false;
	private String nombreReporteAuditoria;
	protected HistoricoReporte currentHistoricoReporte;
	protected String codigoMenu;
	
	/**
	 * @return
	 */
	protected String getSubReporteFileName() {
		return "";
	}

	/**
	 * @param interfazSiCCService the interfazSiCCService to set
	 */
	public void setInterfazSiCCService(InterfazSiCCService interfazSiCCService) {
		this.interfazSiCCService = interfazSiCCService;
	}


	/**
	 * @param generateTabsXLS the generateTabsXLS to set
	 */
	public void setGenerateTabsXLS(boolean generateTabsXLS) {
		this.generateTabsXLS = generateTabsXLS;
	}

		
	/**
	 * Hook method, despues de llamar a
	 * <code>reporteExecutionService.executeReporte(reporteParams)</code>.
	 * Puede ser sobrescrito para realizar alguna logica al final de la
	 * ejecucion del reporte, por ejemplo la llamada a un Store Procedure para
	 * eliminar alguna tabla temporal.
	 * 
	 * @param reporteParams
	 *            parametros del reporte
	 * @throws Exception
	 */
	protected void afterExecuteReporte(ReporteParams reporteParams)
			throws Exception {
	}
	
	/**
	 * Metodo que se ejecuta despues de la ejecucion Principal de GrabarReporte. 
	 * Dicho metodo puede ser sobreescrito 
	 */
	protected void afterGrabarReporte(Map params) {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'afterGrabarReporte' method");
		}
		
		this.visualizarReporte  = true;
		this.mostrarLogPantalla = true;
			
		/* Obteniendo Lista con el Log de Reportes */
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		params.put("usuarioLogin", usuario.getLogin());
		List resultado = reporteService.getLogReporteDisco(params);

		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'afterGrabarReporte Resultado size:' " + resultado.size());
		}
	}
	
	/**
	 * Hook method, antes de llamar a
	 * <code>reporteExecutionService.executeReporte(reporteParams)</code>.
	 * Puede ser sobrescrito para realizar alguna logica antes de la ejecucion
	 * del reporte, por ejemplo la llamada a un Store Procedure para llenar
	 * tablas temporales para la ejecucion del reporte.
	 * 
	 * @param reporteParams
	 *            parametros del reporte
	 * @throws Exception
	 */
	protected void beforeExecuteReporte(ReporteParams reporteParams)
			throws Exception {
	}
	/**
	 * Metodo que se ejecuta antes de la ejecucion Principal de GrabarReporte. 
	 * Dicho metodo puede ser sobreescrito 
	 */
	protected void beforeGrabarReporte() {		
		this.mostrarLogPantalla = false;
		
	}
	protected void configReporteParams(ReporteParams reporteParams) {
	}

		/**
	 * Hook method para la ejecucion del Reporte. Esta implementacion devuelve
	 * siempre true y siempre se ejecuta el Reporte. En caso que el Reporte no
	 * se deba ejecutar debido a algun valor en los parametros se puede
	 * sobrescribir este metodo.
	 * 
	 * @param params
	 *            parametros del Reporte
	 * @return true si se va a ejecutar el Reporte, false en caso de que no se
	 *         ejecute
	 */
	protected boolean continueExecuteReporte(ReporteParams reporteParams) {
		return true;
	}
	
	/**
	 * @param request
	 * @throws IOException
	 */
	private Map copiarReporteServidor(ReporteParams reporteParams) throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'grabarReporteDisco' method");
		}
		if (StringUtils.isBlank(this.nombreReporte)) {
			this.nombreReporte = this.getReporteFileName();
		}
		if (this.visualizarReporte) {
			return null;
		}
		Map queryParams = reporteParams.getQueryParams();
		Map parameterMap = (Map) queryParams.get("parameterMap");
		/* Obteniendo directorio del repositorio a grabar */
		Map criteria = new HashMap();		
		criteria.put("nombreReporte",this.nombreReporte);
		criteria.put("codigoPais", parameterMap.get("codigoPais"));
		Map paramReporte = reporteService.getParametrosReporte(criteria);
		String enviarCorreo = (String) paramReporte.get("enviarCorreo");
		if (Constants.SI.equals(enviarCorreo)) {
			this.enviarporCorreo = true;
		} else {
			this.enviarporCorreo = false;
		}
		
		String enviarFTP = (String) paramReporte.get("enviarFTP");
		if (Constants.SI.equals(enviarFTP)) {
			this.enviarporFTP = true;
		} else {
			this.enviarporFTP = false;
		}
		
		/* Generando Reporte en disco */
		if (paramReporte != null) {
			this.prefijoArchivo = (String) paramReporte.get("prefijoArchivo");
			this.nombreArchivoReporte = this.getNombreArchivoReporte(reporteParams) + this.getExtensionReporte();
			
			String directorioRepositorio = (String) paramReporte.get("directorioRepositorio");
		    if (!StringUtils.isBlank(directorioRepositorio)) {
		    	String rutaPath = (String) parameterMap.get("rutaPath");
		    	
				File srcFile = new File(rutaPath,
							       NOMBRE_REPORTE_VIRTUALIZADO + this.getExtensionReporte());
				File destFile = new File(directorioRepositorio,	this.nombreArchivoReporte);
				FileUtils.copyFile(srcFile, destFile);

				/* En caso el reporte se envie por Correo */
				if (this.enviarporCorreo) {
					
					queryParams.put("nombreReporte", this.nombreReporte);
					queryParams.put("fileAttachment", destFile);
					queryParams.put("nombreArchivoReporte", this.nombreArchivoReporte);
					reporteParams.setQueryParams(queryParams);
				}
				
				/* En caso el reporte se envie por FTP */
				if (this.enviarporFTP) {
					
					queryParams.put("nombreReporte", this.nombreReporte);
					queryParams.put("fileAttachment", destFile);
					queryParams.put("nombreArchivoReporte", this.nombreArchivoReporte);
					reporteParams.setQueryParams(queryParams);
				}
			}
		}
		else {
			Usuario usuario = (Usuario)parameterMap.get("usuarioTemp");
			String mensajeError = messageSource.getMessage("reporte.error.ingresarParametros",null,getLocale(usuario));			
			throw new Exception(mensajeError);
		}
		return paramReporte;
	}

	/**
	 * @param request
	 * @param form
	 * @return
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return null;
	}
	
	/**
	 * Logica para generar Reportes a traves de Oracle
	 * @param parameterMap
	 */
	public final void downloadReporte(Map parameterMap) throws Exception {
		
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'downloadReporte' method");
		}
		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		this.exportacion = formatoExportacion;
		
		String extensionArchivo = new String("");
		if (formatoExportacion.equals("OTXT"))
			extensionArchivo = TXT_EXTENSION;
		if (formatoExportacion.equals("OCSV"))
			extensionArchivo = CSV_EXTENSION;
		if (formatoExportacion.equals("OXLS"))
			extensionArchivo = XLS_EXTENSION;
		
		/* Generando Nombre del Archivo */
		String nombreArchivo = this.generarNombreArchivoReporteOracle(parameterMap);
		parameterMap.put("nombreArchivo", nombreArchivo);
		parameterMap.put("extensionArchivo", extensionArchivo);
		if (formatoExportacion.equals("OXLS")) {
			parameterMap.put("nombreArchivoExcel", nombreArchivo);
			parameterMap.put("extensionArchivoExcel", XLS_EXTENSION);
			parameterMap.put("nombreArchivo", nombreArchivo + "_TEMP");
			parameterMap.put("extensionArchivo", TXT_EXTENSION);
		}
		String tituloReporteOracle = obtieneTituloReporteOracle(parameterMap);
		parameterMap.put("tituloReporteOracle", tituloReporteOracle);
		
		String tipoDatos = obtieneTipoDatosReporteOracle(parameterMap);
		parameterMap.put("tipoDatosOracle", tipoDatos);
		
		/* Generando Reporte en Oracle */
		String directorioArchivo = new String("   ");
		parameterMap.put("directorioArchivo", directorioArchivo);
		parameterMap = this.generarReporteOracle(parameterMap);
		
		/* Generando Archivo Excel en caso sea de ese tipo */
		if (formatoExportacion.equals("OXLS"))
			parameterMap = this.generarExcelOracle(parameterMap);
		
		/* Pasando los valores a sesion */
		if (this.log.isDebugEnabled()) {
			this.log.debug("directorioArchivo: "+ directorioArchivo);
			this.log.debug("nombreArchivo: "+ nombreArchivo);
			this.log.debug("extensionArchivo: "+ extensionArchivo);
			this.log.debug("parameterMap: "+ parameterMap.toString());
			this.log.debug("Final 'downloadReporte' method");
		}
		
		if (!this.indicadorMultiReporte) {
			this.currentHistoricoReporte.setMultiReporte(Constants.NO);
			this.currentHistoricoReporte.setNumeroMultireportes(new Long(1));
			this.currentHistoricoReporte.setEnvioMail(Constants.NO);
			this.currentHistoricoReporte.setNombreArchivoReporte(nombreArchivo + extensionArchivo);
			try {
				this.updateHistoricoReportesValues(Constants.NO);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	/**
	 * Obtiene Usuario y Pais respectivo
	 * @param parameterMap
	 * @return
	 */
	private Map obtenerPaisyUsuario(Map parameterMap) {
		String codigoUsuario = "";
		String codigoPais = "";
		
		/* Obtener usuario */
		try {
			Usuario usuario = (Usuario) parameterMap.get("usuario");
			codigoUsuario = usuario.getLogin();
		}
		catch (Exception e) {
			try {
				codigoUsuario = (String) parameterMap.get("codigoUsuario");
			}
			catch (Exception ex) {
				try {
					Usuario usuario = (Usuario) parameterMap.get("usuarioTemp");
					codigoUsuario = usuario.getLogin();
				}
				catch (Exception ex1) {
					codigoUsuario = "";
				}
			}
		}
		
		/* Obtener Pais */
		try {
			Pais pais = (Pais) parameterMap.get("pais");
			codigoPais = pais.getCodigo();
		}
		catch (Exception e) {
			try {
				codigoPais = (String) parameterMap.get("codigoPais");
			}
			catch (Exception ex) {
				codigoPais = "";
			}
		}
		parameterMap.put("codigoUsuario", codigoUsuario);
		parameterMap.put("codigoPais", codigoPais);
		return parameterMap;
	}
	
	/**
	 * Obtiene key para encontrar Titulo de Reporte en Oracle
	 * @param parameterMap
	 * @return
	 */
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		return null;
	}
	
	/**
	 * Obtiene Tipo de Datos para los campos a generar en el Reporte en Oracle
	 * @param parameterMap
	 * @return
	 */
	public String obtieneTipoDatosReporteOracle(Map parameterMap) {
		return null;
	}
	
	
	/**
	 * Devuelve en caso exista el Titulo para el Reporte en Oracle
	 * @param request
	 * @param parameterMap
	 * @return
	 */
	protected final String obtieneTituloReporteOracle(Map parameterMap) {
		String key = obtieneKeyTituloReporteOracle(parameterMap);
		Usuario usuario= (Usuario)parameterMap.get("usuarioTemp");
		String titulo = new String();
		if (key != null) {
			titulo = messageSource.getMessage(key,null,getLocale(usuario));				 
		}	
		return titulo;
	}
	
	/**
	 * Generando Archivo Excel en base al archivo generado por Oracle
	 * @param parameterMap
	 * @return
	 */
	protected final Map generarExcelOracle(Map parameterMap) {
		FileOutputStream fileOutputStream = null;
		try  {			
			String archivo = (String) parameterMap.get("nombreArchivo");
			String archivoExcel = (String) parameterMap.get("nombreArchivoExcel");
			String extension = (String) parameterMap.get("extensionArchivo");
			String extensionExcel = (String) parameterMap.get("extensionArchivoExcel");
			String directorio = (String) parameterMap.get("directorioArchivo");
		    String filePathTemporal = directorio + archivo + extension;
		    String filePathFinal= directorio + archivoExcel + extensionExcel;
			
			fileOutputStream = new FileOutputStream(filePathFinal);
			ExcelUtil excelUtil = new ExcelUtil(fileOutputStream);
			log.debug("Se creo object ExcelUtil");
			
		    File inputFile = new File(filePathTemporal);
		    
		    int registrosExcel = 0;
		    int hojaActual = 1;
		    LineIterator lineIterator = null;
			String inputLine = null;
			log.debug("Procesando el archivo de proceso temporal... ");
			lineIterator = FileUtils.lineIterator(inputFile, null);
			
			String tipoDatosOracle = (String) parameterMap.get("tipoDatosOracle");
			String[] tipo = new String[0];
			if (StringUtils.isNotBlank(tipoDatosOracle))
				tipo = StringUtils.splitPreserveAllTokens(tipoDatosOracle,	SEPARATOR);

			registrosExcel = generarTituloHojaExcel(parameterMap, excelUtil, registrosExcel, lineIterator);
			while (lineIterator.hasNext()) {
				inputLine = lineIterator.nextLine();
				
                // Formateamos la linea leda
				short columna = 0;
                String[] row = StringUtils.splitPreserveAllTokens(inputLine, SEPARATOR);
				for (int i = 0 ; i < row.length; i++) {
					excelUtil.setColumna(columna);
					if (StringUtils.isNotBlank(tipoDatosOracle))
						excelUtil.generarCeldaExcelGrande(tipo[i].trim(), row[i]);
					else
						excelUtil.generarCeldaExcelGrande(Constants.TIPO_DATO_CARACTER, row[i]);
					columna++;
				}
				excelUtil.setRegistroExcelGrande(registrosExcel);					
				registrosExcel++;
				if (registrosExcel > MAXIMUM_ROWS_PER_SHEET_XLS) {
					hojaActual++;
					excelUtil.cerrarOutput();
					filePathFinal= directorio + archivoExcel + "_" + new Integer(hojaActual).toString().trim() + extensionExcel;
					fileOutputStream = new FileOutputStream(filePathFinal);
					excelUtil = new ExcelUtil(fileOutputStream);
					registrosExcel = 0;
					registrosExcel = generarTituloHojaExcel(parameterMap, excelUtil, registrosExcel, lineIterator);
				}
			}
			excelUtil.cerrarOutput();
			
			/* Zipiando archivos */
			String[] filesAZipiar = new String[hojaActual];
		    filesAZipiar[0] = new String(directorio + archivoExcel + extensionExcel);
		    for(int i=2; i <= hojaActual; i++)
		    	filesAZipiar[i - 1] = new String(directorio + archivoExcel + "_" + new Integer(i).toString().trim() + extensionExcel);
		   
		    String extensionZIP = ".ZIP";		
			parameterMap.put("nombreArchivo", archivoExcel);
			parameterMap.put("extensionArchivo", extensionZIP);
			filePathFinal= directorio + archivoExcel + extensionZIP;
			ZipUtil.zipFiles(filesAZipiar,filePathFinal);
			
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return parameterMap;
	}


	/**
	 * Generar Titulo en Hoja de Excel
	 * @param parameterMap
	 * @param excelUtil
	 * @param registrosExcel
	 * @param lineIterator
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
	private int generarTituloHojaExcel(Map parameterMap, ExcelUtil excelUtil, int registrosExcel, LineIterator lineIterator)
			throws Exception {
		String inputLine;
		String tituloOracle = (String) parameterMap.get("tituloReporteOracle");
		if (StringUtils.isNotBlank(tituloOracle)) {
			inputLine = lineIterator.nextLine();
			short columna = 0;
			String[] row = StringUtils.splitPreserveAllTokens(inputLine, SEPARATOR);
			for (int i = 0 ; i < row.length; i++) {
				excelUtil.setColumna(columna);
				excelUtil.generarCeldaExcelGrande(Constants.TIPO_DATO_CARACTER, row[i]);
				columna++;
			}
			registrosExcel++;
		}
		return registrosExcel;
	}
	
	/**
	 * Devuelve nombre del Archivo generado en Oracle
	 * @param parameterMap
	 * @return
	 */
	protected String generarNombreArchivoReporteOracle(Map parameterMap) {
		return null;
	}
	
	/**
	 * Realiza Logica para generar el archivo en el Oracle 
	 * @param parameterMap
	 */
	protected Map generarReporteOracle (Map parameterMap) {
		return parameterMap;
	}

	/**
	 * Ejecuta el Reporte ScSiCC. Prepara los parametros para la ejecucin,
	 * ejecuta y exporta el reporte.
	 */
	public final void executeReporte(Map params) throws Exception {
		ReporteParams reporteParams = null;
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		Pais pais = (Pais)params.get("pais");
		Map criteriaLogDisco = new HashMap();
		try {
			if (this.log.isDebugEnabled()) {
				this.log.debug("Entering 'executeReporte' method");
			}
			
			reporteParams = this.initReporteParams(params);
			Map parameterMap = (Map) reporteParams.getQueryParams().get("parameterMap");
			String rutaPath = this.usuarioService.getRutaPath();
	    	parameterMap.put("rutaPath", rutaPath);
						
			String formatoExportacion = (String) parameterMap.get("formatoExportacion");
			
			try {
				String codigoMenu = (String) parameterMap.get("codigoMenu");
				this.codigoMenu = codigoMenu;
			}
			catch (Exception e) {
				this.codigoMenu = "";
			}
			
			if (!this.indicadorMultiReporte) {
				this.insertReporteHistorico(pais.getCodigo(), usuario.getLogin(), formatoExportacion);
			}
			
			this.exportacion = formatoExportacion;
			this.beforeExecuteReporte(reporteParams);
			if (StringUtils.isNotBlank(formatoExportacion)) {
				if (formatoExportacion.substring(0, 1).equals("O")) {
					 downloadReporte(params);
					 return;
					
				}	
			}
			
			if (this.continueExecuteReporte(reporteParams)) {

				ReporteResult reporteResult;
					reporteResult = reporteExecutionService.executeReporteVirtualizer(reporteParams);

				if (this.log.isDebugEnabled()) {
					this.log.debug("Imprimiento reporte con Formato");
				}
				this.exportReporte(reporteParams, reporteResult, formatoExportacion);

				
				/* Grabando reporte en Directorio predeterminado */
				//Map paramReporte =
				this.copiarReporteServidor(reporteParams);    
 			    
 			    /* Despues de generado el reporte */
				this.afterExecuteReporte(reporteParams);
				
				//String directorio = (String) paramReporte.get("directorioRepositorio");
				Map queryParams = reporteParams.getQueryParams();
				/* Enviar por Correo */
				String indicadorError = Constants.OK;
				this.isErrorEnvioEmail=false;				
				if (this.enviarporCorreo) {
					MailParams mailParams = new MailParams();
					mailParams.setUsuario(usuario);
					mailParams.setPais(pais);
					mailParams.setQueryParams(queryParams);
										 
					MailResult mailResult = this.getMailService().enviarMail(mailParams);	
					if (!mailResult.isCompletado()) {
						String mensajeErrorCorreo="";						
						this.isErrorEnvioEmail=true;
						this.messageErrorEmail=mensajeErrorCorreo;//para mostar por pantalla
						indicadorError = Constants.ERROR;
						this.generaLogGrabarReporte(reporteService, criteriaLogDisco, indicadorError, 
								mailResult.getMensajeError(), reporteParams);
						throw new Exception("Error Envio Correo "+mailResult.getMensajeError());
					}
				}
				
				if(this.enviarporFTP){
					
					FTPClient ftp = new FTPClient();					
					try {
						Map criteria = new HashMap();		
						criteria.put("nombreReporte", this.nombreReporte);
						criteria.put("codigoPais", parameterMap.get("codigoPais"));
						Map paramReporte = reporteService.getParametrosReporte(criteria);
						
						/* Inicio Loguearse al FTP */
						Integer iPort = MapUtils.getInteger(paramReporte, "puertoFTP");
						ftp.connect(MapUtils.getString(paramReporte, "servidorFTP"), iPort.intValue());
						ftp.login(MapUtils.getString(paramReporte, "usuarioFTP"), MapUtils.getString(paramReporte, "claveFTP"));

						boolean logged = ftp.isConnected();
						if (logged){
							log.info("Succesfully logged to " + MapUtils.getString(paramReporte, "servidorFTP"));
						
							FileInputStream fis = null;
							String nombreDestinoFTPIntermedio = this.nombreArchivoReporte + Constants.EXTENSION_TMP;
							
							String rutaOrigen = FileUtil.formatDirectory(MapUtils.getString(paramReporte, "directorioRepositorio")) + this.nombreArchivoReporte;
							File filaOrigen = new File(rutaOrigen);

							fis = new FileInputStream(filaOrigen);
							
							ftp.setFileType(FTP.BINARY_FILE_TYPE);

							String rutaFTP = MapUtils.getString(paramReporte, "rutaFTP");
							if (!rutaFTP.trim().endsWith("/"))
								rutaFTP = rutaFTP.trim() + "/";

							ftp.storeFile(rutaFTP + this.nombreArchivoReporte, fis);

							int reply = ftp.getReplyCode();
							if (!FTPReply.isPositiveCompletion(reply)) {
								throw new StoringFileOverFtpException();
							}
						}		
					}
					catch(Exception ex){
						log.error(ex.getMessage(), ex);
					}
					finally{
						try{
							ftp.logout();
							ftp.disconnect();					
						}
						catch(Exception ex){}
					}
				}
				
				this.generaLogGrabarReporte(reporteService, criteriaLogDisco, indicadorError, 
							Constants.OK_MESSAGE, reporteParams);
				
				if (!this.indicadorMultiReporte) {
					this.currentHistoricoReporte.setMultiReporte(Constants.NO);
					this.currentHistoricoReporte.setNumeroMultireportes(new Long(1));
					this.currentHistoricoReporte.setEnvioMail(Constants.SI);
					this.currentHistoricoReporte.setNombreArchivoReporte(this.nombreArchivoReporte);
					try {
						this.updateHistoricoReportesValues(Constants.NO);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}			
				
											
		} catch (Exception e) {
			e.printStackTrace();
			if (!this.indicadorMultiReporte) {
				try {
					this.currentHistoricoReporte.setNombreArchivoReporte(this.nombreArchivoReporte);
					this.updateHistoricoReportesValues(Constants.NO);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		
		}
		this.afterExecuteReporte(reporteParams);		
		return;
	}





	/**
	 * Inserta log en tabla temporal para que posteriormente sea posible visualizar dicho log en pantalla 
	 * @param reporteService
	 * @param criteriaLogDisco
	 * @param indicadorLog
	 * @param mensaje
	 */
	private void generaLogGrabarReporte(ReporteService reporteService, Map criteriaLogDisco, 
				String indicadorLog, String mensaje,
				ReporteParams reporteParams) {
		String valorFiltro = this.getValorFiltroGrabarReporte(reporteParams);
		Map params = reporteParams.getQueryParams();
		String usuarioLogin = (String) params.get("usuarioLogin");
		criteriaLogDisco.put("nombreReporte",this.nombreArchivoReporte);
		criteriaLogDisco.put("indicadorLog", indicadorLog);
		criteriaLogDisco.put("descripcionLog", mensaje);
		criteriaLogDisco.put("valorFiltro", valorFiltro);
		criteriaLogDisco.put("usuarioLogin", usuarioLogin);
		reporteService.insertLogReporteDisco(criteriaLogDisco);
	}

	/**
	 * Devuelve el forward por defecto del metodo <code>executeXXX</code>. En
	 * caso de que el <code>executeXXX</code> devuelva un Error en la
	 * generacion del Reporte
	 * 
	 * @return String con el forward para el 'errorReporte'
	 */
	protected String getErrorReporteForward() {
		return DEFAULT_ERROR_FORWARD;
	}

	/**
	 * Devuelve extension del Reporte
	 * @return
	 */
	public String getExtensionReporte() {
		if (StringUtils.equals("XLS", this.exportacion)
				|| StringUtils.equals("VXLS", this.exportacion)) {
			return XLS_EXTENSION;
		}
		if (StringUtils.equals("CSV", this.exportacion)
				|| StringUtils.equals("VCSV", this.exportacion)) {
			return CSV_EXTENSION;
		}
		if (StringUtils.equals("PDF", this.exportacion)
				|| StringUtils.equals("VPDF", this.exportacion)) {
			return PDF_EXTENSION;
		}
		if (StringUtils.equals("TXT", this.exportacion)
				|| StringUtils.equals("VTXT", this.exportacion)) {
			return TXT_EXTENSION;
		}
		return PDF_EXTENSION;
	}

	/**
	 * @return Returns the messageErrorEmail.
	 */
	public String getMessageErrorEmail() {
		return this.messageErrorEmail;
	}



	/**
	 * Metodo que devuelve nombre del Archivo a generar en el Servidor 
	 * @return
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		if (!StringUtils.isBlank(this.prefijoArchivo)) {
			nombreArchivoReporte = this.prefijoArchivo + "_"
				+ sdf.format(new Date(System.currentTimeMillis()));
		} else {
			nombreArchivoReporte = this.nombreReporte + "_"
				+ sdf.format(new Date(System.currentTimeMillis()));
		}
		return nombreArchivoReporte;
	}

	/**
	 * @return Returns the nombreReporte.
	 */
	public String getNombreReporte() {
		return this.nombreReporte;
	}

	/**
	 * @return Returns the nroReporteProcesando.
	 */
	public int getNroReporteProcesando() {
		return this.nroReporteProcesando;
	}

	/**
	 * Metodo que devuelve la cantidad de Reportes a generar en el Servidor
	 * Dicho metodo debe ser sobreescrito para que genere mas de 1 reporte
	 * @return
	 */
	protected int getNroReportesAGenerar() {
		return 1;
	}

	/**
	 * @return Returns the prefijoArchivo.
	 */
	public String getPrefijoArchivo() {
		return this.prefijoArchivo;
	}

	/**
	 * Este metodo debe retornar el nombre del reporte sin la extension.
	 * 
	 * <p>
	 * El estandar es "reporte[Sistema][Descripcion]".
	 * 
	 * <p>
	 * Ejemplo: <code>return "reporteINCParametrosGenerales";</code>
	 * 
	 * @return nombre del reporte
	 */
	protected abstract String getReporteFileName();
	
	/**
	 * Metodo que obtiene el valor del Filtro a ser grabado en el Log del Reporte.
	 * Dicho metodo puedes ser sobreescrito
	 * @param reporteParams
	 * @return
	 */
	protected String getValorFiltroGrabarReporte(ReporteParams reporteParams) {
		return null;
	}

	/**
	 * Genera Reporte en el Servidor 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public final void grabarReporte(Map params) throws Exception {
		
		/* Antes de ejecutar Grabar Reporte */
		this.beforeGrabarReporte();
		this.visualizarReporte  = false;
		int contadorGenerarMultipleReporte = 1;
			
		Usuario usuario = (Usuario)params.get("usuarioTemp");		
		params.put("usuarioLogin", usuario.getLogin());
		String codigoUsuario = (String) params.get("codigoUsuario");
		String codigoPais = (String) params.get("codigoPais");
		
		this.insertReporteHistorico(codigoPais, usuario.getLogin(), this.exportacion);
		if(this.isDeleteTemporal) {
			reporteService.deleteLogReporteDisco(params);
		}
		
		/* Obteniendo nro de reportes a generar */
		contadorGenerarMultipleReporte = this.getNroReportesAGenerar();
		this.log.debug("contadorGenerarMultipleReporte "+contadorGenerarMultipleReporte);
		if (contadorGenerarMultipleReporte < 0) {
			//String key = this.errorKeyGenerarMultipleReporte();			
			throw new Exception("no hay reporte a generar");
		}
		
		/* Auditoria del Reporte */
		this.indicadorMultiReporte = false;
		this.currentHistoricoReporte.setMultiReporte(Constants.NO);
		this.currentHistoricoReporte.setNumeroMultireportes(new Long(contadorGenerarMultipleReporte));
		if (contadorGenerarMultipleReporte > 1) {
			this.currentHistoricoReporte.setMultiReporte(Constants.SI);
			this.indicadorMultiReporte = true;
		}
		
		/* Generando los reportes respectivos en el disco */
		for(int i=0; i < contadorGenerarMultipleReporte; i++) {
			this.nroReporteProcesando = i + 1;
			this.beforeIniBucleGrabarReporte();
			executeReporte(params);
		}	
		
		/* Despues de ejecutar Grabar Reporte */
		this.afterGrabarReporte(params);	
		
		if (this.indicadorMultiReporte) {
			params = this.obtenerPaisyUsuario(params);
			
			this.currentHistoricoReporte.setEnvioMail(Constants.SI);
			this.currentHistoricoReporte.setCodigoReporte(this.nombreArchivoReporte);
			try {
				this.updateHistoricoReportesValues(Constants.NO);
			}
			catch(Exception e) {
				e.printStackTrace();
				this.updateHistoricoReportesValues(Constants.SI);
			}
		}
	}

	protected void beforeIniBucleGrabarReporte() {
		
	}

	/**
	 * Inicializa la Parametrizacin del Reporte
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ReporteParams initReporteParams(Map params) throws Exception {
		ReporteParams reporteParams = new ReporteParams();
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		Map param = prepareParamsBeforeExecute(params);
		reporteParams.setJasperFileName(JASPER_DIRECTORIO + this.getReporteFileName() + JASPER_EXTENSION);
		params.put(JRParameter.REPORT_LOCALE, this.getLocale(usuario));
		reporteParams.setQueryParams(param);
		this.configReporteParams(reporteParams);
		return reporteParams;
	}

	/**
	 * @return Returns the isGrabarTemporal.
	 */
	public boolean isDeleteTemporal() {
		return this.isDeleteTemporal;
	}

	/**
	 * @return Returns the isErrorEnvioEmail.
	 */
	public boolean isErrorEnvioEmail() {
		return this.isErrorEnvioEmail;
	}

	/**
	 * @return Returns the mostrarLogPantalla.
	 */
	public boolean isMostrarLogPantalla() {
		return this.mostrarLogPantalla;
	}

	
/**
	 * @return Returns the virtualizador.
	 */
	public boolean isVirtualizador() {
		return this.virtualizador;
	}
	

	/**
	 * @return Returns the visualizarReporte.
	 */
	public boolean isVisualizarReporte() {
		return this.visualizarReporte;
	}


	protected void prepareParameterMap(Map parameterMap) throws Exception  {
		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected final Map prepareParamsBeforeExecute(Map params) throws Exception {
		Usuario usuario = (Usuario)params.get("usuarioTemp");
		String descripcionPais = (String)params.get("descripcionPais");
		
		params.put("usuario.login", usuario.getLogin());
		
		params.put("superiorIzquierda", messageSource.getMessage("reporte.maestro.belcorp", null, getLocale(usuario))
										+ descripcionPais);
		params.put("condicionUsuario", "SI");
		params.put("condicionFechaHora", "SI");
		Map parameterMap = super.prepareParamsBeforeExecute(params);
		this.exportacion = (String) parameterMap.get("formatoExportacion");
		
		/* Seteando variables donde se guardara el Reporte Temporalmente */
		Map paramReporteGeneral = reporteService
				.getParametrosReporteGeneral(null);
		String directorioTemporal = (String) paramReporteGeneral
				.get("directorioTemporal");
		String directorioRepositorio = (String) paramReporteGeneral
				.get("directorioRepositorio");
		
		parameterMap.put("directorioTemporal", directorioTemporal);
		parameterMap.put("directorioRepositorio", directorioRepositorio);
		
		/* Seteando variables iniciales del Reporte */
		parameterMap.put("usuario.login", usuario.getLogin());
		parameterMap.put("superiorIzquierda", messageSource.getMessage("reporte.maestro.belcorp", null, getLocale(usuario))
				+ descripcionPais);				
		parameterMap.put("condicionUsuario", "SI");
		parameterMap.put("condicionFechaHora", "SI");
		
		
		
		String s = this.getLocale(usuario).getLanguage();
		Idioma idioma = service.getIdiomaByCodigoISO(s);
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		String oidIdiomaIso = reporteService.getOidString(
				"getOidIdiomaByCodigoIdiomaIso", parameterMap);
		parameterMap.put("oidIdiomaIso", oidIdiomaIso);
		
		this.prepareParameterMap(parameterMap);
		this.log.info("Despues de prepareParameterMap, parameterMap="
						+ parameterMap);
		params.put("parameterMap", parameterMap);
		return params;
	}


	/**
	 * @param isGrabarTemporal The isGrabarTemporal to set.
	 */
	public void setDeleteTemporal(boolean isDeleteTemporal) {
		this.isDeleteTemporal = isDeleteTemporal;
	}


	/**
	 * @param isErrorEnvioEmail The isErrorEnvioEmail to set.
	 */
	public void setErrorEnvioEmail(boolean isErrorEnvioEmail) {
		this.isErrorEnvioEmail = isErrorEnvioEmail;
	}

	/**
	 * @param messageErrorEmail The messageErrorEmail to set.
	 */
	public void setMessageErrorEmail(String messageErrorEmail) {
		this.messageErrorEmail = messageErrorEmail;
	}

	/**
	 * @param mostrarLogPantalla The mostrarLogPantalla to set.
	 */
	public void setMostrarLogPantalla(boolean mostrarLogPantalla) {
		this.mostrarLogPantalla = mostrarLogPantalla;
	}

	/**
	 * @param nombreReporte The nombreReporte to set.
	 */
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	/**
	 * @param nroReporteProcesando The nroReporteProcesando to set.
	 */
	public void setNroReporteProcesando(int nroReporteProcesando) {
		this.nroReporteProcesando = nroReporteProcesando;
	}

	/**
	 * @param prefijoArchivo The prefijoArchivo to set.
	 */
	public void setPrefijoArchivo(String prefijoArchivo) {
		this.prefijoArchivo = prefijoArchivo;
	}

	/**
	 * @param virtualizador
	 *            The virtualizador to set.
	 */
	public void setVirtualizador(boolean virtualizador) {
		this.virtualizador = virtualizador;
	}

	/**
	 * @param visualizarReporte The visualizarReporte to set.
	 */
	public void setVisualizarReporte(boolean visualizarReporte) {
		this.visualizarReporte = visualizarReporte;
	}



	/**
	 * vitualiza el archivo segun formato 
	 * @param reporteParams
	 * @param reporteResult
	 * @param response
	 * @param request
	 * @param exportFormat
	 * @throws IOException
	 */
	private void exportReporte(ReporteParams reporteParams,
			ReporteResult reporteResult,
			String exportFormat) throws IOException {

		// variables del virtualizador
		Map queryParams = reporteParams.getQueryParams();
		Map parameterMap = (Map) queryParams.get("parameterMap");
		String path = (String) parameterMap.get("rutaPath");
		
		this.log.debug("Project Path " + path);
		String outfile = new String("");// archivo de salida
		outfile += path;
		outfile += NOMBRE_REPORTE_VIRTUALIZADO;

		if (exportFormat.equals("VPDF") || exportFormat.equals("PDF")) {// PDF virtualizado

			try {
				outfile += PDF_EXTENSION;
				this.log.debug("Virtualizado en disco  " + outfile);
				JasperExportManager.exportReportToPdfFile(reporteResult
						.getJasperPrint(), outfile);

			} catch (JRException e) {
				e.printStackTrace();
			}

		} else if (exportFormat.equals("VXLS") || exportFormat.equals("XLS") ) {// XLS virtualizado

			try {
				outfile += XLS_EXTENSION;
				this.log.debug("Virtualizado en disco  " + outfile);

				JRXlsExporter exporter = new JRXlsExporter();
				//JExcelApiExporter exporter = new JExcelApiExporter();

				exporter.setParameter(JRExporterParameter.JASPER_PRINT,	reporteResult.getJasperPrint());
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
				exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,	Boolean.TRUE);
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,	outfile);
				exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[]{StringUtils.left(getReporteFileName(), 30)});
				if (generateTabsXLS) {
					exporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET,MAXIMUM_ROWS_PER_SHEET_XLS);
				}
				exporter.exportReport();

			} catch (JRException e) {
				e.printStackTrace();
			}

		}

		else if (exportFormat.equals("VCSV") || exportFormat.equals("CSV") ) {// PDF virtualizado

			try {
				outfile += CSV_EXTENSION;
				this.log.debug("Virtualizado en disco  " + outfile);
				JRCsvExporter csvExporter = new JRCsvExporter();

				csvExporter.setParameter(JRExporterParameter.JASPER_PRINT,
						reporteResult.getJasperPrint());
				csvExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
						outfile);
				csvExporter.exportReport();

			} catch (JRException e) {
				 e.printStackTrace();
			}
		   finally {
			// 

		   }

		}
		
		else if (exportFormat.equals("VTXT") || exportFormat.equals("TXT") ) {
			try {
				outfile += TXT_EXTENSION;
				this.log.debug("Virtualizado en disco  " + outfile);
				JRCsvExporter csvExporter = new JRCsvExporter();

				csvExporter.setParameter(JRExporterParameter.JASPER_PRINT,
						reporteResult.getJasperPrint());
				csvExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
						outfile);
				csvExporter.exportReport();

			} catch (JRException e) {
			
				e.printStackTrace();
			}
		   finally {
			// 

		   }
			
		}	
	}


	/**
	 * mail service
	 * @return the mailService
	 */
	abstract public BaseMailService getMailService();
	
	/**
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param formatoExportacion
	 * @param indicadorError
	 */
	private void insertReporteHistorico(String codigoPais, String codigoUsuario, String formatoExportacion) {
		this.currentHistoricoReporte = new HistoricoReporte();
		this.currentHistoricoReporte.setCodigoPais(codigoPais);
		this.currentHistoricoReporte.setCodigoMenu(this.codigoMenu);
		this.currentHistoricoReporte.setCodigoReporte(nombreReporte);	
		this.currentHistoricoReporte.setCodigoUsuario(codigoUsuario);
		this.currentHistoricoReporte.setFormatoReporte(formatoExportacion);
		this.currentHistoricoReporte.setIndicadorError(Constants.NO);
		this.currentHistoricoReporte.setFechaInicio(new Timestamp(System.currentTimeMillis()));
		try {			
			this.currentHistoricoReporte.setIpMaquina(InetAddress.getLocalHost().getHostAddress());
			
		} 
		catch (Exception e) {
			this.currentHistoricoReporte.setIpMaquina("");
		}
		
		try {		
			this.currentHistoricoReporte.setCodigoPeriodo(this.interfazSiCCService.getPeriodoDefaultByPaisCanal(codigoPais,"VD"));		
		} 
		catch (Exception e) {
			this.currentHistoricoReporte.setCodigoPeriodo("");	
		}
		Long id = this.reporteService.getDevuelveIdSgteCodHistoricoReporte();
		this.currentHistoricoReporte.setCodigoHistoricoReporte(id);
		this.reporteService.insertHistoricoReporte(this.currentHistoricoReporte);
		if (log.isDebugEnabled()) {
			log.debug("Finish 'insertReporteHistorico' method");
		}
	}
		
	/**
	 * Guarda los valores para ser grabados en la auditoria del Reporte
	 * @param nombreReporte
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param formatoExportacion
	 */
	public void updateHistoricoReportesValues(String indicadorError){

		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateHistoricoReportesValues' method");
		}
		this.currentHistoricoReporte.setIndicadorError(indicadorError);
		this.currentHistoricoReporte.setFechaFin(new Timestamp(System.currentTimeMillis()));				
		this.currentHistoricoReporte.setDuracionSegundos
			((long) ((this.currentHistoricoReporte.getFechaFin().getTime() - this.currentHistoricoReporte.getFechaInicio().getTime())/1000));
		
		this.reporteService.updateHistoricoReporte(this.currentHistoricoReporte);
		if (log.isDebugEnabled()) {
			log.debug("Finish 'updateHistoricoReportesValues' method");
		}
		
	}


	
}
