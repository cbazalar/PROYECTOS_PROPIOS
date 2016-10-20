package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRRecepcionarServiciosPostventasForm;

@ManagedBean
@SessionScoped
public class InterfazOCRRecepcionarServiciosPostventasAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7702811265839502287L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazOCRRecepcionarServiciosPostventasForm formInterfaz = new InterfazOCRRecepcionarServiciosPostventasForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteriaInterfaz = new HashMap();
		criteriaInterfaz.put("codigoPais", pais.getCodigo());

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		String codDocumento = interfazSiCCService.getCodigoDocumento(criteriaInterfaz);

		criteriaInterfaz.put("codigoDocumento", codDocumento);

		TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(pais.getCodigo(),Constants.STO_TIPO_DOCUMENTO_SPVC);
		ProcesoSTOService stoService = (ProcesoSTOService)getBean("spusicc.procesoSTOService");
		String numLoteSTO=stoService.updateLoteSTO(tipoDocumentoDigitadoPK);
		
		InterfazOCRRecepcionarServiciosPostventasForm f =(InterfazOCRRecepcionarServiciosPostventasForm)form;
		f.setNumLoteSTO(numLoteSTO);
		
		params.put("numLoteSTO",numLoteSTO);		
		
		return params;
	}

}
