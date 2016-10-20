package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.ape.model.ConsultaFacturasCajas;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEConsultarFacturasCajasService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPEConsultarFacturasCajasService extends Service{

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