package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */

public interface ProcesoCOBCargarCronogramaCarteraService extends Service {

	
	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUpload(Map datos);
	
	/**
	 * Metodo que borra la tabla temporal del cronograma de cartera
	 */
	public void deleteTablasCargaCronogramaCartera(Map datos);
	
	/**
	 * Metodo que realiza la validacion del archivo y la carga a la tabla temporal
	 * @param datos
	 * @throws Exception 
	 */
	public void executeValidarCronogramaCartera(Map datos) throws Exception;
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargarCronogramaCartera();
	
	/**
	 * Metodo que procesa el cronograma de cartera
	 * @param datos 
	 */
	public void executeProcesarCronogramaCartera(Map datos);
}