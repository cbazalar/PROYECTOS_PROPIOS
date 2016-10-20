package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ReporteINCPremiosElectivosInvalidosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteINCPremiosElectivosInvalidosAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = -4354309115953273606L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCPremiosElectivosInvalidosForm f = new ReporteINCPremiosElectivosInvalidosForm();
		return f;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(StringUtils.equalsIgnoreCase(this.formatoExportacion, "XLS"))
			return "reporteINCPremiosElectivosInXLS";
				
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if(StringUtils.equalsIgnoreCase(this.formatoExportacion, "PDF")|| 
				StringUtils.equalsIgnoreCase(this.formatoExportacion, "VPDF"))
			return "reporteINCPremiosElectivosInvalidosPDF";
		
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		params.put("titulo", this.getReportResourceMessage("reporteINCPremiosElectivosInvalidosForm.titulo"));
		params.put("formatoExportacion", this.formatoExportacion);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
