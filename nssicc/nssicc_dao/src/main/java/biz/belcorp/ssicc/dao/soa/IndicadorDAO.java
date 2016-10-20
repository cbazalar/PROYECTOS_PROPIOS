/**
 * 
 */
package biz.belcorp.ssicc.dao.soa;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Danny Amaro
 *
 */
public interface IndicadorDAO extends DAO {
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getIndicadores(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getIndicadoresPEG(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getIndicadoresMetas(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getIndicadoresLetsCriticas(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getIndicadoresLetsProductivas(Map criteria);
	
	/**
	 * obtiene indicadores  de pedidos web facturados de comercial
	 * @param criteria
	 * @return
	 */
	public List getIndicadoresPedWebComercial(Map criteria);
	
	/**
	 * obtiene indicadores  de pedidos facturados de comercial
	 * @param criteria
	 * @return
	 */
	public List getIndicadoresPedComercial(Map criteria);
	
	/**
	 * obtiene indicadores  de Monto pedidos web facturados de comercial
	 * @param criteria
	 * @return
	 */
	public List getIndicadoresMontoPedWebComercial(Map criteria);

}
