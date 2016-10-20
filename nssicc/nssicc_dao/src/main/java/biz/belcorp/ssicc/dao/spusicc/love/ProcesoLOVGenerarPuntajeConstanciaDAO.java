package biz.belcorp.ssicc.dao.spusicc.love;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLOVGenerarPuntajeConstanciaDAO
		extends DAO {

	/**
	 * Proceso que Genera Puntaje por Constancia para el programa LOVE
	 * 
	 * @param params
	 */
	public void executeGenerarPuntajeConstancia(Map params);

}