package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOBPedidosFacturadosConDeudaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteCOBPedidosFacturadosConDeudaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 92836622751784919L;
	private String formatoReporte;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBPedidosFacturadosConDeudaForm reporteForm = new ReporteCOBPedidosFacturadosConDeudaForm();
		return reporteForm;
	}



	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteCOBPedidosFacturadosConDeudaAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		
		log.debug("Todo OK: Redireccionando");

	}
	
	public String setValidarReporte() {
		ReporteCOBPedidosFacturadosConDeudaForm form = (ReporteCOBPedidosFacturadosConDeudaForm) this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoDesde());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoHasta());
		if(codperfin<codperini){
			String mensaje =  this.getResourceMessage("reporteCOBPedidosFacturadosConDeuda.errors.compare.codigoPeriodo");
			return mensaje;
		}

	    					
	    return null;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCOBPedidosFacturadosConDeudaForm form = (ReporteCOBPedidosFacturadosConDeudaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteCOBPedidosFacturadosConDeudaXLS";
		else
			return "reporteMaestroHorizontal";
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
		ReporteCOBPedidosFacturadosConDeudaForm f = (ReporteCOBPedidosFacturadosConDeudaForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();	
		return params;
	}

	
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOBPedidosFacturadosConDeudaService";
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

	

}

