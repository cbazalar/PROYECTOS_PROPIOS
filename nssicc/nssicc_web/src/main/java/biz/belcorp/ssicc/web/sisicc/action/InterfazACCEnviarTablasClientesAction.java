package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazACCEnviarTablasClientesForm;

@ManagedBean
@SessionScoped
public class InterfazACCEnviarTablasClientesAction  extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2636841696490162141L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazACCEnviarTablasClientesForm formInterfaz = new InterfazACCEnviarTablasClientesForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
		HashMap criteria = new HashMap();
		
		InterfazACCEnviarTablasClientesForm interfazForm = (InterfazACCEnviarTablasClientesForm) this.formInterfaz;
		criteria.put("codigoPais", usuario.getPais().getCodigo());
		// por defecto MARCA TODAS
		// por defecto CANAL VENTA DIRECTA

		interfazForm.setCodigoPeriodo(interfazSiCCService
				.getPeriodoDefaultByPaisCanal(usuario.getPais().getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		return params;		
	}

}
