package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author pejflorencio
 *
 */
public interface ProcesoCCCBloqueoFinancieroMasivoService extends Service {

	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUploadBloqueoFinancieroMasivo(Map datos);
	
	/**
	 * Metodo que borra la tabla temporal de la Carga de Bloqueo Financiero Masivo
	 */
	public void deleteTablasBloqueoFinancieroMasivo(Map datos);
	
	/**
	 * Metodo que realiza la validacion del archivo y la carga a la tabla temporal
	 * @param datos
	 * @throws Exception 
	 */
	public void executeValidarBloqueoFinancieroMasivo(Map datos) throws Exception;
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresBloqueoFinancieroMasivo(Map datos);
	
	/**
	 * Metodo que procesa el Bloqueo Financiero Masivo
	 * @param datos 
	 */
	public void executeProcesarBloqueoFinancieroMasivo(Map datos);

}
