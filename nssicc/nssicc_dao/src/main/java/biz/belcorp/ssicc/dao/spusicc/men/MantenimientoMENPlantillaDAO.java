package biz.belcorp.ssicc.dao.spusicc.men;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoMENPlantillaDAO extends DAO{

	/**
	 * retorna los parametros de la plantilla
	 * @return
	 */
	List getParametrosPlantilla();

	/**
	 * retorna la lista que se mostrara en el control
	 * @param map
	 * @return
	 */
	List getSelectPlantilla(Map map);

	/**
	 * Inserta la cabecera de plantilla
	 * @param mapCabecera
	 */
	void insertPlantilla(Map mapCabecera);

	/**
	 * Inserta el detalle de la plantilla
	 * @param mapDetalle
	 */
	void insertPlantillaDetalle(Map mapDetalle);
	
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
	 * Devuelve los detalles de la plantilla
	 * @param map
	 * @return
	 */
	List getPlantillaDetalle(Map map);
	
	/**
	 * Actualiza plantilla 
	 * @param mapCabecera
	 */
	void updatePlantilla(Map map);

	/**
	 * Elimina los detalles de la plantilla de mensaje
	 * @param mapCabecera
	 */
	void deletePlantillaDetalle(Map mapCabecera);

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
	 * Inserta la configuracion proceso
	 * @param mapCabecera
	 */
	void insertProcesoPlantilla(Map mapCabecera);

	/**
	 * actualiza la configuracion proceso
	 * @param mapCabecera
	 */
	void updateProcesoPlantilla(Map mapCabecera);

	/**
	 * Realiza la actualizacion de la plantilla 
	 * @param mapDetalle
	 */
	void updatePlantillaDetalle(Map mapDetalle);

	/**
	 * @param mapDetalle
	 */
	void insertProcesoPlantillaDetalle(Map mapDetalle);

	/**
	 * @param mapCabecera
	 */
	void deleteProcesoPlantillaDetalle(Map mapCabecera);

	/**
	 * @param mapDetalle
	 */
	void updateProcesoPlantillaDetalle(Map mapDetalle);

	/**
	 * Actulaiza el proceos a disponible o no disponible
	 * @param mapCabecera
	 */
	void updateProceso(Map mapCabecera);

	/**
	 * Devuelve proceso habilitado
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
	 */
	void deleteProcesoHabilitado(Map map);

	/**
	 * @param listaProcesoSeleccionado
	 * @param login
	 */
	void insertProcesoHabilitado(Map map,List listaProcesoSeleccionado, String login);

	/**
	 * Retorna lista de procesos disponibles
	 * @param map
	 * @return
	 */
	List getProcesosDisponibles(Map map);

	/**
	 * Actualiza proceso habilitados
	 * @param map
	 */
	void updateProcesoHabilitado(Map map);

	/**Inserta los proceso habiitados en el historico
	 * @param map
	 */
	void insertProcesoHabilitadoHisto(Map map);

	/**
	 * Actualiza proceso habilitados en el historico
	 * @param map
	 */
	void updateProcesoHabilitadoHisto(Map map);

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
