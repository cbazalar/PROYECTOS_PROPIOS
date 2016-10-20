package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteOCRConsolidadoPedidoForm;


/**
 * The Class ConsultaOCRPedidosDuplicadosAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/09/2014
 */
@ManagedBean
@SessionScoped
public class ReporteOCRConsolidadoPedidoAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteOCRConsolidadoPedidoForm form = new ReporteOCRConsolidadoPedidoForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteOCRConsolidadoPedidoForm form = (ReporteOCRConsolidadoPedidoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		String valor = "";
		
		if ("XLS".equals(form.getFormatoExportacion())) {			
			valor =  "reporteOCRConsolidadoPedido" + form.getTipoConsulta() + "XLS";
		}else{
			valor = "reporteMaestroVertical";
		}
		
		return valor;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteOCRConsolidadoPedidoForm form = (ReporteOCRConsolidadoPedidoForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		String valor = "";
		
		if ("PDF".equals(form.getFormatoExportacion())) {			
			valor =  "reporteOCRConsolidadoPedido" + form.getTipoConsulta() + "PDF";
		}
		return valor;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		this.mostrarReporteXLS = true;
		
        ReporteOCRConsolidadoPedidoForm form = (ReporteOCRConsolidadoPedidoForm)this.formReporte;
     // Carga Fecha y Periodo
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        form.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),	Constants.CODIGO_CANAL_DEFAULT));	
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteOCRConsolidadoPedidoForm form = (ReporteOCRConsolidadoPedidoForm)this.formReporte;		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteria = params;
		criteria.put("codigoPeriodo", form.getCodigoPeriodo());
		
		params.put("codigoPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
		log.debug("codigoPeriodo:"+params.get("codigoPeriodo"));
		
		if(form.getTipoConsulta().equals("Periodo"))
			params.put("titulo", this.getResourceMessage("reporteOCRConsolidadoPedidoForm.titlePeriodo") + "\n" + "Campaña: "+form.getCodigoPeriodo());
		if(form.getTipoConsulta().equals("PeriodoZona"))
			params.put("titulo", this.getResourceMessage("reporteOCRConsolidadoPedidoForm.titlePeriodoZona") + "\n" + "Campaña: "+form.getCodigoPeriodo());
		if(form.getTipoConsulta().equals("PeriodoRegion"))
			params.put("titulo", this.getResourceMessage("reporteOCRConsolidadoPedidoForm.titlePeriodoRegion") + "\n" + "Campaña: "+form.getCodigoPeriodo());
		return criteria;
	}
	
}
