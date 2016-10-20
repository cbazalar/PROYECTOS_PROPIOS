package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACAnalisisVentaDiariayAcumuladaForm;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACAnalisisVentaDiariayAcumuladaAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACAnalisisVentaDiariayAcumuladaForm form = new ReporteSACAnalisisVentaDiariayAcumuladaForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACAnalisisVentaDiariayAcumuladaForm form = (ReporteSACAnalisisVentaDiariayAcumuladaForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(form.getFormatoExportacion())){
			if(StringUtils.equalsIgnoreCase(form.getVistaReporte(), "Detalle")){
				if(StringUtils.equalsIgnoreCase(form.getTipoNegocio(),"Negocio")){
					return "reporteSACAnalisisVentaDiariayAcumuladaNegocioDetalleXLS";
				}else{					
					return "reporteSACAnalisisVentaDiariayAcumuladaUnidadNegocioDetalleXLS";
				}
			}else{
				if(StringUtils.equalsIgnoreCase(form.getTipoNegocio(),"Negocio")){
					return "reporteSACAnalisisVentaDiariayAcumuladaNegocioResumenXLS";
				}else{
					return "reporteSACAnalisisVentaDiariayAcumuladaUnidadNegocioResumenXLS";
				}
			}
		}
		
		return "reporteMaestroHorizontal";	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSACAnalisisVentaDiariayAcumuladaForm form = (ReporteSACAnalisisVentaDiariayAcumuladaForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		return "reporteSACAnalisisVentaDiariayAcumulada" + form.getReporte();
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
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteSACAnalisisVentaDiariayAcumuladaForm form = (ReporteSACAnalisisVentaDiariayAcumuladaForm) this.formReporte;
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		 
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setFechaProcesoDate(DateUtil.convertStringToDate(DateUtil.convertDateToString(DateUtil.getDatePattern(), new Date())));
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSACAnalisisVentaDiariayAcumuladaForm form = (ReporteSACAnalisisVentaDiariayAcumuladaForm) formReporte;
		StringBuilder titulo= new StringBuilder("\n");
		
		StringBuilder reporte = new StringBuilder();
		if(form.getFechaProcesoDate()!=null){
			form.setFechaProceso(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaProcesoDate()));
		}		
		
		if(StringUtils.equalsIgnoreCase(form.getVistaReporte(),"Detalle")){
			if(StringUtils.equalsIgnoreCase(form.getTipoNegocio(),"Negocio")){
				reporte.append("NegocioDetallePDF");
				titulo.append("Negocio/Detalle");
			}else{
				reporte.append("UnidadNegocioDetallePDF");
				titulo.append("UnidadNegocio/Detalle");
			}				
		}else{			
			if(StringUtils.equalsIgnoreCase(form.getTipoNegocio(), "Negocio")){
				reporte.append("NegocioResumenPDF");
				titulo.append("Negocio/Resumen");
			}else{
				reporte.append("UnidadNegocioResumenPDF");
				titulo.append("UnidadNegocio/Resumen");
			}
		}
		form.setReporte(reporte.toString());
		params.put("codigoPeriodo", form.getCodigoPeriodo());
		params.put("fechaProceso", form.getFechaProceso());
		params.put("NroReporte", "");
		params.put("titulo", getReportResourceMessage("reporteSACAnalisisVentaDiariayAcumuladaForm.title")+titulo.toString());
		return params;
	}	
	

}
