package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYEEnviarTipoProductoCatalogoForm;

@ManagedBean
@SessionScoped
public class InterfazMYEEnviarTipoProductoCatalogoAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6130457171596970629L;

	private List siccMarcaList;
	private List siccCanalList;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		InterfazMYEEnviarTipoProductoCatalogoForm formInterfaz = new InterfazMYEEnviarTipoProductoCatalogoForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		InterfazMYEEnviarTipoProductoCatalogoForm f = (InterfazMYEEnviarTipoProductoCatalogoForm) this.formInterfaz;
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		this.siccMarcaList =  svc.getMarcas();		
		this.siccCanalList =  svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());	
		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);		
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params =  super.prepareParamsBeforeExecute(params, form);
		
		return params;
	}
	
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}
}
