package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCLiquidacionCobranzasAplicacionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCLiquidacionCobranzasAplicacionAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -1091798983329193025L;

	private String formatoReporte;
	private String tipoVista;
	private List siccBancoList;
	private List siccTiposOrigenLotesBancariosList;

	/**
	 * @return
	 */
	public List getSiccBancoList() {
		return siccBancoList;
	}

	/**
	 * @param siccBancoList
	 */
	public void setSiccBancoList(List siccBancoList) {
		this.siccBancoList = siccBancoList;
	}

	/**
	 * @return
	 */
	public List getSiccTiposOrigenLotesBancariosList() {
		return siccTiposOrigenLotesBancariosList;
	}

	/**
	 * @param siccTiposOrigenLotesBancariosList
	 */
	public void setSiccTiposOrigenLotesBancariosList(
			List siccTiposOrigenLotesBancariosList) {
		this.siccTiposOrigenLotesBancariosList = siccTiposOrigenLotesBancariosList;
	}

	/**
	 * @return
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCLiquidacionCobranzasAplicacionForm reporteForm = new ReporteCCCLiquidacionCobranzasAplicacionForm();
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
		return "reporteCCCLiquidacionCobranzasAplicacion" + this.tipoVista
				+ "XLS";
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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCLiquidacionCobranzasAplicacionForm.prepareParameterMap' method");
		}

		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}

		// Preparando los parametros
		ReporteCCCLiquidacionCobranzasAplicacionForm reporteCCCForm = (ReporteCCCLiquidacionCobranzasAplicacionForm) this.formReporte;

		reporteCCCForm.setFechaPagoDesde(DateUtil.getDate(reporteCCCForm
				.getFechaPagoDesdeD()));
		reporteCCCForm.setFechaPagoHasta(DateUtil.getDate(reporteCCCForm
				.getFechaPagoHastaD()));
		reporteCCCForm.setFechaProcDesde(DateUtil.getDate(reporteCCCForm
				.getFechaProcDesdeD()));
		reporteCCCForm.setFechaProcHasta(DateUtil.getDate(reporteCCCForm
				.getFechaProcHastaD()));
		params.put("fechaPagoDesde", reporteCCCForm.getFechaPagoDesde());
		params.put("fechaPagoHasta", reporteCCCForm.getFechaPagoHasta());
		params.put("fechaProcDesde", reporteCCCForm.getFechaProcDesde());
		params.put("fechaProcHasta", reporteCCCForm.getFechaProcHasta());
		this.tipoVista = reporteCCCForm.getTipoVista();

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
			this.log.debug("Entering 'ReporteCCCLiquidacionCobranzasAplicacionForm.setViewAtributes' method");
		}

		// Servicios y form
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		ReporteCCCLiquidacionCobranzasAplicacionForm f = (ReporteCCCLiquidacionCobranzasAplicacionForm) this.formReporte;

		f.setFechaPagoDesdeD(new Date(System.currentTimeMillis()));
		f.setFechaPagoHastaD(new Date(System.currentTimeMillis()));
		f.setFechaProcDesdeD(new Date(System.currentTimeMillis()));
		f.setFechaProcHastaD(new Date(System.currentTimeMillis()));
		// defecto
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		// Map para almacenar los parametros
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		// Lista de Cuentas Corrientes Bancarias
		this.siccBancoList = serviceCCC
				.getCuentasCorrientesBancariasList(criteria);
		this.siccTiposOrigenLotesBancariosList = serviceCCC
				.getTipoOrigenLotesBancarios();
		log.debug("Todo Ok: Redireccionando");
		mostrarReportePDF=false;
		mostrarReporteXLS=true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCLiquidacionCobranzasAplicacionForm form = (ReporteCCCLiquidacionCobranzasAplicacionForm) this.formReporte;

		String vFechaPagoInicio = DateUtil.getDate(form.getFechaPagoDesdeD());
		String vFechaPagoFin = DateUtil.getDate(form.getFechaPagoHastaD());
		String vFechaProcInicio = DateUtil.getDate(form.getFechaProcDesdeD());
		String vFechaProcFin = DateUtil.getDate(form.getFechaProcHastaD());

		if (!vFechaPagoInicio.isEmpty() || !vFechaPagoFin.isEmpty()) {
			if (vFechaPagoInicio.isEmpty() || vFechaPagoFin.isEmpty()) {
				return "Por favor complete ambas fechas de pago.";
			} else {
				if (vFechaPagoInicio.compareTo(vFechaPagoFin) > 0) {
					return "La fecha inicio de pago no puede ser mayor a la fecha final de pago";
				} else
					return null;
			}
		}
		if (!vFechaProcInicio.isEmpty() || !vFechaProcFin.isEmpty()) {
			if (vFechaProcInicio.isEmpty() || vFechaProcFin.isEmpty()) {
				return "Por favor complete ambas fechas de proceso.";
			} else {
				if (vFechaProcInicio.compareTo(vFechaProcFin) > 0) {
					return "La fecha inicio de proceso no puede ser mayor a la fecha final de proceso";
				} else
					return null;
			}
		}
		return null;
	}
}