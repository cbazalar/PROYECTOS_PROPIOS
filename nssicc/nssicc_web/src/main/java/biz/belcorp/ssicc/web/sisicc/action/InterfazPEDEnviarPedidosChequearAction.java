package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazLARService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPEDEnviarPedidosChequearForm;

/**
 * The Class InterfazPEDEnviarPedidosChequearAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 12/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazPEDEnviarPedidosChequearAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -5424988687215194587L;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazPEDEnviarPedidosChequearForm form = new InterfazPEDEnviarPedidosChequearForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {	
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}
		
		InterfazPEDEnviarPedidosChequearForm f = (InterfazPEDEnviarPedidosChequearForm) this.formInterfaz;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params =  super.prepareParamsBeforeExecute(params, form);
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);		
		criteriaOperacion.put("estadoCampanha",Constants.NUMERO_CERO);
		criteriaOperacion.put("indicadorActiva",Constants.ESTADO_ACTIVO);
		
		MantenimientoOCRPedidoControlFacturacionService serviceFact = 
			(MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteriaOperacion);
		
        params.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
        params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        params.put("fecha", controlFacturacion.getFechaProceso());
		
		return params;		
	}
	
    /* (non-Javadoc)
    * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
    */
    protected void beforeExecuteInterfaz(Map params) {
    	
    	super.beforeExecuteInterfaz(params);
    	log.debug("Entering 'reload' method");
        
        // Obtenemos la referencia al service
    	InterfazLARService svc = (InterfazLARService) getBean("sisicc.interfazLARService");
       
        //Se llama al procedure para realizar el proceso de Generacion de Pedidos a Chequear
        if (log.isDebugEnabled()) {
            log.debug("Realizando la generacion de pedidos a chequear ...");
        }
    	svc.executeGenerarPedidosChequear(params);
    	if (log.isDebugEnabled()) {
            log.debug("Generacion de pedidos a chequear finalizada");
        }
    	
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult)
     */
    protected void afterExecuteInterfaz(Map params,	InterfazExecutionResult interfazExecutionResult) throws Exception{
    	super.afterExecuteInterfaz(params, interfazExecutionResult);
    	log.debug("Entering 'reload' method");
    	
    	// Validamos el resultado de la ejecucion
        if(interfazExecutionResult.ejecucionCompletada()) {
        	InterfazLARService svc = (InterfazLARService) getBean("sisicc.interfazLARService");
        	//Se actualiza el indicador de envio a yobel
        	svc.executeActualizarPedidosChequear(params);
        }
    	
    }
   
}