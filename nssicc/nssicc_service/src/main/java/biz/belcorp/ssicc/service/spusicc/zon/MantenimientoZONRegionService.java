package biz.belcorp.ssicc.service.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface MantenimientoZONRegionService extends Service{

	/**
	 * Obtiene la lista de regiones
	 * @param criteria
	 * @return
	 */
	public List getRegionesList(Map criteria);

	/**
	 * Elimina logicamente una lista de regiones seleccionadas
	 * @param oidRegiones
	 * @param usuario
	 * @param codigoPeriodo 
	 */
	public void deleteRegiones(String oidRegiones, String usuario, String codigoPeriodo);

	/**
	 * Obtiene el indicador de activa de las zonas filtradas por el oid de sus regiones
	 * @param oidRegion
	 * @return
	 */
	public List getZonasIndActivasByOidRegion(Integer oidRegion);

	/**
	 * Obtiene la region por medio del Oid de regin
	 * @param oidRegion
	 * @return
	 */
	public Map getRegion(Integer oidRegion);

	/**
	 * Encuentra una region para ser validada
	 * @param codigoRegionRegistro
	 * @return
	 */
	public Integer getEncuentraRegionByCodigoRegion(String codigoRegionRegistro);

	/**
	 * Inserta una region
	 * @param map
	 */
	public void insertRegion(Map params);

	/**
	 * Actualiza la descripcion de una region
	 * @param map
	 */
	public void updateRegion(Map map);

	/**
	 * Elimina una region fisicamente
	 * @param integer
	 */
	public void deleteRegionFisicamente(Integer integer);

}