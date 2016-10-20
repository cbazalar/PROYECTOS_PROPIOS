package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazAPERecepcionarAnaquelesForm;

@ManagedBean
@SessionScoped
public class InterfazAPERecepcionarAnaquelesAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3055016928642829867L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazAPERecepcionarAnaquelesForm form=new InterfazAPERecepcionarAnaquelesForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute (Map params, BaseForm form) throws Exception{
		
		params=super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}
