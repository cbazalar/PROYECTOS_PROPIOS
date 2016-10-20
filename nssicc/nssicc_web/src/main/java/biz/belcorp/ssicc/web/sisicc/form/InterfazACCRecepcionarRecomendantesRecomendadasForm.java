package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazACCRecepcionarRecomendantesRecomendadasForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 22/12/2014
 */
public class InterfazACCRecepcionarRecomendantesRecomendadasForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;
	private String codigoPeriodo;	

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 * struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

}

