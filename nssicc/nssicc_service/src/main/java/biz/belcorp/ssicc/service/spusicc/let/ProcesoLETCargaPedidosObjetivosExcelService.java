package biz.belcorp.ssicc.service.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoLETCargaPedidosObjetivosExcelService extends Service{

	/**
	 * Realiza la carga del archivo excel,procede con las validaciones respectivas antes de procesar el excel e inserta en la lista pasada
	 * como segundo parametro los datos del excel y los regisotros q tubieron error en el tercer parametro
	 * @param criteria
	 * @param pedidosObjetivosList
	 * @param errorPedidosObjetivosListList
	 * @param flagErrorGlobal
	 * @return
	 */
	public boolean loadfileExcel(Map criteria, List pedidosObjetivosList,List errorPedidosObjetivosListList, Boolean flagErrorGlobal)throws Exception;
	
	/**
	 * Verifica si el codigo de zona ingresado existe
	 * @param criteria
	 * @return
	 */
	public Integer getExisteZona(Map criteria);
	
	/**
	 * Verifica si el codigo de seccion ingresado existe asociado a la zona ingresada
	 * @param criteria
	 * @return
	 */
	public Integer getExisteSeccionByZona(Map criteria);

	/**
	 * Ejecuta la carga de pedidos objetivos
	 * @param pedidosObjetivosList
	 */
	public void executeCargaPedidosObjetivos(List pedidosObjetivosList);
	
	/**
	 * Obtiene el codigo de concurso en base al periodo
	 * @param criteria
	 * @return
	 */
	public String getCodigoConcursoByPeriodo(Map criteria);
	
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