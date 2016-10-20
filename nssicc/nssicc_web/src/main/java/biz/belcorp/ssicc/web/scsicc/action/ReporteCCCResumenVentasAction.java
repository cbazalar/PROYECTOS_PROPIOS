package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCResumenVentasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCResumenVentasAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = -2309424187853053162L;
	private String tipoReporte;

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

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		ReporteCCCResumenVentasForm f = (ReporteCCCResumenVentasForm) this.formReporte;
		f.setFechaHastaD(new Date(System.currentTimeMillis()));
		f.setFechaDesdeD(new Date(System.currentTimeMillis()));
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

		ReporteCCCResumenVentasForm reporteCCCResumenVentasForm = (ReporteCCCResumenVentasForm) this.formReporte;
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");

		String fecha1, fecha2, totalFacturaActiva;

		fecha1 = DateUtil.getDate(reporteCCCResumenVentasForm.getFechaDesdeD());
		fecha2 = DateUtil.getDate(reporteCCCResumenVentasForm.getFechaHastaD());
		reporteCCCResumenVentasForm.setFechaDesde(fecha1);
		reporteCCCResumenVentasForm.setFechaHasta(fecha2);
		
		params.put("fechaDesde", reporteCCCResumenVentasForm.getFechaDesde());
		params.put("fechaHasta", reporteCCCResumenVentasForm.getFechaHasta());
		
		totalFacturaActiva = serviceCCC.getTotalFacturaActiva(params);
		params.put("totalFacturaActiva", totalFacturaActiva);

		this.tipoReporte = reporteCCCResumenVentasForm.getTipoReporte();

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
		
		params.put("titulo", this.mPantallaPrincipalBean.getResourceMessage("reporteCCCResumenVentasForm.titulo", new String[] {reporteCCCResumenVentasForm.getFechaDesde(), reporteCCCResumenVentasForm.getFechaHasta()}));

		return params;
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
		ReporteCCCResumenVentasForm form = new ReporteCCCResumenVentasForm();
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
		return "reporteCCCResumenVentas" + this.tipoReporte + "XLS";
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
}