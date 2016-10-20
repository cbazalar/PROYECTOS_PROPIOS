/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author jvelasquez
 *
 */
public interface MantenimientoPEDGestionStockDAO extends DAO {
	
	
	/**
	 * Metodo que devuelve la lista de Motivo de Devolucin 
	 * @param criteria
	 * @return
	 */
	public List getGestionStockList(Map criteria);
	
	/**
	 * Metodo que inserta en tabla PED_GESTI_STOCK
	 * @param criteria
	 * @return
	 */
	public void insertGestionStock(Map criteria);
	
	/**
	 * Metodo que devuelve la lista de la existencia.
	 * Si no lo encuentra devolver NULL
	 * @param criteria
	 * @return
	 */
	public List getValidarCuvExistencia(Map criteria);
	
	
	public int getOidTipoCliente(Map criteria);
	
	public int getOidSubTipoCliente(Map criteria);
	
	public int getOidTipoClasificacion(Map criteria);
	
	public int getOidClasificacion(Map criteria);
	
	public int getValidaGestionStockUk(Map criteria);
		
	public int getValidaRegionxZona (Map criteria);
	
	/**
	 * Metodo que actualiza el estado a 0, Desactivar
	 * @param criteria
	 * @return
	 */
	public void updateDesactivarGestionStock(Map criteria);
	
	/**
	 * Metodo que actualiza el estado a 1, Activar
	 * @param criteria
	 * @return
	 */
	public void updateActivarGestionStock(Map criteria);
	
	/**
	 * Metodo que devuelve la lista de la existencia.
	 * Si no lo encuentra devolver NULL
	 * @param criteria
	 * @return
	 */
	public List getValidarSapExistencia(Map criteria);

	/**
	 * Metodo que retorna el indicador que determina la eliminacin de datos
	 * anteriores registrados por usuario actual en la tabla PED_GESTI_STOCK
	 * @param criteria
	 * @return
	 */
	public String getIndicadorEliminacionCodigoSAC(Map criteria);

	/**
	 * Elimina registros pasados hechos por el usuario en session
	 * @param criteria
	 */
	public void deleteGestionStock(Map criteria);

	/**
	 * Metodo que elimina en tabla PED_GESTI_STOCK
	 * @param criteria
	 */
	public void updateEliminarGestionStock(Map criteria);

	/**
	 * Metodo que elimina un pedido en la entidad PED_GESTI_STOCK  
	 * @param criteria
	 */
	public void deleteEliminarGestionStock(Map criteria);
	
}
