package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MontoMinimo;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoPEDMontoMinimoService extends Service {

	/**
	 * Obtiene la lista de Tipos de Solicitud
	 * @return
	 */
	public List getTipoSolicitud();
	
	/**
	 * Obtiene la lista depedidos con monto minimo
	 * @param criteria
	 * @return
	 */
	public List getMontominimoList(Map criteria);
	
	/**
	 * Obtiene el objeto Monto Minimo
	 * @param criteria
	 * @return
	 */
	public MontoMinimo getMontoMinimoObject(Map criteria);
	
	/**
	 * Inserta un Monto Minimo
	 * @param criteria
	 */
	public void insertMontoMinimo(Map criteria);
	
	/**
	 * Actualiza el Monto Minimo
	 * @param criteria
	 */
	public void updateMontoMinimo(Map criteria);
	
	/**
	 * Elimina el monto minimo
	 * @param criteria
	 * @param items
	 */
	public void deleteMontoMinimo(Map criteria, String[] items);
	
	/**
	 * obtiene el indicador para actualizar el monto minimo 
	 * @param criteria
	 * @return
	 */
	public String getIndicadorActualizaMontominimo(Map criteria);
	
	/**
	 * Actualiza a cero el indicador de error monto minimo
	 * @param criteria
	 */
	public void updateErrorMontoMinimo(Map criteria);
	
	/**
	 * Inserta en la tabla de auditoria
	 * @param criteria
	 */
	public void insertAuditoriaMontoMinimo(Map criteria);
	
	/**
	 * Obtierne el odi del Monto minimo
	 * @param criteria
	 * @return
	 */
	public int getNextOidMontoMinimo(Map criteria);
	
	/**
	 * Obtiene la lista historica de montos minimos
	 * @param criteria
	 * @return
	 */
	public List getMontominimoHistoricoList(Map criteria);	
}