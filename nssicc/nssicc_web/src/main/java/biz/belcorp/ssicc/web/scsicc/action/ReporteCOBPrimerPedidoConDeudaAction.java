package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOBPrimerPedidoConDeudaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**

 */
@ManagedBean
@SessionScoped
public class ReporteCOBPrimerPedidoConDeudaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3159004801980852735L;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBPrimerPedidoConDeudaForm form = new ReporteCOBPrimerPedidoConDeudaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {

		ReporteCOBPrimerPedidoConDeudaForm form = (ReporteCOBPrimerPedidoConDeudaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion())) {
			return "reporteCOBPrimerPedidoConDeudaXLS";
		} else {
			return "reporteMaestroVertical";
		}

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {

		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteCOBPrimerPedidoConDeudaForm f = (ReporteCOBPrimerPedidoConDeudaForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		
		Map criteria = params;
		params.put("campanaDesde", f.getCampanaDesde());
		params.put("campanaHasta", f.getCampanaHasta());
		
		params.put("NroReporte", "");
		return params;
	}

	public String setValidarReporte() {
		ReporteCOBPrimerPedidoConDeudaForm form = (ReporteCOBPrimerPedidoConDeudaForm) this.formReporte;
		int codperini = Integer.parseInt(form.getCampanaDesde());
		int codperfin = Integer.parseInt(form.getCampanaHasta());
		if (codperfin < codperini) {
			String mensaje = this
					.getResourceMessage("reporteRECIndFactTransaccionesForm.errorInicioMayor");
			return mensaje;
		}

		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOBPrimerPedidoConDeudaAction.setViewAtributes' method");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOBPrimerPedidoConDeudaService";
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
