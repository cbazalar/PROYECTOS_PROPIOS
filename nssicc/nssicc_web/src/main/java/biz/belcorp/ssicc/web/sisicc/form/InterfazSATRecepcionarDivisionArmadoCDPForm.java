package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSATRecepcionarDivisionArmadoCDPForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 * @struts.form name = "interfazSATRecepcionarDivisionArmadoCDPForm" extends = "baseInterfazForm"
 *             
 */
public class InterfazSATRecepcionarDivisionArmadoCDPForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 2296246402424841914L;
	
	private String codigoPais;

	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
}