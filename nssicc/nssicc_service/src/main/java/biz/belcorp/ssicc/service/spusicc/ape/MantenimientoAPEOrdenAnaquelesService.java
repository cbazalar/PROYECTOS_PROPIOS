package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.ape.model.OrdenAnaquel;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEOrdenAnaquelesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface MantenimientoAPEOrdenAnaquelesService extends Service{

	/**
	 * Devuelve los datos de la cabecera del Orden de los Anaqueles de una Lnea
	 * @param criteria
	 * @return
	 */
	public List getMapaOrdenLineaList(Map criteria);
	
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
	 * Elimina orden de anaquel
	 * @param criteria
	 */
	public void deleteOrdenAnaquel(Map criteria, String[] items);
	
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
	 * Elimina los detalles del orden de anaqueles
	 * @param criteria
	 */
	public void deleteOrdenAnaquelDetalle(Map criteria);
	
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
	 * Obtiene el oid del Mapa Zona por el Oid del Mapa Centro y Cod de Mapa Zona 
	 * @param criteria
	 * @return
	 */
	public String getOidMapaZonaByMapaCentroZona(Map criteria);
	
	/**
	 * Valida si el archivo cumple con el formato
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public boolean validarArchivoExcel(Map criteria)throws Exception;
	
	/**
	 * Carga los registros del excel al detalle del bono
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public int executeCargaArchivoExcel(Map criteria)throws Exception;
}