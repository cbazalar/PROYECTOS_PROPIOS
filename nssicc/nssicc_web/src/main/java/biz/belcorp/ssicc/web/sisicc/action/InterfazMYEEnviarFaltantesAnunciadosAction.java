package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYEEnviarFaltantesAnunciadosForm;

@ManagedBean
@SessionScoped
public class InterfazMYEEnviarFaltantesAnunciadosAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9124093147428045639L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazMYEEnviarFaltantesAnunciadosForm formInterfaz = new InterfazMYEEnviarFaltantesAnunciadosForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		InterfazMYEEnviarFaltantesAnunciadosForm f = (InterfazMYEEnviarFaltantesAnunciadosForm) this.formInterfaz;
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
				
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		f.setCodigoPeriodo(ajax.getPeriodoDefaultByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT));
		if (StringUtils.isEmpty(f.getCodigoPeriodo())) f.setCodigoPeriodo(periodo);
	
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	

}
