package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBPrimerSegundoTercerPedidoConDeudaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOBPrimerSegundoTercerPedidoConDeudaAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 7265893401869891004L;
	private String formatoReporte;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entro setViewAttributes - ReporteCOBPrimerSegundoTercerPedidoConDeudaAction");
		}			
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCOBPrimerSegundoTercerPedidoConDeudaForm form = (ReporteCOBPrimerSegundoTercerPedidoConDeudaForm) this.formReporte;
		Integer fecha1, fecha2;
		fecha1 = Integer.parseInt(form.getCampanaDesde());
		fecha2 = Integer.parseInt(form.getCampanaHasta());
		if (fecha1 > fecha2) {
			String mensaje = this
					.getResourceMessage("Campaña Hasta debe ser mayor o igual a Campaña Desde");
			return mensaje;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBPrimerSegundoTercerPedidoConDeudaForm form = new ReporteCOBPrimerSegundoTercerPedidoConDeudaForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)) {
			return "reporteCOBPrimerSegundoTercerPedidoConDeudaXLS";
		} else
			return "reporteMaestroVertical";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBPrimerSegundoTercerPedidoConDeudaForm f = (ReporteCOBPrimerSegundoTercerPedidoConDeudaForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();
		params.put("campanaDesde", f.getCampanaDesde());
		params.put("campanaHasta", f.getCampanaHasta());
		params.put("NroReporte", "");
		return params;
	}
	
	protected String devuelveBeanReporteService(){
		return "reportes.reporteCOBPrimerSegundoTercerPedidoConDeudaService";
	}	
}