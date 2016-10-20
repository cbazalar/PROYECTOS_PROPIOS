/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;



/**
 * @author peextllizana
 *
 */
public interface MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO extends DAO {

	
	/**
	 * @param params
	 * @return
	 */
	public List getConsultoraPedidoList(Map params);
		
	public List getCampanaList(Map params);
	
	public void executeProcesoIngresoAnulacionNmps(Map params);
	
	public void executeProcesoGenerarArchivoIngresoAnulacionNmps(Map params);
	
	/**
	 * Metodo que retorna la lista de motivos de devolucion
	 * @param params
	 * @return
	 */
	public List getMotivosDevolucion(Map params);
	
	/**
	 * Metodo que retorna la lista de Tipos de Operacion 
	 * @return
	 */
	public List getTiposOperacionIngresoAnulaciones(Map params);

	/**
	 * Retorna el numero de lote con el cual se va a procesar
	 * @return
	 */
	public String getNumeroLote();
	
	/**
	 * @param params
	 * @return
	 */
	public String getIndicadorNotaMercaderiaPerdida(Map params);
	
	/**
	 * Devuelve lista de valores de trama y pedido
	 * @param params
	 * @return
	 */
	public List getValTramaPedidoList(Map params);
	
	/**
	 * Actualiza los valores de trama y pedido
	 * @param criteria
	 */
	public void updateValTramaPedido(Map criteria);
}
