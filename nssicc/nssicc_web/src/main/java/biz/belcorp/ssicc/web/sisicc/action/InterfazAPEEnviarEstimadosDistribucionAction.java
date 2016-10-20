package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazAPEEnviarEstimadosDistribucionForm;

@ManagedBean
@SessionScoped
public class InterfazAPEEnviarEstimadosDistribucionAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8552265137339552461L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazAPEEnviarEstimadosDistribucionForm formInterfaz = new InterfazAPEEnviarEstimadosDistribucionForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
			
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		
		return params;
	}

}
