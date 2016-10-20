package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRUVEnviarRegistroUnicoVentasForm;


@ManagedBean
@SessionScoped
public class InterfazRUVEnviarRegistroUnicoVentasAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -954651244236994401L;
	
	

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazRUVEnviarRegistroUnicoVentasForm formInterfaz = new InterfazRUVEnviarRegistroUnicoVentasForm(); 
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		
	}

}
