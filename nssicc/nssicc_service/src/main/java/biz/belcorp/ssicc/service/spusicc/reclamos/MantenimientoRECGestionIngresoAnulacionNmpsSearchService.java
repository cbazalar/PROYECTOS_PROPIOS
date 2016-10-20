package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoRECGestionIngresoAnulacionNmpsSearchService extends Service {

	/**
	 * Retorna la Lista de Pedidos por Consultora
	 * @param params
	 */
	public List getConsultoraPedidoList(Map params);
	
	/**
	 * @param params
	 * @return La Lista de Campaas
	 */
	public List getCampanaList(Map params);
	
	/**
	 * Ejecuta el Proceso de Anulacin de Pedidos
	 * @param params
	 */
	public void executeProcesoIngresoAnulacionNmps(Map params);
	
	/**
	 * Ejecuta el Proceso de Generacin de Archivos de Anulacin de Pedidos
	 * @param params
	 */
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
	 * @param criteria
	 * @return La lista de pedidos a anular
	 */
	public List executeCargaArchivoExcel(Map criteria) throws Exception;

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
}
