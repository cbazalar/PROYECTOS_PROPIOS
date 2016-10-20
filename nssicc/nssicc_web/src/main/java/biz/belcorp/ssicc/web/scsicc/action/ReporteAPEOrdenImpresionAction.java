package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPELineaArmadoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEOrdenImpresionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteAPEOrdenImpresionAction extends BaseReporteAbstractAction {
	private String formatoReporte;
	private List tipoReporteList;
	private List siccMarcaList;
	private List siccCentrodList;
	private List siccLineaList;
	private List siccTipoSolList;
	private String oidPais;

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

	/**
	 * @return
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return
	 */
	public List getSiccCentrodList() {
		return siccCentrodList;
	}

	/**
	 * @param siccCentrodList
	 */
	public void setSiccCentrodList(List siccCentrodList) {
		this.siccCentrodList = siccCentrodList;
	}

	/**
	 * @return
	 */
	public List getSiccLineaList() {
		return siccLineaList;
	}

	/**
	 * @param siccLineaList
	 */
	public void setSiccLineaList(List siccLineaList) {
		this.siccLineaList = siccLineaList;
	}

	/**
	 * @return
	 */
	public List getSiccTipoSolList() {
		return siccTipoSolList;
	}

	/**
	 * @param siccTipoSolList
	 */
	public void setSiccTipoSolList(List siccTipoSolList) {
		this.siccTipoSolList = siccTipoSolList;
	}

	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return
	 */
	public List getTipoReporteList() {
		return tipoReporteList;
	}

	/**
	 * @param tipoReporteList
	 */
	public void setTipoReporteList(List tipoReporteList) {
		this.tipoReporteList = tipoReporteList;
	}

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteAPEOrdenImpresionForm reporteForm = new ReporteAPEOrdenImpresionForm();
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
		ReporteAPEOrdenImpresionForm reporteForm = (ReporteAPEOrdenImpresionForm) this.formReporte;
		this.formatoReporte = reporteForm.getFormatoExportacion();

		if ("XLS".equals(this.formatoReporte))
			return "reporteAPEOrdenImpresionXLS";

		else
			return "reporteMaestroVertical";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteAPEOrdenImpresionPDF";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteAPETotalArticulosAFPForm.setViewAtributes' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// Servicios
		MantenimientoAPELineaArmadoService serviceLinea = (MantenimientoAPELineaArmadoService) getBean("spusicc.mantenimientoAPELineaArmadoService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteAPEOrdenImpresionForm form = (ReporteAPEOrdenImpresionForm) this.formReporte;
		// reset
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		form.setFechaFacturacionDt(new Date(System.currentTimeMillis()));
		form.setTipoSolicitudList(null);
		// criteria Map
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("oidPais",
				reporteService.getOidString("getOidPaisByCodigoPais", criteria));
		// Asignacion
		this.setSiccTipoSolList(serviceLinea.getTipoSolicitudList(criteria));
		this.setOidPais(reporteService.getOidString("getOidPaisByCodigoPais",
				criteria));
		if (this.log.isDebugEnabled()) {
			this.log.debug(getSiccTipoSolList());
		}
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
		ReporteAPEOrdenImpresionForm reporteAPEForm = (ReporteAPEOrdenImpresionForm) this.formReporte;
		this.formatoReporte = reporteAPEForm.getFormatoExportacion();
		params.put("NroReporte", " ");
		params.put(
				"titulo",
				getReportResourceMessage("reporteAPEOrdenImpresionForm.titulo"));
		params.put("codigoPais", reporteAPEForm.getCodigoPais());
		params.put("oidPais",this.getOidPais());
		//conversión Fecha
		reporteAPEForm.setFechaFacturacion(DateUtil.getDate(reporteAPEForm.getFechaFacturacionDt()));
		//
		params.put("fechaFacturacion", reporteAPEForm.getFechaFacturacion());
		String condicionTipoSolicitud = "";
		if (!(reporteAPEForm.getTipoSolicitudList() == null)
				|| (Constants.OPCION_TODOS.equals(reporteAPEForm
						.getTipoSolicitudList()))) {
			condicionTipoSolicitud = this.obtieneCondicion(
					reporteAPEForm.getTipoSolicitudList(),
					"solic.cod_tipo_soli", "'");
		}
		params.put("condicionTipoSolicitud", condicionTipoSolicitud);
		return params;
	}
}