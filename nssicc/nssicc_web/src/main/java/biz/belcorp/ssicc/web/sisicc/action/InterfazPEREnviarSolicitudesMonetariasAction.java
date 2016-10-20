package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPEREnviarSolicitudesMonetariasForm;

@ManagedBean
@SessionScoped
public class InterfazPEREnviarSolicitudesMonetariasAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = 7442075243959119800L;
	
	private List siccTipoOrigenDatos;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazPEREnviarSolicitudesMonetariasForm interfazForm= new  InterfazPEREnviarSolicitudesMonetariasForm();
		return interfazForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		// Carga del combo de Tipo de Origen de Datos
		this.siccTipoOrigenDatos=interfazSiCCService.getTipoOrigenDatos();		
	}
	
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);						
		return params;
    }

	public List getSiccTipoOrigenDatos() {
		return siccTipoOrigenDatos;
	}

	public void setSiccTipoOrigenDatos(List siccTipoOrigenDatos) {
		this.siccTipoOrigenDatos = siccTipoOrigenDatos;
	}
	
}
