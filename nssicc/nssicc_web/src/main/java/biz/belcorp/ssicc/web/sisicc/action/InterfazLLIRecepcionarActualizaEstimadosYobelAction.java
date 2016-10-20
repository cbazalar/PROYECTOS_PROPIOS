package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLLIRecepcionarActualizaEstimadosYobelForm;

@ManagedBean
@SessionScoped
public class InterfazLLIRecepcionarActualizaEstimadosYobelAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -8122666937073686834L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLLIRecepcionarActualizaEstimadosYobelForm form = new InterfazLLIRecepcionarActualizaEstimadosYobelForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
	}


}
