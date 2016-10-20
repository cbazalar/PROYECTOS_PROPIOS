package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoINCConfiguracionFaltanteDAO extends DAO {	

	/**
	 * Retorna la lista de Concursos Faltantes de Premios
	 * 
	 * @return
	 */
	List getConcursosFaltante(Map criteria);
	
	/**
	 * Retorna la lista de premios de Concursos Faltantes de Premios
	 *  
	 * @param codigoConcurso
	 * @return
	 */
	List getPremiosFaltante(Map criteria);
	
	/**
	 * Retorna la lista de regiones para Concursos Faltantes de Premios
	 * 
	 * @return
	 */
	List getRegionesFaltante(Map criteria);
	
	/**
	 * Retorna la lista de zonas para Concursos Faltantes de Premios y regiones
	 * 
	 * @return
	 */
	List getZonasFaltante(Map criteria);
	
	/**
	 * Retorna la lista de faltantes
	 * 
	 * @return
	 */
	List getFaltantes(Map criteria);
	
	/**
	 * Actualiza registro de la entidad Faltantes de Premios
	 * 
	 * @param params
	 */
	void updateFaltantes(final List list) throws Exception;
	
}
