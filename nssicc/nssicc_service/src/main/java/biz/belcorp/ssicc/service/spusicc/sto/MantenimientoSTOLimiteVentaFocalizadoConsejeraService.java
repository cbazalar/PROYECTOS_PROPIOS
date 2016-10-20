package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 */

public interface MantenimientoSTOLimiteVentaFocalizadoConsejeraService extends Service{
	
	/**
	 * borra un registro de Limite Venta Focalizado por Consejera
	 * @param parametros
	 */
	public void deleteLimiteVentaFocalizadoConsejera(Map parametros);

	/**
	 * Retorna la lista de Limite Venta Focalizada
	 * @param criteria
	 * @return
	 */
	public List getLimiteVentaFocalizadoConsejeraList(Map criteria);

	/**
	 * Insetta un registro de Limite Venta Focalizado por Consejera
	 * @param criteria
	 * @return
	 */
	public int insertSTOLimiteVentaFocalizadoConsejera(Map criteria);

	/**
	 * Obtiene un registro de Limite Venta Focalizado por Consejera
	 * @param criteria
	 * @return
	 */
	public Map getObjectoSTOLimiteVentaFocalizadoConsejera(Map criteria);

	/**
	 * Actualiza un registro de Limite Venta Focalizado por Consejera
	 * @param criteria
	 */
	public void updateObjetoSTOLimiteVentaFocalizadoConsejera(Map criteria);
	
}