package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAMRecepcionarInterfacesSAPMMForm;

/**
 * The Class InterfazSAMRecepcionarInterfacesSAPMMAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 10/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazSAMRecepcionarInterfacesSAPMMAction extends BaseInterfazAbstractAction {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7446055654986101813L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAMRecepcionarInterfacesSAPMMForm form = new InterfazSAMRecepcionarInterfacesSAPMMForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
	
	}

}
