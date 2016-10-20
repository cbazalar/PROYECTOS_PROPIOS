package biz.belcorp.ssicc.dao.spusicc.app;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextdoliva
 *
 */
public interface ProcesoAPPCargarHomolYobelDAO extends DAO {

	/**
	 * Metodo que inserta registro homologado Yobel
	 * @param criteria
	 */
	public void insertaHomologadoYobel(Map criteria);
		
	/**
	 * Metodo que inserta registo homologado Yobel en tabla temporal
	 * @param params
	 */
	public void insertaTemporalHomologadoYobel(Map params);
	
	/**
	 * Mtodo que limpia la tabla temporal de Homologados
	 */
	public void deleteTemporalHomologadoYobel();
		
	/**
	 * Devuelve la lista con la cantidad de zonas existentes
	 * Devuelve la lista de zonas 
	 * @return
	 */
	public List getSecuenciasZonasList();
	
	/**
	 * Devuelve la lista de zonas con la cantidad de territorios a homologar
	 * de la tabla app_homol_yobel
	 * @return
	 */
	public List getSecuenciasZonasHomologadasList();
	
	/**
	 * Devuelve la lista de zonas con la cantidad de territorios a homologar
	 * de la tabla app_tempo_homol_yobel
	 * @return
	 */
	public List getSecuenciasZonasTempoHomologadasList();
	
	/**
	 * Ejecuta el proceso de secuenciar zonas y territorios
	 */
	public void executeSecuenciarZonasTerritorios();
	
	/**
	 * Limpia la tabla de homologacion
	 */
	public void deleteTablaHomologacion();
	
	/**
	 * Ejecuta el proceso de secuenciar zonas y territorios
	 */
	public void executeSecuenciarZonasTerritorios(Map criteria);

	/**
	 * Ejecuta validaciones sobre la carga de homologacion
	 * @param params
	 */
	public void validarCargaHomologacion(Map criteria);

}