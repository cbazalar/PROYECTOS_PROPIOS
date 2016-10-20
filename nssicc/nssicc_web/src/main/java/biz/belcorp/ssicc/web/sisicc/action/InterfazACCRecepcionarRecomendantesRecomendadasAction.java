package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazACCRecepcionarRecomendantesRecomendadasForm;

/**
 * The Class InterfazACCRecepcionarRecomendantesRecomendadasAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 22/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazACCRecepcionarRecomendantesRecomendadasAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 7390007865424879043L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazACCRecepcionarRecomendantesRecomendadasForm form = new InterfazACCRecepcionarRecomendantesRecomendadasForm();
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		form.setCodigoPeriodo(interfazSiCCService
				.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}	
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();

		HashMap criteria = new HashMap();
		InterfazACCRecepcionarRecomendantesRecomendadasForm interfazForm = (InterfazACCRecepcionarRecomendantesRecomendadasForm) this.formInterfaz;
		criteria.put("codigoPais", pais.getCodigo());

		interfazForm.setCodigoPeriodo(interfazSiCCService
				.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
	}

}
