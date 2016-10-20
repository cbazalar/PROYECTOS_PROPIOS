package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazBANRecepcionarMovimientosBancariosForm;

@ManagedBean
@SessionScoped
public class InterfazBANRecepcionarMovimientosBancariosAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1823518244772443711L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazBANRecepcionarMovimientosBancariosForm form= new InterfazBANRecepcionarMovimientosBancariosForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		InterfazBANRecepcionarMovimientosBancariosForm form= (InterfazBANRecepcionarMovimientosBancariosForm) this.formInterfaz;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActual=sdf.format(new Date(System.currentTimeMillis()));
		form.setFechaGenerar(fechaActual);
		form.setFechaGenerarD(DateUtil.convertStringToDate(fechaActual));
	}
	
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) 
			throws Exception{	
		InterfazBANRecepcionarMovimientosBancariosForm form1= (InterfazBANRecepcionarMovimientosBancariosForm) this.formInterfaz;	
		form1.setFechaGenerar(DateUtil.convertDateToString(form1.getFechaGenerarD()));
		params=super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	

}


	
	
