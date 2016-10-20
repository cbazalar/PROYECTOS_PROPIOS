package biz.belcorp.ssicc.dao.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="MantenimientoCRAActividadDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia </a>
 */

public interface MantenimientoCRAActividadDAO extends DAO {

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
	 * Metodo que guarda la actividad
	 * @param criteria
	 */
	public void insertActividad(Map criteria);

	
	/**
	 * Metodo que devuelve la lista de clases de actividad
	 * @param criteria
	 * @return
	 */
	public List getClaseActividades(Map criteria);

	
	/**
	 * Metodo que actualiza la actividad
	 * @param criteria
	 */
	public void updateActividad(Map criteria);
	
	/**
	 * Metodo que evalua la existencia del cdigo de actividad
	 * @param criteria
	 * @return
	 */
	public int getCodigoActividadExistente(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getActividadesExcepto(Map criteria);

}
