/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Danny Amaro
 *
 */
public interface InterfazYOBDAO extends DAO {
		
	/**
	 * Registra la informacion del Archivo de Lotes
	 * @param params
	 */
	public void insertYOBCargaLotesTrazabilidad(Map params);
	
	/**
	 * DEvuelve el periodo y la fecha de facturacion en base a un numero de pedido
	 * @param params
	 * @return
	 */
	public String getDevuelvePeriodoFechaFacturacionNumPedido(Map params);
	
}
