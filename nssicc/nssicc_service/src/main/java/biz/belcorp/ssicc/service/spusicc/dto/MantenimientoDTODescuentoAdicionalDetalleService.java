package biz.belcorp.ssicc.service.spusicc.dto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoDTODescuentoAdicionalDetalleService  extends Service {

	/**
	 * retorna la lista de Descuento Adicional Detalle
	 * 
	 * @return
	 */
	public List getListDescuentoAdicionalDetalle(Map criteria);

	/**
	 * Recupera la lista de Descuentos Adicionales
	 * 
	 * @param criteria
	 * @return
	 */
	public List getDescuentosAdicionales(Map criteria);
	
	/**
	 * Inserta registro a la entidad Descuento Adicional Detalle
	 * 
	 * @param params
	 */
	public void insertDescuentoAdicionalDetalle(Map params);
	
	/**
	 * Actualiza registro a la entidad Descuento Adicional Detalle
	 * 
	 * @param params
	 */
	public void updateDescuentoAdicionalDetalle(Map params);
	
	/**
	 * Elimina registro a la entidad Descuento Adicional Detalle
	 * 
	 * @param params
	 */
	public void deleteDescuentoAdicionalDetalle(Map params);


}
