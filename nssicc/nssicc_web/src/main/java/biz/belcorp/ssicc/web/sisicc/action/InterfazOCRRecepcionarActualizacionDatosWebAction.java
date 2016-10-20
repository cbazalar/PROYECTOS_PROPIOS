package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRRecepcionarActualizacionDatosWebForm;

@ManagedBean
@SessionScoped
public class InterfazOCRRecepcionarActualizacionDatosWebAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5406291699759952525L;

	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazOCRRecepcionarActualizacionDatosWebForm formInterfaz = new InterfazOCRRecepcionarActualizacionDatosWebForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		
		Map queryParams = super.prepareParamsBeforeExecute(params, form);
		
		InterfazOCRRecepcionarActualizacionDatosWebForm f = (InterfazOCRRecepcionarActualizacionDatosWebForm) form;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) this.getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaInterfaz = new HashMap();
		criteriaInterfaz.put("codigoPais", pais.getCodigo());
		criteriaInterfaz.put("codigoInterfaz", f.getCodigoInterfaz());

		interfazSiCCService.getCodigoDocumento(criteriaInterfaz);
		criteriaInterfaz.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_SAD);
		String numLoteSTO = interfazSiCCService.getNumLoteSTO(criteriaInterfaz);
		
		f.setNumLoteSTO(numLoteSTO);
		queryParams.put("numLoteSTO",numLoteSTO);
			
		return queryParams;
	}
}
