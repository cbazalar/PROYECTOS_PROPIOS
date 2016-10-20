package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="ProcesoRECBloqueoCDRService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
public interface ProcesoRECBloqueoCDRService extends Service {

	/**
	 * Metodo que devuelve la lista de Bloqueos de CDRs
	 * @param map
	 * @return
	 */
	public List getBloqueosCDRByCriteria(Map map);
	
	/**
	 * Metodo que actuliza la condicion de desbloqueo de CDRs
	 * @param map
	 */
	public void updateDesbloqueoCDR(String[] listaIDs, String usuario);
	
	/**
	 * Metodo que realiza el bloqueo de la consultora
	 * @param params
	 */
	public void executeProcesoRECBloqueoCDR(Map params);
	
	/**
	 * Realiza el proceso de Update de Enviar CDRs Recepcionados
	 * @param params
	 */
	public void executeProcesoRECEnviarCDRRecepcionados(Map params);
}
