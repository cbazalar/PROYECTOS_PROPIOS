/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision.retail;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface MantenimientoRETPorcentajeComisionService extends Service {
	
    /**
     * Obtiene lista con informacin correspondiente a los Parametros de Zona Nueva 
     * @param bean
     * @return
     */
    public List getPorcentajeComision(Map map);
	    
	/**
	 * Actualiza registro correspondiente a los Porcentaje Comision 
	 * @param bean
	 * @param usuario
	 */
	public void updatePorcentajeComision(Map map);
	
}