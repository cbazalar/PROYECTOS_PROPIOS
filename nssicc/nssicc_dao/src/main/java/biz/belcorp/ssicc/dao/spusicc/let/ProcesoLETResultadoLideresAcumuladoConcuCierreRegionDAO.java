package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoLETResultadoLideresAcumuladoConcuCierreRegionDAO extends DAO{

	/**
	 * Ejecuta el proceso de resultado de lideres acumulado por concurso al cierre de region
	 * 
	 * @param params
	 */
	public void executeProcesoLETResultadoLideresAcumuladoConcuCierreRegion(Map params);

}
