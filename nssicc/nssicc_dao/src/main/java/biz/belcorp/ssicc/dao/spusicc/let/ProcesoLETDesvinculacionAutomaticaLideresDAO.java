package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoLETDesvinculacionAutomaticaLideresDAO extends DAO{
	
	/**
	 * Ejecuta el proceso de Desvinculacion Automatica de Lideres
	 * @param params
	 */
	public void executeDesvinculacionAutomaticaLideres(Map params);
}