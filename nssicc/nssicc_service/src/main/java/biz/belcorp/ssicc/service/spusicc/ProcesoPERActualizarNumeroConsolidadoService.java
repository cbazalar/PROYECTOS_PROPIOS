/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoPERActualizarNumeroConsolidadoService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
public interface ProcesoPERActualizarNumeroConsolidadoService extends Service {

	public void executeActualizarNumeroConsolidado(Map criteria, Usuario usuario);
	
}