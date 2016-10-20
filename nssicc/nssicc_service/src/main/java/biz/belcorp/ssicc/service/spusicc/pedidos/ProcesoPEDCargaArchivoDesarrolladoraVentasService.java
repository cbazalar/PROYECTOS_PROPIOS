/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DesarrolladoraVenta;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Danny Amaro
 *
 */
public interface ProcesoPEDCargaArchivoDesarrolladoraVentasService extends Service{
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getDesarrolladoraVenta(Map criteria);
		
	/**
	 * @param desarrolladoraVenta
	 * @param usuario
	 */
	public void insertDesarrolladoraVenta(DesarrolladoraVenta desarrolladoraVenta, Usuario usuario);
	
	/**
	 * @param criteria
	 */
	public void removeDesarrolladoraVenta(Map criteria);
	
	/**
	 * @param desarrolladoraVenta
	 * @param usuario
	 */
	public void insertDesarrolladoraVentaHistorico(DesarrolladoraVenta desarrolladoraVenta, Usuario usuario);

}
