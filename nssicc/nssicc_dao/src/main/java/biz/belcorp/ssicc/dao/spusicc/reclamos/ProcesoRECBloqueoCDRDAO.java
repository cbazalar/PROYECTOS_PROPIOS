/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author  Dennys Oliva Iriarte 
 * 
 */
public interface ProcesoRECBloqueoCDRDAO extends DAO {

	/**
	 * Metodo que devuelve la fecha inicio y fin en base al periodo
	 * @param map
	 * @return
	 */
	public List getPeriodoInicialFinal(Map map);

	/**
	 * Metodo que devuelve la lista de Bloqueos de CDRs
	 * @param map
	 * @return
	 */
	public List getBloqueosCDRByCriteria(Map map);
	
	/**
	 * Metodo que actuliza la condicion de desbloqueo de CDRs
	 * @param map
	 */
	public void updateDesbloqueoCDR(Map map);
	
	/**
	 * Metodo que realiza el bloqueo de la consultora
	 * @param params
	 */
	public void executeProcesoRECBloqueoCDR(Map params);
	
	/**
	 * Realiza el proceso de Update de Enviar CDRs Recepcionados por cada registro
	 * @param params
	 */
	public void executeProcesoRECEnviarCDRRecepcionados(Map params);
}
