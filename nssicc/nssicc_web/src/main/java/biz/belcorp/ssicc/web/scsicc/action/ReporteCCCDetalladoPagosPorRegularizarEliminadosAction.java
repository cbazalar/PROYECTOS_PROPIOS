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
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDetalladoPagosPorRegularizarEliminadosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCDetalladoPagosPorRegularizarEliminadosAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 2738796949590860015L;
	private String formatoReporte;
	private List siccBancoList;

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
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCDetalladoPagosPorRegularizarEliminadosForm reporteForm = new ReporteCCCDetalladoPagosPorRegularizarEliminadosForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {

		return "reporteCCCDetalladoPagosPorRegularizarEliminadosXLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		// servicios y formulario
		ReporteCCCDetalladoPagosPorRegularizarEliminadosForm reporteAPEForm = (ReporteCCCDetalladoPagosPorRegularizarEliminadosForm) this.formReporte;
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		// parametros generales
		Pais pais = getmPantallaPrincipalBean().getCurrentCountry();
		// reset
		reporteAPEForm
				.setFechaElimDesdeDt(new Date(System.currentTimeMillis()));
		reporteAPEForm
				.setFechaElimHastaDt(new Date(System.currentTimeMillis()));

		// Map para almacenar los parametros
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		// seteando
		this.siccBancoList = serviceCCC.getCuentasCorrientesBancariasList(criteria);

	}

	public String setValidarReporte() {
		ReporteCCCDetalladoPagosPorRegularizarEliminadosForm form = (ReporteCCCDetalladoPagosPorRegularizarEliminadosForm) this.formReporte;

		String vFechaPagoDesde = DateUtil.getDate(form.getFechaPagoDesdeDt());
		String vFechaPagoHasta = DateUtil.getDate(form.getFechaPagoHastaDt());
		String vFechaProcesoDesde = DateUtil
				.getDate(form.getFechaProcDesdeDt());
		String vFechaProcesoHasta = DateUtil
				.getDate(form.getFechaProcHastaDt());
		String vFechaEliminacionDesde = DateUtil.getDate(form
				.getFechaElimDesdeDt());
		String vFechaEliminacionHasta = DateUtil.getDate(form
				.getFechaElimHastaDt());
		if (!vFechaPagoDesde.isEmpty() || !vFechaPagoHasta.isEmpty()) {
			if (!vFechaPagoDesde.isEmpty() && !vFechaPagoHasta.isEmpty()) {
				if (vFechaPagoDesde.compareTo(vFechaPagoHasta) > 0) {
					return "La fecha de pago 'Desde' no puede ser mayor a la fecha de pago 'Hasta'";
				}
			} else
				return "Complete ambas fechas de pago";
		}

		if (!vFechaProcesoDesde.isEmpty() || !vFechaProcesoHasta.isEmpty()) {
			if (!vFechaProcesoDesde.isEmpty() && !vFechaProcesoHasta.isEmpty()) {
				if (vFechaProcesoDesde.compareTo(vFechaProcesoHasta) > 0) {
					return "La fecha de proceso 'Desde' no puede ser mayor a la fecha de proceso 'Hasta'";
				}
			} else
				return "Complete ambas fechas de proceso";
		}

		if (!vFechaEliminacionDesde.isEmpty()
				|| !vFechaEliminacionHasta.isEmpty()) {
			if (!vFechaEliminacionDesde.isEmpty()
					&& !vFechaEliminacionHasta.isEmpty()) {
				if (vFechaEliminacionDesde.compareTo(vFechaEliminacionHasta) > 0) {
					return "La fecha de eliminación 'Desde' no puede ser mayor a la fecha de eliminación 'Hasta'";
				}
			} else
				return "Complete ambas fechas de eliminación";
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCDetalladoPagosPorRegularizarEliminadosForm.prepareParameterMap' method");
		}
		ReporteCCCDetalladoPagosPorRegularizarEliminadosForm form = (ReporteCCCDetalladoPagosPorRegularizarEliminadosForm) this.formReporte;
		String vFechaPagoDesde = DateUtil.getDate(form.getFechaPagoDesdeDt());
		String vFechaPagoHasta = DateUtil.getDate(form.getFechaPagoHastaDt());
		String vFechaProcesoDesde = DateUtil
				.getDate(form.getFechaProcDesdeDt());
		String vFechaProcesoHasta = DateUtil
				.getDate(form.getFechaProcHastaDt());
		String vFechaEliminacionDesde = DateUtil.getDate(form
				.getFechaElimDesdeDt());
		String vFechaEliminacionHasta = DateUtil.getDate(form
				.getFechaElimHastaDt());

		form.setFechaPagoDesde(vFechaPagoDesde);
		form.setFechaPagoHasta(vFechaPagoHasta);

		form.setFechaProcDesde(vFechaProcesoDesde);
		form.setFechaProcHasta(vFechaProcesoHasta);

		form.setFechaElimDesde(vFechaEliminacionDesde);
		form.setFechaElimHasta(vFechaEliminacionHasta);
		
		params.put("codigoBanco", form.getCodigoBanco());
		params.put("fechaPagoDesde", vFechaPagoDesde);
		params.put("fechaPagoHasta", vFechaPagoHasta);
		params.put("fechaProcDesde", vFechaProcesoDesde);
		params.put("fechaProcHasta", vFechaProcesoHasta);
		params.put("fechaElimDesde", vFechaEliminacionDesde);
		params.put("fechaElimHasta", vFechaEliminacionHasta);
		

		log.debug(" Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");

		return params;

	}

}