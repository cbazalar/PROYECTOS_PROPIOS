package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazDATAdicionalesForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author RRG
 * 
 * @struts.form
 *   name = "interfazDATAdicionalesForm"
 * 	 extends = "baseInterfazForm"	
*/

public class InterfazDATAdicionalesForm extends BaseInterfazForm implements Serializable {
	
	private String codPais;

	/**
	 * @return Returns the codPais.
	 */
	public String getCodPais() {
		return codPais;
	}

	/**
	 * @param codPais The codPais to set.
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
}
