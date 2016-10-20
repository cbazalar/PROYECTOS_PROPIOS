package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;

@Service("reportes.reporteCOBComisionAbogadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOBComisionAbogadosServiceImpl extends BaseReporteInterfaceImpl {
	
	private static final long serialVersionUID = -4390094424857704111L;
	
	@Resource(name="spusicc.consultaCOBGenericoService")
	private ConsultaCOBGenericoService consultaCOBGenericoService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOBComisionAbogadosServiceImpl.beforeExecuteReporte' method");
		}		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		consultaCOBGenericoService.generarComisionAbogados(params);				
		reporteParams.setQueryParams(params);		
		return reporteParams;		
	}
}