/**
 * 
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * 
 * <p>
 * <a href="InterfazACCRecepcionarReferidasForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jvelasquez@sigcomt.com">Jorge Velasquez</a>
 * 
 * @struts.form name = "interfazACCRecepcionarReferidasForm" extends = "baseInterfazForm"
 */
public class InterfazACCRecepcionarReferidasForm extends BaseInterfazForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoBatch;

	/**
	 * @return the codigoBatch
	 */
	public String getCodigoBatch() {
		return codigoBatch;
	}

	/**
	 * @param codigoBatch the codigoBatch to set
	 */
	public void setCodigoBatch(String codigoBatch) {
		this.codigoBatch = codigoBatch;
	}

}
