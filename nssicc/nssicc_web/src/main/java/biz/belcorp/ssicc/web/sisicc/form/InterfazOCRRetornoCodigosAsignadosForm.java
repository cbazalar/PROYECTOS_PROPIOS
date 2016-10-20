package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazOCRRetornoCodigosAsignadosForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cluque@csigcomt.com">Christian Luque</a>
 * 
 * @struts.form name = "interfazOCRRetornoCodigosAsignadosForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazOCRRetornoCodigosAsignadosForm extends BaseInterfazForm implements
		Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String codigoPeriodo;

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}		
}
