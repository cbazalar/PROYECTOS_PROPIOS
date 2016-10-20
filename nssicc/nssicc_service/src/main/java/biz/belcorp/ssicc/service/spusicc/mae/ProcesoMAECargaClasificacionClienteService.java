package biz.belcorp.ssicc.service.spusicc.mae;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAECargaClasificacionClienteService {
	
	/**
	 * Retorna los registros del excel del archivo cargado 
	 * 
	 * @param criteria
	 * @return
	 */
	Map cargarArchivoExcel(Map criteria) throws Exception;

	
	/**
	 * Ejecuta el proceso de Validacion y recupera los registros con errores
	 * 
	 * @param params
	 * @return
	 */
	List executeValidarCargaClasificacionClientes(Map params);
	
	
	/**
	 * Ejecuta el proceso de Actualizacion de Clasificacion de Clientes
	 * 
	 * @param params
	 * @return
	 */
	void executeActualizarCargaClasificacionClientes(Map params);

}
