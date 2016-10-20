package biz.belcorp.ssicc.dao.spusicc.dto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoDTOGrupoDescuentoDAO extends DAO {	

	/**
	 * retorna la lista de Grupo Descuento
	 * 
	 * @return
	 */
	public List getListGrupoDescuento(Map criteria);

	/**
	 * recupera el ultima secuencia + 1 para Grupo Descuento
	 * 
	 * @param params
	 */
	public String getMaxGrupoDescuento();

	/**
	 * Inserta registro a la entidad Grupo Descuento
	 * 
	 * @param params
	 */
	public void insertGrupoDescuento(Map params);
	
	/**
	 * Actualiza registro a la entidad Grupo Descuento
	 * 
	 * @param params
	 */
	public void updateGrupoDescuento(Map params);
	
	/**
	 * Elimina registro a la entidad Grupo Descuento
	 * 
	 * @param calificacionComisionDetalle
	 */
	public void deleteGrupoDescuento(Map params);
	
}

