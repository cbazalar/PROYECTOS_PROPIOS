package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jorge Florencio Arias
 *
 */
public interface ProcesoCCCGenerarInformacionDatamartService extends Service {

	/**
	 * Metodo que genera la informacion para Datamart
	 * @param criteria
	 */
	public void executeGenerarInformacionDatamart(Map criteria);
	
}
