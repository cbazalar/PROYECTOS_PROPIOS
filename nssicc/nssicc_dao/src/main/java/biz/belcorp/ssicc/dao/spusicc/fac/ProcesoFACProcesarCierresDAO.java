package biz.belcorp.ssicc.dao.spusicc.fac;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoFACProcesarCierresDAO extends DAO {
	
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
	 * Valida si una zona ha realizado correctamente sus procesos de cierre
	 * 
	 * @param criteria 
	 * @return
	 */
	boolean validaCierreZona(Map criteria);
	
	/**
	 * Valida si una region ha realizado correctamente sus procesos de cierre
	 * 
	 * @param criteria 
	 * @return
	 */
	boolean validaCierreRegion(Map criteria);

	/**
	 * Valida si un periodo ha realizado correctamente sus procesos de cierre
	 * 
	 * @param criteria 
	 * @return
	 */
	boolean validaCierrePeriodo(Map criteria);

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
	 * Lista que retorna los procesos de control de cierre de SICC
	 * 
	 * @param criteria 
	 * @return
	 */
	List getProcesosControlCierre(Map criteria);
	
}