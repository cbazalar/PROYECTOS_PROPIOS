package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author itocto
 *
 */
public interface ProcesoINCCancelacionPuntosProgramaPuntosDAO
		extends DAO {

	/**
	 * Proceso que va a cancelar los puntos para programas de puntos
	 * 
	 * @param params
	 */
	public void executeCancelacionPuntosProgramaPuntos(Map params);

}
