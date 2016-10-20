//TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERLibroPercepcionesForm;

@ManagedBean
@SessionScoped
public class ReportePERLibroPercepcionesAction extends
		BaseReporteAbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2011193890759508502L;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERLibroPercepcionesForm reporteForm = new ReportePERLibroPercepcionesForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
	}

	public String setValidarReporte() {
		ReportePERLibroPercepcionesForm form = (ReportePERLibroPercepcionesForm) this.formReporte;
		if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0) {
			String mensaje = this.getResourceMessage("errors.compare.dates");
			return mensaje;
		} 

		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		ReportePERLibroPercepcionesForm reportePER = (ReportePERLibroPercepcionesForm)  this.formReporte;
		
		reportePER.setFechaDesde(DateUtil.convertDateToString(reportePER.getFechaDesdeD()));
		reportePER.setFechaHasta(DateUtil.convertDateToString(reportePER.getFechaHastaD()));
		
		formatoReporte = reportePER.getFormatoExportacion();
		
		params.put("NroReporte",getReportResourceMessage("reportePERLibroPercepcionesForm.regimenPercepciones"));
		params.put("superiorIzquierdaPer", getReportResourceMessage("reporte.maestro.cetco.ruc"));
		params.put("condicionFechaHora", "NO");
		params.put("condicionUsuario", "NO");
		params.put("titulo", getResourceMessage("reportePERLibroPercepcionesForm.titulo") + reportePER.getFechaDesde() + 
				" "+ getReportResourceMessage("reportePERLibroPercepcionesForm.al") + " " + reportePER.getFechaHasta());
		params.put("codigoPaisLbel", codpais.substring(0,2)+ Constants.FIN_CODIGO_PAIS_LBEL);
		
		params.put("fechaDesde", reportePER.getFechaDesde());
		params.put("fechaHasta", reportePER.getFechaHasta());
		
		return params;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePERLibroPercepcionesForm form = (ReportePERLibroPercepcionesForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "reporteMaestroHorizontalLibroPercepciones";
		else
			return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReportePERLibroPercepcionesForm form = (ReportePERLibroPercepcionesForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "libroPercepcionesEsquemas";
		else
			return " ";
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}




}
