package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextdoliva
 *
 */
public interface ProcesoAPESecuenciaClientesDAO extends DAO {

	/**
	 * Inserta la secuencia de clientes
	 * @param criteria
	 */
	public void insertSecuenciaClientes(Map criteria);
	
	/**
	 * Borra todos los registros de la tabla APP_RUTAS_CLIEN
	 * @param criteria
	 */
	public void deleteSecuenciaClientes(Map criteria);
	
	/**
	 * Carga la secuencia de clientes
	 * @param queryParams
	 */
	public void executeProcesoAPECargaRutasCliente(Map queryParams);
}
