package biz.belcorp.ssicc.service.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService extends Service {

	/**
	 * Verifica si una region ha sido procesada para un periodo dado.
	 * 
	 * @param params
	 * @return
	 */
	public boolean verificaRegionProcesada(Map params);
	
	/**
	 * Proceso que Genera numero de Pedidos x Seccion al cierre de Region
	 * 
	 * @param params
	 */
	public void executeGenerarNumeroPedidosSeccionPeriodo(Map params);

	/**
	 * Valida si el archivo cumple con el formato
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	boolean validarArchivoExcel(Map criteria)throws Exception;
	
	/**
	 * Ejecuta la carga del archivo excel
	 * @param criteria
	 * @throws Exception
	 */
	public void executeCargaArchivoExcel(Map criteria) throws Exception;

	/**
	 * Devulve la lista de regiones que han cerrado en el peridod
	 * @param params
	 * @return
	 */
	public List getRegionesCerradas(Map params);

}
