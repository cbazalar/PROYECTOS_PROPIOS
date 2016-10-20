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
 * <a href="ProcesoCCCDepuracionSaldosMenoresAcreedoresService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
public interface ProcesoCCCDepuracionSaldosMenoresAcreedoresService extends Service {

	/**
	 * Ejecuta el proceso de Depuracion de Saldos Menores Acreedores en Cuenta Corriente
	 * @param criteria
	 */
	public void executeDepuracionSaldosMenoresAcreedores (Map criteria);
	
}
