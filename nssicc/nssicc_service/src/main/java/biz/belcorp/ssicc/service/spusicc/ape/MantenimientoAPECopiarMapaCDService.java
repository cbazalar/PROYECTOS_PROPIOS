package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPECopiarMapaCDService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPECopiarMapaCDService extends Service{

	/**
	 * Realiza la generacion de una nueva copia de un Mapa de Centro de Distribucin con su detalle
	 * @param criteria
	 */
	public void executeGenerarMapaCDDetalle(Map criteria);
	
}
