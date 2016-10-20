/**
 * 
 */
package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;

/**
 * @author Danny Amaro
 *
 */
@Service("reportes.reporteCCCContableSaldosCampaniasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCCCContableSaldosCampaniasServiceImpl extends BaseReporteInterfaceImpl {
	private static final long serialVersionUID = -4390094424857702131L;
		
	@Resource(name="spusicc.consultaCCCGenericoService")
	private ConsultaCCCGenericoService serviceCCC;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCContableSaldosCampaniasServiceImpl.beforeExecuteReporte' method");
		}
		super.beforeExecuteReporte(reporteParams,formReporte);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		serviceCCC.generarContSaldosCampanias(params);
		
		return reporteParams;
		
	}
	

	
}
