package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextcroman
 */

public interface MantenimientoOCRPreProductosICEService extends Service {	     

	/**
	 * Devuelve la lista de productos ICE
	 * @param criteria
	 * @return
	 */
	public List getListaProductosICE(Map criteria);
	
	/**
	 * Elimina productos ICE
	 * @param criteria
	 */
	public void deleteProductosICE(Map criteria);
	
	/**
	 * Obtiene producto por OID
	 * @param criteria
	 * @return
	 */
	public List getProductoICEbyOid(Map criteria);
	
	/**
	 * Modifica Productos ICE
	 * @param criteria
	 */
	public void updateProductoICE(Map criteria);
	
	/**
	 * Inserta Productos ICE
	 * @param criteria
	 */
	public void insertProductoICE(Map criteria);
}