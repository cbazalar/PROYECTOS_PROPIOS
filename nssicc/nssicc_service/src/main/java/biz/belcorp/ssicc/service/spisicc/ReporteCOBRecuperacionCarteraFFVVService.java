package biz.belcorp.ssicc.service.spisicc;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCOBRecuperacionCobranzaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz"> </a>
 */
public interface ReporteCOBRecuperacionCarteraFFVVService extends Service {

	/**
	 * Ejecuta Reporte respectivo
	 * @param criteria 
	 */
	public void grabarReporte(Map params) throws Exception;
	
	/**
	 * Devuelve Mail Service
	 * @return
	 */
	public BaseMailService getMailService();
	
	

}

