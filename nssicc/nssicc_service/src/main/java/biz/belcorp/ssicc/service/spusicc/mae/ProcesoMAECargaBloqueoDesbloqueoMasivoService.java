package biz.belcorp.ssicc.service.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAECargaBloqueoDesbloqueoMasivoService extends Service {

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
	List executeValidarCargaBloqueoDesbloqueoMasivos(Map params);
	
	/**
	 * Ejecuta el proceso de Actualizacion de Bloqueo/Desbloqueo de Clientes
	 * 
	 * @param params
	 * @return
	 */
	void executeActualizarBloqueoDesbloqueoMasivos(Map params);
	
}
