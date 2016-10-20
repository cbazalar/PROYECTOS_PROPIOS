/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazSAWEnviarDemandaForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Jose Cairampoma</a>
 * 
 * @struts.form name = "interfazSAWEnviarDemandaForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazSAWEnviarDemandaForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoPeriodo;
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
}