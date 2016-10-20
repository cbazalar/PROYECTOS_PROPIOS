package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TarjetaLider;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TarjetaPago;

/**
 * <p>
 * <a href="MantenimientoLECTarjetaPagoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="dtorres@sigcomt.com">Diego Torres</a>
 *         
 */
public interface MantenimientoLECTarjetaPagoDAO extends DAO{
	
	/**
	 * Obtiene la lista de tarjetas pago en base a los criterios de busqueda
	 * @param params
	 * @return
	 */
	public List getTarjetaPagoByCriteria(Map params);
	
	/**
	 * Obtiene un registro en base a id 
	 * 
	 * @param id
	 * @return
	 */
	public TarjetaPago getTarjetaPago(String id);
	
	/**
	 * Inserta un registro de tarjeta de pago en la bd
	 * @param tarjetaPago
	 * @param usuario
	 */
	public void insertTarjetaPago(TarjetaPago tarjetaPago, Usuario usuario);
	
	/**
	 * Actualiza un registro de tarjeta pago en la base de datos 
	 * @param tarjetaPago
	 * @param usuario
	 */
	public void updateTarjetaPago(TarjetaPago tarjetaPago, Usuario usuario);
	
	/**
	 * Desactiva un registro de tarjeta pago en la base de datos 
	 * @param tarjetaPago
	 * @param usuario
	 */
	public void deleteTarjetaPago(TarjetaPago tarjetaPago);
	
	/**
	 * Obtiene la lista de estados de tarjetas pago en base a los criterios de busqueda
	 * @return
	 */
	public List getEstadoTarjetaPago();
	
	/**
	 * Obtiene la lista de tarjetas pago por tarjeta de pago en base a los criterios de busqueda
	 * @param params
	 * @return
	 */
	public List getLecAsociacionTarjetaPagoPorTarjetaPagoByCriteria(Map params);
	
	/**
	 * Obtiene la lista de tarjetas pago por lider en base a los criterios de busqueda
	 * @param params
	 * @return
	 */
	public List getLecAsociacionTarjetaPagoPorLiderByCriteria(Map params);
	
	/**
	 * Devuelve nombre lider
	 * @param codigoLider
	 * @return
	 */
	public String getNombreLider(String codigoLider);
	
	/**
	 * Devuelve boolean
	 * @param codigoLider
	 * @return
	 */
	public boolean validaCodigoLider(String codigoLider);
	
	/**
	 * Devuelve estado de la tarjeta de pago
	 * @param codigoLider
	 * @return
	 */
	public String validaLiderTarjetaAsociada(String codigoLider);
	
	/**
	 * Inserta un registro de tarjeta de lider en la bd
	 * @param tarjetaLider
	 * @param usuario
	 */
	public void insertTarjetaLider(TarjetaLider tarjetaLider, Usuario usuario);
	
	/**
	 * Actualiza estado de tarjeta pago en la base de datos 
	 * @param tarjetaPago
	 * @param usuario
	 */
	public void updateEstadoTarjetaPago(TarjetaPago tarjetaPago, Usuario usuario);
	
	/**
	 * Obtiene un registro en base a id 
	 * 
	 * @param id
	 * @return
	 */
	public TarjetaLider getTarjetaLider(Map params);
	
	/**
	 * Actualiza registro de tarjeta lider en la base de datos 
	 * @param params
	 */
	public void updateTarjetaLider(Map params);
	
	/**
	 * Obtiene un registro en base a numeroTarjeta 
	 * 
	 * @param numeroTarjeta
	 * @return
	 */
	public TarjetaPago getTarjetaPagoByNumeroTarjeta(String numeroTarjeta);
	
	/**
	 * valida tarjeta pago asociada
	 * @param numeroTarjeta
	 * @return
	 */
	public String validaTarjetaPagoAsociada(String numeroTarjeta);
	
	/**
	 * Devuelve boolean
	 * @param numeroTarjeta
	 * @return
	 */
	public boolean validaNumeroTarjeta(String numeroTarjeta);
	
	/**
	 * Actualiza registro de mantenimiento tarjeta lider en la base de datos 
	 * @param params
	 */
	public void updateClienteLider(Map params);
	
	/**
	 * Obtiene la lista de tarjetas pago en base a los criterios de busqueda
	 * @param params
	 * @return
	 */
	public List getLecConsultaTarjetasPago(Map params);
	
	/**
	 * Elimina un registro de tarjeta lider en la base de datos 
	 * @param params
	 */
	public void deleteTarjetaLider(Map params);
}
