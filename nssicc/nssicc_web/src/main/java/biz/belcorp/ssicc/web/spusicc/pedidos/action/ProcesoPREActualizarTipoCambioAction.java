package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPREActualizarTipoCambioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPREActualizarTipoCambioForm;

@ManagedBean
@SessionScoped
public class ProcesoPREActualizarTipoCambioAction extends BaseProcesoAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1393627538489009389L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPREActualizarTipoCambioForm form = new ProcesoPREActualizarTipoCambioForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		
		ProcesoPREActualizarTipoCambioForm form = (ProcesoPREActualizarTipoCambioForm) this.formProceso;
		log.debug("Los parametros del Proceso en el executeProcess son: "+ params.toString());
		
		ProcesoPREActualizarTipoCambioService service = (ProcesoPREActualizarTipoCambioService) getBean("spusicc.procesoPREActualizarTipoCambioService");		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService serviceMoneda = (MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService) getBean("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService");
					
		params.put("codigoPeriodo", form.getCodigoPeriodo());
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", params);
		params.put("oidPeriodo", oidPeriodo);
		
		Map moneda = serviceMoneda.getMoneda(params);
		params.put("numeroDecimales", MapUtils.getString(moneda, "numeroDecimales"));
		service.updateTipoCambio(params);

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		ProcesoPREActualizarTipoCambioForm form = (ProcesoPREActualizarTipoCambioForm) this.formProceso;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)	getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		// Periodo
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setCodigoPeriodoAnterior(controlFacturacion.getCodigoPeriodo());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setValidarProceso()
	 */
	@Override
	public String setValidarProceso() {
		String validationMessage = "";
		ProcesoPREActualizarTipoCambioForm form = (ProcesoPREActualizarTipoCambioForm) this.formProceso;
		
		double p1 = Double.valueOf(form.getCodigoPeriodo());
		double p2 = Double.valueOf(form.getCodigoPeriodoAnterior());
		double t1 = Double.valueOf(form.getTasaCambioActual());
		double t2 = Double.valueOf(form.getTasaCambioNueva());
		
		if(p1 < p2)
			validationMessage = this.getResourceMessage("procesoPREActualizarTipoCambioForm.msg.error.periodo");
		
		if(t1 <= 0 || t2 <= 0)
			validationMessage = this.getResourceMessage("procesoPREActualizarTipoCambioForm.msg.error.tasa");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaBatch = new HashMap();
		
		criteriaBatch.put("codigoPais", pais.getCodigo());
		criteriaBatch.put("codigoSistema", Constants.CODIGO_SISTEMA_PRE);
		criteriaBatch.put("codigoProcesoBatch", Constants.PRE_CODIGO_PROCESO_BATCH_ACTUALIZAR_TIPO_CAMBIO);
		criteriaBatch.put("valorAdicional1", form.getCodigoPeriodo()); //Campaña de Proceso
		criteriaBatch.put("fechaInicioProceso", DateUtil.getDate(new Date()));
				
		ProcesoBatchService serviceBatch = (ProcesoBatchService) getBean("scsicc.procesoBatchService");		
		List procesosActuales = serviceBatch.getProcesoBatchActuByCriteria(criteriaBatch);
		List procesosHistoricos = serviceBatch.getProcesoBatchHistoByCriteria(criteriaBatch);
		
		setMensajeConfirmacionEjecucion(this.getResourceMessage("confirmDialogProceso.mensaje"));
		if((procesosActuales != null && procesosActuales.size() > 0) || (procesosHistoricos != null && procesosHistoricos.size() > 0))
		{
			validationMessage = "";
			setMensajeConfirmacionEjecucion(this.getResourceMessage("procesoPREActualizarTipoCambioForm.msg.error.proceso") + "<br/><br/>" + this.getResourceMessage("confirmDialogProceso.mensaje"));			
		}
			
		return validationMessage;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map<String, Object> params, BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		
		ProcesoPREActualizarTipoCambioForm f = (ProcesoPREActualizarTipoCambioForm) this.formProceso;
		
		params.put("valorAdicional1", f.getCodigoPeriodo());
		params.put("valorAdicional2", f.getTasaCambioActual());
		params.put("valorAdicional3", f.getTasaCambioNueva());
		
		return params;
	}
}

	

