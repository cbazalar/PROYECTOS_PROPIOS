package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 *
 */

public interface ProcesoPREActualizarTipoCambioDAO extends DAO
{

	/**
	 * Actualiza el tipo de cambio en la matriz
	 * 
	 * @param params
	 */
	void updateTipoCambio(Map<String, Object> params);

	/**
	 * Actualiza el tipo de cambio en la configuracion de concursos
	 * 
	 * @param params
	 */
	void updateTipoCambioConcursos(Map<String, Object> params);

	/**
	 * Actualiza el tipo de cambio en la configuracion NX
	 * 
	 * @param params
	 */
	void updateTipoCambioNX(Map<String, Object> params);
}
