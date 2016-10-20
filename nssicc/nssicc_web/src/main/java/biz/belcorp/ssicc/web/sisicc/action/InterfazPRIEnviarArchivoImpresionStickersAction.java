package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPRIEnviarArchivoImpresionStickersForm;


@ManagedBean
@SessionScoped
public class InterfazPRIEnviarArchivoImpresionStickersAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = -8784636157506685655L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazPRIEnviarArchivoImpresionStickersForm form = new InterfazPRIEnviarArchivoImpresionStickersForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
