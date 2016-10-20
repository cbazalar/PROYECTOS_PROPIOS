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
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRRecepcionarSolicitudCreditoMobilForm;

/**
 * The Class InterfazOCRRecepcionarSolicitudCreditoMobilAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 09/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazOCRRecepcionarSolicitudCreditoMobilAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 7944900249404264735L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazOCRRecepcionarSolicitudCreditoMobilForm form = new InterfazOCRRecepcionarSolicitudCreditoMobilForm();
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
		
		InterfazOCRRecepcionarSolicitudCreditoMobilForm form = (InterfazOCRRecepcionarSolicitudCreditoMobilForm) this.formInterfaz;
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
		
		InterfazOCRRecepcionarSolicitudCreditoMobilForm f = (InterfazOCRRecepcionarSolicitudCreditoMobilForm) form;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) this.getBean("sisicc.interfazSiCCService");
		Map criteriaInterfaz = new HashMap();
		criteriaInterfaz.put("codigoPais", f.getCodigoPais());
		criteriaInterfaz.put("codigoInterfaz", f.getCodigoInterfaz());		
		criteriaInterfaz.put("codigoDocumento", Constants.STO_TIPO_DOCUMENTO_SCC);
		String numLoteSTO = interfazSiCCService.getNumLoteSTO(criteriaInterfaz);		
		f.setNumLoteSTO(numLoteSTO);		
		params.put("numLoteSTO",numLoteSTO);
		params.put("host", this.getRequest().getRemoteHost());
		
		return params;
	}
	
}