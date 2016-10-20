package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * @author  <a href="mailto:jjames@sigcomt.com">Jesse James Rios Franco</a>
 * 
 * @struts.form name = "interfazCOBEnviarInformacionTransUnionForm" extends = "baseInterfazForm"
 *
 */
public class InterfazCOBEnviarInformacionTransUnionForm extends BaseInterfazForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoSociedad;

	/**
	 * @return
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad
	 * @struts.validator type = "required"
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}	
}