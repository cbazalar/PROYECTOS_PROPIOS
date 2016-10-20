package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCMigracionPuntosConsultoraDAO extends DAO {

	/**
	 * Retorna la lista de Concursos Migracion Puntos
	 * @return
	 */
	List getListConcursosMigracionPuntos();
	
	/**
	 * Inserta Ambito Geografico para la Migracion de Puntos Consultora
	 * 
	 * @param params
	 */
	void insertMigracionPuntosAmbito(Map params);
	
	/**
	 * Inserta la Migracion de Puntos Consultora
	 * 
	 * @param params
	 */
	void insertMigracionPuntosConsultora(Map params);

	/**
	 * Ejecuta el proceso de Validacion de Migracion de Puntos Consultora
	 * 
	 * @param params
	 */
	void executeValidarMigracionPuntosConsultora(Map params);

	/**
	 * Recupera la lista de Clientes que tiene alguna inconsistencia
	 * 
	 * @param params
	 * @return
	 */
	public List getMigracionPuntosConsultoraList(Map params);
	
	/**
	 * Ejecura el proceso de actualizacion de Migracion de Puntos Consultora
	 * 
	 * @param params
	 */
	void executeActualizarMigracionPuntosConsultora(Map params);
	
	/**
	 * Retorna numero carga
	 * @return
	 */
	String getNumeroCarga();
	
}
