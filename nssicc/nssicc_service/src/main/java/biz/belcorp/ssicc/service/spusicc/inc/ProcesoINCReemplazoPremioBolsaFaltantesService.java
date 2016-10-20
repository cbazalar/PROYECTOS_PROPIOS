package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCReemplazoPremioBolsaFaltantesService  extends Service {

	/**
	 * Retorna la lista de Concursos Faltantes
	 * @return
	 */
	List getListConcursosFaltantes();

	/**
	 * Ejecura el proceso de Reemplazo de premios en bolsa de faltantes
	 * 
	 * @param params
	 */
	void executeReemplazoPremioBolsaFaltantes(Map params);

}
