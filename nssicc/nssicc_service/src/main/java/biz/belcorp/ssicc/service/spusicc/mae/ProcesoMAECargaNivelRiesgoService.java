package biz.belcorp.ssicc.service.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAECargaNivelRiesgoService extends Service {

	/**
	 * Retorna los registros del excel del archivo cargado 
	 * 
	 * @param criteria
	 * @return
	 */
	Map cargarArchivoExcel(Map criteria) throws Exception;

	/**
	 * Ejecuta el proceso de Validacion de Carga de Nivel Riesgo
	 * 
	 * @param params
	 */
	List executeValidarCargaNivelRiesgo(Map params);
	
	
	/**
	 * Ejecura el proceso de actualizacion de Nivel Riesgo
	 * 
	 * @param params
	 */
	void executeActualizarCargaNivelRiesgo(Map params);
	
}