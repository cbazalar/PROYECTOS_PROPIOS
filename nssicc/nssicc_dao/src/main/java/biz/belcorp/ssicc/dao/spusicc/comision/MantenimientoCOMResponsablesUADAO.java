/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="">Jose Luis Rodriguez</a>
 *
 */
public interface MantenimientoCOMResponsablesUADAO extends DAO {

	/**
	 * Retorna la lista de Responsables UA
	 * @param criteria
	 * @return
	 */
	public List getListaResponsablesUA(Map params);
	
	/**
	 * Actualiza la tabla ZON_HISTO_GEREN
	 * @param criteria
	 */
	public void updateResponsableUA(Map criteria);
	
	/**
	 * Devuelve el maximo Oid para la fecha desde
	 * @param criteria
	 * @return
	 */
	public Integer getMaximoOidDesde(Map criteria);
	
	/**
	 * Devuelve el maximo Oid para la fecha hasta
	 * @param criteria
	 * @return
	 */
	public Integer getMaximoOidHasta(Map criteria);

	/**
	 * Valida que el responsable de zona o seccion a registrar no se encuentre ya en otra zona o seccion en el rango de periodos ingresados
	 * @param criteria
	 * @return
	 */
	public String getValidaResponsable(Map criteria);

	/**
	 * Obtiene los codigos de sub gerencia y region por medio del codigo de zona
	 * @param codigoZona
	 * @return
	 */
	public Map getCodigosSubGerenciaRegionByCodigoZona(String codigoZona);

	/**
	 * Inserta la tabla ZON_HISTO_GEREN
	 * @param criteria
	 */
	public void insertResponsableUA(Map criteria);

	/**
	 * Elimina un responsable UA
	 * @param oidHistoGere
	 */
	public void deleteResponsableUA(Integer oidHistoGere);
}
