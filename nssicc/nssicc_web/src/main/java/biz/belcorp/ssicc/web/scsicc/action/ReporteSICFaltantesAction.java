package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICFaltantesForm;

/**
 * The Class ReporteSICFaltantesAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 20/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICFaltantesAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICFaltantesForm form = new ReporteSICFaltantesForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICFaltantesForm form = (ReporteSICFaltantesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteSICFaltantesXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {;
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		ReporteSICFaltantesForm f = (ReporteSICFaltantesForm) this.formReporte;
		
		log.debug(f.getFormatoExportacion());
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("titulo", getResourceMessage("reporteSICFaltantesForm.title"));
		return params;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteSICFacturacionAction - setViewAtributes");
		}
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
	}
}
