/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoMAEClasificacionClientesService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public interface ProcesoMAEClasificacionClientesService extends Service {

	/**
	 * Proceso que realiza la actualizacin de clasificacion de Clientes
	 * 
	 * @param criteria
	 */
	public void executeProcesarClasificacion(Map criteria);

	/**
	 * Proceso que realiza la actualizacin de clasificacion LOVE de Clientes
	 * 
	 * @param criteria
	 */
	public void executeProcesarClasificacionLove(Map criteria);

	/**
	 * Proceso que realiza la actualizacin de datos de Clientes
	 * 
	 * @param criteria
	 */
	public void executeActualizarDatosClientes(Map criteria);
	
}

