package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoINCPremiosElectivosService  extends Service{

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
	 * Elimina los niveles dela consultora para luego volver a insertar los
	 * los selecciones 
	 * @param listPremiosElectivos
	 * @param items
	 */
	void insertPremiosElectivos(List listPremiosElectivos, String[] items);

	/**
	 * Elimina los niveles dela consultora para luego volver a insertar
	 * los seleccionados con las unidades respectivas
	 * @param listPremiosElectivos
	 * @param items
	 * @param unidadesItems
	 */
	void insertPremiosElectivos(List listPremiosElectivos, String[] items,
			String[] unidadesItems);

	/**
	 * Elimina los niveles dela consultora para luego volver a insertar
	 * los seleccionados con las unidades respectivas
	 * @param listPremiosElectivos
	 */
	void insertPremiosElectivos(List listPremiosElectivos);

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
	 * Elimina los premios electivos
	 * @param map
	 */
	void deletePremiosElectivos(Map map);
	
	/**
	 * Inserta los premios invalidos
	 * @param map
	 */
	public void insertPremioElectivoInvalido(Map map);
	
	/**
	 * Retorna la lista de concursos electivos Activos y Diferidos
	 * @return
	 */
	List getListParametrosConcursosElectivosDiferido();

	/**
	 * Realiza la carga de Premios Electivos
	 * 
	 */
	public void executeCargaPremiosElectivos(Map params) throws Exception;

	/**
	 * Elimina informacion de la carga de Premios electivos
	 * 
	 * @param map
	 */
	public void deleteCargaPremiosElectivos(Map params);
	
}
