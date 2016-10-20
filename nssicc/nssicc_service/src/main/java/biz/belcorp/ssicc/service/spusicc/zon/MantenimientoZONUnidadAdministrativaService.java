package biz.belcorp.ssicc.service.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Aurelio Oviedo
 *
 */
public interface MantenimientoZONUnidadAdministrativaService extends Service{

	/**
	 * Obtiene la lista de regiones
	 * @param criteria
	 * @return
	 */
	public List getRegionesList(Map criteria);
	
	public List getRegionesZonasList(Map criteria);
	
	/**
	 * Inserta una zona
	 * @param map
	 */
	public String insertZona(Map params);

	/**
	 * Actualiza la descripcion de una zona
	 * @param map
	 */
	public void updateZona(Map params);
	
	public int getUltimoCodigoZona(Map criteria);
	
	public String removeRegion(Map criteria);
	
	public String removeZona(Map criteria);
	
	public Map getZona(Integer oidZona);
	
	String executeProcesarArchivoExcel(Map params, String directorioTemporal, String nombreArchivo, Usuario usuario) throws Exception;
}