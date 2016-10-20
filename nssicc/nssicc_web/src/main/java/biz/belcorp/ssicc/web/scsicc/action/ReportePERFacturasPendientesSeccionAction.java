package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERFacturasPendientesSeccionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportePERFacturasPendientesSeccionAction extends
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
		ReportePERFacturasPendientesSeccionForm reporteForm = new ReportePERFacturasPendientesSeccionForm();
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
		this.formatoReporte = ((ReportePERFacturasPendientesSeccionForm) this.formReporte)
				.getFormatoExportacion();

		if ("1".equals(this.tipoReporte))
			return "reporteMaestroHorizontalFacturaSeccion";
		else if ("2".equals(tipoReporte))
			return "reporteMaestroHorizontalFacturaSeccion";
		else
			return "reporteMaestroVerticalControlAsistencia";
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
		this.formatoReporte = ((ReportePERFacturasPendientesSeccionForm) this.formReporte)
				.getFormatoExportacion();
		if (("PDF".equals(this.formatoReporte))) {
			if ("1".equals(tipoReporte))
				return "reportePERFacturaPendienteDetalle";
			else if ("2".equals(tipoReporte))
				return "reportePERFacturaPendienteResumen";
			else if ("3".equals(tipoReporte))
				return "reportePERFacturaPendienteZonaPeriodo";
			else if ("4".equals(tipoReporte))
				return "reportePERFacturaPendienteZonaRegion";
			return "reportePERFacturaPendienteDetalle";
		}
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
			log.debug("Entering 'ReportePERFacturasPendientesSeccionForm.prepareParameterMap' method");
		}

		ReportePERFacturasPendientesSeccionForm f = (ReportePERFacturasPendientesSeccionForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();

		String presentacion[] = f.getPresentacion();
		params.put("tipoPresentacion", presentacion[0]);
		params.put("NroReporte", " ");
		tipoReporte = presentacion[0];

		log.debug("Los parametros del Reporte en el Action "
				+ params.toString());
		if ("1".equals(tipoReporte)) {
			params.put("titulo", "FACTURAS PENDIENTES POR SECCION");
		} else if ("2".equals(tipoReporte)) {
			ClassPathResource resource = new ClassPathResource(
					Constants.JASPER_DIRECTORIO
							+ "reportePERFacturaPendienteResumenRecuperacion"
							+ JASPER_EXTENSION);

			params.put(
					"SUBREPORT_DIR1",
					(JasperReport) JRLoader.loadObject(this.getClass()
							.getClassLoader().getResource(resource.getPath())));

			params.put("titulo", "RESUMEN DE FACTURAS PENDIENTES POR SECCION");
		} else if ("3".equals(tipoReporte)) {
			params.put("titulo",
					"PEDIDOS DE RESPONSABILIDAD POR ZONA Y SECCION");
		} else if ("4".equals(tipoReporte)) {
			params.put("titulo", "PEDIDOS DE RESPONSABILIDAD POR ZONA Y REGION");
		}

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
			this.log.debug("Entering 'ReportePERFacturasPendientesSeccionForm.setViewAtributes' method");
		}

		// servicios
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReportePERFacturasPendientesSeccionForm ReportePERFacturasPendientesSeccionForm = (ReportePERFacturasPendientesSeccionForm) this.formReporte;
		ReportePERFacturasPendientesSeccionForm
				.setPais(this.mPantallaPrincipalBean.getCurrentCountry());
		// defecto
		ReportePERFacturasPendientesSeccionForm.reset();
		// asignacion

		this.siccMarcaList = service.getMarcas();

		this.siccCanalList = service
				.getCanalesByCodigoISO(this.mPantallaPrincipalBean
						.getCurrentIdioma().getCodigoISO());
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				ReportePERFacturasPendientesSeccionForm.getCodigoPais(),
				Constants.CODIGO_CANAL_DEFAULT);
		this.formReporte.setCodigoPeriodo(codigoPeriodo);

		ArrayList resultado = new ArrayList();
		Base[] mes = new Base[4];
		String[] presentaciones = { "Detalle de Facturas",
				"Resumen de Facturas", "Pedidos con Responsabilidad por Zona",
				"Pedidos con Responsabilidad por Región" };
		for (int i = 0; i < 4; i++) {
			mes[i] = new Base();
			mes[i].setCodigo("" + (i + 1));
			mes[i].setDescripcion(presentaciones[i]);
			resultado.add(mes[i]);
		}
		this.siccPresentacionList = resultado;

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
		return "reportes.reportePERFacturasPendientesSeccionService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReportePERFacturasPendientesSeccionForm form = (ReportePERFacturasPendientesSeccionForm) this.formReporte;
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
	 * Carga regiones
	 * 
	 * @return
	 */
	public LabelValue[] loadRegionMarcaCanal() {
		try {
			LabelValue[] regiones = null;
			ReportePERFacturasPendientesSeccionForm form = (ReportePERFacturasPendientesSeccionForm) this.formReporte;
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
			ReportePERFacturasPendientesSeccionForm form = (ReportePERFacturasPendientesSeccionForm) this.formReporte;
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
			ReportePERFacturasPendientesSeccionForm form = (ReportePERFacturasPendientesSeccionForm) this.formReporte;
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
			ReportePERFacturasPendientesSeccionForm form = (ReportePERFacturasPendientesSeccionForm) this.formReporte;
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
