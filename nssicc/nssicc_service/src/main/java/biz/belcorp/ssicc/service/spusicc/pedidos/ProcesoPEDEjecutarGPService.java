package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Sergio Apaza
 */
public interface ProcesoPEDEjecutarGPService extends Service {
	
	/**
	 * Ejecuta Proceso GP2 OC 
	 */
	public void executeEjecutarGP2(Map params) throws Exception;

}
