package biz.belcorp.ssicc.dao.spusicc.love;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLOVEliminarPremiosElegidosNoAtendidosDAO
		extends DAO {

	/**
	 * Proceso que Elimina Premios Elegidos que no fueron Atendidos
	 * 
	 * @param params
	 */
	public void executeEliminarPremiosElegidosNoAtendidos(Map params);

}

