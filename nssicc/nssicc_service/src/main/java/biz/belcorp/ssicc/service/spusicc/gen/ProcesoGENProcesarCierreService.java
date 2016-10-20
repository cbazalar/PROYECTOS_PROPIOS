package biz.belcorp.ssicc.service.spusicc.gen;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoGENProcesarCierreService extends Service {

	/**
	 * Lista que retorna las zonas que van a cerrarse
	 * 
	 * @param criteria 
	 * @return
	 */
	List getZonasACerrar(Map criteria);

	/**
	 * Lista que retorna las regiones que van a cerrarse
	 * 
	 * @param criteria 
	 * @return
	 */
	List getRegionesACerrar(Map criteria);

	/**
	 * Lista que retorna los procesos de cierre de Zona
	 * 
	 * @param criteria 
	 * @return
	 */
	List getProcesosCierreZona(Map criteria);
	
	/**
	 * Lista que retorna los procesos de cierre de region
	 * 
	 * @param criteria 
	 * @return
	 */
	List getProcesosCierreRegion(Map criteria);

	/**
	 * Valida si existe zonas x una determina region por procesar
	 * 
	 * @param criteria 
	 * @return
	 */
	boolean existeZonasxRegionSinProcesar(Map criteria);
	
	/**
	 * Ejecuta el proceso de cierre de zonas de SICC
	 * 
	 * @param params
	 */
	void executeProcesarCierreZona(Map params) throws Exception;
	
	/**
	 * Ejecuta el proceso de cierre de region de SICC
	 * 
	 * @param params
	 */
	void executeProcesarCierreRegion(Map params) throws Exception;

	/**
	 * Ejecuta el proceso de cierre de periodo de SICC
	 * 
	 * @param params
	 */
	void executeProcesarCierrePeriodo(Map params) throws Exception;

	/**
	 * Lista que retorna los procesos de cierre de Periodo
	 * 
	 * @param criteria 
	 * @return
	 */
	List getProcesosCierrePeriodo(Map criteria);

	/**
	 * Valida si un periodo esta configurado para realizar el cierre
	 * 
	 * @param criteria 
	 * @return
	 */
	boolean validaPeriodoACerrar(Map criteria);
	
	/**
	 * Valida si existe regiones por procesar
	 * 
	 * @param criteria 
	 * @return
	 */
	boolean existeRegionesSinProcesar(Map criteria);

	/**
	 * Obtiene el indicador del modulo de educacion
	 * @param codigoPais
	 * @return
	 */
	public String getIndicadorModEducacion(String codigoPais);
	
	/**
	 * Ejecuta el proceso de Reemplazos de SICC
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void executeProcesarReemplazos(Map params) throws Exception;
	
	/**
	 * Devuelve Codigo de Periodo en base a la cantidad de campaas
	 * @param params
	 * @return
	 */
	public String getDevuelvePeriodoByCodigoPeriodo(Map params) ;
	
	/**
	 * Devuelve Indicador Campaa Recaudo para Programa LEC
	 * @param params
	 * @return
	 */
	public String getIndicadorCampannaRecaudo(Map params) ;
	

	/**
	 * Lista que retorna los procesos de control de cierre de SICC
	 * 
	 * @param criteria 
	 * @return
	 */
	List getProcesosControlCierre(Map criteria);
	
}