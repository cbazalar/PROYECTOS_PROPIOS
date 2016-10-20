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
 * <a href="ProcesoCCCDepuracionPagosPorRegularizarService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
public interface ProcesoCCCDepuracionPagosPorRegularizarService extends Service {

	/**
	 * Ejecuta el proceso de Depuracion de Saldos Menores Deudores en Cuenta Corriente
	 * @param criteria
	 */
	public void executeDepuracionPagosPorRegularizar (Map criteria);
	
	/**
	 * Proceso que inserta Pago para una consultora
	 * @param criteria
	 */
	public void executeInsertarPago(Map criteria);
	
	/**
	 * Proceso de Reversion de Pago para una consultora
	 * @param criteria
	 */
	public void executeRevertirPago(Map criteria);
	
	/**
	 * Consultar Saldo de la Consultora
	 * @param criteria
	 */
	public void executeConsultarPago(Map criteria);
	
	
	
}
