package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazAPEEnviarCostoCajaForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarCostoCajaAction.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
  */
@ManagedBean
@SessionScoped
public class InterfazAPEEnviarCostoCajaAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1476325373049785615L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazAPEEnviarCostoCajaForm formInterfaz = new InterfazAPEEnviarCostoCajaForm(); 
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		InterfazAPEEnviarCostoCajaForm f = (InterfazAPEEnviarCostoCajaForm) this.formInterfaz;
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		if(f.getFechaFacturacionDate()!=null)
		{
			f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		}
		params =  super.prepareParamsBeforeExecute(params, form);
		return params;
	}

}
