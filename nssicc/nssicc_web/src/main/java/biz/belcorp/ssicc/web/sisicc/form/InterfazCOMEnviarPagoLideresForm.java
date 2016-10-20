/*
 * Created on 30-nov-2005
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
 * <a href="InterfazCOMEnviarPagoLideresForm.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 * @struts.form name = "interfazCOMEnviarPagoLideresForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazCOMEnviarPagoLideresForm extends BaseInterfazForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4765921501523225600L;
	private String codigoPeriodo;
	private String codigoComisionIngreso;
	private String codigoComisionRecu1;
	private String codigoComisionRecu2;

	/**
	 * @return Returns the codigoComisionIngreso.
	 */
	public String getCodigoComisionIngreso() {
		return codigoComisionIngreso;
	}

	/**
	 * @param codigoComisionIngreso
	 *            The codigoComisionIngreso to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoComisionIngreso(String codigoComisionIngreso) {
		this.codigoComisionIngreso = codigoComisionIngreso;
	}

	/**
	 * @return Returns the codigoComisionRecu1.
	 */
	public String getCodigoComisionRecu1() {
		return codigoComisionRecu1;
	}

	/**
	 * @param codigoComisionRecu1
	 *            The codigoComisionRecu1 to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoComisionRecu1(String codigoComisionRecu1) {
		this.codigoComisionRecu1 = codigoComisionRecu1;
	}

	/**
	 * @return Returns the codigoComisionRecu2.
	 */
	public String getCodigoComisionRecu2() {
		return codigoComisionRecu2;
	}

	/**
	 * @param codigoComisionRecu2
	 *            The codigoComisionRecu2 to set.
	 */
	public void setCodigoComisionRecu2(String codigoComisionRecu2) {
		this.codigoComisionRecu2 = codigoComisionRecu2;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

}
