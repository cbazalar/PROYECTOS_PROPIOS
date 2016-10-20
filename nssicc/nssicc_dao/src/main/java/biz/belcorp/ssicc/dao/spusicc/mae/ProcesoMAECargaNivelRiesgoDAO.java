package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAECargaNivelRiesgoDAO extends DAO {

	/**
	 * Inserta la Carga de Nivel Riesgo
	 * 
	 * @param params
	 */
	void insertCargaNivelRiesgo(Map params);

	/**
	 * Ejecuta el proceso de Validacion de Carga de Impresion Paquete Documentario
	 * 
	 * @param params
	 */
	void executeValidarCargaNivelRiesgo(Map params);

	/**
	 * Recupera la lista de Clientes que tiene alguna inconsistencia
	 * 
	 * @param params
	 * @return
	 */
	public List getCargaNivelRiesgoList(Map params);
	
	/**
	 * Ejecura el proceso de actualizacion de Impresion Paquete Documentario
	 * 
	 * @param params
	 */
	void executeActualizarCargaNivelRiesgo(Map params);

	/**
	 * Retorna numero carga
	 * @return
	 */
	String getNumeroCarga();
	
}
