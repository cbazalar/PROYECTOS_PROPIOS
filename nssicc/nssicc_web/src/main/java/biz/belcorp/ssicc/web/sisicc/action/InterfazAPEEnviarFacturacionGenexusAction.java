package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.ape.ProcesoAPEImportarOrdenAnaquelesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazAPEEnviarFacturacionGenexusForm;


@ManagedBean
@SessionScoped
public class InterfazAPEEnviarFacturacionGenexusAction extends BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8232826414417864941L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazAPEEnviarFacturacionGenexusForm form=new InterfazAPEEnviarFacturacionGenexusForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazAPEEnviarFacturacionGenexusForm form=(InterfazAPEEnviarFacturacionGenexusForm) this.formInterfaz;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
    	MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
    	PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
    	// Carga el periodo actual
    	form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
    	form.setFechaFact(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));    
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) 
			throws Exception{

			InterfazAPEEnviarFacturacionGenexusForm interfazForm=(InterfazAPEEnviarFacturacionGenexusForm) this.formInterfaz;
			params = super.prepareParamsBeforeExecute(params, form);
			params.put("tipoProceso", (interfazForm.getTipoProceso()));
			params.put("fechaFacturacion", DateUtil.convertDateToString("dd/MM/yyyy", interfazForm.getFechaFact()));
			return params;
		}

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
     */
    protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception{
    	super.afterExecuteInterfaz(params, interfazExecutionResult);
		if (log.isDebugEnabled()) {
			log.debug("Entering 'afterExecuteInterfaz' method");
			log.debug(params);
		}
		ProcesoAPEImportarOrdenAnaquelesService service = (ProcesoAPEImportarOrdenAnaquelesService)getBean("spusicc.procesoAPEImportarOrdenAnaquelesService");
		if (interfazExecutionResult.ejecucionCompletada()) {
			service.actualizarIndicador(params);
		}
		log.debug("Fin 'afterExecuteInterfaz' method");
	}
}
