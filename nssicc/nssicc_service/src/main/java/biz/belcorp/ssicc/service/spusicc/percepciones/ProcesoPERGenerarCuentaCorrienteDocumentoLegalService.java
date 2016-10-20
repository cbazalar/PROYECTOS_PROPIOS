package biz.belcorp.ssicc.service.spusicc.percepciones;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author pejflorencio
 *
 */
public interface ProcesoPERGenerarCuentaCorrienteDocumentoLegalService extends Service {

		
	/**
	 * Metodo que procesa los pagos bancarios masivos
	 * @param datos 
	 */
	public void executeGenerarCuentaCorrienteDocumentoLegal(Map datos);

}
