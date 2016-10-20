package biz.belcorp.ssicc.service.spusicc.pre;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoPREValidacionMatrizService  extends Service {

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