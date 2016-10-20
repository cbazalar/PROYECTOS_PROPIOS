package biz.belcorp.ssicc.dao.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Implementacion del DAO que ejecutara los metodos de proceso de parametros de rutas
 * <p>
 * <a href="MantenimientoZONDirectorioDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
public interface MantenimientoZONParametrosRutasDAO extends DAO {

	/**
	 * Devuelve la lista de Parametros de Ruta de directorio
	 * @param criteria
	 * @return
	 */
	public List getParametrosRutasList(Map criteria);

	/**
	 * Devuelve los Parametros de Ruta de directorio seleccionados
	 * @param criteria
	 */
	public void deleteParametroRuta(Map criteria);
	
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
