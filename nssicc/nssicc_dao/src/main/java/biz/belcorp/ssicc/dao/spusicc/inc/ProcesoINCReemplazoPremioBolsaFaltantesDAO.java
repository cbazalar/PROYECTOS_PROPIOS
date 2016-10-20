package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCReemplazoPremioBolsaFaltantesDAO extends DAO {

	/**
	 * Retorna la lista de Concursos Faltantes
	 * @return
	 */
	List getListConcursosFaltantes();

	/**
	 * Retorna la lista de Premios Faltantes
	 * @return
	 */
	List getListPremiosFaltantes(String numeroConcurso);

	/**
	 * Retorna la lista de Premios Reemplazos Vigentes
	 * @return
	 */
	List getListPremiosReemplazos(String numeroConcurso);

	/**
	 * Ejecura el proceso de Reemplazo de premios en bolsa de faltantes
	 * 
	 * @param params
	 */
	void executeReemplazoPremioBolsaFaltantes(Map params);

	
}
