package biz.belcorp.ssicc.dao.spusicc.mic;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoMICSegurosDAO extends DAO {
	
	/**
	 * Retorna el cronograma d emicroseguros
	 * @param criteria
	 * @return
	 */
	List getCronogramaMicroseguros(Map criteria);
	
	/**Retorna la lista de tipo de operacion 
	 * @return
	 */
	List getTipoOperacion();

	/**
	 * Inserta cronograma
	 * @param map
	 */
	void insertCronograma(Map map);

	/**
	 * Actualiza Cronograma
	 * @param map
	 */
	void updateCronograma(Map map);
	
	/**
	 * Actualiza Microseguros
	 * @param map
	 */
	void updateMicroseguros(Map map);

	/**
	 * Inserta Microseguros
	 * @param map
	 */
	void insertMicroseguros(Map map);
	
	/**
	 * Actualiza Cobertura
	 * @param map
	 */
	void updateCobertura(Map map);

	/**
	 * Inserta Cobertura
	 * @param map
	 */
	void insertCobertura(Map map);
	
	/**
	 * Actualiza Bancos
	 * @param map
	 */
	void updateBancos(Map map);

	/**
	 * Inserta Bancos
	 * @param map
	 */
	void insertBancos(Map map);

	/**
	 * Actualiza Grupos
	 * @param map
	 */
	void updateGrupos(Map map);

	/**
	 * Actualiza Grupos
	 * @param map
	 */
	void updateGruposDetalle(Map map);

	/**
	 * Inserta Grupos
	 * @param map
	 */
	void insertGrupos(Map map);

	/**
	 * Inserta Grupos Detalle
	 * @param map
	 */
	void insertGruposDetalle(Map map);

	/**
	 * retorna lista de campos
	 * @param criteria
	 * @return
	 */
	List getBancos(Map criteria);

	/** retorna lista de cobertura
	 * @param criteria
	 * @return
	 */
	List getCobertura(Map criteria);

	/**
	 * retorna lista de grupos
	 * @param criteria
	 * @return
	 */
	List getGrupos(Map criteria);

	/**
	 * retorna grups de detalle
	 * @param criteria
	 * @return
	 */
	List getGruposDetalle(Map criteria);

	/**
	 * retorna microseguros
	 * @param criteria
	 * @return
	 */
	List getMicroseguros(Map criteria);
	
	/**
	 * Elima los detalles del grupo
	 * @param criteria
	 */
	void deleteGruposDetalle(Map criteria);
	
	/**Retorna los parametors d emicroseguros
	 * @return
	 */
	Map getParametrosMicroSeguro();

	/**
	 * devuelve la lista de cobertura grupos
	 * @param criteria
	 * @return
	 */
	List getCoberturaGrupos(Map criteria);

	/**
	 * Actualiza la cobertura de grupos
	 * @param map
	 */
	void updateCoberturaGrupos(Map map);

	/**
	 * Inserta la cobertura d egrupos
	 * @param map
	 */
	void insertCoberturaGrupos(Map map);

	/**
	 * Retona 1:si existe una fecha de inico para el mes y anho ingresado 
	 * 0 si no existe
	 * @param map
	 * @return
	 */
	Integer getValidacionFechaInicioCronograma(Map map);
}
