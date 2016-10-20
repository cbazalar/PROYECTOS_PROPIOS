package biz.belcorp.ssicc.service.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProcesoCOBCargarCronogramaService.
 *
 * @author Carlos Bazalar
 */
public interface ProcesoCOBCargarCronogramaService extends Service {

	/**
	 * Metodo que obtiene la ruta del directorio temporal.
	 *
	 * @param datos the datos
	 * @return the string
	 */
	public String obtenerPathUpload(Map datos);
	
	/**
	 * Trae Lista de Tipos de Etapa.
	 *
	 * @param datos the datos
	 * @return the tipos etapa
	 */
	public List getTiposEtapa(Map datos);
	
	
	/**
	 * Metodo que realiza la validacion del archivo y la carga a la tabla temporal.
	 *
	 * @param datos the datos
	 * @return the list
	 * @throws Exception the exception
	 */
	public List executeValidarCargarCronograma(Map datos) throws Exception;
	
	/**
	 * Metodo que procesa proceso los CAD en SICC.
	 *
	 * @param datos the datos
	 * @return the list
	 */
	public List executeProcesarCargarCronograma(Map datos);

	
	/**
	 * Obtener path upload estandar.
	 *
	 * @param datos the datos
	 * @return the string
	 */
	public String obtenerPathUploadEstandar(Map datos);
}
