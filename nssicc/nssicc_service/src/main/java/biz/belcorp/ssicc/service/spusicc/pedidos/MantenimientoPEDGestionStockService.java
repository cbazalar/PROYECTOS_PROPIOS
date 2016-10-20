/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author jvelasquez
 *
 */
public interface MantenimientoPEDGestionStockService extends Service {
	
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
	public String insertGestionStock(Map criteria);
	
	/**
	 * Metodo que devuelve la lista de la existencia.
	 * Si no lo encuentra devolver NULL
	 * @param criteria
	 * @return
	 */
	public List getValidarCuvExistencia(Map criteria);
	
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
	 * Metodo que elimina en tabla PED_GESTI_STOCK
	 * @param criteria
	 */
	public void updateEliminarGestionStock(Map criteria);
}
