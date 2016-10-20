/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pre;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Sigcomt
 *
 */
public interface MantenimientoPREEstimadosService extends Service {	
	
	List  getManPREEstimadosList(Map param);
	void  deleteManPREEstimados(Map param); 
    List getManPREEstimadosCatalogoList();
}
