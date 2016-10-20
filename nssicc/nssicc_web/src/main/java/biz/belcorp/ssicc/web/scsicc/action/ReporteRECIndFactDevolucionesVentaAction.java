package biz.belcorp.ssicc.web.scsicc.action;


import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECIndFactDevolucionesVentaForm;

@ManagedBean
@SessionScoped
public class ReporteRECIndFactDevolucionesVentaAction extends
		BaseReporteAbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3927589546912252597L;
	private String formatoReporte;
	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECIndFactDevolucionesVentaForm reporteForm = new ReporteRECIndFactDevolucionesVentaForm();
		return reporteForm;
	}



	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteRECIndFactDevolucionesVentaAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		log.debug("Todo OK: Redireccionando");

	}
	
	public String setValidarReporte() {
		ReporteRECIndFactDevolucionesVentaForm form = (ReporteRECIndFactDevolucionesVentaForm)this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicio());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFin());
		int codperrefini=Integer.parseInt(form.getCodigoPeriodoRefInicio());
		int codperreffin=Integer.parseInt(form.getCodigoPeriodoRefFin());
		if(codperfin<codperini){
			String mensaje =  this.getResourceMessage("reporteRECIndFactDevolucionesVentaForm.errorInicioMayor");
			return mensaje;
		}else if(codperreffin<codperrefini){
			String mensaje =  this.getResourceMessage("reporteRECIndFactDevolucionesVentaForm.errorInicioMayor");
			return mensaje;
		}

	    					
	    return null;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECIndFactDevolucionesVentaForm form = (ReporteRECIndFactDevolucionesVentaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "ReporteRECIndFactDevolucionesVentaXLS";
		else
			return " ";
	
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
			
		ReporteRECIndFactDevolucionesVentaForm f = (ReporteRECIndFactDevolucionesVentaForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
				
		params.put("CodigoPeriodoInicio",f.getCodigoPeriodoInicio());
		params.put("CodigoPeriodoFin",f.getCodigoPeriodoFin());
		params.put("CodigoPeriodoRefInicio",f.getCodigoPeriodoRefInicio());
		params.put("CodigoPeriodoRefFin",f.getCodigoPeriodoRefFin());
		params.put("titulo", getResourceMessage("reporteRECIndFactDevolucionesVentaForm.title"));	
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
