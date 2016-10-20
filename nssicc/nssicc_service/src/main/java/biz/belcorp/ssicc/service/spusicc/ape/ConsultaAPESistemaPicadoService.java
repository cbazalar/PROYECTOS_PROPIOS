package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaAPESistemaPicadoSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:nlopez@csigcomt.com">Nicolas Lopez</a>
 */

public interface ConsultaAPESistemaPicadoService extends Service {

	/**
	 * Recupera la lista de Sistemas de Picado
	 * 
	 * @param criteria
	 * @return
	 */
	
	public List getSistemaPicadoLista(Map criteria);
	
}
