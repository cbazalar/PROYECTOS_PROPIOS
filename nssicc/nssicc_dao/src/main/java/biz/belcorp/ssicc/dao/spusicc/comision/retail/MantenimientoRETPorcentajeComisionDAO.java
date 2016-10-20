/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision.retail;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli Silva</a>
 *
 */
public interface MantenimientoRETPorcentajeComisionDAO extends DAO {
	
	
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
