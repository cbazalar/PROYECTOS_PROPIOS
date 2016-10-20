package biz.belcorp.ssicc.dao.spusicc.love;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLOVGenerarPuntajeComprasDAO
		extends DAO {

	/**
	 * Proceso que Genera Puntaje por Compras para el programa LOVE
	 * 
	 * @param params
	 */
	public void executeGenerarPuntajeCompras(Map params);

}
