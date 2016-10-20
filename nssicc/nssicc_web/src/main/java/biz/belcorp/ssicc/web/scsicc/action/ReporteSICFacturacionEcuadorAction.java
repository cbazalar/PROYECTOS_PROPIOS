package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICFacturacionEcuadorForm;

/**
 * The Class ReporteSICFacturacionAction.
 * 
 * @autor: Belcorp
 * @version: 1.0 12/05/2014
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSICFacturacionEcuadorAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private LabelValue[] siccRegionList = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICFacturacionEcuadorForm form = new ReporteSICFacturacionEcuadorForm();
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
		ReporteSICFacturacionEcuadorForm form = (ReporteSICFacturacionEcuadorForm) this.formReporte;
		log.debug(form.getFormatoExportacion());

		if (StringUtils.equals(form.getFormatoExportacion(), "XLS")) {
			return "reporteSICFacturacionEcuadorXLS";
		} else {
			return "reporteOCRMaestroHorizontalMM";
		}
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
		ReporteSICFacturacionEcuadorForm form = (ReporteSICFacturacionEcuadorForm) this.formReporte;
		log.debug(form.getFormatoExportacion());

		return "reporteSICFacturacionEcuadorPDF";
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
		;
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap");
		}

		ReporteSICFacturacionEcuadorForm reporteSICForm = (ReporteSICFacturacionEcuadorForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		log.debug(reporteSICForm.getFormatoExportacion());

		String condicionRegion = obtieneCondicion(
				reporteSICForm.getCodigoRegion(), "zon.zorg_oid_regi", "'");
		String condicionRegion1 = obtieneCondicion(
				reporteSICForm.getCodigoRegion(), "reg.oid_regi", "'");

		Map criteria = params;
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",
				criteria);
		criteria.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		String oidPeriodo = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);
		criteria.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		String oidPeriodoAnterior = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodoAnterior", criteria);
		String desPeriodoAnterior = reporteService.getOidString(
				"getDesPeriodoByCodigoPeriodoAnterior", criteria);
		criteria.put("codigoPais", reporteSICForm.getCodigoPais());

		/*
		 * Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); String
		 * indSolPedFactAn = pais.getIndicadorExcluirPedidosAnulados();
		 * 
		 * criteria.put("codigoMarca", "T"); String
		 * oidMarca=reporteService.getOidString("getOidMarcaByCodigoMarca",
		 * criteria);
		 * 
		 * criteria.put("codigoCanal", "VD"); String
		 * oidCanal=reporteService.getOidString("getOidCanalByCodigoCanal",
		 * criteria);
		 */

		params.put("NroReporte", "");
		params.put("condicion", condicionRegion);
		params.put("condicion1", condicionRegion1);
		params.put("oidPais", oidPais);
		params.put("oidPeriodo", oidPeriodo);
		params.put("oidPeriodoAnterior", oidPeriodoAnterior);
		params.put("desPeriodoAnterior", desPeriodoAnterior);

		reporteSICForm.setFechaInicioFacturacion(DateUtil.convertDateToString(
				Constants.DEFAULT_DATE_FORMAT,
				reporteSICForm.getFechaInicioFacturacionDate()));

		reporteSICForm.setFechaFinFacturacion(DateUtil.convertDateToString(
				Constants.DEFAULT_DATE_FORMAT,
				reporteSICForm.getFechaFinFacturacionDate()));

		params.put("fechaInicioFacturacion",
				reporteSICForm.getFechaInicioFacturacion());
		params.put("fechaFinFacturacion",
				reporteSICForm.getFechaFinFacturacion());

		params.put(
				"titulo",
				this.getResourceMessage("reporteSICFacturacionEcuadorForm.titulo")
						+ " "
						+ reporteSICForm.getFechaInicioFacturacion()
						+ " "
						+ this.getResourceMessage("reporte.generico.al")
						+ " " + reporteSICForm.getFechaFinFacturacion());
		/*
		 * params.put("indSolPedFactAn", indSolPedFactAn);
		 * params.put("oidMarca", oidMarca); params.put("oidCanal", oidCanal);
		 */

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
		if (log.isDebugEnabled()) {
			log.debug("ReporteSICFacturacionAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;

		ReporteSICFacturacionEcuadorForm reporteSICForm = (ReporteSICFacturacionEcuadorForm) formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		List listaRegiones = reporteService.getListaGenerico(
				"getRegionesOIDByPais", criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];

		int i = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((BaseOID) object).getDescripcion());
			labelValue.setValue(((BaseOID) object).getOid().toString());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}

		String periodoActual = reporteService.getStringGenerico(
				"getPeriodoByFechaActual", criteriaOperacion);
		reporteSICForm.setCodigoPeriodo(periodoActual);
		criteriaOperacion.put("codigoPeriodo", periodoActual);
		List aux = reporteService.getListaGenerico(
				"getFechaInicioPeriodoByPaisMarcaCanal", criteriaOperacion);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicio;
		if (aux.size() > 0) {
			Base base = (Base) aux.get(0);
			fechaInicio = base.getCodigo();
		} else
			fechaInicio = sdf.format(new Date(System.currentTimeMillis()));

		reporteSICForm.setFechaFinFacturacionDate(DateUtil.convertStringToDate(
				Constants.DEFAULT_DATE_FORMAT,
				sdf.format(new Date(System.currentTimeMillis() - 1))));
		reporteSICForm
				.setFechaInicioFacturacionDate(DateUtil.convertStringToDate(
						Constants.DEFAULT_DATE_FORMAT, fechaInicio));
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
}
