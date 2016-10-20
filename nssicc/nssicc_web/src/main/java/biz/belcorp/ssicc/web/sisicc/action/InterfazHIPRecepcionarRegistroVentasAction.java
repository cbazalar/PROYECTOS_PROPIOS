package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazHIPRecepcionarRegistroVentasForm;

@ManagedBean
@SessionScoped
public class InterfazHIPRecepcionarRegistroVentasAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = 2695333782370395901L;
	
	private List siccSociedadList;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazHIPRecepcionarRegistroVentasForm interfazForm= new  InterfazHIPRecepcionarRegistroVentasForm();
		return interfazForm;
	}
	
	@Override
	 protected void setViewAtributes() throws Exception {
		 
	        if (log.isDebugEnabled()) {
	            log.debug("Entering 'setViewAttributes' method");
	        }	     
			InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	        InterfazHIPRecepcionarRegistroVentasForm f =(InterfazHIPRecepcionarRegistroVentasForm)this.formInterfaz;
	        this.siccSociedadList=service.getSociedadesByCodigoPais(pais.getCodigo());
	        f.setCodigoSociedad(Constants.CODIGO_SOCIEDAD_DEFAULT);	        
	        
	    }
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);						
		return params;
    }

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
	
}
