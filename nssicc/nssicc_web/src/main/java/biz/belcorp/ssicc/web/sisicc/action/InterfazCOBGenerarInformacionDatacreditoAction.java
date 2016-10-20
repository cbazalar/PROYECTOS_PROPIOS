package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazCOBGenerarInformacionDatacreditoService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOBGenerarInformacionDatacreditoForm;

@ManagedBean
@SessionScoped
public class InterfazCOBGenerarInformacionDatacreditoAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9023031854296306069L;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		InterfazCOBGenerarInformacionDatacreditoForm form=new InterfazCOBGenerarInformacionDatacreditoForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazCOBGenerarInformacionDatacreditoForm form= (InterfazCOBGenerarInformacionDatacreditoForm) this.formProceso;
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
		
	}
	
	

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		InterfazCOBGenerarInformacionDatacreditoForm f =(InterfazCOBGenerarInformacionDatacreditoForm)this.formProceso;
		InterfazCOBGenerarInformacionDatacreditoService service = (InterfazCOBGenerarInformacionDatacreditoService) getBean("sisicc.interfazCOBGenerarInformacionDatacreditoService");
		
		Map criteria = new HashMap();
		criteria.put("codigoUsuario", this.mPantallaPrincipalBean.getCurrentUser().getLogin());
		service.executeInterfazCOBGenerarInformacionDatacredito(criteria);
		
		return params;
	}
	
}



