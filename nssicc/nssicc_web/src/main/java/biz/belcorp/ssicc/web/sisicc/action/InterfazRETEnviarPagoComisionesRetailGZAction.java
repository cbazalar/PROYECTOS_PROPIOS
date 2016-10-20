package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRETEnviarPagoComisionesRetailGZForm;

/**

  */
@ManagedBean
@SessionScoped
public class InterfazRETEnviarPagoComisionesRetailGZAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9126843767060094903L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazRETEnviarPagoComisionesRetailGZForm interfazRETEnviarPagoComisionesRetailGZForm = new InterfazRETEnviarPagoComisionesRetailGZForm();
		return interfazRETEnviarPagoComisionesRetailGZForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		  if (log.isDebugEnabled()) {
	            log.debug("Entering 'setViewAttributes' method");
	        }

	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map,
	 * biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		
		InterfazRETEnviarPagoComisionesRetailGZForm f = (InterfazRETEnviarPagoComisionesRetailGZForm) this.formInterfaz;
		String fechaIni=DateUtil.convertDateToString(f.getFechaInicioD());
		String fechaFin=DateUtil.convertDateToString(f.getFechaFinalD());
		f.setFechaInicio(fechaIni);
		f.setFechaFinal(fechaFin);
		params = super.prepareParamsBeforeExecute(params, form);
		return params;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#setValidarInterfaz()
	 */
	@Override
	public String setValidarInterfaz() {

		InterfazRETEnviarPagoComisionesRetailGZForm f = (InterfazRETEnviarPagoComisionesRetailGZForm) this.formInterfaz;
		String mensaje = null;
		String fechaIni=DateUtil.convertDateToString(f.getFechaInicioD());
		String fechaFin=DateUtil.convertDateToString(f.getFechaFinalD());
		
		if(fechaIni.compareTo(fechaFin)>0)			
			mensaje = getResourceMessage("interfazRETEnviarPagoComisionesRetailGZForm.validar.fechas");
					
		return mensaje;
	}
}

