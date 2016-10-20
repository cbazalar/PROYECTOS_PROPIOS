package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


/**
 * The Class InterfazCOBEnviarInformacionProveedoresCobranzaForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 27/11/2014
 */
public class InterfazCOBEnviarInformacionProveedoresCobranzaForm extends BaseInterfazForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoSociedad;

	/**
	 * @return codigoSociedad
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