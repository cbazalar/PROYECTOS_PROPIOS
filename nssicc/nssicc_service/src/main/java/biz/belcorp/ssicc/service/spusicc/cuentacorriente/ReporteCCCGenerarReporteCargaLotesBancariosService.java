package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ReporteCCCGenerarReporteCargaLotesBancariosService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz"> </a>
 */
public interface ReporteCCCGenerarReporteCargaLotesBancariosService extends Service {

	/**
	 * Ejecuta Reporte respectivo
	 * @param criteria 
	 */
	public void grabarReporte(Map params) throws Exception;
	
	/**
	 * @param numeroLote
	 */
	public void setNumeroLote(String numeroLote);

}

