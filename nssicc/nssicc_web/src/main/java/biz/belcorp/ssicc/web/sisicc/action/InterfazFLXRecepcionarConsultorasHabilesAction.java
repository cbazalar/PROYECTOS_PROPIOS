package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazFLXRecepcionarConsultorasHabilesForm;

@ManagedBean
@SessionScoped
public class InterfazFLXRecepcionarConsultorasHabilesAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = -8712937853010973606L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazFLXRecepcionarConsultorasHabilesForm interfazForm= new  InterfazFLXRecepcionarConsultorasHabilesForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception{
	 
		if (log.isDebugEnabled()) {
	            log.debug("Entering 'setViewAttributes' method");
	        }	        
	      
	    }
	
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);						
		return params;
    }
	
}
