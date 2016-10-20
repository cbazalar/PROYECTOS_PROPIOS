package biz.belcorp.ssicc.service.spusicc.app;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoAPPSecuenciarZonaTerritorioService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
public interface ProcesoAPPSecuenciarZonaTerritorioService extends Service {

	/**
	 * Devuelve el listado de las regionas zonas a secuenciar
	 * @param criteria
	 * @return
	 */
	public List getRegionZonaSecuenciarList(Map criteria);
	
	/**
	 * Ejecuta el proceso de resetear la secuencia
	 * @param list
	 * @param oidPais
	 * @param codUsuario
	 */
	public void executeResetearSecuencia(List list, String oidPais, String codUsuario);
	
	/**
	 * Devuelve el listado de los territorios a secuenciar
	 * @param criteria
	 * @return
	 */
	public List getTerritoriosSecuenciarList(Map criteria);
	
	/**
	 * Devuelve la lista de emails parametrizados para el proceso
	 * @param criteria
	 * @return
	 */
	public List getEmailSecuenciarList(Map criteria);
	
	/**
	 * Almacena la secuenciacion de zonas
	 * @param lista
	 * @param secuencias
	 * @param oidPais
	 * @param codUsuario
	 */
	public void grabarSecuenciacionZonas(List lista, String[] secuencias, String oidPais, String codUsuario);
	
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
	 * @param list
	 */
	public void executeResetearSecuenciaTerritorios(List l);

	/**
	 * Almacena la secuenciacion de territorios
	 * @param lista
	 * @param secuencias
	 * @param codUsuario
	 * @param oidPais
	 */
	public void grabarSecuenciacionTerritorios(List lista, String[] secuencias, String codUsuario, String oidPais);
	
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
	 * Ejecuta el proceso de resetear la secuencia de territorios
	 * @param list
	 */
	public String executeResetearSecuenciaTerritorios2(List l, String codUsuario);
	
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
	 * Devuelve la lista de Zonas Secuenciadas
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
