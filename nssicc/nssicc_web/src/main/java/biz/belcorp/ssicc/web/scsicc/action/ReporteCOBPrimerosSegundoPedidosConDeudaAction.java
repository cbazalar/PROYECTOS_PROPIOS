package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOBPrimerosSegundoPedidosConDeudaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteCOBPrimerosSegundoPedidosConDeudaAction extends
		BaseReporteAbstractAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5920103778779246394L;
	private String formatoReporte;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBPrimerosSegundoPedidosConDeudaForm reporteForm = new ReporteCOBPrimerosSegundoPedidosConDeudaForm();
		return reporteForm;
	}



	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteCOBPrimerosSegundoPedidosConDeudaAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		
		log.debug("Todo OK: Redireccionando");

	}
	
	public String setValidarReporte() {
		ReporteCOBPrimerosSegundoPedidosConDeudaForm form = (ReporteCOBPrimerosSegundoPedidosConDeudaForm) this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoDesde());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoHasta());
		if(codperfin<codperini){
			String mensaje =  this.getResourceMessage("reporteCOBPrimerosSegundoPedidosConDeudaForm.errors.compare.codigoPeriodo");
			return mensaje;
		}

	    					
	    return null;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCOBPrimerosSegundoPedidosConDeudaForm form = (ReporteCOBPrimerosSegundoPedidosConDeudaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteCOBPrimerosSegundoPedidosConDeudaXLS";
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
		ReporteCOBPrimerosSegundoPedidosConDeudaForm f = (ReporteCOBPrimerosSegundoPedidosConDeudaForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();	
		return params;
	}

	
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOBPrimerosSegundoPedidosConDeudaService";
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


