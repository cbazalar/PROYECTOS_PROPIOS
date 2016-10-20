package biz.belcorp.ssicc.service.scsicc.framework.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.framework.ReporteExecutionService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteResult;


/**
 * Implementacion del Service de ejecucin de los Reportes ScSiCC.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("scsicc.reporteExecutionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteExecutionServiceImpl extends BaseService  implements ReporteExecutionService {
	
	protected final Log log = LogFactory.getLog(getClass());

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.framework.ReporteExecutionService#executeReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	public ReporteResult executeReporte(ReporteParams reportParams) throws Exception {
		log.debug("Entering 'executeReporte' method");
		ReporteResult reporteResult = new ReporteResult();
		Connection connection = null;
		boolean generoError = false;
		Exception error = null;
		try {
			ClassPathResource resource = new ClassPathResource(reportParams
					.getJasperFileName());
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() ));
			
			log.debug("Se genero el JasperReport");

			connection = this.transactionManager.getDataSource().getConnection();
			DatabaseMetaData metadata= connection.getMetaData();
			log.debug("VERSION DRIVER ORACLE = " + metadata.getDriverVersion());
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, reportParams.getQueryParams(), connection);
			connection.close();
			log.debug("SIZE REPORTE = " + jasperPrint.getOriginsList().size());
			log.debug("Se genero el JasperPrint");
			reporteResult.setJasperPrint(jasperPrint);
	
		}catch (Exception e) {
			e.printStackTrace();
			generoError = true;
			error = e;
		}
		
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			// TODO Auto-generated catch block
			if (generoError) throw new Exception(error);
		}
		return reporteResult;
	}

	/**
	 * Metodo que permite ejecutar reportes de manera virtualizada, es decir la
	 * impresion se hara en disco.
	 * 
	 * @param reportParams -
	 *            parametros a utlizar
	 */
	public ReporteResult executeReporteVirtualizer(ReporteParams reportParams) throws Exception {

		log.debug("Entering 'executeReporteVirtualizer' method");
		ReporteResult reporteResult = new ReporteResult();
		Connection connection = null;
		boolean generoError = false;
		Exception error = null;
		
		Map params = (Map) reportParams.getQueryParams().get("parameterMap");
		String directorioTemporal = (String) params.get("directorioTemporal");
		// JRFileVirtualizer fileVirtualizer =new JRFileVirtualizer(3,
		// "/ssicc/temp/");
		JRFileVirtualizer fileVirtualizer = new JRFileVirtualizer(3,
				directorioTemporal);
		try {
			ClassPathResource resource = new ClassPathResource(reportParams
					.getJasperFileName());
			/*JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(resource.getFile());
			*/
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() ));
			log.debug("Se genero el JasperReport");

			connection = this.transactionManager.getDataSource().getConnection();
			DatabaseMetaData metadata= connection.getMetaData();
			log.debug("VERSION DRIVER ORACLE = " + metadata.getDriverVersion());
			
			JRAbstractLRUVirtualizer virtualizer = null;
			JRSwapFile swapFile = new JRSwapFile(directorioTemporal, 1024, 1024);			
			virtualizer = new JRSwapFileVirtualizer(3, swapFile, true);			
			reportParams.getQueryParams().put(JRParameter.REPORT_VIRTUALIZER,
					virtualizer);// JNUNEZ
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, reportParams.getQueryParams(), connection);
			if (fileVirtualizer != null) {
				fileVirtualizer.setReadOnly(true);
			}
			connection.close();
			
			log.debug("SIZE REPORTE = " + jasperPrint.getOriginsList().size());
			log.debug("Se genero el JasperPrint");
			reporteResult.setJasperPrint(jasperPrint);
		}catch (Exception e) {
			e.printStackTrace();
			generoError = true;
			error = e;
	
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			// TODO Auto-generated catch block
			if (generoError) throw new Exception(error);

		}
		return reporteResult;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.framework.ReporteExecutionService#executeReporteMultiple(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	public ReporteResult executeReporteMultiple(ReporteParams reportParams) throws Exception {
		log.debug("Entering 'executeReporteMultiple' method");
		ReporteResult reporteResult = new ReporteResult();
		Connection connection = null;
		boolean generoError = false;
		Exception error = null;
		try {
			ClassPathResource resource = new ClassPathResource(reportParams.getJasperFileName());
			/*JasperReport jasperReport = (JasperReport) JRLoader.loadObject(resource.getFile());*/
			
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
			//
			
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
			//
						
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
							//
							
							String nombreArchivoConsolidadoRegion = String.format(Constants.FORMATO_NOMBRE_REPORTE_CONSOLIDADO, codigoRegion);
							String rutaArchivoConsolidadoRegion = rutaCarpetaRegion + nombreArchivoConsolidadoRegion;
							
							JasperExportManager.exportReportToPdfFile(jasperPrint, rutaArchivoConsolidadoRegion);
							
							if(log.isDebugEnabled())
								this.log.debug("Reporte generado.");
						}
					}
					//
					
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
			e.printStackTrace();
			generoError = true;
			error = e;
		}
		
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
			if (generoError) throw new Exception(error);

		}
		return reporteResult;
	}

}
