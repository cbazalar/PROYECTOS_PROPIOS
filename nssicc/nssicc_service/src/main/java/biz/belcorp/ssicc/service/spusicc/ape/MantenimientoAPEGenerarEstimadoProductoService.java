package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEGenerarEstimadoProductoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface MantenimientoAPEGenerarEstimadoProductoService extends Service{

	/**
	 * Genera el Estimado para los producto por campaa
	 * @param criteria
	 */
	public void executeGenerarEstimadoProducto(Map criteria);
	
	/**
	 * Genera el numero de OLAS
	 * @param criteria
	 */
	public void executeGenerarOlas(Map criteria);

}