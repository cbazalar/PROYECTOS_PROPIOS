package biz.belcorp.ssicc.service.scsicc.framework;

import biz.belcorp.ssicc.service.scsicc.framework.beans.GraphParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.GraphResult;

/**
 * Service de ejecucin de los Graficos SSiCC.
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */
public interface GraphAbstractService {

	/**
	 * Genera el Grafico SSiCC.
	 * 
	 * @param graphParams
	 *            parametros del grafico
	 * @return GraphResult con los resultados de la ejecucion.
	 */
	public GraphResult executeGrafico(GraphParams graphParams) throws Exception;
	
	
	
}
