package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jose Luis Rodriguez
 *
 */
public interface MantenimientoSTOFacturacionAdicionalDAO extends DAO{

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
	
	/**
	 * Devuelve el Ultimo Oid de la Factura Adicional Cabecera
	 * @return
	 */
	public String getOidUltimoFacturaAdicionalCabecera();
	
	/**
	 * Inserta Factura Adicional Detalle
	 * @param criteria
	 */
	public void insertFacturaAdicionalDetalle(Map criteria);
	
	/**
	 * Elimina las Facturas Adicionales Detalle
	 * @param criteria
	 */
	public void deleteFacturaAdicionalDetalle(Map criteria);
	
	/**
	 * Inserta Facturacion Adicional Detalle
	 * @param criteria
	 */
	public void executeInsertFADDetalle(Map criteria);
	
	/**
	 * Devuelve el valor parametro.
	 * @param criteria
	 * @return
	 */
	public String getValidFacturaAdicional(Map criteria);

}