package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazCOBGenerarInformacionAcovediForm.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */

public class InterfazCOBGenerarInformacionAcovediForm extends BaseProcesoForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
}