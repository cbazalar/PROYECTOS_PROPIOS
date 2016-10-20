package biz.belcorp.ssicc.dao.spusicc.love;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLOVEliminarPuntajeDAO
		extends DAO {

	/**
	 * Proceso que Elimina Puntaje por incumplir con el numero maximo de campaas
	 * sin pasar pedido
	 * 
	 * @param params
	 */
	public void executeEliminarPuntaje(Map params);

}

