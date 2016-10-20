package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.VersionProducto;
/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPEAsignarVersionesProductosDAO extends DAO{

	/**
	 * Retorna la lista de Versiones
	 * @return
	 */
	public List getVersionesList(Map criteria);
	
	/**
	 * Devuelve la lista de versiones asignados al mapa de CD
	 * @param criteria
	 * @return
	 */
	public List getVersionesAsignadosList(Map criteria);
	
	/**
	 * Devuelve el oid del periodo por Marca y Canal
	 * @param criteria
	 * @return
	 */
	public String getOidPeriodobyMarcaCanal(Map criteria);
	
	/**
	 * Obtiene la lista de versiones segun el periodo y el centro de distribucion
	 * @param criteria
	 * @return
	 */
	public List getVersionesbyPeriodoList(Map criteria);
	
	/**
	 * Obtiene la lista de Mapa Zonas en estado Activo
	 * @param criteria
	 * @return
	 */
	public List getMapaZonaVersionList(Map criteria);
	
	/**
	 * Obtiene el objeto Version Producto
	 * @param criteria
	 * @return
	 */
	public VersionProducto getVersionProductoObject(Map criteria);
	
	/**
	 * Obtiene la lista de Mapa Zonas del Mapa Centro seleccionado
	 * @param criteria
	 * @return
	 */
	public List getMapaZonaComboList(Map criteria);
	
	/**
	 * Obtiene el codigo del Mapa Zona
	 * @param criteria
	 * @return
	 */
	public String getCodigoMapaZona(Map criteria);
	
	/**
	 * Valida si existe el codigo de version para el mapa CD y el periodo seleccionado
	 * @param criteria
	 * @return
	 */
	public String getExisteVersionPeriodoMapaCD(Map criteria);
	
	/**
	 * Valida si existe una Version Activa para el mapa CD y el periodo seleccionado
	 * @param criteria
	 * @return
	 */
	public String getVerificaVersionActiva(Map criteria);
	
	/**
	 * Obtiene el Oid del Proceso de APE Estimado
	 * @param criteria
	 * @return
	 */
	public String getOidProcesoAPEEstimado(Map criteria);
	
	/**
	 * Devuelve el sigueinte oid de la Asignacion de Productos Cabecera
	 * @return
	 */
	public String getNextOidAsignacionProductoCab();
	
	/**
	 * Inserta la cabecera de las Versiones
	 * @param criteria
	 */
	public void insertVersionesCab(Map criteria);
	
	/**
	 * Inserta los detalles de la Version asignada
	 * @param criteria
	 */
	public void executeInsertVersionesDetalle(Map criteria);
	
	/**
	 * Desactiva las veriones actules para la linea y periodo
	 * @param criteria
	 */
	public void desactivarVersionesActuales(Map criteria);
	
	/**
	 * Actualiza el indicador de Activacion para la Facturacion
	 * @param criteria
	 */
	public void updateVersiones(Map criteria);
	
	/**
	 * Elimina las Versiones Cabecera
	 * @param criteria
	 */
	public void deleteVersionesCab (Map criteria);
	
	/**
	 * Elimina las Versiones Detalle
	 * @param criteria
	 */
	public void deleteVersionesDet (Map criteria);
	
	/**
	 * Valida si un numero de anaquel se encuentra en la lista de picado para el periodo 
	 * ingresado
	 * @param criteria
	 * @return
	 */
	public String validaVersionAnaquelListaPicado(Map criteria);
	
	/**
	 * Retorna el oid del Mapa CE por el Codigo de Mapa CD
	 * @param criteria
	 * @return
	 */
	public String getOidMapaCDbyCod(Map criteria);
	
	/**
	 * Retorna el oid de la Linea de Armado del Mapa CD dado
	 * @return
	 */
	public String getOidLineaVersion(Map criteria);
	
	/**
	 * Verifica si existe el oid de periodo para la marca y canal seleccionado
	 * @param criteria
	 * @return
	 */
	public int getExisteOidPeriodoCanalMarca(Map criteria);
	
	/**
	 * @param criteria
	 * @return Versin Activa
	 */
	public String getVersionActiva(Map criteria);
}
