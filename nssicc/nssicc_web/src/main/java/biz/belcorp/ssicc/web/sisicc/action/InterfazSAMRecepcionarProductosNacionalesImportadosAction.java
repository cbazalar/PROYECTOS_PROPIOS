package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAMRecepcionarProductosNacionalesImportadosForm;

/**
 * The Class InterfazSAMRecepcionarProductosNacionalesImportadosAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 10/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazSAMRecepcionarProductosNacionalesImportadosAction extends BaseInterfazAbstractAction {
	
	private static final long serialVersionUID = 4429634129570566897L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSAMRecepcionarProductosNacionalesImportadosForm form = new InterfazSAMRecepcionarProductosNacionalesImportadosForm();
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
		
		InterfazSAMRecepcionarProductosNacionalesImportadosForm form = (InterfazSAMRecepcionarProductosNacionalesImportadosForm) this.formInterfaz;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());				
	}
	
}