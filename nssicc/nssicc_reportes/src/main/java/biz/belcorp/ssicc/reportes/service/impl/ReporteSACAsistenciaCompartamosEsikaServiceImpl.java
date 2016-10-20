package biz.belcorp.ssicc.reportes.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;



/**
 * The Class ReporteSACAsistenciaCompartamosEsikaServiceImpl.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 17/09/2014
 */
@Service("reportes.reporteSACAsistenciaCompartamosEsikaServiceImpl")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSACAsistenciaCompartamosEsikaServiceImpl extends BaseReporteInterfaceImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2863893902708922335L;

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public void beforeGrabarReporte(BaseReporteForm form) {
		super.beforeGrabarReporte(form);		
		this.nombreReporte = "reporteSACAsistenciaEsikaFormXLS";
	}
	
}
