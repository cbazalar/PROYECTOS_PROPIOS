package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoAPEImportarOrdenAnaquelesDAO extends DAO {

	/**
	 * Se recupera los mapas distribucion relacionados a un centro de Distribucin
	 * 
	 * @param criteria
	 * @return
	 */
	public List getMapaCentroDistribucion(Map criteria);

	/**
	 * Recupera las mapas de Zona relacionados a un mapa de centro de Distribucin
	 * 
	 * @param criteria
	 * @return
	 */
	public List getMapaZonaCentroDistribucion(Map criteria);

	/**
	 * Recupera los orden de anaqueles relacionados a un mapa de Zonas
	 * 
	 * @param criteria
	 * @return
	 */
	public List getOrdenAnaqueles(Map criteria);
	
	/**
	 * Recupera la informacion de un Orden de Anaquel
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getOrdenAnaquel(Map criteria);
	
	/**
	 * Recupera las subLineas de Armado relacionado a un Orden de Anaquel
	 * 
	 * @param criteria
	 * @return
	 */
	public List getSubLineaOrdenAnaquel(Map criteria);

	/**
	 * Recupera el detalle por Mapa de Centro de Distribucion y SubLinea Armado
	 * 
	 * @param criteria
	 * @return
	 */
	public List getDetalleMapaCDBySubLinea(Map criteria);

	/**
	 * Recupera el detalle por Orden Anaquel y SubLinea Armado
	 * 
	 * @param criteria
	 * @return
	 */
	public List getDetalleOrdenAnaquelBySubLinea(Map criteria);
	
	/**
	 * Inserta un detalle de Orden Anaquel
	 * 
	 * @param criteria
	 */
	public void insertDetalleOrdenAnaquel(Map criteria);
	
	/**
	 * Actualiza un detalle de Orden Anaquel
	 * 
	 * @param criteria
	 */
	public void updateDetalleOrdenAnaquel(Map criteria);
	
	/**
	 * Obtiene una lista de Tipo de solicitud, solo consolidados .
	 * 
	 * @param params
	 *            codigoPais,
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getTipoSolConsolidado(Map params);
	/**
	 * @param params
	 * Actualiza indicador
	 */
	public void actualizarIndicador(Map params);
}
