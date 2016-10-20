package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEGeneracionMapaCentroDistribucionService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface MantenimientoAPEGeneracionMapaCentroDistribucionService extends Service{

	
	/**
	 * Obtiene el siguiente valor del codigo del Mapa de Centro de Distribucion
	 * @return
	 */
	public String getNextCodigoMapaCentroDistribucion();

	/**
	 * Genera el Mapa del Centro de Distribucion 
	 * @param criteria
	 */
	public void executeGenerarMapaCentroDistribucion(Map criteria);

}