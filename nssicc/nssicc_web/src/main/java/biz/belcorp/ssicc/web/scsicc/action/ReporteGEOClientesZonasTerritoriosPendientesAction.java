package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteGEOClientesZonasTerritoriosPendientesForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteGEOClientesZonasTerritoriosPendientesAction extends
		BaseReporteAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -315750563493857602L;
	private String formatoReporte;

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteGEOClientesZonasTerritoriosPendientesForm reporteForm = new ReporteGEOClientesZonasTerritoriosPendientesForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {

		ReporteGEOClientesZonasTerritoriosPendientesForm reporteForm = (ReporteGEOClientesZonasTerritoriosPendientesForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();

		if (StringUtils.equals(formatoReporte, "XLS"))
			return "reporteGEOClientesZonasTerritoriosPendientesXLS";
		else
			return "reporteMaestroHorizontal";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteGEOClientesZonasTerritoriosPendientesForm reporteForm = (ReporteGEOClientesZonasTerritoriosPendientesForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		if (StringUtils.equals(formatoReporte, "XLS"))
			return "";
		else
			return "reporteGEOClientesZonasTerritoriosPendientesPDF";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */

	public String setValidarReporte() {
		ReporteGEOClientesZonasTerritoriosPendientesForm form = (ReporteGEOClientesZonasTerritoriosPendientesForm)this.formReporte;
		Integer fecha1,fecha2;
		Date fecha1D,fecha2D,fecha3D;
		fecha2D = form.getFechaDesdeDt();
		fecha1D = form.getFechaHastaDt();
	    if (fecha2D.compareTo(fecha1D) > 0){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }
	    	    					
	    return null;
	}
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteGEOClientesZonasTerritoriosPendientesForm.setViewAtributes' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// Servicios y formulario
		ReporteGEOClientesZonasTerritoriosPendientesForm form = (ReporteGEOClientesZonasTerritoriosPendientesForm) this.formReporte;
		// reset
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		form.setFechaDesdeDt(new Date(System.currentTimeMillis()));
		form.setFechaHastaDt(new Date(System.currentTimeMillis()));
		log.debug("Todo Ok: Redireccionando");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteAPEOrdenImpresionForm.prepareParameterMap' method");
		}

		ReporteGEOClientesZonasTerritoriosPendientesForm reporteForm = (ReporteGEOClientesZonasTerritoriosPendientesForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		reporteForm.setFechaDesde(DateUtil.getDate(reporteForm.getFechaDesdeDt()));
		reporteForm.setFechaHasta(DateUtil.getDate(reporteForm.getFechaHastaDt()));
		
		params.put("fechaDesde", reporteForm.getFechaDesde());
		params.put("fechaHasta", reporteForm.getFechaHasta());
		
		params.put(
				"NroReporte",
				getReportResourceMessage(
						"reporteGEOClientesZonasTerritoriosPendientesForm.numero.reporte"));
		params.put(
				"titulo",
				getReportResourceMessage(
						"reporteGEOClientesZonasTerritoriosPendientesForm.titulo"));

		return params;

	}

}