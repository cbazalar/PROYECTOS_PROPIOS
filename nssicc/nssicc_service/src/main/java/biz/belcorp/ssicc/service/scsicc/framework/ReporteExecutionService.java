package biz.belcorp.ssicc.service.scsicc.framework;

import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteResult;

/**
 * Service de ejecucin de los Reportes ScSiCC.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public interface ReporteExecutionService {

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
