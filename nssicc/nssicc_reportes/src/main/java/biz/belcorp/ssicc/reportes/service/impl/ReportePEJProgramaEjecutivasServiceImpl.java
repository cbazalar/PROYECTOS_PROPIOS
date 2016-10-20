package biz.belcorp.ssicc.reportes.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;



/**
 * The Class ReporteSACAsistenciaCompartamosEsikaServiceImpl.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 17/09/2014
 */
@Service("reportes.reportePEJProgramaEjecutivasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReportePEJProgramaEjecutivasServiceImpl extends BaseReporteInterfaceImpl {

	private static final long serialVersionUID = -2863893902708922335L;
	
	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {		
		return super.afterExecuteReporte(reporteParams, formReporte);
	}

	@Override
	public void beforeGrabarReporte(BaseReporteForm form) {
		super.beforeGrabarReporte(form);		
		this.nombreReporte = form.getNombreReporte();
	}

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		this.nombreReporte = formReporte.getNombreReporte();		
		return reporteParams;
	}
	
	
	
}
