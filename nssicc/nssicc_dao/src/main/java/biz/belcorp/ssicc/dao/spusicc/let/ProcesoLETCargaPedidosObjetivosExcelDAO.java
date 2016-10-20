package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoLETCargaPedidosObjetivosExcelDAO extends DAO{

	/**
	 * Verifica si el codigo de seccion ingresado existe asociado a la zona ingresada
	 * @param criteria
	 * @return
	 */
	public Integer getExisteSeccionByZona(Map criteria);

	/**
	 * Verifica si el codigo de zona ingresado existe
	 * @param criteria
	 * @return
	 */
	public Integer getExisteZona(Map criteria);

	/**
	 * Valida que las regiones seleccionadas no esten cerradas
	 * @param criteria
	 * @return
	 */
	public Integer validaRegionCerrada(Map criteria);

	/**
	 * Obtiene todos los registros de la tabla de pedidos objetivos
	 * @return
	 */
	public List getPedidosObjetivosAll();

	/**
	 * Obtiene el codigo de concurso en base al periodo
	 * @param criteria
	 * @return
	 */
	public String getCodigoConcursoByPeriodo(Map criteria);

	/**
	 * Inserta un pedido objetivo
	 * @param temp
	 */
	public void insertPedidoObjetivo(Map temp);

	/**
	 * Actualiza un pedido objetivo
	 * @param temp
	 */
	public void updatePedidoObjetivo(Map temp);

	/**
	 * Ejecuta el Calculo de Metas Lideres al cierre de campaa
	 * @param params
	 */
	public void executeCalculoMetasLideresCampana(Map params);

	/**
	 * Verifica si la zona es nueva o rezonificada
	 * @param criteria
	 * @return
	 */
	public Integer getZonaNuevaRezonificada(Map criteria);

	/**
	 * Verifica si la seccion es nueva
	 * @param criteria
	 * @return
	 */
	public Integer getSeccioNueva(Map criteria);

	/**
	 * Obtiene la campaa fin de un concurso mediante el codigo de concurso
	 * @param codigoConcurso
	 * @return
	 */
	public String getCampanaFinConcursoByCodigoConcurso(String codigoConcurso);

}