package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteOCRMasVeinteUnidadesForm;

/**
 * The Class ReporteOCRMasVeinteUnidadesAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 26/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteOCRMasVeinteUnidadesAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteOCRMasVeinteUnidadesForm form = new ReporteOCRMasVeinteUnidadesForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteOCRMasVeinteUnidadesForm form = (ReporteOCRMasVeinteUnidadesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		String valor = "";
		if ("PDF".equals(form.getFormatoExportacion())) {
			return "reporteOCRMasVeinteUnidadesPDF";
		}else{
			return "";
		}
		
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteOCRMasVeinteUnidadesForm form = (ReporteOCRMasVeinteUnidadesForm)this.formReporte;
		params.put("NroReporte", "");
		params.put("titulo", this.getReportResourceMessage(
				"reporteOCRMasVeinteUnidadesForm.titulo")
				+ " "
				+ form.getNumeroUnidades()+ " " 
				+ this.getReportResourceMessage("reporteOCRMasVeinteUnidadesForm.unidades")
				+ " "
				+form.getTipoConsulta());
		
		if(form.getTipoConsulta().compareToIgnoreCase("Sin Facturar")==0){
			params.put("todos", Constants.NO);
		}
		else{
			params.put("todos", Constants.SI);
		}
		return params;
	}
	

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");			
		}
		this.mostrarReporteXLS = true;
		Map criteria = new HashMap();
		criteria.put("codigoPais",this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO);
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		ReporteOCRMasVeinteUnidadesForm form = (ReporteOCRMasVeinteUnidadesForm) formReporte;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		// Carga Fecha y Periodo
		form.setCodigoPais((String)criteria.get("codigoPais"));
		form.setFechaFact(controlFacturacion.getFechaProceso());
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setNumeroUnidades("20");
		form.setTipoConsulta("Sin Facturar");
	}


	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteOCRMasVeinteUnidadesForm form = (ReporteOCRMasVeinteUnidadesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		String valor = "";
		if ("PDF".equals(form.getFormatoExportacion())) {			
			valor =  "reporteMaestroHorizontal";
		}else{
			valor = "reporteOCRMasVeinteUnidadesXLS"; 
		}
		return valor;
	}

}
