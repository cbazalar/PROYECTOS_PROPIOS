package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoPEJDespachoCanastasDAO extends DAO{

	/**
	 * Ejecuta el proceso de despacho de Canastas
	 * @param params
	 */
	public void executeProcesoPEJDespachoCanastas(Map params);

}