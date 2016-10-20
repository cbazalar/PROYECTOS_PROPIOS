package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * 
 * <p>
 * <a href="interfazCOBEnviarRecuCobranzaFFVVDatamartForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Yahir Rivas Luna</a>
 * 
 * @struts.form name = "interfazCOBEnviarRecuCobranzaFFVVDatamartForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazCOBEnviarRecuCobranzaFFVVDatamartForm extends BaseInterfazForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7558203115635586307L;
	private String codigoPeriodo;

	

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}



	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}



}
