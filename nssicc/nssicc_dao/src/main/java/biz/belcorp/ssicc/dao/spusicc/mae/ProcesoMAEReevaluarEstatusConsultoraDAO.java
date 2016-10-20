package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAEReevaluarEstatusConsultoraDAO extends DAO {
	
	/**
	 * Verificamos si existen regiones cerradas para la campaa de proceso
	 * 
	 * @param criteria
	 * @return
	 */
	boolean existenRegionesCerradas(Map criteria);

	/**
	 * Ejecuta el proceso de reevaluar estatus de consultoras
	 * 
	 * @param params
	 */
	void executeReevaluarEstatusConsultora(Map params);

}
