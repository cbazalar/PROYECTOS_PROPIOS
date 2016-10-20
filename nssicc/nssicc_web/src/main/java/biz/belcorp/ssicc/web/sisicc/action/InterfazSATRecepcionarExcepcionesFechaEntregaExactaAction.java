package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSATRecepcionarExcepcionesFechaEntregaExactaForm;

@ManagedBean
@SessionScoped
public class InterfazSATRecepcionarExcepcionesFechaEntregaExactaAction extends BaseInterfazAbstractAction{

	
	private static final long serialVersionUID = -4896682634475699097L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSATRecepcionarExcepcionesFechaEntregaExactaForm interfazForm = new InterfazSATRecepcionarExcepcionesFechaEntregaExactaForm();
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}		
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {		
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}
}
