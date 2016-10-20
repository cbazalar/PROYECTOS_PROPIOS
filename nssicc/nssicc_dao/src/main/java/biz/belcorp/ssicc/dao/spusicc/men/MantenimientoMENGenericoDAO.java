package biz.belcorp.ssicc.dao.spusicc.men;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.LabelValue;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoMENGenericoDAO extends DAO{

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
	 * Retorna los mensajes asociados al documento
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
	 * retorna el codigo de mensaje
	 * @param map
	 * @return
	 */
	String getCodigoMensajeByModulo(Map map);

	/**
	 * Retorna correlativo por seccion y documento
	 * @param map
	 * @return
	 */
	String getCorrelativoDocumentoSecccion(Map map);
	
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
	 * guarda las consideraciones y/o restricciones
	 * @param bean
	 */
	void saveConRestCabecera(Map bean);

	/**
	 * guarda los detalles consideraciones y/o restricciones
	 * @param bean
	 */
	void saveConRestDetalle(Map bean);

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
	 * retorna la descripcion del premio si es que existe , caso contrario
	 * devulve null
	 * @param criteria
	 * @return
	 */
	LabelValue getCodigoPremio(Map criteria);
	
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
	 * Actualiza cabecera de consideraciones y restricciones
	 * @param bean
	 */
	void updateConRestCabecera(Map bean);

	/**
	 * Actualiza cabecera de consideraciones y restricciones
	 * @param bean
	 */
	void updateConRestDetalle(Map bean);

	/**
	 * ejecuta el proceso de eliminacion del mesaje seccion y patron segun corresponda
	 * @param beanPatron
	 */
	void deletePatronMensajeDetalle(Map beanPatron);

	/**
	 * Devuleve descripcion de co venta reemplazado si es que existe
	 * @param criteria
	 * @return
	 */
	LabelValue getCodigoVentaReemplazado(Map criteria);

	/**
	 * Devuleve descripcion de co venta reemplazo si es que existe
	 * @param criteria
	 * @return
	 */
	LabelValue getCodigoVentaReemplazo(Map criteria);

	/**
	 * inserta en el buzon de mensaje
	 * @param m
	 */
	void saveConRestBuzonDetalle(Map m);

	/**
	 * Actualiza en el buzon de mensaje
	 * @param bean
	 */
	void updateConRestBuzonDetalle(Map bean);

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
	 * Valida si existe patron 
	 * @param codigoPeriodo
	 * @param codigoDocumento
	 * @return
	 */
	Integer getValidaPatron(Map criteria);

	/**
	 * Actualiza el orden de impresion
	 * @param criteria
	 */
	void updateImpresionPatronMensaje(Map criteria);

	/**
	 * Ejecuta el proceso de replicar patron
	 */
	void executeReplicarPatron();

	/**
	 * retorna lista de mesnajes
	 * @param map
	 * @return
	 */
	List getMensajeByOid(Map map);

	/**
	 * Actualiza el orden de la seccion
	 * @param criteria
	 */
	void updateImpresionPatronSeccion(Map criteria);

	/**
	 * Actualiza la descripcion del patron
	 * @param beanPatron
	 */
	void updatePatron(Map beanPatron);
	
	/**
	 * devulve numero de registros del buzon
	 * @param map
	 * @return
	 */
	int getRegistrosBuzon(Map map);

	/**
	 * retorna el numero de lote de carga
	 * @return
	 */
	String getNumeroLote();

	/**
	 * Retorna lista de lotes cargado 
	 * @param map
	 * @return
	 */
	List getLoteBuzon(Map map);

	/**
	 * elimina fisicamente el detalle
	 * @param bean
	 */
	void deleteConRestDetalle(Map bean);

	/**
	 * elimina fisicamente la cabecera
	 * @param bean
	 */
	void deleteConRestCabecera(Map bean);

	/**
	 * Devuelve el num registros por lote
	 * @param bean
	 * @return
	 */
	int getNumRegLoteBuzon(Map bean);

	/**
	 * Devulve las variables validas para tipo de grupo 1 : fijos
	 * @param map
	 * @return
	 */
	List getVariables(Map map);

	/**
	 * actualiza la asignacion en sicc cuando mensaje es fijo
	 * @param map
	 */
	void updateAsignacion(Map map);

	/**
	 * Retorna la lista de mensajes disponibles
	 * aquellos que estn en la entidad Mensajes y no estn en la entidad Mensajes Patrn.
	 * @param criteria
	 * @return
	 */
	List getMensajesDisponibles(Map criteria);

	/**
	 * anhade el mensaje al patron desde ordenamiento         
	 * @param criteria
	 */
	void anahdeMensajePatronMensaje(Map criteria);

	/**elimina el mensaje al patron 
	 * @param criteria
	 */
	void deleteImpresionPatronMensaje(Map criteria);
	
	/**
	 * Recupera la descripcion de un mensaje
	 * 
	 * @param map
	 * @return
	 */
	String getDescripcionMensaje(Map criteria);
	
	/**
	 * Desactiva todas las imagenes existentes en el mensaje respectivo
	 * @param map
	 */
	public void updateAnulacionMensajeImagen(Map map);
	
	/**
	 * Actualiza imagenes existentes en el mensaje respectivo
	 * @param map
	 */
	public void insertaMensajeImagen(Map map);
	
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
