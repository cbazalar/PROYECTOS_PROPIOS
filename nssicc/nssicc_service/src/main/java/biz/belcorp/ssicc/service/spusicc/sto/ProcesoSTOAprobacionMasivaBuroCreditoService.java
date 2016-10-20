/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author sguerra
 *
 */
public interface ProcesoSTOAprobacionMasivaBuroCreditoService extends Service {

	/**
	 * Valida existencia del numero de documento 1: existe, 0: no existe
	 * @param value
	 * @return
	 */
	Integer getValidaNumeroDocumento(String value);

	/**
	 * Actualiza el Indicador de telefono, Indicador situacion credito e Indicador sin saldo en ambas marcas
	 * @param criteria
	 */
	public void updateNumeroDocumento(Map criteria);

}
