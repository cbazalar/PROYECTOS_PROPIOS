/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CronogramaCartera;


/**
 * <p>
 * <a href="MantenimientoCOBCronogramaCarteraDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz"> </a>
 */

public interface MantenimientoCOBCronogramaCarteraDAO extends DAO { 
	
	/**
	 * Obtiene una Lista el Cronograma de Cartera en base a los parametros recibidos en el map.
	 * @param criteria
	 * @return Lista con el Cronograma de Cartera 
	 */
	public List getCronogramaCarteraList (Map criteria);
	
	/**
	 * Obtiene un objeto CronogramaCartera especifico en base a los parametro rebicidos.
	 * @param cronogramaCartera
	 * @return
	 */
	public CronogramaCartera getCronogramaCarteraById(CronogramaCartera cronogramaCartera);

	/**
	 * Actualiza el cronograma de cartera
	 * @param criteria
	 */
	public void updateCronogramaCartera(Map criteria);
	
	/**
	 * Verifica si la Cartera ya está asignada
	 * @param criteria
	 * @return
	 */
	public int existeCarteraAsignada(Map criteria);
	
	/**
	 * Realiza el cierre de la Cartera en distintas tablas
	 * @param criteria
	 */
	public void cierreCartera(Map criteria);
	
}
