package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazACCEnviarTablasClientesForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:efernandezo@belcorp.biz">Sergio Apaza</a>
 * 
 * @struts.form name = "interfazACCEnviarTablasClientesForm" extends = "baseInterfazForm"
 */
public class InterfazACCEnviarTablasClientesForm extends BaseInterfazForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPeriodo;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

}

