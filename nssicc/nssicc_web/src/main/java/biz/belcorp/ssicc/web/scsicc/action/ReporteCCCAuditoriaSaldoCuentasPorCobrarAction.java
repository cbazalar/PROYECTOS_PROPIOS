package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCAuditoriaSaldoCuentasPorCobrarForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteCCCAuditoriaSaldoCuentasPorCobrarAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6099567690101840818L;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCAuditoriaSaldoCuentasPorCobrarForm reporteForm = new ReporteCCCAuditoriaSaldoCuentasPorCobrarForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteCCCAuditoriaSaldoCuentasPorCobrarAction - setViewAtributes");
		}

		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;

		ReporteCCCAuditoriaSaldoCuentasPorCobrarForm f = (ReporteCCCAuditoriaSaldoCuentasPorCobrarForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// Para obtener la fecha
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente
		MantenimientoOCRPedidoControlFacturacionService servicePed = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		List listPed = servicePed.getControlFacturacionByCriteria(criteria);
		PedidoControlFacturacion pedido = (PedidoControlFacturacion) listPed
				.get(0);

		f.setFechaProcDesde(pedido.getFechaProceso());
		Map criteriaOperacion = new HashMap();

		criteriaOperacion.put("codigoPais", pais.getCodigo());

		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCAuditoriaSaldoCuentasPorCobrarService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {

		return "reporteCCCAuditoriaSaldoCuentasPorCobrarXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteCCCAuditoriaSaldoCuentasPorCobrarForm f = (ReporteCCCAuditoriaSaldoCuentasPorCobrarForm) this.formReporte;

		params.put("fechaProcDesde", f.getFechaProcDesde());
		formatoReporte = f.getFormatoExportacion();
		log.debug(" Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");

		params.put("nombreReporteCSV",
				"reporteCCCAuditoriaSaldoCuentasPorCobrarCSV");

		log.info("Salio a ReporteCCCAuditoriaSaldoCuentasPorCobrarAction - prepareParameterMap");
		return params;
	}
	
	public String getMailService() {
		return "ccc.mailReporteCCCAuditoriaSaldoCuentasPorCobrarService";
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

}
