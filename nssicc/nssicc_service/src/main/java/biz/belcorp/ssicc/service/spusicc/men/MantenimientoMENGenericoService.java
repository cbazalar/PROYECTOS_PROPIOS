package biz.belcorp.ssicc.service.spusicc.men;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoMENGenericoService  extends Service{

	/**
	 * Retorna los documentos
	 * @param hmap
	 * @return
	 */
	List getDocumentosMensaje(Map hmap);

	/**
	 * retorna patrones 
	 * @param map
	 * @return
	 */
	List getPatronMensaje(Map map);

	/**
	 * Elimina el patron
	 * @param map
	 */
	void deletePatronMensaje(Map map);

	/**
	 * Obtiene las secciones
	 * @param map
	 * @return
	 */
	List getSeccionPatron(Map map);

	/**
	 * Obtiene los modulos
	 * @param map
	 * @return
	 */
	List getModulos(Map map);

	/**
	 * Retonal las consideraciones
	 * @param hmap
	 * @return
	 */
	List getConsideracion(Map map);

	/**
	 * Retonal las restricciones
	 * @param hmap
	 * @return
	 */
	List getRestriccion(Map map);

	/**
	 * Retrona los mesnajes asociados al documento
	 * @param map
	 * @return
	 */
	List getMensajePatron(Map map);
	
	/**
	 * retorna el oid de periodo corporativo
	 * @param map
	 * @return
	 */
	String getOidPeriodoCorpo(Map map);

	/**
	 * Graba patron mensaje
	 * @param map
	 */
	void savePatronMensaje(Map map);

	/**
	 * Actualiza Patron mensaje
	 * @param map
	 */
	void updatePatronMensaje(Map map);

	/**
	 * Retorna Abreviatura de la seccion
	 * @param map
	 * @return
	 */
	String getAbrevSeccion(Map map);


	/**
	 * Retorna 0: si no existe el codigod e mensje
	 * 1:si existe 
	 * @param codigoMensaje
	 * @return
	 */
	int getExisteCodigoMensaje(String codigoMensaje,String campanhaProceso);

	/**
	 * Devuleve la lista de consideraciones o restricciones anhadidas
	 * @param map
	 * @return
	 */
	List getRestConsideracion(Map map);

	/**
	 * devulve los detalles de consideracion /restriccion
	 * @param criteria
	 * @return
	 */
	List getDetalleConsRest(Map criteria);

	/**
	 * devuleve subtipos clientes
	 * @param criteria
	 * @return
	 */
	List getSubTiposClientes(Map criteria);

	/**
	 * devulve tipo clasificaciones
	 * @param criteria
	 * @return
	 */
	List getTiposClasificaciones(Map criteria);

	/**
	 * devuelve clasificaciones
	 * @param criteria
	 * @return
	 */
	List getClasificaciones(Map criteria);

	/**
	 * devuleve territorio
	 * @param criteria
	 * @return
	 */
	List getTerritorio(Map criteria);

	/**devuleve seccion
	 * @param criteria
	 * @return
	 */
	List getSeccion(Map criteria);

	/**
	 * devuelve zonas
	 * @param criteria
	 * @return
	 */
	List getZona(Map criteria);

	/**
	 * Valida si el archivo cumple con el formato
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	boolean validarArchivoExcel(Map criteria)throws Exception;

	/**
	 * Carga los registros del excel 
	 * @param criteria
	 * @throws Exception
	 */
	void executeCargaArchivoExcel(Map criteria)throws Exception;

	/**
	 * retorna los documentos del periodo
	 * @param criteria
	 * @return
	 */
	List getDocumentosPatron(Map criteria);

	/**
	 * retorna las distintas secciones del documento
	 * @param criteria
	 * @return
	 */
	List getSeccionDocumentoPatron(Map criteria);

	/**
	 * retorna los mensajes de la seccion
	 * @param criteria
	 * @return
	 */
	List getMensajesSeccionDocumentoPatron(Map criteria);

	/**
	 * Replica el documento , seccion o secciones y mensajes seleccionados ala campanha destino
	 * @param map
	 */
	void replicarPatron(Map map);

	/**
	 * Orden el patron 
	 * @param map
	 */
	void ordenarPatron(Map map);

	/**
	 * retorna lista de mesnajes
	 * @param map
	 * @return
	 */
	List getMensajeByOid(Map map);

	/**
	 * devulve numero de registros del buzon
	 * @param map
	 * @return
	 */
	int getRegistrosBuzon(Map map);

 	/**
	 * Retorna la lista de lotes cargados 
	 * @param map
	 * @return
	 */
	List getLoteBuzon(Map map);

	/**
	 * Retorna la lista de mensajes disponibles
	 * aquellos que estn en la entidad Mensajes y no estn en la entidad Mensajes Patrn.
	 * @param criteria
	 * @return
	 */
	List getMensajesDisponibles(Map criteria);
	
	/**
	 * Lee las imagenes de los mensajes y deja los archivos en la Ruta Temporal Oracle
	 * @param criteria
	 */
	public void leerMensajeImagen(Map criteria);
	
	/**
	 * Devuelve lista de Bandeja de Flyers
	 * @param criteria
	 * @return
	 */
	public List getListaBandejaFlyers(Map criteria);
	
	/**
	 * Graba Flyers en la tabla de Mensajes
	 * @param criteria
	 */
	public void executaSavePatronFlyersMensaje(Map criteria);
	
	/**
	 * Devuelve lista de Flyers
	 * @param criteria
	 * @return
	 */
	public List getListaFlyers(Map criteria);
}
