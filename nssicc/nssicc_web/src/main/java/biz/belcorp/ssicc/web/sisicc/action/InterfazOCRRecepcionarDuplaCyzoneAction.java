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
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRRecepcionarDuplaCyzoneForm;


/**
 * The Class InterfazOCRRecepcionarDuplaCyzoneAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 04/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazOCRRecepcionarDuplaCyzoneAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 7541897270025274734L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazOCRRecepcionarDuplaCyzoneForm form = new InterfazOCRRecepcionarDuplaCyzoneForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		InterfazOCRRecepcionarDuplaCyzoneForm form = (InterfazOCRRecepcionarDuplaCyzoneForm) this.formInterfaz;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params =  super.prepareParamsBeforeExecute(params, form);
		
		InterfazOCRRecepcionarDuplaCyzoneForm f = (InterfazOCRRecepcionarDuplaCyzoneForm) this.formInterfaz;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteriaInterfaz = new HashMap();
		criteriaInterfaz.put("codigoPais", f.getCodigoPais());
		criteriaInterfaz.put("codigoInterfaz", f.getCodigoInterfaz());

		String codDocumento = interfazSiCCService.getCodigoDocumento(criteriaInterfaz);
		criteriaInterfaz.put("codigoDocumento", codDocumento);
		String numLoteSTO = interfazSiCCService.getNumLoteSTO(criteriaInterfaz);
		
		f.setNumLoteSTO(numLoteSTO);
		
		params.put("numLoteSTO",numLoteSTO);
		params.put("codigoDocumento", Constants.CODIGO_DOCUMENTO_STO_DUPLA_CYZONE);
		
		return params;
	}

	
}