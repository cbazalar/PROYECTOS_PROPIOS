package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRRecepcionarCodigosVentaRechazadosForm;

@ManagedBean
@SessionScoped
public class InterfazOCRRecepcionarCodigosVentaRechazadosAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9081871078543918923L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazOCRRecepcionarCodigosVentaRechazadosForm formInterfaz = new InterfazOCRRecepcionarCodigosVentaRechazadosForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception{		
		// TODO Auto-generated method stub		
	}
	
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		
		return params;
	}
	
	
	
}
