/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCTotalPremiosFacturadosForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * The Class ReporteINCTotalPremFactsAction.
 * 
 * @author Belcorp
 * @version 1.0 11/11/2014
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteINCTotalPremFactsAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -8993017521745196993L;

	private String totalUnidadAdministrativa;

	private boolean whithFecha;

	private List siccConcursoList;

	private List siccUnidadAdminList;

	private Date fechaInicioFacturacionD;

	private Date fechaFinFacturacionD;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCTotalPremiosFacturadosForm form = new ReporteINCTotalPremiosFacturadosForm();
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
		ReporteINCTotalPremiosFacturadosForm form = (ReporteINCTotalPremiosFacturadosForm) this.formReporte;
		if (StringUtils.equals(form.getFormatoExportacion(), "PDF"))
			return "reporteMaestroHorizontal";
		else if (this.whithFecha) {
			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_SUB_GERENCIA)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosSubGerenciaFechaXLS";
			}

			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_REGION)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosRegionFechaXLS";
			}

			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_ZONA)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosZonaFechaXLS";
			}
		} else {
			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_SUB_GERENCIA)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosSubGerenciaXLS";
			}

			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_REGION)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosRegionXLS";
			}

			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_ZONA)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosZonaXLS";
			}

		}

		return "";
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

		if (this.whithFecha) {
			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_SUB_GERENCIA)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosSubGerenciaFechaPDF";
			}

			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_REGION)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosRegionFechaPDF";
			}

			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_ZONA)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosZonaFechaPDF";
			}
		} else {
			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_SUB_GERENCIA)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosSubGerenciaPDF";
			}

			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_REGION)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosRegionPDF";
			}

			if (this.totalUnidadAdministrativa
					.equals(Constants.TIPO_TOTAL_ZONA)) {
				return "reporteINCTotalPremiosAsignadosAndFacturadosZonaPDF";
			}

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
			log.debug("ReporteINCTotalPremiosFacturadosAction- prepareParameterMap...");
		}

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteINCTotalPremiosFacturadosForm form = (ReporteINCTotalPremiosFacturadosForm) this.formReporte;

		this.totalUnidadAdministrativa = form.getCodUnidAdim();
		// Obtener el Valor de la opcion Con fecha
		String conFecha = form.getWhithFecha();
		if (StringUtils.equals(conFecha, "1"))
			this.whithFecha = true;
		else
			this.whithFecha = false;

		String condicionConcurso = obtieneCondicion(form.getNumeroConcurso(),
				"d.NUM_CONC", "'");

		Map criteria = params;
		criteria.put("codigoPeriodo", form.getCodigoPeriodo());
		String oidPeriodo = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);

		String sufijoTitulo;
		String fechaInicio = "";
		String fechaFin = "";

		form.setFechaFinFacturacion(DateUtil.convertDateToString(this
				.getFechaFinFacturacionD()));
		form.setFechaInicioFacturacion(DateUtil.convertDateToString(this
				.getFechaInicioFacturacionD()));

		// Validar Fechas
		if (form.getFechaInicioFacturacion() != null)
			fechaInicio = form.getFechaInicioFacturacion();
		if (form.getFechaFinFacturacion() != null)
			fechaFin = form.getFechaFinFacturacion();

		if (form.getFechaInicioFacturacion() == null)
			if (form.getFechaFinFacturacion() == null)
				sufijoTitulo = "";
			else
				sufijoTitulo = this.mPantallaPrincipalBean
						.getResourceMessage("reporteINCTotalPremiosFacturadosForm.hasta")
						+ fechaFin;
		else if (form.getFechaFinFacturacion() != null)
			sufijoTitulo = this.mPantallaPrincipalBean
					.getResourceMessage("reporteINCTotalPremiosFacturadosForm.desde")
					+ fechaInicio + " al " + fechaFin;
		else
			sufijoTitulo = this.mPantallaPrincipalBean
					.getResourceMessage("reporteINCTotalPremiosFacturadosForm.desde")
					+ fechaInicio;

		params.put("fechaInicioFacturacion", form.getFechaInicioFacturacion());
		params.put("fechaFinFacturacion", form.getFechaFinFacturacion());
		params.put("condicion", condicionConcurso);
		params.put("oidPeriodo", oidPeriodo);

		form.setTitulo(this.mPantallaPrincipalBean
				.getResourceMessage("reporteINCTotalPremiosFacturadosForm.titulo")
				+ sufijoTitulo);
		params.put("titulo", form.getTitulo());

		return params;
	}

	/**
	 * Load fechas periodo.
	 */
	public void loadFechasPeriodo(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechasPeriodo...");
		}

		ReporteINCTotalPremiosFacturadosForm form = (ReporteINCTotalPremiosFacturadosForm) this.formReporte;
		this.setFechaInicioFacturacionD(null);
		this.setFechaFinFacturacionD(null);
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		try {
			String fechaInicio = ajaxService
					.getFechaInicioPeriodoByPaisMarcaCanal(
							form.getCodigoPais(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor);
			String fechaFin = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
					form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, valor);

			form.setCodigoPeriodo(valor);

			this.setFechaInicioFacturacionD(DateUtil
					.convertStringToDate(fechaInicio));

			this.setFechaFinFacturacionD(DateUtil.convertStringToDate(fechaFin));

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
			log.debug("ReporteINCTotalPremiosFacturadosAction - setViewAtributes");
		}
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		// Carga de los Periodos
		ReporteINCTotalPremiosFacturadosForm reporteINCForm = (ReporteINCTotalPremiosFacturadosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteriaOperacion = new HashMap();

		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		String periodoActual = reporteService.getStringGenerico(
				"getPeriodoByFechaActual", criteriaOperacion);
		setSiccConcursoList(reporteService.getListaGenerico(
				"getConcursosByPaisMarcaCanalDetalle", criteriaOperacion));

		// Seteo de lista unidadesAdministrativas
		ArrayList<Base> unidadesAdmin = new ArrayList<Base>();
		Base[] baseUA = new Base[3];
		baseUA[0] = new Base();
		baseUA[0].setCodigo("R");
		baseUA[0].setDescripcion(this.mPantallaPrincipalBean
				.getResourceMessage("select.porRegion"));
		baseUA[1] = new Base();
		baseUA[1].setCodigo("Z");
		baseUA[1].setDescripcion(this.mPantallaPrincipalBean
				.getResourceMessage("select.porZona"));
		baseUA[2] = new Base();
		baseUA[2].setCodigo("S");
		baseUA[2].setDescripcion(this.mPantallaPrincipalBean
				.getResourceMessage("select.porSubGerencia"));
		for (int i = 0; i < 3; i++) {
			unidadesAdmin.add(baseUA[i]);
		}
		setSiccUnidadAdminList(unidadesAdmin);

		criteriaOperacion.put("codigoPeriodo", periodoActual);
		List aux = reporteService.getListaGenerico(
				"getFechaInicioPeriodoByPaisMarcaCanal", criteriaOperacion);
		List auxF = reporteService.getListaGenerico(
				"getFechaFinalPeriodoByPaisMarcaCanal", criteriaOperacion);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicio = "";
		String fechaFin = "";

		if (aux.size() > 0) {
			Base base = (Base) aux.get(0);
			fechaInicio = base.getCodigo();
		} else
			fechaInicio = sdf.format(new Date(System.currentTimeMillis()));

		if (auxF.size() > 0) {
			Base base = (Base) auxF.get(0);
			fechaFin = base.getCodigo();
		} else
			fechaFin = sdf.format(new Date(System.currentTimeMillis()));

		// fechaFin = sdf.format(new Date(System.currentTimeMillis() - 1));

		reporteINCForm.setCodigoPais(pais.getCodigo());
		reporteINCForm.setDescPais(pais.getDescripcion());
		reporteINCForm.setCodigoPeriodo(periodoActual);
		this.setFechaInicioFacturacionD(sdf.parse(fechaInicio));// FechaInicio
																// Date
		this.setFechaFinFacturacionD(sdf.parse(fechaFin));// FechaFin
															// Date
		reporteINCForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		reporteINCForm.setWhithFecha("1");
		this.whithFecha = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		ReporteINCTotalPremiosFacturadosForm form = (ReporteINCTotalPremiosFacturadosForm) this.formReporte;
		form.setFechaFinFacturacion(DateUtil.convertDateToString(this.fechaFinFacturacionD));
		form.setFechaInicioFacturacion(DateUtil.convertDateToString(this.fechaInicioFacturacionD));
		
		
		if (form.getNumeroConcurso().length <=0  ) {
			return "El numero de concurso es requerido";
		}

		if (form.getFechaInicioFacturacion() != null && form.getFechaFinFacturacion() != null) {
			if(DateUtil.compareDates(form.getFechaInicioFacturacion(), form.getFechaFinFacturacion(), "dd/MM/yyyy") > 0)
			{
				return this .getResourceMessage("errors.compare.dates");
			}
		}

		return null;
	}

	/**
	 * Gets the sicc concurso list.
	 * 
	 * @return the sicc concurso list
	 */
	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	/**
	 * Sets the sicc concurso list.
	 * 
	 * @param siccConcursoList
	 *            the new sicc concurso list
	 */
	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}

	/**
	 * Gets the total unidad administrativa.
	 * 
	 * @return the total unidad administrativa
	 */
	public String getTotalUnidadAdministrativa() {
		return totalUnidadAdministrativa;
	}

	/**
	 * Sets the total unidad administrativa.
	 * 
	 * @param totalUnidadAdministrativa
	 *            the new total unidad administrativa
	 */
	public void setTotalUnidadAdministrativa(String totalUnidadAdministrativa) {
		this.totalUnidadAdministrativa = totalUnidadAdministrativa;
	}

	public List getSiccUnidadAdminList() {
		return siccUnidadAdminList;
	}

	/**
	 * Sets the sicc unidad admin list.
	 * 
	 * @param siccUnidadAdminList
	 *            the new sicc unidad admin list
	 */
	public void setSiccUnidadAdminList(List siccUnidadAdminList) {
		this.siccUnidadAdminList = siccUnidadAdminList;
	}

	public boolean isWhithFecha() {
		return whithFecha;
	}

	public void setWhithFecha(boolean whithFecha) {
		this.whithFecha = whithFecha;
	}

	/**
	 * @return the fechaInicioFacturacionD
	 */
	public Date getFechaInicioFacturacionD() {
		return fechaInicioFacturacionD;
	}

	/**
	 * @param fechaInicioFacturacionD
	 *            the fechaInicioFacturacionD to set
	 */
	public void setFechaInicioFacturacionD(Date fechaInicioFacturacionD) {
		this.fechaInicioFacturacionD = fechaInicioFacturacionD;
	}

	/**
	 * @return the fechaFinFacturacionD
	 */
	public Date getFechaFinFacturacionD() {
		return fechaFinFacturacionD;
	}

	/**
	 * @param fechaFinFacturacionD
	 *            the fechaFinFacturacionD to set
	 */
	public void setFechaFinFacturacionD(Date fechaFinFacturacionD) {
		this.fechaFinFacturacionD = fechaFinFacturacionD;
	}

}