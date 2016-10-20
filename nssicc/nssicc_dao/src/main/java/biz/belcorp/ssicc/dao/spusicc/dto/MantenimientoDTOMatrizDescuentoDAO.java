package biz.belcorp.ssicc.dao.spusicc.dto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoDTOMatrizDescuentoDAO extends DAO {	

	/**
	 * retorna la lista de Matriz Descuento
	 * 
	 * @return
	 */
	public List getListMatrizDescuento(Map criteria);

	/**
	 * Recupera la lista de Grupos de Descuento
	 * 
	 * @param criteria
	 * @return
	 */
	public List getGruposDescuento(Map criteria);
	
	/**
	 * Recupera la lista de Categorias de Descuento
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCategoriasDescuento(Map criteria);
	
	/**
	 * Recupera la lista de Tipos de Oferta
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTiposOferta(Map criteria);
	
	/**
	 * Recupera la lista de Negocios
	 * 
	 * @param criteria
	 * @return
	 */
	public List getNegocios(Map criteria);
	
	/**
	 * Recupera la lista de Unidades de Negocios
	 * 
	 * @param criteria
	 * @return
	 */
	public List getUnidadesNegocio(Map criteria);

	/**
	 * Verifica si ya existe una Matriz de Descuento
	 * 
	 * @return
	 */	
	public boolean existeMatrizDescuento(Map criteria);
	
	/**
	 * Inserta registro a la entidad Matriz Descuento
	 * 
	 * @param params
	 */
	public void insertMatrizDescuento(Map params);
	
	/**
	 * Actualiza registro a la entidad Matriz Descuento
	 * 
	 * @param params
	 */
	public void updateMatrizDescuento(Map params);
	
	/**
	 * Elimina registro a la entidad Matriz Descuento
	 * 
	 * @param calificacionComisionDetalle
	 */
	public void deleteMatrizDescuento(Map params);
	
}

