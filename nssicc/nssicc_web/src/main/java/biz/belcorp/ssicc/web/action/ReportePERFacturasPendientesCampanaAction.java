package biz.belcorp.ssicc.web.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.form.ReportePERFacturasPendientesCampanaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportePERFacturasPendientesCampanaAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -1091798983329193025L;

	private String formatoReporte;
	private String tipoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccRegionList;
	private List siccZonaList;
	private List siccSeccionList;
	private List siccPresentacionList;

	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERFacturasPendientesCampanaForm reporteForm = new ReportePERFacturasPendientesCampanaForm();
		return reporteForm;
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
		return "reporteMaestroVertical";
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
		return "reportePERFacturaPendienteCampana";
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
		log.debug("Los parametros del Reporte en el Action "
				+ params.toString());
		params.put("NroReporte", " ");
		params.put("superiorIzquierda", "");
		params.put("condicionUsuario", "NO");
		params.put(
				"titulo",
				getResourceMessage("reportePERFacturasPendientesCampanaForm.titulo"));
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		ReportePERFacturasPendientesCampanaForm f = (ReportePERFacturasPendientesCampanaForm) this.formReporte;
		f.setCodigoPeriodo(codigoPeriodo);
		f.setMontoMinimo("0");
		f.setCodigoMarca("T");
		f.setCodigoCanal("VD");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reportePERFacturasPendientesSeccionService";
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {		
		params=super.prepareParamsBeforeExecute(params, form);
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		reporteService.executeFacturaPendienteCampana(params);		
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReportePERFacturasPendientesCampanaForm form = (ReportePERFacturasPendientesCampanaForm) this.formReporte;
		boolean existeRegion = false;
		boolean existeZona = false;
		boolean existeSeccion = false;
		boolean existeTerritorio = false;

		String codigoRegionForm = form.getCodigoRegion();
		String codigoZonaForm = form.getCodigoZona();
		String codigoTerritorioForm = form.getCodigoTerritorio();
		String CodigoSeccionForm = form.getCodigoSeccion();

		LabelValue[] regiones = null;
		LabelValue[] zonas = null;
		LabelValue[] seccion = null;
		LabelValue[] territorio = null;

		regiones = loadRegionMarcaCanal();
		for (int i = 0; i < regiones.length; i++) {
			String valorRegion=regiones[i].getValue();
			if (StringUtils.equals(valorRegion, codigoRegionForm)) {
				existeRegion = true;
			}
		}
		if (!existeRegion) {
			return "Ingrese una región válida para el Canal y Marca Elegidos";
		} else {

			zonas = loadZonaRegion();
			if (codigoZonaForm.isEmpty()) {
				return null;
			}
			for (int i = 0; i < zonas.length; i++) {
				String valorZona=zonas[i].getValue();
				if (StringUtils.equals(valorZona, codigoZonaForm)) {
					existeZona = true;
				}
			}
			if (!existeZona) {
				return "Ingrese una zona válida para la región elegida";
			} else {
				seccion = loadSeccionesRegion();
				for (int i = 0; i < seccion.length; i++) {
					if (StringUtils.equals(seccion[i].getValue(), CodigoSeccionForm)) {
						existeSeccion = true;
					}
				}
				if (!existeSeccion) {
					if (CodigoSeccionForm.isEmpty()) {
						return null;
					} else
						return "Ingrese una sección válida para la zona elegida";
				} else {
					territorio = loadTerritoriosSeccion();
					for (int i = 0; i < territorio.length; i++) {
						if (StringUtils.equals(territorio[i].getValue(), codigoTerritorioForm)) {
							existeTerritorio = true;
							log.debug(territorio[i].getValue()
									+ "++++++++++++++++++++++++");
						}
					}
					if (!existeTerritorio) {
						if (codigoTerritorioForm.isEmpty()) {
							return null;
						}
						return "Ingrese un territorio válido para la sección elegida";
					} else {
						return null;
					}
				}
			}
		}
	}

	/**
	 * Cargar regiones por marca y canal
	 * 
	 * @return
	 */
	public LabelValue[] loadRegionMarcaCanal() {
		try {
			LabelValue[] regiones = null;
			ReportePERFacturasPendientesCampanaForm form = (ReportePERFacturasPendientesCampanaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			regiones = aSvc
					.getRegionesByPaisMarcaCanal(this.mPantallaPrincipalBean
							.getCurrentCountry().getCodigo(), form
							.getCodigoMarca(), form.getCodigoCanal());
			return regiones;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}
	}

	/**
	 * Cargar Zonas
	 * 
	 * @return
	 */
	public LabelValue[] loadZonaRegion() {
		try {
			LabelValue[] zonas = null;
			ReportePERFacturasPendientesCampanaForm form = (ReportePERFacturasPendientesCampanaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			zonas = aSvc.getZonasByPaisCanalRegion(this.mPantallaPrincipalBean
					.getCurrentCountry().getCodigo(), form.getCodigoCanal(),
					form.getCodigoRegion());
			return zonas;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}
	}

	/**
	 * Cargar secciones
	 * 
	 * @return
	 */
	public LabelValue[] loadSeccionesRegion() {
		try {
			LabelValue[] secciones = null;
			ReportePERFacturasPendientesCampanaForm form = (ReportePERFacturasPendientesCampanaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			secciones = aSvc
					.getSeccionesByPaisMarcaCanalRegionZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(), form.getCodigoMarca(), form
									.getCodigoCanal(), form.getCodigoRegion(),
							form.getCodigoZona());
			return secciones;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}
	}

	/**
	 * Cargar Territorios
	 * 
	 * @return
	 */
	public LabelValue[] loadTerritoriosSeccion() {
		try {
			LabelValue[] territorios = null;
			ReportePERFacturasPendientesCampanaForm form = (ReportePERFacturasPendientesCampanaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			territorios = aSvc
					.getTerritoriosByPaisMarcaCanalRegionZonaSeccion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(), form.getCodigoMarca(), form
									.getCodigoCanal(), form.getCodigoRegion(),
							form.getCodigoZona(), form.getCodigoSeccion());
			return territorios;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}
	}
	
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
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return
	 */
	public List getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 */
	public void setSiccSeccionList(List siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return
	 */
	public List getSiccPresentacionList() {
		return siccPresentacionList;
	}

	/**
	 * @param siccPresentacionList
	 */
	public void setSiccPresentacionList(List siccPresentacionList) {
		this.siccPresentacionList = siccPresentacionList;
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
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
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