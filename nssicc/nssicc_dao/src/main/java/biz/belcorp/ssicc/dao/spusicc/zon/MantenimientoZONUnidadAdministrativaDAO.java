package biz.belcorp.ssicc.dao.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface MantenimientoZONUnidadAdministrativaDAO extends DAO{

	/**
	 * Obtiene la lista de regiones
	 * @param criteria
	 * @return
	 */
	public List getRegionesList(Map criteria);
	
	public List getRegionesZonasList(Map criteria);
	
	/**
	 * Encuentra una zona para ser validada
	 * @param map
	 * @return
	 */
	public Integer getEncuentraZonaByCodigoZona(Map params);

	/**
	 * Inserta una zona
	 * @param params
	 */
	public void insertZona(Map params);

	/**
	 * Actualiza una zona
	 * @param params
	 */
	public void updateZona(Map params);
	
	public int getUltimoCodigoZona(Map criteria);
	
	public int getIndicadorEliminarRegion(Map criteria);
	
	public int getIndicadorEliminarZona(Map criteria);
	
	public void removeRegion(Map criteria);
	
	public void removeZona(Map criteria);
	
	public Map getZona(Integer oidZona);
}