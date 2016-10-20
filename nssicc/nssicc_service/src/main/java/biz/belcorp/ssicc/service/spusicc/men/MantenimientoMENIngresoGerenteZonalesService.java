package biz.belcorp.ssicc.service.spusicc.men;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoMENIngresoGerenteZonalesService  extends Service{

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
	void updateAnulacionMensaje(List listMensajeGerenteZonales,
			String[] seleccion, Usuario usuario);

	/**
	 * Inserta mensajes para reuniones de gerents zonales
	 * @param map
	 */
	void insertMensajeGerenteZonales(Map map);

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
	void deleteMensajeGerenteZonales(List listMensajeGerenteZonales,
			String[] seleccion, Usuario usuario);

	/**
	 * Retorla lista de Menjsaes
	 * @param map
	 * @return
	 */
	List getCodigosMensaje(Map map);

	/**
	 * Retorna la lista de codigos de actividad
	 * @param map
	 * @return
	 */
	List getCodigosActividad(Map map);

	
	/**
	 * Retorna lista Parametros mensajes
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
	 * retorna la lista de escalera de ganancia
	 * @param map
	 * @return
	 */
	List getEscaleraGanaciaMensaje(Map map);

	/**
	 * elimina escalarera de ganancia
	 * @param map
	 */
	void deleteEstadoGananciaMensaje(Map map);

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
}
