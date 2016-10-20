package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERDetalleCtaCteForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportePERDetalleCtaCteAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -1091798983329193025L;

	private String formatoReporte;
	private String tipoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccRegionList;
	private List siccZonaList;
	private List siccSeccionList;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERDetalleCtaCteForm reporteForm = new ReportePERDetalleCtaCteForm();
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
		return "reporteMaestroHorizontal";
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
		this.formatoReporte = ((ReportePERDetalleCtaCteForm) this.formReporte)
				.getFormatoExportacion();
		if (("PDF".equals(formatoReporte)))
			return "reportePERDetalleCtaCte";
		return "";
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
			log.debug("Entering 'ReportePERDetalleCtaCteForm.prepareParameterMap' method");
		}

		ReportePERDetalleCtaCteForm f = (ReportePERDetalleCtaCteForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();
		params.put("NroReporte", " ");
		params.put("titulo",
				getReportResourceMessage("reportePERDetalleCtaCteForm.titulo"));

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
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReportePERDetalleCtaCteForm.setViewAtributes' method");
		}
		// servicios
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReportePERDetalleCtaCteForm ReportePERDetalleCtaCteForm = (ReportePERDetalleCtaCteForm) this.formReporte;
		ReportePERDetalleCtaCteForm.setPais(this.mPantallaPrincipalBean
				.getCurrentCountry());
		// defecto
		ReportePERDetalleCtaCteForm.reset();
		// asignacion

		this.siccMarcaList = service.getMarcas();

		this.siccCanalList = service
				.getCanalesByCodigoISO(this.mPantallaPrincipalBean
						.getCurrentIdioma().getCodigoISO());
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				ReportePERDetalleCtaCteForm.getCodigoPais(),
				Constants.CODIGO_CANAL_DEFAULT);
		this.formReporte.setCodigoPeriodo(codigoPeriodo);

		log.debug("Todo Ok: Redireccionando");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reportePERDetalleCtaCteService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReportePERDetalleCtaCteForm form = (ReportePERDetalleCtaCteForm) this.formReporte;
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
			if (regiones[i].getValue().compareTo(codigoRegionForm) == 0) {
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
				if (zonas[i].getValue().compareTo(codigoZonaForm) == 0) {
					existeZona = true;
				}
			}
			if (!existeZona) {
				return "Ingrese una zona válida para la región elegida";
			} else {
				seccion = loadSeccionesRegion();
				for (int i = 0; i < seccion.length; i++) {
					if (seccion[i].getValue().compareTo(CodigoSeccionForm) == 0) {
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
						if (territorio[i].getValue().compareTo(
								codigoTerritorioForm) == 0) {
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
	 * carga regiones por la marca y canal
	 * 
	 * @return
	 */
	public LabelValue[] loadRegionMarcaCanal() {
		try {
			LabelValue[] regiones = null;
			ReportePERDetalleCtaCteForm form = (ReportePERDetalleCtaCteForm) this.formReporte;
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
	 * Carga zonas
	 * 
	 * @return
	 */
	public LabelValue[] loadZonaRegion() {
		try {
			LabelValue[] zonas = null;
			ReportePERDetalleCtaCteForm form = (ReportePERDetalleCtaCteForm) this.formReporte;
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
	 * Carga secciones
	 * 
	 * @return
	 */
	public LabelValue[] loadSeccionesRegion() {
		try {
			LabelValue[] secciones = null;
			ReportePERDetalleCtaCteForm form = (ReportePERDetalleCtaCteForm) this.formReporte;
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
	 * Carga territorios
	 * 
	 * @return
	 */
	public LabelValue[] loadTerritoriosSeccion() {
		try {
			LabelValue[] territorios = null;
			ReportePERDetalleCtaCteForm form = (ReportePERDetalleCtaCteForm) this.formReporte;
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
}