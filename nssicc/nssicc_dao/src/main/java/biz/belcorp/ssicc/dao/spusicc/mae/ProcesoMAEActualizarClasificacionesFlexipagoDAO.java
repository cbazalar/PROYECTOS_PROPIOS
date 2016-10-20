package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author ghuertasa
 *
 */
public interface ProcesoMAEActualizarClasificacionesFlexipagoDAO
		extends DAO {

	/**
	 * Proceso que calcula y asignan descuentos
	 * 
	 * @param params
	 */
	public void executeActualizarClasificacionesFlexipago(Map params);
	
	
	/**
	 *  Proceso que actualiza Clasificaciones Nuevas Descuento
	 * @param params
	 */
	public void executeActualizarClasificacionNuevasDcto(Map params);

}
