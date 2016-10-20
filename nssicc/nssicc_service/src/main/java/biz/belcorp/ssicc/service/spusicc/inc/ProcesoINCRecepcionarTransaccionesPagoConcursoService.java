package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCRecepcionarTransaccionesPagoConcursoService extends Service {

	/**
	 * Retorna la lista de Pago Concurso
	 * @return
	 */
	List getListPagoConcurso();
	
	/**
	 * Retorna la lista de Motivo Pago
	 * @return
	 */
	List getListMotivoPago();
	
	/**
	 * Retorna los registros del excel del archivo cargado 
	 * 
	 * @param criteria
	 * @return
	 */
	Map cargarArchivoExcel(Map criteria) throws Exception;	
	
	/**
	 * Ejecura el proceso de Actualizar el Recepcionar Transacciones Pago Concurso
	 * 
	 * @param params
	 */
	void executeActualizarTransaccionesPagoConcurso(Map params);
	
}