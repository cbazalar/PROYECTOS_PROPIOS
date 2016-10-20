package biz.belcorp.ssicc.reportes.framework.service;

import java.util.Map;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteResult;


/**
 * Service de ejecucin de los Reportes ScSiCC.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public interface ReporteExecutionService {

	
	/**
	 * Metodo previo que setea los parametros del reporte y ejecuta el reporte JASPER
	 * @param form
	 * @return
	 */
	public ReporteParams executePrevio(BaseReporteForm form, Map<String, Object> parameterAdicionales) throws Exception;
	
	/**
	 * Metodo principal que setea los parametros del reporte y ejecuta el reporte JASPER
	 * @param form
	 * @return
	 */
	public Map<String, Object> generarReporte(BaseReporteForm form, ReporteParams reporteParams) throws Exception;
	
	/**
	 * Metodo previo que setea los parametros del reporte y ejecuta el reporte (Para Reportes NO JASPER)
	 * @param form
	 * @param parameterAdicionales
	 * @return
	 * @throws Exception
	 */
	public ReporteParams executePrevioNoJASPER(BaseReporteForm form, Map<String, Object> parameterAdicionales) throws Exception;
	
	/**
	 * Metodo principal que setea los parametros del reporte y ejecuta el reporte (Para Reportes NO JASPER)
	 * @param form
	 * @param parameterAdicionales
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> generarReporteNoJASPER(BaseReporteForm form, ReporteParams reporteParams) throws Exception;
	
	
	
	/**
	 * Ejecuta el Reporte ScSiCC.
	 * 
	 * @param reportParams
	 *            parametros del reporte
	 * @return ReporteExecutionResult con los resultados de la ejecucion.
	 */
	public ReporteResult executeReporte(ReporteParams reportParams) throws Exception;
	/**
	 * Metodo que  implementa la construccion del jasper 
	 * Virtualizado
	 * @param reportParams
	 * @return
	 */
	public ReporteResult executeReporteVirtualizer(ReporteParams reportParams) throws Exception;
	
	/**
	 * Metodo que genera varios reportes por region y zona
	 * @param reportParams
	 */
	public ReporteResult executeReporteMultiple(ReporteParams reportParams) throws Exception;
	
}
