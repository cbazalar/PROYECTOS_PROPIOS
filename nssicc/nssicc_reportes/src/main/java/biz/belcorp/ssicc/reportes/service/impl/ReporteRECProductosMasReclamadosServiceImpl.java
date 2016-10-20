package biz.belcorp.ssicc.reportes.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reporteRECProductosMasReclamadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteRECProductosMasReclamadosServiceImpl extends BaseReporteInterfaceImpl{
	

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	private String secuencial = "0"; 
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		
	
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		
		reporteService.deleteTableReportesSQL(
				"deleteReporteRECOperacionesUnidadAdm", params);
		reporteService.deleteTableReportesSQL(
				"deleteReporteRECProductosMasReclamados", params);
/*		if (StringUtils.equals(Constants.IND_MOVIMIENTO_DEVUELVE,
				tipoMovimiento))
			reporteService.insertTableReportesSQL(
					"insertReporteRECProductosMasReclamadosDevuelve", params);
		else
			reporteService.insertTableReportesSQL(
					"insertReporteRECProductosMasReclamadosEnvia", params);*/
		
		  reporteService.insertTableReportesSQL(
		  "insertReporteRECProductosMasReclamados", params);
		 
		reporteService.executeReporteSQL(
				"executeReporteRECProductoMasReclamados", params);
		secuencial = params.get("secuencial").toString();
		reporteParams.setQueryParams(params);
		return reporteParams;
	}
	
	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		// TODO Auto-generated method stub
		super.afterExecuteReporte(reporteParams, formReporte);		
		Map params = new HashMap();
		params.put("query", "drop table rec_repor_prod_recla_" + secuencial);
		reporteService.executeReporteSQL("executeQueryDinamico", params);
		
		reporteParams.setQueryParams(params);
		return reporteParams;
	}

	/**
	 * @return the secuencial
	 */
	public String getSecuencial() {
		return secuencial;
	}

	/**
	 * @param secuencial the secuencial to set
	 */
	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}
}