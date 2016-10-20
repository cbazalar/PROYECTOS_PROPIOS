package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.InterfazPREService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.InterfazPRECargaMatrizPlanitForm;

@ManagedBean
@SessionScoped
public class InterfazPRECargaMatrizPlanitAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7656016647042574819L;
	
	

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazPRECargaMatrizPlanitForm f = new InterfazPRECargaMatrizPlanitForm();
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		return super.prepareParamsBeforeExecute(params, form);		
	}
	
	@Override
	protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception {
		// TODO Auto-generated method stub
		InterfazPREService interfazPREService = (InterfazPREService) this.getBean("sisicc.interfazPREService");
		interfazPREService.envioCorreo(params);
		
		int cantiError = interfazPREService.verificarErrorMatrizPlanit(params);
		if(cantiError > 0)
			throw new Exception(this.getResourceMessage("interfazPRECargaMatrizPlanitForm.errorCargaArchivos"));
	}

}
