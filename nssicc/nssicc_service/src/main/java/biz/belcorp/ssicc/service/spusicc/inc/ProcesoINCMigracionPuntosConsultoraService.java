package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCMigracionPuntosConsultoraService extends Service {

	/**
	 * Retorna la lista de Concursos Migracion Puntos
	 * @return
	 */
	List getListConcursosMigracionPuntos();
	
	/**
	 * Retorna los registros del excel del archivo cargado 
	 * 
	 * @param criteria
	 * @return
	 */
	Map cargarArchivoExcel(Map criteria) throws Exception;

	/**
	 * Ejecuta el proceso de Migracion de Puntos Consultora
	 * 
	 * @param params
	 */
	List executeValidarMigracionPuntosConsultora(Map params);
	
	
	/**
	 * Ejecura el proceso de Migracion de Puntos Consultora
	 * 
	 * @param params
	 */
	void executeActualizarMigracionPuntosConsultora(Map params);
	
}