/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Sigcomt
 *
 */
public interface MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService extends Service{
	
	List getOfertaFactorRepeticionList(Map params);
	
	void insertOfertaFactorRepeticion(Map params);
	
	void insertRangoOfertaFactorRepeticion(Map rango);
	
	void removeRangoOfertaFactorRepeticion(String oidRango, String codigoUsuario);
	
	List getRangoOfertaFactorRepeticionList(Map params);
	
	List getRangoGratisOfertaFactorRepeticionList(Map params);
	
	void insertRangoGratisOfertaFactorRepeticion(Map regalo);
	
	void removeRangoGratisOfertaFactorRepeticion(String oidGratis, String codigoUsuario);
	
	void insertCriterioOfertaFactorRepeticion(Map criterio);
	
	void removeCriterioOfertaFactorRepeticion(String oidCriterio, String codigoUsuario);
	
	List executeCalcularProductos(Map params);
	
	List getCriterioOfertaFactorRepeticionList(String oidOferta);

	void removeOfertaFactorRepeticion(String oidOferta, String codigoUsuario);
	
	List getProductoOfertaFactorRepeticionList(Map params);
	
	List getProductoOfertaFactorRepeticionList1(Map params);
		
	Map getMoneda(Map params);

	List getCuvs(Map params);

	void updateOfertaFactorRepeticion(Map params);
	
	/**
	 * Actualiza Lista de Rangos
	 * @param rangoList
	 * @return
	 */
	public String updateRangoOfertaFactorRepeticion(List rangoList, String oidOferta, String codigoUsuario);

}
