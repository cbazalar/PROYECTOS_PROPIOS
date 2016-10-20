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
import biz.belcorp.ssicc.web.sisicc.form.InterfazWEBRecepcionarFamiliaSeguraTelemercadoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class InterfazWEBRecepcionarFamiliaSeguraTelemercadoAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 3137527678661613954L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		params = super.prepareParamsBeforeExecute(params, form);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazWEBRecepcionarFamiliaSeguraTelemercadoForm f = (InterfazWEBRecepcionarFamiliaSeguraTelemercadoForm) form;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteriaInterfaz = new HashMap();
		criteriaInterfaz.put("codigoPais", pais.getCodigo());
		criteriaInterfaz.put("codigoInterfaz", f.getCodigoInterfaz());

		criteriaInterfaz.put("codigoDocumento",
				Constants.STO_TIPO_DOCUMENTO_FAS);
		String numLoteSTO = interfazSiCCService.getNumLoteSTO(criteriaInterfaz);

		f.setNumLoteSTO(numLoteSTO);

		params.put("numLoteSTO", numLoteSTO);

		params.put("codigoProcesoBatch",
				this.mPantallaPrincipalBean.getCodigoProcesoBatch());
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazWEBRecepcionarFamiliaSeguraTelemercadoForm form = new InterfazWEBRecepcionarFamiliaSeguraTelemercadoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

	}
}