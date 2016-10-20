package biz.belcorp.ssicc.service.spusicc.dto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoDTODescuentoAdicionalService  extends Service {

	/**
	 * retorna la lista de Descuento Adicional
	 * 
	 * @return
	 */
	public List getListDescuentoAdicional(Map criteria);

	/**
	 * Inserta registro a la entidad Descuento Adicional
	 * 
	 * @param params
	 */
	public void insertDescuentoAdicional(Map params);
	
	/**
	 * Actualiza registro a la entidad Descuento Adicional
	 * 
	 * @param params
	 */
	public void updateDescuentoAdicional(Map params);
	
	/**
	 * Elimina registro a la entidad Descuento Adicional
	 * 
	 * @param params
	 */
	public void deleteDescuentoAdicional(Map params);


}
