package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCOBRecuperacionCarteraFFVVService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:esanchez@sigcomt.com"> </a>
 */
public interface ProcesoCOBRecuperacionCarteraFFVVService extends Service{
	
	/**
	 * Este metodo ejecuta la Resecuenciacion de Pedidos
	 * @param params
	 */
	public void executeRecuperacionCarteraFFVV(Map params) throws Exception;
}