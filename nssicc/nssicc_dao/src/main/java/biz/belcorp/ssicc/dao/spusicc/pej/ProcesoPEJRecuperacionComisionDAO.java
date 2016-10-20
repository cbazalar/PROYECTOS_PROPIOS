package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoPEJRecuperacionComisionDAO extends DAO{

	/**
	 * Ejecuta el proceso de Recuperacion de comision
	 * @param params
	 */
	public void executeProcesoPEJRecuperacionComision(Map params);

}
