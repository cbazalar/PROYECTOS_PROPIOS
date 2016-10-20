package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEAlarmasValoresCubicajeService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">David Ramos</a>
 */
public interface MantenimientoAPEAlarmasValoresCubicajeService extends Service{

	/**
	 * Devuelve la lista de productos Emitir Alarma
	 * @param criteria
	 * @return
	 */
	public List getAlarmasValoresCubicajeList(Map criteria);
}
