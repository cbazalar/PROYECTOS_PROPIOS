package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOBEnviarRecuperacionCobranzaPorCobradorForm;

@ManagedBean
@SessionScoped
public class InterfazCOBEnviarRecuperacionCobranzaPorCobradorAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2196954254521587121L;
	private List siccSociedadList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazCOBEnviarRecuperacionCobranzaPorCobradorForm form=new InterfazCOBEnviarRecuperacionCobranzaPorCobradorForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteria));
		ArrayList listSociedad = (ArrayList)interfazSiCCService.getSociedadesByCodigoPais(pais.getCodigo());
		siccSociedadList=listSociedad;
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) 
			throws Exception{
		InterfazCOBEnviarRecuperacionCobranzaPorCobradorForm form1=(InterfazCOBEnviarRecuperacionCobranzaPorCobradorForm) this.formInterfaz;
		params =super.prepareParamsBeforeExecute(params, form);
		params.put("codigoSociedad", form1.getCodigoSociedad());
		
		return params;
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	
}

