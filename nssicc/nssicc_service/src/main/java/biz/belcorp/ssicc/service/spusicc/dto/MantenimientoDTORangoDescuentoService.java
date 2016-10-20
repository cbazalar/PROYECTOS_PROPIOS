package biz.belcorp.ssicc.service.spusicc.dto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoDTORangoDescuentoService  extends Service {

	/**
	 * retorna la lista de Rango Descuento
	 * 
	 * @return
	 */
	public List getListRangoDescuento(Map criteria);
	
	/**
	 * Recupera la lista de Grupos de Descuento
	 * 
	 * @param criteria
	 * @return
	 */
	public List getGruposDescuento(Map criteria);

	/**
	 * Inserta registro a la entidad Rango Descuento
	 * 
	 * @param params
	 */
	public void insertRangoDescuento(Map params);
	
	/**
	 * Actualiza registro a la entidad Rango Descuento
	 * 
	 * @param params
	 */
	public void updateRangoDescuento(Map params);
	
	/**
	 * Elimina registro a la entidad Rango Descuento
	 * 
	 * @param calificacionComisionDetalle
	 */
	public void deleteRangoDescuento(Map params);


}
