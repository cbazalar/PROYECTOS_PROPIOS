package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOBEnviarCobranzaPeriodoZonaForm;

@ManagedBean
@SessionScoped
public class InterfazCOBEnviarCobranzaPeriodoZonaAction  extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7778787200020762374L;
	private List siccSociedadList;


	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazCOBEnviarCobranzaPeriodoZonaForm form=new InterfazCOBEnviarCobranzaPeriodoZonaForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		log.debug("qqqqqqqqqqqqqqqSSSSSSSSSSSSSSSSSSSSSSSSSSSSSFFFFFFFFFFFFFFFFFFFFFFFF");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
		InterfazCOBEnviarCobranzaPeriodoZonaForm form=(InterfazCOBEnviarCobranzaPeriodoZonaForm) this.formInterfaz;
		form.setAnho(sdf1.format(new Date(System.currentTimeMillis())));
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
		form.setMes(sdf2.format(new Date(System.currentTimeMillis())));
		form.setCodigoPais(pais.getCodigo());
		siccSociedadList=service.getSociedadesByCodigoPais(pais.getCodigo());
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) 
	throws Exception{
		InterfazCOBEnviarCobranzaPeriodoZonaForm form1= (InterfazCOBEnviarCobranzaPeriodoZonaForm)this.formInterfaz;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");		
		
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("codigoSociedad", form1.getCodigoSociedad());
		params.put("anho",form1.getAnho());
		params.put("mes",form1.getMes());
		params.put("sociedad",service.getSociedadEquivalenciaSAP(params));

		if (log.isDebugEnabled()) {
				log.debug(params.toString() );
		}		
		return params;
		
	}
	
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
}
