package biz.belcorp.ssicc.service.spusicc.mae;

import java.util.Map;

/**
 * @author <a href="#">Yahir Rivas L.</a>
 *
 */
public interface ProcesoMAEEliminarClasificacionClienteService {
	
	/**
	 * Elimina la clasificacion del cliente
	 * 
	 * @param params
	 */
	public void eliminarClasificacionCliente(Map params);

	
	/**
	 * Retorna numero de consultoras clasificacion
	 * @return
	 */
	public String getNroConsultorasClasificacion(Map params);

}
