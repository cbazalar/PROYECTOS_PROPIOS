/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="InterfazPREDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
public interface InterfazPREDAO extends DAO {

	/**
	 * Interfaz que genera el archivo PRE_OFERT
	 * @param map
	 */
	public void executeInterfazPREEnviarOfertaCabecera(Map map);
	
	/**
	 * Interfaz que genera el archivo PRE_OFERT_DETAL
	 * @param map
	 */
	public void executeInterfazPREEnviarOfertaDetalle(Map map);

	/**
	 * Interfaz que genera el archivo PRE_GRUPO_OFERT
	 * @param map
	 */
	public void executeInterfazPREEnviarGrupoOferta(Map map);
	
	/**
	 * Interfaz que genera el archivo PRE_PROMO
	 * @param map
	 */
	public void executeInterfazPREEnviarPromocion(Map map);
	
	/**
	 * Interfaz que genera el archivo PRE_RANGO_PROMO
	 * @param map
	 */
	public void executeInterfazPREEnviarRangoPromocion(Map map);
	
	/**
	 * Interfaz que genera el archivo PRE_MATRI_FACTU
	 * @param map
	 */
	public void executeInterfazPREEnviarMatrizFacturacion(Map map);
	
	/**
	 * Interfaz que recibe el archivo PRE_OFERT
	 * @param map
	 */
	public void executeInterfazPRERecepcionarOfertaCabecera(Map map);
	
	/**
	 * Interfaz que recibe el archivo PRE_OFERT_DETAL
	 * @param map
	 */
	public void executeInterfazPRERecepcionarOfertaDetalle(Map map);
	
	/**
	 * Interfaz que recibe el archivo PRE_GRUPO_OFERT
	 * @param map
	 */
	public void executeInterfazPRERecepcionarGrupoOferta(Map map);
	
	/**
	 * Interfaz que recibe el archivo PRE_PROMO
	 * @param map
	 */
	public void executeInterfazPRERecepcionarPromocion(Map map);
	
	/**
	 * Interfaz que recibe el archivo PRE_RANGO_PROMO
	 * @param map
	 */
	public void executeInterfazPRERecepcionarRangoPromocion(Map map);
	
	/**
	 * Interfaz que inserta los archivos exportados
	 * @param map
	 */
	public void executeInterfazPREInsertarOfertasExportadas(Map map);
	
	/**
	 * Devuelve la ruta donde se exportara el archivo
	 * @param params
	 * @return
	 */
	public String getDirectorioCUV(Map params);
	
	/**
	 * Inserta una nueva Oferta
	 * @param params
	 */
	public void insertOfertaGenerada(Map params);
	
	/**
	 * @param params
	 */
	public void insertCondicionesOferta(Map params);
	
	/**
	 * @param criteria
	 */
	public void insertOfertaNiveles(Map params);
	
	/**
	 * @param params
	 */
	public void insertRangoOfertaNiveles(Map params);

	/**
	 * @param criteria
	 */
	public void insertProductoOfertaNiveles(Map params);
	/**
	 * @param criteria
	 */
	public void insertGratisOfertaNiveles(Map params);
	
	/**
	 * @param params
	 */
	public void executeValidacionMatrizPlanit(Map params);
	
	/**
	 * @param params
	 */
	public void envioCorreo(Map params);
	
	/**
	 * @return
	 */
	public Integer verificarErrorMatrizPlanit(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazPrCargaMatrizPlan(Map params);
}
