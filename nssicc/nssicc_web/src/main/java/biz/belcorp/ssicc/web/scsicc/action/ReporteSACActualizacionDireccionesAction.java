package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACActualizacionDireccionesForm;


// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaOCRPedidosDuplicadosAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/09/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACActualizacionDireccionesAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACActualizacionDireccionesForm form = new ReporteSACActualizacionDireccionesForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACActualizacionDireccionesForm form = (ReporteSACActualizacionDireccionesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		return "reporteSACActualizacionDireccionesXLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSACActualizacionDireccionesForm form = (ReporteSACActualizacionDireccionesForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		return "";
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
		this.mostrarReportePDF = false;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO);
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		
		ReporteSACActualizacionDireccionesForm f = (ReporteSACActualizacionDireccionesForm) this.formReporte;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		// Carga Fecha y Periodo
		f.setFechaFact(controlFacturacion.getFechaProceso());
		f.setFechaFactDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, controlFacturacion.getFechaProceso()));
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSACActualizacionDireccionesForm form = (ReporteSACActualizacionDireccionesForm)this.formReporte;
		
		params.put("NroReporte", "");
		params.put("codigoPais", form.getCodigoPais());
		params.put("periodo", form.getCodigoPeriodo());
		if(form.getFechaFactDate()!=null){
			form.setFechaFact(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaFactDate()));			
		}		
		params.put("fechaFacturacion", form.getFechaFact());	
		params.put("titulo", this.getResourceMessage("reporteSACActualizacionDireccionesForm.titulo"));
		return params;
	}
	
}
