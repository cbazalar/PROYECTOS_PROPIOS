/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Sigcomt
 *
 */
public interface MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO extends DAO {

	List getOfertaFactorRepeticionList(Map params);
	
	void insertOfertaFactorRepeticion(Map params);
	
	void insertRangoOfertaFactorRepeticion(Map rango);
	
	void removeRangoOfertaFactorRepeticion(String oidRango, String codigoUsuario);
	
	List getRangoOfertaFactorRepeticionList(Map params);
	
	List getRangoGratisOfertaFactorRepeticionList(Map params);
	
	void insertRangoGratisOfertaFactorRepeticion(Map regalo);
	
	Map getMoneda(Map params);
	
	List getCuvs(Map params);
	
	void removeRangoGratisOfertaFactorRepeticion(String oidGratis, String codigoUsuario);
	
	void insertCriterioOfertaFactorRepeticion(Map criterio);
	
	void removeCriterioOfertaFactorRepeticion(String oidCriterio, String codigoUsuario);
	
	List getProductoOfertaFactorRepeticionList(Map params);
	
	List getProductoOfertaFactorRepeticionList1(Map params);
		
	void insertProductoOfertaFactorRepeticion(Map producto);
	
	void deleteProductosOfertaFactorRepeticion(String oidOferta, String codigoUsuario);
	
	List getCriterioOfertaFactorRepeticionList(String oidOferta);
	
	void removeOfertaFactorRepeticion(String oidOferta, String codigoUsuario);

	void updateOfertaFactorRepeticion(Map params);
	
	/**
	 * Actualiza Lista de Rangos Configuración de Ofertas N por
	 * @param params
	 */
	public void updateRangoOfertaFactorRepeticion(Map params);
	
	
	
	
	
}
