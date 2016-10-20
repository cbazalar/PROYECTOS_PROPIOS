/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cronograma;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCRAGrupoZonaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */

public interface MantenimientoCRAGrupoZonaService extends Service{
	
	/**
	 * Metodo que devuelve los grupos para llenar combo box.
	 * @return
	 */
	public List getGrupos(Map criteria);
	
	/**
	 * Metodo que devuelve los grupos y zonas.
	 * @param criteria
	 * @return
	 */
	public List getGrupoZonas(Map criteria);
	
	/**
	 * Metodo que inactiva un grupo zona.
	 * @param criteria
	 */
	public void deleteGrupoZona(Map criteria);

	/**
	 * Metodo que devuelve las regiones no asignadas
	 */
	public List getGrupoRegionNoAsignadas();

	/**
	 * @param codigoGrupo
	 * @return
	 */
	public List getZonaAsignadasGrupo(Map criteria);

	/**
	 * @param map
	 */
	public void insertNombreGrupoZona(Map map);

	/**
	 * @param map
	 */
	public void updateNombreGrupoZona(Map map);

	/**
	 * @param map
	 */
	public void updateZonasAsignadas(Map map);	
}
