package biz.belcorp.ssicc.service.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCRAActividadService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */

public interface MantenimientoCRAActividadService extends Service {

	/**
	 * Metodo que devuelve la lista de actividades
	 * @param criteria
	 * @return
	 */
	public List getActividades(Map criteria);

	/**
	 * Metodo que elimina una actividad
	 * @param criteria
	 */
	public void deleteActividad(Map criteria);

	/**
	 * Metodo que devuelve las clases de actividades
	 * @param criteria
	 * @return
	 */
	public List getClaseActividades(Map criteria);
	
	/**
	 * Metodo que guarda la actividad
	 * @param criteria 
	 */
	public void insertActividad(Map criteria);
	
	/**
	 * Metodo que actualiza la actividad
	 * @param criteria
	 */
	public void updateActividad(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getActividadesExcepto(Map criteria);
	
}
	
	
