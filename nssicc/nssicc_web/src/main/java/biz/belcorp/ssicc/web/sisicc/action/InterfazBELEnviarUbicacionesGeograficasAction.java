package biz.belcorp.ssicc.web.sisicc.action;


import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazBELEnviarUbicacionesGeograficasForm;

@ManagedBean
@SessionScoped
public class InterfazBELEnviarUbicacionesGeograficasAction  extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1003739074511625622L;
	private List siccTipoClienteList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazBELEnviarUbicacionesGeograficasForm form= new InterfazBELEnviarUbicacionesGeograficasForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		 if (log.isDebugEnabled()) {
	            log.debug("Entering 'setViewAttributes' method");
	        }
		 Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		// Carga de los combos Tipo Clientes
		 InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		 siccTipoClienteList=svc.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute (Map params,BaseForm form)
			throws Exception{
		params= super.prepareParamsBeforeExecute(params, form);
		HistoricoService historicoService = (HistoricoService) getBean("sisicc.historicoService");
		List historicos = historicoService.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
		        if (historicos.size() == 1)
		            params.put("fechaInicioUltimoProceso", ((Historico) historicos.get(0)).getFechaInicioProceso());
		    } else {
		        params.put("fechaInicioUltimoProceso", null);
		    }
		return params;
	}

	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

}












