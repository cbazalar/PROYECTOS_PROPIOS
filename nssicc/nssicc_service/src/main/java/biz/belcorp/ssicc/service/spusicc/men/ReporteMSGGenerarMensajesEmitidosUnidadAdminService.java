package biz.belcorp.ssicc.service.spusicc.men;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ReporteMSGGenerarMensajesEmitidosUnidadAdminService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz"> </a>
 */
public interface ReporteMSGGenerarMensajesEmitidosUnidadAdminService extends Service {

	/**
	 * Ejecuta Reporte respectivo
	 * @param criteria 
	 */
	public void grabarReporte(Map params) throws Exception;
	
	
	public void setCodigoPeriodo(String codigoPeriodo);
	

}

