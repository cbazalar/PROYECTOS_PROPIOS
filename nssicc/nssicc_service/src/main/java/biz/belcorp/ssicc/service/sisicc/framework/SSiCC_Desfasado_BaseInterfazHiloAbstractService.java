package biz.belcorp.ssicc.service.sisicc.framework;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;


/**
 * @author peextsbuchelli
 *
 */
public interface SSiCC_Desfasado_BaseInterfazHiloAbstractService {
	    
	/**
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Map executeProcessBeforeInterfaz(Map params) throws Exception;	

	/**
	 * @param params
	 * @throws Exception
	 */
	public void executeHilo(Map params) throws Exception;
		
		
	/**
	 * Metodo que es invocado para cambiar el estado del proceso batch de EN EJECUCION a EN EJECUCION DE INTERFAZ
	 * @param params
	 * @param request
	 * @param usuario
	 */
	public void updateEstadoEjecucionInterfazProcesoBatch(Map params,
			Usuario usuario) ;
	
	
	/**
	 * Metodo que es invocado cuando el proceso Batch no ejecuta la interfaz respectiva
	 * a pesar que el proceso Batch haya sido ejecutado correctamente  
	 * Dicho metodo puede ser sobreescrito 
	 * @param params
	 * @param request
	 * @param usuario
	 */
	public void finalizeProcesoBatchSinEjecutarInterfaz(Map params,
			Usuario usuario);
	
	/**
	 * Invocado antes de realizar el forward del metodo 'executeInterfaz'. Esta
	 * implementacion muestra un mensaje al usuario generico en caso que todas
	 * las ejecuciones fueron correctas y un mensaje de error generico en caso
	 * de que alguna halla fallado. En caso se requiera mayor detalle para
	 * mostrar los mensajes al usuario se puede sobreescribir este metodo.
	 * 
	 * @param request
	 *            Request para setear los objetos en sesion.
	 * @param results
	 *            List de InterfazResult
	 */
	public void setExecuteInterfazONResults(Map params);
	
	
	/**
	 * Metodo que puede ser sobreescrito. Devuelve message que ser'a mostrado luego que se invoque
	 * a la ejecucion del hilo del proceso batch  
	 * @return
	 */
	public String getMessageEjecutandoseProcesoBatch();
	
	/**
	 * Metodo que devuelve el listado de archivos que estan en el directorio de entrada de interfaces
	 * @param interfaz
	 * @param request
	 * @return
	 */
	public List getListaArchivos(Map params,Interfaz interfaz);
	
	/**
	 * Metodo que verifica si los numeros de lotes de una interfaz de paquete son iguales para 
	 * los archivos de las interfaces hijas
	 * "0" si es correcto
	 * "1" si no son iguales
	 * "-1" cuando no hay archivos
	 * @param request
	 * @return
	 */
	public String verificaNumeroLote(Map params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public String verificaParamsBeforeExecute(Map params);
	
	/**
	 * Ejecuta la interfaz
	 * @param params
	 */
	public void executeInterfaz(Map params) throws Exception ;
	
	/**
	 * @param params
	 */
	public void beforeExecuteInterfaz(Map params);
	
	/**
	 * @param params
	 * @param interfazExecutionResult
	 */
	public void afterExecuteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult);
	
	
	/**
	 * @param params
	 * @param interfazExecutionResult
	 * @param usuario
	 */
	public void updateInterfazRegistroProcesoBatch(Map params,			
			SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult,
			Usuario usuario);
	
	
	
	/**
	 * @param params
	 * @param usuario
	 * @param exception
	 */
	public void updateInterfazRegistroProcesoBatch(Map params, Usuario usuario,
			Exception exception);

}
