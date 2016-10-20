package biz.belcorp.ssicc.service.spusicc.fdv;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.fdv.ConsultaFDVDistribucionMetaDAO;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="ConsultaFDVDistribucionMetaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public interface ConsultaFDVDistribucionMetaService extends Service{

	/**
	 * Setter del DAO, conveniente para pruebas unitarias
	 */
	public void setConsultaFDVDistribucionMetaDAO(
			ConsultaFDVDistribucionMetaDAO consultaFDVDistribucionMetaDAO);

	/**
     * Obtiene todos los parametros FDV tomando como criterios de busqueda los valores
     * enviados a traves de un Map
     */
	public List getDistribucionMetaFDVByCriteria(Map criteria);
	
}
