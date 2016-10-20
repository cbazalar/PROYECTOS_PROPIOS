package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextdoliva
 */

public interface MantenimientoSTOCalificacionesOrdenTransporteService extends Service{

	/**
	 * retorna la lista de Calificaciones de orden de transporte
	 * @param criteria
	 * @return
	 */
	public List getCalificacionesOrdenTransporte(Map criteria);
	
	/**
	 * Elimina registros de la tabla de Calificaciones de orden de transporte
	 * @param items
	 */
	public void deleteCalificacionesOrdenTransporte(String[] items);
	
	/**
	 * Inserta registro en la tabla de Calificaciones de orden de transporte
	 * @param criteria
	 */
	public void insertCalificacionesOrdenTransporte(Map criteria);
	
	/**
	 * Actualiza registro de la tabla de Calificaciones de orden de transporte
	 * @param criteria
	 */
	public void updateCalificacionesOrdenTransporte(Map criteria);
		
}