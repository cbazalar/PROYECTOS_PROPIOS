package biz.belcorp.ssicc.dao.spusicc.dto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoDTODescuentoAdicionalDetalleDAO extends DAO {	

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
	 * recupera el ultima secuencia + 1 para Descuento Adicional Detalle
	 * 
	 * @param params
	 */
	public String getMaxDescuentoAdicionalDetalle();

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

