/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
  * <p>
 * <a href="InterfazSAMDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@csigcomt.com">Cristhian Roman</a>
 * 
 */
public interface InterfazSAMDAO extends DAO {

	/**Ejecuta la interfaz de envio de movimientos de almacen 
	 * @param criteria
	 */
	public void executeInterfazSAMEnviarMovimientosAlmacen(Map criteria);
				
	/**
	 * Devuelve el numero de movimiento
	 * @param criteria
	 * @return
	 */
	public String getNumeroMovimiento(Map criteria);
	
	/**Devuelve la lista de registros de la interfaz de stock
	 * @param criteria
	 * @return
	 */
	public List getNumeroRegistrosInterfazStock(Map criteria);

	/**
	 * Borra la tabla de la interfaz Recepcionar Productos Nacionales Importados
	 */
	public void deleteInterfazSAMRecepcionarProductosNacionalesImportados();

	/**
	 * Ejecuta la recepcion de la interfaz Recepcionar Productos Nacionales Importados
	 * @param map
	 */
	public void executeInterfazSAMRecepcionarProductosNacionalesImportados(Map map);
	
	/**Ejecuta la interfaz de envio de movimientos de almacen Sicc
	 * @param criteria
	 */
	public void executeInterfazSAMEnviarMovimientosAlmacenSicc(Map criteria);
	
	/**
	 * Obtiene el ultimo Numero de Movimiento de la Entidad [NUMERO SOLICITUD PEDIDOS]
	 * 
	 * @param criteria
	 * @return
	 */
	public String getUltimoNumeroMovimiento(Map criteria);

	/**
	 * Obtiene el ultimo secuencial para la tabla Movimientos almacen Cabecera
	 * 
	 * @param criteria
	 * @return
	 */
	public String getSecuencialMovimientoAlmacen(Map criteria);

	/**
	 * Inserta un registro en la tabla Movimientos almacen Cabecera
	 * 
	 * @param criteria
	 */
	public void insertMovimientoAlmacenCabecera(Map criteria);

	/**
	 * Inserta un registro en la tabla Movimientos almacen Detalle
	 * 
	 * @param criteria
	 */
	public void insertMovimientoAlmacenDetalle(Map criteria);
	
	/**
	 * Ejecuta la interfaz para la reserva de PROL
	 * @param criteria
	 */
	public void executeInterfazSAMEnviarReservaPROL(Map criteria);

	/**
	 * ejecuta el envio de la interfaz cantidad de los productos 
	 * @param params
	 */
	public void executeInterfazSAMEnviarCantidadProducto(Map params);

	/**
	 * Ejecuta la recepcion de lotes producto
	 * @param params
	 */
	public void executeRecepcionLotesProducto(Map params);
	
	/**
	 * Ejecuta la eliminacion en la tabla BEL_STOCK para insertar la data nueva
	 * que llega desde el excel.
	 * @param params
	 */
	public void deleteInterfazSAMRecepcionarStockMAV(Map params);
	
	/**
	 * Carga los registros desde el archivo de texto hacia la tabla BEL_STOCK
	 * @param params
	 */
	public void insertInterfazSAMRecepcionarStockMAV(Map params);
	
	/**
	 * Obtiene el OID del almacen para MAV desde la tabla de parametros
	 * @param codigoPais
	 * @return
	 */
	public String getOidAlmacenMAV(String codigoPais);
	
	/**
	 * Ejecuta la Recepcion de Negocio
	 * @param params
	 */
	public void executeRecepcionNegocio(Map params);
	
	/**
	 * Ejecuta la Recepcion de Super Genericos
	 * @param params
	 */
	public void executeRecepcionSuperGenerico(Map params);
	
	/**
	 * Ejecuta la Recepcion de Super Genericos
	 * @param params
	 */
	public void executeRecepcionGenerico(Map params);
	
	/**
	 * Ejecuta la Recepcion de Descripcion de Productos por Idioma
	 * @param params
	 */
	public void executeRecepcionDescripcionProductoIdioma(Map params);
	
	/**
	 * Ejecuta la Recepcion de Productos
	 * @param params
	 */
	public void executeRecepcionProducto(Map params);
	
	/**Ejecuta la interfaz de envio de movimientos de almacen Colombia
	 * @param criteria
	 */
	public void executeInterfazSAMEnviarMovimientosAlmacenColombia(Map criteria);
	
	
	/**Ejecuta la interfaz de envio de movimientos de almacen Sicc Colombia
	 * @param criteria
	 */
	public void executeInterfazSAMEnviarMovimientosAlmacenSiccColombia(Map criteria);
}