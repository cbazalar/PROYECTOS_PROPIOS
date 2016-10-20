package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;


@Service("reportes.reporteCCCPrimSegPedDeudService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCCCPrimSegPedDeudServiceImpl extends BaseReporteInterfaceImpl{

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	private Integer oid_ejecu_repor = 0;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCPrimSegPedDeudServiceImpl.beforeExecuteReporte' method");
		}
		super.beforeExecuteReporte(reporteParams, formReporte);	
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");	
		reporteService.executeReporteCCCPrimSegPedDeud(params);	
		oid_ejecu_repor = Integer.parseInt(params.get("oidEjecuRepor").toString());
		params.put("p_oid_ejecu_repor", oid_ejecu_repor);
		reporteParams.setQueryParams(params);
		return reporteParams;
	}
	
	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		// TODO Auto-generated method stub
		super.afterExecuteReporte(reporteParams, formReporte);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		log.debug("ReporteCCCPrimSegPedDeudAction - Delete to report ");
		reporteService.deleteTableReporteCCCPrimSegPedDeud(params);
		reporteParams.setQueryParams(params);
		return reporteParams;
	}

	/**
	 * @return the oid_ejecu_repor
	 */
	public Integer getOid_ejecu_repor() {
		return oid_ejecu_repor;
	}

	/**
	 * @param oid_ejecu_repor the oid_ejecu_repor to set
	 */
	public void setOid_ejecu_repor(Integer oid_ejecu_repor) {
		this.oid_ejecu_repor = oid_ejecu_repor;
	}
}
