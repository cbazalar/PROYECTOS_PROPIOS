package biz.belcorp.ssicc.reportes.framework.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JRAbstractLRUVirtualizer;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSwapFile;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface;
import biz.belcorp.ssicc.reportes.framework.service.ParameterContructorService;
import biz.belcorp.ssicc.reportes.framework.service.ReporteExecutionService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteResult;
import biz.belcorp.ssicc.service.util.ZipUtil;

/**
 * Implementacion del Service de ejecucin de los Reportes ScSiCC.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */

@Service("reportes.reporteExecutionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteExecutionServiceImpl extends BaseService implements ReporteExecutionService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	protected static final String XLS_EXTENSION = ".xls";
	protected static final String CSV_EXTENSION = ".csv";
	protected static final String TXT_EXTENSION = ".txt";
	protected static final String XLSX_EXTENSION = ".xlsx";
	protected static final String ZIP_EXTENSION = ".zip";
	
		
	@Resource(name="reportes.parameterContructorService")
	private ParameterContructorService parameterContructorService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.service.ReporteExecutionService#execute(biz.belcorp.ssicc.reportes.framework.base.BaseReporteForm)
	 */
	public ReporteParams executePrevio(BaseReporteForm form, Map<String, Object> parameterAdicionales) throws Exception {

		log.debug("Entering 'ReporteExecutionService.execute' method");
		BaseReporteInterface service = form.getReporteService();
		
		ReporteParams reporteParams = parameterContructorService.inicializarParametrosReporte(form, parameterAdicionales);
		return reporteParams;	
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.ReporteExecutionService#generarReporte(biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm, biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	public Map<String, Object> generarReporte(BaseReporteForm form, ReporteParams reporteParams) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		BaseReporteInterface service = form.getReporteService();
		boolean continuarReporte = true;
		if (service != null) {
			continuarReporte = service.continueExecuteReporte(form);
		}
		
		if (continuarReporte) {
			ReporteResult reporteResult = this.executeReporteVirtualizer(reporteParams);
			if (service != null) {
				reporteParams = service.afterExecuteReporte(reporteParams, form);
			}
			result.put("reporteResult", reporteResult);
			result.put("reporteParams", reporteParams);
		}
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.ReporteExecutionService#executeNoJASPER(biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm, java.util.Map)
	 */
	public ReporteParams executePrevioNoJASPER(BaseReporteForm form, Map<String, Object> parameterAdicionales) throws Exception {

		log.debug("Entering 'ReporteExecutionService.execute' method");
		BaseReporteInterface service = form.getReporteService();
		
		ReporteParams reporteParams = parameterContructorService.inicializarParametrosReporteNoJASPER(form, parameterAdicionales);
		return reporteParams;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.ReporteExecutionService#generarReporteNoJASPER(biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm, biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	public Map<String, Object> generarReporteNoJASPER(BaseReporteForm form, ReporteParams reporteParams) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		BaseReporteInterface service = form.getReporteService();
		boolean continuarReporte = true;
		if (service != null) {
			continuarReporte = service.continueExecuteReporte(form);
		}
		Map parameterAdicionales = reporteParams.getQueryParams();
		
		if (continuarReporte) {
			String formatoExportacion = (String) parameterAdicionales.get("formatoExportacion");
			if (StringUtils.isBlank(formatoExportacion)) {
				formatoExportacion = form.getFormatoExportacion();
				parameterAdicionales.put("formatoExportacion", formatoExportacion);
			}
			String extensionArchivo = new String("");
			if (formatoExportacion.equals("OTXT"))
				extensionArchivo = this.TXT_EXTENSION;
			if (formatoExportacion.equals("OCSV"))
				extensionArchivo = this.CSV_EXTENSION;			
			if (formatoExportacion.equals("OJXLSX"))
				extensionArchivo = this.XLSX_EXTENSION;			
			if (formatoExportacion.equals("OOXLSX"))
				extensionArchivo = this.XLSX_EXTENSION;
			
			/* Generando Nombre del Archivo */
			String nombreArchivo = service.generarNombreArchivoReporteOracle(parameterAdicionales);
			parameterAdicionales.put("nombreArchivo", nombreArchivo);
			parameterAdicionales.put("extensionArchivo", extensionArchivo);
			
			String tituloReporteOracle = service.obtieneTituloReporteOracle(parameterAdicionales);
			parameterAdicionales.put("tituloReporteOracle", tituloReporteOracle);
			
			String tipoDatos = service.obtieneTipoDatosReporteOracle(parameterAdicionales);
			parameterAdicionales.put("tipoDatosOracle", tipoDatos);
			
			/* Generando Reporte en Oracle */
			String directorioArchivo = new String("   ");
			parameterAdicionales.put("directorioArchivo", directorioArchivo);
			
			parameterAdicionales = service.generarReporteOracle(parameterAdicionales);
			reporteParams.setQueryParams(parameterAdicionales);	
			
			/* Generando Archivo Zip en caso sea de ese tipo */
			if (formatoExportacion.equals("OZIP")) {
				parameterAdicionales = this.generarZip(parameterAdicionales);
				reporteParams.setQueryParams(parameterAdicionales);		
			}
			
			/* Procesos After */
			if (service != null) {
				reporteParams = service.afterExecuteReporte(reporteParams, form);
				if(((String) parameterAdicionales.get("formatoExportacion")).equals("OJXLSX")){
					service.convertircsvToxlsx((String)parameterAdicionales.get("directorioArchivo") + nombreArchivo + this.CSV_EXTENSION, (String)parameterAdicionales.get("directorioArchivo") + nombreArchivo + this.XLSX_EXTENSION);
				}
			}
			
			
			result.put("reporteResult", null);
			result.put("reporteParams", reporteParams);
			if (this.log.isDebugEnabled()) {
				directorioArchivo = (String) parameterAdicionales.get("directorioArchivo");
				this.log.debug("directorioArchivo: "+ directorioArchivo);
				this.log.debug("nombreArchivo: "+ nombreArchivo);
				this.log.debug("extensionArchivo: "+ extensionArchivo);
				this.log.debug("parameterMap: "+ parameterAdicionales.toString());
				this.log.debug("Final 'downloadReporte' method");
			}
		}
		return result;		
		
		
	}
	
	
	
	/**
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	protected final Map<String, Object> generarZip(Map<String, Object> parameterMap) throws Exception {

		String accion = MapUtils.getString(parameterMap, "accion", "");
		if(StringUtils.isNotEmpty(accion)){
			String nombreArchivoZip = MapUtils.getString(parameterMap, "nombreArchivo", "");
			
			String archivo1 = "";
			String archivo2 = "";
			
			if(StringUtils.equals(accion, Constants.REPORTE_VENTAS_CODIGO_ACCION_NOTA_CREDITO))
			{
				archivo1 = (String) parameterMap.get("psNota1");
				archivo2 = (String) parameterMap.get("psNota2");
			}
			else if(StringUtils.equals(accion, Constants.REPORTE_VENTAS_CODIGO_ACCION_BOLETA_VENTA))
			{
				archivo1 = (String) parameterMap.get("psBoleta1");
				archivo2 = (String) parameterMap.get("psBoleta2");
			}
			
			
			String directorio = (String) parameterMap.get("directorioArchivo");
			
			/* Zipiando archivos*/ 
			String[] filesAZipiar = new String[]{directorio + archivo1, directorio + archivo2};
			
			parameterMap.put("extensionArchivo", ZIP_EXTENSION);
			
			String filePathFinal = directorio + nombreArchivoZip + ZIP_EXTENSION;
			parameterMap.put("rutaTemporalReportesGenerados", directorio);
			ZipUtil.zipFiles(filesAZipiar,filePathFinal);
			
			File f1 = new File(filesAZipiar[0]);
			File f2 = new File(filesAZipiar[1]);
			
			if(f1.exists())
				f1.delete();
			
			if(f2.exists())
				f2.delete();
		} else {
			
			//Caso para multiples archivos cargados a un ZIP
			String directorio = (String) parameterMap.get("directorioArchivo");
			
			String nombreArchivoZip = MapUtils.getString(parameterMap, "nombreArchivo", "");
			
			String archivos = (String) parameterMap.get("psArchivos");
			
			String[] split = StringUtils.split(archivos, ",");

			/* Zipiando archivos*/ 
			String[] filesAZipiar = new String[split.length];
			
			for( int i = 0; i < split.length; i++ ) {
				filesAZipiar[i] = directorio + split[i];
			}
			
			parameterMap.put("extensionArchivo", ZIP_EXTENSION);
			
			String filePathFinal = directorio + nombreArchivoZip + ZIP_EXTENSION;
			parameterMap.put("rutaTemporalReportesGenerados", directorio);
			ZipUtil.zipFiles(filesAZipiar,filePathFinal);
			
			for( int j = 0; j < split.length; j++ ) {
				File f = new File(filesAZipiar[j]);
				if(f.exists()) f.delete();
			}
		}
	
		return parameterMap;
	}
	
	
	/* 
	 * Executa el reporte(Realiza la consulta que genera la data a mostrar)
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.service.ReporteExecutionService#executeReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	public ReporteResult executeReporte(ReporteParams reportParams) throws Exception {
		log.debug("Entering 'executeReporte' method");
		
		ReporteResult reporteResult = new ReporteResult();
		Connection connection = null;
		boolean generoError = false;
		Exception devuelveError = null;
		try {
			ClassPathResource resource = new ClassPathResource(reportParams
					.getJasperFileName());
			log.debug("Resource : "+ resource.getPath());

			/*
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(resource.getFile());
			*/
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() ));
			log.debug("Se genero el JasperReport");
			
			connection = this.transactionManager.getDataSource().getConnection();
				
			log.debug("Se establecio la coneccion : "+ connection.toString());
									
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, reportParams.getQueryParams(), connection);
			
			connection.close();
			log.debug("Se genero el JasperPrint");
			reporteResult.setJasperPrint(jasperPrint);
		

		}catch (Exception e) {
			log.error("Execpcion No Capturada: "
					+ reportParams.getJasperFileName());
			e.printStackTrace();
			generoError = true;
			devuelveError = e;
		}
		
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			
			if (generoError)	
				throw new Exception(devuelveError);
		}
		return reporteResult;
	}

	/* 
	 * Metodo que permite ejecutar reportes de manera virtualizada, es decir la
	 * impresion se hara en disco.
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.service.ReporteExecutionService#executeReporteVirtualizer(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	public ReporteResult executeReporteVirtualizer(ReporteParams reportParams) throws Exception {

		log.debug("Entering 'executeReporteVirtualizer' method");
		ReporteResult reporteResult = new ReporteResult();
		Connection connection = null;

		Map params = (Map) reportParams.getQueryParams().get("parameterMap");
		String directorioTemporal = (String) params.get("directorioTemporal");
		log.debug("Directorio Temporal : "+directorioTemporal);

		JRFileVirtualizer fileVirtualizer = new JRFileVirtualizer(3, directorioTemporal);
		boolean generoError = false;
		Exception devuelveError = null;
		try {
			ClassPathResource resource = new ClassPathResource(reportParams.getJasperFileName());
			log.debug("Resource : "+ resource.getPath());
	
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() ));
			/**/
			log.debug("Se genero el JasperReport");
			connection = this.transactionManager.getDataSource().getConnection();

			DatabaseMetaData metadata= connection.getMetaData();
			log.debug("VERSION DRIVER ORACLE = " + metadata.getDriverVersion());
			
			
			log.debug("Se establecio la coneccion : "+ connection.getClientInfo().toString());
			JRAbstractLRUVirtualizer virtualizer = null;
			JRSwapFile swapFile = new JRSwapFile(directorioTemporal, 1024, 1024);
			
			virtualizer = new JRSwapFileVirtualizer(3, swapFile, true);			
			reportParams.getQueryParams().put(JRParameter.REPORT_VIRTUALIZER, virtualizer);// JNUNEZ
			JasperPrint jasperPrint = JasperFillManager.fillReport(	jasperReport, reportParams.getQueryParams(), connection);
			
			if (fileVirtualizer != null) {
				fileVirtualizer.setReadOnly(true);
			}
			connection.close();
			log.debug("Se genero el JasperPrint");
			reporteResult.setJasperPrint(jasperPrint);
		} catch (Exception e) {
			generoError = true;
			devuelveError = e;
			e.printStackTrace();
		} 
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
			    }
			if (generoError)	
				throw new Exception(devuelveError);
		}
		return reporteResult;
	}

	/* 
	 * Metodo que permite la ejecucion multiple de reportes.
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.service.ReporteExecutionService#executeReporteMultiple(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	public ReporteResult executeReporteMultiple(ReporteParams reportParams) throws Exception {
		log.debug("Entering 'executeReporteMultiple' method");
		ReporteResult reporteResult = new ReporteResult();
		Connection connection = null;
		boolean generoError = false;
		Exception devuelveError = null;
		try {
			ClassPathResource resource = new ClassPathResource(reportParams.getJasperFileName());
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() ));
			
			connection = this.transactionManager.getDataSource().getConnection();
		
			//Seteamos los parametros que van a variar
			Map queryParams = reportParams.getQueryParams();
			Map parameterMap = (Map)queryParams.get("parameterMap");
			
			List listaRegionesZonas = (ArrayList)MapUtils.getObject(parameterMap, Constants.REPORTE_REGION_ZONA_MAP_LIST);
			
			//Creamos una carpeta temporal para los reportes
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			String carpetaTemporal = sdf.format(new Date(System.currentTimeMillis()));
			String rutaCarpetaTemporal = MapUtils.getString(parameterMap, "directorioTemporal") + carpetaTemporal + File.separator;
			File fileCarpetaTemporal = new File(rutaCarpetaTemporal);
			fileCarpetaTemporal.mkdir();
			
			parameterMap.put("rutaTemporalReportesGenerados", rutaCarpetaTemporal);
			
			//Consolidado de todas las regiones todas las zonas
			String flagGenerarConsolidado = MapUtils.getString(parameterMap, "flagGenerarConsolidado");
			if(StringUtils.equals(flagGenerarConsolidado, Constants.SI))
			{
				if(log.isDebugEnabled())
					this.log.debug("Generando reporte consolidado");
				
				//Generamos el consolidado de todas las zonas de la region
				parameterMap.put("codigoRegion", null);
				parameterMap.put("nombreRegion", "Todas");
				parameterMap.put("codigoZona", null);
				parameterMap.put("nombreZona", "Todas");
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, queryParams, connection);
				try {
					String nombreArchivoConsolidado = String.format(Constants.FORMATO_NOMBRE_REPORTE_CONSOLIDADO, carpetaTemporal);
					String rutaArchivoConsolidado = rutaCarpetaTemporal + nombreArchivoConsolidado;
										
					JasperExportManager.exportReportToPdfFile(jasperPrint, rutaArchivoConsolidado);
					
					if(log.isDebugEnabled())
						this.log.debug("Reporte Consolidado generado");

				} catch (JRException e) {
					log.error("Error al generar reporte Consolidado", e);
				}
			}
						
			for(int i=0; i<listaRegionesZonas.size(); i++){
				String codigoRegion = MapUtils.getString((Map)listaRegionesZonas.get(i), "codigoRegion");
				String codigoZona = MapUtils.getString((Map)listaRegionesZonas.get(i), "codigoZona");

				try {
					//Creamos la carpeta de la region si aun no existe
					String rutaCarpetaRegion = rutaCarpetaTemporal + codigoRegion + File.separator;
					File fileCarpetaRegion = new File(rutaCarpetaRegion);
					
					if(!fileCarpetaRegion.exists())
					{
						fileCarpetaRegion.mkdir();
						
						String flagGenerarConsolidadoRegion = MapUtils.getString(parameterMap, "flagGenerarConsolidadoRegion");
						if(StringUtils.equals(flagGenerarConsolidadoRegion, Constants.SI))
						{
							if(log.isDebugEnabled())
								this.log.debug("Generando reporte consolidado por region " + codigoRegion);
							
							//Generamos el consolidado por region, solo si aun no existe
							parameterMap.put("codigoRegion", codigoRegion);
							parameterMap.put("nombreRegion", codigoRegion);
							parameterMap.put("codigoZona", null);
							parameterMap.put("nombreZona", "Todas");
							
							JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, queryParams, connection);
							
							String nombreArchivoConsolidadoRegion = String.format(Constants.FORMATO_NOMBRE_REPORTE_CONSOLIDADO, codigoRegion);
							String rutaArchivoConsolidadoRegion = rutaCarpetaRegion + nombreArchivoConsolidadoRegion;
							
							JasperExportManager.exportReportToPdfFile(jasperPrint, rutaArchivoConsolidadoRegion);
							
							if(log.isDebugEnabled())
								this.log.debug("Reporte generado.");
						}
					}
					
					//Generamos archivos independientes para cada zona
					parameterMap.put("codigoRegion", codigoRegion);
					parameterMap.put("nombreRegion", codigoRegion);
										
					parameterMap.put("codigoZona", codigoZona);
					parameterMap.put("nombreZona", codigoZona);
					
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, queryParams, connection);
	
					String rutaArchivoZona = rutaCarpetaRegion + codigoZona + ".pdf";
					if(log.isDebugEnabled())
						this.log.debug("Escribiendo reporte " + (i+1) + " de " + listaRegionesZonas.size() + " en disco: " + rutaArchivoZona);
					
					JasperExportManager.exportReportToPdfFile(jasperPrint, rutaArchivoZona);
					if(log.isDebugEnabled())
						this.log.debug("Reportes generado");
				
				} catch (JRException e) {
					log.error("Error al generar reporte", e);
				}
			}
			
			connection.close();
			if(log.isDebugEnabled())
				this.log.debug("Generacion de reportes completa.");
			
		
		}catch (Exception e) {
			log.error("Execpcion No Capturada: " + reportParams.getJasperFileName());
			e.printStackTrace();
			generoError = true;
			devuelveError = e;
		}
		
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
			if (generoError)	
				throw new Exception(devuelveError);
		}
		return reporteResult;
	}
		
}
