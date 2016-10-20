/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoService extends Service {

	/**
	 * Ejecuta el proceso de Bloqueo Financiero Por Dias De Atraso
	 * @param criteria
	 */
	public void executeBloqueoFinancieroPorDiasDeAtraso (Map criteria);
	
}
