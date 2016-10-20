package biz.belcorp.ssicc.service.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ConsultaLETResultadosConcursosService extends Service{

	/**
	 * Obtiene la lista de concursos activos con la descripcion formada por el codigo y descripcion
	 * @param criteria
	 * @return
	 */
	public List getParametroConcursoDescCompletaList(Map criteria);

	/**
	 * Obtiene la lista de resultados de concurso 
	 * @param criterios
	 * @return
	 */
	public List getConsultaLETResultadosConcursos(Map criterios);

	/**
	 * Obtiene un resultado de concurso
	 * @param criteria
	 * @return
	 */
	public Map getResultadoConcursoByCriteria(Map criteria);

	/**
	 * Obtiene el historico de clasificacion por lider y campaa de clasificacion
	 * @param criteria
	 * @return
	 */
	public Map getHistoricoClasificacionByCriteria(Map criteria);

	/**
	 * Obtiene la ultima seccion a cargo de la lider dentro de un concurso
	 * @param criteria
	 * @return
	 */
	public Map getUltimaSeccionCargoConcursoByCriteria(Map criteria);

	/**
	 * Obtiene la seccion del periodo actual
	 * @param criteria
	 * @return
	 */
	public Map getGestionSeccionActualByCriteria(Map criteria);

	/**
	 * Obtiene la lista del detalle del resultado de concurso
	 * @param criteria
	 * @return
	 */
	public List getDetalleResultadosConcursosListByCriteria(Map criteria);

	/**
	 * Obtiene la lista de recomendaciones de la lider dentro del rango del concurso
	 * @param criteria
	 * @return
	 */
	public List getRecomendacionesListByCriteria(Map criteria);

	/**
	 * Obtiene el total de recomendadas de una lider dentro de un rango de campaas de un concurso
	 * @param criteria
	 * @return
	 */
	public Integer getTotalRecomendadasByCriteria(Map criteria);

	/**
	 * Obtiene el total de ingresos efectivos de una lider dentro de un rango de campaas de un concurso
	 * @param criteria
	 * @return
	 */
	public Integer getTotalIngresosEfectivos(Map criteria);

	/**
	 * Obtiene la lista de rotaciones de la lider en diferentes secciones
	 * @param criteria
	 * @return
	 */
	public List getRotacionesListByCriteria(Map criteria);
}