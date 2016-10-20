package biz.belcorp.ssicc.dao.spusicc.app;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextdoliva
 *
 */
/**
 * @author peextdoliva
 *
 */
public interface ProcesoAPPSecuenciarZonaTerritorioDAO extends DAO {

	/**
	 * Devuelve el listado de las regionas zonas a secuenciar
	 * @param criteria
	 * @return
	 */
	public List getRegionZonaSecuenciarList(Map criteria);
	
	/**
	 * Ejecuta el proceso de resetear la secuencia
	 * @param criteria	 */
	public String executeResetearSecuencia(Map criteria);
	
	/**
	 * Devuelve el listado de los territorios a secuenciar
	 * @param criteria
	 * @return
	 */
	public List getTerritoriosSecuenciarList(Map criteria);
	
	/**
	 * Devuelve la cantidad de zonas sin secuencia
	 * @param criteria
	 * @return
	 */
	public String getCantidadZonasSinSecuencia(Map criteria);
	
	/**
	 * Devuelve la cantidad de territorios sin secuencia
	 * @param criteria
	 * @return
	 */
	public String getCantidadTerritoriosSinSecuencia(Map criteria);
	
	/**
	 * Devuelve la lista de emails parametrizados para el proceso
	 * @param criteria
	 * @return
	 */
	public List getEmailSecuenciarList(Map criteria);
	
	/**
	 * Almacena la secuenciacion de zonas
	 * @param criteria
	 */
	public void grabarSecuenciacionZonas(Map criteria);
	
	/**
	 * Devuelve la lista de Zonas sin secuenciar
	 * @return
	 */
	public List getZonasSinSecuenciarList();
	
	/**
	 * Devuelve la lista de territorios sin secuenciar
	 * @return
	 */
	public List getTerritoriosSinSecuenciarList();

	/**
	 * Ejecuta el proceso de resetear la secuencia de territorios
	 * @param criteria
	 */
	public void executeResetearSecuenciaTerritorios(Map criteria);

	/**
	 * Almacena la secuenciacion de territorios
	 * @param criteria
	 */
	public void grabarSecuenciacionTerritorios(Map criteria);
	
	/**
	 * Elimina Las Rutas terri al ingresar a la opcion
	 */
	public void deleteRutasTerri();
	
	/**
	 * Devuelve la cantidad de secuencias que se repiten
	 * @param criteria
	 * @return
	 */
	public List getCantidadSecuenciasExistentes(Map criteria);
	
	/**
	 * Devuelve la lista con la cantidad de zonas existentes
	 * Devuelve la lista de zonas 
	 * @return
	 */
	public List getSecuenciasZonasList(Map criteria);
	
	/**
	 * Devuelve la lista de zonas con la cantidad de territorios a homologar
	 * @return
	 */
	public List getSecuenciasZonasHomologadasList(Map criteria);
	
	/**
	 * Ejecuta el proceso de resetear la secuencia de territorios
	 * @param criteria
	 */
	public void executeResetearSecuenciaTerritorios2(Map criteria);
	
	/**
	 * Devuelve los datos para la auditoria
	 * @param criteria
	 * @return
	 */
	public List getAuditoriaList(Map criteria);
	
	/**
	 * Se inserta en la tabla de auditoria
	 * @param criteria
	 */
	public void insertaAuditoria(Map criteria);
	
	/**
	 * DEvuelve la lista de Zonas Secuenciadas
	 * @param criteria
	 * @return
	 */
	public List getSecuenciaZonasList(Map criteria);
	
	/**
	 * Devuelve la lista de Territorios Secuenciados
	 * @param criteria
	 * @return
	 */
	public List getSecuenciaTerritorioList(Map criteria);

	/**
	 * Ejecuta la secuencia de territorios
	 * @param criteria
	 */
	public void executeProcesoSecuenciarZonaTerritorio(Map criteria);

}