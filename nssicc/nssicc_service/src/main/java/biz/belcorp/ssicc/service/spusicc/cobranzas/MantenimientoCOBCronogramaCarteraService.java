/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CronogramaCartera;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCOBCronogramaCarteraService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz"> </a>
 */
public interface MantenimientoCOBCronogramaCarteraService extends Service {

		
	/**
	 * Obtiene en una lista el Cronograma de Cartera
	 * @param criteria
	 * @return
	 */
	public List getCronogramaCarteraList (Map criteria);
			
	/**
	 * Obtiene en un objeto Cronograma de Cartera
	 * @param cronogramaCartera
	 * @return
	 */
	CronogramaCartera getCronogramaCarteraById(CronogramaCartera cronogramaCartera);	
	
	/**
	 * Actualiza el Cronograma de Cartera
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
