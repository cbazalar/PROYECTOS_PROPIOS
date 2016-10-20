package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPRERecepcionarOfertasExportadasForm;

@ManagedBean
@SessionScoped
public class InterfazPRERecepcionarOfertasExportadasAction extends BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8928240612872820338L;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazPRERecepcionarOfertasExportadasForm f = new InterfazPRERecepcionarOfertasExportadasForm();
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
	            log.debug("Entering 'setViewAttributes' method");
	    }
		
//		String procesoBatch = this.getParametrosPantalla().get("codigoProcesoBatch");
//		String interfaz = this.getParametrosPantalla().get("codigoInterfaz");
		
		//this.mostrarBotonExecute = true;
//	       String codigoProcesoBatch = request.getParameter("codigoProcesoBatch");
//	       
//			request.getSession().setAttribute("codigoProcesoBatch", codigoProcesoBatch);
//	        log.debug(getViewForward());
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		params.put("codigoPais", pais.getCodigo());
		
		return params;
	}


}
