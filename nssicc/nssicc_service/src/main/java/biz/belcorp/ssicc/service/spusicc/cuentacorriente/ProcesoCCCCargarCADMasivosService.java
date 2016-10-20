package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author pejflorencio
 *
 */
public interface ProcesoCCCCargarCADMasivosService extends Service {

	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUpload(Map datos);
	
	/**
	 * Metodo que borra la tabla temporal de la Carga de Cargos y Abonos Masivos
	 */
	public void deleteTablasCargaCargosAbonosMasivos(Map datos);
	
	/**
	 * Metodo que realiza la validacion del archivo y la carga a la tabla temporal
	 * @param datos
	 * @throws Exception 
	 */
	public void executeValidarCargosAbonosMasivos(Map datos) throws Exception;
	
	/**
	 * Metodo que lista los errores de la carga
	 * @return
	 */
	public List getErroresCargaCargosAbonosMasivos(Map datos);
	
	/**
	 * Metodo que procesa proceso los CAD en SICC
	 * @param datos 
	 */
	public void executeProcesarCargosAbonosMasivos(Map datos);

}
