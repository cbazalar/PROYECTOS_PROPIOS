package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCDespacharPremiosSolicitudesRetenidasDAO
		extends DAO {

	/**
	 * Proceso que Retiene Solicitudes de Premiacion de Niveles Electivos
	 * 
	 * @param params
	 */
	public void executeDespacharPremiosSolicitudesRetenidas(Map params);

}


