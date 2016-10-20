package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazCOBEnviarArchivoTransaccionCobranzaForm extends
		BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoSociedad;
	private String anhio;
	private String mes;

	/**
	 * @return anhio
	 */
	public String getAnhio() {
		return anhio;
	}

	/**
	 * @struts.validator type = "required"
	 */
	public void setAnhio(String anhio) {
		this.anhio = anhio;
	}

	/**
	 * @return mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @struts.validator type = "required"
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return codigoSoci
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @struts.validator type = "required"
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
}