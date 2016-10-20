package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOMEnviarLalnForm;

@SessionScoped
@ManagedBean
public class InterfazCOMEnviarLalnAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2556874232717521451L;
	private List siccPeriodoList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazCOMEnviarLalnForm form = new InterfazCOMEnviarLalnForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
	    InterfazService interfazService = (InterfazService) getBean("sisicc.interfazService");
		InterfazCOMEnviarLalnForm f = (InterfazCOMEnviarLalnForm) this.formInterfaz;
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		String codigoPais = f.getCodigoPais();
		String codigoSistema = f.getCodigoSistema();
		String codigoInterfaz = f.getCodigoInterfaz();
		InterfazPK interfazPK = new InterfazPK(codigoPais, codigoSistema, codigoInterfaz);
		Interfaz interfaz = interfazService.getInterfaz(interfazPK);
		

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		for (int j = 0; j < interfaz.getParametros().size(); j++) {
			ParametroInterfaz parametroInterfaz = (ParametroInterfaz) interfaz.getParametros().get(j);
			criteria.put(parametroInterfaz.getNombre(), parametroInterfaz.getValor());
		}
		this.siccPeriodoList=svc.getPeriodosByPMC(criteria);
		f.setCodigoPeriodo(svc.getPeriodoDefaultByPMC(criteria));
		
	}

	/**
	 * @return the siccPeriodoList
	 */
	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * @param siccPeriodoList the siccPeriodoList to set
	 */
	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}
	
	

}
