package biz.belcorp.ssicc.service.spusicc.mae;

import java.util.Map;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAECargaMasivaDireccionService {
	
	/**
	 * Retorna los registros del excel del archivo cargado 
	 * 
	 * @param criteria
	 * @return
	 */
	Map cargarArchivoExcel(Map criteria) throws Exception;

	
	/**
	 * Ejecuta el proceso de Validacion Carga Masiva de Direccion
	 * 
	 * @param params
	 * @return
	 */
	Map executeValidarCargaMasivaDireccion(Map params);
	
	
	/**
	 * Ejecuta el proceso de Actualizacion de Carga Masiva de Direccion
	 * 
	 * @param params
	 * @return
	 */
	void executeActualizarCargaMasivaDireccion(Map params) throws Exception;

}
