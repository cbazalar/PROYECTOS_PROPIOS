package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author pejflorencio
 *
 */
public interface ProcesoCCCCargarPagosBancariosMasivosService extends Service {

	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUpload(Map datos);
	
	/**
	 * Metodo que borra la tabla temporal de la Carga de Pagos Bancarios Masivos
	 */
	public void deleteTablasCargaPagosBancariosMasivos();
	
	/**
	 * Metodo que realiza la validacion del archivo y la carga a la tabla temporal
	 * @param datos
	 * @throws Exception 
	 */
	public void executeValidarPagosBancariosMasivos(Map datos) throws Exception;
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargaPagosBancariosMasivos();
	
	/**
	 * Metodo que procesa los pagos bancarios masivos
	 * @param datos 
	 */
	public void executeProcesarPagosBancariosMasivos(Map datos);

	/**
	 * @param criteria
	 * @return
	 */
	public List getParamCargaArchivosBanco(Map criteria);

	/**
	 * @param datos
	 */
	public void insertCargaArchivosBanco(Map datos);

	/**
	 * 
	 */
	public void executeValidarCargaArchivosBanco();

	/**
	 * 
	 */
	public void deleteTablaCargaArchivosBanco();

}
