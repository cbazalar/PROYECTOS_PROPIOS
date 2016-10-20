package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDetalleDocumentosContablesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCDetalleDocumentosContablesAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -5309787452883611368L;
	private String tipoReporte;
	private List siccSociedadList = new ArrayList();
	private LabelValue[] cccDocumentosContablesList = {};

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Carga de lista para cargar los combos en el JSP
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");

		// La constante SICC_SOCIEDAD_LIST almacena la lista de Sociedades por
		// Paiss
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		this.cccDocumentosContablesList = aSvc.getDocumentosContables();

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		ReporteCCCDetalleDocumentosContablesForm f = (ReporteCCCDetalleDocumentosContablesForm) this.formReporte;
		f.setFechaHastaD(new Date(System.currentTimeMillis()));
		f.setFechaDesdeD(new Date(System.currentTimeMillis()));
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}

		// Preparando los parametros
		ReporteCCCDetalleDocumentosContablesForm reporteCCCDetalleDocumentosContablesForm = (ReporteCCCDetalleDocumentosContablesForm) this.formReporte;

		String fecha1, fecha2;
		fecha1 = DateUtil.getDate(reporteCCCDetalleDocumentosContablesForm
				.getFechaDesdeD());
		fecha2 = DateUtil.getDate(reporteCCCDetalleDocumentosContablesForm
				.getFechaHastaD());
		reporteCCCDetalleDocumentosContablesForm.setFechaDesde(fecha1);
		reporteCCCDetalleDocumentosContablesForm.setFechaHasta(fecha2);

		this.tipoReporte = reporteCCCDetalleDocumentosContablesForm.getTipoReporte();
		params.put("fechaDesde",
				reporteCCCDetalleDocumentosContablesForm.getFechaDesde());
		params.put("fechaHasta",
				reporteCCCDetalleDocumentosContablesForm.getFechaHasta());
		params.put("tipoDocu",
				reporteCCCDetalleDocumentosContablesForm.getTipoDocumento());
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCDetalleDocumentosContablesForm form = new ReporteCCCDetalleDocumentosContablesForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCDetalleDocumentosContables" + this.tipoReporte + "XLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 *            the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the cccDocumentosContablesList
	 */
	public LabelValue[] getCccDocumentosContablesList() {
		return cccDocumentosContablesList;
	}

	/**
	 * @param cccDocumentosContablesList
	 *            the cccDocumentosContablesList to set
	 */
	public void setCccDocumentosContablesList(
			LabelValue[] cccDocumentosContablesList) {
		this.cccDocumentosContablesList = cccDocumentosContablesList;
	}
}