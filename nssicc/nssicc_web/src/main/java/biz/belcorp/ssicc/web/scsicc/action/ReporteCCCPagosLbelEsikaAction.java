package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCPagosLbelEsikaForm;

@ManagedBean
@SessionScoped
public class ReporteCCCPagosLbelEsikaAction extends
BaseReporteAbstractAction {
	
	
	private static final long serialVersionUID = -5593991020655649813L;
	
	private String formatoReporte;	
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCPagosLbelEsikaForm reporteForm = new ReporteCCCPagosLbelEsikaForm();
		return reporteForm;
	}
	
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteCCCPagosLbelEsikaForm f = (ReporteCCCPagosLbelEsikaForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codpais);
		f.setCodigoPais(codpais);		
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteCCCPagosLbelEsikaXLS";
		return "reporteMaestroVertical";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		ReporteCCCPagosLbelEsikaForm f = (ReporteCCCPagosLbelEsikaForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		String nfecha=DateUtil.convertDateToString(f.getFechaD());
		params.put("fecha",nfecha);
		return params;
	}
	
	
}
