package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoPEJCargaTarjetaPaycardDAO extends DAO{

	/**
	 * Ejecuta el proceso de carga de tarjeta paycard cta. cte.
	 * @param params
	 */
	public void executeProcesoPEJCargaTarjetaPaycard(Map params);

}