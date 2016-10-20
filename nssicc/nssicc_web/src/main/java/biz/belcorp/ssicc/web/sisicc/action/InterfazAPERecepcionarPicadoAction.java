package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazAPERecepcionarPicadoForm;

@ManagedBean
@SessionScoped
public class InterfazAPERecepcionarPicadoAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5870946556311566381L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazAPERecepcionarPicadoForm interfazForm = new InterfazAPERecepcionarPicadoForm();
		
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
