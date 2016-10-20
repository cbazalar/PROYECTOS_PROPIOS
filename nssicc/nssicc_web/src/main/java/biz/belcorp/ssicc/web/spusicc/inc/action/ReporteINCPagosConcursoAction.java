package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCRecepcionarTransaccionesPagoConcursoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ReporteINCPagosConcursoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteINCPagosConcursoAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 4595148572115096913L;
	
	private List incPagoConcursoList;
	private List incMotivosPagoList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCPagosConcursoForm f = new ReporteINCPagosConcursoForm();
		return f;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(StringUtils.equalsIgnoreCase(this.formatoExportacion, "XLS"))
			return "reporteINCPagosConcursoXLS";
				
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		if(StringUtils.equalsIgnoreCase(this.formatoExportacion, "PDF")|| 
				StringUtils.equalsIgnoreCase(this.formatoExportacion, "VPDF"))
			return "reporteINCPagosConcurso";
		
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {	
		params.put("titulo", this.getReportResourceMessage("reporteINCPagosConcursoForm.titulo"));
		params.put("formatoExportacion", this.formatoExportacion);
		
		String codigoTipoAbono = (String) params.get("codigoTipoAbono");
		String condicionTipoAbono = "";
		
		if(!StringUtils.isEmpty(codigoTipoAbono)) {
			condicionTipoAbono = "AND det.pgta_cod_tipo_abon = '" + codigoTipoAbono + "'";
		}
		params.put("condicionTipoAbono", condicionTipoAbono);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		ProcesoINCRecepcionarTransaccionesPagoConcursoService service = (ProcesoINCRecepcionarTransaccionesPagoConcursoService) 
				getBean("spusicc.procesoINCRecepcionarTransaccionesPagoConcursoService");

		this.incPagoConcursoList = service.getListPagoConcurso();
		this.incMotivosPagoList = service.getListMotivoPago();
		
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
	}

	/**
	 * @return the incPagoConcursoList
	 */
	public List getIncPagoConcursoList() {
		return incPagoConcursoList;
	}

	/**
	 * @param incPagoConcursoList the incPagoConcursoList to set
	 */
	public void setIncPagoConcursoList(List incPagoConcursoList) {
		this.incPagoConcursoList = incPagoConcursoList;
	}

	/**
	 * @return the incMotivosPagoList
	 */
	public List getIncMotivosPagoList() {
		return incMotivosPagoList;
	}

	/**
	 * @param incMotivosPagoList the incMotivosPagoList to set
	 */
	public void setIncMotivosPagoList(List incMotivosPagoList) {
		this.incMotivosPagoList = incMotivosPagoList;
	}

}
