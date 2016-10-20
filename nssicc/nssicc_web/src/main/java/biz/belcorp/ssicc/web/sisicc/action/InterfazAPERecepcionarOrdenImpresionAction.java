package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazAPERecepcionarOrdenImpresionForm;

@ManagedBean
@SessionScoped
public class InterfazAPERecepcionarOrdenImpresionAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8069961181175383555L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazAPERecepcionarOrdenImpresionForm interfazForm = new InterfazAPERecepcionarOrdenImpresionForm();
		
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
