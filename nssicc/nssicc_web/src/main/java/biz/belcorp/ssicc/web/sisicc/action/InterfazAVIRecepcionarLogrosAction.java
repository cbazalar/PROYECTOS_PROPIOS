package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazAVIRecepcionarLogrosForm;

@ManagedBean
@SessionScoped
public class InterfazAVIRecepcionarLogrosAction extends BaseInterfazAbstractAction{

	
	private static final long serialVersionUID = 8112851250228035586L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazAVIRecepcionarLogrosForm interfazForm = new InterfazAVIRecepcionarLogrosForm();
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
