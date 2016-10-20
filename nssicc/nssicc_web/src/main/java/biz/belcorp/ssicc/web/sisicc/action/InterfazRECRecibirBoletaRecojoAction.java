package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRECRecibirBoletaRecojoForm;

@ManagedBean
@SessionScoped
public class InterfazRECRecibirBoletaRecojoAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 36507450651039471L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazRECRecibirBoletaRecojoForm formInterfaz = new InterfazRECRecibirBoletaRecojoForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		// TODO Auto-generated method stub
		
	}

}
