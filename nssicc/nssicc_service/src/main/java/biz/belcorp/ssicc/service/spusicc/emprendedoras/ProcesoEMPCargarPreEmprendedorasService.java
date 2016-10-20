package biz.belcorp.ssicc.service.spusicc.emprendedoras;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */

public interface ProcesoEMPCargarPreEmprendedorasService extends Service {

	/**
	 * Metodo que lista los programas
	 * @return
	 */
	public List getProgramas();
	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUpload(Map datos);
	
	/**
	 * Metodo que borra la tabla temporal de pre emprendedoras
	 */
	public void deleteTablasCargaPreEmprendedoras();
	
	/**
	 * Metodo que realiza la validacion del archivo y la carga a la tabla temporal
	 * @param datos
	 * @throws Exception 
	 */
	public void executeValidarPreEmprendedoras(Map datos) throws Exception;
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargaPreEmprendedoras();
	
	/**
	 * Metodo que procesa pre emprendedoras
	 * @param datos 
	 */
	public void executeProcesarPreEmprendedoras(Map datos);
}