package biz.belcorp.ssicc.dao.spusicc.dto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoDTODescuentoAdicionalGrupoDAO extends DAO {	

	/**
	 * retorna la lista de Descuento Adicional Grupo
	 * 
	 * @return
	 */
	public List getListDescuentoAdicionalGrupo(Map criteria);

	/**
	 * Inserta registro a la entidad Descuento Adicional Grupo
	 * 
	 * @param params
	 */
	public void insertDescuentoAdicionalGrupo(Map params);
	
	/**
	 * Actualiza registro a la entidad Descuento Adicional Grupo
	 * 
	 * @param params
	 */
	public void updateDescuentoAdicionalGrupo(Map params);
	
	/**
	 * Elimina registro a la entidad Descuento Adicional Grupo
	 * 
	 * @param params
	 */
	public void deleteDescuentoAdicionalGrupo(Map params);
	
}

