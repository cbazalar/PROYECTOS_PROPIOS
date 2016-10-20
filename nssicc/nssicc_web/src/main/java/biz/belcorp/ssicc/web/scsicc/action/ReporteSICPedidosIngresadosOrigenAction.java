package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICPedidosIngresadosOrigenForm;

/**
 * The Class ReporteSICPedidosIngresadosOrigenAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/06/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICPedidosIngresadosOrigenAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICPedidosIngresadosOrigenForm form = new ReporteSICPedidosIngresadosOrigenForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICPedidosIngresadosOrigenForm form = (ReporteSICPedidosIngresadosOrigenForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			if ("A".equals(form.getTipoReporte())){
				return "reporteSICPedidosIngresadosOrigenActualXLS";
			}else{
				return "reporteSICPedidosIngresadosOrigenHistoricoXLS";
			}
		}
		else
			return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICPedidosIngresadosOrigenForm form = (ReporteSICPedidosIngresadosOrigenForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(form.getFormatoExportacion()) || "VPDF".equals(form.getFormatoExportacion())){
			if ("A".equals(form.getTipoReporte())){
				return "reporteSICPedidosIngresadosOrigenActualPDF";
			}else{
				return "reporteSICPedidosIngresadosOrigenHistoricoPDF";
			}
		}
		else
			return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {;
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		ReporteSICPedidosIngresadosOrigenForm reporteSICForm = (ReporteSICPedidosIngresadosOrigenForm) this.formReporte;
		
		log.debug(reporteSICForm.getFormatoExportacion());
		params.put("NroReporte", "");

		String titulo = this.getResourceMessage("reporteSICPedidosIngresadosOrigenForm.titulo");

		if ("A".equals(reporteSICForm.getTipoReporte())){
			titulo+= this.getResourceMessage("reporteSICPedidosIngresadosOrigenForm.titulo.actual");
			params.put("codigoPeriodo", null);
		}else{ 
			titulo+=  this.getResourceMessage("reporteSICPedidosIngresadosOrigenForm.titulo.historico");
			params.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		}
		
		params.put("titulo", titulo);
		params.put("codigoPais", reporteSICForm.getCodigoPais());
				
		return params;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ReporteSICFacturacionAction - setViewAtributes");
		}
		
		this.mostrarReporteXLS = true;
		ReporteSICPedidosIngresadosOrigenForm reporteSICForm = (ReporteSICPedidosIngresadosOrigenForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		reporteSICForm.setTipoReporte("A");
	}
	
}
