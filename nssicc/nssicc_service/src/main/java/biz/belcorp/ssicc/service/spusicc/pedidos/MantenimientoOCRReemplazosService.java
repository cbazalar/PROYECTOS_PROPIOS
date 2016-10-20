package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextdoliva
 */

public interface MantenimientoOCRReemplazosService extends Service {	     

	/**
	 * Devuelve la lista de Reemplazos segun el periodo
	 * @param criteria
	 * @return
	 */
	public List getReemplazosByPeriodo(Map criteria);
	
	/**
	 * Elimina el reemplazo por el oid
	 * @param criteria
	 */
	public void deleteReemplazos(Map criteria);
	
	/**
	 * Inserta el reemplazo
	 * @param criteria
	 */
	public void insertOCRReemplazos(Map criteria);

	/**
	 * actualiza el indicador de actividad en el reemplazo
	 * @param criteria
	 */
	public void updateReemplazos(Map criteria);
}