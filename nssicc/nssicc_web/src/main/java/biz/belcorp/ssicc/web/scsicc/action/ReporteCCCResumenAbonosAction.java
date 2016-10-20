package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCResumenAbonosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCResumenAbonosAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = 7683490796518555871L;
	private String tipoReporte;
	private Boolean indicadorConsultora;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.indicadorConsultora = false;
		// Obtenemos el Pais
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		ReporteCCCResumenAbonosForm f = (ReporteCCCResumenAbonosForm) this.formReporte;
		f.setFechaHastaD(new Date(System.currentTimeMillis()));
		f.setFechaDesdeD(new Date(System.currentTimeMillis()));
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
		ReporteCCCResumenAbonosForm form = new ReporteCCCResumenAbonosForm();
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
		ReporteCCCResumenAbonosForm f = (ReporteCCCResumenAbonosForm) this.formReporte;

		if (StringUtils
				.equalsIgnoreCase(f.getIndicadorConsultora(), Constants.SI)) {
			return "reporteCCCResumenAbonosPorConsultoraXLS";
		} else {
			return "reporteCCCResumenAbonos" + this.tipoReporte + "XLS";

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
			log.debug("Entering 'prepareParameterMap' method");
		}

		// Preparando los parametros

		ReporteCCCResumenAbonosForm reporteCCCResumenAbonosForm = (ReporteCCCResumenAbonosForm) this.formReporte;

		String fecha1, fecha2;

		fecha1 = DateUtil.getDate(reporteCCCResumenAbonosForm.getFechaDesdeD());
		fecha2 = DateUtil.getDate(reporteCCCResumenAbonosForm.getFechaHastaD());
		reporteCCCResumenAbonosForm.setFechaDesde(fecha1);
		reporteCCCResumenAbonosForm.setFechaHasta(fecha2);

		this.tipoReporte = reporteCCCResumenAbonosForm.getTipoReporte();
		if(this.indicadorConsultora){
			reporteCCCResumenAbonosForm.setIndicadorConsultora("S");
		}else{
			reporteCCCResumenAbonosForm.setIndicadorConsultora(null);

		}

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();

		// Se obtiene el oid del tipo de documento Guia de Remision
		criteria.put("codigoGuiaRemision", "GU");
		params.put("oidGuiaRemision",
				reporteService.getOidString("getOidGuiaRemision", criteria));

		// Se obtiene el oid del tipo de documento Nota de Credito boleta Venta
		criteria.put("codigoNotaCreditoBol", "020");
		params.put("oidNotaCreditoBol",
				reporteService.getOidString("getOidNotaCreditoBol", criteria));

		// Se obtiene el oid del tipo de documento Nota de Credito Factura 1
		criteria.put("codigoNotaCreditoFact1", "021");
		params.put("oidNotaCreditoFact1",
				reporteService.getOidString("getOidNotaCreditoFact1", criteria));

		// Se obtiene el oid del tipo de documento Nota de Credito Factura 2
		criteria.put("codigoNotaCreditoFact2", "022");
		params.put("oidNotaCreditoFact2",
				reporteService.getOidString("getOidNotaCreditoFact2", criteria));

		// Se obtiene el oid del tipo de documento NC Globales
		criteria.put("codigoNCGlobales", "027");
		params.put("oidNCGlobales",
				reporteService.getOidString("getOidNCGlobales", criteria));
		params.put("fechaDesde",fecha1);
		params.put("fechaHasta",fecha2);
		return params;
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
	 * @return the indicadorConsultora
	 */
	public Boolean getIndicadorConsultora() {
		return indicadorConsultora;
	}

	/**
	 * @param indicadorConsultora the indicadorConsultora to set
	 */
	public void setIndicadorConsultora(Boolean indicadorConsultora) {
		this.indicadorConsultora = indicadorConsultora;
	}

	
}