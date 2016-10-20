package biz.belcorp.ssicc.dao.spusicc.mav;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAVCargaMasivaDAO extends DAO {

	/**
	 * Elimina los datos de la tabla temporal de Carga Masiva
	 * 
	 * @param params
	 */
	void deleteCargaMasiva();
	
	/**
	 * Inserta un registro de Carga Masiva
	 * 
	 * @param params
	 */
	void insertCargaMasiva(Map params);
	
	/**
	 * Inserta en batch un conjunto de registros de Carga Masiva
	 * 
	 * @param list
	 * @throws Exception
	 */
	void insertCargaMasivaBatch(final List list) throws Exception;

	/**
	 * Ejecuta el proceso de Validacion de los codigo de Clientes cargados
	 * 
	 * @param params
	 */
	void executeValidarCargaMasiva(Map params);

	/**
	 * Recupera el resumen de los parametros de configuracion MAV que seran ingresados
	 * 
	 * @param params
	 * @return
	 */
	public List getResumenCargaMasiva(Map params);
	
	/**
	 * Recupera la lista de errores encontrados en la validacion de la Carga Masiva
	 * 
	 * @param params
	 * @return
	 */
	public List getErroresCargaMasiva(Map params);
	
	/**
	 * Ejecura el proceso de actualizacion de Carga Masiva
	 * 
	 * @param params
	 */
	void executeActualizarCargaMasiva(Map params);

	/**
	 * Retorna numero carga
	 * @return
	 */
	String getNumeroCarga();
	
	/**
	 * Actualiza los codigos de los genrentes desde el DV hacia los envios del MAV
	 * @param params
	 */
	void executeActualizarGerentesDirectorio(Map params);
	
}
