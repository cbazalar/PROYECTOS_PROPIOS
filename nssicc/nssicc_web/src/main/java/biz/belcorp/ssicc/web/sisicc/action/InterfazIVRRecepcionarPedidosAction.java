package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazIVRRecepcionarPedidosForm;

/**
 * The Class InterfazIVRRecepcionarPedidosAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 23/12/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class InterfazIVRRecepcionarPedidosAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -3054015909966201650L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazIVRRecepcionarPedidosForm form = new InterfazIVRRecepcionarPedidosForm();
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
		
		InterfazIVRRecepcionarPedidosForm form = (InterfazIVRRecepcionarPedidosForm) this.formInterfaz;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		form.setCodigoPais(pais.getCodigo());
	}
	   
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {

		params =  super.prepareParamsBeforeExecute(params, form);
		
		InterfazIVRRecepcionarPedidosForm f = (InterfazIVRRecepcionarPedidosForm) form;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais = f.getCodigoPais();

		ProcesoSTOService stoService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");		
		String numLoteSTO = stoService.updateLoteSTO(new TipoDocumentoDigitadoPK(codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC));
		f.setNumLoteSTO(numLoteSTO);

		params.put("codigoPais", codigoPais);
		params.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("indOrigen", Constants.STO_ORIGEN_IVR);	
		params.put("numLoteSTO", numLoteSTO); 

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazSiCCService.updateInterfazProcesoBatch(params);

		return params;        
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult)  throws Exception{
		super.afterExecuteInterfaz(params, interfazExecutionResult);

        String codigoPais = (String)params.get("codigoPais");
        ProcesoSTOExecutionService procesoSTOExecutionService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
    	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC,Constants.STO_ACCI_VALI_AUTO);    			
        procesoSTOExecutionService.execute(accionTipoDocumento,params, null);
	}

}