package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAMRecepcionarLotesProductoForm;

/**
 * The Class InterfazSAMRecepcionarLotesProductoAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 10/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazSAMRecepcionarLotesProductoAction extends BaseInterfazAbstractAction {
	private static final long serialVersionUID = 1103610875730081938L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAMRecepcionarLotesProductoForm form = new InterfazSAMRecepcionarLotesProductoForm();
		
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

        InterfazSAMRecepcionarLotesProductoForm f =(InterfazSAMRecepcionarLotesProductoForm) this.formInterfaz;
        Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());	
    }
	
}