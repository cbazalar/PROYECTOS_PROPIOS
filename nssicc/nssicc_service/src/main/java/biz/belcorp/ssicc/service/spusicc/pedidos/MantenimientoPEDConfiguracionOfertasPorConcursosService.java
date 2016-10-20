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
public interface MantenimientoPEDConfiguracionOfertasPorConcursosService extends Service{

	List getOfertaConcursosList(Map params);
	
	void insertOfertaConcursos(Map params);
	
	void insertRangoOfertaConcursos(Map rango);
	
	void removeRangoOfertaConcursos(String oidRango, String codigoUsuario);
	
	List getRangoOfertaConcursosList(Map params);
	
	List getRangoGratisOfertaConcursosList(Map params);
	
	void insertRangoGratisOfertaConcursos(Map regalo);
	
	void removeRangoGratisOfertaConcursos(String oidGratis, String codigoUsuario);
	
	void insertCriterioOfertaConcursos(Map criterio);
	
	void removeCriterioOfertaConcursos(String oidCriterio, String codigoUsuario);
	
	List executeCalcularProductos(Map params);
	
	List getCriterioOfertaConcursosList(String oidOferta);

	void removeOfertaConcursos(String oidOferta, String codigoUsuario);
	
	List getProductoOfertaConcursosList(Map params);
	
	List getProductoOfertaConcursosList1(Map params);
		
	Map getMoneda(Map params);

	List getCuvs(Map params);

	void updateOfertaConcursos(Map params);
	
	/**
	 * Actualiza Lista de Rangos
	 * @param rangoList
	 * @return
	 */
	public String updateRangoOfertaConcursos(List rangoList, String codigoUsuario);

}
