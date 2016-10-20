package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCDarPorAtendidoBolsaFaltantesDAO extends DAO {

	/**
	 * Retorna la lista de Concursos Vigentes
	 * @return
	 */
	List getListConcursoVigentes();
	
	/**
	 * Ejecura el proceso de dar por atendido a premios de bolsa de faltantes para que 
	 * no puedan atenderse en el futuro
	 * 
	 * @param params
	 */
	void executeActualizarBolsaFaltantes(Map params);

	/**
	 * Inserta el dato codigo cliente sea valido o no
	 * 
	 * @param bolsaAtendido
	 */
	void insertBolsaAtendido(Map bolsaAtendido);

	/**
	 * Retorna numero lote
	 * @return
	 */
	String getNumeroLote();

	/**
	 * Obtiene los Periodos de los productos no atendidos de la Bolsa de Faltantes
	 * 
	 * @param criteria
	 * @return
	 */
	List getPeriodosProductosNoAtendidos(Map criteria);
	
	/**
	 * Obtiene los Productos no atendidos de la Bolsa de Faltantes
	 * 
	 * @param criteria
	 * @return
	 */
	List getProductosNoAtendidos(Map criteria);
	
}

