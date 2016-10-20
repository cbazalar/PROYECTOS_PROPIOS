package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazECMRecepcionarMovimientosAbonosConsultoraForm;

@ManagedBean
@SessionScoped
public class InterfazECMRecepcionarMovimientosAbonosConsultoraAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 2225728943007215583L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazECMRecepcionarMovimientosAbonosConsultoraForm interfazForm = new InterfazECMRecepcionarMovimientosAbonosConsultoraForm();
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		InterfazECMRecepcionarMovimientosAbonosConsultoraForm f = (InterfazECMRecepcionarMovimientosAbonosConsultoraForm) this.formInterfaz;
		f.setFechaGenerarDate(new Date(System.currentTimeMillis()));	
	}

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazECMRecepcionarMovimientosAbonosConsultoraForm f = (InterfazECMRecepcionarMovimientosAbonosConsultoraForm) this.formInterfaz;
		String fecha=DateUtil.convertDateToString(f.getFechaGenerarDate());
		f.setFechaGenerar(fecha);
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}
