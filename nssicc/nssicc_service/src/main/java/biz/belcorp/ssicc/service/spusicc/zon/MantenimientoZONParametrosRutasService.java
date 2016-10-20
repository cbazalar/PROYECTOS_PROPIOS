package biz.belcorp.ssicc.service.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoZONParametrosRutasService"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */
/**
 * @author peextdoliva
 *
 */
public interface MantenimientoZONParametrosRutasService extends Service {

	/**
	 * Devuelve la lista de Parametros de Ruta de directorio
	 * @param criteria
	 * @return
	 */
	public List getParametrosRutasList(Map criteria);

	/**
	 * Elimina los parametros de Ruta de directorio seleccionados
	 * @param items
	 */
	public void deleteParametroRuta(String[] items, String codigoUsuario);

	/**
	 * Actualiza el parametro de Ruta de directorio seleccionados
	 * @param params
	 */
	public void updateParametroRutaDirectorio(Map params);

	/**
	 * Actualiza el parametro de Ruta de directorio seleccionados
	 * @param params
	 */
	public void insertParametroRutaDirectorio(Map params);
	
	
}
