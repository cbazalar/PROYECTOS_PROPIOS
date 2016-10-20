/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="">Jose Luis Rodriguez</a>
 *
 */
public interface MantenimientoCOMResponsablesUAService extends Service {

	/**
	 * Retorna la lista de Responsables UA
	 * @param criteria
	 * @return
	 */
	public List getListaResponsablesUA(Map criteria);
    
	/**
	 * Actualiza la tabla ZON_HISTO_GEREN
	 * @param criteria
	 */
	public void updateResponsableUA(Map criteria);

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
	 * Inserta la tabla ZON_HISTO_GEERN
	 * @param criteria
	 */
	public void insertResponsableUA(Map criteria);
	
	/**
	 * Elimina un responsable UA
	 * @param oidHistoGere
	 */
	public void deleteResponsableUA(Integer oidHistoGere);
}