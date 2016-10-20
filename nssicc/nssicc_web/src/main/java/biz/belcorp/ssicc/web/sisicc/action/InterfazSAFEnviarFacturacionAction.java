package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAFEnviarFacturacionForm;

@ManagedBean
@SessionScoped
public class InterfazSAFEnviarFacturacionAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1422854598154683823L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazSAFEnviarFacturacionForm form=new InterfazSAFEnviarFacturacionForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
	 if (log.isDebugEnabled()) {
         log.debug("Entering 'setViewAttributes' method");
     }
 	 
	 InterfazSAFEnviarFacturacionForm form=(InterfazSAFEnviarFacturacionForm) this.formInterfaz;
	 Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 Date fecha = new Date(System.currentTimeMillis());	 
	 form.setFechaD(DateUtil.convertStringToDate(sdf.format(fecha)));
	 form.setCodigoPais(pais.getCodigo());
	}
				
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) 
			throws Exception{
		InterfazSAFEnviarFacturacionForm interfazForm=(InterfazSAFEnviarFacturacionForm) this.formInterfaz;
		String fecha=DateUtil.convertDateToString(interfazForm.getFechaD());
		interfazForm.setFecha(fecha);
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}
