package biz.belcorp.ssicc.dao.spusicc.dto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoDTORangoDescuentoDAO extends DAO {	

	/**
	 * retorna la lista de Matriz Descuento
	 * 
	 * @return
	 */
	public List getListRangoDescuento(Map criteria);

	/**
	 * Recupera la lista de Grupos de Descuento
	 * 
	 * @param criteria
	 * @return
	 */
	public List getGruposDescuento(Map criteria);
	
	/**
	 * recupera el ultima secuencia + 1 para Matriz Descuento
	 * 
	 * @param params
	 */
	public String getMaxRangoDescuento(String codigoGrupo);

	/**
	 * Inserta registro a la entidad Matriz Descuento
	 * 
	 * @param params
	 */
	public void insertRangoDescuento(Map params);
	
	/**
	 * Actualiza registro a la entidad Matriz Descuento
	 * 
	 * @param params
	 */
	public void updateRangoDescuento(Map params);
	
	/**
	 * Elimina registro a la entidad Matriz Descuento
	 * 
	 * @param calificacionComisionDetalle
	 */
	public void deleteRangoDescuento(Map params);
	
}

