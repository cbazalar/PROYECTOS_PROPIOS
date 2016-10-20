package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLARRecepcionarResultadoChequeoForm;

/**
 * The Class InterfazLARRecepcionarResultadoChequeoAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 12/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazLARRecepcionarResultadoChequeoAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 8368497348706193243L;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLARRecepcionarResultadoChequeoForm form = new InterfazLARRecepcionarResultadoChequeoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {	
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}
		
		InterfazLARRecepcionarResultadoChequeoForm f = (InterfazLARRecepcionarResultadoChequeoForm) this.formInterfaz;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
	}

}