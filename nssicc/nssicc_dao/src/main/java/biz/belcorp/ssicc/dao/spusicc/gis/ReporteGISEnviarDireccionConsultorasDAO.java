/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.gis;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ReporteGISEnviarDireccionConsultorasDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
public interface ReporteGISEnviarDireccionConsultorasDAO extends DAO {

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
