package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.spisicc.ProcesoImpresionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCGenerarInformacionBuroCrediticioForm;

/**
 * @author jpulido
 *
 */
@ManagedBean
@SessionScoped
public class ProcesoCCCGenerarInformacionBuroCrediticioAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#executeProcessBeforeInterfaz(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcessBeforeInterfaz(
			Map<String, Object> params) throws Exception {
		ProcesoImpresionService service = (ProcesoImpresionService) getBean("spisicc.procesoImpresionService");

		if (service.validacionLimiteTiempoEjecucionProceso()) {
			service.executeCalculoInterMora();
		}
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoCCCGenerarInformacionBuroCrediticioForm form = new ProcesoCCCGenerarInformacionBuroCrediticioForm();
		return form;
	}
}