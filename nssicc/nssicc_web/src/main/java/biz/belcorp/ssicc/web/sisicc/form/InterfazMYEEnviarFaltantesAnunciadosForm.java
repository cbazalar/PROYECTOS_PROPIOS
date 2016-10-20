/*
 * Created on 04-jun-2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazMYEEnviarFaltantesAnunciadosForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 * @struts.form
 *   name = "interfazMYEEnviarFaltantesAnunciadosForm"
 * 	 extends = "baseInterfazForm"	
*/

public class InterfazMYEEnviarFaltantesAnunciadosForm extends BaseInterfazForm implements Serializable {
	
	/**
	 * 
	 */
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
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
}
