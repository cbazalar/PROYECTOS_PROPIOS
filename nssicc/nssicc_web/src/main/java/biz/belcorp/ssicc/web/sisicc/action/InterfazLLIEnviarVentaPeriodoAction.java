package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLLIEnviarVentaPeriodoForm;

@ManagedBean
@SessionScoped
public class InterfazLLIEnviarVentaPeriodoAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7189695963264151773L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazLLIEnviarVentaPeriodoForm form= new InterfazLLIEnviarVentaPeriodoForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazLLIEnviarVentaPeriodoForm form= (InterfazLLIEnviarVentaPeriodoForm) this.formInterfaz;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		form.setCodigoPais(pais.getCodigo());
	}
	
	protected Map<String, Object>  prepareParamsBeforeExecute(Map params, BaseForm form) 
			throws Exception{
		params =  super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}
