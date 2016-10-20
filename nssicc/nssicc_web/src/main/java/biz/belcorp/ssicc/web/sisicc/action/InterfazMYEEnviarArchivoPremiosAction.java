package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYEEnviarArchivoPremiosForm;


@ManagedBean
@SessionScoped
public class InterfazMYEEnviarArchivoPremiosAction  extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5163353850663550963L;

	
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazMYEEnviarArchivoPremiosForm formInterfaz = new InterfazMYEEnviarArchivoPremiosForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
	}

}
