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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteVENFacturaNotaCreditoDebitoForm;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class ReporteVENFacturaNotaCreditoDebitoAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6878400994347942636L;	
	private String tipoReporte;

	private String codigoIdiomaISO = "";

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {

		ReporteVENFacturaNotaCreditoDebitoForm r = new ReporteVENFacturaNotaCreditoDebitoForm();
		return r;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {

		if ("XLS".equals(this.formatoExportacion))
			if ("1".equals(tipoReporte))
				return "reporteRUVFacturaXLS";
			else if ("2".equals(tipoReporte))
				return "reporteRUVNotaCreditoXLS";
			else if ("3".equals(tipoReporte))
				return "reporteRUVNotaDebitoXLS";
			else
				return "";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {

		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		log.info("Entro a ReporteVENFacturaNotaCreditoDebitoAction - prepareParameterMap");

		ReporteVENFacturaNotaCreditoDebitoForm f = (ReporteVENFacturaNotaCreditoDebitoForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		f.setFechaInicio(DateUtil.convertDateToString(f.getCalendarInicio()));
		f.setFechaFin(DateUtil.convertDateToString(f.getCalendarFin()));

		try {
			// -- Formato exportacion
			this.tipoReporte = f.getTipoReporte();

			// -- Parametros Reporte -------------------------------------
			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			String oidPais = reporteService.getOidString(
					"getOidPaisByCodigoPais", criteria);
			params.put("oidPais", oidPais);

			params.put("fechaInicio", f.getFechaInicio());
			params.put("fechaFin", f.getFechaFin());

			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(f.getCodigoPais());
			parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_RUV);
			parametroPais
					.setNombreParametro(Constants.RUV_CODIGO_PARAMETRO_CONSTANTE_FACTURACION);
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			List lstParametros = genericoService
					.getParametrosPais(parametroPais);

			ParametroPais parametro = null;
			String constanteFacturacion = "";
			if (CollectionUtils.size(lstParametros) == 1) {
				parametro = (ParametroPais) lstParametros.get(0);
				constanteFacturacion = parametro.getValorParametro();
			}
			params.put("constanteFacturacion", constanteFacturacion);

			parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(f.getCodigoPais());
			parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_RUV);
			parametroPais
					.setNombreParametro(Constants.RUV_CODIGO_PARAMETRO_CONSTANTE_NOTACREDITO);
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			lstParametros = genericoService.getParametrosPais(parametroPais);

			String constanteNotaCredito = "";
			if (CollectionUtils.size(lstParametros) == 1) {
				parametro = (ParametroPais) lstParametros.get(0);
				constanteNotaCredito = parametro.getValorParametro();
			}
			params.put("constanteNotaCredito", constanteNotaCredito);

			parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(f.getCodigoPais());
			parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_RUV);
			parametroPais
					.setNombreParametro(Constants.RUV_CODIGO_PARAMETRO_CONSTANTE_NOTADEBITO);
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			lstParametros = genericoService.getParametrosPais(parametroPais);

			String constanteNotaDebito = "";
			if (CollectionUtils.size(lstParametros) == 1) {
				parametro = (ParametroPais) lstParametros.get(0);
				constanteNotaDebito = parametro.getValorParametro();
			}
			params.put("constanteNotaDebito", constanteNotaDebito);

		} catch (Exception e) {

			addError("Error: ", getResourceMessage("errors.detail"));
		}

		log.info("Salio a ReporteVENFacturaNotaCreditoDebitoAction - prepareParameterMap");

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		log.info("Entro a ReporteVENFacturaNotaCreditoDebitoAction - setViewAttributes");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		try {
			ReporteVENFacturaNotaCreditoDebitoForm f = (ReporteVENFacturaNotaCreditoDebitoForm) this.formReporte;
			GenericoService genericoService = (GenericoService) getBean("genericoService");

			this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(pais.getCodigo());
			parametroPais.setCodigoSistema("GEN");
			parametroPais.setNombreParametro("reporteLocaleIdiomaNumero");
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			List lstParametros = genericoService
					.getParametrosPais(parametroPais);

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
			f.setCalendarInicio(DateUtil.convertStringToDate(f.getFechaInicio()));
			f.setCalendarFin(DateUtil.convertStringToDate(f.getFechaFin()));

		} catch (Exception e) {

			addError("Error: ", getResourceMessage("errors.detail"));
		}

		log.info("Salio a ReporteVENFacturaNotaCreditoDebitoAction - setViewAttributes");

	}

	protected void configReporteParams(ReporteParams reporteParams) {
		Map params = reporteParams.getQueryParams();
		Map parameterMap = (Map) params.get("parameterMap");
		String idiomaReporte = (String) parameterMap.get("idiomaReporte");
		String paisReporte = (String) parameterMap.get("paisReporte");

		Locale locale = new Locale(idiomaReporte, paisReporte);
		params.put(JRParameter.REPORT_LOCALE, locale);
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

}
