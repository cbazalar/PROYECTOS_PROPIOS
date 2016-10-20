package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * The Interface ReporteSACFechaRealEntregaService.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 04/06/2014
 */
public interface ReporteSTOEnviarInformeEstatusSolicitudesCreditoService extends Service{
	
	/**
	 * Ejecuta Reporte respectivo
	 * @param criteria 
	 */
	public void grabarReporte(Map params) throws Exception;
}
