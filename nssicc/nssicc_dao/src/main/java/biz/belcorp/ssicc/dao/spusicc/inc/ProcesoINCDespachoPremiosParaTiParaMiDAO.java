package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCDespachoPremiosParaTiParaMiDAO
		extends DAO {

	/**
	 * Proceso que despacha premios pendientes de despacho de concursos para ti para mi.
	 * 
	 * @param params
	 */
	public void executeDespachoPremiosParaTiParaMi(Map params);

}
