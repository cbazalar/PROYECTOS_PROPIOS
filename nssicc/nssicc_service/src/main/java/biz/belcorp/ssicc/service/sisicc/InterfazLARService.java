/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc;


import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazLARService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
public interface InterfazLARService extends Service {

	/**
	 * Se realiza el proceso de generacion de pedidos a chequear
	 * @param params
	 */
	public void executeGenerarPedidosChequear(Map params);
	
	/**
	 * Se actualiza el indicador de envio a Yobel para los pedidos generados
	 * en el proceso de pedidos a chequear
	 * @param params
	 */
	public void executeActualizarPedidosChequear(Map params);
	
	/**
	 * Actualiza la informacion luego de generar las interfaces
	 * @param params
	 */
	public void updateLARDocumentosCabeceraComplemento(Map params);
	
	/**
	 * Almacena temporalmente los parametros para la generacion de LAR 8
	 * @param params
	 */
	void insertInterfazLAR8Parametros(Map params);
	
	/**
	 * Elimina los parametros para la generacion de LAR 8
	 */
	public void deleteInterfazLAR8Parametros();
	
}
