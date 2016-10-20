package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLLIProductosNoRegistradosEnLLForm;

@ManagedBean
@SessionScoped
public class ReporteLLIProductosNoRegistradosEnLLAction extends
		BaseReporteAbstractAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1305771803560090822L;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLLIProductosNoRegistradosEnLLForm reporteForm = new ReporteLLIProductosNoRegistradosEnLLForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.info("ReporteLLIProductosNoRegistradosEnLLAction - setViewAttributes");
		}
			
		log.debug("Todo Ok: Redireccionando");

	}


	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteLLIProductosNoRegistradosEnLLForm form = (ReporteLLIProductosNoRegistradosEnLLForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "reporteMaestroVertical";
		else
			return "";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteLLIProductosNoRegistradosEnLLForm form = (ReporteLLIProductosNoRegistradosEnLLForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion())) {
			return "reporteLLIProductosNoRegistradosEnLL";
		} else {
			return "";
		}
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
	
		
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
	
		ReporteLLIProductosNoRegistradosEnLLForm reporte = (ReporteLLIProductosNoRegistradosEnLLForm) this.formReporte;

		Map criteria = params;
		formatoReporte = reporte.getFormatoExportacion();
			
		params.put("CodigoPais", reporte.getCodigoPais());
		params.put("CodigoPeriodo", reporte.getCodigoPeriodo());
		log.debug("CodigoPais: " + reporte.getCodigoPais());
		log.debug("CodigoPeriodo: " + reporte.getCodigoPeriodo());
		params.put("titulo",
				getResourceMessage("reporteLLIProductosNoRegistradosEnLLForm.title"));
		return params;
	}

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}


	

}

