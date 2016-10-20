/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoPERActualizarNumeroConsolidadoDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
public interface ProcesoPERActualizarNumeroConsolidadoDAO extends DAO {

	public void updateNumeroConsolidadoPercepciones(Map criteria,
			Usuario usuario);

}