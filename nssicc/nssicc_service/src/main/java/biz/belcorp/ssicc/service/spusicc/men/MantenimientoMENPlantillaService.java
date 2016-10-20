package biz.belcorp.ssicc.service.spusicc.men;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoMENPlantillaService  extends Service{

	/**
	 * retorna la lista con los parametros de la plantilla
	 * @returnInte
	 */
	List getParametrosPlantilla();

	/**
	 * retorna la lista que se mostrara en el control
	 * @param map
	 * @return
	 */
	List getSelectPlantilla(Map map);

	/**
	 * @param listPlantilla
	 * @param usuario
	 */
	void insertPlantilla(List listPlantilla, Usuario usuario);

	/**
	 * Retorna los datos de la cabecera d ela plantilla
	 * @param map
	 * @return
	 */
	List getPlantillaCabecera(Map map);

	/**
	 * Elimina tanto los detalles como la cebecera
	 * @param map
	 */
	void deletePlantilla(Map map);

	/**
	 * Devuelve lista de detalles de la plantilla
	 * @param map
	 * @return
	 */
	List getPlantillaDetalle(Map map);

	/**
	 * Actualiza las plantilla de mensajes
	 * @param listPlantilla
	 * @param usuario
	 */
	void updatePlantilla(List listPlantilla, Usuario usuario);

	/**
	 * Retorna lista de procesos activos
	 * @param map
	 * @return
	 */
	List getProcesos(Map map);

	/**
	 * Retorna la lista de procesos habilitados
	 * @param map
	 * @return
	 */
	List getProcesosConfigurados(Map map);

	/**
	 * Retorna lista de parametors ordenados por el numero de orden de los detalles de 
	 * la plantilla
	 * @param map
	 * @return
	 */
	List getParametrosPlantillaDetalleVisibles(Map map);

	/**
	 * Retorna la vista con el indicador de plantilla seleccionado
	 * @param map
	 * @return
	 */
	List getSelectPlantillaSeleccionado(Map map);

	/**
	 * Actulaiza la configuracion de proceso en base de una plantilla
	 * @param listProcesoCabec
	 * @param usuario
	 */
	void updateProcesoPlantilla(List listProcesoCabec, Usuario usuario);

	/**
	 * Inserta la configracion de un proceso en base a una plantilla
	 * @param listProcesoCabec
	 * @param usuario
	 */
	void insertProcesoPlantilla(List listProcesoCabec, Usuario usuario);

	/**
	 * Devuelve la lista de proceso Habilitados
	 * @return
	 */
	List getListProcesoHabilitado();

	/**
	 * Registra proceso Habilitado
	 * @param criteria
	 */
	void insertProcesoHabilitado(Map criteria);

	/**
	 * Elimina los proces habilitados
	 * @param criteria
	 */
	void deleteProcesoHabilitado(Map criteria);

	/**
	 * retorna lista de procesos disponibles
	 * @param map
	 * @return
	 */
	List getProcesosDisponibles(Map map);

	/**
	 * Retorna 1 si el indicador de procesos se encuentra activo
	 * @return
	 */
	String getIndicadorProcesoActivo();
	
	/**
	 * retorna lista de procesos habilitados
	 * @param map
	 * @return
	 */
	List getProcesosHabilitados(Map map);

	/**
	 * Se inserta los proceos habilitados en el paquete de interfaz, para su ejecucion
	 * @param criteria
	 */
	void setProcesoPaquete(Map criteria);

	/**
	 * Elimina los procesos asociado a la plantilla 
	 * @param map
	 */
	void deleteProcesoPlantilla(Map map);

	/**
	 * ejecuta el proceso de carga de pedidos a un temporal
	 * @param map
	 */
	void executePedidoCliente(Map map);
	/**
	 * verifica si la campaa esta en periodo de cruce
	 * @param map
	 */
	public Integer getDevuelveCampanaIndicadorCruce(Map map);		
	/**
	 * Devuelve la campaan anterior
	 * @param map
	 */
	public String getDevuelveCampanaAnterior(Map map);		
	/**
	 * ejecuta el proceso de actualizacion de mensajes de la campaa
	 * @param map
	 */
	public void executeProcesoActualizarMensajes(Map map) ;	
	
	/**
	 * verifica que no existan zonas, ni regiones programadas para cierre y con estado distinto a “PROCESADO” 
	 * @param map
	 */
	public Integer getVerRegionesZonasAprobadasNoProc(Map map);	
}
