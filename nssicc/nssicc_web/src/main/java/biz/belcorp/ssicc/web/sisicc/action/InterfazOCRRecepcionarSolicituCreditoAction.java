/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
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
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRRecepcionarSolicitudCreditoForm;

/**
 * Clase Action de la Interfaz Recepcionar Solicitud de Credito.
 * 
 * @author <a href="mailto:llizana@belcorp.biz">Leonardo Lizana</a>
 */
@ManagedBean
@SessionScoped
public class InterfazOCRRecepcionarSolicituCreditoAction extends BaseInterfazAbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3203189676172552747L;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazOCRRecepcionarSolicitudCreditoForm();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'setViewAttributes' method");
	    }

	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		
		Map queryParams = super.prepareParamsBeforeExecute(params, form);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazOCRRecepcionarSolicitudCreditoForm f = (InterfazOCRRecepcionarSolicitudCreditoForm) this.formInterfaz;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteriaInterfaz = new HashMap();
		criteriaInterfaz.put("codigoPais", pais.getCodigo());
		criteriaInterfaz.put("codigoInterfaz", f.getCodigoInterfaz());

		String codDocumento = interfazSiCCService.getCodigoDocumento(criteriaInterfaz);
		criteriaInterfaz.put("codigoDocumento", codDocumento);
		String numLoteSTO = interfazSiCCService.getNumLoteSTO(criteriaInterfaz);

		f.setNumLoteSTO(numLoteSTO);
		
		queryParams.put("numLoteSTO",numLoteSTO);
		
		return queryParams;
	}
	

	
    

}
