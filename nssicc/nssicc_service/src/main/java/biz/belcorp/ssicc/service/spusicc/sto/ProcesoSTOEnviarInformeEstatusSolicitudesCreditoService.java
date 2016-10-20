package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * 
 * <p>
 * <a href="ProcesoSTOEnviarInformeEstatusSolicitudesCreditoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public interface ProcesoSTOEnviarInformeEstatusSolicitudesCreditoService extends Service {
	
	/**
	 *Genera y enva los reportes a las GR y GZ
	 *
	 * @param params
	 * @return
	 */
	String executeEnviarReportes(Map params) throws Exception;
}
