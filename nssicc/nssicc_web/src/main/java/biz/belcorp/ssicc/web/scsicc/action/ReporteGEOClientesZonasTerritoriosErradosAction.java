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
import biz.belcorp.ssicc.web.scsicc.form.ReporteGEOClientesZonasTerritoriosErradosForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteGEOClientesZonasTerritoriosErradosAction extends
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
		ReporteGEOClientesZonasTerritoriosErradosForm reporteForm = new ReporteGEOClientesZonasTerritoriosErradosForm();
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
		
		ReporteGEOClientesZonasTerritoriosErradosForm reporteForm = (ReporteGEOClientesZonasTerritoriosErradosForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		if (StringUtils.equals(formatoReporte, "XLS"))
			return "reporteGEOClientesZonasTerritoriosErradosXLS";
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
		ReporteGEOClientesZonasTerritoriosErradosForm reporteForm = (ReporteGEOClientesZonasTerritoriosErradosForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();

		if (StringUtils.equals(formatoReporte, "XLS"))
			return "";
		else
			return "reporteGEOClientesZonasTerritoriosErradosPDF";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */

	public String setValidarReporte() {
		ReporteGEOClientesZonasTerritoriosErradosForm form = (ReporteGEOClientesZonasTerritoriosErradosForm)this.formReporte;
		Date fecha1D,fecha2D;
		fecha2D = form.getFechaDesdeD();
		fecha1D = form.getFechaHastaD();
	    if (fecha2D.compareTo(fecha1D) > 0){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }
	    	    					
	    return null;
	}
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteGEOClientesZonasTerritoriosErradosForm.setViewAtributes' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		// Servicios y formulario
		ReporteGEOClientesZonasTerritoriosErradosForm form = (ReporteGEOClientesZonasTerritoriosErradosForm) this.formReporte;
		// reset
		form.setFechaDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaHastaD(new Date(System.currentTimeMillis()));
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
		ReporteGEOClientesZonasTerritoriosErradosForm reporteForm = (ReporteGEOClientesZonasTerritoriosErradosForm) this.formReporte;
		
		reporteForm.setFechaDesde(DateUtil.convertDateToString(reporteForm.getFechaDesdeD()));
		reporteForm.setFechaHasta(DateUtil.convertDateToString(reporteForm.getFechaHastaD()));
		
		params.put("fechaDesde", reporteForm.getFechaDesde());
		params.put("fechaHasta", reporteForm.getFechaHasta());

		
		formatoReporte = reporteForm.getFormatoExportacion();
		params.put("NroReporte", getReportResourceMessage(
				"reporteGEOClientesZonasTerritoriosErradosForm.numero.reporte"));
		params.put("titulo",
				getReportResourceMessage(
						"reporteGEOClientesZonasTerritoriosErradosForm.titulo"
						));

		return params;

	}

}