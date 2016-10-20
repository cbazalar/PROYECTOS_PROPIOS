package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSATRecepcionarDivisionArmadoCDPForm;

@ManagedBean
@SessionScoped
public class InterfazSATRecepcionarOrdenImpresionAPESATAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = -8016794935673136083L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSATRecepcionarDivisionArmadoCDPForm interfazForm = new InterfazSATRecepcionarDivisionArmadoCDPForm();
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		InterfazSATRecepcionarDivisionArmadoCDPForm f = (InterfazSATRecepcionarDivisionArmadoCDPForm) this.formInterfaz;
		Pais pais=mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {		
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}
}
