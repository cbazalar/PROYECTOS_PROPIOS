package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * 
 * <p>
 * <a href="interfazSAFEnviarComisionEjecutivaForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 * 
 * @struts.form name = "interfazSAFEnviarComisionEjecutivaForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazSAFEnviarComisionEjecutivaForm extends BaseInterfazForm
		implements Serializable {

	private static final long serialVersionUID = -7558203115635586307L;
	
	private String codigoPeriodo;

	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}



}
