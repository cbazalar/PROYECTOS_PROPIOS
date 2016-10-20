package biz.belcorp.ssicc.dao.spusicc.pre;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
public interface ProcesoPREEliminarMatrizFacturacionDAO extends DAO {

	/**
	 * Ejecura el proceso de Eiminar Matriz de Facturación
	 *  
	 * @param params
	 */
	void executeEliminarMatrizFacturacion(Map params);

}
