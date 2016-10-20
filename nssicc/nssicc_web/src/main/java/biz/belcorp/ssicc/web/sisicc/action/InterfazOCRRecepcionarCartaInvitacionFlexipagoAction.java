package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRRecepcionarCartaInvitacionFlexipagoForm;

@ManagedBean
@SessionScoped
public class InterfazOCRRecepcionarCartaInvitacionFlexipagoAction extends
		BaseInterfazAbstractAction {


	private static final long serialVersionUID = -6927807789080156566L;


	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception{
		
		params = super.prepareParamsBeforeExecute(params,form);	
		
		InterfazOCRRecepcionarCartaInvitacionFlexipagoForm f = (InterfazOCRRecepcionarCartaInvitacionFlexipagoForm)this.formInterfaz;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaInterfaz = new HashMap();
		criteriaInterfaz.put("codigoPais", pais.getCodigo());
		criteriaInterfaz.put("codigoInterfaz", f.getCodigoInterfaz());
		String codDocumento = interfazSiCCService.getCodigoDocumento(criteriaInterfaz);
		criteriaInterfaz.put("codigoDocumento", codDocumento);
		String numLoteSTO = interfazSiCCService.getNumLoteSTO(criteriaInterfaz);				
		f.setNumLoteSTO(numLoteSTO);		
		params.put("numLoteSTO",numLoteSTO);
		params.put("codigoProcesoBatch", this.mPantallaPrincipalBean.getCodigoProcesoBatch());
		return params;
	}

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazOCRRecepcionarCartaInvitacionFlexipagoForm form = new InterfazOCRRecepcionarCartaInvitacionFlexipagoForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
