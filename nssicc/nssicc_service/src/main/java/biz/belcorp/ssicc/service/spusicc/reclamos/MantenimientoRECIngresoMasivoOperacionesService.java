/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author vcupe - Vidal Cupe Quispe
 *
 */
public interface MantenimientoRECIngresoMasivoOperacionesService extends Service {
	
	/**
	 * Mtodo que retorna la lista de los Tipos de Operacin
	 * @param params
	 * @return 
	 */
	public List getTipoOperacionList(Map params);

	/**
	 * @param criteria
	 * @return La Campaa Actual
	 */
	public String getObtenerCampahniaActual(Map criteria);	
	
	/**
	 * Obtiene el cuadro resumen de la carga de cliente
	 * @param criteria
	 * @return
	 */	
	public String getObtenerCampahniaActiva(Map criteria); 
	
	/**
	 * Obtiene los tipos de operaciones masivas
	 * @return
	 */
	public List getTipoMasivoOperacionesList();
	
	/**
	 * Inserta los Ingreso Masivos de Operaciones en las tablas temporales
	 * @param cabeceraList
	 * @param detalleList 
	 * @param params
	 */
	public void insertIngresoMasivoOperaciones(List cabeceraList, List detalleList, Map params);

	public List getCuv(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getMotivoReclamoList(Map criteria);
	
}
