package biz.belcorp.ssicc.dao.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface MantenimientoZONRegionDAO extends DAO{

	/**
	 * Obtiene la lista de regiones
	 * @param criteria
	 * @return
	 */
	public List getRegionesList(Map criteria);

	/**
	 * Elimina logicamente una regin
	 * @param criteria
	 */
	public void deleteRegion(Map criteria);

	/**
	 * Obtiene el indicador de activa de las zonas filtradas por el oid de sus regiones
	 * @param oidRegion
	 * @return
	 */
	public List getZonasIndActivasByOidRegion(Integer oidRegion);

	/**
	 * Obtiene la region por medio del Oid de Regin
	 * @param oidRegion
	 * @return
	 */
	public Map getRegion(Integer oidRegion);

	/**
	 * Encuentra una region a ser validada
	 * @param codigoRegionRegistro
	 * @return
	 */
	public Integer getEncuentraRegionByCodigoRegion(String codigoRegionRegistro);

	/**
	 * Inserta una region
	 * @param params
	 */
	public void insertRegion(Map params);

	/**
	 * Actualiza una region
	 * @param params
	 */
	public void updateRegion(Map params);

	/**Elimina una region fisicamente
	 * @param oidRegion
	 */
	public void deleteRegionFisicamente(Integer oidRegion);
}