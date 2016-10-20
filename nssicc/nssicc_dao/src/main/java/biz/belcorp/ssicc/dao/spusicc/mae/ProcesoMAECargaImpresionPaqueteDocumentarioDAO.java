package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAECargaImpresionPaqueteDocumentarioDAO extends DAO {

	/**
	 * Inserta la Carga de Impresion Paquete Documentario
	 * 
	 * @param params
	 */
	void insertCargaImpresionPaqueteDocumentario(Map params);

	/**
	 * Ejecuta el proceso de insercion de Carga de Impresion Paquete Documentario
	 * 
	 * @param params
	 */
	void executeInsertarCargaImpresionPaqueteDocumentario(Map params);

	/**
	 * Ejecuta el proceso de Validacion de Carga de Impresion Paquete Documentario
	 * 
	 * @param params
	 */
	void executeValidarCargaImpresionPaqueteDocumentario(Map params);

	/**
	 * Recupera la lista de Clientes que tiene alguna inconsistencia
	 * 
	 * @param params
	 * @return
	 */
	public List getCargaImpresionPaqueteDocumentarioList(Map params);
	
	/**
	 * Ejecura el proceso de actualizacion de Impresion Paquete Documentario
	 * 
	 * @param params
	 */
	void executeActualizarCargaImpresionPaqueteDocumentario(Map params);

	/**
	 * Retorna numero carga
	 * @return
	 */
	String getNumeroCarga();
	
}
