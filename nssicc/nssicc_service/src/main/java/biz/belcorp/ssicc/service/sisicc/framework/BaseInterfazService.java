package biz.belcorp.ssicc.service.sisicc.framework;

import biz.belcorp.ssicc.service.framework.Service;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;

/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Interface que deben implementar las Interfaces Unitarias SiCC.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public interface BaseInterfazService extends Service {

	/**
	 * Ejecuta la interfaz dado los parametros.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return resultado de la interfaz
	 * @throws InterfazException
	 */
	public InterfazResult executeInterfaz(InterfazParams interfazParams) throws Exception;
	
	/**
	 * Ejeucta Paquete de Inetrfaces en base a los parametros ingresados
	 * @param interfazParams
	 * @return
	 */
	public InterfazExecutionResult executePaquete(InterfazParams interfazParams) throws Exception;
	
	
	/* INI FRAMEWORK NSSICC PRUEBA TRANSACCION */
	public void executePruebaTransaccion();
	
	
	/**
	 * @param interfazParams
	 * @throws Exception
	 */
	public void beforeNewTransaction(InterfazParams interfazParams) throws Exception;
	
	/**
	 * @param interfazParams
	 * @throws Exception
	 */
	public void afterNewTransaction(InterfazParams interfazParams) throws Exception;
	
	/**
	 * @param interfazParams
	 * @return
	 * @throws Exception
	 */
	public int processInterfazNEW(InterfazParams interfazParams) throws Exception;
	
	/* FIN FRAMEWORK NSSICC PRUEBA TRANSACCION */
}