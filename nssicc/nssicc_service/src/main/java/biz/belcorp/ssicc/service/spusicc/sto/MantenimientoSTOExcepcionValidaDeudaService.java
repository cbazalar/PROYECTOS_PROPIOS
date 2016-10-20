package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 */

public interface MantenimientoSTOExcepcionValidaDeudaService extends Service{

	/**
	 * Devuelve la lista de Exceciones Deuda de acuerdo a los filtros
	 * @param criteria
	 * @return
	 */
	public List getExcepcionValidaDeudaList(Map criteria);

	/**
	 * Inserta Excepcion Deuda
	 * @param criteria
	 */
	public void insertExcepValidDeuda(Map criteria);

	/**
	 * Elmina Excepcion Deuda
	 * @param parametros
	 */
	public void deleteExcepcionValidaDeuda(Map parametros);

}