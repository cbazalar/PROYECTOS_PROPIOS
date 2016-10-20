package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazCOBGenerarInformacionAcovediService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOBGenerarInformacionAcovediForm;

@ManagedBean
@SessionScoped
public class InterfazCOBGenerarInformacionAcovediAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = -6643189564046520945L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		InterfazCOBGenerarInformacionAcovediForm form = new InterfazCOBGenerarInformacionAcovediForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		InterfazCOBGenerarInformacionAcovediForm form = (InterfazCOBGenerarInformacionAcovediForm) this.formProceso;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		form.setCodigoPais(pais.getCodigo());
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		InterfazCOBGenerarInformacionAcovediService service = (InterfazCOBGenerarInformacionAcovediService) getBean("sisicc.interfazCOBGenerarInformacionAcovediService");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params = super.prepareParamsBeforeExecute(params, form);
		
		params.put("codigoUsuario", usuario.getLogin());
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		InterfazCOBGenerarInformacionAcovediForm form = (InterfazCOBGenerarInformacionAcovediForm) this.formProceso;
		InterfazCOBGenerarInformacionAcovediService service = (InterfazCOBGenerarInformacionAcovediService) getBean("sisicc.interfazCOBGenerarInformacionAcovediService");
				
		service.executeInterfazCOBGenerarInformacionAcovedi(params);
		
		return params;
	}
}