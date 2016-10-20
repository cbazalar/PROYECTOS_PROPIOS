package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazBELEnviarDireccionClientesForm;

@ManagedBean
@SessionScoped
public class InterfazBELEnviarDireccionClientesAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4361466250211747071L;
	private List siccTipoClienteList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazBELEnviarDireccionClientesForm form=new InterfazBELEnviarDireccionClientesForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'setViewAttributes' method");
		}
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		// Carga de los combos Marca, Canal, Acceso y Periodo
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		siccTipoClienteList= svc.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		InterfazBELEnviarDireccionClientesForm form=(InterfazBELEnviarDireccionClientesForm) this.formInterfaz;
		 form.setCodigoTipoCliente(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute (Map params,BaseForm form)
			throws Exception{
		
		HistoricoService historicoService = (HistoricoService) getBean("sisicc.historicoService");
		List historicos = historicoService.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
		        if (historicos.size() == 1)
		            params.put("fechaInicioUltimoProceso", ((Historico) historicos.get(0)).getFechaInicioProceso());
		    } else {
		        params.put("fechaInicioUltimoProceso", null);
		    }
		params= super.prepareParamsBeforeExecute(params, form);
		return params;
	}

	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	
}

















