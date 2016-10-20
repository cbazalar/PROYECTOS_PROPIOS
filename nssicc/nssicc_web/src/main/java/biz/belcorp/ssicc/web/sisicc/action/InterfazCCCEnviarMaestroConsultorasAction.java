package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCCCEnviarMaestroConsultorasForm;


/**
 * The Class InterfazCCCEnviarMaestroConsultorasAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 03/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazCCCEnviarMaestroConsultorasAction extends BaseInterfazAbstractAction {
	
	private static final long serialVersionUID = -8365133901799726120L;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCCCEnviarMaestroConsultorasForm interfazCCCEnviarForm = new InterfazCCCEnviarMaestroConsultorasForm();
		return interfazCCCEnviarForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {	
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}
		
		InterfazCCCEnviarMaestroConsultorasForm f = (InterfazCCCEnviarMaestroConsultorasForm) this.formInterfaz;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
	}
}
