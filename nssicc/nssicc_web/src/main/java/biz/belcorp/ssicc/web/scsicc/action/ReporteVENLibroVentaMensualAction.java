package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteVENLibroVentaMensualForm;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteVENLibroVentaMensualAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3242031133257312751L;
	private String formatoReporte;
	private String codigoPais;
	private String codigoIntefaz;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENLibroVentaMensualForm reporteForm = new ReporteVENLibroVentaMensualForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteVENLibroVentaMensualAction - setViewAtributes");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		codigoPais = pais.getCodigo();
		codigoIntefaz = this.parametrosPantalla.get("codigoInterfaz");
		this.mostrarReporteOCSV = true;
	

		log.debug("ReporteVENLibroVentaMensualAction - setViewAttributes");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteVENLibroVentaMensualService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais parametroPais = new ParametroPais();

		parametroPais.setCodigoPais(codigoPais);
		parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_RUV);
		parametroPais.setCodigoParametro(Constants.CODIGO_PARAMETRO_MUESTRA_REPORTE_LIBRO_VENTAS_VENEZUELA);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);

		List parametros = genericoService.getParametrosPais(parametroPais);

		String parametroReporteVenezuela = "";

		if (parametros != null && parametros.size() > 0) {
			ParametroPais p = (ParametroPais) parametros.get(0);
			parametroReporteVenezuela = p.getValorParametro();
		}

		if ("PDF".equals(formReporte.getFormatoExportacion())) {
			if (parametroReporteVenezuela.equals("1")) {
				return "reporteVENLibroVentaMensualParametroActivoPDF";
			} else {
				return "reporteVENLibroVentaMensualPDF";
			}

		}

		return null;

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}

		log.debug("ReporteVENLibroVentaMensualAction - prepareParameterMap");
		ReporteVENLibroVentaMensualForm f = (ReporteVENLibroVentaMensualForm) this.formReporte;

		formatoReporte = f.getFormatoExportacion();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.setGenerateTabsXLS(true);

		String fechaIni = DateUtil.convertDateToString(f.getFechaInicioD());
		String fechaFin = DateUtil.convertDateToString(f.getFechaFinD());

		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoSistema", Constants.CODIGO_SISTEMA_RUV);
		criteria.put("codigoParam", "001");

		params.put("codigoPais", pais.getCodigo());
		params.put("marca", reporteService.getTituloReportePaisMarca(criteria));
		params.put("codigoPeriodo", f.getCodigoAnhoMes());

		params.put("fechaInicio", fechaIni);
		params.put("fechaFin", fechaFin);

		params.put("codigoInterfaz", codigoIntefaz);
		params.put("formatoExportacion", formatoReporte);

		String anhio = StringUtils.substring(f.getCodigoAnhoMes(), 0, 4);
		String mes = StringUtils.substring(f.getCodigoAnhoMes(), 4, 6);

		params.put("titulo",getResourceMessage("reporteVENLibroVentaMensualForm.titulo")
						+ " " + fechaIni + " al " + fechaFin);

		return params;
	}

	public String setValidarReporte() {
		ReporteVENLibroVentaMensualForm form = (ReporteVENLibroVentaMensualForm) this.formReporte;
		if (form.getFechaFinD().compareTo(form.getFechaInicioD()) < 0) {
			String mensaje = this.getResourceMessage("errors.compare.dates");
			return mensaje;
		}
		return null;
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

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoIntefaz() {
		return codigoIntefaz;
	}

	public void setCodigoIntefaz(String codigoIntefaz) {
		this.codigoIntefaz = codigoIntefaz;
	}
	
	

}
