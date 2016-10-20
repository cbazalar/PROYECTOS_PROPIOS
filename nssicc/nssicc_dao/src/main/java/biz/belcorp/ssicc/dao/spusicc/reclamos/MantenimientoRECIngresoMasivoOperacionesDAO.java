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
public interface MantenimientoRECIngresoMasivoOperacionesDAO extends DAO {

	/**
	 * Mtodo que retorna la lista de los Tipos de Operacin
	 * 
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
	 * @param criteria
	 * @return La Campaa Actual y activa
	 */
	public String getObtenerCampahniaActiva(Map criteria);

	/**
	 * Obtiene los tipos de operaciones masivas
	 * 
	 * @return
	 */
	public List getTipoMasivoOperacionesList();

	/**
	 * Inserta los Ingreso Masivos de Operaciones en las tablas temporales
	 * 
	 * @param cabeceraList
	 * @param detalleList
	 * @param params
	 */
	public void insertIngresoMasivoOperaciones(List cabeceraList,
			List detalleList, Map params);

	/**
	 * @param criteria
	 * @return El Indicador Express
	 */
	public String getIndExpress(Map criteria);

	public List getCuv(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getMotivoReclamoList(Map criteria);
}