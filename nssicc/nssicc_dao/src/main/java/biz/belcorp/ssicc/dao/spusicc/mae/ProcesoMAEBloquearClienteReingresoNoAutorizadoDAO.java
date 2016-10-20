package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoMAEBloquearClienteReingresoNoAutorizadoDAO
		extends DAO {

	/**
	 * Proceso que va a Bloquear Cliente por Reingreso No Autorizado
	 * 
	 * @param params
	 */
	public void executeBloquearClienteReingresoNoAutorizado(Map params);

}

