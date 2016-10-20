package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCPrimerosPedDeudorForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCPrimerosPedDeudorAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 8112598332013337911L;
	private String formatoReporte;
	private String tipoVista;
	private List siccSociedadList;

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
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
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
		ReporteCCCPrimerosPedDeudorForm reporteForm = new ReporteCCCPrimerosPedDeudorForm();
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
		this.formatoReporte = ((ReporteCCCPrimerosPedDeudorForm) this.formReporte)
				.getFormatoExportacion();
		String nombreReporte = null;
		if ("D".equals(this.tipoVista))
			nombreReporte = "reporteCCCPrimPedDeudDXLS";
		else
			nombreReporte = "reporteCCCPrimPedDeudSXLS";
		return nombreReporte;
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
			log.debug("Entering 'ReporteCOMComisionPagoEjecutivasAction.prepareParameterMap' method");
		}
		ReporteCCCPrimerosPedDeudorForm reporteCCCForm = (ReporteCCCPrimerosPedDeudorForm) this.formReporte;

		this.tipoVista = reporteCCCForm.getTipoVista();

		log.debug(" Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
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
			this.log.debug("Entering 'ReporteCOMComisionPagoEjecutivasAction.setViewAtributes' method");
		}
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteCCCPrimerosPedDeudorForm reporteCCCForm = (ReporteCCCPrimerosPedDeudorForm) this.formReporte;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));

		reporteCCCForm.setCodigoPeriodoInicial(periodo);
		reporteCCCForm.setCodigoPeriodoFinal(periodo);

		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());
		log.debug("Todo Ok: Redireccionando");
	}

	protected void afterExecuteReporte(ReporteParams reporteParams)
			throws Exception {
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		log.debug("ReporteCCCPrimerosPedDeudorAction - Delete to report ");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		reporteService.deleteTableReporteCCCPrimPedDeud(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCPrimerosPedDeudorForm reporteRETForm = (ReporteCCCPrimerosPedDeudorForm) this.formReporte;
		if (reporteRETForm.getCodigoPeriodoInicial().compareTo(
				reporteRETForm.getCodigoPeriodoFinal()) > 0)
			return "El periodo inicial no puede ser mayor al periodo final";

		return null;
	}
}