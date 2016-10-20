package biz.belcorp.ssicc.dao.spusicc.fdv;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ConsultaFDVParametroDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public interface ConsultaFDVParametroDAO extends DAO{

	/**
     * Obtiene un listado de todos los parametros FDV en base a ciertos criterios los
     * cuales son enviados a travs de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            bsqueda
     * @return Lista de parametros FDV
     */
	public List getParametrosFDVByCriteria(Map criteria);

}
