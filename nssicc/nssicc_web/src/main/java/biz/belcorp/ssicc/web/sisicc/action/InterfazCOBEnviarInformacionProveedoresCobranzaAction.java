package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazCOBEnviarInformacionProveedoresCobranzaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOBEnviarInformacionProveedoresCobranzaForm;


/**
 * The Class InterfazEVIEnviarEjecutivoVirtualAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 27/11/2014
 */
@ManagedBean
@SessionScoped
public class InterfazCOBEnviarInformacionProveedoresCobranzaAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -5839587738167202268L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCOBEnviarInformacionProveedoresCobranzaForm interfazCOBEnviarForm = new InterfazCOBEnviarInformacionProveedoresCobranzaForm();
		return interfazCOBEnviarForm;
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
		
		Map criteria = new HashMap();
		criteria.put("codigoUsuario", usuario.getLogin());

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
	 */
	protected void beforeExecuteInterfaz(Map params) {
		super.beforeExecuteInterfaz(params);
		log.debug("Entering 'beforeExecuteInterfaz' method - InterfazCOBEnviarInformacionProveedoresCobranzaAction");
		
		InterfazCOBEnviarInformacionProveedoresCobranzaService service = (InterfazCOBEnviarInformacionProveedoresCobranzaService) getBean("sisicc.interfazCOBEnviarInformacionProveedoresCobranzaService");
		service.executeInterfazCOBEnviarInformacionProveedoresCobranza(params);
	}

}