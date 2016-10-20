package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazADAEnviarInformacionAdamForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 03/12/2014
 */
public class InterfazADAEnviarInformacionAdamForm extends BaseInterfazForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoPeriodo;
	
	/**
	 * @return codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
}