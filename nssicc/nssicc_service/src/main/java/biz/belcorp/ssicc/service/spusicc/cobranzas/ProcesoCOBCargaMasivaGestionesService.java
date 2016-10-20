package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Gonzalo Huertas
 *
 */
public interface ProcesoCOBCargaMasivaGestionesService extends Service {

	/**
	 * Metodo que obtiene la ruta del directorio temporal
	 * @param datos
	 * @return
	 */
	public String obtenerPathUpload(Map datos);
	
	/**
	 * Trae Lista de Tipos de Etapa
	 * @param datos
	 * @return
	 */
	public List getTiposEtapa(Map datos);
	
	
	/**
	 * Metodo que realiza la validacion del archivo y la carga a la tabla temporal
	 * @param datos
	 * @throws Exception 
	 */
	public List executeValidarCargaMasivaGestiones(Map datos) throws Exception;
	
	/**
	 * Metodo que procesa proceso los CAD en SICC
	 * @param datos 
	 */
	public String executeProcesarCargaMasivaGestiones(Map datos);

}
