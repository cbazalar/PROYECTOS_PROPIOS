package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazCOBEnviarArchivosInfocorpForm extends BaseInterfazForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String campanya;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the campanya
	 */
	public String getCampanya() {
		return campanya;
	}
	
	/**
	 * @param campanya the campanya to set
	 */
	public void setCampanya(String campanya) {
		this.campanya = campanya;
	}
}
