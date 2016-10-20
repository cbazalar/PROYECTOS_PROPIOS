package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author itocto
 *
 */
public interface ProcesoINCAcumulacionPuntosProgramaConstanciaDAO
		extends DAO {

	/**
	 * Proceso que va a generar los puntos para programas de constancia
	 * 
	 * @param params
	 */
	public void executeAcumulacionPuntosProgramaConstancia(Map params);

}
