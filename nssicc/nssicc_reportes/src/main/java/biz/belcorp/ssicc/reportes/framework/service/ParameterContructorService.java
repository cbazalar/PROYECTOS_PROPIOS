package biz.belcorp.ssicc.reportes.framework.service;

import java.util.Map;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

public interface ParameterContructorService {
	
	/**
	 * Inicializacion para Reportes generados en JASPER
	 * @param form
	 * @param parameterAdicionales
	 * @return
	 */
	public ReporteParams inicializarParametrosReporte(BaseReporteForm form, Map<String, Object> parameterAdicionales) throws Exception;
	
	/**
	 * Inicializacion para Reportes NO JASPER
	 * @param form
	 * @param parameterAdicionales
	 * @return
	 * @throws Exception
	 */
	public ReporteParams inicializarParametrosReporteNoJASPER(BaseReporteForm form, Map<String, Object> parameterAdicionales) throws Exception;
	
		
	/**
	 * @return
	 */
	public String getJasperDirectorio();

}
