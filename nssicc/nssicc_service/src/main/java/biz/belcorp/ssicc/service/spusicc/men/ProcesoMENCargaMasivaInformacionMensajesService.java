package biz.belcorp.ssicc.service.spusicc.men;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 *
 */
public interface ProcesoMENCargaMasivaInformacionMensajesService extends Service {

	/**
	 * Retorna los registros del excel del archivo cargado 
	 * 
	 * @param criteria
	 * @return
	 */
	public Map cargarArchivoExcel(Map criteria) throws Exception;
	
	/**
	 * Ejecuta el proceso de Validacion de Carga Masiva de Informacion
	 * 
	 * @param params
	 * @return
	 */
	public List executeValidarCargaMasiva(Map params);
		
	/**
	 * Ejecuta el proceso de Actualizacion de Carga Masiva de Informacion
	 * 
	 * @param params
	 * @return
	 */
	public void executeActualizarCargaMasiva(Map params);
	
	/**
	 * Elimina informacion de la tabla de Mensaje de Faltante de Anunciado basado en el 
	 * periodo ingresado como parametro
	 * @param params
	 */
	public void deleteCargaMasivaFaltanteAnunciado(Map params);
	
	/**
	 * Devuelve listado de Faltante Anunciado basado en el codigo de periodo ingresado como
	 * parametro
	 * @param params
	 * @return
	 */
	public List getDevuelveFaltanteAnunciado(Map params);
	
	/**
	 * Elimina informacion de la tabla de Mensaje de Faltante de Anunciado basado en el 
	 * registro ingresado como parametro
	 * @param params
	 */
	public void deleteFaltanteAnunciado(Map params);
	
	/**
	 * Inserta registro en la tabla de Faltante de Anunciado
	 * @param params
	 */
	public void insertFaltanteAnunciado(Map params);
	
	/**
	 * Devuelve listado de Faltante Anunciado basado en el codigo de periodo 
	 * y codigo cuv ingresado como parametro
	 * @param params
	 * @return
	 */
	public Map getDevuelveFaltanteAnunciadoCodigoCUV(Map params);
	
	/**
	 * Devuelve Campaa en base al nro de campaa ingresado
	 * @param params
	 * @return
	 */
	public String getDevuelveCodigoCampanha(Map params);
	
	/**
	 * Retorna los registros del excel del archivo cargado 
	 * 
	 * @param criteria
	 * @return
	 */
	public Map cargarArchivoExcelConferencias(Map criteria) throws Exception;
	
	/**
	 * Ejecuta el proceso de Validacion de Carga Masiva de Informacion
	 * 
	 * @param params
	 * @return
	 */
	public List executeValidarCargaMasivaConferencias(Map params);

	/**
	 * Elimina informacion de la tabla de Mensaje de Conferencias basado en el 
	 * periodo y region ingresados como parametros
	 * @param params
	 */
	public void deleteCargaMasivaConferencias(Map params);
	
	/**
	 * Ejecuta el proceso de Actualizacion de Carga Masiva de Informacion de Conferencias
	 * 
	 * @param params
	 * @return
	 */
	public void executeActualizarCargaMasivaConferencias(Map params);
	
	/**
	 * Devuelve listado de Conferencias basado en el codigo de region ingresado como
	 * parametro
	 * @param params
	 * @return
	 */
	public List getDevuelveConferencias(Map params);
	
	/**
	 * Inserta registro en la tabla de Conferencias
	 * @param params
	 */
	public void insertConferencias(Map params);
	
	/**
	 * Obtiene datos de la Zona por Region
	 * @param criteria
	 * @return
	 */
	public Map getGerenteZona(Map criteria);
	
	/**
	 * Elimina informacion de la tabla de Mensaje de Conferencias basado en el 
	 * registro ingresado como parametro
	 * @param params
	 */
	public void deleteConferencias(Map params);
	
	/**
	 * Devuelve codigo de Region de una Zona
	 * @param zona
	 * @return
	 */
	public String getDevuelveCodigoRegionZona(Map criteria);
	
}
