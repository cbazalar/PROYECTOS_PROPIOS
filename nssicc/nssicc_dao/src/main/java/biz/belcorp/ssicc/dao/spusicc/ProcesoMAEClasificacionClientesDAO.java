/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoMAEClasificacionClientesDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public interface ProcesoMAEClasificacionClientesDAO extends DAO {

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

	/**Proceso q se encarga de ejecutar las validaciones de MAE
	 * @param criteria
	 */
	public void executeValidacionClientes(Map criteria);

	/**
	 * Proceso que realiza la Inactivación de conultoras registradas 
	 * despues de dos capañasde creadas en el maestro
	 * 
	 * @param criteria
	 */
	public void executeInactivarConsultora2campanas(Map criteria);
}
