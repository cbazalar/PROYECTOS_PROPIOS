package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRETEnviarComisionesForm;

/**

  */
@ManagedBean
@SessionScoped
public class InterfazRETEnviarComisionesAction extends
		BaseInterfazAbstractAction {



	/**
	 * 
	 */
	private static final long serialVersionUID = 9046611317055958195L;



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazRETEnviarComisionesForm interfazRETEnviarComisionesForm = new InterfazRETEnviarComisionesForm();
		return interfazRETEnviarComisionesForm;
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
		
		InterfazRETEnviarComisionesForm f = (InterfazRETEnviarComisionesForm) this.formInterfaz;
		String fechaIni=DateUtil.convertDateToString(f.getFechaInicioD());
		String fechaFin=DateUtil.convertDateToString(f.getFechaFinD());
		f.setFechaInicio(fechaIni);
		f.setFechaFin(fechaFin);
		params = super.prepareParamsBeforeExecute(params, form);
		return params;

	}

	
	

}


