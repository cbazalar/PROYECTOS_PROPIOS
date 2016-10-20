package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteRECSolicitudesPendientesAtencionForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECSolicitudesPendientesAtencionAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	private List siccOperacionesList;
	private LabelValue[] siccTipoOperacionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccTerritorioList;
	private List siccGrupoProceso;
	private String codigoIdiomaISO;
	private List siccRegionList;
	private List siccMarcaList;
	private List siccUnidadNegocioList;
	private List siccEstadoOperacionList;
	private List siccReclamoList;
	private List siccNegocioList;

	/**
	 * @return
	 */
	public List getSiccGrupoProceso() {
		return siccGrupoProceso;
	}

	/**
	 * @param siccGrupoProceso
	 */
	public void setSiccGrupoProceso(List siccGrupoProceso) {
		this.siccGrupoProceso = siccGrupoProceso;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	/**
	 * @param siccTipoOperacionList
	 */
	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
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
	public List getSiccUnidadNegocioList() {
		return siccUnidadNegocioList;
	}

	/**
	 * @param siccUnidadNegocioList
	 */
	public void setSiccUnidadNegocioList(List siccUnidadNegocioList) {
		this.siccUnidadNegocioList = siccUnidadNegocioList;
	}

	/**
	 * @return
	 */
	public List getSiccEstadoOperacionList() {
		return siccEstadoOperacionList;
	}

	/**
	 * @param siccEstadoOperacionList
	 */
	public void setSiccEstadoOperacionList(List siccEstadoOperacionList) {
		this.siccEstadoOperacionList = siccEstadoOperacionList;
	}

	/**
	 * @return
	 */
	public List getSiccReclamoList() {
		return siccReclamoList;
	}

	/**
	 * @param siccReclamoList
	 */
	public void setSiccReclamoList(List siccReclamoList) {
		this.siccReclamoList = siccReclamoList;
	}

	/**
	 * @return
	 */
	public List getSiccNegocioList() {
		return siccNegocioList;
	}

	/**
	 * @param siccNegocioList
	 */
	public void setSiccNegocioList(List siccNegocioList) {
		this.siccNegocioList = siccNegocioList;
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

	private Boolean habilitarOperacion = false;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECSolicitudesPendientesAtencionForm form = new ReporteRECSolicitudesPendientesAtencionForm();
		return form;
	}

	/**
	 * @param form
	 * @return
	 */
	public String setValidarReporte(
			ReporteRECSolicitudesPendientesAtencionForm form) {
		if (form.getCodigoPeriodoInicio().compareTo(form.getCodigoPeriodoFin()) == 0)
			return "Usted debe de ingresar una fecha diferente";

		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteRECSolicitudesPendientesAtencionForm form = (ReporteRECSolicitudesPendientesAtencionForm) this.formReporte;

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		// ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(codigoPais,
		// codigoMarca, codigoCanal, codigoPeriodo);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
		this.mostrarReporteXLS = true;
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccMarcaList = reporteService.getListaGenerico("getMarcaProdu",
				criteriaOperacion);
		this.siccUnidadNegocioList = reporteService.getListaGenerico(
				"getListaUnidadNegocio", criteriaOperacion);
		this.siccEstadoOperacionList = reporteService.getListaGenerico(
				"getListaEstadoOperacion", criteriaOperacion);
		this.siccReclamoList = reporteService.getListaGenerico(
				"getListaEstadoReclamo", criteriaOperacion);
		this.siccNegocioList = reporteService.getListaGenerico("getListaNegocio",
				criteriaOperacion);
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccOperacionesList = interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);

		this.siccTipoOperacionList = null;
		this.siccZonaList = null;
		this.siccTerritorioList = null;
		this.siccGrupoProceso = reporteService.getListaGenerico("getGrupoProceso2",
				criteriaOperacion);
		this.setCodigoIdiomaISO(this.mPantallaPrincipalBean.getOidIdiomaIso());

		/* colocando periodos */
		String codigoPeriodo = interfazSiCCService
				.getPeriodoDefaultByPaisCanal(form.getCodigoPais(),
						Constants.CODIGO_CANAL_DEFAULT);
		form.setCodigoPeriodoInicio(codigoPeriodo);
		form.setCodigoPeriodoFin(codigoPeriodo);

		/* colocando fechas */
		// DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		// Date startDate = df.parse(startDate);

		String date = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(
				form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo);
		Date fecha = DateUtil.convertStringToDate(date);
		form.setFechaFacturacionInicioD(fecha);
		date = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
				form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, codigoPeriodo);
		fecha = DateUtil.convertStringToDate(date);
		form.setFechaFacturacionFinD(fecha);
	}

	/**
	 * Desabilita la opcion Operacion
	 */
	public void desahabilitarOperacion(ValueChangeEvent val) {
		try {
			log.info("el valor es ");
			log.info("val " + val.getNewValue().toString());
			String obj = val.getNewValue().toString();
			if (obj.equals("1")) {
				this.setHabilitarOperacion(true);
			} else {
				this.setHabilitarOperacion(false);
			}
			log.info(this.getHabilitarOperacion().toString());
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
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

		if ("XLS0".equals(this.formatoReporte))
			return "reporteRECSolicPendAteRecXLS";
		else if ("XLS1".equals(this.formatoReporte))
			return "reporteRECSolicPendAteIncXLS";
		else if ("XLS2".equals(this.formatoReporte))
			return "reporteRECSolicPendAteFalXLS";
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
	@Override
	protected String devuelveNombreSubReporte() throws Exception {

		if ("PDF0".equals(this.formatoReporte))
			return "reporteRECSolicPendAteRecPDF";
		else if ("PDF1".equals(this.formatoReporte))
			return "reporteRECSolicPendAteIncPDF";
		else
			return "reporteRECSolicPendAteFalPDF";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		Date dtFechaInicioPeriodoInicial = null;
		Date dtFechaFinalPeriodoInicial = null;
		Date dtFechaInicioPeriodoFinal = null;
		Date dtFechaFinalPeriodoFinal = null;
		
		ReporteRECSolicitudesPendientesAtencionForm form = (ReporteRECSolicitudesPendientesAtencionForm) this.formReporte;
		Integer fecha1, fecha2;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicio());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFin());

		if (fecha1 > fecha2) {
			String mensaje = "El periodo inicial no puede ser mayor al final";
			return mensaje;
		}

		String vFechaInicio = DateUtil.getDate(form
				.getFechaFacturacionInicioD());
		String vFechaFin = DateUtil.getDate(form.getFechaFacturacionFinD());
		if (!vFechaInicio.isEmpty() && !vFechaFin.isEmpty()) {
			if (form.getFechaFacturacionInicioD().compareTo(
					form.getFechaFacturacionFinD()) > 0) {
				String mensaje = "La fecha Inicial no puede ser mayor a la final";
				return mensaje;
			}
		}

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");

		String fechaInicioPeriodoInicial = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(
						form.getCodigoPais(),
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT,
						form.getCodigoPeriodoInicio());
						
		String fechaFinalPeriodoInicial = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
						form.getCodigoPais(),
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT,
						form.getCodigoPeriodoInicio());
		
		
		String fechaInicioPeriodoFinal = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(
						form.getCodigoPais(),
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT,
						form.getCodigoPeriodoFin());
		
		String fechaFinalPeriodoFinal= ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
						form.getCodigoPais(),
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT,
						form.getCodigoPeriodoFin());

		
		try {
			dtFechaInicioPeriodoInicial = DateUtil.convertStringToDate(fechaInicioPeriodoInicial);
			dtFechaFinalPeriodoInicial = DateUtil.convertStringToDate(fechaFinalPeriodoInicial);
			dtFechaInicioPeriodoFinal = DateUtil.convertStringToDate(fechaInicioPeriodoFinal);
			dtFechaFinalPeriodoFinal = DateUtil.convertStringToDate(fechaFinalPeriodoFinal);
		} catch (ParseException e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
		if(form.getFechaFacturacionInicioD()!=null){
			Integer resultado = form.getFechaFacturacionInicioD().compareTo(dtFechaFinalPeriodoInicial);
			Integer resultado1 = dtFechaInicioPeriodoInicial.compareTo(form.getFechaFacturacionInicioD());

			if(resultado.intValue()>0 || resultado1.intValue()>0){
				String mensaje = "La fecha inicial de facturacion debe de encontrarse en los siguientes intervalos "
								+DateUtil.getDate(dtFechaInicioPeriodoInicial)+" - "+DateUtil.getDate(dtFechaFinalPeriodoInicial);
				return mensaje;
			}
		}
		
		if(form.getFechaFacturacionFinD()!=null){
			Integer resultado2 = form.getFechaFacturacionFinD().compareTo(dtFechaFinalPeriodoFinal);
			Integer resultado3 = dtFechaInicioPeriodoFinal.compareTo(form.getFechaFacturacionFinD());
			
			if(resultado2.intValue()>0 || resultado3.intValue()>0){
				String mensaje = "La fecha final de facturacion debe de encontrarse en los siguientes intervalos "
						+DateUtil.getDate(dtFechaInicioPeriodoFinal)+" - "+DateUtil.getDate(dtFechaFinalPeriodoFinal);
				return mensaje;
			}
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
		log.debug(">>ENTRO A prepareParameterMap ");

		ReporteRECSolicitudesPendientesAtencionForm reporteRECForm = (ReporteRECSolicitudesPendientesAtencionForm) this.formReporte;
		this.formatoReporte = reporteRECForm.getFormatoExportacion()
				+ reporteRECForm.getTipoReporte();

		reporteRECForm.setFechaFacturacionInicio(DateUtil
				.getDate(reporteRECForm.getFechaFacturacionInicioD()));
		reporteRECForm.setFechaFacturacionFin(DateUtil.getDate(reporteRECForm
				.getFechaFacturacionFinD()));
		String condicionZonas = obtieneCondicion(reporteRECForm.getZonaList(),
				"ZON.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(
				reporteRECForm.getRegionList(), "R.COD_REGI", "'");
		String condicionGrupo = obtieneCondicion(
				reporteRECForm.getGrupoProcesoList(), "ped.COD_GRUP_PROC", "'");
		String condicionOperacion = obtieneCondicion(
				reporteRECForm.getOperacionList(), "op.COD_OPER", "'");

		String condicionTerritorio = obtieneCondicion(
				reporteRECForm.getTerritorioList(), "TE.COD_TERR", "'");
		String condicion = condicionZonas + condicionRegion
				+ condicionTerritorio + condicionGrupo;

		if ("PDF0".equals(formatoReporte) || "XLS0".equals(formatoReporte))
			condicion = condicion + condicionOperacion;

		ClassPathResource resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "subReporteRECSolicPendAtenRec" + JASPER_EXTENSION);
		
		
		params.put("SUBREPORT_DIR1",
					(JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
		
		String titulo;
		if (StringUtils.equals(reporteRECForm.getTipoReporte(), "0"))
			titulo = getReportResourceMessage("reporteRECSolicitudesPendientesAtencionForm.titulo.pendientes");
		else if (StringUtils.equals(reporteRECForm.getTipoReporte(), "1"))
			titulo = getReportResourceMessage("reporteRECSolicitudesPendientesAtencionForm.titulo.incentivos");
		else
			titulo = getReportResourceMessage("reporteRECSolicitudesPendientesAtencionForm.titulo.bolsaFaltantes");
		params.put("condicion", condicion);
		params.put(
				"titulo",
				getReportResourceMessage("reporteRECSolicitudesPendientesAtencionForm.titulo")
						+ titulo);

		// Se agregan como parametros del reporte de Reclamos el periodo de
		// inicio y de fin
		Map criteria = params;
		ReporteService reporteService = (ReporteService) this
				.getBean("scsicc.reporteService");

		criteria.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoInicio());
		String oidPeriodoInicio = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPeriodoInicio", oidPeriodoInicio);
		criteria.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoFin());
		String oidPeriodoFin = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPeriodoFin", oidPeriodoFin);
		return params;
	}

	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegions ");
		try {
			ReporteRECSolicitudesPendientesAtencionForm form = (ReporteRECSolicitudesPendientesAtencionForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			// Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
						form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, regiones,
						Constants.FORMATO_TOTAL));
				form.setZonaList(null);
			} else {
				this.siccZonaList = null;
				form.setZonaList(null);
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * @param val
	 */
	public void showTerritorioxZona(ValueChangeEvent val) {
		log.debug(">>showTerritorioxZona ");
		try {
			ReporteRECSolicitudesPendientesAtencionForm form = (ReporteRECSolicitudesPendientesAtencionForm) this.formReporte;

			String[] regiones = (String[]) form.getRegionList();

			String[] zonas = (String[]) val.getNewValue();
			if (!val.equals(null) && zonas.length > 0) {

				List<String> listaRegiones = new ArrayList<String>(
						Arrays.asList(regiones));

				List<String> listaZonas = new ArrayList<String>(
						Arrays.asList(zonas));

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccTerritorioList(aSvc
						.getTerritoriosMultipleByPaisMarcaCanalRegionZona(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, listaRegiones,
								listaZonas, Constants.FORMATO_TOTAL));

				form.setTerritorioList(null);
			} else {
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
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
	 * @return
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
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
	 * @return
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the habilitarOperacion
	 */
	public Boolean getHabilitarOperacion() {
		return habilitarOperacion;
	}

	/**
	 * @param habilitarOperacion
	 *            the habilitarOperacion to set
	 */
	public void setHabilitarOperacion(Boolean habilitarOperacion) {
		this.habilitarOperacion = habilitarOperacion;
	}

}