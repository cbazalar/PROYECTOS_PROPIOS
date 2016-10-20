package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="#">Yahir Rivas L.</a>
 *
 */
public interface ProcesoMAEEliminarClasificacionClienteDAO extends DAO {

	/**
	 * Elimina la clasificacion del cliente
	 * 
	 * @param params
	 */
	void eliminarClasificacionCliente(Map params);

	
	/**
	 * Retorna numero de consultoras clasificacion
	 * @return
	 */
	public String getNroConsultorasClasificacion(Map params);
	
	/**
	 * Guarda en el historico las clasificaciones eliminadas
	 * @param params
	 */
	void insertClasificacionClienteHistorico(Map params);	

}

