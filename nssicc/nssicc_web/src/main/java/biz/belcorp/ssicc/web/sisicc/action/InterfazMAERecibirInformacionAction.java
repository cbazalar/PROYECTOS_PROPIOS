package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMAERecibirInformacionForm;


@ManagedBean
@SessionScoped
public class InterfazMAERecibirInformacionAction extends BaseInterfazAbstractAction{



	/**
	 * 
	 */
	private static final long serialVersionUID = -1362358884562287794L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazMAERecibirInformacionForm form= new InterfazMAERecibirInformacionForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazMAERecibirInformacionForm interfazOCRForm = (InterfazMAERecibirInformacionForm) this.formInterfaz ; 
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		// Carga Periodo
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        interfazOCRForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		InterfazMAERecibirInformacionForm f = (InterfazMAERecibirInformacionForm) this.formInterfaz ;
		
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		
		String codigoPais = pais.getCodigo();
		
		params.put("codigoPais", codigoPais);
		
		params.put("usuario", usuario);
		params.put("codigoUsuario", usuario.getLogin());		
				

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazSiCCService.updateInterfazProcesoBatch(params);
		
		

		return params;

	}
	
}

