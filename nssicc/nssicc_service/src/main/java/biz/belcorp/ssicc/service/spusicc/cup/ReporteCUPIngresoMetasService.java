package biz.belcorp.ssicc.service.spusicc.cup;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ReporteCUPIngresoMetasService extends Service{

	/**
	 * Ejecute el proceso que genera la data para el reporte de Ingreso de Metas en estado Aprobado o Rechazado
	 * @param params
	 */
	public void executeReporteCUPIngresoMetas(Map params);

}