package biz.belcorp.ssicc.dao.spusicc.pre;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoPREValidacionMatrizDAO extends DAO {

	/**
	 * retorna la lista de Validacion Matriz
	 * 
	 * @return
	 */
	public List getListValidacionMatriz(Map criteria);

	/**
	 * Actualiza registro a la entidad Validacion Matriz
	 * 
	 * @param params
	 */
	public void updateValidacionMatriz(Map params);
	
}
