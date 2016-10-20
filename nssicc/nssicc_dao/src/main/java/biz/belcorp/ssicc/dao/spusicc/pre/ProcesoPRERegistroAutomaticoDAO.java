package biz.belcorp.ssicc.dao.spusicc.pre;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoPRERegistroAutomaticoDAO extends DAO {

	/**
	 * Ejecura el proceso de Registro Automatico de Matriz
	 * 
	 * @param params
	 */
	void executeRegistroAutomatico(Map params);

}
