package biz.belcorp.ssicc.service.sisicc.framework;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;

/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Service de ejecucion de las Interfaces SiSiCC, este Service funciona como
 * interface entre la capa web (invocado por el
 * <code>BaseInterfazAbstractAction</code>) y las implementaciones de los
 * services especificos de cada interfaz.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public interface InterfazExecutionService {

	/**
	 * Ejecuta validaciones Previas a la Ejecución de la Interfaz
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> executeInterfazValidacionesPrevias(Map<String, Object> params) throws Exception;
	
	public Map<String, Object> executeInterfazValidacionesPreviasBase(Map<String, Object> params) throws Exception;
	
	/**
	 * Ejecuta la Interfaz SiSiCC, contiene la logica para delegar la ejecucion
	 * de las interfaces unitarias y de paquete.
	 * FALTA IMPLEMENTARLO COMO HILO APARTE EN EL ACTION !!!!! 
	 * 
	 * @param params
	 *            parametros de la interfaz
	 * @return List de InterfazResult con los resultados de la ejecucion.
	 */
	public InterfazExecutionResult executeInterfaz(Map<String, Object> params) throws Exception;
	
	/**
	 * Obtiene la implementacion especifica de la Interfaz del Map de
	 * implementaciones a partir del codigo.
	 * 
	 * @param codigo
	 *            utilizado como key del Map
	 * @return Implementacion especifica de la Interfaz SiCC
	 */
	public BaseInterfazService getInterfazImplementation(String codigo);
	
	/**
	 * Metodo que devuelve el listado de archivos que estan en el directorio de entrada de interfaces
	 * @param interfaz
	 * @param request
	 * @return
	 */
	public List getListArchivos(Interfaz interfaz) throws Exception ;
	
	/**
	 * Metodo que devuelve Lista de Interfaces y el listado de archivos que estan en el directorio de entrada de interfaces
	 * @param params
	 * @return
	 */
	public Map<String, Object> obtenerListaInterfaces(Map<String, Object> params) throws Exception ;
	
	/**
	 * 
	 * 
	 * Metodo que verifica si los numeros de lotes de una interfaz de paquete son iguales para 
	 * los archivos de las interfaces hijas
	 * "0" si es correcto
	 * "1" si no son iguales
	 * "-1" cuando no hay archivos
	 * @param params
	 * @return
	 */
	public String verificaNumeroLote(Map<String, Object> params) throws Exception ;
	
	
	/**
	 * Inserta Registro en la tabla de Procesos BATCH Actuales
	 * @param interfazParams
	 * @throws Exception
	 */
	public Map<String, Object> insertProceBatch(Map<String, Object> params) throws Exception;
	
	/**
	 * Verifica que el Proceso Batch se encuentren en ejecución
	 * Devuelve NULL si no hay proceso en ejecucion
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String verificarProcesoBatchEnEjecucion(Map<String, Object> params) ;
	
	
	/**
	 *  Verifica que los procesos Batch Dependientes se encuentren en ejecución
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String verificarProcesoBatchEnEjecucionDependientes(Map<String, Object> params) ;
	
	
	/**
	 * @param interfazParams
	 * @param exception
	 */
	public void updateInterfazRegistroProcesoBatch(Map<String, Object> params, Exception exception);
	
	/**
	 * Metodo que es invocado para finalizar la ejecucion del proceso BATCH
	 * @param params
	 * @param request
	 * @param interfazExecutionResult
	 * @param usuario
	 */
	public void updateInterfazRegistroProcesoBatch(Map<String, Object> params);
	
	
	public Map getInterfazImplementations();
	
}