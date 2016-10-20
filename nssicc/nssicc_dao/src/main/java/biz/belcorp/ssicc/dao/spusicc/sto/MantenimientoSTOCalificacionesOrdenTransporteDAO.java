package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextdoliva
 */

public interface MantenimientoSTOCalificacionesOrdenTransporteDAO extends DAO{
	
	/**
	 * retorna la lista de Novedades de orden de transporte
	 * @param criteria
	 * @return
	 */
	public List getCalificacionesOrdenTransporte(Map criteria);
	
	
	/**
	 * Elimina registros de la tabla de Calificaciones de orden de transporte
	 * @param criteria
	 */
	public void deleteCalificacionesOrdenTransporte(Map criteria);
	
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