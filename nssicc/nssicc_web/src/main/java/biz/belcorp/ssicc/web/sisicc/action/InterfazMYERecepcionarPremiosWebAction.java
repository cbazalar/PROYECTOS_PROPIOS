package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYERecepcionarPremiosWebForm;

@ManagedBean
@SessionScoped
public class InterfazMYERecepcionarPremiosWebAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -998394945976374282L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazMYERecepcionarPremiosWebForm form= new  InterfazMYERecepcionarPremiosWebForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
	}
	
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);	
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		params.put("codigousuario", usuario.getLogin());
		return params;
    }

}


