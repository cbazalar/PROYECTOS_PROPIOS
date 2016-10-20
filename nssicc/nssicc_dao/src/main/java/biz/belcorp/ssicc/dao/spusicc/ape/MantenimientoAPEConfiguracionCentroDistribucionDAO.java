package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.CentroDistribucion;
/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPEConfiguracionCentroDistribucionDAO extends DAO{

	/**
	 * Retorna los Codigos de los Centros de Distribucion
	 * @return
	 */
	public List getCodigoCentroDistribucionList(Map criteria);

	/**
	 * Retorna las descripciones de los Centros de Distribucion
	 * @return
	 */
	public List getDescripcionCentroDistribucionList(Map criteria);

	/**
	 * Retorna los datos de los Centros de Distribucion
	 * @param criteria
	 * @return
	 */
	public List getCentroDistribucionList(Map criteria);
	
	/**
	 * Retorna la lista de Nivel de Outsourcing
	 * @param criteria
	 * @return
	 */
	public List getNivelOutsourcingList(Map criteria);
	
	/**
	 * Retorna la lista de Orden
	 * @param criteria
	 * @return
	 */
	public List getOrdenList(Map criteria);
	
	/**
	 * Retorna la lista de Orden Lista de Picado
	 * @param criteria
	 * @return
	 */
	public List getOrdenListaPicadoList(Map criteria);
	
	/**
	 * Retorna la lista de Aqgrupacion AFP y Olas
	 * @param criteria
	 * @return
	 */
	public List getAgrupacionList(Map criteria);
	
	/**
	 * Retorna la lista de Visualizacion Chequeo
	 * @param criteria
	 * @return
	 */
	public List getVisualizacionChequeoList(Map criteria);
	
	/**
	 * Retorna el objeto Centro de Distribucion
	 * @param map
	 * @return
	 */
	public CentroDistribucion getCentroDistribucionObject(Map map);
	
	/**
	 * Registra un Centro de Distribucion
	 * @param params
	 */
	public void insertCentroDistribucion(Map params);
	
	/**
	 * Actualiza los datos del Centro de Distribucion
	 * @param params
	 */
	public void updateCentroDistribucion(Map params);
	
	/**
	 * Obtiene el Oid del Nivel de Outsourcing
	 * @param criteria
	 * @return
	 */
	public String getOidNivelOutsourcing( Map criteria);
	
	/**
	 * Obtiene el oid del Orden de la Lista de Picado
	 * @param criteria
	 * @return
	 */
	public String getOidOrdenListaPicado( Map criteria);
	
	/**
	 * Obtiene el oid del Orden del Producto
	 * @param criteria
	 * @return
	 */
	public String getOidOrden( Map criteria);
	
	/**
	 * Obtiene el oid de la Agrupacion AFP
	 * @param criteria
	 * @return
	 */
	public String getOidAgrupacionAFP( Map criteria);
	
	/**
	 * Obtiene el oid del orden de Visualizacion
	 * @param criteria
	 * @return
	 */
	public String getOidOrdenVisualizacion( Map criteria);
	
	/**
	 * Obtiene el oid de la Agrupacion OLAS
	 * @param criteria
	 * @return
	 */
	public String getOidAgrupacionOLAS( Map criteria);

	/**
	 * Inserta en la tabla de idiomas
	 * @param criteria
	 */
	public void insertIdiomasAPE(Map criteria);
	
	/**
	 * Actualiza la tabla idiomas
	 * @param criteria
	 */
	public void updateIdiomasAPE(Map criteria);
	
	/**
	 * Obtiene el numero de atributo del idioma
	 * @param criteria
	 * @return
	 */
	public String getNumAtributoIdioma( Map criteria);
	
	/**
	 * Obtiene el maximo oid del Centro de Distribucion
	 * @param criteria
	 * @return
	 */
	public String getMaximoOidCentroDistribucion( Map criteria);
	
	/**
	 * Valida si existe un centro por default
	 * @param criteria
	 * @return
	 */
	public int getExisteCentroDefault(Map criteria);
	
	/**
	 * Obtiene el centro por default
	 * @param criteria
	 * @return
	 */
	public String getDescripcionCentroDefault( Map criteria);
	
	/**
	 * Elimina un Centro de Distribucion
	 * @param map
	 */
	public void deleteCentroDistribucion(Map criteria);
	
	/**
	 * Elimina el CD de la tabla de idiomas
	 * @param criteria
	 */
	public void deleteIdiomaAPE(Map criteria);
	
	/**
	 * Metodo que valida que no existan registros en las tablas hijas de la tabla APP_CONF_CENTR_DISTR
	 * @param criteria
	 */
	public void executeValidarRegistrosCentroDistribucion(Map criteria);
}
