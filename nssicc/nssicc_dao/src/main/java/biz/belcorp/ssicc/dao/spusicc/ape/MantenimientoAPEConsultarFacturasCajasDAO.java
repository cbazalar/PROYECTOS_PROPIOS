package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ConsultaFacturasCajas;

/**
 * @author Nicols Lpez
 *
 */

public interface MantenimientoAPEConsultarFacturasCajasDAO extends DAO{

	/**
	 * Retorna la Consulta de Facturas y Cajas por Numero de Consolidado
	 * @param criteria
	 * @return
	 */
	public List getConsultaFacturasCajasList(Map criteria);
	
	/**
	 * @param criteria
	 * @return El objeto de Consulta Facturas Cajas
	 */
	public ConsultaFacturasCajas getProductosFacturasCajasObject(Map criteria);
	
	/**
	 * @param criteria
	 * @return Lista de Productos Facturas Cajas
	 */
	public List getProductosFacturasCajasList(Map criteria);
	
}