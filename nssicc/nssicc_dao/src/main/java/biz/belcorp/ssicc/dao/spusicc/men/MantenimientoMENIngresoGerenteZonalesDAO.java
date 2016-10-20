package biz.belcorp.ssicc.dao.spusicc.men;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoMENIngresoGerenteZonalesDAO extends DAO{

	/**
	 * Devuelve la lista de mensaje para reuniones  de gerente zonales
	 * @param map
	 * @return
	 */
	List getMensajeGerenteZonales(Map map);

	/**
	 * Anula los mensajes selecionados
	 * @param listMensajeGerenteZonales
	 * @param seleccion
	 * @param usuario
	 */
	void updateAnulacionMensaje(Map map);

	/**
	 * Retorna el rengo de fechas dada la actividad como uno de los parametros
	 * @param map
	 * @return
	 */
	List getRangoFechaActividad(Map map);

	/**
	 * Inserta mensajes para reuniones de gerents zonales
	 * @param map
	 * @param campanhaActividad 
	 */
	void insertMensajeGerenteZonales(Map map, String campanhaActividad);
	
	/**
	 * Retorna la lista de zonas que seran ingresadas
	 * @param criteria
	 * @return
	 */
	List getZonasFromRegion(Map criteria);

	/**
	 * Actulaiza el mensaje gerente zonales
	 * @param map
	 */
	void updateMensajeGerenteZonales(Map map);
	
	/**
	 * elimina fisicamente los mensajes gz
	 * @param listMensajeGerenteZonales
	 * @param seleccion
	 * @param usuario
	 */
	void deleteMensajeGerenteZonales(Map map);

	/**
	 * Retorna la fecha de actividad de una zona en una campanha de actividad
	 * @param map
	 * @return
	 */
	String getFechaActividad(Map map);

	/**
	 * Retorna lista de Actividad
	 * @param map
	 * @return
	 */
	List getCodigosActividad(Map map);

	/**
	 * Retorna lista de codigo mensajes
	 * @param map
	 * @return
	 */
	List getCodigosMensaje(Map map);
	
	/**
	 * Retorna lista de parametros actividad
	 * @param map
	 * @return
	 */
	List getParametrosActividadMensaje(Map map);

	/**
	 * Inserta los parametros de actividad
	 * @param map
	 */
	void insertParametrosActividadMensaje(Map map);

	/**
	 * Actualiza Parametros Actividad
	 * @param map
	 */
	void updateParametrosActividadMensaje(Map map);
	
	/**
	 * Elimina el parametro de Actividad
	 * @param map
	 */
	void deleteParametrosActividadMensaje(Map map);

	/**
	 * Retorna el nombre de proceso
	 * @param codigoProceso
	 * @return
	 */
	String getNombreProgramaProceso(String codigoProceso);

	/**
	 * elimina escalera ganancua
	 * @param map
	 */
	void deleteEstadoGananciaMensaje(Map map);

	/**
	 * retorna lista de escalera de ganancia
	 * @param map
	 * @return
	 */
	List getEscaleraGanaciaMensaje(Map map);
	
	/**
	 * retorna true si hay traslapado de rangos
	 * @param map
	 * @return
	 */
	boolean getExisteRangoTraslapado(Map map);

	/**
	 * retorna true si existe codigo mensaje en rangos de escalon
	 * @param map
	 * @return
	 */
	boolean getExisteMensajeRangos(Map map);

	/**
	 * inserta escalon de ganancia
	 * @param map
	 */
	void insertEscaleraGananciaMensaje(Map map);

	/**
	 * actualiza escalon de ganancia
	 * @param map
	 */
	void updateEscaleraGananciaMensaje(Map map);

	/**
	 * retorna lista de mensajes por codigo venta
	 * @param map
	 * @return
	 */
	List getMensajeCodigoVenta(Map map);

	/**
	 * elimina los mensajes por codigo venta
	 * @param map
	 */
	void deleteMensajeCodigoVenta(Map map);
	
	/**
	 * Inserta el listado de codigo de ventas
	 * @param map
	 * @throws Exception
	 */
	void insertMensajeCodigoVentaMensaje(Map map) throws Exception;

	/**
	 * Actualiza el mensaje por codigo de venta
	 * @param map
	 * @throws Exception
	 */
	void updateMensajeCodigoVentaMensaje(Map map) throws Exception ;
	/**
	 * verifica Zonas Aprobadas No Procesadas
	 * @param map
	 * @throws Exception
	 */
	Integer getVerificaZonasAprobadasNoProcesadas(Map map) ;
}
