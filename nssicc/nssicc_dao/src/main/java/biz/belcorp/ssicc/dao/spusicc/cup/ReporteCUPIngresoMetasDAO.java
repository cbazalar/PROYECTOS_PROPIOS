package biz.belcorp.ssicc.dao.spusicc.cup;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ReporteCUPIngresoMetasDAO extends DAO{
	
	/**
	 * Ejecute el proceso que genera la data para el reporte de Ingreso de Metas en estado Aprobado o Rechazado
	 * @param params
	 */
	public void executeReporteCUPIngresoMetas(Map params);

}