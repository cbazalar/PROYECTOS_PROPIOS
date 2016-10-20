/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author vcupe - Vidal Cupe Quispe
 *
 */
public interface MantenimientoRECPedidosExpressPremiosBloqueadosDAO extends DAO {
	
	
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
	 * @param criteria
	 * @return La Campaa Actual y activa
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