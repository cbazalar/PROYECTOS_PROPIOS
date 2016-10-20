package biz.belcorp.ssicc.dao.spusicc.men;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoMENGenerarMensajesDAO extends DAO{

	/**
	 * Devuleve las plantillas asociadas al proceso y tipo de proceso
	 * @param params
	 * @return
	 */
	List getPlantillaProceso(Map params);

	/**
	 * Ejecuta el proceso de mensajes
	 * @param params
	 */
	void executaProcesoMensajes(Map params);

	/**
	 * Obtine los correos de gz de unidad administrativa
	 * @param params
	 * @return
	 */
	List getCorreosGz(Map params);

	/**
	 * Proceso que realiza la eliminacin del buzon de mensajes
	 * @param params
	 */
	void executeProcesarEliminarBuzonMensaje(Map params);

	/** Actualiza los mensajes al cierre de campanha
	 * desde la entidad mensajes por campanha(ssicc) a la tabla de sicc mensajes
	 * @param params
	 */
	void executeProcesoActualizarMensajes(Map params);
	
	/**
	 * Obtiene datos del Producto en base al CUV y al codigo de Periodo
	 * @param criteria
	 * @return
	 */
	public Map getDatosProductoCUVPeriodo(Map criteria);
	
	/**
	 * Proceso que actualiza o inserta en la tabla de Faltante Anunciado
	 * @param params
	 */
	public void executeProcesoActualizarFaltanteAnunciado(Map params);
	
	/**
	 * Elimina informacion de la tabla de Mensaje de Faltante de Anunciado basado en el 
	 * periodo ingresado como parametro
	 * @param params
	 */
	public void deleteCargaMasivaFaltanteAnunciado(Map params);
	
	/**
	 * Devuelve listado de Faltante Anunciado basado en el codigo de periodo ingresado como
	 * parametro
	 * @param params
	 * @return
	 */
	public List getDevuelveFaltanteAnunciado(Map params);
	
	/**
	 * Elimina informacion de la tabla de Mensaje de Faltante de Anunciado basado en el 
	 * registro ingresado como parametro
	 * @param params
	 */
	public void deleteFaltanteAnunciado(Map params);
	
	/**
	 * Devuelve listado de Faltante Anunciado basado en el codigo de periodo 
	 * y codigo cuv ingresado como parametro
	 * @param params
	 * @return
	 */
	public Map getDevuelveFaltanteAnunciadoCodigoCUV(Map params);
	
	
	/**
	 * Devuelve Campaa en base al nro de campaa ingresado
	 * @param params
	 * @return
	 */
	public String getDevuelveCodigoCampanha(Map params);
	
	/**
	 * Obtiene datos de la Region
	 * @param criteria
	 * @return
	 */
	public Map getDatosRegion(Map criteria);
	
	/**
	 * Obtiene datos de la Zona por Region
	 * @param criteria
	 * @return
	 */
	public Map getDatosZonaByRegion(Map criteria);
	
	/**
	 * Elimina informacion de la tabla de Mensaje de Conferencias basado en el 
	 * periodo y region ingresados como parametros
	 * @param params
	 */
	public void deleteCargaMasivaConferencias(Map params);
	
	/**
	 * Proceso que actualiza o inserta en la tabla de Conferencias
	 * @param params
	 */
	public void executeProcesoActualizarConferencias(Map params);
	
	/**
	 * Devuelve listado de Conferencias basado en el codigo de region ingresado como
	 * parametro
	 * @param params
	 * @return
	 */
	public List getDevuelveConferencias(Map params);
	
	/**
	 * Elimina informacion de la tabla de Mensaje de Conferencias basado en el 
	 * registro ingresado como parametro
	 * @param params
	 */
	public void deleteConferencias(Map params);
	
	/**
	 * Devuelve codigo de Region de una Zona
	 * @param zona
	 * @return
	 */
	public String getDevuelveCodigoRegionZona(Map criteria);
}
