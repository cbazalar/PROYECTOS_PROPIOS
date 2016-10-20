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
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteMAEConsejerasBloqueadasDesbloqueadasForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteMAEConsejerasBloqueadasDesbloqueadasAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3357362891001461915L;
	private String formatoReporte;
	private List maeHorasList;
	private List siccRegionList;
	private List maeTiposReportesList;
	private LabelValue[] siccZonaList = {};
	private Boolean wbloqueo;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAEConsejerasBloqueadasDesbloqueadasForm reporteForm = new ReporteMAEConsejerasBloqueadasDesbloqueadasForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteMAEConsejerasBloqueadasDesbloqueadasAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		this.mostrarReporteOCSV = true;

		Map criteriaPeriodo = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		
		ReporteMAEConsejerasBloqueadasDesbloqueadasForm f = (ReporteMAEConsejerasBloqueadasDesbloqueadasForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoSTOLevantamientoErroresValidacionService procesoSTOLevantamientoErroresValidacionService = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		criteriaPeriodo.put("codigoParametro",
				Constants.STO_INTERVALO_CARGA_STO);
		List listaHorasCarga = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteriaPeriodo);

		this.maeHorasList = listaHorasCarga;
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaPeriodo);
		
		this.maeTiposReportesList = reporteService.getTipoReporteList();

		f.setCodigoPais(pais.getCodigo());
		f.setMostrarBotonExcel(this.esVisibleBotonExcel(pais.getCodigo()));
		this.wbloqueo = false;
		this.siccZonaList=ajaxService.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(),Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, f.getRegionList(),"T");

		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteMAEConsejerasBloqueadasDesbloqueadasService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteMAEConsejerasBloqueadasDesbloqueadasForm form = (ReporteMAEConsejerasBloqueadasDesbloqueadasForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteMAEConsultorasBloqDesbloqueadasXLS";
		else
			return "";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	protected String obtieneCondicionFechas(String fechaInicio,
			String horaInicio, String fechaFin, String horaFin, String estado,
			String comilla) {
		String resultado = "";
		if (fechaInicio == null && fechaFin == null) {
			return "";
		} else if (fechaInicio != null && fechaFin == null) {
			if (estado.equals("B")) {
				resultado = " AND B.FEC_BLOQ>=NVL(TO_DATE(" + comilla
						+ fechaInicio + comilla + ",'DD/MM/YYYY'),B.FEC_BLOQ) ";
			} else if (estado.equals("D")) {
				resultado = " AND B.FEC_DESB>=NVL(TO_DATE(" + comilla
						+ fechaInicio + comilla + ",'DD/MM/YYYY'),B.FEC_DESB) ";
			} else {
				resultado = " AND (B.FEC_BLOQ>=NVL(TO_DATE(" + comilla
						+ fechaInicio + comilla
						+ ",'DD/MM/YYYY'),B.FEC_BLOQ)) OR "
						+ "     (B.FEC_DESB>=NVL(TO_DATE(" + comilla
						+ fechaInicio + comilla
						+ ",'DD/MM/YYYY'),B.FEC_DESB)) ";
			}
			return resultado;
		} else if (fechaInicio == null && fechaFin != null) {
			if (estado.equals("B")) {
				resultado = " AND B.FEC_BLOQ<=NVL(TO_DATE(" + comilla
						+ fechaFin + comilla + ",'DD/MM/YYYY'),B.FEC_BLOQ)";
			} else if (estado.equals("D")) {
				resultado = " AND B.FEC_DESB<=NVL(TO_DATE(" + comilla
						+ fechaFin + comilla + ",'DD/MM/YYYY'),B.FEC_DESB)";
			} else {
				resultado = " AND (B.FEC_BLOQ<=NVL(TO_DATE(" + comilla
						+ fechaFin + comilla
						+ ",'DD/MM/YYYY'),B.FEC_BLOQ)) OR "
						+ "     (B.FEC_DESB<=NVL(TO_DATE(" + comilla + fechaFin
						+ comilla + ",'DD/MM/YYYY'),B.FEC_DESB)) ";
			}
			return resultado;
		} else if (fechaInicio != null && fechaFin != null) {
			if (estado.equals("B")) {
				if (StringUtils.isNotEmpty(horaInicio))
					resultado = " AND B.FEC_BLOQ>=NVL(TO_DATE(" + comilla
							+ fechaInicio + " " + horaInicio + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_BLOQ)";
				else
					resultado = " AND B.FEC_BLOQ>=NVL(TO_DATE(" + comilla
							+ fechaInicio + comilla
							+ ",'DD/MM/YYYY'),B.FEC_BLOQ)";

				if (StringUtils.isNotEmpty(horaFin))
					resultado = resultado + " AND B.FEC_BLOQ<=NVL(TO_DATE("
							+ comilla + fechaFin + " " + horaFin + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_BLOQ)";
				else
					resultado = resultado + " AND B.FEC_BLOQ<=NVL(TO_DATE("
							+ comilla + fechaFin + comilla
							+ ",'DD/MM/YYYY'),B.FEC_BLOQ)";
			} else if (estado.equals("D")) {
				if (StringUtils.isNotEmpty(horaInicio))
					resultado = " AND B.FEC_DESB>=NVL(TO_DATE(" + comilla
							+ fechaInicio + " " + horaInicio + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_DESB)";
				else
					resultado = " AND B.FEC_DESB>=NVL(TO_DATE(" + comilla
							+ fechaInicio + comilla
							+ ",'DD/MM/YYYY'),B.FEC_DESB)";

				if (StringUtils.isNotEmpty(horaFin))
					resultado = resultado + " AND B.FEC_DESB<=NVL(TO_DATE("
							+ comilla + fechaFin + " " + horaFin + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_DESB)";
				else
					resultado = resultado + " AND B.FEC_DESB<=NVL(TO_DATE("
							+ comilla + fechaFin + comilla
							+ ",'DD/MM/YYYY'),B.FEC_DESB)";
			} else {

				if (StringUtils.isNotEmpty(horaInicio)
						&& StringUtils.isEmpty(horaFin))
					resultado = " AND ( ( B.FEC_BLOQ>=NVL(TO_DATE(" + comilla
							+ fechaInicio + " " + horaInicio + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_BLOQ) "
							+ " AND B.FEC_BLOQ<=NVL(TO_DATE(" + comilla
							+ fechaFin + comilla
							+ ",'DD/MM/YYYY'),B.FEC_BLOQ)) OR "
							+ " ( B.FEC_DESB>=NVL(TO_DATE(" + comilla
							+ fechaInicio + " " + horaInicio + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_DESB) "
							+ " AND FEC_DESB<=NVL(TO_DATE(" + comilla
							+ fechaFin + comilla + ",'DD/MM/YYYY'),FEC_DESB)))";

				if (StringUtils.isEmpty(horaInicio)
						&& StringUtils.isNotEmpty(horaFin))
					resultado = " AND ( ( B.FEC_BLOQ>=NVL(TO_DATE(" + comilla
							+ fechaInicio + comilla
							+ ",'DD/MM/YYYY'),B.FEC_BLOQ) "
							+ " AND B.FEC_BLOQ<=NVL(TO_DATE(" + comilla
							+ fechaFin + " " + horaFin + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_BLOQ)) OR "
							+ " ( B.FEC_DESB>=NVL(TO_DATE(" + comilla
							+ fechaInicio + comilla
							+ ",'DD/MM/YYYY'),B.FEC_DESB) "
							+ " AND FEC_DESB<=NVL(TO_DATE(" + comilla
							+ fechaFin + " " + horaFin + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_DESB)))";

				if (StringUtils.isNotEmpty(horaInicio)
						&& StringUtils.isNotEmpty(horaFin))
					resultado = " AND ( ( B.FEC_BLOQ>=NVL(TO_DATE(" + comilla
							+ fechaInicio + " " + horaInicio + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_BLOQ) "
							+ " AND B.FEC_BLOQ<=NVL(TO_DATE(" + comilla
							+ fechaFin + " " + horaFin + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_BLOQ)) OR "
							+ " ( B.FEC_DESB>=NVL(TO_DATE(" + comilla
							+ fechaInicio + " " + horaInicio + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_DESB) "
							+ " AND FEC_DESB<=NVL(TO_DATE(" + comilla
							+ fechaFin + " " + horaFin + comilla
							+ ",'DD/MM/YYYY HH24:MI'),B.FEC_DESB)))";

				if (StringUtils.isEmpty(horaInicio)
						&& StringUtils.isEmpty(horaFin))
					resultado = " AND ( ( B.FEC_BLOQ>=NVL(TO_DATE(" + comilla
							+ fechaInicio + comilla
							+ ",'DD/MM/YYYY'),B.FEC_BLOQ) "
							+ " AND B.FEC_BLOQ<=NVL(TO_DATE(" + comilla
							+ fechaFin + comilla
							+ ",'DD/MM/YYYY'),B.FEC_BLOQ)) OR "
							+ " ( B.FEC_DESB>=NVL(TO_DATE(" + comilla
							+ fechaInicio + comilla
							+ ",'DD/MM/YYYY'),B.FEC_DESB) "
							+ " AND FEC_DESB<=NVL(TO_DATE(" + comilla
							+ fechaFin + comilla + ",'DD/MM/YYYY'),FEC_DESB)))";
			}
		}

		return resultado;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteMAEConsejerasBloqueadasDesbloqueadasForm reporteSICForm = (ReporteMAEConsejerasBloqueadasDesbloqueadasForm) this.formReporte;
		formatoReporte = reporteSICForm.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String indicadorBloqueo = reporteSICForm.getIndicadorBloqueo();
		if (StringUtils.equalsIgnoreCase(indicadorBloqueo, "true")) {
			indicadorBloqueo = "S";
		} else {
			indicadorBloqueo = "";
		}
		String fechaIni = "";
		String fechaFin = "";
		
		fechaIni = DateUtil.convertDateToString(reporteSICForm.getFechaInicioD());
		fechaFin = DateUtil.convertDateToString(reporteSICForm.getFechaFinD());
		reporteSICForm.setFechaInicio(fechaIni);
		reporteSICForm.setFechaFin(fechaFin);
	

		if (StringUtils.isBlank(indicadorBloqueo))
			indicadorBloqueo = "";
		String condicionRegion = obtieneCondicion(
				reporteSICForm.getRegionList(), "zr.cod_regi", "'");
		String condicionZonas = obtieneCondicion(reporteSICForm.getZonaList(),
				"zz.cod_zona", "'");
		String condicionFechas = obtieneCondicionFechas(fechaIni,
				reporteSICForm.getHoraInicio(), fechaFin, reporteSICForm.getHoraFin(),
				reporteSICForm.getCodigoEstado(), "'");

		params.put("codigoPeriodoInicial", reporteSICForm.getCodigoPeriodoInicial());
		params.put("codigoPeriodoFinal", reporteSICForm.getCodigoPeriodoFinal());
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZonas", condicionZonas);
		params.put("condicionFechas", condicionFechas);
		params.put("codigoTipoReporte", reporteSICForm.getCodigoTipoReporte());
		params.put("formatoExportacion", formatoReporte);

		String descripcionTipoReporte = "";
		if (reporteSICForm.getCodigoTipoReporte().equals(Constants.TODAS)) {
			descripcionTipoReporte = "Todas";
		} else {
			Map criteria = new HashMap();
			criteria.put("codigoTipoReporte",
					reporteSICForm.getCodigoTipoReporte());
			List listaDescripcion = reporteService.getTipoReporteList(criteria);
			if (listaDescripcion != null && listaDescripcion.size() > 0) {
				Base base = (Base) listaDescripcion.get(0);
				descripcionTipoReporte = base.getDescripcion();
			}
		}
		params.put("descripcionTipoReporte", descripcionTipoReporte);
		String condicionBloqueo = "";
		String codigoEstado = reporteSICForm.getCodigoEstado();
		if (StringUtils.equals(codigoEstado, "B") && StringUtils.equals(indicadorBloqueo, Constants.SI))
			condicionBloqueo = " AND B.FEC_DESB IS NULL";
		if (StringUtils.equals(codigoEstado, "D"))
			condicionBloqueo = " AND B.FEC_DESB IS NOT NULL";

		params.put("condicionBloqueo", condicionBloqueo);

		params.put("NroReporte", "");
		params.put(
				"titulo",
				getResourceMessage("reporteMAEConsejerasBloqueadasDesbloqueadasForm.title"));

		log.info("Salio a ReporteMAEConsejerasBloqueadasDesbloqueadasAction - prepareParameterMap");
		return params;
	}

	public String setValidarReporte() {
		ReporteMAEConsejerasBloqueadasDesbloqueadasForm form = (ReporteMAEConsejerasBloqueadasDesbloqueadasForm) this.formReporte;

		String codPerIni = form.getCodigoPeriodoInicial();
		String codPerFin = form.getCodigoPeriodoFinal();
		if (codPerIni.length() > 0 && codPerFin.length() == 0) {
			String mensaje = this
					.getResourceMessage("reporteMAEConsejerasBloqueadasDesbloqueadasForm.msg.codigoPeriodoFinal");
			return mensaje;

		}
		if (codPerIni.length() == 0 && codPerFin.length() > 0) {
			String mensaje = this
					.getResourceMessage("reporteMAEConsejerasBloqueadasDesbloqueadasForm.msg.codigoPeriodoInicial");
			return mensaje;

		}

		if (form.getFechaInicioD() != null && form.getFechaFinD() == null) {
			String mensaje = this
					.getResourceMessage("reporteMAEConsejerasBloqueadasDesbloqueadasForm.msg.fechaFin");
			return mensaje;
		}
		
		if (form.getFechaInicioD() == null && form.getFechaFinD() != null) {
			String mensaje = this
					.getResourceMessage("reporteMAEConsejerasBloqueadasDesbloqueadasForm.msg.fechaInicio");
			return mensaje;
		}


		return null;
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		ReporteMAEConsejerasBloqueadasDesbloqueadasForm form = (ReporteMAEConsejerasBloqueadasDesbloqueadasForm) this.formReporte;
		String[] valores = (String[]) val.getNewValue();
		if (valores.length > 0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.siccZonaList=ajaxService.getZonasMultipleByPaisMarcaCanalRegion(
					form.getCodigoPais(),Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, valores,"T");
					

		} else {
			setSiccZonaList(null);
		}
	}

	/**
	 * Metodo para ocultar bloqueo
	 * 
	 * @param val
	 */
	public void loadBloqueo(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadBloqueo");
		}
		ReporteMAEConsejerasBloqueadasDesbloqueadasForm reporteSICForm = (ReporteMAEConsejerasBloqueadasDesbloqueadasForm) this.formReporte;

		String valor = (String) val.getNewValue();
		if (valor.equals("B")) {
			setWbloqueo(true);

		} else {
			setWbloqueo(false);
			reporteSICForm.setIndicadorBloqueo(null);

		}
	}

	private boolean esVisibleBotonExcel(String codigoPais) {
		GenericoService genericoService1 = (GenericoService) getBean("genericoService");

		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(codigoPais);
		parametroPais1.setCodigoSistema(Constants.SISTEMA_GEN);
		parametroPais1.setNombreParametro("mostrarBotonReporteXLS");
		parametroPais1.setIndicadorActivo("1");

		List lstParametros1 = genericoService1
				.getParametrosPais(parametroPais1);
		boolean activo = false;

		if (lstParametros1 != null && lstParametros1.size() > 0) {
			activo = true;
		}

		return activo;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the maeHorasList
	 */
	public List getMaeHorasList() {
		return maeHorasList;
	}

	/**
	 * @param maeHorasList
	 *            the maeHorasList to set
	 */
	public void setMaeHorasList(List maeHorasList) {
		this.maeHorasList = maeHorasList;
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
	 * @return the maeTiposReportesList
	 */
	public List getMaeTiposReportesList() {
		return maeTiposReportesList;
	}

	/**
	 * @param maeTiposReportesList
	 *            the maeTiposReportesList to set
	 */
	public void setMaeTiposReportesList(List maeTiposReportesList) {
		this.maeTiposReportesList = maeTiposReportesList;
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
	 * @return the wbloqueo
	 */
	public Boolean getWbloqueo() {
		return wbloqueo;
	}

	/**
	 * @param wbloqueo
	 *            the wbloqueo to set
	 */
	public void setWbloqueo(Boolean wbloqueo) {
		this.wbloqueo = wbloqueo;
	}

}
