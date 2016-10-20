package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


public interface ProcesoINCProcesoVencimientoPuntosDAO extends DAO {

	/**
	 * Proceso de Vencimiento de Puntos
	 * 
	 * @param params
	 */
	public void executeProcesoVencimientoPuntos(Map params);
	
}

