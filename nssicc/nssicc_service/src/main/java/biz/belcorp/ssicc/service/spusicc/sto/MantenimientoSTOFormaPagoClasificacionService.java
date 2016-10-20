package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Gonzalo Javier Huertas Agurto
 */

public interface MantenimientoSTOFormaPagoClasificacionService extends Service{

	/**
	 * Devuelve la lista de Forma de Pago de Clasificacion
	 * @param criteria
	 * @return
	 */
	public List getFormaPagoClasificacionList(Map criteria);

	/**
	 * Elimina las Forma de Pago de Clasificacion
	 * @param criteria
	 */
	public void deleteFormaPagoClasificacion(Map criteria);

	/**
	 * Inserta Forma de Pago de Clasificacion
	 * @param criteria
	 */
	public void insertFormaPagoClasificacion(Map criteria);

}