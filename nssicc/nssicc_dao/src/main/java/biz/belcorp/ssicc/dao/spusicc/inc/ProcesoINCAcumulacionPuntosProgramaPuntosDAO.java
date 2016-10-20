package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author itocto
 *
 */
public interface ProcesoINCAcumulacionPuntosProgramaPuntosDAO
		extends DAO {

	/**
	 * Proceso que va a generar los puntos para programas de puntos
	 * 
	 * @param params
	 */
	public void executeAcumulacionPuntosProgramaPuntos(Map params);

}
