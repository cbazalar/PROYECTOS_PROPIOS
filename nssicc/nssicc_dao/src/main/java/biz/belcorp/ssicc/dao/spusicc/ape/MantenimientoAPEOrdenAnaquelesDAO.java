package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.OrdenAnaquel;
/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPEOrdenAnaquelesDAO extends DAO{
	
	/**
	 * Devuelve los datos de la cabecera del Orden de los Anaqueles de una Lnea
	 * @param criteria
	 * @return
	 */
	public List getMapaOrdenLineaList(Map criteria);
	
	/**
	 * Devuelve la lista de Mapa de Centro de Distribucion 
	 * @param criteria
	 * @return
	 */
	public List getMapaCentroDistribucionComboList(Map criteria);
	
	/**
	 * Devuelve la lista de Mapa Zonas de un Mapa de Centro de Distribucion
	 * @param criteria
	 * @return
	 */
	public List getMapaZonaComboList(Map criteria);
	
	/**
	 * Devuelve la lista de Orden de Anaqueles
	 * @param criteria
	 * @return
	 */
	public List getMapaOrdenComboList(Map criteria);
	
	/**
	 * Obtiene el oid del orden de anaquel cabecera
	 * @return
	 */
	public String getOidOrdenAnaquel(Map criteria);
	
	/**
	 * Obtiene el oid del Mapa Zona 
	 * @param criteria
	 * @return
	 */
	public String getOidMapaZonaByCodMapaZona(Map criteria);
	
	/**
	 * Obtiene la lista de Linea de Armado por el mapa de 
	 * centro de distribucin
	 * @param criteria
	 * @return
	 */
	public List getLineaMapaOrdenComboList(Map criteria);
	
	/**
	 * Obtiene las sublineas de la linea de armado 
	 * @param criteria
	 * @return
	 */
	public List getSubLineasNuevoOrdenAnaquel(Map criteria);
	
	/**
	 * Validad si existe un orden de anaquel por defecto para el mapa de CD
	 * @param criteria
	 * @return
	 */
	public int validaExisteOrdeAnaquelDefault(Map criteria);
	
	/**
	 * Devuelve la descripcion del Centro de Distribucion
	 * @param criteria
	 * @return
	 */
	public String getDescripcionCentroDistribucion(Map criteria);
	
	/**
	 * Devuelve la descripcion del Mapa CD
	 * @param criteria
	 * @return
	 */
	public String getDescripcionMapaCentroDistribucion(Map criteria);
	
	/**
	 * Devuelve la descripcion del Mapa Zona
	 * @param criteria
	 * @return
	 */
	public String getDescripcionMapaZona(Map criteria);
	
	/**
	 * Devuelve la descripcion del Mapa Anaquel por defecto del Mapa CD
	 * @param criteria
	 * @return
	 */
	public String getDescripcionMapaAnaquelDefault(Map criteria);
	
	/**
	 * Obtiene el objeto Orden Anaquel Cabecera
	 * @param criteria
	 * @return
	 */
	public OrdenAnaquel getOrdenAnaquelObject(Map criteria);
	
	/**
	 * Obtiene las sublineas de la linea de armado 
	 * @param criteria
	 * @return
	 */
	public List getSubLineasModificarOrdenAnaquel(Map criteria);
	
	/**
	 * Obtiene los detalles del Orden de Anaquel
	 * @param criteria
	 * @return
	 */
	public List getDetalleOrdenAnaquelList(Map criteria);
	
	/**
	 * Obtiene los detalles del Orden de Anaquel para Consulta
	 * @param criteria
	 * @return
	 */
	public List getDetalleOrdenAnaquelConsultaList(Map criteria);
	
	/**
	 * Registra la cabecera del Orden de los anaqueles
	 * @param criteria
	 */
	public void insertOrdenAnaquelCabecera(Map criteria);
	
	/**
	 * Registra los detalles del Orden de los anaqueles
	 * @param criteria
	 */
	public void insertOrdenAnaquelDetalle(Map criteria);
	
	/**
	 * Obtiene el siguiente oid de Orden de Anaquel Cabecera
	 * @param criteria
	 * @return
	 */
	public String getNextOidOrdenAnaquelCab(Map criteria);
	
	/**
	 * Obtiene el siguiente oid de Orden de Anaquel Detalle
	 * @param criteria
	 * @return
	 */
	public String getNextOidOrdenAnaquelDet(Map criteria);
	
	/**
	 * Obtiene el siguiente codigo de Orden de Anaquel Cabecera
	 * @param criteria
	 * @return
	 */
	public int getMaxCodOrdenAnaquel(Map criteria);
	
	/**
	 * Actualiza los detalles de Orden de Anaquel
	 * @param criteria
	 */
	public void updateOrdenAnaquelDetalle(Map criteria);
	
	/**
	 * Elimina los detalles de orden de anaquel
	 * @param criteria
	 */
	public void deleteOrdenAnaquelDetalle(Map criteria);
	
	/**
	 * Elimina la cabecera de orden de anaquel
	 * @param criteria
	 */
	public void deleteOrdenAnaquelCabecera(Map criteria);
	
	/**
	 * Elimina de Idiomas la tabla de Orden de Anaquel
	 * @param criteria
	 */
	public void deleteIdiomaAnaquel(Map criteria);
	
	/**
	 * Valida si existe el detalle a insertar
	 * @param criteria
	 * @return
	 */
	public int validaExisteAnaquelDetalle(Map criteria);
	
	/**
	 * Actualiza a Cero el numero de orden
	 * @param criteria
	 */
	public void updateNumeroOrdenAnaquelDetalle(Map criteria);
	
	/**
	 * Obtiene el oid del Mapa Zona por el Oid del Mapa Centro y Cod de Mapa Zona 
	 * @param criteria
	 * @return
	 */
	public String getOidMapaZonaByMapaCentroZona(Map criteria);
	
	/**
	 * Obtiene la lista de Linea del Mapa Zona
	 * @param criteria
	 * @return
	 */
	public List getLineaMapaOrdenZonaList(Map criteria);
	
	/**
	 * Elimina los detalle del Orden de Anaquel del Mapa Centro de Distribucion Detalle
	 * @param criteria
	 */
	public void deleteOrdenAnaquelDetbyMapaCent(Map criteria);
	
	/**
	 * Valida si existe un Mapa de Anaquel por Defecto
	 * @param criteria
	 * @return
	 */
	public String validaExisteMapaAnaquelDefault(Map criteria);
	
	/**
	 * Actualiza el campo por defecto del Mapa Orden Cabecera
	 * @param criteria
	 */
	public void updateMapaOrdenAnaquelDefault(Map criteria);
	
	/**
	 * Devuelve el oid por Defecto del Mapa Orden de Anaquel
	 * @param criteria
	 * @return
	 */
	public String validaExisteOrdenAnaquelDefault(Map criteria);
	
	/**
	 * Obtiene el Mapa Centro Detalle y la SubLinea
	 * @param criteria
	 * @return
	 */
	public String getMapaCentroSubLineaList(Map criteria);
	
	/**
	 * Actualiza el Numero de Orden del Anaquel
	 * @param criteria
	 */
	public void actualizaNumeroOrden(Map criteria);
}
