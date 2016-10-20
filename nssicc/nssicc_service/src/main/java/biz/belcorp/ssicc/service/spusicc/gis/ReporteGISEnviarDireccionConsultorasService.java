/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.gis;

import java.util.List;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ReporteGISEnviarDireccionConsultorasService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
public interface ReporteGISEnviarDireccionConsultorasService extends Service {

	/**
	 * Obtiene una lista de estados que posee una direccin de consultora
	 * @author sguerra
	 * @return
	 */
	public List getEstadoDireccion();

	/**
	 * Obtiene las estructuras geopolticas del pas
	 * @author sguerra
	 * @param codigo
	 * @return
	 */
	public List getEstructurasGeopoliticas(String codigo);
}
