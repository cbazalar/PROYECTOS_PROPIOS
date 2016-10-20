package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMDetalleComisionRecuperacionForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOMDetalleComisionRecuperacionAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 6748074627923655072L;
	private List siccComisionList;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private String formatoExportacion1;
	private String tipoVista;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMDetalleComisionRecuperacionForm form = new ReporteCOMDetalleComisionRecuperacionForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCOMDetalleComisionRecXLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	public String setValidarReporte() {
		ReporteCOMDetalleComisionRecuperacionForm form = (ReporteCOMDetalleComisionRecuperacionForm) this.formReporte;
		String mensaje = "";
		if (StringUtils.isBlank(form.getCodigoRegion())) {
			mensaje = "'Region' es un campo requerido";
			this.addError("Error ", mensaje);
			return mensaje;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMDetalleComisionRecuperacionAction.prepareParameterMap' method");
		}

		ReporteCOMDetalleComisionRecuperacionForm form = (ReporteCOMDetalleComisionRecuperacionForm) this.formReporte;

		form.setFormatoExportacion(this.formatoExportacion);
		this.formatoExportacion1 = "XLS";
		this.tipoVista = form.getTipoReporte();

		String condicionZona = StringUtils.EMPTY;
		condicionZona = this.obtieneCondicion(form.getZonaList(),
				"re.COD_ZONA", "'");
		log.debug(">>Zonas Seleccionadas: " + condicionZona);

		// se ingresan los parametros en el map
		params.put("codComision", form.getCodigoComision());
		params.put("codPeriodo", form.getCodigoPeriodo());
		params.put("codRegion", form.getCodigoRegion());
		params.put("condicionZona", condicionZona);

		form.setTitulo(this
				.getReportResourceMessage("reporteCOMDetalleComisionRecuperacionForm.titulo"));
		params.put("titulo", form.getTitulo());

		return params;
	}

	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ReporteCOMDetalleComisionRecuperacionForm form = (ReporteCOMDetalleComisionRecuperacionForm) this.formReporte;
			String regiones = (String) val.getNewValue();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasByPaisActivasNoActivas(
					form.getCodigoPais(), regiones));
			form.setZonaList(null);

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMDetalleComisionRecuperacionAction.setViewAtributes' method");
		}
		this.formatoExportacion = "XLS";
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteCOMDetalleComisionRecuperacionForm f = (ReporteCOMDetalleComisionRecuperacionForm) this.formReporte;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();

		// se obtiene el list de comisiones
		setSiccComisionList(service.getComision());

		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);

		f.setCodigoPeriodo(codigoPeriodo);
		f.setCodigoPais(codigoPais);
		f.setDescPais(pais.getDescripcion());

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codigoPais);

		setSiccRegionList(reporteService.getListaGenerico(
				"getRegionesByPaisActivasNoActivas", criteriaOperacion));

		Base base = (Base) this.siccRegionList.get(0);
		setSiccZonaList(aSvc.getZonasByPaisActivasNoActivas(f.getCodigoPais(),
				base.getCodigo()));
	}

	/**
	 * @return
	 */
	public List getSiccComisionList() {
		return siccComisionList;
	}

	/**
	 * @param siccComisionList
	 */
	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	/**
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista
	 *            the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	 * @return the formatoExportacion1
	 */
	public String getFormatoExportacion1() {
		return formatoExportacion1;
	}

	/**
	 * @param formatoExportacion1
	 *            the formatoExportacion1 to set
	 */
	public void setFormatoExportacion1(String formatoExportacion1) {
		this.formatoExportacion1 = formatoExportacion1;
	}

}