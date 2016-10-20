package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.sgr.MantenimientoSGRGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSGRSolicitudesForm;

@ManagedBean
@SessionScoped
public class ReporteSGRSolicitudesAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 3415122626841563673L;
	private String formatoReporte;
	private String tipoFormato;
	private List siccRegionList;
	private List sgrAseguradoraList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSGRSolicitudesService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSGRSolicitudesForm reporteForm = new ReporteSGRSolicitudesForm();
		return reporteForm;
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
			log.debug("ReporteSGRSolicitudesAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSGRGenericoService polizaService = (MantenimientoSGRGenericoService) this
				.getBean("spusicc.mantenimientoSGRGenericoService");
		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);

		this.sgrAseguradoraList = polizaService.getPoliza(null);

		log.debug("Todo OK: Redireccionando");

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
		String reporteFileName = "";

		if (this.tipoFormato.equals("0"))
			reporteFileName = "reporteSGRSolicitudesTodXLS";

		if (this.tipoFormato.equals("1"))
			reporteFileName = "reporteSGRSolicitudesRegXLS";

		if (this.tipoFormato.equals("2"))
			reporteFileName = "reporteSGRSolicitudesRecXLS";

		if (this.tipoFormato.equals("3") || tipoFormato.equals("4"))
			reporteFileName = "reporteSGRSolicitudesActXLS";

		if ("XLS".equals(this.formatoReporte))
			return reporteFileName;
		else
			return "reporteMaestroHorizontalSGRGenerico";
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
		String subReporte = "";

		if (this.tipoFormato.equals("0"))
			subReporte = "reporteSGRSolicitudesTodPDF";

		if (this.tipoFormato.equals("1"))
			subReporte = "reporteSGRSolicitudesRegPDF";

		if (this.tipoFormato.equals("2"))
			subReporte = "reporteSGRSolicitudesRecPDF";

		if (this.tipoFormato.equals("3") || tipoFormato.equals("4"))
			subReporte = "reporteSGRSolicitudesActPDF";
		
		return subReporte;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		MantenimientoSGRGenericoService polizaService = (MantenimientoSGRGenericoService) this
				.getBean("spusicc.mantenimientoSGRGenericoService");
		ReporteSGRSolicitudesForm reporteCOBForm = (ReporteSGRSolicitudesForm) this.formReporte;
		this.formatoReporte = reporteCOBForm.getFormatoExportacion();
		this.tipoFormato = reporteCOBForm.getCodigoEstado();

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();

		String codigoRegion = reporteCOBForm.getCodigoRegion();
		if (codigoRegion.compareToIgnoreCase("") == 0 || codigoRegion == null)
			codigoRegion = null;

		String codigoZona = reporteCOBForm.getCodigoZona();
		if (codigoZona.compareToIgnoreCase("") == 0 || codigoZona == null)
			codigoZona = null;

		String codigoSeccion = reporteCOBForm.getCodigoSeccion();
		if (codigoSeccion.compareToIgnoreCase("") == 0 || codigoSeccion == null)
			codigoSeccion = null;

		String codigoPeriodoInicio = reporteCOBForm.getCodigoPeriodoInicio();
		String codigoPeriodoFin = reporteCOBForm.getCodigoPeriodoFin();

		String codigoEstado = reporteCOBForm.getCodigoEstado();
		if (codigoEstado.compareToIgnoreCase("") == 0 || codigoEstado == null)
			codigoEstado = null;

		params.put("codigoPais", codigoPais);
		params.put("codigoRegion", null);
		params.put("codigoZona", null);
		params.put("codigoPeriodoInicio", codigoPeriodoInicio);
		params.put("codigoPeriodoFin", codigoPeriodoFin);
		params.put("codigoEstado", codigoEstado);
		params.put("tipoFormato", tipoFormato);
		params.put("NroReporte", "");
		params.put("titulo",
				getResourceMessage("reporteSGRSolicitudesForm.titulo"));
		// CAMPANHA VIGENTE SOLO PARA REGISTRADAS
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String campanhaVigente = service.getPeriodoDefaultByPaisCanal(
				codigoPais, Constants.CODIGO_CANAL_DEFAULT);
		params.put("campanhaVigente", campanhaVigente);


		if (StringUtils.isBlank(reporteCOBForm.getCodigoPoliza())) {
			params.put("nombreAseguradora", Constants.OPCION_TODOS);
		} else {
			List resultado = polizaService.getPoliza(params);

			if (resultado != null && resultado.size() > 0) {
				Map p = (Map) resultado.get(0);
				params.put(
						"nombreAseguradora",
						MapUtils.getString(p, "codigoPoliza", "")
								+ " - "
								+ MapUtils
										.getString(p, "descripcionPoliza", ""));
			}
		}

		return params;
	}

	/**
	 * Carga zonas
	 * 
	 * @param val
	 */
	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");

		if (val.getNewValue() == null) {
			return;
		}
		try {
			ReporteSGRSolicitudesForm form = (ReporteSGRSolicitudesForm) this.formReporte;
			form.setCodigoRegion(null);
			String valor = (String) val.getNewValue();
			if (valor.trim().length() > 0) {
					form.setCodigoRegion(valor);
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.siccZonaList = aSvc.getDesZonasByPaisMarcaCanalRegion(
						this.mPantallaPrincipalBean.getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, valor);

			} else {
				setSiccZonaList(null);
				setSiccSeccionList(null);

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	// CARGA SECCION
	public void loadseccion(ValueChangeEvent val) {
		log.debug("loadseccion");
		if (val.getNewValue() == null) {
			return;
		}
		try {
			ReporteSGRSolicitudesForm form = (ReporteSGRSolicitudesForm) this.formReporte;
			String region = (String) form.getCodigoRegion();
			String zona = (String) val.getNewValue();
			if (region.length() > 0 && zona.length() > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				setSiccSeccionList(aSvc.getSeccionesByPaisMarcaCanalRegionZona(
						this.mPantallaPrincipalBean.getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, region, zona));

			} else {
				setSiccSeccionList(null);

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

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

	/**
	 * @return the tipoFormato
	 */
	public String getTipoFormato() {
		return tipoFormato;
	}

	/**
	 * @param tipoFormato
	 *            the tipoFormato to set
	 */
	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the sgrAseguradoraList
	 */
	public List getSgrAseguradoraList() {
		return sgrAseguradoraList;
	}

	/**
	 * @param sgrAseguradoraList
	 *            the sgrAseguradoraList to set
	 */
	public void setSgrAseguradoraList(List sgrAseguradoraList) {
		this.sgrAseguradoraList = sgrAseguradoraList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 *            the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}
}