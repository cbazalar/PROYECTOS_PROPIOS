package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPEJBancoCierreRegionForm.java.html"><i>View Source </i></a>
 * </p>
 * 
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 * 
 * @struts.form name = "interfazPEJBancoCierreRegionForm" extends = "baseInterfazForm"
 */

public class InterfazPEJBancoCierreRegionForm extends BaseInterfazForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPeriodo;
	
	private String codigoRegion;

	/**
	 * @return
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

	/**
	 * @return
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 * @struts.validator type = "required"
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}	
}