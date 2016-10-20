/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Sigcomt
 *
 */
public interface MantenimientoPEDConfiguracionOfertasPorConcursosDAO extends DAO {

	List getOfertaConcursosList(Map params);

	void insertOfertaConcursos(Map params);

	void insertRangoOfertaConcursos(Map rango);

	void removeRangoOfertaConcursos(String oidRango, String codigoUsuario);

	List getRangoOfertaConcursosList(Map params);

	List getRangoGratisOfertaConcursosList(Map params);
	
	void insertRangoGratisOfertaConcursos(Map regalo);

	Map getMoneda(Map params);

	List getCuvs(Map params);

	void removeRangoGratisOfertaConcursos(String oidGratis, String codigoUsuario);  
	
	void insertCriterioOfertaConcursos(Map criterio);
	
	void removeCriterioOfertaConcursos(String oidCriterio, String codigoUsuario);
	
	List getProductoOfertaConcursosList(Map params);

	List getProductoOfertaConcursosList1(Map params);

	void insertProductoOfertaConcursos(Map producto);
	
	void deleteProductosOfertaConcursos(String oidOferta, String codigoUsuario);
	
	List getCriterioOfertaConcursosList(String oidOferta);
	
	void removeOfertaConcursos(String oidOferta, String codigoUsuario);

	void updateOfertaConcursos(Map params);
	
	/**
	 * Actualiza Lista de Rangos Configuración de Ofertas por Concurso
	 * @param params
	 */
	public void updateRangoOfertaConcursos(Map params);
	
}
