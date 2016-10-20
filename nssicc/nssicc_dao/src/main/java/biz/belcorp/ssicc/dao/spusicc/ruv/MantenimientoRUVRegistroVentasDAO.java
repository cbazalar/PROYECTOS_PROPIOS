package biz.belcorp.ssicc.dao.spusicc.ruv;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 */
public interface MantenimientoRUVRegistroVentasDAO extends DAO{
	
	/**
	 * Retorna la lista de Registro de Ventas
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListRegistroVentas(Map criteria);

	
	/**
	 * Retorna la lista de Tasas Impuesto
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTasasImpuesto(Map criteria);	
	
	/**
	 * Retorna Los Canales
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCanales(Map criteria);
	
	/**
	 * Retorna Los Accesos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getAccesos(Map criteria);
	
	/**
	 * Retorna Los SubAccesos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getSubAccesos(Map criteria);
	
	/**
	 * Retorna la lista de Tipos Documento Legal
	 * 
	 * @return
	 */
	public List getTiposDocumentoLegal();
	
	/**
	 * Retorna la lista de Sociedades
	 * 
	 * @param criteria
	 * @return
	 */
	public List getSociedades(Map criteria);

	/**
	 * Retorna la lista de Tipos documento
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTiposDocumento();	
	
	/**
	 * Obtiene formato de fecha, Separador Miles, Separador Decimal y Cantidad de decimales para un pais 
	 *
	 * @param codigoPais
	 * @return
	 */
	public Map getFormatoFechaNumerico(String codigoPais);
	
	/**
	 * Retorna el total de los registro de ventas
	 * @param criteria
	 * @return
	 */
	public String getTotalRegistroVentas(Map criteria);
	
	/**
	 * Retorna el total de los registro de ventas para un rango de numero documento legales
	 * @param criteria
	 * @return
	 */
	public String getTotalRegistroRangoVentas(Map criteria);
	
	/**
	 * retorna un determinado Registro de Venta
	 * 
	 * @return
	 */
	public Map getRegistroVenta(String oid);

	/**
	 * Inserta Registro de Venta
	 * 
	 * @param params
	 */
	public void insertRegistroVenta(Map params);
	
	/**
	 * Actualiza Registro de Venta
	 * 
	 * @param params
	 */
	public void updateRegistroVenta(Map params);
	
	/**
	 * Elimina Registro de Venta
	 * 
	 * @param params
	 */
	public void deleteRegistroVenta(Map params);
	
}
