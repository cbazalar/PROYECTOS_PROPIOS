/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DesarrolladoraVenta;

/**
 * @author Danny Amaro
 *
 */
public interface ProcesoPEDCargaArchivoDesarrolladoraVentasDAO extends DAO{
	
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
