package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoINCPremiosElectivosDAO extends DAO {

	/**
	 * Retorna la lista de concursos electivos Activos
	 * @return
	 */
	List getListParametrosConcursosElectivos();

	/**
	 * Ejecuta Validaciones , si es conrrecta las validacion retorna
	 * los datos de la cabecera quese mostararn en patalla asi mismo consolida
	 * la lista de elegidos y seleccionables 
	 * @param map
	 */
	void executeValidacionesPremiosElectivos(Map map);

	/**
	 * Retorna la lista de premios electivos ya consolidados
	 * @return
	 */
	List getPremiosElectivos();

	/**
	 * inserta el premio en funcion al numero de unidades
	 * @param map
	 */
	void insertPremioElectivo(Map map);

	/**
	 * Elimina los niveles de la consultora para el concurso
	 * @param map
	 */
	void deletePremiosElectivos(Map map);
	
	/**
	 * Devuelve lista de codigo ventas ficiticio
	 * @param map
	 * @return
	 */
	List<Map> getListCodigoVentas(Map<String, String> map);
	
	/**
	 * Retorna longitud codigo de  venta ficiticio
	 * @param codigoPais
	 * @return
	 */
	String getLongitudCodVentaFicticio(String codigoPais);
	
	/**
	 * inserta el premio invalidos
	 * @param map
	 */
	void insertPremioElectivoInvalido(Map map);

	/**
	 * Retorna la lista de concursos electivos Activos y Diferidos
	 * @return
	 */
	List getListParametrosConcursosElectivosDiferido();

	/**
	 * Elimina informacion de la carga de Premios electivos
	 * 
	 * @param map
	 */
	public void deleteCargaPremiosElectivos(Map map);
	
	/**
	 * Inserta un registro de Premios electivos
	 * 
	 * @param map
	 */
	public void insertCargaPremiosElectivos(Map map);

	/**
	 * Realiza la carga de los Premios electivos
	 * 
	 * @param map
	 */
	public void executeCargaPremiosElectivos(Map map); 
	
}
