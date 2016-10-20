package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAECargaMasivaDireccionDAO extends DAO {

	/**
	 * Inserta la Carga Masiva de Direcciones
	 * 
	 * @param params
	 */
	void insertCargaMasivaDireccion(Map params);

	/**
	 * Ejecuta el proceso de Validacion de Carga Masiva de Direcciones
	 * 
	 * @param params
	 */
	void executeValidarCargaMasivaDireccion(Map params);
	
	/**
	 * Recupera la lista de Clientes que estan cambiando de UA
	 * 
	 * @param params
	 * @return
	 */
	public List getCargaMasivaDireccionUAList(Map params);
	
	/**
	 * Actualiza Datos de UA a la tabla de Carga Masiva de Direcciones
	 * 
	 * @param params
	 */
	void updateCargaMasivaDireccionUA(Map params);

	/**
	 * Recupera la lista de Clientes que tiene alguna inconsistencia
	 * 
	 * @param params
	 * @return
	 */
	public List getCargaMasivaDireccionList(Map params);
	
	/**
	 * Recupera la lista de Clientes que tiene alguna observacion de UA
	 * 
	 * @param params
	 * @return
	 */
	public List getCargaMasivaDireccionObsList(Map params);
	
	/**
	 * Recupera la lista de Clientes que han pasado validacion OK y seran procesados
	 * 
	 * @param params
	 * @return
	 */
	public List getCargaMasivaDireccionOKList(Map params);
	
	/**
	 * Actualiza Datos a la tabla de Carga Masiva de Direcciones
	 * 
	 * @param params
	 */
	void updateCargaMasivaDireccionOK(Map params);

	/**
	 * Retorna numero carga
	 * @return
	 */
	String getNumeroCarga();
	
	/**
	 * Retorna numero lote
	 * @return
	 */
	String getNumeroLote();

}
