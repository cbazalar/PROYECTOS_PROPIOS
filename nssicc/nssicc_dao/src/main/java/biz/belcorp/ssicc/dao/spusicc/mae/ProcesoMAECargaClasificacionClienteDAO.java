package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAECargaClasificacionClienteDAO extends DAO {

	/**
	 * Inserta el codigo de cliente
	 * 
	 * @param params
	 */
	void insertCargaClasificacionCliente(Map params);

	/**
	 * Ejecuta el proceso de Validacion de los codigo de Clientes cargados
	 * 
	 * @param params
	 */
	void executeValidarCargaClasificacionClientes(Map params);

	/**
	 * Recupera la lista de Clientes que tiene alguna inconsistencia
	 * 
	 * @param params
	 * @return
	 */
	public List getCargaClasificacionClientesList(Map params);
	
	/**
	 * Ejecura el proceso de actualizacion de clasificaciones para los Clientes validos
	 * 
	 * @param params
	 */
	void executeActualizarCargaClasificacionClientes(Map params);

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

