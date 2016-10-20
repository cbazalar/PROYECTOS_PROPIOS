package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazADARecepcionarMovimientosDescuentosPersonalForm;

@ManagedBean
@SessionScoped
public class InterfazADARecepcionarMovimientosDescuentosPersonalAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -245457814368036999L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazADARecepcionarMovimientosDescuentosPersonalForm formInterfaz = new InterfazADARecepcionarMovimientosDescuentosPersonalForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
