package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLARRecepcionarActualizaSecuenciacionzonasForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazMYEEnviarMaestrosAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
@ManagedBean
@SessionScoped
public class InterfazLARRecepcionarActualizaSecuenciacionzonasAction extends
		BaseInterfazAbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = 835393671527101683L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLARRecepcionarActualizaSecuenciacionzonasForm form = new InterfazLARRecepcionarActualizaSecuenciacionzonasForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {

	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}

