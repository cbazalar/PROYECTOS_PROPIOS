package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCCalcularVencimientoPuntosDAO extends DAO {

	/**
	 * Proceso de Vencimiento de Puntos
	 * 
	 * @param params
	 */
	public void executeCalcularVencimientoPuntos(Map params);
	
}

