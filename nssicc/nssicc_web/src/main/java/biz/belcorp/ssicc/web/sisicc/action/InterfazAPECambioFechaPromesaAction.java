package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazAPECambioFechaPromesaForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazAPEPedidosDespachadosForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarCostoCajaAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazAPECambioFechaPromesaAction extends BaseInterfazAbstractAction
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7419804856622090516L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazAPECambioFechaPromesaForm form = new InterfazAPECambioFechaPromesaForm(); 
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params =  super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}

