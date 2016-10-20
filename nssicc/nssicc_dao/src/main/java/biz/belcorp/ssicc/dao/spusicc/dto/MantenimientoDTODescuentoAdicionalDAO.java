package biz.belcorp.ssicc.dao.spusicc.dto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoDTODescuentoAdicionalDAO extends DAO {	

	/**
	 * retorna la lista de Descuento Adicional
	 * 
	 * @return
	 */
	public List getListDescuentoAdicional(Map criteria);

	/**
	 * recupera el ultima secuencia + 1 para Descuento Adicional
	 * 
	 * @param params
	 */
	public String getMaxDescuentoAdicional();

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

