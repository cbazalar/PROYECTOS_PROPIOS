package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jose Luis Rodriguez
 */

public interface MantenimientoSTOFacturacionAdicionalService extends Service{

	/**
	 * Devuelve la lista de Factura Adicional
	 * @param criteria
	 * @return
	 */
	public List getFacturaAdicionalList(Map criteria);

	/**
	 * Elimina las Facturas Adicionales
	 * @param criteria
	 */
	public void deleteFacturaAdicional(Map criteria);

	/**
	 * Inserta Factura Adicional
	 * @param criteria
	 */
	public void insertFacturaAdicional(Map criteria);

}