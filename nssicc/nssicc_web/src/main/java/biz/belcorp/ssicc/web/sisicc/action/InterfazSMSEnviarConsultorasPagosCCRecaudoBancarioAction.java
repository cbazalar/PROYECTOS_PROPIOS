package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSMSEnviarConsultorasPagosCCRecaudoBancarioForm;

@ManagedBean
@SessionScoped
public class InterfazSMSEnviarConsultorasPagosCCRecaudoBancarioAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 403282976068972262L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSMSEnviarConsultorasPagosCCRecaudoBancarioForm f = new InterfazSMSEnviarConsultorasPagosCCRecaudoBancarioForm();
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}	
		this.esInterfazSalida = true;
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		InterfazSMSEnviarConsultorasPagosCCRecaudoBancarioForm f = (InterfazSMSEnviarConsultorasPagosCCRecaudoBancarioForm) formInterfaz;
		
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("fechaProceso", DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, f.getDfechaProceso()));
		params.put("flagNovedades", Constants.NUMERO_CERO);
		
		return params;
	}

}
