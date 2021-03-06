package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ResultadoChequeo;

/**
 * @author Jesse J. Rios Franco
 *
 */
public interface MantenimientoPEDResultadoChequeoDAO extends DAO{

	/**
	 * retorna una lista de objetos de tipo ResultadoChequeo por el criterio de codigoResultadoChequeo
	 * @param map
	 * @return
	 */
	public List getResultadosChequeoList(Map map);

	/**
	 * realiza la elimiacion en conjunto de los resultados de chequeos seleccionados, la eliminacion 
	 * lo realiza por el codigoResultadoChequeo
	 * @param map
	 */
	public void deleteResultadosChequeo(Map map);

	/**
	 * retorna el objeto ResultadoChequeo por su id
	 * @param id
	 * @return ResultadoChequeo
	 */
	public ResultadoChequeo getResultadoChequeoObject(String id);

	/**
	 * inserta en la Base de datos un nuevo registro de resultados de chequeo
	 * @param params
	 */
	public void insertResultadoChequeo(Map params);

	/**
	 * actualiza en la Base de datos un nuevo registro de resultados de chequeo
	 * @param params
	 */
	public void updateResultadoChequeo(Map params);

}
