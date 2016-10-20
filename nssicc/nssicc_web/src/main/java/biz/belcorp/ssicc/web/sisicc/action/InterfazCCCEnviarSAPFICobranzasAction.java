/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCCCEnviarSAPFICobranzasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class InterfazCCCEnviarSAPFICobranzasAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCCCEnviarSAPFICobranzasForm interfazCCCEnviarSaldosCastigadasForm = new InterfazCCCEnviarSAPFICobranzasForm();
		return interfazCCCEnviarSaldosCastigadasForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}
		InterfazCCCEnviarSAPFICobranzasForm f = (InterfazCCCEnviarSAPFICobranzasForm) this.formInterfaz;
		f.setFechaProcesoHastaD(new Date(System.currentTimeMillis()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map,
	 * biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazCCCEnviarSAPFICobranzasForm f = (InterfazCCCEnviarSAPFICobranzasForm) this.formInterfaz;
		f.setFechaProcesoHasta(DateUtil.getDate(f.getFechaProcesoHastaD()));
		params.put("fechaProcesoHasta", f.getFechaProcesoHasta());

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'prepareParamsBeforeExecute' method");
			log.debug(params.toString());
		}
		return params;
	}
}