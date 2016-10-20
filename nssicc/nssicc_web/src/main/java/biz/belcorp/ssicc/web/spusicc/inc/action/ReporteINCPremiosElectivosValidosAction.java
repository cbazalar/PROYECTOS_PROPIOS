package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ReporteINCPremiosElectivosValidosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteINCPremiosElectivosValidosAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 4595148572115096913L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCPremiosElectivosValidosForm f = new ReporteINCPremiosElectivosValidosForm();
		return f;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(StringUtils.equalsIgnoreCase(this.formatoExportacion, "XLS"))
			return "reporteINCPremiosElectivosVaXLS";
				
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		if(StringUtils.equalsIgnoreCase(this.formatoExportacion, "PDF")|| 
				StringUtils.equalsIgnoreCase(this.formatoExportacion, "VPDF"))
			return "reporteINCPremiosElectivosValidosPDF";
		
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {		
		params.put("titulo", this.getReportResourceMessage("reporteINCPremiosElectivosValidosForm.titulo"));
		params.put("formatoExportacion", this.formatoExportacion);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
