package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazEVIEnviarEjecutivoVirtualForm;


/**
 * The Class InterfazEVIEnviarEjecutivoVirtualAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 27/11/2014
 */
@ManagedBean
@SessionScoped
public class InterfazEVIEnviarEjecutivoVirtualAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -3269769609828041724L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazEVIEnviarEjecutivoVirtualForm interfazEVIEnviarEjecutivoVirtualForm = new InterfazEVIEnviarEjecutivoVirtualForm();
		return interfazEVIEnviarEjecutivoVirtualForm;
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
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		HashMap criteria = new HashMap();
		InterfazEVIEnviarEjecutivoVirtualForm interfazForm = (InterfazEVIEnviarEjecutivoVirtualForm) this.formInterfaz;
		criteria.put("codigoPais", usuario.getPais().getCodigo());
		// por defecto MARCA TODAS
		// por defecto CANAL VENTA DIRECTA

		interfazForm.setCodigoPeriodo(interfazSiCCService.getPeriodoDefaultByPaisCanal(usuario.getPais().getCodigo(),
																					   Constants.CODIGO_CANAL_DEFAULT));
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		criteria.put("codigoPeriodo", interfazForm.getCodigoPeriodo());
		criteria.put("campanias", Constants.PERIODO_SIGUIENTE);
		String desPeriodoSiguiente = reporteService.getOidString("getDesPeriodoByCodigoPeriodoX", criteria);
		interfazForm.setCodigoNuevoPeriodo(desPeriodoSiguiente);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params =  super.prepareParamsBeforeExecute(params, form);
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		return params;
	}
}
