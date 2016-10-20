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
public interface CobranzasDAO extends DAO{
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getEstatusCobranzaSeccion(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getEstatusCobranzaDeuda(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getDiasTranscurridosDesdeFacturacion(Map criteria);

}
