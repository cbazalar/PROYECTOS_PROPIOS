/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ExcepcionesValidaciones;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Danny Amaro
 *
 */
public interface MantenimientoExcepcionesValidacionesService extends Service{
	
	/**
	 * @param excepcionesValidaciones
	 * @param usuario
	 */
	public void insertExcepcionesValidaciones(ExcepcionesValidaciones excepcionesValidaciones, Usuario usuario);
	
	/**
	 * @param criteria
	 */
	public void removeExcepcionesValidacione(Map criteria);
	
	/**
	 * @param excepcionesValidaciones
	 * @param usuario
	 */
	public void insertExcepcionesValidacionesHistorico(ExcepcionesValidaciones excepcionesValidaciones, Usuario usuario);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getExcepcionesValidaciones(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getExcepcionesValidacionesExist(Map criteria);
	
}
