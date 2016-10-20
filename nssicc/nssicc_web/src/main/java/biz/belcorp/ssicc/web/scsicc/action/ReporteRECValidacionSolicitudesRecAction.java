package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECValidacionSolicitudesRecForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECValidacionSolicitudesRecAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 6995372400433404750L;

	private String formatoReporte;

	private String reporteSufijo;
	private List siccUserList = new ArrayList();
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccTerritorioList = {};
	private List siccOperacionesList = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// Carga de los combos User
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccUserList = reporteService.getListaGenerico("getAutor",
				criteriaOperacion);

		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}
		this.siccOperacionesList = interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);

		ReporteRECValidacionSolicitudesRecForm formREC = (ReporteRECValidacionSolicitudesRecForm) this.formReporte;
		formREC.setCodigoPais(pais.getCodigo());

		formREC.setFechaValidacionD(new Date(System.currentTimeMillis()));

		formREC.setFechaValidacionFinalD(new Date(System.currentTimeMillis()));

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
		formREC.setCodigoPeriodo(sdf1.format(new Date(System
				.currentTimeMillis())));

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
		ReporteRECValidacionSolicitudesRecForm form = new ReporteRECValidacionSolicitudesRecForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteRECValidacionSolicitudesRecForm form = (ReporteRECValidacionSolicitudesRecForm) this.formReporte;
		Date fecha1D, fecha2D;
		fecha1D = form.getFechaValidacionD();
		fecha2D = form.getFechaValidacionFinalD();

		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this
					.getResourceMessage("reporteRECValidacionSolicitudesRecForm.errors.compare.dates");
			return mensaje;
		}

		return null;
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
		if ("XLS".equals(formatoReporte))
			return "reporteRECValidacionSolicitudes" + this.reporteSufijo;
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
		return "reporteRECValidacionSolicitudes" + this.reporteSufijo;
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
		ReporteRECValidacionSolicitudesRecForm reporteRECForm = (ReporteRECValidacionSolicitudesRecForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		String fecha1, fecha2;
		fecha1 = DateUtil.getDate(reporteRECForm.getFechaValidacionD());
		fecha2 = DateUtil.getDate(reporteRECForm.getFechaValidacionFinalD());

		reporteRECForm.setFechaValidacion(fecha1);
		reporteRECForm.setFechaValidacionFinal(fecha2);

		String descripcionRegionList = descripcionMultipleLista(
				reporteRECForm.getRegionList(), this.siccRegionList);
		String descripcionZonaList = descripcionMultipleLista(
				reporteRECForm.getZonaList(), this.siccZonaList);
		String descripcionTerritorioList = descripcionMultipleLista(
				reporteRECForm.getTerritorioList(), this.siccTerritorioList);
		String descripcionUserList = descripcionMultipleLista(
				reporteRECForm.getUserList(), this.siccUserList);
		String descripcionOperacionList = descripcionMultipleLista(
				reporteRECForm.getOperacionList(), this.siccOperacionesList);
		
		params.put("fechaValidacion", fecha1);
		params.put("fechaValidacionFinal", fecha2);
		params.put("descripcionUserList", descripcionUserList);
		params.put("descripcionOperacionList", descripcionOperacionList);
		params.put("descripcionRegionList", descripcionRegionList);
		params.put("descripcionZonaList", descripcionZonaList);
		params.put("descripcionTerritorioList", descripcionTerritorioList);

		this.formatoReporte = reporteRECForm.getFormatoExportacion();
		this.reporteSufijo = reporteRECForm.getTipoReporte()
				+ this.formatoReporte;
		Map criteria = params;
		if (StringUtils.isNotEmpty(reporteRECForm.getCodigoPeriodo()))
			params.put("oidPeriodo", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));
		else
			params.put("oidPeriodo", null);

		String condicionZonas = obtieneCondicion(reporteRECForm.getZonaList(),
				"ZON_ZONA.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(
				reporteRECForm.getRegionList(), "ZON_REGIO.COD_REGI", "'");
		String condicionTerritorio = obtieneCondicion(
				reporteRECForm.getTerritorioList(), "ZON_TERRI.COD_TERR", "'");
		String condicionUser = obtieneCondicion(reporteRECForm.getUserList(),
				"CABEC_RECLA.COD_USUA_INGR", "'");
		String condicion = condicionZonas + condicionRegion
				+ condicionTerritorio;
		params.put("condicionUbigeo", condicion);
		params.put("condicion", condicionUser);
		params.put(
				"NroReporte",
				getReportResourceMessage("reporteRECValidacionSolicitudesRecForm.numero.reporte"));
		params.put(
				"operacionList",
				obtieneCondicion(reporteRECForm.getOperacionList(),
						"ope.COD_OPER", "'"));
		params.put(
				"titulo",
				getReportResourceMessage("reporteRECValidacionSolicitudesRecForm.titulo"));
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
			ReporteRECValidacionSolicitudesRecForm form = (ReporteRECValidacionSolicitudesRecForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
//				List<String> strings = new ArrayList<String>(
//						Arrays.asList(regiones));

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc
						.getZonasMultipleByPaisMarcaCanalRegion(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
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
	 *            Obtiene la lsita de territorios de acuerdo a la zonas
	 *            escogidas.
	 */
	public void showTerritorioxZona(ValueChangeEvent val) {
		log.debug(">>showTerritorioxZona ");
		try {
			ReporteRECValidacionSolicitudesRecForm form = (ReporteRECValidacionSolicitudesRecForm) this.formReporte;

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
	 * @return the reporteSufijo
	 */
	public String getReporteSufijo() {
		return reporteSufijo;
	}

	/**
	 * @param reporteSufijo
	 *            the reporteSufijo to set
	 */
	public void setReporteSufijo(String reporteSufijo) {
		this.reporteSufijo = reporteSufijo;
	}

	/**
	 * @return the siccUserList
	 */
	public List getSiccUserList() {
		return siccUserList;
	}

	/**
	 * @param siccUserList
	 *            the siccUserList to set
	 */
	public void setSiccUserList(List siccUserList) {
		this.siccUserList = siccUserList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
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
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList
	 *            the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the siccOperacionesList
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 *            the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}
}