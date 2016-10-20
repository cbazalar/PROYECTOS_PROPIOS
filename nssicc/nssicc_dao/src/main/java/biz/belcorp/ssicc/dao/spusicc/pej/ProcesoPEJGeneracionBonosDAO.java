package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoPEJGeneracionBonosDAO extends DAO{

	/**
	 * Ejecuta el proceso de Generacion de bonos
	 * @param params
	 */
	public void executeProcesoPEJGeneracionBonos(Map params);

}