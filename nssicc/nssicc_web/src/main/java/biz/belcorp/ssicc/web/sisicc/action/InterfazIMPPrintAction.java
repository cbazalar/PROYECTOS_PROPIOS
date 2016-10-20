/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;


import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazIMPPrintForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIMPPrintAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazIMPPrintAction extends BaseInterfazAbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3203189676172552747L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazIMPPrintForm interfazIMPPrintForm = new InterfazIMPPrintForm();
		return interfazIMPPrintForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'setViewAttributes' method");
	    }
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		 
		InterfazIMPPrintForm interfazDATForm = (InterfazIMPPrintForm) this.formInterfaz ;
	    InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

          
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		InterfazIMPPrintForm f = (InterfazIMPPrintForm) this.formInterfaz ;
		params =  super.prepareParamsBeforeExecute(params, form);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		params.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());
		
		String fechaFacturacion = DateUtil.convertDateToString(f.getFechaFacturacionDate());
		params.put("fechaFacturacion", fechaFacturacion);
		return params;
	}
}
