package biz.belcorp.ssicc.service.spusicc.mic;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoMICSegurosService  extends Service{

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
	 * Actualiza cronograma
	 * @param map
	 */
	void updateCronograma(Map map);

	/**
	 * Inserta Cronograma
	 * @param map
	 */
	void insertCronograma(Map map);

	/**
	 * Retorna lista de microseguros
	 * @param criteria
	 * @return
	 */
	List getMicroseguros(Map criteria);

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
	 * Retorna lista de cobertura
	 * @param criteria
	 * @return
	 */
	List getCobertura(Map criteria);


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
	 * Retorna lista de bancos
	 * @param criteria
	 * @return
	 */
	List getBancos(Map criteria);	
	
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
	 * Retorna lista de grupos
	 * @param criteria
	 * @return
	 */
	List getGrupos(Map criteria);

	/**
	 * Retorna lista de grupos detalle
	 * @param criteria
	 * @return
	 */
	List getGruposDetalle(Map criteria);
	
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
