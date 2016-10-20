package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazFLXEnvioResultadoProgramasForm;

@ManagedBean
@SessionScoped
public class InterfazFLXEnvioResultadoProgramasAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3487548867212415043L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazFLXEnvioResultadoProgramasForm form=new InterfazFLXEnvioResultadoProgramasForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazFLXEnvioResultadoProgramasForm form=(InterfazFLXEnvioResultadoProgramasForm) this.formInterfaz;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaPeriodo = new HashMap();
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.NUMERO_UNO);
	    PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
	    log.debug("periodo actual " + controlFacturacion.getCodigoPeriodo());
	    
	    form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) 
			throws Exception{
		
    	params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}
