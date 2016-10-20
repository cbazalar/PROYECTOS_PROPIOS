package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Nicols Lpez
 *
 */
public interface MantenimientoAPEMapaZonaLineaArmadoDAO extends DAO {

	/**
	 * @param criteria
	 * @return Codigo Mapa Zona Cabecera
	 */
	public String getCodMapaZonaCabecera(Map criteria);
	
	/**
	 * @param criteria
	 * Registra el Mapa de Zona por Linea de Armado cabecera
	 */
	public void insertarMapaZonaCabecera(Map criteria);
	
	/**
	 * @param criteria
	 * @return El Oid Mapa de Zona de Cabecera
	 */
	public String getOidMapaZona(Map criteria);
	
	/**
	 * @param criteria
	 * Registra el detalle de Mapa de Zonas por Linea de Armado
	 */
	public void insertarMapaZonaDetalle(Map criteria);

	/**
	 * @param criteria
	 * @return Lista de Mapa de Zona por Oid Mapa CD
	 */
	public List getMapaZonaxOidMapaCD(Map criteria);
	
	/**
	 * @param criteria
	 * @return Lista de Mapa de Zonas Cabecera como resultado de Bsqueda
	 */
	public List getListaMapaZonaLineaArmado(Map criteria);
	
	
	/**
	 * @param criteria
	 * @return Obtener la lista de Detalle de Mapa de Zona
	 */
	public List getObtenerDetMapaZonaLineaArmado(Map criteria);
	
	
	/**
	 * @param criteria
	 * @return Las Sublineas para llenado de Combo box
	 */
	public List getSubLineaComboList(Map criteria);
	
	/**
	 * @param criteria
	 * @return Codigo CD por Oid Mapa CD
	 */
	public String getObtenerCodigoCDxOidMapaCD(Map criteria);
	
	/**
	 * @param criteria
	 * @return Codigo Linea Armado por Oid Sub Linea
	 */
	public String getObtenerCodLineaArmadoxOidSubLinea(Map criteria);
	
	/**
	 * @param criteria
	 * @return Cdigo Mapa CD
	 */
	public String getObtenerCodigoMapaCD(Map criteria);
	
	/**
	 * @param criteria
	 * @return Lista de Sub Lineas por Linea de Armado
	 */
	public List getSubLineaporLineaList(Map criteria);
	
	/**
	 * Actualiza el dato de Mapa Zona Default
	 * @param criteria
	 */
	public void updateMapaZonaCabecera(Map criteria);
	
	/**
	 * Elimina registro de Mapa de Zona
	 * @param criteria
	 */
	public void deleteLineaMapaZona(Map criteria);
	
	/**
	 * Elimina el detalle de Mapa de Zona por Linea de Armado
	 * @param criteria
	 */
	public void deleteMapaZonaxLineaArmado(Map criteria);
	
	/**
	 * @param criteria
	 * @return Cantidad si existe Mapa Zona por Defecto
	 */
	public String getValidaExisteMapaZonaDefecto(Map criteria);
	
	/**
	 * @param criteria
	 * @return el maximo valor de Bahia por SubLnea
	 */
	public String getObtenerBahiaMayorxSubLinea(Map criteria);
	
	/**
	 * @param criteria
	 * @return La descripcin de SubLinea
	 */
	public String getObtenerDescripcionSubLinea(Map criteria);

	/**
	 * @param criteria
	 * @return Oid Mapa CD x OidCentro
	 */
	public String getOidMapaCDxOidCentro(Map criteria);
	
	/**
	 * @param criteria
	 * @return La lista de Bahias por SubLinea
	 */
	public List getObtenerBahiasxSubLineaList(Map criteria);

	/**
	 * @param criteria
	 * @return El total de Bahias por SubLinea
	 */
	public String getObtenerCantidadBahiasxSubLinea(Map criteria);
}
