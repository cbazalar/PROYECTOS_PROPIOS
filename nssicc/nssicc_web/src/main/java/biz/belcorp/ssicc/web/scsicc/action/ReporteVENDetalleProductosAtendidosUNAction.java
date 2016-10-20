package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.JRParameter;

import org.apache.commons.collections.CollectionUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENDetalleProductosAtendidosUNForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteVENDetalleProductosAtendidosUNAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	// -- Variables Instancia
	private String formatoExportacion;
	private String tipoReporte;
	private String codigoIdiomaISO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseSubReporteAbstractAction
	 * #getSubReporteFileName()
	 */
	protected String getSubReporteFileName() {
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteVENDetalleProductosAtendidosUNForm form = (ReporteVENDetalleProductosAtendidosUNForm) this.formReporte;
		Date fecha1D = form.getFechaInicioD();
		Date fecha2D = form.getFechaFinD();
		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this
					.getResourceMessage("reporteVENCabecerasFacturasAnuladasForm.validar.fechas");
			return mensaje;
		}
		long fecha1Miles = (fecha2D.getTime() - fecha1D.getTime())/1000;
		long numeroFijo = 2678400000L;
		if(fecha1Miles >= numeroFijo){		
			String mensaje = this
					.getResourceMessage("reporteVENCabecerasFacturasAnuladasForm.errors.compare.dates31");
			return mensaje;
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction
	 * #getReporteFileName()
	 */
	protected String getReporteFileName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #configReporteParams
	 * (biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	@Override
	protected void configReporteParams(ReporteParams reporteParams) {
		Map params = reporteParams.getQueryParams();
		Map parameterMap = (Map) params.get("parameterMap");
		String idiomaReporte = (String) parameterMap.get("idiomaReporte");
		String paisReporte = (String) parameterMap.get("paisReporte");

		Locale locale = new Locale(idiomaReporte, paisReporte);
		params.put(JRParameter.REPORT_LOCALE, locale);
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENDetalleProductosAtendidosUNForm form = new ReporteVENDetalleProductosAtendidosUNForm();
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
		return null;
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
		log.info("Entro a ReporteVENDetalleProductosAtendidosUNAction - prepareParameterMap");

		ReporteVENDetalleProductosAtendidosUNForm f = (ReporteVENDetalleProductosAtendidosUNForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));

		f.setFormatoExportacion(this.formatoExportacion);
		// -- Formato exportacion

		// -- Parametros Reporte -------------------------------------
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",
				criteria);
		params.put("oidPais", oidPais);

		params.put("fechaInicio", f.getFechaInicio());
		params.put("fechaFin", f.getFechaFin());
		log.info("Salio a ReporteVENDetalleProductosAtendidosUNAction - prepareParameterMap");

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

		log.info("Entro a ReporteVENDetalleProductosAtendidosUNAction - setViewAttributes");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		ReporteVENDetalleProductosAtendidosUNForm f = (ReporteVENDetalleProductosAtendidosUNForm) this.formReporte;
		GenericoService genericoService = (GenericoService) getBean("genericoService");

		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		this.mostrarReportePDF = false;
		this.mostrarReporteOCSV = true;
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("GEN");
		parametroPais.setNombreParametro("reporteLocaleIdiomaNumero");
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List lstParametros = genericoService.getParametrosPais(parametroPais);

		ParametroPais parametro = null;
		String idiomaReporte = pais.getCodigoIdiomaIso();
		if (CollectionUtils.size(lstParametros) == 1) {
			parametro = (ParametroPais) lstParametros.get(0);
			idiomaReporte = parametro.getValorParametro();
		}
		f.setIdiomaReporte(idiomaReporte);

		parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("GEN");
		parametroPais.setNombreParametro("reporteLocatePaisNumero");
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		lstParametros = genericoService.getParametrosPais(parametroPais);

		String paisReporte = pais.getCodigoPaisIso();
		if (CollectionUtils.size(lstParametros) == 1) {
			parametro = (ParametroPais) lstParametros.get(0);
			paisReporte = parametro.getValorParametro();
		}
		f.setPaisReporte(paisReporte);

		Calendar fecha = new GregorianCalendar();
		int imes = fecha.get(Calendar.MONTH) + 2;
		int ianhio = fecha.get(Calendar.YEAR);

		String anhio = String.valueOf(ianhio);
		String mes = String.valueOf(imes);
		String mesAnterior = String.valueOf(imes - 1);

		if (mes.length() == 1) {
			mes = "0" + mes;
		}

		if (mesAnterior.length() == 1) {
			mesAnterior = "0" + mesAnterior;
		}

		Date primerDiaMes = DateUtil.convertStringToDate("01/" + mes + "/"
				+ anhio);
		Date ultimoDiaMesAnterior = DateUtil.addToDate(primerDiaMes,
				Calendar.DATE, -1);
		String ultimoDiaMes = DateUtil
				.convertDateToString(ultimoDiaMesAnterior);

		f.setFechaInicio("01/" + mesAnterior + "/" + anhio);
		f.setFechaFin(ultimoDiaMes);
		f.setFechaFinD(DateUtil.convertStringToDate(f.getFechaFin()));
		f.setFechaInicioD(DateUtil.convertStringToDate(f.getFechaInicio()));

		log.info("Salio a ReporteVENDetalleProductosAtendidosUNAction - setViewAttributes");
	}

	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/**
	 * @param formatoExportacion
	 *            the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 *            the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

}
