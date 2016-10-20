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
public interface MantenimientoRECPedidosExpressPremiosBloqueadosService extends Service {
	
	/**
	 * Mtodo que retorna la lista de Concursos
	 * @param params
	 * @return 
	 */
	public List getConcursosList();
	
	/**
	 * Mtodo que retorna la lista de Premios
	 * @param params
	 * @return 
	 */
	public List getPremiosList();

	/**
	 * Mtodo que retorna la lista de Regiones
	 * @param params
	 * @return 
	 */
	public List getRegionesList();

	/**
	 * Mtodo que retorna la lista de pedidos express con premios bloqueados
	 * @param params
	 * @return 
	 */
	public List getPedidosExpressPremBloqList(Map criteria);
	
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
	 * Inserta los Ingreso Masivos de Operaciones en las tablas temporales
	 * @param cabeceraList
	 * @param detalleList 
	 * @param params
	 */
	public void insertIngresoMasivoOperaciones(List cabeceraList, List detalleList, Map params);
}
