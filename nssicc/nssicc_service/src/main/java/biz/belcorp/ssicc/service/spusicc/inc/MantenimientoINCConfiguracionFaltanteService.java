package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoINCConfiguracionFaltanteService extends Service {

	/**
	 * retorna la lista de Concursos Faltantes de Premios
	 * 
	 * @return
	 */
	List getConcursosFaltante(Map criteria);
	
	/**
	 * retorna la lista de regiones para Concursos Faltantes de Premios
	 * 
	 * @return
	 */
	List getRegionesFaltante(Map criteria);
	
	/**
	 * retorna la lista de faltantes
	 * 
	 * @return
	 */
	List getFaltantes(Map criteria);
	
	/**
	 * Actualiza registro de la entidad Faltantes de Premios
	 * 
	 * @param params
	 */
	void updateFaltantes(List list)  throws Exception;

}
