package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSATRecepcionarCoberturaCodigoTerritorialForm;

@ManagedBean
@SessionScoped
public class InterfazSATRecepcionarCoberturaCodigoTerritorialAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3682301432527977163L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazSATRecepcionarCoberturaCodigoTerritorialForm form=new InterfazSATRecepcionarCoberturaCodigoTerritorialForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) 
			throws Exception{	
		params=super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	

}
