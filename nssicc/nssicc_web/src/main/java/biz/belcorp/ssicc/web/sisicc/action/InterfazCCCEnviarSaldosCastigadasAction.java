package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCCCEnviarSaldosCastigadasForm;

@ManagedBean
@SessionScoped
public class InterfazCCCEnviarSaldosCastigadasAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCCCEnviarSaldosCastigadasForm interfazCCCEnviarSaldosCastigadasForm = new InterfazCCCEnviarSaldosCastigadasForm();
		return interfazCCCEnviarSaldosCastigadasForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}
	}
}