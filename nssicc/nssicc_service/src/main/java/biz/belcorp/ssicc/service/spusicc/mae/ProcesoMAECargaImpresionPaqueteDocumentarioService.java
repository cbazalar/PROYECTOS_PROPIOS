package biz.belcorp.ssicc.service.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAECargaImpresionPaqueteDocumentarioService extends Service {

	/**
	 * Retorna los registros del excel del archivo cargado 
	 * 
	 * @param criteria
	 * @return
	 */
	Map cargarArchivoExcel(Map criteria) throws Exception;

	
	/**
	 * Ejecuta el proceso de insercion de Carga de Impresion Paquete Documentario
	 * 
	 * @param params
	 */
	void executeInsertarCargaImpresionPaqueteDocumentario(Map params);

	/**
	 * Ejecuta el proceso de Validacion de Carga de Impresion Paquete Documentario
	 * 
	 * @param params
	 */
	List executeValidarCargaImpresionPaqueteDocumentario(Map params);
	
	
	/**
	 * Ejecura el proceso de actualizacion de Impresion Paquete Documentario
	 * 
	 * @param params
	 */
	void executeActualizarCargaImpresionPaqueteDocumentario(Map params);
	
}
