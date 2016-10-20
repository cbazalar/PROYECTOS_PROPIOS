package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCCalcularPuntajeConsultoraDAO extends DAO {

	/**
	 * Proceso que Calcula Puntaje Consultoras
	 * 
	 * @param params
	 */
	public void executeCalcularPuntajeConsultora(Map params);
	
}
