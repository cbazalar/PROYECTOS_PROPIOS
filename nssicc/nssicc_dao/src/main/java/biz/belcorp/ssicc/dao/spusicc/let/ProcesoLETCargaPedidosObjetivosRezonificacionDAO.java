package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoLETCargaPedidosObjetivosRezonificacionDAO extends DAO{

	/**
	 * Valida si existe concursos en el rango de periodos del concurso, tantos concursos vigentes, siguientes o subsiguientes
	 * @param criteria
	 * @return
	 */
	public Integer getValidaExisteConcursoByCodigoPeriodo(Map criteria);

	/**
	 * Obtiene la lista de zonas rezonificadas
	 * @param criteria
	 * @return
	 */
	public List getZonasRezonificacion(Map criteria);

	/**
	 * Obtiene la region cerrada por medio del codigo de zona
	 * @param criteria
	 * @return
	 */
	public String getRegionCerradaByCodigoZona(Map criteria);

	/**
	 * Inserta registro en la GTT de Zonas Rezonificadas para su proceso en el calculo
	 * @param parametros
	 */
	public void insertZonasRezonificadasGTT(Map parametros);

	/**
	 * Ejecuta el proceso de Carga de Pedidos Objetivos por Rezonificaciones
	 * @param params
	 */
	public void executeCargaPedidosObjetivosRezonificacion(Map params);
	
	/**
	 * Verifica si la zona es nueva o rezonificada
	 * @param criteria
	 * @return
	 */
	public Integer getZonaNuevaRezonificada(Map criteria);

	/**
	 * Verifica si la zona es nueva
	 * @param criteria
	 * @return
	 */
	public Integer getZonaNueva(Map criteria);
}