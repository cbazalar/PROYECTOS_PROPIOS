package biz.belcorp.ssicc.service.spusicc.cobranzas;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCOBRecuperacionCobranzaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz"> </a>
 */
public interface ProcesoCOBRecuperacionCobranzaService extends Service {

	/**
	 * Ejecuta la Interfaz Reporte Recuperacin de Cobranza por Cobrador
	 * @param criteria
	 */
	public void executeEnvioRecuperacionCobranzaPorCobrador() throws Exception;

}

